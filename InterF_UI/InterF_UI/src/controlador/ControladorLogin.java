package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import vista.Login;
import static vista.Login.inputPassword;
import static vista.Login.loginBoton;
import static vista.Login.inputUsuario;
import static vista.Login.setInicioAdminVentana;
import static vista.Login.setInicioUsuarioVentana;
import clases_administradoras.*;
import static vista.InicioUsuario.labelMostrarInfoUsuario;


//import at.favre.lib.crypto.bcrypt.BCrypt;
public class ControladorLogin implements ActionListener {

    //NO LE MUEVAN A ESTAS VARIABLES SON LOS ATRIBUTOS DEL CONTROLADOR
    private final Login VISTA;
    private final UsuarioAdmin ADMINISTRADOR_USUARIO;

    //CONSTRUCTOR DE CONTROLADOR DEL LOGIN QUE SE INICIALIZA EN EL ARCHIVO PRINCIPAL
    public ControladorLogin(Login VISTA, UsuarioAdmin ADMINISTRADOR_USUARIO) {
        this.VISTA = VISTA;
        this.ADMINISTRADOR_USUARIO = ADMINISTRADOR_USUARIO;

        this.VISTA.loginBoton.addActionListener(this);
        this.VISTA.inputUsuario.addActionListener(this);
        this.VISTA.inputPassword.addActionListener(this);

    }

    //FUNCIONES==============================================================================================================================================================
    String traducirPassword() {

        char[] passwordChar = inputPassword.getPassword();
        String password = new String(passwordChar);
        return password;
    }

    //=======================================================================================================================================================================
    @Override
    public void actionPerformed(ActionEvent e) {

//BLOQUE IF QUE ACTIVA LOS EVENTOS AL PRESIONAR INICIAR SESION EN EL LOGIN=================================================================================================
        {
            if (e.getSource() == VISTA.loginBoton) {
                //Verifica que no hayan campos vacios
                if (VISTA.hayCamposVacios()) {
                    JOptionPane.showMessageDialog(null, "Hay campos vacios !!", "Aviso", JOptionPane.WARNING_MESSAGE);
                    //Y tambien que no sea administrador para mandarlo a la vista de usuario o gerente segun privilegios de la cuenta ingresada
                } else if (!VISTA.esAdmin()) {
                    try {

                        if (ADMINISTRADOR_USUARIO.validarLoginUsuario(inputUsuario.getText(), traducirPassword())) {

                            JOptionPane.showMessageDialog(null, "Bienvenido " + inputUsuario.getText(), "Acceso Correcto", JOptionPane.PLAIN_MESSAGE);
                            VISTA.dispose();
                            labelMostrarInfoUsuario.setText(inputUsuario.getText());
                            loginBoton.setBackground_No_Hover_1(new java.awt.Color(0, 0, 0));
                            loginBoton.setBackground_No_Hover_2(new java.awt.Color(0, 0, 0));
                            inputUsuario.setText("Ingrese su usuario");
                            inputPassword.setText("**********");
                            setInicioUsuarioVentana();

                        } else {

                            JOptionPane.showMessageDialog(null, "Datos incorrectos verifica", "Aviso", JOptionPane.ERROR_MESSAGE);
                            loginBoton.setBackground_No_Hover_1(new java.awt.Color(0, 0, 0));
                            loginBoton.setBackground_No_Hover_2(new java.awt.Color(0, 0, 0));
                            inputUsuario.setText("Ingrese su usuario");
                            inputPassword.setText("**********");

                        }

                    } catch (Exception ex) {
                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                //Verifica si el que inicia sesion es el administrador , y si lo es lo manda a su respectiva vista
                if (VISTA.esAdmin()) {
                    JOptionPane.showMessageDialog(null, "Binevenido Administrador", "Acceso Privilegiado", JOptionPane.INFORMATION_MESSAGE);
                    try {
                        VISTA.dispose();
                        loginBoton.setBackground_No_Hover_1(new java.awt.Color(0, 0, 0));
                        loginBoton.setBackground_No_Hover_2(new java.awt.Color(0, 0, 0));
                        inputUsuario.setText("Ingrese su usuario");
                        inputPassword.setText("**********");
                        setInicioAdminVentana();
                    } catch (Exception ex) {
                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println(ex);
                    }

                }

            }
        }
        //AQUI TERMINA EL BLOQUE IF DEL BOTON DE INICIAR SESION====================================================================================================================

    }

}
