<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.venvidrio.sgt.loader.TSgtPendientesLoader,java.lang.*,java.util.*, java.io.*"%>
<%  TSgtPendientesLoader buscar = new TSgtPendientesLoader();   
   
    String cod_busq = (String) request.getParameter("COD_BUSQUEDA");
    String RETORNO  = "";

       RETORNO = buscar.verRequerimientosPendientesPorTipoRequerimiento(cod_busq);       

%>

<%= RETORNO %>