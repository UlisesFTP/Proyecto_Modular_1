package vista;

import static inicio.Main.inicializarFlatLaf;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import static vista.InicioUsuario.productosCarrito;
import clases_administradoras.ProductoAdmin;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import controlador.Correo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Producto;
import tabledark.TableDark;
import static vista.InicioAdministrador.tablaProductos;
import static vista.InicioUsuario.tablaVentaProductos;
import static vista.InicioUsuario.totalPagar;
import static vista.vistaSeleccionClienteCarrito.tablaClientesCarrito;

/**
 *
 * @author joel_
 */
public class vistaCarrito extends javax.swing.JFrame {

    private ProductoAdmin productoAdmin;
    public vistaSeleccionClienteCarrito vistaCarritoCliente;
    private Correo correoVentaRealizada;
    boolean hayCampoSeleccionadoProducto = false;

    public vistaCarrito() {
        inicializarFlatLaf();

        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        vistaCarritoCliente = new vistaSeleccionClienteCarrito();
        int selectedRow;

        try {
            productoAdmin = new ProductoAdmin();
            correoVentaRealizada = new Correo();
        } catch (SQLException ex) {
            Logger.getLogger(vistaCarrito.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(vistaCarrito.class.getName()).log(Level.SEVERE, null, ex);
        }

        TextPrompt placeholderBarraPrecio = new TextPrompt("Efectivo", textPrecio);

        placeholderBarraPrecio.changeAlpha(0.75f);

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

    private boolean esSoloDigitosPrecio(String campo) {

        if (!campo.matches("^(?:\\d+|\\d+\\.\\d{1,2})$")) {
            JOptionPane.showMessageDialog(null, "Ingresa solo digitos positivos y maximo 2 decimales en el precio !", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;

    }

    private boolean siAlcanzaDinero() {

        if (Double.parseDouble(labelDineroRecibido.getText()) < Double.parseDouble(labelTotalPago.getText())) {
            JOptionPane.showMessageDialog(null, "No hay suficiente efectivo recibido para realizar la venta !", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    private void realizarVenta() {

        try {
            int idProducto;
            productoAdmin.leerProductos();
            DefaultTableModel DefaultModelProductoCarrito = (DefaultTableModel) tablaProductosCarrito.getModel();

            // Iterar todas las filas de la tabla e imprimir los elementos de cada columna
            int rowCount = DefaultModelProductoCarrito.getRowCount();

            for (int i = 0; i < rowCount; i++) {

                idProducto = productoAdmin.obtenerID(DefaultModelProductoCarrito.getValueAt(i, 0).toString(),
                        DefaultModelProductoCarrito.getValueAt(i, 2).toString(), DefaultModelProductoCarrito.getValueAt(i, 3).toString());
                productoAdmin.venderProducto(idProducto, Integer.parseInt(DefaultModelProductoCarrito.getValueAt(i, 4).toString()));

            }
            enviarPDFVenta();
            JOptionPane.showMessageDialog(null, "Se realizo la venta con exito");
            productosCarrito.clear();
            this.dispose();
            labelTotalPago.setText("0.00");
            labelDineroRecibido.setText("0.00");
            labelCambio.setText("0.00");
            textPrecio.setText("");
            InicioUsuario.totalPagar = 0;

            DefaultModelProductoCarrito.setRowCount(0);

        } catch (SQLException ex) {
            Logger.getLogger(vistaCarrito.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private boolean HayProductos() {
        DefaultTableModel DefaultModelProductoCarrito = (DefaultTableModel) tablaProductosCarrito.getModel();
        if (DefaultModelProductoCarrito.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "No hay productos en el carrito para realizar la venta", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        } else {
            return true;
        }

    }

    private boolean hayClienteSeleccionado() {

        if (labelClienteEscojido.getText().equals("--------")) {
            JOptionPane.showMessageDialog(null, "No hay cliente seleccionado para realizar la venta", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;

    }

    private void actualizarTablaProductos() {
        try {
            DefaultTableModel DefaultModelProductoAdmin = (DefaultTableModel) tablaProductos.getModel();
            DefaultModelProductoAdmin.setRowCount(0);
            DefaultTableModel DefaultModelProductoVenta = (DefaultTableModel) tablaVentaProductos.getModel();
            DefaultModelProductoVenta.setRowCount(0);

            productoAdmin.leerProductos();
            Producto[] productos = productoAdmin.obtenerLista();
            for (Producto producto : productos) {

                String nombre = producto.getNombre();
                double precio = producto.getPrecio();
                String marca = producto.getMarca();
                String categoria = producto.getCategoria();
                int unidades = producto.getUnidades();

                DefaultModelProductoAdmin.addRow(new Object[]{nombre, precio, marca, categoria, unidades});
                DefaultModelProductoVenta.addRow(new Object[]{nombre, precio, marca, categoria, unidades});

            }

        } catch (SQLException ex) {
            Logger.getLogger(FormularioEditarEmpleado.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void enviarPDFVenta() {

        try {

            // Especifica la ruta completa donde deseas guardar el PDF
            String outputPath = "src/PDFventas/textoVenta.pdf";

            Document document = new Document(PageSize.A4);

            // step 2: creation of the writer
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream(outputPath));

            DefaultTableModel DefaultModelProductoCarrito = (DefaultTableModel) tablaProductosCarrito.getModel();
            int rowCount = DefaultModelProductoCarrito.getRowCount();

            String nombreCliente = tablaClientesCarrito.getValueAt(tablaClientesCarrito.getSelectedRow(), 0).toString()
                    + " " + tablaClientesCarrito.getValueAt(tablaClientesCarrito.getSelectedRow(), 1).toString();

            String DineroPagado = labelDineroRecibido.getText();
            String totalAPagar = labelTotalPago.getText();
            String cambio = labelCambio.getText();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String fecha = LocalDateTime.now().format(formato);
            String correo = labelCorreoCliente.getText();

            try {
                // Configuración del PDF

                document.open();

                // Agregar imagen en la parte superior (ruta de la imagen puede variar)
                Image logo = Image.getInstance("src/images/logoBarberia.jpg");
                logo.scaleToFit(100, 100);
                document.add(logo);

                BaseFont baseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
                Font font = new Font(baseFont, 15, Font.BOLD, Color.BLACK);
                Font fontTitle = new Font(baseFont, 18, Font.BOLD, Color.GREEN);
                Font fontTitleSuperior = new Font(baseFont, 22, Font.BOLD, Color.BLACK);
                Font cuerpo = new Font(baseFont, 13, Font.NORMAL, Color.DARK_GRAY);

                // Agregar contenido al PDF
                Paragraph title = new Paragraph("BP BARBERIA", fontTitleSuperior);
                title.setAlignment(Element.ALIGN_CENTER);
                title.setFont(fontTitleSuperior);
                document.add(title);

                // Agregar contenido al PDF
                Paragraph subtitle = new Paragraph("Comprobante de compra", fontTitle);
                subtitle.setAlignment(Element.ALIGN_CENTER);
                subtitle.setFont(fontTitle);
                document.add(subtitle);

                document.add(new Paragraph("\t", font));
                document.add(new Paragraph("\t", font));

                document.add(new Paragraph("Cliente: " + nombreCliente, font));
                document.add(new Paragraph("Fecha de compra: " + fecha, font));
                document.add(new Paragraph("Efectivo recibido: " + DineroPagado + " MXN $", font));
                document.add(new Paragraph("Total a pagar : " + totalAPagar + " MXN $", font));
                document.add(new Paragraph("------------------------------------------", font));

                document.add(new Paragraph("Cambio: " + cambio + " MXN $", font));

                document.add(new Paragraph("\t", font));
                document.add(new Paragraph("\t", font));

                PdfPTable table = new PdfPTable(5);
                table.setWidthPercentage(115);
                table.setSpacingBefore(10f);
                table.setSpacingAfter(10f);
                agregarCelda(table, "Producto", fontTitle);
                agregarCelda(table, "Precio", fontTitle);
                agregarCelda(table, "Marca", fontTitle);
                agregarCelda(table, "Categoria", fontTitle);
                agregarCelda(table, "Cantidad", fontTitle);

                for (int i = 0; i < rowCount; i++) {

                    agregarCelda(table, DefaultModelProductoCarrito.getValueAt(i, 0).toString(), font);
                    agregarCelda(table, DefaultModelProductoCarrito.getValueAt(i, 1).toString(), font);
                    agregarCelda(table, DefaultModelProductoCarrito.getValueAt(i, 2).toString(), font);
                    agregarCelda(table, DefaultModelProductoCarrito.getValueAt(i, 3).toString(), font);
                    agregarCelda(table, DefaultModelProductoCarrito.getValueAt(i, 4).toString(), font);

                }
                document.add(table);
                document.add(new Paragraph("\t", font));
                document.add(new Paragraph("\t", font));
                document.add(new Paragraph("\t", font));

                document.add(new Phrase(" Gracias por tu compra revisa todos tus productos esten en buen estado , tienes garantia de 5 dias.", cuerpo));
                document.close();
                JOptionPane.showMessageDialog(null, "Enviando documento espere ...", "", JOptionPane.PLAIN_MESSAGE);
                correoVentaRealizada.createEmail(correo, "Comprobante de compra", " ¡ Hola " + nombreCliente + " ! aqui esta el comprobante de tu compras realizadas en BP Barber", outputPath);
                correoVentaRealizada.sendEmail();
                System.out.println("PDF creado exitosamente en: " + outputPath);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(InicioUsuario.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DocumentException | IOException ex) {
                Logger.getLogger(InicioUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (DocumentException | FileNotFoundException ex) {
            Logger.getLogger(InicioUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void agregarCelda(PdfPTable table, String contenido, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(contenido, font));
        table.addCell(cell);
    }

    private boolean estaSelccionadaTablaProductos() {

        tablaVentaProductos.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent Mouse_evt) {

                tablaVentaProductos = (TableDark) Mouse_evt.getSource();
                Point point = Mouse_evt.getPoint();
                int row = tablaVentaProductos.rowAtPoint(point);

                if (Mouse_evt.getClickCount() == 1) {

                    hayCampoSeleccionadoProducto = true;

                }

            }
        });
        return hayCampoSeleccionadoProducto;
    }

    private void removerProductoDelCarrito() {
        DefaultTableModel defaultModelProductoCarrito = (DefaultTableModel) tablaProductosCarrito.getModel();
        String nombreProducto = tablaProductosCarrito.getValueAt(tablaProductosCarrito.getSelectedRow(), 0).toString();
        String marca = tablaProductosCarrito.getValueAt(tablaProductosCarrito.getSelectedRow(), 2).toString();
        String categoria = tablaProductosCarrito.getValueAt(tablaProductosCarrito.getSelectedRow(), 3).toString();

        Iterator<Producto> iterator = productosCarrito.iterator();
        while (iterator.hasNext()) {
            Producto producto = iterator.next();
            if (producto.getNombre().equalsIgnoreCase(nombreProducto)
                    && producto.getMarca().equalsIgnoreCase(marca)
                    && producto.getCategoria().equalsIgnoreCase(categoria)) {

                double precioARestar = Double.parseDouble(tablaProductosCarrito.getValueAt(tablaProductosCarrito.getSelectedRow(), 1).toString());
                double precioRestado = Double.parseDouble(labelTotalPago.getText()) - precioARestar;

                iterator.remove();  // Use iterator to safely remove the element

                defaultModelProductoCarrito.removeRow(tablaProductosCarrito.getSelectedRow());
                labelTotalPago.setText(String.valueOf(precioRestado));
                totalPagar = totalPagar - precioARestar;
                System.out.println(totalPagar);
                JOptionPane.showMessageDialog(null, "Producto removido del carrito !");
            }
        }
        actualizarTablaProductos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        num_max1 = new grafico_barrav1_jwc.num_max();
        jPanel2 = new javax.swing.JPanel();
        layared_Round_JWC1 = new swing.Layared_Round_JWC();
        salir = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        salir1 = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        panel_Round_Degradado_JWC4 = new panel_degradado_jwc.Panel_Round_Degradado_JWC();
        jLabel23 = new javax.swing.JLabel();
        panel_Round_JWC5 = new swing.Panel_Round_JWC();
        textPrecio = new javax.swing.JFormattedTextField();
        panel_Round_JWC11 = new swing.Panel_Round_JWC();
        panel_Round_JWC1 = new swing.Panel_Round_JWC();
        jLabel1 = new javax.swing.JLabel();
        labelTotalPago = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        labelDineroRecibido = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        labelClienteEscojido = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        labelCambio = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnVentasAgregar1 = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        jLabel2 = new javax.swing.JLabel();
        labelCorreoCliente = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        scrollTablaCarrito = new javax.swing.JScrollPane();
        tablaProductosCarrito = new tabledark.TableDark();
        panelVentasAgregar = new swing.Panel_Round_JWC();
        labelVentasAgregar = new javax.swing.JLabel();
        btnVentasAgregar = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        panelCarrito = new panel_degradado_jwc.Panel_Round_Degradado_JWC();
        btnCarrito = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        jLabel13 = new javax.swing.JLabel();

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
        salir.setBounds(790, 10, 20, 20);

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
        salir1.setBounds(760, 10, 20, 20);

        panel_Round_Degradado_JWC4.setColor1(new java.awt.Color(148, 213, 193));
        panel_Round_Degradado_JWC4.setColor2(new java.awt.Color(34, 227, 117));
        panel_Round_Degradado_JWC4.setInferior_derecho(60);
        panel_Round_Degradado_JWC4.setSuperior_izquierdo(60);

        jLabel23.setFont(new java.awt.Font("Product Sans", 1, 36)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Carrito");

        javax.swing.GroupLayout panel_Round_Degradado_JWC4Layout = new javax.swing.GroupLayout(panel_Round_Degradado_JWC4);
        panel_Round_Degradado_JWC4.setLayout(panel_Round_Degradado_JWC4Layout);
        panel_Round_Degradado_JWC4Layout.setHorizontalGroup(
            panel_Round_Degradado_JWC4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_Round_Degradado_JWC4Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(209, Short.MAX_VALUE))
        );
        panel_Round_Degradado_JWC4Layout.setVerticalGroup(
            panel_Round_Degradado_JWC4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_Round_Degradado_JWC4Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        layared_Round_JWC1.add(panel_Round_Degradado_JWC4);
        panel_Round_Degradado_JWC4.setBounds(0, 0, 400, 70);

        panel_Round_JWC5.setBackground(new java.awt.Color(0, 0, 0));
        panel_Round_JWC5.setEsqInferiorDerecha(30);
        panel_Round_JWC5.setEsqInferiorIzquierda(30);
        panel_Round_JWC5.setEsqSuperiorDerecha(30);
        panel_Round_JWC5.setEsqSuperiorIzquierda(30);

        textPrecio.setBackground(new java.awt.Color(0, 0, 0));
        textPrecio.setBorder(null);
        textPrecio.setForeground(new java.awt.Color(255, 255, 255));
        textPrecio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        textPrecio.setFont(new java.awt.Font("JetBrains Mono NL", 0, 13)); // NOI18N
        textPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textPrecioKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout panel_Round_JWC5Layout = new javax.swing.GroupLayout(panel_Round_JWC5);
        panel_Round_JWC5.setLayout(panel_Round_JWC5Layout);
        panel_Round_JWC5Layout.setHorizontalGroup(
            panel_Round_JWC5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_Round_JWC5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(textPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        panel_Round_JWC5Layout.setVerticalGroup(
            panel_Round_JWC5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_Round_JWC5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(textPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        layared_Round_JWC1.add(panel_Round_JWC5);
        panel_Round_JWC5.setBounds(50, 160, 150, 30);

        panel_Round_JWC11.setBackground(new java.awt.Color(0, 171, 84));
        panel_Round_JWC11.setEsqInferiorDerecha(30);
        panel_Round_JWC11.setEsqInferiorIzquierda(30);
        panel_Round_JWC11.setEsqSuperiorDerecha(30);
        panel_Round_JWC11.setEsqSuperiorIzquierda(30);

        javax.swing.GroupLayout panel_Round_JWC11Layout = new javax.swing.GroupLayout(panel_Round_JWC11);
        panel_Round_JWC11.setLayout(panel_Round_JWC11Layout);
        panel_Round_JWC11Layout.setHorizontalGroup(
            panel_Round_JWC11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        panel_Round_JWC11Layout.setVerticalGroup(
            panel_Round_JWC11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        layared_Round_JWC1.add(panel_Round_JWC11);
        panel_Round_JWC11.setBounds(130, 160, 110, 30);

        panel_Round_JWC1.setBackground(new java.awt.Color(0, 0, 0));
        panel_Round_JWC1.setEsqInferiorDerecha(30);
        panel_Round_JWC1.setEsqInferiorIzquierda(30);
        panel_Round_JWC1.setEsqSuperiorDerecha(30);
        panel_Round_JWC1.setEsqSuperiorIzquierda(30);

        jLabel1.setText("Total a pagar :");

        labelTotalPago.setForeground(new java.awt.Color(153, 255, 153));
        labelTotalPago.setText("0.00");

        jLabel3.setText("MXN $");

        jLabel4.setText("Dinero obtenido :");

        labelDineroRecibido.setForeground(new java.awt.Color(153, 255, 153));
        labelDineroRecibido.setText("0.00");

        jLabel6.setText("MXN $");

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel7.setText("Cliente: ");

        labelClienteEscojido.setText("--------");

        jLabel9.setText("Cambio :");

        labelCambio.setForeground(new java.awt.Color(153, 255, 153));
        labelCambio.setText("0.00");

        jLabel11.setText("MXN $");

        btnVentasAgregar1.setText("Ver clientes");
        btnVentasAgregar1.setToolTipText("Agregar");
        btnVentasAgregar1.setBackground_Hover_1(new java.awt.Color(3, 142, 62));
        btnVentasAgregar1.setBackground_Hover_2(new java.awt.Color(5, 111, 44));
        btnVentasAgregar1.setBackground_No_Hover_1(new java.awt.Color(0, 197, 97));
        btnVentasAgregar1.setBackground_No_Hover_2(new java.awt.Color(1, 140, 69));
        btnVentasAgregar1.setColor_NoHover_text(new java.awt.Color(0, 0, 0));
        btnVentasAgregar1.setEsquina_inferior_derecho(15);
        btnVentasAgregar1.setEsquina_inferior_izquierdo(15);
        btnVentasAgregar1.setEsquina_superior_derecho(15);
        btnVentasAgregar1.setEsquina_superior_izquierdo(15);
        btnVentasAgregar1.setFocusPainted(false);
        btnVentasAgregar1.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        btnVentasAgregar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentasAgregar1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Correo");

        labelCorreoCliente.setText("--------");

        javax.swing.GroupLayout panel_Round_JWC1Layout = new javax.swing.GroupLayout(panel_Round_JWC1);
        panel_Round_JWC1.setLayout(panel_Round_JWC1Layout);
        panel_Round_JWC1Layout.setHorizontalGroup(
            panel_Round_JWC1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_Round_JWC1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panel_Round_JWC1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_Round_JWC1Layout.createSequentialGroup()
                        .addGroup(panel_Round_JWC1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(panel_Round_JWC1Layout.createSequentialGroup()
                                .addGroup(panel_Round_JWC1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel_Round_JWC1Layout.createSequentialGroup()
                                        .addGroup(panel_Round_JWC1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(labelTotalPago, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(panel_Round_JWC1Layout.createSequentialGroup()
                                        .addGroup(panel_Round_JWC1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addGroup(panel_Round_JWC1Layout.createSequentialGroup()
                                                .addComponent(labelDineroRecibido, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(79, 79, 79)
                                .addGroup(panel_Round_JWC1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelClienteEscojido, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelCorreoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(53, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_Round_JWC1Layout.createSequentialGroup()
                        .addComponent(labelCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnVentasAgregar1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89))
                    .addGroup(panel_Round_JWC1Layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(29, Short.MAX_VALUE))))
        );
        panel_Round_JWC1Layout.setVerticalGroup(
            panel_Round_JWC1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_Round_JWC1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(panel_Round_JWC1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_Round_JWC1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTotalPago)
                    .addComponent(jLabel3)
                    .addComponent(labelClienteEscojido))
                .addGap(24, 24, 24)
                .addGroup(panel_Round_JWC1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_Round_JWC1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDineroRecibido)
                    .addComponent(jLabel6)
                    .addComponent(labelCorreoCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_Round_JWC1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCambio)
                    .addComponent(jLabel11)
                    .addComponent(btnVentasAgregar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        layared_Round_JWC1.add(panel_Round_JWC1);
        panel_Round_JWC1.setBounds(340, 80, 460, 250);

        jLabel12.setText("Efectivo: ");
        layared_Round_JWC1.add(jLabel12);
        jLabel12.setBounds(60, 130, 120, 16);

        tablaProductosCarrito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Precio", "Marca", "Categoria", "Unidades"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaProductosCarrito.setFont(new java.awt.Font("Product Sans", 0, 14)); // NOI18N
        scrollTablaCarrito.setViewportView(tablaProductosCarrito);

        layared_Round_JWC1.add(scrollTablaCarrito);
        scrollTablaCarrito.setBounds(20, 340, 800, 230);

        panelVentasAgregar.setBackground(new java.awt.Color(0, 0, 0));
        panelVentasAgregar.setEsqInferiorDerecha(15);
        panelVentasAgregar.setEsqInferiorIzquierda(15);
        panelVentasAgregar.setEsqSuperiorDerecha(15);
        panelVentasAgregar.setEsqSuperiorIzquierda(15);

        labelVentasAgregar.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelVentasAgregar.setForeground(new java.awt.Color(255, 255, 255));
        labelVentasAgregar.setText("Quitar");

        btnVentasAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/rectangulo-xmark.png"))); // NOI18N
        btnVentasAgregar.setText("");
        btnVentasAgregar.setToolTipText("Agregar");
        btnVentasAgregar.setBackground_Hover_1(new java.awt.Color(146, 9, 17));
        btnVentasAgregar.setBackground_Hover_2(new java.awt.Color(146, 9, 17));
        btnVentasAgregar.setBackground_No_Hover_1(new java.awt.Color(229, 10, 46));
        btnVentasAgregar.setBackground_No_Hover_2(new java.awt.Color(229, 10, 46));
        btnVentasAgregar.setColor_NoHover_text(new java.awt.Color(0, 0, 0));
        btnVentasAgregar.setEsquina_inferior_derecho(15);
        btnVentasAgregar.setEsquina_inferior_izquierdo(15);
        btnVentasAgregar.setEsquina_superior_derecho(15);
        btnVentasAgregar.setEsquina_superior_izquierdo(15);
        btnVentasAgregar.setFocusPainted(false);
        btnVentasAgregar.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        btnVentasAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentasAgregarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelVentasAgregarLayout = new javax.swing.GroupLayout(panelVentasAgregar);
        panelVentasAgregar.setLayout(panelVentasAgregarLayout);
        panelVentasAgregarLayout.setHorizontalGroup(
            panelVentasAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVentasAgregarLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnVentasAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVentasAgregarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelVentasAgregar)
                .addGap(22, 22, 22))
        );
        panelVentasAgregarLayout.setVerticalGroup(
            panelVentasAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVentasAgregarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelVentasAgregar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnVentasAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        layared_Round_JWC1.add(panelVentasAgregar);
        panelVentasAgregar.setBounds(240, 250, 90, 60);

        panelCarrito.setColor1(new java.awt.Color(1, 26, 13));
        panelCarrito.setColor2(new java.awt.Color(0, 0, 0));
        panelCarrito.setInferior_derecho(30);
        panelCarrito.setInferior_izquierdo(30);
        panelCarrito.setSuperior_derecho(30);
        panelCarrito.setSuperior_izquierdo(30);

        btnCarrito.setForeground(new java.awt.Color(255, 255, 255));
        btnCarrito.setText("Vender");
        btnCarrito.setBackground_Hover_1(new java.awt.Color(2, 214, 120));
        btnCarrito.setBackground_Hover_2(new java.awt.Color(20, 149, 67));
        btnCarrito.setBackground_No_Hover_1(new java.awt.Color(0, 0, 0));
        btnCarrito.setBackground_No_Hover_2(new java.awt.Color(0, 0, 0));
        btnCarrito.setColor_Hover_text(new java.awt.Color(0, 0, 0));
        btnCarrito.setEsquina_inferior_derecho(30);
        btnCarrito.setEsquina_inferior_izquierdo(30);
        btnCarrito.setEsquina_superior_derecho(30);
        btnCarrito.setEsquina_superior_izquierdo(30);
        btnCarrito.setFocusPainted(false);
        btnCarrito.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        btnCarrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCarritoActionPerformed(evt);
            }
        });

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/dolar-de-saco_1.png"))); // NOI18N

        javax.swing.GroupLayout panelCarritoLayout = new javax.swing.GroupLayout(panelCarrito);
        panelCarrito.setLayout(panelCarritoLayout);
        panelCarritoLayout.setHorizontalGroup(
            panelCarritoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCarritoLayout.createSequentialGroup()
                .addComponent(btnCarrito, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        panelCarritoLayout.setVerticalGroup(
            panelCarritoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCarrito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelCarritoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layared_Round_JWC1.add(panelCarrito);
        panelCarrito.setBounds(40, 250, 170, 60);

        getContentPane().add(layared_Round_JWC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 610));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        this.dispose();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }//GEN-LAST:event_salirActionPerformed

    private void salir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salir1ActionPerformed
        this.setExtendedState(ICONIFIED);        // TODO add your handling code here:
    }//GEN-LAST:event_salir1ActionPerformed

    private void btnVentasAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasAgregarActionPerformed
//        if (estaSelccionadaTablaProductos()) {
        removerProductoDelCarrito();
        hayCampoSeleccionadoProducto = false;
//        } else {
//            JOptionPane.showMessageDialog(null, "Elige un producto para quitar del carrito !", "Aviso", JOptionPane.WARNING_MESSAGE);
//
//        }
    }//GEN-LAST:event_btnVentasAgregarActionPerformed

    private void btnCarritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarritoActionPerformed

        if (verificarCamposVacios(textPrecio) && esSoloDigitosPrecio(textPrecio.getText())
                && siAlcanzaDinero() && HayProductos() && hayClienteSeleccionado()) {
            realizarVenta();
            actualizarTablaProductos();

        }


    }//GEN-LAST:event_btnCarritoActionPerformed

    private void btnVentasAgregar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasAgregar1ActionPerformed
        vistaCarritoCliente.setVisible(true);
        vistaCarritoCliente.toFront();

    }//GEN-LAST:event_btnVentasAgregar1ActionPerformed

    private void textPrecioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textPrecioKeyReleased
        try {
            labelDineroRecibido.setText(textPrecio.getText());
            if (textPrecio.getText().trim().isEmpty()) {
                labelDineroRecibido.setText("0.00");
                labelCambio.setText("0.00");
            }

            double resultadoCambio = Double.parseDouble(labelDineroRecibido.getText()) - Double.parseDouble(labelTotalPago.getText());

            labelCambio.setText(String.valueOf(resultadoCambio));
        } catch (NumberFormatException ex) {
        }
    }//GEN-LAST:event_textPrecioKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static btn_efecto01_jwc.btn_efecto_V1_JWC btnCarrito;
    public static btn_efecto01_jwc.btn_efecto_V1_JWC btnVentasAgregar;
    public static btn_efecto01_jwc.btn_efecto_V1_JWC btnVentasAgregar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelCambio;
    public static javax.swing.JLabel labelClienteEscojido;
    public static javax.swing.JLabel labelCorreoCliente;
    private javax.swing.JLabel labelDineroRecibido;
    public static javax.swing.JLabel labelTotalPago;
    private javax.swing.JLabel labelVentasAgregar;
    private swing.Layared_Round_JWC layared_Round_JWC1;
    private grafico_barrav1_jwc.num_max num_max1;
    private panel_degradado_jwc.Panel_Round_Degradado_JWC panelCarrito;
    private swing.Panel_Round_JWC panelVentasAgregar;
    private panel_degradado_jwc.Panel_Round_Degradado_JWC panel_Round_Degradado_JWC4;
    private swing.Panel_Round_JWC panel_Round_JWC1;
    private swing.Panel_Round_JWC panel_Round_JWC11;
    private swing.Panel_Round_JWC panel_Round_JWC5;
    private btn_efecto01_jwc.btn_efecto_V1_JWC salir;
    private btn_efecto01_jwc.btn_efecto_V1_JWC salir1;
    public static javax.swing.JScrollPane scrollTablaCarrito;
    public static tabledark.TableDark tablaProductosCarrito;
    private javax.swing.JFormattedTextField textPrecio;
    // End of variables declaration//GEN-END:variables
}
