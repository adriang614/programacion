package com.juego.clases;

import com.juego.habilidades.CuraCC;
import com.juego.habilidades.DanioCC;
import com.juego.habilidades.DanioLD;
import com.juego.modelo.Personaje;

public class Mago extends Clase implements IClase {
    //----------CONSTRUCTOR----------
    public Mago() {
        super(0, 0, 3, 90, 90, 0, 0);
    }


    //----------HABILIDADES MAGO----------
    DanioCC HechizoConcentrado = new DanioCC("HechizoConcentrado", 5, "El mago concentra su energia y golpea la mente del enemigo", 15, "inteligencia");
    DanioLD HechizoPreciso = new DanioLD("HechizoPreciso", 1, "Lanza un hechizo a distancia con gran precision", 40, "inteligencia");
    CuraCC ConjuroCurativo = new CuraCC("ConjuroCurativo", 3, "Restaura su vida mediante un conjuro en hebreo", 23, "inteligencia");


    //----------APLICAR BONIFICADOR DE CLASE----------
    @Override
    public void aplicarbonificador(Personaje p) {
        p.setInteligencia(getInteligencia() + this.getInteligencia());
    }
}
