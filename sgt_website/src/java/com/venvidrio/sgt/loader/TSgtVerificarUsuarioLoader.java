/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venvidrio.sgt.loader;

import com.venvidrio.sgt.utility.sgtUtility;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
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
public class TSgtVerificarUsuarioLoader {
    
    private static String hostLGV="maillgv.venvidrio.com.ve";
    private static String hostVLR="mailvlr.venvidrio.com.ve";
    private static String smtpPort="25";
    private static String from="atencion.soporte@venvidrio.com.ve";
    
    public String obtenerNuevoUsuario(){
        Connection con;
        Statement stmt=null;
        ResultSet rs = null;
        //("voy a hacer la conexion a la SGT_BD");
        con = new sgtUtility().Conexion_Sorg();
        String ls_sql="";
        String max="";
        int cod=0;
        int id= 0;

        try {
            stmt = con.createStatement();
            ls_sql="select max(cod_consecutivo) from t_sgt_consecutivos;";
            
            rs=stmt.executeQuery(ls_sql);            
            if(rs.next()){
                //("Estoy en rs.next()");
               max=rs.getString(1);
//               System.out.println("SQL DE CODIGO REQUERIMIENTO: " + ls_sql);
//               System.out.println("VALOR DEL MAX: " + max);
            }
            if(max!=null){
            cod= Integer.parseInt(max)+1;
            }
            System.out.println("CODIGO DEL MAX: " + cod);
            if(cod==0){
                id =1;
                System.out.println("USUARIO NUEVO: VGSIT" + id);
            }else if( cod >= 1){                 
                 id = cod;
                 System.out.println("CODIGO >1: " + id);                 
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
        String consecutivo = Integer.toString(id);
        
        return consecutivo;
    }
    
    public void insertarConsecutivo(String consecutivo){

    sgtUtility util = new sgtUtility();
    java.util.Date today=new java.util.Date(); 
    Connection con =null;
    PreparedStatement pstmt=null;
    con=util.Conexion_Sorg();
    
    int numero = Integer.parseInt(consecutivo);
    
    String sql ="INSERT INTO t_sgt_consecutivos(\n" +
                "            cod_consecutivo, con_fecha_registro)\n" +
                "    VALUES (?, ?);";

    
    try {
        System.out.println("-----GUARDANDO CONSECUTIVO----- ");
        pstmt= con.prepareStatement(sql);
        pstmt.setInt(1, numero);
        pstmt.setTimestamp(2, new java.sql.Timestamp(today.getTime()));
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
    
    public String VerficarUsuario(String cod_planta, String ficha_personal){
    sgtUtility util = new sgtUtility();
    Connection con = null;
    Statement stm=null;
    ResultSet rs=null;
    String retorno="";
    
    
        try {
            String sql = "SELECT   \n" +
                        "  uco_cod_planta, \n" +
                        "  uco_ficha_emp,\n" +
                        "  uco_cod_usuario, \n" +
                        "  uco_contrasena, \n" +
                        "  uco_ruta, \n" +
                        "  uco_seguridad_status, \n" +
                        "  uco_cuenta_correo, \n" +
                        "  uco_nb_bd_notes, \n" +
                        "  uco_ruta_alternativa, \n" +
                        "  uco_ruta_requisicion, \n" +
                        "  uco_fecha_caducidad, \n" +
                        "  uco_status_caducidad, \n" +
                        "  uco_dias_caducidad, \n" +
                        "  uco_ruta_publicacion\n" +
                        "FROM \n" +
                        "  vv_usuario_contrasena\n" +
                        "WHERE \n" +
                        "  vv_usuario_contrasena.uco_cod_planta = '"+cod_planta+"' AND \n" +
                        "  vv_usuario_contrasena.uco_ficha_emp = '"+ficha_personal+"' AND "+
                        "  (vv_usuario_contrasena.uco_cod_usuario like '%VGSIT%' OR  vv_usuario_contrasena.uco_cod_usuario like '%VVSIT%') AND \n" +
                        "  vv_usuario_contrasena.uco_seguridad_status = '0';";
            con = util.Conexion_Sorg();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while(rs.next()){
                String planta=rs.getString("uco_cod_planta");
                String ficha = rs.getString("uco_ficha_emp");
                String usuario = rs.getString("uco_cod_usuario");
                String pass = rs.getString("uco_contrasena");
                String ruta = rs.getString("uco_ruta");
                String seguridad = rs.getString("uco_seguridad_status");
                String correo = rs.getString("uco_cuenta_correo");
                String notes = rs.getString("uco_nb_bd_notes");
                String alternativa = rs.getString("uco_ruta_alternativa");
                String requisicion = rs.getString("uco_ruta_requisicion");
                String fecha_cadu = rs.getString("uco_fecha_caducidad");
                String status_cadu  = rs.getString("uco_status_caducidad");
                String dias_cadu = rs.getString("uco_dias_caducidad");
                String ruta_publi = rs.getString("uco_ruta_publicacion");
                
                retorno+=usuario;
                System.out.println("usuario= "+ usuario);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stm != null) stm.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                ////("VSreqUsuarioContrasenaManager::viewUsuarioAplic(): SQLException " + e.toString());

            }
        }
    
    return retorno;
    }
    
    public String VerificarCorreo(String cod_planta, String ficha_personal){
    sgtUtility util = new sgtUtility();
    Connection con = null;
    Connection con2 = null;   
    Statement stm=null;
    Statement stm2 = null;
    ResultSet rs=null;
    ResultSet rs2 = null;
    String retorno="";
    String cedula="";
    
        try {
            String sql = "SELECT \n" +
                        "  per_cedula, \n" +
                        "  per_nombre\n" +
                        "FROM \n" +
                        "  public.vv_personal\n" +
                        "WHERE \n" +
                        "  per_cod_planta = '"+cod_planta+"' AND \n" +
                        "  (per_ficha = '"+ficha_personal+"' OR per_cedula='"+ficha_personal+"');";
            con = util.Conexion_Sorg();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while(rs.next()){
                cedula=rs.getString("per_cedula");     
                System.out.println("CEDULA= "+ cedula);
            }
            
            String sql2 = "SELECT ABAN8, WWREM1 FROM F0111 INNER JOIN F0101 ON F0101.ABAN8 = F0111.WWAN8 AND ABTAX='"+cedula+"' AND WWREM1 LIKE ('%venvidrio.com.ve%') AND WWIDLN='0' AND TRIM(WWREM1)!=''";
            con2 = util.Conexion_jd();
            stm2 = con2.createStatement();
            rs2 = stm2.executeQuery(sql2);
            if(rs2.next()){
                String correo=rs2.getString("WWREM1");            
                System.out.println("CORREO= "+ correo);
                retorno+=correo;
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stm != null) stm.close();
                if (con != null) con.close();
                if (rs2 != null) rs2.close();
                if (stm2 != null) stm2.close();
                if (con2 != null) con2.close();
            } catch (SQLException e) {
                ////("VSreqUsuarioContrasenaManager::viewUsuarioAplic(): SQLException " + e.toString());

            }
        }
    
    return retorno;
    }
    
    public String verDatosUsuario(String cod_planta, String ficha_personal){
    sgtUtility util = new sgtUtility();
    Connection con = null;
    Statement stm=null;
    ResultSet rs=null;
    String retorno="";
    
    
        try {
            String sql = "SELECT   \n" +
                        "  uco_cod_planta, \n" +
                        "  uco_ficha_emp,\n" +
                        "  uco_cod_usuario, \n" +
                        "  uco_contrasena, \n" +
                        "  uco_ruta, \n" +
                        "  uco_seguridad_status, \n" +
                        "  uco_cuenta_correo, \n" +
                        "  uco_nb_bd_notes, \n" +
                        "  uco_ruta_alternativa, \n" +
                        "  uco_ruta_requisicion, \n" +
                        "  uco_fecha_caducidad, \n" +
                        "  uco_status_caducidad, \n" +
                        "  uco_dias_caducidad, \n" +
                        "  uco_ruta_publicacion\n" +
                        "FROM \n" +
                        "  vv_usuario_contrasena\n" +
                        "WHERE \n" +
                        "  vv_usuario_contrasena.uco_cod_planta = '"+cod_planta+"' AND \n" +
                        "  vv_usuario_contrasena.uco_ficha_emp = '"+ficha_personal+"' AND \n" +
                        "  vv_usuario_contrasena.uco_seguridad_status = '0';";
            con = util.Conexion_Sorg();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while(rs.next()){
                String planta=rs.getString("uco_cod_planta");
                String ficha = rs.getString("uco_ficha_emp");
                String usuario = rs.getString("uco_cod_usuario");
                String pass = rs.getString("uco_contrasena");
                String ruta = rs.getString("uco_ruta");
                String seguridad = rs.getString("uco_seguridad_status");
                String correo = rs.getString("uco_cuenta_correo");
                String notes = rs.getString("uco_nb_bd_notes");
                String alternativa = rs.getString("uco_ruta_alternativa");
                String requisicion = rs.getString("uco_ruta_requisicion");
                String fecha_cadu = rs.getString("uco_fecha_caducidad");
                String status_cadu  = rs.getString("uco_status_caducidad");
                String dias_cadu = rs.getString("uco_dias_caducidad");
                String ruta_publi = rs.getString("uco_ruta_publicacion");
                
                retorno+="<strong>"+usuario+"</strong>";
                System.out.println("usuario= "+ usuario);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stm != null) stm.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                ////("VSreqUsuarioContrasenaManager::viewUsuarioAplic(): SQLException " + e.toString());

            }
        }
    
    return retorno;
    }
    
    public String verUsuario(String cod_planta, String ficha_personal){
    sgtUtility util = new sgtUtility();
    Connection con = null;
    Statement stm=null;
    ResultSet rs=null;
    String retorno="";    
    String correo = VerificarCorreo(cod_planta, ficha_personal);
    retorno+="<table>";
                
        try {
            String sql = "SELECT \n" +
                        "  pla_nom_planta, \n" +
                        "  per_nombre, \n" +
                        "  dep_desc_dpto, \n" +
                        "  per_ficha\n" +
                        "FROM \n" +
                        "  public.vv_personal, \n" +
                        "  public.vv_planta, \n" +
                        "  public.vv_departamento\n" +
                        "WHERE \n" +
                        "  vv_personal.per_cod_planta = vv_planta.pla_cod_planta AND\n" +
                        "  vv_personal.per_depart = vv_departamento.dep_cod_dpto AND\n" +
                        "  vv_departamento.dep_cod_planta = vv_planta.pla_cod_planta AND\n" +
                        "  (per_ficha='"+ficha_personal+"' OR per_cedula='"+ficha_personal+"') AND\n" +
                        "  per_cod_planta='"+cod_planta+"';";
            con = util.Conexion_Sorg();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while(rs.next()){
                String planta=rs.getString("pla_nom_planta");
                String nombre = rs.getString("per_nombre");
                String dpto = rs.getString("dep_desc_dpto");
                String ficha = rs.getString("per_ficha");
                
                
                retorno+="<tr >";
                retorno+="<td style='padding: 5px;  text-align: right; '><strong>Planta:</strong></td>";
                retorno+="<td style='padding: 5px; text-align: justify; color: #610B0B;'>"+planta+"<input type='hidden' name='lm_planta' value='"+cod_planta+"'></td>";
                retorno+="</tr>";
                retorno+="<tr>";
                retorno+="<td style='padding: 5px;  text-align: right;'><strong>Nombre:</strong></td>";
                retorno+="<td style='padding: 5px; text-align: justify; color: #610B0B;'>"+nombre+"<input type='hidden' name='lm_nombre' value='"+nombre+"'></td>";
                retorno+="</tr>";
                retorno+="<tr>";
                retorno+="<td style='padding: 5px;  text-align: right;'><strong>Departamento:</strong></td>";
                retorno+="<td style='padding: 5px; text-align: justify; color: #610B0B;'>"+dpto+"<input type='hidden' name='lm_dpto' value='"+dpto+"'></td>";
                retorno+="</tr>";
                retorno+="<tr>";
                retorno+="<td style='padding: 5px;  text-align: right;'><strong>Ficha:</strong></td>";
                retorno+="<td style='padding: 5px; text-align: justify; color: #610B0B;'>"+ficha+"<input type='hidden' name='lm_ficha' value='"+ficha_personal+"'></td>";
                retorno+="</tr>";
                retorno+="<tr>";
                retorno+="<td style='padding: 5px;  text-align: right;' valign='top'><strong>Correo:</strong></td>";
                retorno+="<td style='padding: 5px; text-align: justify; color: #610B0B;'>";
                        if(correo.equals("")){
                            String cau = "atencion.soporte@venvidrio.com.ve";
                            retorno+="No tienes correo, al registrarte llama al <strong>2073</strong><input type='hidden' name='lm_correo' value='"+cau+"'>";
                            }else{
                            retorno+=correo+"<input type='hidden' name='lm_correo' value='"+correo+"'><br><label style='color: black;'>¿Ese es tu correo?<br></label><input type='radio' value='si' name='lm_si_no' checked><label style='color: black;'>Sí</label><br><input type='radio' value='no' name='lm_si_no'><label style='color: black;'>No</label>";
                            }                        
                        retorno+="</td>";
                retorno+="</tr>";                
                retorno+="<tr>";
                retorno+="<td style='padding: 5px;  text-align: right;'><strong>Contraseña:</strong></td>";
                retorno+="<td style='padding: 5px;'><input style='width: 140px; height: 25px; padding-left:10px;' type='password' id='lm_contrasena'  maxlength='9' name='lm_contrasena' placeholder='Contraseña'></td>";
                retorno+="</tr>";
                retorno+="<tr>";
                retorno+="<td></td>";
                retorno+="<td><label style='font:12px sans serif; color:red;'>La contraseña debe ser menor a diez (10) carácteres</label></td>";
                retorno+="</tr>";
                
            }
            retorno+="</table>";
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stm != null) stm.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                ////("VSreqUsuarioContrasenaManager::viewUsuarioAplic(): SQLException " + e.toString());

            }
        }
    
    return retorno;
    }
    
    public void verDatosCorreoUsuario(String usuario, String correo){
    sgtUtility util = new sgtUtility();
    Connection con = null;
    Statement stm=null;
    ResultSet rs=null;
    String to= correo;  
    String pass =  "";
    String ficha = "";
    String planta ="";
    String nom_planta="";
    String nombre_emp = "";
    String dpto_emp ="";
    
    
        try {
            String sql = "SELECT   \n" +
                        "  uco_cod_planta, \n" +
                        "  uco_ficha_emp,\n" +
                        "  uco_cod_usuario, \n" +
                        "  uco_contrasena, \n" +
                        "  uco_ruta, \n" +
                        "  uco_seguridad_status, \n" +
                        "  uco_cuenta_correo, \n" +
                        "  uco_nb_bd_notes, \n" +
                        "  uco_ruta_alternativa, \n" +
                        "  uco_ruta_requisicion, \n" +
                        "  uco_fecha_caducidad, \n" +
                        "  uco_status_caducidad, \n" +
                        "  uco_dias_caducidad, \n" +
                        "  uco_ruta_publicacion\n" +
                        "FROM \n" +
                        "  vv_usuario_contrasena\n" +
                        "WHERE \n" +
                        "  uco_cod_usuario = '"+usuario+"' AND \n" +
                        "  vv_usuario_contrasena.uco_seguridad_status = '0';";
            con = util.Conexion_Sorg();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while(rs.next()){                        
                planta = rs.getString("uco_cod_planta");
                ficha = rs.getString("uco_ficha_emp");
                pass = rs.getString("uco_contrasena");
                   
                
            }
            String sql1 = "SELECT \n" +
                        "  pla_nom_planta, \n" +
                        "  per_nombre, \n" +
                        "  dep_desc_dpto, \n" +
                        "  per_ficha\n" +
                        "FROM \n" +
                        "  public.vv_personal, \n" +
                        "  public.vv_planta, \n" +
                        "  public.vv_departamento\n" +
                        "WHERE \n" +
                        "  vv_personal.per_cod_planta = vv_planta.pla_cod_planta AND\n" +
                        "  vv_personal.per_depart = vv_departamento.dep_cod_dpto AND\n" +
                        "  vv_departamento.dep_cod_planta = vv_planta.pla_cod_planta AND\n" +
                        "  (per_ficha='"+ficha+"' OR per_cedula ='"+ficha+"') AND\n" +
                        "  per_cod_planta='"+planta+"';";
            
            stm = con.createStatement();
            rs = stm.executeQuery(sql1);
            while(rs.next()){                        
                nom_planta=rs.getString("pla_nom_planta");
                nombre_emp = rs.getString("per_nombre");
                dpto_emp = rs.getString("dep_desc_dpto");
                   
                
            }
            
            String mensaje = "<!DOCTYPE HTML>\n" +
                            "<html>\n" +
                            "<head>\n" +
                            "<title> Nuevo Requerimiento</title>\n" +
                            "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
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
                            "  border: 1px solid #8A0808;\n" +
                            "  padding-bottom: 20px; \n" +
                            "  background: transparent;\n" +
                            "}\n" +
                            "#fondo{\n" +
                            "    width: 250px;\n" +
                            "    height: 250px;\n" +
                            "    position: absolute;\n" +
                            "    margin-left: -200px;\n" +
                            "    margin-top: 35px;\n" +
                            "    opacity: 0.3;\n" +
                            "}\n" +
                            "#detalles td{\n" +
                            "  padding-top: 20px;\n" +
                            "  \n" +
                            "}\n" +
                            ".titulo{\n" +
                            "  text-align: left;  \n" +
                            "  font: bold 13px sans-serif;\n" +
                            "  width: 100px;\n" +
                            "  padding-left: 10px;\n" +
                            "\n" +
                            "}\n" +
                            ".detalleReque{\n" +
                            "  font-size: 14px;\n" +
                            "  text-align:left;\n" +
                            "  font-family: sans-serif;\n" +
                            "  \n" +
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
                            "  font-weight: 200;\n" +
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
                            "#barra{\n" +
                            "  width: 460px;\n" +
                            "  height: 45px;\n" +
                            "  background-color: #8A0808;\n" +
                            "  border: 1px solid #8A0808;\n" +
                            "  color: white;\n" +
                            "  text-align: center;\n" +
                            "}\n" +
                            "#centrado{\n" +
                            "    margin: 0 auto;\n" +
                            "}\n" +
                            "</style>\n" +
                            "</head>\n" +
                            "<body>\n" +
                            "    <section id=\"centrado\">\n" +
                            "      <div class=\"triangulo_top_left\"></div>\n" +
                            "      <div class=\"triangulo_bottom_right\"></div>\n" +
                            "      <div id=\"encabezado\"><h2>Datos del usuario<img id=\"fondo\" src=\"http://10.183.12.158:8080/sgt_website/sgt_imagenes/help_desk_logo.png\"></h2></div>\n" +
                            "      <table id=\"detalles\">\n" +
                            "        <tr>\n" +
                            "          <td class=\"titulo\">Planta:</td>\n" +
                            "          <td class=\"detalleReque\">"+nom_planta+"</td>\n" +
                            "        </tr>\n" +
                            "        <tr>\n" +
                            "          <td class=\"titulo\">Nombre:</td>\n" +
                            "          <td class=\"detalleReque\">"+nombre_emp+"</td>\n" +
                            "        </tr>\n" +
                            "        <tr>\n" +
                            "          <td class=\"titulo\">Ficha:</td>\n" +
                            "          <td class=\"detalleReque\">"+ficha+"</td>\n" +
                            "        </tr>\n" +
                            "        <tr>\n" +
                            "          <td class=\"titulo\">Departamento:</td>\n" +
                            "          <td class=\"detalleReque\">"+dpto_emp+"</td>\n" +
                            "        </tr>\n" +
                            "        <tr>\n" +
                            "          <td class=\"titulo\">Usuario:</td>\n" +
                            "          <td class=\"detalleReque\">"+usuario+"</td>\n" +
                            "        </tr>\n" +
                            "        <tr>\n" +
                            "          <td class=\"titulo\">Contrase&ntilde;a:</td>\n" +
                            "          <td class=\"detalleReque\">"+pass+"</td>\n" +
                            "        </tr>\n" +
                            "      </table>\n" +
                            "      <div id=\"barra\">Con estos datos podras ingresar al Sistema de Gesti&oacute;n de Tecnolog&iacute;a (Centro de Atenci&oacute;n al Usuario)</div>\n" +
                            "    </section>\n" +
                            "</body>\n" +
                            "</html>";
            if(to.equals("atencion.soporte@venvidrio.com.ve")){
                Send_correo_verificar_usuario_CAU(mensaje, to, nombre_emp, planta);
            }else{
                Send_correo_verificar_usuario(mensaje, to, planta);
            }                
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stm != null) stm.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                ////("VSreqUsuarioContrasenaManager::viewUsuarioAplic(): SQLException " + e.toString());

            }
        }
    }
    
    public String verPlanta(String usuario){
    sgtUtility util = new sgtUtility();
    Connection con = null;
    Statement stm=null;
    ResultSet rs=null;
    String retorno="";
    
    
        try {
            String sql = "SELECT   \n" +
                        "  uco_cod_planta, \n" +
                        "  uco_ficha_emp,\n" +
                        "  uco_cod_usuario, \n" +
                        "  uco_contrasena\n" +
                        "FROM \n" +
                        "  vv_usuario_contrasena\n" +
                        "WHERE \n" +
                        "  uco_cod_usuario = '"+usuario+"' AND \n" +
                        "  vv_usuario_contrasena.uco_seguridad_status = '0';";
            con = util.Conexion_Sorg();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while(rs.next()){
                String planta=rs.getString("uco_cod_planta");
                
                
                retorno+=planta;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stm != null) stm.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                ////("VSreqUsuarioContrasenaManager::viewUsuarioAplic(): SQLException " + e.toString());

            }
        }
    
    return retorno;
    }
    
    public String verFicha(String usuario){
    sgtUtility util = new sgtUtility();
    Connection con = null;
    Statement stm=null;
    ResultSet rs=null;
    String retorno="";
    
    
        try {
            String sql = "SELECT   \n" +
                        "  uco_cod_planta, \n" +
                        "  uco_ficha_emp,\n" +
                        "  uco_cod_usuario, \n" +
                        "  uco_contrasena\n" +
                        "FROM \n" +
                        "  vv_usuario_contrasena\n" +
                        "WHERE \n" +
                        "  uco_cod_usuario = '"+usuario+"' AND \n" +
                        "  vv_usuario_contrasena.uco_seguridad_status = '0';";
            con = util.Conexion_Sorg();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while(rs.next()){
                String ficha=rs.getString("uco_ficha_emp");
                
                
                retorno+=ficha;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stm != null) stm.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                ////("VSreqUsuarioContrasenaManager::viewUsuarioAplic(): SQLException " + e.toString());

            }
        }
    
    return retorno;
    }
    
    public String verContrasena(String usuario){
    sgtUtility util = new sgtUtility();
    Connection con = null;
    Statement stm=null;
    ResultSet rs=null;
    String retorno="";
    
    
        try {
            String sql = "SELECT   \n" +
                        "  uco_cod_planta, \n" +
                        "  uco_ficha_emp,\n" +
                        "  uco_cod_usuario, \n" +
                        "  uco_contrasena\n" +
                        "FROM \n" +
                        "  vv_usuario_contrasena\n" +
                        "WHERE \n" +
                        "  uco_cod_usuario = '"+usuario+"' AND \n" +
                        "  vv_usuario_contrasena.uco_seguridad_status = '0';";
            con = util.Conexion_Sorg();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while(rs.next()){
                String pass=rs.getString("uco_contrasena");
                
                
                retorno+=pass;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stm != null) stm.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                ////("VSreqUsuarioContrasenaManager::viewUsuarioAplic(): SQLException " + e.toString());

            }
        }
    
    return retorno;
    }
    
    public void verDatosUsuarioActualizar(String usuario, String correo, String nuevo_usuario){
    sgtUtility util = new sgtUtility();
    Connection con = null;
    Connection con2 = null;
    PreparedStatement pstmt=null;
    Statement stm=null;
    ResultSet rs=null;
    java.util.Date today=new java.util.Date(); 
    
    String to= correo;
    String planta = verPlanta(usuario);
    String ficha = verFicha(usuario);
    String pass = verContrasena(usuario);
    String nom_planta="";
    String nombre_emp = "";
    String dpto_emp ="";
    
    String fecha_cadu ="2015/12/31";
    String[] partes= fecha_cadu.split("/");
    Calendar miCal=Calendar.getInstance();
        miCal.set(Calendar.DATE, Integer.parseInt(partes[2]));//Seteando el Dia
        miCal.set(Calendar.MONTH, Integer.parseInt(partes[1])-1);//Seteando el Mes -1
        miCal.set(Calendar.YEAR, Integer.parseInt(partes[0]));//Seteando el Anno
        
        Date fecha_cadu_sql=new Date(miCal.getTimeInMillis());
    
        System.out.println("ESTOY GUARDANDO EL REGISTRO DEL USUARIO ACTUALIZADO");
    
        try {
            
            String sql = "INSERT INTO t_vv_usuario_contrasena(\n" +
            "            uco_cod_planta, uco_ficha_emp, uco_cod_usuario, uco_contrasena, \n" +
            "            uco_ruta, uco_seguridad_status, uco_cuenta_correo, uco_nb_bd_notes, \n" +
            "            uco_ruta_alternativa, uco_ruta_requisicion, uco_fecha_caducidad, \n" +
            "            uco_status_caducidad, uco_dias_caducidad, uco_ruta_publicacion)\n" +
            "           SELECT   \n" +
            "               uco_cod_planta, uco_ficha_emp, '"+nuevo_usuario+"' , uco_contrasena, \n" +
            "               uco_ruta, uco_seguridad_status, uco_cuenta_correo, uco_nb_bd_notes, \n" +
            "               uco_ruta_alternativa, uco_ruta_requisicion, uco_fecha_caducidad, \n" +
            "               uco_status_caducidad, uco_dias_caducidad, uco_ruta_publicacion\n" +
            "           FROM \n" +
            "               t_vv_usuario_contrasena\n" +
            "           WHERE \n" +
            "               uco_cod_usuario = '"+usuario+"' AND \n" +
            "               t_vv_usuario_contrasena.uco_seguridad_status = '0';";
            con = util.Conexion_Empresa();
            pstmt = con.prepareStatement(sql);
            pstmt.executeUpdate();
            
            //VARIABLES PARA LA TABLA USUARIO_ROL
        String cod_aplic="042";
        String cod_rol="03";
        String usuario_mod ="VGSIT01";
            
            String sql2 ="INSERT INTO t_vv_usuario_rol(\n" +
                    "            usu_cod_aplic, usu_cod_rol, usu_cod_usuario, usu_cod_usuario_mod, \n" +
                    "            usu_fecha_modificacion)\n" +
                    "    VALUES (?, ?, ?, ?, \n" +
                    "            ?);";
            
            pstmt = con.prepareStatement(sql2);
            pstmt.setString(1, cod_aplic);
            pstmt.setString(2, cod_rol);
            pstmt.setString(3, nuevo_usuario);
            pstmt.setString(4, usuario_mod);
            pstmt.setDate(5, new java.sql.Date(today.getTime()));
            pstmt.executeUpdate();
            
            
            String sql1 = "SELECT \n" +
                        "  pla_nom_planta, \n" +
                        "  per_nombre, \n" +
                        "  dep_desc_dpto, \n" +
                        "  per_ficha\n" +
                        "FROM \n" +
                        "  public.vv_personal, \n" +
                        "  public.vv_planta, \n" +
                        "  public.vv_departamento\n" +
                        "WHERE \n" +
                        "  vv_personal.per_cod_planta = vv_planta.pla_cod_planta AND\n" +
                        "  vv_personal.per_depart = vv_departamento.dep_cod_dpto AND\n" +
                        "  vv_departamento.dep_cod_planta = vv_planta.pla_cod_planta AND\n" +
                        "  per_ficha='"+ficha+"' AND\n" +
                        "  per_cod_planta='"+planta+"';";
            con2 = util.Conexion_Sorg();
            stm = con2.createStatement();
            rs = stm.executeQuery(sql1);
            while(rs.next()){                        
                nom_planta=rs.getString("pla_nom_planta");
                nombre_emp = rs.getString("per_nombre");
                dpto_emp = rs.getString("dep_desc_dpto");
                   
                
            }
            
            String mensaje = "<!DOCTYPE HTML>\n" +
                            "<html>\n" +
                            "<head>\n" +
                            "<title> Nuevo Requerimiento</title>\n" +
                            "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
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
                            "  border: 1px solid #8A0808;\n" +
                            "  padding-bottom: 20px; \n" +
                            "  background: transparent;\n" +
                            "}\n" +
                            "#fondo{\n" +
                            "    width: 250px;\n" +
                            "    height: 250px;\n" +
                            "    position: absolute;\n" +
                            "    margin-left: -200px;\n" +
                            "    margin-top: 35px;\n" +
                            "    opacity: 0.3;\n" +
                            "}\n" +
                            "#detalles td{\n" +
                            "  padding-top: 20px;\n" +
                            "  \n" +
                            "}\n" +
                            ".titulo{\n" +
                            "  text-align: left;  \n" +
                            "  font: bold 13px sans-serif;\n" +
                            "  width: 100px;\n" +
                            "  padding-left: 10px;\n" +
                            "\n" +
                            "}\n" +
                            ".detalleReque{\n" +
                            "  font-size: 14px;\n" +
                            "  text-align:left;\n" +
                            "  font-family: sans-serif;\n" +
                            "  \n" +
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
                            "  font-weight: 200;\n" +
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
                            "#barra{\n" +
                            "  width: 460px;\n" +
                            "  height: 45px;\n" +
                            "  background-color: #8A0808;\n" +
                            "  border: 1px solid #8A0808;\n" +
                            "  color: white;\n" +
                            "  text-align: center;\n" +
                            "}\n" +
                            "#centrado{\n" +
                            "    margin: 0 auto;\n" +
                            "}\n" +
                            "</style>\n" +
                            "</head>\n" +
                            "<body>\n" +
                            "    <section id=\"centrado\">\n" +
                            "      <div class=\"triangulo_top_left\"></div>\n" +
                            "      <div class=\"triangulo_bottom_right\"></div>\n" +
                            "      <div id=\"encabezado\"><h2>Datos del usuario<img id=\"fondo\" src=\"http://10.183.9.20:8080/sgt_website/sgt_imagenes/help_desk_logo.png\"></h2></div>\n" +
                            "      <table id=\"detalles\">\n" +
                            "        <tr>\n" +
                            "          <td class=\"titulo\">Planta:</td>\n" +
                            "          <td class=\"detalleReque\">"+nom_planta+"</td>\n" +
                            "        </tr>\n" +
                            "        <tr>\n" +
                            "          <td class=\"titulo\">Nombre:</td>\n" +
                            "          <td class=\"detalleReque\">"+nombre_emp+"</td>\n" +
                            "        </tr>\n" +
                            "        <tr>\n" +
                            "          <td class=\"titulo\">Ficha:</td>\n" +
                            "          <td class=\"detalleReque\">"+ficha+"</td>\n" +
                            "        </tr>\n" +
                            "        <tr>\n" +
                            "          <td class=\"titulo\">Departamento:</td>\n" +
                            "          <td class=\"detalleReque\">"+dpto_emp+"</td>\n" +
                            "        </tr>\n" +
                            "        <tr>\n" +
                            "          <td class=\"titulo\">Usuario:</td>\n" +
                            "          <td class=\"detalleReque\">"+nuevo_usuario+"</td>\n" +
                            "        </tr>\n" +
                            "        <tr>\n" +
                            "          <td class=\"titulo\">Contraseña:</td>\n" +
                            "          <td class=\"detalleReque\">"+pass+"</td>\n" +
                            "        </tr>\n" +
                            "      </table>\n" +
                            "      <div id=\"barra\">Con estos datos podras ingresar al Sistema de Gestión de Tecnología (Centro de Atención al Usuario)</div>\n" +
                            "    </section>\n" +
                            "</body>\n" +
                            "</html>";
            if(to.equals("atencion.soporte@venvidrio.com.ve")){
                Send_correo_verificar_usuario_CAU(mensaje, to, nombre_emp, planta);
            }else{
                Send_correo_verificar_usuario(mensaje, to, planta);
            }                
            
        } catch (SQLException e) {
            System.out.println("ERRO: "+e);
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stm != null) stm.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
                if (con2 != null) con2.close();
            } catch (SQLException e) {
                ////("VSreqUsuarioContrasenaManager::viewUsuarioAplic(): SQLException " + e.toString());

            }
        }
    }
    
    public void insertarNuevoUsuario(String planta_usu, String ficha_usu, String codigo_usuario, String contrasena_usu, String correo_usu){               
        sgtUtility util = new sgtUtility();
        
        java.util.Date today=new java.util.Date(); 
               
        Connection con =null;
        PreparedStatement pstmt=null;
        con=util.Conexion_Empresa();
        System.out.println("-----------------------------------");
        System.out.println("VOY A GUARDAR  UN USUARIO");
        //Variables para la tabla USUARIO_CONTRASEÑA
        String correo="";
        String ruta ="";
        String seguridad ="0";
        String notes="";
        String ruta_alterna ="VEN06";
        String ruta_requi ="";
        String fecha_cadu ="2015/12/31";
        String[] partes= fecha_cadu.split("/");
        if(!correo_usu.equals("atencion.soporte@venvidrio.com.ve")){
            
            correo = correo_usu;            
            System.out.println("condicion 1 correo: "+ correo);
        }else{
            correo="sin correo";
            System.out.println("condicion 2 correo: "+ correo);
        }
        
        
        Calendar miCal=Calendar.getInstance();
        miCal.set(Calendar.DATE, Integer.parseInt(partes[2]));//Seteando el Dia
        miCal.set(Calendar.MONTH, Integer.parseInt(partes[1])-1);//Seteando el Mes -1
        miCal.set(Calendar.YEAR, Integer.parseInt(partes[0]));//Seteando el Anno
        
        Date fecha_cadu_sql=new Date(miCal.getTimeInMillis());
        
        String status_cadu="";
        int dias_cadu = 30;
        String ruta_publi ="";

        //VARIABLES PARA LA TABLA USUARIO_ROL
        String cod_aplic="042";
        String cod_rol="03";
        String usuario_mod ="VGSIT01";
        
        String sql="INSERT INTO t_vv_usuario_contrasena(\n" +
                    "            uco_cod_planta, uco_ficha_emp, uco_cod_usuario, uco_contrasena, \n" +
                    "            uco_ruta, uco_seguridad_status, uco_cuenta_correo, uco_nb_bd_notes, \n" +
                    "            uco_ruta_alternativa, uco_ruta_requisicion, uco_fecha_caducidad, \n" +
                    "            uco_status_caducidad, uco_dias_caducidad, uco_ruta_publicacion)\n" +
                    "    VALUES (?, ?, ?, ?, \n" +
                    "            ?, ?, ?, ?, \n" +
                    "            ?, ?, ?, \n" +
                    "            ?, ?, ?);";
        String sql1 ="INSERT INTO t_vv_usuario_rol(\n" +
                    "            usu_cod_aplic, usu_cod_rol, usu_cod_usuario, usu_cod_usuario_mod, \n" +
                    "            usu_fecha_modificacion)\n" +
                    "    VALUES (?, ?, ?, ?, \n" +
                    "            ?);";
        
        try {          
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1, planta_usu);
            pstmt.setString(2, ficha_usu);
            pstmt.setString(3, codigo_usuario);
            pstmt.setString(4, contrasena_usu);
            pstmt.setString(5, ruta);
            pstmt.setString(6, seguridad);
            pstmt.setString(7, correo);
            pstmt.setString(8, notes);
            pstmt.setString(9, ruta_alterna);
            pstmt.setString(10, ruta_requi);
            pstmt.setDate(11, fecha_cadu_sql);
            pstmt.setString(12, status_cadu);
            pstmt.setInt(13, dias_cadu);
            pstmt.setString(14, ruta_publi);            
            pstmt.executeUpdate();
            
            pstmt = con.prepareStatement(sql1);
            pstmt.setString(1, cod_aplic);
            pstmt.setString(2, cod_rol);
            pstmt.setString(3, codigo_usuario);
            pstmt.setString(4, usuario_mod);
            pstmt.setDate(5, new java.sql.Date(today.getTime()));
            pstmt.executeUpdate();
            
            System.out.println("LOS DATOS FUERON GUARDADOS EXITOSAMENTE");
            verDatosCorreoUsuario(codigo_usuario, correo_usu);
        } catch (SQLException e) {
            System.out.println("ERROR: " +e);
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
    
    public static void Send_correo_verificar_usuario(String mensaje, String to, String planta_usu){
        
        //String to ="marilys.rivas@venvidrio.com.ve";  
        //String to ="mario.ortega@venvidrio.com.ve"; 
        String planta = planta_usu;
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
                message.setSubject("Verificación de Usuario");
            //set content of the message in html format
            message.setContent(mensaje,"text/html");

                // Send message
               Transport.send(message);
               System.out.println("Mensaje de correo enviado");
            } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    public static void Send_correo_verificar_usuario_CAU(String mensaje, String to, String nombre_emp, String planta_usu){
        
        //String to ="marilys.rivas@venvidrio.com.ve";  
        //String to ="mario.ortega@venvidrio.com.ve";
        String planta = planta_usu;
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
                message.setSubject("Verificación de Usuario "+nombre_emp+"");
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
