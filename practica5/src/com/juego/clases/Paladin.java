package com.juego.clases;

import com.juego.habilidades.CuraCC;
import com.juego.habilidades.DanioCC;
import com.juego.habilidades.DanioLD;
import com.juego.habilidades.Habilidad;
import com.juego.modelo.Personaje;

import java.util.ArrayList;

public class Paladin extends Clase implements IClase {
    //----------CONSTRUCTOR----------
    public Paladin() {
        super(2, 0, 1, 115, 115, 0, 0);
    }


    //----------OBTENER HABILIDADES----------
    @Override
    public ArrayList<Habilidad> getHabilidades() {
        ArrayList result = new ArrayList<Habilidad>();
        //----------HABILIDADES PALADIN----------
        DanioCC GolpeDeJuramento = new DanioCC("GolpeDeJuramento", 5, "ataca con la fuerza de su juramento y su arma", 32, "fuerza");
        DanioLD Veredicto = new DanioLD("Veredicto", 1, "señala al enemigo y una energia castigadora lo alcanza a distancia", 36, "inteligencia");
        CuraCC DisciplinaDeAcero = new CuraCC("DisciplinaDeAcero", 3, "se mantiene firme y recupera vida gracias a su entrenamiento", 24, "defensa");

        result.add(GolpeDeJuramento);
        result.add(Veredicto);
        result.add(DisciplinaDeAcero);

        return result;
    }


    //----------APLICAR BONIFICADOR DE CLASE----------
    @Override
    public void aplicarbonificador(Personaje p) {
        p.setInteligencia(getInteligencia() + this.getInteligencia());
        p.setFuerza(getFuerza() + this.getFuerza());
    }
}
