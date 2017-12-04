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
public class GuardarAreaAnalistaEventHandler extends EventHandlerBase {

    private ResourceBundle bundle = ResourceBundle.getBundle("sgtURL");
    
    @Override
    protected String getURL(){
        
        return bundle.getString("GUARDAR_AREA_ANALISTA");
    }     
    @Override
    public void process(ServletContext sc, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, NamingException {
           
        
            TSgtActualizarLoader area = new TSgtActualizarLoader();    
            
            int codigo = area.obtenerCodAutoAnalistaArea();
            String cod = Integer.toString(codigo);
            
            
            
            String cod_planta = (String) request.getParameter("lm_planta");
            String ficha = (String) request.getParameter("lm_ficha");
            String cod_area = (String) request.getParameter("lm_area");
            String cod_tipo = (String) request.getParameter("lm_tipo_analista");
            System.out.println("--------------Guardando Analista Area -------------------");
            System.out.println("planta analisa: "+cod_planta);
            System.out.println("ficha analista: "+ficha);
            System.out.println("area: "+cod_area);
            System.out.println("tipo analista: "+cod_tipo);
            area.insertarAnalistaArea( cod, cod_planta, ficha, cod_area, cod_tipo);
        }
}
