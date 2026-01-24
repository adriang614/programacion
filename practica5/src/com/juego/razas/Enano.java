package com.juego.razas;

import com.juego.modelo.Personaje;

public class Enano implements Raza {

    private int vidaBase = 110;
    private int fuerzaBase = 7;
    private int destrezaBase = 4;
    private int inteligenciaBase = 4;
    private int defensaBase = 6;
    private int rapidezBase = 4;

    public Enano() {}

    @Override public int getVidaBase() { return vidaBase; }
    @Override public int getFuerzaBase() { return fuerzaBase; }
    @Override public int getDestrezaBase() { return destrezaBase; }
    @Override public int getInteligenciaBase() { return inteligenciaBase; }
    @Override public int getDefensaBase() { return defensaBase; }
    @Override public int getRapidezBase() { return rapidezBase; }
}
