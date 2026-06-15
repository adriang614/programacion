package es.securebank.core.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TarjetaCredito {
    private int id;
    private int cuentaId;
    private String numero;
    private String cvvHash;
    private BigDecimal limiteCredito;
    private BigDecimal saldoDispuesto;
    private LocalDate fechaEmision;
    private LocalDate fechaCaducidad;
    private String estado;

    public TarjetaCredito() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getCuentaId() { return cuentaId; }
    public void setCuentaId(int cuentaId) { this.cuentaId = cuentaId; }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public String getCvvHash() { return cvvHash; }
    public void setCvvHash(String cvvHash) { this.cvvHash = cvvHash; }
    public BigDecimal getLimiteCredito() { return limiteCredito; }
    public void setLimiteCredito(BigDecimal limiteCredito) { this.limiteCredito = limiteCredito; }
    public BigDecimal getSaldoDispuesto() { return saldoDispuesto; }
    public void setSaldoDispuesto(BigDecimal saldoDispuesto) { this.saldoDispuesto = saldoDispuesto; }
    public LocalDate getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(LocalDate fechaEmision) { this.fechaEmision = fechaEmision; }
    public LocalDate getFechaCaducidad() { return fechaCaducidad; }
    public void setFechaCaducidad(LocalDate fechaCaducidad) { this.fechaCaducidad = fechaCaducidad; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return String.format("TarjetaCredito{numero='%s', limite=%s, dispuesto=%s, estado=%s}",
                numero, limiteCredito, saldoDispuesto, estado);
    }
}
