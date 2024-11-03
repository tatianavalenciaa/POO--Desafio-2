package daos;

import conexion.Conexion;
import entidades.Dvd;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DvdDao extends Conexion {
    
    private static final Logger logger = LogManager.getLogger(DvdDao.class);
    
    public List<Dvd> obtenerDvds() throws SQLException {
        
        List<Dvd> dvds = new ArrayList<>();
        
        sql =
            """
            select 
                *
            from 
                dvd
            order by 
                codigo asc
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            rs = stmt.executeQuery(); 
            
            while (rs.next()) {
                Dvd dvd = new Dvd();
                dvd.setCodigo(rs.getString("codigo"));
                dvd.setTitulo(rs.getString("titulo"));
                dvd.setDirector(rs.getString("director"));
                dvd.setDuracion(rs.getString("duracion"));
                dvd.setGenero(rs.getString("genero"));
                dvd.setUnidadesDisp(rs.getInt("unidades_disp"));
                dvds.add(dvd);
            }
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
       
        return dvds;
        
    }
    
    public Dvd obtenerDvd(String codigo) throws SQLException {
        
        Dvd dvd = null;
        
        sql = 
            """
            select 
                *
            from 
                dvd
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
                dvd = new Dvd();
                dvd.setCodigo(rs.getString("codigo"));
                dvd.setTitulo(rs.getString("titulo"));
                dvd.setDirector(rs.getString("director"));
                dvd.setDuracion(rs.getString("duracion"));
                dvd.setGenero(rs.getString("genero"));
                dvd.setUnidadesDisp(rs.getInt("unidades_disp"));
            }
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
       
        return dvd;
        
    }
    
    public void registrarDvd(Dvd dvd) throws SQLException {
        
        sql = 
            """
            insert into dvd (
                codigo, titulo, director, duracion, genero, unidades_disp
            )
            values (
                ?, ?, ?, ?, ?, ?
            )
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            int i = 0;
            stmt.setString(++i, dvd.getCodigo());
            stmt.setString(++i, dvd.getTitulo());
            stmt.setString(++i, dvd.getDirector());
            stmt.setString(++i, dvd.getDuracion());
            stmt.setString(++i, dvd.getGenero());
            stmt.setInt(++i, dvd.getUnidadesDisp());
            
            stmt.executeUpdate(); 
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
        
    }
    
    public void modificarDvd(Dvd dvd) throws SQLException {
        sql =
            """
            update 
                dvd
            set
                titulo = ?, 
                director = ?, 
                duracion = ?, 
                genero = ?, 
                unidades_disp = ?
            where 
                codigo = ?
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            int i = 0;
            stmt.setString(++i, dvd.getTitulo());
            stmt.setString(++i, dvd.getDirector());
            stmt.setString(++i, dvd.getDuracion());
            stmt.setString(++i, dvd.getGenero());
            stmt.setInt(++i, dvd.getUnidadesDisp());
            stmt.setString(++i, dvd.getCodigo());
            
            stmt.executeUpdate(); 
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
    }
    
    public void eliminarDvd(String codigo) throws SQLException {
        
        sql = 
            """
            delete from
                dvd
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
