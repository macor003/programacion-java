<%@page import="com.venvidrio.sms.servlet.smsLogin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.venvidrio.sms.utility.* ,java.lang.*,java.util.*, java.io.*"%>
<%  smsLogin verUsuario = new smsLogin();   
   
    String cod_planta = (String) request.getParameter("COD_PLANTA");
    String ficha = (String) request.getParameter("FICHA");
    String usuario = verUsuario.VerficarUsuario(cod_planta, ficha);
    String correo = verUsuario.VerificarCorreo(cod_planta, ficha);
%>
<% if(!usuario.equals("") && usuario.substring(0,5).equals("VGSIT")) { System.out.println("CONDICION 1: " +usuario.substring(0,5));%>
<p style="text-align: justify; color: #610B0B;">Usted ya posee un usuario <strong><%= usuario %></strong>, se le enviaran por correo los datos correspondientes para que pueda iniciar sesión 
    ¿Es este tu correo? <strong><%= correo %></strong>
    <input type="button" value="Sí, sí lo es" onclick="EnviarCorreo();">
    <input type="button" value="No, no lo es" onclick="EnviarCorreoCau();">
    <input type="hidden" value="<%= usuario %>" name="lm_usuario" id="lm_usuario">
    <input type="hidden" value="<%= correo %>" name="lm_correo" id="lm_correo">
</p>  
    
<%}else if (!usuario.equals("") && !usuario.substring(0,5).equals("VGSIT")) { System.out.println("CONDICION 2: " +usuario.substring(0,5));%>
<p style="text-align: justify; color: #610B0B;">Usted posee un usuario <strong><%= usuario %></strong> se actualizara al siguiente formato <strong>VGSIT(XX)</strong>, se le enviaran por correo los datos correspondientes para que pueda iniciar sesión 
    ¿Es este tu correo? <strong><%= correo %></strong>
    <input type="button" value="Sí, sí lo es" onclick="EnviarCorreoActualizar();">
    <input type="button" value="No, no lo es" onclick="EnviarCorreoCauActualizar();">
    <input type="hidden" value="<%= usuario %>" name="lm_usuario" id="lm_usuario">
    <input type="hidden" value="<%= correo %>" name="lm_correo" id="lm_correo">
<%}else{%>
  <p style="text-align: justify; color: #610B0B;"><strong>Usted no posee un usuario, puedes crearte un usuario</strong>&nbsp;<input type="button" value="Aquí" onclick="enviarDatos();"></p>
<%}%>
