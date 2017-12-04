/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venvidrio.sgt.event;


import com.venvidrio.sgt.loader.TSgtLogonLoader;
import com.venvidrio.sgt.utility.EventHandlerBase;
import com.venvidrio.sgt.loader.TSgtRequerimientoLoader;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class GuardarRequerimientoEventHandler extends EventHandlerBase {

    private ResourceBundle bundle = ResourceBundle.getBundle("sgtURL");
    
    protected String getURL(){
        
        return bundle.getString("GUARDAR_REQUERIMIENTO");
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
            TSgtRequerimientoLoader requerimiento = new TSgtRequerimientoLoader();    
            
            int cod_reque = requerimiento.getAutoID();
            String planta_usu = (String) request.getParameter("lm_planta");
            String dpto_usu = (String) request.getParameter("lm_dpto");
            String ficha_usu =(String) request.getParameter("lm_ficha");
            
            String usuario_log = (String) session.getAttribute("usuario");
            String ficha_log = (String) session.getAttribute("ficha");
            String planta_log = (String) session.getAttribute("planta");
            
            String cod_urge = (String) request.getParameter("lm_urgencia");
            String tipo_reque = (String) request.getParameter("lm_tipo_requerimiento");
            String area_reque = (String) request.getParameter("lm_area_requerimiento");
            String clasi_reque = (String) request.getParameter("lm_clasificacion");
            String desc_reque = (String) request.getParameter("lm_descripcion");
            //
            if(area_reque == null & clasi_reque == null){
                 area_reque ="";
                 clasi_reque ="";
            }
            
//            System.out.println("Area: "+area_reque);
//            System.out.println("Clasi: "+clasi_reque);
            
            requerimiento.insertarRequerimiento(cod_reque, planta_usu, cod_urge, dpto_usu, ficha_usu, tipo_reque, area_reque, clasi_reque, desc_reque, usuario_log, ficha_log, planta_log);
            
            
            if(tipo_reque.equals("01") || tipo_reque.equals("02")){
                requerimiento.CorreoRequerimientoCreado(cod_reque, desc_reque, ficha_usu, planta_usu); 
            }else{
                requerimiento.CorreoRequerimientoCreado(cod_reque, desc_reque, ficha_usu, planta_usu); 
                requerimiento.CorreoRequerimientoAprobar(cod_reque, desc_reque, ficha_usu, planta_usu, cod_urge);
            }
            
             
        }
    }
    
}