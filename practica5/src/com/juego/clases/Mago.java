package com.juego.clases;

import com.juego.habilidades.CuraCC;
import com.juego.habilidades.DanioCC;
import com.juego.habilidades.DanioLD;
import com.juego.habilidades.Habilidad;
import com.juego.modelo.Personaje;
import java.util.ArrayList;

public class Mago extends Clase implements IClase {
    //----------CONSTRUCTOR----------
    public Mago() {
        super(0, 0, 3, 90, 90, 0, 0);
    }


    //----------OBTENER HABILIDADES----------
    @Override
    public ArrayList<Habilidad> getHabilidades() {
        ArrayList result = new ArrayList<Habilidad>();
        //----------HABILIDADES MAGO----------
        DanioCC HechizoConcentrado = new DanioCC("HechizoConcentrado", 5, "El mago concentra su energia y golpea la mente del enemigo", 20, "inteligencia");
        DanioLD HechizoPreciso = new DanioLD("HechizoPreciso", 1, "Lanza un hechizo a distancia con gran precision", 45, "inteligencia");
        CuraCC ConjuroCurativo = new CuraCC("ConjuroCurativo", 3, "Restaura su vida mediante un conjuro en hebreo", 22, "inteligencia");

        result.add(HechizoConcentrado);
        result.add(HechizoPreciso);
        result.add(ConjuroCurativo);

        return result;
    }


    //----------APLICAR BONIFICADOR DE CLASE----------
    @Override
    public void aplicarbonificador(Personaje p) {
        p.setInteligencia(getInteligencia() + this.getInteligencia());
    }
}
