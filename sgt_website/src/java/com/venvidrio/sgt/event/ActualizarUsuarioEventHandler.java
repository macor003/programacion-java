/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venvidrio.sgt.event;

import com.venvidrio.sgt.loader.TSgtVerificarUsuarioLoader;
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

/**
 *
 * @author Ortegam
 */
public class ActualizarUsuarioEventHandler extends EventHandlerBase {

    private ResourceBundle bundle = ResourceBundle.getBundle("sgtURL");
    
    @Override
    protected String getURL(){
        
        return bundle.getString("ACTUALIZAR_USUARIO");
    }
    
    @Override
    public void process(ServletContext sc, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, NamingException {
              
                            
            TSgtVerificarUsuarioLoader enviar = new TSgtVerificarUsuarioLoader();
            
            String consecutivo = enviar.obtenerNuevoUsuario();
            String usuario = request.getParameter("lm_usuario");
            String correo = request.getParameter("lm_correo");
            String codigo_usuario="";
            String planta = enviar.verPlanta(usuario);
            
            if(planta.equals("02")){
                    codigo_usuario = "VGSIT"+consecutivo;
                    System.out.println("ESTE ES EL USUARIO NUEVO DE LOS GUAYOS: "+ codigo_usuario);
                }else if(planta.equals("05")){
                    codigo_usuario = "VVSIT"+consecutivo;
                    System.out.println("ESTE ES EL USUARIO NUEVO DE VALERA: "+ codigo_usuario);
                }
            
            System.out.println("------------------------");
            System.out.println("Este es el usuario: " +usuario);
            System.out.println("Este el correo a quien se le enviaran los datos: " + correo);
            
            enviar.insertarConsecutivo(consecutivo);
            enviar.verDatosUsuarioActualizar(usuario, correo, codigo_usuario);
            
        
    }
}
