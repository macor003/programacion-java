/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venvidrio.sms.main;

import com.venvidrio.sms.utility.smsUtility;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ortegam
 */
public class ProcesoEnvioSMS {
    
    public static void main(String[] args) throws FileNotFoundException, SQLException, IOException {
        //new Pruebas().getImg();
        new Pruebas().ProcesoEnvio();
        
    }
     public void ProcesoEnvio() throws IOException{
         
         smsUtility util = new smsUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        
        
            try{
                String sql ="SELECT msj_codigo, msj_fecha, msj_directorio, msj_mensaje, msj_usuario_generador, \n" +
                            "       msj_estatus\n" +
                            "FROM it_msj_mensajes\n" +
                            "WHERE msj_estatus='P';";
                
                con = util.Conexion_IT();
                stm= con.createStatement();    
                rs = stm.executeQuery(sql);
                System.out.println("SQL PENDIENTES: " + sql);
                while(rs.next()){
                    String codigo = rs.getString("msj_codigo");
                    String fecha = rs.getString("msj_fecha");
                    String directorio = rs.getString("msj_directorio");
                    String mensaje = rs.getString("msj_mensaje");
                    
                    String nombre = codigo+"-"+fecha.substring(0,10);
                    System.out.println("archivo: "+nombre);
                    
                    //String ruta ="\\\\Oidvnt04\\apps_compras\\Aimeed\\"+nombre+".txt";
                    String ruta = "\\\\Dvelgv0248\\owens_data\\MENSAJES_POR_ENVIAR\\"+nombre+".txt";
                    //String ruta= "C:\\Users\\ortegam\\Videos\\"+nombre+".txt";
                    File archivo = new File(ruta);
                    BufferedWriter bw;
                    if(archivo.exists()) {
                        bw = new BufferedWriter(new FileWriter(archivo));
                        bw.write("El fichero de texto ya estaba creado.");
                        System.out.println("El archivo existe");
                    } else {
                        bw = new BufferedWriter(new FileWriter(archivo));
                        bw.write(directorio+"\r\n"+mensaje);
                        System.out.println("Archivo Creado");
                    }
                    bw.close();
                    
                    String ActualizarEstatus="E";
                    PreparedStatement pstmt=null;
                    String sql1="UPDATE it_msj_mensajes\n" +
                            "   SET msj_estatus=?\n" +
                            " WHERE msj_codigo='"+codigo+"';";
                    pstmt=con.prepareStatement(sql1);
                    pstmt.setString(1, ActualizarEstatus);        
                    pstmt.executeUpdate();
                    
                }
            
            }catch(SQLException e){
                System.out.println("ERROR GUARDANDO ARCHIVO: "+e);
                e.printStackTrace();
            }finally{
                try{
                    if (rs != null) rs.close();
                    if (stm != null) stm.close();
                    if (con != null) con.close();
                }catch(SQLException e){
                    
                }
            }
         
     }
    
    
}
