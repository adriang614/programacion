package com.rpg.services;

import com.rpg.handler.RecursoNoEncontradoException;
import com.rpg.model.*;
import com.rpg.utils.*;
import java.util.*;

public class GestionMundo {
    private List<Ciudad> ciudades;
    private List<Personaje> personajes;
    private Map<String, Item> catalogoItems;

    public GestionMundo() {
        this.ciudades = new ArrayList<>();
        this.personajes = new ArrayList<>();
        this.catalogoItems = new HashMap<>();
    }

    public void cargarDatos() {
        System.out.println("Iniciando RPG Data Engine... \n");

        // Se cargan las ciudades (TXT)
        TxtHelper txtHelper = new TxtHelper();
        try {
            this.ciudades = txtHelper.leerCiudades("practica7/Ficheros/ciudades.txt");
        } catch (Exception e) {
            // El error ya se escribe dentro del helper, solo se avisa
            System.out.println("Hubo problemas al cargar algunas ciudades.");
        }

        // Se cargan los ítems (JSON) y los pasamos a un Mapa
        JsonHelper jsonHelper = new JsonHelper();
        List<Item> listaItems = jsonHelper.readList("practica7/Ficheros/items.json", Item.class);
        for (Item it : listaItems) {
            // Guardamos cada ítem usando su ID como llave
            this.catalogoItems.put(it.getId(), it);
        }

        // Se cargan los personajes (JSON)
        this.personajes = jsonHelper.readList("practica7/Ficheros/personajes.json", Personaje.class);

        //Se vinculan los items con lso personajes
        vincularItemsAPersonajes();
    }

    public void vincularItemsAPersonajes() {
        // Recorrer cada personaje de la lista
        for (Personaje p : this.personajes) {

            // Solo se recorre si el equipo no es nulo
            if (p.getEquipo() != null) {

                // Recorrer los ítems que tiene el personaje actualmente
                for (Item itemEquipado : p.getEquipo()) {
                    String idBuscado = itemEquipado.getId(); // "W01"

                    // Buscar en catalogoItems
                    if (this.catalogoItems.containsKey(idBuscado)) {
                        // Si existe, se copian los datos de catalogoItems al ítem del personaje
                        Item datosReales = this.catalogoItems.get(idBuscado);
                        itemEquipado.setNombre(datosReales.getNombre());
                        itemEquipado.setTipo(datosReales.getTipo());
                        itemEquipado.setValor(datosReales.getValor());
                    } else {
                        // Si no existe, se lanza la excepcion
                        try {
                            throw new RecursoNoEncontradoException("Item '" + idBuscado + "' no existe para el personaje: " + p.getNombre());
                        } catch (RecursoNoEncontradoException e) {
                            LoggerCustom.escribirLog("RecursoNoEncontradoException", e.getMessage());
                        }
                    }
                }
            }
        }
    }

    public void crearNuevoPersonaje(String nombre, String raza, int nivel, String idItemInicial) {
        Personaje p = new Personaje(nombre, raza, nivel);

        // Se busca el item que el usuario quiers darle
        if (this.catalogoItems.containsKey(idItemInicial)) {
            p.agregarItem(this.catalogoItems.get(idItemInicial));
        } else {
            // Si no existe, se lanza excepcion y se escribe el error
            try {
                throw new RecursoNoEncontradoException("No se pudo asignar el item inicial '"
                        + idItemInicial + "' a " + nombre + " porque no existe en el catálogo");
            } catch (RecursoNoEncontradoException e) {
                LoggerCustom.escribirLog("RecursoNoEncontradoException", e.getMessage());
                System.out.println("Error: El ítem inicial no existe");
            }
        }

        // Se añade a la lista de personajes
        this.personajes.add(p);
    }

    public void interactuarCrearPersonaje() {
        Scanner sc = new Scanner(System.in);

        System.out.println("--- CREACIÓN DE NUEVO PERSONAJE ---");

        System.out.print("Nombre del personaje: ");
        String nombre = sc.nextLine();

        System.out.print("Raza: ");
        String raza = sc.nextLine();

        int nivel = 1;
        try {
            System.out.print("Nivel inicial: ");
            nivel = Integer.parseInt(sc.nextLine());
            if (nivel < 1) {
                System.out.println("[Aviso: El nivel no puede ser menor a 1. Se asignará nivel 1]");
                nivel = 1;
            }
        } catch (NumberFormatException e) {
            System.out.println("Nivel no válido, se asigna 1 por defecto.");
        }

        System.out.print("ID del ítem inicial (ej: W01): ");
        String idItem = sc.nextLine();

        crearNuevoPersonaje(nombre, raza, nivel, idItem);
    }

    public void guardarYSalir() {
        JsonHelper jh = new JsonHelper();
        jh.writeList("practica7/Ficheros/personajes.json", this.personajes);
        System.out.println(" \nDatos guardados con éxito.");
    }
}