/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venvidrio.intranet.main;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.venvidrio.intranet.utility.intranetUtility;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ortegam
 */
public class Pruebas {
    
    private Connection con = null;
    private String x;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, SQLException {
        
        
        
            new Pruebas().InsertarImg();
        
        
    }
    
    public void setX(String x) {
       this.x=x;
   }
    
    public void Fecha(){
        Calendar miCal= Calendar.getInstance();
        //Calendar calendario = Calendar.getInstance();
        Calendar calendario = new GregorianCalendar();
        int diaHoyInt = miCal.get(Calendar.DAY_OF_MONTH);
        int mesInt1=miCal.get(Calendar.MONTH);
        int mesInt=mesInt1+1;
        int añoInt =miCal.get(Calendar.YEAR);

        int hora = calendario.get(Calendar.HOUR_OF_DAY);
        int minuto = calendario.get(Calendar.MINUTE);
        int segundo = calendario.get(Calendar.SECOND);
        
        String fecha= añoInt+"/"+mesInt+"/"+diaHoyInt + " - " + hora+":"+minuto;
        System.out.println("Fecha: "+fecha);
    }    
    
    public void InsertarImg() throws FileNotFoundException, SQLException{
        Connection con=this.Conectar("IT_DB");
        PreparedStatement pstmt = null;
        
        //Tamaño de la imagen 448 X 297
        String ruta="C:\\noticia\\noticia3.jpg";
        File file = new File(ruta);
        FileInputStream in = new FileInputStream(file);
        String fecha_nac="2015-10-27";
        String[] partes= fecha_nac.split("-");
        Calendar miCal=Calendar.getInstance();
        miCal.set(Calendar.DATE, Integer.parseInt(partes[2]));//Seteando el Dia
        miCal.set(Calendar.MONTH, Integer.parseInt(partes[1])-1);//Seteando el Mes -1
        miCal.set(Calendar.YEAR, Integer.parseInt(partes[0]));//Seteando el Anno
        
        java.sql.Date fecha_publi=new java.sql.Date(miCal.getTimeInMillis());
        
        String sql="INSERT INTO it_noticias(\n" +
                    "            not_cod_noticia, not_titulo_noticia, not_fecha_noticia, not_descripcion_noticia, \n" +
                    "            not_imagen, not_nombre_imagen)\n" +
                    "    VALUES (?, ?, ?, ?, \n" +
                    "            ?, ?);";
//        String sql="UPDATE it_noticias\n" +                    
//                    "            SET not_imagen=?\n" +
//                    "    WHERE not_cod_noticia='03' ";
        
        pstmt=con.prepareStatement(sql);
       
        pstmt.setInt(1, 5);
        pstmt.setString(2, "Noche de cohetones por nuestro 5to Aniversario");
        pstmt.setDate(3, fecha_publi);  
        pstmt.setString(4, "Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba ");        
        pstmt.setBinaryStream(5, in, (int) file.length());
        pstmt.setString(6, "NOTI-"+fecha_nac);     
        
        pstmt.executeUpdate();
        
        if(pstmt!=null) pstmt.close();
        if(con!=null) con.close();
        
    }
    
    public void verPersonal(){
        Connection conex = null;
        Connection conex2 = null;
        Statement stm = null;
        ResultSet rs = null;
        PreparedStatement pstmt=null;
        
        
            try{
                String sql ="SELECT  CIA,\n" +
                            "        TRIM(FICHA)AS FICHA,\n" +
                            "        SUBSTRING(FECNAC,0,5)||'-'||SUBSTRING(FECNAC,5,2)||'-'||SUBSTRING(FECNAC,7,2) AS FECHA_CUMPLE,\n" +
                            "        SUBSTRING(FECING,0,5)||'-'||SUBSTRING(FECING,5,2)||'-'||SUBSTRING(FECING,7,2) AS FECHA_INGRESO,\n" +
                            "        NOMBRE, \n" +
                            "        CODDEP,\n" +
                            "        TIPNOM\n" +
                            "FROM NMPP007 WHERE  CIA='03' AND (TIPNOM='1' OR TIPNOM='2') --AND TRIM(FICHA)='4919'";
                
                conex = Conexion_jd();
                stm= conex.createStatement();    
                rs = stm.executeQuery(sql);
                //System.out.println("SQL PENDIENTES: " + sql);
                while(rs.next()){                    
                    String cod = rs.getString("CIA");
                    String ficha = rs.getString("FICHA");
                    String fec_nac = rs.getString("FECHA_CUMPLE");                    
                    String fec_ing = rs.getString("FECHA_INGRESO");
                    String nombre = rs.getString("NOMBRE");
                    String dpto = rs.getString("CODDEP");
                    String tipo_nom = rs.getString("TIPNOM");
                    
                    System.out.println("********************");
                    System.out.println("Empresa: "+cod);
                    System.out.println("Ficha: "+ficha);
                    System.out.println("Fecha_nac: "+fec_nac);
                    System.out.println("Fecha_ingre: "+fec_ing);
                    System.out.println("Nombre: "+nombre);
                    System.out.println("Departamento: "+dpto);
                    System.out.println("TipoNomina: "+tipo_nom);
                    System.out.println("********************");
                    
                    this.InsertarPersonal(cod, ficha, fec_nac, fec_ing, nombre, dpto, tipo_nom);
                    
                    
                }
                
                
           
//           if(cod_reque.equals("11")){
//              retorno +="<button class='btnPaginacion' disabled id='btnPrevio'>Anterior</button>"; 
//           }
//           retorno+="&nbsp;&nbsp;&nbsp;&nbsp;<button onclick='next(this.name);' class='btnPaginacion' name='cod"+cod_reque+"' id='btnSiguiente'>Siguiente</button>";
//            
            
            }catch(SQLException e){
                System.out.println("ERROR CONSULTA: "+e);
                e.printStackTrace();
            }finally{
                try{
                    if (rs != null) rs.close();
                    if (stm != null) stm.close();
                    if (pstmt != null) pstmt.close();
                    if (conex != null) conex.close();
                    if (conex2 != null) conex2.close();
                }catch(SQLException e){
                    
                }
            }   
    }
    
    public void InsertarPersonal(String cod, String ficha, String fec_nac, String fec_ing, String nombre, String dpto, String tipo_nom){
       
        Connection conex2 = null;
        PreparedStatement pstmt=null;
        
        try{
            String[] partes= fec_nac.split("-");
            Calendar miCal=Calendar.getInstance();
            miCal.set(Calendar.DATE, Integer.parseInt(partes[2]));//Seteando el Dia
            miCal.set(Calendar.MONTH, Integer.parseInt(partes[1])-1);//Seteando el Mes -1
            miCal.set(Calendar.YEAR, Integer.parseInt(partes[0]));//Seteando el Anno

            java.sql.Date fecha_nacimiento=new java.sql.Date(miCal.getTimeInMillis());


            String[] parte= fec_ing.split("-");
            Calendar miCal1=Calendar.getInstance();
            miCal1.set(Calendar.DATE, Integer.parseInt(parte[2]));//Seteando el Dia
            miCal1.set(Calendar.MONTH, Integer.parseInt(parte[1])-1);//Seteando el Mes -1
            miCal1.set(Calendar.YEAR, Integer.parseInt(parte[0]));//Seteando el Anno

            java.sql.Date fecha_ingreso=new java.sql.Date(miCal1.getTimeInMillis());


            String sql2 ="INSERT INTO it_personal(\n" +
                        "            per_cod_planta, per_ficha, per_fecnaci, per_fecing, per_nombre, \n" +
                        "            per_cod_dpto, per_cod_estatus)\n" +
                        "    VALUES (?, ?, ?, ?, ?, \n" +
                        "            ?, ?);";


            conex2=this.Conectar("IT_DB");                    
            pstmt=conex2.prepareStatement(sql2);

            pstmt.setString(1, cod);
            pstmt.setString(2, ficha);
            pstmt.setDate(3, fecha_nacimiento);
            pstmt.setDate(4, fecha_ingreso);
            pstmt.setString(5, nombre);
            pstmt.setString(6, dpto);
            pstmt.setString(7, tipo_nom);
            pstmt.executeUpdate();
            System.out.println("***** Guardado ******");
        }catch(SQLException e){
                System.out.println("ERROR CONSULTA: "+e);
                e.printStackTrace();
            }finally{
                try{
                    if (pstmt != null) pstmt.close();
                    if (conex2 != null) conex2.close();
                }catch(SQLException e){
                    
                }
            }
        
    }
    
    public void getDetalleNoticia() throws SQLException{
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
                        "  FROM it_noticias WHERE not_cod_noticia='"+3+"'  ORDER BY fecha desc;";
        
        stmt=con.createStatement();
        rs=stmt.executeQuery(sql);
        while(rs.next()){
            String cod_noti= rs.getString("not_cod_noticia");
            String titulo=rs.getString("not_titulo_noticia");
            String fecha=rs.getString("fecha");
            String detalle = rs.getString("not_descripcion_noticia");
            
            
            
            //byte[] img_bytea=rs.getBytes("not_imagen");
            //String img_code=Base64.encode(img_bytea);
            //System.out.println("CODE = "+img_code);
            //String cdv_nombre_img=rs.getString("not_nombre_imagen");
            
            /*String ruta="C:\\Program Files\\Apache Software Foundation\\Tomcat 7.0\\webapps\\cdv_website\\cdv_imagenes\\"+cdv_nombre_img;
            OutputStream out = new BufferedOutputStream(new FileOutputStream(ruta));
            out.write(img_bytea);
            out.close();*/
            
            System.out.println(detalle);
            System.out.println(detalle.replaceAll("\r\n", "<br>"));
            
        }
        
        if(rs!=null) rs.close();
        if(stmt!=null) stmt.close();
        if(con!=null) con.close();
    }
    
    public Connection Conectar(String bd){
        Connection con = null;
        String url = "jdbc:postgresql://10.183.9.190:5432/"+bd;
        try {
            Class.forName("org.postgresql.Driver");
             con = DriverManager.getConnection(url,"postgres","123");
            //System.out.println("EXITO");
        } catch (ClassNotFoundException  e) {
            System.out.println("El error=="+e);
        } catch(SQLException e){
            System.out.println("El error=="+e);
        }
        return con;
    }
    
    public Connection Conexion_jd () {
        

            
        String url = "jdbc:as400://10.183.9.65/SPIFAVI";

        try {
        Class.forName("com.ibm.as400.access.AS400JDBCDriver");
        con = DriverManager.getConnection( url,"CONECTAR","CONECTAR");//en donde tengo vv_sig va el due;o de la bd_

        } catch (Exception e) {
            System.out.println("Error Conectando a JDE"+e);
               //("conexion:"+e);
        x=""+e;
        this.setX(""+x);
}

return con;

}
}
