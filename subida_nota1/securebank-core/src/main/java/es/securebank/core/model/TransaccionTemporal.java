package es.securebank.core.model;

import es.securebank.core.model.enums.TipoTransaccion;
import java.math.BigDecimal;

public record TransaccionTemporal(
        long timestampMillis,
        BigDecimal importe,
        TipoTransaccion tipo
) {
}
