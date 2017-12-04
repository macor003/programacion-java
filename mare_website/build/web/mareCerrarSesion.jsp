<%-- 
    Document   : mareCerrarSesion
    Created on : 27/01/2015, 02:43:46 PM
    Author     : Mario
--%>

<%@page session="true" %>
<%
session.removeAttribute("nombre");
session.invalidate();
%>
<jsp:forward page="mareLogout.jsp"/>
