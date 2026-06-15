package es.securebank.core.exception;

public class CuentaNoEncontradaException extends SecureBankException {
    public CuentaNoEncontradaException(String iban) {
        super("Cuenta no encontrada: " + iban);
    }
}
