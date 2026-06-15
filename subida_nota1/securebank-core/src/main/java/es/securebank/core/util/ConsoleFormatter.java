package es.securebank.core.util;

public class ConsoleFormatter {

    public static void titulo(String texto) {
        System.out.println("\n==================================");
        System.out.println(texto.toUpperCase());
        System.out.println("==================================");
    }

    public static void exito(String mensaje) {
        System.out.println("[OK] " + mensaje);
    }

    public static void error(String mensaje) {
        System.out.println("[ERROR] " + mensaje);
    }

    public static void info(String mensaje) {
        System.out.println("[INFO] " + mensaje);
    }
}