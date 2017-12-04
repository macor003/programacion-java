/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venvidrio.cdv.event;

import com.venvidrio.cdv.loader.CarritoLoader;
import com.venvidrio.cdv.utility.EventHandlerBase;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
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
public class ActualizarDespachoEventHandler extends EventHandlerBase  {
    
    private ResourceBundle bundle = ResourceBundle.getBundle("cdvURL");

    @Override
      protected String getURL() {
          
            return bundle.getString("ACTUALIZAR_DESPACHO");
      }
    @Override
    public void process(ServletContext sc, HttpServletRequest request, HttpServletResponse response)
                 throws IOException, ServletException, NamingException {
        
        System.out.println("Bandera 1");
            HttpSession session = request.getSession(false);
            CarritoLoader pedido = new CarritoLoader();    
            
               
            int cantidad= Integer.parseInt(request.getParameter("lm_cant_productos"));            
            ArrayList lista=new ArrayList();
            for(int i=0; i<cantidad; i++){
                String rif = (String) session.getAttribute("rif");
                String var_cod_articulo=request.getParameter("lm_cod_producto"+(i+1)+"");
                String var_cant_despachada=request.getParameter("lm_despachada"+(i+1)+"");
                String cod_estatus=request.getParameter("lm_cod_estatus");
                String cod_pedido=request.getParameter("lm_codigo_pedido");
                
                System.out.println("Codigo_articulo "+(i+1)+": "+var_cod_articulo);
                System.out.println("Despacho "+(i+1)+": "+var_cant_despachada);
                System.out.println("Status: "+cod_estatus);
                System.out.println("Cod_pedido: "+cod_pedido);
            try {
                //Metodo de Update
                pedido.UpdateCantDespachada(rif, cod_pedido, var_cod_articulo, var_cant_despachada, cod_estatus);
                //lista.add(var_campo);
            } catch (SQLException ex) {
                System.out.println("Error despacho: "+ex);
                ex.printStackTrace();
                //Logger.getLogger(ActualizarDespachoEventHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            
            System.out.println("--------------Guardando -------------------");
            request.setAttribute("mensaje1", "Despacho actualizado");            
            request.setAttribute("dir", "cdv_config_exito.jsp");
        
            
        
    }
    
    @Override
    protected void _dispatch(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        //Debug.log(this, "_dispatch", "redirecting to " + getURL());
        RequestDispatcher rd  = request.getRequestDispatcher((String) request.getAttribute("dir"));
        if (rd == null) {
            // Debug.log (this, "_dispatch", "rd = null!");
        }
        rd.forward(request, response);
     }

}
