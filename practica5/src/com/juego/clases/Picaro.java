package com.juego.clases;

import com.juego.habilidades.CuraCC;
import com.juego.habilidades.DanioCC;
import com.juego.habilidades.DanioLD;
import com.juego.modelo.Personaje;

public class Picaro extends Clase implements IClase {
    //----------CONSTRUCTOR----------
    public Picaro() {
        super(0, 3, 0, 105, 105, 0, 0);
        getHabilidades().add(ApuñaladaPorLaEspalda);
        getHabilidades().add(CuchilloVolador);
        getHabilidades().add(RobarVenda);
    }


    //----------HABILIDADES PICARO----------
    DanioCC ApuñaladaPorLaEspalda = new DanioCC("ApuñaladaPorLaEspalda", 5, "ataca por la espalda aprovechando un descuido del enemigo", 35, "fuerza");
    DanioLD CuchilloVolador = new DanioLD("CuchilloVolador", 1, "lanza un cuchillo con gran precision desde la distancia", 28, "destreza");
    CuraCC RobarVenda = new CuraCC("RobarVenda", 3, "roba una venda y se cura rapidamente", 20, "destreza");


    //----------APLICAR BONIFICADOR DE CLASE----------
    @Override
    public void aplicarbonificador(Personaje p) {
        p.setDestreza(getDestreza() + this.getDestreza());
    }
}
