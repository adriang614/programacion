package com.juego.razas;

import com.juego.modelo.Personaje;

public class Elfo implements Raza {

    @Override
    public int getVidaBase() { return 90; }

    @Override
    public int getFuerzaBase() { return 4; }

    @Override
    public int getDestrezaBase() { return 6; }

    @Override
    public int getInteligenciaBase() { return 7; }

    @Override
    public int getDefensaBase() { return 4; }

    @Override
    public int getRapidezBase() { return 6; }

}