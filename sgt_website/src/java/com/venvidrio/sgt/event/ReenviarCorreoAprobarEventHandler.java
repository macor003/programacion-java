/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venvidrio.sgt.event;

import com.venvidrio.sgt.loader.TSgtPendientesLoader;
import com.venvidrio.sgt.loader.TSgtRequerimientoLoader;
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
public class ReenviarCorreoAprobarEventHandler extends EventHandlerBase {

    private ResourceBundle bundle = ResourceBundle.getBundle("sgtURL");
    
    protected String getURL(){
        
        return bundle.getString("REENVIAR_CORREO_APROBAR");
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
            
            TSgtRequerimientoLoader enviar = new TSgtRequerimientoLoader();    
            
            String cod = (String) request.getParameter("cod");
            int cod_reque = Integer.parseInt(cod);
            enviar.ReenviarCorreoRequerimientoAprobar(cod_reque);
                  
        }
    }
}
