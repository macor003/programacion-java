/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
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
import loader.mareComprar;
import loader.mareRegistrarLoader;
import utility.EventHandlerBase;

/**
 *
 * @author Mario
 */
public class GuardarEnCarritoEventHandler extends EventHandlerBase {

    private ResourceBundle bundle = ResourceBundle.getBundle("sgtURL");
    
    @Override
    protected String getURL(){
        
        return bundle.getString("AGREGAR_AL_CARRITO");
    }
    
    @Override
    public void process(ServletContext sc, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, NamingException {
        
        HttpSession session = request.getSession(true);
           
                    
            mareComprar registro = new mareComprar(); 
            
            String correo = (String) session.getAttribute("correo");
            System.out.println("correo:"+correo);
            String modelo = (String) request.getParameter("modelo");
            String talla_busto = (String) request.getParameter("talla_b-"+modelo);
            String talla_cintura =(String) request.getParameter("talla_c-"+modelo);
            String canti = (String) request.getParameter("cantidad-"+modelo);
            
            System.out.println("modelo: "+modelo);
            System.out.println("talla_b: "+talla_busto);
            
            String costo = registro.verCosto(modelo);
            System.out.println("precio:"+costo);
            
            String tallas = talla_busto+"-"+talla_cintura;
            System.out.println("tallas:"+tallas);
            int cantidad =Integer.parseInt(canti);
            System.out.println("cantidad: "+cantidad);
            int precio = Integer.parseInt(costo);
            System.out.println("precio: "+precio);
            
            registro.insertarAlCarrito(correo, modelo, cantidad, tallas, precio);
                         
        
    }

}
