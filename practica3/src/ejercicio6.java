//Modifica el programa anterior de tal forma que no se repita ningún número en el array
//además de que tiene que estar comprendido en un rango entre 20-40.

public class ejercicio6 {
    public static void main(String[] args) {
        int filas = 6;
        int columnas = 10;
        int[][] a = new int[filas][columnas];

        // Creamos un array con los números del 20 al 40
        int[] numeros = new int[21];
        for (int i = 0; i < 21; i++) {
            numeros[i] = 20 + i;
        }

        // Mezclamos los números aleatoriamente
        for (int i = 0; i < numeros.length; i++) {
            int aleatorio = (int)(Math.random() * numeros.length);
            int aux = numeros[i];
            numeros[i] = numeros[aleatorio];
            numeros[aleatorio] = aux;
        }

        // Rellenar el array con los números sin repetir
        int indice = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (indice < numeros.length) {
                    a[i][j] = numeros[indice];
                    indice++;
                } else {
                    a[i][j] = 0; // valor vacío
                }
            }
        }

        int max = a[0][0];
        int min = a[0][0];
        int filaMax = 0;
        int colMax = 0;
        int filaMin = 0;
        int colMin = 0;

        // Buscar máximo y mínimo (ignorando los 0)
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (a[i][j] != 0) {
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
        }

        // Suma de filas
        int[] sumasFilas = new int[filas];
        int sumaTotFil = 0;
        for (int i = 0; i < filas; i++) {
            int suma = 0;
            for (int j = 0; j < columnas; j++) {
                suma += a[i][j];
            }
            sumasFilas[i] = suma;
            sumaTotFil += suma;
        }

        // Suma de columnas
        int[] sumasColumnas = new int[columnas];
        int sumaTotCol = 0;
        for (int j = 0; j < columnas; j++) {
            int suma = 0;
            for (int i = 0; i < filas; i++) {
                suma += a[i][j];
            }
            sumasColumnas[j] = suma;
            sumaTotCol += suma;
        }

        // Mostrar tabla
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
                if (a[i][j] == 0) {
                    System.out.print("     "); // celda vacía (espacio en blanco)
                } else {
                    System.out.printf("%4d ", a[i][j]);
                }
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

        System.out.println("\nNúmero máximo: " + max + " en posición [" + filaMax + "][" + colMax + "]");
        System.out.println("Número mínimo: " + min + " en posición [" + filaMin + "][" + colMin + "]");
    }
}