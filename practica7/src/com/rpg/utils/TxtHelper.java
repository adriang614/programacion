package com.rpg.utils;

import com.rpg.handler.FormatoInvalidoException;
import com.rpg.handler.RecursoNoEncontradoException;
import com.rpg.model.Ciudad;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TxtHelper {

    public List<Ciudad> leerCiudades(String ruta) throws RecursoNoEncontradoException {
        List<Ciudad> ciudades = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {

                // Saltar líneas vacías o comentarios que empiecen por #
                if (linea.trim().isEmpty() || linea.startsWith("#"))  {
                    continue;
                }

                try {
                    String[] partes = linea.split(";");

                    if (partes.length != 4) {
                        throw new FormatoInvalidoException("Número de campos incorrecto (" + partes.length + ")");
                    }

                    // .trim() para que los espacios en blanco no den problema
                    String nombre = partes[0].trim();
                    int poblacion = Integer.parseInt(partes[1].trim());
                    String clima = partes[2].trim();
                    int nivelRiesgo = Integer.parseInt(partes[3].trim());

                    ciudades.add(new Ciudad(nombre, poblacion, clima, nivelRiesgo));

                }

                catch (NumberFormatException e) {
                    LoggerCustom.escribirLog("DatoInvalidoException", "Error de formato numérico en línea: " + linea);
                }

                catch (FormatoInvalidoException e) {
                    LoggerCustom.escribirLog("FormatoInvalidoException", e.getMessage() + " en línea: " + linea);
                }
            }
        }
        catch (IOException e) {
            // Si el archivo no existe, esto sí es un error crítico
            LoggerCustom.escribirLog("RecursoNoEncontradoException", "No se pudo abrir: " + ruta);
            throw new RecursoNoEncontradoException("Fichero no encontrado: " + ruta);
        }

        return ciudades;
    }
}