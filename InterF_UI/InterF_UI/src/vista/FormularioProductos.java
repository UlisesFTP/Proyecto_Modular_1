package vista;

import conexion.Conexion;
import java.awt.Color;
import javax.swing.JOptionPane;
import static inicio.Main.inicializarFlatLaf;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import clases_administradoras.ProductoAdmin;
import java.awt.HeadlessException;
import modelo.Producto;
import static vista.FormularioEditarProductos.comboBoxCategoriasModificar;
import static vista.FormularioEditarProductos.comboBoxMarcasModificar;
import static vista.InicioAdministrador.tablaProductos;
import static vista.InicioUsuario.comboboxCategoriaVentas;
import static vista.InicioUsuario.comboboxMarcaVentas;
import static vista.InicioUsuario.tablaVentaProductos;
import static vista.InicioAdministrador.comboboxMarcaVistaProductos;
import static vista.InicioAdministrador.comboboxCategoriaVistaProductos;

/**
 *
 * @author joel_
 */
public class FormularioProductos extends javax.swing.JFrame {

    private String marca;
    private String categoria;
    private Conexion CONEXION;
    private ProductoAdmin productoAdmin;

    public static ArrayList<String> marcas;
    public static ArrayList<String> categorias;

    public FormularioProductos() {

        inicializarFlatLaf();
        initComponents();
        try {
            CONEXION = Conexion.getInstance();
            marcas = new ArrayList<>();
            categorias = new ArrayList<>();
            productoAdmin = new ProductoAdmin();
            productoAdmin.leerProductos();
            leerMarcas();
            leerCategoria();
            llenarComboBox();

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FormularioProductos.class.getName()).log(Level.SEVERE, null, ex);
        }

        setBackground(new Color(0, 0, 0, 0));

        TextPrompt placeholderBarraNombre = new TextPrompt("Nombre", textNombre);
        TextPrompt placeholderBarraPrecio = new TextPrompt("Precio", textPrecio);

        placeholderBarraNombre.changeAlpha(0.75f);
        placeholderBarraPrecio.changeAlpha(0.75f);

    }

    private void llenarComboBox() {

        for (String marca : marcas) {
            comboBoxMarcasRegistrar.addItem(marca);

        }

        for (String categoria : categorias) {
            comboBoxCategoriasRegistrar.addItem(categoria);

        }

    }

    private void leerMarcas() throws SQLException {

        String sql = "SELECT * FROM marca_producto WHERE es_activa = true";
        ResultSet rs = CONEXION.executeQuery(sql);

        try {
            while (rs.next()) {
                marcas.add(rs.getString("nombre"));
            }

            rs.close();

        } catch (SQLException e) {
            System.err.println("Error al leer datos: " + e.getMessage());
        }

    }

    private void leerCategoria() throws SQLException {

        String sql = "SELECT * FROM categoria_producto WHERE es_activa = true";
        ResultSet rs = CONEXION.executeQuery(sql);

        try {
            while (rs.next()) {
                categorias.add(rs.getString("nombre"));

            }

            rs.close();

        } catch (SQLException e) {
            System.err.println("Error al leer datos: " + e.getMessage());
        }

    }

    private boolean registarMarca(String nombre_marca) {

        for (String marca : marcas) {
            if (nombre_marca.equalsIgnoreCase(marca)) {
                JOptionPane.showMessageDialog(null, "Ya existe esta marca !", "Aviso", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        try {
            PreparedStatement statement = CONEXION.obtenerSentenciaPreparada("INSERT INTO marca_producto (nombre, es_activa) VALUES (?, ?)");

            statement.setString(1, nombre_marca);
            statement.setBoolean(2, true);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            System.err.println(e);
        }

        return true;

    }

    private int regresarIDMarca(String nombre) {

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
            Logger.getLogger(FormularioProductos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id;

    }

    private int regresarIDCaregoria(String nombre) {
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
            Logger.getLogger(FormularioProductos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id;

    }

    private boolean registarCategoria(String nombre_categoria) {

        for (String categoria : categorias) {
            if (nombre_categoria.equalsIgnoreCase(categoria)) {
                JOptionPane.showMessageDialog(null, "Ya existe esta categoria !", "Aviso", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        try {
            PreparedStatement statement = CONEXION.obtenerSentenciaPreparada("INSERT INTO categoria_producto (nombre, es_activa) VALUES (?, ?)");

            statement.setString(1, nombre_categoria);
            statement.setBoolean(2, true);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            System.err.println(e);
        }

        return true;

    }

    private boolean verificarCamposVacios(JTextField... campos) {

        for (JTextField campo : campos) {

            if (campo.getText().trim().isBlank() || comboBoxMarcasRegistrar.getSelectedItem() == null || comboBoxCategoriasRegistrar.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Hay campos vacios !", "Aviso", JOptionPane.WARNING_MESSAGE);
                return false;

            }

        }

        return true;

    }

    private void limpiarCampos(JTextField... campos) {

        for (JTextField campo : campos) {

            campo.setText("");

        }

    }

    private boolean esSoloDigitos(String... campos) {

        for (String campo : campos) {

            if (!campo.matches("^(?:\\d+|\\d+\\.\\d{1,2})$")) {
                JOptionPane.showMessageDialog(null, "Ingresa solo digitos positivos y maximo 2 decimales en el precio !", "ERROR", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        return true;

    }

    private void actualizarTablaProductos() {
        try {
            DefaultTableModel DefaultModelProductoAdmin = (DefaultTableModel) tablaProductos.getModel();
            DefaultModelProductoAdmin.setRowCount(0);
            DefaultTableModel DefaultModelProductoUsuario = (DefaultTableModel) tablaVentaProductos.getModel();
            DefaultModelProductoUsuario.setRowCount(0);

            productoAdmin.leerProductos();
            Producto[] productos = productoAdmin.obtenerLista();
            for (Producto producto : productos) {

                String nombre = producto.getNombre();
                double precio = producto.getPrecio();
                String marca = producto.getMarca();
                String categoria = producto.getCategoria();
                int unidades = producto.getUnidades();

                DefaultModelProductoAdmin.addRow(new Object[]{nombre, precio, marca, categoria, unidades});
                if (producto.getUnidades() > 0) {
                    DefaultModelProductoUsuario.addRow(new Object[]{nombre, precio, marca, categoria, unidades});

                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(FormularioEditarEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void llenarComboBoxMarcas() {
        comboBoxMarcasRegistrar.removeAllItems();
        comboBoxMarcasModificar.removeAllItems();
        for (String marca : marcas) {
            comboBoxMarcasRegistrar.addItem(marca);
            comboBoxMarcasModificar.addItem(marca);

        }

    }

    private void llenarComboBoxCategorias() {
        comboBoxCategoriasRegistrar.removeAllItems();
        comboBoxCategoriasModificar.removeAllItems();

        for (String categoria : categorias) {

            comboBoxCategoriasRegistrar.addItem(categoria);
            comboBoxCategoriasModificar.addItem(categoria);

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        num_max1 = new grafico_barrav1_jwc.num_max();
        jPanel2 = new javax.swing.JPanel();
        layared_Round_JWC1 = new swing.Layared_Round_JWC();
        agregarCategoriaBtn = new swing.Btn_Round_JWC();
        salir = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        salir1 = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        panel_Round_Degradado_JWC4 = new panel_degradado_jwc.Panel_Round_Degradado_JWC();
        jLabel23 = new javax.swing.JLabel();
        panel_Round_JWC4 = new swing.Panel_Round_JWC();
        textNombre = new javax.swing.JTextField();
        panel_Round_JWC6 = new swing.Panel_Round_JWC();
        textPrecio = new javax.swing.JTextField();
        panel_Round_JWC9 = new swing.Panel_Round_JWC();
        panel_Round_JWC10 = new swing.Panel_Round_JWC();
        panel_Round_JWC7 = new swing.Panel_Round_JWC();
        textUnidades = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        agregarMarcaBtn = new swing.Btn_Round_JWC();
        comboBoxCategoriasRegistrar = new javax.swing.JComboBox<>();
        comboBoxMarcasRegistrar = new javax.swing.JComboBox<>();
        panel_Round_Degradado_JWC1 = new panel_degradado_jwc.Panel_Round_Degradado_JWC();
        btnGuardar = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        jLabel4 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        layared_Round_JWC1.setBackground(new java.awt.Color(51, 51, 51));
        layared_Round_JWC1.setForeground(new java.awt.Color(204, 204, 204));
        layared_Round_JWC1.setEsqInferiorDerecha(50);
        layared_Round_JWC1.setEsqInferiorIzquierda(50);
        layared_Round_JWC1.setEsqSuperiorDerecha(50);
        layared_Round_JWC1.setEsqSuperiorIzquierda(50);

        agregarCategoriaBtn.setBackground(new java.awt.Color(0, 197, 97));
        agregarCategoriaBtn.setText("Agregar Categoria");
        agregarCategoriaBtn.setArco_esquina(30);
        agregarCategoriaBtn.setColor_Hover(new java.awt.Color(2, 111, 56));
        agregarCategoriaBtn.setColor_N_text(new java.awt.Color(0, 0, 0));
        agregarCategoriaBtn.setColor_Normal(new java.awt.Color(0, 197, 97));
        agregarCategoriaBtn.setFocusPainted(false);
        agregarCategoriaBtn.setFont(new java.awt.Font("Product Sans", 1, 14)); // NOI18N
        agregarCategoriaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarCategoriaBtnActionPerformed(evt);
            }
        });
        layared_Round_JWC1.add(agregarCategoriaBtn);
        agregarCategoriaBtn.setBounds(460, 310, 130, 30);

        salir.setText("");
        salir.setBackground_Hover_1(new java.awt.Color(255, 153, 153));
        salir.setBackground_Hover_2(new java.awt.Color(255, 0, 0));
        salir.setBackground_No_Hover_1(new java.awt.Color(153, 0, 51));
        salir.setBackground_No_Hover_2(new java.awt.Color(255, 102, 102));
        salir.setEsquina_inferior_derecho(20);
        salir.setEsquina_inferior_izquierdo(20);
        salir.setEsquina_superior_derecho(20);
        salir.setEsquina_superior_izquierdo(20);
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        layared_Round_JWC1.add(salir);
        salir.setBounds(580, 10, 20, 20);

        salir1.setText("");
        salir1.setBackground_Hover_1(new java.awt.Color(255, 204, 153));
        salir1.setBackground_Hover_2(new java.awt.Color(255, 153, 0));
        salir1.setBackground_No_Hover_1(new java.awt.Color(102, 51, 0));
        salir1.setBackground_No_Hover_2(new java.awt.Color(255, 153, 0));
        salir1.setEsquina_inferior_derecho(20);
        salir1.setEsquina_inferior_izquierdo(20);
        salir1.setEsquina_superior_derecho(20);
        salir1.setEsquina_superior_izquierdo(20);
        salir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salir1ActionPerformed(evt);
            }
        });
        layared_Round_JWC1.add(salir1);
        salir1.setBounds(550, 10, 20, 20);

        panel_Round_Degradado_JWC4.setColor1(new java.awt.Color(148, 213, 193));
        panel_Round_Degradado_JWC4.setColor2(new java.awt.Color(34, 227, 117));
        panel_Round_Degradado_JWC4.setInferior_derecho(60);
        panel_Round_Degradado_JWC4.setSuperior_izquierdo(60);

        jLabel23.setFont(new java.awt.Font("Product Sans", 1, 24)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Registrar Producto");

        javax.swing.GroupLayout panel_Round_Degradado_JWC4Layout = new javax.swing.GroupLayout(panel_Round_Degradado_JWC4);
        panel_Round_Degradado_JWC4.setLayout(panel_Round_Degradado_JWC4Layout);
        panel_Round_Degradado_JWC4Layout.setHorizontalGroup(
            panel_Round_Degradado_JWC4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_Round_Degradado_JWC4Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(121, Short.MAX_VALUE))
        );
        panel_Round_Degradado_JWC4Layout.setVerticalGroup(
            panel_Round_Degradado_JWC4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_Round_Degradado_JWC4Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        layared_Round_JWC1.add(panel_Round_Degradado_JWC4);
        panel_Round_Degradado_JWC4.setBounds(0, 0, 400, 70);

        panel_Round_JWC4.setBackground(new java.awt.Color(0, 0, 0));
        panel_Round_JWC4.setEsqInferiorDerecha(30);
        panel_Round_JWC4.setEsqInferiorIzquierda(30);
        panel_Round_JWC4.setEsqSuperiorDerecha(30);
        panel_Round_JWC4.setEsqSuperiorIzquierda(30);

        textNombre.setBackground(new java.awt.Color(0, 0, 0));
        textNombre.setFont(new java.awt.Font("JetBrainsMono NF", 0, 13)); // NOI18N
        textNombre.setForeground(new java.awt.Color(255, 255, 255));
        textNombre.setBorder(null);
        textNombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textNombreMouseClicked(evt);
            }
        });
        textNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textNombreActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_Round_JWC4Layout = new javax.swing.GroupLayout(panel_Round_JWC4);
        panel_Round_JWC4.setLayout(panel_Round_JWC4Layout);
        panel_Round_JWC4Layout.setHorizontalGroup(
            panel_Round_JWC4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_Round_JWC4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(textNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );
        panel_Round_JWC4Layout.setVerticalGroup(
            panel_Round_JWC4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_Round_JWC4Layout.createSequentialGroup()
                .addComponent(textNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        layared_Round_JWC1.add(panel_Round_JWC4);
        panel_Round_JWC4.setBounds(50, 140, 265, 30);

        panel_Round_JWC6.setBackground(new java.awt.Color(0, 0, 0));
        panel_Round_JWC6.setEsqInferiorDerecha(30);
        panel_Round_JWC6.setEsqInferiorIzquierda(30);
        panel_Round_JWC6.setEsqSuperiorDerecha(30);
        panel_Round_JWC6.setEsqSuperiorIzquierda(30);

        textPrecio.setBackground(new java.awt.Color(0, 0, 0));
        textPrecio.setForeground(new java.awt.Color(255, 255, 255));
        textPrecio.setBorder(null);

        javax.swing.GroupLayout panel_Round_JWC6Layout = new javax.swing.GroupLayout(panel_Round_JWC6);
        panel_Round_JWC6.setLayout(panel_Round_JWC6Layout);
        panel_Round_JWC6Layout.setHorizontalGroup(
            panel_Round_JWC6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_Round_JWC6Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(textPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        panel_Round_JWC6Layout.setVerticalGroup(
            panel_Round_JWC6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(textPrecio, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        layared_Round_JWC1.add(panel_Round_JWC6);
        panel_Round_JWC6.setBounds(50, 230, 150, 30);

        panel_Round_JWC9.setBackground(new java.awt.Color(0, 171, 84));
        panel_Round_JWC9.setEsqInferiorDerecha(30);
        panel_Round_JWC9.setEsqInferiorIzquierda(30);
        panel_Round_JWC9.setEsqSuperiorDerecha(30);
        panel_Round_JWC9.setEsqSuperiorIzquierda(30);

        javax.swing.GroupLayout panel_Round_JWC9Layout = new javax.swing.GroupLayout(panel_Round_JWC9);
        panel_Round_JWC9.setLayout(panel_Round_JWC9Layout);
        panel_Round_JWC9Layout.setHorizontalGroup(
            panel_Round_JWC9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        panel_Round_JWC9Layout.setVerticalGroup(
            panel_Round_JWC9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        layared_Round_JWC1.add(panel_Round_JWC9);
        panel_Round_JWC9.setBounds(260, 140, 100, 30);

        panel_Round_JWC10.setBackground(new java.awt.Color(0, 171, 84));
        panel_Round_JWC10.setEsqInferiorDerecha(30);
        panel_Round_JWC10.setEsqInferiorIzquierda(30);
        panel_Round_JWC10.setEsqSuperiorDerecha(30);
        panel_Round_JWC10.setEsqSuperiorIzquierda(30);

        javax.swing.GroupLayout panel_Round_JWC10Layout = new javax.swing.GroupLayout(panel_Round_JWC10);
        panel_Round_JWC10.setLayout(panel_Round_JWC10Layout);
        panel_Round_JWC10Layout.setHorizontalGroup(
            panel_Round_JWC10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        panel_Round_JWC10Layout.setVerticalGroup(
            panel_Round_JWC10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        layared_Round_JWC1.add(panel_Round_JWC10);
        panel_Round_JWC10.setBounds(140, 230, 100, 30);

        panel_Round_JWC7.setBackground(new java.awt.Color(0, 0, 0));
        panel_Round_JWC7.setEsqInferiorDerecha(30);
        panel_Round_JWC7.setEsqInferiorIzquierda(30);
        panel_Round_JWC7.setEsqSuperiorDerecha(30);
        panel_Round_JWC7.setEsqSuperiorIzquierda(30);

        textUnidades.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        jLabel3.setText("Unidades");

        javax.swing.GroupLayout panel_Round_JWC7Layout = new javax.swing.GroupLayout(panel_Round_JWC7);
        panel_Round_JWC7.setLayout(panel_Round_JWC7Layout);
        panel_Round_JWC7Layout.setHorizontalGroup(
            panel_Round_JWC7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_Round_JWC7Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(panel_Round_JWC7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );
        panel_Round_JWC7Layout.setVerticalGroup(
            panel_Round_JWC7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_Round_JWC7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(textUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        layared_Round_JWC1.add(panel_Round_JWC7);
        panel_Round_JWC7.setBounds(50, 310, 140, 80);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Marca");
        layared_Round_JWC1.add(jLabel1);
        jLabel1.setBounds(320, 200, 70, 16);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Categoria");
        layared_Round_JWC1.add(jLabel2);
        jLabel2.setBounds(320, 300, 90, 16);

        agregarMarcaBtn.setBackground(new java.awt.Color(0, 197, 97));
        agregarMarcaBtn.setText("Agregar Marca");
        agregarMarcaBtn.setArco_esquina(30);
        agregarMarcaBtn.setColor_Hover(new java.awt.Color(2, 111, 56));
        agregarMarcaBtn.setColor_N_text(new java.awt.Color(0, 0, 0));
        agregarMarcaBtn.setColor_Normal(new java.awt.Color(0, 197, 97));
        agregarMarcaBtn.setFocusPainted(false);
        agregarMarcaBtn.setFont(new java.awt.Font("Product Sans", 1, 14)); // NOI18N
        agregarMarcaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarMarcaBtnActionPerformed(evt);
            }
        });
        layared_Round_JWC1.add(agregarMarcaBtn);
        agregarMarcaBtn.setBounds(460, 200, 130, 30);

        comboBoxCategoriasRegistrar.setBackground(new java.awt.Color(0, 0, 0));
        comboBoxCategoriasRegistrar.setFont(new java.awt.Font("Product Sans", 1, 12)); // NOI18N
        comboBoxCategoriasRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        comboBoxCategoriasRegistrar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 239, 112)));
        layared_Round_JWC1.add(comboBoxCategoriasRegistrar);
        comboBoxCategoriasRegistrar.setBounds(320, 320, 100, 40);

        comboBoxMarcasRegistrar.setBackground(new java.awt.Color(0, 0, 0));
        comboBoxMarcasRegistrar.setFont(new java.awt.Font("Product Sans", 1, 12)); // NOI18N
        comboBoxMarcasRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        comboBoxMarcasRegistrar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 239, 112)));
        layared_Round_JWC1.add(comboBoxMarcasRegistrar);
        comboBoxMarcasRegistrar.setBounds(320, 220, 100, 40);

        panel_Round_Degradado_JWC1.setColor1(new java.awt.Color(1, 26, 13));
        panel_Round_Degradado_JWC1.setColor2(new java.awt.Color(0, 0, 0));
        panel_Round_Degradado_JWC1.setInferior_derecho(30);
        panel_Round_Degradado_JWC1.setInferior_izquierdo(30);
        panel_Round_Degradado_JWC1.setSuperior_derecho(30);
        panel_Round_Degradado_JWC1.setSuperior_izquierdo(30);

        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.setBackground_Hover_1(new java.awt.Color(2, 214, 120));
        btnGuardar.setBackground_Hover_2(new java.awt.Color(20, 149, 67));
        btnGuardar.setBackground_No_Hover_1(new java.awt.Color(0, 0, 0));
        btnGuardar.setBackground_No_Hover_2(new java.awt.Color(0, 0, 0));
        btnGuardar.setColor_Hover_text(new java.awt.Color(0, 0, 0));
        btnGuardar.setEsquina_inferior_derecho(30);
        btnGuardar.setEsquina_inferior_izquierdo(30);
        btnGuardar.setEsquina_superior_derecho(30);
        btnGuardar.setEsquina_superior_izquierdo(30);
        btnGuardar.setFocusPainted(false);
        btnGuardar.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/disco.png"))); // NOI18N

        javax.swing.GroupLayout panel_Round_Degradado_JWC1Layout = new javax.swing.GroupLayout(panel_Round_Degradado_JWC1);
        panel_Round_Degradado_JWC1.setLayout(panel_Round_Degradado_JWC1Layout);
        panel_Round_Degradado_JWC1Layout.setHorizontalGroup(
            panel_Round_Degradado_JWC1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_Round_Degradado_JWC1Layout.createSequentialGroup()
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        panel_Round_Degradado_JWC1Layout.setVerticalGroup(
            panel_Round_Degradado_JWC1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_Round_Degradado_JWC1Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        layared_Round_JWC1.add(panel_Round_Degradado_JWC1);
        panel_Round_Degradado_JWC1.setBounds(220, 440, 160, 60);

        getContentPane().add(layared_Round_JWC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 620, 530));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void agregarMarcaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarMarcaBtnActionPerformed

        try {

            marca = JOptionPane.showInputDialog("Ingresa el nombre de la marca");

            if (marca.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Campo vacio", "Aviso", JOptionPane.WARNING_MESSAGE);
                marca = JOptionPane.showInputDialog("Ingresa el nombre de la marca");

            } else {
                if (registarMarca(marca)) {
                    leerMarcas();
                    JOptionPane.showMessageDialog(null, "Marca registrada con exito !");
                    comboboxMarcaVentas.addItem(marca);
                    comboboxMarcaVistaProductos.addItem(marca);
                    comboBoxMarcasRegistrar.addItem(marca);
                    comboBoxMarcasModificar.addItem(marca);

                    
                }

            }
        } catch (HeadlessException | SQLException ex) {
        }
    }//GEN-LAST:event_agregarMarcaBtnActionPerformed

    private void textNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNombreActionPerformed

    }//GEN-LAST:event_textNombreActionPerformed

    private void textNombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textNombreMouseClicked

    }//GEN-LAST:event_textNombreMouseClicked

    private void salir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salir1ActionPerformed
        this.setExtendedState(ICONIFIED);        // TODO add your handling code here:
    }//GEN-LAST:event_salir1ActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        this.dispose();
        limpiarCampos(textNombre, textPrecio);
        textUnidades.setValue(1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }//GEN-LAST:event_salirActionPerformed

    private void agregarCategoriaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarCategoriaBtnActionPerformed

        try {
            categoria = JOptionPane.showInputDialog("Ingresa el nombre de la categoria");

            if (categoria.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Campo vacio", "Aviso", JOptionPane.WARNING_MESSAGE);
                categoria = JOptionPane.showInputDialog("Ingresa el nombre de la categoria");

            } else {
                if (registarCategoria(categoria)) {
                    leerCategoria();

                    JOptionPane.showMessageDialog(null, "Categoria registrada con exito !");
                    comboboxCategoriaVentas.addItem(categoria);
                    comboboxCategoriaVistaProductos.addItem(categoria);
                    comboBoxCategoriasRegistrar.addItem(categoria);
                    comboBoxCategoriasModificar.addItem(categoria);

                }

            }
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_agregarCategoriaBtnActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (verificarCamposVacios(textNombre, textPrecio) && esSoloDigitos(textPrecio.getText())) {
            try {

                if (productoAdmin.registrarProducto(textNombre.getText(), Double.parseDouble(textPrecio.getText().trim()),
                        regresarIDMarca(comboBoxMarcasRegistrar.getSelectedItem().toString()),
                        regresarIDCaregoria(comboBoxCategoriasRegistrar.getSelectedItem().toString()), Integer.parseInt(textUnidades.getValue().toString().trim()))) {

                    JOptionPane.showMessageDialog(null, "Producto registrado con exito");
                    this.dispose();
                    actualizarTablaProductos();
                    limpiarCampos(textNombre, textPrecio);
                    textUnidades.setValue(1);

                }
            } catch (SQLException ex) {
                Logger.getLogger(FormularioEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_btnGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.Btn_Round_JWC agregarCategoriaBtn;
    private swing.Btn_Round_JWC agregarMarcaBtn;
    public static btn_efecto01_jwc.btn_efecto_V1_JWC btnGuardar;
    public static javax.swing.JComboBox<String> comboBoxCategoriasRegistrar;
    public static javax.swing.JComboBox<String> comboBoxMarcasRegistrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private swing.Layared_Round_JWC layared_Round_JWC1;
    private grafico_barrav1_jwc.num_max num_max1;
    private panel_degradado_jwc.Panel_Round_Degradado_JWC panel_Round_Degradado_JWC1;
    private panel_degradado_jwc.Panel_Round_Degradado_JWC panel_Round_Degradado_JWC4;
    private swing.Panel_Round_JWC panel_Round_JWC10;
    private swing.Panel_Round_JWC panel_Round_JWC4;
    private swing.Panel_Round_JWC panel_Round_JWC6;
    private swing.Panel_Round_JWC panel_Round_JWC7;
    private swing.Panel_Round_JWC panel_Round_JWC9;
    private btn_efecto01_jwc.btn_efecto_V1_JWC salir;
    private btn_efecto01_jwc.btn_efecto_V1_JWC salir1;
    private javax.swing.JTextField textNombre;
    private javax.swing.JTextField textPrecio;
    private javax.swing.JSpinner textUnidades;
    // End of variables declaration//GEN-END:variables
}
