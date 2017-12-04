/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venvidrio.cdv.main;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author carmonahjd
 */
public class Pruebas {
    
    public static void main(String[] args) throws FileNotFoundException, SQLException, IOException {
        //new Pruebas().getImg();
        //new Pruebas().InsertarImg();
        int random=(int) (Math.random()*10);
        
        int numero=random * 6;
        System.out.println("Random = "+random);
        System.out.println("Num = "+numero);
    }
    
    public void getImg() throws FileNotFoundException, IOException, SQLException{
        Connection con=this.Conectar("CDV_DB");
        Statement stmt=null;
        ResultSet rs=null;
        
        String sql="SELECT \n" +
            "  t_cdv_productos.cdv_img_producto, \n" +
            "  t_cdv_productos.cdv_nombre_img \n" +
            " FROM \n" +
            "  t_cdv_productos \n" +
            " WHERE \n" +
            "  t_cdv_productos.cdv_id_producto = 1";
        
        stmt=con.createStatement();
        rs=stmt.executeQuery(sql);
        if(rs.next()){
            byte[] img_bytea=rs.getBytes("cdv_img_producto");            
            String nombre_img=rs.getString("cdv_nombre_img");
            
            Base64.encode(img_bytea);
            OutputStream out = new BufferedOutputStream(new FileOutputStream("C:\\Envases2\\"+nombre_img));
            out.write(img_bytea);
            out.close();
            
        }
         
         
         if(rs!=null) rs.close();
         if(stmt!=null) stmt.close();
         if(con!=null) con.close();
         
    }
    
    public void InsertarImg() throws FileNotFoundException, SQLException{
        Connection con=this.Conectar("CDV_DB");
        PreparedStatement pstmt = null;
        
        String ruta="C:\\Envases\\CV-07657.jpg";
        File file = new File(ruta);
        FileInputStream in = new FileInputStream(file);
        
        String sql="INSERT INTO t_cdv_productos(\n" +
            "            cdv_id_producto, cdv_cod_cia, cdv_desc_producto, cdv_molde, cdv_capacidad_llenado, \n" +
            "            cdv_altura, cdv_diametro, cdv_tipo_tapa, cdv_envases_caja, cdv_precio, \n" +
            "            cdv_img_producto, cdv_fecha_hora_modif, cdv_usuario_modif, cdv_nombre_img, cdv_estatus)\n" +
            "    VALUES (?, ?, ?, ?, ?, \n" +
            "            ?, ?, ?, ?, ?, \n" +
            "            ?, ?, ?, ?, ?)";
        
        pstmt=con.prepareStatement(sql);
        
        pstmt.setInt(1, 1);
        pstmt.setString(2, "00002");
        pstmt.setString(3, "TARRO 230 ML");
        pstmt.setString(4, "C-07657");
        pstmt.setString(5, "220 cc");
        pstmt.setString(6, "76,99 mm");
        pstmt.setString(7, "71,44 mm");
        pstmt.setString(8, "Twist Off");
        pstmt.setInt(9, 9);
        pstmt.setDouble(10, 124.44);
        pstmt.setBinaryStream(11, in, (int) file.length());
        pstmt.setTimestamp(12, new java.sql.Timestamp(new java.util.Date().getTime()));
        pstmt.setString(13, "J215847892");
        pstmt.setString(14, "CV-07657.jpg");
        pstmt.setString(15, "A");
        
        pstmt.executeUpdate();
        
        //*****************
        
        ruta="C:\\Envases\\CV-01832.jpg";
        file = new File(ruta);
        in = new FileInputStream(file);
        
        pstmt.setInt(1, 2);
        pstmt.setString(2, "00002");
        pstmt.setString(3, "BOCÓN 300 ML");
        pstmt.setString(4, "CV-01832");
        pstmt.setString(5, "290 cc");
        pstmt.setString(6, "130,18 mm");
        pstmt.setString(7, "63,09 mm");
        pstmt.setString(8, "Twist Off");
        pstmt.setInt(9, 24);
        pstmt.setDouble(10, 213.35);
        pstmt.setBinaryStream(11, in, (int) file.length());
        pstmt.setTimestamp(12, new java.sql.Timestamp(new java.util.Date().getTime()));
        pstmt.setString(13, "J215847892");
        pstmt.setString(14, "CV-01832.jpg");
        pstmt.setString(15, "A");
        
        pstmt.executeUpdate();
        
        //*****************
        
        ruta="C:\\Envases\\EV-01782.jpg";
        file = new File(ruta);
        in = new FileInputStream(file);
        
        pstmt.setInt(1, 3);
        pstmt.setString(2, "00002");
        pstmt.setString(3, "SALSA INGLESA 150 ML");
        pstmt.setString(4, "EV-01782");
        pstmt.setString(5, "140 cc");
        pstmt.setString(6, "161,54 mm");
        pstmt.setString(7, "48,03 mm");
        pstmt.setString(8, "Rosca");
        pstmt.setInt(9, 24);
        pstmt.setDouble(10, 210.84);
        pstmt.setBinaryStream(11, in, (int) file.length());
        pstmt.setTimestamp(12, new java.sql.Timestamp(new java.util.Date().getTime()));
        pstmt.setString(13, "J215847892");
        pstmt.setString(14, "EV-01782.jpg");
        pstmt.setString(15, "A");
        
        pstmt.executeUpdate();
        
        //********************
        
        ruta="C:\\Envases\\LV-02140.jpg";
        file = new File(ruta);
        in = new FileInputStream(file);
        
        pstmt.setInt(1, 4);
        pstmt.setString(2, "00002");
        pstmt.setString(3, "ESPAÑOLA 1000 ML");
        pstmt.setString(4, "LV-02140");
        pstmt.setString(5, "990 cc");
        pstmt.setString(6, "281,74 mm");
        pstmt.setString(7, "86,49 mm");
        pstmt.setString(8, "Rosca");
        pstmt.setInt(9, 12);
        pstmt.setDouble(10, 181.40);
        pstmt.setBinaryStream(11, in, (int) file.length());
        pstmt.setTimestamp(12, new java.sql.Timestamp(new java.util.Date().getTime()));
        pstmt.setString(13, "J215847892");
        pstmt.setString(14, "LV-02140.jpg");
        pstmt.setString(15, "A");
        
        pstmt.executeUpdate();
        
        if(pstmt!=null) pstmt.close();
        if(con!=null) con.close();
        
    }
    
    public Connection Conectar(String bd){
        Connection con = null;
        String url = "jdbc:postgresql://10.183.9.24:5432/"+bd;
        try {
            Class.forName("org.postgresql.Driver");
             con = DriverManager.getConnection(url,"casitavdv","L0g1t3ch");
            //System.out.println("EXITO");
        } catch (ClassNotFoundException  e) {
            System.out.println("El error=="+e);
        } catch(SQLException e){
            System.out.println("El error=="+e);
        }
        return con;
    }
    
}
