package es.securebank.core.model;

import es.securebank.core.model.enums.TipoTransaccion;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class Transaccion {
    private int id;
    private int cuentaId;
    private Integer cuentaDestinoId;
    private TipoTransaccion tipo;
    private BigDecimal importe;
    private BigDecimal saldoPosterior;
    private String concepto;
    private ZonedDateTime fecha;
    private String referencia;

    public Transaccion() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getCuentaId() { return cuentaId; }
    public void setCuentaId(int cuentaId) { this.cuentaId = cuentaId; }
    public Integer getCuentaDestinoId() { return cuentaDestinoId; }
    public void setCuentaDestinoId(Integer cuentaDestinoId) { this.cuentaDestinoId = cuentaDestinoId; }
    public TipoTransaccion getTipo() { return tipo; }
    public void setTipo(TipoTransaccion tipo) { this.tipo = tipo; }
    public BigDecimal getImporte() { return importe; }
    public void setImporte(BigDecimal importe) { this.importe = importe; }
    public BigDecimal getSaldoPosterior() { return saldoPosterior; }
    public void setSaldoPosterior(BigDecimal saldoPosterior) { this.saldoPosterior = saldoPosterior; }
    public String getConcepto() { return concepto; }
    public void setConcepto(String concepto) { this.concepto = concepto; }
    public ZonedDateTime getFecha() { return fecha; }
    public void setFecha(ZonedDateTime fecha) { this.fecha = fecha; }
    public String getReferencia() { return referencia; }
    public void setReferencia(String referencia) { this.referencia = referencia; }

    @Override
    public String toString() {
        return String.format("Transaccion{id=%d, tipo=%s, importe=%s, fecha=%s}", id, tipo, importe, fecha);
    }
}
