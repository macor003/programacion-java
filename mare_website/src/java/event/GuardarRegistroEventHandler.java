/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import loader.mareRegistrarLoader;
import utility.EventHandlerBase;

/**
 *
 * @author Mario
 */
public class GuardarRegistroEventHandler  extends EventHandlerBase {

    private ResourceBundle bundle = ResourceBundle.getBundle("sgtURL");
    
    @Override
    protected String getURL(){
        
        return bundle.getString("GUARDAR_REGISTRO_USUARIO");
    }
    
    @Override
    public void process(ServletContext sc, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, NamingException {
        
        HttpSession session = request.getSession(false);
         String rol = "1";      
                    
            mareRegistrarLoader registro = new mareRegistrarLoader();    
            
            String nombre_usu = (String) request.getParameter("lm_nombre");
            String apellido_usu = (String) request.getParameter("lm_apellido");
            String correo_usu =(String) request.getParameter("lm_correo");
            String pass = (String) request.getParameter("lm_contrasena");
            String sexo = (String) request.getParameter("lm_sexo");
            String fecha_nac = (String) request.getParameter("lm_fecha_nac");
//            
        try {            
            registro.insertarUsuario(nombre_usu, apellido_usu, correo_usu, pass, sexo, fecha_nac);
        } catch (ParseException ex) {
            Logger.getLogger(GuardarRegistroEventHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
                         
        
    }
}