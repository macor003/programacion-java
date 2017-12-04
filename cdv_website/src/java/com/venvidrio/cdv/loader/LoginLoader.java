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

/**
 *
 * @author CarmonaHJD
 */
public class LoginLoader {
    private String id_usuario;
    private String nombre_usuario;
    private String correo;
    private String direccion;
    private String estatus;
    private int nivel;

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
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

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
    
    
    public int ValidaDatosUsuario(String rif, String passwd) throws SQLException{
        Connection con=new cdvUtility().Conexion();
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        
        int cod=0;//No Existe el usuario
        
        String sql="SELECT \n" +
            "  t_cdv_usuarios.cdv_id_usuario, \n" +
            "  t_cdv_usuarios.cdv_password, \n" +
            "  t_cdv_usuarios.cdv_nombre_usuario, \n" +
            "  t_cdv_usuarios.cdv_correo, \n" +
            "  t_cdv_usuarios.cdv_direccion, \n" +
            "  t_cdv_usuarios.cdv_estatus, \n" +
            "  t_cdv_usuarios.cdv_nivel \n" +
            " FROM \n" +
            "  t_cdv_usuarios \n" +
            " WHERE \n" +
            "  trim(upper(t_cdv_usuarios.cdv_id_usuario)) = ?";
        
        pstmt=con.prepareStatement(sql);
        pstmt.setString(1, rif);
        
        rs=pstmt.executeQuery();
        
        if(rs.next()){
            String cdv_estatus=rs.getString("cdv_estatus");
            if(cdv_estatus.equals("A")){//Activo
                String cdv_password=rs.getString("cdv_password").trim();
                
                if(cdv_password.equals(passwd)){//Passwd Validado
                    cod=1;//Usuario Ingresa
                    String cdv_nombre_usuario=rs.getString("cdv_nombre_usuario");
                    String cdv_correo=rs.getString("cdv_correo");
                    String cdv_direccion=rs.getString("cdv_direccion");
                    int cdv_nivel=rs.getInt("cdv_nivel");
                    
                    this.setId_usuario(rif);
                    this.setNombre_usuario(cdv_nombre_usuario);
                    this.setCorreo(cdv_correo);
                    this.setDireccion(cdv_direccion);
                    this.setNivel(cdv_nivel);
                    
                    
                }else{//Passwd Incorrecto
                    cod=2;
                }
            }else{//Inactivo
                cod=3;//Usuario Inactivo
            }
        }else{
            cod=0;
        }
        
        if(rs!=null) rs.close();
        if(pstmt!=null) pstmt.close();
        if(con!=null) con.close();
        
        return cod;
    }
}
