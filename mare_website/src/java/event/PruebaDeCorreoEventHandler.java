/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import utility.EventHandlerBase;
import java.io.IOException;
import java.util.Enumeration;
import java.util.ResourceBundle;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import loader.mareCorreoLoader;

/**
 *
 * @author Ortegam
 */
public class PruebaDeCorreoEventHandler extends EventHandlerBase {

    private ResourceBundle bundle = ResourceBundle.getBundle("sgtURL");
    
    protected String getURL(){
        
        return bundle.getString("CORREO");
    }
    
    @Override
    public void process(ServletContext sc, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, NamingException {
        
        mareCorreoLoader correo =  new mareCorreoLoader();
        
        correo.enviarCorreo();
        
        
    }
}
