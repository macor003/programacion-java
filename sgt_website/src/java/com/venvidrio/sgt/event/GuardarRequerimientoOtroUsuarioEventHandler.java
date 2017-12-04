/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venvidrio.sgt.event;

import com.venvidrio.sgt.loader.TSgtPendientesLoader;
import com.venvidrio.sgt.utility.EventHandlerBase;

import com.venvidrio.sgt.utility.EventHandlerBase;
import com.venvidrio.sgt.loader.TSgtRequerimientoLoader;

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
public class GuardarRequerimientoOtroUsuarioEventHandler extends EventHandlerBase {

    private ResourceBundle bundle = ResourceBundle.getBundle("sgtURL");
    
    protected String getURL(){
        
        return bundle.getString("GUARDAR_REQUERIMIENTO_OTRO_USUARIO");
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
            System.out.println("Bandera 0");
            int cod_reque = requerimiento.getAutoID();
            String planta_usu = (String) request.getParameter("lm_planta");
            String dpto_usu = (String) request.getParameter("lm_dpto");
            String ficha_usu =(String) request.getParameter("lm_ficha");
            System.out.println("Bandera 1");
            
            String cod_urge = (String) request.getParameter("lm_urgencia");
            System.out.println("Bandera 2");
            String tipo_reque = (String) request.getParameter("lm_tipo_requerimiento");
            String area_reque = (String) request.getParameter("lm_area_requerimiento");
            System.out.println("Bandera 3");
            String clasi_reque = (String) request.getParameter("lm_clasificacion");
            String desc_reque = (String) request.getParameter("lm_descripcion");
            System.out.println("Bandera 4");
            
            String cod_planta_analista = (String) request.getParameter("lm_planta_analista");
            String ficha_analista = (String) request.getParameter("lm_ficha_analista");
            System.out.println("Codigo planta: "+ cod_planta_analista);
            System.out.println("Ficha: "+ ficha_analista);
            
            
            String ficha_log = (String) session.getAttribute("ficha");
            String usuario_log = (String) session.getAttribute("usuario");            
            String planta_log = (String) session.getAttribute("planta");
                        
            
            //System.out.println("Codigo de la prioridad: "+ cod_priori);
            if(cod_planta_analista.equals("su") && ficha_analista.equals("su")){
                cod_planta_analista="NULL";
                ficha_analista="NULL";
            }
            
            
            requerimiento.insertarRequerimientoOtroUsuario(cod_reque, planta_usu, cod_urge, dpto_usu, ficha_usu, tipo_reque, area_reque, clasi_reque, desc_reque, ficha_log, planta_log, usuario_log, cod_planta_analista, ficha_analista);
            
            if(tipo_reque.equals("01") || tipo_reque.equals("02")){
                requerimiento.CorreoRequerimientoCreado(cod_reque, desc_reque, ficha_usu, planta_usu);
            }else{
                requerimiento.CorreoRequerimientoCreado(cod_reque, desc_reque, ficha_usu, planta_usu);
                requerimiento.CorreoRequerimientoAprobar(cod_reque, desc_reque, ficha_usu, planta_usu, cod_urge);
            }
            
        }
    }
    
}