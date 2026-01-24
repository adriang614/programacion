package com.juego.presentacion;

import com.juego.modelo.*;
import com.juego.clases.*;
import com.juego.razas.*;

import java.util.ArrayList;
import java.util.List;

public class Presentador {

    private Vista vista;
    private List<Personaje> personajes;

    public Presentador(Vista vista) {
        this.vista = vista;
        this.personajes = new ArrayList<>();
        precargarPersonajes();
    }

    public void iniciar() {
        int opcion;

        do {
            System.out.println("\n========== MENÚ PRINCIPAL ==========");
            System.out.println("1. Jugar");
            System.out.println("2. Crear personaje");
            System.out.println("3. Salir");
            System.out.println("====================================");

            opcion = vista.pedirNumero("Selecciona una opción:");

            switch (opcion) {
                case 1 -> jugar();
                case 2 -> crearPersonaje();
                case 3 -> System.out.println("Saliendo del juego...");
                default -> System.out.println("Opción no válida.");
            }

        } while (opcion != 3);
    }

    private void jugar() {
        System.out.println("\n--- Selección de personajes ---");
        System.out.println("Selecciona el primer personaje:");
        Personaje p1 = vista.elegirPersonaje(personajes);

        System.out.println("Selecciona el segundo personaje:");
        Personaje p2 = vista.elegirPersonaje(personajes);

        if (p1 == p2) {
            System.out.println("No puedes elegir el mismo personaje dos veces.");
            return;
        }

        Combate combate = new Combate(p1, p2, vista);
        combate.inciarCombate(p1, p2);
    }

    private void crearPersonaje() {
        GestorPersonaje gestor = new GestorPersonaje(vista);
        Personaje nuevo = gestor.crearPJ();
        personajes.add(nuevo);
        System.out.println("Personaje creado correctamente.");
    }

    private void precargarPersonajes() {
        personajes.add(new Personaje("Adrian", new Humano(), new Sacerdote()));
        personajes.add(new Personaje("Paco", new Elfo(), new Mago()));
        personajes.add(new Personaje("Josemi", new Humano(), new Sacerdote()));
        personajes.add(new Personaje("Tomas", new Elfo(), new Guerrero()));
        personajes.add(new Personaje("Ango", new Enano(), new Druida()));
        personajes.add(new Personaje("Secundino", new Humano(), new Bardo()));
    }
}
