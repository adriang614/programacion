package com.juego.clases;

import com.juego.habilidades.CuraCC;
import com.juego.habilidades.DanioCC;
import com.juego.habilidades.DanioLD;
import com.juego.habilidades.Habilidad;
import com.juego.modelo.Personaje;

import java.util.ArrayList;

public class Picaro extends Clase implements IClase {
    //----------CONSTRUCTOR----------
    public Picaro() {
        super(0, 3, 0, 105, 105, 0, 0);
    }


    //----------OBTENER HABILIDADES----------
    @Override
    public ArrayList<Habilidad> getHabilidades() {
        ArrayList result = new ArrayList<Habilidad>();
        //----------HABILIDADES PICARO----------
        DanioCC ApuñaladaPorLaEspalda = new DanioCC("ApuñaladaPorLaEspalda", 5, "ataca por la espalda aprovechando un descuido del enemigo", 44, "fuerza");
        DanioLD CuchilloVolador = new DanioLD("CuchilloVolador", 1, "lanza un cuchillo con gran precision desde la distancia", 50, "destreza");
        CuraCC RobarVenda = new CuraCC("RobarVenda", 3, "roba una venda y se cura rapidamente", 18, "destreza");

        result.add(ApuñaladaPorLaEspalda);
        result.add(CuchilloVolador);
        result.add(RobarVenda);

        return result;
    }


    //----------APLICAR BONIFICADOR DE CLASE----------
    @Override
    public void aplicarbonificador(Personaje p) {
        p.setDestreza(getDestreza() + this.getDestreza());
    }
}
