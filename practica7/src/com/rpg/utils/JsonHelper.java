package com.rpg.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class JsonHelper {

    private final Gson gson;

    public JsonHelper() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    // -----------------------------
    // LECTURA DE LISTAS
    // -----------------------------
    public <T> List<T> readList(String path, Class<T> clazz) {

        try (FileReader reader = new FileReader(path)) {

            Type tipoLista = TypeToken.getParameterized(List.class, clazz).getType();

            return gson.fromJson(reader, tipoLista);

        } catch (IOException e) {
            LoggerCustom.log("No se pudo leer el archivo JSON: " + path);
            return List.of(); // lista vacía para no romper el programa
        }
    }

    // -----------------------------
    // ESCRITURA DE LISTAS
    // -----------------------------
    public <T> void writeList(String path, List<T> lista) {

        try (FileWriter writer = new FileWriter(path)) {

            gson.toJson(lista, writer);

        } catch (IOException e) {
            LoggerCustom.log("No se pudo escribir en el archivo JSON: " + path);
        }
    }
}

