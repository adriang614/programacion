package com.juego.clases;

import com.juego.habilidades.CuraCC;
import com.juego.habilidades.DanioCC;
import com.juego.habilidades.DanioLD;
import com.juego.habilidades.Habilidad;
import com.juego.modelo.Personaje;
import java.util.ArrayList;

public class Guerrero extends Clase implements IClase {
    //----------CONSTRUCTOR----------
    public Guerrero() {
        super(3, 0, 0, 120, 120, 0, 0);
    }

    //----------OBTENER HABILIDADES----------
    @Override
    public ArrayList<Habilidad> getHabilidades() {
        ArrayList result = new ArrayList<Habilidad>();
        //----------HABILIDADES GUERRERO----------
        DanioCC OstionGitano = new DanioCC("OstionGitano", 5, "Desata un golpe con toda su fuerza, demostrando coraje y disciplina", 30, "fuerza");
        DanioLD EmbestidaDelLeon = new DanioLD("EmbestidaDelLeon", 1, "Carga con todo su cuerpo sobre el enemigo, rompiendo su línea defensiva", 38, "fuerza");
        CuraCC Resiliencia = new CuraCC("Resiliencia", 3, "Se concentra en su fortaleza interior para recuperar energía y aguantar más", 20, "destreza");

        result.add(OstionGitano);
        result.add(EmbestidaDelLeon);
        result.add(Resiliencia);

        return result;
    }


    //----------APLICAR BONIFICADOR DE CLASE----------
    @Override
    public void aplicarbonificador(Personaje p) {
        p.setFuerza(getFuerza() + this.getFuerza());
    }
}