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
    <body onload="fjs_init();">
        <SCRIPT language="JavaScript">
            //    <META HTTP-EQUIV="PowerSiteData" NAME="SERVERLANGUAGE" CONTENT="Java">
            
            //***
            // Funcion que se ejecuta al cargar la pagina (onload).  Permite inicializar valores de objetos
            //*****
            function fjs_init(){
                var dml = document.f_forma;
                dml.reset();
                dml.tf_usuario.select();
                dml.tf_usuario.focus();

                fjs_scrollit(100);
              }

            function fjs_aceptar() {
                var dml = document.f_forma;

                // Se asignan los valores de la forma "f_forma" a variables locales
                var ls_usuario    = dml.tf_usuario.value;
                var ls_contrasena = dml.tf_contrasena.value;

                // Si el usuario no introdujo un nombre,se emite un mensaje de error y se retorna falso


                dml.submit();
              }
            $(function() {
    $( document ).tooltip();
  });
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
                <form name="f_forma" onSubmit="return fjs_aceptar();" action="mareLogin" method="post">
                    <img src="mare_imagenes/mare_mare.png" alt="" style="width: 230px; height: 150px; margin-left: 50px; margin-bottom: -30px;"/>
                    <h3 class="text-center" style="font-weight: 400;">Inicio de Sesión</h3>
                    <input type="email" name="tf_usuario" class="form-control correo" placeholder="Correo Electrónico">
                    <input type="password" name="tf_contrasena" class="form-control pass" placeholder="Contraseña">
                    <input type="submit" class="btn btn-success btn-block" onclick="fjs_aceptar();" value="Iniciar sesión">
                </form><br>
                <div class="alert alert-danger alert-dismissable">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>¡Atención!</strong> El correo y la contraseña no coinciden.
                </div>
                <hr>
                <a href="Controller?event=REGISTRAR_USUARIO_CLIENTE" class="btn btn-primary btn-block">Crear Cuenta</a>
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
    </body>
</html>