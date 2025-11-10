import java.util.Scanner;
public class estudio10p1{
    public static void main (String[] args){
        Scanner s = new Scanner(System.in);
        System.out.println ("Escribe el día de la semana:");
        String nombredia = s.nextLine();

        int numerodia;

        switch (nombredia){
            case "lunes" -> numerodia = 1;
            case "martes" -> numerodia = 2;
            case "miercoles" -> numerodia = 3;
            case "jueves" -> numerodia = 4;
            case "viernes" -> numerodia = 5;
            case "sabado" -> numerodia = 6;
            case "domingo" -> numerodia = 7;
            default -> numerodia = 0;
        }

        System.out.println(nombredia + " es el día número " + numerodia + " de la semana");
    }
}