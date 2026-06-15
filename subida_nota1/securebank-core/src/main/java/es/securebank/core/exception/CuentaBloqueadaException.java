package es.securebank.core.exception;

public class CuentaBloqueadaException extends SecureBankException {
    public CuentaBloqueadaException(String iban) {
        super("Cuenta bloqueada: " + iban);
    }
}
