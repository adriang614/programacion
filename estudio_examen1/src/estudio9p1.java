import java.util.Scanner;
public class estudio9p1{
    public static void main (String[] args){
        Scanner s = new Scanner(System.in);
        System.out.println ("¿Qué hora es?");
        int hora = s.nextInt();
        while (true){
            if ((hora >= 6) && (hora <= 12)){
                System.out.println ("Buenos días");
            } else if ((hora >= 13) && (hora <= 20)){
                System.out.println ("Buenas tarde");
            } else if (((hora >=21) && (hora <= 23) || (hora >= 0) && (hora <= 5))){
                System.out.println ("Buenas noches");
            } else {
                System.out.println ("Fuera de rango");
            }
            break;
        }
    }
}