import java.util.Scanner;

public class ejercicio5 {

    public static void main(String[] args) {
        // Crea un array de números donde le indicamos por teclado el tamaño del array, rellenaremos el array con
        // números aleatorios entre 0 y 9, al final muestra por pantalla el valor de cada posición
        // y la suma de todos los valores.

        Scanner sc = new Scanner(System.in);
        System.out.println("¿Qué tamaño quieres que tenga el array?");
        int v = sc.nextInt();
        int[] a = new int[v];

        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random()*10);
            System.out.println("valor " + i + ": " + a[i]);
        }

        int suma = 0;
        for (int i = 0; i < a.length; i++){
            suma = suma + a[i];
        }

        System.out.println("La suma de todos los números es: " + suma);
    }
}