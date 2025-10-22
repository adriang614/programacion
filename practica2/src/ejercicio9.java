//Crea un programa en Java que:
//Pida al usuario por teclado 10 números enteros y los guarde en un array.
//Recorra el array para encontrar:
//El valor máximo y la posición en la que aparece.
//El valor mínimo y la posición en la que aparece.
//Muestre el resultado en consola

import java.util.Scanner;

public class ejercicio9 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        //Pedir al usuario por teclado 10 números y guardarlos en un array.
        System.out.println("Introduce 10 números");
        int[] a = new int[10];
        for (int i = 0; i < a.length; i++) {
            a[i] = sc.nextInt();
        }

        //Recorrer el array para encontrar el valor minimo, máximo y sus posiciones.
        int max = a[0];
        int posmax = 0;
        int min =  a[0];
        int posmin = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
                posmax = i;
            }
            else if (a[i] < min) {
                min = a[i];
                posmin = i;
            }
        }

        //Mostrar el resultado en consola.
        System.out.println("Valor máximo: " + max + " en la posición: " + posmax);
        System.out.println("Valor mínimo: " + min + " en la posición: " + posmin);
    }
}