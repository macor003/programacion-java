/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venvidrio.sgt.event;

import com.venvidrio.sgt.loader.TSgtActualizarLoader;
import com.venvidrio.sgt.loader.TSgtRequerimientoLoader;
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
public class GuardarEdicionRequerimientoEventHandler extends EventHandlerBase {

    private ResourceBundle bundle = ResourceBundle.getBundle("sgtURL");
    
    protected String getURL(){
        
        return bundle.getString("GUARDAR_EDICION_REQUERIMIENTO");
    }
    
    @Override
    public void process(ServletContext sc, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, NamingException {
        
        HttpSession session = request.getSession(false);
        String rol = (String)session.getAttribute("rol");
        
        if (rol!=null){
            Enumeration parametros=request.getParameterNames();
            
            int i=0;
            while(parametros.hasMoreElements()){
                i++;
                String param=(String) parametros.nextElement();
                System.out.println("PARAM "+i+" == "+param);
            }
            TSgtActualizarLoader requerimiento = new TSgtActualizarLoader();    
            
                        
            String usuario_log = (String) session.getAttribute("usuario");
            String ficha_log = (String) session.getAttribute("ficha");
            String planta_log = (String) session.getAttribute("planta");
            
            
            String cod_reque = (String) request.getParameter("lm_cod");
            String cod_urge = (String) request.getParameter("lm_urgencia");
            String tipo_reque = (String) request.getParameter("lm_tipo_requerimiento");
            String area_reque = (String) request.getParameter("lm_area_requerimiento");
            String clasi_reque = (String) request.getParameter("lm_clasificacion");
            String desc_reque = (String) request.getParameter("lm_descripcion");
            //String area_reque ="";
            //String clasi_reque ="";
            
            System.out.println("Datos para actualizar: cod_requerimiento:  "+cod_reque+" codigo urgencia "+cod_urge+" codigo tipo reque "+tipo_reque+" area reque: "+area_reque+" clasi reque: "+clasi_reque+" desc reque "+desc_reque+"");
            
            requerimiento.ActualizarRequerimiento(cod_reque, cod_urge, tipo_reque, area_reque, clasi_reque, desc_reque, ficha_log, planta_log);
            
                        
             
        }
    }

}
