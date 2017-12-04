/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venvidrio.sms.event;

import com.venvidrio.sms.loader.TSmsMensajesLoader;
import com.venvidrio.sms.utility.EventHandlerBase;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
 * @author ortegam
 */
public class GuardarEnvioEventHandler extends EventHandlerBase {

    private ResourceBundle bundle = ResourceBundle.getBundle("smsURL");
    
    @Override
    protected String getURL(){
        
        return bundle.getString("GUARDAR_MENSAJE");
    }     
    @Override
    public void process(ServletContext sc, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, NamingException {
            
            HttpSession session = request.getSession(false);
            String rol = (String)session.getAttribute("rol");
            
            if(rol!=null){
                TSmsMensajesLoader sms = new TSmsMensajesLoader();    

                String usuario = (String) session.getAttribute("usuario");
                String directorio = (String) request.getParameter("lm_directorio");
                String mensaje = (String) request.getParameter("lm_mensaje");

                System.out.println("--------------Guardando Mensaje-------------------");
                System.out.println("Directorio: "+directorio);
                System.out.println("Mensaje: "+mensaje);
                try {
                    sms.GuardarMensaje(directorio, mensaje, usuario);
                    //sms.EnviarSMS(directorio, mensaje);  
                } catch (SQLException ex) {
                    System.out.println("Error guardando: "+ex);
                    ex.printStackTrace();
                    //Logger.getLogger(GuardarEnvioEventHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
            
        }

}
