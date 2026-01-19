package com.juego.clases;

import com.juego.habilidades.CuraCC;
import com.juego.habilidades.DanioCC;
import com.juego.habilidades.DanioLD;
import com.juego.modelo.Personaje;

public class Sacerdote extends Clase implements IClase {

    //----------CONSTRUCTOR----------
    public Sacerdote() {
        super(0, 0, 3, 95, 95, 0, 0);
        getHabilidades().add(PuñetazoSagrado);
        getHabilidades().add(BibliaVoladora);
        getHabilidades().add(RezoSanador);
    }


    //----------HABILIDADES SACERDOTE----------
    DanioCC PuñetazoSagrado  = new DanioCC("PuñetazoSagrado", 5, "el sacerdote reparte una hostia bendecida en toda la cara", 20, "fuerza");
    DanioLD BibliaVoladora = new DanioLD("BibliaVoladora", 1, "Lanza una biblia a distancia con la fuerza de la fe", 30, "destreza");
    CuraCC RezoSanador  = new CuraCC("RezoSanador", 3, "el sacerdote murmura una oracion y recupera vida gracias a su fe en dios", 25, "inteligencia");


    //----------APLICAR BONIFICADOR DE CLASE----------
    @Override
    public void aplicarbonificador(Personaje p) {
        p.setInteligencia(getInteligencia() + this.getInteligencia());
    }
}
