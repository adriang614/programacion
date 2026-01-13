package com.juego.clases;

import com.juego.modelo.Personaje;

public class Picaro extends Clase implements IClase {
    //----------CONSTRUCTOR----------
    public Picaro() {
        super(0, 3, 0, 105, 105, 0, 0);
    }

    @Override
    public void aplicarbonificador(Personaje p) {
        p.setDestreza(getDestreza() + this.getDestreza());
    }
}
