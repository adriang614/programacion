package com.juego.razas;

import com.juego.modelo.Personaje;

public class Enano implements Raza {

    @Override
    public int getVidaBase() { return 110; }

    @Override
    public int getFuerzaBase() { return 7; }

    @Override
    public int getDestrezaBase() { return 4; }

    @Override
    public int getInteligenciaBase() { return 4; }

    @Override
    public int getDefensaBase() { return 6; }

    @Override
    public int getRapidezBase() { return 4; }
}