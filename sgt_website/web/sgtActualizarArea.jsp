<%-- 
    Document   : sgtPrincipal
    Created on : 26-may-2014, 13:58:03
    Author     : Ortegam
--%>
<%@page import="com.venvidrio.sgt.loader.TSgtActualizarLoader"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HelpDesk | Actualizar Area</title>
        <link href="sgt_imagenes/icono2.ico" type="image/x-icon" rel="shortcut icon" />
        <link rel="stylesheet" type="text/css" href="sgt_estilos/estilohome.css" />
        <link rel="stylesheet" type="text/css" href="sgt_estilos/menu.css" />
        <script src="sgt_script/ajax.js" type="text/javascript" ></script>
        <script src="sgt_script/jquery.js"></script>
        <script src="sgt_script/jquery.dataTables.js"></script>
        <link rel="stylesheet" type="text/css" href="sgt_estilos/jquery.dataTables.css" />  
        <script src="sgt_script/jquery-ui.js"></script>
    
    <script>
        $(document).ready(function() {
            $('#DataTable').dataTable( {
            } );
        } );
        
        function fjs_editarArea(codigo,descripcion){
            document.f_form.action="Controller?event=EDITAR_AREA";
            var cod = codigo.substring(18);
            var desc = descripcion.substring(10);
            document.getElementById("desc_area").value = desc;
            document.getElementById("cod_area").value = cod;
            
            if(window.confirm("Esta a punto de MODIFICAR esta area ¿Quiere Continuar?")){
                document.f_form.submit();                
            }
            
        }
        function fjs_eliminarArea(nombre){
            var cod = nombre.substring(11); 
            //alert("Codigo a eliminar "+cod);
            document.f_form.cod_area.value = cod;
            if(window.confirm("¿Esta seguro(a) que desea elimiar esta area?")){                
                document.f_form.action="Controller?event=ELIMINAR_AREA";
                document.f_form.submit();
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
        
        TSgtActualizarLoader actualizar = new TSgtActualizarLoader();
        String area = actualizar.verArea();
            
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
        
        <h2 id="tituloArea">Actualizar Tabla de Areas</h2>
        
       
        <div style="width: 650px; margin: 0 auto; margin-top: -1000px; font: 12px sans-serif;">
            <form name="f_form" method="post">
                <a href="Controller?event=AGREGAR_AREA"><button type="button" id="AddArea">Agregar Nueva Area</button></a>
                <input type="hidden" id="cod_area" name="cod_area">
                <input type="hidden" id="desc_area" name="desc_area">
                <table id="DataTable" class="display" cellspacing="0" width="100%" style=" border: 1px solid #A60A0A; margin-top: 10px;">
                <thead style="  font: 13px Open Sans Light; font-weight: 300;color: white; background-color: #A60A0A; top: 10px;">
                    <tr>
                        <th>Codigo</th>
                        <th>Descripción</th>
                        <th style="width: 20px;"></th>
                        <th style="width: 20px;"></th>                        
                    </tr>
                </thead>
                <%= area%>
                </table>
            </form>
        </div>
        
    </body>
</html>