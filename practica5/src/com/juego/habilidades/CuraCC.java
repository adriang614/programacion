package com.juego.habilidades;

import com.juego.modelo.Personaje;

public class CuraCC implements Habilidad{

    //----------ATRIBUTOS----------
    private String nombre;
    private int usos;
    private int usosMax;
    private String descripcion;
    private int valor;
    private String escalabilidad;

    //----------CONSTRUCTOR----------
    public CuraCC(String nombre, int usosMax, String descripcion, int valor, String escalabilidad) {
        this.nombre = nombre;
        this.usos = usosMax;
        this.usosMax = usosMax;
        this.descripcion = descripcion;
        this.valor = valor;
        this.escalabilidad = escalabilidad;
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

    public int getUsosMax() {
        return usosMax;
    }

    public void setUsosMax(int usosMax) {
        this.usosMax = usosMax;
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

    public String getEscalabilidad() {
        return escalabilidad;
    }

    public void setEscalabilidad(String escalabilidad) {
        this.escalabilidad = escalabilidad;
    }

    @Override
    public boolean usar() {
        return false;
    }

    @Override
    public int escalado(Personaje p) {
        return 0;
    }
}

