package rpg.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {

    public static void escribirLog(String tipo, String mensaje) {

        // Formato de fecha: día/mes/año hora:minuto:segundo
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String fecha = LocalDateTime.now().format(formato);

        try (FileWriter fw = new FileWriter("practica8/files/info.log", true)) {

            fw.write("[" + fecha + "] [" + tipo + "] " + mensaje + "\n");

        }
        catch (IOException e) {
            System.out.println("No se pudo escribir en info.log");
        }
    }
}