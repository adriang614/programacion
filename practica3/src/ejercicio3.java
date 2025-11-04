// Modifica el programa anterior de tal forma que los números que se introducen
// en el array se generen de forma aleatoria (valores entre 1 y 1999).
public class ejercicio3 {
    public static void main(String[] args) {

        int filas = 4;
        int columnas = 5;
        int[][] numeros = new int[filas][columnas];

        // Rellenar array con numeros aleatorios
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                int aleatorio = (int) (Math.random()*1999 + 1);
                numeros[i][j] = aleatorio;
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

            System.out.printf(" | fila %d = %d\n", i, sumaFila);
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