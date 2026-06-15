package es.securebank.core.exception;

public class SecureBankException extends RuntimeException {
    public SecureBankException(String message) { super(message); }
    public SecureBankException(String message, Throwable cause) { super(message, cause); }
}
