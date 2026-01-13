package com.juego.razas;

import com.juego.modelo.Personaje;

public class Enano implements Raza {

    //----------ATRIBUTOS----------
    private int fuerza;
    private int inteligencia;
    private int destreza;
    private int vidaBase;
    private String especialidad;

    //----------CONSTRUCTOR----------
    public Enano() {
        this.fuerza = 7;
        this.inteligencia = 4;
        this.destreza = 4;
        this.vidaBase = 110;
        this.especialidad = "Resistencia: Ideal para Guerreros y Paladines.";
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