package daos;

import conexion.Conexion;
import entidades.Libro;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LibroDao extends Conexion {
    
    private static final Logger logger = LogManager.getLogger(LibroDao.class);
    
    public List<Libro> obtenerLibros() throws SQLException {
        
        List<Libro> libros = new ArrayList<>();
        
        sql = 
            """
            select 
                *
            from 
                libro
            order by 
                codigo asc
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            rs = stmt.executeQuery(); 
            
            while (rs.next()) {
                Libro libro = new Libro();
                libro.setCodigo(rs.getString("codigo"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setNumPag(rs.getInt("num_pag"));
                libro.setEditorial(rs.getString("editorial"));
                libro.setIsbn(rs.getString("isbn"));
                libro.setAnioPub(rs.getInt("anio_pub"));
                libro.setUnidadesDisp(rs.getInt("unidades_disp"));
                libros.add(libro);
            }
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
       
        return libros;
        
    }
    
    public Libro obtenerLibro(String codigo) throws SQLException {
        
        Libro libro = null;
        
        sql = 
            """
            select 
                *
            from 
                libro
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
                libro = new Libro();
                libro.setCodigo(rs.getString("codigo"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setNumPag(rs.getInt("num_pag"));
                libro.setEditorial(rs.getString("editorial"));
                libro.setIsbn(rs.getString("isbn"));
                libro.setAnioPub(rs.getInt("anio_pub"));
                libro.setUnidadesDisp(rs.getInt("unidades_disp"));
            }
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
       
        return libro;
        
    }
    
    public void registrarLibro(Libro libro) throws SQLException {
        
        sql = 
            """
            insert into libro (
                codigo, titulo, autor, num_pag, editorial, isbn, anio_pub, unidades_disp
            )
            values (
                ?, ?, ?, ?, ?, ?, ?, ? 
            )
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            int i = 0;
            stmt.setString(++i, libro.getCodigo());
            stmt.setString(++i, libro.getTitulo());
            stmt.setString(++i, libro.getAutor());
            stmt.setInt(++i, libro.getNumPag());
            stmt.setString(++i, libro.getEditorial());
            stmt.setString(++i, libro.getIsbn());
            stmt.setInt(++i, libro.getAnioPub());
            stmt.setInt(++i, libro.getUnidadesDisp());
            
            stmt.executeUpdate(); 
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
        
    }
    
    public void modificarLibro(Libro libro) throws SQLException {
        
        sql = 
            """
            update 
                libro
            set
                titulo = ?, 
                autor = ?, 
                num_pag = ?, 
                editorial = ?, 
                isbn = ?, 
                anio_pub = ?, 
                unidades_disp = ?
            where 
                codigo = ?
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            int i = 0;
            stmt.setString(++i, libro.getTitulo());
            stmt.setString(++i, libro.getAutor());
            stmt.setInt(++i, libro.getNumPag());
            stmt.setString(++i, libro.getEditorial());
            stmt.setString(++i, libro.getIsbn());
            stmt.setInt(++i, libro.getAnioPub());
            stmt.setInt(++i, libro.getUnidadesDisp());
            stmt.setString(++i, libro.getCodigo());
            
            stmt.executeUpdate(); 
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
        
    }
    
    public void eliminarLibro(String codigo) throws SQLException {
        
        sql = 
            """
            delete from
                libro
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
