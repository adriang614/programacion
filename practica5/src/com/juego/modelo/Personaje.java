package com.juego.modelo;

import com.juego.clases.Clase;
import com.juego.razas.Raza;

public class Personaje {

    //----------ATRIBUTOS----------
    private String nombre;
    private int vida;
    private int vidaMax;
    private int fuerza;
    private int destreza;
    private int inteligencia;
    private int defensa;
    private int rapidez;
    private Clase clase;
    private Raza raza;

    //----------CONSTRUCTOR----------
    public Personaje(String nombre, int vidaMax, int fuerza, int destreza, int inteligencia, int defensa, int rapidez, Clase clase, Raza raza) {
        this.nombre = nombre;
        this.vida = vidaMax;
        this.vidaMax = vidaMax;
        this.fuerza = fuerza;
        this.destreza = destreza;
        this.inteligencia = inteligencia;
        this.defensa = defensa;
        this.rapidez = rapidez;
        this.clase = clase;
        this.raza = raza;
    }

    //----------GET Y SET----------
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getVidaMax() {
        return vidaMax;
    }

    public void setVidaMax(int vidaMax) {
        this.vidaMax = vidaMax;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getDestreza() {
        return destreza;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getRapidez() {
        return rapidez;
    }

    public void setRapidez(int rapidez) {
        this.rapidez = rapidez;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    public Raza getRaza() {
        return raza;
    }

    public void setRaza(Raza raza) {
        this.raza = raza;
    }
}
