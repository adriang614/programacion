package com.rpg.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerCustom {

    public static void log(String mensaje) {
        try (FileWriter fw = new FileWriter("practica7/Ficheros/errores.log", true)) {

            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            fw.write("[" + timestamp + "] ERROR: " + mensaje + "\n");

        } catch (IOException e) {
            System.out.println("No se pudo escribir en errores.log");
        }
    }
}
