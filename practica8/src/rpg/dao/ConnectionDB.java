package rpg.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import rpg.utils.Log;

public class ConnectionDB {

    private static String URL = "jdbc:postgresql://localhost:5432/XRPG";
    private static String USER = "xrpg_user";
    private static String PASS = "xrpg_password";
    private static Connection conexion = null;

    public static Connection obtenerConexion() {
        try {
            if (conexion == null || conexion.isClosed()) {
                conexion = DriverManager.getConnection(URL, USER, PASS);
                Log.escribirLog("INFO", "Conexión establecida con la base de datos XRPG.");
            }
        } catch (SQLException e) {
            Log.escribirLog("ERROR", "No se pudo conectar a la base de datos: " + e.getMessage());
            System.out.println("Error crítico: No se pudo conectar a la base de datos.");
        }
        return conexion;
    }


    public static void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                Log.escribirLog("INFO", "Conexión a la base de datos cerrada correctamente.");
            } catch (SQLException e) {
                Log.escribirLog("ERROR", "Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}