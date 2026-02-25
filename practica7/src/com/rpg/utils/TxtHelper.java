package com.rpg.utils;

import com.rpg.model.Ciudad;

import java.io.*;

public class TxtHelper {
    private File file;
    private FileReader fr;
    private BufferedReader br;

    public TxtHelper() throws FileNotFoundException {
        this.file = new File("practica7/Ficheros/ciudades.txt");
        this.fr = new FileReader(file);
        this.br = new BufferedReader(fr);
    }

    public void leerLinea() {
        try {
            var file = new BufferedReader(new FileReader("practica7/Ficheros/ciudades.txt"));

            String linea;
            while((linea = file.readLine()) != null)
                System.out.println(linea);

            file.close();
        } catch (IOException e) {
            System.out.println("No se ha podido abrir el fichero.");
        }
    }

    public void escribirFichero(Ciudad c) {
            try (var file = new BufferedWriter(new FileWriter("practica7/Ficheros/ciudades.txt", true))) {
                for (int i = 1; i <= 10; i++) {
                    file.write("BW. Línea nº " + i);
                    file.newLine();
                }
                System.out.println("Escritura realizada.");
            } catch (IOException e) {
                System.out.println("No se ha podido escribir en el fichero.");
            }
    }
}
