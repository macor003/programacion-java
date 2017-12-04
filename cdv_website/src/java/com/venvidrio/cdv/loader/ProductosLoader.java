/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venvidrio.cdv.loader;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.venvidrio.cdv.utility.cdvUtility;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;


/**
 *
 * @author carmonahjd
 */
public class ProductosLoader {
    
    public String getProductosHTML() throws SQLException, FileNotFoundException, IOException{
        cdvUtility util=new cdvUtility();
        Connection con=util.Conexion();
        Statement stmt=null;
        ResultSet rs=null;
        
        DecimalFormat df_precio=new DecimalFormat("#,##0.00");
        DecimalFormat df_cantidad=new DecimalFormat("#0");
        
        String html="<div class=\"container\">\n" +
"            <div class=\"jumbotron\"><center><h1><span class=\"glyphicon glyphicon-glass\"></span> PRODUCTOS</h1></center></div>";
        
        String html2="";
        
        String sql="SELECT \n" +
            "  t_cdv_productos.cdv_id_producto, \n" +
            "  t_cdv_productos.cdv_cod_cia, \n" +
            "  t_cdv_productos.cdv_desc_producto, \n" +
            "  t_cdv_productos.cdv_molde, \n" +
            "  t_cdv_productos.cdv_capacidad_llenado, \n" +
            "  t_cdv_productos.cdv_altura, \n" +
            "  t_cdv_productos.cdv_diametro, \n" +
            "  t_cdv_productos.cdv_tipo_tapa, \n" +
            "  t_cdv_productos.cdv_envases_caja, \n" +
            "  t_cdv_productos.cdv_precio, \n" +
            "  t_cdv_productos.cdv_img_producto, \n" +
            "  t_cdv_productos.cdv_fecha_hora_modif, \n" +
            "  t_cdv_productos.cdv_usuario_modif, \n" +
            "  t_cdv_productos.cdv_nombre_img, \n" +
            "  t_cdv_productos.cdv_estatus \n" +
            " FROM \n" +
            "  t_cdv_productos \n" +
            " WHERE \n" +
            "  t_cdv_productos.cdv_estatus = 'A' \n" +
            " ORDER BY \n" +
            "  t_cdv_productos.cdv_id_producto ASC";
        
        stmt=con.createStatement();
        rs=stmt.executeQuery(sql);
        while(rs.next()){
            int cdv_id_producto=rs.getInt("cdv_id_producto");
            String cdv_desc_producto=rs.getString("cdv_desc_producto");
            String cdv_molde=rs.getString("cdv_molde");
            String cdv_capacidad_llenado=rs.getString("cdv_capacidad_llenado");
            String cdv_altura=rs.getString("cdv_altura");
            String cdv_diametro=rs.getString("cdv_diametro");
            String cdv_tipo_tapa=rs.getString("cdv_tipo_tapa");
            int cdv_envases_caja=rs.getInt("cdv_envases_caja");
            double cdv_precio=rs.getDouble("cdv_precio");
            
            
            byte[] img_bytea=rs.getBytes("cdv_img_producto");
            String img_code=Base64.encode(img_bytea);
            //System.out.println("CODE = "+img_code);
            String cdv_nombre_img=rs.getString("cdv_nombre_img");
            
            /*String ruta="C:\\Program Files\\Apache Software Foundation\\Tomcat 7.0\\webapps\\cdv_website\\cdv_imagenes\\"+cdv_nombre_img;
            OutputStream out = new BufferedOutputStream(new FileOutputStream(ruta));
            out.write(img_bytea);
            out.close();*/
            
            
            html+="<div class='col-lg-3'>\n" +
"                <div class='thumbnail item' style=\"background-color: white; box-shadow: 0 8px 17px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);\">\n" +
"                    <img src=\"data:image/png;base64,"+img_code+"\" data-toggle=\"modal\" data-target=\"#PopUp"+cdv_id_producto+"\" class=\"img-rounded center-block\" style=\" cursor: pointer; width: 200px; height:200px;\" alt='item'/>\n" +
"                    <h4>"+cdv_desc_producto+"</h4>\n" +
"                    <div class=\"col-lg-4\"><label style=\"font-size: 16px;\">Cantidad:</label></div>\n" +
"                    <div class=\"col-lg-8\"><input name='lm_cantidad_1_"+cdv_id_producto+"' type=\"number\" class=\"form-control\" value=\"0\" style=\"height: 25px;\"></div>\n" +
"                    <button name='lm_cantidad_1_"+cdv_id_producto+"' onclick='AgregarCarrito(this.name)' type=\"button\" class='btn btn-primary btn-block agregar'><i class=\"glyphicon glyphicon-plus\"></i> Agregar al carrito</button>\n" +
"                </div>\n" +
"            </div>";
            
            
            
            html2+="<div class=\"modal fade\" id=\"PopUp"+cdv_id_producto+"\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">\n" +
"                <div class=\"modal-dialog\">\n" +
"                    <div class=\"modal-content\">\n" +
"                        <div class=\"modal-header\">\n" +
"                            <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">×</button>\n" +
"                            <h2 class=\"modal-title text-danger\">"+cdv_desc_producto+"</h2>\n" +
"                        </div>\n" +
"                        <div class=\"modal-body\">\n" +
"                            <div class=\"col-lg-8 col-lg-offset-2\">\n" +
"                                <img src=\"data:image/png;base64,"+img_code+"\" class=\"img-thumbnail center-block\" alt=\"\"/><br>\n" +
"                            \n" +
"                                <table class=\"table\">\n" +
"                                    <tbody>\n" +
"                                        <tr>\n" +
"                                            <td><strong>Molde:</strong></td>\n" +
"                                            <td>"+cdv_molde+"</td>\n" +
"                                        </tr>\n" +
"                                        <tr>\n" +
"                                            <td><strong>Capacidad de llenado:</strong></td>\n" +
"                                            <td>"+cdv_capacidad_llenado+"</td>\n" +
"                                        </tr>\n" +
"                                        <tr>\n" +
"                                            <td><strong>Alto:</strong></td>\n" +
"                                            <td>"+cdv_altura+"</td>\n" +
"                                        </tr>\n" +
"                                        <tr>\n" +
"                                            <td><strong>Diámetro:</strong></td>\n" +
"                                            <td>"+cdv_diametro+"</td>\n" +
"                                        </tr>\n" +
"                                        <tr>\n" +
"                                            <td><strong>Tipo de Tapa:</strong></td>\n" +
"                                            <td>"+cdv_tipo_tapa+"</td>\n" +
"                                        </tr>\n" +
"                                        <tr>\n" +
"                                            <td><strong>Envases por Caja:</strong></td>\n" +
"                                            <td>"+df_cantidad.format(cdv_envases_caja)+"</td>\n" +
"                                        </tr>\n" +
"                                        <tr>\n" +
"                                            <td><strong>P.V.P.</strong></td>\n" +
"                                            <td>Bs. "+df_precio.format(cdv_precio)+"  (IVA Incluido)</td>\n" +
"                                        </tr>\n" +
"                                    </tbody>\n" +
"                                </table>\n" +
"                            <div class=\"col-lg-4\"><h4><strong>Cantidad:</strong></h4></div>\n" +
"                            <div class=\"col-lg-8\"><input name='lm_cantidad_2_"+cdv_id_producto+"' type=\"number\" class=\"form-control\" value=\"10\" style=\"height: 35px; font-size: 18px;\"></div>\n" +
"                            <button name='lm_cantidad_2_"+cdv_id_producto+"' onclick='AgregarCarrito(this.name)' class=\"btn btn-primary btn-block\"><span class=\"glyphicon glyphicon-plus\"></span> Agregar</button>\n" +
"                            <button class=\"btn btn-danger btn-block\" data-dismiss=\"modal\"><span class=\"glyphicon glyphicon-remove\"></span> Cerrar</button>\n" +
"                            </div>\n" +
"                        </div>\n" +
"                        <div class=\"modal-footer\">\n" +
"                            \n" +
"                        </div>\n" +
"                    </div>\n" +
"                </div>\n" +
"            </div>";
        }
        
        html+="</div>";
        
        html+=html2;
        
        if(rs!=null) rs.close();
        if(stmt!=null) stmt.close();
        if(con!=null) con.close();
        
        return html;
    }
    
    
    
}
