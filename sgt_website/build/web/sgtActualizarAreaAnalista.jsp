<%-- 
    Document   : sgtActualizarAreaAnalista
    Created on : 22-oct-2014, 14:47:05
    Author     : Ortegam
--%>

<%@page import="com.venvidrio.sgt.loader.TSgtActualizarLoader"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HelpDesk | Actualizar Analista por Area</title>
        <link href="sgt_imagenes/icono2.ico" type="image/x-icon" rel="shortcut icon" />
        <link rel="stylesheet" type="text/css" href="sgt_estilos/estilohome.css" />
        <link rel="stylesheet" type="text/css" href="sgt_estilos/menu.css" />
        
        <script src="sgt_script/jquery.js"></script>
        <script src="sgt_script/jquery.dataTables.js"></script>
        <link rel="stylesheet" type="text/css" href="sgt_estilos/jquery.dataTables.css" />  
        <script src="sgt_script/jquery-ui.js"></script>
    </head>
    <script>
        $(document).ready(function() {
            $('#DataTable').dataTable( {
            } );
        } );
        function fjs_guardarAreaAnalista(){ 
            var ficha_analista = document.getElementById("cod_area").value;
            var nombre_analista = document.getElementById("desc_area").value;
             if(cod_area === "" || cod_area.length === 0){
                 alert("Debe ingresar un codigo para la nueva area");
                 return false;
             }else if( desc_area ==="" || desc_area.length === 0){
                 alert("Debe ingresar una descripción para la nueva area");
                 return false;
             }
             if(window.confirm("¿Está seguro(a) que desea Guardar la información?")){                        
			document.f_forma.submit();
		}
        }
        
    </script>
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
        
        TSgtActualizarLoader actualizar = new TSgtActualizarLoader();
        String area_analista = actualizar.verAreaAnalista();
        
        %>
        <img src="sgt_imagenes/background.png" alt="" class="fondo">
        <header>
            <img src="sgt_imagenes/cintillo.png" alt="" class="cintillo">
            
            <%= barra%>
        </header>
        
        <div id="UsuarioSesion">
            <img src="sgt_imagenes/user.png" alt="">
            
            <a id="NombreUsuario"><%if(session.getAttribute("nombre_usuario")==null){%>			
			<%}else{ %>
            <%= session.getAttribute("nombre_usuario") %>
			  <%} %></a>                       
            <a href="sgtCerrarSesion.jsp" id="out"> | &nbsp;<span>Salir</span></a>             
        </div>
        
        <img src="sgt_imagenes/venvidrio.png" alt="" id="logo">
        
        <h2 id="tituloAreaAnalista">Actualizar Analista por Area</h2>
        
        <div style="width: 950px; margin: 0 auto; margin-top: -1000px; font: 12px sans-serif;">
            <form>
                <a href="Controller?event=AGREGAR_ANALISTA_AREA"><button type="button" id="AddAnalistaArea">Agregar Analista a una Area</button></a>
                <table  id="DataTable" class="display" cellspacing="0" width="100%" style=" border: 1px solid #A60A0A; margin-top: 10px;">
                    <thead style=" font-weight: 300;color: white; background-color: #A60A0A; top: 10px;">
                        <tr>
                            <th style="width: 2px;">Ficha</th>
                            <th>Planta</th>
                            <th>Nombre Analista</th>
                            <th>Area</th>
                            <th>Tipo de Analista</th>
                            <th ></th>
                            <th ></th>
                        </tr>
                    </thead>
                    <%= area_analista%>
                </table>
            </form>
        </div>
        
    </body>
</html>
