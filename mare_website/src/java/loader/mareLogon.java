package loader;


import utility.mareUtility;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;

/**
 *
 * @author Ortegam
 */
public class mareLogon {
    private String mare_usuario;
    private String mare_contrasena;
    private String mare_cedula_personal;
    private String mare_cedula_usuario;
    private String mare_rol;
    private String mare_nombre;
    private String mare_apellido;
    private String mare_depart;
    private Context ctx = null;
    private String x;


    public String getUsuario () {
        return this.mare_usuario;
    }
    
    public void setUsuario (String mare_usuario) {
        this.mare_usuario = mare_usuario;
    }
    
    public String getContrasena () {
        return this.mare_contrasena;
    }
    
    public void setContrasena (String mare_contrasena) {
        this.mare_contrasena = mare_contrasena;
    }
    
    public String getCedula_usuario(){
        return this.mare_cedula_usuario;
    }
    
    public void setCedula_usuario(String mare_cedula_usuario){
        this.mare_cedula_usuario = mare_cedula_usuario;
    }

    public String getRol(){
        return this.mare_rol;
    }

    public void setRol(String mare_rol){
        this.mare_rol = mare_rol;
    }

    public String getNombre(){
        return this.mare_nombre;
    }

    public void setNombre(String mare_nombre){
        this.mare_nombre = mare_nombre;
    }
    
    public String getApellido(){
        return this.mare_apellido;
    }
    
    public void setApellido(String mare_apellido){
        this.mare_apellido = mare_apellido;
    }
    
    public String getX () { //metodo para retornar exception...
       return this.x;
    }

    public void setX(String x) {
       this.x=x;
   }

    public void usuarioAplicacion(String mare_usuario) throws Exception{
       //("Estoy en usuarioAplicacion");
       mareUtility util = new mareUtility();
       Statement stmt=null;
       ResultSet rs = null;
       Connection con = null;

////(con);
        String ls_sql = "SELECT \n" +
                        "  correo_usuario, \n" +
                        "  cod_rol\n" +
                        "FROM \n" +
                        "  public.mare_usuario\n" +
                        "WHERE\n" +
                        "  correo_usuario ='"+mare_usuario+"'; ";
        ////(ls_sql);
        try{
            
             con=util.Conexion_mare();             
            stmt = con.createStatement();           
            rs=stmt.executeQuery(ls_sql);            

            while(rs.next()){
                setRol(rs.getString("cod_rol"));
                verPersonal(mare_usuario);
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

    public void getUsuario(String mare_usuario) throws Exception{
       ////(sig_usuario);
       mareUtility util = new mareUtility();
       Statement stmt=null;
       ResultSet rs = null;
       Connection con = null;

////(con);
        String ls_sql = "SELECT \n" +
                        "  mare_usuario.correo_usuario, \n" +
                        "  mare_usuario.cod_rol\n" +
                        "FROM \n" +
                        "  public.mare_usuario\n" +
                        "WHERE\n" +
                        "  correo_usuario ='"+mare_usuario+"'; ";
        ////(ls_sql);
        try{
            con=util.Conexion_mare();
            stmt = con.createStatement();
            rs=stmt.executeQuery(ls_sql);

            while(rs.next()){
                setContrasena(rs.getString("correo_usuario"));
                //("ficha:"+getsigFichaEmp());
                verPersonal(getContrasena());
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

    public void verPersonal(String mare_usuario) throws Exception{    //Metodo que completa los set y get faltantes
       ////(sgt_usuario);
      
       mareUtility util = new mareUtility();
       Statement stmt=null;
       ResultSet rs = null;
       Connection con = null;

////(con);
        String ls_sql = "SELECT \n" +
                        "  correo_usuario, \n" +
                        "  cod_rol, \n" +
                        "  nombre_usuario, \n" +
                        "  apellido_usuario\n" +
                        "FROM \n" +
                        "  public.mare_usuario\n" +
                        "WHERE\n" +
                        "  correo_usuario ='"+mare_usuario+"';";
        System.out.println("SQL Personal = "+ls_sql);
        try{
            con=util.Conexion_mare();
            stmt = con.createStatement();
            rs=stmt.executeQuery(ls_sql);

            while(rs.next()){
                setNombre(rs.getString("nombre_usuario"));
                setApellido(rs.getString("apellido_usuario"));
                setRol(rs.getString("cod_rol"));                
                //("status: "+getsigStatus());
                validarContrasena(mare_usuario);
                ////(getsigUsuario());_
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
       
    public void validarContrasena(String mare_usuario) throws Exception{
       ////(sig_usuario);
       mareUtility util = new mareUtility();
       Statement stmt=null;
       ResultSet rs = null;
       Connection con = null;

////(con);
       /* String ls_sql = "SELECT UCO_CONTRASENA " +
                            "FROM  t_vv_usuario_contrasena " +
                            "WHERE UCO_FICHA_EMP = '"+sig_ficha_emp+"' "; */

        String ls_sql = "SELECT \n" +
                        "  pass_usuario\n" +
                        "FROM \n" +
                        "  public.mare_usuario\n" +
                        "WHERE\n" +
                        "  correo_usuario ='"+mare_usuario+"';";
        System.out.println("SQL Contraseña" + ls_sql);
        ////(ls_sql);
        try{
            con=util.Conexion_mare();
            stmt = con.createStatement();
            rs=stmt.executeQuery(ls_sql);

            while(rs.next()){
                setContrasena(rs.getString("pass_usuario").toUpperCase());
                ////(getsigUsuario());
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
