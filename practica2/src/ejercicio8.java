import java.util.Scanner;

public class ejercicio8 {

    public static void main(String[] args) {
        // Programa Java para leer la altura de N personas y calcular la altura media.
        // Calcular cuántas personas tienen una altura superior a la media y cuántas tienen una altura inferior a la media.
        // El valor de N se pide por teclado y debe ser entero positivo.

        Scanner sc = new Scanner(System.in);

        //Crear array con el número de personas
        System.out.println("¿De cuantas personas quieres calcular la altura media?");
        int n = sc.nextInt();
        int[] a = new int[n];

        //Calcular la altura media
        int na = 0;
        int al = 0;
        for (int i = 0; i < a.length; i++) {
            System.out.println("Introduce las alturas de las personas: ");
            a[i] = sc.nextInt();
            ;
        }
























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