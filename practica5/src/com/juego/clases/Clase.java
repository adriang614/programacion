package com.juego.clases;

public class Clase {
    private int fuerza;
    private int destreza;
    private int inteligencia;
    private int vida;
    private int vidaMax;
    private int defensa;
    private int rapidez;

    //CONTRUCTOR
    public Clase(int fuerza, int destreza, int inteligencia, int vida, int vidaMax, int defensa, int rapidez) {
        this.fuerza = fuerza;
        this.destreza = destreza;
        this.inteligencia = inteligencia;
        this.vida = vida;
        this.vidaMax = vidaMax;
        this.defensa = defensa;
        this.rapidez = rapidez;
    }

    //GET Y SET

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
}
