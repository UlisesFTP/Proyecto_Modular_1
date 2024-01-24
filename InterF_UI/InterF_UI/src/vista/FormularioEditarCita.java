package vista;

import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author joel_
 */
public class FormularioEditarCita extends javax.swing.JFrame {

    public FormularioEditarCita() {
        initComponents();
        setBackground(new Color(0, 0, 0, 0));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        num_max1 = new grafico_barrav1_jwc.num_max();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        layared_Round_JWC1 = new swing.Layared_Round_JWC();
        salir = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        salir1 = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        panel_Round_Degradado_JWC4 = new panel_degradado_jwc.Panel_Round_Degradado_JWC();
        jLabel23 = new javax.swing.JLabel();
        panel_Round_Degradado_JWC1 = new panel_degradado_jwc.Panel_Round_Degradado_JWC();
        btnGuardarUsuario = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        jLabel1 = new javax.swing.JLabel();
        dateModificarCitas = new com.toedter.calendar.JDateChooser();
        comboBoxServiciosModificarCita = new javax.swing.JComboBox<>();
        comboBoxHorasModificarCita = new javax.swing.JComboBox<>();
        comboBoxBarberosModificarCita = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

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

        jLabel2.setText("jLabel2");

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
        salir.setBounds(470, 10, 20, 20);

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
        salir1.setBounds(440, 10, 20, 20);

        panel_Round_Degradado_JWC4.setColor1(new java.awt.Color(169, 127, 3));
        panel_Round_Degradado_JWC4.setColor2(new java.awt.Color(255, 204, 51));
        panel_Round_Degradado_JWC4.setInferior_derecho(60);
        panel_Round_Degradado_JWC4.setSuperior_izquierdo(60);

        jLabel23.setBackground(new java.awt.Color(0, 0, 0));
        jLabel23.setFont(new java.awt.Font("Product Sans", 1, 24)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Modificar Cita");

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        layared_Round_JWC1.add(panel_Round_Degradado_JWC4);
        panel_Round_Degradado_JWC4.setBounds(0, 0, 400, 70);

        panel_Round_Degradado_JWC1.setColor1(new java.awt.Color(1, 26, 13));
        panel_Round_Degradado_JWC1.setColor2(new java.awt.Color(0, 0, 0));
        panel_Round_Degradado_JWC1.setInferior_derecho(30);
        panel_Round_Degradado_JWC1.setInferior_izquierdo(30);
        panel_Round_Degradado_JWC1.setSuperior_derecho(30);
        panel_Round_Degradado_JWC1.setSuperior_izquierdo(30);

        btnGuardarUsuario.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarUsuario.setText("Guardar");
        btnGuardarUsuario.setBackground_Hover_1(new java.awt.Color(255, 204, 51));
        btnGuardarUsuario.setBackground_Hover_2(new java.awt.Color(169, 127, 3));
        btnGuardarUsuario.setBackground_No_Hover_1(new java.awt.Color(0, 0, 0));
        btnGuardarUsuario.setBackground_No_Hover_2(new java.awt.Color(0, 0, 0));
        btnGuardarUsuario.setColor_Hover_text(new java.awt.Color(0, 0, 0));
        btnGuardarUsuario.setEsquina_inferior_derecho(30);
        btnGuardarUsuario.setEsquina_inferior_izquierdo(30);
        btnGuardarUsuario.setEsquina_superior_derecho(30);
        btnGuardarUsuario.setEsquina_superior_izquierdo(30);
        btnGuardarUsuario.setFocusPainted(false);
        btnGuardarUsuario.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        btnGuardarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarUsuarioActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/disco.png"))); // NOI18N

        javax.swing.GroupLayout panel_Round_Degradado_JWC1Layout = new javax.swing.GroupLayout(panel_Round_Degradado_JWC1);
        panel_Round_Degradado_JWC1.setLayout(panel_Round_Degradado_JWC1Layout);
        panel_Round_Degradado_JWC1Layout.setHorizontalGroup(
            panel_Round_Degradado_JWC1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_Round_Degradado_JWC1Layout.createSequentialGroup()
                .addComponent(btnGuardarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        panel_Round_Degradado_JWC1Layout.setVerticalGroup(
            panel_Round_Degradado_JWC1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnGuardarUsuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_Round_Degradado_JWC1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        layared_Round_JWC1.add(panel_Round_Degradado_JWC1);
        panel_Round_Degradado_JWC1.setBounds(140, 350, 160, 60);
        layared_Round_JWC1.add(dateModificarCitas);
        dateModificarCitas.setBounds(80, 110, 120, 50);

        comboBoxServiciosModificarCita.setBackground(new java.awt.Color(0, 0, 0));
        comboBoxServiciosModificarCita.setFont(new java.awt.Font("Product Sans", 1, 12)); // NOI18N
        comboBoxServiciosModificarCita.setForeground(new java.awt.Color(255, 255, 255));
        comboBoxServiciosModificarCita.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 51)));
        layared_Round_JWC1.add(comboBoxServiciosModificarCita);
        comboBoxServiciosModificarCita.setBounds(80, 240, 120, 50);

        comboBoxHorasModificarCita.setBackground(new java.awt.Color(0, 0, 0));
        comboBoxHorasModificarCita.setFont(new java.awt.Font("Product Sans", 1, 12)); // NOI18N
        comboBoxHorasModificarCita.setForeground(new java.awt.Color(255, 255, 255));
        comboBoxHorasModificarCita.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10:00:00", "10:15:00", "10:30:00", "10:45:00", "11:00:00", "11:15:00", "11:30:00", "11:45:00", "12:00:00", "12:15:00", "12:30:00", "12:45:00", "13:00:00", "13:15:00", "13:30:00", "13:45:00", "14:00:00", "14:15:00", "14:30:00", "14:45:00", "15:00:00", "15:15:00", "15:30:00", "15:45:00", "16:00:00", "16:15:00", "16:30:00", "16:45:00", "17:00:00", "17:15:00", "17:30:00", "17:45:00", "18:00:00", "18:15:00", "18:30:00", "18:45:00", "19:00:00" }));
        comboBoxHorasModificarCita.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 51)));
        comboBoxHorasModificarCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxHorasModificarCitaActionPerformed(evt);
            }
        });
        layared_Round_JWC1.add(comboBoxHorasModificarCita);
        comboBoxHorasModificarCita.setBounds(250, 110, 110, 50);

        comboBoxBarberosModificarCita.setBackground(new java.awt.Color(0, 0, 0));
        comboBoxBarberosModificarCita.setFont(new java.awt.Font("Product Sans", 1, 12)); // NOI18N
        comboBoxBarberosModificarCita.setForeground(new java.awt.Color(255, 255, 255));
        comboBoxBarberosModificarCita.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 51)));
        layared_Round_JWC1.add(comboBoxBarberosModificarCita);
        comboBoxBarberosModificarCita.setBounds(250, 240, 110, 50);

        jLabel3.setText("Dia");
        layared_Round_JWC1.add(jLabel3);
        jLabel3.setBounds(80, 80, 60, 16);

        jLabel4.setText("Hora");
        layared_Round_JWC1.add(jLabel4);
        jLabel4.setBounds(250, 80, 80, 16);

        jLabel5.setText("Servicios");
        layared_Round_JWC1.add(jLabel5);
        jLabel5.setBounds(80, 210, 70, 16);

        jLabel6.setText("Barbero");
        layared_Round_JWC1.add(jLabel6);
        jLabel6.setBounds(250, 210, 80, 16);

        getContentPane().add(layared_Round_JWC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 510, 440));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        this.dispose();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }//GEN-LAST:event_salirActionPerformed

    private void salir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salir1ActionPerformed
        this.setExtendedState(ICONIFIED);
    }//GEN-LAST:event_salir1ActionPerformed

    private void btnGuardarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarUsuarioActionPerformed

    }//GEN-LAST:event_btnGuardarUsuarioActionPerformed

    private void comboBoxHorasModificarCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxHorasModificarCitaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxHorasModificarCitaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static btn_efecto01_jwc.btn_efecto_V1_JWC btnGuardarUsuario;
    public static javax.swing.JComboBox<String> comboBoxBarberosModificarCita;
    private javax.swing.JComboBox<String> comboBoxHorasModificarCita;
    public static javax.swing.JComboBox<String> comboBoxServiciosModificarCita;
    private com.toedter.calendar.JDateChooser dateModificarCitas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private swing.Layared_Round_JWC layared_Round_JWC1;
    private grafico_barrav1_jwc.num_max num_max1;
    private panel_degradado_jwc.Panel_Round_Degradado_JWC panel_Round_Degradado_JWC1;
    private panel_degradado_jwc.Panel_Round_Degradado_JWC panel_Round_Degradado_JWC4;
    private btn_efecto01_jwc.btn_efecto_V1_JWC salir;
    private btn_efecto01_jwc.btn_efecto_V1_JWC salir1;
    // End of variables declaration//GEN-END:variables
}
