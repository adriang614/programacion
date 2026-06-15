package es.securebank.core.model;

import es.securebank.core.model.enums.EstadoCuenta;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class CuentaBancaria {
    private int id;
    private String iban;
    private int clienteId;
    private String tipo;
    private BigDecimal saldo;
    private BigDecimal saldoRetenido;
    private ZonedDateTime fechaApertura;
    private EstadoCuenta estado;

    public CuentaBancaria() {}

    public CuentaBancaria(int id, String iban, int clienteId, String tipo,
                          BigDecimal saldo, BigDecimal saldoRetenido,
                          ZonedDateTime fechaApertura, EstadoCuenta estado) {
        this.id = id;
        this.iban = iban;
        this.clienteId = clienteId;
        this.tipo = tipo;
        this.saldo = saldo;
        this.saldoRetenido = saldoRetenido;
        this.fechaApertura = fechaApertura;
        this.estado = estado;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getIban() { return iban; }
    public void setIban(String iban) { this.iban = iban; }
    public int getClienteId() { return clienteId; }
    public void setClienteId(int clienteId) { this.clienteId = clienteId; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public BigDecimal getSaldo() { return saldo; }
    public void setSaldo(BigDecimal saldo) { this.saldo = saldo; }
    public BigDecimal getSaldoRetenido() { return saldoRetenido; }
    public void setSaldoRetenido(BigDecimal saldoRetenido) { this.saldoRetenido = saldoRetenido; }
    public ZonedDateTime getFechaApertura() { return fechaApertura; }
    public void setFechaApertura(ZonedDateTime fechaApertura) { this.fechaApertura = fechaApertura; }
    public EstadoCuenta getEstado() { return estado; }
    public void setEstado(EstadoCuenta estado) { this.estado = estado; }

    public BigDecimal getSaldoDisponible() {
        return saldo.subtract(saldoRetenido);
    }

    @Override
    public String toString() {
        return String.format("CuentaBancaria{iban='%s', tipo='%s', saldo=%s, estado=%s}",
                iban, tipo, saldo, estado);
    }
}