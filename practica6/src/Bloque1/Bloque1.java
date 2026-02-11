package Bloque1;

import java.util.*;

public class Bloque1 {

    // Crear ArrayList
    private ArrayList<String> registroCombate = new ArrayList<>();

    // Crear HashSet
    private HashSet<String> villanos = new HashSet<>();

    public Bloque1() {
        elRegistroDeMuertes();
        elCensoUnico();
        bolsaDeOro();
        limpiezaDelCalabozo();
        mercadoDeHechizos();
        expulsionDelReino();
    }

    public ArrayList<String> elRegistroDeMuertes() {

        // Añadir 5 elementos
        registroCombate.add("Orco derrotado");
        registroCombate.add("Poción usada");
        registroCombate.add("Golpe crítico recibido");
        registroCombate.add("Hechizo lanzado");
        registroCombate.add("Tesoro encontrado");

        // Mostrar el tercer evento
        System.out.println("Tercer evento: " + registroCombate.get(2));

        return registroCombate;
    }

    public void elCensoUnico() {

        // Añadir 2 elementos iguales
        villanos.add("Morgoth");
        villanos.add("Morgoth");
        villanos.add("Sauron");


        // Mostrar tamaño del HashSet para ver que no se ha añadido duplicado
        System.out.println("El tamaño del HashSet villanos es: " + villanos.size());
    }

    public void bolsaDeOro() {

        //Crear HashMap e introducirle las clave/valor (aventurero/cantidad oro)
        HashMap<String, Integer> mapa1 = new HashMap<>(Map.of("Juan", 2, "Pepe", 3, "Jose", 4));

        //Mostrar el valor de una clave (mostrar la cantidad de oro usando el nombre del aventurero)
        System.out.println("La cantidad de oro del aventurero Juan es: " + mapa1.get("Juan"));
    }

    public void limpiezaDelCalabozo() {

        //Eliminar el elemento mas antiguo (índice 0)
        registroCombate.remove(0);

        //Añadir el elemento "Dragón avistado" al final de la lista
        registroCombate.add("Dragón avistado");

        System.out.println("Se ha eliminado el elemento mas antiguo y se ha añadido el nuevo elemento al final de la lista correctamente");
    }

    public void mercadoDeHechizos() {

        //Crear HashMap e introducirle las clave/valor (hechizos/costes de maná)
        HashMap<String, Double> mapa2 = new HashMap<>(Map.of("Bola de Fuego", 75.0, "Curación Menor", 20.0, "Rayo Arcano", 55.0, "Tormenta de Hielo", 90.0, "Escudo Mágico", 40.0));

        //Imprimir los hechizoa que cuesten mas de 50 de maná
        for (String hechizo : mapa2.keySet()){
            if (mapa2.get(hechizo) > 50){
                System.out.println(hechizo + " -> " + mapa2.get(hechizo));
            }
        }
    }

    public void expulsionDelReino() {
        if (villanos.contains("Sauron")){
            villanos.remove("Sauron");
            System.out.println("Sauron ha sido derrotado, eliminandolo...");
        }
    }
}
