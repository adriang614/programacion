package com.juego.razas;

import com.juego.modelo.Personaje;

public class Humano implements Raza {

    private int vidaBase = 100;
    private int fuerzaBase = 5;
    private int destrezaBase = 5;
    private int inteligenciaBase = 5;
    private int defensaBase = 5;
    private int rapidezBase = 5;

    public Humano() {}

    @Override public int getVidaBase() { return vidaBase; }
    @Override public int getFuerzaBase() { return fuerzaBase; }
    @Override public int getDestrezaBase() { return destrezaBase; }
    @Override public int getInteligenciaBase() { return inteligenciaBase; }
    @Override public int getDefensaBase() { return defensaBase; }
    @Override public int getRapidezBase() { return rapidezBase; }
}

