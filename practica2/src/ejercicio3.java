import java.util.Scanner;

public class ejercicio3 {

    public static void main(String[] args) {
        // Crea un array que contenga 5 números.
        // Realiza un programa que te muestre por pantalla SOLO el más pequeño de ellos.
        Scanner sc = new Scanner(System.in);
        int[] a = new int[5];

        for (int i = 0; i < 5; i++) {
            System.out.println("Introduce el valor:");
            a[i] = sc.nextInt();
        }

        int nummin = a[0];

        for (int i = 1; i < a.length; i++) {
            if (a[i] < nummin) {
                nummin = a[i];
            }
        }

        System.out.println("El número más pequeño es: " + nummin);
    }
}
