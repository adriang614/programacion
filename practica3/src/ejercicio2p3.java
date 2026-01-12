//Escribe un programa que solicite 20 números enteros.
// Estos números debemos de introducirlo en un array de 4 filas por 5 columnas.
// El programa mostrará las sumas parciales de filas y en las columnas el mayor número de la columna.
// La suma total debe aparecer en la esquina inferior derecha.

import java.util.Scanner;

public class ejercicio2p3 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int filas = 4;
        int columnas = 5;
        int[][] numeros = new int[filas][columnas];

        // Pedir 20 números
        System.out.println("Introduce 20 números enteros:");
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print("Número [" + i + "][" + j + "]: ");
                numeros[i][j] = sc.nextInt();
            }
        }

        // Mostrar la tabla con sumas de filas
        int sumaTotal = 0;

        for (int i = 0; i < filas; i++) {
            int sumaFila = 0;

            for (int j = 0; j < columnas; j++) {
                System.out.printf("%7d", numeros[i][j]);
                sumaFila += numeros[i][j];
            }

            System.out.printf(" | Σ fila %d = %d\n", i, sumaFila);
            sumaTotal += sumaFila;
        }

        // Separador
        System.out.println("-------------------------------------------------------------");

        // Calcular y mostrar suma de cada columna
        for (int j = 0; j < columnas; j++) {
            int sumaColumna = 0;
            for (int i = 0; i < filas; i++) {
                sumaColumna += numeros[i][j];
            }
            System.out.printf("%7d", sumaColumna);
        }

        // Mostrar la suma total
        System.out.printf(" | TOTAL = %d\n", sumaTotal);
    }
}
