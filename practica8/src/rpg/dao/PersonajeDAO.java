package rpg.dao;

import rpg.model.*;
import rpg.utils.Log;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonajeDAO {

    // 1. METODO PARA INSERTAR (CREATE)
    public void insertar(Personaje p) {
        String sql = "INSERT INTO Personajes (nombre, nivel, oro, vida_actual, id_raza, id_clase, id_ciudad_actual) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionDB.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, p.getNombre());
            pstmt.setInt(2, p.getNivel());
            pstmt.setInt(3, p.getOro());
            pstmt.setInt(4, p.getVidaActual());
            pstmt.setInt(5, p.getRaza().getId());
            pstmt.setInt(6, p.getClase().getId());
            pstmt.setInt(7, p.getCiudadActual().getId());

            pstmt.executeUpdate();
            Log.escribirLog("INFO", "Personaje creado con éxito: " + p.getNombre());

        } catch (SQLException e) {
            Log.escribirLog("ERROR", "No se pudo insertar el personaje " + p.getNombre() + ": " + e.getMessage());
        }
    }

    // 2. METODO PARA LISTAR (READ)
    // Nota: Para un 10, aquí deberíamos hacer JOINs o usar los otros DAOs para rellenar los objetos internos
    public List<Personaje> obtenerTodos() {
        List<Personaje> lista = new ArrayList<>();
        String sql = "SELECT * FROM Personajes";

        try (Connection conn = ConnectionDB.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Personaje p = new Personaje();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setNivel(rs.getInt("nivel"));
                p.setOro(rs.getInt("oro"));
                p.setVidaActual(rs.getInt("vida_actual"));

                // Aquí simplificamos: en la lógica de control les asignaremos el objeto completo
                // basándonos en los IDs: rs.getInt("id_raza"), etc.

                lista.add(p);
            }
            Log.escribirLog("INFO", "Se han listado " + lista.size() + " personajes.");

        } catch (SQLException e) {
            Log.escribirLog("ERROR", "Error al obtener la lista de personajes: " + e.getMessage());
        }
        return lista;
    }

    // 3. METODO PARA ACTUALIZAR (UPDATE)
    public void actualizar(Personaje p) {
        String sql = "UPDATE Personajes SET nivel = ?, oro = ?, vida_actual = ?, id_ciudad_actual = ? WHERE id = ?";

        try (Connection conn = ConnectionDB.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, p.getNivel());
            pstmt.setInt(2, p.getOro());
            pstmt.setInt(3, p.getVidaActual());
            pstmt.setInt(4, p.getCiudadActual().getId());
            pstmt.setInt(5, p.getId());

            int filas = pstmt.executeUpdate();
            if (filas > 0) {
                Log.escribirLog("INFO", "Personaje actualizado: " + p.getNombre() + " (ID: " + p.getId() + ")");
            }

        } catch (SQLException e) {
            Log.escribirLog("ERROR", "Fallo al actualizar personaje " + p.getId() + ": " + e.getMessage());
        }
    }

    // 4. METODO PARA ELIMINAR (DELETE)
    public void eliminar(int id) {
        String sql = "DELETE FROM Personajes WHERE id = ?";

        try (Connection conn = ConnectionDB.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            Log.escribirLog("INFO", "Personaje con ID " + id + " eliminado permanentemente.");

        } catch (SQLException e) {
            Log.escribirLog("ERROR", "No se pudo eliminar el personaje con ID " + id + ": " + e.getMessage());
        }
    }
}