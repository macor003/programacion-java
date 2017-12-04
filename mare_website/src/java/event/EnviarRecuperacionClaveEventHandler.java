/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
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
import loader.ConsultarRegistrosLoader;
import loader.mareRegistrarLoader;
import utility.EventHandlerBase;

/**
 *
 * @author Mario
 */
public class EnviarRecuperacionClaveEventHandler extends EventHandlerBase {

    private ResourceBundle bundle = ResourceBundle.getBundle("sgtURL");
    
    @Override
    protected String getURL(){
        
        return bundle.getString("RECUPERAR_CLAVE");
    }
    
    @Override
    public void process(ServletContext sc, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, NamingException {
        
        HttpSession session = request.getSession(false);
         String rol = "1";      
                    
            ConsultarRegistrosLoader registro = new ConsultarRegistrosLoader();    
            
            String correo_usu =(String) request.getParameter("lm_correo");
//                     
            registro.verContrasena(correo_usu);
        
                         
        
    }
}
