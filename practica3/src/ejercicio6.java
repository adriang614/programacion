//Modifica el programa anterior de tal forma que no se repita ningún número en el array
//además de que tiene que estar comprendido en un rango entre 20-40.


public class ejercicio6 {
    public static void main (String[] args){

        int filas = 6;
        int columnas = 10;
        int[][] a = new int[filas][columnas];

        // Rellenar array con números que estén entre el 0 y el 1000
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

        System.out.println ("La suma total de las filas y las columnas es: " + (sumaTotCol + sumaTotFil));
    }
}