import java.util.Scanner;

public class ejercicio7 {

    public static void main(String[] args) {
        // Programa Java que llene un array con 10 números enteros que se leen por teclado.
        // A continuación calcula y muestra la media de los valores positivos y la de los valores negativos del array.

        //Crear array con 10 posiciones
        Scanner sc = new Scanner(System.in);
        int[] a = new int[10];

        //Guardar los numeros del array leyendo por teclado
        for (int i = 0; i < a.length; i++) {
            System.out.println("Introduce el valor:");
            a[i] = sc.nextInt();
        }

        //Identificar positivos y negativos
        int positivos = 0;
        int negativos = 0;
        int sumapos = 0;
        int sumaneg = 0;

        for (int i = 0; i < a.length; i++) {
            if (a[i] > 0) {
                sumapos = sumapos + a[i];
                positivos++;
            } else if (a[i] < 0) {
                sumaneg = sumaneg + a[i];
                negativos++;
            }
        }

        //media positivos
        if (positivos > 0) {
            double mediapos = (double) sumapos / positivos;
            System.out.println("La media de los valores positivos es: " + mediapos);
        } else {
            System.out.println("No hay valores positivos.");
        }

        //media negativos
        if (negativos > 0) {
            double medianeg = (double) sumaneg / negativos;
            System.out.println("La media de los valores negativos es: " + medianeg);
        } else {
            System.out.println("No hay valores negativos.");
        }
    }
}