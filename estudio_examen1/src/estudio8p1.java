import java.util.Scanner;

public class estudio8p1{
    public static void main (String[] args){
        Scanner s = new Scanner(System.in);
        System.out.println ("Escribe un día de la semana");
        String dia = s.nextLine();
        if (dia .equals("lunes")){
            System.out.println ("Digitalización");
        } else if (dia .equals("martes")){
            System.out.println ("Programación");
        }
    }
}