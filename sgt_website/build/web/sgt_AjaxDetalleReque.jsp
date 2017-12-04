<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.venvidrio.sgt.loader.TSgtVerTipoRequerimientoLoader,java.lang.*,java.util.*, java.io.*"%>
<%  TSgtVerTipoRequerimientoLoader requerimiento = new TSgtVerTipoRequerimientoLoader();   
   
    String cod_reque = (String) request.getParameter("COD_REQUERIMIENTO");
   
    String RETORNO  = "";
	
   
         RETORNO = requerimiento.verDetalleReque(cod_reque);

%>

<%= RETORNO %>