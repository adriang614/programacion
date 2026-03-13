package com.rpg.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Personaje {
    private String nombre;
    private String raza;
    private int nivel;
    private List<Item> equipo = new ArrayList<>();

    public Personaje(String nombre, String raza, int nivel) {
        this.nombre = nombre;
        this.raza = raza;
        this.nivel = nivel;
        this.equipo = new ArrayList<>();
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

    public void setEquipo(List<Item> items) {
        this.equipo = items;
    }

    public void agregarItem(Item nuevoItem) {
        if (nuevoItem != null) {
            this.equipo.add(nuevoItem);
        }
    }

}


