
package vista;

import controlador.ControladorLogin;
import java.awt.Color;
import javax.swing.JOptionPane;
import static vista.Login.ventanaLogin;
import static vista.Login.ventanaAdministrador;
import static inicio.Main.*;


/**
 *
 * @author joel_
 */
public class InicioA extends javax.swing.JFrame {

    

 
    public InicioA() {
        initComponents();
        setBackground(new Color(0,0,0,0));
        Usuarios_p.setVisible(false);
        Servicios_p.setVisible(false);
        Ventas_p.setVisible(false);
        Graficos_p.setVisible(false);
        citas_p.setVisible(false);
        
        
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        layared_Round_JWC1 = new swing.Layared_Round_JWC();
        salir1 = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        salir = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        panel_Round_JWC1 = new swing.Panel_Round_JWC();
        panel_Round_JWC2 = new swing.Panel_Round_JWC();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btn_ventas = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        btn_usuarios = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        btn_salir = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        btn_servicios = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        btn_graficos = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        jPanel1 = new javax.swing.JPanel();
        btn_actualizar = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        Usuarios_p = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        Servicios_p = new javax.swing.JPanel();
        CITAS_BP1 = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        SERVICIOS_BP1 = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        servicio_p = new javax.swing.JPanel();
        citas_p = new javax.swing.JPanel();
        Ventas_p = new javax.swing.JPanel();
        SERVICIOS_BP2 = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        SERVICIOS_BP3 = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        Graficos_p = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btn_efecto_V1_JWC1 = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        jPanel6 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        layared_Round_JWC1.setBackground(new java.awt.Color(51, 51, 51));
        layared_Round_JWC1.setForeground(new java.awt.Color(204, 204, 204));
        layared_Round_JWC1.setEsqInferiorDerecha(50);
        layared_Round_JWC1.setEsqInferiorIzquierda(50);
        layared_Round_JWC1.setEsqSuperiorDerecha(50);
        layared_Round_JWC1.setEsqSuperiorIzquierda(50);

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
        salir1.setBounds(1030, 10, 20, 20);

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
        salir.setBounds(1060, 10, 20, 20);

        panel_Round_JWC1.setBackground(new java.awt.Color(27, 27, 27));
        panel_Round_JWC1.setEsqInferiorIzquierda(50);
        panel_Round_JWC1.setEsqSuperiorIzquierda(50);
        panel_Round_JWC1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_Round_JWC2.setBackground(new java.awt.Color(56, 56, 56));
        panel_Round_JWC2.setEsqInferiorDerecha(20);
        panel_Round_JWC2.setEsqInferiorIzquierda(20);
        panel_Round_JWC2.setEsqSuperiorDerecha(20);
        panel_Round_JWC2.setEsqSuperiorIzquierda(20);
        panel_Round_JWC2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Usuario:");
        panel_Round_JWC2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Admin");
        panel_Round_JWC2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/marcador (3).png"))); // NOI18N
        panel_Round_JWC2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, -1, -1));

        panel_Round_JWC1.add(panel_Round_JWC2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 150, 70));

        btn_ventas.setForeground(new java.awt.Color(204, 204, 204));
        btn_ventas.setText("Ventas");
        btn_ventas.setBackground_Hover_1(new java.awt.Color(2, 214, 120));
        btn_ventas.setBackground_Hover_2(new java.awt.Color(51, 51, 51));
        btn_ventas.setBackground_No_Hover_1(new java.awt.Color(27, 27, 27));
        btn_ventas.setBackground_No_Hover_2(new java.awt.Color(27, 27, 27));
        btn_ventas.setBtn_abajo(btn_graficos);
        btn_ventas.setBtn_arriba(btn_servicios);
        btn_ventas.setBtn_arriba_abajo_esquina_ovalado(60);
        btn_ventas.setColor_Hover_text(new java.awt.Color(0, 0, 0));
        btn_ventas.setColor_NoHover_text(new java.awt.Color(204, 204, 204));
        btn_ventas.setEsquina_inferior_izquierdo(40);
        btn_ventas.setEsquina_superior_izquierdo(40);
        btn_ventas.setFocusPainted(false);
        btn_ventas.setFont(new java.awt.Font("Product Sans Black", 1, 14)); // NOI18N
        btn_ventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ventasActionPerformed(evt);
            }
        });
        panel_Round_JWC1.add(btn_ventas, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, 160, 50));

        btn_usuarios.setBackground(new java.awt.Color(27, 27, 27));
        btn_usuarios.setForeground(new java.awt.Color(204, 204, 204));
        btn_usuarios.setText("Usuarios");
        btn_usuarios.setToolTipText("");
        btn_usuarios.setAlignmentX(0.5F);
        btn_usuarios.setBackground_Hover_1(new java.awt.Color(2, 214, 120));
        btn_usuarios.setBackground_Hover_2(new java.awt.Color(51, 51, 51));
        btn_usuarios.setBackground_No_Hover_1(new java.awt.Color(27, 27, 27));
        btn_usuarios.setBackground_No_Hover_2(new java.awt.Color(27, 27, 27));
        btn_usuarios.setBtn_abajo(btn_servicios);
        btn_usuarios.setBtn_arriba_abajo_esquina_ovalado(60);
        btn_usuarios.setColor_Hover_text(new java.awt.Color(0, 0, 0));
        btn_usuarios.setColor_NoHover_text(new java.awt.Color(204, 204, 204));
        btn_usuarios.setEsquina_inferior_izquierdo(40);
        btn_usuarios.setEsquina_superior_izquierdo(40);
        btn_usuarios.setFocusPainted(false);
        btn_usuarios.setFont(new java.awt.Font("Product Sans Black", 1, 14)); // NOI18N
        btn_usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_usuariosActionPerformed(evt);
            }
        });
        panel_Round_JWC1.add(btn_usuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 160, 50));

        btn_salir.setForeground(new java.awt.Color(204, 204, 204));
        btn_salir.setText("Cerrar Sesion");
        btn_salir.setBackground_Hover_1(new java.awt.Color(204, 0, 51));
        btn_salir.setBackground_Hover_2(new java.awt.Color(51, 51, 51));
        btn_salir.setBackground_No_Hover_1(new java.awt.Color(27, 27, 27));
        btn_salir.setBackground_No_Hover_2(new java.awt.Color(27, 27, 27));
        btn_salir.setBtn_arriba(btn_graficos);
        btn_salir.setBtn_arriba_abajo_esquina_ovalado(60);
        btn_salir.setColor_Hover_text(new java.awt.Color(0, 0, 0));
        btn_salir.setColor_NoHover_text(new java.awt.Color(204, 204, 204));
        btn_salir.setEsquina_inferior_izquierdo(40);
        btn_salir.setEsquina_superior_izquierdo(40);
        btn_salir.setFocusPainted(false);
        btn_salir.setFont(new java.awt.Font("Product Sans Black", 1, 14)); // NOI18N
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });
        panel_Round_JWC1.add(btn_salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 420, 160, 50));

        btn_servicios.setForeground(new java.awt.Color(204, 204, 204));
        btn_servicios.setText("Servicios/citas");
        btn_servicios.setAlignmentX(1.0F);
        btn_servicios.setAlignmentY(1.0F);
        btn_servicios.setBackground_Hover_1(new java.awt.Color(2, 214, 120));
        btn_servicios.setBackground_Hover_2(new java.awt.Color(51, 51, 51));
        btn_servicios.setBackground_No_Hover_1(new java.awt.Color(27, 27, 27));
        btn_servicios.setBackground_No_Hover_2(new java.awt.Color(27, 27, 27));
        btn_servicios.setBtn_abajo(btn_ventas);
        btn_servicios.setBtn_arriba(btn_usuarios);
        btn_servicios.setBtn_arriba_abajo_esquina_ovalado(60);
        btn_servicios.setColor_Hover_text(new java.awt.Color(0, 0, 0));
        btn_servicios.setColor_NoHover_text(new java.awt.Color(204, 204, 204));
        btn_servicios.setEsquina_inferior_izquierdo(40);
        btn_servicios.setEsquina_superior_izquierdo(40);
        btn_servicios.setFocusPainted(false);
        btn_servicios.setFont(new java.awt.Font("Product Sans Black", 1, 14)); // NOI18N
        btn_servicios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_serviciosActionPerformed(evt);
            }
        });
        panel_Round_JWC1.add(btn_servicios, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 160, 50));

        btn_graficos.setForeground(new java.awt.Color(204, 204, 204));
        btn_graficos.setText("Graficos ");
        btn_graficos.setBackground_Hover_1(new java.awt.Color(2, 214, 120));
        btn_graficos.setBackground_Hover_2(new java.awt.Color(51, 51, 51));
        btn_graficos.setBackground_No_Hover_1(new java.awt.Color(27, 27, 27));
        btn_graficos.setBackground_No_Hover_2(new java.awt.Color(27, 27, 27));
        btn_graficos.setBtn_abajo(btn_salir);
        btn_graficos.setBtn_arriba(btn_ventas);
        btn_graficos.setBtn_arriba_abajo_esquina_ovalado(60);
        btn_graficos.setColor_Hover_text(new java.awt.Color(0, 0, 0));
        btn_graficos.setColor_NoHover_text(new java.awt.Color(204, 204, 204));
        btn_graficos.setEsquina_inferior_izquierdo(40);
        btn_graficos.setEsquina_superior_izquierdo(40);
        btn_graficos.setFocusPainted(false);
        btn_graficos.setFont(new java.awt.Font("Product Sans Black", 1, 14)); // NOI18N
        btn_graficos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_graficosActionPerformed(evt);
            }
        });
        panel_Round_JWC1.add(btn_graficos, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, 160, 50));

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );

        panel_Round_JWC1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, 30, -1));

        btn_actualizar.setBackground(new java.awt.Color(27, 27, 27));
        btn_actualizar.setForeground(new java.awt.Color(204, 204, 204));
        btn_actualizar.setText("Actualizar Admin");
        btn_actualizar.setToolTipText("");
        btn_actualizar.setAlignmentX(0.5F);
        btn_actualizar.setBackground_Hover_1(new java.awt.Color(2, 214, 120));
        btn_actualizar.setBackground_Hover_2(new java.awt.Color(51, 51, 51));
        btn_actualizar.setBackground_No_Hover_1(new java.awt.Color(27, 27, 27));
        btn_actualizar.setBackground_No_Hover_2(new java.awt.Color(27, 27, 27));
        btn_actualizar.setBtn_abajo(btn_servicios);
        btn_actualizar.setBtn_arriba_abajo_esquina_ovalado(60);
        btn_actualizar.setColor_Hover_text(new java.awt.Color(0, 0, 0));
        btn_actualizar.setColor_NoHover_text(new java.awt.Color(204, 204, 204));
        btn_actualizar.setEsquina_inferior_izquierdo(40);
        btn_actualizar.setEsquina_superior_izquierdo(40);
        btn_actualizar.setFocusPainted(false);
        btn_actualizar.setFont(new java.awt.Font("Product Sans Black", 1, 14)); // NOI18N
        btn_actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_actualizarActionPerformed(evt);
            }
        });
        panel_Round_JWC1.add(btn_actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 160, 50));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/huella-dactilar.png"))); // NOI18N
        panel_Round_JWC1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 50, 40));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/alt-de-inicio-de-sesion.png"))); // NOI18N
        panel_Round_JWC1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 50, 30));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/usuarios.png"))); // NOI18N
        panel_Round_JWC1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 50, 40));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/reservar-usuario.png"))); // NOI18N
        panel_Round_JWC1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 50, 40));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/archivo-factura-dolar.png"))); // NOI18N
        panel_Round_JWC1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 50, 40));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/grafico-histograma.png"))); // NOI18N
        panel_Round_JWC1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 50, 40));

        layared_Round_JWC1.add(panel_Round_JWC1);
        panel_Round_JWC1.setBounds(0, 0, 210, 640);

        Usuarios_p.setBackground(new java.awt.Color(51, 51, 51));
        Usuarios_p.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setBackground(new java.awt.Color(51, 51, 51));
        jTable1.setForeground(new java.awt.Color(255, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Gerente"
            }
        ));
        jTable1.setGridColor(new java.awt.Color(0, 255, 204));
        jTable1.setSelectionBackground(new java.awt.Color(204, 255, 255));
        jTable1.setSelectionForeground(new java.awt.Color(51, 153, 255));
        jScrollPane1.setViewportView(jTable1);

        Usuarios_p.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 350, 250));

        jTable2.setBackground(new java.awt.Color(51, 51, 51));
        jTable2.setForeground(new java.awt.Color(255, 255, 255));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Personal"
            }
        ));
        jTable2.setGridColor(new java.awt.Color(204, 204, 204));
        jTable2.setSelectionForeground(new java.awt.Color(51, 102, 255));
        jScrollPane2.setViewportView(jTable2);

        Usuarios_p.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 110, 350, 250));

        layared_Round_JWC1.add(Usuarios_p);
        Usuarios_p.setBounds(210, 10, 860, 620);

        Servicios_p.setBackground(new java.awt.Color(51, 51, 51));
        Servicios_p.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CITAS_BP1.setForeground(new java.awt.Color(255, 255, 255));
        CITAS_BP1.setText("Citas");
        CITAS_BP1.setBackground_Hover_1(new java.awt.Color(0, 255, 255));
        CITAS_BP1.setBackground_Hover_2(new java.awt.Color(51, 51, 51));
        CITAS_BP1.setBackground_No_Hover_1(new java.awt.Color(51, 51, 51));
        CITAS_BP1.setBackground_No_Hover_2(new java.awt.Color(51, 51, 51));
        CITAS_BP1.setEsquina_inferior_derecho(30);
        CITAS_BP1.setEsquina_inferior_izquierdo(30);
        CITAS_BP1.setEsquina_superior_derecho(30);
        CITAS_BP1.setEsquina_superior_izquierdo(30);
        CITAS_BP1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        CITAS_BP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CITAS_BP1ActionPerformed(evt);
            }
        });
        Servicios_p.add(CITAS_BP1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 360, -1, -1));

        SERVICIOS_BP1.setForeground(new java.awt.Color(255, 255, 255));
        SERVICIOS_BP1.setText("Servicios");
        SERVICIOS_BP1.setBackground_Hover_1(new java.awt.Color(0, 255, 255));
        SERVICIOS_BP1.setBackground_Hover_2(new java.awt.Color(51, 51, 51));
        SERVICIOS_BP1.setBackground_No_Hover_1(new java.awt.Color(51, 51, 51));
        SERVICIOS_BP1.setBackground_No_Hover_2(new java.awt.Color(51, 51, 51));
        SERVICIOS_BP1.setEsquina_inferior_derecho(30);
        SERVICIOS_BP1.setEsquina_inferior_izquierdo(30);
        SERVICIOS_BP1.setEsquina_superior_derecho(30);
        SERVICIOS_BP1.setEsquina_superior_izquierdo(30);
        SERVICIOS_BP1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        SERVICIOS_BP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SERVICIOS_BP1ActionPerformed(evt);
            }
        });
        Servicios_p.add(SERVICIOS_BP1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 300, -1, -1));

        servicio_p.setBackground(new java.awt.Color(51, 51, 51));

        citas_p.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout citas_pLayout = new javax.swing.GroupLayout(citas_p);
        citas_p.setLayout(citas_pLayout);
        citas_pLayout.setHorizontalGroup(
            citas_pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 860, Short.MAX_VALUE)
        );
        citas_pLayout.setVerticalGroup(
            citas_pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout servicio_pLayout = new javax.swing.GroupLayout(servicio_p);
        servicio_p.setLayout(servicio_pLayout);
        servicio_pLayout.setHorizontalGroup(
            servicio_pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, servicio_pLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(citas_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        servicio_pLayout.setVerticalGroup(
            servicio_pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, servicio_pLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(citas_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Servicios_p.add(servicio_p, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 620));

        layared_Round_JWC1.add(Servicios_p);
        Servicios_p.setBounds(210, 10, 860, 620);

        Ventas_p.setBackground(new java.awt.Color(51, 51, 51));
        Ventas_p.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SERVICIOS_BP2.setForeground(new java.awt.Color(255, 255, 255));
        SERVICIOS_BP2.setText("Agregar producto");
        SERVICIOS_BP2.setBackground_Hover_1(new java.awt.Color(0, 255, 255));
        SERVICIOS_BP2.setBackground_Hover_2(new java.awt.Color(51, 51, 51));
        SERVICIOS_BP2.setBackground_No_Hover_1(new java.awt.Color(51, 51, 51));
        SERVICIOS_BP2.setBackground_No_Hover_2(new java.awt.Color(51, 51, 51));
        SERVICIOS_BP2.setEsquina_inferior_derecho(30);
        SERVICIOS_BP2.setEsquina_inferior_izquierdo(30);
        SERVICIOS_BP2.setEsquina_superior_derecho(30);
        SERVICIOS_BP2.setEsquina_superior_izquierdo(30);
        SERVICIOS_BP2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        SERVICIOS_BP2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SERVICIOS_BP2ActionPerformed(evt);
            }
        });
        Ventas_p.add(SERVICIOS_BP2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 170, -1, -1));

        SERVICIOS_BP3.setForeground(new java.awt.Color(255, 255, 255));
        SERVICIOS_BP3.setText("Eliminar producto");
        SERVICIOS_BP3.setBackground_Hover_1(new java.awt.Color(0, 255, 255));
        SERVICIOS_BP3.setBackground_Hover_2(new java.awt.Color(51, 51, 51));
        SERVICIOS_BP3.setBackground_No_Hover_1(new java.awt.Color(51, 51, 51));
        SERVICIOS_BP3.setBackground_No_Hover_2(new java.awt.Color(51, 51, 51));
        SERVICIOS_BP3.setEsquina_inferior_derecho(30);
        SERVICIOS_BP3.setEsquina_inferior_izquierdo(30);
        SERVICIOS_BP3.setEsquina_superior_derecho(30);
        SERVICIOS_BP3.setEsquina_superior_izquierdo(30);
        SERVICIOS_BP3.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        SERVICIOS_BP3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SERVICIOS_BP3ActionPerformed(evt);
            }
        });
        Ventas_p.add(SERVICIOS_BP3, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 220, -1, -1));

        jTable3.setBackground(new java.awt.Color(51, 51, 51));
        jTable3.setForeground(new java.awt.Color(255, 255, 255));
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Codigo", "Categoria", "Nombre p", "Precio", "Unidades"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        Ventas_p.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 613, -1));

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        Ventas_p.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 70, 140, -1));

        jButton1.setText("jButton1");
        Ventas_p.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 70, 40, -1));

        layared_Round_JWC1.add(Ventas_p);
        Ventas_p.setBounds(210, 10, 860, 620);

        Graficos_p.setBackground(new java.awt.Color(51, 51, 51));
        Graficos_p.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 706, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 267, Short.MAX_VALUE)
        );

        Graficos_p.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 72, -1, -1));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 218, Short.MAX_VALUE)
        );

        Graficos_p.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 378, -1, -1));

        btn_efecto_V1_JWC1.setForeground(new java.awt.Color(255, 255, 255));
        btn_efecto_V1_JWC1.setText("Generar informe");
        btn_efecto_V1_JWC1.setBackground_Hover_1(new java.awt.Color(0, 255, 255));
        btn_efecto_V1_JWC1.setBackground_Hover_2(new java.awt.Color(51, 51, 51));
        btn_efecto_V1_JWC1.setBackground_No_Hover_1(new java.awt.Color(51, 51, 51));
        btn_efecto_V1_JWC1.setBackground_No_Hover_2(new java.awt.Color(51, 51, 51));
        btn_efecto_V1_JWC1.setEsquina_inferior_derecho(30);
        btn_efecto_V1_JWC1.setEsquina_inferior_izquierdo(30);
        btn_efecto_V1_JWC1.setEsquina_superior_derecho(30);
        btn_efecto_V1_JWC1.setEsquina_superior_izquierdo(30);
        btn_efecto_V1_JWC1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        btn_efecto_V1_JWC1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_efecto_V1_JWC1ActionPerformed(evt);
            }
        });
        Graficos_p.add(btn_efecto_V1_JWC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 450, -1, -1));

        layared_Round_JWC1.add(Graficos_p);
        Graficos_p.setBounds(210, 10, 860, 620);

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 860, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );

        layared_Round_JWC1.add(jPanel6);
        jPanel6.setBounds(210, 10, 860, 620);

        getContentPane().add(layared_Round_JWC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1090, 640));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void salir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salir1ActionPerformed
        this.setExtendedState(ICONIFIED);        // TODO add your handling code here:
    }//GEN-LAST:event_salir1ActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_salirActionPerformed

    private void btn_usuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_usuariosActionPerformed
       Usuarios_p.setVisible(true); // TODO add your handling code here:
    }//GEN-LAST:event_btn_usuariosActionPerformed

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
       JOptionPane.showMessageDialog(rootPane, "ADIOS !");
        ventanaAdministrador.setVisible(false);
        this.dispose();
        ControladorLogin ctrl = new ControladorLogin(gerente, usuario, ventanaLogin, conGen, conUse);
        ventanaLogin.setVisible(true);
        
    }//GEN-LAST:event_btn_salirActionPerformed

    private void btn_serviciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_serviciosActionPerformed
Servicios_p.setVisible(true);
Usuarios_p.setVisible(false);
Ventas_p.setVisible(false);
Graficos_p.setVisible(false);// TODO add your handling code here:
    }//GEN-LAST:event_btn_serviciosActionPerformed

    private void SERVICIOS_BP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SERVICIOS_BP1ActionPerformed
  // TODO add your handling code here:
    }//GEN-LAST:event_SERVICIOS_BP1ActionPerformed

    private void CITAS_BP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CITAS_BP1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CITAS_BP1ActionPerformed

    private void btn_ventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ventasActionPerformed
Ventas_p.setVisible(true);
Servicios_p.setVisible(false);
Usuarios_p.setVisible(false);
Graficos_p.setVisible(false);




        
        
        
        
    }//GEN-LAST:event_btn_ventasActionPerformed

    private void SERVICIOS_BP2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SERVICIOS_BP2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SERVICIOS_BP2ActionPerformed

    private void SERVICIOS_BP3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SERVICIOS_BP3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SERVICIOS_BP3ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void btn_graficosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_graficosActionPerformed
Ventas_p.setVisible(false);
Servicios_p.setVisible(false);
Usuarios_p.setVisible(false);
Graficos_p.setVisible(true);



       
       
       
       
       
       
       
    }//GEN-LAST:event_btn_graficosActionPerformed

    private void btn_efecto_V1_JWC1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_efecto_V1_JWC1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_efecto_V1_JWC1ActionPerformed

    private void btn_actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_actualizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_actualizarActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private btn_efecto01_jwc.btn_efecto_V1_JWC CITAS_BP1;
    private javax.swing.JPanel Graficos_p;
    private btn_efecto01_jwc.btn_efecto_V1_JWC SERVICIOS_BP1;
    private btn_efecto01_jwc.btn_efecto_V1_JWC SERVICIOS_BP2;
    private btn_efecto01_jwc.btn_efecto_V1_JWC SERVICIOS_BP3;
    private javax.swing.JPanel Servicios_p;
    private javax.swing.JPanel Usuarios_p;
    private javax.swing.JPanel Ventas_p;
    private btn_efecto01_jwc.btn_efecto_V1_JWC btn_actualizar;
    private btn_efecto01_jwc.btn_efecto_V1_JWC btn_efecto_V1_JWC1;
    private btn_efecto01_jwc.btn_efecto_V1_JWC btn_graficos;
    private btn_efecto01_jwc.btn_efecto_V1_JWC btn_salir;
    private btn_efecto01_jwc.btn_efecto_V1_JWC btn_servicios;
    private btn_efecto01_jwc.btn_efecto_V1_JWC btn_usuarios;
    private btn_efecto01_jwc.btn_efecto_V1_JWC btn_ventas;
    private javax.swing.JPanel citas_p;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private swing.Layared_Round_JWC layared_Round_JWC1;
    private swing.Panel_Round_JWC panel_Round_JWC1;
    private swing.Panel_Round_JWC panel_Round_JWC2;
    private btn_efecto01_jwc.btn_efecto_V1_JWC salir;
    private btn_efecto01_jwc.btn_efecto_V1_JWC salir1;
    private javax.swing.JPanel servicio_p;
    // End of variables declaration//GEN-END:variables
}
