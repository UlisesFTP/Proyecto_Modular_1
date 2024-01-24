package vista;

import clases_administradoras.ProductoAdmin;
import conexion.Conexion;
import java.awt.Color;
import javax.swing.JOptionPane;
import static inicio.Main.inicializarFlatLaf;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Producto;
import static vista.FormularioProductos.comboBoxCategoriasRegistrar;
import static vista.FormularioProductos.comboBoxMarcasRegistrar;
import static vista.InicioAdministrador.comboboxCategoriaVistaProductos;
import static vista.InicioAdministrador.comboboxMarcaVistaProductos;
import static vista.InicioAdministrador.tablaProductos;
import static vista.InicioUsuario.comboboxCategoriaVentas;
import static vista.InicioUsuario.comboboxMarcaVentas;
import static vista.InicioUsuario.tablaVentaProductos;

/**
 *
 * @author joel_
 */
public class FormularioEditarProductos extends javax.swing.JFrame {

    private String marca;
    private String categoria;
    private Conexion CONEXION;
    private ProductoAdmin productoAdmin;

    public static ArrayList<String> marcas;
    public static ArrayList<String> categorias;

    public FormularioEditarProductos() {

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
            Logger.getLogger(FormularioEditarProductos.class.getName()).log(Level.SEVERE, null, ex);
        }

        setBackground(new Color(0, 0, 0, 0));

        TextPrompt placeholderBarraNombre = new TextPrompt("Nombre", textNombre);
        TextPrompt placeholderBarraPrecio = new TextPrompt("Precio", textPrecio);

        placeholderBarraNombre.changeAlpha(0.75f);
        placeholderBarraPrecio.changeAlpha(0.75f);

    }

    private void llenarComboBox() {

        for (String marca : marcas) {
            comboBoxMarcasModificar.addItem(marca);

        }

        for (String categoria : categorias) {
            comboBoxCategoriasModificar.addItem(categoria);
        }

    }

    private void leerMarcas() throws SQLException {

        String sql = "SELECT * FROM marca_producto WHERE es_activa = true";
        ResultSet rs = CONEXION.executeQuery(sql);

        try {
            while (rs.next()) {
                marcas.add(rs.getString("nombre"));
            }

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

        } catch (SQLException e) {
            System.err.println("Error al leer datos: " + e.getMessage());
        }

    }

    private boolean verificarCamposVacios(JTextField... campos) {

        for (JTextField campo : campos) {

            if (campo.getText().trim().isBlank()) {
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

    private boolean modificarMarca() {

        try {

            if (comboBoxMarcasModificar.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "No hay nada seleccionado ! ", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            marca = comboBoxMarcasModificar.getSelectedItem().toString();
            JOptionPane.showMessageDialog(null, "Esta es la marca a modificar: " + marca);
            int id = productoAdmin.regresarIDMarca(marca);

            String marcaNueva;
            marcaNueva = JOptionPane.showInputDialog("Ingresa el nuevo nombre de la marca");

            if (marcaNueva == null) {
                JOptionPane.showMessageDialog(null, "Accion Cancelada");
                return false;
            }

            productoAdmin.editarMarca(id, marcaNueva);
            leerMarcas();

            comboBoxMarcasModificar.removeItem(marca);
            comboboxMarcaVistaProductos.removeItem(marca);
            comboboxMarcaVentas.removeItem(marca);
            comboBoxMarcasRegistrar.removeItem(marca);

            comboBoxMarcasModificar.addItem(marcaNueva);
            comboboxMarcaVistaProductos.addItem(marcaNueva);
            comboboxMarcaVentas.addItem(marcaNueva);
            comboBoxMarcasRegistrar.addItem(marcaNueva);
            actualizarTablaProductos();
        } catch (SQLException ex) {
            Logger.getLogger(FormularioEditarProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;

    }

    private boolean modificarCategoria() {

        try {
            if (comboBoxCategoriasModificar.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "No hay nada seleccionado ! ", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            categoria = comboBoxCategoriasModificar.getSelectedItem().toString();
            JOptionPane.showMessageDialog(null, "Esta es la categoria a modificar: " + categoria);
            int id = productoAdmin.regresarIDMarca(categoria);

            String categoriaNueva;
            categoriaNueva = JOptionPane.showInputDialog("Ingresa el nuevo nombre de la categoria");
            if (categoriaNueva == null) {
                JOptionPane.showMessageDialog(null, "Accion Cancelada");
                return false;
            }
            productoAdmin.editarCategoria(id, categoriaNueva);
            leerMarcas();

            comboBoxCategoriasModificar.removeItem(categoria);
            comboboxCategoriaVistaProductos.removeItem(categoria);
            comboboxCategoriaVentas.removeItem(categoria);
            comboBoxCategoriasRegistrar.removeItem(categoria);

            comboBoxCategoriasModificar.addItem(categoriaNueva);
            comboboxCategoriaVistaProductos.addItem(categoriaNueva);
            comboboxCategoriaVentas.addItem(categoriaNueva);
            comboBoxCategoriasRegistrar.addItem(categoriaNueva);

            actualizarTablaProductos();
        } catch (SQLException ex) {
            Logger.getLogger(FormularioEditarProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;

    }

    private boolean removerMarca() {

        try {
            if (comboBoxMarcasModificar.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "No hay nada que borrar !", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            marca = comboBoxMarcasModificar.getSelectedItem().toString();

            int id = productoAdmin.regresarIDMarca(marca);
            productoAdmin.removerMarca(id);
            comboBoxMarcasModificar.removeItem(marca);
            comboboxMarcaVistaProductos.removeItem(marca);
            comboboxMarcaVentas.removeItem(marca);
            comboBoxMarcasRegistrar.removeItem(marca);
            leerMarcas();
            actualizarTablaProductos();

        } catch (SQLException ex) {
            Logger.getLogger(FormularioEditarProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;

    }

    private boolean removerCategoria() {

        try {
            if (comboBoxCategoriasModificar.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "No hay nada que borrar !", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            categoria = comboBoxCategoriasModificar.getSelectedItem().toString();
            int id = productoAdmin.regresarIDCaregoria(categoria);
            productoAdmin.removerCategoria(id);
            comboBoxCategoriasModificar.removeItem(categoria);
            comboboxCategoriaVistaProductos.removeItem(categoria);
            comboboxCategoriaVentas.removeItem(categoria);
            comboBoxMarcasRegistrar.removeItem(categoria);
            leerCategoria();
            actualizarTablaProductos();

        } catch (SQLException ex) {
            Logger.getLogger(FormularioEditarProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        num_max1 = new grafico_barrav1_jwc.num_max();
        jPanel2 = new javax.swing.JPanel();
        layared_Round_JWC1 = new swing.Layared_Round_JWC();
        modificarCategoriaBtn = new swing.Btn_Round_JWC();
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
        panelUnidades = new swing.Panel_Round_JWC();
        textUnidades = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        modificarMarcaBtn = new swing.Btn_Round_JWC();
        comboBoxCategoriasModificar = new javax.swing.JComboBox<>();
        comboBoxMarcasModificar = new javax.swing.JComboBox<>();
        panel_Round_Degradado_JWC1 = new panel_degradado_jwc.Panel_Round_Degradado_JWC();
        btnGuardar = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        jLabel4 = new javax.swing.JLabel();
        removerMarcaBtn = new swing.Btn_Round_JWC();
        removerMarcaBtn1 = new swing.Btn_Round_JWC();

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

        modificarCategoriaBtn.setBackground(new java.awt.Color(255, 204, 51));
        modificarCategoriaBtn.setText("Modificar Categoria");
        modificarCategoriaBtn.setArco_esquina(30);
        modificarCategoriaBtn.setColor_Hover(new java.awt.Color(169, 127, 3));
        modificarCategoriaBtn.setColor_N_text(new java.awt.Color(0, 0, 0));
        modificarCategoriaBtn.setColor_Normal(new java.awt.Color(255, 204, 51));
        modificarCategoriaBtn.setFocusPainted(false);
        modificarCategoriaBtn.setFont(new java.awt.Font("Product Sans", 1, 14)); // NOI18N
        modificarCategoriaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarCategoriaBtnActionPerformed(evt);
            }
        });
        layared_Round_JWC1.add(modificarCategoriaBtn);
        modificarCategoriaBtn.setBounds(460, 320, 140, 30);

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

        panel_Round_Degradado_JWC4.setColor1(new java.awt.Color(169, 127, 3));
        panel_Round_Degradado_JWC4.setColor2(new java.awt.Color(255, 204, 51));
        panel_Round_Degradado_JWC4.setInferior_derecho(60);
        panel_Round_Degradado_JWC4.setSuperior_izquierdo(60);

        jLabel23.setFont(new java.awt.Font("Product Sans", 1, 24)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Editar Producto");

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

        panel_Round_JWC9.setBackground(new java.awt.Color(255, 204, 51));
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

        panel_Round_JWC10.setBackground(new java.awt.Color(255, 204, 51));
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

        panelUnidades.setBackground(new java.awt.Color(0, 0, 0));
        panelUnidades.setEsqInferiorDerecha(30);
        panelUnidades.setEsqInferiorIzquierda(30);
        panelUnidades.setEsqSuperiorDerecha(30);
        panelUnidades.setEsqSuperiorIzquierda(30);

        textUnidades.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        jLabel3.setText("Unidades");

        javax.swing.GroupLayout panelUnidadesLayout = new javax.swing.GroupLayout(panelUnidades);
        panelUnidades.setLayout(panelUnidadesLayout);
        panelUnidadesLayout.setHorizontalGroup(
            panelUnidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelUnidadesLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(panelUnidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );
        panelUnidadesLayout.setVerticalGroup(
            panelUnidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelUnidadesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(textUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        layared_Round_JWC1.add(panelUnidades);
        panelUnidades.setBounds(50, 310, 140, 80);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Marca");
        layared_Round_JWC1.add(jLabel1);
        jLabel1.setBounds(320, 200, 70, 16);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Categoria");
        layared_Round_JWC1.add(jLabel2);
        jLabel2.setBounds(320, 310, 90, 16);

        modificarMarcaBtn.setBackground(new java.awt.Color(255, 204, 51));
        modificarMarcaBtn.setText("Modificar Marca");
        modificarMarcaBtn.setArco_esquina(30);
        modificarMarcaBtn.setColor_Hover(new java.awt.Color(169, 127, 3));
        modificarMarcaBtn.setColor_N_text(new java.awt.Color(0, 0, 0));
        modificarMarcaBtn.setColor_Normal(new java.awt.Color(255, 204, 51));
        modificarMarcaBtn.setFocusPainted(false);
        modificarMarcaBtn.setFont(new java.awt.Font("Product Sans", 1, 14)); // NOI18N
        modificarMarcaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarMarcaBtnActionPerformed(evt);
            }
        });
        layared_Round_JWC1.add(modificarMarcaBtn);
        modificarMarcaBtn.setBounds(460, 190, 130, 30);

        comboBoxCategoriasModificar.setBackground(new java.awt.Color(0, 0, 0));
        comboBoxCategoriasModificar.setFont(new java.awt.Font("Product Sans", 1, 12)); // NOI18N
        comboBoxCategoriasModificar.setForeground(new java.awt.Color(255, 255, 255));
        comboBoxCategoriasModificar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 51)));
        layared_Round_JWC1.add(comboBoxCategoriasModificar);
        comboBoxCategoriasModificar.setBounds(320, 340, 100, 40);

        comboBoxMarcasModificar.setBackground(new java.awt.Color(0, 0, 0));
        comboBoxMarcasModificar.setFont(new java.awt.Font("Product Sans", 1, 12)); // NOI18N
        comboBoxMarcasModificar.setForeground(new java.awt.Color(255, 255, 255));
        comboBoxMarcasModificar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 51)));
        layared_Round_JWC1.add(comboBoxMarcasModificar);
        comboBoxMarcasModificar.setBounds(320, 220, 100, 40);

        panel_Round_Degradado_JWC1.setColor1(new java.awt.Color(1, 26, 13));
        panel_Round_Degradado_JWC1.setColor2(new java.awt.Color(0, 0, 0));
        panel_Round_Degradado_JWC1.setInferior_derecho(30);
        panel_Round_Degradado_JWC1.setInferior_izquierdo(30);
        panel_Round_Degradado_JWC1.setSuperior_derecho(30);
        panel_Round_Degradado_JWC1.setSuperior_izquierdo(30);

        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.setAutoscrolls(true);
        btnGuardar.setBackground_Hover_1(new java.awt.Color(255, 204, 51));
        btnGuardar.setBackground_Hover_2(new java.awt.Color(169, 127, 3));
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

        removerMarcaBtn.setBackground(new java.awt.Color(204, 0, 51));
        removerMarcaBtn.setForeground(new java.awt.Color(255, 255, 255));
        removerMarcaBtn.setText("Remover Marca");
        removerMarcaBtn.setArco_esquina(30);
        removerMarcaBtn.setColor_Hover(new java.awt.Color(153, 0, 51));
        removerMarcaBtn.setColor_Normal(new java.awt.Color(204, 0, 51));
        removerMarcaBtn.setFocusPainted(false);
        removerMarcaBtn.setFont(new java.awt.Font("Product Sans", 1, 14)); // NOI18N
        removerMarcaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerMarcaBtnActionPerformed(evt);
            }
        });
        layared_Round_JWC1.add(removerMarcaBtn);
        removerMarcaBtn.setBounds(460, 240, 130, 30);

        removerMarcaBtn1.setBackground(new java.awt.Color(204, 0, 51));
        removerMarcaBtn1.setForeground(new java.awt.Color(255, 255, 255));
        removerMarcaBtn1.setText("Remover Categoria");
        removerMarcaBtn1.setArco_esquina(30);
        removerMarcaBtn1.setColor_Hover(new java.awt.Color(153, 0, 51));
        removerMarcaBtn1.setColor_Normal(new java.awt.Color(204, 0, 51));
        removerMarcaBtn1.setFocusPainted(false);
        removerMarcaBtn1.setFont(new java.awt.Font("Product Sans", 1, 14)); // NOI18N
        removerMarcaBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerMarcaBtn1ActionPerformed(evt);
            }
        });
        layared_Round_JWC1.add(removerMarcaBtn1);
        removerMarcaBtn1.setBounds(460, 370, 140, 30);

        getContentPane().add(layared_Round_JWC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 620, 530));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void modificarMarcaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarMarcaBtnActionPerformed
        if (modificarMarca()) {
            JOptionPane.showMessageDialog(null, "Marca modificada con exito");

        }

    }//GEN-LAST:event_modificarMarcaBtnActionPerformed

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

    private void modificarCategoriaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarCategoriaBtnActionPerformed
        if (modificarCategoria()) {
            JOptionPane.showMessageDialog(null, "Categoria modificada con exito");
        }

    }//GEN-LAST:event_modificarCategoriaBtnActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (verificarCamposVacios(textNombre, textPrecio) && esSoloDigitos(textPrecio.getText())) {
            try {
                productoAdmin.leerProductos();
                String nombreObtenido = tablaProductos.getValueAt(tablaProductos.getSelectedRow(), 0).toString();
                String marcaObtenida = tablaProductos.getValueAt(tablaProductos.getSelectedRow(), 2).toString();
                String categoriaObtenida = tablaProductos.getValueAt(tablaProductos.getSelectedRow(), 3).toString();
                int id = productoAdmin.obtenerID(nombreObtenido, marcaObtenida, categoriaObtenida);
                if (productoAdmin.editarProducto(id, textNombre.getText(), Double.parseDouble(textPrecio.getText().trim()),
                        productoAdmin.regresarIDMarca(comboBoxMarcasModificar.getSelectedItem().toString()), productoAdmin.regresarIDCaregoria(comboBoxCategoriasModificar.getSelectedItem().toString()),
                        Integer.parseInt(textUnidades.getValue().toString().trim()))) {

                    JOptionPane.showMessageDialog(null, "Producto modificado con exito");
                    this.dispose();
                    actualizarTablaProductos();
                    limpiarCampos(textNombre, textPrecio);
                    textUnidades.setValue(1);

                }
            } catch (SQLException ex) {
                Logger.getLogger(FormularioEditarProductos.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void removerMarcaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerMarcaBtnActionPerformed
        int result = JOptionPane.showConfirmDialog(null, "¿Deseas remover esta marca ? " + "Esta es la marca a eliminar: "
                + comboBoxMarcasModificar.getSelectedItem().toString(), "Confirmación", JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            if (removerMarca()) {

                JOptionPane.showMessageDialog(null, "Marca eliminada");

            }

        }

    }//GEN-LAST:event_removerMarcaBtnActionPerformed

    private void removerMarcaBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerMarcaBtn1ActionPerformed
        int result = JOptionPane.showConfirmDialog(null, "¿Deseas remover esta categoria? " + "Esta es la categoria a eliminar: "
                + comboBoxCategoriasModificar.getSelectedItem().toString(), "Confirmación", JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            if (removerCategoria()) {
                JOptionPane.showMessageDialog(null, "Categoria eliminada");

            }

        }
    }//GEN-LAST:event_removerMarcaBtn1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static btn_efecto01_jwc.btn_efecto_V1_JWC btnGuardar;
    public static javax.swing.JComboBox<String> comboBoxCategoriasModificar;
    public static javax.swing.JComboBox<String> comboBoxMarcasModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private swing.Layared_Round_JWC layared_Round_JWC1;
    private swing.Btn_Round_JWC modificarCategoriaBtn;
    private swing.Btn_Round_JWC modificarMarcaBtn;
    private grafico_barrav1_jwc.num_max num_max1;
    private swing.Panel_Round_JWC panelUnidades;
    private panel_degradado_jwc.Panel_Round_Degradado_JWC panel_Round_Degradado_JWC1;
    private panel_degradado_jwc.Panel_Round_Degradado_JWC panel_Round_Degradado_JWC4;
    private swing.Panel_Round_JWC panel_Round_JWC10;
    private swing.Panel_Round_JWC panel_Round_JWC4;
    private swing.Panel_Round_JWC panel_Round_JWC6;
    private swing.Panel_Round_JWC panel_Round_JWC9;
    private swing.Btn_Round_JWC removerMarcaBtn;
    private swing.Btn_Round_JWC removerMarcaBtn1;
    private btn_efecto01_jwc.btn_efecto_V1_JWC salir;
    private btn_efecto01_jwc.btn_efecto_V1_JWC salir1;
    public javax.swing.JTextField textNombre;
    public javax.swing.JTextField textPrecio;
    public javax.swing.JSpinner textUnidades;
    // End of variables declaration//GEN-END:variables
}
