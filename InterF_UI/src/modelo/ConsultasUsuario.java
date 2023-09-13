

package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultasUsuario extends Conexion{
    
    
      public boolean buscar(Usuario usuario) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        
        String sql = "SELECT * FROM usuario WHERE usuario=? ";
        
     

          try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getUsuario());
            rs = ps.executeQuery();

            if (rs.next()) {
                usuario.setId(Integer.parseInt(rs.getString("id")));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setUsuario(rs.getString("Usuario"));
                usuario.setContrasenia(rs.getString("contraseña"));
                usuario.setCorreo(rs.getString("correo"));

                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        
        
    }
    
     public boolean registrar(Usuario usuario) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO usuario ( nombre, apellido , usuario, contrasenia, correo) VALUES(?,?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getUsuario());
            ps.setString(4, usuario.getContrasenia());
            ps.setString(5, usuario.getCorreo());

            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    
      public boolean modificar(Usuario usuario) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "UPDATE producto SET codigo=?, nombre=?, precio=?, cantidad=? WHERE id=? or usuario=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getUsuario());
            ps.setString(4, usuario.getContrasenia());
            ps.setString(5, usuario.getCorreo());

            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
