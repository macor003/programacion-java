<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.venvidrio.sgt.loader.TSgtPendientesLoader,java.lang.*,java.util.*, java.io.*"%>
<%  TSgtPendientesLoader buscar = new TSgtPendientesLoader();   
   
    String cod_busq = (String) request.getParameter("COD_BUSQUEDA");
   
    String RETORNO  = "";
	
   if(cod_busq.equals("01")){
       RETORNO = "<input type='text' id='txtPorNombre' style='height: 15px;' placeholder='Ingrese nombre'> <input type='button' id='btnBuscarPorNombre' value='Buscar'  onclick='cargaTablaNombreXML();' style='position: relative; top: -23px;left: 215px;'> <a href='Controller?event=PENDIENTES_REQUERIMIENTO'><input type='button' value='Cancelar busqueda' style='position: relative; top: -23px;left: 225px;'></a>";
   }else if(cod_busq.equals("02")){
       RETORNO = buscar.BuscarTiporequerimiento();
   }else if(cod_busq.equals("03")){
       RETORNO = buscar.BuscarClasificacion();
   }
         

%>

<%= RETORNO %>