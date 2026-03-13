package com.rpg.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonHelper {

    private Gson gson;

    public JsonHelper() {
        // El PrettyPrinting es para que el .json no sea una sola línea
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    // <T> = genérico"
    public <T> List<T> readList(String ruta, Class<T> clase) {
        try (FileReader reader = new FileReader(ruta)) {

            // TypeToken crea una lista de el tipo que sea"
            Type tipoLista = TypeToken.getParameterized(List.class, clase).getType();

            // GSON lee el archivo y lo convierte en esa lista
            List<T> resultado = gson.fromJson(reader, tipoLista);

            // Si el archivo está vacío, GSON devuelve null.
            // Si es null, se devuelve una lista vacía para evitar errores
            if (resultado != null) {
                return resultado;
            }
            else {
                return new ArrayList<>();
            }

        } catch (IOException e) {
            LoggerCustom.escribirLog("RecursoNoEncontradoException", "No se pudo leer el JSON: " + ruta);
            return new ArrayList<>();
        }
    }

    public <T> void writeList(String path, List<T> lista) {
        try (FileWriter writer = new FileWriter(path)) {
            // Convierte la lista a texto JSON
            gson.toJson(lista, writer);
        } catch (IOException e) {
            LoggerCustom.escribirLog("RPGDataException", "No se pudo escribir en el JSON: " + path);
        }
    }
}
