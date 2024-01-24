package clases_administradoras;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Cliente;
import conexion.Conexion;
import javax.swing.JOptionPane;

public class ClienteAdmin {

    private ArrayList<Cliente> clientes;

    private final String LEER = "SELECT * FROM clientes WHERE es_activo = true";
    private final String CREAR = "INSERT INTO clientes (nombre, apellido, telefono, correo, es_activo ) VALUES (?, ?, ?, ?, ?)";
    private final String ACTUALIZAR = "UPDATE clientes SET nombre = ?, apellido = ?, telefono = ?, correo = ? WHERE id = ? ";
    private final String REMOVER_O_RESTABLECER = "UPDATE clientes SET es_activo = ? WHERE id = ?";

    private final Conexion CONEXION;

    public ClienteAdmin() throws SQLException, ClassNotFoundException {
        CONEXION = Conexion.getInstance();
    }

    public Cliente[] obtenerLista() throws SQLException {
        return (Cliente[]) clientes.toArray(Cliente[]::new);
    }

    public Cliente[] leerClientes() throws SQLException {
        clientes = new ArrayList<>();
        String sql = LEER;
        ResultSet rs = CONEXION.executeQuery(sql);
        try {
            while (rs.next()) {
                clientes.add(new Cliente(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("telefono"),
                        rs.getString("correo")));
            }

        } catch (SQLException e) {
            System.err.println("Error al leer datos: " + e.getMessage());
        }
        return clientes.toArray(Cliente[]::new);
    }

    public boolean registrarClientes(String nombre, String apellido, String telefono, String correo) throws SQLException {

        for (Cliente cliente : clientes) {
            if (cliente.getNombre().equalsIgnoreCase(nombre) && cliente.getApellido().equalsIgnoreCase(apellido)
                    && cliente.getTelefono().equals(telefono)) {
                JOptionPane.showMessageDialog(null, "Ya esta registrado este cliente !", "Aviso", JOptionPane.ERROR_MESSAGE);
                return false;
            } else if (cliente.getCorreo().equals(correo)) {
                JOptionPane.showMessageDialog(null, "Ya hay alguien usando este correo !", "Aviso", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        try (PreparedStatement statement = CONEXION.obtenerSentenciaPreparada(CREAR)) {
            statement.setString(1, nombre);
            statement.setString(2, apellido);
            statement.setString(3, telefono);
            statement.setString(4, correo);
            statement.setBoolean(5, true);
            statement.execute();

            System.out.println("Valor insertado correctamente");
            leerClientes();
        }
        return true;
    }

    public int obtenerID(String correo) {
        for (Cliente cliente : clientes) {
            if (cliente.getCorreo().equals(correo)) {
                return cliente.getId();
            }
        }

        return -1;
    }

    public boolean editarCliente(int id, String nombre, String apellido, String telefono, String correo) throws SQLException {

        try (PreparedStatement statement = CONEXION.obtenerSentenciaPreparada(ACTUALIZAR)) {
            statement.setInt(5, id);
            statement.setString(1, nombre);
            statement.setString(2, apellido);
            statement.setString(3, telefono);
            statement.setString(4, correo);

            statement.executeUpdate();

            System.out.println("Actualizado");

        }

        return true;

    }

    public boolean cambiarEstado(int id, boolean estado) throws SQLException {

        try (PreparedStatement statement = CONEXION.obtenerSentenciaPreparada(REMOVER_O_RESTABLECER)) {

            statement.setBoolean(1, estado);
            statement.setInt(2, id);

            statement.execute();

        }

        return true;

    }

    public ArrayList<Cliente> buscarClientes(String textoBusqueda) throws SQLException {
        leerClientes(); // Asegurarse de tener la lista actualizada
        ArrayList<Cliente> clientesFiltrados = new ArrayList<>();

        if (textoBusqueda == null || textoBusqueda.isEmpty()) {
            return clientes;
        }

        String textoBusquedaEnMinusculas = textoBusqueda.toLowerCase();

        for (Cliente cliente : clientes) {
            if (cliente.getNombre().toLowerCase().startsWith(textoBusquedaEnMinusculas)
                    || cliente.getTelefono().toLowerCase().startsWith(textoBusquedaEnMinusculas)) {
                clientesFiltrados.add(cliente);
            }
        }
        return clientesFiltrados;
    }

}
