<%-- 
    Document   : sgtPendientes
    Created on : 12-sep-2014, 9:29:23
    Author     : Ortegam
--%>

<%@page import="com.venvidrio.sgt.loader.TSgtPendientesLoader"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
     <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HelpDesk | Pendientes</title>
        <link href="sgt_imagenes/icono2.ico" type="image/x-icon" rel="shortcut icon" />
        <link rel="stylesheet" type="text/css" href="sgt_estilos/estilohome.css" />
        <link rel="stylesheet" type="text/css" href="sgt_estilos/menu.css" />
        <script src="sgt_script/ajax.js" type="text/javascript" ></script>
        
        <link rel="stylesheet" href="sgt_estilos/jquery-ui.css">
        <link rel="stylesheet" href="sgt_estilos/jquery-ui.theme.css">
        <script src="sgt_script/jquery-1.11.1.js"></script>
        <script src="sgt_script/jquery-ui.js"></script>        
        <%--link rel="stylesheet" href="sgt_estilos/demo.css"--%>
        <link rel="stylesheet" href="sgt_estilos/jqpagination.css">        
        <script src="sgt_script/demo.js"></script>
        <script src="sgt_script/jquery.jqpagination.js"></script>
        <script src="sgt_script/scripts.js"></script>
        <script>
           function ejecutaCodigo(nombre){
               var id = nombre.substring(3);
               //alert("Codigo del Requerimiento: " + id); 
               document.f_forma.codigo.value = id;               
            } 
            function next(numero){
                var id = numero.substring(3);
                
                document.f_forma.next.value=id;
                document.f_forma.action = "";
            }
        </script>
        
    </head>
    <body>
       <%@ page import= "java.lang.*, com.venvidrio.sgt.utility.sgtUtility, com.venvidrio.sgt.loader.TSgtAprobarLoader"%>
        <%
        sgtUtility util = new sgtUtility(); 
	String barra="";
	String rol=(String) session.getAttribute("rol");
        if(rol==null || rol.equals(null) || rol.equals("") || rol.equals("null")){
        response.sendRedirect(util.getPaginaSessionCaducada());
	}else{
	barra = util.mostrarBarra(rol);
	}
            String next = request.getParameter("next");
            TSgtPendientesLoader pendiente = new TSgtPendientesLoader();
            String tabla = pendiente.verRequerimientosPendientesSiguiente(next);            
        
        %>
        <header>
            <img src="sgt_imagenes/cintillo.png" alt="" class="cintillo">
            <%= barra %>
        </header>
        
        <div id="UsuarioSesion">
            <img src="sgt_imagenes/user.png" alt="">
            
            <a id="NombreUsuario"><%if(session.getAttribute("nombre_usuario")==null){%>			
			<%}else{ %>
            <%= session.getAttribute("nombre_usuario") %>
			  <%} %></a>                       
            <a href="sgtCerrarSesion.jsp" id="out"> | &nbsp;<span>Salir</span></a>             
        </div>            
            <img src="sgt_imagenes/background.png" alt="" class="fondo">
            <img src="sgt_imagenes/venvidrio.png" alt="" id="logo">
            <label id="tituloPendiente">Requerimientos Pendientes</label>
            <form name="f_forma" method="post">
                <input type="hidden" id="codigo" name="codigo">
                <input type="hidden" id="next" name="next">
                <%= tabla%>
            </form>       
            
    </body>
</html>
