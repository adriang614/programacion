package com.rpg.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerCustom {



    public static void log(String tipo, String mensaje) {
        try (FileWriter fw = new FileWriter("practica7/Ficheros/errores.log", true)) {

            fw.write("[" + LocalDateTime.now() + "] [" + tipo + "]" + mensaje );

        } catch (IOException e) {
            System.out.println("No se pudo escribir en errores.log");
        }
    }

    public static void escribirFichero {

    }
    }
