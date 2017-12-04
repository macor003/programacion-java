/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venvidrio.sgt.event;

import com.venvidrio.sgt.loader.TSgtPendientesLoader;
import com.venvidrio.sgt.utility.EventHandlerBase;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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

/**
 *
 * @author Ortegam
 */
public class GuardarComentarioEventHandler extends EventHandlerBase {

    private ResourceBundle bundle = ResourceBundle.getBundle("sgtURL");
    
    @Override
    protected String getURL(){
        
        return bundle.getString("GUARDAR_COMENTARIO");
    }
    
    @Override
    public void process(ServletContext sc, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, NamingException {
        
        HttpSession session = request.getSession(false);
        String rol = (String)session.getAttribute("rol");
        
        if (rol!=null){
            Enumeration parametros=request.getParameterNames();
            
            int i=0;
            while(parametros.hasMoreElements()){
                i++;
                String param=(String) parametros.nextElement();
                System.out.println("PARAM "+i+" == "+param);
            }
            
            TSgtPendientesLoader asignar = new TSgtPendientesLoader();
            
            String cod = (String) request.getParameter("cod");
            int cod_reque = Integer.parseInt(cod);
            String usuario_log = (String) session.getAttribute("usuario");
            String comentario = (String) request.getParameter("textSolucion");
            String ficha_log = (String) session.getAttribute("ficha");
            String planta_log = (String) session.getAttribute("planta");
            try {
                asignar.insertarComentarioRequerimiento(cod_reque, usuario_log, comentario, ficha_log, planta_log);
            } catch (SQLException ex) {
                Logger.getLogger(GuardarComentarioEventHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
                  
        }
    }
}
