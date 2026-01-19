package com.juego.clases;

import com.juego.habilidades.Habilidad;
import com.juego.modelo.Personaje;

import java.util.List;

public interface IClase {
    void aplicarbonificador(Personaje p);
    List<Habilidad> getHabilidades();
}