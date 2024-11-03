package daos;

import conexion.Conexion;
import entidades.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UsuarioDao extends Conexion {
    
    private static final Logger logger = LogManager.getLogger(UsuarioDao.class);
    
    public List<Usuario> obtenerUsuarios() throws SQLException {
        
        List<Usuario> usuarios = new ArrayList<>();
        
        sql = 
            """
            select 
                u.id_usuario,
                u.usuario,
                u.password,
                u.id_rol,
                r.nombre
            from 
                usuario u
                join rol r on u.id_rol = r.id_rol
            order by 
                id_usuario asc
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            rs = stmt.executeQuery(); 
            
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setPassword(rs.getString("password"));
                usuario.setIdRol(rs.getInt("id_rol"));
                usuario.setRol(rs.getString("nombre"));
                usuarios.add(usuario);
            }
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
       
        return usuarios;
        
    }
    
    public Usuario obtenerUsuario(Integer id) throws SQLException {
        
        Usuario usuario = null;
        
        sql = 
            """
            select 
                *
            from 
                usuario
            where
                id_usuario = ?
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            int i = 0;
            stmt.setInt(++i, id);
            rs = stmt.executeQuery(); 
            
            while (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setPassword(rs.getString("password"));
                usuario.setIdRol(rs.getInt("id_rol"));
            }
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
       
        return usuario;
        
    }
    
    public void registrarUsuario(Usuario usuario) throws SQLException {
        
        sql = 
            """
            insert into usuario (
                usuario, password, id_rol
            )
            values (
                ?, ?, ?
            )
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            int i = 0;
            stmt.setString(++i, usuario.getUsuario());
            stmt.setString(++i, usuario.getPassword());
            stmt.setInt(++i, usuario.getIdRol());
            
            stmt.executeUpdate(); 
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
        
    }
    
    public void modificarUsuario(Usuario usuario) throws SQLException {
        
        sql = 
            """
            update 
                usuario
            set
                usuario = ?, 
                password = ?, 
                id_rol = ?
            where 
                id_usuario = ?
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            int i = 0;
            stmt.setString(++i, usuario.getUsuario());
            stmt.setString(++i, usuario.getPassword());
            stmt.setInt(++i, usuario.getIdRol());
            stmt.setInt(++i, usuario.getIdUsuario());
            
            stmt.executeUpdate(); 
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
        
    }
    
    public void eliminarUsuario(Integer id) throws SQLException {
        
        sql = 
            """
            delete from
                usuario
            where
                id_usuario = ?
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
    
    public Usuario obtenerUsuarioXUsuarioYPassword(String user, String pass) throws SQLException {
        
        Usuario usuario = null;
        
        sql = 
            """
            select 
                *
            from 
                usuario
            where
                usuario = ?
                and password = ?
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            int i = 0;
            stmt.setString(++i, user);
            stmt.setString(++i, pass);
            rs = stmt.executeQuery(); 
            
            while (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setPassword(rs.getString("password"));
                usuario.setIdRol(rs.getInt("id_rol"));
            }
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
       
        return usuario;
        
    }
}
