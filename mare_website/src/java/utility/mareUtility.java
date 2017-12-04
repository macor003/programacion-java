/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Mario
 */
public class mareUtility {
    
    private Connection con = null;
    
    public Connection Conexion_mare () {
        

        /*Variable para almacenar la URL de conexión a nuestra Base de Datos, si esta estuviera en otra máquina, necesitariamos estar registrados en ella y contar con su IP*/
               //("Estoy en Conexion_SORG");
        String url = "jdbc:postgresql://localhost:5432/mare_DB";
  
        try {
       //Acceso al Driver
           //("Voy a accesar al Driver");
        Class.forName("org.postgresql.Driver");

       //La conexión con los parámetros necesarios
           //("la conexion sera "+url+",postgres,"+"123");

        con = DriverManager.getConnection( url,"postgres","123");//en donde tengo vv_sig va el due;o de la bd_
            System.out.println("La conexion a la base de dato fue exitosa");
            //("EXITO");
        } catch (Exception e) {
           ////(e);
           //("El error=="+e);
        }

        return con;

}
    
    public String mostrarBarraBasica(){
        String htmlcode="";
        
//Usuarios General que visitan la pagina sin registrarse
            htmlcode=    "<div class=\"navbar navbar-inverse navbar-fixed-top \" role=\"navigation\">\n" +
"            <div class=\"container\">\n" +
"              <div class=\"navbar-header\">\n" +
"                <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\".navbar-responsive-collapse\">\n" +
"                  <span class=\"icon-bar\"></span>\n" +
"                  <span class=\"icon-bar\"></span>\n" +
"                  <span class=\"icon-bar\"></span>\n" +
"                </button>\n" +
"                  <a class=\"navbar-brand\" href=\"#\">\n" +
"                  <img src=\"mare_imagenes/mare_mare_2.png\" alt=\"\" class=\"navbar-brand\" style=\"width: 110px; height: 85px; margin-top: -30px;\"/>\n" +
"                  </a>\n" +
"              </div>\n" +
"              <div class=\"navbar-collapse collapse navbar-responsive-collapse\">\n" +
"                <ul class=\"nav navbar-nav\">\n" +
"                    <li><a href='index.jsp'>Inicio</a></li>\n" +
"                    <li><a href='Controller?event=NOSOTROS'>Nosotros</a></li>\n" +
"                    <li><a href='Controller?event=CONTACTANOS'>Contactenos</a></li>\n" +
"                    <li class=\"dropdown\">\n" +
"                    <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">Catálogo<b class=\"caret\"></b></a>\n" +
"                        <ul class=\"dropdown-menu\">\n" +
"                            <li class=\"dropdown-header\">Damas</li>\n" +
"                            <li><a href='Controller?event=BIKINIS'>Bikinis</a></li>\n" +
"                            <li><a href='Controller?event=ENTEROS'>Enteros</a></li>\n" +
"                            <li><a href='Controller?event=TANKINIS'>Tankinis</a></li>\n" +
"                        </ul>                    \n" +
"                    </li>\n" +
"                </ul>\n" +
"                <ul class=\"nav navbar-nav navbar-right\">\n" +
"                    <li><a href='Controller?event=INICIO_SESION'>Inicia Sesión</a></li>                    \n" +
"                    <li><a href='Controller?event=REGISTRAR_USUARIO_CLIENTE'>Registrate</a></li>\n" +
"                    \n" +
"                </ul> \n" +
"              </div>                \n" +
"            </div>\n" +
"        </div>";
        return htmlcode;

    }
    
    public String mostrarBarra(String rol, String nombre, String apellido){
         System.out.println("ROL para BARRA: " + rol);
        String htmlcode="";
        if(rol.equals("1")){ //Administrador, SuperUsuario
            htmlcode=    "<div class=\"navbar navbar-inverse navbar-fixed-top \" role=\"navigation\">\n" +
"            <div class=\"container\">\n" +
"              <div class=\"navbar-header\">\n" +
"                <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\".navbar-responsive-collapse\">\n" +
"                  <span class=\"icon-bar\"></span>\n" +
"                  <span class=\"icon-bar\"></span>\n" +
"                  <span class=\"icon-bar\"></span>\n" +
"                </button>\n" +
"                  <a class=\"navbar-brand\" href=\"#\">\n" +
"                  <img src=\"mare_imagenes/mare_mare_2.png\" alt=\"\" class=\"navbar-brand\" style=\"width: 110px; height: 85px; margin-top: -30px;\"/>\n" +
"                  </a>\n" +
"              </div>\n" +
"              <div class=\"navbar-collapse collapse navbar-responsive-collapse\">\n" +
"                <ul class=\"nav navbar-nav\">\n" +
"                    <li><a href='Controller?event=PRINCIPAL'>Inicio</a></li>\n" +
"                    <li><a href='Controller?event=NOSOTROS'>Nosotros</a></li>\n" +
"                    <li class=\"dropdown\">\n" +
"                    <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">Catálogo<b class=\"caret\"></b></a>\n" +
"                        <ul class=\"dropdown-menu\">\n"  +
"                            <li class=\"dropdown-header\">Damas</li>\n" +
"                            <li><a href='bikinis_pedir.jsp'>Bikinis</a></li>\n" +
"                            <li><a href='enteros_pedir.jsp'>Enteros</a></li>\n" +
"                            <li><a href='tankinis_pedir.jsp'>Tankinis</a></li>\n" +
"                        </ul>                    \n" +
"                    </li>\n" +
"                </ul>\n" +
"                <ul class=\"nav navbar-nav navbar-right\">\n" +
"                    <li class='navbar-item hidden-collapsed icon menuitem-purchases'><a></a><li>"+                    
"                    <li class=\"dropdown\">\n" +
"                    <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">"+nombre+apellido+"<b class=\"caret\"></b></a>\n" +
"                        <ul class=\"dropdown-menu\">\n"+
"                            <li><a href=\"Controller?event=PANEL_DE_CONTROL\">Panel de control</a></li>\n" +
"                            <li><a href='mareCarrito.jsp'>Carrito</a></li>\n" +
"                            <li><a href='Controller?event=CONFIGURAR_CUENTA'>Configurar cuenta</a></li>\n" +
"                            <li><a href=\"mareCerrarSesion.jsp\">Cerrar sesión</a></li>\n" +
"                        </ul>                    \n" +
"                    </li>\n" +
"                </ul> \n" +
"              </div>                \n" +
"            </div>\n" +
"        </div>";
                
        }
        else if(rol.equals("2")){ //Rol de Coordinador ISO
            htmlcode= "<div class=\"navbar navbar-inverse navbar-fixed-top \" role=\"navigation\">\n" +
"            <div class=\"container\">\n" +
"              <div class=\"navbar-header\">\n" +
"                <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\".navbar-responsive-collapse\">\n" +
"                  <span class=\"icon-bar\"></span>\n" +
"                  <span class=\"icon-bar\"></span>\n" +
"                  <span class=\"icon-bar\"></span>\n" +
"                </button>\n" +
"                  <a class=\"navbar-brand\" href=\"#\">\n" +
"                  <img src=\"mare_imagenes/mare_mare_2.png\" alt=\"\" class=\"navbar-brand\" style=\"width: 110px; height: 85px; margin-top: -30px;\"/>\n" +
"                  </a>\n" +
"              </div>\n" +
"              <div class=\"navbar-collapse collapse navbar-responsive-collapse\">\n" +
"                <ul class=\"nav navbar-nav\">\n" +
"                    <li><a href='Controller?event=PRINCIPAL'>Inicio</a></li>\n" +
"                    <li><a href='Controller?event=NOSOTROS'>Nosotros</a></li>\n" +
"                    <li class=\"dropdown\">\n" +
"                    <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">Catálogo<b class=\"caret\"></b></a>\n" +
"                        <ul class=\"dropdown-menu\">\n"  +
"                            <li class=\"dropdown-header\">Damas</li>\n" +
"                            <li><a href='bikinis_pedir.jsp'>Bikinis</a></li>\n" +
"                            <li><a href='enteros_pedir.jsp'>Enteros</a></li>\n" +
"                            <li><a href='tankinis_pedir.jsp'>Tankinis</a></li>\n" +
"                        </ul>                    \n" +
"                    </li>\n" +
"                </ul>\n" +
"                <ul class=\"nav navbar-nav navbar-right\">\n" +
"                    <li class=\"dropdown\">\n" +
"                    <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">"+nombre+apellido+"<b class=\"caret\"></b></a>\n" +
"                        <ul class=\"dropdown-menu\">\n"+
"                            <li><a href=\"Controller?event=PANEL_DE_CONTROL\">Panel de control</a></li>\n" +
"                            <li><a href='mareCarrito.jsp'>Carrito</a></li>\n" +
"                            <li><a href=\"#\">Configurar cuenta</a></li>\n" +
"                            <li><a href=\"mareCerrarSesion.jsp\">Cerrar sesión</a></li>\n" +
"                        </ul>                    \n" +
"                    </li>\n" +
"                </ul> \n" +
"              </div>                \n" +
"            </div>\n" +
"        </div>";

        } else if(rol.equals("3")){ //Usuario Consulta
            htmlcode= "<div class=\"navbar navbar-inverse navbar-fixed-top \" role=\"navigation\">\n" +
"            <div class=\"container\">\n" +
"              <div class=\"navbar-header\">\n" +
"                <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\".navbar-responsive-collapse\">\n" +
"                  <span class=\"icon-bar\"></span>\n" +
"                  <span class=\"icon-bar\"></span>\n" +
"                  <span class=\"icon-bar\"></span>\n" +
"                </button>\n" +
"                  <a class=\"navbar-brand\" href=\"#\">\n" +
"                  <img src=\"mare_imagenes/mare_mare_2.png\" alt=\"\" class=\"navbar-brand\" style=\"width: 110px; height: 85px; margin-top: -30px;\"/>\n" +
"                  </a>\n" +
"              </div>\n" +
"              <div class=\"navbar-collapse collapse navbar-responsive-collapse\">\n" +
"                <ul class=\"nav navbar-nav\">\n" +
"                    <li><a href='Controller?event=PRINCIPAL'>Inicio</a></li>\n" +
"                    <li><a href='Controller?event=NOSOTROS'>Nosotros</a></li>\n" +
"                    <li class=\"dropdown\">\n" +
"                    <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">Catálogo<b class=\"caret\"></b></a>\n" +
"                        <ul class=\"dropdown-menu\">\n"  +
"                            <li class=\"dropdown-header\">Damas</li>\n" +
"                            <li><a href='bikinis_pedir.jsp'>Bikinis</a></li>\n" +
"                            <li><a href='enteros_pedir.jsp'>Enteros</a></li>\n" +
"                            <li><a href='tankinis_pedir.jsp'>Tankinis</a></li>\n" +
"                        </ul>                    \n" +
"                    </li>\n" +
"                </ul>\n" +
"                <ul class=\"nav navbar-nav navbar-right\">\n" +
"                    <li class='navbar-item hidden-collapsed icon menuitem-purchases'>"+
"                    <li class=\"dropdown\">\n" +
"                    <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">"+nombre+apellido+"<b class=\"caret\"></b></a>\n" +
"                        <ul class=\"dropdown-menu\">\n"+
//"                            <li><a href=\"Controller?event=PANEL_DE_CONTROL\">Panel de control</a></li>\n" +
"                            <li><a href='mareCarrito.jsp'>Carrito</a></li>\n" +
"                            <li><a href='Controller?event=CONFIGURAR_CUENTA'>Configurar cuenta</a></li>\n" +
"                            <li><a href=\"mareCerrarSesion.jsp\">Cerrar sesión</a></li>\n" +
"                        </ul>                    \n" +
"                    </li>\n" +
"                </ul> \n" +
"              </div>                \n" +
"            </div>\n" +
"        </div>";
                    }
        return htmlcode;

    }
    
}
