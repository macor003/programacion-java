/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venvidrio.cdv.main;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Ortegam
 */
public class Pruebas2 {
    
     public static void main(String[] args) throws FileNotFoundException, SQLException, IOException {
        //new Pruebas().getImg();
        new Pruebas2().archivo_txt();
    }
     public void archivo_txt () throws IOException{
         Calendar fecha = Calendar.getInstance();
         int dia = fecha.get(Calendar.DAY_OF_MONTH);
         int mes = fecha.get(Calendar.MONTH);
         int año = fecha.get(Calendar.YEAR);
         
         String day = Integer.toString(dia);
         String month = Integer.toString(mes+1);
         String year = Integer.toString(año);
         
         String date = ""+day+"-"+month+"-"+year+"";
         
         
         String ruta = "\\\\Dvelgv0248\\owens_data\\MENSAJES_POR_ENVIAR\\"+date+".txt";
        File archivo = new File(ruta);
        BufferedWriter bw;
        if(archivo.exists()) {
            
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write("El fichero de texto ya estaba creado.");
            System.out.println("El archivo existe");
        } else {
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write("yhonny\r\nBuenas tardes esto es una prueba de envio de mensajes");
            System.out.println("Archivo Creado");
        }
        bw.close();
     }
     
     
     public void leer_correo(){
        Properties prop = new Properties();

        // Deshabilitamos TLS
        prop.setProperty("mail.pop3.starttls.enable", "false");

        // Hay que usar SSL
        prop.setProperty("mail.pop3.socketFactory.class","javax.net.ssl.SSLSocketFactory" );
        prop.setProperty("mail.pop3.socketFactory.fallback", "false");

        // Puerto 995 para conectarse.
        prop.setProperty("mail.pop3.port","995");
        prop.setProperty("mail.pop3.socketFactory.port", "995");
        
        Session sesion = Session.getInstance(prop);
        sesion.setDebug(true);
        
        Store store;
        Folder folder;
        try {
            
            store = sesion.getStore("pop3");store.connect("pop.gmail.com","mcor63@gmail.com","macor20638429");
            folder = store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);
            
            Message [] mensajes = folder.getMessages();
            
            for (int i=0;i<mensajes.length;i++){
                
               System.out.println("From:"+mensajes[i].getFrom()[0].toString());
               System.out.println("Subject:"+mensajes[i].getSubject());
            }
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(Pruebas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(Pruebas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    } 
     
     public void Correo() {
            String mensaje ="<html>\n" +
                            "    <head>\n" +
                            "        <title></title>\n" +
                            "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" +
                            "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                            "    </head>\n" +
                            "    <body>\n" +
                    "<strong>Usuario: </strong> Usuario"+
                    "<strong>Contraseña: </strong> Contraseña\n"+
                            "        <img src=\"https://dl.dropboxusercontent.com/u/9771556/venvidrio/venvidrio.png\" alt=\"\" style=\"width: 150px;\"/>\n" +
                            "        <h4 style=\"font-family: sans-serif;\">\n" +
                            "            Notificaciones Venvidrio<br>\n" +
                            "            Venezolana del Vidrio C.A.<br>\n" +
                            "            Teléfono:+58(241)xxxxxxx\n" +
                            "        </h4>\n" +
                            "    </body>\n" +
                            "</html>";     
            Send_correo_nuevo(mensaje);
    }
    
    public static void Send_correo_nuevo(String mensaje){
        
        System.out.println("Estoy enviando el correo");
        String to ="m_cesar111@hotmail.com";
        //String to ="mcor63@gmail.com";
        //String to ="johandavid27@hotmail.com";
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
            MimeBodyPart texto = new MimeBodyPart();
            texto.setContent(mensaje,"text/html");
        
            BodyPart adjunto = new MimeBodyPart();
            adjunto.setDataHandler( new DataHandler(new FileDataSource("C:\\documento.pdf")));
            adjunto.setFileName("documento.pdf");
            
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            multiParte.addBodyPart(adjunto);
            
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(
                Message.RecipientType.TO,
                new InternetAddress(to));
            message.setSubject("Cotización Venvidrio");
            message.setContent(multiParte);
            //message.setContent(mensaje,"text/html");

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
