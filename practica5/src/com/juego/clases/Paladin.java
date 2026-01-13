package com.juego.clases;

import com.juego.modelo.Personaje;

public class Paladin extends Clase implements IClase {
    //----------CONSTRUCTOR----------
    public Paladin() {
        super(2, 0, 1, 115, 115, 0, 0);
    }

    @Override
    public void aplicarbonificador(Personaje p) {
        p.setInteligencia(getInteligencia() + this.getInteligencia());
        p.setFuerza(getFuerza() + this.getFuerza());
    }
}
