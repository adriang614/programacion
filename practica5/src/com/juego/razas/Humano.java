package com.juego.razas;

import com.juego.modelo.Personaje;

public class Humano implements Raza {

    public Humano() {

    }

    @Override
    public int getVidaBase() { return 100; }

    @Override
    public int getFuerzaBase() { return 5; }

    @Override
    public int getDestrezaBase() { return 5; }

    @Override
    public int getInteligenciaBase() { return 5; }

    @Override
    public int getDefensaBase() { return 5; }

    @Override
    public int getRapidezBase() { return 5; }

}
