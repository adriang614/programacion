import com.rpg.services.GestionMundo;

public class Main {
    public static void main(String[] args) {
        GestionMundo juego = new GestionMundo();

        juego.cargarDatos();
        juego.interactuarCrearPersonaje();
        juego.guardarYSalir();
    }
}