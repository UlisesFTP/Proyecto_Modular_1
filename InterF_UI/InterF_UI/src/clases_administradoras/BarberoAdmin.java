package clases_administradoras;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Barbero;
import conexion.Conexion;

public class BarberoAdmin {

    private ArrayList<Barbero> barberos;

    private final String LEER = "SELECT * FROM barberos WHERE es_activo = true";
    private final String CREAR = "INSERT INTO barberos (nombre, correo, esta_disponible, es_activo) VALUES (?, ?, ?, ?)";
    private final String ACTUALIZAR = "UPDATE barberos SET nombre = ?, correo = ?, esta_disponible = ? WHERE id = ? ";
    private final String REMOVER_O_RESTABLECER = "UPDATE barberos SET es_activo = ? WHERE id = ?";

    private final Conexion CONEXION;

    public BarberoAdmin() throws SQLException, ClassNotFoundException {
        CONEXION = Conexion.getInstance();
    }

    public Barbero[] obtenerLista() throws SQLException {
        return (Barbero[]) barberos.toArray(Barbero[]::new);
    }

    public Barbero[] leerBarberos() throws SQLException {
        barberos = new ArrayList<>();
        String sql = LEER;
        ResultSet rs = CONEXION.executeQuery(sql);
        try {
            while (rs.next()) {
                barberos.add(new Barbero(rs.getInt("id"), rs.getString("nombre"), rs.getString("correo"),
                        rs.getBoolean("esta_disponible")));
            }

        } catch (SQLException e) {
            System.err.println("Error al leer datos: " + e.getMessage());
        }
        return barberos.toArray(Barbero[]::new);
    }

    public boolean registrarBarberos(String nombre, String correo, boolean disponibilidad) throws SQLException {

        for (Barbero barber : barberos) {
            if (barber.getNombre().equalsIgnoreCase(nombre)) {
                JOptionPane.showMessageDialog(null, "Ya existe este usuario , elige otro nombre !", "Aviso", JOptionPane.ERROR_MESSAGE);
                return false;
            } else if (barber.getCorreo().equals(correo)) {
                JOptionPane.showMessageDialog(null, "Ya hay alguien usando este correo !", "Aviso", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        try (PreparedStatement statement = CONEXION.obtenerSentenciaPreparada(CREAR)) {
            statement.setString(1, nombre);
            statement.setString(2, correo);
            statement.setBoolean(3, disponibilidad);
            statement.setBoolean(4, true);

            statement.execute();

            System.out.println("Valor insertado correctamente");
            leerBarberos();
        }
        return true;
    }

    public int obtenerID(String nombre) {
        for (Barbero barbero : barberos) {
            if (barbero.getNombre().equals(nombre)) {
                return barbero.getId();
            }
        }
        return -1;
    }

    public boolean editarBarbero(int id, String nombre, String correo, String estado) throws SQLException {

        boolean esta_disponible = false;
        try (PreparedStatement statement = CONEXION.obtenerSentenciaPreparada(ACTUALIZAR)) {

            statement.setInt(4, id);
            System.out.println(id + " " + " " + nombre + " " + correo + " " + estado);
            statement.setString(1, nombre);
            statement.setString(2, correo);

            if (estado.equalsIgnoreCase("Disponible")) {

                esta_disponible = true;
                System.out.println("true");

            } else if (estado.equalsIgnoreCase("No disponible")) {

                esta_disponible = false;
                System.out.println("false");

            }

            statement.setBoolean(3, esta_disponible);

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

            System.out.println("Removido");
        }

        return true;

    }

    public ArrayList<Barbero> filtrarBarberosPorDisponibilidad(boolean estaDisponible) throws SQLException {
        leerBarberos();
        ArrayList<Barbero> barberosFiltrados = new ArrayList<>();

        for (Barbero barbero : barberos) {
            if (barbero.isDisponible() == estaDisponible) {
                barberosFiltrados.add(barbero);
            }
        }
        return barberosFiltrados;
    }

}
