package rpg.ui;

import rpg.model.*;
import rpg.dao.*;
import rpg.logic.GestionMundo;
import rpg.logic.MotorCombate;
import rpg.exception.*;
import rpg.utils.Log;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Scanner sc = new Scanner(System.in);
    private GestionMundo mundo = new GestionMundo();
    private MotorCombate motor = new MotorCombate();
    private Personaje personajeActivo = null;

    public void iniciarMenu() {
        int opcion = -1;
        do {
            System.out.println("\n--- ⚔️ RPG DINASTÍA BBDD ⚔️ ---");
            if (personajeActivo != null) {
                System.out.println("👤 Jugando como: " + personajeActivo.getNombre() + " | Oro: " + personajeActivo.getOro() + "g");
            }
            System.out.println("1. Crear Nuevo Personaje");
            System.out.println("2. Seleccionar Personaje para Jugar");
            System.out.println("3. Viajar de Ciudad");
            System.out.println("4. Entrar en la Tienda");
            System.out.println("5. Cobrar Impuestos");
            System.out.println("6. Arena de Combate PvP");
            System.out.println("7. Ver Estadísticas");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
                switch (opcion) {
                    case 1: crearPersonaje(); break;
                    case 2: seleccionarPersonaje(); break;
                    case 3: menuViajar(); break;
                    case 4: menuTienda(); break;
                    case 5:
                        menuImpuestos();
                        if (personajeActivo != null) {
                            personajeActivo = new PersonajeDAO().obtenerPorId(personajeActivo.getId());
                        }
                        break;
                    case 6: menuCombate(); break;
                    case 7:
                        mundo.mostrarTopRicos();
                        mundo.mostrarCensoClases();
                        break;
                    case 0: System.out.println("¡Hasta la próxima!"); break;
                    default: System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.err.println("❌ ERROR: " + e.getMessage());
                Log.escribirLog("ERROR", "Error en menú: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    private void crearPersonaje() {
        System.out.print("Nombre del héroe: ");
        String nombre = sc.nextLine();

        // SELECT de Razas y Clases (Requisito 1)
        List<Raza> razas = new RazaDAO().obtenerRazas();
        for (int i = 0; i < razas.size(); i++) System.out.println((i + 1) + ". " + razas.get(i).getNombre());
        System.out.print("Elige Raza: ");
        Raza r = razas.get(Integer.parseInt(sc.nextLine()) - 1);

        List<Clase> clases = new ClaseDAO().obtenerClases();
        for (int i = 0; i < clases.size(); i++) System.out.println((i + 1) + ". " + clases.get(i).getNombre());
        System.out.print("Elige Clase: ");
        Clase c = clases.get(Integer.parseInt(sc.nextLine()) - 1);

        Personaje nuevo = new Personaje(nombre, r, c);
        new PersonajeDAO().insertar(nuevo);
        Log.escribirLog("INFO", "Nuevo personaje creado: " + nombre);
        System.out.println("¡Personaje guardado!");
    }

    private void seleccionarPersonaje() {
        List<Personaje> lista = new PersonajeDAO().obtenerPersonajes();
        for (int i = 0; i < lista.size(); i++) System.out.println((i + 1) + ". " + lista.get(i).getNombre());
        System.out.print("Selecciona tu personaje: ");
        personajeActivo = lista.get(Integer.parseInt(sc.nextLine()) - 1);
    }

    private void menuViajar() throws NivelInsuficienteException {
        if (personajeActivo == null) return;
        List<Ciudad> ciudades = new CiudadDAO().obtenerCiudades();
        for (int i = 0; i < ciudades.size(); i++)
            System.out.println((i+1) + ". " + ciudades.get(i).getNombre() + " (Nivel min: " + ciudades.get(i).getNivelMinimoAcceso() + ")");

        System.out.print("¿A dónde quieres viajar?: ");
        Ciudad destino = ciudades.get(Integer.parseInt(sc.nextLine()) - 1);
        mundo.viajar(personajeActivo, destino);
    }

    private void menuTienda() throws FondosInsuficientesException {
        if (personajeActivo == null) return;
        List<Item> items = new ItemDAO().obtenerItems();
        for (int i = 0; i < items.size(); i++)
            System.out.println((i+1) + ". " + items.get(i).getNombre() + " - " + items.get(i).getPrecioOro() + "g");

        System.out.print("¿Qué quieres comprar?: ");
        int idx = Integer.parseInt(sc.nextLine()) - 1;
        System.out.print("Cantidad: ");
        int cant = Integer.parseInt(sc.nextLine());
        mundo.comprarItem(personajeActivo, items.get(idx), cant);
    }

    private void menuImpuestos() {
        System.out.println("Cobrando impuestos a todos los personajes del reino...");
        List<Personaje> todos = new PersonajeDAO().obtenerPersonajes();
        mundo.cobrarImpuestos(todos);
    }

    private void menuCombate() {
        if (personajeActivo == null) {
            System.out.println("⚠️ Primero selecciona un personaje (Opción 2).");
            return;
        }

        System.out.println("Selecciona un oponente:");
        List<Personaje> todos = new PersonajeDAO().obtenerPersonajes();
        for (int i = 0; i < todos.size(); i++) {
            System.out.println((i + 1) + ". " + todos.get(i).getNombre());
        }

        int seleccion = Integer.parseInt(sc.nextLine()) - 1;
        Personaje enemigo = todos.get(seleccion);

        if (personajeActivo.getId() == enemigo.getId()) {
            System.out.println("No puedes luchar contra ti mismo.");
            return;
        }

        InventarioDAO iDAO = new InventarioDAO();
        HabilidadDAO hDAO = new HabilidadDAO();

        List<Item> invAtacante = iDAO.obtenerItemsPersonaje(personajeActivo.getId());
        List<Item> invEnemigo = iDAO.obtenerItemsPersonaje(enemigo.getId());

        List<Habilidad> habsAtacante = hDAO.obtenerHabilidadesPersonaje(personajeActivo.getId());
        List<Habilidad> habsEnemigo = hDAO.obtenerHabilidadesPersonaje(enemigo.getId());

        motor.iniciarDuelo(
                personajeActivo, enemigo,
                invAtacante, invEnemigo,
                habsAtacante, habsEnemigo
        );
    }
}