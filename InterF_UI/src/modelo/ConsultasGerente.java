

package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConsultasGerente extends Conexion{
    
        public boolean buscar(Gerente gerente) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        
        String sql = "SELECT * FROM gerente WHERE usuario=? ";
        
     

          try {
            ps = con.prepareStatement(sql);
            ps.setString(1, gerente.getUsuario());
            rs = ps.executeQuery();

            if (rs.next()) {
                gerente.setId(Integer.parseInt(rs.getString("id")));
                gerente.setNombre(rs.getString("nombre"));
                gerente.setApellido(rs.getString("apellido"));
                gerente.setUsuario(rs.getString("Usuario"));
                gerente.setContrasenia(rs.getString("contraseña"));
                gerente.setCorreo(rs.getString("correo"));

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
    
     public boolean registrar(Gerente gerente) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO gerente ( nombre, apellido , usuario, contrasenia, correo) VALUES(?,?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, gerente.getNombre());
            ps.setString(2, gerente.getApellido());
            ps.setString(3, gerente.getUsuario());
            ps.setString(4, gerente.getContrasenia());
            ps.setString(5, gerente.getCorreo());

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
    
    
      public boolean modificar(Gerente gerente) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "UPDATE producto SET codigo=?, nombre=?, precio=?, cantidad=? WHERE id=? or usuario=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, gerente.getNombre());
            ps.setString(2, gerente.getApellido());
            ps.setString(3, gerente.getUsuario());
            ps.setString(4, gerente.getContrasenia());
            ps.setString(5, gerente.getCorreo());

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
