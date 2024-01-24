package vista;

import java.awt.Color;
import javax.swing.JOptionPane;
import static inicio.Main.*;
import javax.swing.table.DefaultTableModel;
import clases_administradoras.*;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import modelo.*;
import tabledark.TableDark;
import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import static vista.FormularioProductos.categorias;
import static vista.FormularioProductos.marcas;
import static vista.InicioUsuario.comboBoxBarberosAgendar;
import static vista.InicioUsuario.comboBoxServiciosAgenda;
import static vista.InicioUsuario.tablaAgendarCita;
import static vista.InicioUsuario.tablaVentaProductos;
import static vista.InicioUsuario.tablaVerCitas;
import static vista.vistaSeleccionClienteCarrito.tablaClientesCarrito;

/**
 *
 * @author joel_
 */
public class InicioAdministrador extends javax.swing.JFrame {

    public FormularioEmpleado formUsuarios = new FormularioEmpleado();
    public FormularioServicios formServicios = new FormularioServicios();
    public FormularioClientes formClientes = new FormularioClientes();
    public FormularioProductos formProductos = new FormularioProductos();
    public FormularioBarberos formBarberos = new FormularioBarberos();

    public FormularioEditarEmpleado formEditarUsuarios = new FormularioEditarEmpleado();
    public FormularioEditarServicios formEditarServicios = new FormularioEditarServicios();
    public FormularioEditarClientes formEditarClientes = new FormularioEditarClientes();
    public FormularioEditarProductos formEditarProductos = new FormularioEditarProductos();
    public FormularioEditarBarberos formEditarBarberos = new FormularioEditarBarberos();

    private UsuarioAdmin usuarioAdmin;
    private BarberoAdmin barberoAdmin;
    private ProductoAdmin productoAdmin;
    private ServicioAdmin servicioAdmin;
    private ClienteAdmin clienteAdmin;
    private CitaAdmin citaAdmin;

    boolean hayCampoSeleccionadoUsuarios = false;
    boolean hayCampoSeleccionadoBarberos = false;
    boolean hayCampoSeleccionadoProducto = false;
    boolean hayCampoSeleccionadoServicio = false;
    boolean hayCampoSeleccionadoCliente = false;
    boolean hayCampoSeleccionadoCita = false;

    public InicioAdministrador() {
        inicializarFlatLaf();
        initComponents();
        panelEmpleados.setVisible(true);
        generarComponetesInterfaz();
        setBackground(new Color(0, 0, 0, 0));

        try {
            usuarioAdmin = new UsuarioAdmin();
            barberoAdmin = new BarberoAdmin();
            productoAdmin = new ProductoAdmin();
            clienteAdmin = new ClienteAdmin();
            servicioAdmin = new ServicioAdmin();
            citaAdmin = new CitaAdmin();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(InicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }

        generarTablaUsuarios();
        generarTablaProductos();
        generarTablaServicios();
        generarTablaCitas();
        generarTablaBarberos();
        generarTablaClientes();

        estaSelccionadaTablaUsuarios();
        estaSelccionadaTablaBarberos();
        estaSelccionadaTablaClientes();
        estaSelccionadaTablaProductos();
        estaSelccionadaTablaServicios();

    }

    private void cambiarVisibilidadInterfazEmpleados(boolean esVisible) {
        panelEmpleados.setVisible(esVisible);
        panelBarraBusquedaEmpleado.setVisible(esVisible);
        barraBusquedaEmpleado.setVisible(esVisible);
        btnBuscarEmpleado.setVisible(esVisible);
        scrollTablaEmpleado.setVisible(esVisible);
        tablaEmpleado.setVisible(esVisible);
        panelAgregarEmpleado.setVisible(esVisible);
        labelAgregarEmpleado.setVisible(esVisible);
        btnAgregarEmpleado.setVisible(esVisible);
        panelModificarEmpleado.setVisible(esVisible);
        labelModificarEmpleado.setVisible(esVisible);
        btnModificarEmpleado.setVisible(esVisible);
        panelRemoverEmpleado.setVisible(esVisible);
        labelRemoverEmpleado.setVisible(esVisible);
        btnRemoverEmpleado.setVisible(esVisible);
    }

    private void cambiarVisivilidadInterfazBarberos(boolean esVisible) {
        panelBarberos.setVisible(esVisible);
        scrollTablaBarberos.setVisible(esVisible);
        tablaBarberos.setVisible(esVisible);
        labelMensajeEstadoBarberosCombobox.setVisible(esVisible);
        panelComboboxBarberos.setVisible(esVisible);
        panelEfectoComboboxBarberos.setVisible(esVisible);
        panelAgregarBarberos.setVisible(esVisible);
        labelAgregarBarbero.setVisible(esVisible);
        btnAgregarBarbero.setVisible(esVisible);
        panelModificarBarberos.setVisible(esVisible);
        labelModificarBarbero.setVisible(esVisible);
        btnModificarBarbero.setVisible(esVisible);
        panelRemoverBarberos.setVisible(esVisible);
        labelRemoverBarbero.setVisible(esVisible);
        btnRemoverBarbero.setVisible(esVisible);
    }

    private void cambiarVisivilidadInterfazClientes(boolean esVisible) {
        panelClientes.setVisible(esVisible);
        scrollTablaClientes.setVisible(esVisible);
        tablaClientes.setVisible(esVisible);
        panelBarraBusquedaClientes.setVisible(esVisible);
        barraBusquedaClientes.setVisible(esVisible);
        btnBarraBusquedaCliente.setVisible(esVisible);
        panelAgregarCliente.setVisible(esVisible);
        labelAgregarCliente.setVisible(esVisible);
        btnAgregarCliente.setVisible(esVisible);
        panelModificarCliente.setVisible(esVisible);
        labelModificarCliente.setVisible(esVisible);
        btnModificarCliente.setVisible(esVisible);
        panelRemoverCliente.setVisible(esVisible);
        labelRemoverCliente.setVisible(esVisible);
        btnRemoverCliente.setVisible(esVisible);

    }

    private void cambiarVisivilidadInterfazServicios(boolean esVisible) {
        panelServicios.setVisible(esVisible);
        panelAdministrarCitasefecto.setVisible(esVisible);
        panelAdministarCitasBtn.setVisible(esVisible);
        labelAdministrarCitasIcono.setVisible(esVisible);
        labelMensajeAdministrarCitas.setVisible(esVisible);
        panelAdministarServiciosBtn.setVisible(esVisible);
        panelAdministrarServiciosEfecto.setVisible(esVisible);
        labelAdministrarCitasIcono.setVisible(esVisible);
        labelMensajeAdministrarServicios.setVisible(esVisible);

    }

    private void cambiarVisivilidadInterfazProductos(boolean esVisible) {
        panelProductos.setVisible(esVisible);
        ScrolltablaProductos.setVisible(esVisible);
        tablaProductos.setVisible(esVisible);
        panelBarraBusquedaProducto.setVisible(esVisible);
        barraBusquedaProductos.setVisible(esVisible);
        btnBuscarProducto.setVisible(esVisible);
        labelMarca.setVisible(esVisible);
        comboboxMarcaVistaProductos.setVisible(esVisible);
        panelComboboxMarca.setVisible(esVisible);
        panelComboboxMarcaEfecto.setVisible(esVisible);
        labelCategoria.setVisible(esVisible);
        comboboxCategoriaVistaProductos.setVisible(esVisible);
        panelComboboxCategoria.setVisible(esVisible);
        panelComboboxCategoriaEfecto.setVisible(esVisible);
        panelAgregarProducto.setVisible(esVisible);
        labelAgregarProducto.setVisible(esVisible);
        btnAgregarProducto.setVisible(esVisible);
        panelModificarProducto.setVisible(esVisible);
        labelModificarProducto.setVisible(esVisible);
        btnModificarProducto.setVisible(esVisible);
        panelRemoverProducto.setVisible(esVisible);
        labelRemoverProducto.setVisible(esVisible);
        btnRemoverProducto.setVisible(esVisible);
    }

//    private void cambiarVisivilidadInterfazEstadisticas(boolean esVisible) {
//        panelEstadisticas.setVisible(esVisible);
//        panelGrafica.setVisible(esVisible);
//        panelGraficaPastel.setVisible(esVisible);
//        btnGenerarInforme.setVisible(esVisible);
//    }

    private void cambiarVisivilidadInterfazAdministrarServicios(boolean esVisible) {
        panelAdministrarServicios.setVisible(esVisible);
        scrollTablaAdministrarServicios.setVisible(esVisible);
        tablaServicios.setVisible(esVisible);
        panelBarraBusquedaAdministrarServicios.setVisible(esVisible);
        barraBusquedaServicios.setVisible(esVisible);
        btnBuscarServicios.setVisible(esVisible);
        panel_IdentificadorAdministrarServicios.setVisible(esVisible);
        labelAdministrarServicios.setVisible(esVisible);
        panelAdministrarServicios_Agregar.setVisible(esVisible);
        labelAdministrarServicios_Agregar.setVisible(esVisible);
        btnAdministrarServicios_Agregar.setVisible(esVisible);
        panelAdministrarServicios_Modificar.setVisible(esVisible);
        labelAdministrarServicios_Modificar.setVisible(esVisible);
        btnAdministrarServicios_Modificar.setVisible(esVisible);
        panelAdministrarServicios_Remover.setVisible(esVisible);
        labelAdministrarServicios_Remover.setVisible(esVisible);
        btnAdministrarServicios_Remover.setVisible(esVisible);
    }

    private void cambiarVisivilidadInterfazAdministrarCitas(boolean esVisible) {
        panelAdministrarCitas.setVisible(esVisible);
        panel_IdentificadorAdministrarCitas.setVisible(esVisible);
        labelAdministrarCitas.setVisible(esVisible);
        scrollTablaCitas.setVisible(esVisible);
        tablaCitasAdmin.setVisible(esVisible);
//        btnFiltroProximasReservas.setVisible(esVisible);
//        btnFiltroTodasReservas.setVisible(esVisible);
//        btnFiltroReservasCanceladas.setVisible(esVisible);

        panelBarraBusquedaCitas.setVisible(esVisible);
        barraBusquedaCitas.setVisible(esVisible);
        btnBuscarCita.setVisible(esVisible);

    }

    private void generarComponetesInterfaz() {

        panelBarberos.setVisible(false);
        panelClientes.setVisible(false);
        panelServicios.setVisible(false);
        panelProductos.setVisible(false);
        panelEstadisticas.setVisible(false);
        panelAdministrarCitas.setVisible(false);
        panelAdministrarServicios.setVisible(false);

        TextPrompt placeholderBarraUsuarios = new TextPrompt("Buscar Emplado", barraBusquedaEmpleado);
        TextPrompt placeholderBarraProductos = new TextPrompt("Buscar Producto", barraBusquedaProductos);
        TextPrompt placeholderBarraServicios = new TextPrompt("Buscar Servicio", barraBusquedaServicios);
        TextPrompt placeholderBarraClientes = new TextPrompt("Buscar Cliente ", barraBusquedaClientes);

        TextPrompt placeholderBarraClientesCitas = new TextPrompt("Buscar por cliente", barraBusquedaCitas);

        placeholderBarraUsuarios.changeAlpha(0.55f);
        placeholderBarraProductos.changeAlpha(0.55f);
        placeholderBarraServicios.changeAlpha(0.55f);
        placeholderBarraClientes.changeAlpha(0.55f);
        placeholderBarraClientesCitas.changeAlpha(0.55f);

    }

    //OPERACIONES EN LA VISTA DE USUARIO(EMPLEADOS)======================================================================================================================
    private void generarTablaUsuarios() {
        try {
            usuarioAdmin.leerUsuarios();

            tablaEmpleado.fixTable(scrollTablaEmpleado);

            try {
                DefaultTableModel mode = (DefaultTableModel) tablaEmpleado.getModel();
                Usuario[] usuarios = usuarioAdmin.obtenerLista();

                for (Usuario usuario : usuarios) {

                    String nombre = usuario.getNombre();
                    String apellido = usuario.getApellido();
                    String user = usuario.getUsuario();
                    String correo = usuario.getCorreo();

                    mode.addRow(new Object[]{nombre, apellido, user, correo});

                }

            } catch (SQLException ex) {
                Logger.getLogger(InicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean estaSelccionadaTablaUsuarios() {

        tablaEmpleado.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent Mouse_evt) {

                tablaEmpleado = (TableDark) Mouse_evt.getSource();
                Point point = Mouse_evt.getPoint();
                int row = tablaEmpleado.rowAtPoint(point);

                if (Mouse_evt.getClickCount() == 1) {

                    hayCampoSeleccionadoUsuarios = true;
                }

            }
        });
        return hayCampoSeleccionadoUsuarios;
    }

    private void actualizarUsuario() {
        formEditarUsuarios.textNombre.setText(tablaEmpleado.getValueAt(tablaEmpleado.getSelectedRow(), 0).toString());
        formEditarUsuarios.textApellido.setText(tablaEmpleado.getValueAt(tablaEmpleado.getSelectedRow(), 1).toString());
        formEditarUsuarios.textUsuario.setText(tablaEmpleado.getValueAt(tablaEmpleado.getSelectedRow(), 2).toString());
        formEditarUsuarios.textPassword.setText(usuarioAdmin.obtenerContrasenia(tablaEmpleado.getValueAt(tablaEmpleado.getSelectedRow(), 2).toString()));
        formEditarUsuarios.textCorreo.setText(tablaEmpleado.getValueAt(tablaEmpleado.getSelectedRow(), 3).toString());

    }

    private void actualizarTablaUsuarios() {
        try {
            DefaultTableModel DefaultModelUsuario = (DefaultTableModel) tablaEmpleado.getModel();
            DefaultModelUsuario.setRowCount(0);

            usuarioAdmin.leerUsuarios();
            Usuario[] usuarios = usuarioAdmin.obtenerLista();
            for (Usuario usuario : usuarios) {

                String nombre = usuario.getNombre();
                String apellido = usuario.getApellido();
                String user = usuario.getUsuario();
                String correo = usuario.getCorreo();

                DefaultModelUsuario.addRow(new Object[]{nombre, apellido, user, correo});

            }
        } catch (SQLException ex) {
            Logger.getLogger(FormularioEditarEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void removerUsuario() {

        try {
            usuarioAdmin.leerUsuarios();

            String idUsuario = tablaEmpleado.getValueAt(tablaEmpleado.getSelectedRow(), 2).toString();
            int id = usuarioAdmin.obtenerID(idUsuario);
            System.out.println(id);
            usuarioAdmin.cambiarEstado(id, false);
            actualizarTablaUsuarios();

        } catch (SQLException ex) {
            Logger.getLogger(InicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //========================================================================================================================================================================  
    // OPERACIONES EN LA VISTA DE BARBEROS=======================================================================================================================================
    private void generarTablaBarberos() {

        try {

            barberoAdmin.leerBarberos();

            tablaBarberos.fixTable(scrollTablaBarberos);

            DefaultTableModel DefaultModelBarberos = (DefaultTableModel) tablaBarberos.getModel();

            Barbero[] barberos = barberoAdmin.obtenerLista();
            String estado;

            for (Barbero barbero : barberos) {

                String nombre = barbero.getNombre();
                String correo = barbero.getCorreo();
                Boolean disponibilidad = barbero.isDisponible();

                if (barbero.isDisponible() == true) {
                    estado = "Disponible";
                } else {
                    estado = "No disponible";
                }

                DefaultModelBarberos.addRow(new Object[]{nombre, correo, estado});

            }

        } catch (SQLException ex) {
            Logger.getLogger(InicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private boolean estaSelccionadaTablaBarberos() {

        tablaBarberos.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent Mouse_evt) {

                tablaBarberos = (TableDark) Mouse_evt.getSource();
                Point point = Mouse_evt.getPoint();
                int row = tablaBarberos.rowAtPoint(point);

                if (Mouse_evt.getClickCount() == 1) {

                    hayCampoSeleccionadoBarberos = true;
                }

            }
        });
        return hayCampoSeleccionadoBarberos;
    }

    private void actualizarBarbero() {

        formEditarBarberos.textNombre.setText(tablaBarberos.getValueAt(tablaBarberos.getSelectedRow(), 0).toString());
        formEditarBarberos.textCorreo.setText(tablaBarberos.getValueAt(tablaBarberos.getSelectedRow(), 1).toString());

    }

    private void removerBarbero() {

        try {
            barberoAdmin.leerBarberos();

            String idBarbero = tablaBarberos.getValueAt(tablaBarberos.getSelectedRow(), 0).toString();
            String estado;
            comboBoxBarberosAgendar.removeItem(idBarbero);

            int id = barberoAdmin.obtenerID(idBarbero);
            barberoAdmin.cambiarEstado(id, false);
            actualizarTablaBarberos();
        } catch (SQLException ex) {
            Logger.getLogger(InicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void actualizarTablaBarberos() {
        String estado = "";
        try {
            DefaultTableModel DefaultModelBarbero = (DefaultTableModel) tablaBarberos.getModel();
            DefaultModelBarbero.setRowCount(0);

            barberoAdmin.leerBarberos();
            Barbero[] barberos = barberoAdmin.obtenerLista();
            for (Barbero barbero : barberos) {

                String nombre = barbero.getNombre();
                String correo = barbero.getCorreo();
                boolean disponible = barbero.isDisponible();

                if (barbero.isDisponible() == true) {
                    estado = "Disponible";
                } else {
                    estado = "No disponible";
                }

                DefaultModelBarbero.addRow(new Object[]{nombre, correo, estado});

            }
        } catch (SQLException ex) {
            Logger.getLogger(FormularioEditarEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarTodosLosBarberos() {
        try {
            Barbero[] todosLosBarberos = barberoAdmin.leerBarberos();
            DefaultTableModel modelo = (DefaultTableModel) tablaBarberos.getModel();
            modelo.setRowCount(0);

            for (Barbero barbero : todosLosBarberos) {
                String estadoDisponibilidad = barbero.isDisponible() ? "Disponible" : "No disponible";
                modelo.addRow(new Object[]{barbero.getNombre(), barbero.getCorreo(), estadoDisponibilidad});
            }
        } catch (SQLException ex) {
            Logger.getLogger(InicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizarTablaBarberosConFiltro(boolean estaDisponible) {
        try {
            ArrayList<Barbero> barberosFiltrados = barberoAdmin.filtrarBarberosPorDisponibilidad(estaDisponible);

            DefaultTableModel modelo = (DefaultTableModel) tablaBarberos.getModel();
            modelo.setRowCount(0);

            for (Barbero barbero : barberosFiltrados) {
                String estadoDisponibilidad = barbero.isDisponible() ? "Disponible" : "No disponible";
                modelo.addRow(new Object[]{barbero.getNombre(), barbero.getCorreo(), estadoDisponibilidad});
            }
        } catch (SQLException ex) {
            Logger.getLogger(InicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//=====================================================================================================================================================================================
//OPERACIONES VISTA CLIENTES============================================================================================================================================================    
    private void generarTablaClientes() {
        try {
            clienteAdmin.leerClientes();

            tablaClientes.fixTable(scrollTablaClientes);
            DefaultTableModel DefaultModelClientes = (DefaultTableModel) tablaClientes.getModel();

            Cliente[] clientes = clienteAdmin.obtenerLista();

            for (Cliente cliente : clientes) {
                String nombre = cliente.getNombre();
                String apellido = cliente.getApellido();
                String telefono = cliente.getTelefono();
                String correo = cliente.getCorreo();

                DefaultModelClientes.addRow(new Object[]{nombre, apellido, telefono, correo});
            }

        } catch (SQLException ex) {
            Logger.getLogger(InicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void actualizarCliente() {
        formEditarClientes.textNombre.setText(tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 0).toString());
        formEditarClientes.textApellido.setText(tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 1).toString());
        formEditarClientes.textTelefono.setText(tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 2).toString());
        formEditarClientes.textCorreo.setText(tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 3).toString());

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

    private void removerCliente() {

        try {
            clienteAdmin.leerClientes();
            String idCliente = tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 3).toString();
            String estado;

            int id = clienteAdmin.obtenerID(idCliente);
            System.out.println(id);
            clienteAdmin.cambiarEstado(id, false);
            actualizarTablaClientes();
        } catch (SQLException ex) {
            Logger.getLogger(InicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private boolean estaSelccionadaTablaClientes() {

        tablaClientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent Mouse_evt) {

                tablaClientes = (TableDark) Mouse_evt.getSource();
                Point point = Mouse_evt.getPoint();
                int row = tablaClientes.rowAtPoint(point);

                if (Mouse_evt.getClickCount() == 1) {

                    hayCampoSeleccionadoCliente = true;
                }

            }
        });
        return hayCampoSeleccionadoCliente;
    }
//==============================================================================================================================================================================================

//OPERACIONES VISTA PRODUCTOS============================================================================================================================================================================
    private void generarTablaProductos() {

        try {
            productoAdmin.leerProductos();
            tablaProductos.fixTable(ScrolltablaProductos);
            DefaultTableModel mode2 = (DefaultTableModel) tablaProductos.getModel();
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
            Logger.getLogger(InicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void llenarComboBoxProductos() {
        for (String marca : marcas) {
            comboboxMarcaVistaProductos.addItem(marca);
        }

        for (String categoria : categorias) {
            comboboxCategoriaVistaProductos.addItem(categoria);
        }

    }

    private void actualizarProducto() {
        formEditarProductos.textNombre.setText(tablaProductos.getValueAt(tablaProductos.getSelectedRow(), 0).toString());
        formEditarProductos.textPrecio.setText(tablaProductos.getValueAt(tablaProductos.getSelectedRow(), 1).toString());
        formEditarProductos.textUnidades.setValue(Integer.valueOf(tablaProductos.getValueAt(tablaProductos.getSelectedRow(), 4).toString()));

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

    private void removerProducto() {

        try {
            productoAdmin.leerProductos();
            String nombreObtenido = tablaProductos.getValueAt(tablaProductos.getSelectedRow(), 0).toString();
            String marcaObtenida = tablaProductos.getValueAt(tablaProductos.getSelectedRow(), 2).toString();
            String categoriaObtenida = tablaProductos.getValueAt(tablaProductos.getSelectedRow(), 3).toString();

            int id = productoAdmin.obtenerID(nombreObtenido, marcaObtenida, categoriaObtenida);

            productoAdmin.cambiarEstado(id, false);

            actualizarTablaProductos();

        } catch (SQLException ex) {
            Logger.getLogger(InicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private boolean estaSelccionadaTablaProductos() {

        tablaProductos.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent Mouse_evt) {

                tablaProductos = (TableDark) Mouse_evt.getSource();
                Point point = Mouse_evt.getPoint();
                int row = tablaProductos.rowAtPoint(point);

                if (Mouse_evt.getClickCount() == 1) {

                    hayCampoSeleccionadoProducto = true;
                }

            }
        });
        return hayCampoSeleccionadoProducto;
    }

    private void aplicarFiltroCombinado() {
        try {
            String marcaSeleccionada = (String) comboboxMarcaVistaProductos.getSelectedItem();
            String categoriaSeleccionada = (String) comboboxCategoriaVistaProductos.getSelectedItem();
            ArrayList<Producto> productosFiltrados = productoAdmin.filtrarProductos(marcaSeleccionada, categoriaSeleccionada);

            DefaultTableModel modelo = (DefaultTableModel) tablaProductos.getModel();
            modelo.setRowCount(0);

            for (Producto producto : productosFiltrados) {
                modelo.addRow(new Object[]{producto.getNombre(), producto.getPrecio(), producto.getMarca(), producto.getCategoria(), producto.getUnidades()});
            }
        } catch (SQLException ex) {
            Logger.getLogger(InicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//=======================================================================================================================================================================================================    
//OPERACIONES EN LA VISTA SERVICIOS========================================================================================================================================================================================
    private void generarTablaServicios() {

        try {
            servicioAdmin.leerServicios();

            tablaServicios.fixTable(scrollTablaAdministrarServicios);

            DefaultTableModel DefaultModelServicios = (DefaultTableModel) tablaServicios.getModel();

            Servicio[] servicios = servicioAdmin.obtenerLista();

            for (Servicio servicio : servicios) {

                String nombre = servicio.getNombre();
                double precio = servicio.getPrecio();
                int duracion = servicio.getDuracion();

                DefaultModelServicios.addRow(new Object[]{nombre, precio, duracion});

            }

        } catch (SQLException ex) {
            Logger.getLogger(InicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void actualizarServicio() {

        formEditarServicios.textNombre.setText(tablaServicios.getValueAt(tablaServicios.getSelectedRow(), 0).toString());
        formEditarServicios.textPrecio.setText(tablaServicios.getValueAt(tablaServicios.getSelectedRow(), 1).toString());
        formEditarServicios.textMinutos.setValue(Integer.valueOf(tablaServicios.getValueAt(tablaServicios.getSelectedRow(), 2).toString()));
    }

    private void removerServicio() {

        try {

            servicioAdmin.leerServicios();
            String nombreObtenido = tablaServicios.getValueAt(tablaServicios.getSelectedRow(), 0).toString();
            comboBoxServiciosAgenda.removeItem(nombreObtenido);

            int id = servicioAdmin.obtenerID(nombreObtenido);

            servicioAdmin.cambiarEstado(id, false);

            actualizarTablaServicios();
            actualizarTablaCitas();

        } catch (SQLException ex) {
            Logger.getLogger(InicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void actualizarTablaServicios() {
        try {
            DefaultTableModel DefaultModelServicio = (DefaultTableModel) tablaServicios.getModel();
            DefaultModelServicio.setRowCount(0);

            servicioAdmin.leerServicios();
            Servicio[] servicios = servicioAdmin.obtenerLista();
            for (Servicio servicio : servicios) {

                String nombre = servicio.getNombre();
                double precio = servicio.getPrecio();
                int duracion = servicio.getDuracion();

                DefaultModelServicio.addRow(new Object[]{nombre, precio, duracion});

            }

        } catch (SQLException ex) {
            Logger.getLogger(FormularioEditarEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean estaSelccionadaTablaServicios() {

        tablaServicios.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent Mouse_evt) {

                tablaServicios = (TableDark) Mouse_evt.getSource();
                Point point = Mouse_evt.getPoint();
                int row = tablaServicios.rowAtPoint(point);

                if (Mouse_evt.getClickCount() == 1) {

                    hayCampoSeleccionadoServicio = true;
                }

            }
        });
        return hayCampoSeleccionadoServicio;
    }

//=======================================================================================================================================================================================================
//OPERACIONES EN LA VISTA CITAS===========================================================================================================================================================================
    private void generarTablaCitas() {

        try {
            citaAdmin.leerCitas();
            tablaCitasAdmin.fixTable(scrollTablaCitas);

            DefaultTableModel DefaultModelCitas = (DefaultTableModel) tablaCitasAdmin.getModel();

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

    private void actualizarTablaCitas() {
        try {
            citaAdmin = new CitaAdmin();
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FormularioEditarClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void removerCita() {

        try {

            citaAdmin.leerCitas();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fecha = LocalDate.parse(tablaCitasAdmin.getValueAt(tablaCitasAdmin.getSelectedRow(), 0).toString(), formatter);

            int id = citaAdmin.obtenerID(tablaCitasAdmin.getValueAt(tablaCitasAdmin.getSelectedRow(), 2).toString(),
                    fecha,
                    tablaCitasAdmin.getValueAt(tablaCitasAdmin.getSelectedRow(), 3).toString(),
                    tablaCitasAdmin.getValueAt(tablaCitasAdmin.getSelectedRow(), 4).toString());

            citaAdmin.cancelarCita(id);

            actualizarTablaCitas();

        } catch (SQLException ex) {
            Logger.getLogger(InicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private boolean estaSelccionadaTablaCitas() {

        tablaCitasAdmin.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent Mouse_evt) {

                tablaCitasAdmin = (TableDark) Mouse_evt.getSource();
                Point point = Mouse_evt.getPoint();
                int row = tablaCitasAdmin.rowAtPoint(point);

                if (Mouse_evt.getClickCount() == 1) {

                    hayCampoSeleccionadoCita = true;
                }

            }
        });
        return hayCampoSeleccionadoCita;
    }

//========================================================================================================================================================================================================    
//FUNCIONES FILTRADO TABLAS================================================================================================================================================================
    private void buscarYActualizarTablaUsuarios(String textoBusqueda) {
        Usuario[] usuariosFiltrados = usuarioAdmin.buscarUsuarios(textoBusqueda);
        DefaultTableModel modelo = (DefaultTableModel) tablaEmpleado.getModel();
        modelo.setRowCount(0); // Limpia la tabla

        for (Usuario usuario : usuariosFiltrados) {
            String nombre = usuario.getNombre();
            String apellido = usuario.getApellido();
            String user = usuario.getUsuario();
            String correo = usuario.getCorreo();

            modelo.addRow(new Object[]{nombre, apellido, user, correo});
        }
    }

//============================================================================================================================================
    private void actualizarTablaClientesConBusqueda(String textoBusqueda) {
        try {
            ArrayList<Cliente> clientesFiltrados = clienteAdmin.buscarClientes(textoBusqueda);

            DefaultTableModel modelo = (DefaultTableModel) tablaClientes.getModel();
            modelo.setRowCount(0);

            for (Cliente cliente : clientesFiltrados) {
                modelo.addRow(new Object[]{cliente.getNombre(), cliente.getApellido(), cliente.getTelefono(), cliente.getCorreo()});
            }
        } catch (SQLException ex) {
            Logger.getLogger(InicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//============================================================================================================================================
    private void actualizarTablaProductosConBusqueda(String textoBusqueda) {
        try {
            ArrayList<Producto> productosFiltrados = productoAdmin.buscarProductos(textoBusqueda);

            DefaultTableModel modelo = (DefaultTableModel) tablaProductos.getModel();
            modelo.setRowCount(0);

            for (Producto producto : productosFiltrados) {
                modelo.addRow(new Object[]{producto.getNombre(), producto.getPrecio(), producto.getMarca(), producto.getCategoria(), producto.getUnidades()});
            }
        } catch (SQLException ex) {
            Logger.getLogger(InicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//============================================================================================================================================
    private void actualizarTablaServiciosConBusqueda(String textoBusqueda) {
        try {
            ArrayList<Servicio> serviciosFiltrados = servicioAdmin.buscarServiciosPorNombre(textoBusqueda);

            DefaultTableModel modelo = (DefaultTableModel) tablaServicios.getModel();
            modelo.setRowCount(0);

            for (Servicio servicio : serviciosFiltrados) {
                modelo.addRow(new Object[]{servicio.getNombre(), servicio.getPrecio(), servicio.getDuracion()});
            }
        } catch (SQLException ex) {
            Logger.getLogger(InicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//============================================================================================================================================
    private void actualizarTablaCitasConBusqueda(String textoBusqueda) {
        try {
            ArrayList<Cita> citasFiltradas = citaAdmin.buscarCitasPorCliente(textoBusqueda);

            DefaultTableModel modelo = (DefaultTableModel) tablaCitasAdmin.getModel();
            modelo.setRowCount(0);

            for (Cita cita : citasFiltradas) {
                String estado = cita.isCumplida() ? "Cumplida" : "No cumplida";
                modelo.addRow(new Object[]{cita.getFecha(), cita.getHora(), cita.getCliente(), cita.getServicio(), cita.getBarbero(), estado});
            }
        } catch (SQLException ex) {
            Logger.getLogger(InicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//==========================================================================================================================================================================================    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        num_max1 = new grafico_barrav1_jwc.num_max();
        jPanel2 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        layared_Round_JWC1 = new swing.Layared_Round_JWC();
        btnMinimizar = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        btnSalir = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        panelMenu = new swing.Panel_Round_JWC();
        panelInfoUsuario = new swing.Panel_Round_JWC();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnEmpleados = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        iconoEmpleados = new javax.swing.JLabel();
        btnBarberos = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        iconoBarberos = new javax.swing.JLabel();
        btnClientes = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        iconoClientes = new javax.swing.JLabel();
        btnServicios = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        iconoServicios = new javax.swing.JLabel();
        btnProductos = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        iconoProductos = new javax.swing.JLabel();
        btnEstadisticas = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        iconoEstadisticas = new javax.swing.JLabel();
        btnCerrarSesion = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        iconoCerrarSesion = new javax.swing.JLabel();
        panelMenuEfecto = new javax.swing.JPanel();
        panelEmpleados = new javax.swing.JPanel();
        panelBarraBusquedaEmpleado = new swing.Panel_Round_JWC();
        barraBusquedaEmpleado = new javax.swing.JTextField();
        btnBuscarEmpleado = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        scrollTablaEmpleado = new javax.swing.JScrollPane();
        tablaEmpleado = new tabledark.TableDark();
        panelAgregarEmpleado = new swing.Panel_Round_JWC();
        labelAgregarEmpleado = new javax.swing.JLabel();
        btnAgregarEmpleado = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        panelModificarEmpleado = new swing.Panel_Round_JWC();
        labelModificarEmpleado = new javax.swing.JLabel();
        btnModificarEmpleado = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        panelRemoverEmpleado = new swing.Panel_Round_JWC();
        labelRemoverEmpleado = new javax.swing.JLabel();
        btnRemoverEmpleado = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        panelBarberos = new javax.swing.JPanel();
        scrollTablaBarberos = new javax.swing.JScrollPane();
        tablaBarberos = new tabledark.TableDark();
        labelMensajeEstadoBarberosCombobox = new javax.swing.JLabel();
        comboBoxBarberos = new javax.swing.JComboBox<>();
        panelComboboxBarberos = new swing.Panel_Round_JWC();
        panelEfectoComboboxBarberos = new swing.Panel_Round_JWC();
        panelAgregarBarberos = new swing.Panel_Round_JWC();
        labelAgregarBarbero = new javax.swing.JLabel();
        btnAgregarBarbero = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        panelModificarBarberos = new swing.Panel_Round_JWC();
        labelModificarBarbero = new javax.swing.JLabel();
        btnModificarBarbero = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        panelRemoverBarberos = new swing.Panel_Round_JWC();
        labelRemoverBarbero = new javax.swing.JLabel();
        btnRemoverBarbero = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        panelClientes = new javax.swing.JPanel();
        scrollTablaClientes = new javax.swing.JScrollPane();
        tablaClientes = new tabledark.TableDark();
        panelBarraBusquedaClientes = new swing.Panel_Round_JWC();
        barraBusquedaClientes = new javax.swing.JTextField();
        btnBarraBusquedaCliente = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        panelAgregarCliente = new swing.Panel_Round_JWC();
        labelAgregarCliente = new javax.swing.JLabel();
        btnAgregarCliente = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        panelModificarCliente = new swing.Panel_Round_JWC();
        labelModificarCliente = new javax.swing.JLabel();
        btnModificarCliente = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        panelRemoverCliente = new swing.Panel_Round_JWC();
        labelRemoverCliente = new javax.swing.JLabel();
        btnRemoverCliente = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        panelServicios = new javax.swing.JPanel();
        panelAdministarCitasBtn = new swing.Panel_Round_JWC();
        panelAdministrarCitasefecto = new panel_degradado_jwc.Panel_Round_Degradado_JWC();
        labelAdministrarCitasIcono = new javax.swing.JLabel();
        labelMensajeAdministrarCitas = new javax.swing.JLabel();
        panelAdministarServiciosBtn = new swing.Panel_Round_JWC();
        panelAdministrarServiciosEfecto = new panel_degradado_jwc.Panel_Round_Degradado_JWC();
        labelAdministrarserviciosIcono = new javax.swing.JLabel();
        labelMensajeAdministrarServicios = new javax.swing.JLabel();
        panelProductos = new javax.swing.JPanel();
        ScrolltablaProductos = new javax.swing.JScrollPane();
        tablaProductos = new tabledark.TableDark();
        panelBarraBusquedaProducto = new swing.Panel_Round_JWC();
        barraBusquedaProductos = new javax.swing.JTextField();
        btnBuscarProducto = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        labelMarca = new javax.swing.JLabel();
        comboboxMarcaVistaProductos = new javax.swing.JComboBox<>();
        panelComboboxMarca = new swing.Panel_Round_JWC();
        panelComboboxMarcaEfecto = new swing.Panel_Round_JWC();
        labelCategoria = new javax.swing.JLabel();
        comboboxCategoriaVistaProductos = new javax.swing.JComboBox<>();
        panelComboboxCategoria = new swing.Panel_Round_JWC();
        panelComboboxCategoriaEfecto = new swing.Panel_Round_JWC();
        panelAgregarProducto = new swing.Panel_Round_JWC();
        labelAgregarProducto = new javax.swing.JLabel();
        btnAgregarProducto = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        panelModificarProducto = new swing.Panel_Round_JWC();
        labelModificarProducto = new javax.swing.JLabel();
        btnModificarProducto = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        panelRemoverProducto = new swing.Panel_Round_JWC();
        labelRemoverProducto = new javax.swing.JLabel();
        btnRemoverProducto = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        panelEstadisticas = new javax.swing.JPanel();
        panelGrafica = new javax.swing.JPanel();
        panelGraficaPastel = new javax.swing.JPanel();
        btnGenerarInforme = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        panelAdministrarServicios = new javax.swing.JPanel();
        scrollTablaAdministrarServicios = new javax.swing.JScrollPane();
        tablaServicios = new tabledark.TableDark();
        panelBarraBusquedaAdministrarServicios = new swing.Panel_Round_JWC();
        barraBusquedaServicios = new javax.swing.JTextField();
        btnBuscarServicios = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        panel_IdentificadorAdministrarServicios = new panel_degradado_jwc.Panel_Round_Degradado_JWC();
        labelAdministrarServicios = new javax.swing.JLabel();
        panelAdministrarServicios_Agregar = new swing.Panel_Round_JWC();
        labelAdministrarServicios_Agregar = new javax.swing.JLabel();
        btnAdministrarServicios_Agregar = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        panelAdministrarServicios_Modificar = new swing.Panel_Round_JWC();
        labelAdministrarServicios_Modificar = new javax.swing.JLabel();
        btnAdministrarServicios_Modificar = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        panelAdministrarServicios_Remover = new swing.Panel_Round_JWC();
        labelAdministrarServicios_Remover = new javax.swing.JLabel();
        btnAdministrarServicios_Remover = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        panelAdministrarCitas = new javax.swing.JPanel();
        panel_IdentificadorAdministrarCitas = new panel_degradado_jwc.Panel_Round_Degradado_JWC();
        labelAdministrarCitas = new javax.swing.JLabel();
        scrollTablaCitas = new javax.swing.JScrollPane();
        tablaCitasAdmin = new tabledark.TableDark();
        panelBarraBusquedaCitas = new swing.Panel_Round_JWC();
        barraBusquedaCitas = new javax.swing.JTextField();
        btnBuscarCita = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        panelCancelarCita = new swing.Panel_Round_JWC();
        labelCancelarCita = new javax.swing.JLabel();
        btnCancelarCita = new btn_efecto01_jwc.btn_efecto_V1_JWC();

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

        jToolBar1.setRollover(true);

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

        jLabel1.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Usuario:");
        panelInfoUsuario.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Admin");
        panelInfoUsuario.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/marcador (3).png"))); // NOI18N
        panelInfoUsuario.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, -1, -1));

        panelMenu.add(panelInfoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 150, 70));

        btnEmpleados.setBackground(new java.awt.Color(27, 27, 27));
        btnEmpleados.setForeground(new java.awt.Color(204, 204, 204));
        btnEmpleados.setText("Empleados");
        btnEmpleados.setToolTipText("");
        btnEmpleados.setAlignmentX(1.0F);
        btnEmpleados.setAlignmentY(1.0F);
        btnEmpleados.setBackground_Hover_1(new java.awt.Color(2, 214, 120));
        btnEmpleados.setBackground_Hover_2(new java.awt.Color(51, 51, 51));
        btnEmpleados.setBackground_No_Hover_1(new java.awt.Color(27, 27, 27));
        btnEmpleados.setBackground_No_Hover_2(new java.awt.Color(27, 27, 27));
        btnEmpleados.setBtn_abajo(btnBarberos);
        btnEmpleados.setBtn_arriba_abajo_esquina_ovalado(60);
        btnEmpleados.setColor_Hover_text(new java.awt.Color(0, 0, 0));
        btnEmpleados.setColor_NoHover_text(new java.awt.Color(204, 204, 204));
        btnEmpleados.setEsquina_inferior_izquierdo(40);
        btnEmpleados.setEsquina_superior_izquierdo(40);
        btnEmpleados.setFocusPainted(false);
        btnEmpleados.setFont(new java.awt.Font("Product Sans", 1, 14)); // NOI18N
        btnEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmpleadosActionPerformed(evt);
            }
        });
        panelMenu.add(btnEmpleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 160, 50));

        iconoEmpleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/usuarios.png"))); // NOI18N
        panelMenu.add(iconoEmpleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 50, 40));

        btnBarberos.setBackground(new java.awt.Color(27, 27, 27));
        btnBarberos.setForeground(new java.awt.Color(204, 204, 204));
        btnBarberos.setText("Barberos");
        btnBarberos.setToolTipText("");
        btnBarberos.setAlignmentX(0.5F);
        btnBarberos.setBackground_Hover_1(new java.awt.Color(2, 214, 120));
        btnBarberos.setBackground_Hover_2(new java.awt.Color(51, 51, 51));
        btnBarberos.setBackground_No_Hover_1(new java.awt.Color(27, 27, 27));
        btnBarberos.setBackground_No_Hover_2(new java.awt.Color(27, 27, 27));
        btnBarberos.setBtn_abajo(btnClientes);
        btnBarberos.setBtn_arriba(btnEmpleados);
        btnBarberos.setBtn_arriba_abajo_esquina_ovalado(60);
        btnBarberos.setColor_Hover_text(new java.awt.Color(0, 0, 0));
        btnBarberos.setColor_NoHover_text(new java.awt.Color(204, 204, 204));
        btnBarberos.setEsquina_inferior_izquierdo(40);
        btnBarberos.setEsquina_superior_izquierdo(40);
        btnBarberos.setFocusPainted(false);
        btnBarberos.setFont(new java.awt.Font("Product Sans", 1, 14)); // NOI18N
        btnBarberos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBarberosActionPerformed(evt);
            }
        });
        panelMenu.add(btnBarberos, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 160, 50));

        iconoBarberos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/vista/iconos/peluqueria.png"))); // NOI18N
        panelMenu.add(iconoBarberos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, -1, -1));

        btnClientes.setBackground(new java.awt.Color(27, 27, 27));
        btnClientes.setForeground(new java.awt.Color(204, 204, 204));
        btnClientes.setText("Clientes");
        btnClientes.setToolTipText("");
        btnClientes.setAlignmentX(0.5F);
        btnClientes.setBackground_Hover_1(new java.awt.Color(2, 214, 120));
        btnClientes.setBackground_Hover_2(new java.awt.Color(51, 51, 51));
        btnClientes.setBackground_No_Hover_1(new java.awt.Color(27, 27, 27));
        btnClientes.setBackground_No_Hover_2(new java.awt.Color(27, 27, 27));
        btnClientes.setBtn_abajo(btnServicios);
        btnClientes.setBtn_arriba(btnBarberos);
        btnClientes.setBtn_arriba_abajo_esquina_ovalado(60);
        btnClientes.setColor_Hover_text(new java.awt.Color(0, 0, 0));
        btnClientes.setColor_NoHover_text(new java.awt.Color(204, 204, 204));
        btnClientes.setEsquina_inferior_izquierdo(40);
        btnClientes.setEsquina_superior_izquierdo(40);
        btnClientes.setFocusPainted(false);
        btnClientes.setFont(new java.awt.Font("Product Sans", 1, 14)); // NOI18N
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });
        panelMenu.add(btnClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 160, 50));

        iconoClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/vista/iconos/retrato.png"))); // NOI18N
        panelMenu.add(iconoClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, -1, 30));

        btnServicios.setForeground(new java.awt.Color(204, 204, 204));
        btnServicios.setText("Servicios/citas");
        btnServicios.setAlignmentX(1.0F);
        btnServicios.setAlignmentY(1.0F);
        btnServicios.setBackground_Hover_1(new java.awt.Color(2, 214, 120));
        btnServicios.setBackground_Hover_2(new java.awt.Color(51, 51, 51));
        btnServicios.setBackground_No_Hover_1(new java.awt.Color(27, 27, 27));
        btnServicios.setBackground_No_Hover_2(new java.awt.Color(27, 27, 27));
        btnServicios.setBtn_abajo(btnProductos);
        btnServicios.setBtn_arriba(btnClientes);
        btnServicios.setBtn_arriba_abajo_esquina_ovalado(60);
        btnServicios.setColor_Hover_text(new java.awt.Color(0, 0, 0));
        btnServicios.setColor_NoHover_text(new java.awt.Color(204, 204, 204));
        btnServicios.setEsquina_inferior_izquierdo(40);
        btnServicios.setEsquina_superior_izquierdo(40);
        btnServicios.setFocusPainted(false);
        btnServicios.setFont(new java.awt.Font("Product Sans", 1, 14)); // NOI18N
        btnServicios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnServiciosActionPerformed(evt);
            }
        });
        panelMenu.add(btnServicios, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, 150, 50));

        iconoServicios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/reservar-usuario.png"))); // NOI18N
        panelMenu.add(iconoServicios, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 50, 40));

        btnProductos.setForeground(new java.awt.Color(204, 204, 204));
        btnProductos.setText("Productos");
        btnProductos.setBackground_Hover_1(new java.awt.Color(2, 214, 120));
        btnProductos.setBackground_Hover_2(new java.awt.Color(51, 51, 51));
        btnProductos.setBackground_No_Hover_1(new java.awt.Color(27, 27, 27));
        btnProductos.setBackground_No_Hover_2(new java.awt.Color(27, 27, 27));
        btnProductos.setBtn_abajo(btnEstadisticas);
        btnProductos.setBtn_arriba(btnServicios);
        btnProductos.setBtn_arriba_abajo_esquina_ovalado(60);
        btnProductos.setColor_Hover_text(new java.awt.Color(0, 0, 0));
        btnProductos.setColor_NoHover_text(new java.awt.Color(204, 204, 204));
        btnProductos.setEsquina_inferior_izquierdo(40);
        btnProductos.setEsquina_superior_izquierdo(40);
        btnProductos.setFocusPainted(false);
        btnProductos.setFont(new java.awt.Font("Product Sans", 1, 14)); // NOI18N
        btnProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosActionPerformed(evt);
            }
        });
        panelMenu.add(btnProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 360, 160, 50));

        iconoProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/archivo-factura-dolar.png"))); // NOI18N
        panelMenu.add(iconoProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 50, 40));

        btnEstadisticas.setForeground(new java.awt.Color(204, 204, 204));
        btnEstadisticas.setText("Estadisticas");
        btnEstadisticas.setBackground_Hover_1(new java.awt.Color(2, 214, 120));
        btnEstadisticas.setBackground_Hover_2(new java.awt.Color(51, 51, 51));
        btnEstadisticas.setBackground_No_Hover_1(new java.awt.Color(27, 27, 27));
        btnEstadisticas.setBackground_No_Hover_2(new java.awt.Color(27, 27, 27));
        btnEstadisticas.setBtn_abajo(btnCerrarSesion);
        btnEstadisticas.setBtn_arriba(btnProductos);
        btnEstadisticas.setBtn_arriba_abajo_esquina_ovalado(60);
        btnEstadisticas.setColor_Hover_text(new java.awt.Color(0, 0, 0));
        btnEstadisticas.setColor_NoHover_text(new java.awt.Color(204, 204, 204));
        btnEstadisticas.setEsquina_inferior_izquierdo(40);
        btnEstadisticas.setEsquina_superior_izquierdo(40);
        btnEstadisticas.setFocusPainted(false);
        btnEstadisticas.setFont(new java.awt.Font("Product Sans", 1, 14)); // NOI18N
        btnEstadisticas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstadisticasActionPerformed(evt);
            }
        });
        panelMenu.add(btnEstadisticas, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 410, 160, 50));

        iconoEstadisticas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/grafico-histograma.png"))); // NOI18N
        panelMenu.add(iconoEstadisticas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 50, 40));

        btnCerrarSesion.setForeground(new java.awt.Color(204, 204, 204));
        btnCerrarSesion.setText("Cerrar Sesion");
        btnCerrarSesion.setBackground_Hover_1(new java.awt.Color(204, 0, 51));
        btnCerrarSesion.setBackground_Hover_2(new java.awt.Color(51, 51, 51));
        btnCerrarSesion.setBackground_No_Hover_1(new java.awt.Color(27, 27, 27));
        btnCerrarSesion.setBackground_No_Hover_2(new java.awt.Color(27, 27, 27));
        btnCerrarSesion.setBtn_arriba(btnEstadisticas);
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
        panelMenu.add(btnCerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 460, 160, 50));

        iconoCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/alt-de-inicio-de-sesion.png"))); // NOI18N
        panelMenu.add(iconoCerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, 50, 30));

        panelMenuEfecto.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout panelMenuEfectoLayout = new javax.swing.GroupLayout(panelMenuEfecto);
        panelMenuEfecto.setLayout(panelMenuEfectoLayout);
        panelMenuEfectoLayout.setHorizontalGroup(
            panelMenuEfectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        panelMenuEfectoLayout.setVerticalGroup(
            panelMenuEfectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );

        panelMenu.add(panelMenuEfecto, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 30, 350));

        layared_Round_JWC1.add(panelMenu);
        panelMenu.setBounds(0, 0, 210, 640);

        panelEmpleados.setBackground(new java.awt.Color(51, 51, 51));
        panelEmpleados.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelBarraBusquedaEmpleado.setBackground(new java.awt.Color(0, 0, 0));
        panelBarraBusquedaEmpleado.setEsqInferiorDerecha(30);
        panelBarraBusquedaEmpleado.setEsqInferiorIzquierda(30);
        panelBarraBusquedaEmpleado.setEsqSuperiorDerecha(30);
        panelBarraBusquedaEmpleado.setEsqSuperiorIzquierda(30);

        barraBusquedaEmpleado.setBackground(new java.awt.Color(0, 0, 0));
        barraBusquedaEmpleado.setFont(new java.awt.Font("JetBrainsMono NF", 0, 13)); // NOI18N
        barraBusquedaEmpleado.setForeground(new java.awt.Color(255, 255, 255));
        barraBusquedaEmpleado.setBorder(null);
        barraBusquedaEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barraBusquedaEmpleadoActionPerformed(evt);
            }
        });
        barraBusquedaEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                barraBusquedaEmpleadoKeyTyped(evt);
            }
        });

        btnBuscarEmpleado.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/busqueda (2).png"))); // NOI18N
        btnBuscarEmpleado.setText("");
        btnBuscarEmpleado.setBackground_Hover_1(new java.awt.Color(0, 0, 204));
        btnBuscarEmpleado.setBackground_Hover_2(new java.awt.Color(0, 0, 153));
        btnBuscarEmpleado.setBackground_No_Hover_1(new java.awt.Color(0, 0, 0));
        btnBuscarEmpleado.setBackground_No_Hover_2(new java.awt.Color(0, 0, 0));
        btnBuscarEmpleado.setEsquina_inferior_derecho(30);
        btnBuscarEmpleado.setEsquina_inferior_izquierdo(30);
        btnBuscarEmpleado.setEsquina_superior_derecho(30);
        btnBuscarEmpleado.setEsquina_superior_izquierdo(30);
        btnBuscarEmpleado.setFocusPainted(false);
        btnBuscarEmpleado.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        btnBuscarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarEmpleadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBarraBusquedaEmpleadoLayout = new javax.swing.GroupLayout(panelBarraBusquedaEmpleado);
        panelBarraBusquedaEmpleado.setLayout(panelBarraBusquedaEmpleadoLayout);
        panelBarraBusquedaEmpleadoLayout.setHorizontalGroup(
            panelBarraBusquedaEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBarraBusquedaEmpleadoLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(barraBusquedaEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBuscarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelBarraBusquedaEmpleadoLayout.setVerticalGroup(
            panelBarraBusquedaEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBarraBusquedaEmpleadoLayout.createSequentialGroup()
                .addGroup(panelBarraBusquedaEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(barraBusquedaEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panelEmpleados.add(panelBarraBusquedaEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 260, 30));

        tablaEmpleado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Apellido", "Usuario", "Correo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaEmpleado.setFont(new java.awt.Font("Product Sans", 0, 14)); // NOI18N
        scrollTablaEmpleado.setViewportView(tablaEmpleado);

        panelEmpleados.add(scrollTablaEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 840, 520));

        panelAgregarEmpleado.setBackground(new java.awt.Color(0, 0, 0));
        panelAgregarEmpleado.setEsqInferiorDerecha(15);
        panelAgregarEmpleado.setEsqInferiorIzquierda(15);
        panelAgregarEmpleado.setEsqSuperiorDerecha(15);
        panelAgregarEmpleado.setEsqSuperiorIzquierda(15);

        labelAgregarEmpleado.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelAgregarEmpleado.setForeground(new java.awt.Color(255, 255, 255));
        labelAgregarEmpleado.setText("Agregar");

        btnAgregarEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/agregar-usuario.png"))); // NOI18N
        btnAgregarEmpleado.setText("");
        btnAgregarEmpleado.setToolTipText("Agregar");
        btnAgregarEmpleado.setBackground_Hover_1(new java.awt.Color(3, 142, 62));
        btnAgregarEmpleado.setBackground_Hover_2(new java.awt.Color(5, 111, 44));
        btnAgregarEmpleado.setBackground_No_Hover_1(new java.awt.Color(0, 197, 97));
        btnAgregarEmpleado.setBackground_No_Hover_2(new java.awt.Color(1, 140, 69));
        btnAgregarEmpleado.setColor_NoHover_text(new java.awt.Color(0, 0, 0));
        btnAgregarEmpleado.setEsquina_inferior_derecho(15);
        btnAgregarEmpleado.setEsquina_inferior_izquierdo(15);
        btnAgregarEmpleado.setEsquina_superior_derecho(15);
        btnAgregarEmpleado.setEsquina_superior_izquierdo(15);
        btnAgregarEmpleado.setFocusPainted(false);
        btnAgregarEmpleado.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        btnAgregarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarEmpleadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAgregarEmpleadoLayout = new javax.swing.GroupLayout(panelAgregarEmpleado);
        panelAgregarEmpleado.setLayout(panelAgregarEmpleadoLayout);
        panelAgregarEmpleadoLayout.setHorizontalGroup(
            panelAgregarEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAgregarEmpleadoLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(labelAgregarEmpleado)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAgregarEmpleadoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnAgregarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelAgregarEmpleadoLayout.setVerticalGroup(
            panelAgregarEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAgregarEmpleadoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelAgregarEmpleado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAgregarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        panelEmpleados.add(panelAgregarEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, 90, 60));

        panelModificarEmpleado.setBackground(new java.awt.Color(0, 0, 0));
        panelModificarEmpleado.setEsqInferiorDerecha(15);
        panelModificarEmpleado.setEsqInferiorIzquierda(15);
        panelModificarEmpleado.setEsqSuperiorDerecha(15);
        panelModificarEmpleado.setEsqSuperiorIzquierda(15);

        labelModificarEmpleado.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelModificarEmpleado.setForeground(new java.awt.Color(255, 255, 255));
        labelModificarEmpleado.setText(" Modificar");

        btnModificarEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/editar.png"))); // NOI18N
        btnModificarEmpleado.setText("");
        btnModificarEmpleado.setToolTipText("Agregar");
        btnModificarEmpleado.setBackground_Hover_1(new java.awt.Color(255, 153, 0));
        btnModificarEmpleado.setBackground_Hover_2(new java.awt.Color(169, 127, 3));
        btnModificarEmpleado.setBackground_No_Hover_1(new java.awt.Color(255, 204, 51));
        btnModificarEmpleado.setBackground_No_Hover_2(new java.awt.Color(213, 171, 3));
        btnModificarEmpleado.setColor_NoHover_text(new java.awt.Color(0, 0, 0));
        btnModificarEmpleado.setEsquina_inferior_derecho(15);
        btnModificarEmpleado.setEsquina_inferior_izquierdo(15);
        btnModificarEmpleado.setEsquina_superior_derecho(15);
        btnModificarEmpleado.setEsquina_superior_izquierdo(15);
        btnModificarEmpleado.setFocusPainted(false);
        btnModificarEmpleado.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        btnModificarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarEmpleadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelModificarEmpleadoLayout = new javax.swing.GroupLayout(panelModificarEmpleado);
        panelModificarEmpleado.setLayout(panelModificarEmpleadoLayout);
        panelModificarEmpleadoLayout.setHorizontalGroup(
            panelModificarEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelModificarEmpleadoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnModificarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelModificarEmpleadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelModificarEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelModificarEmpleadoLayout.setVerticalGroup(
            panelModificarEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelModificarEmpleadoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelModificarEmpleado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        panelEmpleados.add(panelModificarEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 90, 60));

        panelRemoverEmpleado.setBackground(new java.awt.Color(0, 0, 0));
        panelRemoverEmpleado.setEsqInferiorDerecha(15);
        panelRemoverEmpleado.setEsqInferiorIzquierda(15);
        panelRemoverEmpleado.setEsqSuperiorDerecha(15);
        panelRemoverEmpleado.setEsqSuperiorIzquierda(15);

        labelRemoverEmpleado.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelRemoverEmpleado.setForeground(new java.awt.Color(255, 255, 255));
        labelRemoverEmpleado.setText("Remover");

        btnRemoverEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/papelera-xmark.png"))); // NOI18N
        btnRemoverEmpleado.setText("");
        btnRemoverEmpleado.setToolTipText("Agregar");
        btnRemoverEmpleado.setBackground_Hover_1(new java.awt.Color(164, 0, 37));
        btnRemoverEmpleado.setBackground_Hover_2(new java.awt.Color(117, 7, 33));
        btnRemoverEmpleado.setBackground_No_Hover_1(new java.awt.Color(197, 0, 45));
        btnRemoverEmpleado.setBackground_No_Hover_2(new java.awt.Color(171, 0, 11));
        btnRemoverEmpleado.setColor_NoHover_text(new java.awt.Color(0, 0, 0));
        btnRemoverEmpleado.setEsquina_inferior_derecho(15);
        btnRemoverEmpleado.setEsquina_inferior_izquierdo(15);
        btnRemoverEmpleado.setEsquina_superior_derecho(15);
        btnRemoverEmpleado.setEsquina_superior_izquierdo(15);
        btnRemoverEmpleado.setFocusPainted(false);
        btnRemoverEmpleado.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        btnRemoverEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverEmpleadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRemoverEmpleadoLayout = new javax.swing.GroupLayout(panelRemoverEmpleado);
        panelRemoverEmpleado.setLayout(panelRemoverEmpleadoLayout);
        panelRemoverEmpleadoLayout.setHorizontalGroup(
            panelRemoverEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRemoverEmpleadoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnRemoverEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelRemoverEmpleadoLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(labelRemoverEmpleado)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRemoverEmpleadoLayout.setVerticalGroup(
            panelRemoverEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRemoverEmpleadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelRemoverEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRemoverEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        panelEmpleados.add(panelRemoverEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 10, 90, 60));

        layared_Round_JWC1.add(panelEmpleados);
        panelEmpleados.setBounds(210, 0, 860, 640);

        panelBarberos.setBackground(new java.awt.Color(51, 51, 51));
        panelBarberos.setFont(new java.awt.Font("Product Sans", 0, 12)); // NOI18N
        panelBarberos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaBarberos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Correo", "Disponible"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaBarberos.setFont(new java.awt.Font("Product Sans", 0, 14)); // NOI18N
        scrollTablaBarberos.setViewportView(tablaBarberos);

        panelBarberos.add(scrollTablaBarberos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 840, 390));

        labelMensajeEstadoBarberosCombobox.setFont(new java.awt.Font("Product Sans", 1, 12)); // NOI18N
        labelMensajeEstadoBarberosCombobox.setForeground(new java.awt.Color(255, 255, 255));
        labelMensajeEstadoBarberosCombobox.setText("Filtrar por estado");
        panelBarberos.add(labelMensajeEstadoBarberosCombobox, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, -1, -1));

        comboBoxBarberos.setBackground(new java.awt.Color(0, 0, 0));
        comboBoxBarberos.setFont(new java.awt.Font("Product Sans", 1, 12)); // NOI18N
        comboBoxBarberos.setForeground(new java.awt.Color(255, 255, 255));
        comboBoxBarberos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Disponible", "No disponible" }));
        comboBoxBarberos.setBorder(null);
        comboBoxBarberos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxBarberosActionPerformed(evt);
            }
        });
        panelBarberos.add(comboBoxBarberos, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 40, 110, 40));

        panelComboboxBarberos.setBackground(new java.awt.Color(0, 0, 0));
        panelComboboxBarberos.setEsqInferiorIzquierda(30);
        panelComboboxBarberos.setEsqSuperiorIzquierda(30);

        javax.swing.GroupLayout panelComboboxBarberosLayout = new javax.swing.GroupLayout(panelComboboxBarberos);
        panelComboboxBarberos.setLayout(panelComboboxBarberosLayout);
        panelComboboxBarberosLayout.setHorizontalGroup(
            panelComboboxBarberosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        panelComboboxBarberosLayout.setVerticalGroup(
            panelComboboxBarberosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        panelBarberos.add(panelComboboxBarberos, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 40, 100, 40));

        panelEfectoComboboxBarberos.setBackground(new java.awt.Color(13, 245, 120));
        panelEfectoComboboxBarberos.setEsqInferiorDerecha(90);
        panelEfectoComboboxBarberos.setEsqInferiorIzquierda(90);
        panelEfectoComboboxBarberos.setEsqSuperiorDerecha(30);
        panelEfectoComboboxBarberos.setEsqSuperiorIzquierda(30);

        javax.swing.GroupLayout panelEfectoComboboxBarberosLayout = new javax.swing.GroupLayout(panelEfectoComboboxBarberos);
        panelEfectoComboboxBarberos.setLayout(panelEfectoComboboxBarberosLayout);
        panelEfectoComboboxBarberosLayout.setHorizontalGroup(
            panelEfectoComboboxBarberosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        panelEfectoComboboxBarberosLayout.setVerticalGroup(
            panelEfectoComboboxBarberosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        panelBarberos.add(panelEfectoComboboxBarberos, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 30, 110, 50));

        panelAgregarBarberos.setBackground(new java.awt.Color(0, 0, 0));
        panelAgregarBarberos.setEsqInferiorDerecha(15);
        panelAgregarBarberos.setEsqInferiorIzquierda(15);
        panelAgregarBarberos.setEsqSuperiorDerecha(15);
        panelAgregarBarberos.setEsqSuperiorIzquierda(15);

        labelAgregarBarbero.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelAgregarBarbero.setForeground(new java.awt.Color(255, 255, 255));
        labelAgregarBarbero.setText("Agregar");

        btnAgregarBarbero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/agregar-usuario.png"))); // NOI18N
        btnAgregarBarbero.setText("");
        btnAgregarBarbero.setToolTipText("Agregar");
        btnAgregarBarbero.setBackground_Hover_1(new java.awt.Color(3, 142, 62));
        btnAgregarBarbero.setBackground_Hover_2(new java.awt.Color(5, 111, 44));
        btnAgregarBarbero.setBackground_No_Hover_1(new java.awt.Color(0, 197, 97));
        btnAgregarBarbero.setBackground_No_Hover_2(new java.awt.Color(1, 140, 69));
        btnAgregarBarbero.setColor_NoHover_text(new java.awt.Color(0, 0, 0));
        btnAgregarBarbero.setEsquina_inferior_derecho(15);
        btnAgregarBarbero.setEsquina_inferior_izquierdo(15);
        btnAgregarBarbero.setEsquina_superior_derecho(15);
        btnAgregarBarbero.setEsquina_superior_izquierdo(15);
        btnAgregarBarbero.setFocusPainted(false);
        btnAgregarBarbero.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        btnAgregarBarbero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarBarberoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAgregarBarberosLayout = new javax.swing.GroupLayout(panelAgregarBarberos);
        panelAgregarBarberos.setLayout(panelAgregarBarberosLayout);
        panelAgregarBarberosLayout.setHorizontalGroup(
            panelAgregarBarberosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAgregarBarberosLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(labelAgregarBarbero)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAgregarBarberosLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnAgregarBarbero, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelAgregarBarberosLayout.setVerticalGroup(
            panelAgregarBarberosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAgregarBarberosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelAgregarBarbero)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAgregarBarbero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        panelBarberos.add(panelAgregarBarberos, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 90, 60));

        panelModificarBarberos.setBackground(new java.awt.Color(0, 0, 0));
        panelModificarBarberos.setEsqInferiorDerecha(15);
        panelModificarBarberos.setEsqInferiorIzquierda(15);
        panelModificarBarberos.setEsqSuperiorDerecha(15);
        panelModificarBarberos.setEsqSuperiorIzquierda(15);

        labelModificarBarbero.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelModificarBarbero.setForeground(new java.awt.Color(255, 255, 255));
        labelModificarBarbero.setText(" Modificar");

        btnModificarBarbero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/editar.png"))); // NOI18N
        btnModificarBarbero.setText("");
        btnModificarBarbero.setToolTipText("Agregar");
        btnModificarBarbero.setBackground_Hover_1(new java.awt.Color(255, 153, 0));
        btnModificarBarbero.setBackground_Hover_2(new java.awt.Color(169, 127, 3));
        btnModificarBarbero.setBackground_No_Hover_1(new java.awt.Color(255, 204, 51));
        btnModificarBarbero.setBackground_No_Hover_2(new java.awt.Color(213, 171, 3));
        btnModificarBarbero.setColor_NoHover_text(new java.awt.Color(0, 0, 0));
        btnModificarBarbero.setEsquina_inferior_derecho(15);
        btnModificarBarbero.setEsquina_inferior_izquierdo(15);
        btnModificarBarbero.setEsquina_superior_derecho(15);
        btnModificarBarbero.setEsquina_superior_izquierdo(15);
        btnModificarBarbero.setFocusPainted(false);
        btnModificarBarbero.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        btnModificarBarbero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarBarberoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelModificarBarberosLayout = new javax.swing.GroupLayout(panelModificarBarberos);
        panelModificarBarberos.setLayout(panelModificarBarberosLayout);
        panelModificarBarberosLayout.setHorizontalGroup(
            panelModificarBarberosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelModificarBarberosLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnModificarBarbero, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelModificarBarberosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelModificarBarbero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelModificarBarberosLayout.setVerticalGroup(
            panelModificarBarberosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelModificarBarberosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelModificarBarbero)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificarBarbero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        panelBarberos.add(panelModificarBarberos, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 90, 60));

        panelRemoverBarberos.setBackground(new java.awt.Color(0, 0, 0));
        panelRemoverBarberos.setEsqInferiorDerecha(15);
        panelRemoverBarberos.setEsqInferiorIzquierda(15);
        panelRemoverBarberos.setEsqSuperiorDerecha(15);
        panelRemoverBarberos.setEsqSuperiorIzquierda(15);

        labelRemoverBarbero.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelRemoverBarbero.setForeground(new java.awt.Color(255, 255, 255));
        labelRemoverBarbero.setText("Remover");

        btnRemoverBarbero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/papelera-xmark.png"))); // NOI18N
        btnRemoverBarbero.setText("");
        btnRemoverBarbero.setToolTipText("Agregar");
        btnRemoverBarbero.setBackground_Hover_1(new java.awt.Color(164, 0, 37));
        btnRemoverBarbero.setBackground_Hover_2(new java.awt.Color(117, 7, 33));
        btnRemoverBarbero.setBackground_No_Hover_1(new java.awt.Color(197, 0, 45));
        btnRemoverBarbero.setBackground_No_Hover_2(new java.awt.Color(171, 0, 11));
        btnRemoverBarbero.setColor_NoHover_text(new java.awt.Color(0, 0, 0));
        btnRemoverBarbero.setEsquina_inferior_derecho(15);
        btnRemoverBarbero.setEsquina_inferior_izquierdo(15);
        btnRemoverBarbero.setEsquina_superior_derecho(15);
        btnRemoverBarbero.setEsquina_superior_izquierdo(15);
        btnRemoverBarbero.setFocusPainted(false);
        btnRemoverBarbero.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        btnRemoverBarbero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverBarberoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRemoverBarberosLayout = new javax.swing.GroupLayout(panelRemoverBarberos);
        panelRemoverBarberos.setLayout(panelRemoverBarberosLayout);
        panelRemoverBarberosLayout.setHorizontalGroup(
            panelRemoverBarberosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRemoverBarberosLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnRemoverBarbero, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelRemoverBarberosLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(labelRemoverBarbero)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRemoverBarberosLayout.setVerticalGroup(
            panelRemoverBarberosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRemoverBarberosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelRemoverBarbero)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemoverBarbero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        panelBarberos.add(panelRemoverBarberos, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 90, 60));

        layared_Round_JWC1.add(panelBarberos);
        panelBarberos.setBounds(210, 10, 860, 620);

        panelClientes.setBackground(new java.awt.Color(51, 51, 51));
        panelClientes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaClientes.setFont(new java.awt.Font("Product Sans", 0, 14)); // NOI18N
        scrollTablaClientes.setViewportView(tablaClientes);

        panelClientes.add(scrollTablaClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 830, 420));

        panelBarraBusquedaClientes.setBackground(new java.awt.Color(0, 0, 0));
        panelBarraBusquedaClientes.setEsqInferiorDerecha(30);
        panelBarraBusquedaClientes.setEsqInferiorIzquierda(30);
        panelBarraBusquedaClientes.setEsqSuperiorDerecha(30);
        panelBarraBusquedaClientes.setEsqSuperiorIzquierda(30);

        barraBusquedaClientes.setBackground(new java.awt.Color(0, 0, 0));
        barraBusquedaClientes.setFont(new java.awt.Font("JetBrainsMono NF", 0, 13)); // NOI18N
        barraBusquedaClientes.setForeground(new java.awt.Color(255, 255, 255));
        barraBusquedaClientes.setBorder(null);
        barraBusquedaClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barraBusquedaClientesActionPerformed(evt);
            }
        });
        barraBusquedaClientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                barraBusquedaClientesKeyTyped(evt);
            }
        });

        btnBarraBusquedaCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnBarraBusquedaCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/busqueda (2).png"))); // NOI18N
        btnBarraBusquedaCliente.setText("");
        btnBarraBusquedaCliente.setBackground_Hover_1(new java.awt.Color(0, 0, 204));
        btnBarraBusquedaCliente.setBackground_Hover_2(new java.awt.Color(0, 0, 153));
        btnBarraBusquedaCliente.setBackground_No_Hover_1(new java.awt.Color(0, 0, 0));
        btnBarraBusquedaCliente.setBackground_No_Hover_2(new java.awt.Color(0, 0, 0));
        btnBarraBusquedaCliente.setEsquina_inferior_derecho(30);
        btnBarraBusquedaCliente.setEsquina_inferior_izquierdo(30);
        btnBarraBusquedaCliente.setEsquina_superior_derecho(30);
        btnBarraBusquedaCliente.setEsquina_superior_izquierdo(30);
        btnBarraBusquedaCliente.setFocusPainted(false);
        btnBarraBusquedaCliente.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N

        javax.swing.GroupLayout panelBarraBusquedaClientesLayout = new javax.swing.GroupLayout(panelBarraBusquedaClientes);
        panelBarraBusquedaClientes.setLayout(panelBarraBusquedaClientesLayout);
        panelBarraBusquedaClientesLayout.setHorizontalGroup(
            panelBarraBusquedaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBarraBusquedaClientesLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(barraBusquedaClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBarraBusquedaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelBarraBusquedaClientesLayout.setVerticalGroup(
            panelBarraBusquedaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBarraBusquedaClientesLayout.createSequentialGroup()
                .addGroup(panelBarraBusquedaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(barraBusquedaClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBarraBusquedaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panelClientes.add(panelBarraBusquedaClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 260, 30));

        panelAgregarCliente.setBackground(new java.awt.Color(0, 0, 0));
        panelAgregarCliente.setEsqInferiorDerecha(15);
        panelAgregarCliente.setEsqInferiorIzquierda(15);
        panelAgregarCliente.setEsqSuperiorDerecha(15);
        panelAgregarCliente.setEsqSuperiorIzquierda(15);

        labelAgregarCliente.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelAgregarCliente.setForeground(new java.awt.Color(255, 255, 255));
        labelAgregarCliente.setText("Agregar");

        btnAgregarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/agregar-usuario.png"))); // NOI18N
        btnAgregarCliente.setText("");
        btnAgregarCliente.setToolTipText("Agregar");
        btnAgregarCliente.setBackground_Hover_1(new java.awt.Color(3, 142, 62));
        btnAgregarCliente.setBackground_Hover_2(new java.awt.Color(5, 111, 44));
        btnAgregarCliente.setBackground_No_Hover_1(new java.awt.Color(0, 197, 97));
        btnAgregarCliente.setBackground_No_Hover_2(new java.awt.Color(1, 140, 69));
        btnAgregarCliente.setColor_NoHover_text(new java.awt.Color(0, 0, 0));
        btnAgregarCliente.setEsquina_inferior_derecho(15);
        btnAgregarCliente.setEsquina_inferior_izquierdo(15);
        btnAgregarCliente.setEsquina_superior_derecho(15);
        btnAgregarCliente.setEsquina_superior_izquierdo(15);
        btnAgregarCliente.setFocusPainted(false);
        btnAgregarCliente.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        btnAgregarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAgregarClienteLayout = new javax.swing.GroupLayout(panelAgregarCliente);
        panelAgregarCliente.setLayout(panelAgregarClienteLayout);
        panelAgregarClienteLayout.setHorizontalGroup(
            panelAgregarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAgregarClienteLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(labelAgregarCliente)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAgregarClienteLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnAgregarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelAgregarClienteLayout.setVerticalGroup(
            panelAgregarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAgregarClienteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelAgregarCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAgregarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        panelClientes.add(panelAgregarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, 90, 60));

        panelModificarCliente.setBackground(new java.awt.Color(0, 0, 0));
        panelModificarCliente.setEsqInferiorDerecha(15);
        panelModificarCliente.setEsqInferiorIzquierda(15);
        panelModificarCliente.setEsqSuperiorDerecha(15);
        panelModificarCliente.setEsqSuperiorIzquierda(15);

        labelModificarCliente.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelModificarCliente.setForeground(new java.awt.Color(255, 255, 255));
        labelModificarCliente.setText(" Modificar");

        btnModificarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/editar.png"))); // NOI18N
        btnModificarCliente.setText("");
        btnModificarCliente.setToolTipText("Agregar");
        btnModificarCliente.setBackground_Hover_1(new java.awt.Color(255, 153, 0));
        btnModificarCliente.setBackground_Hover_2(new java.awt.Color(169, 127, 3));
        btnModificarCliente.setBackground_No_Hover_1(new java.awt.Color(255, 204, 51));
        btnModificarCliente.setBackground_No_Hover_2(new java.awt.Color(213, 171, 3));
        btnModificarCliente.setColor_NoHover_text(new java.awt.Color(0, 0, 0));
        btnModificarCliente.setEsquina_inferior_derecho(15);
        btnModificarCliente.setEsquina_inferior_izquierdo(15);
        btnModificarCliente.setEsquina_superior_derecho(15);
        btnModificarCliente.setEsquina_superior_izquierdo(15);
        btnModificarCliente.setFocusPainted(false);
        btnModificarCliente.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        btnModificarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelModificarClienteLayout = new javax.swing.GroupLayout(panelModificarCliente);
        panelModificarCliente.setLayout(panelModificarClienteLayout);
        panelModificarClienteLayout.setHorizontalGroup(
            panelModificarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelModificarClienteLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnModificarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelModificarClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelModificarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelModificarClienteLayout.setVerticalGroup(
            panelModificarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelModificarClienteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelModificarCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        panelClientes.add(panelModificarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 90, 60));

        panelRemoverCliente.setBackground(new java.awt.Color(0, 0, 0));
        panelRemoverCliente.setEsqInferiorDerecha(15);
        panelRemoverCliente.setEsqInferiorIzquierda(15);
        panelRemoverCliente.setEsqSuperiorDerecha(15);
        panelRemoverCliente.setEsqSuperiorIzquierda(15);

        labelRemoverCliente.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelRemoverCliente.setForeground(new java.awt.Color(255, 255, 255));
        labelRemoverCliente.setText("Remover");

        btnRemoverCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/papelera-xmark.png"))); // NOI18N
        btnRemoverCliente.setText("");
        btnRemoverCliente.setToolTipText("Agregar");
        btnRemoverCliente.setBackground_Hover_1(new java.awt.Color(164, 0, 37));
        btnRemoverCliente.setBackground_Hover_2(new java.awt.Color(117, 7, 33));
        btnRemoverCliente.setBackground_No_Hover_1(new java.awt.Color(197, 0, 45));
        btnRemoverCliente.setBackground_No_Hover_2(new java.awt.Color(171, 0, 11));
        btnRemoverCliente.setColor_NoHover_text(new java.awt.Color(0, 0, 0));
        btnRemoverCliente.setEsquina_inferior_derecho(15);
        btnRemoverCliente.setEsquina_inferior_izquierdo(15);
        btnRemoverCliente.setEsquina_superior_derecho(15);
        btnRemoverCliente.setEsquina_superior_izquierdo(15);
        btnRemoverCliente.setFocusPainted(false);
        btnRemoverCliente.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        btnRemoverCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRemoverClienteLayout = new javax.swing.GroupLayout(panelRemoverCliente);
        panelRemoverCliente.setLayout(panelRemoverClienteLayout);
        panelRemoverClienteLayout.setHorizontalGroup(
            panelRemoverClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRemoverClienteLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnRemoverCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelRemoverClienteLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(labelRemoverCliente)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRemoverClienteLayout.setVerticalGroup(
            panelRemoverClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRemoverClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelRemoverCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRemoverCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        panelClientes.add(panelRemoverCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 10, 90, 60));

        layared_Round_JWC1.add(panelClientes);
        panelClientes.setBounds(210, 10, 860, 620);

        panelServicios.setBackground(new java.awt.Color(51, 51, 51));
        panelServicios.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelAdministarCitasBtn.setBackground(new java.awt.Color(0, 0, 0));
        panelAdministarCitasBtn.setEsqInferiorDerecha(30);
        panelAdministarCitasBtn.setEsqInferiorIzquierda(30);
        panelAdministarCitasBtn.setEsqSuperiorDerecha(30);
        panelAdministarCitasBtn.setEsqSuperiorIzquierda(30);
        panelAdministarCitasBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelAdministarCitasBtnMouseClicked(evt);
            }
        });

        panelAdministrarCitasefecto.setColor1(new java.awt.Color(246, 239, 167));
        panelAdministrarCitasefecto.setColor2(new java.awt.Color(255, 0, 120));
        panelAdministrarCitasefecto.setInferior_izquierdo(30);
        panelAdministrarCitasefecto.setSuperior_izquierdo(30);
        panelAdministrarCitasefecto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelAdministrarCitasefectoMouseClicked(evt);
            }
        });

        labelAdministrarCitasIcono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/booking_2460875.png"))); // NOI18N
        labelAdministrarCitasIcono.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelAdministrarCitasIconoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelAdministrarCitasefectoLayout = new javax.swing.GroupLayout(panelAdministrarCitasefecto);
        panelAdministrarCitasefecto.setLayout(panelAdministrarCitasefectoLayout);
        panelAdministrarCitasefectoLayout.setHorizontalGroup(
            panelAdministrarCitasefectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAdministrarCitasefectoLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(labelAdministrarCitasIcono)
                .addGap(18, 18, 18))
        );
        panelAdministrarCitasefectoLayout.setVerticalGroup(
            panelAdministrarCitasefectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAdministrarCitasefectoLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(labelAdministrarCitasIcono, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        labelMensajeAdministrarCitas.setFont(new java.awt.Font("Product Sans", 1, 24)); // NOI18N
        labelMensajeAdministrarCitas.setForeground(new java.awt.Color(255, 255, 255));
        labelMensajeAdministrarCitas.setText("Administar Citas");

        javax.swing.GroupLayout panelAdministarCitasBtnLayout = new javax.swing.GroupLayout(panelAdministarCitasBtn);
        panelAdministarCitasBtn.setLayout(panelAdministarCitasBtnLayout);
        panelAdministarCitasBtnLayout.setHorizontalGroup(
            panelAdministarCitasBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAdministarCitasBtnLayout.createSequentialGroup()
                .addComponent(panelAdministrarCitasefecto, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelMensajeAdministrarCitas, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        panelAdministarCitasBtnLayout.setVerticalGroup(
            panelAdministarCitasBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelAdministrarCitasefecto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
            .addGroup(panelAdministarCitasBtnLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(labelMensajeAdministrarCitas, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelServicios.add(panelAdministarCitasBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 450, 170));

        panelAdministarServiciosBtn.setBackground(new java.awt.Color(0, 0, 0));
        panelAdministarServiciosBtn.setEsqInferiorDerecha(30);
        panelAdministarServiciosBtn.setEsqInferiorIzquierda(30);
        panelAdministarServiciosBtn.setEsqSuperiorDerecha(30);
        panelAdministarServiciosBtn.setEsqSuperiorIzquierda(30);
        panelAdministarServiciosBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelAdministarServiciosBtnMouseClicked(evt);
            }
        });

        panelAdministrarServiciosEfecto.setColor1(new java.awt.Color(193, 252, 211));
        panelAdministrarServiciosEfecto.setColor2(new java.awt.Color(12, 205, 163));
        panelAdministrarServiciosEfecto.setInferior_izquierdo(30);
        panelAdministrarServiciosEfecto.setSuperior_izquierdo(30);
        panelAdministrarServiciosEfecto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelAdministrarServiciosEfectoMouseClicked(evt);
            }
        });

        labelAdministrarserviciosIcono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/salon_1057317.png"))); // NOI18N
        labelAdministrarserviciosIcono.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelAdministrarserviciosIconoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelAdministrarServiciosEfectoLayout = new javax.swing.GroupLayout(panelAdministrarServiciosEfecto);
        panelAdministrarServiciosEfecto.setLayout(panelAdministrarServiciosEfectoLayout);
        panelAdministrarServiciosEfectoLayout.setHorizontalGroup(
            panelAdministrarServiciosEfectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAdministrarServiciosEfectoLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(labelAdministrarserviciosIcono)
                .addGap(18, 18, 18))
        );
        panelAdministrarServiciosEfectoLayout.setVerticalGroup(
            panelAdministrarServiciosEfectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAdministrarServiciosEfectoLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(labelAdministrarserviciosIcono)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        labelMensajeAdministrarServicios.setFont(new java.awt.Font("Product Sans", 1, 24)); // NOI18N
        labelMensajeAdministrarServicios.setForeground(new java.awt.Color(255, 255, 255));
        labelMensajeAdministrarServicios.setText("Administar Servicios");

        javax.swing.GroupLayout panelAdministarServiciosBtnLayout = new javax.swing.GroupLayout(panelAdministarServiciosBtn);
        panelAdministarServiciosBtn.setLayout(panelAdministarServiciosBtnLayout);
        panelAdministarServiciosBtnLayout.setHorizontalGroup(
            panelAdministarServiciosBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAdministarServiciosBtnLayout.createSequentialGroup()
                .addComponent(panelAdministrarServiciosEfecto, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelMensajeAdministrarServicios)
                .addGap(0, 35, Short.MAX_VALUE))
        );
        panelAdministarServiciosBtnLayout.setVerticalGroup(
            panelAdministarServiciosBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelAdministrarServiciosEfecto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
            .addGroup(panelAdministarServiciosBtnLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(labelMensajeAdministrarServicios)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelServicios.add(panelAdministarServiciosBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, 450, 170));
        panelAdministarServiciosBtn.getAccessibleContext().setAccessibleDescription("");

        layared_Round_JWC1.add(panelServicios);
        panelServicios.setBounds(210, 10, 860, 620);

        panelProductos.setBackground(new java.awt.Color(51, 51, 51));
        panelProductos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaProductos.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaProductos.setFont(new java.awt.Font("Product Sans", 0, 14)); // NOI18N
        ScrolltablaProductos.setViewportView(tablaProductos);

        panelProductos.add(ScrolltablaProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 850, 490));

        panelBarraBusquedaProducto.setBackground(new java.awt.Color(0, 0, 0));
        panelBarraBusquedaProducto.setEsqInferiorDerecha(30);
        panelBarraBusquedaProducto.setEsqInferiorIzquierda(30);
        panelBarraBusquedaProducto.setEsqSuperiorDerecha(30);
        panelBarraBusquedaProducto.setEsqSuperiorIzquierda(30);

        barraBusquedaProductos.setBackground(new java.awt.Color(0, 0, 0));
        barraBusquedaProductos.setFont(new java.awt.Font("JetBrainsMono NF", 0, 13)); // NOI18N
        barraBusquedaProductos.setForeground(new java.awt.Color(255, 255, 255));
        barraBusquedaProductos.setBorder(null);
        barraBusquedaProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barraBusquedaProductosActionPerformed(evt);
            }
        });
        barraBusquedaProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                barraBusquedaProductosKeyTyped(evt);
            }
        });

        btnBuscarProducto.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/busqueda (2).png"))); // NOI18N
        btnBuscarProducto.setText("");
        btnBuscarProducto.setBackground_Hover_1(new java.awt.Color(0, 0, 204));
        btnBuscarProducto.setBackground_Hover_2(new java.awt.Color(0, 0, 153));
        btnBuscarProducto.setBackground_No_Hover_1(new java.awt.Color(0, 0, 0));
        btnBuscarProducto.setBackground_No_Hover_2(new java.awt.Color(0, 0, 0));
        btnBuscarProducto.setEsquina_inferior_derecho(30);
        btnBuscarProducto.setEsquina_inferior_izquierdo(30);
        btnBuscarProducto.setEsquina_superior_derecho(30);
        btnBuscarProducto.setEsquina_superior_izquierdo(30);
        btnBuscarProducto.setFocusPainted(false);
        btnBuscarProducto.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N

        javax.swing.GroupLayout panelBarraBusquedaProductoLayout = new javax.swing.GroupLayout(panelBarraBusquedaProducto);
        panelBarraBusquedaProducto.setLayout(panelBarraBusquedaProductoLayout);
        panelBarraBusquedaProductoLayout.setHorizontalGroup(
            panelBarraBusquedaProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBarraBusquedaProductoLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(barraBusquedaProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelBarraBusquedaProductoLayout.setVerticalGroup(
            panelBarraBusquedaProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBarraBusquedaProductoLayout.createSequentialGroup()
                .addGroup(panelBarraBusquedaProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(barraBusquedaProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panelProductos.add(panelBarraBusquedaProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 260, 30));

        labelMarca.setForeground(new java.awt.Color(255, 255, 255));
        labelMarca.setText("Marca");
        panelProductos.add(labelMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, -1, -1));

        comboboxMarcaVistaProductos.setBackground(new java.awt.Color(0, 0, 0));
        comboboxMarcaVistaProductos.setFont(new java.awt.Font("Product Sans", 1, 12)); // NOI18N
        comboboxMarcaVistaProductos.setForeground(new java.awt.Color(255, 255, 255));
        comboboxMarcaVistaProductos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todas" }));
        comboboxMarcaVistaProductos.setBorder(null);
        comboboxMarcaVistaProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxMarcaVistaProductosActionPerformed(evt);
            }
        });
        panelProductos.add(comboboxMarcaVistaProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 40, 80, 40));

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

        panelProductos.add(panelComboboxMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 40, 70, 40));

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

        panelProductos.add(panelComboboxMarcaEfecto, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 30, 90, 50));

        labelCategoria.setForeground(new java.awt.Color(255, 255, 255));
        labelCategoria.setText("Categoria");
        panelProductos.add(labelCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, -1, -1));

        comboboxCategoriaVistaProductos.setBackground(new java.awt.Color(0, 0, 0));
        comboboxCategoriaVistaProductos.setFont(new java.awt.Font("Product Sans", 1, 12)); // NOI18N
        comboboxCategoriaVistaProductos.setForeground(new java.awt.Color(255, 255, 255));
        comboboxCategoriaVistaProductos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todas" }));
        comboboxCategoriaVistaProductos.setBorder(null);
        comboboxCategoriaVistaProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxCategoriaVistaProductosActionPerformed(evt);
            }
        });
        panelProductos.add(comboboxCategoriaVistaProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 40, 80, 40));

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

        panelProductos.add(panelComboboxCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, 70, 40));

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

        panelProductos.add(panelComboboxCategoriaEfecto, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 30, 90, 50));

        panelAgregarProducto.setBackground(new java.awt.Color(0, 0, 0));
        panelAgregarProducto.setEsqInferiorDerecha(15);
        panelAgregarProducto.setEsqInferiorIzquierda(15);
        panelAgregarProducto.setEsqSuperiorDerecha(15);
        panelAgregarProducto.setEsqSuperiorIzquierda(15);

        labelAgregarProducto.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelAgregarProducto.setForeground(new java.awt.Color(255, 255, 255));
        labelAgregarProducto.setText("Agregar");

        btnAgregarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/capa-mas.png"))); // NOI18N
        btnAgregarProducto.setText("");
        btnAgregarProducto.setToolTipText("Agregar");
        btnAgregarProducto.setBackground_Hover_1(new java.awt.Color(3, 142, 62));
        btnAgregarProducto.setBackground_Hover_2(new java.awt.Color(5, 111, 44));
        btnAgregarProducto.setBackground_No_Hover_1(new java.awt.Color(0, 197, 97));
        btnAgregarProducto.setBackground_No_Hover_2(new java.awt.Color(1, 140, 69));
        btnAgregarProducto.setColor_NoHover_text(new java.awt.Color(0, 0, 0));
        btnAgregarProducto.setEsquina_inferior_derecho(15);
        btnAgregarProducto.setEsquina_inferior_izquierdo(15);
        btnAgregarProducto.setEsquina_superior_derecho(15);
        btnAgregarProducto.setEsquina_superior_izquierdo(15);
        btnAgregarProducto.setFocusPainted(false);
        btnAgregarProducto.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        btnAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAgregarProductoLayout = new javax.swing.GroupLayout(panelAgregarProducto);
        panelAgregarProducto.setLayout(panelAgregarProductoLayout);
        panelAgregarProductoLayout.setHorizontalGroup(
            panelAgregarProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAgregarProductoLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(labelAgregarProducto)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAgregarProductoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnAgregarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelAgregarProductoLayout.setVerticalGroup(
            panelAgregarProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAgregarProductoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelAgregarProducto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAgregarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        panelProductos.add(panelAgregarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 90, 60));

        panelModificarProducto.setBackground(new java.awt.Color(0, 0, 0));
        panelModificarProducto.setEsqInferiorDerecha(15);
        panelModificarProducto.setEsqInferiorIzquierda(15);
        panelModificarProducto.setEsqSuperiorDerecha(15);
        panelModificarProducto.setEsqSuperiorIzquierda(15);

        labelModificarProducto.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelModificarProducto.setForeground(new java.awt.Color(255, 255, 255));
        labelModificarProducto.setText(" Modificar");

        btnModificarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/editar.png"))); // NOI18N
        btnModificarProducto.setText("");
        btnModificarProducto.setToolTipText("Agregar");
        btnModificarProducto.setBackground_Hover_1(new java.awt.Color(255, 153, 0));
        btnModificarProducto.setBackground_Hover_2(new java.awt.Color(169, 127, 3));
        btnModificarProducto.setBackground_No_Hover_1(new java.awt.Color(255, 204, 51));
        btnModificarProducto.setBackground_No_Hover_2(new java.awt.Color(213, 171, 3));
        btnModificarProducto.setColor_NoHover_text(new java.awt.Color(0, 0, 0));
        btnModificarProducto.setEsquina_inferior_derecho(15);
        btnModificarProducto.setEsquina_inferior_izquierdo(15);
        btnModificarProducto.setEsquina_superior_derecho(15);
        btnModificarProducto.setEsquina_superior_izquierdo(15);
        btnModificarProducto.setFocusPainted(false);
        btnModificarProducto.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        btnModificarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelModificarProductoLayout = new javax.swing.GroupLayout(panelModificarProducto);
        panelModificarProducto.setLayout(panelModificarProductoLayout);
        panelModificarProductoLayout.setHorizontalGroup(
            panelModificarProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelModificarProductoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnModificarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelModificarProductoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelModificarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelModificarProductoLayout.setVerticalGroup(
            panelModificarProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelModificarProductoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelModificarProducto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        panelProductos.add(panelModificarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 90, 60));

        panelRemoverProducto.setBackground(new java.awt.Color(0, 0, 0));
        panelRemoverProducto.setEsqInferiorDerecha(15);
        panelRemoverProducto.setEsqInferiorIzquierda(15);
        panelRemoverProducto.setEsqSuperiorDerecha(15);
        panelRemoverProducto.setEsqSuperiorIzquierda(15);

        labelRemoverProducto.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelRemoverProducto.setForeground(new java.awt.Color(255, 255, 255));
        labelRemoverProducto.setText("Remover");

        btnRemoverProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/papelera-xmark.png"))); // NOI18N
        btnRemoverProducto.setText("");
        btnRemoverProducto.setToolTipText("Agregar");
        btnRemoverProducto.setBackground_Hover_1(new java.awt.Color(164, 0, 37));
        btnRemoverProducto.setBackground_Hover_2(new java.awt.Color(117, 7, 33));
        btnRemoverProducto.setBackground_No_Hover_1(new java.awt.Color(197, 0, 45));
        btnRemoverProducto.setBackground_No_Hover_2(new java.awt.Color(171, 0, 11));
        btnRemoverProducto.setColor_NoHover_text(new java.awt.Color(0, 0, 0));
        btnRemoverProducto.setEsquina_inferior_derecho(15);
        btnRemoverProducto.setEsquina_inferior_izquierdo(15);
        btnRemoverProducto.setEsquina_superior_derecho(15);
        btnRemoverProducto.setEsquina_superior_izquierdo(15);
        btnRemoverProducto.setFocusPainted(false);
        btnRemoverProducto.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        btnRemoverProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRemoverProductoLayout = new javax.swing.GroupLayout(panelRemoverProducto);
        panelRemoverProducto.setLayout(panelRemoverProductoLayout);
        panelRemoverProductoLayout.setHorizontalGroup(
            panelRemoverProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRemoverProductoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnRemoverProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelRemoverProductoLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(labelRemoverProducto)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRemoverProductoLayout.setVerticalGroup(
            panelRemoverProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRemoverProductoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelRemoverProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemoverProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        panelProductos.add(panelRemoverProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 90, 60));

        layared_Round_JWC1.add(panelProductos);
        panelProductos.setBounds(210, 10, 860, 620);

        panelEstadisticas.setBackground(new java.awt.Color(51, 51, 51));
        panelEstadisticas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout panelGraficaLayout = new javax.swing.GroupLayout(panelGrafica);
        panelGrafica.setLayout(panelGraficaLayout);
        panelGraficaLayout.setHorizontalGroup(
            panelGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 706, Short.MAX_VALUE)
        );
        panelGraficaLayout.setVerticalGroup(
            panelGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 267, Short.MAX_VALUE)
        );

        panelEstadisticas.add(panelGrafica, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 72, -1, -1));

        javax.swing.GroupLayout panelGraficaPastelLayout = new javax.swing.GroupLayout(panelGraficaPastel);
        panelGraficaPastel.setLayout(panelGraficaPastelLayout);
        panelGraficaPastelLayout.setHorizontalGroup(
            panelGraficaPastelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );
        panelGraficaPastelLayout.setVerticalGroup(
            panelGraficaPastelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 218, Short.MAX_VALUE)
        );

        panelEstadisticas.add(panelGraficaPastel, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 378, -1, -1));

        btnGenerarInforme.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerarInforme.setText("Generar informe");
        btnGenerarInforme.setBackground_Hover_1(new java.awt.Color(0, 255, 255));
        btnGenerarInforme.setBackground_Hover_2(new java.awt.Color(51, 51, 51));
        btnGenerarInforme.setBackground_No_Hover_1(new java.awt.Color(51, 51, 51));
        btnGenerarInforme.setBackground_No_Hover_2(new java.awt.Color(51, 51, 51));
        btnGenerarInforme.setEsquina_inferior_derecho(30);
        btnGenerarInforme.setEsquina_inferior_izquierdo(30);
        btnGenerarInforme.setEsquina_superior_derecho(30);
        btnGenerarInforme.setEsquina_superior_izquierdo(30);
        btnGenerarInforme.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        btnGenerarInforme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarInformeActionPerformed(evt);
            }
        });
        panelEstadisticas.add(btnGenerarInforme, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 450, -1, -1));

        layared_Round_JWC1.add(panelEstadisticas);
        panelEstadisticas.setBounds(210, 10, 860, 620);

        panelAdministrarServicios.setBackground(new java.awt.Color(51, 51, 51));
        panelAdministrarServicios.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaServicios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Precio", "Duracion"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaServicios.setFont(new java.awt.Font("Product Sans", 0, 14)); // NOI18N
        scrollTablaAdministrarServicios.setViewportView(tablaServicios);

        panelAdministrarServicios.add(scrollTablaAdministrarServicios, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 670, 520));

        panelBarraBusquedaAdministrarServicios.setBackground(new java.awt.Color(0, 0, 0));
        panelBarraBusquedaAdministrarServicios.setEsqInferiorDerecha(30);
        panelBarraBusquedaAdministrarServicios.setEsqInferiorIzquierda(30);
        panelBarraBusquedaAdministrarServicios.setEsqSuperiorDerecha(30);
        panelBarraBusquedaAdministrarServicios.setEsqSuperiorIzquierda(30);

        barraBusquedaServicios.setBackground(new java.awt.Color(0, 0, 0));
        barraBusquedaServicios.setFont(new java.awt.Font("JetBrainsMono NF", 0, 13)); // NOI18N
        barraBusquedaServicios.setForeground(new java.awt.Color(255, 255, 255));
        barraBusquedaServicios.setBorder(null);
        barraBusquedaServicios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barraBusquedaServiciosActionPerformed(evt);
            }
        });
        barraBusquedaServicios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                barraBusquedaServiciosKeyTyped(evt);
            }
        });

        btnBuscarServicios.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarServicios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/busqueda (2).png"))); // NOI18N
        btnBuscarServicios.setText("");
        btnBuscarServicios.setBackground_Hover_1(new java.awt.Color(0, 0, 204));
        btnBuscarServicios.setBackground_Hover_2(new java.awt.Color(0, 0, 153));
        btnBuscarServicios.setBackground_No_Hover_1(new java.awt.Color(0, 0, 0));
        btnBuscarServicios.setBackground_No_Hover_2(new java.awt.Color(0, 0, 0));
        btnBuscarServicios.setEsquina_inferior_derecho(30);
        btnBuscarServicios.setEsquina_inferior_izquierdo(30);
        btnBuscarServicios.setEsquina_superior_derecho(30);
        btnBuscarServicios.setEsquina_superior_izquierdo(30);
        btnBuscarServicios.setFocusPainted(false);
        btnBuscarServicios.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N

        javax.swing.GroupLayout panelBarraBusquedaAdministrarServiciosLayout = new javax.swing.GroupLayout(panelBarraBusquedaAdministrarServicios);
        panelBarraBusquedaAdministrarServicios.setLayout(panelBarraBusquedaAdministrarServiciosLayout);
        panelBarraBusquedaAdministrarServiciosLayout.setHorizontalGroup(
            panelBarraBusquedaAdministrarServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBarraBusquedaAdministrarServiciosLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(barraBusquedaServicios, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBuscarServicios, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelBarraBusquedaAdministrarServiciosLayout.setVerticalGroup(
            panelBarraBusquedaAdministrarServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBarraBusquedaAdministrarServiciosLayout.createSequentialGroup()
                .addGroup(panelBarraBusquedaAdministrarServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(barraBusquedaServicios, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarServicios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panelAdministrarServicios.add(panelBarraBusquedaAdministrarServicios, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 80, 260, 30));

        panel_IdentificadorAdministrarServicios.setColor1(new java.awt.Color(148, 213, 193));
        panel_IdentificadorAdministrarServicios.setColor2(new java.awt.Color(34, 227, 117));
        panel_IdentificadorAdministrarServicios.setInferior_derecho(60);
        panel_IdentificadorAdministrarServicios.setSuperior_derecho(60);

        labelAdministrarServicios.setFont(new java.awt.Font("Product Sans", 1, 26)); // NOI18N
        labelAdministrarServicios.setForeground(new java.awt.Color(0, 0, 0));
        labelAdministrarServicios.setText("Administrar Servicios");

        javax.swing.GroupLayout panel_IdentificadorAdministrarServiciosLayout = new javax.swing.GroupLayout(panel_IdentificadorAdministrarServicios);
        panel_IdentificadorAdministrarServicios.setLayout(panel_IdentificadorAdministrarServiciosLayout);
        panel_IdentificadorAdministrarServiciosLayout.setHorizontalGroup(
            panel_IdentificadorAdministrarServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_IdentificadorAdministrarServiciosLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(labelAdministrarServicios, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_IdentificadorAdministrarServiciosLayout.setVerticalGroup(
            panel_IdentificadorAdministrarServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_IdentificadorAdministrarServiciosLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(labelAdministrarServicios, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelAdministrarServicios.add(panel_IdentificadorAdministrarServicios, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 400, 70));

        panelAdministrarServicios_Agregar.setBackground(new java.awt.Color(0, 0, 0));
        panelAdministrarServicios_Agregar.setEsqInferiorDerecha(15);
        panelAdministrarServicios_Agregar.setEsqInferiorIzquierda(15);
        panelAdministrarServicios_Agregar.setEsqSuperiorDerecha(15);
        panelAdministrarServicios_Agregar.setEsqSuperiorIzquierda(15);

        labelAdministrarServicios_Agregar.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelAdministrarServicios_Agregar.setForeground(new java.awt.Color(255, 255, 255));
        labelAdministrarServicios_Agregar.setText("Agregar");

        btnAdministrarServicios_Agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/capa-mas.png"))); // NOI18N
        btnAdministrarServicios_Agregar.setText("");
        btnAdministrarServicios_Agregar.setToolTipText("Agregar");
        btnAdministrarServicios_Agregar.setBackground_Hover_1(new java.awt.Color(3, 142, 62));
        btnAdministrarServicios_Agregar.setBackground_Hover_2(new java.awt.Color(5, 111, 44));
        btnAdministrarServicios_Agregar.setBackground_No_Hover_1(new java.awt.Color(0, 197, 97));
        btnAdministrarServicios_Agregar.setBackground_No_Hover_2(new java.awt.Color(1, 140, 69));
        btnAdministrarServicios_Agregar.setColor_NoHover_text(new java.awt.Color(0, 0, 0));
        btnAdministrarServicios_Agregar.setEsquina_inferior_derecho(15);
        btnAdministrarServicios_Agregar.setEsquina_inferior_izquierdo(15);
        btnAdministrarServicios_Agregar.setEsquina_superior_derecho(15);
        btnAdministrarServicios_Agregar.setEsquina_superior_izquierdo(15);
        btnAdministrarServicios_Agregar.setFocusPainted(false);
        btnAdministrarServicios_Agregar.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        btnAdministrarServicios_Agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdministrarServicios_AgregarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAdministrarServicios_AgregarLayout = new javax.swing.GroupLayout(panelAdministrarServicios_Agregar);
        panelAdministrarServicios_Agregar.setLayout(panelAdministrarServicios_AgregarLayout);
        panelAdministrarServicios_AgregarLayout.setHorizontalGroup(
            panelAdministrarServicios_AgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAdministrarServicios_AgregarLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(labelAdministrarServicios_Agregar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAdministrarServicios_AgregarLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnAdministrarServicios_Agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelAdministrarServicios_AgregarLayout.setVerticalGroup(
            panelAdministrarServicios_AgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAdministrarServicios_AgregarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelAdministrarServicios_Agregar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAdministrarServicios_Agregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        panelAdministrarServicios.add(panelAdministrarServicios_Agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 90, 60));

        panelAdministrarServicios_Modificar.setBackground(new java.awt.Color(0, 0, 0));
        panelAdministrarServicios_Modificar.setEsqInferiorDerecha(15);
        panelAdministrarServicios_Modificar.setEsqInferiorIzquierda(15);
        panelAdministrarServicios_Modificar.setEsqSuperiorDerecha(15);
        panelAdministrarServicios_Modificar.setEsqSuperiorIzquierda(15);

        labelAdministrarServicios_Modificar.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelAdministrarServicios_Modificar.setForeground(new java.awt.Color(255, 255, 255));
        labelAdministrarServicios_Modificar.setText(" Modificar");

        btnAdministrarServicios_Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/editar.png"))); // NOI18N
        btnAdministrarServicios_Modificar.setText("");
        btnAdministrarServicios_Modificar.setToolTipText("Agregar");
        btnAdministrarServicios_Modificar.setBackground_Hover_1(new java.awt.Color(255, 153, 0));
        btnAdministrarServicios_Modificar.setBackground_Hover_2(new java.awt.Color(169, 127, 3));
        btnAdministrarServicios_Modificar.setBackground_No_Hover_1(new java.awt.Color(255, 204, 51));
        btnAdministrarServicios_Modificar.setBackground_No_Hover_2(new java.awt.Color(213, 171, 3));
        btnAdministrarServicios_Modificar.setColor_NoHover_text(new java.awt.Color(0, 0, 0));
        btnAdministrarServicios_Modificar.setEsquina_inferior_derecho(15);
        btnAdministrarServicios_Modificar.setEsquina_inferior_izquierdo(15);
        btnAdministrarServicios_Modificar.setEsquina_superior_derecho(15);
        btnAdministrarServicios_Modificar.setEsquina_superior_izquierdo(15);
        btnAdministrarServicios_Modificar.setFocusPainted(false);
        btnAdministrarServicios_Modificar.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        btnAdministrarServicios_Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdministrarServicios_ModificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAdministrarServicios_ModificarLayout = new javax.swing.GroupLayout(panelAdministrarServicios_Modificar);
        panelAdministrarServicios_Modificar.setLayout(panelAdministrarServicios_ModificarLayout);
        panelAdministrarServicios_ModificarLayout.setHorizontalGroup(
            panelAdministrarServicios_ModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAdministrarServicios_ModificarLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnAdministrarServicios_Modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelAdministrarServicios_ModificarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelAdministrarServicios_Modificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelAdministrarServicios_ModificarLayout.setVerticalGroup(
            panelAdministrarServicios_ModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAdministrarServicios_ModificarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelAdministrarServicios_Modificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAdministrarServicios_Modificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        panelAdministrarServicios.add(panelAdministrarServicios_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 90, 60));

        panelAdministrarServicios_Remover.setBackground(new java.awt.Color(0, 0, 0));
        panelAdministrarServicios_Remover.setEsqInferiorDerecha(15);
        panelAdministrarServicios_Remover.setEsqInferiorIzquierda(15);
        panelAdministrarServicios_Remover.setEsqSuperiorDerecha(15);
        panelAdministrarServicios_Remover.setEsqSuperiorIzquierda(15);

        labelAdministrarServicios_Remover.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelAdministrarServicios_Remover.setForeground(new java.awt.Color(255, 255, 255));
        labelAdministrarServicios_Remover.setText("Remover");

        btnAdministrarServicios_Remover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/papelera-xmark.png"))); // NOI18N
        btnAdministrarServicios_Remover.setText("");
        btnAdministrarServicios_Remover.setToolTipText("Agregar");
        btnAdministrarServicios_Remover.setBackground_Hover_1(new java.awt.Color(164, 0, 37));
        btnAdministrarServicios_Remover.setBackground_Hover_2(new java.awt.Color(117, 7, 33));
        btnAdministrarServicios_Remover.setBackground_No_Hover_1(new java.awt.Color(197, 0, 45));
        btnAdministrarServicios_Remover.setBackground_No_Hover_2(new java.awt.Color(171, 0, 11));
        btnAdministrarServicios_Remover.setColor_NoHover_text(new java.awt.Color(0, 0, 0));
        btnAdministrarServicios_Remover.setEsquina_inferior_derecho(15);
        btnAdministrarServicios_Remover.setEsquina_inferior_izquierdo(15);
        btnAdministrarServicios_Remover.setEsquina_superior_derecho(15);
        btnAdministrarServicios_Remover.setEsquina_superior_izquierdo(15);
        btnAdministrarServicios_Remover.setFocusPainted(false);
        btnAdministrarServicios_Remover.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        btnAdministrarServicios_Remover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdministrarServicios_RemoverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAdministrarServicios_RemoverLayout = new javax.swing.GroupLayout(panelAdministrarServicios_Remover);
        panelAdministrarServicios_Remover.setLayout(panelAdministrarServicios_RemoverLayout);
        panelAdministrarServicios_RemoverLayout.setHorizontalGroup(
            panelAdministrarServicios_RemoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAdministrarServicios_RemoverLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnAdministrarServicios_Remover, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelAdministrarServicios_RemoverLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(labelAdministrarServicios_Remover)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelAdministrarServicios_RemoverLayout.setVerticalGroup(
            panelAdministrarServicios_RemoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAdministrarServicios_RemoverLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelAdministrarServicios_Remover, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAdministrarServicios_Remover, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        panelAdministrarServicios.add(panelAdministrarServicios_Remover, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 90, 60));

        layared_Round_JWC1.add(panelAdministrarServicios);
        panelAdministrarServicios.setBounds(210, 10, 860, 620);

        panelAdministrarCitas.setBackground(new java.awt.Color(51, 51, 51));
        panelAdministrarCitas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_IdentificadorAdministrarCitas.setColor1(new java.awt.Color(148, 213, 193));
        panel_IdentificadorAdministrarCitas.setColor2(new java.awt.Color(34, 227, 117));
        panel_IdentificadorAdministrarCitas.setInferior_derecho(60);
        panel_IdentificadorAdministrarCitas.setSuperior_derecho(60);

        labelAdministrarCitas.setFont(new java.awt.Font("Product Sans", 1, 26)); // NOI18N
        labelAdministrarCitas.setForeground(new java.awt.Color(0, 0, 0));
        labelAdministrarCitas.setText("Administrar Citas");

        javax.swing.GroupLayout panel_IdentificadorAdministrarCitasLayout = new javax.swing.GroupLayout(panel_IdentificadorAdministrarCitas);
        panel_IdentificadorAdministrarCitas.setLayout(panel_IdentificadorAdministrarCitasLayout);
        panel_IdentificadorAdministrarCitasLayout.setHorizontalGroup(
            panel_IdentificadorAdministrarCitasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_IdentificadorAdministrarCitasLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(labelAdministrarCitas, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );
        panel_IdentificadorAdministrarCitasLayout.setVerticalGroup(
            panel_IdentificadorAdministrarCitasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_IdentificadorAdministrarCitasLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(labelAdministrarCitas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        panelAdministrarCitas.add(panel_IdentificadorAdministrarCitas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 70));

        tablaCitasAdmin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Hora", "Cliente", "Servicio", "Barbero", "Cumplida"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaCitasAdmin.setFont(new java.awt.Font("Product Sans", 0, 14)); // NOI18N
        scrollTablaCitas.setViewportView(tablaCitasAdmin);

        panelAdministrarCitas.add(scrollTablaCitas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 848, 390));

        panelBarraBusquedaCitas.setBackground(new java.awt.Color(0, 0, 0));
        panelBarraBusquedaCitas.setEsqInferiorDerecha(30);
        panelBarraBusquedaCitas.setEsqInferiorIzquierda(30);
        panelBarraBusquedaCitas.setEsqSuperiorDerecha(30);
        panelBarraBusquedaCitas.setEsqSuperiorIzquierda(30);

        barraBusquedaCitas.setBackground(new java.awt.Color(0, 0, 0));
        barraBusquedaCitas.setFont(new java.awt.Font("JetBrainsMono NF", 0, 13)); // NOI18N
        barraBusquedaCitas.setForeground(new java.awt.Color(255, 255, 255));
        barraBusquedaCitas.setBorder(null);
        barraBusquedaCitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barraBusquedaCitasActionPerformed(evt);
            }
        });
        barraBusquedaCitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                barraBusquedaCitasKeyTyped(evt);
            }
        });

        btnBuscarCita.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarCita.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/busqueda (2).png"))); // NOI18N
        btnBuscarCita.setText("");
        btnBuscarCita.setBackground_Hover_1(new java.awt.Color(0, 0, 204));
        btnBuscarCita.setBackground_Hover_2(new java.awt.Color(0, 0, 153));
        btnBuscarCita.setBackground_No_Hover_1(new java.awt.Color(0, 0, 0));
        btnBuscarCita.setBackground_No_Hover_2(new java.awt.Color(0, 0, 0));
        btnBuscarCita.setEsquina_inferior_derecho(30);
        btnBuscarCita.setEsquina_inferior_izquierdo(30);
        btnBuscarCita.setEsquina_superior_derecho(30);
        btnBuscarCita.setEsquina_superior_izquierdo(30);
        btnBuscarCita.setFocusPainted(false);
        btnBuscarCita.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N

        javax.swing.GroupLayout panelBarraBusquedaCitasLayout = new javax.swing.GroupLayout(panelBarraBusquedaCitas);
        panelBarraBusquedaCitas.setLayout(panelBarraBusquedaCitasLayout);
        panelBarraBusquedaCitasLayout.setHorizontalGroup(
            panelBarraBusquedaCitasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBarraBusquedaCitasLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(barraBusquedaCitas, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBuscarCita, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelBarraBusquedaCitasLayout.setVerticalGroup(
            panelBarraBusquedaCitasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBarraBusquedaCitasLayout.createSequentialGroup()
                .addGroup(panelBarraBusquedaCitasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(barraBusquedaCitas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panelAdministrarCitas.add(panelBarraBusquedaCitas, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 89, -1, -1));

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

        panelAdministrarCitas.add(panelCancelarCita, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 30, 90, 60));

        layared_Round_JWC1.add(panelAdministrarCitas);
        panelAdministrarCitas.setBounds(210, 10, 860, 620);

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

    private void btnEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmpleadosActionPerformed
        cambiarVisibilidadInterfazEmpleados(true);
        cambiarVisivilidadInterfazBarberos(false);
        cambiarVisivilidadInterfazClientes(false);
        cambiarVisivilidadInterfazServicios(false);
        cambiarVisivilidadInterfazProductos(false);
      //  cambiarVisivilidadInterfazEstadisticas(false);
        cambiarVisivilidadInterfazAdministrarServicios(false);
        cambiarVisivilidadInterfazAdministrarCitas(false);

    }//GEN-LAST:event_btnEmpleadosActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        JOptionPane.showMessageDialog(rootPane, "ADIOS ADMINISTRADOR !...");
        ventanaAdministrador.setVisible(false);
        this.dispose();

        btnCerrarSesion.setBackground_No_Hover_1(new java.awt.Color(27, 27, 27));
        btnCerrarSesion.setBackground_No_Hover_2(new java.awt.Color(27, 27, 27));
        btnCerrarSesion.setForeground(new Color(204, 204, 204));

        inicio.setVisible(true);

    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnServiciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnServiciosActionPerformed
        cambiarVisibilidadInterfazEmpleados(false);
        cambiarVisivilidadInterfazBarberos(false);
        cambiarVisivilidadInterfazClientes(false);
        cambiarVisivilidadInterfazServicios(true);
        cambiarVisivilidadInterfazProductos(false);
     //   cambiarVisivilidadInterfazEstadisticas(false);
        cambiarVisivilidadInterfazAdministrarServicios(false);
        cambiarVisivilidadInterfazAdministrarCitas(false);
    }//GEN-LAST:event_btnServiciosActionPerformed

    private void btnProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosActionPerformed
        cambiarVisibilidadInterfazEmpleados(false);
        cambiarVisivilidadInterfazBarberos(false);
        cambiarVisivilidadInterfazClientes(false);
        cambiarVisivilidadInterfazServicios(false);
        cambiarVisivilidadInterfazProductos(true);
      //  cambiarVisivilidadInterfazEstadisticas(false);
        cambiarVisivilidadInterfazAdministrarServicios(false);
        cambiarVisivilidadInterfazAdministrarCitas(false);
    }//GEN-LAST:event_btnProductosActionPerformed

    private void btnEstadisticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstadisticasActionPerformed
        cambiarVisibilidadInterfazEmpleados(false);
        cambiarVisivilidadInterfazBarberos(false);
        cambiarVisivilidadInterfazClientes(false);
        cambiarVisivilidadInterfazServicios(false);
        cambiarVisivilidadInterfazProductos(false);
     //   cambiarVisivilidadInterfazEstadisticas(true);
        cambiarVisivilidadInterfazAdministrarServicios(false);
        cambiarVisivilidadInterfazAdministrarCitas(false);
    }//GEN-LAST:event_btnEstadisticasActionPerformed

    private void btnGenerarInformeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarInformeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGenerarInformeActionPerformed

    private void barraBusquedaEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barraBusquedaEmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_barraBusquedaEmpleadoActionPerformed

    private void btnModificarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarEmpleadoActionPerformed
        if (estaSelccionadaTablaUsuarios()) {
            try {
                usuarioAdmin.leerUsuarios();
                actualizarUsuario();
                formEditarUsuarios.setVisible(true);
                formEditarUsuarios.toFront();
                hayCampoSeleccionadoUsuarios = false;
            } catch (SQLException ex) {
                Logger.getLogger(InicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Selecciona un empleado de la tabla para modificar", "AVISO", JOptionPane.WARNING_MESSAGE);

        }


    }//GEN-LAST:event_btnModificarEmpleadoActionPerformed

    private void panelAdministarCitasBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAdministarCitasBtnMouseClicked
        cambiarVisibilidadInterfazEmpleados(false);
        cambiarVisivilidadInterfazBarberos(false);
        cambiarVisivilidadInterfazClientes(false);
        cambiarVisivilidadInterfazServicios(false);
        cambiarVisivilidadInterfazProductos(false);
      //  cambiarVisivilidadInterfazEstadisticas(false);
        cambiarVisivilidadInterfazAdministrarServicios(false);
        cambiarVisivilidadInterfazAdministrarCitas(true);
    }//GEN-LAST:event_panelAdministarCitasBtnMouseClicked

    private void panelAdministarServiciosBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAdministarServiciosBtnMouseClicked
        cambiarVisibilidadInterfazEmpleados(false);
        cambiarVisivilidadInterfazBarberos(false);
        cambiarVisivilidadInterfazClientes(false);
        cambiarVisivilidadInterfazServicios(false);
        cambiarVisivilidadInterfazProductos(false);
      //  cambiarVisivilidadInterfazEstadisticas(false);
        cambiarVisivilidadInterfazAdministrarServicios(true);
        cambiarVisivilidadInterfazAdministrarCitas(false);
    }//GEN-LAST:event_panelAdministarServiciosBtnMouseClicked

    private void barraBusquedaProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barraBusquedaProductosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_barraBusquedaProductosActionPerformed

    private void barraBusquedaServiciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barraBusquedaServiciosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_barraBusquedaServiciosActionPerformed

    private void barraBusquedaCitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barraBusquedaCitasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_barraBusquedaCitasActionPerformed

    private void btnBarberosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBarberosActionPerformed
        cambiarVisibilidadInterfazEmpleados(false);
        cambiarVisivilidadInterfazBarberos(true);
        cambiarVisivilidadInterfazClientes(false);
        cambiarVisivilidadInterfazServicios(false);
        cambiarVisivilidadInterfazProductos(false);
     //   cambiarVisivilidadInterfazEstadisticas(false);
        cambiarVisivilidadInterfazAdministrarServicios(false);
        cambiarVisivilidadInterfazAdministrarCitas(false);     // TODO add your handling code here:
    }//GEN-LAST:event_btnBarberosActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        cambiarVisibilidadInterfazEmpleados(false);
        cambiarVisivilidadInterfazBarberos(false);
        cambiarVisivilidadInterfazClientes(true);
        cambiarVisivilidadInterfazServicios(false);
        cambiarVisivilidadInterfazProductos(false);
     //   cambiarVisivilidadInterfazEstadisticas(false);
        cambiarVisivilidadInterfazAdministrarServicios(false);
        cambiarVisivilidadInterfazAdministrarCitas(false);         // TODO add your handling code here:
    }//GEN-LAST:event_btnClientesActionPerformed

    private void barraBusquedaClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barraBusquedaClientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_barraBusquedaClientesActionPerformed

    private void btnRemoverEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverEmpleadoActionPerformed

        if (hayCampoSeleccionadoUsuarios) {
            int result = JOptionPane.showConfirmDialog(null, "¿Deseas remover este empleado?", "Confirmación", JOptionPane.YES_NO_OPTION);

            if (result == JOptionPane.YES_OPTION) {
                removerUsuario();
                hayCampoSeleccionadoUsuarios = false;
                JOptionPane.showMessageDialog(null, "Usuario removido");

            }
        } else {

            JOptionPane.showMessageDialog(null, "Selecciona un empleado para remover", "AVISO", JOptionPane.WARNING_MESSAGE);

        }


    }//GEN-LAST:event_btnRemoverEmpleadoActionPerformed

    private void btnAgregarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarEmpleadoActionPerformed
        formUsuarios.setVisible(true);
        formUsuarios.toFront();

    }//GEN-LAST:event_btnAgregarEmpleadoActionPerformed

    private void btnBuscarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarEmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarEmpleadoActionPerformed

    private void btnAgregarBarberoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarBarberoActionPerformed
        formBarberos.setVisible(true);
        formBarberos.toFront();


    }//GEN-LAST:event_btnAgregarBarberoActionPerformed

    private void btnRemoverBarberoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverBarberoActionPerformed

        if (hayCampoSeleccionadoBarberos) {
            int result = JOptionPane.showConfirmDialog(null, "¿Deseas remover este barbero?", "Confirmación", JOptionPane.YES_NO_OPTION);

            if (result == JOptionPane.YES_OPTION) {
                removerBarbero();
                actualizarTablaCitas();
                hayCampoSeleccionadoBarberos = false;
                JOptionPane.showMessageDialog(null, "Barbero removido");

            }
        } else {

            JOptionPane.showMessageDialog(null, "Selecciona un barbero para remover", "AVISO", JOptionPane.WARNING_MESSAGE);

        }


    }//GEN-LAST:event_btnRemoverBarberoActionPerformed

    private void btnModificarBarberoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarBarberoActionPerformed
        if (estaSelccionadaTablaBarberos()) {
            try {
                barberoAdmin.leerBarberos();
                actualizarBarbero();
                formEditarBarberos.setVisible(true);
                formEditarBarberos.toFront();
                hayCampoSeleccionadoBarberos = false;
            } catch (SQLException ex) {
                Logger.getLogger(InicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Selecciona un barbero de la tabla para modificar", "AVISO", JOptionPane.WARNING_MESSAGE);

        }


    }//GEN-LAST:event_btnModificarBarberoActionPerformed

    private void btnModificarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarClienteActionPerformed
        if (estaSelccionadaTablaClientes()) {
            try {
                clienteAdmin.leerClientes();
                actualizarCliente();
                formEditarClientes.setVisible(true);
                formEditarClientes.toFront();
                hayCampoSeleccionadoCliente = false;
            } catch (SQLException ex) {
                Logger.getLogger(InicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona un cliente de la tabla para modificar", "AVISO", JOptionPane.WARNING_MESSAGE);

        }

    }//GEN-LAST:event_btnModificarClienteActionPerformed

    private void btnRemoverClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverClienteActionPerformed
        if (hayCampoSeleccionadoCliente) {
            int result = JOptionPane.showConfirmDialog(null, "¿Deseas remover este cliente?", "Confirmación", JOptionPane.YES_NO_OPTION);

            if (result == JOptionPane.YES_OPTION) {
                removerCliente();
                actualizarTablaCitas();

                hayCampoSeleccionadoCliente = false;
                JOptionPane.showMessageDialog(null, "Cliente removido");

            }
        } else {

            JOptionPane.showMessageDialog(null, "Selecciona un cliente para remover", "AVISO", JOptionPane.WARNING_MESSAGE);

        }


    }//GEN-LAST:event_btnRemoverClienteActionPerformed

    private void btnAgregarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarClienteActionPerformed

        formClientes.setVisible(true);
        formClientes.toFront();


    }//GEN-LAST:event_btnAgregarClienteActionPerformed

    private void btnModificarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarProductoActionPerformed

        if (estaSelccionadaTablaProductos()) {
            try {
                productoAdmin.leerProductos();
                actualizarProducto();
                formEditarProductos.setVisible(true);
                formEditarProductos.toFront();
                hayCampoSeleccionadoProducto = false;
            } catch (SQLException ex) {
                Logger.getLogger(InicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

            JOptionPane.showMessageDialog(null, "Selecciona un producto en la tabla para modificar", "AVISO", JOptionPane.WARNING_MESSAGE);

        }


    }//GEN-LAST:event_btnModificarProductoActionPerformed

    private void btnRemoverProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverProductoActionPerformed

        if (hayCampoSeleccionadoProducto) {
            int result = JOptionPane.showConfirmDialog(null, "¿Deseas remover este producto?", "Confirmación", JOptionPane.YES_NO_OPTION);

            if (result == JOptionPane.YES_OPTION) {
                removerProducto();
                hayCampoSeleccionadoProducto = false;
                JOptionPane.showMessageDialog(null, "Producto removido");

            }
        } else {

            JOptionPane.showMessageDialog(null, "Selecciona un producto para remover", "AVISO", JOptionPane.WARNING_MESSAGE);

        }


    }//GEN-LAST:event_btnRemoverProductoActionPerformed

    private void btnAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductoActionPerformed
        formProductos.setVisible(true);
        formProductos.toFront();

    }//GEN-LAST:event_btnAgregarProductoActionPerformed

    private void btnAdministrarServicios_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdministrarServicios_ModificarActionPerformed

        if (estaSelccionadaTablaServicios()) {
            try {
                servicioAdmin.leerServicios();
                actualizarServicio();
                formEditarServicios.setVisible(true);
                formEditarServicios.toFront();
                hayCampoSeleccionadoServicio = false;
            } catch (SQLException ex) {
                Logger.getLogger(InicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Selecciona un servicio de la tabla para modificar", "AVISO", JOptionPane.WARNING_MESSAGE);

        }

    }//GEN-LAST:event_btnAdministrarServicios_ModificarActionPerformed

    private void btnAdministrarServicios_RemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdministrarServicios_RemoverActionPerformed

        if (hayCampoSeleccionadoServicio) {
            int result = JOptionPane.showConfirmDialog(null, "¿Deseas remover este servicio?", "Confirmación", JOptionPane.YES_NO_OPTION);

            if (result == JOptionPane.YES_OPTION) {
                removerServicio();
                actualizarTablaCitas();
                hayCampoSeleccionadoServicio = false;
                JOptionPane.showMessageDialog(null, "Servicio removido");

            }
        } else {

            JOptionPane.showMessageDialog(null, "Selecciona un servicio para remover", "AVISO", JOptionPane.WARNING_MESSAGE);

        }


    }//GEN-LAST:event_btnAdministrarServicios_RemoverActionPerformed

    private void btnAdministrarServicios_AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdministrarServicios_AgregarActionPerformed
        formServicios.setVisible(true);
        formProductos.toFront();
    }//GEN-LAST:event_btnAdministrarServicios_AgregarActionPerformed

    private void comboboxCategoriaVistaProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxCategoriaVistaProductosActionPerformed

        aplicarFiltroCombinado();


    }//GEN-LAST:event_comboboxCategoriaVistaProductosActionPerformed

    private void panelAdministrarCitasefectoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAdministrarCitasefectoMouseClicked
        cambiarVisibilidadInterfazEmpleados(false);
        cambiarVisivilidadInterfazBarberos(false);
        cambiarVisivilidadInterfazClientes(false);
        cambiarVisivilidadInterfazServicios(false);
        cambiarVisivilidadInterfazProductos(false);
    //    cambiarVisivilidadInterfazEstadisticas(false);
        cambiarVisivilidadInterfazAdministrarServicios(false);
        cambiarVisivilidadInterfazAdministrarCitas(true);        // TODO add your handling code here:
    }//GEN-LAST:event_panelAdministrarCitasefectoMouseClicked

    private void labelAdministrarCitasIconoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelAdministrarCitasIconoMouseClicked
        cambiarVisibilidadInterfazEmpleados(false);
        cambiarVisivilidadInterfazBarberos(false);
        cambiarVisivilidadInterfazClientes(false);
        cambiarVisivilidadInterfazServicios(false);
        cambiarVisivilidadInterfazProductos(false);
    //    cambiarVisivilidadInterfazEstadisticas(false);
        cambiarVisivilidadInterfazAdministrarServicios(false);
        cambiarVisivilidadInterfazAdministrarCitas(true);         // TODO add your handling code here:
    }//GEN-LAST:event_labelAdministrarCitasIconoMouseClicked

    private void panelAdministrarServiciosEfectoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAdministrarServiciosEfectoMouseClicked
        cambiarVisibilidadInterfazEmpleados(false);
        cambiarVisivilidadInterfazBarberos(false);
        cambiarVisivilidadInterfazClientes(false);
        cambiarVisivilidadInterfazServicios(false);
        cambiarVisivilidadInterfazProductos(false);
     //   cambiarVisivilidadInterfazEstadisticas(false);
        cambiarVisivilidadInterfazAdministrarServicios(true);
        cambiarVisivilidadInterfazAdministrarCitas(false);      // TODO add your handling code here:
    }//GEN-LAST:event_panelAdministrarServiciosEfectoMouseClicked

    private void labelAdministrarserviciosIconoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelAdministrarserviciosIconoMouseClicked
        cambiarVisibilidadInterfazEmpleados(false);
        cambiarVisivilidadInterfazBarberos(false);
        cambiarVisivilidadInterfazClientes(false);
        cambiarVisivilidadInterfazServicios(false);
        cambiarVisivilidadInterfazProductos(false);
       // cambiarVisivilidadInterfazEstadisticas(false);
        cambiarVisivilidadInterfazAdministrarServicios(true);
        cambiarVisivilidadInterfazAdministrarCitas(false);       // TODO add your handling code here:
    }//GEN-LAST:event_labelAdministrarserviciosIconoMouseClicked

    private void comboBoxBarberosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxBarberosActionPerformed

        String seleccion = (String) comboBoxBarberos.getSelectedItem();

        if ("Todos".equals(seleccion)) {
            mostrarTodosLosBarberos();
        } else {
            boolean estaDisponible = "Disponible".equals(seleccion);
            actualizarTablaBarberosConFiltro(estaDisponible);

        }


    }//GEN-LAST:event_comboBoxBarberosActionPerformed

    private void comboboxMarcaVistaProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxMarcaVistaProductosActionPerformed
        aplicarFiltroCombinado();

    }//GEN-LAST:event_comboboxMarcaVistaProductosActionPerformed

    private void barraBusquedaEmpleadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_barraBusquedaEmpleadoKeyTyped
        String textoBusqueda = barraBusquedaEmpleado.getText().trim();
        if (Character.isLetterOrDigit(evt.getKeyChar())) {
            textoBusqueda += evt.getKeyChar();
        }
        buscarYActualizarTablaUsuarios(textoBusqueda);
    }//GEN-LAST:event_barraBusquedaEmpleadoKeyTyped

    private void barraBusquedaClientesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_barraBusquedaClientesKeyTyped
        String textoBusqueda = barraBusquedaClientes.getText().trim();
        if (Character.isLetterOrDigit(evt.getKeyChar())) {
            textoBusqueda += evt.getKeyChar();
        }
        actualizarTablaClientesConBusqueda(textoBusqueda);
    }//GEN-LAST:event_barraBusquedaClientesKeyTyped

    private void barraBusquedaProductosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_barraBusquedaProductosKeyTyped
        String textoBusqueda = barraBusquedaProductos.getText().trim();
        if (Character.isLetterOrDigit(evt.getKeyChar())) {
            textoBusqueda += evt.getKeyChar();
        }
        actualizarTablaProductosConBusqueda(textoBusqueda);
    }//GEN-LAST:event_barraBusquedaProductosKeyTyped

    private void barraBusquedaCitasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_barraBusquedaCitasKeyTyped
        String textoBusqueda = barraBusquedaCitas.getText().trim();
        if (Character.isLetterOrDigit(evt.getKeyChar())) {
            textoBusqueda += evt.getKeyChar();
        }
        actualizarTablaCitasConBusqueda(textoBusqueda);
    }//GEN-LAST:event_barraBusquedaCitasKeyTyped

    private void barraBusquedaServiciosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_barraBusquedaServiciosKeyTyped
        String textoBusqueda = barraBusquedaServicios.getText().trim();
        if (Character.isLetterOrDigit(evt.getKeyChar())) {
            textoBusqueda += evt.getKeyChar();
        }

        actualizarTablaServiciosConBusqueda(textoBusqueda);
    }//GEN-LAST:event_barraBusquedaServiciosKeyTyped

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrolltablaProductos;
    private javax.swing.JTextField barraBusquedaCitas;
    private javax.swing.JTextField barraBusquedaClientes;
    private javax.swing.JTextField barraBusquedaEmpleado;
    private javax.swing.JTextField barraBusquedaProductos;
    private javax.swing.JTextField barraBusquedaServicios;
    public static btn_efecto01_jwc.btn_efecto_V1_JWC btnAdministrarServicios_Agregar;
    public static btn_efecto01_jwc.btn_efecto_V1_JWC btnAdministrarServicios_Modificar;
    public static btn_efecto01_jwc.btn_efecto_V1_JWC btnAdministrarServicios_Remover;
    public static btn_efecto01_jwc.btn_efecto_V1_JWC btnAgregarBarbero;
    public static btn_efecto01_jwc.btn_efecto_V1_JWC btnAgregarCliente;
    public static btn_efecto01_jwc.btn_efecto_V1_JWC btnAgregarEmpleado;
    public static btn_efecto01_jwc.btn_efecto_V1_JWC btnAgregarProducto;
    private btn_efecto01_jwc.btn_efecto_V1_JWC btnBarberos;
    public static btn_efecto01_jwc.btn_efecto_V1_JWC btnBarraBusquedaCliente;
    public static btn_efecto01_jwc.btn_efecto_V1_JWC btnBuscarCita;
    public static btn_efecto01_jwc.btn_efecto_V1_JWC btnBuscarEmpleado;
    public static btn_efecto01_jwc.btn_efecto_V1_JWC btnBuscarProducto;
    public static btn_efecto01_jwc.btn_efecto_V1_JWC btnBuscarServicios;
    public static btn_efecto01_jwc.btn_efecto_V1_JWC btnCancelarCita;
    public static btn_efecto01_jwc.btn_efecto_V1_JWC btnCerrarSesion;
    private btn_efecto01_jwc.btn_efecto_V1_JWC btnClientes;
    private btn_efecto01_jwc.btn_efecto_V1_JWC btnEmpleados;
    private btn_efecto01_jwc.btn_efecto_V1_JWC btnEstadisticas;
    private btn_efecto01_jwc.btn_efecto_V1_JWC btnGenerarInforme;
    private btn_efecto01_jwc.btn_efecto_V1_JWC btnMinimizar;
    public static btn_efecto01_jwc.btn_efecto_V1_JWC btnModificarBarbero;
    public static btn_efecto01_jwc.btn_efecto_V1_JWC btnModificarCliente;
    public static btn_efecto01_jwc.btn_efecto_V1_JWC btnModificarEmpleado;
    public static btn_efecto01_jwc.btn_efecto_V1_JWC btnModificarProducto;
    private btn_efecto01_jwc.btn_efecto_V1_JWC btnProductos;
    public static btn_efecto01_jwc.btn_efecto_V1_JWC btnRemoverBarbero;
    public static btn_efecto01_jwc.btn_efecto_V1_JWC btnRemoverCliente;
    public static btn_efecto01_jwc.btn_efecto_V1_JWC btnRemoverEmpleado;
    public static btn_efecto01_jwc.btn_efecto_V1_JWC btnRemoverProducto;
    private btn_efecto01_jwc.btn_efecto_V1_JWC btnSalir;
    private btn_efecto01_jwc.btn_efecto_V1_JWC btnServicios;
    private javax.swing.JComboBox<String> comboBoxBarberos;
    public static javax.swing.JComboBox<String> comboboxCategoriaVistaProductos;
    public static javax.swing.JComboBox<String> comboboxMarcaVistaProductos;
    private javax.swing.JLabel iconoBarberos;
    private javax.swing.JLabel iconoCerrarSesion;
    private javax.swing.JLabel iconoClientes;
    private javax.swing.JLabel iconoEmpleados;
    private javax.swing.JLabel iconoEstadisticas;
    private javax.swing.JLabel iconoProductos;
    private javax.swing.JLabel iconoServicios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel labelAdministrarCitas;
    private javax.swing.JLabel labelAdministrarCitasIcono;
    private javax.swing.JLabel labelAdministrarServicios;
    private javax.swing.JLabel labelAdministrarServicios_Agregar;
    private javax.swing.JLabel labelAdministrarServicios_Modificar;
    private javax.swing.JLabel labelAdministrarServicios_Remover;
    private javax.swing.JLabel labelAdministrarserviciosIcono;
    private javax.swing.JLabel labelAgregarBarbero;
    private javax.swing.JLabel labelAgregarCliente;
    private javax.swing.JLabel labelAgregarEmpleado;
    private javax.swing.JLabel labelAgregarProducto;
    private javax.swing.JLabel labelCancelarCita;
    private javax.swing.JLabel labelCategoria;
    private javax.swing.JLabel labelMarca;
    private javax.swing.JLabel labelMensajeAdministrarCitas;
    private javax.swing.JLabel labelMensajeAdministrarServicios;
    private javax.swing.JLabel labelMensajeEstadoBarberosCombobox;
    private javax.swing.JLabel labelModificarBarbero;
    private javax.swing.JLabel labelModificarCliente;
    private javax.swing.JLabel labelModificarEmpleado;
    private javax.swing.JLabel labelModificarProducto;
    private javax.swing.JLabel labelRemoverBarbero;
    private javax.swing.JLabel labelRemoverCliente;
    private javax.swing.JLabel labelRemoverEmpleado;
    private javax.swing.JLabel labelRemoverProducto;
    private swing.Layared_Round_JWC layared_Round_JWC1;
    private grafico_barrav1_jwc.num_max num_max1;
    private swing.Panel_Round_JWC panelAdministarCitasBtn;
    private swing.Panel_Round_JWC panelAdministarServiciosBtn;
    private javax.swing.JPanel panelAdministrarCitas;
    private panel_degradado_jwc.Panel_Round_Degradado_JWC panelAdministrarCitasefecto;
    private javax.swing.JPanel panelAdministrarServicios;
    private panel_degradado_jwc.Panel_Round_Degradado_JWC panelAdministrarServiciosEfecto;
    private swing.Panel_Round_JWC panelAdministrarServicios_Agregar;
    private swing.Panel_Round_JWC panelAdministrarServicios_Modificar;
    private swing.Panel_Round_JWC panelAdministrarServicios_Remover;
    private swing.Panel_Round_JWC panelAgregarBarberos;
    private swing.Panel_Round_JWC panelAgregarCliente;
    private swing.Panel_Round_JWC panelAgregarEmpleado;
    private swing.Panel_Round_JWC panelAgregarProducto;
    private javax.swing.JPanel panelBarberos;
    private swing.Panel_Round_JWC panelBarraBusquedaAdministrarServicios;
    private swing.Panel_Round_JWC panelBarraBusquedaCitas;
    private swing.Panel_Round_JWC panelBarraBusquedaClientes;
    private swing.Panel_Round_JWC panelBarraBusquedaEmpleado;
    private swing.Panel_Round_JWC panelBarraBusquedaProducto;
    private swing.Panel_Round_JWC panelCancelarCita;
    private javax.swing.JPanel panelClientes;
    private swing.Panel_Round_JWC panelComboboxBarberos;
    private swing.Panel_Round_JWC panelComboboxCategoria;
    private swing.Panel_Round_JWC panelComboboxCategoriaEfecto;
    private swing.Panel_Round_JWC panelComboboxMarca;
    private swing.Panel_Round_JWC panelComboboxMarcaEfecto;
    private swing.Panel_Round_JWC panelEfectoComboboxBarberos;
    private javax.swing.JPanel panelEmpleados;
    private javax.swing.JPanel panelEstadisticas;
    private javax.swing.JPanel panelGrafica;
    private javax.swing.JPanel panelGraficaPastel;
    private swing.Panel_Round_JWC panelInfoUsuario;
    private swing.Panel_Round_JWC panelMenu;
    private javax.swing.JPanel panelMenuEfecto;
    private swing.Panel_Round_JWC panelModificarBarberos;
    private swing.Panel_Round_JWC panelModificarCliente;
    private swing.Panel_Round_JWC panelModificarEmpleado;
    private swing.Panel_Round_JWC panelModificarProducto;
    private javax.swing.JPanel panelProductos;
    private swing.Panel_Round_JWC panelRemoverBarberos;
    private swing.Panel_Round_JWC panelRemoverCliente;
    private swing.Panel_Round_JWC panelRemoverEmpleado;
    private swing.Panel_Round_JWC panelRemoverProducto;
    private javax.swing.JPanel panelServicios;
    private panel_degradado_jwc.Panel_Round_Degradado_JWC panel_IdentificadorAdministrarCitas;
    private panel_degradado_jwc.Panel_Round_Degradado_JWC panel_IdentificadorAdministrarServicios;
    private javax.swing.JScrollPane scrollTablaAdministrarServicios;
    public static javax.swing.JScrollPane scrollTablaBarberos;
    private javax.swing.JScrollPane scrollTablaCitas;
    private javax.swing.JScrollPane scrollTablaClientes;
    public javax.swing.JScrollPane scrollTablaEmpleado;
    public static tabledark.TableDark tablaBarberos;
    public static tabledark.TableDark tablaCitasAdmin;
    public static tabledark.TableDark tablaClientes;
    public static tabledark.TableDark tablaEmpleado;
    public static tabledark.TableDark tablaProductos;
    public static tabledark.TableDark tablaServicios;
    // End of variables declaration//GEN-END:variables
}
