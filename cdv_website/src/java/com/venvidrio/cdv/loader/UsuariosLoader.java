/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venvidrio.cdv.loader;

import com.venvidrio.cdv.utility.cdvUtility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

/**
 *
 * @author carmonahjd
 */
public class UsuariosLoader {
    private String rif;
    private String password;
    private String nombre;
    private String correo;
    private String direccion;
    private int nivel;
    private String estatus;

    public String getRif() {
        return rif;
    }

    public void setRif(String rif) {
        this.rif = rif;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
    
    public void RegistrarUsuario() throws SQLException{
        cdvUtility util=new cdvUtility();
        Connection con=new cdvUtility().Conexion();
        PreparedStatement pstmt=null;
        
        String sql="INSERT INTO t_cdv_usuarios(\n" +
            "            cdv_id_usuario, cdv_password, cdv_nombre_usuario, cdv_correo, \n" +
            "            cdv_direccion, cdv_nivel, cdv_estatus, cdv_fecha_hora_modif) \n" +
            "    VALUES (?, ?, ?, ?, \n" +
            "            ?, ?, ?, ?)";
        
        pstmt=con.prepareStatement(sql);
        pstmt.setString(1, this.rif);
        pstmt.setString(2, this.password);
        pstmt.setString(3, this.nombre);
        pstmt.setString(4, this.correo);
        pstmt.setString(5, this.direccion);
        pstmt.setInt(6, this.nivel);
        pstmt.setString(7, this.estatus);
        pstmt.setTimestamp(8, new java.sql.Timestamp(new java.util.Date().getTime()));
        
        pstmt.executeUpdate();
        
        util.Correo(this.rif, this.password, this.correo);
        
        if(pstmt!=null) pstmt.close();
        if(con!=null) con.close();
        
    }
    
    public String ConfigurarUsuario(String rif) throws SQLException{
        Connection con=new cdvUtility().Conexion();
        Statement stmt=null;
        ResultSet rs=null;
        
        
        String html="";
        try{
            
        String sql1="SELECT \n" +
                    "  t_cdv_usuarios.cdv_nombre_usuario, \n" +
                    "  t_cdv_usuarios.cdv_correo, \n" +
                    "  t_cdv_usuarios.cdv_direccion, \n" +
                    "  t_cdv_usuarios.cdv_telefono_local, \n" +
                    "  t_cdv_usuarios.cdv_telefono_movil\n" +
                    "FROM \n" +
                    "  public.t_cdv_usuarios\n" +
                    "WHERE \n" +
                    "  t_cdv_usuarios.cdv_id_usuario ='"+rif+"';";
        stmt=con.createStatement();
        rs=stmt.executeQuery(sql1);
        while(rs.next()){
            String name = rs.getString("cdv_nombre_usuario");
            String mail = rs.getString("cdv_correo");
            String direction = rs.getString("cdv_direccion");
            String tlf_local = rs.getString("cdv_telefono_local");
            String tlf_movil = rs.getString("cdv_telefono_movil");
            
            
             html+="<fieldset>\n" +
"                                <div class=\"form-group\">\n" +
"                                    <label for=\"inputEmail\" class=\"col-lg-2 control-label\">Nombre</label>\n" +
"                                    <div class=\"col-lg-10\">\n" +
"                                        <input type=\"text\" name='lm_nombre' class=\"form-control\" id=\"inputEmail\" placeholder=\"Nombre\" value='"+name+"'>\n" +
"                                    </div>\n" +
"                                </div>\n" +
"                                <div class=\"form-group\">\n" +
"                                    <label for=\"inputEmail\" class=\"col-lg-2 control-label\">Email</label>\n" +
"                                    <div class=\"col-lg-10\">\n" +
"                                        <input type=\"email\" name='lm_email' class=\"form-control\" id=\"inputEmail\" placeholder=\"Email\" value='"+mail+"'>\n" +
"                                    </div>\n" +
"                                </div>\n" +
"                                <div class=\"form-group\">\n" +
"                                    <label for=\"inputEmail\" class=\"col-lg-2 control-label\">Dirección</label>\n" +
"                                    <div class=\"col-lg-10\">\n" +
"                                        <input type=\"text\" name='lm_direccion' class=\"form-control\" id=\"inputEmail\" placeholder=\"Dirección\" value='"+direction+"'>\n" +
"                                    </div>\n" +
"                                </div>\n" +
"                                <div class=\"form-group\">\n" +
"                                    <label for=\"inputEmail\" class=\"col-lg-2 control-label\">Telefono Fijo</label>\n" +
"                                    <div class=\"col-lg-10\">\n" +
"                                        <input type=\"number\" name='lm_tlf_fijo' id='tlf' class=\"form-control\" id=\"inputEmail\" placeholder=\"Telefono Fijo\" value='"+tlf_local+"'>\n" +
"                                    </div>\n" +
"                                </div>\n" +
"                                <div class=\"form-group\">\n" +
"                                    <label for=\"inputEmail\" class=\"col-lg-2 control-label\">Telefono Movil</label>\n" +
"                                    <div class=\"col-lg-10\">\n" +
"                                        <input type=\"number\" name='lm_tlf_movil' id='tlf2' class=\"form-control\" id=\"inputEmail\" placeholder=\"Telefono Movil\" value='"+tlf_movil+"'>\n" +
"                                    </div>\n" +
"                                </div>\n" +
"                                <div class=\"form-group\">\n" +
"                                    <div class=\"col-lg-10 col-lg-offset-7\">\n" +
"                                        <button id='boton_enviar' class=\"btn btn-success\" type='button' data-toggle='modal' data-target='#Confirmar'><span class=\"glyphicon glyphicon-ok\"></span> Guardar</button>\n" +
"                                        <button type=\"submit\" class=\"btn btn-danger\"><span class=\"glyphicon glyphicon-remove\"></span> Cancelar</button>\n" +
"                                    </div>\n" +
"                                </div>\n" +
"                            </fieldset>";
            
        }
        }catch (SQLException e) {
            System.out.println("ERROR: "+e);
        }finally {
            if(rs!=null) rs.close();
            if(stmt!=null) stmt.close();
            if(con!=null) con.close();  
        }       
        
        return html;
    }
    
    public void ActualizarUsuario(String rif, String name, String email, String direction, String tlf_fijo, String tlf_movil) throws SQLException{
        cdvUtility util=new cdvUtility();
        Connection con=new cdvUtility().Conexion();
        PreparedStatement pstmt=null;
        
        String sql="UPDATE t_cdv_usuarios\n" +
                    "   SET cdv_nombre_usuario=?, cdv_correo=?, \n" +
                    "       cdv_direccion=?, cdv_telefono_local=?, cdv_telefono_movil=?\n" +
                    " WHERE cdv_id_usuario='"+rif.toUpperCase()+"';";
        
        pstmt=con.prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.setString(2, email);
        pstmt.setString(3, direction);
        pstmt.setString(4, tlf_fijo);
        pstmt.setString(5, tlf_movil);
        
        pstmt.executeUpdate();
        System.out.println("RIF"+ rif.toUpperCase());
        //util.Correo(this.rif, this.password, this.correo);
        
        if(pstmt!=null) pstmt.close();
        if(con!=null) con.close();
        
    }
    
    public void CambioClaveUsuario(String rif, String contraseña) throws SQLException{
        cdvUtility util=new cdvUtility();
        Connection con=new cdvUtility().Conexion();
        PreparedStatement pstmt=null;
        
        String sql="UPDATE t_cdv_usuarios\n" +
                    "   SET cdv_password=? "+
                    " WHERE cdv_id_usuario='"+rif.toUpperCase()+"';";
        
        pstmt=con.prepareStatement(sql);
        pstmt.setString(1, contraseña);
        
        pstmt.executeUpdate();
        //util.Correo(this.rif, this.password, this.correo);
        
        if(pstmt!=null) pstmt.close();
        if(con!=null) con.close();
        
    }
    
    public String GenerarPasswdAleatorio(){
        String base = "a0bcdLMNOPQRSTUVWXYZefg1hijkl2mnop3qrst4uvw5xyzA6BCD7EFG8HIJK9";
        String passwd="";
        int longitud=6;
        
        for(int i = 0; i < longitud; i++){
            String letra="";
            while(passwd.contains(letra)){
                int random = (int) (Math.random()*10);
                int numero = random * longitud;
                letra = base.substring(numero, numero+1);
            }
            passwd += letra;
        }
        
        return passwd;
    }
}
