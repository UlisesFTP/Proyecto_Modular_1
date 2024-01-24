package vista;

import java.awt.Color;

/**
 *
 * @author joel_
 */
public class Login extends javax.swing.JFrame {

    //ESTAS SON LAS VISTAS ALMACENADAS EN VARIABLES
    public static Login ventanaLogin = new Login();
    public static InicioA ventanaAdministrador = new InicioA();
    public static InicioG ventanaGerente = new InicioG();
    public static InicioU ventanaUsuario = new InicioU();

    public Login() {

        initComponents();
        setBackground(new Color(0, 0, 0, 0));
    }

  
    //FUNCION BOOLEANA QUE VERIFICA QUE USUARIO Y CONTRASEÑA NO ESTEN VACIOS
   public boolean hayCamposVacios() {
        return (inputUsuario.getText().equals("Ingrese su usuario") || inputPassword.getText().equals("**********")
                || inputUsuario.getText().equals("Ingrese su usuario") && inputPassword.getText().equals("**********"));

    }

    //FUNCION BOOLEANA QUE VERIFICA SI INGRESA EL ADMINISTRADOR
   public boolean esAdmin() {

        String usuario = inputUsuario.getText();
        char[] passwordChar = inputPassword.getPassword();
        String password = new String(passwordChar);

        return ("admin".equals(usuario) && "admin".equals(password));

    }

    //FUNCION QUE HACE APARECER LA VENTANA DE ADMINISTRADOR
    public static void setInicioAdminVentana() throws Exception {
        ventanaAdministrador.toFront();
        ventanaAdministrador.setVisible(true);
        ventanaLogin.setVisible(false);

    }

    //FUNCION QUE HACE APARECER LA VENTANA DE GERENTE
    public static void setInicioGerenteVentana() throws Exception {
        ventanaGerente.toFront();
        ventanaGerente.setVisible(true);
        ventanaLogin.setVisible(false);

    }

    //FUNCION QUE HACE APARECER LA VENTANA DE USUARIO
    public static void setInicioUsuarioVentana() throws Exception {
        ventanaUsuario.toFront();
        ventanaUsuario.setVisible(true);
        ventanaLogin.setVisible(false);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        img_Round_JWC1 = new swing.Img_Round_JWC();
        panel_Round_JWC1 = new swing.Panel_Round_JWC();
        panel_Round_JWC2 = new swing.Panel_Round_JWC();
        jLabel2 = new javax.swing.JLabel();
        loginBoton = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        inputUsuario = new javax.swing.JTextField();
        inputPassword = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        exit = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        minest = new btn_efecto01_jwc.btn_efecto_V1_JWC();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        jToolBar1.setRollover(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(img_Round_JWC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 110, -1, -1));

        panel_Round_JWC1.setBackground(new java.awt.Color(51, 51, 51));
        panel_Round_JWC1.setEsqInferiorDerecha(50);
        panel_Round_JWC1.setEsqInferiorIzquierda(50);
        panel_Round_JWC1.setEsqSuperiorDerecha(50);
        panel_Round_JWC1.setEsqSuperiorIzquierda(50);
        panel_Round_JWC1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_Round_JWC2.setBackground(new java.awt.Color(0, 0, 0));
        panel_Round_JWC2.setEsqInferiorIzquierda(50);
        panel_Round_JWC2.setEsqSuperiorIzquierda(50);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/IMG_9815-transformed.jpg"))); // NOI18N
        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout panel_Round_JWC2Layout = new javax.swing.GroupLayout(panel_Round_JWC2);
        panel_Round_JWC2.setLayout(panel_Round_JWC2Layout);
        panel_Round_JWC2Layout.setHorizontalGroup(
            panel_Round_JWC2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_Round_JWC2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 274, Short.MAX_VALUE))
        );
        panel_Round_JWC2Layout.setVerticalGroup(
            panel_Round_JWC2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_Round_JWC2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        panel_Round_JWC1.add(panel_Round_JWC2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 530));

        loginBoton.setForeground(new java.awt.Color(255, 255, 255));
        loginBoton.setText("Iniciar Sesion ");
        loginBoton.setBackground_Hover_1(new java.awt.Color(2, 214, 120));
        loginBoton.setBackground_Hover_2(new java.awt.Color(1, 34, 20));
        loginBoton.setBackground_No_Hover_1(new java.awt.Color(0, 0, 0));
        loginBoton.setBackground_No_Hover_2(new java.awt.Color(0, 0, 0));
        loginBoton.setEsquina_inferior_derecho(30);
        loginBoton.setEsquina_inferior_izquierdo(30);
        loginBoton.setEsquina_superior_derecho(30);
        loginBoton.setEsquina_superior_izquierdo(30);
        loginBoton.setFocusPainted(false);
        loginBoton.setFont(new java.awt.Font("JetBrains Mono", 0, 18)); // NOI18N
        panel_Round_JWC1.add(loginBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 370, -1, 40));

        jLabel1.setFont(new java.awt.Font("JetBrainsMono NF SemiBold", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Login ");
        panel_Round_JWC1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 50, 110, 30));

        jLabel3.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Contraseña");
        panel_Round_JWC1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 250, 100, -1));

        jLabel4.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Usuario ");
        panel_Round_JWC1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 150, -1, -1));

        inputUsuario.setBackground(new java.awt.Color(51, 51, 51));
        inputUsuario.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        inputUsuario.setForeground(new java.awt.Color(204, 204, 204));
        inputUsuario.setText("Ingrese su usuario");
        inputUsuario.setBorder(null);
        inputUsuario.setCaretColor(new java.awt.Color(153, 153, 153));
        inputUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                inputUsuarioMousePressed(evt);
            }
        });
        panel_Round_JWC1.add(inputUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 180, 260, 30));

        inputPassword.setBackground(new java.awt.Color(51, 51, 51));
        inputPassword.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        inputPassword.setForeground(new java.awt.Color(204, 204, 204));
        inputPassword.setText("**********");
        inputPassword.setBorder(null);
        inputPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                inputPasswordMousePressed(evt);
            }
        });
        panel_Round_JWC1.add(inputPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 280, 260, 30));
        panel_Round_JWC1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, 90, 90));

        exit.setText("");
        exit.setBackground_Hover_1(new java.awt.Color(255, 153, 153));
        exit.setBackground_Hover_2(new java.awt.Color(255, 0, 0));
        exit.setBackground_No_Hover_1(new java.awt.Color(153, 0, 51));
        exit.setBackground_No_Hover_2(new java.awt.Color(255, 102, 102));
        exit.setEsquina_inferior_derecho(20);
        exit.setEsquina_inferior_izquierdo(20);
        exit.setEsquina_superior_derecho(20);
        exit.setEsquina_superior_izquierdo(20);
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        panel_Round_JWC1.add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 10, 20, 20));

        minest.setText("");
        minest.setBackground_Hover_1(new java.awt.Color(255, 204, 153));
        minest.setBackground_Hover_2(new java.awt.Color(255, 153, 0));
        minest.setBackground_No_Hover_1(new java.awt.Color(102, 51, 0));
        minest.setBackground_No_Hover_2(new java.awt.Color(255, 153, 0));
        minest.setEsquina_inferior_derecho(20);
        minest.setEsquina_inferior_izquierdo(20);
        minest.setEsquina_superior_derecho(20);
        minest.setEsquina_superior_izquierdo(20);
        minest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minestActionPerformed(evt);
            }
        });
        panel_Round_JWC1.add(minest, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, 20, 20));

        jSeparator1.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        panel_Round_JWC1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 310, 260, 10));

        jSeparator2.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        panel_Round_JWC1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 220, 260, 10));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/cerrar.png"))); // NOI18N
        panel_Round_JWC1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 270, 50, 40));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/iconos/usuario.png"))); // NOI18N
        panel_Round_JWC1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 170, 50, 40));

        getContentPane().add(panel_Round_JWC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 790, 530));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitActionPerformed

    private void minestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minestActionPerformed
        this.setExtendedState(ICONIFIED);        // TODO add your handling code here:
    }//GEN-LAST:event_minestActionPerformed

    private void inputUsuarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inputUsuarioMousePressed

        if (inputUsuario.getText().equals("Ingrese su usuario")) {

            inputUsuario.setText("");
        }

        if (String.valueOf(inputPassword.getPassword()).isEmpty()) {
            inputPassword.setText("**********");
        }

    }//GEN-LAST:event_inputUsuarioMousePressed

    private void inputPasswordMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inputPasswordMousePressed
        if (inputUsuario.getText().isEmpty()) {
            inputUsuario.setText("Ingrese su usuario");
        }

        if (String.valueOf(inputPassword.getText()).equals("**********")) {
            inputPassword.setText("");
        }
    }//GEN-LAST:event_inputPasswordMousePressed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private btn_efecto01_jwc.btn_efecto_V1_JWC exit;
    private swing.Img_Round_JWC img_Round_JWC1;
    public javax.swing.JPasswordField inputPassword;
    public javax.swing.JTextField inputUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JToolBar jToolBar1;
    public btn_efecto01_jwc.btn_efecto_V1_JWC loginBoton;
    private btn_efecto01_jwc.btn_efecto_V1_JWC minest;
    private swing.Panel_Round_JWC panel_Round_JWC1;
    private swing.Panel_Round_JWC panel_Round_JWC2;
    // End of variables declaration//GEN-END:variables
}
