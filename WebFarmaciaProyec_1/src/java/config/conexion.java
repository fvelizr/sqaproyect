
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jvict
 */
public class conexion {
  Connection conn = null;

    public Connection Conexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            String dato = "jdbc:mysql://45.55.47.25:3306/PROYECTO?allowPublicKeyRetrieval=true&useSSL=false";

            conn = DriverManager.getConnection(dato, "INGESOFT", "Secreto123*");

            return conn;

        } catch (ClassNotFoundException e) {
            return null;
        } catch (SQLException e) {
            return conn;
        }
    }

    public Connection getConnection() {
        return Conexion();
    }

    public void cerrar() throws SQLException {
        if (conn != null) {
            conn.close();
            conn = null;
        }

    }
    
    
}
