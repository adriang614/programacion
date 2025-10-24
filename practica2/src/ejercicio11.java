//Escribe un programa java que invierta el orden de los valores de un array.
// Por invertir el orden de los valores de un array, me refiero que el último pasa a ser el primero,
// el penúltimo el segundo y así sucesivamente. PRUEBA CON UN ARRAY DE TAMAÑO 6.

import java.util.Scanner;

public class ejercicio11 {
    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);

        //Crear array de tamaño 6
        int valores[] = {1,2,3,4,5,6};
        int[] invertido = new int[6];

        //Inventir el array
        for (int i = 0; i < valores.length; i++) {
            invertido[i] = valores [valores.length - 1 - i];
        }

        //Mostrar el array invertido
        System.out.println("Array invertido:");
        for (int i = 0; i < invertido.length; i++) {
            System.out.println(invertido[i]);
        }
    }
}