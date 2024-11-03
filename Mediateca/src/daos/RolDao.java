package daos;

import conexion.Conexion;
import entidades.Rol;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RolDao extends Conexion {
    
    private static final Logger logger = LogManager.getLogger(RolDao.class);
    
    public List<Rol> obtenerRoles() throws SQLException {
        
        List<Rol> rols = new ArrayList<>();
        
        sql = 
            """
            select 
                *
            from 
                rol
            order by 
                id_rol asc
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            rs = stmt.executeQuery(); 
            
            while (rs.next()) {
                Rol rol = new Rol();
                rol.setIdRol(rs.getInt("id_rol"));
                rol.setNombre(rs.getString("nombre"));
                rols.add(rol);
            }
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
       
        return rols;
        
    }
    
    public Rol obtenerRol(Integer id) throws SQLException {
        
        Rol rol = null;
        
        sql = 
            """
            select 
                *
            from 
                rol
            where
                id_rol = ?
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            int i = 0;
            stmt.setInt(++i, id);
            rs = stmt.executeQuery(); 
            
            while (rs.next()) {
                rol = new Rol();
                rol.setIdRol(rs.getInt("id_rol"));
                rol.setNombre(rs.getString("nombre"));
            }
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
       
        return rol;
        
    }
    
    public void registrarRol(Rol rol) throws SQLException {
        
        sql = 
            """
            insert into rol (
                nombre
            )
            values (
                ?
            )
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            int i = 0;
            stmt.setString(++i, rol.getNombre());
            
            stmt.executeUpdate(); 
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
        
    }
    
    public void modificarRol(Rol rol) throws SQLException {
        
        sql = 
            """
            update 
                rol
            set
                nombre = ? 
            where 
                id_rol = ?
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            int i = 0;
            stmt.setString(++i, rol.getNombre());
            stmt.setInt(++i, rol.getIdRol());
            
            stmt.executeUpdate(); 
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
        
    }
    
    public void eliminarRol(Integer id) throws SQLException {
        
        sql = 
            """
            delete from
                rol
            where
                id_rol = ?
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            int i = 0;
            stmt.setInt(++i, id);
            
            stmt.executeUpdate(); 
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
        
    }
}
