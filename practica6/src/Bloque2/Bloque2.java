package Bloque2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Bloque2 {

    private HashMap<String, ArrayList<String>> gremios = new HashMap<>();
    private ArrayList<String> miembrosMagos = new ArrayList<>();
    private ArrayList<String> miembrosMonjes = new ArrayList<>();


    public Bloque2() {
        elRepositorioDeGremios();
        elSistemaDeLoot();
        rastreadorDeEstadisticasComplejas();
        elBuscadorDeTraidores();
    }

    public void elRepositorioDeGremios() {
        miembrosMagos.add("MagoHielo");
        miembrosMagos.add("MagoViento");
        miembrosMagos.add("MagoFuego");
        gremios.put("Magos", miembrosMagos);

        miembrosMonjes.add("MonjeSabio");
        miembrosMonjes.add("MonjeFuerte");
        miembrosMonjes.add("MonjeRápido");
        gremios.put("Monjes", miembrosMonjes);

        for (String gremio : gremios.keySet()) {
            if (gremio.equals("Magos")) {
                for (String miembro : gremios.get(gremio)) {
                    System.out.println("Miembro del gremio " + gremio + " : " + miembro);
                }
            }
        }
    }

    public void elSistemaDeLoot() {
        // Crear HashMap de monstruos y objetos
        HashMap<String, HashSet<String>> sistemaLoot = new HashMap<>();

        // Crear sets de objetos
        HashSet<String> lootTrasgo = new HashSet<>();
        HashSet<String> lootDragon = new HashSet<>();

        // Añadir objetos al Trasgo (incluyendo duplicado)
        lootTrasgo.add("Espada Oxidada");
        lootTrasgo.add("Moneda de Cobre");
        lootTrasgo.add("Espada Oxidada"); // duplicado ignorado

        sistemaLoot.put("Trasgo", lootTrasgo);

        // Añadir objetos al Dragón
        lootDragon.add("Espada Legendaria");
        lootDragon.add("Armadura Antigua");
        lootDragon.add("Gema Roja");

        sistemaLoot.put("Dragon", lootDragon);

        // Mostrar el loot del Trasgo
        for (String monstruo : sistemaLoot.keySet()) {
            if (monstruo.equals("Trasgo")) {
                for (String objeto : sistemaLoot.get(monstruo)) {
                    System.out.println("Objeto soltado por " + monstruo + " : " + objeto);
                }
            }
        }
    }

    public void rastreadorDeEstadisticasComplejas() {

        // Crear HashMap principal (Jugador -> Estadísticas)
        HashMap<String, HashMap<String, Integer>> jugadores = new HashMap<>();

        // Crear estadísticas de Conan
        HashMap<String, Integer> statsConan = new HashMap<>();
        statsConan.put("Fuerza", 18);
        statsConan.put("Destreza", 12);

        jugadores.put("Conan", statsConan);

        // Crear estadísticas de Legolas
        HashMap<String, Integer> statsLegolas = new HashMap<>();
        statsLegolas.put("Fuerza", 10);
        statsLegolas.put("Destreza", 20);

        jugadores.put("Legolas", statsLegolas);

        // Buscar a Conan y sumarle +2 a Fuerza
        if (jugadores.containsKey("Conan")) {
            HashMap<String, Integer> estadisticas = jugadores.get("Conan");

            if (estadisticas.containsKey("Fuerza")) {
                estadisticas.put("Fuerza", estadisticas.get("Fuerza") + 2);
            }
        }

        // Mostrar estadísticas de Conan
        for (String jugador : jugadores.keySet()) {
            if (jugador.equals("Conan")) {
                System.out.println("Estadísticas de " + jugador + ": " + jugadores.get(jugador));
            }
        }
    }

    public void elBuscadorDeTraidores() {

        // Crear HashMap de gremios
        HashMap<String, ArrayList<String>> gremios = new HashMap<>();

        // Crear listas de miembros
        ArrayList<String> magos = new ArrayList<>();
        ArrayList<String> guerreros = new ArrayList<>();

        // Añadir miembros (incluyendo a Judas)
        magos.add("Pepe");
        magos.add("Judas");
        magos.add("Juan");

        guerreros.add("Paco");
        guerreros.add("Luis");
        guerreros.add("Antonio");

        gremios.put("Magos", magos);
        gremios.put("Guerreros", guerreros);

        // Recorrer todos los gremios para buscar a Judas
        for (String gremio : gremios.keySet()) {

            if (gremios.get(gremio).contains("Judas")) {

                System.out.println("Judas encontrado en el gremio: " + gremio);

                // Eliminarlo inmediatamente
                gremios.get(gremio).remove("Judas");

                System.out.println("Judas ha sido expulsado del gremio " + gremio);
            }
        }
    }



}
