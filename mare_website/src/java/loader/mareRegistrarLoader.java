/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import utility.mareUtility;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Mario
 */
public class mareRegistrarLoader {
    
    public int getAutoID(){
        Connection con;
        Statement stmt=null;
        ResultSet rs = null;
        //("voy a hacer la conexion a la SGT_BD");
        con = new mareUtility().Conexion_mare();
        String ls_sql="";
        String max="";
        int cod=0;
        int id = 0;

        try {
            stmt = con.createStatement();
            ls_sql="SELECT  max(cod_producto) FROM  mare_producto;";
            
            rs=stmt.executeQuery(ls_sql);
            
            if(rs.next()){
                //("Estoy en rs.next()");
               max=rs.getString(1);
                //("max=="+max);
               System.out.println("SQL DE CODIGO REQUERIMIENTO: " + ls_sql);
               System.out.println("VALOR DEL MAX: " + max);
            }
//            int cantMax=0;
          
            if(max!=null){
            cod= Integer.parseInt(max)+1;
            }
            System.out.println("CODIGO DEL MAX: " + cod);
            if(cod==0){
                id =1;
                System.out.println("Codigo de requerimiento 1: " + id);
            }else if( cod >= 1){                 
                 id = cod;
                 System.out.println("Codigo de requerimiento >1: " + id);
             }
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }finally {
                   try {
                       if (rs != null) rs.close();
                       if (stmt != null) stmt.close();
                       if (con != null) con.close();
                   } catch (SQLException e) {
                       //("Loader:: SQLException " + e.toString());
                   }
               }
        
        return id;
    }
    
    public void insertarUsuario(String nombre_usu, String apellido_usu, String correo_usu, String pass, String sexo, String fecha_nac) throws ParseException{
               
        mareUtility util = new mareUtility();
        java.util.Date today = new java.util.Date();
        
        Connection con =null;        
        int rol = 3;
        int cargo =1;
        
        int sexo1 = Integer.parseInt(sexo);
        System.out.println("fecha: "+ fecha_nac);
        String[] partes= fecha_nac.split("-");
        Calendar miCal=Calendar.getInstance();
        miCal.set(Calendar.DATE, Integer.parseInt(partes[2]));//Seteando el Dia
        miCal.set(Calendar.MONTH, Integer.parseInt(partes[1])-1);//Seteando el Mes -1
        miCal.set(Calendar.YEAR, Integer.parseInt(partes[0]));//Seteando el Anno
        
        java.sql.Date fecha_nacimiento=new java.sql.Date(miCal.getTimeInMillis());
        
        
        PreparedStatement pstmt=null;
        con=util.Conexion_mare();
        System.out.println("--------------------------------------");
        System.out.println("Estoy guardando un registro de cliente");
        System.out.println("nombre: "+ nombre_usu);
        System.out.println("apellido: "+ apellido_usu);
        System.out.println("correo: "+ correo_usu);
        System.out.println("contraseña: "+ pass);
        System.out.println("sexo: "+ sexo);

        String sql="INSERT INTO mare_usuario(\n" +
                    "            correo_usuario, cod_rol, nombre_usuario, apellido_usuario, \n" +
                    "            sexo_usuario, pass_usuario, fecha_registro_usuario, fecha_nac_usu)\n" +
                    "    VALUES (?, ?, ?, ?, \n" +
                    "            ?, ?, ?, ?);";
        System.out.println("SQL: "+ sql);
        try {
            
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1, correo_usu.toUpperCase());
            pstmt.setInt(2, rol);
            pstmt.setString(3, nombre_usu);
            pstmt.setString(4, apellido_usu);
            pstmt.setInt(5, sexo1);
            pstmt.setString(6, pass);           
            pstmt.setTimestamp(7, new java.sql.Timestamp(today.getTime()));
            pstmt.setDate(8, fecha_nacimiento);
            pstmt.executeUpdate();
            
            CorreoRegistro(correo_usu);
        
        } catch (SQLException e) {
           e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
           System.out.println("Error: "+e);
        }finally {
                try {
                       //if (rs != null) rs.close();
                       if (pstmt != null) pstmt.close();
                       if (con != null) con.close();
                   } catch (SQLException e) {
                       System.out.println("Loader:: SQLException " + e.toString());
                   }
               }
               
   }
    
    public void insertarProducto(int cod, String nombre, int tienda, String tipo_producto, int inventario, int costo, String modelo) throws ParseException{
               
        mareUtility util = new mareUtility();
                
        Connection con =null;        
        
        //int fecha_nac = Integer.parseInt(fecha_anno +"-"+fecha_mes+"-"+fecha_dia);
        
        
        PreparedStatement pstmt=null;
        con=util.Conexion_mare();
        System.out.println("--------------------------------------");
        System.out.println("Estoy guardando un registro de producto");
        System.out.println("codigo: "+ cod);
        System.out.println("nombre: "+ nombre);
        System.out.println("tienda: "+ tienda);
        System.out.println("tipo: "+ tipo_producto);
        System.out.println("inventario: "+ inventario);
        System.out.println("cost: "+ costo);

        String sql="INSERT INTO mare_producto(\n" +
                    "            cod_producto, cod_tienda, nombre_producto, inventario_producto, \n" +
                    "            cod_tipo_producto, costo_producto, modelo_producto)\n" +
                    "    VALUES (?, ?, ?, ?, \n" +
                    "            ?, ?, ?);";
        System.out.println("SQL: "+ sql);
        try {
            
            pstmt=con.prepareStatement(sql);
            pstmt.setInt(1, cod);
            pstmt.setInt(2, tienda);
            pstmt.setString(3, nombre);
            pstmt.setInt(4, inventario);
            pstmt.setString(5, tipo_producto);
            pstmt.setInt(6, costo);
            pstmt.setString(7, modelo);
            pstmt.executeUpdate();
                        
        
        } catch (SQLException e) {
           e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
           System.out.println("Error: "+e);
        }finally {
                try {
                       //if (rs != null) rs.close();
                       if (pstmt != null) pstmt.close();
                       if (con != null) con.close();
                   } catch (SQLException e) {
                       System.out.println("Loader:: SQLException " + e.toString());
                   }
               }
               
   }
        
    public void CorreoRegistro(String correo) {
        
        mareUtility util = new mareUtility();        
        Connection con= util.Conexion_mare();
        Statement stmt=null;
        ResultSet rs=null;
        String nombre="";
        String apellido ="";
        String correo2 ="";
        String contrasena="";
        
        try{
            System.out.println("Estoy consultando para enviar el correo");
            System.out.println("CORREO A CONSULTAR: "+correo);
            //SQL PARA SABER LOS DATOS DEL USUARIO
            String sql="SELECT \n" +
                            "  nombre_usuario, \n" +
                            "  apellido_usuario, \n" +
                            "  correo_usuario, \n" +
                            "  pass_usuario\n" +
                            "FROM \n" +
                            "  public.mare_usuario\n" +
                            "WHERE\n" +
                            "  correo_usuario='"+correo.toUpperCase()+"';";
                   
        stmt=con.createStatement();
        rs=stmt.executeQuery(sql);
        while(rs.next()){
            
            nombre=rs.getString("nombre_usuario");
            apellido = rs.getString("apellido_usuario");
            correo2 = rs.getString("correo_usuario");
            contrasena = rs.getString("pass_usuario");
            String mensaje ="<!DOCTYPE html>\n" +
                            "<html>\n" +
                            "    <head>\n" +
                            "        <title>correo</title>\n" +
                            "        <meta charset=\"UTF-8\">\n" +
                            "        \n" +
                            "    </head>\n" +
                            "    <body>\n" +
                            "        <div style=\"background-color: #ECECEC; width: 500px; height: auto;\">\n" +
                            "            <h2 style=\" padding: 10px; background-color: #0075b0; text-align: center; font-weight: 300; color: white; font: 22px sans-serif;\">Bienvenido a Mare Mare Trajes de Baño C.A.</h2>\n" +
                            "            <h4 style=\"text-align: center; background-color: #ECECEC; color: #0075b0; font-weight: 300; padding: 10px; font-family: sans-serif;\">Desde este momento puedes disfrutar de los beneficios que tenemos preparados para ti, podras iniciar sesion con los siguientes datos:</h4>\n" +
                            "            <table style=\"width: 100%;\">\n" +
                            "                <caption style=\"background-color: #ECECEC; color: #0075b0; padding: 5px; font: bold 18px sans-serif;\">Datos del usuario:</caption>\n" +
                            "                <tbody>\n" +
                            "                    <tr>\n" +
                            "                        <td style=\" padding: 5px; width:225px; text-align: right; font:14px sans-serif;\"><strong>Nombre:</strong></td>\n" +
                            "                        <td  style=\" padding: 5px; width:225px; text-align: left; font:14px sans-serif;\">"+nombre+"</td>\n" +
                            "                    </tr>\n" +
                            "                    <tr>\n" +
                            "                        <td  style=\" padding: 5px; width:225px; text-align: right; font:14px sans-serif;\"><strong>Apellido:</strong></td>\n" +
                            "                        <td  style=\" padding: 5px; width:225px; text-align: left; font:14px sans-serif;\">"+apellido+"</td>\n" +
                            "                    </tr>\n" +
                            "                    <tr>\n" +
                            "                        <td  style=\" padding: 5px; width:225px; text-align: right; font:14px sans-serif;\"><strong>Correo:</strong></td>\n" +
                            "                        <td  style=\" padding: 5px; width:225px; text-align: left; font:14px sans-serif;\">"+correo2+"</td>\n" +
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
            System.out.println("nombre: " +nombre);
            System.out.println("apellido: " + apellido);
            System.out.println("correo: " +correo2);
            System.out.println("contraseña: " +contrasena);
            
            Send_correo_nuevo(mensaje, correo2, nombre, apellido);
        
            }
         }catch(SQLException e){
           e.printStackTrace();
         }finally {
                try {                    
                    if (stmt != null) stmt.close();
                    if (con != null) con.close();
                   } catch (SQLException e) {
                       
                   }
               }       
    }
    
    public String verDatosUsuario(String cor){
        mareUtility util = new mareUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        
        String retorno = "";
        System.out.println("--------------");
        System.out.println("VOY A CONSULTAR LOS DATOS DEL USUARIO");
        System.out.println("CORREO: "+cor);
            try{
                String sql ="SELECT \n" +
                            "  nombre_usuario, \n" +
                            "  apellido_usuario, \n" +
                            "  correo_usuario, \n" +
                            "  cedula_usuario, \n" +
                            "  fecha_nac_usu, \n" +
                            "  direccion_usuario\n" +
                            "FROM \n" +
                            "  public.mare_usuario\n" +
                            "WHERE\n" +
                            "  correo_usuario='"+cor.toUpperCase()+"';";
                
                con = util.Conexion_mare();
                stm= con.createStatement();    
                rs = stm.executeQuery(sql);
                System.out.println("SQL DATOS USUARIOS: " + sql);
                while(rs.next()){                    
                    
                    String nombre = rs.getString("nombre_usuario");
                    String apellido = rs.getString("apellido_usuario");                    
                    String correo = rs.getString("correo_usuario");
                    String cedula = rs.getString("cedula_usuario");
                    String fecha_nac = rs.getString("fecha_nac_usu");
                    String direccion = rs.getString("direccion_usuario");
                    
                    System.out.println("nombre: " + nombre);
                    System.out.println("apellido: " + apellido);
                    System.out.println("correo: " + correo);
                    System.out.println("cedula: " +cedula);
                    System.out.println("fecha: "+fecha_nac);
                    System.out.println("direccion: " +direccion);
                        
                    
                    retorno+="Nombre:<input type='text' name='lm_nombre' class='form-control' value='"+nombre+"'><br>";
                    retorno+="Apellido:<input type='text' name='lm_apellido' class='form-control' value='"+apellido+"'><br>";
                    retorno+="Correo:<input type='email' name='lm_correo' class='form-control' value='"+correo+"' ><br>";
                    retorno+="Cedula:<input type='text' name='lm_cedula' class='form-control' value='";
                    if(cedula == null){
                        retorno+="";
                    }else{
                        retorno+=cedula;
                    }
                    retorno+="'><br>";
                    retorno+="Fecha nacimiento:<input type='date' name='lm_fecha_nac' class='form-control' value='"+fecha_nac+"'><br>";
                    retorno+="Dirección:<textarea name='lm_direccion' class='form-control'>";
                    if(direccion == null){
                        retorno+="";
                    }else{
                        retorno+=direccion;
                    }
                    retorno+="</textarea><br>";
                }
            
            }catch(SQLException e){
                System.out.println("ERROR: " +e);
                e.printStackTrace();
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
    
    public void ActualizarDatosUsuario(String nombre_usu, String apellido_usu, String correo_usu, int cedula, String direccion){
    
    mareUtility util = new mareUtility();

    java.util.Date today=new java.util.Date();        
    Connection con =null;
   
    String status = "6";
    String log ="04";
    
    PreparedStatement pstmt=null;
    con=util.Conexion_mare();


    String sql3="UPDATE mare_usuario\n" +
                "   SET correo_usuario=?, nombre_usuario=?, \n" +
                "       apellido_usuario=?, cedula_usuario=?,\n" +
                "       direccion_usuario=?\n" +
                " WHERE correo_usuario='"+correo_usu+"';";
    try {            
        

        pstmt = con.prepareStatement(sql3);
        pstmt.setString(1, correo_usu);
        pstmt.setString(2, nombre_usu);
        pstmt.setString(3, apellido_usu);
        pstmt.setInt(4, cedula);
        pstmt.setString(5, direccion);
        pstmt.executeUpdate();           


    } catch (SQLException e) {
       e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    }finally {
            try {
                   //if (rs != null) rs.close();
                   if (pstmt != null) pstmt.close();
                   if (con != null) con.close();
               } catch (SQLException e) {
                   System.out.println("Loader:: SQLException " + e.toString());
               }
           }

   }
    
    public String validarCorreo(String correo){
        
        mareUtility util = new mareUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String retorno = "";
        
        System.out.println("Este es el correo a verificar: " + correo.toUpperCase());
            try{
                String sql ="SELECT \n" +
                            "  correo_usuario\n" +
                            "FROM \n" +
                            "  public.mare_usuario\n" +
                            "WHERE\n" +
                            "  correo_usuario='"+correo.toUpperCase()+"';";
                con = util.Conexion_mare();
                stm= con.createStatement();
                rs = stm.executeQuery(sql);
                while(rs.next()){
                    String correoBD = rs.getString("correo_usuario");
                    retorno+= correoBD;
                }
            }catch(SQLException e){
                e.printStackTrace();
            }finally{
                try{
                    if (rs != null) rs.close();
                    if (stm != null) stm.close();
                    if (con != null) con.close();
                }catch(SQLException e){
                    
                }
            }
        System.out.println("---");
        System.out.println("CONSULTA RETORNADA: "+retorno );
        return retorno;
    }
    
    public void CorreoFelizCumpleaño(String correo) {
        
        mareUtility util = new mareUtility();        
        Connection con= util.Conexion_mare();
        Statement stmt=null;
        ResultSet rs=null;
        String nombre="";
        String apellido ="";
        String correo2 ="";
        String contrasena="";
        
        try{
            System.out.println("Estoy consultando para enviar el correo");
            System.out.println("CORREO A CONSULTAR: "+correo);
            //SQL PARA SABER LOS DATOS DEL USUARIO
            String sql="SELECT \n" +
                            "  nombre_usuario, \n" +
                            "  apellido_usuario, \n" +
                            "  correo_usuario, \n" +
                            "  pass_usuario\n" +
                            "FROM \n" +
                            "  public.mare_usuario\n" +
                            "WHERE\n" +
                            "  correo_usuario='"+correo.toUpperCase()+"';";                   
        stmt=con.createStatement();
        rs=stmt.executeQuery(sql);
        while(rs.next()){
            
            nombre=rs.getString("nombre_usuario");
            apellido = rs.getString("apellido_usuario");
            correo2 = rs.getString("correo_usuario");
            contrasena = rs.getString("pass_usuario");
            String mensaje ="<!DOCTYPE html>\n" +
                            "<html>\n" +
                            "    <head>\n" +
                            "        <title></title>\n" +
                            "        <meta charset=\"UTF-8\">\n" +
                            "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                            "    </head>\n" +
                            "    <body >\n" +
                            "        <div>            \n" +
                            "            <img src=\"https://dl.dropboxusercontent.com/u/9771556/tarjeta_cumplea%C3%B1os.jpg\" alt=\"\" style=\"width: 900px; height: 400px; \"> \n" +
                            "            <p style=\"font-family: sans-serif; position: absolute; top: 170px; left: 525px; text-align: center;\">\n" +
                            "                <strong>Estimado Sr.(a):</strong><br>\n" +
                            "                ORTEGA MARIO<br><br>\n" +
                            "                En este dia tan especial,<br>\n" +
                            "                <strong>Mare Mare</strong> quiere desearle un<br><br>\n" +
                            "                <span style=\"font: bold 20px sans-serif;\">¡FELIZ CUMPLEAÑOS!</span>\n" +
                            "                \n" +
                            "            </p>\n" +
                            "        </div>\n" +
                            "    </body>\n" +
                            "</html>";
            System.out.println("nombre: " +nombre);
            System.out.println("apellido: " + apellido);
            System.out.println("correo: " +correo2);
            System.out.println("contraseña: " +contrasena);
            
            Send_correo_nuevo(mensaje, correo2, nombre, apellido);
        
            }
         }catch(SQLException e){
           e.printStackTrace();
         }finally {
                try {                    
                    if (stmt != null) stmt.close();
                    if (con != null) con.close();
                   } catch (SQLException e) {
                       
                   }
               }       
    }
    
    public static void Send_correo_nuevo(String mensaje, String to, String nombre, String apellido){
        
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
            message.setSubject("BIENVENIDO " + nombre.toUpperCase()+" "+apellido.toUpperCase());
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
