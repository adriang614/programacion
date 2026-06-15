package es.securebank.core.service;

import es.securebank.core.exception.FraudeConcurrentException;
import es.securebank.core.model.TransaccionTemporal;
import es.securebank.core.model.enums.TipoTransaccion;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AntiFraudeService {

    private final Map<Integer, Deque<TransaccionTemporal>> ventanaAntifraude =
            new ConcurrentHashMap<>();

    private final CacheService cacheService;
    private final DataSource dataSource;

    private static final BigDecimal IMPORTE_MINIMO =
            new BigDecimal("400.00");

    private static final int MAX_OPERACIONES = 3;

    private static final boolean TIEMPO_ACELERADO = true;

    private static final long VENTANA_MS =
            TIEMPO_ACELERADO ? 2_000 : 120_000;

    public AntiFraudeService(
            CacheService cacheService,
            DataSource dataSource) {

        this.cacheService = cacheService;
        this.dataSource = dataSource;
    }

    public void comprobarRetiro(
            int clienteId,
            String iban,
            BigDecimal importe) {

        if (importe.compareTo(IMPORTE_MINIMO) < 0) {
            return;
        }

        ventanaAntifraude.putIfAbsent(
                clienteId,
                new ArrayDeque<>());

        Deque<TransaccionTemporal> cola =
                ventanaAntifraude.get(clienteId);

        long ahora = System.currentTimeMillis();

        synchronized (cola) {

            Iterator<TransaccionTemporal> it =
                    cola.iterator();

            while (it.hasNext()) {

                TransaccionTemporal tx = it.next();

                if (ahora - tx.timestampMillis() > VENTANA_MS) {
                    it.remove();
                }
            }

            long sospechosas =
                    cola.stream()
                            .filter(t ->
                                    t.tipo() == TipoTransaccion.RETIRO &&
                                            t.importe().compareTo(IMPORTE_MINIMO) >= 0)
                            .count();

            if (sospechosas >= MAX_OPERACIONES) {

                bloquearCuentaPorFraude(
                        iban,
                        clienteId);

                throw new FraudeConcurrentException(
                        clienteId,
                        iban,
                        new ArrayList<>(cola));
            }

            cola.addLast(
                    new TransaccionTemporal(
                            ahora,
                            importe,
                            TipoTransaccion.RETIRO));
        }
    }

    private void bloquearCuentaPorFraude(
            String iban,
            int clienteId) {

        try (Connection conn =
                     dataSource.getConnection()) {

            String sqlBloquear =
                    "UPDATE cuentas_bancarias " +
                            "SET estado='BLOQUEADA' " +
                            "WHERE iban=?";

            try (PreparedStatement ps =
                         conn.prepareStatement(sqlBloquear)) {

                ps.setString(1, iban);
                ps.executeUpdate();
            }

            String sqlAudit =
                    "INSERT INTO auditoria_fraude " +
                            "(cliente_id, cuenta_id, tipo_fraude, descripcion, accion_tomada) " +
                            "VALUES (?, " +
                            "(SELECT id FROM cuentas_bancarias WHERE iban=?), " +
                            "'RETIROS_MULTIPLES', " +
                            "'Mas de tres retiros superiores a 400 euros en dos minutos', " +
                            "'CUENTA_BLOQUEADA')";

            try (PreparedStatement ps =
                         conn.prepareStatement(sqlAudit)) {

                ps.setInt(1, clienteId);
                ps.setString(2, iban);
                ps.executeUpdate();
            }

            cacheService.removeCuenta(iban);

            System.out.println(
                    "[ANTIFRAUDE] Cuenta bloqueada: "
                            + iban);

        } catch (Exception e) {

            System.out.println(
                    "[ANTIFRAUDE] Error: "
                            + e.getMessage());
        }
    }
}
