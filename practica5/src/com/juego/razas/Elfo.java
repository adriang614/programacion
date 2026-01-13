package com.juego.razas;

import com.juego.modelo.Personaje;

public class Elfo implements Raza {

    //----------ATRIBUTOS----------
    private int fuerza;
    private int inteligencia;
    private int destreza;
    private int vidaBase;
    private String especialidad;

    //----------CONSTRUCTOR----------
    public Elfo() {
        this.fuerza = 4;
        this.inteligencia = 6;
        this.destreza = 7;
        this.vidaBase = 90;
        this.especialidad = "Agilidad: Ideal para Pícaros, Magos y Bardos.";
    }

    //----------GETTERS Y SETTERS----------
    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }

    public int getDestreza() {
        return destreza;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public int getVidaBase() {
        return vidaBase;
    }

    public void setVidaBase(int vidaBase) {
        this.vidaBase = vidaBase;
    }

    @Override
    public void bonificadorRaza(Personaje p) {
        p.setInteligencia(getInteligencia() + this.getInteligencia());
        p.setFuerza(getFuerza() + this.getFuerza());
        p.setDestreza(getDestreza() + this.getDestreza());
        p.setVida(getFuerza() + this.getVidaBase());
    }
}