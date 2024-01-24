package vista;

import java.awt.Color;
import javax.swing.JFrame;
import clases_administradoras.ClienteAdmin;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Cliente;
import tabledark.TableDark;
import static vista.InicioUsuario.tablaAgendarCita;
import static vista.vistaCarrito.labelClienteEscojido;
import static vista.vistaCarrito.labelCorreoCliente;

/**
 *
 * @author joel_
 */
public class vistaSeleccionClienteCarrito extends javax.swing.JFrame {

    private ClienteAdmin clienteAdmin;
    boolean hayCampoSeleccionadoCliente = false;

    public vistaSeleccionClienteCarrito() {
        try {
            initComponents();
            setBackground(new Color(0, 0, 0, 0));
            clienteAdmin = new ClienteAdmin();
            clienteAdmin.leerClientes();

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(vistaSeleccionClienteCarrito.class.getName()).log(Level.SEVERE, null, ex);
        }

        generarTablaClientes();

        estaSelccionadaTablaClientes();

        TextPrompt placeholderBarraClientes = new TextPrompt("Buscar Cliente", barraBusquedaAgendarCita);

        placeholderBarraClientes.changeAlpha(0.55f);

    }

    private void generarTablaClientes() {
        try {
            clienteAdmin.leerClientes();

            tablaClientesCarrito.fixTable(scrollTablaAgendarCita1);
            DefaultTableModel DefaultModelClientes = (DefaultTableModel) tablaClientesCarrito.getModel();

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

    private boolean estaSelccionadaTablaClientes() {

        tablaClientesCarrito.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent Mouse_evt) {

                tablaClientesCarrito = (TableDark) Mouse_evt.getSource();
                Point point = Mouse_evt.getPoint();
                int row = tablaClientesCarrito.rowAtPoint(point);

                if (Mouse_evt.getClickCount() == 1) {

                    hayCampoSeleccionadoCliente = true;
                    labelSelecionaCliente.setText(tablaClientesCarrito.getValueAt(tablaClientesCarrito.getSelectedRow(), 0).toString()
                            + " " + tablaClientesCarrito.getValueAt(tablaClientesCarrito.getSelectedRow(), 1).toString());

                }

            }
        });
        return hayCampoSeleccionadoCliente;
    }

    private void actualizarTablaClientesConBusqueda(String textoBusqueda) {
        try {
            ArrayList<Cliente> clientesFiltrados = clienteAdmin.buscarClientes(textoBusqueda);

            DefaultTableModel modelo = (DefaultTableModel) tablaClientesCarrito.getModel();
            modelo.setRowCount(0);

            for (Cliente cliente : clientesFiltrados) {
                modelo.addRow(new Object[]{cliente.getNombre(), cliente.getApellido(), cliente.getTelefono(), cliente.getCorreo()});
            }
        } catch (SQLException ex) {
            Logger.getLogger(InicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        num_max1 = new grafico_barrav1_jwc.num_max();
        jPanel2 = new javax.swing.JPanel();
        scrollTablaAgendarCita = new javax.swing.JScrollPane();
        tablaAgendarCita = new tabledark.TableDark();
        layared_Round_JWC1 = new swing.Layared_Round_JWC();
        salir = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        salir1 = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        scrollTablaAgendarCita1 = new javax.swing.JScrollPane();
        tablaClientesCarrito = new tabledark.TableDark();
        labelSelecionaCliente = new javax.swing.JLabel();
        labelCliente = new javax.swing.JLabel();
        separador = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        panelBarraBusquedaAgendarCita = new swing.Panel_Round_JWC();
        barraBusquedaAgendarCita = new javax.swing.JTextField();
        btnBuscarAgendarCita = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        btnVentasAgregar1 = new btn_efecto01_jwc.btn_efecto_V1_JWC();

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
        salir.setBounds(680, 10, 20, 20);

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
        salir1.setBounds(650, 10, 20, 20);

        tablaClientesCarrito.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaClientesCarrito.setFont(new java.awt.Font("Product Sans", 0, 14)); // NOI18N
        scrollTablaAgendarCita1.setViewportView(tablaClientesCarrito);

        layared_Round_JWC1.add(scrollTablaAgendarCita1);
        scrollTablaAgendarCita1.setBounds(10, 140, 720, 370);

        labelSelecionaCliente.setFont(new java.awt.Font("Product Sans", 0, 14)); // NOI18N
        labelSelecionaCliente.setForeground(new java.awt.Color(153, 255, 153));
        labelSelecionaCliente.setText("Selecciona un cliente");
        layared_Round_JWC1.add(labelSelecionaCliente);
        labelSelecionaCliente.setBounds(430, 50, 200, 18);

        labelCliente.setText("Cliente");
        layared_Round_JWC1.add(labelCliente);
        labelCliente.setBounds(430, 30, 120, 16);

        separador.setForeground(new java.awt.Color(255, 255, 255));
        layared_Round_JWC1.add(separador);
        separador.setBounds(90, 60, 0, 3);

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        layared_Round_JWC1.add(jSeparator1);
        jSeparator1.setBounds(430, 80, 200, 10);

        panelBarraBusquedaAgendarCita.setBackground(new java.awt.Color(0, 0, 0));
        panelBarraBusquedaAgendarCita.setEsqInferiorDerecha(30);
        panelBarraBusquedaAgendarCita.setEsqInferiorIzquierda(30);
        panelBarraBusquedaAgendarCita.setEsqSuperiorDerecha(30);
        panelBarraBusquedaAgendarCita.setEsqSuperiorIzquierda(30);

        barraBusquedaAgendarCita.setBackground(new java.awt.Color(0, 0, 0));
        barraBusquedaAgendarCita.setFont(new java.awt.Font("JetBrainsMono NF", 0, 13)); // NOI18N
        barraBusquedaAgendarCita.setForeground(new java.awt.Color(255, 255, 255));
        barraBusquedaAgendarCita.setBorder(null);
        barraBusquedaAgendarCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barraBusquedaAgendarCitaActionPerformed(evt);
            }
        });
        barraBusquedaAgendarCita.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                barraBusquedaAgendarCitaKeyTyped(evt);
            }
        });

        btnBuscarAgendarCita.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarAgendarCita.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/busqueda (2).png"))); // NOI18N
        btnBuscarAgendarCita.setText("");
        btnBuscarAgendarCita.setBackground_Hover_1(new java.awt.Color(0, 0, 204));
        btnBuscarAgendarCita.setBackground_Hover_2(new java.awt.Color(0, 0, 153));
        btnBuscarAgendarCita.setBackground_No_Hover_1(new java.awt.Color(0, 0, 0));
        btnBuscarAgendarCita.setBackground_No_Hover_2(new java.awt.Color(0, 0, 0));
        btnBuscarAgendarCita.setEsquina_inferior_derecho(30);
        btnBuscarAgendarCita.setEsquina_inferior_izquierdo(30);
        btnBuscarAgendarCita.setEsquina_superior_derecho(30);
        btnBuscarAgendarCita.setEsquina_superior_izquierdo(30);
        btnBuscarAgendarCita.setFocusPainted(false);
        btnBuscarAgendarCita.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N

        javax.swing.GroupLayout panelBarraBusquedaAgendarCitaLayout = new javax.swing.GroupLayout(panelBarraBusquedaAgendarCita);
        panelBarraBusquedaAgendarCita.setLayout(panelBarraBusquedaAgendarCitaLayout);
        panelBarraBusquedaAgendarCitaLayout.setHorizontalGroup(
            panelBarraBusquedaAgendarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBarraBusquedaAgendarCitaLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(barraBusquedaAgendarCita, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBuscarAgendarCita, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelBarraBusquedaAgendarCitaLayout.setVerticalGroup(
            panelBarraBusquedaAgendarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBarraBusquedaAgendarCitaLayout.createSequentialGroup()
                .addGroup(panelBarraBusquedaAgendarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(barraBusquedaAgendarCita, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarAgendarCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        layared_Round_JWC1.add(panelBarraBusquedaAgendarCita);
        panelBarraBusquedaAgendarCita.setBounds(40, 20, 265, 30);

        btnVentasAgregar1.setText("Seleccionar");
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
        layared_Round_JWC1.add(btnVentasAgregar1);
        btnVentasAgregar1.setBounds(50, 90, 130, 30);

        getContentPane().add(layared_Round_JWC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 520));

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

    private void barraBusquedaAgendarCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barraBusquedaAgendarCitaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_barraBusquedaAgendarCitaActionPerformed

    private void btnVentasAgregar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasAgregar1ActionPerformed
        if (estaSelccionadaTablaClientes()) {
            labelClienteEscojido.setText(tablaClientesCarrito.getValueAt(tablaClientesCarrito.getSelectedRow(), 0).toString()
                    + " " + tablaClientesCarrito.getValueAt(tablaClientesCarrito.getSelectedRow(), 1).toString());

            labelCorreoCliente.setText(tablaClientesCarrito.getValueAt(tablaClientesCarrito.getSelectedRow(), 3).toString());
            hayCampoSeleccionadoCliente = false;
            this.dispose();
        } else {

            JOptionPane.showMessageDialog(null, "Selecciona un cliente antes de continuar ", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnVentasAgregar1ActionPerformed

    private void barraBusquedaAgendarCitaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_barraBusquedaAgendarCitaKeyTyped

        String textoBusqueda = barraBusquedaAgendarCita.getText().trim();
        if (Character.isLetterOrDigit(evt.getKeyChar())) {
            textoBusqueda += evt.getKeyChar();
        }
        actualizarTablaClientesConBusqueda(textoBusqueda);


    }//GEN-LAST:event_barraBusquedaAgendarCitaKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField barraBusquedaAgendarCita;
    public static btn_efecto01_jwc.btn_efecto_V1_JWC btnBuscarAgendarCita;
    public static btn_efecto01_jwc.btn_efecto_V1_JWC btnVentasAgregar1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelCliente;
    private javax.swing.JLabel labelSelecionaCliente;
    private swing.Layared_Round_JWC layared_Round_JWC1;
    private grafico_barrav1_jwc.num_max num_max1;
    private swing.Panel_Round_JWC panelBarraBusquedaAgendarCita;
    private btn_efecto01_jwc.btn_efecto_V1_JWC salir;
    private btn_efecto01_jwc.btn_efecto_V1_JWC salir1;
    private javax.swing.JScrollPane scrollTablaAgendarCita;
    private javax.swing.JScrollPane scrollTablaAgendarCita1;
    private javax.swing.JSeparator separador;
    public static tabledark.TableDark tablaAgendarCita;
    public static tabledark.TableDark tablaClientesCarrito;
    // End of variables declaration//GEN-END:variables
}
