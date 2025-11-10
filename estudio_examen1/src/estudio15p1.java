import java.util.Scanner;
public class estudio15p1{
    public static void main (String[] args){
        Scanner s = new Scanner(System.in);
        System.out.println ("Introduce un número");
        int x = s.nextInt();
        for (int i = 0; i <= 10; i++){
            System.out.println (x + " * " + i + " = " + x*i);
        }
    }
}