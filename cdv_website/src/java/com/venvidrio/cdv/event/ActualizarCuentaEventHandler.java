package com.venvidrio.cdv.event;

import com.venvidrio.cdv.loader.CarritoLoader;
import com.venvidrio.cdv.loader.UsuariosLoader;
import com.venvidrio.cdv.utility.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by IntelliJ IDEA.
 * User: CarmonaHJD
 * Date: 28-ago-2012
 * Time: 16:02:01
 * To change this template use File | Settings | File Templates.
 */
public class ActualizarCuentaEventHandler extends EventHandlerBase  {
    
    private ResourceBundle bundle = ResourceBundle.getBundle("cdvURL");

    @Override
      protected String getURL() {
          System.out.println("Error Actualizar");
            return bundle.getString("ACTUALIZAR_CUENTA");
      }
    @Override
    public void process(ServletContext sc, HttpServletRequest request, HttpServletResponse response)
                 throws IOException, ServletException, NamingException {
        
        System.out.println("Bandera 1");
            HttpSession session = request.getSession(false);
            UsuariosLoader usuario = new UsuariosLoader();    
            
               
            
            String rif = (String) session.getAttribute("rif");
            String nombre = (String) request.getParameter("lm_nombre");
            String email = (String) request.getParameter("lm_email");
            String direccion = (String) request.getParameter("lm_direccion");
            String tlf_fijo = (String) request.getParameter("lm_tlf_fijo");
            String tlf_movil = (String) request.getParameter("lm_tlf_movil");
            
            System.out.println("--------------Guardando -------------------");
            System.out.println("rif: "+rif.toUpperCase());
            System.out.println("nombre: "+nombre);
            System.out.println("email: "+email);
            System.out.println("direccion: "+direccion);
            System.out.println("tlf_fijo: "+tlf_fijo);
            System.out.println("tlf_movil: "+tlf_movil);
        
        try {
            usuario.ActualizarUsuario(rif, nombre, email, direccion, tlf_fijo, tlf_movil);
            request.setAttribute("mensaje1", "Su registro ha sido guardado exitosamente");            
            request.setAttribute("dir", "cdv_config_exito.jsp");
        } catch (SQLException ex) {
            System.out.println("Error: "+ex);
            Logger.getLogger(ActualizarCuentaEventHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
        
    }
    
    @Override
    protected void _dispatch(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        //Debug.log(this, "_dispatch", "redirecting to " + getURL());
        RequestDispatcher rd  = request.getRequestDispatcher((String) request.getAttribute("dir"));
        if (rd == null) {
            // Debug.log (this, "_dispatch", "rd = null!");
        }
        rd.forward(request, response);
     }
    
}

