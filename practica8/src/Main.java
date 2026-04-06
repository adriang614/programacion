import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        // Datos del docker-compose.yml
        String url = "jdbc:postgresql://localhost:5432/XRPG";
        String user = "xrpg_user";
        String password = "xrpg_password";

        // Conexion y conusulta
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultset = statement.executeQuery("SELECT * FROM personajes")) {

            while (resultset.next()) {
                String name = resultset.getString("nombre");
                int nivel = resultset.getInt("nivel");

                System.out.println(name + "\t" + nivel);
            }

        } catch (SQLException e) {
            System.out.println("Error en la conexión de la base de datos");
            e.printStackTrace();
        }
    }
}