package com.rpg.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Personaje {
    private String nombre;
    private String raza;
    private int nivel;
    private HashMap<String, Item> items;

    public Personaje(String nombre, String raza, int nivel) {
        this.nombre = nombre;
        this.raza = raza;
        this.nivel = nivel;
        this.items = new HashMap<String, Item>();
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

    /*public List<Item> getItems() {
        return items;
    }*/

    public void setEquipo(List<Item> equipo) {
        this.items = items;
    }
}
