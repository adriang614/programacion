package com.juego.presentacion;

import com.juego.habilidades.Habilidad;
import com.juego.modelo.Personaje;

import java.util.List;
import java.util.Scanner;

public class Vista {

    private Scanner sc = new Scanner(System.in);

    // ------ SEPARADOR -----
    public void linea() {
        System.out.println("----------------------------------------");
    }

    // ----- TITULO -----
    public void titulo(String msg) {
        linea();
        System.out.println(" " + msg.toUpperCase());
        linea();
    }

    // ----- PEDIR NUMERO AL JUGADOR -----
    public int pedirNumero(String msg) {
        System.out.println(msg);
        return sc.nextInt();
    }

    // ----- MENÚ DE RAZAS -----
    public void menuRazas() {
        titulo("Selecciona una raza");
        System.out.println("1. Humano");
        System.out.println("2. Enano");
        System.out.println("3. Elfo");
        linea();
    }

    // ----- MENÚ DE CLASES -----
    public void menuClases() {
        titulo("Selecciona una clase");
        System.out.println("1. Bardo");
        System.out.println("2. Guerrero");
        System.out.println("3. Paladin");
        System.out.println("4. Monje");
        System.out.println("5. Mago");
        System.out.println("6. Picaro");
        System.out.println("7. Druida");
        System.out.println("8. Sacerdote");
        linea();
    }

    // ----- BARRA DE VIDA -----
    public String barraVida(Personaje p) {
        int vida = p.getVida();
        int max = p.getVidaMax();
        int bloques = (vida * 20) / max;

        StringBuilder barra = new StringBuilder("[");
        for (int i = 0; i < 20; i++) {
            barra.append(i < bloques ? "#" : " ");
        }
        barra.append("]");

        return barra.toString();
    }

    // ----- MOSTRAR ESTADO DEL COMBATE -----
    public void mostrarEstadoCombate(Personaje p1, Personaje p2) {
        titulo("Estado del combate");

        System.out.println(p1.getNombre() + "  " + barraVida(p1) +
                "  (" + p1.getVida() + "/" + p1.getVidaMax() + ")");
        System.out.println("Habilidades:");
        for (Habilidad h : p1.getHabilidades()) {
            System.out.println(" - " + h.getNombre() + " (usos: " + h.getUsos() + ")");
        }

        System.out.println();

        System.out.println(p2.getNombre() + "  " + barraVida(p2) +
                "  (" + p2.getVida() + "/" + p2.getVidaMax() + ")");
        System.out.println("Habilidades:");
        for (Habilidad h : p2.getHabilidades()) {
            System.out.println(" - " + h.getNombre() + " (usos: " + h.getUsos() + ")");
        }

        linea();
    }

    // ----- MENU ELEGIR HABILIDAD -----
    public Habilidad elegirHabilidad(Personaje p) {
        titulo("Turno de " + p.getNombre());
        System.out.println("Elige una habilidad:");

        for (int i = 0; i < p.getHabilidades().size(); i++) {
            System.out.println((i + 1) + " -> " + p.getHabilidades().get(i).getNombre());
        }

        int opcion;

        // Elegir una opción válida
        do {
            opcion = sc.nextInt();

            if (opcion < 1 || opcion > p.getHabilidades().size()) {
                System.out.println("Opción no válida. Inténtalo de nuevo.");
            }

        } while (opcion < 1 || opcion > p.getHabilidades().size());

        return p.getHabilidades().get(opcion - 1);
    }


    // ----- MENU ELEGIR PERSONAJE -----
    public Personaje elegirPersonaje(List<Personaje> personajes) {
        titulo("Elige un personaje");

        for (int i = 0; i < personajes.size(); i++) {
            System.out.println((i + 1) + " -> " + personajes.get(i).getNombre());
        }

        int opcion = pedirNumero("Selecciona un personaje:");
        return personajes.get(opcion - 1);
    }
}