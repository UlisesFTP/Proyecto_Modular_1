package clases_administradoras;

import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Cita;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Servicio;

public class CitaAdmin {

    private ArrayList<Cita> citas;
    private ClienteAdmin clienteAdmin;
    private BarberoAdmin barberoAdmin;
    private ServicioAdmin servicioAdmin;

    private final String LEER = """
                                  SELECT
                                    c.id,
                                    c.fecha,
                                    c.hora,
                                    CONCAT(cl.nombre, ' ', cl.apellido) AS cliente,
                                    sr.nombre AS servicio,
                                    br.nombre AS barbero,
                                    c.cumplida,
                                    c.cancelada
                                FROM citas c
                                JOIN clientes cl ON c.cliente_id = cl.id
                                JOIN servicios sr ON c.servicio_id = sr.id
                                JOIN barberos br ON c.barbero_id = br.id
                                WHERE cl.es_activo = true AND sr.es_activo = true AND br.es_activo = true AND c.cancelada = false AND br.esta_disponible = true;
                                     """;

    private final String CREAR = "INSERT INTO citas (fecha, hora, cliente_id, servicio_id, barbero_id, cumplida, cancelada) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final String ACTUALIZAR = "UPDATE citas SET fecha = ?, hora = ?, cliente_id = ?, servicio_id = ?, barbero_id = ?, cumplida = ?, cancelada = ? WHERE id= ? ";
    private final String CUMPLIDA = "UPDATE citas SET cumplida = true WHERE id = ?";
    private final String CANCELAR = "UPDATE citas SET cancelada = true WHERE id =?";

    private final Conexion CONEXION;

    public CitaAdmin() throws ClassNotFoundException, SQLException {
        CONEXION = Conexion.getInstance();
        clienteAdmin = new ClienteAdmin();
        barberoAdmin = new BarberoAdmin();
        servicioAdmin = new ServicioAdmin();

    }

    public Cita[] obtenerLista() throws SQLException {
        return (Cita[]) citas.toArray(Cita[]::new);
    }

    public Cita[] leerCitas() throws SQLException {
        citas = new ArrayList<>();
        String sql = LEER;
        ResultSet rs = CONEXION.executeQuery(sql);
        try {
            while (rs.next()) {
                citas.add(new Cita(rs.getInt("id"), rs.getDate("fecha").toLocalDate(), rs.getTime("hora"), rs.getString("cliente"), rs.getString("servicio"),
                        rs.getString("barbero"), rs.getBoolean("cumplida"), rs.getBoolean("cancelada")));
            }

        } catch (SQLException e) {
            System.err.println("Error al leer datos: " + e.getMessage());
        }
        return citas.toArray(Cita[]::new);
    }

    public boolean registrarCita(LocalDate fecha, Time hora, int cliente_id, int servicio_id, int barbero_id) throws SQLException {
        int duracionServicio = 0;

        servicioAdmin.leerServicios();
        barberoAdmin.leerBarberos();

        for (Servicio servicio : servicioAdmin.obtenerLista()) {
            if (servicio.getId() == servicio_id) {
                duracionServicio = servicio.getDuracion();
                break;
            }
        }

        for (Cita cita : citas) {
            LocalTime horaInicioCitaExistente = cita.getHora().toLocalTime();
            LocalTime horaFinCitaExistente = horaInicioCitaExistente.plusMinutes(duracionServicio);

            LocalTime horaInicioNuevaCita = hora.toLocalTime();
            LocalTime horaFinNuevaCita = horaInicioNuevaCita.plusMinutes(duracionServicio);

            if (fecha.equals(cita.getFecha())
                    && !horaInicioNuevaCita.isBefore(horaInicioCitaExistente) // La nueva cita comienza después del inicio de la cita existente
                    && !horaInicioNuevaCita.isAfter(horaFinCitaExistente) // La nueva cita comienza antes de que termine la cita existente
                    && barbero_id == barberoAdmin.obtenerID(cita.getBarbero())) {
                JOptionPane.showMessageDialog(null, "Ya existe una cita agendada con este barbero en este horario !", "Aviso", JOptionPane.ERROR_MESSAGE);
                return false;
            }

        }

//                    if (fecha.equals(cita.getFecha())//Esta es la fecha
//                    && hora.toLocalTime().equals(cita.getHora().toLocalTime())//hora original
//                    && hora.toLocalTime().isAfter(cita.getHora().toLocalTime())//hora despues
//                    && hora.toLocalTime().isBefore(cita.getHora().toLocalTime().plusMinutes(duracionServicio)) // hora antes
//                    && barbero_id == barberoAdmin.obtenerID(cita.getBarbero())) {
//                JOptionPane.showMessageDialog(null, "Ya existe una cita para esta fecha y hora.", "Aviso", JOptionPane.ERROR_MESSAGE);
//                System.out.println("Hora inicio "+ cita.getHora().toLocalTime() + " " + "Hora final " + cita.getHora().toLocalTime().plusMinutes(duracionServicio));
//            if (fecha.equals(cita.getFecha())
//            && !horaInicioNuevaCita.isBefore(horaInicioCitaExistente) // La nueva cita comienza después del inicio de la cita existente
//            && !horaInicioNuevaCita.isAfter(horaFinCitaExistente) // La nueva cita comienza antes de que termine la cita existente
//            && barbero_id == barberoAdmin.obtenerID(cita.getBarbero())) {
//        JOptionPane.showMessageDialog(null, "Ya existe una cita para esta fecha y hora.", "Aviso", JOptionPane.ERROR_MESSAGE);
        if (fecha.isBefore(LocalDate.now())) {
            JOptionPane.showMessageDialog(null, "Ingresa una fecha valida, no se puede agendar en dias pasados", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (hora.toLocalTime().isBefore(LocalTime.now()) && fecha.equals(LocalDate.now())) {

            JOptionPane.showMessageDialog(null, "Ingresa una hora valida, no se puede agendar en horas pasadas", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try (PreparedStatement statement = CONEXION.obtenerSentenciaPreparada(CREAR)) {
            statement.setDate(1, java.sql.Date.valueOf(fecha));
            statement.setTime(2, hora);
            statement.setInt(3, cliente_id);
            statement.setInt(4, servicio_id);
            statement.setInt(5, barbero_id);
            statement.setBoolean(6, false);
            statement.setBoolean(7, false);

            statement.execute();

            leerCitas();
        }
        return true;

    }

    public int obtenerID(String nombre, LocalDate fecha, String servicio, String barbero) {

        int id = -1;

        for (Cita cita : citas) {
            if (cita.getCliente().equals(nombre) && cita.getFecha().equals(fecha) && cita.getServicio().equals(servicio) && cita.getBarbero().equals(barbero)) {
                id = cita.getId();
                System.out.println(id);
                return id;
            }
            
        }

        return id;

    }

    public boolean modificarCita(int id, LocalDate fecha, Time hora, int cliente_id, int servicio_id, int barbero_id) throws SQLException {

        try (PreparedStatement statement = CONEXION.obtenerSentenciaPreparada(ACTUALIZAR)) {
            statement.setInt(7, id);
            statement.setDate(1, java.sql.Date.valueOf(fecha));
            statement.setTime(3, hora);
            statement.setInt(4, cliente_id);
            statement.setInt(5, servicio_id);
            statement.setInt(6, barbero_id);

            statement.executeUpdate();
            statement.close();
        }
        return true;

    }

    public boolean cancelarCita(int id) {

        try (PreparedStatement statement = CONEXION.obtenerSentenciaPreparada(CANCELAR)) {
            statement.setInt(1, id);


            statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CitaAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;

    }

    public boolean citaCumplida(int id, boolean esCumplida) {

        try (PreparedStatement statement = CONEXION.obtenerSentenciaPreparada(CUMPLIDA)) {
            statement.setInt(2, id);
            statement.setBoolean(1, esCumplida);

            statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CitaAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;

    }

    public ArrayList<Cita> buscarCitasPorCliente(String textoBusqueda) throws SQLException {
        leerCitas(); // Asegúrate de tener la lista actualizada
        ArrayList<Cita> citasFiltradas = new ArrayList<>();

        if (textoBusqueda == null || textoBusqueda.isEmpty()) {
            return citas;
        }

        String textoBusquedaEnMinusculas = textoBusqueda.toLowerCase();

        for (Cita cita : citas) {
            if (cita.getCliente().toLowerCase().startsWith(textoBusquedaEnMinusculas)) {
                citasFiltradas.add(cita);
            }
        }
        return citasFiltradas;
    }

}
