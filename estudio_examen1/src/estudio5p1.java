import java.util.Scanner;

public class estudio5p1 {
    public static void main (String[] args){
        Scanner s = new Scanner(System.in);
        System.out.println ("Introduzca la cantidad en pesetas:");
        int pesetas = s.nextInt();
        double euros = pesetas / 166.386;
        System.out.println (pesetas + " pesetas = " + euros + "€");
    }
}