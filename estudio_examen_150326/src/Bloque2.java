import java.util.*;

public class Bloque2 {

    private List<Integer> a;

    public void pruebas () {
        List<Integer> alist = new ArrayList<>();
        alist.add(43);
        alist.remove(Integer.valueOf(43));
        alist.size();

        System.out.println();

        HashSet<String> hset = new HashSet<>();
        hset.size();
        hset.add("sf");
        hset.add("Pepe");
        hset.add("Pepe");
        boolean añadido1 = hset.add("Ana");
        boolean añadido2 = hset.add("Ana");
        System.out.println("¿Se añadió la segunda Ana?: " + añadido2);
        System.out.println("Tamaño del set: " + hset.size());
        if (hset.contains("Pepe")) {
            System.out.println("Ya esta pepe");
        }
        for (String valor : hset) {
            System.out.println(valor);
        }
        hset.remove("Pepe");

        System.out.println();

        HashMap<String, Integer> hmap = new HashMap<>();
        hmap.put("adrian", 9);
        System.out.println(hmap.get("adrian"));


        if (hmap.containsKey("jose")){
            System.out.println("Ya existe jose");
        }
        else {
            hmap.put("jose", 8);
            System.out.println(hmap.get("jose"));
        }

        int notaActual = hmap.get("adrian");
        hmap.put("adrian", notaActual + 1);
        System.out.println(hmap.get("adrian"));

        for (Integer nota : hmap.values()) {
            System.out.println(nota);
        }

        for (String nomnbre : hmap.keySet()) {
            System.out.println(nomnbre);
            System.out.println("Nota: " + hmap.get(nomnbre));
        }

        for (Map.Entry<String, Integer> entrada : hmap.entrySet()){
            System.out.println("Nombre: " + entrada.getKey() + " Nota: " + entrada.getValue());
        }

    }

    Bloque1 bl1 = new Bloque1();

    //PASAR DATOS DE LISTA A MAPA
    public void prueba2 () {
        List<Bloque1> lista = new ArrayList<>();
        HashMap<Integer, Bloque1> mapa = new HashMap<>();

        for (Bloque1 recorrido : lista) {
            mapa.put(recorrido.getId(), recorrido);
        }
    }

    public void ej () {
        List<String> invitados = new ArrayList<>();
        invitados.add("Lucia");
        invitados.add("Ana");
        invitados.add("Lucia");
        invitados.add("Pepi");
        invitados.add("Jose");

        HashSet<String> invitadosUnicos = new HashSet<>();

        for (String persona : invitados){
            invitadosUnicos.add(persona);
        }

        System.out.println("Hay " + invitadosUnicos.size() + " invitados únicos");
    }
}
