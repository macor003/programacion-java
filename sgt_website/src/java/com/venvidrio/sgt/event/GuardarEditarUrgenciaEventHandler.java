/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venvidrio.sgt.event;

import com.venvidrio.sgt.loader.TSgtActualizarLoader;
import com.venvidrio.sgt.utility.EventHandlerBase;
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

/**
 *
 * @author Ortegam
 */
public class GuardarEditarUrgenciaEventHandler extends EventHandlerBase {

    private ResourceBundle bundle = ResourceBundle.getBundle("sgtURL");
    
    @Override
    protected String getURL(){
        
        return bundle.getString("GUARDAR_EDITAR_URGENCIA");
    }     
    @Override
    public void process(ServletContext sc, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, NamingException {
           
        
            TSgtActualizarLoader urgencia = new TSgtActualizarLoader();    
            

            String cod_reque = (String) request.getParameter("cod");
            String cod_urgencia = (String) request.getParameter("cod_urge");

            System.out.println("--------------Guardando editar urgencia-------------------");
            System.out.println("Codigo del Requerimiento: "+cod_reque);
            System.out.println("Codigo de la nueva urgencia: "+cod_urgencia);
            
            
            request.getSession().setAttribute("cod_reque", "cod_reque");
            //response.sendRedirect("sgtDetallesRequerimientoPendiente.jsp");
            request.getRequestDispatcher("sgtDetallesRequerimientoPendiente.jsp").forward(request, response);
            urgencia.editarUrgencia(cod_reque, cod_urgencia);
         }
}
