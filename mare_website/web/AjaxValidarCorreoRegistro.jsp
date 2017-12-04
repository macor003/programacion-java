<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="loader.mareRegistrarLoader,java.lang.*,java.util.*, java.io.*"%>
<%  mareRegistrarLoader verificar = new mareRegistrarLoader();   
   
    String correo = (String) request.getParameter("CORREO");
   
    String RETORNO  = "";
	
   
         RETORNO = verificar.validarCorreo(correo);
         System.out.println("CORREO EN PANTALLA: "+correo.toUpperCase());
         System.out.println("CORREO EN BASE DE DATOS: "+RETORNO);

%>

<% if(correo.toUpperCase().equals(RETORNO)){  %>
    <div class="alert alert-danger alert-dismissable">
  <button type="button" class="close" data-dismiss="alert">&times;</button>
  <strong>¡Atención!</strong> Ese correo ya esta registrado.
</div>
<%}else{%>
    <div class="alert alert-info alert-dismissable">
  <button type="button" class="close" data-dismiss="alert">&times;</button>
  <strong>¡Información!</strong> Correo disponible.
</div>    
<%}%>