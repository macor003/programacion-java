/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venvidrio.sms.loader;

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
 * @author ortegam
 */
public class TSmsMensajesLoader {
    
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
    
    public String getDirectorio(){
        smsUtility util = new smsUtility();
        Connection con = null;
        Statement stm=null;
        ResultSet rs=null;
        String retorno="<select class='form-control' id='lm_directorio' name='lm_directorio'>";
        retorno+="<option value='su'>Seleccione Uno</option>";

            try {
                String sql = "SELECT dir_cod_directorio, dir_nombre, dir_estatus\n" +
                            "FROM it_msj_directorio\n" +
                            "WHERE dir_estatus='1'\n" +
                            "ORDER BY dir_nombre ASC;";
                con = util.Conexion_IT();
                stm = con.createStatement();
                rs = stm.executeQuery(sql);
                while(rs.next()){
                    String cod_directorio=rs.getString("dir_cod_directorio");
                    String nombre = rs.getString("dir_nombre");
                    retorno+="<option value='"+ cod_directorio +"'>"+nombre+"</option>";

                }
              retorno+="</select>";
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
        return retorno;
    }
    public void EnviarSMS(String directorio, String mensaje){
        
        Calendar fecha = Calendar.getInstance();
         int dia = fecha.get(Calendar.DAY_OF_MONTH);
         int mes = fecha.get(Calendar.MONTH);
         int año = fecha.get(Calendar.YEAR);
         
         String day = Integer.toString(dia);
         String month = Integer.toString(mes+1);
         String year = Integer.toString(año);
         
         String date = ""+day+"-"+month+"-"+year+"";
         
         try{
             String ruta ="file:\\\\\\X:\\";
            //String ruta = "\\\\Dvelgv0248\\owens_data\\MENSAJES_POR_ENVIAR\\"+date+".txt";
            //String ruta= "C:\\Users\\ortegam\\Videos\\"+date+".txt";
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
         }catch(IOException e){
             System.out.println("Error: "+e);
             
         }       
    }
    
    public void GuardarMensaje(String directorio, String mensaje, String usuario)throws SQLException{
        smsUtility util=new smsUtility();
        Connection con=new smsUtility().Conexion_IT();
        PreparedStatement pstmt=null;
        String estatus ="P";
        int codigo = getAutoID();
        
        String sql="INSERT INTO it_msj_mensajes(\n" +
                "            msj_codigo, msj_fecha, msj_directorio, msj_mensaje, msj_usuario_generador, \n" +
                "            msj_estatus)\n" +
                "    VALUES (?, ?, ?, ?, ?, \n" +
                "            ?);";
        
        pstmt=con.prepareStatement(sql);
        pstmt.setInt(1, codigo);
        pstmt.setTimestamp(2, new java.sql.Timestamp(new java.util.Date().getTime()));
        pstmt.setString(3, directorio);
        pstmt.setString(4, mensaje);
        pstmt.setString(5, usuario);
        pstmt.setString(6, estatus);
        
        
        pstmt.executeUpdate();
        
        if(pstmt!=null) pstmt.close();
        if(con!=null) con.close();
    }
    
}
