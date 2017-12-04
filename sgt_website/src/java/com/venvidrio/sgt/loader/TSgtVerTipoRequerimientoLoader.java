package com.venvidrio.sgt.loader;

import com.venvidrio.sgt.utility.sgtUtility;
import java.sql.*;
       
/**
 *
 * @author Ortegam
 */
public class TSgtVerTipoRequerimientoLoader {
    
    private String sgt_tiporequerimieto;
    private String sgt_ficha;
    private String sgt_clasificacion;
    
    public String getsgtTipoRequerimieto(){
        return this.sgt_tiporequerimieto;
    }
    
    public void setsgtTipoRequerimiento(String sgt_tiporequerimiento){
        this.sgt_tiporequerimieto = sgt_tiporequerimiento;
    }
    
    public String getsgtFichaPer(){
        return this.sgt_ficha;
    }
    
    public void setsgtFichaPer(String sgt_ficha){
        this.sgt_ficha = sgt_ficha;
    }
    
    public String getsgtClasificacion(){
        return this.sgt_clasificacion;
    }
    
    public void setsgtClasificacion(String sgt_clasificacion){
        this.sgt_clasificacion = sgt_clasificacion;
    }
    
    public String verPlanta(){
    sgtUtility util = new sgtUtility();
    Connection con = null;
    Statement stm=null;
    ResultSet rs=null;
    String retorno="<select id='selectT' onchange='cargaPersonalXML(this.value);' name='lm_planta' >";
    retorno+="<option value='su'>Seleccione Uno(a)</option>";
    
        try {
            String sql = "SELECT \n" +
                         "  pla_cod_planta, \n" +
                         "  pla_nom_planta\n" +
                         "FROM \n" +
                         "  vv_planta;";
            con = util.Conexion_Sorg();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while(rs.next()){
                String planta=rs.getString("pla_nom_planta");
                String cod_planta = rs.getString("pla_cod_planta");
                retorno+="<option value='"+ cod_planta +"'>"+planta+"</option>";
                
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
    
    public String verPlantaAnalista(){
    sgtUtility util = new sgtUtility();
    Connection con = null;
    Statement stm=null;
    ResultSet rs=null;
    String retorno="<select id='selectT' onchange='cargaAnalistaXML(this.value);' name='lm_planta_analista' >";
    retorno+="<option value='su'>Seleccione Uno(a)</option>";
    
        try {
            String sql = "SELECT \n" +
                         "  pla_cod_planta, \n" +
                         "  pla_nom_planta\n" +
                         "FROM \n" +
                         "  vv_planta;";
            con = util.Conexion_Sorg();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while(rs.next()){
                String planta=rs.getString("pla_nom_planta");
                String cod_planta = rs.getString("pla_cod_planta");
                retorno+="<option value='"+ cod_planta +"'>"+planta+"</option>";
                
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
    
    public String verDepartamento(String cod_ficha){
    sgtUtility util = new sgtUtility();
    Connection con = null;
    Statement stm=null;
    ResultSet rs=null;
    String planta = "";
    String retorno="<select id='selectT' onchange='mostrarFicha(this.value)' name='lm_dpto' >";
    retorno+="<option value='su'>Seleccione Uno(a)</option>";
    
        try {
           String sql_planta ="SELECT \n" +
                            "  vv_personal.per_cod_planta\n" +
                            "FROM \n" +
                            "  public.vv_personal\n" +
                            "WHERE \n" +
                            "  per_ficha='"+cod_ficha+"';"; 
           con =util.Conexion_Sorg();
           stm = con.createStatement();
           rs = stm.executeQuery(sql_planta);
           while(rs.next()){
               planta = rs.getString("per_cod_planta");
           }
            
            String sql = "SELECT \n" +
                        "  vv_departamento.dep_desc_dpto, \n" +
                        "  vv_departamento.dep_cod_dpto\n" +
                        "FROM \n" +
                        "  public.vv_departamento, \n" +
                        "  public.vv_personal, \n" +
                        "  public.vv_planta\n" +
                        "WHERE \n" +
                        "  vv_departamento.dep_cod_planta = vv_planta.pla_cod_planta AND\n" +
                        "  vv_personal.per_depart = vv_departamento.dep_cod_dpto AND\n" +
                        "  vv_planta.pla_cod_planta = vv_personal.per_cod_planta AND\n" +
                        "  vv_personal.per_cod_planta = '"+planta+"' AND \n" +
                        "  vv_personal.per_ficha = '"+cod_ficha+"';";
            con = util.Conexion_Sorg();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while(rs.next()){
                String dpto=rs.getString("dep_desc_dpto");
                String cod_dpto = rs.getString("dep_cod_dpto");
                retorno+="<option value='"+ cod_dpto +"'>"+dpto+"</option>";
                
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
    
    public String verPersonalDepartamento( String cod_planta){
    sgtUtility util = new sgtUtility();
    Connection con = null;
    Statement stm=null;
    ResultSet rs=null;
    String retorno="<select id='selectC' disable='false' onchange='cargaDepartamentoXML(this.value);' name='lm_ficha'>";
    retorno+="<option value='su'>Seleccione Uno(a)</option>";
    
        try {
            String sql = "SELECT\n" +
                        "  per_nombre,\n" +
                        "  per_ficha,\n" +
                        "  per_cod_planta \n" +
                        "FROM \n" +
                        "  vv_personal\n" +
                        "WHERE \n" +
                        "  per_cod_planta ='"+cod_planta+"' AND\n" +
                        "  per_fec_retiro='NULL'\n" +
                        "ORDER BY \n" +
                        "  per_nombre;";
            con = util.Conexion_Sorg();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while(rs.next()){
                String nombre=rs.getString("per_nombre");
                String ficha=rs.getString("per_ficha");
                               
                retorno+="<option  value='"+ficha+"'>"+nombre+"</option>";
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
    
    return retorno ;
    
    }
            
    public String verTiporequerimiento(){
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
                retorno+="<option value='"+ cod_tipo +"'>"+tipo+"</option>";
                
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
    
    public String verArearequerimiento( String cod_tipo){
    sgtUtility util = new sgtUtility();
    Connection con = null;
    Statement stm=null;
    ResultSet rs=null;
    String retorno="<select id='selectA' onchange='cargaClasificacionXML(this.value);' disable='false' name='lm_area_requerimiento'>";
    retorno+="<option value='su'>Seleccione Uno(a)</option>";
    
        try {
            String sql = "SELECT sgt_cod_area, sgt_desc_area FROM t_sgt_tipo_requerimiento, t_sgt_area where sgt_cod_tipo_requerimiento = '"+ cod_tipo +"' AND  sgt_desc_area != 'TODAS';";
            con = util.Conexion_Sorg();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while(rs.next()){
                String area=rs.getString("sgt_desc_area");
                String cod=rs.getString("sgt_cod_area");
                retorno+="<option id='optionArea' value='"+cod+"'>"+area+"</option>";
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
    
    public String verArea(){
    sgtUtility util = new sgtUtility();
    Connection con = null;
    Statement stm=null;
    ResultSet rs=null;
    String retorno="<select id='selectA' disable='false' name='lm_area'>";
    retorno+="<option value='su'>Seleccione Uno(a)</option>";
    
        try {
            String sql = "SELECT sgt_cod_area, sgt_desc_area FROM t_sgt_area where sgt_desc_area != 'TODAS';";
            con = util.Conexion_Sorg();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while(rs.next()){
                String area=rs.getString("sgt_desc_area");
                String cod=rs.getString("sgt_cod_area");
                retorno+="<option id='optionArea' value='"+cod+"'>"+area+"</option>";
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
    
    public String verClasificacion(String cod){
        sgtUtility util = new sgtUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String retorno = "<select id='selectC' disable='false' name='lm_clasificacion'>";
        retorno+="<option value='su'>Seleccione Uno(a)</option>";
        
            try{
                String sql ="SELECT \n" +
                            "  sgt_desc_clasificacion, sgt_cod_clasificacion\n" +
                            " FROM \n" +
                            "  t_sgt_clasificacion_area, \n" +
                            "  t_sgt_clasificacion, \n" +
                            "  t_sgt_area\n" +
                            " WHERE \n" +
                            "  t_sgt_clasificacion.sgt_cod_clasificacion = t_sgt_clasificacion_area.sgt_cod_clasificacion_area AND\n" +
                            "  t_sgt_area.sgt_cod_area = t_sgt_clasificacion_area.sgt_cod_area and\n" +
                            "  t_sgt_area.sgt_cod_area = '"+ cod +"' ORDER BY t_sgt_clasificacion.sgt_desc_clasificacion;";
                con = util.Conexion_Sorg();
                stm= con.createStatement();
                rs = stm.executeQuery(sql);
                while(rs.next()){
                    String select_cau = rs.getString("sgt_desc_clasificacion");
                    String cod_cla = rs.getString("sgt_cod_clasificacion");
                    retorno+="<option value='"+cod_cla+"'>"+select_cau+"</option>";
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
    
    public String verClasificacionTelecomunicaciones(){
        sgtUtility util = new sgtUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String retorno = "<select id='selectC'>";
        retorno+="<option value='su'>Seleccione Uno(a)</option>";
        
            try{
                String sql ="SELECT \n" +
                            "  \"t_sgt_clasificacion\".sgt_desc_clasificacion\n" +
                            "FROM \n" +
                            "  public.t_sgt_clasificacion_area, \n" +
                            "  public.\"t_sgt_clasificacion\", \n" +
                            "  public.t_sgt_area\n" +
                            "WHERE \n" +
                            "  \"t_sgt_clasificacion\".sgt_cod_clasificacion = t_sgt_clasificacion_area.sgt_cod_clasificacion_area AND\n" +
                            "  t_sgt_area.sgt_cod_area = t_sgt_clasificacion_area.sgt_cod_area and\n" +
                            "  t_sgt_area.sgt_cod_area = '02' ORDER BY t_sgt_clasificacion.sgt_cod_clasificacion;";
                con = util.Conexion_Sorg();
                stm= con.createStatement();
                rs = stm.executeQuery(sql);
                while(rs.next()){
                    String select_cau = rs.getString(1);
                    retorno+="<option>"+select_cau+"</option>";
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
    
    public String verClasificacionAplicaciones(){
        sgtUtility util = new sgtUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String retorno = "<select id='selectC'>";
        retorno+="<option value='su'>Seleccione Uno(a)</option>";
        
            try{
                String sql ="SELECT \n" +
                            "  \"t_sgt_clasificacion\".sgt_desc_clasificacion\n" +
                            "FROM \n" +
                            "  public.t_sgt_clasificacion_area, \n" +
                            "  public.\"t_sgt_clasificacion\", \n" +
                            "  public.t_sgt_area\n" +
                            "WHERE \n" +
                            "  \"t_sgt_clasificacion\".sgt_cod_clasificacion = t_sgt_clasificacion_area.sgt_cod_clasificacion_area AND\n" +
                            "  t_sgt_area.sgt_cod_area = t_sgt_clasificacion_area.sgt_cod_area and\n" +
                            "  t_sgt_area.sgt_cod_area = '03' ORDER BY t_sgt_clasificacion.sgt_cod_clasificacion;";
                con = util.Conexion_Sorg();
                stm= con.createStatement();
                rs = stm.executeQuery(sql);
                while(rs.next()){
                    String select_cau = rs.getString(1);
                    retorno+="<option>"+select_cau+"</option>";
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
    
    public String verClasificacionInfraestructura(){
        sgtUtility util = new sgtUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String retorno = "<select id='selectC'>";
        retorno+="<option value='su'>Seleccione Uno(a)</option>";
        
            try{
                String sql ="SELECT \n" +
                            "  \"t_sgt_clasificacion\".sgt_desc_clasificacion\n" +
                            "FROM \n" +
                            "  public.t_sgt_clasificacion_area, \n" +
                            "  public.\"t_sgt_clasificacion\", \n" +
                            "  public.t_sgt_area\n" +
                            "WHERE \n" +
                            "  \"t_sgt_clasificacion\".sgt_cod_clasificacion = t_sgt_clasificacion_area.sgt_cod_clasificacion_area AND\n" +
                            "  t_sgt_area.sgt_cod_area = t_sgt_clasificacion_area.sgt_cod_area and\n" +
                            "  t_sgt_area.sgt_cod_area = '04' ORDER BY t_sgt_clasificacion.sgt_cod_clasificacion;";
                con = util.Conexion_Sorg();
                stm= con.createStatement();
                rs = stm.executeQuery(sql);
                while(rs.next()){
                    String select_cau = rs.getString(1);
                    retorno+="<option>"+select_cau+"</option>";
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
    
    public String verUrgencia(){
        sgtUtility util = new sgtUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String retorno = "<select id='selectC' disable='false' name='lm_urgencia'>";
        retorno+="<option value='su'>Seleccione Uno(a)</option>";
        
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
                    retorno+="<option value='"+cod_urge+"'>"+urge+"</option>";
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
    
    public String verDetalleReque(String cod_reque){
        sgtUtility util = new sgtUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String retorno = "<script>";
        
        
            try{
                String sql ="SELECT \n" +
                            "  sgt_cod_requerimiento, \n" +
                            "  per_nombre, \n" +
                            "  sgt_desc_clasificacion, \n" +
                            "  sgt_desc_status, \n" +
                            "  sgt_desc_urgencia, \n" +
                            "  sgt_fecha_requerimiento, \n" +
                            "  sgt_desc_requerimiento\n" +
                            "FROM \n" +
                            "  t_sgt_requerimiento, \n" +
                            "  t_sgt_clasificacion, \n" +
                            "  vv_personal, \n" +
                            "  t_sgt_urgencia, \n" +
                            "  t_sgt_status\n" +
                            "WHERE \n" +
                            "  t_sgt_requerimiento.per_ficha_solicitante = vv_personal.per_ficha AND\n" +
                            "  t_sgt_requerimiento.per_cod_planta_solicitante = vv_personal.per_cod_planta AND\n" +
                            "  t_sgt_requerimiento.sgt_cod_clasificacion_area = t_sgt_clasificacion.sgt_cod_clasificacion AND\n" +
                            "  t_sgt_requerimiento.sgt_cod_status = t_sgt_status.sgt_cod_status AND\n" +
                            "  t_sgt_requerimiento.sgt_cod_urgencia = t_sgt_urgencia.sgt_cod_urgencia AND \n" +
                            "  sgt_cod_requerimiento ='"+cod_reque+"'\n" +
                            "ORDER BY\n" +
                            "  t_sgt_requerimiento.sgt_cod_requerimiento ASC;";
                con = util.Conexion_Sorg();
                stm= con.createStatement();
                rs = stm.executeQuery(sql);
                while(rs.next()){
                    String cod_tipo = rs.getString("sgt_cod_requerimiento");
                    String per_nomre = rs.getString("per_nombre");
                    String desc_clasi = rs.getString("sgt_desc_clasificacion");
                    String desc_status = rs.getString("sgt_desc_status");
                    String desc_urgencia = rs.getString("sgt_desc_urgencia");
                    String fecha_reque = rs.getString("sgt_fecha_requerimiento");
                    String desc_reque = rs.getString("sgt_desc_requerimiento");
                    retorno+="alert('probando');";
                }
                retorno+= "</script>";
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
    
    public String verAnalistaArea(String cod){
        sgtUtility util = new sgtUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String retorno = "<select id='lm_AnalistaArea' disable='false' name='lm_AnalistaArea'>";
        retorno+="<option value='su'>Seleccione Uno(a)</option>";
        
            try{
                String sql ="select \n" +
                            "    sgt_cod_planta_analista, \n" +
                            "    sgt_ficha_analista,\n" +
                            "    per_nombre,\n" +
                            "    t_sgt_area_analista.sgt_cod_area, \n" +
                            "    sgt_desc_area\n" +
                            "from\n" +
                            "    t_sgt_area_analista, \n" +
                            "    t_sgt_area,\n" +
                            "    vv_personal\n" +
                            "where\n" +
                            "    t_sgt_area_analista.sgt_ficha_analista = vv_personal.per_ficha AND\n" +
                            "    t_sgt_area_analista.sgt_cod_planta_analista = vv_personal.per_cod_planta AND\n" +
                            "    t_sgt_area.sgt_cod_area = t_sgt_area_analista.sgt_cod_area  AND\n" +
                            "    t_sgt_area_analista.sgt_cod_area = '"+cod+"';";
                con = util.Conexion_Sorg();
                stm= con.createStatement();
                rs = stm.executeQuery(sql);
                while(rs.next()){
                    String select_ana = rs.getString("per_nombre");
                    String ficha = rs.getString("sgt_ficha_analista");
                    retorno+="<option value='"+ficha+"'>"+select_ana+"</option>";
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
    
     public String verTipoAnalista(){
    sgtUtility util = new sgtUtility();
    Connection con = null;
    Statement stm=null;
    ResultSet rs=null;
    String retorno="<select id='selectA' disable='false' name='lm_tipo_analista'>";
    retorno+="<option value='su'>Seleccione Uno(a)</option>";
    
        try {
            String sql = "select sgt_cod_tipo_analista, sgt_desc_tipo_analista from t_sgt_tipo_analista;";
            con = util.Conexion_Sorg();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while(rs.next()){
                String cod_tipo=rs.getString("sgt_cod_tipo_analista");
                String desc_tipo=rs.getString("sgt_desc_tipo_analista");
                retorno+="<option id='optionArea' value='"+cod_tipo+"'>"+desc_tipo+"</option>";
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
}
