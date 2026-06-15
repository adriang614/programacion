package es.securebank.core.exception;

public class TransactionFailedException extends SecureBankException {
    public TransactionFailedException(String message) { super(message); }
    public TransactionFailedException(String message, Throwable cause) { super(message, cause); }
}
