/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venvidrio.cdv.event;

import com.venvidrio.cdv.loader.CarritoLoader;
import com.venvidrio.cdv.loader.UsuariosLoader;
import com.venvidrio.cdv.utility.EventHandlerBase;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
public class EliminarProductoCarritoEventHandler extends EventHandlerBase  {
    
    private ResourceBundle bundle = ResourceBundle.getBundle("cdvURL");

    @Override
      protected String getURL() {
            return bundle.getString("ELIMINAR_PRODUCTO");
      }
    @Override
    public void process(ServletContext sc, HttpServletRequest request, HttpServletResponse response)
                 throws IOException, ServletException, NamingException {
        
        HttpSession session = request.getSession(false);
        CarritoLoader carrito = new CarritoLoader();

        String pedido = (String) request.getParameter("lm_cod_pedido");
        String producto = (String) request.getParameter("lm_cod_producto");
        System.out.println("Pedido: "+pedido);
        System.out.println("producto: "+producto);
       
        
        try {
            carrito.DeleteArticulo(pedido, producto);
            request.setAttribute("mensaje1", "Se elimino el producto exitosamente");            
            request.setAttribute("dir", "cdv_eliminado_exito.jsp");
        } catch (SQLException ex) {
            System.out.println("Error delete: "+ex);
            ex.printStackTrace();            
        }
            
        
        
        
        
            
        
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
