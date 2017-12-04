/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venvidrio.sgt.event;

import com.venvidrio.sgt.loader.TSgtActualizarLoader;
import com.venvidrio.sgt.loader.TSgtPendientesLoader;
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
public class BuscarEventHandler extends EventHandlerBase {

    private ResourceBundle bundle = ResourceBundle.getBundle("sgtURL");
    
    protected String getURL(){
        
        return bundle.getString("BUSCAR");
    }     
    @Override
    public void process(ServletContext sc, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, NamingException {
           
        
            TSgtPendientesLoader busqueda = new TSgtPendientesLoader();    
            String columna = "";
            String colum = (String) request.getParameter("selectTipoBusqueda");
            if(colum.equals("1")){
                columna = "per_nombre";                
            }else if(colum.equals("2")){
                columna = "sgt_desc_tipo_requerimiento";
            }else if(colum.equals("3")){
                columna = "sgt_desc_clasificacion";
            }
            
            String buscar = (String) request.getParameter("txtBuscar");
            System.out.println("--------------Datos a Buscar-------------------");
            System.out.println("Columna: "+columna);
            System.out.println("Busqueda: "+buscar);
            busqueda.BuscarRequerimientos(columna, buscar);
        }
}