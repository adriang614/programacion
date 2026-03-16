import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;


public class Bloque3 {

    public void exc () {

        try {
            int nuevoNivel = 140;

            if (nuevoNivel > 100) {
                throw new PruebaException("El nivel no puede superar 100");
            }
            else {
                System.out.println("Nivel actualizado");
            }
        } catch (PruebaException e) {
            System.out.println(e.getMessage());
        }

    }

    public List<Persona> leerFichero (String ruta) {
        List<Persona> personas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))){
            String linea;
            while ((linea = br.readLine()) != null) {

                if (linea.trim().isEmpty() || linea.startsWith("#")) continue;

                String [] partes = linea.split(",");

                if (partes.length < 3) {
                    throw new PruebaException("Numero de campos incorrectos");
                }

                String nombre = partes[0].trim();
                int edad = Integer.parseInt(partes[1].trim());

                personas.add(new Persona(nombre,edad));
                System.out.println(linea);
            }
        } catch (IOException e) {
            escribirLog("No es posible leer el fichero");
        } catch (PruebaException e) {
            escribirLog(e.getMessage());
        }
        return personas;
    }

    public static void escribirLog(String mensaje) {
        try (FileWriter fw = new FileWriter("estudio_examen_150326/files/registros.log", true)){
            String fecha = LocalDate.now().toString();
            fw.write(fecha + " " + mensaje + "\n");
        } catch (IOException e) {
            System.out.println("Error al escribir log");
        }
    }

private Gson gson = new Gson();

    public List<Persona> leerJson (String ruta) {
        try (FileReader fr = new FileReader(ruta)) {
            Type tipoLista = new TypeToken<ArrayList<Persona>>(){}.getType();

            List <Persona> lista = gson.fromJson(fr, tipoLista);

            if (lista != null) {
                return lista;
            } else {
                return new ArrayList<>(); // Devolvemos lista vacía si no hay nada
            }
        } catch (IOException e) {
            escribirLog("No se encontró el archivo JSON");
            return new ArrayList<>();
        }
    }

    public void guardarJson(String ruta, List<Persona> lista) {
        try (FileWriter writer = new FileWriter(ruta)) {
            // GSON convierte la lista a texto y la escribe en el fichero
            gson.toJson(lista, writer);
        } catch (IOException e) {
            escribirLog("No se pudo guardar el JSON");
        }
    }
}
