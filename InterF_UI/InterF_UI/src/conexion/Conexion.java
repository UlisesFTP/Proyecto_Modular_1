package conexion;

import java.sql.*;

public class Conexion {

    private static final String MAQUINA = "barberia.c2gqijojdoml.us-east-2.rds.amazonaws.com";
    //  private static final String MAQUINA = "127.0.0.1";
    private static final int PUERTO = 5432;
    private static final String BD_NOMBRE = "barberia";
    private static final String USUARIO = "postgres";
    private static final String CONTRASENIA = "rcblBsJWPISCeAMMHskC";
    // private static final String CONTRASENIA = "XD@xd110"; 

    private static final String DRIVER = "org.postgresql.Driver";

    private final Connection CONEXION;
    private final Statement STATEMENT;
    private static Conexion instancia;

    public Conexion() throws ClassNotFoundException, SQLException {
        String cadenaConexion;
        Class.forName(DRIVER);
        cadenaConexion = "jdbc:postgresql://" + MAQUINA + ":" + PUERTO + "/" + BD_NOMBRE;
        CONEXION = DriverManager.getConnection(cadenaConexion, USUARIO, CONTRASENIA);
        STATEMENT = CONEXION.createStatement();

    }

    public static Conexion getInstance() throws ClassNotFoundException, SQLException {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public ResultSet executeQuery(String query) throws SQLException {

        return STATEMENT.executeQuery(query);
    }

    public boolean execute(String instruction) throws SQLException {
        return STATEMENT.execute(instruction);
    }

    public int executeUpdate(String instruction) throws SQLException {
        return STATEMENT.executeUpdate(instruction);
    }

    public PreparedStatement obtenerSentenciaPreparada(String instruccion) throws SQLException {
        return CONEXION.prepareStatement(instruccion);
    }

    public void cerrarConexion() {
        try {
            if (STATEMENT != null) {
                STATEMENT.close();
            }
            if (CONEXION != null && !CONEXION.isClosed()) {
                CONEXION.close();
                System.out.println("Conexión cerrada");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

}
