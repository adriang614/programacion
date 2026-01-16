package com.juego.clases;

import com.juego.habilidades.CuraCC;
import com.juego.habilidades.DanioCC;
import com.juego.habilidades.DanioLD;
import com.juego.modelo.Personaje;

public class Paladin extends Clase implements IClase {
    //----------CONSTRUCTOR----------
    public Paladin() {
        super(2, 0, 1, 115, 115, 0, 0);
    }


    //----------HABILIDADES PALADIN----------
    DanioCC GolpeDeJuramento = new DanioCC("GolpeDeJuramento", 5, "ataca con la fuerza de su juramento y su arma", 30, "fuerza");
    DanioLD Veredicto = new DanioLD("Veredicto", 1, "señala al enemigo y una energia castigadora lo alcanza a distancia", 28, "inteligencia");
    CuraCC DisciplinaDeAcero = new CuraCC("DisciplinaDeAcero", 3, "se mantiene firme y recupera vida gracias a su entrenamiento", 30, "defensa");


    //----------APLICAR BONIFICADOR DE CLASE----------
    @Override
    public void aplicarbonificador(Personaje p) {
        p.setInteligencia(getInteligencia() + this.getInteligencia());
        p.setFuerza(getFuerza() + this.getFuerza());
    }
}
