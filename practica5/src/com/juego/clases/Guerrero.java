package com.juego.clases;

import com.juego.habilidades.CuraCC;
import com.juego.habilidades.DanioCC;
import com.juego.habilidades.DanioLD;
import com.juego.modelo.Personaje;

public class Guerrero extends Clase implements IClase {
    //----------CONSTRUCTOR----------
    public Guerrero() {
        super(3, 0, 0, 120, 120, 0, 0);
        getHabilidades().add(OstionGitano);
        getHabilidades().add(EmbestidaDelLeón);
        getHabilidades().add(Resiliencia);
    }


    //----------HABILIDADES GUERRERO----------
    DanioCC OstionGitano = new DanioCC("OstionGitano", 5, "Desata un golpe con toda su fuerza, demostrando coraje y disciplina", 25, "fuerza");
    DanioLD EmbestidaDelLeón = new DanioLD("EmbestidaDelLeon", 1, "Carga con todo su cuerpo sobre el enemigo, rompiendo su línea defensiva", 30, "fuerza");
    CuraCC Resiliencia = new CuraCC("Resiliencia", 3, "Se concentra en su fortaleza interior para recuperar energía y aguantar más", 21, "destreza");


    //----------APLICAR BONIFICADOR DE CLASE----------
    @Override
    public void aplicarbonificador(Personaje p) {
        p.setFuerza(getFuerza() + this.getFuerza());
    }
}