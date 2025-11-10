public class estudio4p2 {
    public static void main (String[] args){
        int[] a = new int [100];
        int sum = 0;
        for(int i = 0; i < a.length; i++){
            a[i] = i + 1;
            sum += a[i];
        }
        System.out.println ("La suma de todos los números es: " + sum);

        double media = (double) sum / 100;
        System.out.println ("La media de todos los números es: " + media);
    }
}