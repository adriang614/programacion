import java.util.ArrayList;
import java.util.List;

public class Bloque1 {

    private int id = 1;

    int contador = 3;
    String cadena = "hola";
    boolean comprobacion = false;
    double numero = 0;
    List<String> lista = new ArrayList<>();
    int[] array = new int[4];

    public void pruebas() {
        for (int i = 0 ; i < 10 ; i++) {
            System.out.println(i);
        }

        lista.add(cadena);
        for (String adios : lista ){
            System.out.println(adios);
        }

        while (numero < 10) {
            System.out.println(numero);
            numero++;
        }

        for (int i : array){
            System.out.println(i);
        }
    }

    public double calcularPromedioNivel (int[] niveles){

        if (niveles == null){
            return 0.0;
        }

        double media = 0;
        for (int nivel : niveles) {
            media += nivel;
        }

        media = media/niveles.length;
        System.out.println(media);
        return media;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
