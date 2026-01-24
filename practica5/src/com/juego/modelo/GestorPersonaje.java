package com.juego.modelo;

import com.juego.clases.*;
import com.juego.presentacion.Vista;
import com.juego.razas.Elfo;
import com.juego.razas.Enano;
import com.juego.razas.Humano;
import com.juego.razas.Raza;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorPersonaje {

    //----------ATRIBUTOS----------
    private ArrayList<Personaje> personajes = new ArrayList<>();
    private Vista vista;


    //----------CONSTRUCTOR----------
    public GestorPersonaje (Vista vista){
        this.vista = vista;
    }


    //----------CREAR PERSONAJE----------
    public Personaje crearPJ() {
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Cómo se llama tu personaje?");
        String nombre = sc.nextLine();

        //----

        vista.menuRazas();
        int opcion = 0;
        Raza razaElegida = null;
        while (razaElegida == null) {
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    razaElegida = CrearHumano();
                    break;
                case 2:
                    razaElegida = CrearEnano();
                    break;
                case 3:
                    razaElegida = CrearElfo();
                    break;
                default:
                    System.out.println("Opción no válida, selecciona una raza");
            }
        }


        //----

        vista.menuClases();
        int eleccion = 0;
        Clase claseElegida = null;
        while (claseElegida == null) {
            eleccion = sc.nextInt();
            switch (eleccion) {
                case 1:
                    claseElegida = CrearBardo();
                    break;
                case 2:
                    claseElegida = CrearGuerrero();
                    break;
                case 3:
                    claseElegida = CrearPaladin();
                    break;
                case 4:
                    claseElegida = CrearMonje();
                    break;
                case 5:
                    claseElegida = CrearMago();
                    break;
                case 6:
                    claseElegida = CrearPicaro();
                    break;
                case 7:
                    claseElegida = CrearDruida();
                    break;
                case 8:
                    claseElegida = CrearSacerdote();
                    break;
                default:
                    System.out.println("Opción no válida, selecciona una clase");
            }
        }

        //----

        Personaje nuevo = new Personaje(nombre, razaElegida, claseElegida);

        //----

        personajes.add(nuevo);

        //----

        return nuevo;
    }


    //----------OBTENER PERSONAJES----------
    public ArrayList<Personaje> getPersonajes() {
        return  personajes;
    }


    //----------MÉTODOS PARA CREAR PERSONAJES----------
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

    public Bardo CrearBardo() {
        Bardo ba1 = new Bardo();
        return ba1;
    }

    public Guerrero CrearGuerrero() {
        Guerrero gu1 = new Guerrero();
        return gu1;
    }

    public Paladin CrearPaladin() {
        Paladin pa1 = new Paladin();
        return pa1;
    }

    public Monje CrearMonje() {
        Monje mo1 = new Monje();
        return mo1;
    }

    public Picaro CrearPicaro() {
        Picaro pi1 = new Picaro();
        return pi1;
    }

    public Druida CrearDruida() {
        Druida dr1 = new Druida();
        return dr1;
    }

    public Sacerdote CrearSacerdote() {
        Sacerdote sa1 = new Sacerdote();
        return sa1;
    }

    public Mago CrearMago() {
        Mago ma1 = new Mago();
        return ma1;
    }
}
