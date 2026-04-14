package rpg.dao;

import rpg.model.Raza;
import rpg.utils.Log;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RazaDAO {

    private List<Raza> razas = new ArrayList<>();

    public List<Raza> obtenerRazas() {
        String sql = "SELECT id, nombre, bonificador_vida, bonificador_fuerza FROM Razas";

        try (Connection conn = ConnectionDB.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // Crear el objeto Raza con los datos de la fila actual
                Raza raza = new Raza(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("bonificador_vida"),
                        rs.getInt("bonificador_fuerza")
                );
                razas.add(raza);
            }

            Log.escribirLog("INFO", "Cargadas " + razas.size() + " razas desde la BD.");

        } catch (SQLException e) {
            Log.escribirLog("ERROR", "Error al obtener razas: " + e.getMessage());
        }

        return razas;
    }
}