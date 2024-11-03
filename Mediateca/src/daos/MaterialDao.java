package daos;

import conexion.Conexion;
import entidades.Material;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MaterialDao extends Conexion {
    
    private static final Logger logger = LogManager.getLogger(MaterialDao.class);
    
    public List<Material> obtenerMateriales() throws SQLException {
        
        List<Material> materials = new ArrayList<>();
        
        sql = 
            """
            select 
                *
            from 
                material
            order by 
                id_material asc
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            rs = stmt.executeQuery(); 
            
            while (rs.next()) {
                Material material = new Material();
                material.setIdMaterial(rs.getInt("id_material"));
                material.setCodigo(rs.getString("codigo"));
                material.setFechaCreacion(new Date(rs.getDate("fecha_creacion").getTime()));
                material.setIdUsuario(rs.getInt("id_usuario"));
                materials.add(material);
            }
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
       
        return materials;
        
    }
    
    public Material obtenerMaterial(String codigo) throws SQLException {
        
        Material material = null;
        
        sql = 
            """
            select 
                *
            from 
                material
            where
                codigo = ?
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            int i = 0;
            stmt.setString(++i, codigo);
            rs = stmt.executeQuery(); 
            
            while (rs.next()) {
                material = new Material();
                material.setIdMaterial(rs.getInt("id_material"));
                material.setCodigo(rs.getString("codigo"));
                material.setFechaCreacion(new Date(rs.getDate("fecha_creacion").getTime()));
                material.setIdUsuario(rs.getInt("id_usuario"));
            }
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
       
        return material;
        
    }
    
    public void registrarMaterial(Material material) throws SQLException {
        
        sql = 
            """
            insert into material (
                codigo, id_usuario
            )
            values (
                ?, ?
            )
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            int i = 0;
            stmt.setString(++i, material.getCodigo());
            stmt.setInt(++i, material.getIdUsuario());
            
            stmt.executeUpdate(); 
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
        
    }
    
    public void modificarMaterial(Material material) throws SQLException {
        
        sql = 
            """
            update 
                material
            set
                fecha_creacion = ?, 
                id_usuario = ?, 
            where 
                codigo = ?
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            int i = 0;
            stmt.setTimestamp(++i, new Timestamp(material.getFechaCreacion().getTime()));
            stmt.setInt(++i, material.getIdUsuario());
            stmt.setString(++i, material.getCodigo());
            
            stmt.executeUpdate(); 
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
        
    }
    
    public void eliminarMaterial(String codigo) throws SQLException {
        
        sql = 
            """
            delete from
                material
            where
                codigo = ?
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            int i = 0;
            stmt.setString(++i, codigo);
            
            stmt.executeUpdate(); 
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
        
    }
}
