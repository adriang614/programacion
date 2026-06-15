package es.securebank.core.util;

public class IbanValidator {

    public static boolean esValido(String iban) {

        if (iban == null) {
            return false;
        }

        return iban.matches("^ES\\d{22}$");
    }
}