package com.juego.clases;

import com.juego.modelo.Personaje;

public class Monje extends Clase implements IClase {
    //----------CONSTRUCTOR----------
    public Monje() {
        super(2, 1, 0, 110, 110, 0, 0);
    }

    @Override
    public void aplicarbonificador(Personaje p) {
        p.setDestreza(getInteligencia() + this.getDestreza());
        p.setFuerza(getFuerza() + this.getFuerza());
    }
}
