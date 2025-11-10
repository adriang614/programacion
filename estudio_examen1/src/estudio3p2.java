public class estudio3p2 {
    public static void main (String[] args){
        int[] a = {5, 15, 2, 35, 43};

        int min = a[0];
        for (int i = 0; i < a.length; i++){
            if (a[i] < min){
                min = a[i];
            }
        }
        System.out.println ("El número más pequeño es: " + min);
    }
}