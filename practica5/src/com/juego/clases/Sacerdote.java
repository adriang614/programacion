package com.juego.clases;

import com.juego.modelo.Personaje;

public class Sacerdote extends Clase implements IClase {
    //----------CONSTRUCTOR----------
    public Sacerdote() {
        super(0, 0, 3, 95, 95, 0, 0);
    }

    @Override
    public void aplicarbonificador(Personaje p) {
        p.setInteligencia(getInteligencia() + this.getInteligencia());
    }
}
