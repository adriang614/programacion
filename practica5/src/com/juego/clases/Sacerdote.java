package com.juego.clases;

import com.juego.habilidades.CuraCC;
import com.juego.habilidades.DanioCC;
import com.juego.habilidades.DanioLD;
import com.juego.habilidades.Habilidad;
import com.juego.modelo.Personaje;

import java.util.ArrayList;

public class Sacerdote extends Clase implements IClase {

    //----------CONSTRUCTOR----------
    public Sacerdote() {
        super(0, 0, 3, 95, 95, 0, 0);
    }


    //----------OBTENER HABILIDADES----------
    @Override
    public ArrayList<Habilidad> getHabilidades() {
        ArrayList result = new ArrayList<Habilidad>();
        //----------HABILIDADES SACERDOTE----------
        DanioCC PuñetazoSagrado = new DanioCC("PuñetazoSagrado", 5, "el sacerdote reparte una hostia bendecida en toda la cara", 32, "fuerza");
        DanioLD BibliaVoladora = new DanioLD("BibliaVoladora", 1, "Lanza una biblia a distancia con la fuerza de la fe", 44, "destreza");
        CuraCC RezoSanador = new CuraCC("RezoSanador", 3, "el sacerdote murmura una oracion y recupera vida gracias a su fe en dios", 28, "inteligencia");

        result.add(PuñetazoSagrado);
        result.add(BibliaVoladora);
        result.add(RezoSanador);

        return result;
    }


    //----------APLICAR BONIFICADOR DE CLASE----------
    @Override
    public void aplicarbonificador(Personaje p) {
        p.setInteligencia(getInteligencia() + this.getInteligencia());
    }
}
