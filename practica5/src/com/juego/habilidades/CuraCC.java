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
    public int escalado(Personaje p) {
        double bonus = 0;
        switch (escalabilidad){
            case "fuerza":
                bonus = p.getFuerza() * 1.4;
                break;

            case "destreza":
                bonus = p.getDestreza() * 1.3;
                break;

            case "intelligencia":
                bonus = p.getInteligencia() * 1.3;
                break;

            case "defensa":
                bonus = p.getDefensa() * 1.8;
                break;
        }
        return (int) Math.round(bonus);
    }

    @Override
    public void usar(Personaje enemigo, Personaje atacante) {
        if (usos > 0) {
            int vidaActual = enemigo.getVida();
            enemigo.setVida(vidaActual - valor);
            usos--;
            System.out.println( atacante.getNombre() + " ha usado " + nombre + " curandose " + valor + " de vida.");
        } else {
            System.out.println("No quedan usos de " + nombre);
        }
    }

}

