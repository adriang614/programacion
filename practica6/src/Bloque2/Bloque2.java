package Bloque2;

import java.util.ArrayList;
import java.util.HashMap;

public class Bloque2 {

    private HashMap<String, ArrayList<String>> gremios = new HashMap<>();
    private ArrayList<String> miembrosMagos = new ArrayList<>();
    private ArrayList<String> miembrosMonjes = new ArrayList<>();


    public Bloque2() {

    }

    public void elRepositorioDeGremios() {
        miembrosMagos.add("MagoHielo");
        miembrosMagos.add("MagoViento");
        miembrosMagos.add("MagoFuego");
        gremios.put("Magos", miembrosMagos);
    }
}
