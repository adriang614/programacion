package com.juego.modelo;

import com.juego.clases.Clase;
import com.juego.habilidades.Habilidad;
import com.juego.razas.Raza;

import java.util.List;

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
    private List<Habilidad> habilidades;


    //----------CONSTRUCTOR----------
    public Personaje(String nombre, Clase clase, Raza raza) {
        this.nombre = nombre;
        this.clase = clase;
        this.raza = raza;
        this.fuerza = raza.getFuerzaBase();
        this.destreza = raza.getDestrezaBase();
        this.inteligencia = raza.getInteligenciaBase();
        this.defensa = raza.getDefensaBase();
        this.rapidez = raza.getRapidezBase();
        this.vidaMax = clase.getVidaMax() + raza.getVidaBase();
        this.vida = this.vidaMax;
        this.habilidades = clase.getHabilidades();
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
