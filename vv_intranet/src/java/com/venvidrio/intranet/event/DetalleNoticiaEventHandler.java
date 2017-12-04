/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venvidrio.intranet.event;

import com.venvidrio.intranet.loader.NoticiaLoader;
import com.venvidrio.intranet.utility.EventHandlerBase;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ortegam
 */
public class DetalleNoticiaEventHandler extends EventHandlerBase {
    
     private ResourceBundle bundle = ResourceBundle.getBundle("intraURL");

     @Override
    protected String getURL() {

        return bundle.getString("DETALLE_NOTICIA");

    }
    
     @Override
    public void process(ServletContext sc, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, NamingException {
            System.out.println("--------------Consulta Noticia-------------------");
        
            NoticiaLoader noticia = new NoticiaLoader();    
            
            String cod_noticia = request.getParameter("codigo");
            
            System.out.println("Codigo de Noticias: "+cod_noticia);
            
         try {
             noticia.getDetalleNoticia(cod_noticia);
         } catch (SQLException ex) {
             Logger.getLogger(DetalleNoticiaEventHandler.class.getName()).log(Level.SEVERE, null, ex);
         }
            
        }
}