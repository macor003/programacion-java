<%-- 
    Document   : index
    Created on : 27/10/2014, 02:41:37 PM
    Author     : Mario
--%>

<%@page import="loader.mareRegistrarLoader"%>
<%@page import="utility.mareUtility"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="mare_imagenes/logo.ico" type="image/x-icon" rel="shortcut icon" />
        <title>Mare Mare | Configurar cuenta</title>
        <link href="mare_estilos/estilos.css" rel="stylesheet" type="text/css"/>
        <link href="mare_estilos/bootstrap.css" rel="stylesheet" type="text/css"/>
        
    </head>
    <body>
        <SCRIPT language="JavaScript">
            function Guardar(){
            
             if(bootbox.confirm("¿Está seguro(a) que desea actualizar sus datos?", function(resultado){
                 if(resultado === false){
                     bootbox.alert("¡Atención! Sus datos no seran actualizados.");
                 }else{
                     document.f_forma.action ="Controller?event=GUARDAR_ACTUALIZAR_DATOS";
                    document.f_forma.submit();
                 }
                 
             })){                        
			
		}
            }
        </SCRIPT>  
        <%@ page import= "java.lang.*, utility.mareUtility"%>
        <%
        mareUtility util = new mareUtility(); 
	String barra="";
        String nombre = (String) session.getAttribute("nombre");
        String apellido =(String) session.getAttribute("apellido");
	String rol=(String) session.getAttribute("rol");
        if(rol==null || rol.equals(null) || rol.equals("") || rol.equals("null")){
        barra = util.mostrarBarraBasica();
	}else{
            barra = util.mostrarBarra(rol, nombre.toUpperCase(), apellido.toUpperCase());
        }
	mareRegistrarLoader ver = new mareRegistrarLoader();
        String corre = (String) session.getAttribute("correo");
        String datos= ver.verDatosUsuario(corre);
        %>
        <%--Barra de navegacion--%>   
        <%= barra%><br><br><br>
        
        <div class="container">
            <div class="jumbotron" style="background-color: white; width: 550px; margin: 0 auto;">
                <form name="f_forma" method="post">
                    <img src="mare_imagenes/mare_mare.png" alt="" style="width: 230px; height: 150px; margin-left: 100px; margin-bottom: -30px;"/>
                    <h3 class="text-center" style="font-weight: 400;">Cambia tu configuración básica</h3><br>
                    <%= datos %>
                    <a type="button" class="btn btn-success" onclick="Guardar();">Guardar</a>
                </form>
            </div>
        </div>
        <footer class="navbar navbar-default" style="margin-bottom: 0px; border-radius: 0px;" role="navigation">
            <div class="container">
                <div class="navbar-text text-center">
                    <p class="text-center">&COPY;Todos los derechos reservados por Mare Mare</p>
                </div>
            </div>            
        </footer>
        <script src="mare_script/jquery-1.11.1.js" type="text/javascript"></script>
        <script src="mare_script/bootstrap.js" type="text/javascript"></script>        
        <script src="mare_script/bootbox.js" type="text/javascript"></script>
    </body>
</html>