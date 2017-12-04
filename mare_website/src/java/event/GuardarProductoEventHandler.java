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
public class GuardarProductoEventHandler  extends EventHandlerBase {

    private ResourceBundle bundle = ResourceBundle.getBundle("sgtURL");
    
    @Override
    protected String getURL(){
        
        return bundle.getString("GUARDAR_PRODUCTO");
    }
    
    @Override
    public void process(ServletContext sc, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, NamingException {
        
        HttpSession session = request.getSession(false);
         String rol = "1";      
                    
            mareRegistrarLoader registro = new mareRegistrarLoader();    
            try{
               int cod = registro.getAutoID();
                String nombre = (String) request.getParameter("lm_nombre");
                String tiend = (String) request.getParameter("lm_tienda");
                String tipo_producto =(String) request.getParameter("lm_tipo_producto");
                String inventar = (String) request.getParameter("lm_inventario");
                String cost = (String) request.getParameter("lm_costo");
                String modelo = (String) request.getParameter("lm_modelo");
            
            
                System.out.println("nombre: "+nombre);
                System.out.println("tienda: "+tiend);
                System.out.println("tipo: "+tipo_producto);
                System.out.println("inventario: "+inventar);
                System.out.println("costo: "+cost);
                System.out.println("modelo: "+modelo);
                int tienda = Integer.parseInt(tiend);
                int inventario = Integer.parseInt(inventar);
                int costo = Integer.parseInt(cost); 
                registro.insertarProducto(cod, nombre, tienda, tipo_producto, inventario, costo, modelo);
            }catch(NumberFormatException e){
                
            }catch (ParseException e) {
        }
      
                         
        
    }
}