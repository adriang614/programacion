package es.securebank.core.exception;

import es.securebank.core.model.TransaccionTemporal;

import java.time.LocalDateTime;
import java.util.List;

public class FraudeConcurrentException extends RuntimeException {

    private final int clienteId;
    private final String iban;
    private final List<TransaccionTemporal> transaccionesSospechosas;
    private final LocalDateTime timestampDeteccion;

    public FraudeConcurrentException(
            int clienteId,
            String iban,
            List<TransaccionTemporal> transaccionesSospechosas) {

        super("FRAUDE DETECTADO EN CUENTA " + iban);

        this.clienteId = clienteId;
        this.iban = iban;
        this.transaccionesSospechosas = transaccionesSospechosas;
        this.timestampDeteccion = LocalDateTime.now();
    }

    public int getClienteId() {
        return clienteId;
    }

    public String getIban() {
        return iban;
    }

    public List<TransaccionTemporal> getTransaccionesSospechosas() {
        return transaccionesSospechosas;
    }

    public LocalDateTime getTimestampDeteccion() {
        return timestampDeteccion;
    }

    @Override
    public String toString() {
        return "FraudeConcurrentException{" +
                "clienteId=" + clienteId +
                ", iban='" + iban + '\'' +
                ", transaccionesSospechosas=" + transaccionesSospechosas +
                ", timestampDeteccion=" + timestampDeteccion +
                '}';
    }
}
