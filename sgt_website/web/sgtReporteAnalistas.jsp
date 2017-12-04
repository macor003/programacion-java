<%-- 
    Document   : sgtReporteAnalistas
    Created on : 22/04/2015, 10:29:31 AM
    Author     : Ortegam
--%>


<%@page import="com.venvidrio.sgt.utility.sgtUtility"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HelpDesk | Reportes</title>
        <link href="sgt_imagenes/icono2.ico" type="image/x-icon" rel="shortcut icon" />
        <link rel="stylesheet" type="text/css" href="sgt_estilos/estilohome.css" />
        <link rel="stylesheet" type="text/css" href="sgt_estilos/menu.css" />
        <script>
            function cargaPersonalXML(cod_planta){
                if(window.XMLHttpRequest){
                    xmlhttpdepartamento = new XMLHttpRequest();
                    xmlhttpdepartamento.onreadystatechange = processReqChangeDepartamento;
                    xmlhttpdepartamento.open("GET","sgt_AjaxListarPersonalAsignar.jsp?COD_PLANTA="+cod_planta,true);
                    xmlhttpdepartamento.send(null);
                }
                
                    else if (window.ActiveXObject){// code for IE6, IE5
//	
                    xmlhttpdepartamento = new ActiveXObject("Microsoft.XMLHTTP");
                    xmlhttpdepartamento.onreadystatechange = processReqChangeDepartamento;
                    xmlhttpdepartamento.open("GET","sgt_AjaxListarPersonalAsignar.jsp?COD_PLANTA="+cod_planta,true);
                    xmlhttpdepartamento.send();

                  }
            }            
            function processReqChangeDepartamento(){
                 
                  var analista = document.getElementById("analista");

                  if(xmlhttpdepartamento.readyState === 4){
                          analista.innerHTML = xmlhttpdepartamento.responseText;
                          
                    }                 
            }
        </script>
    </head>
    <body>
       <%@ page import= "java.lang.*, com.venvidrio.sgt.utility.sgtUtility, com.venvidrio.sgt.loader.TSgtVerTipoRequerimientoLoader"%>
        <%
        sgtUtility util = new sgtUtility(); 
	String barra="";
	String rol=(String) session.getAttribute("rol");
        if(rol==null || rol.equals(null) || rol.equals("") || rol.equals("null")){
        response.sendRedirect(util.getPaginaSessionCaducada());
	}else{
	barra = util.mostrarBarra(rol);
	}
         TSgtVerTipoRequerimientoLoader ver = new TSgtVerTipoRequerimientoLoader();   
         String planta = ver.verPlanta();
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
        
        
        <label id="tituloPendiente" style="margin-left: 5%;">Reportes por Analistas</label>
        <section style="position: absolute; top:250px; left: 500px;">   
            <fieldset style="border:0px; width: 500px;">
                
                <label>Desde:</label>&nbsp;<input type="date" name="lm_fecha_desde">&nbsp;&nbsp;&nbsp;
                <label>Hasta:</label>&nbsp;&nbsp;<input type="date" name="lm_fecha_hasta"><br><br>
                
                <table>
                    <tr style="margin: 5px;">
                        <td><label>Planta:</label></td>
                        <td><%= planta%></td>
                    </tr>
                    <tr style="margin: 5px;">
                        <td><label>Analista:</label></td>
                        <td>
                            <div id='analista' name='lm_analista_asignado'>
                                <select id='selectT' disabled='true'>
                                    <option value='su'> Seleccione Uno(a)</option>
                                </select>
                            </div>
                        </td>
                    </tr>
                </table>        
            </fieldset><br>           
            <a href="sgtManejoReporteAnalistas.jsp" target="_blank"><button type="button" class="claseBtn">Generar Reporte</button></a>
        </section>
    </body>
</html>
