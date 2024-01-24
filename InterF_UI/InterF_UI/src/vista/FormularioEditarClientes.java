package vista;

import clases_administradoras.CitaAdmin;
import clases_administradoras.ClienteAdmin;
import java.awt.Color;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Cita;
import modelo.Cliente;
import static vista.InicioAdministrador.tablaCitasAdmin;
import static vista.InicioAdministrador.tablaClientes;
import static vista.InicioUsuario.tablaAgendarCita;
import static vista.InicioUsuario.tablaVerCitas;
import static vista.vistaSeleccionClienteCarrito.tablaClientesCarrito;

/**
 *
 * @author joel_
 */
public class FormularioEditarClientes extends javax.swing.JFrame {

    private ClienteAdmin clienteAdmin;
    private CitaAdmin citaAdmin;

    private String idCliente;

    public FormularioEditarClientes() {
        initComponents();
        setBackground(new Color(0, 0, 0, 0));

        try {
            clienteAdmin = new ClienteAdmin();
            clienteAdmin.leerClientes();

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FormularioEditarClientes.class.getName()).log(Level.SEVERE, null, ex);
        }

        TextPrompt placeholderBarraNombre = new TextPrompt("Nombre", textNombre);
        TextPrompt placeholderBarraApellido = new TextPrompt("Apellido", textApellido);
        TextPrompt placeholderBarraCorreo = new TextPrompt("Correo", textCorreo);
        TextPrompt placeholderBarraTelefono = new TextPrompt("Telefono", textTelefono);

        placeholderBarraNombre.changeAlpha(0.75f);
        placeholderBarraApellido.changeAlpha(0.75f);
        placeholderBarraCorreo.changeAlpha(0.75f);
        placeholderBarraTelefono.changeAlpha(0.75f);

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
            Logger.getLogger(FormularioEditarClientes.class.getName()).log(Level.SEVERE, null, ex);
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
        panel_Round_JWC3 = new swing.Panel_Round_JWC();
        textTelefono = new javax.swing.JFormattedTextField();
        panel_Round_JWC4 = new swing.Panel_Round_JWC();
        textNombre = new javax.swing.JTextField();
        panel_Round_JWC5 = new swing.Panel_Round_JWC();
        textCorreo = new javax.swing.JTextField();
        panel_Round_JWC6 = new swing.Panel_Round_JWC();
        textApellido = new javax.swing.JTextField();
        panel_Round_JWC2 = new swing.Panel_Round_JWC();
        panel_Round_JWC8 = new swing.Panel_Round_JWC();
        panel_Round_JWC9 = new swing.Panel_Round_JWC();
        panel_Round_JWC10 = new swing.Panel_Round_JWC();
        panel_Round_Degradado_JWC1 = new panel_degradado_jwc.Panel_Round_Degradado_JWC();
        btnGuardar = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

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
        salir.setBounds(690, 10, 20, 20);

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
        salir1.setBounds(660, 10, 20, 20);

        panel_Round_Degradado_JWC4.setColor1(new java.awt.Color(169, 127, 3));
        panel_Round_Degradado_JWC4.setColor2(new java.awt.Color(255, 204, 51));
        panel_Round_Degradado_JWC4.setInferior_derecho(60);
        panel_Round_Degradado_JWC4.setSuperior_izquierdo(60);

        jLabel23.setFont(new java.awt.Font("Product Sans", 1, 24)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Modificar Cliente");

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
            .addGroup(panel_Round_JWC3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(textTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        panel_Round_JWC3Layout.setVerticalGroup(
            panel_Round_JWC3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(textTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        layared_Round_JWC1.add(panel_Round_JWC3);
        panel_Round_JWC3.setBounds(370, 200, 265, 30);

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
                .addComponent(textNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        panel_Round_JWC4Layout.setVerticalGroup(
            panel_Round_JWC4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_Round_JWC4Layout.createSequentialGroup()
                .addComponent(textNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        layared_Round_JWC1.add(panel_Round_JWC4);
        panel_Round_JWC4.setBounds(50, 140, 265, 30);

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
                .addGap(18, 18, 18)
                .addComponent(textCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );
        panel_Round_JWC5Layout.setVerticalGroup(
            panel_Round_JWC5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_Round_JWC5Layout.createSequentialGroup()
                .addComponent(textCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        layared_Round_JWC1.add(panel_Round_JWC5);
        panel_Round_JWC5.setBounds(370, 140, 265, 30);

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

        layared_Round_JWC1.add(panel_Round_JWC6);
        panel_Round_JWC6.setBounds(50, 200, 265, 30);

        panel_Round_JWC2.setBackground(new java.awt.Color(255, 204, 51));
        panel_Round_JWC2.setEsqInferiorDerecha(30);
        panel_Round_JWC2.setEsqInferiorIzquierda(30);
        panel_Round_JWC2.setEsqSuperiorDerecha(30);
        panel_Round_JWC2.setEsqSuperiorIzquierda(30);

        javax.swing.GroupLayout panel_Round_JWC2Layout = new javax.swing.GroupLayout(panel_Round_JWC2);
        panel_Round_JWC2.setLayout(panel_Round_JWC2Layout);
        panel_Round_JWC2Layout.setHorizontalGroup(
            panel_Round_JWC2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        panel_Round_JWC2Layout.setVerticalGroup(
            panel_Round_JWC2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        layared_Round_JWC1.add(panel_Round_JWC2);
        panel_Round_JWC2.setBounds(580, 200, 100, 30);

        panel_Round_JWC8.setBackground(new java.awt.Color(255, 204, 51));
        panel_Round_JWC8.setEsqInferiorDerecha(30);
        panel_Round_JWC8.setEsqInferiorIzquierda(30);
        panel_Round_JWC8.setEsqSuperiorDerecha(30);
        panel_Round_JWC8.setEsqSuperiorIzquierda(30);

        javax.swing.GroupLayout panel_Round_JWC8Layout = new javax.swing.GroupLayout(panel_Round_JWC8);
        panel_Round_JWC8.setLayout(panel_Round_JWC8Layout);
        panel_Round_JWC8Layout.setHorizontalGroup(
            panel_Round_JWC8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        panel_Round_JWC8Layout.setVerticalGroup(
            panel_Round_JWC8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        layared_Round_JWC1.add(panel_Round_JWC8);
        panel_Round_JWC8.setBounds(580, 140, 100, 30);

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
        panel_Round_JWC10.setBounds(260, 200, 100, 30);

        panel_Round_Degradado_JWC1.setColor1(new java.awt.Color(1, 26, 13));
        panel_Round_Degradado_JWC1.setColor2(new java.awt.Color(0, 0, 0));
        panel_Round_Degradado_JWC1.setInferior_derecho(30);
        panel_Round_Degradado_JWC1.setInferior_izquierdo(30);
        panel_Round_Degradado_JWC1.setSuperior_derecho(30);
        panel_Round_Degradado_JWC1.setSuperior_izquierdo(30);

        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar");
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
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        layared_Round_JWC1.add(panel_Round_Degradado_JWC1);
        panel_Round_Degradado_JWC1.setBounds(280, 270, 160, 60);

        jLabel2.setFont(new java.awt.Font("JetBrainsMono NF", 0, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Telefono: ");
        layared_Round_JWC1.add(jLabel2);
        jLabel2.setBounds(380, 180, 140, 18);

        getContentPane().add(layared_Round_JWC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 360));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        this.dispose();
        limpiarCampos(textNombre, textApellido, textCorreo, textTelefono);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }//GEN-LAST:event_salirActionPerformed

    private void salir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salir1ActionPerformed
        this.setExtendedState(ICONIFIED);        // TODO add your handling code here:
    }//GEN-LAST:event_salir1ActionPerformed

    private void textNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNombreActionPerformed

    }//GEN-LAST:event_textNombreActionPerformed

    private void textCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCorreoActionPerformed

    private void textApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textApellidoActionPerformed

    private void textNombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textNombreMouseClicked

    }//GEN-LAST:event_textNombreMouseClicked

    private void textApellidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textApellidoMouseClicked

    }//GEN-LAST:event_textApellidoMouseClicked

    private void textCorreoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textCorreoMouseClicked

    }//GEN-LAST:event_textCorreoMouseClicked

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (verificarCamposVacios(textNombre, textApellido, textCorreo, textTelefono) && validarCorreo(textCorreo.getText())
                && esSoloLetras(textNombre.getText(), textApellido.getText())) {
            try {
                clienteAdmin.leerClientes();
                idCliente = tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 3).toString();

                int id = clienteAdmin.obtenerID(idCliente);

                if (clienteAdmin.editarCliente(id, textNombre.getText().trim(), textApellido.getText().trim(),
                        textTelefono.getText().trim(), textCorreo.getText().trim())) {
                    JOptionPane.showMessageDialog(null, "Cliente modificado con exito");
                    this.dispose();
                    actualizarTablaClientes();
                    actualizarTablaCitas();
                    limpiarCampos(textNombre, textApellido, textCorreo, textTelefono);

                }

            } catch (SQLException ex) {
                Logger.getLogger(FormularioBarberos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_btnGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static btn_efecto01_jwc.btn_efecto_V1_JWC btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JPanel jPanel2;
    private swing.Layared_Round_JWC layared_Round_JWC1;
    private grafico_barrav1_jwc.num_max num_max1;
    private panel_degradado_jwc.Panel_Round_Degradado_JWC panel_Round_Degradado_JWC1;
    private panel_degradado_jwc.Panel_Round_Degradado_JWC panel_Round_Degradado_JWC4;
    private swing.Panel_Round_JWC panel_Round_JWC10;
    private swing.Panel_Round_JWC panel_Round_JWC2;
    private swing.Panel_Round_JWC panel_Round_JWC3;
    private swing.Panel_Round_JWC panel_Round_JWC4;
    private swing.Panel_Round_JWC panel_Round_JWC5;
    private swing.Panel_Round_JWC panel_Round_JWC6;
    private swing.Panel_Round_JWC panel_Round_JWC8;
    private swing.Panel_Round_JWC panel_Round_JWC9;
    private btn_efecto01_jwc.btn_efecto_V1_JWC salir;
    private btn_efecto01_jwc.btn_efecto_V1_JWC salir1;
    public javax.swing.JTextField textApellido;
    public javax.swing.JTextField textCorreo;
    public javax.swing.JTextField textNombre;
    public javax.swing.JFormattedTextField textTelefono;
    // End of variables declaration//GEN-END:variables
}
