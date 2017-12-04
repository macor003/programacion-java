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
public class GuardarRegistroUsuarioEventHandler extends EventHandlerBase {

    private ResourceBundle bundle = ResourceBundle.getBundle("sgtURL");
    
    @Override
    protected String getURL(){
        
        return bundle.getString("GUARDAR_REGISTRO_USUARIO");
    }
    
    @Override
    public void process(ServletContext sc, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, NamingException {
               
        
            TSgtVerificarUsuarioLoader registrar= new TSgtVerificarUsuarioLoader();    
            String consecutivo = registrar.obtenerNuevoUsuario();            
        
            try{
                String planta_usu = (String) request.getParameter("lm_planta");            
                String ficha_usu =(String) request.getParameter("lm_ficha");
                String nombre_usu =(String) request.getParameter("lm_nombre");
                String dpto_usu =(String) request.getParameter("lm_dpto");
                String contrasena_usu = (String) request.getParameter("lm_contrasena");                
                String correo_usu ="";
                String codigo_usuario ="";
                
                String correo_si_no = (String) request.getParameter("lm_si_no");
                
                if(correo_si_no != null){
                    if(correo_si_no.equals("si")){
                        correo_usu =(String) request.getParameter("lm_correo").trim();
                    }else{
                        correo_usu = "atencion.soporte@venvidrio.com.ve";
                    }
                }else{
                    correo_usu = (String) request.getParameter("lm_correo").trim();
                }
                

                System.out.println("PLANTA DEL USUARIO QUE SE VA A REGISTRAR: "+ planta_usu);
                
                if(planta_usu.equals("02")){
                    codigo_usuario = "VGSIT"+consecutivo;
                    System.out.println("ESTE ES EL USUARIO NUEVO DE LOS GUAYOS: "+ codigo_usuario);
                }else if(planta_usu.equals("05")){
                    codigo_usuario = "VVSIT"+consecutivo;
                    System.out.println("ESTE ES EL USUARIO NUEVO DE LOS GUAYOS: "+ codigo_usuario);
                }
                System.out.println("NOMBRE DEL USUARIO: "+ nombre_usu);
                System.out.println("FICHA DEL USUARIO: " + ficha_usu);
                System.out.println("DEPARTAMENTO DEL USUARIO: " + dpto_usu);
                System.out.println("CORREO DEL USUARIO: " +correo_usu);
                System.out.println("CONTRASEÑA DEL USUARIO: "+contrasena_usu);
                
                registrar.insertarConsecutivo(consecutivo);                
                registrar.insertarNuevoUsuario(planta_usu, ficha_usu, codigo_usuario, contrasena_usu, correo_usu); 
            }catch(Exception e){
                System.out.println("ERROR: " + e);
            }
            
                  
        }
}
