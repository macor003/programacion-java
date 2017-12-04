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
        <title>HelpDesk | Editar area</title>
        <link href="sgt_imagenes/icono2.ico" type="image/x-icon" rel="shortcut icon" />
        <link rel="stylesheet" type="text/css" href="sgt_estilos/estilohome.css" />
        <link rel="stylesheet" type="text/css" href="sgt_estilos/menu.css" />
        <script>
            function EditarArea(){ 
            var cod_area = document.getElementById("cod_area").value;
            var desc_area = document.getElementById("desc_area").value;
             if(cod_area === "" || cod_area.length === 0){
                 alert("Debe ingresar un codigo para la nueva area");
                 return false;
             }else if( desc_area ==="" || desc_area.length === 0){
                 alert("Debe ingresar una descripción para la nueva area");
                 return false;
             }
             if(window.confirm("¿Está seguro(a) que desea editar el area?")){                        
			document.f_form.submit();
		}
            }            
            function Regresar(){
                history.back();
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
        %>
        <img src="sgt_imagenes/background.png" alt="" class="fondo">
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
        
        <img src="sgt_imagenes/venvidrio.png" alt="" id="logo">
        <h2 id="tituloArea" style="margin-left: 90px;">Editar area</h2>
        <section id="sectionAgregarArea">
            <form name="f_form" action="Controller?event=GUARDAR_EDITAR_AREA" method="post">
                <label class="EtiquetaEnForm" style="margin-left:90px;">Codigo del area</label>
                <input type="text" name="cod_area" id="cod_area" value="<%= request.getParameter("cod_area") %>" style="margin-top: 30px; margin-left: 40px;"  readonly><br>
                <label class="EtiquetaEnForm" style="margin-left: 90px;">Descripción del area</label>
                <input type="text" name="desc_area" id="desc_area" value="<%= request.getParameter("desc_area") %>" style="margin-left: 10px; margin-top: 10px;"><br>
                <button type="button" onclick="EditarArea();" class="claseBtn" style="margin-left: -120px; margin-top: 10px;">Agregar</button>
                <button type="reset" onclick="Regresar();" class="claseBtn">Cancelar</button>
            </form>
        </section>
    </body>
</html>
