//Define un array de números tipo double de 3 filas por 7 columnas con nombre doub y asigna los valores según
// la siguiente tabla.
// Muestra el contenido de todos los elementos del array dispuestos en forma de tabla como
// se muestra en la figura.
public class ejercicio1 {

    public static void main(String[] args) {
        double[][] doub = {
                {0.0, 30.0, 2.0, 0.0, 0.0, 5.0},
                {75.0, 0.0, 0.0, 0.0, 0.0, 0.0},
                {0.0, 0.0, -2.0, 9.0, 0.0, 11.0},
        };

        // Cabecera de columnas
        System.out.print("-----------------");
        for (int i = 0; i < 6; i++) {
            System.out.print("----------------");
        }
        System.out.println();

        System.out.printf("| %-13s |", "Array num");
        for (int i = 0; i < 6; i++) {
            System.out.printf(" %-13s |", "Columna " + i);
        }
        System.out.println();

        System.out.print("-----------------");
        for (int i = 0; i < 6; i++) {
            System.out.print("----------------");
        }
        System.out.println();

        // Contenido de filas
        for (int i = 0; i < doub.length; i++) {
            System.out.printf("| %-13s |", "Fila " + i);
            for (int j = 0; j < doub[i].length; j++) {
                if (doub[i][j] == 0.0 && !(i == 0 && j == 0) && !(i == 1 && j == 4)) {
                    System.out.printf(" %-13s |", "");
                } else {
                    System.out.printf(" %-13.0f |", doub[i][j]);
                }
            }
            System.out.println();

            // Línea separadora entre filas
            System.out.print("-----------------");
            for (int k = 0; k < 6; k++) {
                System.out.print("----------------");
            }
            System.out.println();
        }
    }
}
