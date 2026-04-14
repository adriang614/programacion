package rpg.dao;

import rpg.model.Ciudad;
import rpg.utils.Log;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CiudadDAO {

    public List<Ciudad> obtenerCiudades() {
        List<Ciudad> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, nivel_minimo_acceso FROM Ciudades";

        try (Connection conn = ConnectionDB.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Ciudad(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("nivel_minimo_acceso")
                ));
            }
            Log.escribirLog("INFO", "Cargadas " + lista.size() + " ciudades desde la BD.");

        } catch (SQLException e) {
            Log.escribirLog("ERROR", "Error al obtener ciudades: " + e.getMessage());
        }
        return lista;
    }
}