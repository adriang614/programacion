package es.securebank.core.service;

import es.securebank.core.dao.CuentaDao;
import es.securebank.core.dao.TransaccionDao;
import es.securebank.core.exception.*;
import es.securebank.core.model.CuentaBancaria;
import es.securebank.core.model.Transaccion;
import es.securebank.core.model.enums.EstadoCuenta;
import es.securebank.core.model.enums.TipoTransaccion;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class TransaccionService {

    private final CacheService cacheService;
    private final CuentaDao cuentaDao;
    private final TransaccionDao transaccionDao;
    private final DataSource dataSource;

    public TransaccionService(CacheService cacheService, CuentaDao cuentaDao,
                               TransaccionDao transaccionDao, DataSource dataSource) {
        this.cacheService = cacheService;
        this.cuentaDao = cuentaDao;
        this.transaccionDao = transaccionDao;
        this.dataSource = dataSource;
    }

    // Ingresa dinero en una cuenta
    public void ingresar(String iban, BigDecimal importe, String concepto) {
        // 1. Verificar que la cuenta existe en cache
        CuentaBancaria cuenta = cacheService.getCuenta(iban);
        if (cuenta == null) throw new CuentaNoEncontradaException(iban);
        if (cuenta.getEstado() != EstadoCuenta.ACTIVA) throw new CuentaBloqueadaException(iban);

        // 2. Guardamos copia del estado anterior por si hay rollback
        BigDecimal saldoAnterior = cuenta.getSaldo();
        BigDecimal saldoNuevo = saldoAnterior.add(importe);

        // 3. Intentamos hacer todo en la base de datos
        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);
            try {
                // Actualizar saldo en BD
                String sqlSaldo = "UPDATE cuentas_bancarias SET saldo = ? WHERE iban = ?";
                try (PreparedStatement ps = conn.prepareStatement(sqlSaldo)) {
                    ps.setBigDecimal(1, saldoNuevo);
                    ps.setString(2, iban);
                    ps.executeUpdate();
                }

                // Registrar transaccion en BD
                String sqlTrans = "INSERT INTO transacciones (cuenta_id, tipo, importe, saldo_posterior, concepto) " +
                        "VALUES ((SELECT id FROM cuentas_bancarias WHERE iban = ?), ?, ?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(sqlTrans)) {
                    ps.setString(1, iban);
                    ps.setString(2, TipoTransaccion.INGRESO.name());
                    ps.setBigDecimal(3, importe);
                    ps.setBigDecimal(4, saldoNuevo);
                    ps.setString(5, concepto);
                    ps.executeUpdate();
                }

                conn.commit();

                // 4. Solo si el commit fue bien, actualizamos la cache
                cuenta.setSaldo(saldoNuevo);
                System.out.println("[TRANSACCION] Ingreso de " + importe + "€ en " + iban + " completado.");

            } catch (Exception e) {
                // 5. Si algo falla, rollback y la cache queda igual que antes
                conn.rollback();
                System.out.println("[ERROR] Rollback ejecutado. La cache NO se ha modificado.");
                throw new TransactionFailedException("Fallo al ingresar: " + e.getMessage(), e);
            }
        } catch (TransactionFailedException e) {
            throw e;
        } catch (Exception e) {
            throw new TransactionFailedException("Error de conexion al ingresar", e);
        }
    }

    // Retira dinero de una cuenta
    public void retirar(String iban, BigDecimal importe, String concepto) {
        CuentaBancaria cuenta = cacheService.getCuenta(iban);
        if (cuenta == null) throw new CuentaNoEncontradaException(iban);
        if (cuenta.getEstado() != EstadoCuenta.ACTIVA) throw new CuentaBloqueadaException(iban);
        if (cuenta.getSaldo().compareTo(importe) < 0)
            throw new SaldoInsuficienteException(iban, cuenta.getSaldo(), importe);

        BigDecimal saldoAnterior = cuenta.getSaldo();
        BigDecimal saldoNuevo = saldoAnterior.subtract(importe);

        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);
            try {
                String sqlSaldo = "UPDATE cuentas_bancarias SET saldo = ? WHERE iban = ?";
                try (PreparedStatement ps = conn.prepareStatement(sqlSaldo)) {
                    ps.setBigDecimal(1, saldoNuevo);
                    ps.setString(2, iban);
                    ps.executeUpdate();
                }

                String sqlTrans = "INSERT INTO transacciones (cuenta_id, tipo, importe, saldo_posterior, concepto) " +
                        "VALUES ((SELECT id FROM cuentas_bancarias WHERE iban = ?), ?, ?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(sqlTrans)) {
                    ps.setString(1, iban);
                    ps.setString(2, TipoTransaccion.RETIRO.name());
                    ps.setBigDecimal(3, importe.negate());
                    ps.setBigDecimal(4, saldoNuevo);
                    ps.setString(5, concepto);
                    ps.executeUpdate();
                }

                conn.commit();
                cuenta.setSaldo(saldoNuevo);
                System.out.println("[TRANSACCION] Retiro de " + importe + "€ de " + iban + " completado.");

            } catch (Exception e) {
                conn.rollback();
                System.out.println("[ERROR] Rollback ejecutado. La cache NO se ha modificado.");
                throw new TransactionFailedException("Fallo al retirar: " + e.getMessage(), e);
            }
        } catch (TransactionFailedException e) {
            throw e;
        } catch (Exception e) {
            throw new TransactionFailedException("Error de conexion al retirar", e);
        }
    }

    // Transfiere dinero entre dos cuentas
    public void transferir(String ibanOrigen, String ibanDestino, BigDecimal importe, String concepto) {
        CuentaBancaria origen = cacheService.getCuenta(ibanOrigen);
        CuentaBancaria destino = cacheService.getCuenta(ibanDestino);

        if (origen == null) throw new CuentaNoEncontradaException(ibanOrigen);
        if (destino == null) throw new CuentaNoEncontradaException(ibanDestino);
        if (origen.getEstado() != EstadoCuenta.ACTIVA) throw new CuentaBloqueadaException(ibanOrigen);
        if (origen.getSaldo().compareTo(importe) < 0)
            throw new SaldoInsuficienteException(ibanOrigen, origen.getSaldo(), importe);

        BigDecimal saldoOrigenNuevo = origen.getSaldo().subtract(importe);
        BigDecimal saldoDestinoNuevo = destino.getSaldo().add(importe);

        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);
            try {
                // Actualizar origen
                String sqlOrigen = "UPDATE cuentas_bancarias SET saldo = ? WHERE iban = ?";
                try (PreparedStatement ps = conn.prepareStatement(sqlOrigen)) {
                    ps.setBigDecimal(1, saldoOrigenNuevo);
                    ps.setString(2, ibanOrigen);
                    ps.executeUpdate();
                }

                // Actualizar destino
                try (PreparedStatement ps = conn.prepareStatement(sqlOrigen)) {
                    ps.setBigDecimal(1, saldoDestinoNuevo);
                    ps.setString(2, ibanDestino);
                    ps.executeUpdate();
                }

                // Registrar transaccion salida
                String sqlTrans = "INSERT INTO transacciones (cuenta_id, cuenta_destino_id, tipo, importe, saldo_posterior, concepto) " +
                        "VALUES ((SELECT id FROM cuentas_bancarias WHERE iban = ?), " +
                        "(SELECT id FROM cuentas_bancarias WHERE iban = ?), ?, ?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(sqlTrans)) {
                    ps.setString(1, ibanOrigen);
                    ps.setString(2, ibanDestino);
                    ps.setString(3, TipoTransaccion.TRANSFERENCIA_SALIDA.name());
                    ps.setBigDecimal(4, importe.negate());
                    ps.setBigDecimal(5, saldoOrigenNuevo);
                    ps.setString(6, concepto);
                    ps.executeUpdate();
                }

                conn.commit();

                // Actualizar cache solo si todo fue bien
                origen.setSaldo(saldoOrigenNuevo);
                destino.setSaldo(saldoDestinoNuevo);
                System.out.println("[TRANSACCION] Transferencia de " + importe + "€ de " + ibanOrigen + " a " + ibanDestino + " completada.");

            } catch (Exception e) {
                conn.rollback();
                System.out.println("[ERROR] Rollback ejecutado. La cache NO se ha modificado.");
                throw new TransactionFailedException("Fallo en transferencia: " + e.getMessage(), e);
            }
        } catch (TransactionFailedException e) {
            throw e;
        } catch (Exception e) {
            throw new TransactionFailedException("Error de conexion en transferencia", e);
        }
    }
}
