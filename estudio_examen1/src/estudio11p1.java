import java.util.Scanner;
public class estudio11p1 {
    public static void main (String [] args){
        Scanner s = new Scanner(System.in);
        System.out.println ("Introduce 3 números:");
        int x = s.nextInt(); //1
        int y = s.nextInt(); //2
        int j = s.nextInt(); //3

        while ((x < y) || (y < j)){
            if (x < y){
                int tem = x;
                x = y;
                y = tem;
                //vuelta 1
                // x = 2
                // y = 1
                // j = 3

                //vuelta 2
                // x = 3
                // y = 2
                // j = 1
                // fin porque ya no se cumple ninguna condición del while porque están ordenados
            }
            if (y < j){
                int tem = y;
                y = j;
                j = tem;
                //vuelta 1
                // x = 2
                // y = 3
                // j = 1
            }
        }

        System.out.println("Números ordenados de mayor a menor: " + x + " " + y + " " + j);
    }
}