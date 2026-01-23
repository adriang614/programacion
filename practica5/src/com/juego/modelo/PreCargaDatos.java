package com.juego.modelo;

import com.juego.clases.*;
import com.juego.razas.*;
import java.util.ArrayList;
import java.util.List;

public class PreCargaDatos {

    private List<Personaje> personajes = new ArrayList<>();

    public PreCargaDatos() {
        personajes.add(new Personaje("Adrian", new Humano(), new Sacerdote()));
        personajes.add(new Personaje("Paco", new Elfo(), new Mago()));
        personajes.add(new Personaje("Josemi", new Humano(), new Sacerdote()));
        personajes.add(new Personaje("Tomas", new Elfo(), new Guerrero()));
        personajes.add(new Personaje("Ango", new Enano(), new Druida()));
        personajes.add(new Personaje("Secundino", new Humano(), new Bardo()));
    }

    public List<Personaje> getPersonajes() {
        return personajes;
    }
}