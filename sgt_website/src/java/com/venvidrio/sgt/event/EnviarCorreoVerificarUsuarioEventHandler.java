/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venvidrio.sgt.event;


import com.venvidrio.sgt.loader.TSgtVerificarUsuarioLoader;

import com.venvidrio.sgt.utility.EventHandlerBase;
import java.io.IOException;
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
public class EnviarCorreoVerificarUsuarioEventHandler extends EventHandlerBase {

    private ResourceBundle bundle = ResourceBundle.getBundle("sgtURL");
    
    @Override
    protected String getURL(){
        
        return bundle.getString("CORREO_VERIFICACION");
    }
    
    @Override
    public void process(ServletContext sc, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, NamingException {
              
                            
            TSgtVerificarUsuarioLoader enviar = new TSgtVerificarUsuarioLoader();
            String usuario = request.getParameter("lm_usuario");
            String correo = request.getParameter("lm_correo");
            System.out.println("------------------------");
            System.out.println("Este es el usuario: " +usuario);
            System.out.println("Este el correo a quien se le enviaran los datos: " + correo);
            
            enviar.verDatosCorreoUsuario(usuario, correo);
            
        
    }
}
