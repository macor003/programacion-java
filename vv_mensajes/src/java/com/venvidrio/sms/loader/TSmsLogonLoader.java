/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venvidrio.sms.loader;

import com.venvidrio.sms.utility.smsUtility;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;

/**
 *
 * @author Ortegam
 */
public class TSmsLogonLoader {
    private String sms_usuario;
    private String sms_contrasena;
    private String sms_ficha_emp;
    private String sms_status;
    private String sms_rol;
    private String sms_nombre;
    private String sms_planta;
    private String sms_depart;
    private String sms_desc_dpto;
    private String sms_nom_planta;
    private String sms_cedula;
    private String sms_status_outsourcing;
    
    private Context ctx = null;
    private String x;


    public String getsmsUsuario () {
        return this.sms_usuario;
    }
    
    public void setsmsUsuario (String sms_usuario) {
        this.sms_usuario = sms_usuario;
    }
    
    public String getsmsContrasena () {
        return this.sms_contrasena;
    }
    
    public void setsmsContrasena (String sms_contrasena) {
        this.sms_contrasena = sms_contrasena;
    }
    
    public String getsmsFichaEmp(){
        return this.sms_ficha_emp;
    }
    
    public void setsmsFichaEmp(String sms_ficha_emp){
        this.sms_ficha_emp = sms_ficha_emp;
    }

    public String getsmsStatus(){
        return this.sms_status;
    }

    public void setsmsStatus(String sms_status){
        this.sms_status = sms_status;
    }

    public String getsmsCodDepart(){
        return this.sms_depart;
    }

    public void setsmsCodDepart(String sms_depart){
        this.sms_depart = sms_depart;
    }
    
    public String getsmsDescDpto(){
        return this.sms_desc_dpto;
    }
    
    public void setsmsDescDpto(String sms_desc_dpto){
        this.sms_desc_dpto = sms_desc_dpto;
    }
    
    public String getsmsNombre(){
        return this.sms_nombre;
    }

    public void setsmsNombre(String sms_nombre){
        this.sms_nombre = sms_nombre;
    }

    public String getsmsCodPlanta(){
        return this.sms_planta;
    }

    public void setsmsCodPlanta(String sms_planta){
        this.sms_planta = sms_planta;
    }
    
    public String getsmsNomPlanta(){
        return this.sms_nom_planta;
    }
    
    public void setsmsNomPlanta(String sms_nom_planta){
        this.sms_nom_planta = sms_nom_planta;
    }

    public String getsmsRol(){
        return this.sms_rol;
    }

    public void setsmsRol(String sms_rol){
        this.sms_rol = sms_rol;
    }
    
    public String getsmsCedula(){
        return this.sms_cedula;
    }
    
    public void setsmsCedula(String sms_cedula){
       this.sms_cedula = sms_cedula; 
    }
    
    public String getsmsStatusOutsourcing(){
        return this.sms_status_outsourcing;
    }
    
    public void setsmsStatusOutsourcing(String sms_status_outsourcing){
        this.sms_status_outsourcing = sms_status_outsourcing;
    }

    public String getX () { //metodo para retornar exception...
       return this.x;
    }

    public void setX(String x) {
       this.x=x;
   }

    public void usuarioAplicacion(String sgt_usuario) throws Exception{
       //("Estoy en usuarioAplicacion");
       smsUtility util = new smsUtility();
       Statement stmt=null;
       ResultSet rs = null;
       Connection con = null;

////(con);
        String ls_sql = "     SELECT usu_cod_usuario, " +
                        "     usu_cod_rol " +
                        "     FROM vv_usuario_rol  " +
                        "     WHERE (usu_cod_usuario = '" + sgt_usuario + "'" +
                        "     and usu_cod_aplic = '043'); ";
        ////(ls_sql);
        try{
            //("el sql a hace es=== "+ls_sql);
            //("voy a conectar con la Conexion_Sorg");
            //con=util.Conexion_Empresa();
             con=util.Conexion_IT();
             System.out.println("Conexion=="+con);
            //("voy a crear el statement");
            stmt = con.createStatement();
            System.out.println("LLegue al statement");
            //("voy a ejecutar la consulta");
            rs=stmt.executeQuery(ls_sql);
            System.out.println("Consulta = "+ls_sql);
            //("voy a ejecutar la consulta");

            while(rs.next()){
                //("el usu_cod_usuario="+rs.getString("usu_cod_usuario"));
                System.out.println("Consulta Ejecutada");
                setsmsUsuario(rs.getString("usu_cod_usuario"));
                System.out.println("Codigo Rol = " + rs.getString("usu_cod_rol"));
                
                //("el usu_cod_rol="+rs.getString("usu_cod_rol"));
                setsmsRol(rs.getString("usu_cod_rol"));
                getUsuario(getsmsUsuario());
                ////(getsigUsuario());
            }
        }catch (Exception e) {
            this.setX("ojoo"+e);
            System.out.println("Error 1 = "+e.toString());
        
            //("VSreqUsuarioContrasenaManager::viewUsuarioAplic(): Exception " + e.toString());

        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error 2 = "+e.toString());
                ////("VSreqUsuarioContrasenaManager::viewUsuarioAplic(): SQLException " + e.toString());

            }
        }
   }

    public void getUsuario(String sgt_usuario) throws Exception{
       ////(sig_usuario);
       smsUtility util = new smsUtility();
       Statement stmt=null;
       ResultSet rs = null;
       Connection con = null;

////(con);
        String ls_sql = "SELECT UCO_FICHA_EMP, UCO_COD_PLANTA " +
                            "FROM t_vv_usuario_contrasena " +
                            "WHERE UCO_COD_USUARIO = '"+sgt_usuario+"' ";
        ////(ls_sql);
        try{
            con=util.Conexion_Empresa();
            stmt = con.createStatement();
            rs=stmt.executeQuery(ls_sql);

            while(rs.next()){
                String planta = rs.getString("UCO_COD_PLANTA");
                setsmsFichaEmp(rs.getString("UCO_FICHA_EMP"));
                //("ficha:"+getsigFichaEmp());
                verPersonal(getsmsFichaEmp(), planta);
                ////(getsigUsuario());
            }
        }catch (Exception e) {
            this.setX("ojooo1"+e);
            //("VSreqUsuarioContrasenaManager::viewUsuarioAplic(): Exception " + e.toString());

        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                ////("VSreqUsuarioContrasenaManager::viewUsuarioAplic(): SQLException " + e.toString());

            }
        }
   }

    public void verPersonal(String sgt_ficha_emp, String planta) throws Exception{    //Metodo que completa los set y get faltantes
       ////(sgt_usuario);
      
       smsUtility util = new smsUtility();
       Statement stmt=null;
       ResultSet rs = null;
       Connection con = null;

////(con);
        String ls_sql = "SELECT PER_STATUS,\n" +
                        "	PER_COD_PLANTA, \n" +
                        "	PER_DEPART, \n" +
                        "	t_vv_departamento.dep_desc_dpto,\n" +
                        "	PER_NOMBRE, \n" +
                        "	PLA_NOM_PLANTA,\n" +
                        "	PER_CEDULA \n" +
                        "FROM  t_vv_personal, t_vv_departamento, t_vv_planta \n" +
                        "WHERE t_vv_departamento.dep_cod_planta = t_vv_personal.per_cod_planta AND\n" +
                        "	t_vv_departamento.dep_cod_dpto = t_vv_personal.per_depart AND\n" +
                        "	t_vv_planta.pla_cod_planta = t_vv_personal.per_cod_planta AND\n" +
                        "	(PER_FICHA = '"+sgt_ficha_emp+"' OR PER_CEDULA='"+sgt_ficha_emp+"') AND\n" +
                        "	PER_COD_PLANTA='"+planta+"';";
        System.out.println("SQL Personal = "+ls_sql);
        try{
            con=util.Conexion_Empresa();
            stmt = con.createStatement();
            rs=stmt.executeQuery(ls_sql);

            while(rs.next()){
                setsmsStatus(rs.getString("PER_STATUS"));
                setsmsCodPlanta(rs.getString("PER_COD_PLANTA"));
                setsmsCodDepart(rs.getString("PER_DEPART"));
                setsmsNombre(rs.getString("PER_NOMBRE"));
                setsmsDescDpto(rs.getString("dep_desc_dpto"));
                setsmsNomPlanta(rs.getString("PLA_NOM_PLANTA"));
                setsmsCedula(rs.getString("PER_CEDULA"));
                
                //("status: "+getsigStatus());
                validarContrasena(getsmsFichaEmp(), sms_usuario);
                ////(getsigUsuario());
            }
        }catch (Exception e) {
            System.out.println("Error verPersonal = "+ e);
            this.setX(""+e);
            
            //("VSreqUsuarioContrasenaManager::viewUsuarioAplic(): Exception " + e.toString());

        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                ////("VSreqUsuarioContrasenaManager::viewUsuarioAplic(): SQLException " + e.toString());

            }
        }
       
   }
    
    public void verOutsourcing(String sgt_cedula_emp, String planta) throws Exception{    //Metodo que completa los set y get faltantes
       ////(sgt_usuario);
      
       smsUtility util = new smsUtility();
       Statement stmt=null;
       ResultSet rs = null;
       Connection con = null;

////(con);
        
        
        
        try{
            
            String ls_sql = "SELECT \n" +
                        "  out_cod_planta, \n" +
                        "  out_cedula, \n" +
                        "  out_dpto, \n" +
                        "  out_nombre, \n" +
                        "  out_status, \n" +
                        "  dep_desc_dpto, \n" +
                        "  pla_nom_planta \n" +
                        "FROM \n" +
                        "  public.sgt_outsourcing, \n" +
                        "  public.vv_departamento, \n" +
                        "  public.vv_planta \n" +
                        "WHERE \n" +
                        "  sgt_outsourcing.out_cod_planta = vv_planta.pla_cod_planta AND \n" +
                        "  vv_departamento.dep_cod_dpto = sgt_outsourcing.out_dpto AND \n" +
                        "  vv_departamento.dep_cod_planta = sgt_outsourcing.out_cod_planta AND \n" +
                        "  out_cod_planta='"+planta+"' AND \n" +
                        "  out_cedula='"+sgt_cedula_emp+"';";
            
            System.out.println("SQL outsourcing = "+ls_sql);
            con=util.Conexion_IT();
            stmt = con.createStatement();
            rs=stmt.executeQuery(ls_sql);

            while(rs.next()){                
                setsmsStatusOutsourcing(rs.getString("out_status"));
                setsmsCodPlanta(rs.getString("out_cod_planta"));
                setsmsCodDepart(rs.getString("out_dpto"));
                setsmsNombre(rs.getString("out_nombre"));
                setsmsDescDpto(rs.getString("dep_desc_dpto"));
                setsmsNomPlanta(rs.getString("pla_nom_planta"));
                setsmsCedula(rs.getString("out_cedula"));
//                System.out.println("1 "+ getsmsCodPlanta());
//                System.out.println("2 "+ getsmsCodDepart());
//                System.out.println("3 "+ getsmsNombre());
//                System.out.println("4 "+ getsmsDescDpto());
//                System.out.println("5 "+ getsmsNomPlanta());
//                System.out.println("6 ");
//                System.out.println("7 ");
                
            }
        }catch (Exception e) {
            System.out.println("Error verOutsourcing = "+ e);
            this.setX(""+e);
            
            //("VSreqUsuarioContrasenaManager::viewUsuarioAplic(): Exception " + e.toString());

        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                ////("VSreqUsuarioContrasenaManager::viewUsuarioAplic(): SQLException " + e.toString());

            }
        }
       
   }
       
    public void validarContrasena(String sgt_ficha_emp, String sgt_usuario) throws Exception{
       ////(sig_usuario);
       smsUtility util = new smsUtility();
       Statement stmt=null;
       ResultSet rs = null;
       Connection con = null;

////(con);
       /* String ls_sql = "SELECT UCO_CONTRASENA " +
                            "FROM  t_vv_usuario_contrasena " +
                            "WHERE UCO_FICHA_EMP = '"+sig_ficha_emp+"' "; */

        String ls_sql = "SELECT UCO_CONTRASENA " +
                            "FROM  t_vv_usuario_contrasena " +
                            "WHERE UCO_COD_USUARIO = '"+sgt_usuario+"' AND UCO_SEGURIDAD_STATUS='0' ";
        System.out.println("SQL Contraseña:  " + ls_sql);
        ////(ls_sql);
        try{
            con=util.Conexion_Empresa();
            stmt = con.createStatement();
            rs=stmt.executeQuery(ls_sql);

            while(rs.next()){
                System.out.println("Estoy entrando en el while");
                setsmsContrasena(rs.getString("UCO_CONTRASENA").toUpperCase());
                ////(getsigUsuario());
                System.out.println("Ya setie la contraseña: "+ getsmsContrasena());
            }
        }catch (Exception e) {
            System.out.println("Error contraseña = " + e);
            this.setX(""+e);
            //("VSreqUsuarioContrasenaManager::viewUsuarioAplic(): Exception " + e.toString());

        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                ////("VSreqUsuarioContrasenaManager::viewUsuarioAplic(): SQLException " + e.toString());

            }
        }
   }
    
}
