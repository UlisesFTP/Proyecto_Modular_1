package inicio;

import com.formdev.flatlaf.FlatDarkLaf; //<-- Libreria de flatlaf
import controlador.*;
import java.awt.Font;
import javax.swing.UIManager;
import vista.*;
import clases_administradoras.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {



    private static ControladorLogin ctrl;

    //VARIABLES DE VISTAS
    public static Login inicio = new Login();
    public static InicioAdministrador ventanaAdministrador = new InicioAdministrador();
    public static InicioUsuario ventanaUsuario = new InicioUsuario();

    //VARIABLES CLASES ADMINISTRADORAS
    public static UsuarioAdmin admnistradorUsuarios;


    /*FUNCION QUE INSTALA UNA FUENTE POR DEFECTO AL PROYECTO SE OCUPA
    LA LIBRERIA DE FLATLAF E IMPORTAR PD: HACE QUE LOS JOPTION PANE TENGAN UN ASPECTO MAS ESTETICO
     */
    //NO LE MUEVAN SI OCUPAN LA LIBRERIA MANDEN MENSAJE
    public static void setUIFont(javax.swing.plaf.FontUIResource f) {
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key, f);
            }
        }
    }

    public static void inicializarFlatLaf() {

        FlatDarkLaf.setup();
        setUIFont(new javax.swing.plaf.FontUIResource("JetBrains Mono NL", Font.PLAIN, 13));
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("FlatLaf".equals(info.getName())) {
                try {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(InicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
        }

    }

    public static void main(String[] args) {

        /*AQUI SE INVOCA LA FUNCION DE LA LIBRERIA DE APARIENCIA OSCURA FLATLAF*/
        inicializarFlatLaf();
        try {
            admnistradorUsuarios = new UsuarioAdmin();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        //ESTE ES EL CONSTRUCTOR QUE INICIALIZA LOS CONTROLADORES Y LA INTERFAZ
        ctrl = new ControladorLogin(inicio, admnistradorUsuarios);

        inicio.setVisible(true);

    }

}
