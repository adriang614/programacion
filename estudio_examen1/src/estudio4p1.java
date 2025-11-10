import java.util.Scanner;

public class estudio4p1 {
    public static void main (String[] args){
        System.out.println("Introduce 2 números");
        Scanner s = new Scanner(System.in);
        int x = s.nextInt();
        int y = s.nextInt();
        int multiplicacion = x * y;
        System.out.println("x * y = " + multiplicacion);
    }
}