package rpg.dao;

import rpg.model.Item;
import rpg.utils.Log;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventarioDAO {


     //Añadir un ítem al inventario de un personaje o aumentar su cantidad si ya existe.
    public void agregarItem(int idPersonaje, int idItem, int cantidad) {
        String sql = "INSERT INTO Inventarios (id_personaje, id_item, cantidad) " +
                "VALUES (?, ?, ?) ON CONFLICT (id_personaje, id_item) " +
                "DO UPDATE SET cantidad = Inventarios.cantidad + EXCLUDED.cantidad";

        try (Connection conn = ConnectionDB.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idPersonaje);
            pstmt.setInt(2, idItem);
            pstmt.setInt(3, cantidad);

            pstmt.executeUpdate();
            Log.escribirLog("INFO", "Item ID " + idItem + " añadido al inventario del personaje " + idPersonaje);

        } catch (SQLException e) {
            Log.escribirLog("ERROR", "Error al actualizar inventario: " + e.getMessage());
        }
    }


     // Obtener el inventario de un personaje.
    public Map<Item, Integer> obtenerInventario(int idPersonaje) {
        Map<Item, Integer> inventario = new HashMap<>();
        String sql = "SELECT i.*, inv.cantidad FROM Items i " +
                "JOIN Inventarios inv ON i.id = inv.id_item " +
                "WHERE inv.id_personaje = ?";

        try (Connection conn = ConnectionDB.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idPersonaje);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Item item = new Item(
                        rs.getInt("id"), rs.getString("nombre"), rs.getString("tipo"),
                        rs.getInt("precio_oro"), rs.getInt("bonificador_ataque"), rs.getInt("bonificador_defensa")
                );
                inventario.put(item, rs.getInt("cantidad"));
            }
            Log.escribirLog("INFO", "Inventario cargado para personaje " + idPersonaje + " (" + inventario.size() + " objetos)");

        } catch (SQLException e) {
            Log.escribirLog("ERROR", "Error al cargar inventario: " + e.getMessage());
        }
        return inventario;
    }



    // Obtener la lista simple de items que tiene un personaje.
    public List<Item> obtenerItemsPersonaje(int idPersonaje) {
        List<Item> lista = new ArrayList<>();
        String sql = "SELECT i.* FROM Items i JOIN Inventarios inv ON i.id = inv.id_item WHERE inv.id_personaje = ?";

        try (Connection conn = ConnectionDB.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idPersonaje);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                lista.add(new Item(
                        rs.getInt("id"), rs.getString("nombre"), rs.getString("tipo"),
                        rs.getInt("precio_oro"), rs.getInt("bonificador_ataque"), rs.getInt("bonificador_defensa")
                ));
            }
        } catch (SQLException e) {
            Log.escribirLog("ERROR", "Error al listar items para cálculo de stats: " + e.getMessage());
        }
        return lista;
    }
}