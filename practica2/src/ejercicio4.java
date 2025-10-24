public class ejercicio4 {

    public static void main(String[] args) {
        // Crea un array de números de 100 posiciones, que contendrá los números del 1 al 100.
        // Obtén la suma de todos ellos y la media.
        int[] a = new int[100];

        for (int i = 0; i < 100; i++) {
            a[i] = i + 1;
        }

        int suma = 0;
        for (int i = 0; i < a.length; i++){
            suma = suma + a[i];
        }

        double media = (double) suma / (double) a.length;

        System.out.println("La suma de todos los números es: " + suma);
        System.out.println("La media de todos los números es: " + media);
    }
}