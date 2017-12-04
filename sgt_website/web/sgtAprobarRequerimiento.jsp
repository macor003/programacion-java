<%-- 
    Document   : sgtAprobarRequerimiento
    Created on : 28-jul-2014, 10:45:37
    Author     : Ortegam
--%>

<%@page import="com.venvidrio.sgt.loader.TSgtVerTipoRequerimientoLoader"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1" />
        <title>HelpDesk | Pendientes</title>
        <link href="sgt_imagenes/icono2.ico" type="image/x-icon" rel="shortcut icon" />
        <link rel="stylesheet" type="text/css" href="sgt_estilos/estilohome.css" />
        <link rel="stylesheet" type="text/css" href="sgt_estilos/menu.css" />
        <script>
           function ejecutaCodigo(nombre){
               var id = nombre.substring(10);
               //alert("Codigo del Requerimiento: " + id); 
               document.f_forma.codigo.value = id;     
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
            TSgtAprobarLoader requerimiento=new TSgtAprobarLoader();
            String TablaAprobar= requerimiento.verRequerimientoPorAprobar(ficha);          
            
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
            <h2>Requerimientos Pendientes por Aprobar</h2>
            <form name="f_forma" action="Controller?event=DETALLE_APROBAR_REQUERIMIENTO" method="post" >
                <input type="hidden" id="codigo" name="codigo">
            <table id="tablaAprobar">
                <tr>
                    <td id="tdNumero" class="cabeceraAprobar"><label>N°</label></td>
                    <td id="tdClasificacion" class="cabeceraAprobar"><label>A nombre de</label></td>
                    <td id="tdFecha" class="cabeceraAprobar"><label>Fecha de Creación</label></td>
                    <td id="tdDescripcion" class="cabeceraAprobar"><label>Descripción</label></td>
                    <td id="tdDecision" class="cabeceraAprobar"><label>Decisión</label></td>
                </tr>
                <%= TablaAprobar %>
            </table>            
            </form>
        </section>
            
    </body>
</html>
