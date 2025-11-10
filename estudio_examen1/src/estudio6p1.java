import java.util.Scanner;

public class estudio6p1 {
    public static void main (String[] args){
        Scanner s = new Scanner(System.in);
        System.out.println ("Introduce 2 números");
        int x = s.nextInt();
        int y = s.nextInt();
        int sum = x + y;
        int res = x - y;
        int mul = x * y;
        double div = (double) x / (double) y;
        System.out.println ("Suma = " + sum);
        System.out.println ("Resta = " + res);
        System.out.println ("Multiplicación = " + mul);
        System.out.println ("División = " + div);
    }
}