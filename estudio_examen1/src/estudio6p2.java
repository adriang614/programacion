import java.util.Scanner;

public class estudio6p2 {
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

        for (int i = 0; i < a.length; i++){
            if (a[i] > 0){
                pos++;
            } else if (a[i] < 0){
                neg++;
            } else
                cer++;
        }

        System.out.println("Hay " + pos + " número positivos");
        System.out.println("Hay " + neg + " números negativos");
        System.out.println("Hay " + cer + " ceros");
    }
}
