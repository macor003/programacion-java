/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venvidrio.cdv.event;

import com.venvidrio.cdv.loader.UsuariosLoader;
import com.venvidrio.cdv.utility.EventHandlerBase;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ortegam
 */
public class CambioClaveEventHandler extends EventHandlerBase  {
    
    private ResourceBundle bundle = ResourceBundle.getBundle("cdvURL");

    @Override
      protected String getURL() {
            return bundle.getString("CAMBIAR_CONTRASENA");
      }
    @Override
    public void process(ServletContext sc, HttpServletRequest request, HttpServletResponse response)
                 throws IOException, ServletException, NamingException {
        
        System.out.println("Bandera 1");
            HttpSession session = request.getSession(false);
            UsuariosLoader usuario = new UsuariosLoader();    
            
        System.out.println("Bandera 1");       
            
            String rif = (String) session.getAttribute("rif");
            String contraseña = (String) request.getParameter("pass1");
        System.out.println("Bandera 1");
        System.out.println("contraseña: "+contraseña);
       
        try {
            usuario.CambioClaveUsuario(rif, contraseña);
            request.setAttribute("mensaje1", "El cambio de contraseña se realizo con exito");            
            request.setAttribute("dir", "cdv_config_exito.jsp");
        } catch (SQLException ex) {
            System.out.println("ErrorEvent: "+ex);
            Logger.getLogger(CambioClaveEventHandler.class.getName()).log(Level.SEVERE, null, ex);
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
