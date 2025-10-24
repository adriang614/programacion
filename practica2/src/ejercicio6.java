import java.util.Scanner;

public class ejercicio6 {

    public static void main(String[] args) {
        // Programa Java que guarda en un array 10 números enteros que se leen por teclado.
        // A continuación se recorre el array y calcula cuántos números son positivos, cuántos negativos y cuántos ceros.

        //Crear array con 10 posiciones
        Scanner sc = new Scanner(System.in);
        int[] a = new int[10];

        //Guardar los numeros del array leyendo por teclado
        for (int i = 0; i < a.length; i++) {
            System.out.println("Introduce el valor:");
            a[i] = sc.nextInt();
        }

        //Calcular cuantos numeros son positivos, cuantos negativos y cuantos ceros
        int positivos = 0;
        int negativos = 0;
        int ceros = 0;

        for (int i = 0; i < a.length; i++) {
            if (a[i]>0){
                positivos++;
            }
            else if (a[i]<0) {
                negativos++;
            }
            else {
                ceros++;
            }
        }
        System.out.println("Hay " + positivos + " número positivos");
        System.out.println("Hay " + negativos + " números negativos");
        System.out.println("Hay " + ceros + " ceros");
    }
}