package com.juego.modelo;

import com.juego.clases.Clase;
import com.juego.razas.Elfo;
import com.juego.razas.Enano;
import com.juego.razas.Humano;
import com.juego.razas.Raza;

import java.util.Scanner;

public class GestorPersonaje {
    public Personaje crearPJ() {
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Cómo se llama tu personaje?");
        String nombre = sc.nextLine();

        //----

        System.out.println("¿Qué raza es tu personaje?");
        int opcion = 0;
        Raza razaElegida = null;
        do {
            switch (opcion) {
                case 1: razaElegida = CrearHumano();
                    break;
                case 2: razaElegida = CrearEnano();
                    break;
                case 3: razaElegida = CrearElfo();
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion < 4 && opcion > 0);

        //----

        System.out.println("¿Qué clase es tu personaje?");
        int eleccion = 0;
        Clase claseElegida = null;
        do {
            switch (opcion) {
                case 1: claseElegida = CrearHumano();
                case 2: claseElegida = CrearEnano();
                case 3: claseElegida = CrearElfo();
            }
        } while (eleccion < 4 && eleccion > 0);
        return null;
    }


    public Humano CrearHumano() {
        Humano h1 = new Humano();
        return h1;
    }

    public Enano CrearEnano() {
        Enano en1 = new Enano();
        return en1;
    }

    public Elfo CrearElfo() {
        Elfo el1 = new Elfo();
        return el1;
    }

}
