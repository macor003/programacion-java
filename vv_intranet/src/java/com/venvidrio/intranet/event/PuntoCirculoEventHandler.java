/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venvidrio.intranet.event;

import com.venvidrio.intranet.loader.NoticiaLoader;
import com.venvidrio.intranet.utility.EventHandlerBase;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ortegam
 */
public class PuntoCirculoEventHandler extends EventHandlerBase {
    
     private ResourceBundle bundle = ResourceBundle.getBundle("intraURL");

     @Override
    protected String getURL() {

        return bundle.getString("PUNTO_Y_CIRCULO");

    }
    
     @Override
    public void process(ServletContext sc, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, NamingException {
            
            
        }
}
