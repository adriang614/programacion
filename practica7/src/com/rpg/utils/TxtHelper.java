package com.rpg.utils;

import com.rpg.handler.DatoInvalidoException;
import com.rpg.handler.FormatoInvalidoException;
import com.rpg.handler.RecursoNoEncontradoException;
import com.rpg.model.Ciudad;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TxtHelper {
    private File file;
    private FileReader fr;
    private BufferedReader br;

    public TxtHelper() throws FileNotFoundException {
        this.file = new File("practica7/Ficheros/ciudades.txt");
        this.fr = new FileReader(file);
        this.br = new BufferedReader(fr);
    }

    /*public void leerLineaf() {
        try {
            var file = new BufferedReader(new FileReader("practica7/Ficheros/ciudades.txt"));

            String linea;
            while((linea = file.readLine()) != null) {
                System.out.println(linea);
                }
            file.close();
        } catch (IOException e) {
            System.out.println("No se ha podido abrir el fichero.");
        }
    }*/

    //-----------------------------------------------------------------------------------------------------------------------------------------------


    public List<Ciudad> leerLineas() throws RecursoNoEncontradoException {
        List<Ciudad> ciudades = new ArrayList<>();
        try (var file = new BufferedReader(new FileReader("practica7/Ficheros/ciudades.txt"))) {

            String linea;
            while ((linea = file.readLine()) != null) {
                try {
                    String [] partes = linea.split(";");

                    if (partes.length != 4) {
                        throw new FormatoInvalidoException("Linea equivocada: " + linea);
                    }

                    String nombre = partes [0];

                    try {
                        int poblacion = Integer.parseInt(partes[1]);
                        int nivelRiesgo = Integer.parseInt(partes[3]);
                    }

                    String clima = partes[2];

                    Ciudad c = new Ciudad(nombre, poblacion, clima, nivelRiesgo);
                    ciudades.add(c);
                }

                catch (NumberFormatException e) {
                    LoggerCustom.log("ERROR", "Número negativo en " + linea);
                }

                catch (FormatoInvalidoException e) {
                    LoggerCustom.log("ERROR", "Línea corrupta en " + linea);
                }
            }
            file.close();
        } catch (IOException e) {
            LoggerCustom.log("ERROR", "No se ha podido abrir el fichero");
            throw new RecursoNoEncontradoException("No se ha podido abrir el fichero");
        }

        return ciudades;
    }



    //-----------------------------------------------------------------------------------------------------------------------------------------------


    /*public void writeList(Ciudad c)  {
            try (var file = new BufferedWriter(new FileWriter("practica7/Ficheros/ciudades.txt", true))) {

                file.write(c.getNombre() + " ; " + c.getPoblacion() + " ; " + c.getClima() + " ; " + c.getNivelRiesgo());

                file.newLine();

                System.out.println("Escritura realizada.");
            } catch (RecursoNoEncontradoException e) {
                throw new RuntimeException(e);
            }
    }*/
}
