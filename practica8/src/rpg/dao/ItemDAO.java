package rpg.dao;

import rpg.model.Item;
import rpg.utils.Log;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {

    public List<Item> obtenerItems() {
        List<Item> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, tipo, precio_oro, bonificador_ataque, bonificador_defensa FROM Items";

        try (Connection conn = ConnectionDB.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Item(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("tipo"),
                        rs.getInt("precio_oro"),
                        rs.getInt("bonificador_ataque"),
                        rs.getInt("bonificador_defensa")
                ));
            }
            Log.escribirLog("INFO", "Inventario cargado: " + lista.size() + " items disponibles.");

        } catch (SQLException e) {
            Log.escribirLog("ERROR", "Error al listar items: " + e.getMessage());
        }
        return lista;
    }
}