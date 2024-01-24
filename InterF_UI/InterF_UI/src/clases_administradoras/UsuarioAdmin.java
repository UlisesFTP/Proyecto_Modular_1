package clases_administradoras;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Usuario;
import conexion.Conexion;

public class UsuarioAdmin {

    private ArrayList<Usuario> usuarios;

    private final String LEER = "SELECT * FROM usuarios WHERE es_activo = true";
    private final String CREAR = "INSERT INTO usuarios (nombre, apellido, usuario, contrasenia, correo, es_activo) VALUES (?, ?, ?, ?, ?, ?)";
    private final String ACTUALIZAR = "UPDATE usuarios SET nombre = ?, apellido = ?, usuario = ?, contrasenia = ?, correo = ? WHERE id = ? ";
    private final String REMOVER_O_RESTABLECER = "UPDATE usuarios SET es_activo = ? WHERE id = ?";

    private final Conexion CONEXION;

    public UsuarioAdmin() throws SQLException, ClassNotFoundException {
        CONEXION = Conexion.getInstance();
    }

    public Usuario[] obtenerLista() throws SQLException {
        return (Usuario[]) usuarios.toArray(Usuario[]::new);
    }

    public Usuario[] leerUsuarios() throws SQLException {
        usuarios = new ArrayList<>();
        String sql = LEER;
        ResultSet rs = CONEXION.executeQuery(sql);
        try {
            while (rs.next()) {
                usuarios.add(new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"),
                        rs.getString("usuario"), rs.getString("contrasenia"), rs.getString("correo")));
            }

        } catch (SQLException e) {
            System.err.println("Error al leer datos: " + e.getMessage());
        }
        return usuarios.toArray(Usuario[]::new);
    }

    public boolean registrarUsuarios(String nombre, String apellido, String usuario, String contrasenia, String correo) throws SQLException {

        for (Usuario user : usuarios) {
            if (user.getUsuario().equals(usuario)) {
                JOptionPane.showMessageDialog(null, "Ya existe este usuario , elige otro nombre !", "Aviso", JOptionPane.ERROR_MESSAGE);
                return false;
            } else if (user.getCorreo().equals(correo)) {
                JOptionPane.showMessageDialog(null, "Ya hay alguien usando este correo !", "Aviso", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        try (PreparedStatement statement = CONEXION.obtenerSentenciaPreparada(CREAR)) {
            statement.setString(1, nombre);
            statement.setString(2, apellido);
            statement.setString(3, usuario);
            statement.setString(4, contrasenia);
            statement.setString(5, correo);
            statement.setBoolean(6, true);

            statement.execute();

        }
        return true;
    }

    public int obtenerID(String nombre_usuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsuario().equals(nombre_usuario)) {
                return usuario.getId();
            }
        }
        return -1;
    }

    public boolean editarUsuario(int id, String nombre, String apellido, String usuario, String contrasenia, String correo) throws SQLException {

        try (PreparedStatement statement = CONEXION.obtenerSentenciaPreparada(ACTUALIZAR)) {

            statement.setInt(6, id);

            statement.setString(1, nombre);
            statement.setString(2, apellido);
            statement.setString(3, usuario);
            statement.setString(4, contrasenia);
            statement.setString(5, correo);

            statement.executeUpdate();
            ;
            System.out.println("Actualizado");
        }

        return true;

    }

    public boolean cambiarEstado(int id, boolean estado) throws SQLException {

        try (PreparedStatement statement = CONEXION.obtenerSentenciaPreparada(REMOVER_O_RESTABLECER)) {

            statement.setBoolean(1, estado);
            statement.setInt(2, id);
            statement.execute();

            System.out.println("Removido");
        }

        return true;

    }

    public boolean validarLoginUsuario(String usuario, String contrasenia) throws SQLException {

        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND contrasenia = ? AND es_activo = true";

        PreparedStatement statement = CONEXION.obtenerSentenciaPreparada(sql);

        statement.setString(1, usuario);
        statement.setString(2, contrasenia);

        try (ResultSet rs = statement.executeQuery()) {
            return rs.next();

        }
    }

    public String obtenerContrasenia(String nombre_usuario) {

        for (Usuario usuario : usuarios) {
            if (usuario.getUsuario().equals(nombre_usuario)) {
                return usuario.getContrasenia();
            }
        }
        return null;

    }

    public Usuario[] buscarUsuarios(String textoBusqueda) {
        // Si la barra de búsqueda está vacía, retorna todos los usuarios
        if (textoBusqueda == null || textoBusqueda.isEmpty()) {
            return usuarios.toArray(new Usuario[0]);
        }

        ArrayList<Usuario> usuariosFiltrados = new ArrayList<>();

        // Búsqueda insensible a mayúsculas/minúsculas
        String textoBusquedaEnMinusculas = textoBusqueda.toLowerCase();

        for (Usuario usuario : usuarios) {
            // Verifica si el nombre del usuario comienza con el texto de búsqueda
            if (usuario.getNombre().toLowerCase().startsWith(textoBusquedaEnMinusculas)) {
                usuariosFiltrados.add(usuario);
            }
        }

        return usuariosFiltrados.toArray(new Usuario[0]);
    }

}
