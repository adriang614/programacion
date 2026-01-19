package com.juego.modelo;

import com.juego.clases.*;
import com.juego.razas.*;
import java.util.ArrayList;
import java.util.List;

public class PreCargaDatos {

    //----------ATRIBUTOS----------
    private List<Personaje> personajes = new ArrayList<>();

    //----------CONSTRUCTOR----------
    public PreCargaDatos() {
        personajes.add(new Personaje("Adrián", new Sacerdote(), new Humano()));
        personajes.add(new Personaje("Carlos", new Druida(), new Elfo()));
    }

    //----------GET----------
    public List<Personaje> getPersonajes() {
        return personajes;
    }
}

