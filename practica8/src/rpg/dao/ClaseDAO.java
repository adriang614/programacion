package rpg.dao;

import rpg.model.Clase;
import rpg.utils.Log;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClaseDAO {

    public List<Clase> obtenerClases() {
        List<Clase> lista = new ArrayList<>();
        String sql = "SELECT id, nombre FROM Clases_RPG";

        try (Connection conn = ConnectionDB.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Clase(
                        rs.getInt("id"),
                        rs.getString("nombre")
                ));
            }
            Log.escribirLog("INFO", "Cargadas " + lista.size() + " clases de la base de datos.");

        } catch (SQLException e) {
            Log.escribirLog("ERROR", "Error al cargar clases: " + e.getMessage());
        }
        return lista;
    }
}
