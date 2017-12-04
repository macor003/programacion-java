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
import java.util.Calendar;
import utility.mareUtility;

/**
 *
 * @author Mario
 */
public class marePedidoLoader {
    
    public int getAutoID(){
        Connection con;
        Statement stmt=null;
        ResultSet rs = null;
        //("voy a hacer la conexion a la SGT_BD");
        con = new mareUtility().Conexion_mare();
        String ls_sql="";
        String max="";
        int cod=0;
        int id = 0;

        try {
            stmt = con.createStatement();
            ls_sql="SELECT  max(cod_pedido) FROM  mare_pedidos;";
            
            rs=stmt.executeQuery(ls_sql);
            
            if(rs.next()){
                //("Estoy en rs.next()");
               max=rs.getString(1);
                //("max=="+max);
               System.out.println("SQL DE CODIGO REQUERIMIENTO: " + ls_sql);
               System.out.println("VALOR DEL MAX: " + max);
            }
//            int cantMax=0;
          
            if(max!=null){
            cod= Integer.parseInt(max)+1;
            }
            System.out.println("CODIGO DEL MAX: " + cod);
            if(cod==0){
                id =1;
                System.out.println("Codigo de requerimiento 1: " + id);
            }else if( cod >= 1){                 
                 id = cod;
                 System.out.println("Codigo de requerimiento >1: " + id);
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
