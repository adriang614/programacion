package rpg.logic;

import rpg.dao.InventarioDAO;
import rpg.exception.FondosInsuficientesException;
import rpg.model.Item;
import rpg.model.Personaje;
import rpg.model.Ciudad;
import rpg.dao.PersonajeDAO;
import rpg.exception.NivelInsuficienteException;
import rpg.utils.Log;

import java.util.*;

public class GestionMundo {

     // Viajar de Ciudad.
    public void viajar(Personaje p, Ciudad destino) throws NivelInsuficienteException {
        if (p.getNivel() < destino.getNivelMinimoAcceso()) {
            String errorMsg = "Nivel insuficiente para entrar en " + destino.getNombre() + " (Requerido: " + destino.getNivelMinimoAcceso() + ")";
            Log.escribirLog("ERROR", "NivelInsuficienteException - " + errorMsg);
            throw new NivelInsuficienteException(errorMsg);
        }

        p.setCiudadActual(destino);
        new PersonajeDAO().actualizarCiudad(p.getId(), destino.getId());
        Log.escribirLog("INFO", p.getNombre() + " viajó a " + destino.getNombre());
    }

    // Compra de Items.
    public void comprarItem(Personaje p, Item item, int cantidad) throws FondosInsuficientesException {
        int costeTotal = item.getPrecioOro() * cantidad;

        // 1. Comprobar dinero
        if (p.getOro() < costeTotal) {
            String errorMsg = "El personaje " + p.getNombre() + " no tiene suficiente oro. " +
                    "Requerido: " + costeTotal + ", Disponible: " + p.getOro();

            Log.escribirLog("ERROR", "Intento de compra fallido: FondosInsuficientesException - " + errorMsg);

            throw new FondosInsuficientesException(errorMsg);
        }

        // 2. Si hay dinero, se hace la compra
        // Se actualiza el objeto
        p.setOro(p.getOro() - costeTotal);

        // Se actualiza la base de datos
        PersonajeDAO pDAO = new PersonajeDAO();
        InventarioDAO invDAO = new InventarioDAO();

        pDAO.actualizarOro(p.getId(), p.getOro()); // Se resta el oro
        invDAO.agregarItem(p.getId(), item.getId(), cantidad); // Se añade la compra al inventario

        Log.escribirLog("INFO", "Compra exitosa: " + p.getNombre() + " ha comprado " +
                cantidad + "x " + item.getNombre() + " por " + costeTotal + " de oro.");

        System.out.println("¡Compra realizada! Nuevo saldo: " + p.getOro() + " de oro.");
    }

    // Cobro de impuestos.
    public void cobrarImpuestos(List<Personaje> residentes) {
        PersonajeDAO pDAO = new PersonajeDAO();
        Iterator<Personaje> it = residentes.iterator();

        while (it.hasNext()) {
            Personaje p = it.next();
            int nuevoOro = p.getOro() - 20;
            p.setOro(nuevoOro);

            if (p.getOro() < 0) {
                // Desterrado
                pDAO.desterrarPersonaje(p.getId());
                it.remove();
                Log.escribirLog("WARNING", p.getNombre() + " ha sido desterrado por impagos.");
            } else {
                // Pago normal
                pDAO.actualizarOro(p.getId(), p.getOro());
                Log.escribirLog("INFO", "Impuestos cobrados a " + p.getNombre());
            }
        }
    }

    // TOP jugadores ricos
    public void mostrarTopRicos() {
        PersonajeDAO pDAO = new PersonajeDAO();
        List<Personaje> todos = pDAO.obtenerPersonajes();

        Collections.sort(todos, new Comparator<Personaje>() {
            @Override
            public int compare(Personaje p1, Personaje p2) {
                // De mayor a menor
                return Integer.compare(p2.getOro(), p1.getOro());
            }
        });

        System.out.println("\n--- 🏆 TOP 3 MÁS RICOS ---");
        int tope = Math.min(3, todos.size());
        for (int i = 0; i < tope; i++) {
            Personaje p = todos.get(i);
            System.out.println((i + 1) + ". " + p.getNombre() + " (" + p.getOro() + " g)");
        }
    }

    // Censo de clases
    public void mostrarCensoClases() {
        PersonajeDAO pDAO = new PersonajeDAO();
        List<Personaje> todos = pDAO.obtenerPersonajes();
        HashMap<String, Integer> censo = new HashMap<>();

        for (Personaje p : todos) {
            String nombreClase;

            if (p.getClase() != null && p.getClase().getNombre() != null) {
                nombreClase = p.getClase().getNombre();
            } else {
                nombreClase = "Clase Desconocida"; // Para personajes sin clase
            }

            if (censo.containsKey(nombreClase)) {
                censo.put(nombreClase, censo.get(nombreClase) + 1);
            } else {
                censo.put(nombreClase, 1);
            }
        }

        System.out.println("\n--- 📊 CENSO DE CLASES ---");
        if (censo.isEmpty()) {
            System.out.println("No hay personajes registrados en el reino.");
        } else {
            for (String clase : censo.keySet()) {
                System.out.println("- " + clase + ": " + censo.get(clase));
            }
        }
    }
}