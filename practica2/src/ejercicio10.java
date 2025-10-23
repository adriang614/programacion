//Crea un programa que muestre por pantalla la nota de un estudiante,
// de entre una lista de estudiantes con sus respectivas notas.
// El nombre del estudiante lo introduce el usuario por teclado (usa la clase Scanner).
// Utiliza un array para los nombres de los alumnos y otro para las notas.
// ¿Serías capaz de hacerlo con un array Bidimensional?

package src;

import java.util.Scanner;

public class ejercicio10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Array bidimensional: [nombre, nota]
        String[][] alumnos = {
                {"Ana", "8.5"},
                {"Luis", "6.0"},
                {"Carmen", "9.2"},
                {"Carlos", "7.3"},
                {"Pepi", "5.5"}
        };

        //Nombre del estudiante introducido por el usuario por teclado
        System.out.println("Introduce el nombre del estudiante: ");
        String nombre_buscado = sc.nextLine();

        //Mostrar por pantalla la nota de un estudiante
        for (int i = 0; i < alumnos.length; i++) {
            if (alumnos[i][0].equalsIgnoreCase(nombre_buscado)) {
                System.out.println("La nota de " + alumnos[i][0] + " es: " + alumnos[i][1]);
            }
        }
    }
}

