package com.juego.clases;

import com.juego.habilidades.CuraCC;
import com.juego.modelo.Personaje;

public class Sacerdote extends Clase implements IClase {

    //----------CONSTRUCTOR----------
    public Sacerdote() {
        super(0, 0, 3, 95, 95, 0, 0);
    }

    //----------HABILIDADES SACERDOTE----------
    CuraCC RezoSanador  = new CuraCC("RezoSanador", 3, "el sacerdote murmura una oracion y recupera vida gracias a su fe en dios", 20, "intelligencia");

    @Override
    public void aplicarbonificador(Personaje p) {
        p.setInteligencia(getInteligencia() + this.getInteligencia());
    }
}
