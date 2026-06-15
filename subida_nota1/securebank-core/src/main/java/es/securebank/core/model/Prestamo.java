package es.securebank.core.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Prestamo {
    private int id;
    private int clienteId;
    private int cuentaId;
    private BigDecimal capitalInicial;
    private BigDecimal capitalPendiente;
    private BigDecimal interesAnual;
    private int cuotasTotales;
    private int cuotasPagadas;
    private BigDecimal cuotaMensual;
    private LocalDate fechaConcesion;
    private LocalDate fechaVencimiento;
    private String estado;

    public Prestamo() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getClienteId() { return clienteId; }
    public void setClienteId(int clienteId) { this.clienteId = clienteId; }
    public int getCuentaId() { return cuentaId; }
    public void setCuentaId(int cuentaId) { this.cuentaId = cuentaId; }
    public BigDecimal getCapitalInicial() { return capitalInicial; }
    public void setCapitalInicial(BigDecimal capitalInicial) { this.capitalInicial = capitalInicial; }
    public BigDecimal getCapitalPendiente() { return capitalPendiente; }
    public void setCapitalPendiente(BigDecimal capitalPendiente) { this.capitalPendiente = capitalPendiente; }
    public BigDecimal getInteresAnual() { return interesAnual; }
    public void setInteresAnual(BigDecimal interesAnual) { this.interesAnual = interesAnual; }
    public int getCuotasTotales() { return cuotasTotales; }
    public void setCuotasTotales(int cuotasTotales) { this.cuotasTotales = cuotasTotales; }
    public int getCuotasPagadas() { return cuotasPagadas; }
    public void setCuotasPagadas(int cuotasPagadas) { this.cuotasPagadas = cuotasPagadas; }
    public BigDecimal getCuotaMensual() { return cuotaMensual; }
    public void setCuotaMensual(BigDecimal cuotaMensual) { this.cuotaMensual = cuotaMensual; }
    public LocalDate getFechaConcesion() { return fechaConcesion; }
    public void setFechaConcesion(LocalDate fechaConcesion) { this.fechaConcesion = fechaConcesion; }
    public LocalDate getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(LocalDate fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return String.format("Prestamo{id=%d, capital=%s, pendiente=%s, estado=%s}",
                id, capitalInicial, capitalPendiente, estado);
    }
}
