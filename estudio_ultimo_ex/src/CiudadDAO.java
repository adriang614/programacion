import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class CiudadDAO {

    private ConexionDB conexionDB = new ConexionDB();

    public List<Ciudad> obtenerCiudades () {
        List<Ciudad> ciudadList = new ArrayList<>();
        String sql = "SELECT * FROM Ciudades";
        Connection conn = conexionDB.getConn();

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                ciudadList.add(new Ciudad(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("nivel")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Ciudad c : ciudadList) {
            System.out.println(c);
        }
        return ciudadList;
    }

    public void insertarCiudad(String nombre, int nivel) {
        String sql = "INSERT INTO Ciudades (nombre, nivel) VALUES (?, ?)";
        Connection conn = conexionDB.getConn();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombre);
            pstmt.setInt(2, nivel);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al insertar la ciudad: " + e.getMessage());}
    }

    public void eliminarCiudadesDebiles(List<Ciudad> listaCiudades) {
        // 1. Obtenemos el iterador de la lista
        Iterator<Ciudad> it = listaCiudades.iterator();

        // 2. Mientras queden elementos...
        while (it.hasNext()) {
            Ciudad c = it.next(); // Obtenemos el objeto actual

            // 3. Condición de borrado
            if (c.getNivel() < 5) {
                // ¡IMPORTANTE!: Usamos el .remove() del iterador, NO de la lista
                it.remove();
            }
        }
    }
}