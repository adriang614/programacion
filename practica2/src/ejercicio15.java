//Crea un programa que:
//Cree una lista de enteros (ArrayList<Integer>).
//Pida al usuario 10 números enteros y los añada a la lista.
//Elimine los valores duplicados manteniendo solo el primero que apareció.
//Ordene la lista de menor a mayor y la muestre por pantalla.

import java.util.ArrayList;
import java.util.Scanner;

public class ejercicio15 {
    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);

        ArrayList<Integer> listaNumeros = new ArrayList<>();
        int numero;

        System.out.println("Introduce 10 números enteros:");

        // Pedir 10 números
        for (int i = 0; i < 10; i++) {
            numero = sc.nextInt();
            listaNumeros.add(numero);
        }

        // Mostrar la lista con duplicados
        System.out.println("Lista con duplicados: ");
        for (int i = 0; i < listaNumeros.size(); i++) {
            System.out.println(listaNumeros.get(i));
        }

        // Crear una nueva lista sin duplicados
        ArrayList<Integer> listaSinDuplicados = new ArrayList<>();

        for (int i = 0; i < listaNumeros.size(); i++) {
            int repetido = 0;

            // Comprobar si el número ya estaba en listaSinDuplicados
            for (int j = 0; j < listaSinDuplicados.size(); j++) {
                if (listaNumeros.get(i).equals(listaSinDuplicados.get(j))) {
                    repetido = 1;
                }
            }

            // Si no está repetido, se añade
            if (repetido == 0) {
                listaSinDuplicados.add(listaNumeros.get(i));
            }
        }

        // Ordenar manualmente de menor a mayor
        for (int i = 0; i < listaSinDuplicados.size() - 1; i++) {
            for (int j = 0; j < listaSinDuplicados.size() - 1 - i; j++) {
                if (listaSinDuplicados.get(j) > listaSinDuplicados.get(j + 1)) {
                    int aux = listaSinDuplicados.get(j);
                    listaSinDuplicados.set(j, listaSinDuplicados.get(j + 1));
                    listaSinDuplicados.set(j + 1, aux);
                }
            }
        }

        // Mostrar la lista final sin duplicados y ordenada
        System.out.println("Lista sin duplicados y ordenada: ");
        for (int i = 0; i < listaSinDuplicados.size(); i++) {
            System.out.print(listaSinDuplicados.get(i) + " ");
        }
    }
}