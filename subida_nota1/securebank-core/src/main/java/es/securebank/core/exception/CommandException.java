package es.securebank.core.exception;

public class CommandException extends SecureBankException {
    public CommandException(String message) { super(message); }
    public CommandException(String message, Throwable cause) { super(message, cause); }
}
