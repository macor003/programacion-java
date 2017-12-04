package com.venvidrio.sgt.loader;


import com.venvidrio.sgt.utility.sgtUtility;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;

/**
 *
 * @author Ortegam
 */
public class TSgtLogonLoader {
    private String sgt_usuario;
    private String sgt_contrasena;
    private String sgt_ficha_emp;
    private String sgt_status;
    private String sgt_rol;
    private String sgt_nombre;
    private String sgt_planta;
    private String sgt_depart;
    private String sgt_desc_dpto;
    private String sgt_nom_planta;
    private String sgt_cedula;
    private String sgt_status_outsourcing;
    
    private Context ctx = null;
    private String x;


    public String getsgtUsuario () {
        return this.sgt_usuario;
    }
    
    public void setsgtUsuario (String sgt_usuario) {
        this.sgt_usuario = sgt_usuario;
    }
    
    public String getsgtContrasena () {
        return this.sgt_contrasena;
    }
    
    public void setsgtContrasena (String sgt_contrasena) {
        this.sgt_contrasena = sgt_contrasena;
    }
    
    public String getsgtFichaEmp(){
        return this.sgt_ficha_emp;
    }
    
    public void setsgtFichaEmp(String sgt_ficha_emp){
        this.sgt_ficha_emp = sgt_ficha_emp;
    }

    public String getsgtStatus(){
        return this.sgt_status;
    }

    public void setsgtStatus(String sgt_status){
        this.sgt_status = sgt_status;
    }

    public String getsgtCodDepart(){
        return this.sgt_depart;
    }

    public void setsgtCodDepart(String sgt_depart){
        this.sgt_depart = sgt_depart;
    }
    
    public String getsgtDescDpto(){
        return this.sgt_desc_dpto;
    }
    
    public void setsgtDescDpto(String sgt_desc_dpto){
        this.sgt_desc_dpto = sgt_desc_dpto;
    }
    
    public String getsgtNombre(){
        return this.sgt_nombre;
    }

    public void setsgtNombre(String sgt_nombre){
        this.sgt_nombre = sgt_nombre;
    }

    public String getsgtCodPlanta(){
        return this.sgt_planta;
    }

    public void setsgtCodPlanta(String sgt_planta){
        this.sgt_planta = sgt_planta;
    }
    
    public String getsgNomPlanta(){
        return this.sgt_nom_planta;
    }
    
    public void setsgtNomPlanta(String sgt_nom_planta){
        this.sgt_nom_planta = sgt_nom_planta;
    }

    public String getsgtRol(){
        return this.sgt_rol;
    }

    public void setsgtRol(String sgt_rol){
        this.sgt_rol = sgt_rol;
    }
    
    public String getsgtCedula(){
        return this.sgt_cedula;
    }
    
    public void setsgtCedula(String sgt_cedula){
       this.sgt_cedula = sgt_cedula; 
    }
    
    public String getsgtStatusOutsourcing(){
        return this.sgt_status_outsourcing;
    }
    
    public void setsgtStatusOutsourcing(String sgt_status_outsourcing){
        this.sgt_status_outsourcing = sgt_status_outsourcing;
    }

    public String getX () { //metodo para retornar exception...
       return this.x;
    }

    public void setX(String x) {
       this.x=x;
   }

    public void usuarioAplicacion(String sgt_usuario) throws Exception{
       //("Estoy en usuarioAplicacion");
       sgtUtility util = new sgtUtility();
       Statement stmt=null;
       ResultSet rs = null;
       Connection con = null;

////(con);
        String ls_sql = "     SELECT usu_cod_usuario, " +
                        "     usu_cod_rol " +
                        "     FROM vv_usuario_rol  " +
                        "     WHERE (usu_cod_usuario = '" + sgt_usuario + "'" +
                        "     and usu_cod_aplic = '042') ";
        ////(ls_sql);
        try{
            //("el sql a hace es=== "+ls_sql);
            //("voy a conectar con la Conexion_Sorg");
            //con=util.Conexion_Empresa();
             con=util.Conexion_Sorg();
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
                setsgtUsuario(rs.getString("usu_cod_usuario"));
                System.out.println("Codigo Rol = " + rs.getString("usu_cod_rol"));
                
                //("el usu_cod_rol="+rs.getString("usu_cod_rol"));
                setsgtRol(rs.getString("usu_cod_rol"));
                getUsuario(getsgtUsuario());
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
       sgtUtility util = new sgtUtility();
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
                setsgtFichaEmp(rs.getString("UCO_FICHA_EMP"));
                //("ficha:"+getsigFichaEmp());
                verPersonal(getsgtFichaEmp(), planta);
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
      
       sgtUtility util = new sgtUtility();
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
                setsgtStatus(rs.getString("PER_STATUS"));
                setsgtCodPlanta(rs.getString("PER_COD_PLANTA"));
                setsgtCodDepart(rs.getString("PER_DEPART"));
                setsgtNombre(rs.getString("PER_NOMBRE"));
                setsgtDescDpto(rs.getString("dep_desc_dpto"));
                setsgtNomPlanta(rs.getString("PLA_NOM_PLANTA"));
                setsgtCedula(rs.getString("PER_CEDULA"));
                
                //("status: "+getsigStatus());
                validarContrasena(getsgtFichaEmp(), sgt_usuario);
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
      
       sgtUtility util = new sgtUtility();
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
                        "  public.t_sgt_outsourcing, \n" +
                        "  public.vv_departamento, \n" +
                        "  public.vv_planta \n" +
                        "WHERE \n" +
                        "  t_sgt_outsourcing.out_cod_planta = vv_planta.pla_cod_planta AND \n" +
                        "  vv_departamento.dep_cod_dpto = t_sgt_outsourcing.out_dpto AND \n" +
                        "  vv_departamento.dep_cod_planta = t_sgt_outsourcing.out_cod_planta AND \n" +
                        "  out_cod_planta='"+planta+"' AND \n" +
                        "  out_cedula='"+sgt_cedula_emp+"';";
            
            System.out.println("SQL outsourcing = "+ls_sql);
            con=util.Conexion_Sorg();
            stmt = con.createStatement();
            rs=stmt.executeQuery(ls_sql);

            while(rs.next()){                
                setsgtStatusOutsourcing(rs.getString("out_status"));
                setsgtCodPlanta(rs.getString("out_cod_planta"));
                setsgtCodDepart(rs.getString("out_dpto"));
                setsgtNombre(rs.getString("out_nombre"));
                setsgtDescDpto(rs.getString("dep_desc_dpto"));
                setsgtNomPlanta(rs.getString("pla_nom_planta"));
                setsgtCedula(rs.getString("out_cedula"));
                System.out.println("1 "+ getsgtCodPlanta());
                System.out.println("2 "+ getsgtCodDepart());
                System.out.println("3 "+ getsgtNombre());
                System.out.println("4 "+ getsgtDescDpto());
                System.out.println("5 "+ getsgNomPlanta());
                System.out.println("6 ");
                System.out.println("7 ");
                
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
       sgtUtility util = new sgtUtility();
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
                setsgtContrasena(rs.getString("UCO_CONTRASENA").toUpperCase());
                ////(getsigUsuario());
                System.out.println("Ya setie la contraseña: "+ getsgtContrasena());
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
