<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.venvidrio.sgt.loader.TSgtVerificarUsuarioLoader,java.lang.*,java.util.*, java.io.*"%>
<%  TSgtVerificarUsuarioLoader verUsuario = new TSgtVerificarUsuarioLoader();   
   
    String cod_planta = (String) request.getParameter("COD_PLANTA");
    String ficha = (String) request.getParameter("FICHA");
    String RETORNO = verUsuario.verUsuario(cod_planta, ficha);

 
%>
<%= RETORNO %>