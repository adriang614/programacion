import java.util.Scanner;

public class prueba1 {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        int[] a = new int[10];

        for (int i = 0; i < a.length; i++) {
            System.out.println("Introduce el número " + i + " : ");
            a[i] = s.nextInt();
        }

        for (int i = 0; i < a.length; i++) {
            System.out.println("Valor " + a[i] + " en el índice " + i);
        }

    }
}
