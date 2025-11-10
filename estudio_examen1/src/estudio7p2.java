import java.util.Scanner;

public class estudio7p2 {
    public static void main (String[] args ){
        Scanner s = new Scanner(System.in);
        System.out.println("Introduce 10 números");
        int[] a = new int[10];
        for (int i = 0; i < a.length; i++){
            a[i] = s.nextInt();
        }

        int pos = 0;
        int neg = 0;
        int cer = 0;
        int sumapos = 0;
        int sumaneg = 0;

        for (int i = 0; i < a.length; i++){
            if (a[i] > 0){
                pos++;
                sumapos += a[i];
            } else if (a[i] < 0){
                neg++;
                sumaneg += a[i];
            } else
                cer++;
        }

        double mediapos = (double) sumapos / a.length;
        double medianeg = (double) sumaneg / a.length;

        System.out.println("Media positivos = " + mediapos);
        System.out.println("Media negativos = " + medianeg);
    }
}