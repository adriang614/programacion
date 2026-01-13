package com.juego.habilidades;

public class CuraCC implements Habilidad {

    //----------ATRIBUTOS----------
    private String nombre;
    private int usos;
    private String descripcion;
    private int valor;
    private int escalabilidad;
    private int coste;

    //----------CONSTRUCTOR----------
    public CuraCC(String nombre, int usos, String descripcion, int valor, int escalabilidad, int coste) {
        this.nombre = nombre;
        this.usos = usos;
        this.descripcion = descripcion;
        this.valor = valor;
        this.escalabilidad = escalabilidad;
        this.coste = coste;
    }

    //----------GET Y SET----------
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getUsos() {
        return usos;
    }

    public void setUsos(int usos) {
        this.usos = usos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getEscalabilidad() {
        return escalabilidad;
    }

    public void setEscalabilidad(int escalabilidad) {
        this.escalabilidad = escalabilidad;
    }

    public int getCoste() {
        return coste;
    }

    public void setCoste(int coste) {
        this.coste = coste;
    }

    @Override
    public boolean usar() {
        return false;
    }
}
