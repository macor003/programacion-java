/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import utility.mareUtility;

/**
 *
 * @author Mario
 */
public class mareComprar {
    
     public String verCosto(String modelo){
        mareUtility util = new mareUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String retorno = "";
        System.out.println("--------------");
        System.out.println("VOY A CONSULTAR LOS DATOS DEL USUARIO");
        System.out.println("modelo: "+modelo);
            try{
                String sql ="SELECT \n" +
                        "  nombre_producto, \n" +
                        "  modelo_producto, \n" +
                        "  costo_producto\n" +
                        "FROM \n" +
                        "  public.mare_producto\n" +
                        "where \n" +
                        " modelo_producto ='"+modelo+"';";
                
                con = util.Conexion_mare();
                stm= con.createStatement();    
                rs = stm.executeQuery(sql);
                System.out.println("SQL DATOS USUARIOS: " + sql);
                if(rs.next()){                    
                    
                    String costo = rs.getString("costo_producto");
                    retorno+=costo;
                    System.out.println("costo: "+costo);
                }
            
            }catch(SQLException e){
                System.out.println("ERROR: " +e);
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
    
    public void insertarAlCarrito(String correo, String modelo, int cantidad, String tallas, int precio) {
               
        mareUtility util = new mareUtility();
                
        Connection con =null;  
        int status=1;
        
        //int fecha_nac = Integer.parseInt(fecha_anno +"-"+fecha_mes+"-"+fecha_dia);
        
        
        PreparedStatement pstmt=null;
        con=util.Conexion_mare();
        System.out.println("--------------------------------------");
        System.out.println("Estoy guardando un registro de producto");
        System.out.println("correo: "+ correo);
        System.out.println("modelo: "+ modelo);
        System.out.println("cantidad: "+ cantidad);
        System.out.println("tallas: "+ tallas);
        System.out.println("precio: "+ precio);

        String sql="INSERT INTO mare_carrito_compra(\n" +
                    "            car_modelo_producto, car_cantidad, car_precio, car_tallas, car_status, \n" +
                    "            car_correo_usu)\n" +
                    "    VALUES (?, ?, ?, ?, ?, \n" +
                    "            ?);";
        System.out.println("SQL: "+ sql);
        try {
            
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1, modelo);
            pstmt.setInt(2, cantidad);
            pstmt.setInt(3, precio);
            pstmt.setString(4, tallas);
            pstmt.setInt(5, status);
            pstmt.setString(6, correo);
            pstmt.executeUpdate();
                        
        
        } catch (SQLException e) {
           e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
           System.out.println("Error: "+e);
        }finally {
                try {
                       //if (rs != null) rs.close();
                       if (pstmt != null) pstmt.close();
                       if (con != null) con.close();
                   } catch (SQLException e) {
                       System.out.println("Loader:: SQLException " + e.toString());
                   }
               }
               
   }
     
    
}
