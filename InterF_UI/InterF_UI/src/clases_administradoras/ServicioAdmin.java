package clases_administradoras;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import conexion.Conexion;
import modelo.Servicio;

public class ServicioAdmin {

    private ArrayList<Servicio> servicios;

    private final String LEER = "SELECT * FROM servicios WHERE es_activo = true";
    private final String CREAR = "INSERT INTO servicios (nombre, precio, duracion, es_activo) VALUES (?, ?, ?, ?)";
    private final String ACTUALIZAR = "UPDATE servicios SET nombre = ?, precio = ?, duracion = ? WHERE id = ? ";
    private final String REMOVER_O_RESTABLECER = "UPDATE servicios SET es_activo = ? WHERE id = ?";

    private final Conexion CONEXION;

    public ServicioAdmin() throws SQLException, ClassNotFoundException {
        CONEXION = Conexion.getInstance();
    }

    public Servicio[] obtenerLista() throws SQLException {
        return (Servicio[]) servicios.toArray(Servicio[]::new);
    }

    public Servicio[] leerServicios() throws SQLException {
        servicios = new ArrayList<>();
        String sql = LEER;
        ResultSet rs = CONEXION.executeQuery(sql);
        try {
            while (rs.next()) {
                servicios.add(new Servicio(rs.getInt("Id"), rs.getString("nombre"), rs.getDouble("precio"), rs.getInt("duracion")));
            }

        } catch (SQLException e) {
            System.err.println("Error al leer datos: " + e.getMessage());
        }
        return servicios.toArray(Servicio[]::new);
    }

    public boolean registrarServicio(String nombre, double precio, int duracion) throws SQLException {

        for (Servicio service : servicios) {
            if (service.getNombre().equalsIgnoreCase(nombre)) {
                JOptionPane.showMessageDialog(null, "Ya existe este servicio !", "Aviso", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        try (PreparedStatement statement = CONEXION.obtenerSentenciaPreparada(CREAR)) {
            statement.setString(1, nombre);
            statement.setDouble(2, precio);
            statement.setInt(3, duracion);
            statement.setBoolean(4, true);

            statement.execute();

            System.out.println("Valor insertado correctamente");
            leerServicios();
        }
        return true;
    }

    public int obtenerID(String nombre) {
        for (Servicio servicio : servicios) {
            if (servicio.getNombre().equals(nombre)) {
                return servicio.getId();
            }
        }
        return -1;
    }

    public boolean editarServicio(int id, String nombre, double precio, int duracion) throws SQLException {

        try (PreparedStatement statement = CONEXION.obtenerSentenciaPreparada(ACTUALIZAR)) {
            statement.setString(1, nombre);
            statement.setDouble(2, precio);
            statement.setInt(3, duracion);

            statement.setInt(4, id);

            statement.executeUpdate();

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

    public ArrayList<Servicio> buscarServiciosPorNombre(String textoBusqueda) throws SQLException {
        leerServicios(); // Asegúrate de tener la lista actualizada
        ArrayList<Servicio> serviciosFiltrados = new ArrayList<>();

        if (textoBusqueda == null || textoBusqueda.isEmpty()) {
            return servicios;
        }

        String textoBusquedaEnMinusculas = textoBusqueda.toLowerCase();

        for (Servicio servicio : servicios) {
            if (servicio.getNombre().toLowerCase().startsWith(textoBusquedaEnMinusculas)) {
                serviciosFiltrados.add(servicio);
            }
        }
        return serviciosFiltrados;
    }

}
