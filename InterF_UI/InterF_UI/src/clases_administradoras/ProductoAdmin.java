package clases_administradoras;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import conexion.Conexion;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Producto;
import vista.FormularioEditarProductos;
import static vista.FormularioProductos.comboBoxMarcasRegistrar;
import static vista.FormularioProductos.comboBoxCategoriasRegistrar;

public class ProductoAdmin {

    private ArrayList<Producto> productos;

    private final String LEER = """
                                     SELECT
                                         p.id,
                                         p.nombre,
                                         p.precio,
                                         mp.nombre AS marca,
                                         cp.nombre AS categoria,
                                         p.unidades,
                                         p.es_activo
                                     FROM productos p
                                     JOIN marca_producto mp ON p.marca = mp.id
                                     JOIN categoria_producto cp ON p.categoria = cp.id   
                                     WHERE p.es_activo = true AND mp.es_activa = true AND cp.es_activa = true;
                                     """;
    private final String CREAR = "INSERT INTO productos (nombre, precio, marca, categoria, unidades, es_activo) VALUES (?, ?, ?, ?, ?, ?)";
    private final String ACTUALIZAR = "UPDATE productos SET nombre = ?, precio = ?, marca = ?, categoria = ?, unidades = ? WHERE id = ? ";
    private final String REMOVER_O_RESTABLECER = "UPDATE productos SET es_activo = ? WHERE id = ?";

    private final Conexion CONEXION;

    public ProductoAdmin() throws SQLException, ClassNotFoundException {
        CONEXION = Conexion.getInstance();
    }

    public int regresarIDMarca(String nombre) {

        int id = -1;

        try {

            String sql = "SELECT id FROM marca_producto WHERE nombre = ?";

            PreparedStatement statement = CONEXION.obtenerSentenciaPreparada(sql);

            statement.setString(1, nombre);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormularioEditarProductos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id;

    }

    public int regresarIDCaregoria(String nombre) {
        int id = -1;

        try {

            String sql = "SELECT id FROM categoria_producto WHERE nombre = ?";

            PreparedStatement statement = CONEXION.obtenerSentenciaPreparada(sql);

            statement.setString(1, nombre);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormularioEditarProductos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id;

    }

    public String regresarNombreCaregoria(int id) {
        String nombre = "";
        String sql = "SELECT nombre FROM categoria_producto WHERE id = ?";
        try {
            PreparedStatement statement = CONEXION.obtenerSentenciaPreparada(sql);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                nombre = resultSet.getString("nombre");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nombre;

    }

    public String regresarNombreMarca(int id) {
        String nombre = "";
        String sql = "SELECT nombre FROM marca_producto WHERE id = ?";
        try {
            PreparedStatement statement = CONEXION.obtenerSentenciaPreparada(sql);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                nombre = resultSet.getString("nombre");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nombre;

    }

    public Producto[] obtenerLista() throws SQLException {
        return (Producto[]) productos.toArray(Producto[]::new);
    }

    public Producto[] leerProductos() throws SQLException {
        productos = new ArrayList<>();
        String sql = LEER;
        ResultSet rs = CONEXION.executeQuery(sql);
        try {
            while (rs.next()) {
                productos.add(new Producto(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("precio"), rs.getString("marca"), rs.getString("categoria"),
                        rs.getInt("unidades")));
            }

        } catch (SQLException e) {
            System.err.println("Error al leer datos: " + e.getMessage());
        }
        return productos.toArray(Producto[]::new);
    }

    public boolean registrarProducto(String nombre, double precio, int marca, int categoria, int unidades) throws SQLException {

        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombre) && producto.getCategoria().equalsIgnoreCase(comboBoxCategoriasRegistrar.getSelectedItem().toString())
                    && producto.getMarca().equalsIgnoreCase(comboBoxMarcasRegistrar.getSelectedItem().toString())) {
                JOptionPane.showMessageDialog(null, "Ya existe este producto !", "Aviso", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        try (PreparedStatement statement = CONEXION.obtenerSentenciaPreparada(CREAR)) {
            statement.setString(1, nombre);
            statement.setDouble(2, precio);
            statement.setInt(3, marca);
            statement.setInt(4, categoria);
            statement.setInt(5, unidades);
            statement.setBoolean(6, true);

            statement.execute();
            System.out.println("Valor insertado correctamente");

            leerProductos();
        }
        return true;
    }

    public int obtenerID(String nombre, String marca, String categoria) {
        for (Producto producto : productos) {
            if (producto.getNombre().equals(nombre) && producto.getMarca().equals(marca) && producto.getCategoria().equals(categoria)) {
                return producto.getId();
            }
        }
        return -1;
    }

    public boolean editarProducto(int id, String nombre, double precio, int marca, int categoria, int unidades) throws SQLException {

        try (PreparedStatement statement = CONEXION.obtenerSentenciaPreparada(ACTUALIZAR)) {
            statement.setString(1, nombre);
            statement.setDouble(2, precio);
            statement.setInt(3, marca);
            statement.setInt(4, categoria);
            statement.setInt(5, unidades);

            statement.setInt(6, id);

            statement.executeUpdate();

        }
        return true;

    }

    public boolean venderProducto(int id, int cantidadVendida) throws SQLException {

        try (PreparedStatement statement = CONEXION.obtenerSentenciaPreparada("UPDATE productos SET unidades = unidades - ? WHERE id = ?")) {

            statement.setInt(1, cantidadVendida);

            statement.setInt(2, id);

            statement.executeUpdate();
            System.out.println("Se vendio");
        }
        return true;

    }

    public void editarMarca(int id, String nombreMarca) {

        try {
            System.out.println(id);
            PreparedStatement statement = CONEXION.obtenerSentenciaPreparada("UPDATE marca_producto SET nombre = ? WHERE id = ?");
            statement.setString(1, nombreMarca);
            statement.setInt(2, id);
            statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ProductoAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void editarCategoria(int id, String nombreCategoria) {

        try {
            System.out.println(id);
            PreparedStatement statement = CONEXION.obtenerSentenciaPreparada("UPDATE categoria_producto SET nombre = ? WHERE id = ?");
            statement.setString(1, nombreCategoria);
            statement.setInt(2, id);
            statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ProductoAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void removerMarca(int id) {

        try {
            System.out.println("Marca a remover: " + id);

            PreparedStatement statement = CONEXION.obtenerSentenciaPreparada("UPDATE marca_producto SET es_activa = false WHERE id = ?");

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ProductoAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void removerCategoria(int id) {
        try {
            PreparedStatement statement = CONEXION.obtenerSentenciaPreparada("UPDATE categoria_producto SET es_activa = false WHERE id = ?");

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ProductoAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean cambiarEstado(int id, boolean estado) throws SQLException {

        try (PreparedStatement statement = CONEXION.obtenerSentenciaPreparada(REMOVER_O_RESTABLECER)) {
            statement.setBoolean(1, estado);
            statement.setInt(2, id);
            statement.execute();

        }

        return true;

    }

    public ArrayList<Producto> buscarProductos(String textoBusqueda) throws SQLException {
        leerProductos(); // Asegúrate de tener la lista actualizada
        ArrayList<Producto> productosFiltrados = new ArrayList<>();

        if (textoBusqueda == null || textoBusqueda.isEmpty()) {
            return productos;
        }

        String textoBusquedaEnMinusculas = textoBusqueda.toLowerCase();

        for (Producto producto : productos) {
            if (producto.getNombre().toLowerCase().startsWith(textoBusquedaEnMinusculas)) {
                productosFiltrados.add(producto);
            }
        }
        return productosFiltrados;
    }

    public ArrayList<Producto> filtrarProductos(String marcaSeleccionada, String categoriaSeleccionada) throws SQLException {
        leerProductos(); // Asegúrate de tener la lista actualizada
        ArrayList<Producto> productosFiltrados = new ArrayList<>();

        for (Producto producto : productos) {
            boolean coincideMarca = "Todas".equals(marcaSeleccionada) || producto.getMarca().equalsIgnoreCase(marcaSeleccionada);
            boolean coincideCategoria = "Todas".equals(categoriaSeleccionada) || producto.getCategoria().equalsIgnoreCase(categoriaSeleccionada);

            if (coincideMarca && coincideCategoria) {
                productosFiltrados.add(producto);
            }
        }
        return productosFiltrados;
    }

}
