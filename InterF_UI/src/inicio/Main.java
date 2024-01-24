package inicio;

import com.formdev.flatlaf.FlatDarkLaf; //<-- Libreria de flatlaf
import controlador.*;
import java.awt.Font;
import javax.swing.UIManager;
import modelo.*;
import vista.*;

public class Main {

//ESTAS SON PARAMETROS DEL CONTORLADOR
    public static Gerente gerente = new Gerente();
    public static ConsultasGerente conGen = new ConsultasGerente();
    public static Usuario usuario = new Usuario();
    public static ConsultasUsuario conUse = new ConsultasUsuario();
    public static Login inicio = new Login();

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
    
    public static void main(String[] args) {

        /*AQUI SE INVOCA LA FUNCION DE LA LIBRERIA DE APARIENCIA OSCURA FLATLAF*/
        FlatDarkLaf.setup();
        setUIFont(new javax.swing.plaf.FontUIResource("JetBrains Mono Regular", Font.PLAIN, 14));
        String[] palabra = new String[5];

        //ESTE ES EL CONSTRUCTOR QUE INICIALIZA EL CONTROLADOR Y LA INTERFAZ
        ControladorLogin ctrl = new ControladorLogin(gerente, usuario, inicio, conGen, conUse);
        inicio.setVisible(true);
        
    }
    
}
