package rpg.dao;

import rpg.model.Habilidad;
import rpg.utils.Log;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HabilidadDAO {

    public List<Habilidad> obtenerHabilidades() {
        List<Habilidad> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, dano_base, usos_maximos FROM Habilidades";

        try (Connection conn = ConnectionDB.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // El último parámetro es null porque la relación con ClaseRPG
                // se suele montar en la lógica de negocio para no complicar el DAO
                lista.add(new Habilidad(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("dano_base"),
                        rs.getInt("usos_maximos"),
                        null
                ));
            }
            Log.escribirLog("INFO", "Diccionario de habilidades cargado: " + lista.size() + " habilidades leídas.");

        } catch (SQLException e) {
            Log.escribirLog("ERROR", "Error en HabilidadDAO: " + e.getMessage());
        }
        return lista;
    }
}