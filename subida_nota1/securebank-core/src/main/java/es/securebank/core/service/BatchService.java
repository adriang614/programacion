package es.securebank.core.service;

import es.securebank.core.model.CuentaBancaria;
import es.securebank.core.model.enums.EstadoCuenta;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// Este servicio cobra 2€ de comision a todas las cuentas
// Lo hace en segundo plano sin bloquear la consola
public class BatchService {

    private final CacheService cacheService;
    private final DataSource dataSource;
    private static final BigDecimal COMISION = new BigDecimal("2.00");

    public BatchService(CacheService cacheService, DataSource dataSource) {
        this.cacheService = cacheService;
        this.dataSource = dataSource;
    }

    public void cobrarComisiones() {
        // Lanzamos el proceso en un hilo separado para no bloquear la consola
        Thread hilo = new Thread(() -> {
            long inicio = System.currentTimeMillis();
            System.out.println("[BATCH] Iniciando cobro de comisiones...");

            int procesadas = 0;
            int bloqueadas = 0;

            // Cogemos todas las cuentas de la cache
            Map<String, CuentaBancaria> cache = cacheService.getCacheCuentas();

            // Creamos una lista de IBANs para iterar de forma segura
            List<String> ibans = new ArrayList<>(cache.keySet());

            // Pool de 4 hilos para procesar en paralelo
            ExecutorService pool = Executors.newFixedThreadPool(4);

            for (String iban : ibans) {
                pool.submit(() -> {
                    CuentaBancaria cuenta = cache.get(iban);
                    if (cuenta == null) return;

                    // Bloqueamos la cuenta individualmente para evitar problemas
                    synchronized (cuenta) {
                        try (Connection conn = dataSource.getConnection()) {
                            conn.setAutoCommit(false);
                            try {
                                BigDecimal saldoNuevo = cuenta.getSaldo().subtract(COMISION);

                                // Actualizar saldo en BD
                                String sqlSaldo = "UPDATE cuentas_bancarias SET saldo = ? WHERE iban = ?";
                                try (PreparedStatement ps = conn.prepareStatement(sqlSaldo)) {
                                    ps.setBigDecimal(1, saldoNuevo);
                                    ps.setString(2, iban);
                                    ps.executeUpdate();
                                }

                                // Registrar comision en transacciones
                                String sqlTrans = "INSERT INTO transacciones (cuenta_id, tipo, importe, saldo_posterior, concepto) " +
                                        "VALUES ((SELECT id FROM cuentas_bancarias WHERE iban = ?), 'COMISION', ?, ?, 'Comision mantenimiento mensual')";
                                try (PreparedStatement ps = conn.prepareStatement(sqlTrans)) {
                                    ps.setString(1, iban);
                                    ps.setBigDecimal(2, COMISION.negate());
                                    ps.setBigDecimal(3, saldoNuevo);
                                    ps.executeUpdate();
                                }

                                // Si el saldo queda negativo, bloqueamos la cuenta
                                if (saldoNuevo.compareTo(BigDecimal.ZERO) < 0) {
                                    String sqlBloquear = "UPDATE cuentas_bancarias SET estado = 'BLOQUEADA' WHERE iban = ?";
                                    try (PreparedStatement ps = conn.prepareStatement(sqlBloquear)) {
                                        ps.setString(1, iban);
                                        ps.executeUpdate();
                                    }
                                    conn.commit();

                                    // Eliminar de cache
                                    cacheService.removeCuenta(iban);
                                    System.out.println("[BATCH] Cuenta " + iban + " bloqueada por saldo negativo.");
                                } else {
                                    conn.commit();
                                    cuenta.setSaldo(saldoNuevo);
                                }

                            } catch (Exception e) {
                                conn.rollback();
                                System.out.println("[BATCH] Error procesando " + iban + ": " + e.getMessage());
                            }
                        } catch (Exception e) {
                            System.out.println("[BATCH] Error de conexion en " + iban + ": " + e.getMessage());
                        }
                    }
                });
            }

            // Esperamos a que terminen todos los hilos
            pool.shutdown();
            try {
                pool.awaitTermination(5, TimeUnit.MINUTES);
            } catch (InterruptedException e) {
                System.out.println("[BATCH] El proceso fue interrumpido.");
            }

            long tiempo = System.currentTimeMillis() - inicio;
            System.out.println("[BATCH] Proceso completado en " + tiempo + "ms");
            System.out.println("[BATCH] Cuentas procesadas: " + ibans.size());
        });

        hilo.setDaemon(true);
        hilo.start();
        System.out.println("[BATCH] Proceso lanzado en segundo plano. La consola sigue activa.");
    }
}
