package bloque1;

import java.util.*;

public class Bloque1 {

    // Crear ArrayList
    private ArrayList<String> registroCombate = new ArrayList<>();

    // Crear HashSet
    private HashSet<String> villanos = new HashSet<>();

    public Bloque1() {
        elRegistroDeMuertes();
        System.out.println();
        elCensoUnico();
        System.out.println();
        bolsaOro();
        //limpiezaDelCalabozo();
        //mercadoDeHechizos();
        //expulsionDelReino();
    }

    public ArrayList<String> elRegistroDeMuertes() {
        registroCombate.add("Orco derrotado");
        registroCombate.add("Poción usada");
        registroCombate.add("Ataque usado");
        registroCombate.add("Enemigo derrotado");
        registroCombate.add("Partida guardada");

        System.out.println ("Tercer evento: " + registroCombate.get(2));

        return registroCombate;
    }

    public void elCensoUnico() {
        villanos.add("Juan");
        villanos.add("Morgoth");
        villanos.add("Raul");
        villanos.add("Morgoth");
        villanos.add("Pepe");

        System.out.println("Tamaño del HashSet villanos: " + villanos.size());
    }


    public void bolsaOro() {
        Map<String, Integer> mapa1 = new HashMap<>();

        mapa1.put("Finn", 56);
        mapa1.put("Jake", 63);
        mapa1.put("Kai", 24);

        for (Map.Entry<String,Integer> e : mapa1.entrySet()) {
            System.out.println("Aventurero :" + e.getKey() + " Oro: " + e.getValue());
        }
    }

}