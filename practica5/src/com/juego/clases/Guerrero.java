package com.juego.clases;

import com.juego.modelo.Personaje;

public class Guerrero extends Clase implements IClase {
    //----------CONSTRUCTOR----------
    public Guerrero() {
        super(3, 0, 0, 120, 120, 0, 0);
    }

    @Override
    public void aplicarbonificador(Personaje p) {
        p.setFuerza(getFuerza() + this.getFuerza());
    }
}