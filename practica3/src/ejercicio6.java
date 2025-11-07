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
                int aleatorio = (int) (Math.random()*21 + 20);
                a[i][j] = aleatorio;
            }
        }

        int max = a[0][0];
        int min = a[0][0];
        int filaMax = 0;
        int colMax = 0;
        int filaMin = 0;
        int colMin = 0;

        // Calcular máximo, minimo y sus posiciones
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

        // Calcular la suma de cada fila y la suma total de las filas
        int[] sumasFilas = new int[filas];
        int sumaTotFil = 0;
        for (int i = 0; i < filas; i++) {
            int sumaFila = 0;
            for (int j = 0; j < columnas; j++) {
                sumaFila += a[i][j];
            }
            sumasFilas[i] = sumaFila;
            sumaTotFil += sumaFila;
        }

        // Calcular la suma de cada columna y la suma total de las columnas
        int[] sumasColumnas = new int[columnas];
        int sumaTotCol = 0;
        for (int j = 0; j < columnas; j++) {
            int sumaCol = 0;
            for (int i = 0; i < filas; i++) {
                sumaCol += a[i][j];
            }
            sumasColumnas[j] = sumaCol;
            sumaTotCol += sumaCol;
        }

        // Mostrar tabla con sumas incluidas
        System.out.println("--------------------------------------------------------------------------------");
        System.out.print("       ");
        for (int j = 0; j < columnas; j++) {
            System.out.printf(" C%-3d", j);
        }
        System.out.println(" | Suma fila");
        System.out.println("--------------------------------------------------------------------------------");

        for (int i = 0; i < filas; i++) {
            System.out.printf("Fila %-2d|", i);
            for (int j = 0; j < columnas; j++) {
                System.out.printf("%4d ", a[i][j]);
            }
            System.out.printf("| %5d\n", sumasFilas[i]);
        }

        System.out.println("--------------------------------------------------------------------------------");
        System.out.print("Suma Col|");
        for (int j = 0; j < columnas; j++) {
            System.out.printf("%4d ", sumasColumnas[j]);
        }
        System.out.printf("| %5d (Total)\n", (sumaTotCol + sumaTotFil));
        System.out.println("--------------------------------------------------------------------------------");

        // Mostrar máximo y mínimo fuera de la tabla
        System.out.println("\nNúmero máximo: " + max + " en posición [" + filaMax + "][" + colMax + "]");
        System.out.println("Número mínimo: " + min + " en posición [" + filaMin + "][" + colMin + "]");
    }
}