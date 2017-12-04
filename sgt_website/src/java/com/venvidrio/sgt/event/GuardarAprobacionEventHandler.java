/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venvidrio.sgt.event;

import com.venvidrio.sgt.loader.TSgtAprobarLoader;

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
public class GuardarAprobacionEventHandler extends EventHandlerBase {

    private ResourceBundle bundle = ResourceBundle.getBundle("sgtURL");
    
    protected String getURL(){
        
        return bundle.getString("GUARDAR_APROBACION");
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
            TSgtAprobarLoader detalle =new TSgtAprobarLoader();
            
            String cod = (String) request.getParameter("cod");
            int cod_reque = Integer.parseInt(cod);
            String ficha = (String) session.getAttribute("ficha");
            String usuario_log = (String) session.getAttribute("usuario");
            String ficha_log = (String) session.getAttribute("ficha");
            String planta_log = (String) session.getAttribute("planta");
            System.out.println("------------------------");
            System.out.println("codigo reque Aprobar" + cod_reque);
            System.out.println("ficha de usuario aprobador "+ficha);           
            
            detalle.CorreoAprobado(cod_reque);
            detalle.insertarAprobacionRequerimiento(cod_reque, ficha, usuario_log, ficha_log, planta_log);
            
        }
    }
}
