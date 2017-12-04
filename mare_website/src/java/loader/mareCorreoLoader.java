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
public class mareCorreoLoader {
    
    public void enviarCorreoPromocion(){
        mareUtility util = new mareUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        
        
            try{
                String sql =" SELECT \n" +
                            "  correo_usuario\n" +
                            "FROM \n" +
                            "  public.mare_usuario;";            
                
                con = util.Conexion_mare();
                stm= con.createStatement();    
                rs = stm.executeQuery(sql);
                System.out.println("SQL PENDIENTES: " + sql);
                while(rs.next()){                    
                    
                    String correo_usuario = rs.getString("correo_usuario");
                     
                 
                }
            String mensaje ="<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "    <head>\n" +
                        "        <title></title>\n" +
                        "        <meta charset=\"UTF-8\">\n" +
                        "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                        "    </head>\n" +
                        "    <body>\n" +
                        "        <img src=\"https://dl.dropboxusercontent.com/u/9771556/promocion1.jpg\" alt=\"\" style=\"width: 600px; height: 600px;\">\n" +
                        "    </body>\n" +
                        "</html>\n" +
                        "";
                        Send_correo_nuevo_promocion(mensaje);
            }catch(SQLException e){
            }finally{
                try{
                    if (rs != null) rs.close();
                    if (stm != null) stm.close();
                    if (con != null) con.close();
                }catch(SQLException e){
                    
                }
            }
       
       
    }
    
    public void enviarCorreo(){
        String mensaje ="<!DOCTYPE html>\n" +
"<html>\n" +
"    <head>\n" +
"        <title></title>\n" +
"        <meta charset=\"UTF-8\">\n" +
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"    </head>\n" +
"    <body >\n" +
"        <div style=\"background: rgba(212,228,239,1);width: 500px; height: 300px;border-radius: 15px;\">\n" +
"            <img src=\"https://dl.dropboxusercontent.com/u/9771556/mare_mare.png\" alt='' style=\" width: 150px; height: 100px; margin-top: 20px; margin-left: 270px;\">\n" +
"            \n" +
"            <p style=\"font-family: sans-serif;text-align: center; margin-top: -150px; margin-left: 200px;\">\n" +
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
        Send_correo_nuevo(mensaje);
    }
    
    public static void Send_correo_nuevo_promocion(String mensaje){
        
        
        //String to ="m_cesar111@hotmail.com";
        //String to ="mcor63@gmail.com";
        String para ="m_cesar111@hotmail.com";
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
                new InternetAddress(para));
            message.setSubject("PROMOCIÓN SEMANA SANTA 2015");
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
   
    public static void Send_correo_nuevo(String mensaje){
        
        
        //String to ="m_cesar111@hotmail.com";
        //String to ="mcor63@gmail.com";
        String to ="m_cesar111@hotmail.com";
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
            message.setSubject("FELIZ CUMPLEAÑOS");
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
