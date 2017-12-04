/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venvidrio.sgt.event;

import com.venvidrio.sgt.loader.TSgtPendientesLoader;
import com.venvidrio.sgt.utility.EventHandlerBase;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.ResourceBundle;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ortegam
 */
public class GuardarAsignacionEventHandler extends EventHandlerBase {

    private ResourceBundle bundle = ResourceBundle.getBundle("sgtURL");
    
    protected String getURL(){
        
        return bundle.getString("GUARDAR_ASIGNACION");
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
            String planta_asig = (String) request.getParameter("lm_planta");
            String ficha_asig =(String) request.getParameter("lm_ficha_analista"); 
            
            String ficha_log = (String) session.getAttribute("ficha");
            String planta_log = (String) session.getAttribute("planta");
            
            String area_asig = asignar.verAreaAnalista(ficha_asig, planta_asig);
            
            asignar.insertarAsignacionRequerimiento(cod_reque, ficha_asig, planta_asig, ficha_log, planta_log, area_asig);
            asignar.CorreoRequerimientoAsignado(cod_reque, ficha_asig, ficha_log, planta_asig, planta_log);
            
        }
    }
}
