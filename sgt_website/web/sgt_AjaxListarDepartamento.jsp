<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.venvidrio.sgt.loader.TSgtVerTipoRequerimientoLoader,java.lang.*,java.util.*, java.io.*"%>
<%  TSgtVerTipoRequerimientoLoader requerimiento = new TSgtVerTipoRequerimientoLoader();   
   
    String cod_planta = (String) request.getParameter("COD_PLANTA");
   
    String RETORNO  = "";
	
   
         RETORNO = requerimiento.verDepartamento(cod_planta);

%>

<%= RETORNO %>