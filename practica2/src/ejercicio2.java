import java.util.Scanner;

public class ejercicio2 {

    public static void main (String[] args){
        //Muestra por pantalla todos los elementos de un array de números enteros separados por un espacio.
        Scanner sc = new Scanner(System.in);
        int[] arrayInt = new int[10];

        for (int i=0 ; i<10; i++){
            System.out.println("Introduce el valor");
            arrayInt[i] = sc.nextInt();
        }

        for (int i = 0; i < arrayInt.length; i++) {
            System.out.print(arrayInt[i] + " ");
        }
    }
}
