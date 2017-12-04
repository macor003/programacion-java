<%-- 
    Document   : index
    Created on : 05-may-2014, 15:40:32
    Author     : Ortegam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HelpDesk | Inicio</title>
        <link href="sgt_imagenes/icono2.ico" type="image/x-icon" rel="shortcut icon" />
        <link rel="stylesheet" type="text/css" href="sgt_estilos/estilos2.css" />
        
        
    </head>
    <body onLoad="fjs_init();" style="margin: 0 auto;">
        <%@ page language="java" isErrorPage="false" isThreadSafe="true" autoFlush="true" buffer="8kb"   %>
        <%@ page import="java.lang.*,java.util.*"  %>
        <%@ page import="com.venvidrio.sgt.utility.sgtUtility" %>
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
        <img src="sgt_imagenes/background.png" alt="" class="fondo">
        <img src="sgt_imagenes/cintillo.png" alt="" class="cintillo">
        <div class="BarraVino"></div>
        
        <h1 id="titulo">Sistema de Gestión de Tecnología</h1>
        <h3>Ingrese su Usuario y Contraseña</h3>
        <img src="sgt_imagenes/venvidrio.png" id="logo" alt="" >
        <img id="perfil" src="sgt_imagenes/perfil2.png" alt="">
        
        <section id="centro">
            <form name="f_forma" onSubmit="return fjs_aceptar();" action="sgtLogin" method="post">
                <input type="text" name="tf_usuario" placeholder="Usuario" id="usuario">
                <input type="password" name="tf_contrasena" placeholder="Contraseña" id="contraseña">
                <input type="submit" name="acceder" id="acceder" value="Acceder" onclick="fjs_aceptar();">
                
            </form>
            
        </section>
        <h4 style=" font-weight: 300; position: relative; top: -270px; left: 480px; width: 600px;"><strong>¿No sabes cual es tu usuario?</strong> Puedes consultarlo dando click en el siguiente botón</h4>
        <a href="Controller?event=CONSULTAR_USUARIO"><input type="button" value="Consultar aquí" id="registrate" ></a>
        <a href="sgtPanelDeControl.jsp">Panel de Control </a>
        <h4>&copy; Dirección General de Tecnología</h4>
        
    </body>
</html>
