import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private String url = "jdbc:postgresql://localhost:5432/XRPG";
    private String user = "xrpg_user";
    private String pass = "xrpg_password";
    private Connection conn = null;

    public Connection getConn () {
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(url,user,pass);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
}