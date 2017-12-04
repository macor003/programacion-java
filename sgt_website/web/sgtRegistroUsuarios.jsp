<%-- 
    Document   : index
    Created on : 05-may-2014, 15:40:32
    Author     : Ortegam
--%>

<%@page import="com.venvidrio.sgt.loader.TSgtVerificarUsuarioLoader"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HelpDesk | Registro</title>
        <link href="sgt_imagenes/icono2.ico" type="image/x-icon" rel="shortcut icon" />
        <link rel="stylesheet" type="text/css" href="sgt_estilos/estilos2.css" />
        <script src="sgt_script/ajax.js" type="text/javascript" ></script>
        
        
        <SCRIPT language="JavaScript">
            function registrar(){
                //var usuario = document.getElementById("lm_usuario").value;
                //var correo = document.getElementById("lm_correo").value;
                //alert("Se enviaran los datos de "+usuario+" a este correo " + correo);
                
                if(!fsj_validaInput2("lm_contrasena","una contraseña")){return false;}
                if(window.confirm("¿Guardar registro?")){  
                    document.f_forma.action ="Controller?event=GUARDAR_REGISTRO_USUARIO";
                    document.f_forma.submit();
                } 
            }
                
        </SCRIPT>
    </head>
    <body>        
        <%@ page language="java" isErrorPage="false" isThreadSafe="true" autoFlush="true" buffer="8kb"   %>
        <%@ page import="java.lang.*,java.util.*"  %>
        <%@ page import="com.venvidrio.sgt.utility.sgtUtility" %>
        <% 
            TSgtVerificarUsuarioLoader verUsuario = new TSgtVerificarUsuarioLoader();
            String cod_planta = request.getParameter("lm_planta");
            String ficha_personal = request.getParameter("lm_ficha");
            
            String datos = verUsuario.verUsuario(cod_planta, ficha_personal);
        %>
        <img src="sgt_imagenes/background.png" alt="" class="fondo">        
        <img src="sgt_imagenes/cintillo.png" alt="" class="cintillo">
        <div class="BarraVino"></div>
        
        <h1 id="titulo" style="margin-left: -8%; margin-top: -3%;">Registrate en  el Sistema de Gestión de Tecnología</h1>        
        <%--img src="sgt_imagenes/venvidrio.png" id="logo" alt="" --%>
        <img id="perfil" style="margin-top: -7%;" src="sgt_imagenes/perfil2.png" alt="">
        
        <section id="centro" style="margin-top: -7%;  height: 30%; width: 40%; margin-left: -2%;" >
            <form name="f_forma" method="post">
                <%= datos %>
                <div id="alerta"><p>Al dar click al botón "Registrar" se enviaran los datos &nbsp; para<br> el inicio de sesión a tu correo, en el caso de no tener correo<br> interno llama al <strong>2073</strong></p></div>
                <input type="submit" id="btn" name="acceder" value="Registrar" onclick="registrar();">&nbsp;
                <input type="reset" id="btn" onclick="history.back();" name="acceder" value="Cancelar">              
            </form>
        </section>
        <h4>&copy; Gerencia Corporativa de Tecnología</h4>
        
    </body>
</html>
