package com.juego.habilidades;

import com.juego.modelo.Personaje;

public interface Habilidad {
    int escalado(Personaje p);
    void usar(Personaje enemigo, Personaje atacante);
    String getNombre();
    int getUsos();
    void setUsos(int usos);
}
