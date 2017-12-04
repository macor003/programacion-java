/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venvidrio.sms.main;


import com.venvidrio.sms.utility.smsUtility;
import java.util.Calendar;
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
 * @author Ortegam
 */
public class Pruebas {
    
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
     
     public void archivo_txt () throws IOException{
         Calendar fecha = Calendar.getInstance();
         int dia = fecha.get(Calendar.DAY_OF_MONTH);
         int mes = fecha.get(Calendar.MONTH);
         int año = fecha.get(Calendar.YEAR);
         
         String day = Integer.toString(dia);
         String month = Integer.toString(mes+1);
         String year = Integer.toString(año);
         
         String date = ""+day+"-"+month+"-"+year+"";
         
         
        String ruta = "\\\\Dvelgv0248\\owens_data\\MENSAJES_POR_ENVIAR\\"+date+".txt";
        //String ruta= "C:\\Users\\ortegam\\Videos\\"+date+".txt";
        File archivo = new File(ruta);
        BufferedWriter bw;
        if(archivo.exists()) {
            
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write("El fichero de texto ya estaba creado.");
            System.out.println("El archivo existe");
        } else {
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write("yhonny\r\nBuenos dias esto es una prueba de envio de mensajes");
            System.out.println("Archivo Creado");
        }
        bw.close();
     }
     
     public int getAutoID(){
        Connection con;
        Statement stmt=null;
        ResultSet rs = null;
        //("voy a hacer la conexion a la SGT_BD");
        con = new smsUtility().Conexion_IT();
        String ls_sql="";
        String max="";
        int cod=0;
        int id = 0;

        try {
            stmt = con.createStatement();
            ls_sql="SELECT max(msj_codigo) FROM it_msj_mensajes;";
            
            rs=stmt.executeQuery(ls_sql);
            
            if(rs.next()){
                //("Estoy en rs.next()");
               max=rs.getString(1);
                //("max=="+max);
               System.out.println("SQL DE CODIGO: " + ls_sql);
               System.out.println("VALOR DEL MAX: " + max);
            }
//            int cantMax=0;
          
            if(max!=null){
            cod= Integer.parseInt(max)+1;
            }
            System.out.println("CODIGO DEL MAX: " + cod);
            if(cod==0){
                id =1;
                System.out.println("Codigo 1: " + id);
            }else if( cod >= 1){                 
                 id = cod;
                 System.out.println("Codigo >1: " + id);
             }
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }finally {
                   try {
                       if (rs != null) rs.close();
                       if (stmt != null) stmt.close();
                       if (con != null) con.close();
                   } catch (SQLException e) {
                       //("Loader:: SQLException " + e.toString());
                   }
               }
        
        return id;
    }
}
