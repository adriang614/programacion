package es.securebank.core.exception;

import java.math.BigDecimal;

public class SaldoInsuficienteException extends SecureBankException {
    public SaldoInsuficienteException(String iban, BigDecimal saldo, BigDecimal importe) {
        super(String.format("Saldo insuficiente en %s. Disponible: %s, Requerido: %s", iban, saldo, importe));
    }
}
