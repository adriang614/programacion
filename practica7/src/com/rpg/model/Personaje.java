package com.rpg.model;

import java.util.List;

public class Personaje {
    private String nombre;
    private String raza;
    private int nivel;
    private List<Item> equipo;

    public Personaje(String nombre, String raza, int nivel, List<Item> equipo) {
        this.nombre = nombre;
        this.raza = raza;
        this.nivel = nivel;
        this.equipo = equipo;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public List<Item> getEquipo() {
        return equipo;
    }

    public void setEquipo(List<Item> equipo) {
        this.equipo = equipo;
    }
}
