package es.securebank.core.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

    private static final DateTimeFormatter FORMATO =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static String formatear(LocalDateTime fecha) {
        return fecha.format(FORMATO);
    }

    public static String ahora() {
        return LocalDateTime.now().format(FORMATO);
    }
}