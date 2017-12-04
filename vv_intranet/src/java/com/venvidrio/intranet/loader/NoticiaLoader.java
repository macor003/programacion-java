/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venvidrio.intranet.loader;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.venvidrio.intranet.utility.intranetUtility;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

/**
 *
 * @author Ortegam
 */
public class NoticiaLoader {
    
    
    public String getNoticia() throws SQLException, FileNotFoundException, IOException{
        intranetUtility util=new intranetUtility();
        Connection con=util.Conexion_IT();
        Statement stmt=null;
        ResultSet rs=null;
        
        String html="";
        
        String sql="SELECT 	not_cod_noticia, \n" +
                        "	not_titulo_noticia, \n" +
                        "	TO_CHAR(not_fecha_noticia,'DD-MM-YYYY')as fecha, \n" +
                        "	not_descripcion_noticia, \n" +
                        "	not_imagen, not_nombre_imagen\n" +
                        "  FROM it_noticias ORDER BY not_fecha_noticia desc;";
        
        stmt=con.createStatement();
        rs=stmt.executeQuery(sql);
        while(rs.next()){
            String cod_noticia= rs.getString("not_cod_noticia");
            String titulo=rs.getString("not_titulo_noticia");
            String fecha=rs.getString("fecha");
            
            
            
            byte[] img_bytea=rs.getBytes("not_imagen");
            String img_code=Base64.encode(img_bytea);
            //System.out.println("CODE = "+img_code);
            String cdv_nombre_img=rs.getString("not_nombre_imagen");
            
            /*String ruta="C:\\Program Files\\Apache Software Foundation\\Tomcat 7.0\\webapps\\cdv_website\\cdv_imagenes\\"+cdv_nombre_img;
            OutputStream out = new BufferedOutputStream(new FileOutputStream(ruta));
            out.write(img_bytea);
            out.close();*/
            
            
            html+="<div class=\"col-sm-3 col-md-4\">\n" +
"                        <div class=\"thumbnail\"> \n" +
"                            <img class='img_noticia' src='data:image/png;base64,"+img_code+"' alt=\"\" class=\"img-responsive\"/>                  \n" +
"                            <div class=\"caption\">\n" +
"                                <div class='col-lg-12 contenedor'> "+
"                                   <p><span class=\"fa fa-calendar\"></span> "+fecha+"</p>\n" +
"                                   <h3><a href='#' style='font-size:20px;' id='"+cod_noticia+"' onclick='enviar_noticia(this.id);'>"+titulo+"</a></h3>\n" +
"                                </div>"+
"                          </div>\n" +
"                        </div>\n" +
"                    </div>";
            
            
            
            
        }
        
        html+="</div>";
        
        if(rs!=null) rs.close();
        if(stmt!=null) stmt.close();
        if(con!=null) con.close();
        
        return html;
    }
    
    public String getDetalleNoticia(String cod_noticia) throws SQLException{
        intranetUtility util=new intranetUtility();
        Connection con=util.Conexion_IT();
        Statement stmt=null;
        ResultSet rs=null;
        
        String html="";
        
        String sql="SELECT 	not_cod_noticia, \n" +
                        "	not_titulo_noticia, \n" +
                        "	TO_CHAR(not_fecha_noticia,'DD-MM-YYYY')as fecha, \n" +
                        "	not_descripcion_noticia, \n" +
                        "	not_imagen, not_nombre_imagen, not_autor\n" +
                        "  FROM it_noticias WHERE not_cod_noticia='"+cod_noticia+"'  ORDER BY fecha desc;";
        
        stmt=con.createStatement();
        rs=stmt.executeQuery(sql);
        while(rs.next()){
            String cod_noti= rs.getString("not_cod_noticia");
            String titulo=rs.getString("not_titulo_noticia");
            String fecha=rs.getString("fecha");
            String detalle = rs.getString("not_descripcion_noticia");
            String autor = rs.getString("not_autor");
            
            
            
            byte[] img_bytea=rs.getBytes("not_imagen");
            String img_code=Base64.encode(img_bytea);
            //System.out.println("CODE = "+img_code);
            String cdv_nombre_img=rs.getString("not_nombre_imagen");
            
            /*String ruta="C:\\Program Files\\Apache Software Foundation\\Tomcat 7.0\\webapps\\cdv_website\\cdv_imagenes\\"+cdv_nombre_img;
            OutputStream out = new BufferedOutputStream(new FileOutputStream(ruta));
            out.write(img_bytea);
            out.close();*/
            String detalle1 = detalle.replaceAll("\r\n", "<br>");
            
            html+="<h1 class=\"text-left\">"+titulo+"</h1>\n" +
"                <img src='data:image/png;base64,"+img_code+"' alt='' class='center-block'/><br>\n" +
"                <p class=\"text-justify\" style='font-size: 20px;'><strong>"+fecha+"</strong> - "+detalle1+ "<br><br><strong>Foto y Autor:&nbsp;"+autor+"</strong></p>"+
"                 <br><br>";
            
            
            
            
        }
        html+="</div>";
        
        if(rs!=null) rs.close();
        if(stmt!=null) stmt.close();
        if(con!=null) con.close();
        
        return html;
    }
    
}
