/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venvidrio.sms.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Ortegam
 */
public class smsUtility {
    
    private Connection con = null;
    private String x;
    
    public void setX(String x) {
       this.x=x;
   }
    
    public Connection Conexion_IT () {
        

        /*Variable para almacenar la URL de conexión a nuestra Base de Datos, si esta estuviera en otra máquina, necesitariamos estar registrados en ella y contar con su IP*/
               //("Estoy en Conexion_SORG");
        String url = "jdbc:postgresql://10.183.9.190:5432/IT_DB";
  
        try {
       //Acceso al Driver
           //("Voy a accesar al Driver");
        Class.forName("org.postgresql.Driver");

       //La conexión con los parámetros necesarios
           //("la conexion sera "+url+",postgres,"+"123");

        con = DriverManager.getConnection( url,"postgres","123");//en donde tengo vv_sig va el due;o de la bd_

            //("EXITO");
        } catch (Exception e) {
           ////(e);
           //("El error=="+e);
        }

        return con;

}
    
    public Connection Conexion_Empresa () {
        

            /*Variable para almacenar la URL de conexión a nuestra Base de Datos, si esta estuviera en otra máquina, necesitariamos estar registrados en ella y contar con su IP*/
           //String url = "jdbc:postgresql://10.183.9.190:5432/EMPRESA_DB";   Modificar debido a que cambie el servidor a local brbrbr
        String url = "jdbc:postgresql://10.183.9.190:5432/EMPRESA_DB";

               //String url = "jdbc:postgresql://10.183.11.159:5432/SREQ_DB";

        try {
           //Acceso al Driver
        Class.forName("org.postgresql.Driver");

           //La conexión con los parámetros necesarios
        con = DriverManager.getConnection( url,"postgres","123");//en donde tengo vv_sig va el due;o de la bd_

        } catch (Exception e) {
               //("conexion:"+e);
        x=""+e;
        this.setX(""+x);
}

return con;

}
    
    public Connection Conexion_SPP_Sorg () {

            /*Variable para almacenar la URL de conexión a nuestra Base de Datos, si esta estuviera en otra máquina, necesitariamos estar registrados en ella y contar con su IP*/
           //String url = "jdbc:postgresql://10.183.9.190:5432/EMPRESA_DB";   Modificar debido a que cambie el servidor a local brbrbr
                   String url = "jdbc:sybase:Tds:OIDVSYBASE:5000/sorg_db";

               //String url = "jdbc:postgresql://10.183.11.159:5432/SREQ_DB";
            Connection con=null;
           try {
           //Acceso al Driver
           Class.forName("com.sybase.jdbc2.jdbc.SybDriver");

           //La conexión con los parámetros necesarios
             con = DriverManager.getConnection( url,"oi_spp","oi_spp");//en donde tengo vv_sig va el due;o de la bd_
              //("Exito");
               //
           } catch (Exception e) {
               //("conexion:"+e);
               /*x=""+e;
               this.setX(""+x);*/
           }

           return con;

}
    
    public Connection Conexion_jd () {
        

            /*Variable para almacenar la URL de conexión a nuestra Base de Datos, si esta estuviera en otra máquina, necesitariamos estar registrados en ella y contar con su IP*/
           //String url = "jdbc:postgresql://10.183.9.190:5432/EMPRESA_DB";   Modificar debido a que cambie el servidor a local brbrbr
        String url = "jdbc:as400://10.183.9.65/OWENSCRP";

               //String url = "jdbc:postgresql://10.183.11.159:5432/SREQ_DB";

        try {
           //Acceso al Driver
        Class.forName("com.ibm.as400.access.AS400JDBCDriver");

           //La conexión con los parámetros necesarios
        con = DriverManager.getConnection( url,"CONECTAR","CONECTAR");//en donde tengo vv_sig va el due;o de la bd_

        } catch (Exception e) {
               //("conexion:"+e);
        x=""+e;
        this.setX(""+x);
}

return con;

}
       
    public String FechaActual(){
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
               
        //("La fecha de hoy es="+fecha);
        return fecha;
        
    }
    
    public String mostrarBarra(String rol){
         System.out.println("ROL para BARRA: " + rol);
        String htmlcode="";
        if(rol.equals("01")){ //Administrador, SuperUsuario
            htmlcode=    "<nav>\n" +
"            <ul id=\"nav\">\n" +
"      \n" +
"                    <li><a href=\"Controller?event=PAGINA_PRINCIPAL\">Inicio</a></li>\n" +
"                    <li><a class=\"hsubs\" href=\"#\">Crear</a>\n" +
"                        <ul class=\"subs\">\n" +
"                            <li><a href=\"Controller?event=CREAR_REQUERIMIENTO\" target=\"_self\">Nuevo requerimiento</a></li>\n" +
"                            <li><a href=\"Controller?event=CREAR_REQUERIMIENTO_OTRO_USUARIO\" target=\"_self\">Requerimiento para otro usuario</a></li>\n" +
"                        </ul>\n" +
"                    </li>\n" +
"                    <li><a class=\"hsubs\" href=\"#\">Requerimientos</a>\n" +
"                        <ul class=\"subs\">\n" +
"                            <li><a href='Controller?event=ASIGNADOS'>Asignados</a></li>\n" +
"                            <li><a href='Controller?event=CREADOS'>Creados</a></li>\n" +
"                            <li><a href='Controller?event=HISTORICO_REQUERIMIENTOS'>Historial</a></li>\n" + 
"                            <li><a href='Controller?event=PENDIENTES_REQUERIMIENTO'>Pendientes por asignar</a></li>\n" +                    
"                            <li><a href='Controller?event=APROBAR_REQUERIMIENTO'>Pendientes por aprobar</a></li>\n" +
"                        </ul>\n" +
"                    </li>\n" +
"                    <li><a class=\"hsubs\" href=\"#\">Reportes</a>\n" +
"                        <ul class=\"subs\">\n" +
"                            <li><a href='Controller?event=REPORTE_ANALISTAS'>Reporte por Analistas</a></li>\n" +
"                        </ul>\n" +
"                    </li>\n" +
"                    <li><a href='#'>Configuración</a>\n" +
"                        <ul class=\"subs\">\n" +
"                            <li><a href='Controller?event=ACTUALIZAR_AREA'>Areas</a>\n" +
//"                            <li><a href='Controller?event=ACTUALIZAR_AREA_ANALISTA'>Analistas por Areas</a>\n" +
//"                            <li><a href='#'>Tipos de Requerimientos</a>\n" +                    
"                                <ul class=\"lsubs\">\n" +
"                                    <li><a href=\"#\">Prueba</a></li>\n" +
"                                </ul>\n" +
"                            </li>\n" +
"                        </ul>\n" +
"                    </li>\n" +
"                    <div id=\"lavalamp\"></div>\n" +
"            </ul>\n" +
"         </nav>";
                
        }
        else if(rol.equals("02")){ //Rol de usuario coordinador o supervisor
            htmlcode="<nav>\n" +
"            <ul id=\"nav\">\n" +
"      \n" +
"                    <li><a href=\"Controller?event=PAGINA_PRINCIPAL\">Inicio</a></li>\n" +
"                    <li><a class=\"hsubs\" href=\"#\">Crear</a>\n" +
"                        <ul class=\"subs\">\n" +
"                            <li><a href=\"Controller?event=CREAR_REQUERIMIENTO\" target=\"_self\">Nuevo requerimiento</a></li>\n" +
"                            <li><a href=\"Controller?event=CREAR_REQUERIMIENTO_OTRO_USUARIO\" target=\"_self\">Requerimiento para otro usuario</a></li>\n" +
"                        </ul>\n" +
"                    </li>\n" +
"                    <li><a class=\"hsubs\" href=\"#\">Requerimientos</a>\n" +
"                        <ul class=\"subs\">\n" +
//"                            <li><a href='Controller?event=ASIGNADOS'>Asignados</a></li>\n" +
"                            <li><a href='Controller?event=CREADOS'>Creados</a></li>\n" +
//"                            <li><a href='Controller?event=HISTORICO_REQUERIMIENTOS'>Historial</a></li>\n" + 
//"                            <li><a href='Controller?event=PENDIENTES_REQUERIMIENTO'>Pendientes por asignar</a></li>\n" +                    
"                            <li><a href='Controller?event=APROBAR_REQUERIMIENTO'>Pendientes por aprobar</a></li>\n" +
"                        </ul>\n" +
"                    </li>\n" +
//"                    <li><a class=\"hsubs\" href=\"#\">Reportes</a>\n" +
//"                        <ul class=\"subs\">\n" +
//"                            <li><a href='reporte.jsp'>Prueba de reportes</a></li>\n" +
//"                        </ul>\n" +
//"                    </li>\n" +
//"                    <li><a href='#'>Configuración</a>\n" +
//"                        <ul class=\"subs\">\n" +
//"                            <li><a href='Controller?event=ACTUALIZAR_AREA'>Areas</a>\n" +
////"                            <li><a href='Controller?event=ACTUALIZAR_AREA_ANALISTA'>Analistas por Areas</a>\n" +
////"                            <li><a href='#'>Tipos de Requerimientos</a>\n" +                    
//"                                <ul class=\"lsubs\">\n" +
//"                                    <li><a href=\"#\">Prueba</a></li>\n" +
//"                                </ul>\n" +
//"                            </li>\n" +
//"                        </ul>\n" +
//"                    </li>\n" +
"                    <div id=\"lavalamp\"></div>\n" +
"            </ul>\n" +
"         </nav>";

        } else if(rol.equals("03")){ //Rol Usuario general.
            htmlcode="<nav>\n" +
"            <ul id=\"nav\">\n" +
"      \n" +
"                    <li><a href=\"Controller?event=PAGINA_PRINCIPAL\">Inicio</a></li>\n" +
"                    <li><a class=\"hsubs\" href=\"#\">Crear</a>\n" +
"                        <ul class=\"subs\">\n" +
"                            <li><a href=\"Controller?event=CREAR_REQUERIMIENTO\" target=\"_self\">Nuevo requerimiento</a></li>\n" +
//"                            <li><a href=\"Controller?event=CREAR_REQUERIMIENTO_OTRO_USUARIO\" target=\"_self\">Nuevo requerimiento para otro usuario</a></li>\n" +
"                        </ul>\n" +
"                    </li>\n" +
"                    <li><a class=\"hsubs\" href=\"#\">Requerimientos</a>\n" +
"                        <ul class=\"subs\">\n" +
//"                            <li><a href='Controller?event=ASIGNADOS'>Asignados</a></li>\n" +
"                            <li><a href='Controller?event=CREADOS'>Creados</a></li>\n" +
//"                            <li><a href='Controller?event=HISTORICO_REQUERIMIENTOS'>Historial</a></li>\n" + 
//"                            <li><a href='Controller?event=PENDIENTES_REQUERIMIENTO'>Pendientes por asignar</a></li>\n" +                    
//"                            <li><a href='Controller?event=APROBAR_REQUERIMIENTO'>Pendientes por aprobar</a></li>\n" +
"                        </ul>\n" +
"                    </li>\n" +
//"                    <li><a class=\"hsubs\" href=\"#\">Reportes</a>\n" +
//"                        <ul class=\"subs\">\n" +
//"                            <li><a href='reporte.jsp'>Prueba de reportes</a></li>\n" +
//"                        </ul>\n" +
//"                    </li>\n" +
//"                    <li><a href='#'>Configuración</a>\n" +
//"                        <ul class=\"subs\">\n" +
//"                            <li><a href='Controller?event=ACTUALIZAR_AREA'>Areas</a>\n" +
////"                            <li><a href='Controller?event=ACTUALIZAR_AREA_ANALISTA'>Analistas por Areas</a>\n" +
////"                            <li><a href='#'>Tipos de Requerimientos</a>\n" +                    
//"                                <ul class=\"lsubs\">\n" +
//"                                    <li><a href=\"#\">Prueba</a></li>\n" +
//"                                </ul>\n" +
//"                            </li>\n" +
//"                        </ul>\n" +
//"                    </li>\n" +
"                    <div id=\"lavalamp\"></div>\n" +
"            </ul>\n" +
"         </nav>";
                    }
        return htmlcode;

    }
    
    public String mostrarMenuPrincipal(String rol){
         System.out.println("ROL para BARRA: " + rol);
        String htmlcode="";
        if(rol.equals("01")){ //Administrador, SuperUsuario
            htmlcode=    "<a href=\"Controller?event=CREAR_REQUERIMIENTO\" target=\"_self\"><input type=\"submit\" name=\"crear\" id=\"botonC\" value=\"Crear Requerimiento\"></a><br>\n" +
"                <a href=\"Controller?event=CREAR_REQUERIMIENTO_OTRO_USUARIO\" target=\"_self\"><input type=\"submit\" name=\"crear\" id=\"botonO\" value=\"Crear Requerimiento para otro Usuario\"></a><br>\n" +
"                <a href=\"Controller?event=APROBAR_REQUERIMIENTO\" target=\"_self\"><input type=\"submit\" name=\"crear\" id=\"botonA\" value=\"Aprobar Requerimiento\"><br></a>";
                
        }
        else if(rol.equals("02")){ //Rol de usuario coordinador o supervisor
            htmlcode="                <a href=\"Controller?event=CREAR_REQUERIMIENTO\" target=\"_self\"><input type=\"submit\" name=\"crear\" id=\"botonC\" value=\"Crear Requerimiento\"></a><br>\n" +
"                <a href=\"Controller?event=CREAR_REQUERIMIENTO_OTRO_USUARIO\" target=\"_self\"><input type=\"submit\" name=\"crear\" id=\"botonO\" value=\"Crear Requerimiento para otro Usuario\"></a><br>\n" +
"                <a href=\"Controller?event=APROBAR_REQUERIMIENTO\" target=\"_self\"><input type=\"submit\" name=\"crear\" id=\"botonA\" value=\"Aprobar Requerimiento\"><br></a>";

        } else if(rol.equals("03")){ //Rol Usuario general.
            htmlcode="                <a href=\"Controller?event=CREAR_REQUERIMIENTO\" target=\"_self\"><input type=\"submit\" name=\"crear\" id=\"botonC\" value=\"Crear Requerimiento\"></a><br>\n";
//"                <a href=\"Controller?event=CREAR_REQUERIMIENTO_OTRO_USUARIO\" target=\"_self\"><input type=\"submit\" name=\"crear\" id=\"botonO\" value=\"Crear Requerimiento para otro Usuario\"></a><br>\n" +
//"                <a href=\"Controller?event=APROBAR_REQUERIMIENTO\" target=\"_self\"><input type=\"submit\" name=\"crear\" id=\"botonA\" value=\"Aprobar Requerimiento\"><br></a>";
                    }
        return htmlcode;

    }
    
    public String getPaginaSessionCaducada(){
        //String retorno="http://10.183.9.20:8080/sig_website/sigSesionCaducada.jsp";
        String retorno="sgtSesionCaducada.jsp";
        return retorno;
    }

}
