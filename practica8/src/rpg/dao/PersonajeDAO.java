package rpg.dao;

import rpg.model.*;
import rpg.utils.Log;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonajeDAO {

    // METODO PARA INSERTAR (CREATE)
    public void insertar(Personaje p) {
        String sqlPersonaje = "INSERT INTO personajes (nombre, id_clase, id_raza, id_ciudad_actual, oro, nivel, vida_actual) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlHabilidades = "INSERT INTO personaje_habilidades (id_personaje, id_habilidad, equipada) " +
                "SELECT ?, id, TRUE FROM habilidades WHERE id_clase = ? LIMIT 3";

        try (Connection conn = ConnectionDB.obtenerConexion()) {
            conn.setAutoCommit(false);

            try (PreparedStatement pstmtP = conn.prepareStatement(sqlPersonaje, Statement.RETURN_GENERATED_KEYS)) {
                pstmtP.setString(1, p.getNombre());
                pstmtP.setInt(2, p.getClase().getId());
                pstmtP.setInt(3, p.getRaza().getId());
                pstmtP.setInt(4, p.getCiudadActual().getId());
                pstmtP.setInt(5, p.getOro());
                pstmtP.setInt(6, p.getNivel());
                pstmtP.setInt(7, p.getVidaActual());
                pstmtP.executeUpdate();

                ResultSet rs = pstmtP.getGeneratedKeys();
                if (rs.next()) {
                    int idGenerado = rs.getInt(1);
                    p.setId(idGenerado);

                    try (PreparedStatement pstmtH = conn.prepareStatement(sqlHabilidades)) {
                        pstmtH.setInt(1, idGenerado);
                        pstmtH.setInt(2, p.getClase().getId());
                        pstmtH.executeUpdate();
                    }
                }
                conn.commit();
                Log.escribirLog("INFO", "Personaje e habilidades iniciales creados: " + p.getNombre());
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            Log.escribirLog("ERROR", "Error al insertar personaje: " + e.getMessage());
        }
    }

    // METODO PARA LISTAR (READ)
    public List<Personaje> obtenerPersonajes() {
        List<Personaje> lista = new ArrayList<>();
        String sql = "SELECT p.*, " +
                "c.nombre AS nombre_clase, " +
                "ciu.nombre AS nombre_ciudad, " +
                "r.nombre AS nombre_raza, r.bonificador_vida, r.bonificador_fuerza " +
                "FROM personajes p " +
                "LEFT JOIN clases_rpg c ON p.id_clase = c.id " +
                "LEFT JOIN ciudades ciu ON p.id_ciudad_actual = ciu.id " +
                "LEFT JOIN razas r ON p.id_raza = r.id";

        try (Connection conn = ConnectionDB.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Personaje p = new Personaje();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setOro(rs.getInt("oro"));
                p.setNivel(rs.getInt("nivel"));
                p.setVidaActual(rs.getInt("vida_actual"));

                Raza raza = new Raza(
                        rs.getInt("id_raza"),
                        rs.getString("nombre_raza"),
                        rs.getInt("bonificador_vida"),
                        rs.getInt("bonificador_fuerza")
                );
                p.setRaza(raza);

                Clase clase = new Clase(rs.getInt("id_clase"), rs.getString("nombre_clase"));
                p.setClase(clase);

                Ciudad ciudad = new Ciudad(rs.getInt("id_ciudad_actual"), rs.getString("nombre_ciudad"), 1);
                p.setCiudadActual(ciudad);

                lista.add(p);
            }

            Log.escribirLog("INFO", "Se han cargado " + lista.size() + " personajes.");

        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
            Log.escribirLog("ERROR", "Error al listar personajes: " + e.getMessage());
        }
        return lista;
    }

    // METODO PARA ACTUALIZAR (UPDATE)
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

    // METODO PARA ELIMINAR
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

    // METODO PARA ACTUALIZAR ORO
    public void actualizarOro(int idPersonaje, int nuevoOro) {
        String sql = "UPDATE Personajes SET oro = ? WHERE id = ?";

        try (Connection conn = ConnectionDB.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, nuevoOro);
            pstmt.setInt(2, idPersonaje);

            int filas = pstmt.executeUpdate();
            if (filas > 0) {
                Log.escribirLog("INFO", "Oro actualizado para ID " + idPersonaje + ": " + nuevoOro + " monedas.");
            }

        } catch (SQLException e) {
            Log.escribirLog("ERROR", "Error al actualizar oro: " + e.getMessage());
        }
    }


     // METODO PARA ACTUALIZAR CIUDAD ACTUAL.
    public void actualizarCiudad(int idPersonaje, int idCiudadNueva) {
        String sql = "UPDATE Personajes SET id_ciudad_actual = ? WHERE id = ?";

        try (Connection conn = ConnectionDB.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idCiudadNueva);
            pstmt.setInt(2, idPersonaje);

            pstmt.executeUpdate();
            // El log se suele poner en la lógica, pero aquí confirma que el SQL fue OK
        } catch (SQLException e) {
            Log.escribirLog("ERROR", "Error SQL al cambiar de ciudad: " + e.getMessage());
        }
    }


    // METODO PARA DESTERRAR
    public void desterrarPersonaje(int idPersonaje) {
        String sql = "UPDATE Personajes SET id_ciudad_actual = NULL, oro = 0 WHERE id = ?";

        try (Connection conn = ConnectionDB.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idPersonaje);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            Log.escribirLog("ERROR", "Error SQL al desterrar personaje: " + e.getMessage());
        }
    }

    // METODO PARA OBTENER POR ID
    public Personaje obtenerPorId(int id) {
        Personaje p = null;
        String sql = "SELECT p.*, c.nombre AS nombre_clase, ciu.nombre AS nombre_ciudad, " +
                "r.nombre AS nombre_raza, r.bonificador_vida, r.bonificador_fuerza " +
                "FROM personajes p " +
                "LEFT JOIN clases_rpg c ON p.id_clase = c.id " +
                "LEFT JOIN ciudades ciu ON p.id_ciudad_actual = ciu.id " +
                "LEFT JOIN razas r ON p.id_raza = r.id " +
                "WHERE p.id = ?";

        try (Connection conn = ConnectionDB.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                p = new Personaje();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setOro(rs.getInt("oro"));
                p.setNivel(rs.getInt("nivel"));
                p.setVidaActual(rs.getInt("vida_actual"));

                Clase clasePersonaje = new Clase(rs.getInt("id_clase"), rs.getString("nombre_clase"));
                p.setClase(clasePersonaje);
                p.setRaza(new Raza(rs.getInt("id_raza"), rs.getString("nombre_raza"), rs.getInt("bonificador_vida"), rs.getInt("bonificador_fuerza")));
                p.setCiudadActual(new Ciudad(rs.getInt("id_ciudad_actual"), rs.getString("nombre_ciudad"), 1));

                List<Habilidad> habilidades = new ArrayList<>();
                String sqlHab = "SELECT h.* FROM habilidades h " +
                        "JOIN personaje_habilidades ph ON h.id = ph.id_habilidad " +
                        "WHERE ph.id_personaje = ? AND ph.equipada = TRUE";

                try (PreparedStatement pstmtHab = conn.prepareStatement(sqlHab)) {
                    pstmtHab.setInt(1, p.getId());
                    ResultSet rsHab = pstmtHab.executeQuery();
                    while (rsHab.next()) {
                        Habilidad h = new Habilidad(
                                rsHab.getInt("id"),
                                rsHab.getString("nombre"),
                                rsHab.getInt("daño_base"),
                                rsHab.getInt("usos_maximos"),
                                clasePersonaje
                        );
                        habilidades.add(h);
                    }
                }
                p.setHabilidades(habilidades);
            }
        } catch (SQLException e) {
            Log.escribirLog("ERROR", "Error en obtenerPorId: " + e.getMessage());
        }
        return p;
    }
}