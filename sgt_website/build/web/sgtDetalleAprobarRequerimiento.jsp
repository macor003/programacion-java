<%-- 
    Document   : sgtDetalleAprobarRequerimiento
    Created on : 20-ago-2014, 13:59:12
    Author     : Ortegam
--%>

<%@page import="com.venvidrio.sgt.utility.sgtUtility"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@page import="com.venvidrio.sgt.loader.TSgtAprobarLoader"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1" />
        <title>HelpDesk | Aprobar</title>
        <link href="sgt_imagenes/icono2.ico" type="image/x-icon" rel="shortcut icon" />
        <link rel="stylesheet" type="text/css" href="sgt_estilos/estilohome.css" />
        <link rel="stylesheet" type="text/css" href="sgt_estilos/menu.css" />
        <script>
            function GuardarRechazo(){
              var cod = "<%= request.getParameter("codigo")%>";
               //alert("Codigo del Requerimiento: "+ cod);               
               document.f_forma.cod.value= cod;
               document.f_forma.action = "Controller?event=GUARDAR_RECHAZO";
               if(window.confirm("¿Está seguro que desea Rechazar el Requerimiento?")){                        
			document.f_forma.submit();
		}
            }
           function GuardarAprobacion(){
              var cod = "<%= request.getParameter("codigo")%>";
               //alert("Codigo del Requerimiento: "+ cod);               
               document.f_forma.cod.value= cod;
               document.f_forma.action = "Controller?event=GUARDAR_APROBACION";
               if(window.confirm("¿Está seguro que desea Aprobar el Requerimiento?")){                        
			document.f_forma.submit();
		}
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
        String ficha = (String)session.getAttribute("ficha");        
        String var = request.getParameter("codigo");
        System.out.println("Codigo del Requerimieno JSP Detalle: "+ var);
        TSgtAprobarLoader detalle =new TSgtAprobarLoader();
        detalle.verDetallesRequerimientoPorAprobar(ficha, var);
        
        String TablaDetalle = detalle.verDetallesRequerimientoPorAprobar(ficha, var);
                   
        %>
        <img src="sgt_imagenes/background.png" alt="" class="fondo">
        <header>
            <img src="sgt_imagenes/cintillo.png" alt="" class="cintillo">
           <%= barra %> 
        </header>
        
        
        <div id="UsuarioSesion" >
            <img src="sgt_imagenes/user.png" alt="">
            
            <a id="NombreUsuario"><%if(session.getAttribute("nombre_usuario")==null){%>			
			<%}else{ %>
            <%= session.getAttribute("nombre_usuario") %>
			  <%} %></a>                       
            <a href="sgtCerrarSesion.jsp" id="out"> | &nbsp;<span>Salir</span></a>              
        </div>
        
        
        <img src="sgt_imagenes/venvidrio.png" alt="" id="logo">
        
        <section id="centroAprobar">
            <h2>Detalles del Requerimiento a Aprobar</h2>
            <form name="f_forma" method="post">
                <input type="hidden" id="cod" name="cod">
                <%= TablaDetalle %> 
                <br>
                <input type="button" value="Aprobar" name="btnAprobar" id="btnAprobar" onclick="GuardarAprobacion();">
                <input type="button" value="Rechazar" name="btnRechazar" id="btnRechazar" onclick="GuardarRechazo();">
                <a href='Controller?event=APROBAR_REQUERIMIENTO'><input type="button" value="Regresar" name="btnRegresar" id="btnRegresar"></a>
            </form>
        </section>
        
    </body>
</html>
