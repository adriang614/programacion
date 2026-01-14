package com.juego.habilidades;

import com.juego.modelo.Personaje;

public interface Habilidad {
    boolean usar();
    int escalado(Personaje p);
}
