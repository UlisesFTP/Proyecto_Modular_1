package vista;

import clases_administradoras.BarberoAdmin;
import clases_administradoras.CitaAdmin;
import clases_administradoras.ClienteAdmin;
import clases_administradoras.ProductoAdmin;
import clases_administradoras.ServicioAdmin;
import conexion.Conexion;
import static inicio.Main.*;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Cita;
import modelo.Cliente;
import modelo.Producto;
import static vista.FormularioProductos.categorias;
import static vista.FormularioProductos.marcas;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextField;
import modelo.Barbero;
import modelo.Servicio;
import tabledark.TableDark;
import static vista.InicioAdministrador.tablaCitasAdmin;
import static vista.InicioAdministrador.tablaClientes;
import static vista.InicioAdministrador.tablaProductos;
import static vista.vistaCarrito.labelTotalPago;
import static vista.vistaCarrito.scrollTablaCarrito;
import static vista.vistaCarrito.tablaProductosCarrito;
import controlador.Correo;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import static vista.InicioAdministrador.tablaCitasAdmin;
import static vista.vistaSeleccionClienteCarrito.tablaClientesCarrito;

/**
 *
 * @author joel_
 */
public class InicioUsuario extends javax.swing.JFrame {

    private ClienteAdmin clienteAdmin;
    private ProductoAdmin productoAdmin;
    private CitaAdmin citaAdmin;
    private BarberoAdmin barberoAdmin;
    private ServicioAdmin servicioAdmin;
    public Conexion CONEXION;
    public static ArrayList<String> barberos;
    public static ArrayList<String> servicios;
    boolean hayCampoSeleccionadoCliente = false;
    boolean hayCampoSeleccionadoProducto = false;
    boolean hayCampoSeleccionadoCita = false;
    public vistaCarrito carrtioView;
    public static ArrayList<Producto> productosCarrito;
    public static double totalPagar = 0;
    private Correo correoConfirmacionCita;

    public InicioUsuario() {
        try {
            inicializarFlatLaf();

            initComponents();

            CONEXION = new Conexion();
            setBackground(new Color(0, 0, 0, 0));
            carrtioView = new vistaCarrito();

            panelRegistrarCliente.setVisible(true);
            panelVentas.setVisible(false);
            panelVerCitas.setVisible(false);
            panelAgendarCita.setVisible(false);

            clienteAdmin = new ClienteAdmin();
            productoAdmin = new ProductoAdmin();
            citaAdmin = new CitaAdmin();
            barberoAdmin = new BarberoAdmin();
            servicioAdmin = new ServicioAdmin();
            generarPlaceHolders();
            generarTablaClientes();
            generarTablaProductos();
            generarTablaCitas();

            leerServiciosComboBox();
            leerBarberosComboBox();

            estaSelccionadaTablaClientes();
            estaSelccionadaTablaProductos();
            correoConfirmacionCita = new Correo();
            productosCarrito = new ArrayList<>();

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(InicioUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void cambiarVisibilidadRegistrarClienteInterfaz(boolean esVisible) {
        panelRegistrarCliente.setVisible(esVisible);

    }

    private void generarPlaceHolders() {

        TextPrompt placeholderBarraNombre = new TextPrompt("Nombre", textNombre);
        TextPrompt placeholderBarraApellido = new TextPrompt("Apellido", textApellido);
        TextPrompt placeholderBarraCorreo = new TextPrompt("Correo", textCorreo);
        TextPrompt placeholderBarraTelefono = new TextPrompt("Telefono", textTelefono);

        placeholderBarraNombre.changeAlpha(0.75f);
        placeholderBarraApellido.changeAlpha(0.75f);
        placeholderBarraCorreo.changeAlpha(0.75f);
        placeholderBarraTelefono.changeAlpha(0.75f);

//        TextPrompt placeholderBarraClientes = new TextPrompt("Buscar Cliente", barraBusquedaAgendarCita);
        TextPrompt placeholderBarraCitas = new TextPrompt("Buscar por cliente", barraBusquedaVerCita);
        TextPrompt placeholderBarraProductos = new TextPrompt("Buscar Producto", barraBusquedaVentas);

//        placeholderBarraClientes.changeAlpha(0.55f);
        placeholderBarraCitas.changeAlpha(0.55f);
        placeholderBarraProductos.changeAlpha(0.55f);

    }

    private void cambiarVisibilidadInterfazAgendarCita(boolean esVisible) {
        panelAgendarCita.setVisible(esVisible);
        panel_IdentificadorAgendarCita.setVisible(esVisible);
        labelAgendarCita.setVisible(esVisible);
        scrollTablaAgendarCita.setVisible(esVisible);
        tablaAgendarCita.setVisible(esVisible);
//        panelBarraBusquedaAgendarCita.setVisible(esVisible);
//        barraBusquedaAgendarCita.setVisible(esVisible);
//        btnBuscarAgendarCita.setVisible(true);
        comboBoxHoras.setVisible(esVisible);
        comboBoxHoras.setVisible(esVisible);
        comboBoxServiciosAgenda.setVisible(esVisible);
        dateChooserAgendarCita.setVisible(esVisible);
        labelDia.setVisible(esVisible);
        labelHora.setVisible(esVisible);
        labelBarbero.setVisible(esVisible);
        labelServicio.setVisible(esVisible);
        labelSelecionaCliente.setVisible(esVisible);
        labelCliente.setVisible(esVisible);
        panelAgendarCitaVistaUsuario.setVisible(esVisible);
        btnAgendar.setVisible(esVisible);
        separador.setVisible(esVisible);
    }

    private void cambiarVisibilidadVerCitaInterfaz(boolean esVisible) {
        panelVerCitas.setVisible(esVisible);
        scrollTablaVerCitas.setVisible(esVisible);
        tablaVerCitas.setVisible(esVisible);
//        btnFiltroProximasReservas.setVisible(esVisible);
//        btnFiltroTodasReservas.setVisible(esVisible);
        panelBarraBusquedaVerCita.setVisible(esVisible);
//        barraBusquedaAgendarCita.setVisible(esVisible);
        btnVerCitaBuscar.setVisible(esVisible);
//        panelVerCitaModificarCita.setVisible(esVisible);
//        labelVerCitaModificar.setVisible(esVisible);
//        btnVerCitaModificar.setVisible(esVisible);
        panelCancelarCita.setVisible(esVisible);
        labelCancelarCita.setVisible(esVisible);
        btnCancelarCita.setVisible(esVisible);

    }

    private void cambiarVisibilidadVentasInterfaz(boolean esVisible) {
        panelVentas.setVisible(esVisible);
        scrollTablaVentas.setVisible(esVisible);
        tablaVentaProductos.setVisible(esVisible);
        panelBarraBusquedaVentas.setVisible(esVisible);
        barraBusquedaVentas.setVisible(esVisible);
        btnBuscarVentas.setVisible(esVisible);
        panelVentasAgregar.setVisible(esVisible);
        labelVentasAgregar.setVisible(esVisible);
        btnVentasAgregar.setVisible(esVisible);
        separadorVentas.setVisible(esVisible);
        labelSeleccionaProducto.setVisible(esVisible);
        labelProducto.setVisible(esVisible);
        panelComboUnidades.setVisible(esVisible);
        spinnerUnidades.setVisible(esVisible);
        labelComboboxUnidades.setVisible(esVisible);
        labelVentasMarca.setVisible(esVisible);
        labelVentasCategoria.setVisible(esVisible);
        comboboxCategoriaVentas.setVisible(esVisible);
        panelComboboxCategoria.setVisible(esVisible);
        panelComboboxCategoriaEfecto.setVisible(esVisible);
        panelCarrito.setVisible(esVisible);
        btnCarrito.setVisible(esVisible);
    }

    //OPERACIONES VISTA VER CITAS=================================================================================================================================================
    private void generarTablaCitas() {

        try {
            citaAdmin.leerCitas();
            tablaVerCitas.fixTable(scrollTablaVerCitas);

            DefaultTableModel DefaultModelCitas = (DefaultTableModel) tablaVerCitas.getModel();

            Cita[] citas = citaAdmin.obtenerLista();
            String estado;

            for (Cita cita : citas) {

                LocalDate fecha = cita.getFecha();
                Time hora = cita.getHora();
                String cliente = cita.getCliente();
                String servicio = cita.getServicio();
                String barbero = cita.getBarbero();

                if (cita.isCumplida() == true) {
                    estado = "Cumplida";
                } else {
                    estado = "No cumplida";
                }

                DefaultModelCitas.addRow(new Object[]{fecha, hora, cliente, servicio, barbero, estado});

            }
        } catch (Exception ex) {

        }
    }

    private boolean estaSelccionadaTablaCitas() {

        tablaVerCitas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent Mouse_evt) {

                tablaVerCitas = (TableDark) Mouse_evt.getSource();
                Point point = Mouse_evt.getPoint();
                int row = tablaVerCitas.rowAtPoint(point);

                if (Mouse_evt.getClickCount() == 1) {

                    hayCampoSeleccionadoCita = true;
                }

            }
        });
        return hayCampoSeleccionadoCita;
    }

    private void removerCita() {

        try {

            citaAdmin.leerCitas();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fecha = LocalDate.parse(tablaVerCitas.getValueAt(tablaVerCitas.getSelectedRow(), 0).toString(), formatter);

            int id = citaAdmin.obtenerID(tablaVerCitas.getValueAt(tablaVerCitas.getSelectedRow(), 2).toString(),
                    fecha,
                    tablaVerCitas.getValueAt(tablaVerCitas.getSelectedRow(), 3).toString(),
                    tablaVerCitas.getValueAt(tablaVerCitas.getSelectedRow(), 4).toString());

            citaAdmin.cancelarCita(id);

            actualizarTablaCitas();

        } catch (SQLException ex) {
            Logger.getLogger(InicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//==========================================================================================================================================================================================
    //OPERACIONES VISTA AGENDAR CITAS=========================================================================================================================================
    private void generarTablaClientes() {
        try {
            clienteAdmin.leerClientes();

            tablaAgendarCita.fixTable(scrollTablaAgendarCita);
            DefaultTableModel DefaultModelClientes = (DefaultTableModel) tablaAgendarCita.getModel();

            Cliente[] clientes = clienteAdmin.obtenerLista();

            for (Cliente cliente : clientes) {
                String nombre = cliente.getNombre();
                String apellido = cliente.getApellido();
                String correo = cliente.getCorreo();
                String telefono = cliente.getTelefono();

                DefaultModelClientes.addRow(new Object[]{nombre, apellido, telefono, correo});
            }

        } catch (SQLException ex) {
            Logger.getLogger(InicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private boolean estaSelccionadaTablaClientes() {

        tablaAgendarCita.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent Mouse_evt) {

                tablaAgendarCita = (TableDark) Mouse_evt.getSource();
                Point point = Mouse_evt.getPoint();
                int row = tablaAgendarCita.rowAtPoint(point);

                if (Mouse_evt.getClickCount() == 1) {

                    hayCampoSeleccionadoCliente = true;
                    labelSelecionaCliente.setText(tablaAgendarCita.getValueAt(tablaAgendarCita.getSelectedRow(), 0).toString()
                            + " " + tablaAgendarCita.getValueAt(tablaAgendarCita.getSelectedRow(), 1).toString());

                }

            }
        });
        return hayCampoSeleccionadoCliente;
    }

    private boolean hayCamposSeleccionados() {

        if (comboBoxBarberosAgendar.getSelectedItem() == null
                || comboBoxServiciosAgenda.getSelectedItem() == null
                || dateChooserAgendarCita.getDate() == null) {

            return false;
        }

        return true;

    }

    private void actualizarTablaClientes() {
        try {
            DefaultTableModel DefaultModelTablaClientesUsuario = (DefaultTableModel) tablaAgendarCita.getModel();
            DefaultModelTablaClientesUsuario.setRowCount(0);
            DefaultTableModel DefaultModelTablaClientesAdmin = (DefaultTableModel) tablaClientes.getModel();
            DefaultModelTablaClientesAdmin.setRowCount(0);
            DefaultTableModel DefaultModelClientes = (DefaultTableModel) tablaClientesCarrito.getModel();
            DefaultModelClientes.setRowCount(0);

            clienteAdmin.leerClientes();
            Cliente[] clientes = clienteAdmin.obtenerLista();
            for (Cliente cliente : clientes) {

                String nombre = cliente.getNombre();
                String apellido = cliente.getApellido();
                String telefono = cliente.getTelefono();
                String correo = cliente.getCorreo();

                DefaultModelTablaClientesUsuario.addRow(new Object[]{nombre, apellido, telefono, correo});
                DefaultModelTablaClientesAdmin.addRow(new Object[]{nombre, apellido, telefono, correo});
                DefaultModelClientes.addRow(new Object[]{nombre, apellido, telefono, correo});

            }
        } catch (SQLException ex) {
            Logger.getLogger(FormularioEditarEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizarTablaCitas() {
        try {
            DefaultTableModel DefaultModelTablaVerCitas = (DefaultTableModel) tablaVerCitas.getModel();
            DefaultModelTablaVerCitas.setRowCount(0);

            DefaultTableModel DefaultModelTablaVerCitasAdmin = (DefaultTableModel) tablaCitasAdmin.getModel();
            DefaultModelTablaVerCitasAdmin.setRowCount(0);

            citaAdmin.leerCitas();
            Cita[] citas = citaAdmin.obtenerLista();
            for (Cita cita : citas) {

                LocalDate fecha = cita.getFecha();
                Time hora = cita.getHora();
                String cliente = cita.getCliente();
                String servicio = cita.getServicio();
                String barbero = cita.getBarbero();
                boolean cumplida = cita.isCumplida();

                DefaultModelTablaVerCitas.addRow(new Object[]{fecha, hora, cliente, servicio, barbero, cumplida});
                DefaultModelTablaVerCitasAdmin.addRow(new Object[]{fecha, hora, cliente, servicio, barbero, cumplida});

            }
        } catch (SQLException ex) {
            Logger.getLogger(FormularioEditarEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void leerBarberosComboBox() throws SQLException {
        barberoAdmin.leerBarberos();
        for (Barbero barbero : barberoAdmin.obtenerLista()) {

            if (barbero.isDisponible()) {

                comboBoxBarberosAgendar.addItem(barbero.getNombre());
            }

        }

    }

    private void leerServiciosComboBox() throws SQLException {
        servicioAdmin.leerServicios();
        for (Servicio servicio : servicioAdmin.obtenerLista()) {
            comboBoxServiciosAgenda.addItem(servicio.getNombre());
        }

    }

    private void enviarPDFConfrimacionCita(String correo) {

        try {

            // Especifica la ruta completa donde deseas guardar el PDF
            String outputPath = "src/PDFcitas/textoCita.pdf";

            Document document = new Document(PageSize.A4);

            // step 2: creation of the writer
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream(outputPath));

            // step 3: we open the document
            String nombreCliente = tablaAgendarCita.getValueAt(tablaAgendarCita.getSelectedRow(), 0).toString()
                    + " " + tablaAgendarCita.getValueAt(tablaAgendarCita.getSelectedRow(), 1).toString();
            String nombreBarbero = comboBoxBarberosAgendar.getSelectedItem().toString();
            String servicio = comboBoxServiciosAgenda.getSelectedItem().toString();
            String fecha = dateChooserAgendarCita.getDate().toString(); // Formato: "yyyy-MM-dd HH:mm:ss"
            String hora = comboBoxHoras.getSelectedItem().toString();
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
                Paragraph subtitle = new Paragraph("Confirmación de Cita", fontTitle);
                subtitle.setAlignment(Element.ALIGN_CENTER);
                subtitle.setFont(fontTitle);
                document.add(subtitle);

                document.add(new Paragraph("\t", font));
                document.add(new Paragraph("\t", font));

                document.add(new Paragraph("Cliente: " + nombreCliente, font));
                document.add(new Paragraph("Barbero: " + nombreBarbero, font));
                document.add(new Paragraph("Servicio: " + servicio, font));
                document.add(new Paragraph("Fecha: " + fecha, font));
                document.add(new Paragraph("Hora: " + hora, font));

                document.add(new Paragraph("\t", font));
                document.add(new Paragraph("\t", font));
                document.add(new Paragraph("\t", font));

                document.add(new Phrase("Procura llegar a tiempo para evitar cualquier inconveniente , "
                        + "puede posponer su cita si es necesario de lo contrario sera cancelada.", cuerpo));
                document.close();
                JOptionPane.showMessageDialog(null, "Enviando documento espere ...", "", JOptionPane.PLAIN_MESSAGE);
                correoConfirmacionCita.createEmail(correo, "Cita confirmada", " ¡ Hola " + nombreCliente + " ! parece que has agendado una cita abajo hay un documento con los datos de la cita", outputPath);
                correoConfirmacionCita.sendEmail();
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

    private void actualizarTablaVerCitasConBusqueda(String textoBusqueda) {
        try {
            ArrayList<Cita> citasFiltradas = citaAdmin.buscarCitasPorCliente(textoBusqueda);

            DefaultTableModel modelo = (DefaultTableModel) tablaVerCitas.getModel();
            modelo.setRowCount(0);

            for (Cita cita : citasFiltradas) {
                String estado = cita.isCumplida() ? "Cumplida" : "No cumplida";
                modelo.addRow(new Object[]{cita.getFecha(), cita.getHora(), cita.getCliente(), cita.getServicio(), cita.getBarbero(), estado});
            }
        } catch (SQLException ex) {
            Logger.getLogger(InicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizarTablaAgendarCitaConBusqueda(String textoBusqueda) {
        try {
            ArrayList<Cliente> clientesFiltrados = clienteAdmin.buscarClientes(textoBusqueda); // Asumiendo que este método existe

            DefaultTableModel modelo = (DefaultTableModel) tablaAgendarCita.getModel();
            modelo.setRowCount(0);

            for (Cliente cliente : clientesFiltrados) {
                modelo.addRow(new Object[]{cliente.getNombre(), cliente.getApellido(), cliente.getTelefono(), cliente.getCorreo()});
            }
        } catch (SQLException ex) {
            Logger.getLogger(InicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizarTablaVentaProductosConBusqueda(String textoBusqueda) {
        try {
            ArrayList<Producto> productosFiltrados = productoAdmin.buscarProductos(textoBusqueda);

            DefaultTableModel modelo = (DefaultTableModel) tablaVentaProductos.getModel();
            modelo.setRowCount(0);

            for (Producto producto : productosFiltrados) {
                modelo.addRow(new Object[]{producto.getNombre(), producto.getPrecio(), producto.getMarca(), producto.getCategoria(), producto.getUnidades()});
            }
        } catch (SQLException ex) {
            Logger.getLogger(InicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//==========================================================================================================================================================================
//OPERACIONES VISTA REGISTRO CLIENTES =======================================================================================================================================
    private boolean verificarCamposVacios(JTextField... campos) {

        for (JTextField campo : campos) {

            if (campo.getText().trim().isBlank()) {
                JOptionPane.showMessageDialog(null, "Hay campos vacios !", "Aviso", JOptionPane.WARNING_MESSAGE);
                return false;

            }

        }

        return true;

    }

    private boolean validarCorreo(String correo) {

        String patron = "^[A-Za-z0-9+_.-]+@(.+)$";

        Pattern pattern = Pattern.compile(patron);

        Matcher matcher = pattern.matcher(correo);

        if (!matcher.matches()) {
            JOptionPane.showMessageDialog(null, "Ingresa un correo valido", "Aviso", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void limpiarCampos(JTextField... campos) {

        for (JTextField campo : campos) {

            campo.setText("");

        }

    }

    private boolean esSoloLetras(String... campos) {

        for (String campo : campos) {

            if (!campo.matches("^[a-zA-Z]*$")) {
                JOptionPane.showMessageDialog(null, "Ingresa datos validos en el nombre y apellido", "ERROR", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        return true;

    }

//============================================================================================================================================================================
//OPERACIONES VISTA VENTAS=======================================================================================================================================================
    private void generarTablaProductos() {

        try {
            productoAdmin.leerProductos();
            tablaVentaProductos.fixTable(scrollTablaVentas);
            DefaultTableModel mode2 = (DefaultTableModel) tablaVentaProductos.getModel();
            Producto[] productos = productoAdmin.obtenerLista();
            llenarComboBoxProductos();

            for (Producto producto : productos) {
                String nombre = producto.getNombre();
                double precio = producto.getPrecio();
                String marca = producto.getMarca();
                String categoria = producto.getCategoria();
                int unidades = producto.getUnidades();

                mode2.addRow(new Object[]{nombre, precio, marca, categoria, unidades});

            }

        } catch (SQLException ex) {
            Logger.getLogger(InicioAdministrador.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

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
            Logger.getLogger(FormularioEditarEmpleado.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void llenarComboBoxProductos() {
        for (String marca : marcas) {
            comboboxMarcaVentas.addItem(marca);
        }

        for (String categoria : categorias) {
            comboboxCategoriaVentas.addItem(categoria);
        }

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
                    labelSeleccionaProducto.setText(tablaVentaProductos.getValueAt(tablaVentaProductos.getSelectedRow(), 0).toString());

                }

            }
        });
        return hayCampoSeleccionadoProducto;
    }

    private void almacenarProductoEnCarrito() {

        String nombre = tablaVentaProductos.getValueAt(tablaVentaProductos.getSelectedRow(), 0).toString();
        double precio = Double.parseDouble(tablaVentaProductos.getValueAt(tablaVentaProductos.getSelectedRow(), 1).toString());
        String marca = tablaVentaProductos.getValueAt(tablaVentaProductos.getSelectedRow(), 2).toString();
        String categoria = tablaVentaProductos.getValueAt(tablaVentaProductos.getSelectedRow(), 3).toString();
        int unidades = Integer.parseInt(spinnerUnidades.getValue().toString());

        Producto producto = new Producto(nombre, precio, marca, categoria, unidades);
        totalPagar += precio * unidades;
        System.out.println(totalPagar);
        labelTotalPago.setText(String.valueOf(totalPagar));

        productosCarrito.add(producto);

    }

    private void aplicarFiltroCombinadoParaVentas() {
        try {
            String marcaSeleccionada = (String) comboboxMarcaVentas.getSelectedItem();
            String categoriaSeleccionada = (String) comboboxCategoriaVentas.getSelectedItem();
            ArrayList<Producto> productosFiltrados = productoAdmin.filtrarProductos(marcaSeleccionada, categoriaSeleccionada);

            DefaultTableModel modelo = (DefaultTableModel) tablaVentaProductos.getModel();
            modelo.setRowCount(0);

            for (Producto producto : productosFiltrados) {
                modelo.addRow(new Object[]{producto.getNombre(), producto.getPrecio(), producto.getMarca(), producto.getCategoria(), producto.getUnidades()});
            }
        } catch (SQLException ex) {
            Logger.getLogger(InicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//================================================================================================================================================================================
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        layared_Round_JWC1 = new swing.Layared_Round_JWC();
        btnMinimizar = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        btnSalir = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        panelMenu = new swing.Panel_Round_JWC();
        panelInfoUsuario = new swing.Panel_Round_JWC();
        labelUsuario = new javax.swing.JLabel();
        labelMostrarInfoUsuario = new javax.swing.JLabel();
        btnRegistrarCliente = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        iconoRegistrarCliente = new javax.swing.JLabel();
        btnAgendarCitas = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        iconoAgendarCita = new javax.swing.JLabel();
        btnVerCitas = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        iconoVerCitas = new javax.swing.JLabel();
        btnVentas = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        iconoVentas = new javax.swing.JLabel();
        btnCerrarSesion = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        iconoCerrarSesion = new javax.swing.JLabel();
        panelMenuEfecto = new javax.swing.JPanel();
        panelRegistrarCliente = new javax.swing.JPanel();
        panel_Round_JWC4 = new swing.Panel_Round_JWC();
        textNombre = new javax.swing.JTextField();
        panel_Round_Degradado_JWC4 = new panel_degradado_jwc.Panel_Round_Degradado_JWC();
        jLabel23 = new javax.swing.JLabel();
        panel_Round_JWC6 = new swing.Panel_Round_JWC();
        textApellido = new javax.swing.JTextField();
        panel_Round_JWC5 = new swing.Panel_Round_JWC();
        textCorreo = new javax.swing.JTextField();
        panel_Round_JWC3 = new swing.Panel_Round_JWC();
        textTelefono = new javax.swing.JFormattedTextField();
        panel_Round_Degradado_JWC1 = new panel_degradado_jwc.Panel_Round_Degradado_JWC();
        btnGuardar = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        jLabel1 = new javax.swing.JLabel();
        panel_Round_JWC9 = new swing.Panel_Round_JWC();
        panel_Round_JWC10 = new swing.Panel_Round_JWC();
        panel_Round_JWC11 = new swing.Panel_Round_JWC();
        panel_Round_JWC12 = new swing.Panel_Round_JWC();
        jLabel2 = new javax.swing.JLabel();
        panelAgendarCita = new javax.swing.JPanel();
        panel_IdentificadorAgendarCita = new panel_degradado_jwc.Panel_Round_Degradado_JWC();
        labelAgendarCita = new javax.swing.JLabel();
        scrollTablaAgendarCita = new javax.swing.JScrollPane();
        tablaAgendarCita = new tabledark.TableDark();
        comboBoxHoras = new javax.swing.JComboBox<>();
        comboBoxBarberosAgendar = new javax.swing.JComboBox<>();
        comboBoxServiciosAgenda = new javax.swing.JComboBox<>();
        dateChooserAgendarCita = new com.toedter.calendar.JDateChooser();
        labelDia = new javax.swing.JLabel();
        labelHora = new javax.swing.JLabel();
        labelBarbero = new javax.swing.JLabel();
        labelServicio = new javax.swing.JLabel();
        labelSelecionaCliente = new javax.swing.JLabel();
        labelCliente = new javax.swing.JLabel();
        panelAgendarCitaVistaUsuario = new panel_degradado_jwc.Panel_Round_Degradado_JWC();
        btnAgendar = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        jLabel5 = new javax.swing.JLabel();
        separador = new javax.swing.JSeparator();
        panelVerCitas = new javax.swing.JPanel();
        scrollTablaVerCitas = new javax.swing.JScrollPane();
        tablaVerCitas = new tabledark.TableDark();
        panelBarraBusquedaVerCita = new swing.Panel_Round_JWC();
        barraBusquedaVerCita = new javax.swing.JTextField();
        btnVerCitaBuscar = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        panelCancelarCita = new swing.Panel_Round_JWC();
        labelCancelarCita = new javax.swing.JLabel();
        btnCancelarCita = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        panelVentas = new javax.swing.JPanel();
        scrollTablaVentas = new javax.swing.JScrollPane();
        tablaVentaProductos = new tabledark.TableDark();
        panelBarraBusquedaVentas = new swing.Panel_Round_JWC();
        barraBusquedaVentas = new javax.swing.JTextField();
        btnBuscarVentas = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        panelVentasAgregar = new swing.Panel_Round_JWC();
        labelVentasAgregar = new javax.swing.JLabel();
        btnVentasAgregar = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        separadorVentas = new javax.swing.JSeparator();
        labelSeleccionaProducto = new javax.swing.JLabel();
        labelProducto = new javax.swing.JLabel();
        panelComboUnidades = new swing.Panel_Round_JWC();
        spinnerUnidades = new javax.swing.JSpinner();
        labelComboboxUnidades = new javax.swing.JLabel();
        labelVentasMarca = new javax.swing.JLabel();
        labelVentasCategoria = new javax.swing.JLabel();
        comboboxCategoriaVentas = new javax.swing.JComboBox<>();
        panelComboboxCategoria = new swing.Panel_Round_JWC();
        panelComboboxCategoriaEfecto = new swing.Panel_Round_JWC();
        comboboxMarcaVentas = new javax.swing.JComboBox<>();
        panelComboboxMarca = new swing.Panel_Round_JWC();
        panelComboboxMarcaEfecto = new swing.Panel_Round_JWC();
        panelCarrito = new panel_degradado_jwc.Panel_Round_Degradado_JWC();
        btnCarrito = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        layared_Round_JWC1.setBackground(new java.awt.Color(51, 51, 51));
        layared_Round_JWC1.setForeground(new java.awt.Color(204, 204, 204));
        layared_Round_JWC1.setEsqInferiorDerecha(50);
        layared_Round_JWC1.setEsqInferiorIzquierda(50);
        layared_Round_JWC1.setEsqSuperiorDerecha(50);
        layared_Round_JWC1.setEsqSuperiorIzquierda(50);

        btnMinimizar.setText("");
        btnMinimizar.setBackground_Hover_1(new java.awt.Color(255, 204, 153));
        btnMinimizar.setBackground_Hover_2(new java.awt.Color(255, 153, 0));
        btnMinimizar.setBackground_No_Hover_1(new java.awt.Color(102, 51, 0));
        btnMinimizar.setBackground_No_Hover_2(new java.awt.Color(255, 153, 0));
        btnMinimizar.setEsquina_inferior_derecho(20);
        btnMinimizar.setEsquina_inferior_izquierdo(20);
        btnMinimizar.setEsquina_superior_derecho(20);
        btnMinimizar.setEsquina_superior_izquierdo(20);
        btnMinimizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinimizarActionPerformed(evt);
            }
        });
        layared_Round_JWC1.add(btnMinimizar);
        btnMinimizar.setBounds(1030, 10, 20, 20);

        btnSalir.setText("");
        btnSalir.setBackground_Hover_1(new java.awt.Color(255, 153, 153));
        btnSalir.setBackground_Hover_2(new java.awt.Color(255, 0, 0));
        btnSalir.setBackground_No_Hover_1(new java.awt.Color(153, 0, 51));
        btnSalir.setBackground_No_Hover_2(new java.awt.Color(255, 102, 102));
        btnSalir.setEsquina_inferior_derecho(20);
        btnSalir.setEsquina_inferior_izquierdo(20);
        btnSalir.setEsquina_superior_derecho(20);
        btnSalir.setEsquina_superior_izquierdo(20);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        layared_Round_JWC1.add(btnSalir);
        btnSalir.setBounds(1060, 10, 20, 20);

        panelMenu.setBackground(new java.awt.Color(27, 27, 27));
        panelMenu.setEsqInferiorIzquierda(50);
        panelMenu.setEsqSuperiorIzquierda(50);
        panelMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelInfoUsuario.setBackground(new java.awt.Color(56, 56, 56));
        panelInfoUsuario.setEsqInferiorDerecha(20);
        panelInfoUsuario.setEsqInferiorIzquierda(20);
        panelInfoUsuario.setEsqSuperiorDerecha(20);
        panelInfoUsuario.setEsqSuperiorIzquierda(20);
        panelInfoUsuario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelUsuario.setFont(new java.awt.Font("JetBrainsMono NF", 0, 12)); // NOI18N
        labelUsuario.setForeground(new java.awt.Color(255, 255, 255));
        labelUsuario.setText("Usuario:");
        panelInfoUsuario.add(labelUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        labelMostrarInfoUsuario.setForeground(new java.awt.Color(204, 204, 204));
        labelMostrarInfoUsuario.setText("...");
        panelInfoUsuario.add(labelMostrarInfoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 130, -1));

        panelMenu.add(panelInfoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 150, 70));

        btnRegistrarCliente.setBackground(new java.awt.Color(27, 27, 27));
        btnRegistrarCliente.setForeground(new java.awt.Color(204, 204, 204));
        btnRegistrarCliente.setText("Registar Cliente");
        btnRegistrarCliente.setToolTipText("");
        btnRegistrarCliente.setAlignmentX(0.5F);
        btnRegistrarCliente.setBackground_Hover_1(new java.awt.Color(2, 214, 120));
        btnRegistrarCliente.setBackground_Hover_2(new java.awt.Color(51, 51, 51));
        btnRegistrarCliente.setBackground_No_Hover_1(new java.awt.Color(27, 27, 27));
        btnRegistrarCliente.setBackground_No_Hover_2(new java.awt.Color(27, 27, 27));
        btnRegistrarCliente.setBorderPainted(false);
        btnRegistrarCliente.setBtn_abajo(btnAgendarCitas);
        btnRegistrarCliente.setBtn_arriba_abajo_esquina_ovalado(60);
        btnRegistrarCliente.setColor_Hover_text(new java.awt.Color(0, 0, 0));
        btnRegistrarCliente.setColor_NoHover_text(new java.awt.Color(204, 204, 204));
        btnRegistrarCliente.setDefaultCapable(false);
        btnRegistrarCliente.setEsquina_inferior_izquierdo(40);
        btnRegistrarCliente.setEsquina_superior_izquierdo(40);
        btnRegistrarCliente.setFocusPainted(false);
        btnRegistrarCliente.setFont(new java.awt.Font("Product Sans", 1, 14)); // NOI18N
        btnRegistrarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarClienteActionPerformed(evt);
            }
        });
        panelMenu.add(btnRegistrarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 160, 50));

        iconoRegistrarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/agregar-cliente.png"))); // NOI18N
        panelMenu.add(iconoRegistrarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 50, 40));

        btnAgendarCitas.setForeground(new java.awt.Color(204, 204, 204));
        btnAgendarCitas.setText("Agendar citas");
        btnAgendarCitas.setAlignmentX(1.0F);
        btnAgendarCitas.setAlignmentY(1.0F);
        btnAgendarCitas.setBackground_Hover_1(new java.awt.Color(2, 214, 120));
        btnAgendarCitas.setBackground_Hover_2(new java.awt.Color(51, 51, 51));
        btnAgendarCitas.setBackground_No_Hover_1(new java.awt.Color(27, 27, 27));
        btnAgendarCitas.setBackground_No_Hover_2(new java.awt.Color(27, 27, 27));
        btnAgendarCitas.setBtn_abajo(btnVerCitas);
        btnAgendarCitas.setBtn_arriba(btnRegistrarCliente);
        btnAgendarCitas.setBtn_arriba_abajo_esquina_ovalado(60);
        btnAgendarCitas.setColor_Hover_text(new java.awt.Color(0, 0, 0));
        btnAgendarCitas.setColor_NoHover_text(new java.awt.Color(204, 204, 204));
        btnAgendarCitas.setEsquina_inferior_izquierdo(40);
        btnAgendarCitas.setEsquina_superior_izquierdo(40);
        btnAgendarCitas.setFocusPainted(false);
        btnAgendarCitas.setFont(new java.awt.Font("Product Sans", 1, 14)); // NOI18N
        btnAgendarCitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgendarCitasActionPerformed(evt);
            }
        });
        panelMenu.add(btnAgendarCitas, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 160, 50));

        iconoAgendarCita.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/reservar-usuario.png"))); // NOI18N
        panelMenu.add(iconoAgendarCita, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 50, 40));

        btnVerCitas.setForeground(new java.awt.Color(204, 204, 204));
        btnVerCitas.setText("Ver Citas");
        btnVerCitas.setBackground_Hover_1(new java.awt.Color(2, 214, 120));
        btnVerCitas.setBackground_Hover_2(new java.awt.Color(51, 51, 51));
        btnVerCitas.setBackground_No_Hover_1(new java.awt.Color(27, 27, 27));
        btnVerCitas.setBackground_No_Hover_2(new java.awt.Color(27, 27, 27));
        btnVerCitas.setBtn_abajo(btnVentas);
        btnVerCitas.setBtn_arriba(btnAgendarCitas);
        btnVerCitas.setBtn_arriba_abajo_esquina_ovalado(60);
        btnVerCitas.setColor_Hover_text(new java.awt.Color(0, 0, 0));
        btnVerCitas.setColor_NoHover_text(new java.awt.Color(204, 204, 204));
        btnVerCitas.setEsquina_inferior_izquierdo(40);
        btnVerCitas.setEsquina_superior_izquierdo(40);
        btnVerCitas.setFocusPainted(false);
        btnVerCitas.setFont(new java.awt.Font("Product Sans", 1, 14)); // NOI18N
        btnVerCitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerCitasActionPerformed(evt);
            }
        });
        panelMenu.add(btnVerCitas, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, 160, 50));

        iconoVerCitas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/calendario.png"))); // NOI18N
        panelMenu.add(iconoVerCitas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 50, 40));

        btnVentas.setForeground(new java.awt.Color(204, 204, 204));
        btnVentas.setText("Ventas");
        btnVentas.setBackground_Hover_1(new java.awt.Color(2, 214, 120));
        btnVentas.setBackground_Hover_2(new java.awt.Color(51, 51, 51));
        btnVentas.setBackground_No_Hover_1(new java.awt.Color(27, 27, 27));
        btnVentas.setBackground_No_Hover_2(new java.awt.Color(27, 27, 27));
        btnVentas.setBtn_abajo(btnCerrarSesion);
        btnVentas.setBtn_arriba(btnVerCitas);
        btnVentas.setBtn_arriba_abajo_esquina_ovalado(60);
        btnVentas.setColor_Hover_text(new java.awt.Color(0, 0, 0));
        btnVentas.setColor_NoHover_text(new java.awt.Color(204, 204, 204));
        btnVentas.setEsquina_inferior_izquierdo(40);
        btnVentas.setEsquina_superior_izquierdo(40);
        btnVentas.setFocusPainted(false);
        btnVentas.setFont(new java.awt.Font("Product Sans", 1, 14)); // NOI18N
        btnVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentasActionPerformed(evt);
            }
        });
        panelMenu.add(btnVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, 160, 50));

        iconoVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/carrito-de-compras.png"))); // NOI18N
        panelMenu.add(iconoVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 50, 40));

        btnCerrarSesion.setForeground(new java.awt.Color(204, 204, 204));
        btnCerrarSesion.setText("Cerrar Sesion");
        btnCerrarSesion.setBackground_Hover_1(new java.awt.Color(204, 0, 51));
        btnCerrarSesion.setBackground_Hover_2(new java.awt.Color(51, 51, 51));
        btnCerrarSesion.setBackground_No_Hover_1(new java.awt.Color(27, 27, 27));
        btnCerrarSesion.setBackground_No_Hover_2(new java.awt.Color(27, 27, 27));
        btnCerrarSesion.setBtn_arriba(btnVentas);
        btnCerrarSesion.setBtn_arriba_abajo_esquina_ovalado(60);
        btnCerrarSesion.setColor_Hover_text(new java.awt.Color(0, 0, 0));
        btnCerrarSesion.setColor_NoHover_text(new java.awt.Color(204, 204, 204));
        btnCerrarSesion.setEsquina_inferior_izquierdo(40);
        btnCerrarSesion.setEsquina_superior_izquierdo(40);
        btnCerrarSesion.setFocusPainted(false);
        btnCerrarSesion.setFont(new java.awt.Font("Product Sans", 1, 14)); // NOI18N
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });
        panelMenu.add(btnCerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 420, 160, 50));

        iconoCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/alt-de-inicio-de-sesion.png"))); // NOI18N
        panelMenu.add(iconoCerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 50, 30));

        panelMenuEfecto.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout panelMenuEfectoLayout = new javax.swing.GroupLayout(panelMenuEfecto);
        panelMenuEfecto.setLayout(panelMenuEfectoLayout);
        panelMenuEfectoLayout.setHorizontalGroup(
            panelMenuEfectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        panelMenuEfectoLayout.setVerticalGroup(
            panelMenuEfectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );

        panelMenu.add(panelMenuEfecto, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, 30, -1));

        layared_Round_JWC1.add(panelMenu);
        panelMenu.setBounds(0, 10, 210, 640);

        panelRegistrarCliente.setBackground(new java.awt.Color(51, 51, 51));
        panelRegistrarCliente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        panelRegistrarCliente.add(panel_Round_JWC4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, -1));

        panel_Round_Degradado_JWC4.setColor1(new java.awt.Color(148, 213, 193));
        panel_Round_Degradado_JWC4.setColor2(new java.awt.Color(34, 227, 117));
        panel_Round_Degradado_JWC4.setInferior_derecho(60);
        panel_Round_Degradado_JWC4.setSuperior_derecho(60);

        jLabel23.setFont(new java.awt.Font("Product Sans", 1, 24)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Registar Cliente");

        javax.swing.GroupLayout panel_Round_Degradado_JWC4Layout = new javax.swing.GroupLayout(panel_Round_Degradado_JWC4);
        panel_Round_Degradado_JWC4.setLayout(panel_Round_Degradado_JWC4Layout);
        panel_Round_Degradado_JWC4Layout.setHorizontalGroup(
            panel_Round_Degradado_JWC4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_Round_Degradado_JWC4Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );
        panel_Round_Degradado_JWC4Layout.setVerticalGroup(
            panel_Round_Degradado_JWC4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_Round_Degradado_JWC4Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        panelRegistrarCliente.add(panel_Round_Degradado_JWC4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 70));

        panel_Round_JWC6.setBackground(new java.awt.Color(0, 0, 0));
        panel_Round_JWC6.setEsqInferiorDerecha(30);
        panel_Round_JWC6.setEsqInferiorIzquierda(30);
        panel_Round_JWC6.setEsqSuperiorDerecha(30);
        panel_Round_JWC6.setEsqSuperiorIzquierda(30);

        textApellido.setBackground(new java.awt.Color(0, 0, 0));
        textApellido.setFont(new java.awt.Font("JetBrainsMono NF", 0, 13)); // NOI18N
        textApellido.setForeground(new java.awt.Color(255, 255, 255));
        textApellido.setBorder(null);
        textApellido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textApellidoMouseClicked(evt);
            }
        });
        textApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textApellidoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_Round_JWC6Layout = new javax.swing.GroupLayout(panel_Round_JWC6);
        panel_Round_JWC6.setLayout(panel_Round_JWC6Layout);
        panel_Round_JWC6Layout.setHorizontalGroup(
            panel_Round_JWC6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_Round_JWC6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(textApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );
        panel_Round_JWC6Layout.setVerticalGroup(
            panel_Round_JWC6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_Round_JWC6Layout.createSequentialGroup()
                .addComponent(textApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panelRegistrarCliente.add(panel_Round_JWC6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, -1, -1));

        panel_Round_JWC5.setBackground(new java.awt.Color(0, 0, 0));
        panel_Round_JWC5.setEsqInferiorDerecha(30);
        panel_Round_JWC5.setEsqInferiorIzquierda(30);
        panel_Round_JWC5.setEsqSuperiorDerecha(30);
        panel_Round_JWC5.setEsqSuperiorIzquierda(30);

        textCorreo.setBackground(new java.awt.Color(0, 0, 0));
        textCorreo.setFont(new java.awt.Font("JetBrainsMono NF", 0, 13)); // NOI18N
        textCorreo.setForeground(new java.awt.Color(255, 255, 255));
        textCorreo.setBorder(null);
        textCorreo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textCorreoMouseClicked(evt);
            }
        });
        textCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textCorreoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_Round_JWC5Layout = new javax.swing.GroupLayout(panel_Round_JWC5);
        panel_Round_JWC5.setLayout(panel_Round_JWC5Layout);
        panel_Round_JWC5Layout.setHorizontalGroup(
            panel_Round_JWC5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_Round_JWC5Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(textCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        panel_Round_JWC5Layout.setVerticalGroup(
            panel_Round_JWC5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_Round_JWC5Layout.createSequentialGroup()
                .addComponent(textCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panelRegistrarCliente.add(panel_Round_JWC5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 190, -1, -1));

        panel_Round_JWC3.setBackground(new java.awt.Color(0, 0, 0));
        panel_Round_JWC3.setEsqInferiorDerecha(30);
        panel_Round_JWC3.setEsqInferiorIzquierda(30);
        panel_Round_JWC3.setEsqSuperiorDerecha(30);
        panel_Round_JWC3.setEsqSuperiorIzquierda(30);

        textTelefono.setBackground(new java.awt.Color(0, 0, 0));
        textTelefono.setBorder(null);
        textTelefono.setForeground(new java.awt.Color(255, 255, 255));
        try {
            textTelefono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        textTelefono.setFont(new java.awt.Font("JetBrainsMono NF", 0, 13)); // NOI18N

        javax.swing.GroupLayout panel_Round_JWC3Layout = new javax.swing.GroupLayout(panel_Round_JWC3);
        panel_Round_JWC3.setLayout(panel_Round_JWC3Layout);
        panel_Round_JWC3Layout.setHorizontalGroup(
            panel_Round_JWC3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_Round_JWC3Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(textTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        panel_Round_JWC3Layout.setVerticalGroup(
            panel_Round_JWC3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(textTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        panelRegistrarCliente.add(panel_Round_JWC3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 280, -1, -1));

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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/disco.png"))); // NOI18N

        javax.swing.GroupLayout panel_Round_Degradado_JWC1Layout = new javax.swing.GroupLayout(panel_Round_Degradado_JWC1);
        panel_Round_Degradado_JWC1.setLayout(panel_Round_Degradado_JWC1Layout);
        panel_Round_Degradado_JWC1Layout.setHorizontalGroup(
            panel_Round_Degradado_JWC1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_Round_Degradado_JWC1Layout.createSequentialGroup()
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        panel_Round_Degradado_JWC1Layout.setVerticalGroup(
            panel_Round_Degradado_JWC1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_Round_Degradado_JWC1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        panelRegistrarCliente.add(panel_Round_Degradado_JWC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, 160, 60));

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

        panelRegistrarCliente.add(panel_Round_JWC9, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 280, -1, -1));

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

        panelRegistrarCliente.add(panel_Round_JWC10, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 190, -1, -1));

        panel_Round_JWC11.setBackground(new java.awt.Color(0, 171, 84));
        panel_Round_JWC11.setEsqInferiorDerecha(30);
        panel_Round_JWC11.setEsqInferiorIzquierda(30);
        panel_Round_JWC11.setEsqSuperiorDerecha(30);
        panel_Round_JWC11.setEsqSuperiorIzquierda(30);

        javax.swing.GroupLayout panel_Round_JWC11Layout = new javax.swing.GroupLayout(panel_Round_JWC11);
        panel_Round_JWC11.setLayout(panel_Round_JWC11Layout);
        panel_Round_JWC11Layout.setHorizontalGroup(
            panel_Round_JWC11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        panel_Round_JWC11Layout.setVerticalGroup(
            panel_Round_JWC11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        panelRegistrarCliente.add(panel_Round_JWC11, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 280, -1, -1));

        panel_Round_JWC12.setBackground(new java.awt.Color(0, 171, 84));
        panel_Round_JWC12.setEsqInferiorDerecha(30);
        panel_Round_JWC12.setEsqInferiorIzquierda(30);
        panel_Round_JWC12.setEsqSuperiorDerecha(30);
        panel_Round_JWC12.setEsqSuperiorIzquierda(30);

        javax.swing.GroupLayout panel_Round_JWC12Layout = new javax.swing.GroupLayout(panel_Round_JWC12);
        panel_Round_JWC12.setLayout(panel_Round_JWC12Layout);
        panel_Round_JWC12Layout.setHorizontalGroup(
            panel_Round_JWC12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        panel_Round_JWC12Layout.setVerticalGroup(
            panel_Round_JWC12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        panelRegistrarCliente.add(panel_Round_JWC12, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 190, -1, -1));

        jLabel2.setText("Telefono");
        panelRegistrarCliente.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 250, 90, -1));

        layared_Round_JWC1.add(panelRegistrarCliente);
        panelRegistrarCliente.setBounds(210, 10, 860, 620);

        panelAgendarCita.setBackground(new java.awt.Color(51, 51, 51));
        panelAgendarCita.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_IdentificadorAgendarCita.setColor1(new java.awt.Color(148, 213, 193));
        panel_IdentificadorAgendarCita.setColor2(new java.awt.Color(34, 227, 117));
        panel_IdentificadorAgendarCita.setInferior_derecho(60);
        panel_IdentificadorAgendarCita.setSuperior_derecho(60);

        labelAgendarCita.setFont(new java.awt.Font("Product Sans", 1, 26)); // NOI18N
        labelAgendarCita.setForeground(new java.awt.Color(0, 0, 0));
        labelAgendarCita.setText("Agendar Cita");

        javax.swing.GroupLayout panel_IdentificadorAgendarCitaLayout = new javax.swing.GroupLayout(panel_IdentificadorAgendarCita);
        panel_IdentificadorAgendarCita.setLayout(panel_IdentificadorAgendarCitaLayout);
        panel_IdentificadorAgendarCitaLayout.setHorizontalGroup(
            panel_IdentificadorAgendarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_IdentificadorAgendarCitaLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(labelAgendarCita, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );
        panel_IdentificadorAgendarCitaLayout.setVerticalGroup(
            panel_IdentificadorAgendarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_IdentificadorAgendarCitaLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(labelAgendarCita, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        panelAgendarCita.add(panel_IdentificadorAgendarCita, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 70));

        tablaAgendarCita.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Apellido", "Telefono", "Correo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaAgendarCita.setFont(new java.awt.Font("Product Sans", 0, 14)); // NOI18N
        scrollTablaAgendarCita.setViewportView(tablaAgendarCita);

        panelAgendarCita.add(scrollTablaAgendarCita, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 660, 430));

        comboBoxHoras.setBackground(new java.awt.Color(0, 0, 0));
        comboBoxHoras.setFont(new java.awt.Font("Product Sans", 1, 12)); // NOI18N
        comboBoxHoras.setForeground(new java.awt.Color(255, 255, 255));
        comboBoxHoras.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10:00:00", "10:15:00", "10:30:00", "10:45:00", "11:00:00", "11:15:00", "11:30:00", "11:45:00", "12:00:00", "12:15:00", "12:30:00", "12:45:00", "13:00:00", "13:15:00", "13:30:00", "13:45:00", "14:00:00", "14:15:00", "14:30:00", "14:45:00", "15:00:00", "15:15:00", "15:30:00", "15:45:00", "16:00:00", "16:15:00", "16:30:00", "16:45:00", "17:00:00", "17:15:00", "17:30:00", "17:45:00", "18:00:00", "18:15:00", "18:30:00", "18:45:00", "19:00:00" }));
        comboBoxHoras.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 239, 112)));
        comboBoxHoras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxHorasActionPerformed(evt);
            }
        });
        panelAgendarCita.add(comboBoxHoras, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 120, 50));

        comboBoxBarberosAgendar.setBackground(new java.awt.Color(0, 0, 0));
        comboBoxBarberosAgendar.setFont(new java.awt.Font("Product Sans", 1, 12)); // NOI18N
        comboBoxBarberosAgendar.setForeground(new java.awt.Color(255, 255, 255));
        comboBoxBarberosAgendar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 239, 112)));
        panelAgendarCita.add(comboBoxBarberosAgendar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 120, 50));

        comboBoxServiciosAgenda.setBackground(new java.awt.Color(0, 0, 0));
        comboBoxServiciosAgenda.setFont(new java.awt.Font("Product Sans", 1, 12)); // NOI18N
        comboBoxServiciosAgenda.setForeground(new java.awt.Color(255, 255, 255));
        comboBoxServiciosAgenda.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 239, 112)));
        panelAgendarCita.add(comboBoxServiciosAgenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 120, 50));

        dateChooserAgendarCita.setBackground(new java.awt.Color(51, 51, 51));
        dateChooserAgendarCita.setForeground(new java.awt.Color(102, 255, 51));
        panelAgendarCita.add(dateChooserAgendarCita, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 140, 30));

        labelDia.setForeground(new java.awt.Color(255, 255, 255));
        labelDia.setText("Dia");
        panelAgendarCita.add(labelDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 30, -1));

        labelHora.setForeground(new java.awt.Color(255, 255, 255));
        labelHora.setText("Hora");
        panelAgendarCita.add(labelHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 40, -1));

        labelBarbero.setForeground(new java.awt.Color(255, 255, 255));
        labelBarbero.setText("Barbero");
        panelAgendarCita.add(labelBarbero, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, -1, -1));

        labelServicio.setForeground(new java.awt.Color(255, 255, 255));
        labelServicio.setText("Servicio");
        panelAgendarCita.add(labelServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, -1, -1));

        labelSelecionaCliente.setFont(new java.awt.Font("Product Sans", 0, 14)); // NOI18N
        labelSelecionaCliente.setForeground(new java.awt.Color(153, 255, 153));
        labelSelecionaCliente.setText("Selecciona un cliente");
        panelAgendarCita.add(labelSelecionaCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, 160, -1));

        labelCliente.setText("Cliente");
        panelAgendarCita.add(labelCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, -1, -1));

        panelAgendarCitaVistaUsuario.setColor1(new java.awt.Color(1, 26, 13));
        panelAgendarCitaVistaUsuario.setColor2(new java.awt.Color(0, 0, 0));
        panelAgendarCitaVistaUsuario.setInferior_derecho(30);
        panelAgendarCitaVistaUsuario.setInferior_izquierdo(30);
        panelAgendarCitaVistaUsuario.setSuperior_derecho(30);
        panelAgendarCitaVistaUsuario.setSuperior_izquierdo(30);

        btnAgendar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgendar.setText("Angendar");
        btnAgendar.setBackground_Hover_1(new java.awt.Color(2, 214, 120));
        btnAgendar.setBackground_Hover_2(new java.awt.Color(20, 149, 67));
        btnAgendar.setBackground_No_Hover_1(new java.awt.Color(0, 0, 0));
        btnAgendar.setBackground_No_Hover_2(new java.awt.Color(0, 0, 0));
        btnAgendar.setColor_Hover_text(new java.awt.Color(0, 0, 0));
        btnAgendar.setEsquina_inferior_derecho(30);
        btnAgendar.setEsquina_inferior_izquierdo(30);
        btnAgendar.setEsquina_superior_derecho(30);
        btnAgendar.setEsquina_superior_izquierdo(30);
        btnAgendar.setFocusPainted(false);
        btnAgendar.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        btnAgendar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgendarActionPerformed(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/calendario-reloj.png"))); // NOI18N

        javax.swing.GroupLayout panelAgendarCitaVistaUsuarioLayout = new javax.swing.GroupLayout(panelAgendarCitaVistaUsuario);
        panelAgendarCitaVistaUsuario.setLayout(panelAgendarCitaVistaUsuarioLayout);
        panelAgendarCitaVistaUsuarioLayout.setHorizontalGroup(
            panelAgendarCitaVistaUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAgendarCitaVistaUsuarioLayout.createSequentialGroup()
                .addComponent(btnAgendar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        panelAgendarCitaVistaUsuarioLayout.setVerticalGroup(
            panelAgendarCitaVistaUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAgendar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelAgendarCitaVistaUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelAgendarCita.add(panelAgendarCitaVistaUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 160, 60));

        separador.setForeground(new java.awt.Color(255, 255, 255));
        panelAgendarCita.add(separador, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 220, 10));

        layared_Round_JWC1.add(panelAgendarCita);
        panelAgendarCita.setBounds(210, 10, 860, 620);

        panelVerCitas.setBackground(new java.awt.Color(51, 51, 51));
        panelVerCitas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaVerCitas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Hora", "Cliente", "Servicio", "Barbero", "Cumplida"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaVerCitas.setFont(new java.awt.Font("Product Sans", 0, 14)); // NOI18N
        scrollTablaVerCitas.setViewportView(tablaVerCitas);

        panelVerCitas.add(scrollTablaVerCitas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 860, 430));

        panelBarraBusquedaVerCita.setBackground(new java.awt.Color(0, 0, 0));
        panelBarraBusquedaVerCita.setEsqInferiorDerecha(30);
        panelBarraBusquedaVerCita.setEsqInferiorIzquierda(30);
        panelBarraBusquedaVerCita.setEsqSuperiorDerecha(30);
        panelBarraBusquedaVerCita.setEsqSuperiorIzquierda(30);

        barraBusquedaVerCita.setBackground(new java.awt.Color(0, 0, 0));
        barraBusquedaVerCita.setFont(new java.awt.Font("JetBrainsMono NF", 0, 13)); // NOI18N
        barraBusquedaVerCita.setForeground(new java.awt.Color(255, 255, 255));
        barraBusquedaVerCita.setBorder(null);
        barraBusquedaVerCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barraBusquedaVerCitaActionPerformed(evt);
            }
        });
        barraBusquedaVerCita.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                barraBusquedaVerCitaKeyTyped(evt);
            }
        });

        btnVerCitaBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnVerCitaBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/busqueda (2).png"))); // NOI18N
        btnVerCitaBuscar.setText("");
        btnVerCitaBuscar.setBackground_Hover_1(new java.awt.Color(0, 0, 204));
        btnVerCitaBuscar.setBackground_Hover_2(new java.awt.Color(0, 0, 153));
        btnVerCitaBuscar.setBackground_No_Hover_1(new java.awt.Color(0, 0, 0));
        btnVerCitaBuscar.setBackground_No_Hover_2(new java.awt.Color(0, 0, 0));
        btnVerCitaBuscar.setEsquina_inferior_derecho(30);
        btnVerCitaBuscar.setEsquina_inferior_izquierdo(30);
        btnVerCitaBuscar.setEsquina_superior_derecho(30);
        btnVerCitaBuscar.setEsquina_superior_izquierdo(30);
        btnVerCitaBuscar.setFocusPainted(false);
        btnVerCitaBuscar.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N

        javax.swing.GroupLayout panelBarraBusquedaVerCitaLayout = new javax.swing.GroupLayout(panelBarraBusquedaVerCita);
        panelBarraBusquedaVerCita.setLayout(panelBarraBusquedaVerCitaLayout);
        panelBarraBusquedaVerCitaLayout.setHorizontalGroup(
            panelBarraBusquedaVerCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBarraBusquedaVerCitaLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(barraBusquedaVerCita, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnVerCitaBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelBarraBusquedaVerCitaLayout.setVerticalGroup(
            panelBarraBusquedaVerCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBarraBusquedaVerCitaLayout.createSequentialGroup()
                .addGroup(panelBarraBusquedaVerCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(barraBusquedaVerCita, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerCitaBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panelVerCitas.add(panelBarraBusquedaVerCita, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        panelCancelarCita.setBackground(new java.awt.Color(0, 0, 0));
        panelCancelarCita.setEsqInferiorDerecha(15);
        panelCancelarCita.setEsqInferiorIzquierda(15);
        panelCancelarCita.setEsqSuperiorDerecha(15);
        panelCancelarCita.setEsqSuperiorIzquierda(15);

        labelCancelarCita.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelCancelarCita.setForeground(new java.awt.Color(255, 255, 255));
        labelCancelarCita.setText("Cancelar");

        btnCancelarCita.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/papelera-xmark.png"))); // NOI18N
        btnCancelarCita.setText("");
        btnCancelarCita.setToolTipText("Agregar");
        btnCancelarCita.setBackground_Hover_1(new java.awt.Color(164, 0, 37));
        btnCancelarCita.setBackground_Hover_2(new java.awt.Color(117, 7, 33));
        btnCancelarCita.setBackground_No_Hover_1(new java.awt.Color(197, 0, 45));
        btnCancelarCita.setBackground_No_Hover_2(new java.awt.Color(171, 0, 11));
        btnCancelarCita.setColor_NoHover_text(new java.awt.Color(0, 0, 0));
        btnCancelarCita.setEsquina_inferior_derecho(15);
        btnCancelarCita.setEsquina_inferior_izquierdo(15);
        btnCancelarCita.setEsquina_superior_derecho(15);
        btnCancelarCita.setEsquina_superior_izquierdo(15);
        btnCancelarCita.setFocusPainted(false);
        btnCancelarCita.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        btnCancelarCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarCitaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCancelarCitaLayout = new javax.swing.GroupLayout(panelCancelarCita);
        panelCancelarCita.setLayout(panelCancelarCitaLayout);
        panelCancelarCitaLayout.setHorizontalGroup(
            panelCancelarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCancelarCitaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnCancelarCita, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelCancelarCitaLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(labelCancelarCita)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelCancelarCitaLayout.setVerticalGroup(
            panelCancelarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCancelarCitaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelCancelarCita, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelarCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        panelVerCitas.add(panelCancelarCita, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 90, 90, 60));

        layared_Round_JWC1.add(panelVerCitas);
        panelVerCitas.setBounds(210, 10, 860, 620);

        panelVentas.setBackground(new java.awt.Color(51, 51, 51));
        panelVentas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaVentaProductos.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaVentaProductos.setFont(new java.awt.Font("Product Sans", 0, 14)); // NOI18N
        scrollTablaVentas.setViewportView(tablaVentaProductos);

        panelVentas.add(scrollTablaVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 870, 310));

        panelBarraBusquedaVentas.setBackground(new java.awt.Color(0, 0, 0));
        panelBarraBusquedaVentas.setEsqInferiorDerecha(30);
        panelBarraBusquedaVentas.setEsqInferiorIzquierda(30);
        panelBarraBusquedaVentas.setEsqSuperiorDerecha(30);
        panelBarraBusquedaVentas.setEsqSuperiorIzquierda(30);

        barraBusquedaVentas.setBackground(new java.awt.Color(0, 0, 0));
        barraBusquedaVentas.setFont(new java.awt.Font("JetBrainsMono NF", 0, 13)); // NOI18N
        barraBusquedaVentas.setForeground(new java.awt.Color(255, 255, 255));
        barraBusquedaVentas.setBorder(null);
        barraBusquedaVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barraBusquedaVentasActionPerformed(evt);
            }
        });
        barraBusquedaVentas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                barraBusquedaVentasKeyTyped(evt);
            }
        });

        btnBuscarVentas.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/busqueda (2).png"))); // NOI18N
        btnBuscarVentas.setText("");
        btnBuscarVentas.setBackground_Hover_1(new java.awt.Color(0, 0, 204));
        btnBuscarVentas.setBackground_Hover_2(new java.awt.Color(0, 0, 153));
        btnBuscarVentas.setBackground_No_Hover_1(new java.awt.Color(0, 0, 0));
        btnBuscarVentas.setBackground_No_Hover_2(new java.awt.Color(0, 0, 0));
        btnBuscarVentas.setEsquina_inferior_derecho(30);
        btnBuscarVentas.setEsquina_inferior_izquierdo(30);
        btnBuscarVentas.setEsquina_superior_derecho(30);
        btnBuscarVentas.setEsquina_superior_izquierdo(30);
        btnBuscarVentas.setFocusPainted(false);
        btnBuscarVentas.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N

        javax.swing.GroupLayout panelBarraBusquedaVentasLayout = new javax.swing.GroupLayout(panelBarraBusquedaVentas);
        panelBarraBusquedaVentas.setLayout(panelBarraBusquedaVentasLayout);
        panelBarraBusquedaVentasLayout.setHorizontalGroup(
            panelBarraBusquedaVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBarraBusquedaVentasLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(barraBusquedaVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBuscarVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelBarraBusquedaVentasLayout.setVerticalGroup(
            panelBarraBusquedaVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBarraBusquedaVentasLayout.createSequentialGroup()
                .addGroup(panelBarraBusquedaVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(barraBusquedaVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panelVentas.add(panelBarraBusquedaVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 260, 30));

        panelVentasAgregar.setBackground(new java.awt.Color(0, 0, 0));
        panelVentasAgregar.setEsqInferiorDerecha(15);
        panelVentasAgregar.setEsqInferiorIzquierda(15);
        panelVentasAgregar.setEsqSuperiorDerecha(15);
        panelVentasAgregar.setEsqSuperiorIzquierda(15);

        labelVentasAgregar.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelVentasAgregar.setForeground(new java.awt.Color(255, 255, 255));
        labelVentasAgregar.setText("Agregar");

        btnVentasAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/carrito-de-compra-anadir.png"))); // NOI18N
        btnVentasAgregar.setText("");
        btnVentasAgregar.setToolTipText("Agregar");
        btnVentasAgregar.setBackground_Hover_1(new java.awt.Color(3, 142, 62));
        btnVentasAgregar.setBackground_Hover_2(new java.awt.Color(5, 111, 44));
        btnVentasAgregar.setBackground_No_Hover_1(new java.awt.Color(0, 197, 97));
        btnVentasAgregar.setBackground_No_Hover_2(new java.awt.Color(1, 140, 69));
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
            .addGroup(panelVentasAgregarLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(labelVentasAgregar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVentasAgregarLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnVentasAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelVentasAgregarLayout.setVerticalGroup(
            panelVentasAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVentasAgregarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelVentasAgregar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVentasAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        panelVentas.add(panelVentasAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 90, 60));

        separadorVentas.setForeground(new java.awt.Color(255, 255, 255));
        panelVentas.add(separadorVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, 220, 10));

        labelSeleccionaProducto.setFont(new java.awt.Font("Product Sans", 0, 14)); // NOI18N
        labelSeleccionaProducto.setForeground(new java.awt.Color(153, 255, 153));
        labelSeleccionaProducto.setText("Selecciona un producto");
        panelVentas.add(labelSeleccionaProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 160, -1));

        labelProducto.setText("Producto");
        panelVentas.add(labelProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, -1, -1));

        panelComboUnidades.setBackground(new java.awt.Color(0, 0, 0));
        panelComboUnidades.setEsqInferiorDerecha(30);
        panelComboUnidades.setEsqInferiorIzquierda(30);
        panelComboUnidades.setEsqSuperiorDerecha(30);
        panelComboUnidades.setEsqSuperiorIzquierda(30);

        spinnerUnidades.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        labelComboboxUnidades.setText("Unidades");

        javax.swing.GroupLayout panelComboUnidadesLayout = new javax.swing.GroupLayout(panelComboUnidades);
        panelComboUnidades.setLayout(panelComboUnidadesLayout);
        panelComboUnidadesLayout.setHorizontalGroup(
            panelComboUnidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelComboUnidadesLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(panelComboUnidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelComboboxUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );
        panelComboUnidadesLayout.setVerticalGroup(
            panelComboUnidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelComboUnidadesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelComboboxUnidades)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(spinnerUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        panelVentas.add(panelComboUnidades, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        labelVentasMarca.setForeground(new java.awt.Color(255, 255, 255));
        labelVentasMarca.setText("Marca");
        panelVentas.add(labelVentasMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, -1, -1));

        labelVentasCategoria.setForeground(new java.awt.Color(255, 255, 255));
        labelVentasCategoria.setText("Categoria");
        panelVentas.add(labelVentasCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, -1, -1));

        comboboxCategoriaVentas.setBackground(new java.awt.Color(0, 0, 0));
        comboboxCategoriaVentas.setFont(new java.awt.Font("Product Sans", 1, 12)); // NOI18N
        comboboxCategoriaVentas.setForeground(new java.awt.Color(255, 255, 255));
        comboboxCategoriaVentas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todas" }));
        comboboxCategoriaVentas.setBorder(null);
        comboboxCategoriaVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxCategoriaVentasActionPerformed(evt);
            }
        });
        panelVentas.add(comboboxCategoriaVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 40, 80, 40));

        panelComboboxCategoria.setBackground(new java.awt.Color(0, 0, 0));
        panelComboboxCategoria.setEsqInferiorIzquierda(30);
        panelComboboxCategoria.setEsqSuperiorIzquierda(30);

        javax.swing.GroupLayout panelComboboxCategoriaLayout = new javax.swing.GroupLayout(panelComboboxCategoria);
        panelComboboxCategoria.setLayout(panelComboboxCategoriaLayout);
        panelComboboxCategoriaLayout.setHorizontalGroup(
            panelComboboxCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );
        panelComboboxCategoriaLayout.setVerticalGroup(
            panelComboboxCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        panelVentas.add(panelComboboxCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, 70, 40));

        panelComboboxCategoriaEfecto.setBackground(new java.awt.Color(13, 245, 120));
        panelComboboxCategoriaEfecto.setEsqInferiorDerecha(90);
        panelComboboxCategoriaEfecto.setEsqInferiorIzquierda(90);
        panelComboboxCategoriaEfecto.setEsqSuperiorDerecha(30);
        panelComboboxCategoriaEfecto.setEsqSuperiorIzquierda(30);

        javax.swing.GroupLayout panelComboboxCategoriaEfectoLayout = new javax.swing.GroupLayout(panelComboboxCategoriaEfecto);
        panelComboboxCategoriaEfecto.setLayout(panelComboboxCategoriaEfectoLayout);
        panelComboboxCategoriaEfectoLayout.setHorizontalGroup(
            panelComboboxCategoriaEfectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );
        panelComboboxCategoriaEfectoLayout.setVerticalGroup(
            panelComboboxCategoriaEfectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        panelVentas.add(panelComboboxCategoriaEfecto, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 30, 90, 50));

        comboboxMarcaVentas.setBackground(new java.awt.Color(0, 0, 0));
        comboboxMarcaVentas.setFont(new java.awt.Font("Product Sans", 1, 12)); // NOI18N
        comboboxMarcaVentas.setForeground(new java.awt.Color(255, 255, 255));
        comboboxMarcaVentas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todas" }));
        comboboxMarcaVentas.setBorder(null);
        comboboxMarcaVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxMarcaVentasActionPerformed(evt);
            }
        });
        panelVentas.add(comboboxMarcaVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 40, 80, 40));

        panelComboboxMarca.setBackground(new java.awt.Color(0, 0, 0));
        panelComboboxMarca.setEsqInferiorIzquierda(30);
        panelComboboxMarca.setEsqSuperiorIzquierda(30);

        javax.swing.GroupLayout panelComboboxMarcaLayout = new javax.swing.GroupLayout(panelComboboxMarca);
        panelComboboxMarca.setLayout(panelComboboxMarcaLayout);
        panelComboboxMarcaLayout.setHorizontalGroup(
            panelComboboxMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );
        panelComboboxMarcaLayout.setVerticalGroup(
            panelComboboxMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        panelVentas.add(panelComboboxMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 40, 70, 40));

        panelComboboxMarcaEfecto.setBackground(new java.awt.Color(13, 245, 120));
        panelComboboxMarcaEfecto.setEsqInferiorDerecha(90);
        panelComboboxMarcaEfecto.setEsqInferiorIzquierda(90);
        panelComboboxMarcaEfecto.setEsqSuperiorDerecha(30);
        panelComboboxMarcaEfecto.setEsqSuperiorIzquierda(30);

        javax.swing.GroupLayout panelComboboxMarcaEfectoLayout = new javax.swing.GroupLayout(panelComboboxMarcaEfecto);
        panelComboboxMarcaEfecto.setLayout(panelComboboxMarcaEfectoLayout);
        panelComboboxMarcaEfectoLayout.setHorizontalGroup(
            panelComboboxMarcaEfectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );
        panelComboboxMarcaEfectoLayout.setVerticalGroup(
            panelComboboxMarcaEfectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        panelVentas.add(panelComboboxMarcaEfecto, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 30, 90, 50));

        panelCarrito.setColor1(new java.awt.Color(1, 26, 13));
        panelCarrito.setColor2(new java.awt.Color(0, 0, 0));
        panelCarrito.setInferior_derecho(30);
        panelCarrito.setInferior_izquierdo(30);
        panelCarrito.setSuperior_derecho(30);
        panelCarrito.setSuperior_izquierdo(30);

        btnCarrito.setForeground(new java.awt.Color(255, 255, 255));
        btnCarrito.setText("Carrito");
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

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/carrito-de-compras blanco.png"))); // NOI18N

        javax.swing.GroupLayout panelCarritoLayout = new javax.swing.GroupLayout(panelCarrito);
        panelCarrito.setLayout(panelCarritoLayout);
        panelCarritoLayout.setHorizontalGroup(
            panelCarritoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCarritoLayout.createSequentialGroup()
                .addComponent(btnCarrito, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        panelCarritoLayout.setVerticalGroup(
            panelCarritoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCarrito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelCarritoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelVentas.add(panelCarrito, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 170, 180, 60));

        layared_Round_JWC1.add(panelVentas);
        panelVentas.setBounds(210, 10, 860, 620);

        getContentPane().add(layared_Round_JWC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1090, 640));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnMinimizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinimizarActionPerformed
        this.setExtendedState(ICONIFIED);        // TODO add your handling code here:
    }//GEN-LAST:event_btnMinimizarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        System.exit(0);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnRegistrarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarClienteActionPerformed
        cambiarVisibilidadRegistrarClienteInterfaz(true);
        cambiarVisibilidadInterfazAgendarCita(false);
        cambiarVisibilidadVerCitaInterfaz(false);
        cambiarVisibilidadVentasInterfaz(false);
    }//GEN-LAST:event_btnRegistrarClienteActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed

        JOptionPane.showMessageDialog(rootPane, "ADIOS " + labelMostrarInfoUsuario.getText() + " !");
        ventanaUsuario.setVisible(false);
        this.dispose();

        btnCerrarSesion.setBackground_No_Hover_1(new java.awt.Color(27, 27, 27));
        btnCerrarSesion.setBackground_No_Hover_2(new java.awt.Color(27, 27, 27));
        btnCerrarSesion.setForeground(new Color(204, 204, 204));

        inicio.setVisible(true);


    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnAgendarCitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgendarCitasActionPerformed
        cambiarVisibilidadRegistrarClienteInterfaz(false);
        cambiarVisibilidadInterfazAgendarCita(true);
        cambiarVisibilidadVerCitaInterfaz(false);
        cambiarVisibilidadVentasInterfaz(false);
    }//GEN-LAST:event_btnAgendarCitasActionPerformed

    private void btnVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasActionPerformed
        cambiarVisibilidadRegistrarClienteInterfaz(false);
        cambiarVisibilidadInterfazAgendarCita(false);
        cambiarVisibilidadVerCitaInterfaz(false);
        cambiarVisibilidadVentasInterfaz(true);
    }//GEN-LAST:event_btnVentasActionPerformed

    private void btnVerCitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerCitasActionPerformed
        cambiarVisibilidadRegistrarClienteInterfaz(false);
        cambiarVisibilidadInterfazAgendarCita(false);
        cambiarVisibilidadVerCitaInterfaz(true);
        cambiarVisibilidadVentasInterfaz(false);
    }//GEN-LAST:event_btnVerCitasActionPerformed

    private void barraBusquedaVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barraBusquedaVentasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_barraBusquedaVentasActionPerformed

    private void comboBoxHorasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxHorasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxHorasActionPerformed

    private void btnAgendarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgendarActionPerformed

        if (estaSelccionadaTablaClientes() && hayCamposSeleccionados()) {

            try {
                Date fechaDeJCalendar = dateChooserAgendarCita.getDate();
                LocalDate fechaAgenda = fechaDeJCalendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                String horaObtenidaComboBox = comboBoxHoras.getSelectedItem().toString();
                SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
                Date date = formatoHora.parse(horaObtenidaComboBox);

                java.sql.Time hora = new Time(date.getTime());
                if (citaAdmin.registrarCita(fechaAgenda, hora,
                        clienteAdmin.obtenerID(tablaAgendarCita.getValueAt(tablaAgendarCita.getSelectedRow(), 3).toString()),
                        servicioAdmin.obtenerID(comboBoxServiciosAgenda.getSelectedItem().toString()), barberoAdmin.obtenerID(comboBoxBarberosAgendar.getSelectedItem().toString()))) {

                    enviarPDFConfrimacionCita(tablaAgendarCita.getValueAt(tablaAgendarCita.getSelectedRow(), 3).toString());
                    JOptionPane.showMessageDialog(null, "Cita agendada con exito !");
                    hayCampoSeleccionadoCliente = false;
                    actualizarTablaCitas();

                }
            } catch (ParseException | SQLException ex) {
                Logger.getLogger(InicioUsuario.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Hay campos vacios !", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btnAgendarActionPerformed

    private void btnVentasAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasAgregarActionPerformed
        if (hayCampoSeleccionadoProducto) {
            if (Integer.parseInt(spinnerUnidades.getValue().toString())
                    <= Integer.parseInt(tablaVentaProductos.getValueAt(tablaVentaProductos.getSelectedRow(), 4).toString())) {

                almacenarProductoEnCarrito();
                int unidadesRestantes = Integer.valueOf((tablaVentaProductos.getValueAt(tablaVentaProductos.getSelectedRow(), 4)).toString())
                        - Integer.valueOf(spinnerUnidades.getValue().toString());

                tablaVentaProductos.setValueAt(unidadesRestantes, tablaVentaProductos.getSelectedRow(), 4);

                JOptionPane.showMessageDialog(null, "Producto agregado al carrito");
                hayCampoSeleccionadoProducto = false;

            } else {

                JOptionPane.showMessageDialog(null, "No hay suficientes unidades", "Aviso", JOptionPane.WARNING_MESSAGE);

            }
        } else {

            JOptionPane.showMessageDialog(null, "No has escojido ningun producto !", "Aviso", JOptionPane.WARNING_MESSAGE);

        }


    }//GEN-LAST:event_btnVentasAgregarActionPerformed

    private void btnCarritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarritoActionPerformed
        try {
            productoAdmin.leerProductos();
            tablaProductosCarrito.fixTable(scrollTablaCarrito);
            DefaultTableModel mode = (DefaultTableModel) tablaProductosCarrito.getModel();
            int unidades = 0;
            mode.setRowCount(0);
            for (Producto producto : productosCarrito) {
                String nombre = producto.getNombre();
                String marca = producto.getMarca();
                String categoria = producto.getCategoria();
                unidades = producto.getUnidades();
                double precio = producto.getPrecio() * unidades;

                mode.addRow(new Object[]{nombre, precio, marca, categoria, unidades});

            }

            carrtioView.setVisible(true);
            carrtioView.toFront();
            hayCampoSeleccionadoProducto = false;

        } catch (SQLException ex) {
            Logger.getLogger(InicioUsuario.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCarritoActionPerformed

    private void barraBusquedaVerCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barraBusquedaVerCitaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_barraBusquedaVerCitaActionPerformed

    private void btnCancelarCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarCitaActionPerformed
        if (estaSelccionadaTablaCitas()) {
            int result = JOptionPane.showConfirmDialog(null, "¿Deseas cancelar esta cita?", "Confirmación", JOptionPane.YES_NO_OPTION);

            if (result == JOptionPane.YES_OPTION) {
                removerCita();
                hayCampoSeleccionadoCita = false;
                JOptionPane.showMessageDialog(null, "Cita removida");

            }
        } else {

            JOptionPane.showMessageDialog(null, "Selecciona un cita para remover", "AVISO", JOptionPane.WARNING_MESSAGE);

        }


    }//GEN-LAST:event_btnCancelarCitaActionPerformed

    private void textNombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textNombreMouseClicked

    }//GEN-LAST:event_textNombreMouseClicked

    private void textNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNombreActionPerformed

    }//GEN-LAST:event_textNombreActionPerformed

    private void textApellidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textApellidoMouseClicked

    }//GEN-LAST:event_textApellidoMouseClicked

    private void textApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textApellidoActionPerformed

    private void textCorreoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textCorreoMouseClicked

    }//GEN-LAST:event_textCorreoMouseClicked

    private void textCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCorreoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (verificarCamposVacios(textNombre, textApellido, textCorreo, textTelefono) && validarCorreo(textCorreo.getText())
                && esSoloLetras(textNombre.getText(), textApellido.getText())) {
            try {
                clienteAdmin.leerClientes();
                if (clienteAdmin.registrarClientes(textNombre.getText().trim(), textApellido.getText().trim(),
                        textTelefono.getText().trim(), textCorreo.getText().trim())) {
                    JOptionPane.showMessageDialog(null, "Cliente registrado con exito");
                    limpiarCampos(textNombre, textApellido, textCorreo, textTelefono);
                    actualizarTablaClientes();

                }

            } catch (SQLException ex) {
                Logger.getLogger(FormularioBarberos.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }


    }//GEN-LAST:event_btnGuardarActionPerformed

    private void comboboxCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboboxCategoriaActionPerformed

    private void barraBusquedaVentasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_barraBusquedaVentasKeyTyped
        String textoBusqueda = barraBusquedaVentas.getText().trim();
        if (Character.isLetterOrDigit(evt.getKeyChar())) {
            textoBusqueda += evt.getKeyChar();
        }

        actualizarTablaVentaProductosConBusqueda(textoBusqueda);

    }//GEN-LAST:event_barraBusquedaVentasKeyTyped

    private void barraBusquedaVerCitaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_barraBusquedaVerCitaKeyTyped
        String textoBusqueda = barraBusquedaVerCita.getText().trim();
        if (Character.isLetterOrDigit(evt.getKeyChar())) {
            textoBusqueda += evt.getKeyChar();
        }

        actualizarTablaVerCitasConBusqueda(textoBusqueda);

    }//GEN-LAST:event_barraBusquedaVerCitaKeyTyped

    private void comboboxMarcaVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxMarcaVentasActionPerformed

        aplicarFiltroCombinadoParaVentas();


    }//GEN-LAST:event_comboboxMarcaVentasActionPerformed

    private void comboboxCategoriaVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxCategoriaVentasActionPerformed
        aplicarFiltroCombinadoParaVentas();
    }//GEN-LAST:event_comboboxCategoriaVentasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField barraBusquedaVentas;
    private javax.swing.JTextField barraBusquedaVerCita;
    public static btn_efecto01_jwc.btn_efecto_V1_JWC btnAgendar;
    private btn_efecto01_jwc.btn_efecto_V1_JWC btnAgendarCitas;
    public static btn_efecto01_jwc.btn_efecto_V1_JWC btnBuscarVentas;
    public static btn_efecto01_jwc.btn_efecto_V1_JWC btnCancelarCita;
    public static btn_efecto01_jwc.btn_efecto_V1_JWC btnCarrito;
    public static btn_efecto01_jwc.btn_efecto_V1_JWC btnCerrarSesion;
    public static btn_efecto01_jwc.btn_efecto_V1_JWC btnGuardar;
    private btn_efecto01_jwc.btn_efecto_V1_JWC btnMinimizar;
    private btn_efecto01_jwc.btn_efecto_V1_JWC btnRegistrarCliente;
    private btn_efecto01_jwc.btn_efecto_V1_JWC btnSalir;
    private btn_efecto01_jwc.btn_efecto_V1_JWC btnVentas;
    public static btn_efecto01_jwc.btn_efecto_V1_JWC btnVentasAgregar;
    public static btn_efecto01_jwc.btn_efecto_V1_JWC btnVerCitaBuscar;
    private btn_efecto01_jwc.btn_efecto_V1_JWC btnVerCitas;
    public static javax.swing.JComboBox<String> comboBoxBarberosAgendar;
    private javax.swing.JComboBox<String> comboBoxHoras;
    public static javax.swing.JComboBox<String> comboBoxServiciosAgenda;
    public static javax.swing.JComboBox<String> comboboxCategoriaVentas;
    public static javax.swing.JComboBox<String> comboboxMarcaVentas;
    private com.toedter.calendar.JDateChooser dateChooserAgendarCita;
    private javax.swing.JLabel iconoAgendarCita;
    private javax.swing.JLabel iconoCerrarSesion;
    private javax.swing.JLabel iconoRegistrarCliente;
    private javax.swing.JLabel iconoVentas;
    private javax.swing.JLabel iconoVerCitas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel labelAgendarCita;
    private javax.swing.JLabel labelBarbero;
    private javax.swing.JLabel labelCancelarCita;
    private javax.swing.JLabel labelCliente;
    private javax.swing.JLabel labelComboboxUnidades;
    private javax.swing.JLabel labelDia;
    private javax.swing.JLabel labelHora;
    public static javax.swing.JLabel labelMostrarInfoUsuario;
    private javax.swing.JLabel labelProducto;
    private javax.swing.JLabel labelSeleccionaProducto;
    private javax.swing.JLabel labelSelecionaCliente;
    private javax.swing.JLabel labelServicio;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JLabel labelVentasAgregar;
    private javax.swing.JLabel labelVentasCategoria;
    private javax.swing.JLabel labelVentasMarca;
    private swing.Layared_Round_JWC layared_Round_JWC1;
    private javax.swing.JPanel panelAgendarCita;
    private panel_degradado_jwc.Panel_Round_Degradado_JWC panelAgendarCitaVistaUsuario;
    private swing.Panel_Round_JWC panelBarraBusquedaVentas;
    private swing.Panel_Round_JWC panelBarraBusquedaVerCita;
    private swing.Panel_Round_JWC panelCancelarCita;
    private panel_degradado_jwc.Panel_Round_Degradado_JWC panelCarrito;
    private swing.Panel_Round_JWC panelComboUnidades;
    private swing.Panel_Round_JWC panelComboboxCategoria;
    private swing.Panel_Round_JWC panelComboboxCategoriaEfecto;
    private swing.Panel_Round_JWC panelComboboxMarca;
    private swing.Panel_Round_JWC panelComboboxMarcaEfecto;
    private swing.Panel_Round_JWC panelInfoUsuario;
    private swing.Panel_Round_JWC panelMenu;
    private javax.swing.JPanel panelMenuEfecto;
    private javax.swing.JPanel panelRegistrarCliente;
    private javax.swing.JPanel panelVentas;
    private swing.Panel_Round_JWC panelVentasAgregar;
    private javax.swing.JPanel panelVerCitas;
    private panel_degradado_jwc.Panel_Round_Degradado_JWC panel_IdentificadorAgendarCita;
    private panel_degradado_jwc.Panel_Round_Degradado_JWC panel_Round_Degradado_JWC1;
    private panel_degradado_jwc.Panel_Round_Degradado_JWC panel_Round_Degradado_JWC4;
    private swing.Panel_Round_JWC panel_Round_JWC10;
    private swing.Panel_Round_JWC panel_Round_JWC11;
    private swing.Panel_Round_JWC panel_Round_JWC12;
    private swing.Panel_Round_JWC panel_Round_JWC3;
    private swing.Panel_Round_JWC panel_Round_JWC4;
    private swing.Panel_Round_JWC panel_Round_JWC5;
    private swing.Panel_Round_JWC panel_Round_JWC6;
    private swing.Panel_Round_JWC panel_Round_JWC9;
    private javax.swing.JScrollPane scrollTablaAgendarCita;
    private javax.swing.JScrollPane scrollTablaVentas;
    private javax.swing.JScrollPane scrollTablaVerCitas;
    private javax.swing.JSeparator separador;
    private javax.swing.JSeparator separadorVentas;
    public javax.swing.JSpinner spinnerUnidades;
    public static tabledark.TableDark tablaAgendarCita;
    public static tabledark.TableDark tablaVentaProductos;
    public static tabledark.TableDark tablaVerCitas;
    private javax.swing.JTextField textApellido;
    private javax.swing.JTextField textCorreo;
    private javax.swing.JTextField textNombre;
    private javax.swing.JFormattedTextField textTelefono;
    // End of variables declaration//GEN-END:variables
}
