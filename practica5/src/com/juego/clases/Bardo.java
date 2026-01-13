package com.juego.clases;

import com.juego.modelo.Personaje;

public class Bardo extends Clase implements IClase {
    //----------CONSTRUCTOR----------
    public Bardo() {
        super(0, 0, 3, 90, 90, 0, 0);
    }

    @Override
    public void aplicarbonificador(Personaje p) {
        p.setInteligencia(getInteligencia() + this.getInteligencia());
    }
}
