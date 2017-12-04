
package com.venvidrio.sgt.loader;

import static com.venvidrio.sgt.loader.TSgtRequerimientoLoader.Send_correo_nuevo_requerimiento;
import com.venvidrio.sgt.utility.sgtUtility;
import java.sql.*;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;





/**
 *
 * @author Ortegam
 */
public class TSgtAprobarLoader {
    
    private static String hostLGV="maillgv.venvidrio.com.ve";
    private static String hostVLR="mailvlr.venvidrio.com.ve";
    private static String smtpPort="25";
    private static String from="atencion.soporte@venvidrio.com.ve";
    
    public String verRequerimientoPorAprobar(String ficha){
    sgtUtility util = new sgtUtility();
    Connection con = null;
    Statement stm = null;
    ResultSet rs = null;
    String retorno = "";


        try{
            String sql ="SELECT \n" +
                        "  sgt_cod_requerimiento, \n" +
                        "  sgt_fecha_requerimiento, \n" +
                        "  sgt_desc_requerimiento, \n" +
                        "  per_nombre, \n" +
                        "  per_ficrep, \n" +
                        "  sgt_cod_tipo_requerimieto\n" +
                        "FROM \n" +
                        "  t_sgt_requerimiento, \n" +
                        "  vv_personal \n" +
                        "WHERE \n" +
                        "  per_ficha = per_ficha_solicitante AND\n" +
                        "  per_cod_planta = per_cod_planta_solicitante AND\n" +
                        "  sgt_cod_tipo_requerimieto='03' AND\n" +
                        "  sgt_cod_status ='1' and \n" +
                        "  per_ficrep='"+ficha+"'\n" +
                        "  ORDER BY \n" +
                        "  sgt_cod_requerimiento;";
            con = util.Conexion_Sorg();
            stm= con.createStatement();
            rs = stm.executeQuery(sql);
            System.out.println("SQL: " + sql);
            while(rs.next()){
                retorno += "<tr id='tablaDescripcion' class='registro' >";
                String cod_reque = rs.getString("sgt_cod_requerimiento");
                String fecha_reque = rs.getString("sgt_fecha_requerimiento");
                String desc_reque = rs.getString("sgt_desc_requerimiento");
                String nombre_usu = rs.getString("per_nombre");
                
                retorno+="<td class='detalleAprobar'><input type='text' value='"+cod_reque+"' id='cod_reque' disabled name='lm_cod"+cod_reque+"' class='detalleAprobar'></td>";
                retorno+="<td class='detalleAprobar'>"+nombre_usu+"</td>";
                retorno+="<td class='detalleAprobar'>"+fecha_reque.substring(0, 11)+"</td>";
                retorno+="<td class='detalleAprobar'>"+desc_reque+"</td>";
                retorno+="<td class='detalleAprobar'>";
                retorno+="<a href='Controller?event=DETALLE_APROBAR_REQUERIMIENTO' target='_self'><input type='submit' value='Ir Aprobar' name='lm_aprobar"+cod_reque+"' id='irAprobar' onclick='ejecutaCodigo(this.name);'></a>";
                retorno+="</td>"; 
                retorno+="</tr>";
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
    return retorno;    
}

    public String verDetallesRequerimientoPorAprobar(String ficha, String var){
    sgtUtility util = new sgtUtility();
    Connection con = null;
    Statement stm = null;
    ResultSet rs = null;
    String retorno = "";

    System.out.println("Codigo del Requerimiento: " + var);
    System.out.println("Ficha del Aprobador: " + ficha);
                retorno+="<table id='detallesRequerimiento'>";
                retorno+="<tr id='tituloTabla'><th id='Campos'>Campos</th><th>Detalles</th></tr>";


        try{
            String sql ="SELECT \n" +
                        "  sgt_cod_requerimiento, \n" +
                        "  sgt_fecha_requerimiento, \n" +
                        "  sgt_desc_requerimiento, \n" +
                        "  per_nombre, \n" +
                        "  per_ficrep, \n" +
                        "  sgt_cod_tipo_requerimieto, \n" +
                        "  sgt_desc_urgencia " +
                        "FROM \n" +
                        "  t_sgt_requerimiento, \n" +
                        "  vv_personal, \n" +
                        "  t_sgt_urgencia " +
                        "WHERE \n" +
                        "  per_ficha = per_ficha_solicitante AND\n" +
                        "  per_cod_planta = per_cod_planta_solicitante AND\n" +
                        "   t_sgt_urgencia.sgt_cod_urgencia = t_sgt_requerimiento.sgt_cod_urgencia AND " +
                        "  sgt_cod_tipo_requerimieto='03' AND\n" +
                        "  sgt_cod_requerimiento = '"+var+"' AND " +
                        "  sgt_cod_status ='1' and \n" +
                        "  per_ficrep='"+ficha+"'\n" +
                        "  ORDER BY \n" +
                        "  sgt_cod_requerimiento;";

            con = util.Conexion_Sorg();
            stm= con.createStatement();
            rs = stm.executeQuery(sql);




            while(rs.next()){
                retorno += "<tr id='tablaDescripcion'>";
                String cod_reque = rs.getString("sgt_cod_requerimiento");
                String fecha_reque = rs.getString("sgt_fecha_requerimiento");
                String desc_reque = rs.getString("sgt_desc_requerimiento");
                String per_nom = rs.getString("per_nombre");  
                String urgencia = rs.getString("sgt_desc_urgencia");
                retorno+="<tr><td class='campo'>Código del Requerimiento:</td><td>"+cod_reque+"</td></tr>";
                retorno+="<tr><td class='campo'>Nombre del Usuario:</td><td>"+per_nom+"</td></tr>";
                retorno+="<tr><td class='campo'>Fecha del Requerimiento</td><td>"+fecha_reque.substring(0, 16)+"</td></tr>";
                //retorno+="<tr><td class='campo'>Clasificación del Requerimieto:</td><td>"+desc_clasi+"</td></tr>";
                retorno+="<tr><td class='campo'>Decripción del Requerimiento:</td><td>"+desc_reque+"</td></tr>";
                retorno+="<tr><td class='campo'>Urgencia:</td><td>"+urgencia+"</td></tr>";

            }


            retorno+="</table>";
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
    return retorno;  

    }

    public void insertarAprobacionRequerimiento(int cod_reque, String ficha, String usuario_log, String ficha_log, String planta_log){
    
    sgtUtility util = new sgtUtility();

    java.util.Date today=new java.util.Date();        
    Connection con =null;
   
    String status = "6";
    String log ="04";
    
    PreparedStatement pstmt=null;
    con=util.Conexion_Sorg();

    String sql="INSERT INTO t_sgt_aprobacion_requerimiento(\n" +
                "            sgt_cod_requerimiento, sgt_usuario_aprob, sgt_fecha_aprob, sgt_cod_status)\n" +
                "    VALUES (?, ?, ?, ?);";


    String sql2 ="INSERT INTO t_sgt_log_requerimiento(\n" +
                "            sgt_cod_requerimiento, sgt_fecha_registro, sgt_cod_log, sgt_ficha_usulog, sgt_planta_usulog)\n" +
                "    VALUES (?, ?, ?, ?, ?);";

    String sql3="UPDATE t_sgt_requerimiento\n" +
                  "   SET sgt_cod_status=?, sgt_usuario_aprobador=?, sgt_fecha_aprobado=?\n" +
                  " WHERE sgt_cod_requerimiento='"+cod_reque+"';";
    try {            
            
        pstmt=con.prepareStatement(sql);
        pstmt.setInt(1, cod_reque);
        pstmt.setString(2, usuario_log);
        pstmt.setTimestamp(3, new java.sql.Timestamp(today.getTime()));
        pstmt.setString(4, status);
        pstmt.executeUpdate();

        pstmt = con.prepareStatement(sql3);
        pstmt.setString(1, status);
        pstmt.setString(2, usuario_log);
        pstmt.setTimestamp(3, new java.sql.Timestamp(today.getTime()));
        pstmt.executeUpdate();           

        pstmt=con.prepareStatement(sql2);
        pstmt.setInt(1, cod_reque);
        pstmt.setTimestamp(2, new java.sql.Timestamp(today.getTime()));
        pstmt.setString(3, log);
        pstmt.setString(4, ficha_log);
        pstmt.setString(5, planta_log);
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
    
    public void CorreoAprobado(int cod_reque) {
        
        sgtUtility util = new sgtUtility();
        TSgtVerificarUsuarioLoader ver = new TSgtVerificarUsuarioLoader(); 
        Connection con= util.Conexion_Sorg();
        Statement stmt=null;
        ResultSet rs=null;
        String nombre="";
        String clasi ="";
        
        String planta_solic ="";
        String ficha_solic="";
        String desc ="";
        String clasi_reque ="";
        try{
                           
            
        String sql_con="SELECT \n" +
                    "  per_ficha_solicitante, \n" +
                    "  per_cod_planta_solicitante," +
                    "  sgt_desc_requerimiento, \n" +
                    "  sgt_cod_clasificacion_area\n" +
                    "FROM \n" +
                    "  public.t_sgt_requerimiento\n" +
                    "WHERE \n" +
                    "  sgt_cod_requerimiento='"+cod_reque+"';";
        
        con = util.Conexion_Sorg();
            stmt= con.createStatement();
            rs = stmt.executeQuery(sql_con);
            System.out.println("SQL: " + sql_con);
            while(rs.next()){
                ficha_solic = rs.getString("per_ficha_solicitante");
                planta_solic = rs.getString("per_cod_planta_solicitante");
                desc = rs.getString("sgt_desc_requerimiento");
                clasi_reque = rs.getString("sgt_cod_clasificacion_area");
            }
            //Sql para saber la clasificacion del requerimieto
            String sql_clasi="SELECT \n" +
                            "  sgt_desc_clasificacion\n" +
                            "FROM \n" +
                            "  t_sgt_clasificacion\n" +
                            "WHERE \n" +
                            "  sgt_cod_clasificacion = '"+clasi_reque+"';";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql_clasi);
            while(rs.next()){
                clasi = rs.getString("sgt_desc_clasificacion");
            }
                       
        //Sql para saber las fichas y los correos de los Responsables de Indicadores
        String sql="SELECT \n" +
                    "  per_nombre\n" +
                    "FROM \n" +
                    "  vv_personal, \n" +
                    "  vv_planta\n" +
                    "WHERE \n" +
                    "  per_cod_planta = vv_planta.pla_cod_planta AND\n" +
                    "  per_ficha = '"+ficha_solic+"' AND \n" +
                    "  per_cod_planta = '"+planta_solic+"';";
        
        stmt=con.createStatement();
        rs=stmt.executeQuery(sql);
        while(rs.next()){
            
            nombre=rs.getString("per_nombre");
            String mensaje ="<!DOCTYPE HTML>\n" +
                            "<html>\n" +
                            "<head>\n" +
                            "<title> Nuevo Requerimiento</title>\n" +
                            "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                            "</head>\n" +
                            "<style>\n" +
                            "body{\n" +
                            "  margin-left: 50px;\n" +
                            "  margin-top: 50px;\n" +
                            "}\n" +
                            "#encabezado{\n" +
                            "  width:460px;\n" +
                            "  height: 45px;\n" +
                            "  text-align:center;\n" +
                            "  font-weight: 300;\n" +
                            "  border: 1px solid #8A0808;\n" +
                            "  color: white;\n" +
                            "  background-color: #8A0808;\n" +
                            "}\n" +
                            "h2{\n" +
                            "  margin-top:10px;\n" +
                            "  font: bold 20px sans-serif;\n" +
                            "  font-weight: 400;\n" +
                            "  letter-spacing: 1px;\n" +
                            "}\n" +
                            "#correo{\n" +
                            "  position: relative;\n" +
                            "  top: -50px;\n" +
                            "  left: 40%;\n" +
                            "  width: 12%;\n" +
                            "  height: 120%;\n" +
                            "}\n" +
                            "#detalles{\n" +
                            "  width: 462px;\n" +
                            "  height: 100px;\n" +
                            "  border: 1px solid #8A0808;  \n" +
                            "  background-color: white;\n" +
                            "  padding-bottom: 20px;\n" +
                            "}\n" +
                            "#detalles td{\n" +
                            "  padding-top: 20px;\n" +
                            "}\n" +
                            "#titulo{\n" +
                            "  text-align: left;  \n" +
                            "  font: bold 13px sans-serif;\n" +
                            "  width: 100px;\n" +
                            "  padding-left: 10px;\n" +
                            "\n" +
                            "}\n" +
                            "#detalleReque{\n" +
                            "  font-size: 14px;\n" +
                            "  text-align:left;\n" +
                            "  font-family: sans-serif;\n" +
                            "}\n" +
                            "#nombre{\n" +
                            "  font-size: 13px;\n" +
                            "  text-align:left;\n" +
                            "  font-family: sans-serif;\n" +
                            "}\n" +
                            "#aprobar{\n" +
                            "  width: 229px;\n" +
                            "  height: 30px;\n" +
                            "  background-color: #8A0808;\n" +
                            "  border: 1px solid #8A0808;  \n" +
                            "}\n" +
                            "#aprobar:hover{\n" +
                            "  cursor: pointer;\n" +
                            "  background-color: #B90000;\n" +
                            "  border: 1px solid #B90000;\n" +
                            "}\n" +
                            "#aprobar label{  \n" +
                            "  position: relative;\n" +
                            "  top: 5px;\n" +
                            "  left: 85px;\n" +
                            "  color: white;  \n" +
                            "  font: 16px sans-serif;\n" +
                            "  font-weight: 200px;\n" +
                            "  cursor: pointer;\n" +
                            "}\n" +
                            "#aprobar a{\n" +
                            "  text-decoration: none;\n" +
                            "}\n" +
                            "#aprobar a:hover{\n" +
                            "  cursor: pointer;\n" +
                            "}\n" +
                            "#rechazar{\n" +
                            "  width: 229px;\n" +
                            "  height: 30px;\n" +
                            "  background-color: #8A0808;\n" +
                            "  border: 1px solid #8A0808;  \n" +
                            "  margin-top: -32px;\n" +
                            "  margin-left: 231px;\n" +
                            "}\n" +
                            "#rechazar:hover{\n" +
                            "  cursor: pointer;\n" +
                            "  background-color: #B90000;\n" +
                            "  border: 1px solid #B90000;\n" +
                            "}\n" +
                            "#rechazar label{  \n" +
                            "  position: relative;\n" +
                            "  top: 5px;\n" +
                            "  left: 85px;\n" +
                            "  color: white;  \n" +
                            "  font: 16px sans-serif;\n" +
                            "  font-weight: 200;\n" +
                            "  cursor: pointer;\n" +
                            "}\n" +
                            "#rechazar a{\n" +
                            "  text-decoration: none;\n" +
                            "}\n" +
                            ".triangulo_top_left {\n" +
                            "    width: 0;\n" +
                            "    height: 0;\n" +
                            "    border-top: 30px solid #fff; \n" +
                            "    border-right: 38px solid transparent;\n" +
                            "    position: absolute;           \n" +
                            "}\n" +
                            ".triangulo_bottom_right {\n" +
                            "    width: 0;\n" +
                            "    height: 0;\n" +
                            "    border-bottom: 30px solid #000; \n" +
                            "    border-left: 38px solid transparent;  \n" +
                            "    position: absolute;  \n" +
                            "    opacity: 0.5;           \n" +
                            "}\n" +
                            "input[type=\"submit\"]{\n" +
                            "  width: 462px;\n" +
                            "  height: 45px;\n" +
                            "  background-color: #8A0808;\n" +
                            "  border: 1px solid #8A0808;\n" +
                            "  color: white;\n" +
                            "}\n" +
                            "input[type=\"submit\"]:hover{\n" +
                            "  cursor: pointer;\n" +
                            "  background-color: #B90000;\n" +
                            "  border: 1px solid #B90000;\n" +
                            "}\n" +
                            "</style>\n" +
                            "<body>\n" +
                            "  <div class=\"triangulo_top_left\"></div>\n" +
                            "  <div class=\"triangulo_bottom_right\"></div>\n" +
                            "  <div id=\"encabezado\"><h2>Requerimiento Aprobado</h2></div>\n" +
                            "  <table id=\"detalles\">\n" +
                            "    <tr>\n" +
                            "      <td id=\"titulo\">C&oacute;digo:</td>\n" +
                            "      <td id=\"detalleReque\">"+cod_reque+"</td>\n" +
                            "    </tr>\n" +
                            "    <tr>\n" +
                            "      <td id=\"titulo\">A Nombre de:</td>\n" +
                            "      <td id=\"nombre\">"+nombre+"</td>\n" +
                            "    </tr>\n" +
                            "    <tr>\n" +
                            "      <td id=\"titulo\">Clasificaci&oacute;n:</td>\n" +
                            "      <td id=\"detalleReque\">"+clasi+"</td>\n" +
                            "    </tr>\n" +
                            "    <tr>\n" +
                            "      <td id=\"titulo\">Descripci&oacute;n:</td>\n" +
                            "      <td id=\"detalleReque\">"+desc+"</td>\n" +
                            "    </tr>\n" +
                            "  </table>\n" +
                            "  <a href='http://10.183.9.20:8080/sgt_website/index.jsp'><input type=\"submit\" value=\"Iniciar sesi&oacute;n para ver detalles\"></a>\n" +
                            "  \n" +
                            "</body>\n" +
                            "</html>";
            
         String to = ver.VerificarCorreo(planta_solic, ficha_solic);
         Send_correo_aprobado(mensaje, to, planta_solic);
         Send_correo_aprobado_cau(mensaje, nombre);
         //Send_correo_nuevo_requerimiento_cau(mensaje);
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
    
    public static void Send_correo_aprobado_cau(String mensaje, String nombre){
        
        //String to ="marilys.rivas@venvidrio.com.ve";  
        String to ="atencion.soporte@venvidrio.com.ve"; 
        String planta ="02";
        Properties props = System.getProperties();
        if(planta.equals("05")){
            props.put("mail.smtp.host", hostVLR);
        }else if(planta.equals("02")){
            props.put("mail.smtp.host", hostLGV);
        }
                props.put("mail.smtp.port", smtpPort);

                // Get session
                Session session = Session.getDefaultInstance(props, null);

                // Define message
                MimeMessage message = new MimeMessage(session);

        try {
            //Who sends message
            message.setFrom(new InternetAddress(from));
            //Who goes the message to
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            //set the subject of the message
                message.setSubject("Requerimiento Aprobado para " + nombre);
            //set content of the message in html format
            message.setContent(mensaje,"text/html");

                // Send message
               Transport.send(message);
               System.out.println("Mensaje de correo enviado");
            } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    public static void Send_correo_aprobado(String mensaje, String to, String planta){
        
        //String to ="marilys.rivas@venvidrio.com.ve";  
        //String to ="mario.ortega@venvidrio.com.ve"; 
        //String planta ="02";
        Properties props = System.getProperties();
        if(planta.equals("05")){
            props.put("mail.smtp.host", hostVLR);
        }else if(planta.equals("02")){
            props.put("mail.smtp.host", hostLGV);
        }
                props.put("mail.smtp.port", smtpPort);

                // Get session
                Session session = Session.getDefaultInstance(props, null);

                // Define message
                MimeMessage message = new MimeMessage(session);

        try {
            //Who sends message
            message.setFrom(new InternetAddress(from));
            //Who goes the message to
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            //set the subject of the message
                message.setSubject("Requerimiento aprobado");
            //set content of the message in html format
            message.setContent(mensaje,"text/html");

                // Send message
               Transport.send(message);
               System.out.println("Mensaje de correo enviado");
            } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    public void insertarRechazoRequerimiento(int cod_reque, String ficha, String usuario_log, String ficha_log, String planta_log){

    sgtUtility util = new sgtUtility();

    java.util.Date today=new java.util.Date();        
    Connection con =null;

    String status = "7";
    String log ="08";
    PreparedStatement pstmt=null;
    con=util.Conexion_Sorg();

//    String sql="INSERT INTO t_sgt_aprobacion_requerimiento(\n" +
//                "            sgt_cod_requerimiento, sgt_usuario_aprob, sgt_fecha_aprob, sgt_cod_status)\n" +
//                "    VALUES (?, ?, ?, ?);";


    String sql2 ="INSERT INTO t_sgt_log_requerimiento(\n" +
                "            sgt_cod_requerimiento, sgt_fecha_registro, sgt_cod_log, sgt_ficha_usulog, sgt_planta_usulog)\n" +
                "    VALUES (?, ?, ?, ?, ?);";

    String sql3="UPDATE t_sgt_requerimiento\n" +
                  "   SET sgt_cod_status=?, sgt_usuario_aprobador=?, sgt_fecha_aprobado=?\n" +
                  " WHERE sgt_cod_requerimiento='"+cod_reque+"';";
    try {               
//
//        pstmt=con.prepareStatement(sql);
//        pstmt.setInt(1, cod_reque);
//        pstmt.setString(2, usuario_log);
//        pstmt.setTimestamp(3, new java.sql.Timestamp(today.getTime()));
//        pstmt.setString(4, status);
//        pstmt.executeUpdate();

        pstmt = con.prepareStatement(sql3);
        pstmt.setString(1, status);
        pstmt.setString(2, usuario_log);
        pstmt.setTimestamp(3, new java.sql.Timestamp(today.getTime()));
        pstmt.executeUpdate();           

        pstmt=con.prepareStatement(sql2);
        pstmt.setInt(1, cod_reque);
        pstmt.setTimestamp(2, new java.sql.Timestamp(today.getTime()));
        pstmt.setString(3, log);
        pstmt.setString(4, ficha_log);
        pstmt.setString(5, planta_log);
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
    
    public void CorreoRechazado(int cod_reque) {
        
        sgtUtility util = new sgtUtility();
        TSgtVerificarUsuarioLoader ver = new TSgtVerificarUsuarioLoader(); 
        Connection con= util.Conexion_Sorg();
        Statement stmt=null;
        ResultSet rs=null;
        String nombre="";
        String clasi ="";
        
        String planta_solic ="";
        String ficha_solic="";
        String desc ="";
        String clasi_reque ="";
        try{
                           
            
        String sql_con="SELECT \n" +
                    "  per_ficha_solicitante, \n" +
                    "  per_cod_planta_solicitante," +
                    "  sgt_desc_requerimiento, \n" +
                    "  sgt_cod_clasificacion_area\n" +
                    "FROM \n" +
                    "  public.t_sgt_requerimiento\n" +
                    "WHERE \n" +
                    "  sgt_cod_requerimiento='"+cod_reque+"';";
        
        con = util.Conexion_Sorg();
            stmt= con.createStatement();
            rs = stmt.executeQuery(sql_con);
            System.out.println("SQL: " + sql_con);
            while(rs.next()){
                ficha_solic = rs.getString("per_ficha_solicitante");
                planta_solic = rs.getString("per_cod_planta_solicitante");
                desc = rs.getString("sgt_desc_requerimiento");
                clasi_reque = rs.getString("sgt_cod_clasificacion_area");
            }
            //Sql para saber la clasificacion del requerimieto
            String sql_clasi="SELECT \n" +
                            "  sgt_desc_clasificacion\n" +
                            "FROM \n" +
                            "  t_sgt_clasificacion\n" +
                            "WHERE \n" +
                            "  sgt_cod_clasificacion = '"+clasi_reque+"';";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql_clasi);
            while(rs.next()){
                clasi = rs.getString("sgt_desc_clasificacion");
            }
                       
        //Sql para saber las fichas y los correos de los Responsables de Indicadores
        String sql="SELECT \n" +
                    "  per_nombre\n" +
                    "FROM \n" +
                    "  vv_personal, \n" +
                    "  vv_planta\n" +
                    "WHERE \n" +
                    "  per_cod_planta = vv_planta.pla_cod_planta AND\n" +
                    "  per_ficha = '"+ficha_solic+"' AND \n" +
                    "  per_cod_planta = '"+planta_solic+"';";
        
        stmt=con.createStatement();
        rs=stmt.executeQuery(sql);
        while(rs.next()){
            
            nombre=rs.getString("per_nombre");
            String mensaje ="<!DOCTYPE HTML>\n" +
                            "<html>\n" +
                            "<head>\n" +
                            "<title> Nuevo Requerimiento</title>\n" +
                            "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                            "</head>\n" +
                            "<style>\n" +
                            "body{\n" +
                            "  margin-left: 50px;\n" +
                            "  margin-top: 50px;\n" +
                            "}\n" +
                            "#encabezado{\n" +
                            "  width:460px;\n" +
                            "  height: 45px;\n" +
                            "  text-align:center;\n" +
                            "  font-weight: 300;\n" +
                            "  border: 1px solid #8A0808;\n" +
                            "  color: white;\n" +
                            "  background-color: #8A0808;\n" +
                            "}\n" +
                            "h2{\n" +
                            "  margin-top:10px;\n" +
                            "  font: bold 20px sans-serif;\n" +
                            "  font-weight: 400;\n" +
                            "  letter-spacing: 1px;\n" +
                            "}\n" +
                            "#correo{\n" +
                            "  position: relative;\n" +
                            "  top: -50px;\n" +
                            "  left: 40%;\n" +
                            "  width: 12%;\n" +
                            "  height: 120%;\n" +
                            "}\n" +
                            "#detalles{\n" +
                            "  width: 462px;\n" +
                            "  height: 100px;\n" +
                            "  border: 1px solid #8A0808;  \n" +
                            "  background-color: white;\n" +
                            "  padding-bottom: 20px;\n" +
                            "}\n" +
                            "#detalles td{\n" +
                            "  padding-top: 20px;\n" +
                            "}\n" +
                            "#titulo{\n" +
                            "  text-align: left;  \n" +
                            "  font: bold 13px sans-serif;\n" +
                            "  width: 100px;\n" +
                            "  padding-left: 10px;\n" +
                            "\n" +
                            "}\n" +
                            "#detalleReque{\n" +
                            "  font-size: 14px;\n" +
                            "  text-align:left;\n" +
                            "  font-family: sans-serif;\n" +
                            "}\n" +
                            "#nombre{\n" +
                            "  font-size: 13px;\n" +
                            "  text-align:left;\n" +
                            "  font-family: sans-serif;\n" +
                            "}\n" +
                            "#aprobar{\n" +
                            "  width: 229px;\n" +
                            "  height: 30px;\n" +
                            "  background-color: #8A0808;\n" +
                            "  border: 1px solid #8A0808;  \n" +
                            "}\n" +
                            "#aprobar:hover{\n" +
                            "  cursor: pointer;\n" +
                            "  background-color: #B90000;\n" +
                            "  border: 1px solid #B90000;\n" +
                            "}\n" +
                            "#aprobar label{  \n" +
                            "  position: relative;\n" +
                            "  top: 5px;\n" +
                            "  left: 85px;\n" +
                            "  color: white;  \n" +
                            "  font: 16px sans-serif;\n" +
                            "  font-weight: 200px;\n" +
                            "  cursor: pointer;\n" +
                            "}\n" +
                            "#aprobar a{\n" +
                            "  text-decoration: none;\n" +
                            "}\n" +
                            "#aprobar a:hover{\n" +
                            "  cursor: pointer;\n" +
                            "}\n" +
                            "#rechazar{\n" +
                            "  width: 229px;\n" +
                            "  height: 30px;\n" +
                            "  background-color: #8A0808;\n" +
                            "  border: 1px solid #8A0808;  \n" +
                            "  margin-top: -32px;\n" +
                            "  margin-left: 231px;\n" +
                            "}\n" +
                            "#rechazar:hover{\n" +
                            "  cursor: pointer;\n" +
                            "  background-color: #B90000;\n" +
                            "  border: 1px solid #B90000;\n" +
                            "}\n" +
                            "#rechazar label{  \n" +
                            "  position: relative;\n" +
                            "  top: 5px;\n" +
                            "  left: 85px;\n" +
                            "  color: white;  \n" +
                            "  font: 16px sans-serif;\n" +
                            "  font-weight: 200;\n" +
                            "  cursor: pointer;\n" +
                            "}\n" +
                            "#rechazar a{\n" +
                            "  text-decoration: none;\n" +
                            "}\n" +
                            ".triangulo_top_left {\n" +
                            "    width: 0;\n" +
                            "    height: 0;\n" +
                            "    border-top: 30px solid #fff; \n" +
                            "    border-right: 38px solid transparent;\n" +
                            "    position: absolute;           \n" +
                            "}\n" +
                            ".triangulo_bottom_right {\n" +
                            "    width: 0;\n" +
                            "    height: 0;\n" +
                            "    border-bottom: 30px solid #000; \n" +
                            "    border-left: 38px solid transparent;  \n" +
                            "    position: absolute;  \n" +
                            "    opacity: 0.5;           \n" +
                            "}\n" +
                            "input[type=\"submit\"]{\n" +
                            "  width: 462px;\n" +
                            "  height: 45px;\n" +
                            "  background-color: #8A0808;\n" +
                            "  border: 1px solid #8A0808;\n" +
                            "  color: white;\n" +
                            "}\n" +
                            "input[type=\"submit\"]:hover{\n" +
                            "  cursor: pointer;\n" +
                            "  background-color: #B90000;\n" +
                            "  border: 1px solid #B90000;\n" +
                            "}\n" +
                            "</style>\n" +
                            "<body>\n" +
                            "  <div class=\"triangulo_top_left\"></div>\n" +
                            "  <div class=\"triangulo_bottom_right\"></div>\n" +
                            "  <div id=\"encabezado\"><h2>Requerimiento rechazado</h2></div>\n" +
                            "  <table id=\"detalles\">\n" +
                            "    <tr>\n" +
                            "      <td id=\"titulo\">C&oacute;digo:</td>\n" +
                            "      <td id=\"detalleReque\">"+cod_reque+"</td>\n" +
                            "    </tr>\n" +
                            "    <tr>\n" +
                            "      <td id=\"titulo\">A Nombre de:</td>\n" +
                            "      <td id=\"nombre\">"+nombre+"</td>\n" +
                            "    </tr>\n" +
                            "    <tr>\n" +
                            "      <td id=\"titulo\">Clasificaci&oacute;n:</td>\n" +
                            "      <td id=\"detalleReque\">"+clasi+"</td>\n" +
                            "    </tr>\n" +
                            "    <tr>\n" +
                            "      <td id=\"titulo\">Descripci&oacute;n:</td>\n" +
                            "      <td id=\"detalleReque\">"+desc+"</td>\n" +
                            "    </tr>\n" +
                            "  </table>\n" +
                            "  <a href='http://10.183.9.20:8080/sgt_website/index.jsp'><input type=\"submit\" value=\"Iniciar sesi&oacute;n para ver detalles\"></a>\n" +
                            "  \n" +
                            "</body>\n" +
                            "</html>";
            
         String to = ver.VerificarCorreo(planta_solic, ficha_solic);
         Send_correo_rechazado(mensaje, to, planta_solic);
         Send_correo_rechazado_cau(mensaje, nombre);
         //Send_correo_nuevo_requerimiento_cau(mensaje);
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
    
    public static void Send_correo_rechazado_cau(String mensaje, String nombre){
        
        //String to ="marilys.rivas@venvidrio.com.ve";  
        String to ="atencion.soporte@venvidrio.com.ve"; 
        String planta ="02";
        Properties props = System.getProperties();
        if(planta.equals("05")){
            props.put("mail.smtp.host", hostVLR);
        }else if(planta.equals("02")){
            props.put("mail.smtp.host", hostLGV);
        }
                props.put("mail.smtp.port", smtpPort);

                // Get session
                Session session = Session.getDefaultInstance(props, null);

                // Define message
                MimeMessage message = new MimeMessage(session);

        try {
            //Who sends message
            message.setFrom(new InternetAddress(from));
            //Who goes the message to
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            //set the subject of the message
                message.setSubject("Requerimiento rechazado para " + nombre);
            //set content of the message in html format
            message.setContent(mensaje,"text/html");

                // Send message
               Transport.send(message);
               System.out.println("Mensaje de correo enviado");
            } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    public static void Send_correo_rechazado(String mensaje, String to, String planta){
        
        //String to ="marilys.rivas@venvidrio.com.ve";  
        //String to ="mario.ortega@venvidrio.com.ve"; 
        //String planta ="02";
        Properties props = System.getProperties();
        if(planta.equals("05")){
            props.put("mail.smtp.host", hostVLR);
        }else if(planta.equals("02")){
            props.put("mail.smtp.host", hostLGV);
        }
                props.put("mail.smtp.port", smtpPort);

                // Get session
                Session session = Session.getDefaultInstance(props, null);

                // Define message
                MimeMessage message = new MimeMessage(session);

        try {
            //Who sends message
            message.setFrom(new InternetAddress(from));
            //Who goes the message to
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            //set the subject of the message
                message.setSubject("Requerimiento rechazado");
            //set content of the message in html format
            message.setContent(mensaje,"text/html");

                // Send message
               Transport.send(message);
               System.out.println("Mensaje de correo enviado");
            } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
        
}
