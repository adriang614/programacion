//Define un array de números tipo double de 3 filas por 7 columnas con nombre doub y asigna los valores según
// la siguiente tabla.
// Muestra el contenido de todos los elementos del array dispuestos en forma de tabla como
// se muestra en la figura.
public class ejercicio1 {

    public static void main (String[] args){
        double[][] doub = {
                {0.0,30.0,2.0,0.0,0.0,5.0},
                {75.0,0.0,0.0,0.0,0.0,0.0},
                {0.0,0.0,-2.0,9.0,0.0,11.0},
        };

        System.out.printf("%-10s","Array num");

        for (int i = 0; i < 7; i++) {
            System.out.printf("%-10s", " Columna " + i);
        }

        System.out.println();

        //Mostrar el contenido en tabla
        for (int i = 0; i < doub.length; i++) {
            System.out.print("Fila " + i);
            for (int j = 0; j < doub[i].length; j++) {
                System.out.printf("%-10f", doub[i][j]);
            }
            System.out.println();
        }
    }
}
