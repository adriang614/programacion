package es.securebank.core.util;

public class DniValidator {

    private static final String LETRAS = "TRWAGMYFPDXBNJZSQVHLCKE";

    public static boolean esValido(String dni) {

        if (dni == null || !dni.matches("\\d{8}[A-Z]")) {
            return false;
        }

        int numero = Integer.parseInt(dni.substring(0, 8));

        char letraEsperada = LETRAS.charAt(numero % 23);

        return letraEsperada == dni.charAt(8);
    }
}