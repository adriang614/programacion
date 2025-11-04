import java.util.Scanner;

//Crear un programa que cuando se le introduzca números enteros rellene
// un array de 6 filas por 10 columnas con números enteros positivos comprendidos
// entre 0 y 1000 (ambos incluidos). A continuación, el programa deberá:
//dar la posición del número máximo y mínimo
//la suma total de todas las filas y columnas
//la suma de todas las columnas
//la suma de todas las filas.
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
                int numero; // Variable para leer el número
                do {
                    System.out.print("Número [" + i + "][" + j + "]: ");
                    numero = sc.nextInt();

                    if (numero < 0 || numero > 1000) {
                        System.out.println("Debes introducir únicamente números entre 0 y 1000. Inténtalo de nuevo.");
                    }
                } while (numero < 0 || numero > 1000); // Repite mientras sea inválido

                a[i][j] = numero; // Guardar número válido en array
            }
        }
    }
}
