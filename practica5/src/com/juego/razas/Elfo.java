package com.juego.razas;

import com.juego.modelo.Personaje;

public class Elfo implements Raza {

    private int fuerzaBase = 4;
    private int vidaBase = 90;
    private int destrezaBase = 6;
    private int inteligenciaBase = 7;
    private int defensaBase = 4;
    private int rapidezBase = 6;

    public Elfo() {}

    @Override public int getVidaBase() { return vidaBase; }
    @Override public int getFuerzaBase() { return fuerzaBase; }
    @Override public int getDestrezaBase() { return destrezaBase; }
    @Override public int getInteligenciaBase() { return inteligenciaBase; }
    @Override public int getDefensaBase() { return defensaBase; }
    @Override public int getRapidezBase() { return rapidezBase; }
}
