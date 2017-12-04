<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.venvidrio.sgt.loader.TSgtVerTipoRequerimientoLoader,java.lang.*,java.util.*, java.io.*"%>
<%  TSgtVerTipoRequerimientoLoader requerimiento = new TSgtVerTipoRequerimientoLoader();   
   
    String cod = (String) request.getParameter("COD_AREA");
   
    String RETORNO  = "";
	
   
         RETORNO = requerimiento.verAnalistaArea(cod);

%>

<%= RETORNO %>