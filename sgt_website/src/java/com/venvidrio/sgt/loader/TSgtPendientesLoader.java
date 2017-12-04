/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venvidrio.sgt.loader;

import com.venvidrio.sgt.utility.sgtUtility;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.math.*;
import java.sql.PreparedStatement;
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
public class TSgtPendientesLoader {
    
    private static String hostLGV="maillgv.venvidrio.com.ve";
    private static String hostVLR="mailvlr.venvidrio.com.ve";
    private static String smtpPort="25";
    private static String from="atencion.soporte@venvidrio.com.ve";
    
    public int obtenerCodAuto(){
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
            ls_sql="SELECT  max(sgt_cod_solucion_requerimiento) FROM  t_sgt_solucion_requerimiento;";
            
            rs=stmt.executeQuery(ls_sql);            
            if(rs.next()){
                //("Estoy en rs.next()");
               max=rs.getString(1);
//               System.out.println("SQL DE CODIGO REQUERIMIENTO: " + ls_sql);
//               System.out.println("VALOR DEL MAX: " + max);
            }
            System.out.println("ULTIMO CODIGO:");
            if(max!=null){
            cod= Integer.parseInt(max)+1;
            }
            System.out.println("CODIGO DEL MAX: " + cod);
            if(cod==0){
                id =1;
                System.out.println("Codigo de la solucion 1: " + id);
            }else if( cod >= 1){                 
                 id = cod;
                 System.out.println("Codigo de la solucion >1: " + id);
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
    
    public String verRequerimientosPendientes(){
        sgtUtility util = new sgtUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        
        String retorno = "";
        String cod_reque = "";
        retorno +="";
        
        
            try{
                String sql ="SELECT \n" +
                            "  sgt_cod_requerimiento, \n" +
                            "  per_nombre, \n" +
                            "  sgt_fecha_requerimiento, \n" +
                            "  sgt_desc_tipo_requerimiento, \n" +
                            "  t_sgt_requerimiento.sgt_cod_status,\n" +
                            "  sgt_cod_tipo_requerimieto,\n" +
                            "  sgt_desc_status \n" +
                            "FROM \n" +
                            "  public.t_sgt_status,\n" +
                            "  public.t_sgt_requerimiento, \n" +
                            "  public.t_sgt_tipo_requerimiento, \n" +
                            "  public.vv_personal \n" +
                            "WHERE \n" +
                            "  t_sgt_requerimiento.per_ficha_solicitante = vv_personal.per_ficha AND\n" +
                            "  t_sgt_requerimiento.per_cod_planta_solicitante = vv_personal.per_cod_planta AND\n" +
                            "  t_sgt_requerimiento.sgt_cod_tipo_requerimieto = t_sgt_tipo_requerimiento.sgt_cod_tipo_requerimiento AND\n" +
                            "  t_sgt_requerimiento.sgt_cod_status = t_sgt_status.sgt_cod_status AND \n" +
                            "  (t_sgt_requerimiento.sgt_cod_status ='1' OR t_sgt_requerimiento.sgt_cod_status='6')\n" +
                            "ORDER BY\n" +
                            "  sgt_cod_requerimiento DESC;";
                
                con = util.Conexion_Sorg();
                stm= con.createStatement();    
                rs = stm.executeQuery(sql);
                //System.out.println("SQL PENDIENTES: " + sql);
                while(rs.next()){                    
                    cod_reque = rs.getString("sgt_cod_requerimiento");
                    String tipo_reque = rs.getString("sgt_desc_tipo_requerimiento");
                    String nombre_usu = rs.getString("per_nombre");                    
                    String cod_status = rs.getString("sgt_cod_status");
                    String fecha_reque = rs.getString("sgt_fecha_requerimiento");
                    String cod_tipo_reque = rs.getString("sgt_cod_tipo_requerimieto");
                    String desc_status = rs.getString("sgt_desc_status");
                    
                retorno +="<tr class='registro'>";
                    retorno +="<td >"+cod_reque+"</td>";
                    retorno +="<td>"+nombre_usu+"</td>";
                    retorno +="<td >"+fecha_reque.substring(0,16)+"</td>";
                    retorno +="<td >"+tipo_reque+"</td>";
                    retorno +="<td>";  
                    if(cod_status.equals("1") & cod_tipo_reque.equals("03") ){
                        retorno +="<img src='sgt_imagenes/clock2.png' alt='' title=\"Solicitud de Cambio Pendiente por Aprobar\" style='width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("6")){
                        retorno +="<img src='sgt_imagenes/check.png' alt='' title=\"Solicitud de Cambio Aprobada\" style=' width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("7")){
                        retorno +="<img src='sgt_imagenes/cancel.png' alt='' title=\"Solicitud de Cambio Rechazado\" style='width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("5")){
                        retorno +="<img src='sgt_imagenes/cancel2.png' alt='' title=\"Requerimiento cancelado\" style='width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("4")){
                        retorno +="<img src='sgt_imagenes/finish.png' alt='' title=\"Requerimiento finalizado\" style='width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("8")){
                        retorno +="<img src='sgt_imagenes/prosess.png' alt='' title=\"Procesando requerimiento\" style=' width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("1")){
                        retorno +="<img src='sgt_imagenes/inbox.png' alt='' title=\"Requerimiento pendiente por asignar\" style=' width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("2")){
                        retorno +="<img src='sgt_imagenes/asignado.png' alt='' title=\"Requerimiento asignado\" style=' width: 20px; height: 20px;'>";
                    }
                    retorno +="</td>";                    
                    retorno +="<td>"+desc_status+"</td>";
                    retorno +="<td ><button id='editarRequerimientoPendiente' formtarget='_blank' onclick='ejecutaCodigo(this.name);' name='cod"+cod_reque+"' class='editarArea' title='Ver Detalles y Editar Status' ></button></td>";
                    
                retorno +="</tr>";
                }
                
           
//           if(cod_reque.equals("11")){
//              retorno +="<button class='btnPaginacion' disabled id='btnPrevio'>Anterior</button>"; 
//           }
//           retorno+="&nbsp;&nbsp;&nbsp;&nbsp;<button onclick='next(this.name);' class='btnPaginacion' name='cod"+cod_reque+"' id='btnSiguiente'>Siguiente</button>";
//            
            
            }catch(SQLException e){
                System.out.println("ERROR PENDIENTES: "+e);
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
    
    public String verRequerimientosHistorico(){
        sgtUtility util = new sgtUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        
        String retorno = "";
        String cod_reque = "";
        retorno +="";
        
        
            try{
                String sql ="SELECT \n" +
                            "  sgt_cod_requerimiento, \n" +
                            "  per_nombre, \n" +
                            "  sgt_fecha_requerimiento, \n" +
                            "  sgt_desc_tipo_requerimiento, \n" +
                            "  t_sgt_requerimiento.sgt_cod_status,\n" +
                            "  sgt_cod_tipo_requerimieto,\n" +
                            "  sgt_desc_status \n" +
                            "FROM \n" +
                            "  public.t_sgt_status,\n" +
                            "  public.t_sgt_requerimiento, \n" +
                            "  public.t_sgt_tipo_requerimiento, \n" +
                            "  public.vv_personal \n" +
                            "WHERE \n" +
                            "  t_sgt_requerimiento.per_ficha_solicitante = vv_personal.per_ficha AND\n" +
                            "  t_sgt_requerimiento.per_cod_planta_solicitante = vv_personal.per_cod_planta AND\n" +
                            "  t_sgt_requerimiento.sgt_cod_tipo_requerimieto = t_sgt_tipo_requerimiento.sgt_cod_tipo_requerimiento AND\n" +
                            "  t_sgt_requerimiento.sgt_cod_status = t_sgt_status.sgt_cod_status\n" +
                            "ORDER BY\n" +
                            "  sgt_cod_requerimiento DESC;";
                
                con = util.Conexion_Sorg();
                stm= con.createStatement();    
                rs = stm.executeQuery(sql);
               // System.out.println("SQL PENDIENTES: " + sql);
                while(rs.next()){                    
                    cod_reque = rs.getString("sgt_cod_requerimiento");
                    String tipo_reque = rs.getString("sgt_desc_tipo_requerimiento");
                    String nombre_usu = rs.getString("per_nombre");                    
                    String cod_status = rs.getString("sgt_cod_status");
                    String fecha_reque = rs.getString("sgt_fecha_requerimiento");
                    String cod_tipo_reque = rs.getString("sgt_cod_tipo_requerimieto");
                    String desc_status = rs.getString("sgt_desc_status");
                    
                retorno +="<tr class='registro'>";
                    retorno +="<td >"+cod_reque+"</td>";
                    retorno +="<td>"+nombre_usu+"</td>";
                    retorno +="<td >"+fecha_reque.substring(0,16)+"</td>";
                    retorno +="<td >"+tipo_reque+"</td>";
                    retorno +="<td>";  
                    if(cod_status.equals("1") & cod_tipo_reque.equals("03") ){
                        retorno +="<img src='sgt_imagenes/clock2.png' alt='' title=\"Solicitud de Cambio Pendiente por Aprobar\" style='width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("6")){
                        retorno +="<img src='sgt_imagenes/check.png' alt='' title=\"Solicitud de Cambio Aprobada\" style=' width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("7")){
                        retorno +="<img src='sgt_imagenes/cancel.png' alt='' title=\"Solicitud de Cambio Rechazado\" style='width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("5")){
                        retorno +="<img src='sgt_imagenes/cancel2.png' alt='' title=\"Requerimiento cancelado\" style='width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("4")){
                        retorno +="<img src='sgt_imagenes/finish.png' alt='' title=\"Requerimiento finalizado\" style='width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("8")){
                        retorno +="<img src='sgt_imagenes/process.png' alt='' title=\"Procesando requerimiento\" style=' width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("1")){
                        retorno +="<img src='sgt_imagenes/inbox.png' alt='' title=\"Requerimiento pendiente por asignar\" style=' width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("2")){
                        retorno +="<img src='sgt_imagenes/asignado.png' alt='' title=\"Requerimiento asignado\" style=' width: 20px; height: 20px;'>";
                    }
                    retorno +="</td>";                    
                    retorno +="<td>"+desc_status+"</td>";
                    retorno +="<td ><button id='editarRequerimientoPendiente' formtarget='_blank' onclick='ejecutaCodigo(this.name);' name='cod"+cod_reque+"' class='editarArea' title='Ver Detalles y Editar Status' ></button></td>";
                    
                retorno +="</tr>";
                }
                
           
//           if(cod_reque.equals("11")){
//              retorno +="<button class='btnPaginacion' disabled id='btnPrevio'>Anterior</button>"; 
//           }
//           retorno+="&nbsp;&nbsp;&nbsp;&nbsp;<button onclick='next(this.name);' class='btnPaginacion' name='cod"+cod_reque+"' id='btnSiguiente'>Siguiente</button>";
//            
            
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
    
    public String verRequerimientosAsignados(String ficha, String planta){
        sgtUtility util = new sgtUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        
        String retorno = "";
        String cod_reque = "";
        retorno +="";
        
        
            try{          
                String sql ="SELECT \n" +
                            "  sgt_cod_requerimiento, \n" +
                            "  per_nombre, \n" +
                            "  sgt_fecha_requerimiento, \n" +
                            "  sgt_desc_tipo_requerimiento, \n" +
                            "  t_sgt_requerimiento.sgt_cod_status,\n" +
                            "  sgt_cod_tipo_requerimieto,\n" +
                            "  sgt_desc_status \n" +
                            "FROM \n" +
                            "  public.t_sgt_status,\n" +
                            "  public.t_sgt_requerimiento, \n" +
                            "  public.t_sgt_tipo_requerimiento, \n" +
                            "  public.vv_personal \n" +
                            "WHERE \n" +
                            "  t_sgt_requerimiento.per_ficha_solicitante = vv_personal.per_ficha AND\n" +
                            "  t_sgt_requerimiento.per_cod_planta_solicitante = vv_personal.per_cod_planta AND\n" +
                            "  t_sgt_requerimiento.sgt_cod_tipo_requerimieto = t_sgt_tipo_requerimiento.sgt_cod_tipo_requerimiento AND\n" +
                            "  t_sgt_requerimiento.sgt_cod_status = t_sgt_status.sgt_cod_status AND \n" +
                            "  (t_sgt_requerimiento.sgt_cod_status='2' OR t_sgt_requerimiento.sgt_cod_status='8') AND \n" +
                            "  sgt_ficha_analista = '"+ficha+"' AND \n" +
                            "  sgt_cod_planta_analista = '"+planta+"' \n" +
                            "ORDER BY\n" +
                            "  sgt_cod_requerimiento DESC;";
                
                con = util.Conexion_Sorg();
                stm= con.createStatement();    
                rs = stm.executeQuery(sql);
                //System.out.println("SQL PENDIENTES: " + sql);
                while(rs.next()){                    
                    cod_reque = rs.getString("sgt_cod_requerimiento");
                    String tipo_reque = rs.getString("sgt_desc_tipo_requerimiento");
                    String nombre_usu = rs.getString("per_nombre");                    
                    String cod_status = rs.getString("sgt_cod_status");
                    String fecha_reque = rs.getString("sgt_fecha_requerimiento");
                    String cod_tipo_reque = rs.getString("sgt_cod_tipo_requerimieto");
                    String desc_status = rs.getString("sgt_desc_status");
                    
                retorno +="<tr class='registro'>";
                    retorno +="<td >"+cod_reque+"</td>";
                    retorno +="<td>"+nombre_usu+"</td>";
                    retorno +="<td >"+fecha_reque.substring(0,16)+"</td>";
                    retorno +="<td >"+tipo_reque+"</td>";
                    retorno +="<td>";  
                    if(cod_status.equals("1") & cod_tipo_reque.equals("03") ){
                        retorno +="<img src='sgt_imagenes/clock2.png' alt='' title=\"Solicitud de Cambio Pendiente por Aprobar\" style='width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("6")){
                        retorno +="<img src='sgt_imagenes/check.png' alt='' title=\"Solicitud de Cambio Aprobada\" style=' width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("7")){
                        retorno +="<img src='sgt_imagenes/cancel.png' alt='' title=\"Solicitud de Cambio Rechazado\" style='width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("5")){
                        retorno +="<img src='sgt_imagenes/cancel2.png' alt='' title=\"Requerimiento cancelado\" style='width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("4")){
                        retorno +="<img src='sgt_imagenes/finish.png' alt='' title=\"Requerimiento finalizado\" style='width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("8")){
                        retorno +="<img src='sgt_imagenes/process.png' alt='' title=\"Procesando requerimiento\" style=' width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("1")){
                        retorno +="<img src='sgt_imagenes/inbox.png' alt='' title=\"Requerimiento pendiente por asignar\" style=' width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("2")){
                        retorno +="<img src='sgt_imagenes/asignado.png' alt='' title=\"Requerimiento asignado\" style=' width: 20px; height: 20px;'>";
                    }
                    retorno +="</td>";                    
                    retorno +="<td>"+desc_status+"</td>";
                    retorno +="<td ><button id='editarRequerimientoPendiente' formtarget='_blank' onclick='ejecutaCodigo(this.name);' name='cod"+cod_reque+"' class='editarArea' title='Ver Detalles y Editar Status' ></button></td>";
                    
                retorno +="</tr>";
                }
                
           
//           if(cod_reque.equals("11")){
//              retorno +="<button class='btnPaginacion' disabled id='btnPrevio'>Anterior</button>"; 
//           }
//           retorno+="&nbsp;&nbsp;&nbsp;&nbsp;<button onclick='next(this.name);' class='btnPaginacion' name='cod"+cod_reque+"' id='btnSiguiente'>Siguiente</button>";
//            
            
            }catch(SQLException e){
                System.out.println("ERROR: "+e);
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
    
    public String verRequerimientosCreadosUsuarioLog(String ficha, String planta){
        sgtUtility util = new sgtUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        
        String retorno = "";
        String cod_reque = "";
        retorno +="";
        
        
            try{
                String sql ="SELECT \n" +
                            "  sgt_cod_requerimiento, \n" +
                            "  per_nombre, \n" +
                            "  sgt_fecha_requerimiento, \n" +
                            "  sgt_desc_tipo_requerimiento, \n" +
                            "  t_sgt_requerimiento.sgt_cod_status,\n" +
                            "  sgt_cod_tipo_requerimieto,\n" +
                            "  sgt_desc_status \n" +
                            "FROM \n" +
                            "  public.t_sgt_status,\n" +
                            "  public.t_sgt_requerimiento, \n" +
                            "  public.t_sgt_tipo_requerimiento, \n" +
                            "  public.vv_personal \n" +
                            "WHERE \n" +
                            "  t_sgt_requerimiento.per_ficha_solicitante = vv_personal.per_ficha AND\n" +
                            "  t_sgt_requerimiento.per_cod_planta_solicitante = vv_personal.per_cod_planta AND\n" +
                            "  t_sgt_requerimiento.sgt_cod_tipo_requerimieto = t_sgt_tipo_requerimiento.sgt_cod_tipo_requerimiento AND\n" +
                            "  t_sgt_requerimiento.sgt_cod_status = t_sgt_status.sgt_cod_status AND \n" +
"                              per_ficha_solicitante = '"+ficha+"' AND \n" +
"                              per_cod_planta_solicitante = '"+planta+"' \n" +
"                            ORDER BY\n" +
"                              sgt_cod_requerimiento DESC;";
                
                con = util.Conexion_Sorg();
                stm= con.createStatement();    
                rs = stm.executeQuery(sql);
                //System.out.println("SQL PENDIENTES: " + sql);
                while(rs.next()){                    
                    cod_reque = rs.getString("sgt_cod_requerimiento");
                    String tipo_reque = rs.getString("sgt_desc_tipo_requerimiento");
                    String nombre_usu = rs.getString("per_nombre");                    
                    String cod_status = rs.getString("sgt_cod_status");
                    String fecha_reque = rs.getString("sgt_fecha_requerimiento");
                    String desc_status = rs.getString("sgt_desc_status");
                    String cod_tipo_reque = rs.getString("sgt_cod_tipo_requerimieto");
                    
                retorno +="<tr class='registro'>";
                    retorno +="<td >"+cod_reque+"</td>";
                    retorno +="<td>"+nombre_usu+"</td>";
                    retorno +="<td >"+fecha_reque.substring(0,16)+"</td>";
                    retorno +="<td >"+tipo_reque+"</td>";
                    retorno +="<td>";  
                    if(cod_status.equals("1") & cod_tipo_reque.equals("03") ){
                        retorno +="<img src='sgt_imagenes/clock2.png' alt='' title=\"Solicitud de Cambio Pendiente por Aprobar\" style='width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("6")){
                        retorno +="<img src='sgt_imagenes/check.png' alt='' title=\"Solicitud de Cambio Aprobada\" style=' width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("7")){
                        retorno +="<img src='sgt_imagenes/cancel.png' alt='' title=\"Solicitud de Cambio Rechazado\" style='width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("5")){
                        retorno +="<img src='sgt_imagenes/cancel2.png' alt='' title=\"Requerimiento cancelado\" style='width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("4")){
                        retorno +="<img src='sgt_imagenes/finish.png' alt='' title=\"Requerimiento finalizado\" style='width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("8")){
                        retorno +="<img src='sgt_imagenes/prosess.png' alt='' title=\"Solicitud de Cambio Rechazado\" style=' width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("1")){
                        retorno +="<img src='sgt_imagenes/inbox.png' alt='' title=\"Requerimiento pendiente por asignar\" style=' width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("2")){
                        retorno +="<img src='sgt_imagenes/asignado.png' alt='' title=\"Requerimiento asignado\" style=' width: 20px; height: 20px;'>";
                    }
                    retorno +="</td>";                    
                    retorno +="<td>"+desc_status+"</td>";
                    retorno +="<td ><button id='editarRequerimientoPendiente' onclick='ejecutaCodigo(this.name);' name='cod"+cod_reque+"' class='editarArea' title='Ver Detalles y Editar Status' ></button></td>";
                    
                retorno +="</tr>";
                }
                
           
//           if(cod_reque.equals("11")){
//              retorno +="<button class='btnPaginacion' disabled id='btnPrevio'>Anterior</button>"; 
//           }
//           retorno+="&nbsp;&nbsp;&nbsp;&nbsp;<button onclick='next(this.name);' class='btnPaginacion' name='cod"+cod_reque+"' id='btnSiguiente'>Siguiente</button>";
//            
            
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
    
    public String verHistorialRequerimiento(String cod){
        sgtUtility util = new sgtUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        
        String retorno = "";
        String cod_reque = "";
        retorno +="";
        
        
            try{
                String sql ="SELECT \n" +
                            "      sgt_fecha_registro, \n" +
                            "      sgt_desc_log, \n" +
                            "      per_nombre, \n" +
                            "      sgt_descripcion\n" +
                            "    FROM \n" +
                            "      public.t_sgt_log_requerimiento, \n" +
                            "      public.t_sgt_log, \n" +
                            "      public.vv_personal\n" +
                            "    WHERE \n" +
                            "      t_sgt_log_requerimiento.sgt_cod_log = t_sgt_log.sgt_cod_log AND\n" +
                            "      (t_sgt_log_requerimiento.sgt_ficha_analista = vv_personal.per_ficha OR t_sgt_log_requerimiento.sgt_ficha_analista = vv_personal.per_cedula) AND      \n" +
                            "      t_sgt_log_requerimiento.sgt_planta_analista = vv_personal.per_cod_planta AND\n" +
                            "      t_sgt_log_requerimiento.sgt_cod_requerimiento = '"+cod+"'\n" +
                            "    ORDER BY \n" +
                            "      sgt_fecha_registro;";
                
                con = util.Conexion_Sorg();
                stm= con.createStatement();    
                rs = stm.executeQuery(sql);
                //System.out.println("SQL PENDIENTES HISTORIAL: " + sql);
                while(rs.next()){                    
                    
                    String fecha_registro = rs.getString("sgt_fecha_registro");
                    String desc_log = rs.getString("sgt_desc_log");                    
                    String nombre = rs.getString("per_nombre");
                    String descripcion = rs.getString("sgt_descripcion");
                    
                    if(descripcion == null){
                        descripcion="[sin comentario]";
                    }
                    
                retorno +="<tr class='registro'>";
                    retorno +="<td>"+fecha_registro.substring(0,16)+"</td>";
                    retorno +="<td>"+desc_log+"</td>";
                    retorno +="<td>"+nombre+"</td>";
                    retorno +="<td>"+descripcion+"</td>";              
                retorno +="</tr>";
                }
                
           
//           if(cod_reque.equals("11")){
//              retorno +="<button class='btnPaginacion' disabled id='btnPrevio'>Anterior</button>"; 
//           }
//           retorno+="&nbsp;&nbsp;&nbsp;&nbsp;<button onclick='next(this.name);' class='btnPaginacion' name='cod"+cod_reque+"' id='btnSiguiente'>Siguiente</button>";
//            
            
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
        
    public String verRequerimientosPendientesPorTipoRequerimiento(String cod_tipo){
        sgtUtility util = new sgtUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        
        String retorno = "";
        String cod_reque = "";
        retorno +="";
        
                    retorno+="<table id='tablaPendiente'>";
                    retorno+="<tr>";
                    retorno+="    <th class='cabeceraArea' id='cod'>N°</th>";
                    retorno+="    <th class='cabeceraArea' id='nom'>Nombre Usuario</th>";
                    retorno+="    <th class='cabeceraArea' id='fecha'>Fecha</th>";
                    retorno+="    <th class='cabeceraArea' id='tipo'>Tipo Requerimiento</th>";
                    retorno+="<th class='cabeceraArea' id='clasi'>Clasificación</th>";
                    retorno+="<th class='cabeceraArea' id='edit'>+</th>";    
                    retorno+="</tr>";
        
            try{
                String sql ="SELECT \n" +
"                              sgt_cod_requerimiento, \n" +
"                              per_nombre, \n" +
"                              sgt_fecha_requerimiento, \n" +
"                              sgt_desc_tipo_requerimiento, \n" +
"                              sgt_desc_clasificacion, \n" +
"                              sgt_cod_status, sgt_cod_tipo_requerimieto \n" +
"                            FROM \n" +
"                              public.t_sgt_requerimiento, \n" +
"                              public.t_sgt_clasificacion, \n" +
"                              public.t_sgt_tipo_requerimiento, \n" +
"                              public.vv_personal\n" +
"                            WHERE \n" +
"                              t_sgt_requerimiento.per_cod_planta_solicitante = vv_personal.per_cod_planta AND\n" +
"                              t_sgt_requerimiento.per_ficha_solicitante = vv_personal.per_ficha AND\n" +
"                              t_sgt_clasificacion.sgt_cod_clasificacion = t_sgt_requerimiento.sgt_cod_clasificacion_area AND\n" +
"                              t_sgt_tipo_requerimiento.sgt_cod_tipo_requerimiento = t_sgt_requerimiento.sgt_cod_tipo_requerimieto AND\n" +
"                              t_sgt_requerimiento.sgt_cod_requerimiento >='1' AND \n" +
"                              sgt_cod_tipo_requerimiento = '"+cod_tipo+"'\n" +
"                            ORDER BY\n" +
"                              sgt_cod_requerimiento DESC;";            
                
                con = util.Conexion_Sorg();
                stm= con.createStatement();    
                rs = stm.executeQuery(sql);
                //System.out.println("SQL PENDIENTES: " + sql);
                while(rs.next()){                    
                    cod_reque = rs.getString("sgt_cod_requerimiento");
                    String tipo_reque = rs.getString("sgt_desc_tipo_requerimiento");
                    String nombre_usu = rs.getString("per_nombre");                    
                    String cod_status = rs.getString("sgt_cod_status");
                    String fecha_reque = rs.getString("sgt_fecha_requerimiento");
                    String clasi_reque = rs.getString("sgt_desc_clasificacion");
                    String cod_tipo_reque = rs.getString("sgt_cod_tipo_requerimieto");
                    
                retorno +="<tr class='registro'>";
                    retorno +="<td class='detalleArea' >"+cod_reque+"</td>";
                    retorno +="<td class='detalleArea' >"+nombre_usu+"</td>";
                    retorno +="<td class='detalleArea' >"+fecha_reque.substring(0,16)+"</td>";
                    retorno +="<td class='detalleArea' >"+tipo_reque+"";
                    if(cod_status.equals("1") & cod_tipo_reque.equals("03") ){
                        retorno +="<img src='sgt_imagenes/clock2.png' alt='' title=\"Solicitud de Cambio Pendiente por Aprobar\" style='position:absolute; margin-left:15px; width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("6")){
                        retorno +="<img src='sgt_imagenes/check.png' alt='' title=\"Solicitud de Cambio Aprobada\" style='position:absolute; margin-left:15px; width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("7")){
                        retorno +="<img src='sgt_imagenes/cancel.png' alt='' title=\"Solicitud de Cambio Rechazado\" style=' position:absolute; margin-left:15px; width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("5")){
                        retorno +="<img src='sgt_imagenes/cancel2.png' alt='' title=\"Requerimiento cancelado\" style=' position:absolute; margin-left:15px; width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("4")){
                        retorno +="<img src='sgt_imagenes/finish.png' alt='' title=\"Requerimiento finalizado\" style=' position:absolute; margin-left:15px; width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("8")){
                        retorno +="<img src='sgt_imagenes/prosess.png' alt='' title=\"Solicitud de Cambio Rechazado\" style=' position:absolute; margin-left:15px; width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("1")){
                        retorno +="<img src='sgt_imagenes/inbox.png' alt='' title=\"Requerimiento pendiente por asignar\" style=' position:absolute; margin-left:15px; width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("2")){
                        retorno +="<img src='sgt_imagenes/asignado.png' alt='' title=\"Requerimiento asignado\" style=' position:absolute; margin-left:15px; width: 20px; height: 20px;'>";
                    }
                    retorno +="</td>";                    
                    retorno +="<td class='detalleArea' >"+clasi_reque+"</td>";
                    retorno +="<td class='detalleArea'><button id='editarRequerimientoPendiente' onclick='ejecutaCodigo(this.name);' name='cod"+cod_reque+"' class='editarArea' title='Ver Detalles y Editar Status' ></button></td>";
                    
                retorno +="</tr>";
                }
                retorno+="</table>";
           
//           if(cod_reque.equals("11")){
//              retorno +="<button class='btnPaginacion' disabled id='btnPrevio'>Anterior</button>"; 
//           }
//           retorno+="&nbsp;&nbsp;&nbsp;&nbsp;<button onclick='next(this.name);' class='btnPaginacion' name='cod"+cod_reque+"' id='btnSiguiente'>Siguiente</button>";
//            
            
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
    
    public String verRequerimientosPendientesPorClasificacion(String cod_tipo){
        sgtUtility util = new sgtUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        
        String retorno = "";
        String cod_reque = "";
        retorno +="";
        
                    retorno+="<table id='tablaPendiente'>";
                    retorno+="<tr>";
                    retorno+="    <th class='cabeceraArea' id='cod'>N°</th>";
                    retorno+="    <th class='cabeceraArea' id='nom'>Nombre Usuario</th>";
                    retorno+="    <th class='cabeceraArea' id='fecha'>Fecha</th>";
                    retorno+="    <th class='cabeceraArea' id='tipo'>Tipo Requerimiento</th>";
                    retorno+="<th class='cabeceraArea' id='clasi'>Clasificación</th>";
                    retorno+="<th class='cabeceraArea' id='edit'>+</th>";    
                    retorno+="</tr>";
        
            try{
                String sql ="SELECT \n" +
"                              sgt_cod_requerimiento, \n" +
"                              per_nombre, \n" +
"                              sgt_fecha_requerimiento, \n" +
"                              sgt_desc_tipo_requerimiento, \n" +
"                              sgt_desc_clasificacion, \n" +
"                              sgt_cod_status, sgt_cod_tipo_requerimieto \n" +
"                            FROM \n" +
"                              public.t_sgt_requerimiento, \n" +
"                              public.t_sgt_clasificacion, \n" +
"                              public.t_sgt_tipo_requerimiento, \n" +
"                              public.vv_personal\n" +
"                            WHERE \n" +
"                              t_sgt_requerimiento.per_cod_planta_solicitante = vv_personal.per_cod_planta AND\n" +
"                              t_sgt_requerimiento.per_ficha_solicitante = vv_personal.per_ficha AND\n" +
"                              t_sgt_clasificacion.sgt_cod_clasificacion = t_sgt_requerimiento.sgt_cod_clasificacion_area AND\n" +
"                              t_sgt_tipo_requerimiento.sgt_cod_tipo_requerimiento = t_sgt_requerimiento.sgt_cod_tipo_requerimieto AND\n" +
"                              t_sgt_requerimiento.sgt_cod_requerimiento >='1' AND \n" +
"                              sgt_cod_clasificacion = '"+cod_tipo+"'\n" +
"                            ORDER BY\n" +
"                              sgt_cod_requerimiento DESC;";            
                
                con = util.Conexion_Sorg();
                stm= con.createStatement();    
                rs = stm.executeQuery(sql);
                System.out.println("SQL PENDIENTES: " + sql);
                while(rs.next()){                    
                    cod_reque = rs.getString("sgt_cod_requerimiento");
                    String tipo_reque = rs.getString("sgt_desc_tipo_requerimiento");
                    String nombre_usu = rs.getString("per_nombre");                    
                    String cod_status = rs.getString("sgt_cod_status");
                    String fecha_reque = rs.getString("sgt_fecha_requerimiento");
                    String clasi_reque = rs.getString("sgt_desc_clasificacion");
                    String cod_tipo_reque = rs.getString("sgt_cod_tipo_requerimieto");
                    
                retorno +="<tr class='registro'>";
                    retorno +="<td class='detalleArea' >"+cod_reque+"</td>";
                    retorno +="<td class='detalleArea' >"+nombre_usu+"</td>";
                    retorno +="<td class='detalleArea' >"+fecha_reque.substring(0,16)+"</td>";
                    retorno +="<td class='detalleArea' >"+tipo_reque+"";
                    if(cod_status.equals("1") & cod_tipo_reque.equals("03") ){
                        retorno +="<img src='sgt_imagenes/clock2.png' alt='' title=\"Solicitud de Cambio Pendiente por Aprobar\" style='position:absolute; margin-left:15px; width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("6")){
                        retorno +="<img src='sgt_imagenes/check.png' alt='' title=\"Solicitud de Cambio Aprobada\" style='position:absolute; margin-left:15px; width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("7")){
                        retorno +="<img src='sgt_imagenes/cancel.png' alt='' title=\"Solicitud de Cambio Rechazado\" style=' position:absolute; margin-left:15px; width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("5")){
                        retorno +="<img src='sgt_imagenes/cancel2.png' alt='' title=\"Requerimiento cancelado\" style=' position:absolute; margin-left:15px; width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("4")){
                        retorno +="<img src='sgt_imagenes/finish.png' alt='' title=\"Requerimiento finalizado\" style=' position:absolute; margin-left:15px; width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("8")){
                        retorno +="<img src='sgt_imagenes/prosess.png' alt='' title=\"Solicitud de Cambio Rechazado\" style=' position:absolute; margin-left:15px; width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("1")){
                        retorno +="<img src='sgt_imagenes/inbox.png' alt='' title=\"Requerimiento pendiente por asignar\" style=' position:absolute; margin-left:15px; width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("2")){
                        retorno +="<img src='sgt_imagenes/asignado.png' alt='' title=\"Requerimiento asignado\" style=' position:absolute; margin-left:15px; width: 20px; height: 20px;'>";
                    }
                    retorno +="</td>";                    
                    retorno +="<td class='detalleArea' >"+clasi_reque+"</td>";
                    retorno +="<td class='detalleArea'><button id='editarRequerimientoPendiente' onclick='ejecutaCodigo(this.name);' name='cod"+cod_reque+"' class='editarArea' title='Ver Detalles y Editar Status' ></button></td>";
                    
                retorno +="</tr>";
                }
                retorno+="</table>";
           
//           if(cod_reque.equals("11")){
//              retorno +="<button class='btnPaginacion' disabled id='btnPrevio'>Anterior</button>"; 
//           }
//           retorno+="&nbsp;&nbsp;&nbsp;&nbsp;<button onclick='next(this.name);' class='btnPaginacion' name='cod"+cod_reque+"' id='btnSiguiente'>Siguiente</button>";
//            
            
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
    
    public String verRequerimientosPendientesPorNombre(String nombre){
        sgtUtility util = new sgtUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        
        String retorno = "";
        String cod_reque = "";
        retorno +="";
        
                    retorno+="<table id='tablaPendiente'>";
                    retorno+="<tr>";
                    retorno+="    <th class='cabeceraArea' id='cod'>N°</th>";
                    retorno+="    <th class='cabeceraArea' id='nom'>Nombre Usuario</th>";
                    retorno+="    <th class='cabeceraArea' id='fecha'>Fecha</th>";
                    retorno+="    <th class='cabeceraArea' id='tipo'>Tipo Requerimiento</th>";
                    retorno+="<th class='cabeceraArea' id='clasi'>Clasificación</th>";
                    retorno+="<th class='cabeceraArea' id='edit'>+</th>";    
                    retorno+="</tr>";
        
            try{
                String sql ="SELECT \n" +
                            "  sgt_cod_requerimiento, \n" +
                            "  per_nombre, \n" +
                            "  sgt_fecha_requerimiento, \n" +
                            "  sgt_desc_tipo_requerimiento, \n" +
                            "  sgt_desc_clasificacion," + 
                            "  sgt_cod_status, sgt_cod_tipo_requerimieto \n" +
                            "FROM \n" +
                            "  public.t_sgt_requerimiento, \n" +
                            "  public.t_sgt_clasificacion, \n" +
                            "  public.t_sgt_tipo_requerimiento, \n" +
                            "  public.vv_personal, \n" +
                            "  public.vv_planta\n" +
                            "WHERE \n" +
                            "  t_sgt_requerimiento.sgt_cod_clasificacion_area = t_sgt_clasificacion.sgt_cod_clasificacion AND\n" +
                            "  t_sgt_requerimiento.sgt_cod_tipo_requerimieto = t_sgt_tipo_requerimiento.sgt_cod_tipo_requerimiento AND\n" +
                            "  vv_personal.per_cod_planta = t_sgt_requerimiento.per_cod_planta_solicitante AND\n" +
                            "  vv_personal.per_ficha = t_sgt_requerimiento.per_ficha_solicitante AND\n" +
                            "  vv_planta.pla_cod_planta = vv_personal.per_cod_planta AND\n" +
                            "  per_nombre like '%"+nombre+"%'\n" +
                            " ORDER BY\n" +
                            "   sgt_cod_requerimiento DESC;";           
                
                con = util.Conexion_Sorg();
                stm= con.createStatement();    
                rs = stm.executeQuery(sql);
                System.out.println("SQL PENDIENTES: " + sql);
                while(rs.next()){                    
                    cod_reque = rs.getString("sgt_cod_requerimiento");
                    String tipo_reque = rs.getString("sgt_desc_tipo_requerimiento");
                    String nombre_usu = rs.getString("per_nombre");                    
                    String cod_status = rs.getString("sgt_cod_status");
                    String fecha_reque = rs.getString("sgt_fecha_requerimiento");
                    String clasi_reque = rs.getString("sgt_desc_clasificacion");
                    String cod_tipo_reque = rs.getString("sgt_cod_tipo_requerimieto");
                    
                retorno +="<tr class='registro'>";
                    retorno +="<td class='detalleArea' >"+cod_reque+"</td>";
                    retorno +="<td class='detalleArea' >"+nombre_usu+"</td>";
                    retorno +="<td class='detalleArea' >"+fecha_reque.substring(0,16)+"</td>";
                    retorno +="<td class='detalleArea' >"+tipo_reque+"";
                    if(cod_status.equals("1") & cod_tipo_reque.equals("03") ){
                        retorno +="<img src='sgt_imagenes/clock2.png' alt='' title=\"Solicitud de Cambio Pendiente por Aprobar\" style='position:absolute; margin-left:15px; width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("6")){
                        retorno +="<img src='sgt_imagenes/check.png' alt='' title=\"Solicitud de Cambio Aprobada\" style='position:absolute; margin-left:15px; width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("7")){
                        retorno +="<img src='sgt_imagenes/cancel.png' alt='' title=\"Solicitud de Cambio Rechazado\" style=' position:absolute; margin-left:15px; width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("5")){
                        retorno +="<img src='sgt_imagenes/cancel2.png' alt='' title=\"Requerimiento cancelado\" style=' position:absolute; margin-left:15px; width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("4")){
                        retorno +="<img src='sgt_imagenes/finish.png' alt='' title=\"Requerimiento finalizado\" style=' position:absolute; margin-left:15px; width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("8")){
                        retorno +="<img src='sgt_imagenes/prosess.png' alt='' title=\"Solicitud de Cambio Rechazado\" style=' position:absolute; margin-left:15px; width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("1")){
                        retorno +="<img src='sgt_imagenes/inbox.png' alt='' title=\"Requerimiento pendiente por asignar\" style=' position:absolute; margin-left:15px; width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("2")){
                        retorno +="<img src='sgt_imagenes/asignado.png' alt='' title=\"Requerimiento asignado\" style=' position:absolute; margin-left:15px; width: 20px; height: 20px;'>";
                    }
                    retorno +="</td>";                    
                    retorno +="<td class='detalleArea' >"+clasi_reque+"</td>";
                    retorno +="<td class='detalleArea'><button id='editarRequerimientoPendiente' onclick='ejecutaCodigo(this.name);' name='cod"+cod_reque+"' class='editarArea' title='Ver Detalles y Editar Status' ></button></td>";
                    
                retorno +="</tr>";
                }
                retorno+="</table>";
           
//           if(cod_reque.equals("11")){
//              retorno +="<button class='btnPaginacion' disabled id='btnPrevio'>Anterior</button>"; 
//           }
//           retorno+="&nbsp;&nbsp;&nbsp;&nbsp;<button onclick='next(this.name);' class='btnPaginacion' name='cod"+cod_reque+"' id='btnSiguiente'>Siguiente</button>";
//            
            
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
    
    public String BuscarRequerimientos(String columna, String buscar){
        sgtUtility util = new sgtUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        
        String retorno = "";
        String cod_reque = "";
        retorno +="";       
        
            try{
                String sql ="SELECT \n" +
                            "  sgt_cod_requerimiento, \n" +
                            "  per_nombre, \n" +
                            "  sgt_fecha_requerimiento, \n" +
                            "  sgt_desc_tipo_requerimiento, \n" +
                            "  sgt_desc_clasificacion, \n" +
                            "  sgt_cod_status, sgt_cod_tipo_requerimieto \n" +
                            "FROM \n" +
                            "  public.t_sgt_requerimiento, \n" +
                            "  public.t_sgt_clasificacion, \n" +
                            "  public.t_sgt_tipo_requerimiento, \n" +
                            "  public.vv_personal\n" +
                            "WHERE \n" +
                            "  t_sgt_requerimiento.per_cod_planta_solicitante = vv_personal.per_cod_planta AND\n" +
                            "  t_sgt_requerimiento.per_ficha_solicitante = vv_personal.per_ficha AND\n" +
                            "  t_sgt_clasificacion.sgt_cod_clasificacion = t_sgt_requerimiento.sgt_cod_clasificacion_area AND\n" +
                            "  t_sgt_tipo_requerimiento.sgt_cod_tipo_requerimiento = t_sgt_requerimiento.sgt_cod_tipo_requerimieto AND\n" +
                            "  t_sgt_requerimiento.sgt_cod_requerimiento >='1' AND\n" +
                            "  "+columna+" LIKE '%"+buscar+"%'\n" +
                            "ORDER BY\n" +
                            "  sgt_cod_requerimiento;";             
                
                con = util.Conexion_Sorg();
                stm= con.createStatement();    
                rs = stm.executeQuery(sql);
                System.out.println("SQL PENDIENTES: " + sql);
                while(rs.next()){                    
                    cod_reque = rs.getString("sgt_cod_requerimiento");
                    String tipo_reque = rs.getString("sgt_desc_tipo_requerimiento");
                    String nombre_usu = rs.getString("per_nombre");                    
                    String cod_status = rs.getString("sgt_cod_status");
                    String fecha_reque = rs.getString("sgt_fecha_requerimiento");
                    String clasi_reque = rs.getString("sgt_desc_clasificacion");
                    String cod_tipo_reque = rs.getString("sgt_cod_tipo_requerimieto");
                    
                retorno +="<tr class='registro'>";
                    retorno +="<td class='detalleArea' >"+cod_reque+"</td>";
                    retorno +="<td class='detalleArea' >"+nombre_usu+"</td>";
                    retorno +="<td class='detalleArea' >"+fecha_reque.substring(0,16)+"</td>";
                    retorno +="<td class='detalleArea' >"+tipo_reque+"";
                    if(cod_status.equals("1") & cod_tipo_reque.equals("03") ){
                        retorno +="<img src='sgt_imagenes/clock2.png' alt='' title=\"Solicitud de Cambio Pendiente por Aprobar\" style='position:absolute; margin-left:15px; width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("6")){
                        retorno +="<img src='sgt_imagenes/check.png' alt='' title=\"Solicitud de Cambio Aprobada\" style='position:absolute; margin-left:15px; width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("7")){
                        retorno +="<img src='sgt_imagenes/cancel.png' alt='' title=\"Solicitud de Cambio Rechazado\" style=' position:absolute; margin-left:15px; width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("5")){
                        retorno +="<img src='sgt_imagenes/cancel2.png' alt='' title=\"Requerimiento cancelado\" style=' position:absolute; margin-left:15px; width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("4")){
                        retorno +="<img src='sgt_imagenes/finish.png' alt='' title=\"Requerimiento finalizado\" style=' position:absolute; margin-left:15px; width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("8")){
                        retorno +="<img src='sgt_imagenes/prosess.png' alt='' title=\"Solicitud de Cambio Rechazado\" style=' position:absolute; margin-left:15px; width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("1")){
                        retorno +="<img src='sgt_imagenes/inbox.png' alt='' title=\"Requerimiento pendiente por asignar\" style=' position:absolute; margin-left:15px; width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("2")){
                        retorno +="<img src='sgt_imagenes/asignado.png' alt='' title=\"Requerimiento asignado\" style=' position:absolute; margin-left:15px; width: 20px; height: 20px;'>";
                    }
                    retorno +="</td>";                    
                    retorno +="<td class='detalleArea' >"+clasi_reque+"</td>";
                    retorno +="<td class='detalleArea'><button id='editarRequerimientoPendiente' onclick='ejecutaCodigo(this.name);' name='cod"+cod_reque+"' class='editarArea' title='Ver Detalles y Editar Status' ></button></td>";
                    
                retorno +="</tr>";
                }                    
           
//           if(cod_reque.equals("11")){
//              retorno +="<button class='btnPaginacion' disabled id='btnPrevio'>Anterior</button>"; 
//           }
//           retorno+="&nbsp;&nbsp;&nbsp;&nbsp;<button onclick='next(this.name);' class='btnPaginacion' name='cod"+cod_reque+"' id='btnSiguiente'>Siguiente</button>";
//            
            
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
    
    public String verRequerimientosPendientesSiguiente(String next){
        sgtUtility util = new sgtUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        
        String siguiente = ""+next+" - 1";
        System.out.println("---------------Estoy en una pagina siguiente--------------------");
        System.out.println("Codigo del OFFSET: "+ siguiente);
        String retorno = "";
        String cod_reque = "";
        retorno +="<table id='tablaPendiente'>";
                    retorno +="<tr>";
                    retorno +="<td class='cabeceraArea' id='cod'>N°</td>";
                    retorno +="<td class='cabeceraArea' id='nom'>Nombre Usuario</td>";
                    retorno +="<td class='cabeceraArea' id='fecha'>Fecha</td>";
                    retorno +="<td class='cabeceraArea' id='tipo'>Tipo Requerimiento</td>";
                    retorno +="<td class='cabeceraArea' id='clasi'>Clasificación</td>";
                    retorno +="<td class='cabeceraArea' id='edit'>+</td>";                    
                    retorno +="</tr>";        
        
            try{
                String sql ="SELECT \n" +
                            "  sgt_cod_requerimiento, \n" +
                            "  per_nombre, \n" +
                            "  sgt_fecha_requerimiento, \n" +
                            "  sgt_desc_tipo_requerimiento, \n" +
                            "  sgt_desc_clasificacion, \n" +
                            "  sgt_cod_status, sgt_cod_tipo_requerimieto \n" +
                            "FROM \n" +
                            "  public.t_sgt_requerimiento, \n" +
                            "  public.t_sgt_clasificacion, \n" +
                            "  public.t_sgt_tipo_requerimiento, \n" +
                            "  public.vv_personal\n" +
                            "WHERE \n" +
                            "  t_sgt_requerimiento.per_cod_planta_solicitante = vv_personal.per_cod_planta AND\n" +
                            "  t_sgt_requerimiento.per_ficha_solicitante = vv_personal.per_ficha AND\n" +
                            "  t_sgt_clasificacion.sgt_cod_clasificacion = t_sgt_requerimiento.sgt_cod_clasificacion_area AND\n" +
                            "  t_sgt_tipo_requerimiento.sgt_cod_tipo_requerimiento = t_sgt_requerimiento.sgt_cod_tipo_requerimieto AND\n" +
                            "  t_sgt_requerimiento.sgt_cod_requerimiento >'1'  \n" +
                            "ORDER BY\n" +
                            "  sgt_cod_requerimiento "+
                            "LIMIT \n" +
                            "  10\n" +
                            "OFFSET\n" +
                            " "+siguiente+" ;";               
                
                con = util.Conexion_Sorg();
                stm= con.createStatement();    
                rs = stm.executeQuery(sql);
                System.out.println("SQL PENDIENTES: " + sql);
                while(rs.next()){                    
                    cod_reque = rs.getString("sgt_cod_requerimiento");
                    String tipo_reque = rs.getString("sgt_desc_tipo_requerimiento");
                    String nombre_usu = rs.getString("per_nombre");                    
                    String cod_status = rs.getString("sgt_cod_status");
                    String fecha_reque = rs.getString("sgt_fecha_requerimiento");
                    String clasi_reque = rs.getString("sgt_desc_clasificacion");
                    String cod_tipo_reque = rs.getString("sgt_cod_tipo_requerimieto");
                    
                retorno +="<tr class='registro'>";
                    retorno +="<td class='detalleArea' >"+cod_reque+"</td>";
                    retorno +="<td class='detalleArea' >"+nombre_usu+"</td>";
                    retorno +="<td class='detalleArea' >"+fecha_reque.substring(0,16)+"</td>";
                    retorno +="<td class='detalleArea' >"+tipo_reque+"";
                    if(cod_status.equals("1") & cod_tipo_reque.equals("03") ){
                        retorno +="<img src='sgt_imagenes/clock2.png' alt='' title=\"Pendiente por Aprobar\" style='position:absolute; margin-left:15px; width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("6")){
                        retorno +="<img src='sgt_imagenes/check.png' alt='' title=\"Aprobado\" style='position:absolute; margin-left:15px; width: 20px; height: 20px;'>";
                    }else if(cod_status.equals("7")){
                        retorno +="<img src='sgt_imagenes/cancel.png' alt='' title=\"Rechazado\" style=' position:absolute; margin-left:15px; width: 20px; height: 20px;'>";
                    }
                    retorno +="</td>";                    
                    retorno +="<td class='detalleArea' >"+clasi_reque+"</td>";
                    retorno +="<td class='detalleArea'><button id='editarRequerimientoPendiente' onclick='ejecutaCodigo(this.name);' name='cod"+cod_reque+"' class='editarArea' title='Ver Detalles y Editar Status' ></button></td>";
                    
                retorno +="</tr>";
                }                    
            retorno +="</table>"; 
           if(cod_reque.equals("11")){
              retorno +="<button class='btnPaginacion' disabled id='btnPrevio'>Anterior</button>"; 
           }
           retorno+="&nbsp;&nbsp;&nbsp;&nbsp;<button onclick='next(this.name);' class='btnPaginacion' name='cod"+cod_reque+"' id='btnSiguiente'>Siguiente</button>";
            
            
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
    
    public String verPersonalResponsable(String cod){
        sgtUtility util = new sgtUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        
        String retorno = "";
        retorno +="";
        String ficha_responsable ="";
        
            try{
                String sql1 = "SELECT \n" +
                                "  per_ficrep, \n" +
                                "  sgt_cod_requerimiento, per_fec_retiro \n" +
                                "FROM \n" +
                                "  public.t_sgt_requerimiento, \n" +
                                "  public.vv_personal\n" +
                                "WHERE \n" +
                                "  t_sgt_requerimiento.per_cod_planta_solicitante = vv_personal.per_cod_planta AND\n" +
                                "  t_sgt_requerimiento.per_ficha_solicitante = vv_personal.per_ficha  AND \n" +
                                "  per_fec_retiro ='NULL' AND sgt_cod_requerimiento ='"+cod+"';";
                con = util.Conexion_Sorg();
                stm= con.createStatement();    
                rs = stm.executeQuery(sql1);
                while(rs.next()){
                    ficha_responsable = rs.getString("per_ficrep");
                }
                
                String sql ="SELECT \n" +
                        "  per_nombre, per_fec_retiro \n" +
                        "FROM \n" +
                        "  public.vv_personal\n" +
                        "where\n" +
                        "  per_ficha='"+ficha_responsable+"' AND per_fec_retiro ='NULL';";
                
                
                stm= con.createStatement();    
                rs = stm.executeQuery(sql);
                System.out.println("SQL PENDIENTES: " + sql);
                while(rs.next()){
                    
                    String nombre_respon = rs.getString("per_nombre");
                    
                retorno +=nombre_respon;
                }
                
           
//           if(cod_reque.equals("11")){
//              retorno +="<button class='btnPaginacion' disabled id='btnPrevio'>Anterior</button>"; 
//           }
//           retorno+="&nbsp;&nbsp;&nbsp;&nbsp;<button onclick='next(this.name);' class='btnPaginacion' name='cod"+cod_reque+"' id='btnSiguiente'>Siguiente</button>";
//            
            
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
    
    public String verDetallesRequerimientoPendientes(String cod){
        sgtUtility util = new sgtUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        TSgtVerTipoRequerimientoLoader requerimiento=new TSgtVerTipoRequerimientoLoader();
        String planta = requerimiento.verPlanta();
        String tipo_reque = requerimiento.verTiporequerimiento();
        String cod_status = "";
        String retorno = "";
        String cod_reque = "";
        String analista_asignado="";
        retorno +="";
        String nombre_responsable= verPersonalResponsable(cod);        
        String elaborado_por = verPersonalCreador(cod);
        
        
                     retorno+="<table id='tablaPendienteDetalle'>";
                    retorno+="<tr>";
                        retorno+="<td class='cabeceraArea' style='width: 20px; text-align: center;'>Campo</td>";
                        retorno+="<td class='cabeceraArea' style='width: 550px; text-align: center;'>Detalles</td>";
                    retorno+="</tr>";
        
            try{
                String sql1 = "SELECT \n" +
                            "  per_nombre\n" +
                            "FROM \n" +
                            "  public.t_sgt_requerimiento, \n" +
                            "  public.vv_personal\n" +
                            "WHERE \n" +
                            "  t_sgt_requerimiento.sgt_ficha_analista = vv_personal.per_ficha AND\n" +
                            "  t_sgt_requerimiento.sgt_cod_planta_analista = vv_personal.per_cod_planta AND\n" +
                            "  sgt_cod_requerimiento = '"+cod+"';";
                con = util.Conexion_Sorg();
                stm= con.createStatement();    
                rs = stm.executeQuery(sql1);
                while(rs.next()){
                    analista_asignado = rs.getString("per_nombre");
                }
                
                String sql ="SELECT \n" +
                            "  sgt_cod_requerimiento, \n" +
                            "  per_nombre, \n" +
                            "  sgt_fecha_requerimiento, \n" +
                            "  sgt_desc_tipo_requerimiento, \n" +
                            "  sgt_desc_status, \n" +
                            "  sgt_desc_urgencia, "+ 
                            "  t_sgt_requerimiento.sgt_cod_status, sgt_cod_tipo_requerimieto,\n" +
                            "  sgt_desc_requerimiento, \n" +
                            "  dep_desc_dpto "+
                            "FROM \n" +
                            "  public.t_sgt_requerimiento, \n" +
                            "  public.vv_planta, \n" +
                            "  public.vv_personal, \n" +
                            "  public.t_sgt_tipo_requerimiento, \n" +
                            "  public.t_sgt_status, \n" +
                            "  public.t_sgt_urgencia, \n" +
                            "  public.vv_departamento "+
                            "WHERE \n" +
                            "  t_sgt_requerimiento.per_cod_planta_solicitante = vv_personal.per_cod_planta AND\n" +
                            "  t_sgt_requerimiento.per_ficha_solicitante = vv_personal.per_ficha AND\n" +
                            "  vv_personal.per_cod_planta = vv_departamento.dep_cod_planta AND "+
                            "  vv_personal.per_depart = vv_departamento.dep_cod_dpto AND "+
                            "  t_sgt_requerimiento.sgt_cod_urgencia = t_sgt_urgencia.sgt_cod_urgencia AND\n" +
                            "  t_sgt_requerimiento.sgt_cod_tipo_requerimieto = t_sgt_tipo_requerimiento.sgt_cod_tipo_requerimiento AND\n" +
                            "  t_sgt_requerimiento.sgt_cod_status = t_sgt_status.sgt_cod_status AND\n" +
                            "  vv_planta.pla_cod_planta = vv_personal.per_cod_planta AND\n" +
                            "  sgt_cod_requerimiento ='"+cod+"';";             
                
                
                stm= con.createStatement();    
                rs = stm.executeQuery(sql);
                System.out.println("SQL PENDIENTES: " + sql);
                String cod_tipo_reque ="";
                while(rs.next()){                    
                    cod_reque = rs.getString("sgt_cod_requerimiento");                    
                    String nombre_usu = rs.getString("per_nombre");                 
                    String fecha_reque = rs.getString("sgt_fecha_requerimiento");
                    String desc_tipo_reque = rs.getString("sgt_desc_tipo_requerimiento");
                    String desc_status = rs.getString("sgt_desc_status");                   
                    String desc_urge = rs.getString("sgt_desc_urgencia");
                    String desc_reque = rs.getString("sgt_desc_requerimiento");
                    String desc_dpto = rs.getString("dep_desc_dpto");
                    cod_status = rs.getString("sgt_cod_status");
                    cod_tipo_reque = rs.getString("sgt_cod_tipo_requerimieto");
                    retorno +="<tr class='registro'>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>Codigo:</td>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>"+cod_reque+"<button style='margin-left:20px;' onclick='EditarRequerimiento();'>Editar requerimiento</button></td>";//
                    retorno +="</tr>";
                    retorno +="<tr class='registro'>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>A Nombre de:</td>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>"+nombre_usu+"</td>";
                    retorno +="</tr>";
                    retorno +="<tr class='registro'>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>Departamento:</td>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>"+desc_dpto+"</td>";
                    retorno +="</tr>";
                    retorno +="<tr class='registro'>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>Elaborado por:</td>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>"+elaborado_por+"</td>";
                    retorno +="</tr>";
                    retorno +="<tr class='registro'>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>Fecha:</td>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>"+fecha_reque.substring(0, 16)+"</td>";
                    retorno +="</tr>";
                    retorno +="<tr class='registro'>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>Descripción:</td>";
                        retorno +="<td class='detalleArea' style='text-align: left;'><textarea rows='8' cols='250'name='lm_descripcion' readonly style='width:700px;'>"+desc_reque+"</textarea></td>";
                    retorno +="</tr>";
                    retorno +="<tr class='registro'>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>Tipo Requerimiento:</td>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>"+desc_tipo_reque+"";                        
                                if(cod_status.equals("1") & cod_tipo_reque.equals("03") ){
                                    retorno +="<label id='rPendiente'>En espera de desición <strong>"+nombre_responsable+"</strong></label><button type='button' style='margin-left:20px;' onclick='ReenviarCorreo();' >Reenviar correo</button>";
                                }else if(cod_status.equals("6")){
                                    retorno +="<label id='rAprobado'>Aprobada por <strong>"+nombre_responsable+"</strong></label>";
                                }else if(cod_status.equals("7")){
                                    retorno +="<label id='rRechazado'>Rechazada por <strong>"+nombre_responsable+"</strong></label>";
                                }
                        retorno +="</td>";
                    retorno +="</tr>";
//                    retorno +="<tr class='registro'>";
//                        retorno +="<td class='detalleArea' style='text-align: left;'>Clasificación:</td>";
//                        retorno +="<td class='detalleArea' style='text-align: left;'>"+desc_clasi+"</td>";
//                    retorno +="</tr>";
                    retorno +="<tr class='registro'>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>Status:</td>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>"+desc_status+" &nbsp;&nbsp;";
                                if(analista_asignado !=null){
                                    retorno+="["+analista_asignado+"]";
                                }
                        retorno+="</td>";
                    retorno +="</tr>";
                    retorno +="<tr class='registro'>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>Urgencia:</td>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>"+desc_urge+" &nbsp;&nbsp;&nbsp;</td>";
                    retorno +="</tr>";
                }                    
                    
                retorno+="</table>";
                retorno+="<br>";             
                // System.out.println("Codigo de estatus: " + cod_status);
              retorno+="<section style='margin-left: 50px; margin-top: -20px;width: 900px;'>";
              if(cod_status.equals("1") & cod_tipo_reque.equals("03")){
                  retorno+="<button class='claseBtn' onclick='atras();' style='margin-left:152px;'>Ir Atras</button>"; 
              }else if(cod_status.equals("1") || cod_status.equals("6") & cod_tipo_reque.equals("03")){
                  retorno+="<label id='tituloAsignar'>Asignar al analista</label><br>";
                    retorno+="<hr style='width: 250px; margin-left: 0px;'>";
                    retorno+="<label>Planta:</label><br>";                    
                    retorno+=""+planta+"<br>";
                    retorno+="<label style='position: relative; margin-top: 10px;'>Analista:</label>";
                    retorno+="<div id='analista' name='lm_analista_asignado' style='margin-top:5px;'>";
                        retorno+="<select id='selectT' disabled='true'>";
                            retorno+="<option value='su'> Seleccione Uno(a)</option>";
                        retorno+="</select>";
                    retorno+="</div>";
                    retorno+="<input type='button' class='claseBtn' style='margin-left: -160px; margin-top: 10px;' onclick='Asignar();' value='Asignar Ticket'>";                    
                    retorno+="<input type='button' id='cancelarTicket' value='Cancelar Ticket' onclick='Cancelar();'>";
                    retorno+="<button class='claseBtn' onclick='atras();' style='margin-left: 152px;'>Ir Atras</button>"; 
              }else if(cod_status.equals("2") || cod_status.equals("3") || cod_status.equals("8")){
                    retorno+="<textarea rows='5' cols='15' placeholder='Ingrese una descripcion para comentar o finalizar' name='textSolucion' id='textSolucion'></textarea>";
                    retorno+="<input type='button' class='claseBtn'  style='margin-left: 150px; top:150px;' value='Comentar' onclick='Comentar();'><br>";
                    retorno+="<input type='button' class='claseBtn'  style='margin-left: 150px; top:160px;' value='Finalizar Ticket' onclick='Finalizar();'>";                    
                    retorno+="<button class='claseBtn' onclick='atras();' style='margin-left: 152px;top: 150px;'>Ir Atras</button>"; 
              }else if(cod_status.equals("4") || cod_status.equals("5") || cod_status.equals("7")){                    
                    retorno+="<button class='claseBtn' onclick='atras();' style='margin-left: 152px;'>Ir Atras</button>"; 
              }                
                       
                retorno+="</section>";
                
                
                
//           if(cod_reque.equals("11")){
//              retorno +="<button class='btnPaginacion' disabled id='btnPrevio'>Anterior</button>"; 
//           }
//           retorno+="&nbsp;&nbsp;&nbsp;&nbsp;<button onclick='next(this.name);' class='btnPaginacion' name='cod"+cod_reque+"' id='btnSiguiente'>Siguiente</button>";
//            
            
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
    
    public String verDetallesHistorial(String cod){
        sgtUtility util = new sgtUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        TSgtVerTipoRequerimientoLoader requerimiento=new TSgtVerTipoRequerimientoLoader();
        String planta = requerimiento.verPlanta();
        String cod_status = "";
        String retorno = "";
        String cod_reque = "";
        String analista_asignado="";
        retorno +="";
        String elaborado_por = verPersonalCreador(cod);
        
        
                     retorno+="<table id='tablaPendienteDetalle'>";
                    retorno+="<tr>";
                        retorno+="<td class='cabeceraArea' style='width: 20px; text-align: center;'>Campo</td>";
                        retorno+="<td class='cabeceraArea' style='width: 550px; text-align: center;'>Detalles</td>";
                    retorno+="</tr>";
        
            try{
                String sql1 = "SELECT \n" +
                            "  per_nombre\n" +
                            "FROM \n" +
                            "  public.t_sgt_requerimiento, \n" +
                            "  public.vv_personal\n" +
                            "WHERE \n" +
                            "  t_sgt_requerimiento.sgt_ficha_analista = vv_personal.per_ficha AND\n" +
                            "  t_sgt_requerimiento.sgt_cod_planta_analista = vv_personal.per_cod_planta AND\n" +
                            "  sgt_cod_requerimiento = '"+cod+"';";
                con = util.Conexion_Sorg();
                stm= con.createStatement();    
                rs = stm.executeQuery(sql1);
                while(rs.next()){
                    analista_asignado = rs.getString("per_nombre");
                }
                
                String sql ="SELECT \n" +
                            "  sgt_cod_requerimiento, \n" +
                            "  per_nombre, \n" +
                            "  sgt_fecha_requerimiento, \n" +
                            "  sgt_desc_tipo_requerimiento, \n" +
                            "  sgt_desc_status, \n" +
//                            "  sgt_desc_clasificacion, \n" +
                            "  sgt_desc_urgencia, "+ 
                            "  t_sgt_requerimiento.sgt_cod_status, sgt_cod_tipo_requerimieto,\n" +
                            "  sgt_desc_requerimiento, \n" +
                            "  dep_desc_dpto "+
                            "FROM \n" +
                            "  public.t_sgt_requerimiento, \n" +
                            "  public.vv_planta, \n" +
                            "  public.vv_personal, \n" +
                            "  public.t_sgt_tipo_requerimiento, \n" +
                            "  public.t_sgt_status, \n" +
//                            "  public.t_sgt_clasificacion, \n" +
                            "  public.t_sgt_urgencia, \n" +
                            "  public.vv_departamento "+
                            "WHERE \n" +
                            "  t_sgt_requerimiento.per_cod_planta_solicitante = vv_personal.per_cod_planta AND\n" +
                            "  t_sgt_requerimiento.per_ficha_solicitante = vv_personal.per_ficha AND\n" +
                            "  vv_personal.per_cod_planta = vv_departamento.dep_cod_planta AND "+
                            "  vv_personal.per_depart = vv_departamento.dep_cod_dpto AND "+
//                            "  t_sgt_requerimiento.sgt_cod_clasificacion_area = t_sgt_clasificacion.sgt_cod_clasificacion AND\n" +
                            "  t_sgt_requerimiento.sgt_cod_urgencia = t_sgt_urgencia.sgt_cod_urgencia AND\n" +
                            "  t_sgt_requerimiento.sgt_cod_tipo_requerimieto = t_sgt_tipo_requerimiento.sgt_cod_tipo_requerimiento AND\n" +
                            "  t_sgt_requerimiento.sgt_cod_status = t_sgt_status.sgt_cod_status AND\n" +
                            "  vv_planta.pla_cod_planta = vv_personal.per_cod_planta AND\n" +
                            "  sgt_cod_requerimiento ='"+cod+"';";             
                
               
                stm= con.createStatement();    
                rs = stm.executeQuery(sql);
                System.out.println("SQL PENDIENTES: " + sql);
                while(rs.next()){                    
                    cod_reque = rs.getString("sgt_cod_requerimiento");                    
                    String nombre_usu = rs.getString("per_nombre");                 
                    String fecha_reque = rs.getString("sgt_fecha_requerimiento");
                    String desc_tipo_reque = rs.getString("sgt_desc_tipo_requerimiento");
                    String desc_status = rs.getString("sgt_desc_status");
//                    String desc_clasi = rs.getString("sgt_desc_clasificacion");                    
                    String desc_urge = rs.getString("sgt_desc_urgencia");
                    String desc_reque = rs.getString("sgt_desc_requerimiento");
                    String desc_dpto = rs.getString("dep_desc_dpto");
                    cod_status = rs.getString("sgt_cod_status");
                    String cod_tipo_reque = rs.getString("sgt_cod_tipo_requerimieto");
                    retorno +="<tr class='registro'>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>Codigo:</td>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>"+cod_reque+"</td>";
                    retorno +="</tr>";
                    retorno +="<tr class='registro'>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>A Nombre de:</td>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>"+nombre_usu+"</td>";
                    retorno +="</tr>";
                    retorno +="<tr class='registro'>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>Departamento:</td>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>"+desc_dpto+"</td>";
                    retorno +="</tr>";
                    retorno +="<tr class='registro'>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>Elaborado por:</td>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>"+elaborado_por+"</td>";
                    retorno +="</tr>";
                    retorno +="<tr class='registro'>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>Fecha:</td>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>"+fecha_reque.substring(0, 16)+"</td>";
                    retorno +="</tr>";
                    retorno +="<tr class='registro'>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>Descripción:</td>";
                        retorno +="<td class='detalleArea' style='text-align: left;'><textarea rows='8' cols='250'name='lm_descripcion' readonly style='width:700px;'>"+desc_reque+"</textarea></td>";
                    retorno +="</tr>";
                    retorno +="<tr class='registro'>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>Tipo Requerimiento:</td>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>"+desc_tipo_reque+"";                        
                                if(cod_status.equals("1") & cod_tipo_reque.equals("03") ){
                                    retorno +="<label id='rPendiente'>Pendiente por Decisión </label>";
                                }else if(cod_status.equals("6")){
                                    retorno +="<label id='rAprobado'>Aprobada</label>";
                                }else if(cod_status.equals("7")){
                                    retorno +="<label id='rRechazado'>Rechazada</label>";
                                }
                        retorno +="</td>";
                    retorno +="</tr>";
//                    retorno +="<tr class='registro'>";
//                        retorno +="<td class='detalleArea' style='text-align: left;'>Clasificación:</td>";
//                        retorno +="<td class='detalleArea' style='text-align: left;'>"+desc_clasi+"</td>";
//                    retorno +="</tr>";
                    retorno +="<tr class='registro'>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>Status:</td>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>"+desc_status+" &nbsp;&nbsp;";
                                if(analista_asignado !=null){
                                    retorno+="["+analista_asignado+"]";
                                }
                        retorno+="</td>";
                    retorno +="</tr>";
                    retorno +="<tr class='registro'>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>Urgencia:</td>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>"+desc_urge+" &nbsp;&nbsp;&nbsp;</td>";
                    retorno +="</tr>";
                }                    
                    
                retorno+="</table>";
                retorno+="<br>";             
                // System.out.println("Codigo de estatus: " + cod_status);
              retorno+="<section style='margin-left: 50px;width: 900px;'>"; 
              
                    retorno+="<button class='claseBtn' onclick='atras();' style='margin-left: 152px;'>Ir Atras</button>"; 
                                   
                retorno+="</section>";
                
                
                
//           if(cod_reque.equals("11")){
//              retorno +="<button class='btnPaginacion' disabled id='btnPrevio'>Anterior</button>"; 
//           }
//           retorno+="&nbsp;&nbsp;&nbsp;&nbsp;<button onclick='next(this.name);' class='btnPaginacion' name='cod"+cod_reque+"' id='btnSiguiente'>Siguiente</button>";
//            
            
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
    
    public String verPersonalCreador(String cod){
        sgtUtility util = new sgtUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        
        String retorno = "";
        retorno +="";
        
        
            try{
                String sql ="SELECT \n" +
                            "  sgt_cod_requerimiento, \n" +
                            "  per_nombre\n" +
                            "FROM \n" +
                            "  public.t_sgt_requerimiento, \n" +
                            "  public.vv_usuario_contrasena, \n" +
                            "  public.vv_personal\n" +
                            "WHERE \n" +
                            "  t_sgt_requerimiento.sgt_usuario_auditoria = vv_usuario_contrasena.uco_cod_usuario AND\n" +
                            "  vv_usuario_contrasena.uco_cod_planta = vv_personal.per_cod_planta AND\n" +
                            "  vv_usuario_contrasena.uco_ficha_emp = vv_personal.per_ficha AND\n" +
                            "  sgt_cod_requerimiento ='"+cod+"'\n" +
                            "ORDER BY \n" +
                            "  sgt_cod_requerimiento;";
                
                con = util.Conexion_Sorg();
                stm= con.createStatement();    
                rs = stm.executeQuery(sql);
                //System.out.println("SQL PENDIENTES: " + sql);
                while(rs.next()){
                    
                    String nombre_usu = rs.getString("per_nombre");
                    
                retorno +=nombre_usu;
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
        
    public String verDetallesRequerimientoAsignado(String cod){
        sgtUtility util = new sgtUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        TSgtVerTipoRequerimientoLoader requerimiento=new TSgtVerTipoRequerimientoLoader();
        String planta = requerimiento.verPlanta();
        String cod_status = "";
        String retorno = "";
        String cod_reque = "";
        retorno +="";
        String analista_asignado="";
        String elaborado_por = verPersonalCreador(cod);
        
        
                     retorno+="<table id='tablaPendienteDetalle'>";
                    retorno+="<tr>";
                        retorno+="<td class='cabeceraArea' style='width: 20px; text-align: center;'>Campo</td>";
                        retorno+="<td class='cabeceraArea' style='width: 550px; text-align: center;'>Detalles</td>";
                    retorno+="</tr>";
        
            try{
                String sql1 = "SELECT \n" +
                            "  per_nombre\n" +
                            "FROM \n" +
                            "  public.t_sgt_requerimiento, \n" +
                            "  public.vv_personal\n" +
                            "WHERE \n" +
                            "  t_sgt_requerimiento.sgt_ficha_analista = vv_personal.per_ficha AND\n" +
                            "  t_sgt_requerimiento.sgt_cod_planta_analista = vv_personal.per_cod_planta AND\n" +
                            "  sgt_cod_requerimiento = '"+cod+"';";
                con = util.Conexion_Sorg();
                stm= con.createStatement();    
                rs = stm.executeQuery(sql1);
                while(rs.next()){
                    analista_asignado = rs.getString("per_nombre");
                }
                
                String sql ="SELECT \n" +
                            "  sgt_cod_requerimiento, \n" +
                            "  per_nombre, \n" +
                            "  sgt_fecha_requerimiento, \n" +
                            "  sgt_desc_tipo_requerimiento, \n" +
                            "  sgt_desc_status, \n" +
//                            "  sgt_desc_clasificacion, \n" +
                            "  sgt_desc_urgencia, "+ 
                            "  t_sgt_requerimiento.sgt_cod_status, sgt_cod_tipo_requerimieto,\n" +
                            "  sgt_desc_requerimiento, \n" +
                            "  dep_desc_dpto "+
                            "FROM \n" +
                            "  public.t_sgt_requerimiento, \n" +
                            "  public.vv_planta, \n" +
                            "  public.vv_personal, \n" +
                            "  public.t_sgt_tipo_requerimiento, \n" +
                            "  public.t_sgt_status, \n" +
//                            "  public.t_sgt_clasificacion, \n" +
                            "  public.t_sgt_urgencia, \n" +
                            "  public.vv_departamento "+
                            "WHERE \n" +
                            "  t_sgt_requerimiento.per_cod_planta_solicitante = vv_personal.per_cod_planta AND\n" +
                            "  t_sgt_requerimiento.per_ficha_solicitante = vv_personal.per_ficha AND\n" +
                            "  vv_personal.per_cod_planta = vv_departamento.dep_cod_planta AND "+
                            "  vv_personal.per_depart = vv_departamento.dep_cod_dpto AND "+
//                            "  t_sgt_requerimiento.sgt_cod_clasificacion_area = t_sgt_clasificacion.sgt_cod_clasificacion AND\n" +
                            "  t_sgt_requerimiento.sgt_cod_urgencia = t_sgt_urgencia.sgt_cod_urgencia AND\n" +
                            "  t_sgt_requerimiento.sgt_cod_tipo_requerimieto = t_sgt_tipo_requerimiento.sgt_cod_tipo_requerimiento AND\n" +
                            "  t_sgt_requerimiento.sgt_cod_status = t_sgt_status.sgt_cod_status AND\n" +
                            "  vv_planta.pla_cod_planta = vv_personal.per_cod_planta AND\n" +
                            "  sgt_cod_requerimiento ='"+cod+"';";             
                
                
                stm= con.createStatement();    
                rs = stm.executeQuery(sql);
                //System.out.println("SQL PENDIENTES: " + sql);
                while(rs.next()){                    
                    cod_reque = rs.getString("sgt_cod_requerimiento");                    
                    String nombre_usu = rs.getString("per_nombre");                 
                    String fecha_reque = rs.getString("sgt_fecha_requerimiento");
                    String desc_tipo_reque = rs.getString("sgt_desc_tipo_requerimiento");
                    String desc_status = rs.getString("sgt_desc_status");
//                    String desc_clasi = rs.getString("sgt_desc_clasificacion");                    
                    String desc_urge = rs.getString("sgt_desc_urgencia");
                    String desc_reque = rs.getString("sgt_desc_requerimiento");
                    cod_status = rs.getString("sgt_cod_status");
                    String desc_dpto = rs.getString("dep_desc_dpto");
                    String cod_tipo_reque = rs.getString("sgt_cod_tipo_requerimieto");
                    retorno +="<tr class='registro'>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>Codigo:</td>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>"+cod_reque+"<button style='margin-left:20px;' onclick='EditarRequerimiento();'>Editar requerimiento</button></td>";
                    retorno +="</tr>";
                    retorno +="<tr class='registro'>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>A Nombre de:</td>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>"+nombre_usu+"</td>";
                    retorno +="</tr>";
                    retorno +="<tr class='registro'>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>Departamento:</td>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>"+desc_dpto+"</td>";
                    retorno +="</tr>";
                    retorno +="<tr class='registro'>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>Elaborado por:</td>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>"+elaborado_por+"</td>";
                    retorno +="</tr>";
                    retorno +="<tr class='registro'>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>Fecha:</td>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>"+fecha_reque.substring(0, 16)+"</td>";
                    retorno +="</tr>";
                    retorno +="<tr class='registro'>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>Descripción:</td>";
                        retorno +="<td class='detalleArea' style='text-align: left;'><textarea rows='8' cols='250'name='lm_descripcion' readonly style='width:700px;'>"+desc_reque+"</textarea></td>";
                    retorno +="</tr>";
                    retorno +="<tr class='registro'>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>Tipo Requerimiento:</td>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>"+desc_tipo_reque+"";                        
                                if(cod_status.equals("1") & cod_tipo_reque.equals("03") ){
                                    retorno +="<label id='rPendiente'>Pendiente por Decisión </label>";
                                }else if(cod_status.equals("6")){
                                    retorno +="<label id='rAprobado'>Aprobada</label>";
                                }else if(cod_status.equals("7")){
                                    retorno +="<label id='rRechazado'>Rechazada</label>";
                                }
                        retorno +="</td>";
                    retorno +="</tr>";
//                    retorno +="<tr class='registro'>";
//                        retorno +="<td class='detalleArea' style='text-align: left;'>Clasificación:</td>";
//                        retorno +="<td class='detalleArea' style='text-align: left;'>"+desc_clasi+"</td>";
//                    retorno +="</tr>";
                    retorno +="<tr class='registro'>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>Status:</td>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>"+desc_status+" &nbsp;&nbsp;";
                                if(analista_asignado !=null){
                                    retorno+="["+analista_asignado+"]";
                                }
                        retorno+="</td>";
                    retorno +="</tr>";
                    retorno +="<tr class='registro'>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>Urgencia:</td>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>"+desc_urge+" &nbsp;&nbsp;&nbsp;</td>";
                    retorno +="</tr>";
                }                    
                    
                retorno+="</table>";
                retorno+="<br>";             
                // System.out.println("Codigo de estatus: " + cod_status);
              retorno+="<section style='margin-left: 50px;width: 900px; margin-top:-20px;'>"; 
              if(!cod_status.equals("7") && !cod_status.equals("4")){
                  retorno+="<label id='tituloAsignar'>Re-asignar ticket</label><br>";
                    retorno+="<hr style='width: 250px; margin-left: 0px;'>";
                    retorno+="<label>Planta:</label><br>";                    
                    retorno+=""+planta+"<br>";
                    retorno+="<label style='position: relative; margin-top: 10px;'>Analista:</label>";
                    retorno+="<div id='analista' name='lm_analista_asignado' style='margin-top:5px;'>";
                        retorno+="<select id='selectT' disabled='true'>";
                            retorno+="<option value='su'> Seleccione Uno(a)</option>";
                        retorno+="</select>";
                    retorno+="</div>";
                    retorno+="<input type='button' class='claseBtn' style='margin-left: -160px; margin-top: 10px;' onclick='Asignar();' value='Re-asignar Ticket'>";        
                    retorno+="<textarea style='margin-top:-100px;' rows='5' cols='15' placeholder='Ingrese una descripcion para comentar o finalizar' name='textSolucion' id='textSolucion'></textarea>";
                    retorno+="<input type='button' class='claseBtn'  style='margin-left: 150px; top:-10px;' value='Comentar' onclick='Comentar();'><br>";
                    retorno+="<input type='button' class='claseBtn'  style='margin-left: 165px;' value='Finalizar Ticket' onclick='Finalizar();'>";       
                    retorno+="<input type='button' id='cancelarTicket' value='Cancelar Ticket' onclick='Cancelar();'>";
                    retorno+="<button class='claseBtn' onclick='atras();' style='margin-left: 152px;'>Ir Atras</button>"; 
              } else{
                  retorno+="<button class='claseBtn' onclick='atras();' style='margin-left: 152px;'>Ir Atras</button>";
              }                      
                retorno+="</section>";
                
                
                
//           if(cod_reque.equals("11")){
//              retorno +="<button class='btnPaginacion' disabled id='btnPrevio'>Anterior</button>"; 
//           }
//           retorno+="&nbsp;&nbsp;&nbsp;&nbsp;<button onclick='next(this.name);' class='btnPaginacion' name='cod"+cod_reque+"' id='btnSiguiente'>Siguiente</button>";
//            
            
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
    
    public String verDetallesRequerimientoPendientesUsuarioLog(String cod){
        sgtUtility util = new sgtUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        TSgtVerTipoRequerimientoLoader requerimiento=new TSgtVerTipoRequerimientoLoader();
        String planta = requerimiento.verPlanta();
        String cod_status = "";
        String retorno = "";
        String cod_reque = "";
        retorno +="";
        
        
                     retorno+="<table id='tablaPendienteDetalle'>";
                    retorno+="<tr>";
                        retorno+="<td class='cabeceraArea' style='width: 20px; text-align: center;'>Campo</td>";
                        retorno+="<td class='cabeceraArea' style='width: 550px; text-align: center;'>Detalles</td>";
                    retorno+="</tr>";
        
            try{
                String sql ="SELECT \n" +
                            "  sgt_cod_requerimiento, \n" +
                            "  per_nombre, \n" +
                            "  sgt_fecha_requerimiento, \n" +
                            "  sgt_desc_tipo_requerimiento, \n" +
                            "  sgt_desc_status, \n" +
//                            "  sgt_desc_clasificacion, \n" +
                            "  sgt_desc_urgencia, "+ 
                            "  t_sgt_requerimiento.sgt_cod_status, sgt_cod_tipo_requerimieto,\n" +
                            "  sgt_desc_requerimiento\n" +
                            "FROM \n" +
                            "  public.t_sgt_requerimiento, \n" +
                            "  public.vv_planta, \n" +
                            "  public.vv_personal, \n" +
                            "  public.t_sgt_tipo_requerimiento, \n" +
                            "  public.t_sgt_status, \n" +
//                            "  public.t_sgt_clasificacion, \n" +
                            "  public.t_sgt_urgencia\n" +
                            "WHERE \n" +
                            "  t_sgt_requerimiento.per_cod_planta_solicitante = vv_personal.per_cod_planta AND\n" +
                            "  t_sgt_requerimiento.per_ficha_solicitante = vv_personal.per_ficha AND\n" +
//                            "  t_sgt_requerimiento.sgt_cod_clasificacion_area = t_sgt_clasificacion.sgt_cod_clasificacion AND\n" +
                            "  t_sgt_requerimiento.sgt_cod_urgencia = t_sgt_urgencia.sgt_cod_urgencia AND\n" +
                            "  t_sgt_requerimiento.sgt_cod_tipo_requerimieto = t_sgt_tipo_requerimiento.sgt_cod_tipo_requerimiento AND\n" +
                            "  t_sgt_requerimiento.sgt_cod_status = t_sgt_status.sgt_cod_status AND\n" +
                            "  vv_planta.pla_cod_planta = vv_personal.per_cod_planta AND\n" +
                            "  sgt_cod_requerimiento ='"+cod+"';";             
                
                con = util.Conexion_Sorg();
                stm= con.createStatement();    
                rs = stm.executeQuery(sql);
                //System.out.println("SQL PENDIENTES: " + sql);
                while(rs.next()){                    
                    cod_reque = rs.getString("sgt_cod_requerimiento");                    
                    String nombre_usu = rs.getString("per_nombre");                 
                    String fecha_reque = rs.getString("sgt_fecha_requerimiento");
                    String desc_tipo_reque = rs.getString("sgt_desc_tipo_requerimiento");
                    String desc_status = rs.getString("sgt_desc_status");
//                    String desc_clasi = rs.getString("sgt_desc_clasificacion");                    
                    String desc_urge = rs.getString("sgt_desc_urgencia");
                    String desc_reque = rs.getString("sgt_desc_requerimiento");
                    cod_status = rs.getString("sgt_cod_status");
                    String cod_tipo_reque = rs.getString("sgt_cod_tipo_requerimieto");
                    retorno +="<tr class='registro'>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>Codigo:</td>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>"+cod_reque+"</td>";
                    retorno +="</tr>";
                    retorno +="<tr class='registro'>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>A Nombre de:</td>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>"+nombre_usu+"</td>";
                    retorno +="</tr>";
                    retorno +="<tr class='registro'>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>Fecha:</td>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>"+fecha_reque.substring(0, 16)+"</td>";
                    retorno +="</tr>";
                    retorno +="<tr class='registro'>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>Descripción:</td>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>"+desc_reque+"</td>";
                    retorno +="</tr>";
                    retorno +="<tr class='registro'>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>Tipo Requerimiento:</td>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>"+desc_tipo_reque+"";                        
                                if(cod_status.equals("1") & cod_tipo_reque.equals("03") ){
                                    retorno +="<label id='rPendiente'>Pendiente por Decisión </label>";
                                }else if(cod_status.equals("6")){
                                    retorno +="<label id='rAprobado'>Aprobada</label>";
                                }else if(cod_status.equals("7")){
                                    retorno +="<label id='rRechazado'>Rechazada</label>";
                                }
                        retorno +="</td>";
                    retorno +="</tr>";
//                    retorno +="<tr class='registro'>";
//                        retorno +="<td class='detalleArea' style='text-align: left;'>Clasificación:</td>";
//                        retorno +="<td class='detalleArea' style='text-align: left;'>"+desc_clasi+"</td>";
//                    retorno +="</tr>";
                    retorno +="<tr class='registro'>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>Status:</td>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>"+desc_status+"</td>";
                    retorno +="</tr>";
                    retorno +="<tr class='registro'>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>Urgencia:</td>";
                        retorno +="<td class='detalleArea' style='text-align: left;'>"+desc_urge+"</td>";
                    retorno +="</tr>";
                }                    
                    
                retorno+="</table>";       
                retorno+="<button class='claseBtn' onclick='atras();' style='margin-left: 152px;'>Ir Atras</button>"; 
                
                
//           if(cod_reque.equals("11")){
//              retorno +="<button class='btnPaginacion' disabled id='btnPrevio'>Anterior</button>"; 
//           }
//           retorno+="&nbsp;&nbsp;&nbsp;&nbsp;<button onclick='next(this.name);' class='btnPaginacion' name='cod"+cod_reque+"' id='btnSiguiente'>Siguiente</button>";
//            
            
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
            
    public String verPersonalAsignar(String cod_planta){
    sgtUtility util = new sgtUtility();
    Connection con = null;
    Statement stm=null;
    ResultSet rs=null;
    String retorno="<select id='selectT' name='lm_ficha_analista' >";
    retorno+="<option value='su'>Seleccione Uno(a)</option>";
//    String cod_depto ="";
//    if(cod_planta.equals("02")){
//        cod_depto="306";
//    }else if(cod_planta.equals("05")){
//        cod_depto="269";
//    }
        try {
            String sql = "SELECT \n" +
                        "  sgt_cod_planta_analista, \n" +
                        "  sgt_ficha_analista, \n" +
                        "  sgt_nombre_analista\n" +
                        "FROM \n" +
                        "  public.t_sgt_area_analista\n" +
                        "WHERE \n" +
                        "  sgt_cod_planta_analista='"+cod_planta+"'\n" +
                        "ORDER BY \n" +
                        "  sgt_nombre_analista ASC;";
            con = util.Conexion_Sorg();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while(rs.next()){
                String nombre =rs.getString("sgt_nombre_analista");
                String ficha = rs.getString("sgt_ficha_analista");
                retorno+="<option value='"+ ficha +"'>"+nombre+"</option>";
                
            }
          retorno+="</select>";
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
    
    public String BuscarTiporequerimiento(){
    sgtUtility util = new sgtUtility();
    Connection con = null;
    Statement stm=null;
    ResultSet rs=null;
    String retorno="<select id='selectT' onchange='cargaTablaTipoXML(this.value);' name='lm_tipo_requerimiento'>";
    retorno+="<option value='su'>Seleccione Uno(a)</option>";
    
        try {
            String sql = "select sgt_cod_tipo_requerimiento, sgt_desc_tipo_requerimiento from t_sgt_tipo_requerimiento";
            con = util.Conexion_Sorg();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while(rs.next()){
                String tipo=rs.getString("sgt_desc_tipo_requerimiento");
                String cod_tipo = rs.getString("sgt_cod_tipo_requerimiento");
                retorno+="<option value='"+ cod_tipo +"'>"+tipo+"</option>";
                
            }
          retorno+="</select>";
          retorno+="<a href='Controller?event=PENDIENTES_REQUERIMIENTO'><input type='button' value='Cancelar busqueda' id='btnCancelarBusqueda'></a>";
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
    
    public String BuscarClasificacion(){
        sgtUtility util = new sgtUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String retorno = "<select id='selectC' onchange='cargaTablaClasificacionXML(this.value)' disable='false' name='lm_clasificacion'>";
        retorno+="<option value='su'>Seleccione Uno(a)</option>";
        
            try{
                String sql ="SELECT \n" +
                            "  sgt_desc_clasificacion, \n" +
                            "  sgt_cod_clasificacion\n" +
                            "FROM \n" +
                            "  public.t_sgt_clasificacion\n" +
                            "ORDER BY \n" +
                            "  sgt_desc_clasificacion;";
                con = util.Conexion_Sorg();
                stm= con.createStatement();
                rs = stm.executeQuery(sql);
                while(rs.next()){
                    String select_cau = rs.getString("sgt_desc_clasificacion");
                    String cod_cla = rs.getString("sgt_cod_clasificacion");
                    retorno+="<option value='"+cod_cla+"'>"+select_cau+"</option>";
                }
                retorno+="</select>";
                retorno+="<a href='Controller?event=PENDIENTES_REQUERIMIENTO'><input type='button' value='Cancelar busqueda' id='btnCancelarBusqueda'></a>";
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
    
    public String verAreaAnalista(String ficha, String planta){
        sgtUtility util = new sgtUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        
        String retorno = "";
        retorno +="";
        
        
            try{
                String sql ="SELECT \n" +
                            "  t_sgt_area_analista.sgt_cod_area, \n" +
                            "  sgt_ficha_analista, \n" +
                            "  sgt_cod_planta_analista\n" +
                            "FROM \n" +
                            "  public.t_sgt_area_analista, \n" +
                            "  public.t_sgt_area\n" +
                            "WHERE \n" +
                            "  t_sgt_area_analista.sgt_cod_area = t_sgt_area.sgt_cod_area AND\n" +
                            "  sgt_ficha_analista='"+ficha+"' AND\n" +
                            "  sgt_cod_planta_analista ='"+planta+"' ;";
                
                con = util.Conexion_Sorg();
                stm= con.createStatement();    
                rs = stm.executeQuery(sql);
                System.out.println("SQL PENDIENTES: " + sql);
                while(rs.next()){
                    
                    String cod_area = rs.getString("sgt_cod_area");
                    
                retorno +=cod_area;
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
    
    public void insertarAsignacionRequerimiento(int cod_reque, String ficha_asig, String planta_asig, String ficha_log, String planta_log, String area_asig){

    sgtUtility util = new sgtUtility();

    java.util.Date today=new java.util.Date();        
    Connection con =null;
    String status ="2";
    String log ="03";
    PreparedStatement pstmt=null;
    con=util.Conexion_Sorg();
    
    try {
        
        System.out.println("-----------------Asignando el requerimiento---------");
        String sql ="INSERT INTO t_sgt_log_requerimiento(\n" +
                    "            sgt_cod_requerimiento, sgt_fecha_registro, sgt_cod_log, sgt_ficha_analista, sgt_planta_analista, sgt_ficha_usulog, sgt_planta_usulog)\n" +
                    "    VALUES (?, ?, ?, ?, ?, ?, ?);";

        String sql1="UPDATE t_sgt_requerimiento\n" +
                      "   SET sgt_ficha_analista=?, sgt_cod_planta_analista=?, sgt_cod_area_analista=?, sgt_cod_status=? " +
                      " WHERE sgt_cod_requerimiento='"+cod_reque+"';";
                   

        pstmt=con.prepareStatement(sql1);
        pstmt.setString(1, ficha_asig);
        pstmt.setString(2, planta_asig);
        pstmt.setString(3, area_asig);
        pstmt.setString(4, status);
        pstmt.executeUpdate();
        
        System.out.println("Guardando el log del requerimiento: " + sql);
        pstmt=con.prepareStatement(sql);
        pstmt.setInt(1, cod_reque);
        pstmt.setTimestamp(2, new java.sql.Timestamp(today.getTime()));
        pstmt.setString(3, log);
        pstmt.setString(4, ficha_asig);
        pstmt.setString(5, planta_asig);
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
    
    public void insertarCancelarRequerimiento(int cod_reque, String usuario_log, String ficha_log, String planta_log){

    sgtUtility util = new sgtUtility();

    java.util.Date today=new java.util.Date();        
    Connection con =null;
    String status = "5";
    String log ="05";
    PreparedStatement pstmt=null;
    con=util.Conexion_Sorg();
    
    String sql ="INSERT INTO t_sgt_log_requerimiento(\n" +
                "            sgt_cod_requerimiento, sgt_fecha_registro, sgt_cod_log, sgt_ficha_usulog, sgt_planta_usulog)\n" +
                "    VALUES (?, ?, ?, ?, ?);";

    String sql1="UPDATE t_sgt_requerimiento\n" +
                  "   SET sgt_cod_status=? " +
                  " WHERE sgt_cod_requerimiento='"+cod_reque+"';";
    try {               

        pstmt=con.prepareStatement(sql1);
        pstmt.setString(1, status);
        pstmt.executeUpdate();

        pstmt=con.prepareStatement(sql);
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
    
    public void insertarFinalizarRequerimiento(int cod_reque, String usuario_log, int cod_solu, String solucion, String ficha_log, String planta_log){

    sgtUtility util = new sgtUtility();

    java.util.Date today=new java.util.Date();
    String id_solu = Integer.toString(cod_solu);
    Connection con =null;
    String status = "4";
    String log ="06";
    PreparedStatement pstmt=null;
    con=util.Conexion_Sorg();
    
    String sql ="INSERT INTO t_sgt_log_requerimiento(\n" +
                "            sgt_cod_requerimiento, sgt_fecha_registro, sgt_cod_log, sgt_ficha_analista, sgt_planta_analista, sgt_ficha_usulog, sgt_planta_usulog, sgt_descripcion)\n" +
                "    VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

    String sql1="UPDATE t_sgt_requerimiento\n" +
                  "   SET sgt_cod_solucion_requerimieto=?, sgt_cod_status=?, sgt_fecha_cierre=? " +
                  " WHERE sgt_cod_requerimiento='"+cod_reque+"';";
    
    String sql2="INSERT INTO t_sgt_solucion_requerimiento( " +
                " sgt_cod_solucion_requerimiento, sgt_como, sgt_usuario_auditoria, " +
                " sgt_fecha_auditoria) " +
                " VALUES (?, ?, ?, ?); ";
    
    try {
        System.out.println("-----SOLUCION REQUERIMIENTO----- "+ solucion);
        pstmt= con.prepareStatement(sql2);
        pstmt.setInt(1, cod_solu);
        pstmt.setString(2, solucion);
        pstmt.setString(3, usuario_log);
        pstmt.setTimestamp(4, new java.sql.Timestamp(today.getTime()));
        pstmt.executeUpdate();
        
        
        pstmt=con.prepareStatement(sql1);
        pstmt.setString(1, id_solu);
        pstmt.setString(2, status);
        pstmt.setTimestamp(3, new java.sql.Timestamp(today.getTime()));
        pstmt.executeUpdate();

        pstmt=con.prepareStatement(sql);
        pstmt.setInt(1, cod_reque);
        pstmt.setTimestamp(2, new java.sql.Timestamp(today.getTime()));
        pstmt.setString(3, log);
        pstmt.setString(4, ficha_log);
        pstmt.setString(5, planta_log);
        pstmt.setString(6, ficha_log);
        pstmt.setString(7, planta_log);
        pstmt.setString(8, solucion);
        pstmt.executeUpdate();



    } catch (SQLException e) {
        System.out.println("ERROR: "+e);
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
    
    public void insertarComentarioRequerimiento(int cod_reque, String usuario_log, String comentario, String ficha_log, String planta_log) throws SQLException{

    sgtUtility util = new sgtUtility();

    java.util.Date today=new java.util.Date();
    
    Connection con =null;
     Statement stm = null;
     ResultSet rs = null;
    String log ="07";
    PreparedStatement pstmt=null;
    con=util.Conexion_Sorg();
    String sql ="INSERT INTO t_sgt_log_requerimiento(\n" +
"            sgt_cod_requerimiento, sgt_fecha_registro, sgt_cod_log, sgt_descripcion, \n" +
"            sgt_ficha_analista, sgt_planta_analista, sgt_ficha_usulog, sgt_planta_usulog)\n" +
"    VALUES (?, ?, ?, ?, \n" +
"            ?, ?, ?, ?);";
    
     
    try {
        
        
        pstmt=con.prepareStatement(sql);
        pstmt.setInt(1, cod_reque);
        pstmt.setTimestamp(2, new java.sql.Timestamp(today.getTime()));
        pstmt.setString(3, log);
        pstmt.setString(4, comentario);
        pstmt.setString(5, ficha_log);
        pstmt.setString(6, planta_log);
        pstmt.setString(7, ficha_log);
        pstmt.setString(8, planta_log);
        
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
    
    public void CorreoRequerimientoAsignado(int cod_reque, String ficha_asig, String ficha_log, String planta_asig, String planta_log) {
        
        sgtUtility util = new sgtUtility();
        TSgtVerificarUsuarioLoader ver = new TSgtVerificarUsuarioLoader(); 
        Connection con= util.Conexion_Sorg();
        Statement stmt=null;
        ResultSet rs=null;
        String nombre="";
        String clasi ="";
        String to = ver.VerificarCorreo(planta_asig, ficha_asig);
        String cod = Integer.toString(cod_reque);
        String elaborado_por = verPersonalCreador(cod);
        
        try{
            System.out.println("----------CREANDO CORREO DE ASIGNACION------------");
                               
        //Sql para saber las fichas y los correos de los Responsables de Indicadores
        String sql="SELECT \n" +
                    "  sgt_cod_requerimiento, \n" +
                    "  per_nombre, \n" +
                    "  sgt_desc_clasificacion, \n" +
                    "  sgt_desc_requerimiento\n" +
                    "FROM \n" +
                    "  public.t_sgt_requerimiento, \n" +
                    "  public.vv_personal, \n" +
                    "  public.t_sgt_clasificacion\n" +
                    "WHERE \n" +
                    "  t_sgt_requerimiento.per_cod_planta_solicitante = vv_personal.per_cod_planta AND\n" +
                    "  t_sgt_requerimiento.per_ficha_solicitante = vv_personal.per_ficha AND\n" +
                    "  t_sgt_requerimiento.sgt_cod_clasificacion_area = t_sgt_clasificacion.sgt_cod_clasificacion AND\n" +
                    "  sgt_cod_requerimiento = '"+cod_reque+"';";
        
        stmt=con.createStatement();
        rs=stmt.executeQuery(sql);
        while(rs.next()){
            
            nombre=rs.getString("per_nombre");
            clasi = rs.getString("sgt_desc_clasificacion");
            String desc_reque = rs.getString("sgt_desc_requerimiento");
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
                                "        <td id=\"titulo\" style=\"padding-top: 20px;text-align: left; font: bold 13px sans-serif; width: 100px;padding-left: 10px;\">Codigo:</td>\n" +
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
                                "        <td id=\"titulo\" style=\"padding-top: 20px;text-align: left; font: bold 13px sans-serif; width: 100px;padding-left: 10px;\">Clasificaci&oacute;n:</td>\n" +
                                "        <td id=\"detalleReque\" style=\"padding-top: 20px;font-size: 14px; text-align:left; font-family: sans-serif;\">"+clasi+"</td>\n" +
                                "    </tr>\n" +
                                "    <tr>\n" +
                                "        <td id=\"titulo\" style=\"padding-top: 20px;text-align: left; font: bold 13px sans-serif; width: 100px;padding-left: 10px;\">Descripci&oacute;n:</td>\n" +
                                "        <td id=\"detalleReque\" style=\"padding-top: 20px;font-size: 14px; text-align:left; font-family: sans-serif;\">"+desc_reque+"</td>\n" +
                                "    </tr>\n" +
                                "  </table>\n" +
                                "  <a href='http://10.183.9.20:8080/sgt_website/index.jsp'><input style=\"width: 462px; height: 45px; background-color: #8A0808; border: 1px solid #8A0808;  color: white;\" type=\"submit\" value=\"Iniciar sesi&oacute;n para ver detalles\"></a>\n" +
                                "  \n" +
                                "</body>\n" +
                                "</html>";
         Send_correo_asignado(mensaje, to, planta_asig, nombre);
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
       
    public void CorreoRequerimientoFinalizado(int cod_reque) {
        
        sgtUtility util = new sgtUtility();
        TSgtVerificarUsuarioLoader ver = new TSgtVerificarUsuarioLoader(); 
        Connection con= util.Conexion_Sorg();
        Statement stmt=null;
        ResultSet rs=null;
        String nombre="";
        String clasi ="";
        
        
        try{
                               
        //Sql para saber las fichas y los correos de los Responsables de Indicadores
        String sql="SELECT \n" +
"                      sgt_cod_requerimiento, \n" +
"                      per_nombre,\n" +
"                      per_cod_planta_solicitante,\n" +
"                      per_ficha_solicitante, \n" +
"                      sgt_desc_clasificacion, \n" +
"                      sgt_desc_requerimiento\n" +
"                    FROM \n" +
"                      public.t_sgt_requerimiento, \n" +
"                      public.vv_personal, \n" +
"                      public.t_sgt_clasificacion\n" +
"                    WHERE \n" +
"                      t_sgt_requerimiento.per_cod_planta_solicitante = vv_personal.per_cod_planta AND\n" +
"                      t_sgt_requerimiento.per_ficha_solicitante = vv_personal.per_ficha AND\n" +
"                      t_sgt_requerimiento.sgt_cod_clasificacion_area = t_sgt_clasificacion.sgt_cod_clasificacion AND\n" +
"                      sgt_cod_requerimiento = '"+cod_reque+"';";
        
        stmt=con.createStatement();
        rs=stmt.executeQuery(sql);
        while(rs.next()){
            
            nombre=rs.getString("per_nombre");
            clasi = rs.getString("sgt_desc_clasificacion");
            String desc_reque = rs.getString("sgt_desc_requerimiento");
            String planta_usu = rs.getString("per_cod_planta_solicitante");
            String ficha_usu = rs.getString("per_ficha_solicitante");
            String mensaje ="<!DOCTYPE html>\n" +
                            "<html>\n" +
                            "    <head>\n" +
                            "        <title></title>\n" +
                            "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\n" +
                            "        \n" +
                            "    </head>\n" +
                            "    <body>\n" +
                            "        <div style=\"background-color: #ECECEC; width: 500px; height: auto;\">\n" +
                            "            <div style=\"width: 0; height: 0; border-top: 30px solid #fff; border-right: 38px solid transparent; position: absolute;  \"></div>\n" +
                            "            <div style=\"width: 0; height: 0; border-bottom: 30px solid #000; border-left: 38px solid transparent; position: absolute; opacity: 0.5;\"></div>\n" +
                            "            <h2 style=\" padding: 10px; background-color: #8A0808; text-align: center; font-weight: 300; color: white; font: 22px sans-serif; height: auto\">Requerimiento Finalizado</h2>\n" +
                            "            <h4 style=\"text-align: center; background-color: #ECECEC; color: #8A0808; font-weight: 300; padding: 10px; font-family: sans-serif;\">Nos complace informarle que su requerimiento ha sido finalizado con éxito, ante cualquier duda llama al <strong>2073</strong>.</h4>\n" +
                            "            <table style=\"width: 100%;\">\n" +
                            "                <caption style=\"background-color: #ECECEC; color: #8A0808; padding: 5px; font: bold 18px sans-serif;\">Datos del requerimiento:</caption>\n" +
                            "                <tbody>\n" +
                            "                    <tr>\n" +
                            "                        <td style=\" padding: 5px; width:100px; text-align: right; font:14px sans-serif;\"><strong>Código:</strong></td>\n" +
                            "                        <td  style=\" padding: 5px; width:325px; text-align: left; font:14px sans-serif;\">"+cod_reque+"</td>\n" +
                            "                    </tr>\n" +
                            "                    <tr>\n" +
                            "                        <td  style=\" padding: 5px; width:100px; text-align: right; font:14px sans-serif;\"><strong>Nombre:</strong></td>\n" +
                            "                        <td  style=\" padding: 5px; width:225px; text-align: left; font:14px sans-serif;\">"+nombre+"</td>\n" +
                            "                    </tr>\n" +
                            "                    <tr>\n" +
                            "                        <td  style=\" padding: 5px; width:100px; text-align: right; font:14px sans-serif;\"><strong>Clasificación:</strong></td>\n" +
                            "                        <td  style=\" padding: 5px; width:225px; text-align: left; font:14px sans-serif;\">"+clasi+"</td>\n" +
                            "                    </tr>\n" +
                            "                    <tr>\n" +
                            "                        <td  style=\" padding: 5px; width:100px; text-align: right; font:14px sans-serif;\"><strong>Descripción:</strong></td>\n" +
                            "                        <td  style=\" padding: 5px; width:225px; text-align: left; font:14px sans-serif;\">"+desc_reque+"</td>\n" +
                            "                    </tr>\n" +
                            "                </tbody>\n" +
                            "            </table>\n" +
                            "            <h4 style=\"text-align: center; background-color: #ECECEC; color: #8A0808; font-weight: 300; padding: 10px; font: 12px sans-serif;\">Gerencia Corporativa de Tecnología</h4>            \n" +
                            "        </div>\n" +
                            "    </body>\n" +
                            "</html>";
            String to = ver.VerificarCorreo(planta_usu, ficha_usu);
            //String to = "mario.ortega@venvidrio.com.ve";
            Send_correo_finalizado(mensaje, to, planta_usu, nombre);
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
    
    public static void Send_correo_asignado(String mensaje, String to, String planta, String nombre){
        System.out.println("------------ENVIANDO EL CORREO ASIGNADO---------------");
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
                message.setSubject("Nuevo Requerimiento Asignado a nombre de: "+nombre);
            //set content of the message in html format
            message.setContent(mensaje,"text/html");

                // Send message
               Transport.send(message);
               System.out.println("Mensaje de correo enviado");
            } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
  
    public static void Send_correo_finalizado(String mensaje, String to, String planta, String nombre){
        
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
                message.setSubject("Requerimiento finalizado");
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
