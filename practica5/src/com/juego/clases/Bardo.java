package com.juego.clases;

import com.juego.habilidades.CuraCC;
import com.juego.habilidades.DanioCC;
import com.juego.habilidades.DanioLD;
import com.juego.habilidades.Habilidad;
import com.juego.modelo.Personaje;

import java.util.ArrayList;

public class Bardo extends Clase implements IClase {

    //----------CONSTRUCTOR----------
    public Bardo() {
        super(0, 0, 3, 90, 90, 0, 0);
    }

    //----------OBTENER HABILIDADES----------
    @Override
    public ArrayList<Habilidad> getHabilidades() {
        ArrayList result = new ArrayList<Habilidad>();
        //----------HABILIDADES BARDO----------
        DanioCC PuaDeFuria = new DanioCC("PuaDeFuria", 5, "Golpea con la guitarra a ritmo de taconeo, desbordando fuerza y estilo", -25, "destreza");
        DanioLD LamentoGitano = new DanioLD("LamentoGitano", 1, "Canta con fuerza y rabia, proyectando energia a distancia contra sus enemigos", -50, "destreza");
        CuraCC SoloDelCanelita = new CuraCC("SoloDelCanelita", 3, "Canta con alma, devolviendo energia y vitalidad a si mismo", 30, "inteligencia");

        result.add(PuaDeFuria);
        result.add(LamentoGitano);
        result.add(SoloDelCanelita);

        return result;
    }

    //----------APLICAR BONIFICADOR DE CLASE----------
    @Override
    public void aplicarbonificador(Personaje p) {
        p.setInteligencia(getInteligencia() + this.getInteligencia());
    }
}
