/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venvidrio.cnt.event;

import com.venvidrio.cnt.utility.EventHandlerBase;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ortegam
 */
public class CargarNoticiaEventHandler extends EventHandlerBase  {

    private ResourceBundle bundle = ResourceBundle.getBundle("cntURL");

    @Override
      protected String getURL() {

          return bundle.getString("CARGAR_NOTICIA");
      }
    @Override
    public void process(ServletContext sc, HttpServletRequest request, HttpServletResponse response)
                 throws IOException, ServletException, NamingException {
        
        
        
            

            String nombre=(String) request.getParameter("lm_nombre");
            String correo=(String) request.getParameter("lm_correo");
            String direccion=(String) request.getParameter("lm_direccion");
            
           
            
        
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
