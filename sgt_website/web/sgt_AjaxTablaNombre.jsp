<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.venvidrio.sgt.loader.TSgtPendientesLoader,java.lang.*,java.util.*, java.io.*"%>
<%  TSgtPendientesLoader buscar = new TSgtPendientesLoader();   
   
    String nombre = (String) request.getParameter("COD_BUSQUEDA");
    String RETORNO  = "";

    if(nombre.equals("")){
        RETORNO= "<label>Debe ingresar algun nombre</label>";
    }else{
        RETORNO = buscar.verRequerimientosPendientesPorNombre(nombre);        
    }     

%>

<%= RETORNO %>