package com.juego.razas;

import com.juego.modelo.Personaje;

public class Humano implements Raza {

    //----------ATRIBUTOS----------
    private int fuerza;
    private int inteligencia;
    private int destreza;
    private int vidaBase;
    private String especialidad;

    //----------CONSTRUCTOR----------
    public Humano() {
        this.fuerza = 5;
        this.inteligencia = 5;
        this.destreza = 5;
        this.vidaBase = 100;
        this.especialidad = "Equilibrado: Versátil para cualquier clase";
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
