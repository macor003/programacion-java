<%@page import="com.venvidrio.sgt.loader.TSgtPendientesLoader"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.lang.*,java.util.*, java.io.*"%>
<%  TSgtPendientesLoader analista = new TSgtPendientesLoader();   
   
    String cod_planta = (String) request.getParameter("COD_PLANTA");
   
    String RETORNO  = "";
	
   
         RETORNO = analista.verPersonalAsignar(cod_planta);

%>


<%= RETORNO %>