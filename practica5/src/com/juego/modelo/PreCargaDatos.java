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

    }

    //----------GET----------
    public List<Personaje> getPersonajes() {
        return personajes;
    }
}

