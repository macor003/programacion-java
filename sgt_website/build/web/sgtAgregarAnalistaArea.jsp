<%-- 
    Document   : sgtPrincipal
    Created on : 26-may-2014, 13:58:03
    Author     : Ortegam
--%>
<%@page import="com.venvidrio.sgt.loader.TSgtVerTipoRequerimientoLoader"%>
<%@page import="com.venvidrio.sgt.loader.TSgtActualizarLoader"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HelpDesk | Agregar analista a area</title>
        <link href="sgt_imagenes/icono2.ico" type="image/x-icon" rel="shortcut icon" />
        <link rel="stylesheet" type="text/css" href="sgt_estilos/estilohome.css" />
        <link rel="stylesheet" type="text/css" href="sgt_estilos/menu.css" />
        <script>
            function CrearArea(){ 
                if(!fsj_validaSelect("lm_planta","una planta")){return false;}
                if(!fsj_validaSelect("lm_area","un area")){return false;}
                if(!fsj_validaSelect("lm_tipo_analista","el perfil")){return false;}
                if(!fsj_validaSelect("lm_ficha","un personal")){return false;}
             if(window.confirm("¿Está seguro(a) que desea crear el area?")){                        
			document.f_form.submit();
		}
            }
            
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
                 
                  var analista = document.getElementById("lm_analista");

                  if(xmlhttpdepartamento.readyState === 4){
                          analista.innerHTML = xmlhttpdepartamento.responseText;
                          
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
            TSgtVerTipoRequerimientoLoader requerimiento=new TSgtVerTipoRequerimientoLoader();
            String planta = requerimiento.verPlanta();
            String area = requerimiento.verArea();
            String tipo_analista = requerimiento.verTipoAnalista();
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
        <h2 id="tituloArea">Agregar analista a una area</h2>
        <section id="sectionAgregarArea"><br>
            
            <form name="f_form" action="Controller?event=GUARDAR_CREAR_AREA" method="post">
                <table style="margin-left: 90px;">
                    <tr>
                        <td style="padding: 5px;"><label class="EtiquetaEnForm">Planta:</label></td>
                        <td style="padding: 5px;"><%= planta %></td>
                    </tr>
                    <tr>
                        <td style="padding: 5px;"><label class="EtiquetaEnForm">Analista:</label></td>
                        <td style="padding: 5px;">
                            <div id="lm_analista">
                                <select id="selectT">
                                    <option value='su'> Seleccione Uno(a)</option>
                                </select>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td style="padding: 5px;"><label class="EtiquetaEnForm">Area:</label></td>
                        <td style="padding: 5px;"><%= area %></td>
                    </tr>
                    <tr>
                        <td style="padding: 5px;"><label class="EtiquetaEnForm">Tipo analista:</label></td>
                        <td style="padding: 5px;"><%= tipo_analista%></td>
                    </tr>
                </table>               
                <button type="button" onclick="GuardarAnalistaArea();" class="claseBtn" style="margin-left: -120px; margin-top: 10px;">Agregar</button>
                <button type="button" class="claseBtn">Cancelar</button>
            </form>
        </section>
    </body>
</html>
