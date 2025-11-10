import java.util.Scanner;
public class estudio5p2 {
    public static void main (String[] args){
        Scanner s = new Scanner(System.in);
        System.out.println ("¿De qué tamaño quieres que sea tu array?");
        int x = s.nextInt();
        int[] a = new int[x];
        int sum = 0;

        for(int i = 0; i < a.length; i++){
            a[i] = (int) (Math.random()*10);
            sum += a[i];
            System.out.println("Posición " + i + " = " + a[i]);
        }

        double media = ((double) sum / (double) x);

        System.out.println ("La suma de todos los valores es: " + sum);
        System.out.println ("La media de todos los valores es: " + media);
    }
}