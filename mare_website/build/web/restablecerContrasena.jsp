<%-- 
    Document   : index
    Created on : 27/10/2014, 02:41:37 PM
    Author     : Mario
--%>

<%@page import="utility.mareUtility"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="mare_imagenes/logo.ico" type="image/x-icon" rel="shortcut icon" />
        <title>Mare Mare | Inicio sesión</title>
        <link href="mare_estilos/estilos.css" rel="stylesheet" type="text/css"/>
        <link href="mare_estilos/bootstrap.css" rel="stylesheet" type="text/css"/>
        
    </head>
    <body>
        <SCRIPT language="JavaScript">
            function correoContraseña(){
                var correo = document.getElementById("lm_correo").value;
                //alert("Modelo: "+model+" cantidad: "+cant+", Talla busto: "+talla_b+", Talla cadera: "+talla_c);
                if(bootbox.confirm("Se le enviara un correo con su contraseña", function(resultado){
                 if(resultado === false){
                     bootbox.alert("¡Atención! Cancelo el correo de recuperación de contraseña.");
                 }else{
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
        barra = util.mostrarBarraBasica();
	//<--String rol=(String) session.getAttribute("rol");
        //if(rol==null || rol.equals(null) || rol.equals("") || rol.equals("null")){
        //response.sendRedirect(util.getPaginaSessionCaducada());
	//}else{
	//barra = util.mostrarBarra(rol);
	//>
            
        %>
        <%--Barra de navegacion--%>   
        <%= barra%><br><br><br>
        
        <div class="container">
            <div class="jumbotron boxlogin" style="background-color: white;">
                <form name="f_forma" action="Controller?event=RECUPERAR_CLAVE" method="post">
                    <img src="mare_imagenes/mare_mare.png" alt="" style="width: 230px; height: 150px; margin-left: 50px; margin-bottom: -30px;"/>
                    <h3 class="text-center" style="font-weight: 400;">Verificar contraseña</h3>
                    <input type="email" name="lm_correo" id="lm_correo" class="form-control correo" placeholder="Correo Electrónico"><br>
                    <input type="button" class="btn btn-success btn-block" onclick="correoContraseña();" value="Verificar">
                </form>
            </div>
        </div>
         <footer class="navbar navbar-default navbar-fixed-bottom" role="navigation">
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