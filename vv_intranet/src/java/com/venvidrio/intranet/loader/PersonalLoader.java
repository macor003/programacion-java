/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venvidrio.intranet.loader;

import com.venvidrio.intranet.utility.intranetUtility;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Ortegam
 */
public class PersonalLoader {
    
    public String verPersonalCumple(){
        
        intranetUtility util = new intranetUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        
        String retorno = "";
        String cod_reque = "";
        retorno +="";
        Calendar miCal= Calendar.getInstance();
        //Calendar calendario = Calendar.getInstance();
        Calendar calendario = new GregorianCalendar();
        int dia= miCal.get(Calendar.DAY_OF_MONTH);
        int mesInt1=miCal.get(Calendar.MONTH);
        int mes=mesInt1+1;
        
        
        
            try{
                String sql ="SELECT \n" +
                            "  it_personal.per_nombre, \n" +
                            "  vv_planta.pla_nom_planta\n" +
                            "FROM \n" +
                            "  public.it_personal, \n" +
                            "  public.vv_planta\n" +
                            "WHERE \n" +
                            "  it_personal.per_cod_planta = vv_planta.pla_cod_planta AND\n" +
                            "  EXTRACT('month' from per_fecnaci)='"+mes+"' AND EXTRACT('day' from per_fecnaci)='"+dia+"';";
                
                con = util.Conexion_IT();
                stm= con.createStatement();    
                rs = stm.executeQuery(sql);
                System.out.println("SQL Personal: " + sql);
                while(rs.next()){                    
                    
                    String nombre = rs.getString("per_nombre");
                    String planta = rs.getString("pla_nom_planta");         
                    retorno +="<p><span class=\"fa fa-birthday-cake\"></span> "+nombre+" - "+planta+"</p>";
                }
                
           
//           if(cod_reque.equals("11")){
//              retorno +="<button class='btnPaginacion' disabled id='btnPrevio'>Anterior</button>"; 
//           }
//           retorno+="&nbsp;&nbsp;&nbsp;&nbsp;<button onclick='next(this.name);' class='btnPaginacion' name='cod"+cod_reque+"' id='btnSiguiente'>Siguiente</button>";
//            
            
            }catch(SQLException e){
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
    
    
    public String verPersonalServicio(){
        
        intranetUtility util = new intranetUtility();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        
        String retorno = "";
        String cod_reque = "";
        retorno +="";
        Calendar miCal= Calendar.getInstance();
        //Calendar calendario = Calendar.getInstance();
        Calendar calendario = new GregorianCalendar();
        int dia= miCal.get(Calendar.DAY_OF_MONTH);
        int mesInt1=miCal.get(Calendar.MONTH);
        int mes=mesInt1+1;
        int año = miCal.get(Calendar.YEAR);
        int count = 0;
        
        
            try{
                String sql ="SELECT '"+año+"'-EXTRACT('year' from per_fecing) as años_de_servicio,\n" +
                            "       per_nombre, \n" +
                            "        vv_planta.pla_nom_planta\n" +
                            "FROM \n" +
                            "	public.it_personal, \n" +
                            "	public.vv_planta\n" +
                            "WHERE 	it_personal.per_cod_planta = vv_planta.pla_cod_planta AND \n" +
                            "	EXTRACT('month' from per_fecing)='"+mes+"' AND EXTRACT('day' from per_fecing)='"+dia+"'\n" +
                            "ORDER BY años_de_servicio desc;";
                
                con = util.Conexion_IT();
                stm= con.createStatement();    
                rs = stm.executeQuery(sql);
                System.out.println("SQL Personal: " + sql);
                
                while(rs.next()){                    
                    count = count +1;
                    String nombre = rs.getString("per_nombre");                    
                    String planta = rs.getString("pla_nom_planta");
                    String años =rs.getString("años_de_servicio");
                    
                    retorno +="<p><span class=\"fa fa-angle-right\"></span> <span class=\"label label-success\">"+años+" año(s)</span> "+nombre+" - "+planta+"</p>";
                }
                if(count == 0){
                    retorno +="<h4 class='text-center' style=' font-size: 50px;' >"
                            + "<span class='fa fa-frown-o'></span>\n" +
                                "<h4 class='text-center'>No hay cumpleañeros de servicio</h4>"
                            + "</h4>";
                }
                System.out.println("Conteo: "+ count);
           
//           if(cod_reque.equals("11")){
//              retorno +="<button class='btnPaginacion' disabled id='btnPrevio'>Anterior</button>"; 
//           }
//           retorno+="&nbsp;&nbsp;&nbsp;&nbsp;<button onclick='next(this.name);' class='btnPaginacion' name='cod"+cod_reque+"' id='btnSiguiente'>Siguiente</button>";
//            
            
            }catch(SQLException e){
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
    
}
