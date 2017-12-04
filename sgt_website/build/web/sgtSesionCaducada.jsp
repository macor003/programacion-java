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
        <title>.::Sesión Caducada::.</title>
        <link rel="stylesheet" type="text/css" href="sgt_estilos/estilos2.css" />
        <%--link rel="stylesheet" type="text/css" href="sgt_estilos/estilohome.css" /--%>
                    
    </head>
    <body >
        <%@ page language="java" isErrorPage="false" isThreadSafe="true" autoFlush="true" buffer="8kb"   %>
        <%@ page import="java.lang.*,java.util.*"  %>
        <%@ page import="com.venvidrio.sgt.utility.sgtUtility" %>
        <img src="sgt_imagenes/background.png" alt="" class="fondo">
        <img src="sgt_imagenes/cintillo.png" alt="" class="cintillo" style="left: 220px; margin-top: -817px;">
        <div class="BarraVino" style="margin-top: -768px;"></div>
        
        <img src="sgt_imagenes/venvidrio.png" id="logo" alt="">
        <section id="mensajeCaducada">
            <img src="sgt_imagenes/clock.png" alt="" class="clock">
            <h1>SU SESIÓN HA EXPIRADO</h1><br><br>
            <h3>HA EXCEDIDO EL TIEMPO INACTIVO<br> DISCULPE LAS MOLESTIAS</h3>            
            <a href="index.jsp" target="_self"><input type="submit" name="IniciarNuevamente" value="Iniciar Sesión Nuevamente" id="IniciarNuevamente"></a>            
        </section>
                       
    </body>
</html>
