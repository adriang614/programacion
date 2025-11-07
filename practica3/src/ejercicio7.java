//Modifica el programa del Ejercicio 6 para que:
//Los números NO se repitan (como en el ejercicio anterior).
//Los números estén comprendidos en un rango dinámico (el usuario introduce el valor mínimo y máximo).
//Al final, el programa muestre:
    //La media aritmética de todos los números del array.
    //La posición de todos los números primos que haya en el array.
    //Una representación gráfica en consola de cada fila, donde cada número se represente con un
    // número de * proporcional a su valor dentro del rango dado
    // (por ejemplo, si el rango es 10-20 y aparece el 15, se mostrarán 5 *).


import java.util.Scanner;

public class ejercicio7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int filas = 6;
        int columnas = 10;
        int[][] a = new int[filas][columnas];

        // Pedir rango al usuario
        System.out.print("Introduce el valor mínimo del rango: ");
        int minRango = sc.nextInt();
        System.out.print("Introduce el valor máximo del rango: ");
        int maxRango = sc.nextInt();

        // Crear array con los números del rango
        int cantidad = maxRango - minRango + 1;
        int[] numeros = new int[cantidad];
        for (int i = 0; i < cantidad; i++) {
            numeros[i] = minRango + i;
        }

        // Mezclar los números aleatoriamente
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
                    a[i][j] = 0;
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

        // Mostrar máximo y mínimo
        System.out.println("\nNúmero máximo: " + max + " en posición [" + filaMax + "][" + colMax + "]");
        System.out.println("Número mínimo: " + min + " en posición [" + filaMin + "][" + colMin + "]");

        // Media
        int suma = 0;
        int contador = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (a[i][j] != 0) {
                    suma += a[i][j];
                    contador++;
                }
            }
        }
        double media = (double)suma / contador;
        System.out.printf("\nMedia aritmética de los números: %.2f\n", media);

        // Encontrar numeros primos y sus posiciones
        System.out.println("\nPosiciones de los números primos:");
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                int n = a[i][j];
                if (n >= 2) {
                    boolean primo = true;
                    for (int k = 2; k < n; k++) {
                        if (n % k == 0) {
                            primo = false;
                            break;
                        }
                    }
                    if (primo && n != 0) {
                        System.out.println(n + " en posición [" + i + "][" + j + "]");
                    }
                }
            }
        }

        // Representacion con *
        System.out.println("\nRepresentación gráfica (asteriscos según valor):");
        for (int i = 0; i < filas; i++) {
            System.out.printf("Fila %d: ", i);
            for (int j = 0; j < columnas; j++) {
                if (a[i][j] != 0) {
                    int numAsteriscos = a[i][j] - minRango + 1;
                    for (int k = 0; k < numAsteriscos; k++) {
                        System.out.print("*");
                    }
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
