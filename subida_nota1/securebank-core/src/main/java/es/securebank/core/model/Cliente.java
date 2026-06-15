package es.securebank.core.model;

import es.securebank.core.model.enums.EstadoCliente;
import java.time.ZonedDateTime;

public class Cliente {
    private int id;
    private String dni;
    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;
    private String pinHash;
    private ZonedDateTime fechaAlta;
    private EstadoCliente estado;

    public Cliente() {}

    public Cliente(int id, String dni, String nombre, String apellidos,
                   String email, String telefono, String pinHash,
                   ZonedDateTime fechaAlta, EstadoCliente estado) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono = telefono;
        this.pinHash = pinHash;
        this.fechaAlta = fechaAlta;
        this.estado = estado;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getPinHash() { return pinHash; }
    public void setPinHash(String pinHash) { this.pinHash = pinHash; }
    public ZonedDateTime getFechaAlta() { return fechaAlta; }
    public void setFechaAlta(ZonedDateTime fechaAlta) { this.fechaAlta = fechaAlta; }
    public EstadoCliente getEstado() { return estado; }
    public void setEstado(EstadoCliente estado) { this.estado = estado; }

    @Override
    public String toString() {
        return String.format("Cliente{id=%d, dni='%s', nombre='%s %s', estado=%s}",
                id, dni, nombre, apellidos, estado);
    }
}