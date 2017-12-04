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
public class EditarRequerimientoEventHandler extends EventHandlerBase {

    private ResourceBundle bundle = ResourceBundle.getBundle("sgtURL");
    
    @Override
    protected String getURL(){
        
        return bundle.getString("EDITAR_REQUERIMIENTO");
    }
    
//    @Override
//    public void process(ServletContext sc, HttpServletRequest request, HttpServletResponse response)
//            throws IOException, ServletException, NamingException {
//        
//        HttpSession session = request.getSession(false);
//        String rol = (String)session.getAttribute("rol");
//        
//        if (rol!=null){
//            
//            
//            
//                  
//        }
//    }
    
}
