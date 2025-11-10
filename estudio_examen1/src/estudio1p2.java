import java.util.Scanner;
public class estudio1p2 {
    public static void main (String[] args){
        Scanner s = new Scanner(System.in);
        int[] a = new int[10];
        System.out.println ("Introduce 10 números");
        for (int i = 0; i < a.length; i++){
            a[i] = s.nextInt();
        }
        for (int i = 0; i < a.length; i++){
            System.out.println("Índice " + i + " = valor " + a[i]);
        }
    }
}