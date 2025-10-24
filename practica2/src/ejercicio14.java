//Crea un programa que:
//Cree una lista de Strings (ArrayList<String>).
//Pida al usuario nombres por teclado hasta que introduzca la palabra "fin".
//Luego pida un nombre a buscar y diga si está en la lista o no, mostrando también en qué posición se encuentra si existe.

import java.util.ArrayList;
import java.util.Scanner;

public class ejercicio14 {
    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);

        ArrayList<String> listaS = new ArrayList<>();
        String nombre;

        // Pedir números hasta que el usuario introduzca un número negativo
        System.out.println("Introduce nombres (escribe fin para finalizar):");
        do {
            System.out.print("Nombre: ");
            nombre = sc.nextLine();

            if (!nombre.equals("fin")) {
                listaS.add(nombre);
            }
        } while (!nombre.equals("fin"));

        System.out.println("¿Qué nombre quieres buscar?");
        String nombre_buscado = sc.nextLine();

        if (listaS.contains(nombre_buscado)){
            int posicion = listaS.indexOf(nombre_buscado);
            System.out.println(nombre_buscado + " está en la lista en la posición: " + posicion);
        }
        else {
            System.out.println(nombre_buscado + " no está en la lista");
        }
    }
}