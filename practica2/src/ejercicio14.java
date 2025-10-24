//Crea un programa que:
//Cree una lista de Strings (ArrayList<String>).
//Pida al usuario nombres por teclado hasta que introduzca la palabra "fin".
//Luego pida un nombre a buscar y diga si está en la lista o no, mostrando también en qué posición se encuentra si existe.

import java.util.ArrayList;
import java.util.Scanner;

public class ejercicio14 {
    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);

        // Crear lista de Strings
        ArrayList<String> listaS = new ArrayList<>();
        String nombre;

        // Pedir al usuario nombres por teclado hasta que introduzca la palabra "fin".
        System.out.println("Introduce nombres (escribe fin para finalizar):");
        do {
            System.out.print("Nombre: ");
            nombre = sc.nextLine();

            if (!nombre.equals("fin")) {
                listaS.add(nombre);
            }
        } while (!nombre.equals("fin"));

        // Pedir un nombre a buscar
        System.out.println("¿Qué nombre quieres buscar?");
        String nombre_buscado = sc.nextLine();

        // Buscar
        int posicion = -1;

        for (int i = 0; i < listaS.size(); i++) {
            if (listaS.get(i).equals(nombre_buscado)) {
                posicion = i;
            }
        }

        // Mostrar resultado
        if (posicion == -1) {
            System.out.println(nombre_buscado + " no está en la lista");
        } else {
            System.out.println(nombre_buscado + " está en la lista en la posición: " + posicion);
        }
    }
}