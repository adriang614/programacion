package com.juego.modelo;

import com.juego.habilidades.Habilidad;

import java.util.ArrayList;
import java.util.Scanner;

public class Combate {
    Personaje p1;
    Personaje p2;

    public Combate (Personaje p1, Personaje p2){
        this.p1 = p1;
        this.p2 = p2;
        inciarCombate(p1, p2);
    }

    public void inciarCombate(Personaje p1, Personaje p2){
        int cont = 1;
        while (this.p1.getVida() > 0 || this.p2.getVida() > 0){
            mostrarDatos();
            System.out.println("Turno " + cont);
            Habilidad h1 = elegirHabilidad(p1);
            h1.setUsos(h1.getUsos()-1);
            Habilidad h2 = elegirHabilidad(p2);
            h2.setUsos(h2.getUsos()-1);
            efectoHabilidad(h1, h2);
            cont++;
        };
        if (p1.getVida() < 0) {
            System.out.println(p2.getNombre() + " es el ganador :)");
        }
        else {
            System.out.println(p1.getNombre() + " es el ganador :)");
        }
    }


    public Habilidad elegirHabilidad(Personaje p) {
        Scanner sc = new Scanner(System.in);
        System.out.println( p.getNombre() +  " elige una habilidad:");

        for (int i = 0; i < p.getClase().getHabilidades().size(); i++) {
            System.out.println((i + 1) + "-> " + p.getClase().getHabilidades().get(i).getNombre());
        }

        int opcion = sc.nextInt();

        return p.getClase().getHabilidades().get(opcion - 1);
    }


    public void mostrarDatos() {
        System.out.println("----- ESTADO DEL COMBATE -----");
        System.out.println(p1.getNombre() + " - Vida: " + p1.getVida() + " / " + p1.getVidaMax());
        System.out.println("Habilidades:");
        for (Habilidad h : p1.getClase().getHabilidades()) {
            System.out.println(" - " + h.getNombre() + " (usos: " + h.getUsos() + ")");
        }

        System.out.println();

        System.out.println(p2.getNombre() + " - Vida: " + p2.getVida() + " / " + p2.getVidaMax());
        System.out.println("Habilidades:");
        for (Habilidad h : p2.getClase().getHabilidades()) {
            System.out.println(" - " + h.getNombre() + " (usos: " + h.getUsos() + ")");
        }

        System.out.println("-------------------------------");
    }


    public void efectoHabilidad(Habilidad h1, Habilidad h2){
        h1.usar(p2, p1);
        h2.usar(p1, p2);
    }

}
