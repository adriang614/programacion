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

        int filas = 6;
        int columnas = 10;
        int[][] a = new int[filas][columnas];

        // Rellenar array con números que estén entre el 0 y el 1000
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                int aleatorio = (int) (Math.random()*1001);
                a[i][j] = aleatorio;
            }
        }
        int max = a[0][0];
        int min = a[0][0];
        int filaMax = 0;
        int colMax = 0;
        int filaMin = 0;
        int colMin = 0;

        // Calcular máximo, minimo y mostrar sus posciones
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

        // Calcular la suma de cada fila y la suma total de las filas
        System.out.println("Suma de cada fila:");
        int sumaTotFil = 0;
        for (int i = 0; i < filas; i++) {
            int sumaFila = 0;
            for (int j = 0; j < columnas; j++) {
                sumaFila += a[i][j];
            }
            sumaTotFil += sumaFila;
            System.out.println("Fila " + i + ": " + sumaFila);
        }
        System.out.println ("La suma total de las filas es: " + sumaTotFil);

        // Calcular la suma de cada columna y la suma total de las columnas
        System.out.println("Suma de cada columna: ");
        int sumaTotCol = 0;
        for (int j = 0; j < columnas; j++) {
            int sumaCol = 0;
            for (int i = 0; i < filas; i++) {
                sumaCol += a[i][j];
            }
            sumaTotCol += sumaCol;
            System.out.println("Columna " + j + ": " + sumaCol);
        }
        System.out.println ("La suma total de las columnas es: " + sumaTotFil);

        // Calcular la suma total de filas y columnas
        System.out.println ("La suma total de las filas y las columnas es: " + (sumaTotCol + sumaTotFil));
    }
}