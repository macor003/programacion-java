package com.venvidrio.sgt.loader;

import com.venvidrio.sgt.utility.sgtUtility;
import com.venvidrio.sgt.loader.TSgtVerificarUsuarioLoader;
        
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 *
 * @author Ortegam
 */
public class TSgtRequerimientoLoader {
    
    private static String hostLGV="maillgv.venvidrio.com.ve";
    private static String hostVLR="mailvlr.venvidrio.com.ve";
    private static String smtpPort="25";
    private static String from="atencion.soporte@venvidrio.com.ve";
    
    
    public int getAutoID(){
        Connection con;
        Statement stmt=null;
        ResultSet rs = null;
        //("voy a hacer la conexion a la SGT_BD");
        con = new sgtUtility().Conexion_Sorg();
        String ls_sql="";
        String max="";
        int cod=0;
        int id = 0;

        try {
            stmt = con.createStatement();
            ls_sql="SELECT  max(sgt_cod_requerimiento) FROM  t_sgt_requerimiento;";
            
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
    
    public void insertarRequerimiento(int cod_reque, String planta_usu, String cod_urge, String dpto_usu,
                                        String ficha_usu, String tipo_reque, String area_reque, String clasi_reque, String desc_reque, String usuario_log, String ficha_log, String planta_log){
               
        sgtUtility util = new sgtUtility();
        
        java.util.Date today=new java.util.Date();        
        Connection con =null;
        String ficha_analista="NULL";
        String cod_planta_analista="NULL";
        int status = 1;
        String log ="01";
        PreparedStatement pstmt=null;
        con=util.Conexion_Sorg();

        String sql="INSERT INTO t_sgt_requerimiento(\n" +
        "            sgt_cod_requerimiento, sgt_cod_tipo_requerimieto, sgt_desc_requerimiento, \n" +
        "            per_cod_planta_solicitante, per_ficha_solicitante, \n" +
        "            sgt_fecha_requerimiento, \n" +
        "            sgt_cod_clasificacion_area, sgt_fecha_auditoria, sgt_usuario_auditoria, sgt_cod_urgencia, sgt_cod_status, sgt_ficha_analista, sgt_cod_planta_analista, sgt_area_requerimiento)\n" +
        "    VALUES (?, ?, ?, \n" +
        "            ?, ?, ?, \n" +
        "            ?, ?, ?, ?, ?, ?, ?, ?);";
        
        
        String sql2 ="INSERT INTO t_sgt_log_requerimiento(\n" +
                    "            sgt_cod_requerimiento, sgt_fecha_registro, sgt_cod_log, sgt_ficha_analista, sgt_planta_analista, sgt_ficha_usulog, sgt_planta_usulog)\n" +
                    "    VALUES (?, ?, ?, ?, ?, ?, ?);";
        try {               
           
            pstmt=con.prepareStatement(sql);
            pstmt.setInt(1, cod_reque);
            pstmt.setString(2, tipo_reque);
            pstmt.setString(3, desc_reque);
            pstmt.setString(4, planta_usu);
            pstmt.setString(5, ficha_usu);
            pstmt.setTimestamp(6, new java.sql.Timestamp(today.getTime()));
            pstmt.setString(7, clasi_reque);
            pstmt.setTimestamp(8, new java.sql.Timestamp(today.getTime()));
            pstmt.setString(9, usuario_log);
            pstmt.setString(10, cod_urge);
            pstmt.setInt(11, status);
            pstmt.setString(12, ficha_analista);
            pstmt.setString(13, cod_planta_analista);
            pstmt.setString(14, area_reque);
            pstmt.executeUpdate();
            
            pstmt=con.prepareStatement(sql2);
            pstmt.setInt(1, cod_reque);
            pstmt.setTimestamp(2, new java.sql.Timestamp(today.getTime()));
            pstmt.setString(3, log);
            pstmt.setString(4, ficha_usu);
            pstmt.setString(5, planta_usu);
            pstmt.setString(6, ficha_log);
            pstmt.setString(7, planta_log);
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
         
    public void insertarRequerimientoOtroUsuario(int cod_reque, String planta_usu, String cod_urge, String dpto_usu,
                                        String ficha_usu, String tipo_reque, String area_reque, String clasi_reque, String desc_reque, String ficha_log, String planta_log, String usuario_log, String cod_planta_analista, String ficha_analista){
               
        sgtUtility util = new sgtUtility();
        
        TSgtPendientesLoader asignar = new TSgtPendientesLoader();
        
        java.util.Date today=new java.util.Date();
        int status =0;
                
        Connection con =null;
        if(cod_planta_analista.equals("NULL") && ficha_analista.equals("NULL")){
             status = 1;  
        }else{
             status = 2;  
        }
        
        String log = "01";
        PreparedStatement pstmt=null;
        con=util.Conexion_Sorg();

        String sql="INSERT INTO t_sgt_requerimiento(\n" +
        "            sgt_cod_requerimiento, sgt_cod_tipo_requerimieto, sgt_desc_requerimiento, \n" +
        "            per_cod_planta_solicitante, per_ficha_solicitante, \n" +
        "            sgt_fecha_requerimiento, \n" +
        "            sgt_cod_clasificacion_area, sgt_fecha_auditoria, sgt_usuario_auditoria, sgt_cod_urgencia, sgt_cod_status, sgt_ficha_analista, sgt_cod_planta_analista, sgt_area_requerimiento)\n" +
        "    VALUES (?, ?, ?, \n" +
        "            ?, ?, ?, \n" +
        "            ?, ?, ?, ?, ?, ?, ?, ?);";
        
        String sql2 ="INSERT INTO t_sgt_log_requerimiento(\n" +
                    "            sgt_cod_requerimiento, sgt_fecha_registro, sgt_cod_log, sgt_ficha_analista, sgt_planta_analista, sgt_ficha_usulog, sgt_planta_usulog)\n" +
                    "    VALUES (?, ?, ?, ?, ?, ?, ?);";
        try {               
           
            pstmt=con.prepareStatement(sql);
            pstmt.setInt(1, cod_reque);
            pstmt.setString(2, tipo_reque);
            pstmt.setString(3, desc_reque);
            pstmt.setString(4, planta_usu);
            pstmt.setString(5, ficha_usu);
            pstmt.setTimestamp(6, new java.sql.Timestamp(today.getTime()));
            pstmt.setString(7, clasi_reque);
            pstmt.setTimestamp(8, new java.sql.Timestamp(today.getTime()));
            pstmt.setString(9, usuario_log);
            pstmt.setString(10, cod_urge);
            pstmt.setInt(11, status);
            pstmt.setString(12, ficha_analista);
            pstmt.setString(13, cod_planta_analista);
            pstmt.setString(14, area_reque);
            pstmt.executeUpdate();
            
            
            pstmt=con.prepareStatement(sql2);
            pstmt.setInt(1, cod_reque);
            pstmt.setTimestamp(2, new java.sql.Timestamp(today.getTime()));
            pstmt.setString(3, log);
            pstmt.setString(4, ficha_usu);
            pstmt.setString(5, planta_usu);
            pstmt.setString(6, ficha_log);
            pstmt.setString(7, planta_log);
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
        
        asignar.CorreoRequerimientoAsignado(cod_reque, ficha_analista, ficha_log, cod_planta_analista, planta_log);

   }

    public void insertarRequerimiento(String cod_reque, String planta_usu, String dpto_usu, String ficha_usu, String tipo_reque, String area_reque, String clasi_reque, String desc_reque) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void getCedulaPersonal(String ficha, String planta_usu) {
        System.out.println("---------------------------------------");
        System.out.println("VOY A BUSCAR LA CEDULA DEL SOLICITANTE");
        sgtUtility util = new sgtUtility();
        
        Connection con= util.Conexion_Sorg();
        Connection con_jde = util.Conexion_jd();
        Statement stmt=null;
        ResultSet rs=null;
        String cedula ="";        
        try{
            
        //Sql para saber las fichas y los correos de los Responsables de Indicadores
        String sql="SELECT \n" +
                    "  per_nombre,\n" +
                    "  per_cedula\n" +
                    "FROM \n" +
                    "  vv_personal, \n" +
                    "  vv_planta\n" +
                    "WHERE \n" +
                    "  per_cod_planta = vv_planta.pla_cod_planta AND\n" +
                    "  per_ficha = '"+ficha+"' AND \n" +
                    "  per_cod_planta = '"+planta_usu+"';";
        
        stmt=con.createStatement();
        rs=stmt.executeQuery(sql);
        while(rs.next()){
            
           cedula =rs.getString("per_cedula");
            System.out.println("Cedula del usuario que creo el ticket: " +cedula);
            getCorreoPersonal(cedula);
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
    
    public void getCorreoPersonal(String cedula) {
        System.out.println("---------------------------------------");
        System.out.println("VOY A BUSCAR EL CORREO DEL SOLICITANTE");
        sgtUtility util = new sgtUtility();
                
        Connection con = util.Conexion_jd();
        Statement stmt=null;
        ResultSet rs=null;        
        try{
            
        //Sql para saber el correo del solicitante. 
        String sql="SELECT ABAN8, WWREM1 FROM F0111 INNER JOIN F0101 ON F0101.ABAN8 = F0111.WWAN8 AND ABTAX='"+cedula+"' AND WWIDLN='0' AND TRIM(WWREM1)!=''";
        
        stmt=con.createStatement();
        rs=stmt.executeQuery(sql);
        while(rs.next()){
            
           String correo =rs.getString("per_cedula");
            System.out.println("Cedula del usuario que creo el ticket: " +cedula);
            
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
    
    
    public void CorreoRequerimientoCreado(int cod_reque, String desc_reque, String ficha, String planta_usu) {
        
        sgtUtility util = new sgtUtility();
        TSgtVerificarUsuarioLoader ver = new TSgtVerificarUsuarioLoader(); 
        
        Connection con= util.Conexion_Sorg();
        Statement stmt=null;
        ResultSet rs=null;
        String nombre="";
        String clasi ="";
        String to = ver.VerificarCorreo(planta_usu, ficha);
        TSgtPendientesLoader get = new TSgtPendientesLoader();
        String cod = Integer.toString(cod_reque);
        String elaborado_por = get.verPersonalCreador(cod);
        
        try{
         
//            //Sql para saber la clasificacion del requerimieto
//            String sql_clasi="SELECT \n" +
//                            "  sgt_desc_clasificacion\n" +
//                            "FROM \n" +
//                            "  t_sgt_clasificacion\n" +
//                            "WHERE \n" +
//                            "  sgt_cod_clasificacion = '"+clasi_reque+"';";
//            stmt = con.createStatement();
//            rs = stmt.executeQuery(sql_clasi);
//            while(rs.next()){
//                clasi = rs.getString("sgt_desc_clasificacion");
//            }
                       
        //Sql para saber las fichas y los correos de los Responsables de Indicadores
        String sql="SELECT \n" +
                    "  per_nombre\n" +
                    "FROM \n" +
                    "  vv_personal, \n" +
                    "  vv_planta\n" +
                    "WHERE \n" +
                    "  per_cod_planta = vv_planta.pla_cod_planta AND\n" +
                    "  (per_ficha = '"+ficha+"' OR per_cedula='"+ficha+"') AND \n" +
                    "  per_cod_planta = '"+planta_usu+"';";
        
        stmt=con.createStatement();
        rs=stmt.executeQuery(sql);
        while(rs.next()){
            
            nombre=rs.getString("per_nombre");
            String mensaje ="<!DOCTYPE HTML>\n" +
                            "<html>\n" +
                            "<head>\n" +
                            "<title></title>\n" +
                            "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\n" +
                            "</head>\n" +
                            "<body style=\"margin-left: 50px; margin-top: 50px;\">\n" +
                            "    <div class=\"triangulo_top_left\" style=\"width: 0; height: 0; border-top: 30px solid #fff; border-right: 38px solid transparent; position: absolute;  \"></div>\n" +
                            "  <div class=\"triangulo_bottom_right\" style=\"width: 0; height: 0; border-bottom: 30px solid #000; border-left: 38px solid transparent; position: absolute; opacity: 0.5;\"></div>\n" +
                            "  <div id=\"encabezado\" style=\"width:460px;height: 45px;text-align:center;font-weight: 300;border: 1px solid #8A0808;color: white;background-color: #8A0808;\">\n" +
                            "      <h2 style=\"margin-top:10px;font: bold 20px sans-serif;font-weight: 400;letter-spacing: 1px;\">Ticket Requerimiento</h2>\n" +
                            "  </div>\n" +
                            "  <table id=\"detalles\" style=\"width: 462px;height: 100px;border: 1px solid #8A0808;background-color:white;padding-bottom: 20px;\">\n" +
                            "    <tr>\n" +
                            "        <td id=\"titulo\" style=\"padding-top: 20px;text-align: left; font: bold 13px sans-serif; width: 100px;padding-left: 10px;\">C&oacute;digo:</td>\n" +
                            "        <td id=\"detalleReque\" style=\" padding-top: 20px;font-size: 14px; text-align:left; font-family: sans-serif;\">"+cod_reque+"</td>\n" +
                            "    </tr>\n" +
                            "    <tr>\n" +
                            "        <td id=\"titulo\" style=\"padding-top: 20px;text-align: left; font: bold 13px sans-serif; width: 100px;padding-left: 10px;\">A Nombre de:</td>\n" +
                            "        <td id=\"detalleReque\" style=\"padding-top: 20px;font-size: 14px; text-align:left; font-family: sans-serif;\">"+nombre+"</td>\n" +
                            "    </tr>\n" +
                            "    <tr>\n" +
                            "        <td id=\"titulo\" style=\"padding-top: 20px;text-align: left; font: bold 13px sans-serif; width: 100px;padding-left: 10px;\">Elaborado por:</td>\n" +
                            "        <td id=\"detalleReque\" style=\"padding-top: 20px;font-size: 14px; text-align:left; font-family: sans-serif;\">"+elaborado_por+"</td>\n" +
                            "    </tr>\n" + 
                            "    <tr>\n" +
                            "        <td id=\"titulo\" style=\"padding-top: 20px;text-align: left; font: bold 13px sans-serif; width: 100px;padding-left: 10px;\">Descripci&oacute;n:</td>\n" +
                            "        <td id=\"detalleReque\" style=\"padding-top: 20px;font-size: 14px; text-align:left; font-family: sans-serif;\">"+desc_reque+"</td>\n" +
                            "    </tr>\n" +
                            "  </table>\n" +
                            "  <a href='http://10.183.9.20:8080/sgt_website/index.jsp'><input style=\"width: 462px; height: 45px; background-color: #8A0808; border: 1px solid #8A0808;  color: white;\" type=\"submit\" value=\"Iniciar Sesi&oacute;n para Aprobar &oacute; Rechazar\"></a>\n" +
                            "  \n" +
                            "</body>\n" +
                            "</html>";
         Send_correo_nuevo_requerimiento(mensaje, to, planta_usu, nombre);
         Send_correo_nuevo_requerimiento_cau(mensaje, planta_usu, nombre);
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
    
    public void CorreoRequerimientoAprobar(int cod_reque, String desc_reque,String ficha, String planta_usu, String cod_urge) {
        
        sgtUtility util = new sgtUtility();
        TSgtVerificarUsuarioLoader ver = new TSgtVerificarUsuarioLoader(); 
        Connection con= util.Conexion_Sorg();
        Statement stmt=null;
        ResultSet rs=null;
        String nombre="";
        String urgencia ="";
        String clasi="";
        String ficha_responsable="";
        String planta_responsable="";
        TSgtPendientesLoader get = new TSgtPendientesLoader();
        String cod = Integer.toString(cod_reque);
        String elaborado_por = get.verPersonalCreador(cod);
        
        
        try{
            //Sql para saber la ficha del responsable
            String sql_ficha_resp="SELECT \n" +
                                    "  per_ficrep, \n" +
                                    "  per_nombre, \n" +
                                    "  dep_desc_dpto\n" +
                                    "FROM \n" +
                                    "  vv_personal, \n" +
                                    "  vv_planta, \n" +
                                    "  vv_departamento\n" +
                                    "WHERE \n" +
                                    "  vv_personal.per_cod_planta = vv_planta.pla_cod_planta AND\n" +
                                    "  vv_personal.per_depart = vv_departamento.dep_cod_dpto AND\n" +
                                    "  vv_departamento.dep_cod_planta = vv_planta.pla_cod_planta AND\n" +
                                    "  vv_personal.per_ficha = '"+ficha+"' AND \n" +
                                    "  vv_personal.per_cod_planta = '"+planta_usu+"';";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql_ficha_resp);
            while(rs.next()){
                ficha_responsable = rs.getString("per_ficrep");
            }
            
            
            //Sql para saber la planta del responsable
            String sql_planta_resp="SELECT \n" +
                                "  per_cod_planta, \n" +
                                "  per_ficha\n" +
                                "FROM \n" +
                                "  public.vv_personal\n" +
                                "WHERE \n" +
                                "per_ficha='"+ficha_responsable+"';";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql_planta_resp);
            while(rs.next()){
                planta_responsable = rs.getString("per_cod_planta");
            }
            
            //Sql para saber la urgencia del requerimiento
            String sql_urgencia="SELECT \n" +
                                "  sgt_desc_urgencia\n" +
                                "FROM \n" +
                                "  t_sgt_urgencia\n" +
                                "WHERE \n" +
                                "  sgt_cod_urgencia='"+cod_urge+"';";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql_urgencia);
            while(rs.next()){
                urgencia = rs.getString("sgt_desc_urgencia");
            }
            
            //Sql para saber la clasificacion del requerimieto
//            String sql_clasi="SELECT \n" +
//                            "  sgt_desc_clasificacion\n" +
//                            "FROM \n" +
//                            "  t_sgt_clasificacion\n" +
//                            "WHERE \n" +
//                            "  sgt_cod_clasificacion = '"+clasi_reque+"';";
//            stmt = con.createStatement();
//            rs = stmt.executeQuery(sql_clasi);
//            while(rs.next()){
//                clasi = rs.getString("sgt_desc_clasificacion");
//            }
                       
        //Sql para saber nombre del creador del ticket
        String sql="SELECT \n" +
                    "  per_nombre\n" +
                    "FROM \n" +
                    "  vv_personal, \n" +
                    "  vv_planta\n" +
                    "WHERE \n" +
                    "  per_cod_planta = vv_planta.pla_cod_planta AND\n" +
                    "  per_ficha = '"+ficha+"' AND \n" +
                    "  per_cod_planta = '"+planta_usu+"';";
        
        stmt=con.createStatement();
        rs=stmt.executeQuery(sql);
        while(rs.next()){
            
            nombre=rs.getString("per_nombre");
            String mensaje ="<!DOCTYPE HTML>\n" +
                            "<html>\n" +
                            "<head>\n" +
                            "<title></title>\n" +
                            "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\n" +
                            "</head>\n" +
                            "<body style=\"margin-left: 50px; margin-top: 50px;\">\n" +
                            "    <div class=\"triangulo_top_left\" style=\"width: 0; height: 0; border-top: 30px solid #fff; border-right: 38px solid transparent; position: absolute;  \"></div>\n" +
                            "  <div class=\"triangulo_bottom_right\" style=\"width: 0; height: 0; border-bottom: 30px solid #000; border-left: 38px solid transparent; position: absolute; opacity: 0.5;\"></div>\n" +
                            "  <div id=\"encabezado\" style=\"width:460px;height: 45px;text-align:center;font-weight: 300;border: 1px solid #8A0808;color: white;background-color: #8A0808;\">\n" +
                            "      <h2 style=\"margin-top:10px;font: bold 20px sans-serif;font-weight: 400;letter-spacing: 1px;\">Ticket Requerimiento</h2>\n" +
                            "  </div>\n" +
                            "  <table id=\"detalles\" style=\"width: 462px;height: 100px;border: 1px solid #8A0808;background-color:white;padding-bottom: 20px;\">\n" +
                            "    <tr>\n" +
                            "        <td id=\"titulo\" style=\"padding-top: 20px;text-align: left; font: bold 13px sans-serif; width: 100px;padding-left: 10px;\">C&oacute;digo:</td>\n" +
                            "        <td id=\"detalleReque\" style=\" padding-top: 20px;font-size: 14px; text-align:left; font-family: sans-serif;\">"+cod_reque+"</td>\n" +
                            "    </tr>\n" +
                            "    <tr>\n" +
                            "        <td id=\"titulo\" style=\"padding-top: 20px;text-align: left; font: bold 13px sans-serif; width: 100px;padding-left: 10px;\">A Nombre de:</td>\n" +
                            "        <td id=\"detalleReque\" style=\"padding-top: 20px;font-size: 14px; text-align:left; font-family: sans-serif;\">"+nombre+"</td>\n" +
                            "    </tr>\n" +
                            "    <tr>\n" +
                            "        <td id=\"titulo\" style=\"padding-top: 20px;text-align: left; font: bold 13px sans-serif; width: 100px;padding-left: 10px;\">Elaborado por:</td>\n" +
                            "        <td id=\"detalleReque\" style=\"padding-top: 20px;font-size: 14px; text-align:left; font-family: sans-serif;\">"+elaborado_por+"</td>\n" +
                            "    </tr>\n" +                     
                            "    <tr>\n" +
                            "    <tr>\n" +
                            "        <td id=\"titulo\" style=\"padding-top: 20px;text-align: left; font: bold 13px sans-serif; width: 100px;padding-left: 10px;\">Descripci&oacute;n:</td>\n" +
                            "        <td id=\"detalleReque\" style=\"padding-top: 20px;font-size: 14px; text-align:left; font-family: sans-serif;\">"+desc_reque+"</td>\n" +
                            "    </tr>\n" +
                            "    <tr>\n" +
                            "        <td id=\"titulo\" style=\"padding-top: 20px;text-align: left; font: bold 13px sans-serif; width: 100px;padding-left: 10px;\">Urgencia:</td>\n" +
                            "        <td id=\"detalleReque\" style=\"padding-top: 20px;font-size: 14px; text-align:left; font-family: sans-serif;\">"+urgencia+"</td>\n" +
                            "    </tr>\n" +
                            "  </table>\n" +
                            "  <a href='http://10.183.9.20:8080/sgt_website/index.jsp'><input style=\"width: 462px; height: 45px; background-color: #8A0808; border: 1px solid #8A0808;  color: white;\" type=\"submit\" value=\"Iniciar Sesi&oacute;n para Aprobar &oacute; Rechazar\"></a>\n" +
                            "  \n" +
                            "</body>\n" +
                            "</html>";  
            
            
            String to = ver.VerificarCorreo(planta_responsable, ficha_responsable);
            Send_correo_nuevo_requerimiento_aprobar(mensaje, to, planta_usu, nombre);
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
    
    public void ReenviarCorreoRequerimientoAprobar(int cod_reque) {
        
        sgtUtility util = new sgtUtility();
        TSgtVerificarUsuarioLoader ver = new TSgtVerificarUsuarioLoader(); 
        Connection con= util.Conexion_Sorg();
        Statement stmt=null;
        ResultSet rs=null;
        String nombre="";
        String urgencia ="";
        String clasi="";
        String desc_reque ="";
        String ficha_responsable="";
        String planta_responsable="";
        
        
        try{
           
            //Sql para saber nombre clasificacion urgencia descripcion y responsable del requerimieto
            String sql_clasi="SELECT \n" +
                                "  per_nombre, \n" +
                                "  sgt_desc_clasificacion, \n" +
                                "  sgt_desc_urgencia, \n" +
                                "  sgt_desc_requerimiento, \n" +
                                "  per_ficrep\n" +
                                "FROM \n" +
                                "  public.t_sgt_requerimiento, \n" +
                                "  public.vv_personal, \n" +
                                "  public.t_sgt_clasificacion, \n" +
                                "  public.t_sgt_urgencia\n" +
                                "WHERE \n" +
                                "  t_sgt_requerimiento.per_cod_planta_solicitante = vv_personal.per_cod_planta AND\n" +
                                "  t_sgt_requerimiento.per_ficha_solicitante = vv_personal.per_ficha AND\n" +
                                "  t_sgt_requerimiento.sgt_cod_clasificacion_area = t_sgt_clasificacion.sgt_cod_clasificacion AND\n" +
                                "  t_sgt_requerimiento.sgt_cod_urgencia = t_sgt_urgencia.sgt_cod_urgencia AND\n" +
                                "  sgt_cod_requerimiento ='"+cod_reque+"';";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql_clasi);
            while(rs.next()){
                nombre=rs.getString("per_nombre");
                clasi = rs.getString("sgt_desc_clasificacion");
                desc_reque =rs.getString("sgt_desc_requerimiento");
                ficha_responsable =rs.getString("per_ficrep");
                urgencia =rs.getString("sgt_desc_urgencia");
                
            }
                       
        //Sql para saber nombre del creador del ticket
        String sql="SELECT \n" +
                                "  per_cod_planta, \n" +
                                "  per_ficha\n" +
                                "FROM \n" +
                                "  public.vv_personal\n" +
                                "WHERE \n" +
                                "per_ficha='"+ficha_responsable+"';";
        
        stmt=con.createStatement();
        rs=stmt.executeQuery(sql);
        while(rs.next()){
            planta_responsable = rs.getString("per_cod_planta");
            String mensaje ="<!DOCTYPE HTML>\n" +
                            "<html>\n" +
                            "<head>\n" +
                            "<title></title>\n" +
                            "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\n" +
                            "</head>\n" +
                            "<body style=\"margin-left: 50px; margin-top: 50px;\">\n" +
                            "    <div class=\"triangulo_top_left\" style=\"width: 0; height: 0; border-top: 30px solid #fff; border-right: 38px solid transparent; position: absolute;  \"></div>\n" +
                            "  <div class=\"triangulo_bottom_right\" style=\"width: 0; height: 0; border-bottom: 30px solid #000; border-left: 38px solid transparent; position: absolute; opacity: 0.5;\"></div>\n" +
                            "  <div id=\"encabezado\" style=\"width:460px;height: 45px;text-align:center;font-weight: 300;border: 1px solid #8A0808;color: white;background-color: #8A0808;\">\n" +
                            "      <h2 style=\"margin-top:10px;font: bold 20px sans-serif;font-weight: 400;letter-spacing: 1px;\">Ticket Requerimiento</h2>\n" +
                            "  </div>\n" +
                            "  <table id=\"detalles\" style=\"width: 462px;height: 100px;border: 1px solid #8A0808;background-color:white;padding-bottom: 20px;\">\n" +
                            "    <tr>\n" +
                            "        <td id=\"titulo\" style=\"padding-top: 20px;text-align: left; font: bold 13px sans-serif; width: 100px;padding-left: 10px;\">C&oacute;digo:</td>\n" +
                            "        <td id=\"detalleReque\" style=\" padding-top: 20px;font-size: 14px; text-align:left; font-family: sans-serif;\">"+cod_reque+"</td>\n" +
                            "    </tr>\n" +
                            "    <tr>\n" +
                            "        <td id=\"titulo\" style=\"padding-top: 20px;text-align: left; font: bold 13px sans-serif; width: 100px;padding-left: 10px;\">A Nombre de:</td>\n" +
                            "        <td id=\"detalleReque\" style=\"padding-top: 20px;font-size: 14px; text-align:left; font-family: sans-serif;\">"+nombre+"</td>\n" +
                            "    </tr>\n" +
                            "    <tr>\n" +
                            "        <td id=\"titulo\" style=\"padding-top: 20px;text-align: left; font: bold 13px sans-serif; width: 100px;padding-left: 10px;\">Clasificaci&oacute;n:</td>\n" +
                            "        <td id=\"detalleReque\" style=\"padding-top: 20px;font-size: 14px; text-align:left; font-family: sans-serif;\">"+clasi+"</td>\n" +
                            "    </tr>\n" +
                            "    <tr>\n" +
                            "        <td id=\"titulo\" style=\"padding-top: 20px;text-align: left; font: bold 13px sans-serif; width: 100px;padding-left: 10px;\">Descripci&oacute;n:</td>\n" +
                            "        <td id=\"detalleReque\" style=\"padding-top: 20px;font-size: 14px; text-align:left; font-family: sans-serif;\">"+desc_reque+"</td>\n" +
                            "    </tr>\n" +
                            "    <tr>\n" +
                            "        <td id=\"titulo\" style=\"padding-top: 20px;text-align: left; font: bold 13px sans-serif; width: 100px;padding-left: 10px;\">Urgencia:</td>\n" +
                            "        <td id=\"detalleReque\" style=\"padding-top: 20px;font-size: 14px; text-align:left; font-family: sans-serif;\">"+urgencia+"</td>\n" +
                            "    </tr>\n" +
                            "  </table>\n" +
                            "  <a href='http://10.183.9.20:8080/sgt_website/index.jsp'><input style=\"width: 462px; height: 45px; background-color: #8A0808; border: 1px solid #8A0808;  color: white;\" type=\"submit\" value=\"Iniciar Sesi&oacute;n para Aprobar &oacute; Rechazar\"></a>\n" +
                            "  \n" +
                            "</body>\n" +
                            "</html>";  
            
            
            String to = ver.VerificarCorreo(planta_responsable, ficha_responsable);
//            String to ="mario.ortega@venvidrio.com.ve"; 
            Send_correo_nuevo_requerimiento_aprobar(mensaje, to, planta_responsable, nombre);
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
    
    public static void Send_correo_nuevo_requerimiento_cau(String mensaje, String planta, String nombre){
        
        //String to ="marilys.rivas@venvidrio.com.ve";  
        String to ="atencion.soporte@venvidrio.com.ve"; 
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
                message.setSubject("Nuevo Requerimiento a Nombre de: " +nombre);
            //set content of the message in html format
            message.setContent(mensaje,"text/html");

                // Send message
               Transport.send(message);
               System.out.println("Mensaje de correo enviado");
            } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    public static void Send_correo_nuevo_requerimiento(String mensaje, String to, String planta, String nombre){
        
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
                message.setSubject("Nuevo requerimiento");
            //set content of the message in html format
            message.setContent(mensaje,"text/html");

                // Send message
               Transport.send(message);
               System.out.println("Mensaje de correo enviado");
            } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    public static void Send_correo_nuevo_requerimiento_aprobar(String mensaje, String to, String planta, String nombre){
        
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
                message.setSubject("Aprobacion o rechazo para la solicitud de: "+nombre);
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
