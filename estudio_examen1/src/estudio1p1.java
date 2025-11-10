//Escribe un programa en el que se declaren las variables enteras x e y. Asignarles
//los valores 144 y 999 respectivamente. A continuación, muestra por pantalla el valor
//de cada variable, la suma, la resta, la división y la multiplicación.

public class estudio1p1 {
    public static void main (String[] args){
        int x = 144;
        int y = 999;

        System.out.println ("El valor de la variable x es: " + x);
        System.out.println ("El valor de la variable x es: " + y);
        System.out.println ("x + y = " + (x + y));
        System.out.println ("x - y = " + (x - y));
        System.out.println ("x * y = " + (x * y));
        double div = (double) x / (double) y;
        System.out.println("x / y = " + div);
    }
}