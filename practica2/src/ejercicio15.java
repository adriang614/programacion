//Crea un programa que:
//Cree una lista de enteros (ArrayList<Integer>).
//Pida al usuario 10 números enteros y los añada a la lista.
//Elimine los valores duplicados manteniendo solo el primero que apareció.
//Ordene la lista de menor a mayor y la muestre por pantalla.

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ejercicio15 {
    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);

        ArrayList<Integer> listaNumeros = new ArrayList<>();
        int numero;

        System.out.println("Introduce 10 números enteros:");

        for (int i = 0; i < 10; i++) {
            numero = sc.nextInt();
            listaNumeros.add(numero);
        }

        ArrayList<Integer> listaSinDuplicados = new ArrayList<>();

        for (Integer n : listaNumeros) {
            if (!listaSinDuplicados.contains(n)) {
                listaSinDuplicados.add(n);
            }
        }

        // Ordenar la lista de menor a mayor
        Collections.sort(listaSinDuplicados);

        // Mostrar la lista final
        System.out.println("Lista sin duplicados y ordenada: " + listaSinDuplicados);
    }
}