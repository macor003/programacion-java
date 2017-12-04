/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venvidrio.cdv.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author ortegam
 */
public class cdvUtility {
    
    public Connection Conexion() {
        Connection con=null;
        String url = "jdbc:postgresql://10.183.9.24:5432/CDV_DB";
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection( url,"casitavdv","L0g1t3ch");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;

    }
    
    public String getCantidadCarrito(String rif){
        Connection con=this.Conexion();
        Statement stmt=null;
        ResultSet rs=null;
        
        String cantidad="";
        
        String sql="SELECT \n" +
            "  cast(sum(cdv_cantidad_original) as integer) as cant_carrito \n" +
            " FROM \n" +
            "  t_cdv_detalle_pedido, \n" +
            "  t_cdv_encabezado_pedido \n" +
            " WHERE \n" +
            "  t_cdv_detalle_pedido.cdv_id_pedido = t_cdv_encabezado_pedido.cdv_id_pedido AND \n" +
            "  t_cdv_detalle_pedido.cdv_estatus = t_cdv_encabezado_pedido.cdv_estatus_pedido AND \n" +
            "  t_cdv_encabezado_pedido.cdv_id_usuario = '"+rif+"' AND \n" +
            "  t_cdv_encabezado_pedido.cdv_estatus_pedido = 'EPC'";
        
        try{
            stmt=con.createStatement();
            rs=stmt.executeQuery(sql);
            if(rs.next()){
                int cant_carrito=rs.getInt("cant_carrito");

                DecimalFormat df=new DecimalFormat("#,##0");
                cantidad=df.format(cant_carrito);

            }

            if(rs!=null) rs.close();
            if(stmt!=null) stmt.close();
            if(con!=null) con.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return cantidad;
    }
    
    
    public String Barra(String rif, String nom_usuario){
        String htmlcode="";
        htmlcode=    "<div class=\"navbar navbar-default\">\n" +
                        "            <div class=\"container\">\n" +
                        "                <div class=\"navbar-header\">\n" +
                        "                    <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\".navbar-responsive-collapse\">\n" +
                        "                        <a href=\"cdv_carrito.jsp\" style=\"font-size: 17px;\"><strong id='num_carrito' style='color:white;'>"+getCantidadCarrito(rif)+" <span class=\"glyphicon glyphicon-shopping-cart\"></span></strong></a>&nbsp;&nbsp;\n"+
                        "                        MENÚ &nbsp;\n" + 
                        "                        <span class=\"glyphicon glyphicon-menu-hamburger\"></span>\n" +
                        "                    </button>\n" +
                        "                    <a class=\"navbar-brand\" href=\"javascript:void(0)\"><img src=\"cdv_imagenes/casita.png\" alt=\"\" style=\"margin-top: -5px; width: 50px;\"/></a>\n" +
                        "                </div>\n" +
                        "                <div class=\"navbar-collapse collapse navbar-responsive-collapse\">\n" +
                        "                    <ul class=\"nav navbar-nav\">\n" +
                        "                        <li ><a href=\"Controller?event=PRINCIPAL\"><i class=\"glyphicon glyphicon-home\"></i> Inicio</a></li>\n" +
                        "                        <li ><a href=\"Controller?event=VER_PRODUCTOS\"><i class=\"glyphicon glyphicon-glass\"></i> Productos</a></li>\n" +
                        "                    </ul>\n" +
                        "                    <ul class=\"nav navbar-nav navbar-right\">\n";
        
        if(!nom_usuario.equals("")){
                        
              htmlcode+="                        <li ><a class='carrito' href=\"cdv_carrito.jsp\" style=\"font-size: 17px;\"><strong id='num_carrito' style='  background: #814141; padding: 10px 13px; border-radius: 50%; '>"+getCantidadCarrito(rif)+"</strong>&nbsp;<i class=\"glyphicon glyphicon-shopping-cart\"></i></a></li>\n"+
                        "                        <li class=\"dropdown\">\n" +
                        "                            <a href=\"#\" data-target=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\"><i class=\"glyphicon glyphicon-user\"></i> " + nom_usuario + "<b class=\"caret\"></b></a>\n" +
                        "                            <ul class=\"dropdown-menu\">\n" +
                        "                                <li><a href=\"cdv_configurar.jsp\"><i class=\"glyphicon glyphicon-cog\"></i> Configuración</a></li>\n" +
                        "                                <li><a href=\"cdv_panel_control.jsp\"><i class=\"glyphicon glyphicon-dashboard\"></i> Panel de control</a></li>\n" +
                        "                                <li><a href=\"javascript:void(0)\"><i class=\"glyphicon glyphicon-file\"></i> Pedidos</a></li>\n" +
                        "                                <li class=\"divider\" style='background-color:lightgray;'></li>\n" +
                        "                                <li><a href=\"index.jsp\"><i class=\"glyphicon glyphicon-off\"></i> Cerrar sesión</a></li>\n" +
                        "                            </ul>\n" +
                        "                        </li>\n";
              
        }else{
            htmlcode+=  "<li ><a href=\"index.jsp\" style=\"font-size: 17px;\"><strong id='num_carrito'>"+getCantidadCarrito(rif)+" </strong>&nbsp;<i class=\"glyphicon glyphicon-shopping-cart\"></i></a></li>\n"+
                        "<li ><a href=\"index.jsp\" style=\"font-size: 17px;\">Iniciar Sesión <i class=\"glyphicon glyphicon-triangle-right\"></i></a></li>\n";
        }
                        
              htmlcode+="                    </ul>\n" +
                        "                </div>\n" +
                        "            </div>\n" +
                        "        </div>";
              
              
        return htmlcode;
    }
    
    
    public String mostrarBarra(String rol){
        System.out.println("ROL para BARRA: " + rol);
        String htmlcode="";
        if(rol.equals("01")){ //Administrador, SuperUsuario
            htmlcode=    "<div class=\"navbar navbar-default\">\n" +
                        "            <div class=\"container\">\n" +
                        "                <div class=\"navbar-header\">\n" +
                        "                    <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\".navbar-responsive-collapse\">\n" +
                        "                        MENÚ &nbsp;\n" +
                        "                        <span class=\"glyphicon glyphicon-menu-hamburger\"></span>\n" +
                        "                    </button>\n" +
                        "                    <a class=\"navbar-brand\" href=\"javascript:void(0)\"><img src=\"cdv_imagenes/casita.png\" alt=\"\" style=\"margin-top: -5px; width: 50px;\"/></a>\n" +
                        "                </div>\n" +
                        "                <div class=\"navbar-collapse collapse navbar-responsive-collapse\">\n" +
                        "                    <ul class=\"nav navbar-nav\">\n" +
                        "                        <li ><a href=\"cdv_principal.jsp\"><i class=\"glyphicon glyphicon-home\"></i> Inicio</a></li>\n" +
                        "                        <li class=\"active\"><a href=\"cdv_productos.jsp\"><i class=\"glyphicon glyphicon-glass\"></i> Productos</a></li>\n" +
                        "                    </ul>\n" +
                        "                    <ul class=\"nav navbar-nav navbar-right\">\n" +
                        "                        <li ><a href=\"cdv_carrito.jsp\" style=\"font-size: 17px;   background: #814141; padding: 10px 16px; border-radius: 50%;\">0 &nbsp;<i class=\"glyphicon glyphicon-shopping-cart\"></i></a></li>\n" +
                        "                        <li class=\"dropdown\">\n" +
                        "                            <a href=\"#\" data-target=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\"><i class=\"glyphicon glyphicon-user\"></i> Mario Ortega<b class=\"caret\"></b></a>\n" +
                        "                            <ul class=\"dropdown-menu\">\n" +
                        "                                <li><a href=\"javascript:void(0)\"><i class=\"glyphicon glyphicon-cog\"></i> Configuración</a></li>\n" +
                        "                                <li class=\"divider\"></li>\n" +
                        "                                <li><a href=\"index.jsp\"><i class=\"glyphicon glyphicon-off\"></i> Cerrar sesión</a></li>\n" +
                        "                            </ul>\n" +
                        "                        </li>\n" +
                        "                    </ul>\n" +
                        "                </div>\n" +
                        "            </div>\n" +
                        "        </div>";
                
        }
        else if(rol.equals("02")){ //Rol de usuario coordinador o supervisor
            htmlcode="<nav>\n" +
"            <ul id=\"nav\">\n" +
"      \n" +
"                    <li><a href=\"Controller?event=PAGINA_PRINCIPAL\">Inicio</a></li>\n" +
"                    <li><a class=\"hsubs\" href=\"#\">Crear</a>\n" +
"                        <ul class=\"subs\">\n" +
"                            <li><a href=\"Controller?event=CREAR_REQUERIMIENTO\" target=\"_self\">Nuevo requerimiento</a></li>\n" +
"                            <li><a href=\"Controller?event=CREAR_REQUERIMIENTO_OTRO_USUARIO\" target=\"_self\">Requerimiento para otro usuario</a></li>\n" +
"                        </ul>\n" +
"                    </li>\n" +
"                    <li><a class=\"hsubs\" href=\"#\">Requerimientos</a>\n" +
"                        <ul class=\"subs\">\n" +
//"                            <li><a href='Controller?event=ASIGNADOS'>Asignados</a></li>\n" +
"                            <li><a href='Controller?event=CREADOS'>Creados</a></li>\n" +
//"                            <li><a href='Controller?event=HISTORICO_REQUERIMIENTOS'>Historial</a></li>\n" + 
//"                            <li><a href='Controller?event=PENDIENTES_REQUERIMIENTO'>Pendientes por asignar</a></li>\n" +                    
"                            <li><a href='Controller?event=APROBAR_REQUERIMIENTO'>Pendientes por aprobar</a></li>\n" +
"                        </ul>\n" +
"                    </li>\n" +
//"                    <li><a class=\"hsubs\" href=\"#\">Reportes</a>\n" +
//"                        <ul class=\"subs\">\n" +
//"                            <li><a href='reporte.jsp'>Prueba de reportes</a></li>\n" +
//"                        </ul>\n" +
//"                    </li>\n" +
//"                    <li><a href='#'>Configuración</a>\n" +
//"                        <ul class=\"subs\">\n" +
//"                            <li><a href='Controller?event=ACTUALIZAR_AREA'>Areas</a>\n" +
////"                            <li><a href='Controller?event=ACTUALIZAR_AREA_ANALISTA'>Analistas por Areas</a>\n" +
////"                            <li><a href='#'>Tipos de Requerimientos</a>\n" +                    
//"                                <ul class=\"lsubs\">\n" +
//"                                    <li><a href=\"#\">Prueba</a></li>\n" +
//"                                </ul>\n" +
//"                            </li>\n" +
//"                        </ul>\n" +
//"                    </li>\n" +
"                    <div id=\"lavalamp\"></div>\n" +
"            </ul>\n" +
"         </nav>";

        } else if(rol.equals("03")){ //Rol Usuario general.
            htmlcode="<nav>\n" +
"            <ul id=\"nav\">\n" +
"      \n" +
"                    <li><a href=\"Controller?event=PAGINA_PRINCIPAL\">Inicio</a></li>\n" +
"                    <li><a class=\"hsubs\" href=\"#\">Crear</a>\n" +
"                        <ul class=\"subs\">\n" +
"                            <li><a href=\"Controller?event=CREAR_REQUERIMIENTO\" target=\"_self\">Nuevo requerimiento</a></li>\n" +
//"                            <li><a href=\"Controller?event=CREAR_REQUERIMIENTO_OTRO_USUARIO\" target=\"_self\">Nuevo requerimiento para otro usuario</a></li>\n" +
"                        </ul>\n" +
"                    </li>\n" +
"                    <li><a class=\"hsubs\" href=\"#\">Requerimientos</a>\n" +
"                        <ul class=\"subs\">\n" +
//"                            <li><a href='Controller?event=ASIGNADOS'>Asignados</a></li>\n" +
"                            <li><a href='Controller?event=CREADOS'>Creados</a></li>\n" +
//"                            <li><a href='Controller?event=HISTORICO_REQUERIMIENTOS'>Historial</a></li>\n" + 
//"                            <li><a href='Controller?event=PENDIENTES_REQUERIMIENTO'>Pendientes por asignar</a></li>\n" +                    
//"                            <li><a href='Controller?event=APROBAR_REQUERIMIENTO'>Pendientes por aprobar</a></li>\n" +
"                        </ul>\n" +
"                    </li>\n" +
//"                    <li><a class=\"hsubs\" href=\"#\">Reportes</a>\n" +
//"                        <ul class=\"subs\">\n" +
//"                            <li><a href='reporte.jsp'>Prueba de reportes</a></li>\n" +
//"                        </ul>\n" +
//"                    </li>\n" +
//"                    <li><a href='#'>Configuración</a>\n" +
//"                        <ul class=\"subs\">\n" +
//"                            <li><a href='Controller?event=ACTUALIZAR_AREA'>Areas</a>\n" +
////"                            <li><a href='Controller?event=ACTUALIZAR_AREA_ANALISTA'>Analistas por Areas</a>\n" +
////"                            <li><a href='#'>Tipos de Requerimientos</a>\n" +                    
//"                                <ul class=\"lsubs\">\n" +
//"                                    <li><a href=\"#\">Prueba</a></li>\n" +
//"                                </ul>\n" +
//"                            </li>\n" +
//"                        </ul>\n" +
//"                    </li>\n" +
"                    <div id=\"lavalamp\"></div>\n" +
"            </ul>\n" +
"         </nav>";
                    }
        return htmlcode;

    }
    
    
    public void Correo(String usuario, String passwd, String to) {
            String mensaje ="<html>\n" +
                            "    <head>\n" +
                            "        <title></title>\n" +
                            "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" +
                            "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                            "    </head>\n" +
                            "    <body>\n" +
                    "<strong>Usuario: </strong> "+usuario+"\n"+
                    "<strong>Contraseña: </strong> "+passwd+"\n"+
                            "        <img src=\"https://dl.dropboxusercontent.com/u/9771556/venvidrio/venvidrio.png\" alt=\"\" style=\"width: 150px;\"/>\n" +
                            "        <h4 style=\"font-family: sans-serif;\">\n" +
                            "            Notificaciones Venvidrio<br>\n" +
                            "            Venezolana del Vidrio C.A.<br>\n" +
                            "            Teléfono:+58(241)xxxxxxx\n" +
                            "        </h4>\n" +
                            "    </body>\n" +
                            "</html>";     
            Send_correo_nuevo(mensaje, to);
    }
    
    public static void Send_correo_nuevo(String mensaje, String to){
        
        System.out.println("Estoy enviando el correo");
        //String to ="m_cesar111@hotmail.com";
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
        
            //BodyPart adjunto = new MimeBodyPart();
            //adjunto.setDataHandler( new DataHandler(new FileDataSource("C:\\documento.pdf")));
            //adjunto.setFileName("documento.pdf");
            
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            //multiParte.addBodyPart(adjunto);
            
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(
                Message.RecipientType.TO,
                new InternetAddress(to));
            message.setSubject("Usuario Casita del Vidrio");
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
