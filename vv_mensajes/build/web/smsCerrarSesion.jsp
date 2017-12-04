<%-- 
    Document   : smsCerrarSesion
    Created on : 30/07/2015, 10:45:00 AM
    Author     : ortegam
--%>
<%@ page session="true" %>
<%
session.removeAttribute("nombre_usuario");
session.invalidate();

%>
<jsp:forward page="smsLogout.jsp"/>
