package daos;

import conexion.Conexion;
import entidades.Prestamo;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PrestamoDao extends Conexion {
    
    private static final Logger logger = LogManager.getLogger(PrestamoDao.class);
    
    public List<Prestamo> obtenerPrestamosPorIdUsuario(Integer idUsuario) throws SQLException {
        
        List<Prestamo> prestamos = new ArrayList<>();
        
        sql =
            """
            select 
                pre.id_prestamo,
                pre.id_material,
                pre.id_usuario,
                mat.codigo,
                pre.fecha_prestamo,
                pre.fecha_devolucion
            from 
                prestamo pre
                join material mat on pre.id_material = mat.id_material
            where 
                pre.id_usuario = ?
            order by 
                pre.id_prestamo asc
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            int i = 0;
            stmt.setInt(++i, idUsuario);
            rs = stmt.executeQuery(); 
            
            while (rs.next()) {
                Prestamo prestamo = new Prestamo();
                prestamo.setIdPrestamo(rs.getInt("id_prestamo"));
                prestamo.setIdMaterial(rs.getInt("id_material"));
                prestamo.setIdUsuario(rs.getInt("id_usuario"));
                prestamo.setCodigo(rs.getString("codigo"));
                prestamo.setFechaPrestamo(new Date(rs.getDate("fecha_prestamo").getTime()));
                prestamo.setFechaDevolucion(new Date(rs.getDate("fecha_devolucion").getTime()));
                prestamos.add(prestamo);
            }
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
       
        return prestamos;
        
    }
    
    public Prestamo obtenerPrestamo(String codigo, Integer idUsuario) throws SQLException {
        
        Prestamo prestamo = null;
        
        sql = 
            """
            select 
                pre.*,
                mat.codigo
            from 
                prestamo pre
                join material mat on pre.id_material = mat.id_material
            where 
                mat.codigo = ?
                and pre.id_usuario = ?
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            int i = 0;
            stmt.setString(++i, codigo);
            stmt.setInt(++i, idUsuario);
            rs = stmt.executeQuery(); 
            
            while (rs.next()) {
                prestamo = new Prestamo();
                prestamo.setIdPrestamo(rs.getInt("id_prestamo"));
                prestamo.setIdMaterial(rs.getInt("id_material"));
                prestamo.setCodigo(rs.getString("codigo"));
                prestamo.setIdUsuario(rs.getInt("id_usuario"));
                prestamo.setFechaPrestamo(new Date(rs.getDate("fecha_prestamo").getTime()));
                prestamo.setFechaDevolucion(new Date(rs.getDate("fecha_devolucion").getTime()));
            }
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
       
        return prestamo;
        
    }
    
    public void registrarPrestamo(Prestamo prestamo) throws SQLException {
        
        sql = 
            """
            insert into prestamo (
                id_material, id_usuario, fecha_prestamo, fecha_devolucion
            )
            values (
                ?, ?, ?, ?
            )
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            int i = 0;
            stmt.setInt(++i, prestamo.getIdMaterial());
            stmt.setInt(++i, prestamo.getIdUsuario());
            stmt.setTimestamp(++i, new Timestamp(prestamo.getFechaPrestamo().getTime()));
            stmt.setTimestamp(++i, new Timestamp(prestamo.getFechaDevolucion().getTime()));
            
            stmt.executeUpdate(); 
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
        
    }
    
    public void modificarPrestamo(Prestamo prestamo) throws SQLException {
        sql =
            """
            update 
                prestamo
            set
                id_material = ?, 
                id_usuario= ?, 
                fecha_prestamo = ?, 
                fecha_devolucion = ? 
            where 
                id_prestamo = ?
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            int i = 0;
            stmt.setInt(++i, prestamo.getIdMaterial());
            stmt.setInt(++i, prestamo.getIdUsuario());
            stmt.setTimestamp(++i, new Timestamp(prestamo.getFechaPrestamo().getTime()));
            stmt.setTimestamp(++i, new Timestamp(prestamo.getFechaDevolucion().getTime()));
            stmt.setInt(++i, prestamo.getIdPrestamo());
            
            stmt.executeUpdate(); 
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
    }
    
    public void eliminarPrestamo(Prestamo prestamo) throws SQLException {
        
        sql = 
            """
            delete from
                prestamo
            where
                id_material = ?
                and id_usuario = ?
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            int i = 0;
            stmt.setInt(++i, prestamo.getIdMaterial());
            stmt.setInt(++i, prestamo.getIdUsuario());
            
            stmt.executeUpdate(); 
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
    }
}
