package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.*;
import vista.Login;
import static vista.Login.setInicioAdminVentana;
import static vista.Login.setInicioUsuarioVentana;

//import at.favre.lib.crypto.bcrypt.BCrypt;
public class ControladorLogin implements ActionListener {

    //NO LE MUEVAN A ESTAS VARIABLES SON LOS ATRIBUTOS DEL CONTROLADOR
    private final Gerente GERENTE;
    private final Usuario USUARIO;
    private final Login VISTA;
    private final ConsultasGerente CONSULTA_GERENTE;
    private final ConsultasUsuario CONSULTA_USUARIO;
    
    //CONSTRUCTOR DE CONTROLADOR DEL LOGIN QUE SE INICIALIZA EN EL ARCHIVO PRINCIPAL
    public ControladorLogin(Gerente GERENTE, Usuario USUARIO, Login VISTA, ConsultasGerente CONSULTA_GERENTE, ConsultasUsuario CONSULTA_USUARIO) {
        this.GERENTE = GERENTE;
        this.USUARIO = USUARIO;
        this.VISTA = VISTA;
        this.CONSULTA_GERENTE = CONSULTA_GERENTE;
        this.CONSULTA_USUARIO = CONSULTA_USUARIO;

        this.VISTA.loginBoton.addActionListener(this);
        this.VISTA.inputUsuario.addActionListener(this);
        this.VISTA.inputPassword.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        //BLOQUE IF QUE ACTIVA LOS EVENTOS AL PRESIONAR INICIAR SESION EN EL LOGIN=================================================================================================
        if (e.getSource() == VISTA.loginBoton) {
            //Verifica que no hayan campos vacios
            if (VISTA.hayCamposVacios()) {
                JOptionPane.showMessageDialog(null, "Hay campos vacios !!", "Aviso", JOptionPane.WARNING_MESSAGE);
              //Y tambien que no sea administrador para mandarlo a la vista de usuario o gerente segun privilegios de la cuenta ingresada
            } else if (!VISTA.esAdmin()) {
                try {
                    JOptionPane.showMessageDialog(null, "Bienvenido " + VISTA.inputUsuario.getText(), "Acceso Correcto", JOptionPane.PLAIN_MESSAGE);
                    setInicioUsuarioVentana();
                    VISTA.dispose();
                } catch (Exception ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //Verifica si el que inicia sesion es el administrador , y si lo es lo manda a su respectiva vista
            if (VISTA.esAdmin()) {
                JOptionPane.showMessageDialog(null, "Binevenido Administrador", "Acceso Privilegiado", JOptionPane.INFORMATION_MESSAGE);
                try {
                    setInicioAdminVentana();
                    VISTA.dispose();
                } catch (Exception ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println(ex);
                }

            }

        }
        //AQUI TERMINA EL BLOQUE IF DEL BOTON DE INICIAR SESION====================================================================================================================

    }

}
