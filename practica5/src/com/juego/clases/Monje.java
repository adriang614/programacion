package com.juego.clases;

import com.juego.habilidades.CuraCC;
import com.juego.habilidades.DanioCC;
import com.juego.habilidades.DanioLD;
import com.juego.habilidades.Habilidad;
import com.juego.modelo.Personaje;

import java.util.ArrayList;

public class Monje extends Clase implements IClase {
    //----------CONSTRUCTOR----------
    public Monje() {
        super(2, 1, 0, 110, 110, 0, 0);
    }


    //----------OBTENER HABILIDADES----------
    @Override
    public ArrayList<Habilidad> getHabilidades() {
        ArrayList result = new ArrayList<Habilidad>();
        //----------HABILIDADES MONJE----------
        DanioCC PunoZen = new DanioCC("PunoZen", 5, "El monje concentra su ki y golpea con la fuerza de su espíritu", 34, "fuerza");
        DanioLD LanzaChi = new DanioLD("LanzaChi", 1, "Proyecta un chorro de energía concentrada a distancia", 42, "destreza");
        CuraCC MeditacionCurativa = new CuraCC("MeditaciónCurativa", 3, "Se sienta en calma y recupera su propia vida y la de los aliados cercanos", 26, "inteligencia");

        result.add(PunoZen);
        result.add(LanzaChi);
        result.add(MeditacionCurativa);

        return result;
    }


    //----------APLICAR BONIFICADOR DE CLASE----------
    @Override
    public void aplicarbonificador(Personaje p) {
        p.setDestreza(getInteligencia() + this.getDestreza());
        p.setFuerza(getFuerza() + this.getFuerza());
    }
}
