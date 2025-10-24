//Crea un programa que:
//Cree una lista de enteros (ArrayList<Integer>).
//Pida números por teclado hasta que el usuario introduzca un número negativo (ese no se añade).
//Muestre por pantalla todos los números de la lista y la suma total de los mismos.

import java.util.ArrayList;
import java.util.Scanner;

public class ejercicio13 {
    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);

        ArrayList<Integer> listaEnteros = new ArrayList<>();
        int numero;
        int suma = 0;

        // Pedir números hasta que el usuario introduzca un número negativo
        System.out.println("Introduce números enteros (número negativo para finalizar):");
        do {
            System.out.print("Número: ");
            numero = sc.nextInt();

            if (numero >= 0) {
                listaEnteros.add(numero);
                suma = suma + numero;
            }
        } while (numero >= 0);

        // Mostrar los números introducidos
        System.out.println("Números introducidos: ");
        for (int n : listaEnteros) {
            System.out.println(n);
        }

        // Mostrar la suma de los números introducidos
        System.out.println("Suma de los números introducidos: " + suma);
    }
}