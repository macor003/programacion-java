<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.venvidrio.cdv.loader.CarritoLoader,java.lang.*,java.util.*, java.io.*"%>
<%  CarritoLoader carrito = new CarritoLoader();

   
   String id_producto = (String) request.getParameter("id_producto");
   double cantidad = Double.parseDouble((String) request.getParameter("valor"));
   String rif = (String) session.getAttribute("rif");
   
    String RETORNO  = "";
	RETORNO=carrito.AgregarProductoCarrito(id_producto, cantidad, rif);
   

%>

<%= RETORNO %>