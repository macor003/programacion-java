/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import loader.ConsultarRegistrosLoader;
import utility.EventHandlerBase;

/**
 *
 * @author Mario
 */
public class GuardarPedidoEventHandler extends EventHandlerBase {

    private ResourceBundle bundle = ResourceBundle.getBundle("sgtURL");
    
    @Override
    protected String getURL(){
        
        return bundle.getString("GUARDAR_PEDIDO");
    }
    
    @Override
    public void process(ServletContext sc, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, NamingException {
        
        HttpSession session = request.getSession(false);
             
                    
            ConsultarRegistrosLoader registro = new ConsultarRegistrosLoader();    
            
            String correo_usu =(String) session.getAttribute("correo");
//                     
            registro.verContrasena(correo_usu);
        
                         
        
    }

}
