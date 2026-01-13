package com.juego.clases;

import com.juego.modelo.Personaje;

public class Druida extends Clase implements IClase {
    //----------CONSTRUCTOR----------
    public Druida() {
        super(2, 0, 1, 100, 100, 0, 0);
    }

    @Override
    public void aplicarbonificador(Personaje p) {
        p.setInteligencia(getInteligencia() + this.getInteligencia());
        p.setFuerza(getFuerza() + this.getFuerza());
    }
}
