/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venvidrio.sgt.loader;
import static com.venvidrio.sgt.loader.TSgtPendientesLoader.Send_correo_finalizado;
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
public class TSgtActualizarLoader {
    
    private static String hostLGV="maillgv.venvidrio.com.ve";
    private static String hostVLR="mailvlr.venvidrio.com.ve";
    private static String smtpPort="25";
    private static String from="atencion.soporte@venvidrio.com.ve";
    
    public int obtenerCodAutoAnalistaArea(){
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
            ls_sql="select max(sgt_cod_area_analista) from t_sgt_area_analista;";
            
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
    
    public String verArea(){
        sgtUtility util = new sgtUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String retorno = "";
        


            try{
                String sql ="SELECT sgt_cod_area, sgt_desc_area\n" +
                            "  FROM t_sgt_area WHERE sgt_desc_area != 'TODAS';";
                con = util.Conexion_Sorg();
                stm= con.createStatement();
                rs = stm.executeQuery(sql);
                System.out.println("SQL: " + sql);
                while(rs.next()){
                    String cod_area = rs.getString("sgt_cod_area");
                    String desc_area = rs.getString("sgt_desc_area");

                    retorno+="<tr class='registro'>";
                    retorno+="<td class='detalleArea'>"+cod_area+"</td>";
                    retorno+="<td class='detalleArea'>"+desc_area+"</td>";
                    retorno+="<td class='detalleAreaEdit'><a ><img src='sgt_imagenes/edit.png' class='editarArea'  id='editarArea"+desc_area+"' title='Editar Registro' name='lm_cod_area_editar"+cod_area+"' onclick='fjs_editarArea(this.name,this.id);'></a></td>";
                    retorno+="<td class='detalleAreaEdit'><a ><img src='sgt_imagenes/eliminar.png' id='eliminarArea' name='lm_cod_area"+cod_area+"' onclick='fjs_eliminarArea(this.name);' title='Eliminar Registro'></a></td>";
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
    
    public String VerSiguienteCodArea(){
        Connection con;
        Statement stmt=null;
        ResultSet rs = null;
        //("voy a hacer la conexion a la SGT_BD");
        con = new sgtUtility().Conexion_Sorg();
        String ls_sql="";
        String max="";
        String codigo= "";
        int cod=0;
        int id = 0;

        try {
            stmt = con.createStatement();
            ls_sql="select max(sgt_cod_area) from t_sgt_area;";
            
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
                System.out.println("Codigo de area 1: " + id);
            }else if( cod >= 1){                 
                 id = cod;
                 System.out.println("Codigo de area >1: " + id);
             }
            codigo = Integer.toString(id);
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
        
        return codigo ;
    }
    
    public String verAreaAnalista(){
        sgtUtility util = new sgtUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String retorno = "";
        


            try{
                String sql ="SELECT \n" +
"                              sgt_cod_area_analista,\n" +
"                              pla_nom_planta,\n" +
"                              per_ficha, \n" +
"                              per_nombre, \n" +
"                              sgt_desc_area, \n" +
"                              sgt_desc_tipo_analista\n" +
"                            FROM \n" +
"                              t_sgt_area_analista, \n" +
"                              vv_personal, \n" +
"                              t_sgt_area, \n" +
"                              t_sgt_tipo_analista,\n" +
"                              vv_planta\n" +
"                            WHERE \n" +
"			      t_sgt_area_analista.sgt_cod_planta_analista = vv_planta.pla_cod_planta AND\n" +
"                              t_sgt_area_analista.sgt_cod_planta_analista = vv_personal.per_cod_planta AND\n" +
"                              t_sgt_area_analista.sgt_ficha_analista = vv_personal.per_ficha AND\n" +
"                              t_sgt_area_analista.sgt_cod_area = t_sgt_area.sgt_cod_area AND\n" +
"                              t_sgt_area_analista.sgt_cod_tipo_analista = t_sgt_tipo_analista.sgt_cod_tipo_analista ORDER BY sgt_cod_area_analista;";
                con = util.Conexion_Sorg();
                stm= con.createStatement();
                rs = stm.executeQuery(sql);
                System.out.println("SQL: " + sql);
                while(rs.next()){
                    String nombre_planta = rs.getString("pla_nom_planta");
                    String ficha = rs.getString("per_ficha");
                    String nombre = rs.getString("per_nombre");
                    String area = rs.getString("sgt_desc_area");
                    String tipo_area = rs.getString("sgt_desc_tipo_analista");

                    retorno+="<tr class='registro'>";
                    retorno+="<td class='detalleArea'>"+ficha+"<input type='hidden' value='01' id='codigo_area' name=''></td>";
                    retorno+="<td class='detalleArea'>"+nombre_planta+"<input type='hidden' value='CAU' id='descripcion_area'></td>";
                    retorno+="<td class='detalleArea'>"+nombre+"<input type='hidden' value='CAU' id='descripcion_area'></td>";
                    retorno+="<td class='detalleArea'>"+area+"<input type='hidden' value='CAU' id='descripcion_area'></td>";
                    retorno+="<td class='detalleArea'>"+tipo_area+"<input type='hidden' value='CAU' id='descripcion_area'></td>";
                    retorno+="<td class='detalleAreaEdit'><a ><img src='sgt_imagenes/edit.png' style='left: 2px;' class='editarArea' id='editarArea' title='Editar Registro'></a></td>";
                    retorno+="<td class='detalleAreaEdit'><a ><img src='sgt_imagenes/eliminar.png' style='left: 1px;' id='eliminarArea' title='Eliminar Registro'></a></td>";
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
    
    public void insertarArea(String cod_area, String desc_area){               
        sgtUtility util = new sgtUtility();
               
        Connection con =null;
        PreparedStatement pstmt=null;
        con=util.Conexion_Sorg();

        String sql="INSERT INTO t_sgt_area(\n" +
                    "            sgt_cod_area, sgt_desc_area)\n" +
                    "    VALUES (?, ?);";
        
        try {               
           
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1, cod_area);
            pstmt.setString(2, desc_area.toUpperCase());
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
    
    public void insertarAnalistaArea(String cod, String cod_planta, String ficha, String cod_area, String cod_tipo){               
        sgtUtility util = new sgtUtility();
               
        Connection con =null;
        PreparedStatement pstmt=null;
        con=util.Conexion_Sorg();
      
        String sql="INSERT INTO t_sgt_area_analista(\n" +
                   "            sgt_cod_area_analista, sgt_cod_planta_analista, sgt_ficha_analista, \n" +
                   "            sgt_cod_area, sgt_cod_tipo_analista)\n" +
                   "    VALUES (?, ?, ?, \n" +
                   "            ?, ?);";
        
        try {               
           
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1, cod);
            pstmt.setString(2, cod_planta);
            pstmt.setString(3, ficha);
            pstmt.setString(4, cod_area);
            pstmt.setString(5, cod_tipo);
            
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
        
    public void eliminarArea(String cod_area){               
        sgtUtility util = new sgtUtility();
               
        Connection con =null;
        Statement stmt=null;
        con=util.Conexion_Sorg();
        int rows=0; 
        String sql="DELETE FROM t_sgt_area\n" +
                    " WHERE sgt_cod_area='"+cod_area+"';";
        
        try {               
          stmt = con.createStatement();
          rows = stmt.executeUpdate(sql);
            
        } catch (SQLException e) {
           e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }finally {                       
            try {
               //if (rs != null) rs.close();
               if (stmt != null) stmt.close();
               if (con != null) con.close();
               } catch (SQLException e) {
                   System.out.println("Loader:: SQLException " + e.toString());
               }
           }
              
   }
    
    public void editarArea(String cod_area, String desc_area){               
        sgtUtility util = new sgtUtility();
               
        Connection con =null;
        Statement stmt=null;
        con=util.Conexion_Sorg();
        int rows=0; 
        String sql="UPDATE t_sgt_area\n" +
                    "   SET sgt_desc_area='"+desc_area.toUpperCase()+"'\n" +
                    " WHERE sgt_cod_area='"+cod_area+"';";
        
        try {               
          stmt = con.createStatement();
          rows = stmt.executeUpdate(sql);
            
        } catch (SQLException e) {
           e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }finally {                       
            try {
               //if (rs != null) rs.close();
               if (stmt != null) stmt.close();
               if (con != null) con.close();
               } catch (SQLException e) {
                   System.out.println("Loader:: SQLException " + e.toString());
               }
           }
              
   }
    
    public void editarUrgencia(String cod_reque, String cod_urgencia){               
        sgtUtility util = new sgtUtility();
               
        Connection con =null;
        Statement stmt=null;
        con=util.Conexion_Sorg();
        int rows=0; 
        String sql="UPDATE t_sgt_requerimiento\n" +
                    "   SET sgt_cod_urgencia= '" + cod_urgencia+"' \n" +
                    " WHERE sgt_cod_requerimiento='"+cod_reque+"';";
        
        try {               
          stmt = con.createStatement();
          rows = stmt.executeUpdate(sql);
            
        } catch (SQLException e) {
           e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }finally {                       
            try {
               //if (rs != null) rs.close();
               if (stmt != null) stmt.close();
               if (con != null) con.close();
               } catch (SQLException e) {
                   System.out.println("Loader:: SQLException " + e.toString());
               }
           }
              
   }
    
    public void actualizarStatusProcesar(String cod_reque, String status, String ficha_log, String planta_log){               
        sgtUtility util = new sgtUtility();
        java.util.Date today=new java.util.Date(); 
               
        Connection con =null;
        PreparedStatement pstmt=null;
        con=util.Conexion_Sorg();
        int cod = Integer.parseInt(cod_reque);
        String log="09";
        
        String sql="UPDATE t_sgt_requerimiento \n" +
                    " SET sgt_cod_status=? \n" +
                    " WHERE sgt_cod_requerimiento='"+cod_reque+"';";
        
        String sql1 ="INSERT INTO t_sgt_log_requerimiento(\n" +
                "            sgt_cod_requerimiento, sgt_fecha_registro, sgt_cod_log, sgt_ficha_analista, sgt_planta_analista, sgt_ficha_usulog, sgt_planta_usulog)\n" +
                "    VALUES (?, ?, ?, ?, ?, ?, ?);";
        try {               
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1, status);
            pstmt.executeUpdate();
          
            pstmt=con.prepareStatement(sql1);
            pstmt.setInt(1, cod);
            pstmt.setTimestamp(2, new java.sql.Timestamp(today.getTime()));
            pstmt.setString(3, log);
            pstmt.setString(4, ficha_log);
            pstmt.setString(5, planta_log);
            pstmt.setString(6, ficha_log);
            pstmt.setString(7, planta_log);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("ERROR CAMBIO A PROCESAR: "+e);
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
    
    public void CorreoRequerimientoProcesando(String cod_reque) {
        
        sgtUtility util = new sgtUtility();
        TSgtVerificarUsuarioLoader ver = new TSgtVerificarUsuarioLoader(); 
        Connection con= util.Conexion_Sorg();
        Statement stmt=null;
        ResultSet rs=null;
        String nombre="";
        String clasi ="";
        String name_ana="";
        
        try{
            //Sql para saber los datos del requerimiento
        String sql1="SELECT \n" +
                    "  per_nombre, \n" +
                    "  sgt_cod_requerimiento\n" +
                    "FROM \n" +
                    "  public.t_sgt_requerimiento, \n" +
                    "  public.vv_personal\n" +
                    "WHERE \n" +
                    "  t_sgt_requerimiento.sgt_ficha_analista = vv_personal.per_ficha AND\n" +
                    "  t_sgt_requerimiento.sgt_cod_planta_analista = vv_personal.per_cod_planta AND\n" +
                    "  t_sgt_requerimiento.sgt_cod_requerimiento = '"+cod_reque+"';";
        
        stmt=con.createStatement();
        rs=stmt.executeQuery(sql1);
        while(rs.next()){
            name_ana= rs.getString("per_nombre");
        }
            
                               
        //Sql para saber los datos del requerimiento
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
                            "        <meta charset=\"utf-8\">\n" +
                            "        \n" +
                            "    </head>\n" +
                            "    <body>\n" +
                            "        <div style=\"background-color: #ECECEC; width: 550px; height: auto;\">\n" +
                            "            <div style=\"width: 0; height: 0; border-top: 30px solid #fff; border-right: 38px solid transparent; position: absolute;  \"></div>\n" +
                            "            <div style=\"width: 0; height: 0; border-bottom: 30px solid #000; border-left: 38px solid transparent; position: absolute; opacity: 0.5;\"></div>\n" +
                            "            <h2 style=\" padding: 10px; background-color: #8A0808; text-align: center; font-weight: 300; color: white; font: 22px sans-serif; height: auto\">Procesando Requerimiento</h2>\n" +
                            "            <h4 style=\"text-align: center; background-color: #ECECEC; color: #8A0808; font-weight: 300; padding: 10px; font-family: sans-serif;\">Nos complace informale que en este momento su requerimiento esta siendo procesado, ante cualquier duda llama al <strong>2073</strong>.</h4>\n" +
                            "            <table style=\"width: 100%;\">\n" +
                            "                <caption style=\"background-color: #ECECEC; color: #8A0808; padding: 5px; font: bold 18px sans-serif;\">Datos del requerimiento:</caption>\n" +
                            "                <tbody>\n" +
                            "                    <tr>\n" +
                            "                        <td style=\" padding: 5px; width:100px; text-align: right; font:14px sans-serif;\"><strong>C&oacute;digo:</strong></td>\n" +
                            "                        <td  style=\" padding: 5px; width:325px; text-align: left; font:14px sans-serif;\">"+cod_reque+"</td>\n" +
                            "                    </tr>\n" +
                            "                    <tr>\n" +
                            "                        <td  style=\" padding: 5px; width:100px; text-align: right; font:14px sans-serif;\"><strong>Nombre:</strong></td>\n" +
                            "                        <td  style=\" padding: 5px; width:225px; text-align: left; font:14px sans-serif;\">"+nombre+"</td>\n" +
                            "                    </tr>\n" +
                            "                    <tr>\n" +
                            "                        <td  style=\" padding: 5px; width:100px; text-align: right; font:14px sans-serif;\"><strong>Clasificaci&oacute;n:</strong></td>\n" +
                            "                        <td  style=\" padding: 5px; width:225px; text-align: left; font:14px sans-serif;\">"+clasi+"</td>\n" +
                            "                    </tr>\n" +
                            "                    <tr>\n" +
                            "                        <td  style=\" padding: 5px; width:100px; text-align: right; font:14px sans-serif;\"><strong>Descripci&oacute;n:</strong></td>\n" +
                            "                        <td  style=\" padding: 5px; width:225px; text-align: left; font:14px sans-serif;\">"+desc_reque+"</td>\n" +
                            "                    </tr>\n" +
                            "                    <tr>\n" +
                            "                        <td  style=\" padding: 5px; width:100px; text-align: right; font:14px sans-serif;\"><strong>Analista a cargo:</strong></td>\n" +
                            "                        <td  style=\" padding: 5px; width:225px; text-align: left; font:14px sans-serif;\">"+name_ana+"</td>\n" +
                            "                    </tr>\n" +
                            "                </tbody>\n" +
                            "            </table>\n" +
                            "            <h4 style=\"text-align: center; background-color: #ECECEC; color: #8A0808; font-weight: 300; padding: 10px; font: 12px sans-serif;\">Gerencia Corporativa de Tecnolog&iacute;a</h4>            \n" +
                            "        </div>\n" +
                            "    </body>\n" +
                            "</html>";
            String to = ver.VerificarCorreo(planta_usu, ficha_usu);
            Send_correo_procesando(mensaje, to, planta_usu, name_ana);
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
    
    public String verDetallesRequerimientoEditar(String cod){
        sgtUtility util = new sgtUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        TSgtVerTipoRequerimientoLoader requerimiento=new TSgtVerTipoRequerimientoLoader();
        String planta = requerimiento.verPlanta();
        String tipo_reque = requerimiento.verTiporequerimiento();        
        String retorno = "";
        String cod_reque = "";
        retorno +="";
        
            try{
                
                String sql ="SELECT \n" +
"                              pla_nom_planta, \n" +
"                              per_nombre, \n" +
"                              dep_desc_dpto,\n" +
"                              sgt_desc_tipo_requerimiento, \n" +
"                              per_ficha_solicitante, \n" +
"                              sgt_desc_urgencia, \n" +
"                              sgt_desc_clasificacion,\n" +
"                              sgt_desc_area, \n" +
"                              sgt_desc_requerimiento, \n" +
"                              sgt_cod_tipo_requerimieto, \n" +
"                              t_sgt_requerimiento.sgt_cod_urgencia, \n" +
"                              sgt_cod_clasificacion_area, sgt_area_requerimiento \n" +
"                            FROM \n" +
"                              public.t_sgt_requerimiento, \n" +
"                              public.t_sgt_clasificacion, \n" +
"                              public.t_sgt_urgencia, \n" +
"                              public.vv_personal, \n" +
"                              public.vv_planta, \n" +
"                              public.vv_departamento,\n" +
"                              public.t_sgt_area,\n" +
"                              public.t_sgt_tipo_requerimiento\n" +
"                            WHERE \n" +
"                              t_sgt_requerimiento.per_cod_planta_solicitante = vv_personal.per_cod_planta AND\n" +
"                              t_sgt_requerimiento.per_ficha_solicitante = vv_personal.per_ficha AND\n" +
"                              t_sgt_requerimiento.sgt_cod_clasificacion_area = t_sgt_clasificacion.sgt_cod_clasificacion AND\n" +
"                              t_sgt_requerimiento.sgt_area_requerimiento = t_sgt_area.sgt_cod_area AND\n" +
"                              t_sgt_requerimiento.sgt_cod_urgencia = t_sgt_urgencia.sgt_cod_urgencia AND\n" +
"                              t_sgt_requerimiento.sgt_cod_tipo_requerimieto = t_sgt_tipo_requerimiento.sgt_cod_tipo_requerimiento AND\n" +
"                              vv_personal.per_depart = vv_departamento.dep_cod_dpto AND\n" +
"                              vv_personal.per_cod_planta = vv_departamento.dep_cod_planta AND\n" +
"                              vv_personal.per_cod_planta = vv_planta.pla_cod_planta AND \n" +
"                              sgt_cod_requerimiento ='"+cod+"';";             
                
                con = util.Conexion_Sorg();
                stm= con.createStatement();    
                rs = stm.executeQuery(sql);
                System.out.println("SQL PENDIENTES: " + sql);
                while(rs.next()){                   
                    String nombre_usu = rs.getString("per_nombre");                 
                    String planta_usu = rs.getString("pla_nom_planta");
                    String dpto_usu = rs.getString("dep_desc_dpto");
                    String ficha_usu = rs.getString("per_ficha_solicitante");             
                    String desc_urge = rs.getString("sgt_desc_urgencia");
                    String desc_reque = rs.getString("sgt_desc_requerimiento");
                    String desc_tipo_reque = rs.getString("sgt_desc_tipo_requerimiento");
                    String cod_urge = rs.getString("sgt_cod_urgencia");
                    String cod_tipo_reque = rs.getString("sgt_cod_tipo_requerimieto");
                    String cod_area_reque = rs.getString("sgt_area_requerimiento");
                    String cod_clas_reque = rs.getString("sgt_cod_clasificacion_area");
                    String editar_clasi = verClasificacionRequerimiento(cod_clas_reque);
                    String editar_area = verAreaRequerimiento(cod_area_reque);
                    String editar_tipo = verTipoRequerimiento(cod_tipo_reque);
                    String editar_urge = verUrgenciaRequerimiento(cod_urge);
                    
                    retorno+="<tr>";
                            retorno+="<td>";
                                retorno+="<label>Planta:</label>";
                            retorno+="</td>";
                            retorno+="<td>";
                                retorno+="<div name='lm_planta'>";
                                    retorno+="<label>"+planta_usu+"</label>";
                                retorno+="</div>";
                            retorno+="</td>";
                        retorno+="</tr>";
                        retorno+="<tr>";
                            retorno+="<td>";
                                retorno+="<label for='depto'>Departamento:</label>";
                            retorno+="</td>";
                            retorno+="<td>";
                                retorno+="<div name='lm_dpto'>";
                                    retorno+="<label>"+dpto_usu+"</label>";
                                retorno+="</div>";
                            retorno+="</td>";
                        retorno+="</tr>";
                        retorno+="<tr>";
                            retorno+="<td>";
                                retorno+="<label for='nombre'>Nombre:</label>";
                            retorno+="</td>";
                            retorno+="<td>";
                                retorno+="<div name='lm_nombre'>";
                                    retorno+="<label>"+nombre_usu+"</label>";
                                retorno+="</div>";
                            retorno+="</td>";
                        retorno+="</tr>";
                        retorno+="<tr>";
                            retorno+="<td><label for='ficha'>N° de Ficha:</label></td>";
                            retorno+="<td>";
                                retorno+="<div name=lm_ficha'>";
                                    retorno+="<label>"+ficha_usu+"</label>";
                                 retorno+="</div>";
                            retorno+="</td>";
                        retorno+="</tr>";
                    retorno+="</table>";
                retorno+="</fieldset>";
                retorno+="<fieldset id='datosRequerimiento'>";
                    retorno+="<legend id='tituloR'>&nbsp; Datos del Requerimiento</legend>";
                    retorno+="<table>";
                    retorno+="<tr>";
                            retorno+="<td>Codigo: </td>";
                            retorno+="<td>";
                                retorno+="<div name='lm_cod'>";
                                    retorno+="<label>"+cod+"</label>";
                                retorno+="</div>";
                            retorno+="</td>";
                        retorno+="</tr>";
                        retorno+="<tr>";
                            retorno+="<td>Urgencia: </td>";
                            retorno+="<td>";
                                retorno+="<div name='lm_urgencia'>";
                                    retorno+="<label>"+editar_urge+"</label>";
                                retorno+="</div>";
                            retorno+="</td>";
                        retorno+="</tr>";
                        retorno+="<tr>";
                            retorno+="<td>";
                                retorno+="<label id='txtTipo'>Tipo de Requerimiento:</label>";
                            retorno+="</td>";
                            retorno+="<td >";
                                retorno+="<div name='lm_tipo_requerimiento'>";
                                    retorno+="<label>"+editar_tipo+"</label>";
                                retorno+="</div>";
                            retorno+="</td>";
                        retorno+="</tr>";
                        retorno+="<tr>";
                            retorno+="<td>";
                                retorno+="<label>Area:</label>";
                            retorno+="</td>";
                            retorno+="<td>";
                                retorno+="<div id='area' name='lm_area_requerimiento'>";
                                     retorno+="<label>"+editar_area+"</label>";
                                retorno+="</div>";
                            retorno+="</td>";
                        retorno+="</tr>";
                        retorno+="<tr>";
                            retorno+="<td><label id='txtClasi'>Clasificación:</label></td>";
                            retorno+="<td>";
                                retorno+="<div id='clasificacion' name='lm_clasificacion'>";
                                    retorno+="<label>"+editar_clasi+"</label>";
                                retorno+="</div>";
                            retorno+="</td>";
                        retorno+="</tr>";
                        retorno+="<tr id='descrip'>";
                            retorno+="<td>";
                                retorno+="<label id='txtDesc'>Descripción:</label>";
                            retorno+="</td>";
                            retorno+="<td>";
                                retorno+="<textarea rows='15' cols='250'name='lm_descripcion' style='width:500px;'>"+desc_reque+"</textarea>";
                            retorno+="</td>";
                        retorno+="</tr>";
                    
                } 
               
            }catch(SQLException e){
                System.out.println("ERROR CONSULTA EDITAR REQ: "+e);
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
    
    public void ActualizarRequerimiento(String cod_reque, String cod_urge, String tipo_reque, String area_reque, String clasi_reque, String desc_reque, String ficha_log, String planta_log){               
        sgtUtility util = new sgtUtility();
        java.util.Date today=new java.util.Date();                
        Connection con =null;
        PreparedStatement pstmt=null;
        con=util.Conexion_Sorg();
        int cod_requerimiento = Integer.parseInt(cod_reque);
        String log ="10";
        
        String sql="UPDATE t_sgt_requerimiento\n" +
                    "   SET sgt_cod_tipo_requerimieto=?, sgt_desc_requerimiento=?,\n" +
                    "       sgt_cod_urgencia=?, sgt_cod_clasificacion_area=?, sgt_area_requerimiento=?\n" +
                    " WHERE sgt_cod_requerimiento='"+cod_reque+"';";
        
        String sql2 ="INSERT INTO t_sgt_log_requerimiento(\n" +
                    "            sgt_cod_requerimiento, sgt_fecha_registro, sgt_cod_log, sgt_ficha_analista, sgt_planta_analista, sgt_ficha_usulog, sgt_planta_usulog)\n" +
                    "    VALUES (?, ?, ?, ?, ?, ?, ?);";
        
        try {               
           
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1, tipo_reque);
            pstmt.setString(2, desc_reque);
            pstmt.setString(3, cod_urge);
            pstmt.setString(4, clasi_reque);
            pstmt.setString(5, area_reque);
            pstmt.executeUpdate(); 
            
            pstmt=con.prepareStatement(sql2);
            pstmt.setInt(1, cod_requerimiento);
            pstmt.setTimestamp(2, new java.sql.Timestamp(today.getTime()));
            pstmt.setString(3, log);
            pstmt.setString(4, ficha_log);
            pstmt.setString(5, planta_log);
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
    
    public String verTipoRequerimiento(String cod){
    sgtUtility util = new sgtUtility();
    Connection con = null;
    Statement stm=null;
    ResultSet rs=null;
    String retorno="<select id='selectT' onchange='cargaAreaXML(this.value);' name='lm_tipo_requerimiento'>";
    retorno+="<option value='su'>Seleccione Uno(a)</option>";
    
        try {
            String sql = "select sgt_cod_tipo_requerimiento, sgt_desc_tipo_requerimiento from t_sgt_tipo_requerimiento";
            con = util.Conexion_Sorg();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while(rs.next()){
                String tipo=rs.getString("sgt_desc_tipo_requerimiento");
                String cod_tipo = rs.getString("sgt_cod_tipo_requerimiento");
                retorno+="<option value='"+ cod_tipo +"'";
                        if(cod_tipo.equals(cod)){
                            retorno+="selected";
                        }
                        retorno+=">"+tipo+"</option>";
                
            }
          retorno+="</select>";
        } catch (SQLException e) {
            System.out.println("ERROR SQL TIPO REQUERIMIENTO EDITAR: "+e);
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
    
    public String verUrgenciaRequerimiento(String cod){
        sgtUtility util = new sgtUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String retorno = "<select id='selectC' disable='false' name='lm_urgencia'>";
        retorno+="<option value='su'>Seleccione Uno(a)</option>";
        System.out.println("--------VOY A VER LA URGENCIA PARA EDITARLA---------------");
            try{
                String sql ="SELECT sgt_cod_urgencia, sgt_desc_urgencia FROM  t_sgt_urgencia  \n" +
                            "  ORDER BY \n" +
                            "  sgt_cod_urgencia;";
                con = util.Conexion_Sorg();
                stm= con.createStatement();
                rs = stm.executeQuery(sql);
                while(rs.next()){
                    String cod_urge = rs.getString("sgt_cod_urgencia");
                    String urge = rs.getString("sgt_desc_urgencia");
                    retorno+="<option value='"+cod_urge+"'";
                    if(cod_urge.equals(cod)){
                        retorno+="selected";                        
                    };
                    retorno+=">"+urge+"</option>";
                }
                retorno+="</select>";
                System.out.println("YA ENVIE EL SELECT PARA CARGAR LA URGENCIA EDITADA");
            }catch(SQLException e){
                System.out.println("ERROR VIENDO URGENCIA: "+e);
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
    
    public String verAreaRequerimiento(String codigo){
    sgtUtility util = new sgtUtility();
    Connection con = null;
    Statement stm=null;
    ResultSet rs=null;
    String retorno="<select id='selectA' onchange='cargaClasificacionXML(this.value);' disable='false' name='lm_area_requerimiento'>";
    retorno+="<option value='su'>Seleccione Uno(a)</option>";
    
        try {
            String sql = "SELECT sgt_cod_area, sgt_desc_area FROM t_sgt_area where sgt_desc_area != 'TODAS';";
            con = util.Conexion_Sorg();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while(rs.next()){
                String area=rs.getString("sgt_desc_area");
                String cod=rs.getString("sgt_cod_area");
                retorno+="<option id='optionArea' value='"+cod+"'";
                if(cod.equals(codigo)){
                    retorno+="selected";
                }
                retorno+=">"+area+"</option>";
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
    
    public String verClasificacionRequerimiento(String cod){
        sgtUtility util = new sgtUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String retorno = "<select id='selectC' disable='false' name='lm_clasificacion'>";
        retorno+="<option value='su'>Seleccione Uno(a)</option>";
        
            try{
                String sql ="SELECT \n" +
                            "  sgt_cod_clasificacion, \n" +
                            "  sgt_desc_clasificacion\n" +
                            "FROM \n" +
                            "  public.t_sgt_clasificacion\n" +
                            "WHERE\n" +
                            "  sgt_cod_clasificacion='"+cod+"';";
                con = util.Conexion_Sorg();
                stm= con.createStatement();
                rs = stm.executeQuery(sql);
                while(rs.next()){
                    String select_cau = rs.getString("sgt_desc_clasificacion");
                    String cod_cla = rs.getString("sgt_cod_clasificacion");
                    retorno+="<option value='"+cod_cla+"'";
                    if(cod_cla.equals(cod)){
                        retorno+="selected";
                    }
                    retorno+=">"+select_cau+"</option>";
                }
                retorno+="</select>";
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
    
    public static void Send_correo_procesando(String mensaje, String to, String planta, String name_ana){
        
        //String to ="marilys.rivas@venvidrio.com.ve";  
        //String para ="mario.ortega@venvidrio.com.ve"; 
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
                message.setSubject("Requerimiento en proceso por analista: "+name_ana);
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
