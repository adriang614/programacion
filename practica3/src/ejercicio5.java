import java.util.Scanner;

//Crear un programa que cuando se le introduzca números enteros rellene
// un array de 6 filas por 10 columnas con números enteros positivos comprendidos
// entre 0 y 1000 (ambos incluidos).
// A continuación, el programa deberá:
// dar la posición del número máximo y mínimo
// la suma total de todas las filas y columnas
// la suma de todas las columnas
// la suma de todas las filas.
public class ejercicio5 {
    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);

        int filas = 6;
        int columnas = 10;
        int[][] a = new int[filas][columnas];

        // Rellenar array con números que estén entre el 0 y el 1000
        System.out.println("Introduce números enteros positivos entre el 0 y el 1000:");
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                int aleatorio = (int) (Math.random()*1000 + 1);
                a[i][j] = aleatorio;
            }
        }
        int max = a[0][0];
        int min = a[0][0];
        int filaMax = 0;
        int colMax = 0;
        int filaMin = 0;
        int colMin = 0;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (a[i][j] > max) {
                    max = a[i][j];
                    filaMax = i;
                    colMax = j;
                }
                if (a[i][j] < min) {
                    min = a[i][j];
                    filaMin = i;
                    colMin = j;
                }
            }
        }
        System.out.println("Número máximo: " + max + " en posición [" + filaMax + "][" + colMax + "]");
        System.out.println("Número mínimo: " + min + " en posición [" + filaMin + "][" + colMin + "]");


        System.out.println("Suma de cada fila:");
        for (int i = 0; i < filas; i++) {
            int sumaFila = 0;
            for (int j = 0; j < columnas; j++) {
                sumaFila += a[i][j];
            }
            System.out.println("Fila " + i + ": " + sumaFila);
        }



    }
}