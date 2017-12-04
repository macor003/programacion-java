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
public class GuardarCrearAreaEventHandler extends EventHandlerBase {

    private ResourceBundle bundle = ResourceBundle.getBundle("sgtURL");
    
    @Override
    protected String getURL(){
        
        return bundle.getString("GUARDAR_CREAR_AREA");
    }     
    @Override
    public void process(ServletContext sc, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, NamingException {
           
        
            TSgtActualizarLoader area = new TSgtActualizarLoader();    
            

            String cod_area = (String) request.getParameter("cod_area");
            String desc_area = (String) request.getParameter("desc_area");
            
            System.out.println("--------------Guardando Area Nueva-------------------");
            System.out.println("Estes es el nuevo codigo de area: "+cod_area);
            System.out.println("Esta es la nueva descripción del area: "+desc_area);
            area.insertarArea(cod_area, desc_area);
        }
}
