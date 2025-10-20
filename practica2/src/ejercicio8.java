// Programa Java para leer la altura de N personas y calcular la altura media.
// Calcular cuántas personas tienen una altura superior a la media y cuántas tienen una altura inferior a la media.
// El valor de N se pide por teclado y debe ser entero positivo.

import java.util.Scanner;

public class ejercicio8 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //Introducir el número de personas que queramos medir la altura
        System.out.println("¿De cuántas personas quieres calcular la altura media?");
        int n = sc.nextInt();

        //Comprobar que n es valido
        while (n <= 0) {
            System.out.println("Número inválido. Debe ser positivo.");
            System.out.println("¿De cuántas personas quieres calcular la altura media?");
            n = sc.nextInt();
        }

        // Ahora que n es válido, creamos el array
        int[] a = new int[n];

        //Calcular la altura media
        int at = 0;
        int am = 0;
        for (int i = 0; i < a.length; i++) {
            System.out.println("Introduce las alturas de las personas en cm: ");
            a[i] = sc.nextInt();
            at = a[i] + at;
        }
        am = at/n;
        System.out.println("La altura media de las personas es: " + am + "cm");

        //Calcular cuántas personas tienen una altura superior a la media y cuántas tienen una altura inferior a la media.
        int as = 0;
        int ai = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > am) {
                as++; // superior a la media
            }
            else if (a[i] < am) {
                ai++; // inferior a la media
            }
        }
        System.out.println("Número de personas con altura superior a la media: " + as);
        System.out.println("Número de personas con altura inferior a la media: " + ai);
    }
}