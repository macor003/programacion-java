/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venvidrio.cdv.loader;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.venvidrio.cdv.utility.cdvUtility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author CarmonaHJD
 */
public class CarritoLoader {
    
    private int id_pedido;
    private int id_producto;
    private String id_usuario;
    private String estatus_pedido;
    private String nro_pago;
    private String cod_cia;
    private double cant_original;
    private double precio;
    private double total_original;
    private double cant_pendiente;
    private double total_pendiente;
    private double cant_despachada;
    private String estatus;
    private String usuario_modif;
    
    
    

    public int getId_pedido() {
        return id_pedido;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getEstatus_pedido() {
        return estatus_pedido;
    }

    public void setEstatus_pedido(String estatus_pedido) {
        this.estatus_pedido = estatus_pedido;
    }

    public String getNro_pago() {
        return nro_pago;
    }

    public void setNro_pago(String nro_pago) {
        this.nro_pago = nro_pago;
    }
    
    public String getCod_cia() {
        return cod_cia;
    }

    public void setCod_cia(String cod_cia) {
        this.cod_cia = cod_cia;
    }

    public double getCant_original() {
        return cant_original;
    }

    public void setCant_original(double cant_original) {
        this.cant_original = cant_original;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getTotal_original() {
        return total_original;
    }

    public void setTotal_original(double total_original) {
        this.total_original = total_original;
    }

    public double getCant_pendiente() {
        return cant_pendiente;
    }

    public void setCant_pendiente(double cant_pendiente) {
        this.cant_pendiente = cant_pendiente;
    }

    public double getTotal_pendiente() {
        return total_pendiente;
    }

    public void setTotal_pendiente(double total_pendiente) {
        this.total_pendiente = total_pendiente;
    }

    public double getCant_despachada() {
        return cant_despachada;
    }

    public void setCant_despachada(double cant_despachada) {
        this.cant_despachada = cant_despachada;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getUsuario_modif() {
        return usuario_modif;
    }

    public void setUsuario_modif(String usuario_modif) {
        this.usuario_modif = usuario_modif;
    }
    
    public String AgregarProductoCarrito(String id_producto, double cantidad, String rif) throws SQLException{
        Connection con=new cdvUtility().Conexion();
        Statement stmt=null;
        ResultSet rs=null;
        
        String retorno="";
        
        String sql="SELECT \n" +
            "  t_cdv_productos.cdv_cod_cia, \n" +
            "  t_cdv_productos.cdv_precio \n" +
            " FROM \n" +
            "  t_cdv_productos \n" +
            " WHERE \n" +
            "  t_cdv_productos.cdv_id_producto = 1";
        
        stmt=con.createStatement();
        rs=stmt.executeQuery(sql);
        if(rs.next()){
            String cdv_cod_cia=rs.getString("cdv_cod_cia");
            double cdv_precio=rs.getDouble("cdv_precio");
            
            double total=cdv_precio*cantidad;
            
            this.setId_producto(Integer.parseInt(id_producto));
            this.setCod_cia(cdv_cod_cia);
            this.setCant_original(cantidad);
            this.setPrecio(cdv_precio);
            this.setTotal_original(total);
            this.setCant_pendiente(cantidad);
            this.setTotal_pendiente(total);
            this.setCant_despachada(0);
            this.setEstatus("EPC");
            this.setEstatus_pedido("EPC");
            this.setId_usuario(rif);
            
            retorno=AgregarProductoCarrito(con);
            
        }
        
        if(rs!=null) rs.close();
        if(stmt!=null) stmt.close();
        if(con!=null) con.close();
        
        return retorno;
    }
    
    public void x(){
        ArrayList lista = new ArrayList();
        
        
        lista.add("w");
        lista.add("w");
        lista.add("w");
        lista.add("w");
        lista.add("w");
        
        lista.trimToSize();
        lista.size();
        
        
        
        
        String[] e=new String[5];
        e[0]="w";
        e[1]="q";
        e[2]="e";
        e[3]="r";
        e[4]="t";
    }
    
    public String getStatus(String cod_status){
        cdvUtility util = new cdvUtility();
        Connection con = null;
        Statement stm=null;
        ResultSet rs=null;
        String html="<select class='form-control' name='lm_cod_estatus' >";
 
            try {
                String sql = "SELECT cdv_cod_estatus, cdv_desc_estatus \n" +
                            "  FROM t_cdv_estatus order by cdv_orden;";
                con = util.Conexion();
                stm = con.createStatement();
                rs = stm.executeQuery(sql);
                while(rs.next()){
                    String desc =rs.getString("cdv_desc_estatus");
                    String cod = rs.getString("cdv_cod_estatus");
                    html+="<option value='"+ cod +"'";
                            if(cod.equals(cod_status)){
                                html+="selected";
                            }
                            html+=">"+desc+"</option>";

                }
              html+="</select>";
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
        
        return html;
    }
    
    public String AgregarProductoCarrito(Connection con) throws SQLException{
        Statement stmt=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        
        int ultimo_pedido=getUltimoPedidoUsuario(this.getId_usuario());
        
        if(ultimo_pedido == 0){
            ultimo_pedido = getConsecutivoPedido(con);
            this.setId_pedido(ultimo_pedido);
            
            String sql="INSERT INTO t_cdv_encabezado_pedido( \n" +
                "            cdv_id_pedido, cdv_id_usuario, cdv_estatus_pedido, cdv_fecha1) \n" +
                "    VALUES (?, ?, ?, ?)";

            pstmt=con.prepareStatement(sql);
            pstmt.setInt(1, this.getId_pedido());
            pstmt.setString(2, this.getId_usuario());
            pstmt.setString(3, this.getEstatus_pedido());
            pstmt.setTimestamp(4, new java.sql.Timestamp(new java.util.Date().getTime()));

            pstmt.executeUpdate();

        }else{
            this.setId_pedido(ultimo_pedido);
        }
        
        String sql2="SELECT upsert_producto_carrito( \n" +
            "    ?, ?, ?, ?, ?, ?, \n" +
            "    ?, ?, ?, ?, ? )";
        
        pstmt=con.prepareStatement(sql2);
        
        /*pstmt.setInt(1, this.getId_pedido());
        pstmt.setInt(2, this.getId_producto());
        pstmt.setString(3, this.getCod_cia());
        pstmt.setDouble(4, this.getCant_original());
        pstmt.setDouble(5, this.getPrecio());
        pstmt.setDouble(6, this.getTotal_original());
        pstmt.setDouble(7, this.getCant_pendiente());
        pstmt.setDouble(8, this.getTotal_pendiente());
        pstmt.setDouble(9, this.getCant_despachada());
        pstmt.setString(10, this.getEstatus());
        pstmt.setString(11, this.getId_usuario());*/
        
        int type=java.sql.Types.NUMERIC;
        int type2=java.sql.Types.DOUBLE;
        
        pstmt.setObject(1, this.getId_pedido(), type);
        pstmt.setObject(2, this.getId_producto(), type);
        pstmt.setString(3, this.getCod_cia());
        pstmt.setObject(4, this.getCant_original(), type2);
        pstmt.setObject(5, this.getPrecio(), type2);
        pstmt.setObject(6, this.getTotal_original(), type2);
        pstmt.setObject(7, this.getCant_pendiente(), type2);
        pstmt.setObject(8, this.getTotal_pendiente(), type2);
        pstmt.setObject(9, this.getCant_despachada(), type2);
        pstmt.setString(10, this.getEstatus());
        pstmt.setString(11, this.getId_usuario());
        
        
        rs=pstmt.executeQuery();
        String retorno="";
        if(rs.next()){
            int count_carrito=rs.getInt(1);
            DecimalFormat df=new DecimalFormat("#,##0");
            retorno=df.format(count_carrito);
        }
        
        
        
        if(rs!=null) rs.close();
        if(pstmt!=null) pstmt.close();
        if(stmt!=null) stmt.close();
        
        return retorno;
        
    }
    
    public int getConsecutivoPedido(Connection con) throws SQLException{
        Statement stmt=null;
        ResultSet rs=null;
        
        int consec=1;
        
        String sql="SELECT max(cdv_id_pedido) as cdv_id_pedido FROM t_cdv_encabezado_pedido";
        stmt=con.createStatement();
        rs=stmt.executeQuery(sql);
        if(rs.next()){
            consec=rs.getInt("cdv_id_pedido")+1;
        }else{
            consec=1;
        }
        
        if(rs!=null) rs.close();
        if(stmt!=null) stmt.close();
        
        return consec;
    }
    
    public int getUltimoPedidoUsuario(String rif) throws SQLException{
        Connection con=new cdvUtility().Conexion();
        Statement stmt=null;
        ResultSet rs=null;
        
        int pedido=0;
        
        String sql="SELECT \n" +
            "  max(t_cdv_encabezado_pedido.cdv_id_pedido) as cdv_id_pedido \n" +
            " FROM \n" +
            "  t_cdv_encabezado_pedido \n" +
            " WHERE \n" +
            "  trim(upper(t_cdv_encabezado_pedido.cdv_id_usuario)) = '"+rif+"' AND \n" +
            "  t_cdv_encabezado_pedido.cdv_estatus_pedido = 'EPC'";
        
        stmt=con.createStatement();
        rs=stmt.executeQuery(sql);
        if(rs.next()){
            pedido=rs.getInt("cdv_id_pedido");
        }else{
            pedido=0;
        }
        
        
        
        if(rs!=null) rs.close();
        if(stmt!=null) stmt.close();
        if(con!=null) con.close();
        
        return pedido;
    }
    
    public String getProductosCarrito(String rif) throws SQLException{
        Connection con=new cdvUtility().Conexion();
        Statement stmt=null;
        ResultSet rs=null;
        
        DecimalFormat df1=new DecimalFormat("#,##0.00");
        DecimalFormat df2=new DecimalFormat("#,##0");
        
        double suma_total=0;
        
        String html="";
        
        String sql="SELECT \n" +
            "  t_cdv_encabezado_pedido.cdv_id_pedido, \n" +
            "  t_cdv_detalle_pedido.cdv_id_producto, \n" +
            "  t_cdv_detalle_pedido.cdv_cantidad_original, \n" +
            "  t_cdv_detalle_pedido.cdv_precio, \n" +
            "  t_cdv_detalle_pedido.cdv_total_original, \n" +
            "  t_cdv_productos.cdv_desc_producto, \n" +
            "  t_cdv_productos.cdv_molde, \n" +
            "  t_cdv_productos.cdv_capacidad_llenado, \n" +
            "  t_cdv_productos.cdv_altura, \n" +
            "  t_cdv_productos.cdv_diametro, \n" +
            "  t_cdv_productos.cdv_tipo_tapa, \n" +
            "  t_cdv_productos.cdv_envases_caja, \n" +
            "  t_cdv_productos.cdv_img_producto, \n" +
            "  t_cdv_productos.cdv_nombre_img \n" +
            " FROM \n" +
            "  t_cdv_detalle_pedido, \n" +
            "  t_cdv_encabezado_pedido, \n" +
            "  t_cdv_productos \n" +
            " WHERE \n" +
            "  t_cdv_detalle_pedido.cdv_id_pedido = t_cdv_encabezado_pedido.cdv_id_pedido AND \n" +
            "  t_cdv_detalle_pedido.cdv_estatus = t_cdv_encabezado_pedido.cdv_estatus_pedido AND \n" +
            "  t_cdv_productos.cdv_id_producto = t_cdv_detalle_pedido.cdv_id_producto AND \n" +
            "  t_cdv_productos.cdv_cod_cia = t_cdv_detalle_pedido.cdv_cod_cia AND \n" +
            "  t_cdv_encabezado_pedido.cdv_id_usuario = '"+rif+"' AND \n" +
            "  t_cdv_encabezado_pedido.cdv_estatus_pedido = 'EPC'";
        
        stmt=con.createStatement();
        rs=stmt.executeQuery(sql);
        while(rs.next()){
            int cdv_id_pedido=rs.getInt("cdv_id_pedido");
            int cdv_id_producto=rs.getInt("cdv_id_producto");
            double cdv_cantidad_original=rs.getDouble("cdv_cantidad_original");
            double cdv_precio=rs.getDouble("cdv_precio");
            double cdv_total_original=rs.getDouble("cdv_total_original");
            String cdv_desc_producto=rs.getString("cdv_desc_producto");
            String cdv_molde=rs.getString("cdv_molde");
            String cdv_capacidad_llenado=rs.getString("cdv_capacidad_llenado");
            String cdv_altura=rs.getString("cdv_altura");
            String cdv_diametro=rs.getString("cdv_diametro");
            String cdv_tipo_tapa=rs.getString("cdv_tipo_tapa");
            int cdv_envases_caja=rs.getInt("cdv_envases_caja");
            
            suma_total+=cdv_total_original;
            
            byte[] img_bytea=rs.getBytes("cdv_img_producto");
            String img_code=Base64.encode(img_bytea);
            
            String cdv_nombre_img=rs.getString("cdv_nombre_img");
            
            
            html+="<!--------------------------AQUI VA EL DETALLE DEL PEDIDO---------------------------------------->\n" +
            "            <div class=\"thumbnail\" id='mover"+cdv_id_producto+"' style=\"background-color: white; box-shadow: 0 8px 17px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);\">\n" +
            "                <table class=\"table\">\n" +
            "                    <tbody>\n" +
            "                        <tr>\n" +
            "                            <td style=\"width: 120px;\">\n" +
            "                                <img src=\"data:image/png;base64,"+img_code+"\" alt=\"\" class=\"img-rounded\" style=\"width: 100px; height:150px;\"/>\n" +
            "                            </td>\n" +
            "                            <td>\n" +
            "                                <h3 class=\"text-danger\">"+cdv_desc_producto+"</h3>\n" +
            "                                <h5><strong>Cantidad por Caja:</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+df2.format(cdv_envases_caja)+"</h5>\n" +
            "                                <h5><strong>Cantidad de Cajas:</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+df2.format(cdv_cantidad_original)+"</h5>\n" +
            "                                <h5><strong>Costo de Caja:</strong> "+df1.format(cdv_precio)+" Bs.</h5>\n" +
            "                                <h5><strong>Costo Total:</strong> "+df1.format(cdv_total_original)+" Bs.</h5>\n" +
            "                            </td>\n" +
            "                            <td style=\"width: 100px;\">\n" +
            "                                <button class=\"btn btn-primary btn-block\" data-toggle=\"modal\" data-target=\"#PRODUCTO_"+cdv_id_producto+"\"><i class=\"glyphicon glyphicon-pencil\"></i> Editar</button>\n" +
            "                                <button class=\"btn btn-danger btn-block\" id='mover"+cdv_id_producto+"' name='"+cdv_id_pedido+"' onclick='Quitar(this.id, this.name);'><i class=\"glyphicon glyphicon-trash\"></i> Eliminar</button>                                \n" +
            "                            </td>\n" +
            "                        </tr>\n" +
            "                    </tbody>\n" +
            "                </table>             \n" +
            "            </div>\n" +
            "            \n" +
            "            <!---------------ESTA ES LA VENTANA EMERGENTE PARA EDITAR LA CANTIDAD  ---------------------->\n" +
            "            <div class=\"modal fade\" id=\"PRODUCTO_"+cdv_id_producto+"\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">\n" +
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
            "                                            <td>"+df2.format(cdv_envases_caja)+"</td>\n" +
            "                                        </tr>\n" +
            "                                        <tr>\n" +
            "                                            <td><strong>P.V.P.</strong></td>\n" +
            "                                            <td>Bs. "+df1.format(cdv_precio)+" (IVA Incluido)</td>\n" +
            "                                        </tr>\n" +
            "                                    </tbody>\n" +
            "                                </table>\n" +
            "                            <div class=\"col-lg-4\"><h4><strong>Cantidad:</strong></h4></div>\n" +
            "                            <div class=\"col-lg-8\"><input type=\"number\" class=\"form-control\" value=\""+cdv_cantidad_original+"\" style=\"height: 35px; font-size: 18px;\"></div>\n" +
            "                            <button class=\"btn btn-primary btn-block\"><span class=\"glyphicon glyphicon-ok\"></span> Guardar</button>\n" +
            "                            <button class=\"btn btn-danger btn-block\" data-dismiss=\"modal\"><span class=\"glyphicon glyphicon-remove\"></span> Cancelar</button>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                        <div class=\"modal-footer\">\n" +
            "                            \n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "            </div>";
            
           
            
        }
        
        html+="<div class=\"jumbotron\">\n" +
"                <center>\n" +
"                    <h2><strong>TOTAL A CANCELAR:</strong>&nbsp;&nbsp; "+df1.format(suma_total)+" Bs.</h2>\n" +
"                    <button class=\"btn btn-success btn-lg\"><span class=\"glyphicon glyphicon-ok\"></span> Enviar a cotización</button>\n" +
"                </center>\n" +
"            </div>";
        
        
        if(rs!=null) rs.close();
        if(stmt!=null) stmt.close();
        if(con!=null) con.close();
        
        return html;
    }
    
    public String[] getPedidosClientes() throws SQLException{
        Connection con=new cdvUtility().Conexion();
        Statement stmt=null;
        ResultSet rs=null;
        
        DecimalFormat df1=new DecimalFormat("#,##0.00");
        DecimalFormat df2=new DecimalFormat("#,##0");
        
        double suma_total=0;
        String cod ="";
        String[] html= new String[]{"",""};
        try{
            
        
            
        
        System.out.println("Obteniendo pedidos de los clientes");
        String sql1="SELECT \n" +
                    "  t_cdv_encabezado_pedido.cdv_id_pedido, \n" +
                    "  t_cdv_usuarios.cdv_nombre_usuario, \n" +
                    "  t_cdv_encabezado_pedido.cdv_fecha1, \n" +
                    "  t_cdv_estatus.cdv_desc_estatus\n" +
                    "FROM \n" +
                    "  public.t_cdv_encabezado_pedido, \n" +
                    "  public.t_cdv_usuarios, \n" +
                    "  public.t_cdv_estatus\n" +
                    "WHERE \n" +
                    "  t_cdv_encabezado_pedido.cdv_id_usuario = t_cdv_usuarios.cdv_id_usuario AND\n" +
                    "  t_cdv_encabezado_pedido.cdv_estatus_pedido = t_cdv_estatus.cdv_cod_estatus AND" +
                    "  t_cdv_encabezado_pedido.cdv_estatus_pedido != 'EPC'";
        stmt=con.createStatement();
        rs=stmt.executeQuery(sql1);
        System.out.println("SQL: "+sql1);
        while(rs.next()){
            cod = rs.getString("cdv_id_pedido");
            String nombre = rs.getString("cdv_nombre_usuario");
            String fecha = rs.getString("cdv_fecha1");
            String status = rs.getString("cdv_desc_estatus");
            
            html[0]+="<tr>\n" +
                "        <td>"+cod+"</td>\n" +
                "        <td>"+nombre+"</td>\n" +
                "        <td>"+fecha.substring(0,11)+"</td>\n" +
                "        <td>"+status+"</td>\n" +
                "        <td><a class='btn btn-sm btn-primary' style='margin-top: -4px;' data-toggle='modal' data-target='#Editar"+cod+"'><i class='glyphicon glyphicon-eye-open'></i> Ver mas detalles</a></td>\n" +
                "     </tr>\n";
            html[1]+= getModalPedidosClientes(cod);
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
    
    public String getModalPedidosClientes(String codigo) throws SQLException{
        Connection con=new cdvUtility().Conexion();
        Statement stmt=null;
        ResultSet rs=null;
        
        DecimalFormat df1=new DecimalFormat("#,##0.00");
        DecimalFormat df2=new DecimalFormat("#,##0");
        
        double suma_total=0;
        
        String html="";
        try{
            
        String sql1="SELECT \n" +
                    "  t_cdv_encabezado_pedido.cdv_id_pedido, \n" +
                    "  t_cdv_usuarios.cdv_nombre_usuario, \n" +
                    "  t_cdv_estatus.cdv_desc_estatus, \n" +
                    "  TO_CHAR(t_cdv_encabezado_pedido.cdv_fecha1, 'DD-MM-YYYY') AS fecha_pedido, \n" +
                    "  TO_CHAR(t_cdv_encabezado_pedido.cdv_fecha_pago, 'DD-MM-YYYY') AS fecha_pago,\n" +
                    "  t_cdv_encabezado_pedido.cdv_nom_banco," +
                    "  t_cdv_encabezado_pedido.cdv_tipo_pago, \n" +
                    "  t_cdv_encabezado_pedido.cdv_nro_pago, \n" +
                    "  t_cdv_detalle_pedido.cdv_id_producto, \n" +
                    "  t_cdv_productos.cdv_desc_producto, \n" +
                    "  t_cdv_detalle_pedido.cdv_cantidad_original, t_cdv_encabezado_pedido.cdv_estatus_pedido, \n" +
                    "  t_cdv_detalle_pedido.cdv_precio, \n" +
                    "  t_cdv_detalle_pedido.cdv_total_original, \n" +
                    "  t_cdv_detalle_pedido.cdv_cantidad_pendiente, \n" +
                    "  t_cdv_detalle_pedido.cdv_total_pendiente, \n" +
                    "  t_cdv_detalle_pedido.cdv_cantidad_despachada\n" +
                    "FROM \n" +
                    "  public.t_cdv_detalle_pedido, \n" +
                    "  public.t_cdv_encabezado_pedido, \n" +
                    "  public.t_cdv_productos, \n" +
                    "  public.t_cdv_usuarios, \n" +
                    "  public.t_cdv_estatus\n" +
                    "WHERE \n" +
                    "  t_cdv_detalle_pedido.cdv_id_pedido = t_cdv_encabezado_pedido.cdv_id_pedido AND\n" +
                    "  t_cdv_detalle_pedido.cdv_id_producto = t_cdv_productos.cdv_id_producto AND\n" +
                    "  t_cdv_encabezado_pedido.cdv_id_usuario = t_cdv_usuarios.cdv_id_usuario AND\n" +
                    "  t_cdv_encabezado_pedido.cdv_estatus_pedido = t_cdv_estatus.cdv_cod_estatus AND\n" +
                    "  t_cdv_encabezado_pedido.cdv_id_pedido ='"+codigo+"';";
        stmt=con.createStatement();
        rs=stmt.executeQuery(sql1);
        while(rs.next()){
            String cod = rs.getString("cdv_id_pedido");
            String nombre = rs.getString("cdv_nombre_usuario");
            String fecha = rs.getString("fecha_pedido");
            String cod_status = rs.getString("cdv_estatus_pedido");
            String status = getStatus(cod_status);
            String fecha_pago = rs.getString("fecha_pago");
            String cod_pdto = rs.getString("cdv_id_producto");
            String desc_pdto = rs.getString("cdv_desc_producto");
            String cantidad_org = rs.getString("cdv_cantidad_original");            
            double precio = rs.getDouble("cdv_precio");
            String banco =rs.getString("cdv_nom_banco");
            String tipo_pago = rs.getString("cdv_tipo_pago");
            String total_org = rs.getString("cdv_total_original");
            if(fecha_pago == null){
                fecha_pago ="N/A";
            }
            String numero_pago = rs.getString("cdv_nro_pago");
            if(numero_pago == null){
                numero_pago="N/A";
            }
            if(banco == null){
                banco="N/A";
            }
            if(tipo_pago == null){
                tipo_pago="N/A";
            }
             html+="<div class='modal fade' id='Editar"+codigo+"' tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myLargeModalLabel\"aria-hidden=\"true\">\n" +
"                <div class=\"modal-dialog modal-lg\">\n" +
"                    <div class=\"modal-content\">\n" +
"                        <div class=\"modal-header\">\n" +
"                            <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">×</button>\n" +
"                            <h2 class=\"modal-title text-danger text-center\"><u>Detalles del Pedido</u></h2>\n" +
"                        </div>\n" +
"                        <div class=\"modal-body\">\n" +
"                            <div class=\"col-lg-12\">\n" +
"                                <div class=\"col-lg-6\">\n" +
"                                    <table>\n" +
"                                        <tbody>\n" +
"                                            <tr>\n" +
"                                                <td><strong>Código:</strong></td>\n" +
"                                                <td class=\"text-danger\">&nbsp;"+cod+"</td>\n" +
"                                            </tr>\n" +
"                                            <tr>\n" +
"                                                <td><strong>A Nombre de:</strong></td>\n" +
"                                                <td>&nbsp;"+nombre+"</td>\n" +
"                                            </tr>\n" +
"                                            \n" +
"                                            <tr>\n" +
"                                                <td><strong>Fecha:</strong></td> \n" +
"                                                <td>&nbsp;"+fecha+"</td>\n" +
"                                            </tr>\n" +
"                                            <tr>\n" +
"                                                <td><strong>Status:</strong></td>\n" +
"                                                <td>\n" +status+"</td>\n" +
"                                            </tr>\n" +
"                                        </tbody>\n" +
"                                    </table>\n" +
"                                </div>\n" +
"                                <div class=\"col-lg-6\">\n" +
"                                     <table>\n" +
"                                        <tbody>\n" +
"                                            <tr>\n" +
"                                                <td><strong>Fecha Pago:</strong></td>\n" +
"                                                <td class=\"text-danger\">&nbsp;"+fecha_pago+"</td>\n" +
"                                            </tr>\n" +
"                                            <tr>\n" +
"                                                <td><strong>Número de Confirmación:</strong></td>\n" +
"                                                <td class=\"text-danger\">&nbsp;"+numero_pago+"</td>\n" +
"                                            </tr>\n" +
"                                            <tr>\n" +
"                                                <td><strong>Banco:</strong></td>\n" +
"                                                <td class=\"text-danger\">&nbsp;"+banco+"</td>\n" +
"                                            </tr>\n" +
"                                            <tr>\n" +
"                                                <td><strong>Tipo Pago:</strong></td>\n" +
"                                                <td class=\"text-danger\">&nbsp;"+tipo_pago+"</td>\n" +
"                                            </tr>\n" +
"                                        </tbody>\n" +
"                                    </table>\n" +
"                                </div><br>\n" +
"                                <br>\n" +
"                                <div class=\"col-lg-12\"><h3 class=\"text-center text-danger\"><u>Productos</u></h3></div>\n" +
"                                <table class=\"table table-hover\" >\n" +
"                                    <thead>\n" +
"                                        <tr>\n" +
"                                            <th style=\" width: 50px;\"><strong>Código</strong></th>\n" +
"                                            <th>Descripción</th>\n" +
"                                            <th style=\" width: 150px;\"><strong>Cant. por Despachar</strong></th>\n" +
"                                            <th style=\" width: 120px;\"><strong>Cant. Despachada</strong></th> \n" +
"                                            <th><strong>Cant. Total</strong></th>\n" +
"                                            <th><strong>Precio</strong></th>\n" +
"                                            <th><strong>Precio Total</strong></th>\n" +
"                                        </tr>" +
"                                    </thead>\n" +
"                                    <tbody>\n" +
                                        getProductosModalPedidosClientes(cod)+
"                                    </tbody>\n" +
"                                </table>\n" +
"                            </div>\n" +
"                        </div>\n" +
"                        <div class=\"modal-footer\">\n" +
"                            <button class=\"btn btn-primary\" onclick='GuardarDespacho(this.id);' id='"+codigo+"'><span class=\"glyphicon glyphicon-ok\"></span>  Guardar</button>\n" +
"                            <button class=\"btn btn-danger\" data-dismiss=\"modal\"d><span class=\"glyphicon glyphicon-remove\"></span>  Cancelar</button>\n" +
"                        </div>\n" +
"                    </div>\n" +
"                </div>\n" +
"            </div>";
            
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
    
    public String getProductosModalPedidosClientes(String codigo) throws SQLException{
        Connection con=new cdvUtility().Conexion();
        Statement stmt=null;
        ResultSet rs=null;
        
        DecimalFormat df1=new DecimalFormat("#,##0.00");
        DecimalFormat df2=new DecimalFormat("#,##0");
        
        double suma_total=0;
        String cod ="";
        String html= "";
        try{
            
        
            
        
        System.out.println("Obteniendo pedidos de los clientes");
        String sql1="SELECT \n" +
                    "  t_cdv_encabezado_pedido.cdv_id_pedido, \n" +
                    "  t_cdv_usuarios.cdv_nombre_usuario, \n" +
                    "  t_cdv_estatus.cdv_desc_estatus, \n" +
                    "  t_cdv_encabezado_pedido.cdv_fecha1, \n" +
                    "  t_cdv_encabezado_pedido.cdv_fecha_pago, \n" +
                    "  t_cdv_encabezado_pedido.cdv_nro_pago, \n" +
                    "  t_cdv_detalle_pedido.cdv_id_producto, \n" +
                    "  t_cdv_productos.cdv_desc_producto, \n" +
                    "  t_cdv_detalle_pedido.cdv_cantidad_original, \n" +
                    "  t_cdv_detalle_pedido.cdv_precio, \n" +
                    "  t_cdv_detalle_pedido.cdv_total_original, \n" +
                    "  t_cdv_detalle_pedido.cdv_cantidad_pendiente, \n" +
                    "  t_cdv_detalle_pedido.cdv_total_pendiente, \n" +
                    "  t_cdv_detalle_pedido.cdv_cantidad_despachada\n" +
                    "FROM \n" +
                    "  public.t_cdv_detalle_pedido, \n" +
                    "  public.t_cdv_encabezado_pedido, \n" +
                    "  public.t_cdv_productos, \n" +
                    "  public.t_cdv_usuarios, \n" +
                    "  public.t_cdv_estatus\n" +
                    "WHERE \n" +
                    "  t_cdv_detalle_pedido.cdv_id_pedido = t_cdv_encabezado_pedido.cdv_id_pedido AND\n" +
                    "  t_cdv_detalle_pedido.cdv_id_producto = t_cdv_productos.cdv_id_producto AND\n" +
                    "  t_cdv_encabezado_pedido.cdv_id_usuario = t_cdv_usuarios.cdv_id_usuario AND\n" +
                    "  t_cdv_encabezado_pedido.cdv_estatus_pedido = t_cdv_estatus.cdv_cod_estatus AND\n" +
                    "  t_cdv_encabezado_pedido.cdv_id_pedido ='"+codigo+"';";
        stmt=con.createStatement();
        rs=stmt.executeQuery(sql1);
        System.out.println("SQL: "+sql1);
        int conse = 1;
        while(rs.next()){
            cod = rs.getString("cdv_id_pedido");
            
            String cod_pdto = rs.getString("cdv_id_producto");
            String desc_pdto = rs.getString("cdv_desc_producto");
            int cantidad_org = rs.getInt("cdv_cantidad_original");
            int cantidad_desp = rs.getInt("cdv_cantidad_despachada");
            int cantidad_pen = rs.getInt("cdv_cantidad_pendiente");
            double precio = rs.getDouble("cdv_precio");
            String total_org = rs.getString("cdv_total_original");
            
                    
            
            html+="<tr>" +
                    "   <td>"+cod_pdto+"<input type='hidden' value='"+cod_pdto+"' id='' name='lm_cod_producto"+conse+"'></td> \n" +
                    "   <td>"+desc_pdto+"</td> \n" +
                    "   <td><input type='number' value='"+df2.format(cantidad_pen)+"' name='lm_despachada"+conse+"' style='height: 30px;' class='form-control' min='0' max='"+df2.format(cantidad_pen)+"'></td> \n" +
                    "   <td>"+df2.format(cantidad_desp)+"</td> \n" +
                    "   <td>"+df2.format(cantidad_org)+"</td> \n" +
                    "   <td><strong>Bs.</strong>"+df1.format(precio)+" </td> \n" +
                    "   <td><strong>Bs.</strong>"+total_org+"</td> \n" +
                    "</tr> ";
            conse++;
        }
        html+="<input type='hidden' value='"+(conse-1)+"' id='' name='lm_cant_productos'>";
        }catch (SQLException e) {
            System.out.println("ERROR: "+e);
        }finally {
            if(rs!=null) rs.close();
            if(stmt!=null) stmt.close();
            if(con!=null) con.close();  
        }       
        
        return html;
    }
    
    public void UpdateCantDespachada(String rif, String cod_pedido, String cod_articulo, String cant, String estatus) throws SQLException{
        cdvUtility util=new cdvUtility();
        Connection con=new cdvUtility().Conexion();
        PreparedStatement pstmt=null;
        
        int cantidad = Integer.parseInt(cant);
        String sql="UPDATE t_cdv_detalle_pedido\n" +
                    "   SET cdv_cantidad_pendiente= (cdv_cantidad_pendiente - ?), \n" +
                    "       cdv_cantidad_despachada = (cdv_cantidad_despachada + ?), \n" +
                    "       cdv_estatus=?, \n" +
                    "       cdv_fecha_hora_modif=?,\n" +
                    "       cdv_usuario_modif=? \n" +
                    " WHERE cdv_id_pedido='"+cod_pedido+"' AND cdv_id_producto='"+cod_articulo+"';";
        
        pstmt=con.prepareStatement(sql);
        pstmt.setInt(1, cantidad);
        pstmt.setInt(2, cantidad);
        pstmt.setString(3, estatus);
        pstmt.setTimestamp(4, new java.sql.Timestamp(new java.util.Date().getTime()));
        pstmt.setString(5, rif);
        
        pstmt.executeUpdate();
        //util.Correo(this.rif, this.password, this.correo);
        
        if(pstmt!=null) pstmt.close();
        if(con!=null) con.close();
        
    }
    
    public void DeleteArticulo(String pedido, String articulo) throws SQLException{
        cdvUtility util=new cdvUtility();
        Connection con=new cdvUtility().Conexion();
        PreparedStatement pstmt=null;
        
        int cod_pedido = Integer.parseInt(pedido);
        int cod_articulo = Integer.parseInt(articulo);
        
        
        String sql="DELETE FROM t_cdv_detalle_pedido\n" +
                    " WHERE cdv_id_pedido=? AND cdv_id_producto=?;";
        
        pstmt=con.prepareStatement(sql);
        pstmt.setInt(1, cod_pedido);
        pstmt.setInt(2, cod_articulo);
        
        pstmt.executeUpdate();
        //util.Correo(this.rif, this.password, this.correo);
        
        if(pstmt!=null) pstmt.close();
        if(con!=null) con.close();
        
    }
    
    
}
