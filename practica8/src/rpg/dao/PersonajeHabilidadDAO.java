package rpg.dao;

import rpg.model.Clase;
import rpg.model.Habilidad;
import rpg.utils.Log;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonajeHabilidadDAO {


     // Registrar que un personaje ha aprendido una nueva habilidad.
    public void aprenderHabilidad(int idPersonaje, int idHabilidad) {
        String sql = "INSERT INTO Personajes_Habilidades (id_personaje, id_habilidad, equipada_combate) VALUES (?, ?, false)";

        try (Connection conn = ConnectionDB.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idPersonaje);
            pstmt.setInt(2, idHabilidad);

            pstmt.executeUpdate();
            Log.escribirLog("INFO", "Habilidad ID " + idHabilidad + " aprendida por el personaje " + idPersonaje);

        } catch (SQLException e) {
            Log.escribirLog("ERROR", "El personaje ya conoce esta habilidad o error en BD: " + e.getMessage());
        }
    }


     // Cambiar el estado de una habilidad a equipada (true) o desequipada (false).
    public void equiparHabilidad(int idPersonaje, int idHabilidad, boolean equipar) {
        String sql = "UPDATE Personajes_Habilidades SET equipada_combate = ? WHERE id_personaje = ? AND id_habilidad = ?";

        try (Connection conn = ConnectionDB.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setBoolean(1, equipar);
            pstmt.setInt(2, idPersonaje);
            pstmt.setInt(3, idHabilidad);

            pstmt.executeUpdate();
            Log.escribirLog("INFO", "Estado de habilidad " + idHabilidad + " actualizado para el personaje " + idPersonaje);

        } catch (SQLException e) {
            Log.escribirLog("ERROR", "Error al equipar habilidad: " + e.getMessage());
        }
    }


     // Obtener la lista de habilidades que tiene un personaje.
    public List<Habilidad> obtenerHabilidadesPersonaje(int idPersonaje) {
        List<Habilidad> lista = new ArrayList<>();
        String sql = "SELECT h.* FROM Habilidades h " +
                "JOIN Personajes_Habilidades ph ON h.id = ph.id_habilidad " +
                "WHERE ph.id_personaje = ?";

        try (Connection conn = ConnectionDB.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idPersonaje);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Clase claseAux = new Clase();
                claseAux.setId(rs.getInt("id_clase"));

                lista.add(new Habilidad(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("dano_base"),
                        rs.getInt("usos_maximos"),
                        claseAux
                ));
            }
            Log.escribirLog("INFO", "Cargadas " + lista.size() + " habilidades del personaje " + idPersonaje);

        } catch (SQLException e) {
            Log.escribirLog("ERROR", "Error al obtener habilidades: " + e.getMessage());
        }
        return lista;
    }



     // Contar cuántas habilidades tiene equipadas un personaje para el combate.
    public int contarHabilidadesEquipadas(int idPersonaje) {
        String sql = "SELECT COUNT(*) FROM Personajes_Habilidades WHERE id_personaje = ? AND esta_equipada = TRUE";
        int total = 0;

        try (Connection conn = ConnectionDB.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idPersonaje);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                total = rs.getInt(1);
            }

        } catch (SQLException e) {
            Log.escribirLog("ERROR", "Error al contar habilidades equipadas: " + e.getMessage());
        }
        return total;
    }
}