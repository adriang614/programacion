package com.juego.modelo;

import com.juego.habilidades.Habilidad;

public class Combate {
    Personaje p1;
    Personaje p2;

    public Combate (Personaje p1, Personaje p2){
        this.p1 = p1;
        this.p2 = p2;
    }

    public void inciarCombate(Personaje p1, Personaje p2){
        int cont = 0;
        while (this.p1.getVida() > 0 && this.p2.getVida() > 0){
            System.out.println("Turno " + cont);
            this.mostrarDatos();
            cont++;
        };
    }

    public void mostrarDatos(){
        System.out.println("Vida p1: " + this.p1.getVida());
        System.out.println("Vida p2: " + this.p2.getVida());
    }

    public void efectoHabilidad(Habilidad h1, Habilidad h2){

    }

}
