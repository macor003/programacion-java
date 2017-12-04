/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Enumeration;
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
import loader.mareRegistrarLoader;
import utility.EventHandlerBase;

/**
 *
 * @author Mario
 */
public class GuardarActualizarDatosEventHandler  extends EventHandlerBase {

    private ResourceBundle bundle = ResourceBundle.getBundle("sgtURL");
    
    @Override
    protected String getURL(){
        
        return bundle.getString("GUARDAR_ACTUALIZAR_DATOS");
    }
    
    @Override
    public void process(ServletContext sc, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, NamingException {
        
                    
            mareRegistrarLoader registro = new mareRegistrarLoader();    
            
            String nombre_usu = (String) request.getParameter("lm_nombre");
            String apellido_usu = (String) request.getParameter("lm_apellido");
            String correo_usu =(String) request.getParameter("lm_correo");            
            String cedula = (String) request.getParameter("lm_cedula");
            String direccion = (String) request.getParameter("lm_direccion");
            System.out.println("----------------------");
            System.out.println("acutalizando datos");
            System.out.println("nombre: "+nombre_usu);
            System.out.println("apellido: "+apellido_usu);
            System.out.println("correo: " +correo_usu);
            System.out.println("cedula: "+cedula);
            System.out.println("direccion: "+direccion);
            
            int cedula_usu = Integer.parseInt(cedula);
            
            registro.ActualizarDatosUsuario(nombre_usu, apellido_usu, correo_usu, cedula_usu, direccion);
//      
                         
        
    }
}