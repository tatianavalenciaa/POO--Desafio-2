package daos;

import conexion.Conexion;
import entidades.Cd;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CdDao extends Conexion {
    
    private static final Logger logger = LogManager.getLogger(CdDao.class);
    
    public List<Cd> obtenerCds() throws SQLException {
        
        List<Cd> cds = new ArrayList<>();
        
        sql =
            """
            select 
                *
            from 
                cd
            order by 
                codigo asc
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            rs = stmt.executeQuery(); 
            
            while (rs.next()) {
                Cd cd = new Cd();
                cd.setCodigo(rs.getString("codigo"));
                cd.setTitulo(rs.getString("titulo"));
                cd.setArtista(rs.getString("artista"));
                cd.setGenero(rs.getString("genero"));
                cd.setDuracion(rs.getString("duracion"));
                cd.setNumCanciones(rs.getInt("num_canciones"));
                cd.setUnidadesDisp(rs.getInt("unidades_disp"));
                cds.add(cd);
            }
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
       
        return cds;
        
    }
    
    public Cd obtenerCd(String codigo) throws SQLException {
        
        Cd cd = null;
        
        sql = 
            """
            select 
                *
            from 
                cd
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
                cd = new Cd();
                cd.setCodigo(rs.getString("codigo"));
                cd.setTitulo(rs.getString("titulo"));
                cd.setArtista(rs.getString("artista"));
                cd.setGenero(rs.getString("genero"));
                cd.setDuracion(rs.getString("duracion"));
                cd.setNumCanciones(rs.getInt("num_canciones"));
                cd.setUnidadesDisp(rs.getInt("unidades_disp"));
            }
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
       
        return cd;
        
    }
    
    public void registrarCd(Cd cd) throws SQLException {
        
        sql = 
            """
            insert into cd (
                codigo, titulo, artista, genero, duracion, num_canciones, unidades_disp
            )
            values (
                ?, ?, ?, ?, ?, ?, ?
            )
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            int i = 0;
            stmt.setString(++i, cd.getCodigo());
            stmt.setString(++i, cd.getTitulo());
            stmt.setString(++i, cd.getArtista());
            stmt.setString(++i, cd.getGenero());
            stmt.setString(++i, cd.getDuracion());
            stmt.setInt(++i, cd.getNumCanciones());
            stmt.setInt(++i, cd.getUnidadesDisp());
            
            stmt.executeUpdate(); 
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
        
    }
    
    public void modificarCd(Cd cd) throws SQLException {
        sql =
            """
            update 
                cd
            set
                titulo = ?, 
                artista = ?, 
                genero = ?, 
                duracion = ?, 
                num_canciones = ?,
                unidades_disp = ?
            where 
                codigo = ?
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            int i = 0;
            stmt.setString(++i, cd.getTitulo());
            stmt.setString(++i, cd.getArtista());
            stmt.setString(++i, cd.getGenero());
            stmt.setString(++i, cd.getDuracion());
            stmt.setInt(++i, cd.getNumCanciones());
            stmt.setInt(++i, cd.getUnidadesDisp());
            stmt.setString(++i, cd.getCodigo());
            
            stmt.executeUpdate(); 
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
    }
    
    public void eliminarCd(String codigo) throws SQLException {
        
        sql = 
            """
            delete from
                cd
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
