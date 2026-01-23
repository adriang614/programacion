package com.juego.modelo;

import com.juego.habilidades.Habilidad;
import com.juego.presentacion.Vista;

import java.util.ArrayList;
import java.util.Scanner;

public class Combate {
    Personaje p1;
    Personaje p2;
    private Vista vista;

    public Combate (Personaje p1, Personaje p2, Vista vista){
        this.p1 = p1;
        this.p2 = p2;
        this.vista = vista;
    }

    public void inciarCombate(Personaje p1, Personaje p2){
        int cont = 1;
        while (this.p1.getVida() > 0 && this.p2.getVida() > 0){
            vista.mostrarEstadoCombate(p1, p2);
            System.out.println("Turno " + cont);
            Habilidad h1 = vista.elegirHabilidad(p1);
            Habilidad h2 = vista.elegirHabilidad(p2);
            efectoHabilidad(h1, h2);
            cont++;
        }

        // ----- FINAL DEL COMBATE -----
        System.out.println("\n========================================");
        System.out.println("              FIN DEL COMBATE");
        System.out.println("========================================");

        Personaje ganador = (p1.getVida() > 0) ? p1 : p2;

        System.out.println("\n🏆  " + ganador.getNombre().toUpperCase() + " ES EL GANADOR  🏆");
        System.out.println("========================================\n");
    }


    public void efectoHabilidad(Habilidad h1, Habilidad h2){
        h1.usar(p2, p1);
        h2.usar(p1, p2);
    }

}
