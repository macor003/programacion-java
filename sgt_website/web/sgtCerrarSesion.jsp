<%-- 
    Document   : sgt_CerrarSesion
    Created on : 29-may-2014, 14:01:28
    Author     : Ortegam
--%>

<%@ page session="true" %>
<%
session.removeAttribute("nombre_usuario");
session.invalidate();

%>
<jsp:forward page="sgtLogout.jsp"/>
