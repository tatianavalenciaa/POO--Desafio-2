package conexion;

import java.sql.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Conexion {
    
    private static final Logger logger = LogManager.getLogger(Conexion.class);    
    protected Connection conexion;
    protected PreparedStatement stmt;
    protected ResultSet rs;
    protected String sql;
    
    public void abrirConexion() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/mediateca";
        String username = "root";
        String password = "root"; 

        conexion = DriverManager.getConnection(url, username, password);
        logger.info("Conexion abierta");
    }
    
    public void cerrarConexion() throws SQLException {
        if(conexion != null ){
            conexion.close();
        }
        if(stmt != null ){
            stmt.close();
        }
        if(rs != null ){
            rs.close();
        }
        logger.info("Conexion cerrada");
    }
    
}
