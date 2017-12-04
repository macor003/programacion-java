/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venvidrio.sgt.event;

import com.venvidrio.sgt.loader.TSgtActualizarLoader;
import com.venvidrio.sgt.loader.TSgtAprobarLoader;
import com.venvidrio.sgt.utility.EventHandlerBase;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
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
public class GuardarProcesarEventHandler extends EventHandlerBase {

    private ResourceBundle bundle = ResourceBundle.getBundle("sgtURL");
    
    protected String getURL(){
        
        return bundle.getString("PROCESAR_REQUERIMIENTO");
    }
    
    @Override
    public void process(ServletContext sc, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, NamingException {
        
        HttpSession session = request.getSession(false);
        String rol = (String)session.getAttribute("rol");
        
        if (rol!=null){
            
            TSgtActualizarLoader detalle =new TSgtActualizarLoader();
            
            String status ="8";
            String cod = (String) request.getParameter("cod");
            String ficha_log = (String) session.getAttribute("ficha");
            String planta_log = (String) session.getAttribute("planta");           
            
            detalle.actualizarStatusProcesar(cod, status, ficha_log, planta_log);            
            detalle.CorreoRequerimientoProcesando(cod);
            
            
        }
    }
}
