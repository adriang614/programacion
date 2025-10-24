//En España cada persona está identificada con un Documento Nacional de Identidad (DNI)
// en el que figura un número y una letra, por ejemplo 56999545W.
// Realiza un programa donde le pidas al usuario SOLO el número del dni y el programa te devuelva la letra.
// Para calcular la letra solo tienes que dividir el número del DNI entre 23,
// el resto de esta división se corresponde con la posición de la letra en el abecedario.
// Utiliza un array para guardar CADA letra del abecedario.

import java.util.Scanner;

public class ejercicio12 {
    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);

        //Hacer un array para guardar cada letra que se usa en el DNI
        char[] letrasDNI = {
                'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X',
                'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'
        };

        //Guardar el número del DNI
        System.out.println("Introduce tu número del DNI");
        int numeroDNI = sc.nextInt();

        //Saber en que posición está la letra
        int resto = numeroDNI % 23;

        //Saber a que letra corresponde según la posición
        char letra = letrasDNI[resto];

        //Mostrar resultado final
        System.out.println("Tú número del DNI es: " + numeroDNI + letra);
    }
}