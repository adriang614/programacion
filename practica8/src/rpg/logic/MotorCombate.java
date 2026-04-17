package rpg.logic;

import rpg.model.Personaje;
import rpg.model.Item;
import rpg.model.Habilidad;
import rpg.dao.PersonajeDAO;
import rpg.dao.PersonajeHabilidadDAO;
import rpg.exception.LimiteHabilidadesException;
import rpg.utils.Log;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class MotorCombate {

    private Scanner sc = new Scanner(System.in);


     // A. Preparación y Límite de Habilidades
    public void equiparHabilidad(int idPersonaje, int idHabilidad) throws LimiteHabilidadesException {
        PersonajeHabilidadDAO phDAO = new PersonajeHabilidadDAO();

        // Comprobar limite
        if (phDAO.contarHabilidadesEquipadas(idPersonaje) > 3) {
            throw new LimiteHabilidadesException("No puedes equipar más de 3 habilidades para el combate.");
        }

        // Si hay hueco, se actualiza BD
        phDAO.equiparHabilidad(idPersonaje, idHabilidad, true);
    }


     // B. El Bucle de Combate (PvP Táctico)
    public void iniciarDuelo(Personaje p1, Personaje p2, List<Item> inv1, List<Item> inv2,
                             List<Habilidad> habs1, List<Habilidad> habs2) {

        Log.escribirLog("INFO", "Inicio de combate: " + p1.getNombre() + " vs " + p2.getNombre());

        // 1. Cálculo de Estadísticas
        int atk1 = calcularAtaqueTotal(p1, inv1);
        int def1 = calcularDefensaTotal(inv1);
        int atk2 = calcularAtaqueTotal(p2, inv2);
        int def2 = calcularDefensaTotal(inv2);

        // 2. Control de usos en memoria
        Map<Habilidad, Integer> usosHabs1 = inicializarUsos(habs1);
        Map<Habilidad, Integer> usosHabs2 = inicializarUsos(habs2);

        // 3. Bucle de turnos
        boolean turnoP1 = true;
        while (p1.getVidaActual() > 0 && p2.getVidaActual() > 0) {
            if (turnoP1) {
                ejecutarTurno(p1, p2, atk1, def2, habs1, usosHabs1);
            } else {
                ejecutarTurno(p2, p1, atk2, def1, habs2, usosHabs2);
            }
            turnoP1 = !turnoP1;
        }

        // 4. Fin del Combate y Recompensas
        Personaje ganador;
        Personaje perdedor;

        if (p1.getVidaActual() > 0) {
            ganador = p1;
            perdedor = p2;
        } else {
            ganador = p2;
            perdedor = p1;
        }

        gestionarRecompensas(ganador, perdedor);
    }

    private void ejecutarTurno(Personaje atacante, Personaje defensor, int atkAtacante,
                               int defDefensor, List<Habilidad> habilidades, Map<Habilidad, Integer> usos) {

        System.out.println("\n--- Turno de " + atacante.getNombre() + " (HP: " + atacante.getVidaActual() + ") ---");
        System.out.println("1. Ataque Básico");
        for (int i = 0; i < habilidades.size(); i++) {
            Habilidad h = habilidades.get(i);
            System.out.println((i + 2) + ". " + h.getNombre() + " (Usos: " + usos.get(h) + "/" + h.getUsosMaximos() + ")");
        }

        int opcion = sc.nextInt();
        int danoFinal = 0;

        if (opcion >= 2 && opcion <= habilidades.size() + 1) {
            Habilidad elegida = habilidades.get(opcion - 2);
            if (usos.get(elegida) > 0) {
                // Daño Habilidad
                danoFinal = elegida.getDanoBase() - (defDefensor / 2);
                usos.put(elegida, usos.get(elegida) - 1);
                System.out.println("¡Usa " + elegida.getNombre() + "!");
            } else {
                System.out.println("Sin usos. Realizando ataque básico...");
                danoFinal = atkAtacante - (defDefensor / 2);
            }
        } else {
            // Ataque Básico
            danoFinal = atkAtacante - (defDefensor / 2);
            System.out.println("¡Realiza un ataque básico!");
        }

        if (danoFinal < 0) danoFinal = 0;
        defensor.setVidaActual(defensor.getVidaActual() - danoFinal);
        System.out.println("Daño causado: " + danoFinal);
    }

    // Métodos para calcular
    private int calcularAtaqueTotal(Personaje p, List<Item> inv) {
        int total = p.getRaza().getBonificadorFuerza();
        for (Item i : inv) total += i.getBonificadorAtaque();
        return total;
    }

    private int calcularDefensaTotal(List<Item> inv) {
        int total = 0;
        for (Item i : inv) total += i.getBonificadorDefensa();
        return total;
    }

    private Map<Habilidad, Integer> inicializarUsos(List<Habilidad> habs) {
        Map<Habilidad, Integer> mapa = new HashMap<>();
        for (Habilidad h : habs) mapa.put(h, h.getUsosMaximos());
        return mapa;
    }

    // RECOMPENSAS.
    private void gestionarRecompensas(Personaje ganador, Personaje perdedor) {
        int robo = (int) (perdedor.getOro() * 0.20);
        ganador.setOro(ganador.getOro() + robo);
        perdedor.setOro(perdedor.getOro() - robo);

        PersonajeDAO pDAO = new PersonajeDAO();
        pDAO.actualizarOro(ganador.getId(), ganador.getOro());
        pDAO.actualizarOro(perdedor.getId(), perdedor.getOro());

        Log.escribirLog("INFO", "Ganador: " + ganador.getNombre() + " roba " + robo + " monedas a " + perdedor.getNombre());
        System.out.println("\n¡" + ganador.getNombre() + " ha ganado el combate!");
        System.out.println("Ha robado " + robo + " monedas de oro.");
    }
}