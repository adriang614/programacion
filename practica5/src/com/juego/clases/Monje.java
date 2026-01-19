package com.juego.clases;

import com.juego.habilidades.CuraCC;
import com.juego.habilidades.DanioCC;
import com.juego.habilidades.DanioLD;
import com.juego.modelo.Personaje;

public class Monje extends Clase implements IClase {
    //----------CONSTRUCTOR----------
    public Monje() {
        super(2, 1, 0, 110, 110, 0, 0);
        getHabilidades().add(PunoZen);
        getHabilidades().add(LanzaChi);
        getHabilidades().add(MeditaciónCurativa);
    }


    //----------HABILIDADES MONJE----------
    DanioCC PunoZen = new DanioCC("PunoZen", 5, "El monje concentra su ki y golpea con la fuerza de su espíritu", 15, "fuerza");
    DanioLD LanzaChi = new DanioLD("LanzaChi", 1, "Proyecta un chorro de energía concentrada a distancia", 20, "destreza");
    CuraCC MeditaciónCurativa = new CuraCC("MeditaciónCurativa", 3, "Se sienta en calma y recupera su propia vida y la de los aliados cercanos", 30, "inteligencia");


    //----------APLICAR BONIFICADOR DE CLASE----------
    @Override
    public void aplicarbonificador(Personaje p) {
        p.setDestreza(getInteligencia() + this.getDestreza());
        p.setFuerza(getFuerza() + this.getFuerza());
    }
}
