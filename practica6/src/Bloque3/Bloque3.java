package Bloque3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Bloque3 {


    public Bloque3() {
        sistemaDeComercio("Sevilla");
        System.out.println();
        gestionArbolHabilidades();
        System.out.println();
        historialDeIncursiones();
        System.out.println();
        sistemaMensajeriaGlobal();
        System.out.println();
        simuladorEconomiaSubastas();
    }

    public void sistemaDeComercio(String ciudadBuscada) {

        // Crear HashMap de precios base (Item -> Precio)
        HashMap<String, Double> preciosBase = new HashMap<>();
        preciosBase.put("Espada", 100.0);
        preciosBase.put("Escudo", 80.0);
        preciosBase.put("Pocion", 10.0);
        preciosBase.put("Armadura", 150.0);
        preciosBase.put("Anillo", 200.0);
        preciosBase.put("Casco", 60.0);

        // Crear HashMap de ciudades y stock
        HashMap<String, ArrayList<String>> ciudades = new HashMap<>();

        ArrayList<String> stockSevilla = new ArrayList<>();
        stockSevilla.add("Espada");
        stockSevilla.add("Escudo");
        stockSevilla.add("Pocion");
        stockSevilla.add("Armadura");
        stockSevilla.add("Anillo");
        stockSevilla.add("Casco");

        ArrayList<String> stockCadiz = new ArrayList<>();
        stockCadiz.add("Espada");
        stockCadiz.add("Pocion");

        ciudades.put("Sevilla", stockSevilla);
        ciudades.put("Cadiz", stockCadiz);

        // Buscar la ciudad y aplicar impuesto si tiene más de 5 items
        for (String ciudad : ciudades.keySet()) {

            if (ciudad.equals(ciudadBuscada)) {

                ArrayList<String> stock = ciudades.get(ciudad);

                if (stock.size() > 5) {
                    System.out.println("Impuesto de lujo aplicado en " + ciudad);

                    for (String item : stock) {
                        if (preciosBase.containsKey(item)) {
                            double precioNuevo = preciosBase.get(item) * 1.10;
                            System.out.println(item + " -> " + precioNuevo);
                        }
                    }

                } else {
                    System.out.println("Precios normales en " + ciudad);

                    for (String item : stock) {
                        System.out.println(item + " -> " + preciosBase.get(item));
                    }
                }
            }
        }
    }

    public void gestionArbolHabilidades() {

        // Crear estructura principal (Clase -> Habilidades)
        HashMap<String, HashMap<String, Boolean>> clases = new HashMap<>();

        // Crear habilidades del Paladin
        HashMap<String, Boolean> habilidadesPaladin = new HashMap<>();
        habilidadesPaladin.put("Enfoque", false);
        habilidadesPaladin.put("Golpe Divino", false);
        habilidadesPaladin.put("Escudo Sagrado", true);

        clases.put("Paladin", habilidadesPaladin);

        // Intentar desbloquear "Golpe Divino"
        if (clases.containsKey("Paladin")) {

            HashMap<String, Boolean> habilidades = clases.get("Paladin");

            // Verificar si "Enfoque" esta desbloqueada
            if (habilidades.get("Enfoque")) {

                habilidades.put("Golpe Divino", true);
                System.out.println("Golpe Divino ha sido desbloqueado.");

            } else {

                System.out.println("Error: No puedes desbloquear Golpe Divino sin tener Enfoque desbloqueado.");
            }
        }

        // Mostrar estado final de habilidades
        for (String clase : clases.keySet()) {
            System.out.println("Habilidades de " + clase + " : " + clases.get(clase));
        }
    }

    public void historialDeIncursiones() {

        // Crear estructura (Mazmorra -> Lista de raids)
        HashMap<String, List<HashSet<String>>> historial = new HashMap<>();

        // Crear raids de la mazmorra "CuevaOscura"
        List<HashSet<String>> raidsCueva = new ArrayList<>();

        HashSet<String> raid1 = new HashSet<>();
        raid1.add("Pepe");
        raid1.add("Juan");
        raid1.add("Paco");

        HashSet<String> raid2 = new HashSet<>();
        raid2.add("Pepe");
        raid2.add("Luis");
        raid2.add("Antonio");

        raidsCueva.add(raid1);
        raidsCueva.add(raid2);

        historial.put("CuevaOscura", raidsCueva);

        // Crear contador de participaciones
        HashMap<String, Integer> contadorJugadores = new HashMap<>();

        // Recorrer el histoiral
        for (String mazmorra : historial.keySet()) {

            for (HashSet<String> raid : historial.get(mazmorra)) {

                for (String jugador : raid) {

                    if (!contadorJugadores.containsKey(jugador)) {
                        contadorJugadores.put(jugador, 1);
                    } else {
                        contadorJugadores.put(jugador, contadorJugadores.get(jugador) + 1);
                    }
                }
            }
        }

        // Encontrar al jugador con más participaciones
        String mvp = "";
        int maxParticipaciones = 0;

        for (String jugador : contadorJugadores.keySet()) {
            if (contadorJugadores.get(jugador) > maxParticipaciones) {
                maxParticipaciones = contadorJugadores.get(jugador);
                mvp = jugador;
            }
        }

        System.out.println("Jugador más valioso: " + mvp + " con " + maxParticipaciones + " incursiones.");
    }

    public void sistemaMensajeriaGlobal() {

        // Crear HashMap de mensajes por jugador
        HashMap<String, List<String>> mensajesJugadores = new HashMap<>();

        // Crear historial de mensajes
        List<String> mensajesPepe = new ArrayList<>();
        mensajesPepe.add("Hola");
        mensajesPepe.add("Comercio?");
        mensajesPepe.add("Hola");

        List<String> mensajesJuan = new ArrayList<>();
        mensajesJuan.add("Voy al dungeon");
        mensajesJuan.add("Necesito ayuda");
        mensajesJuan.add("Gracias");

        mensajesJugadores.put("Pepe", mensajesPepe);
        mensajesJugadores.put("Juan", mensajesJuan);

        // Crear HashSet de jugadores silenciados
        HashSet<String> jugadoresSilenciados = new HashSet<>();

        // Analizar los últimos 3 mensajes de cada jugador
        for (String jugador : mensajesJugadores.keySet()) {

            List<String> historial = mensajesJugadores.get(jugador);

            if (historial.size() >= 3) {

                int inicio = historial.size() - 3;

                String m1 = historial.get(inicio);
                String m2 = historial.get(inicio + 1);
                String m3 = historial.get(inicio + 2);

                // Verificar si hay 2 mensajes iguales
                if (m1.equals(m2) || m1.equals(m3) || m2.equals(m3)) {

                    jugadoresSilenciados.add(jugador);

                    // Limpiar historial
                    historial.clear();

                    System.out.println("Jugador silenciado por spam: " + jugador);
                }
            }
        }

        System.out.println("Jugadores silenciados: " + jugadoresSilenciados);
    }

    public void simuladorEconomiaSubastas() {

        // Crear HashMap de items y pujsa
        HashMap<String, ArrayList<Double>> mercado = new HashMap<>();

        ArrayList<Double> pujasExcalibur = new ArrayList<>();
        pujasExcalibur.add(100.0);
        pujasExcalibur.add(150.0);
        pujasExcalibur.add(120.0);

        mercado.put("Excalibur", pujasExcalibur);

        // Crear HashMap de saldos de jugadores
        HashMap<String, Double> saldosJugadores = new HashMap<>();
        saldosJugadores.put("Pepe", 130.0);
        saldosJugadores.put("Juan", 200.0);
        saldosJugadores.put("Paco", 90.0);

        // Procesar la venta de "Escalibur"
        String item = "Excalibur";

        if (mercado.containsKey(item)) {

            ArrayList<Double> pujas = mercado.get(item);

            // Ordenar de mayor a menor
            pujas.sort((a, b) -> Double.compare(b, a));

            boolean vendido = false;

            for (Double puja : pujas) {

                // Suponemos que cada puja está ligada a un jugador (simulación simple)
                // Para este ejemplo, asignamos Pepe, Juan y Paco por orden de pujas
                String jugador = "";
                if (puja == 150.0) jugador = "Juan";
                else if (puja == 130.0) jugador = "Pepe"; // ejemplo, ajustable
                else jugador = "Paco";

                // Verificar si el jugador tiene suficiente oro
                if (saldosJugadores.getOrDefault(jugador, 0.0) >= puja) {

                    // Transferir el oro
                    double saldoActual = saldosJugadores.get(jugador);
                    saldosJugadores.put(jugador, saldoActual - puja);

                    // Eliminar item del mercado
                    mercado.remove(item);

                    System.out.println(item + " vendido a " + jugador + " por " + puja);
                    vendido = true;
                    break;

                } else {
                    System.out.println(jugador + " no tiene suficiente oro para la puja de " + puja);
                }
            }

            if (!vendido) {
                System.out.println("El item " + item + " no ha sido vendido.");
            }
        }
    }
}
