/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loader;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import utility.mareUtility;

/**
 *
 * @author Mario
 */
public class ConsultarRegistrosLoader {
    
    public String verDatosUsuarios(){
        mareUtility util = new mareUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        
        String retorno = "";
                
        
            try{
                String sql ="SELECT \n" +
                            "  nombre_usuario, \n" +
                            "  apellido_usuario, \n" +
                            "  correo_usuario\n" +
                            "FROM \n" +
                            "  public.mare_usuario usuarios \n" +
                            "ORDER BY \n" +
                            "  nombre_usuario;";            
                
                con = util.Conexion_mare();
                stm= con.createStatement();    
                rs = stm.executeQuery(sql);
                System.out.println("SQL PENDIENTES: " + sql);
                while(rs.next()){                    
                    
                    String nombre = rs.getString("nombre_usuario");
                    String apellido = rs.getString("apellido_usuario");                    
                    String correo = rs.getString("correo_usuario");
                    
                    
                retorno +="<tr class='registro'>";
                    retorno +="<td class='detalleArea' >"+nombre+"</td>";
                    retorno +="<td class='detalleArea' >"+apellido+"</td>";
                    retorno +="<td class='detalleArea' >"+correo+"</td>";
                    
                    
                retorno +="</tr>";
                }
                
            
            }catch(SQLException e){
            }finally{
                try{
                    if (rs != null) rs.close();
                    if (stm != null) stm.close();
                    if (con != null) con.close();
                }catch(SQLException e){
                    
                }
            }
        return retorno;    
    }
    
    public String verProductos(){
        mareUtility util = new mareUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        
        String retorno = "";
                
        
            try{
                String sql ="SELECT \n" +
                            "  cod_producto, \n" +
                            "  nombre_tienda, \n" +
                            "  nombre_producto, \n" +
                            "  desc_tipo_producto, talla_producto, \n" +
                            "  inventario_busto, inventario_caderas\n" +
                            "FROM \n" +
                            "  public.mare_producto, \n" +
                            "  public.mare_tipo_producto, \n" +
                            "  public.mare_tienda\n" +
                            "WHERE \n" +
                            "  mare_producto.cod_tienda = mare_tienda.cod_tienda AND\n" +
                            "  mare_producto.cod_tipo_producto = mare_tipo_producto.cod_tipo_producto;";            
                
                con = util.Conexion_mare();
                stm= con.createStatement();    
                rs = stm.executeQuery(sql);
                System.out.println("SQL PENDIENTES: " + sql);
                while(rs.next()){                    
                    
                    String cod_prod = rs.getString("cod_producto");
                    String tienda = rs.getString("nombre_tienda");                    
                    String producto = rs.getString("nombre_producto");
                    String desc_tipo_prod = rs.getString("desc_tipo_producto");
                    String talla = rs.getString("talla_producto");
                    String inventario_busto = rs.getString("inventario_busto");
                    String inventario_caderas = rs.getString("inventario_caderas");
                    
                retorno +="<tr>";
                    retorno +="<td>"+cod_prod+"</td>";
                    retorno +="<td>"+tienda+"</td>";
                    retorno +="<td>"+producto+"</td>";
                    retorno +="<td>"+desc_tipo_prod+"</td>";
                    retorno +="<td>"+talla+"</td>";
                    retorno +="<td>"+inventario_busto+"</td>";
                    retorno +="<td>"+inventario_caderas+"</td>";
                retorno +="</tr>";
                }
                
            
            }catch(SQLException e){
            }finally{
                try{
                    if (rs != null) rs.close();
                    if (stm != null) stm.close();
                    if (con != null) con.close();
                }catch(SQLException e){
                    
                }
            }
        return retorno;    
    }   
    
    public String verTiendas(){
        mareUtility util = new mareUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        
        String retorno = "";
        retorno+="<select class='form-control' name='lm_tienda' id='lm_tienda'>";
        retorno+="<option value='su'>Selecciona la tienda</option>"; 
            try{
                String sql ="SELECT \n" +
                            "  nombre_tienda, \n" +
                            "  cod_tienda\n" +
                            "FROM \n" +
                            "  mare_tienda;";            
                
                con = util.Conexion_mare();
                stm= con.createStatement();    
                rs = stm.executeQuery(sql);
                System.out.println("SQL PENDIENTES: " + sql);
                while(rs.next()){                    
                    
                    String cod_tienda = rs.getString("cod_tienda");
                    String nombre = rs.getString("nombre_tienda");                    
                                        
                    retorno+="<option value='"+cod_tienda+"'>"+nombre+"</option>";
                 
                }
                retorno+="</select>";
            
            }catch(SQLException e){
            }finally{
                try{
                    if (rs != null) rs.close();
                    if (stm != null) stm.close();
                    if (con != null) con.close();
                }catch(SQLException e){
                    
                }
            }
        return retorno;    
    }
    
    public String verTipoProducto(){
        mareUtility util = new mareUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        
        String retorno = "";
        retorno+="<select class='form-control' name='lm_tipo_producto' id='lm_tipo_producto'>";
        retorno+="<option value='su'>Selecciona Tipo de Producto</option>"; 
            try{
                String sql ="SELECT \n" +
                            "  desc_tipo_producto, \n" +
                            "  cod_tipo_producto\n" +
                            "FROM \n" +
                            "  public.mare_tipo_producto;";            
                
                con = util.Conexion_mare();
                stm= con.createStatement();    
                rs = stm.executeQuery(sql);
                System.out.println("SQL PENDIENTES: " + sql);
                while(rs.next()){                    
                    
                    String cod_tipo = rs.getString("cod_tipo_producto");
                    String desc = rs.getString("desc_tipo_producto");                    
                                        
                    retorno+="<option value='"+cod_tipo+"'>"+desc+"</option>";
                 
                }
                retorno+="</select>";
            
            }catch(SQLException e){
            }finally{
                try{
                    if (rs != null) rs.close();
                    if (stm != null) stm.close();
                    if (con != null) con.close();
                }catch(SQLException e){
                    
                }
            }
        return retorno;    
    }
    
    public String verSexo(){
        mareUtility util = new mareUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        
        String retorno = "";
        retorno+="<select class='form-control'>";
        retorno+="<option value='su'>Selecciona sexo</option>"; 
            try{
                String sql ="SELECT \n" +
                            "  cod_sexo, \n" +
                            "  desc_sexo\n" +
                            "FROM \n" +
                            "  mare_sexo;";            
                
                con = util.Conexion_mare();
                stm= con.createStatement();    
                rs = stm.executeQuery(sql);
                System.out.println("SQL PENDIENTES: " + sql);
                while(rs.next()){                    
                    
                    String cod_sexo = rs.getString("cod_sexo");
                    String sexo = rs.getString("desc_sexo");                    
                                        
                    retorno+="<option value='"+cod_sexo+"'>"+sexo+"</option>";
                 
                }
                retorno+="</select><br>";
            
            }catch(SQLException e){
            }finally{
                try{
                    if (rs != null) rs.close();
                    if (stm != null) stm.close();
                    if (con != null) con.close();
                }catch(SQLException e){
                    
                }
            }
        return retorno;    
    }
    
    public String verCarrito(String correo){
        mareUtility util = new mareUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        
        String retorno = "";
            try{
                String sql ="SELECT \n" +
                            "  car_modelo_producto, \n" +
                            "  car_tallas, \n" +
                            "  car_cantidad, \n" +
                            "  car_precio\n" +
                            "FROM \n" +
                            "  public.mare_carrito_compra\n" +
                            "WHERE \n" +
                            "  car_status=1 AND car_correo_usu ='"+correo+"';";            
                
                con = util.Conexion_mare();
                stm= con.createStatement();    
                rs = stm.executeQuery(sql);
                System.out.println("SQL PENDIENTES: " + sql);
                while(rs.next()){                    
                    
                    String modelo= rs.getString("car_modelo_producto");
                    String tallas= rs.getString("car_tallas");
                    String cantidad= rs.getString("car_cantidad");
                    String precio = rs.getString("car_precio");
                    
                    retorno +="<tr>";
                    retorno +="<td>"+modelo+"</td>";
                    retorno +="<td>"+tallas+"</td>";
                    retorno +="<td>"+cantidad+"</td>";
                    retorno +="<td style=\"text-align: right;\">"+precio+" Bs.</td>";
                    int costo = Integer.parseInt(precio);
                    int cant = Integer.parseInt(cantidad);
                    int resultado = costo * cant;
                    retorno +="<td style=\"text-align: right;\">"+resultado+" Bs.</td>";
                retorno +="</tr>";
                 
                }
            
            }catch(SQLException e){
            }finally{
                try{
                    if (rs != null) rs.close();
                    if (stm != null) stm.close();
                    if (con != null) con.close();
                }catch(SQLException e){
                    
                }
            }
        return retorno;    
    }
    
    public void verContrasena(String correo){
        mareUtility util = new mareUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
                String to = correo;
        
            try{
                String sql ="SELECT \n" +
                            "  pass_usuario, \n" +
                            "  correo_usuario\n" +
                            " FROM \n" +
                            "  public.mare_usuario \n" +
                            " WHERE \n" +
                            "  correo_usuario = '"+correo.toUpperCase()+"';";            
                
                con = util.Conexion_mare();
                stm= con.createStatement();    
                rs = stm.executeQuery(sql);
                System.out.println("SQL PENDIENTES: " + sql);
                while(rs.next()){                    
                    
                    String contrasena = rs.getString("pass_usuario");
                    
                    String mensaje ="<!DOCTYPE html>\n" +
                            "<html>\n" +
                            "    <head>\n" +
                            "        <title>correo</title>\n" +
                            "        <meta charset=\"UTF-8\">\n" +
                            "        \n" +
                            "    </head>\n" +
                            "    <body>\n" +
                            "        <div style=\"background-color: #ECECEC; width: 500px; height: auto;\">\n" +
                            "            <h2 style=\" padding: 10px; background-color: #0075b0; text-align: center; font-weight: 300; color: white; font: 22px sans-serif;\">Datos para inicio de sesión</h2>\n" +
                            
                            "            <table style=\"width: 100%;\">\n" +
                            "                <caption style=\"background-color: #ECECEC; color: #0075b0; padding: 5px; font: bold 18px sans-serif;\">Usuario y contraseña:</caption>\n" +
                            "                <tbody>\n" +

                            "                    <tr>\n" +
                            "                        <td  style=\" padding: 5px; width:225px; text-align: right; font:14px sans-serif;\"><strong>Correo:</strong></td>\n" +
                            "                        <td  style=\" padding: 5px; width:225px; text-align: left; font:14px sans-serif;\">"+correo+"</td>\n" +
                            "                    </tr>\n" +
                            "                    <tr>\n" +
                            "                        <td  style=\" padding: 5px; width:225px; text-align: right; font:14px sans-serif;\"><strong>Contraseña:</strong></td>\n" +
                            "                        <td  style=\" padding: 5px; width:225px; text-align: left; font:14px sans-serif;\">"+contrasena+"</td>\n" +
                            "                    </tr>\n" +
                            "                </tbody>\n" +
                            "            </table>\n" +
                            "            <h4 style=\"text-align: center; background-color: #ECECEC; color: #0075b0; font-weight: 300; padding: 10px; font-family: sans-serif;\">&copy; Mare Mare Trajes de Baño C.A.</h4>            \n" +
                            "        </div>\n" +
                            "    </body>\n" +
                            "</html>\n" +
                            "";
                    
                Send_correo_verificacion(mensaje, to);
                }
            
            
            }catch(SQLException e){
                System.out.println("ERROR: "+e);
            }finally{
                try{
                    if (rs != null) rs.close();
                    if (stm != null) stm.close();
                    if (con != null) con.close();
                }catch(SQLException e){
                    
                }
            }
    }
    
    
    public static void Send_correo_verificacion(String mensaje, String to){
        
        System.out.println("Estoy enviando el correo");
        //String to ="m_cesar111@hotmail.com";
        //String to ="mcor63@gmail.com";
        //String to ="jaleromo@gmail.com";
        String from ="mcor63@gmail.com";
        //String mensaje ="Esta es una prueba para el envio de correos con mare mare";
        try
        {
            // Propiedades de la conexión
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", "mcor63@gmail.com");
            props.setProperty("mail.smtp.auth", "true");

            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);

            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(
                Message.RecipientType.TO,
                new InternetAddress(to));
            message.setSubject("Verificación de contraseña");
            message.setContent(mensaje,"text/html");

            // Lo enviamos.
            Transport t = session.getTransport("smtp");
            t.connect("mcor63@gmail.com", "cczenxhserkjikbw");
            t.sendMessage(message, message.getAllRecipients());

            // Cierre.
            t.close();
            
        } catch (MessagingException e) {
            System.out.println("ERROR AL ENVIAR CORREO: "+e);
            e.printStackTrace();
        }
    }
    
}
