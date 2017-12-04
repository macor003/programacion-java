<%-- 
    Document   : index
    Created on : 05-may-2014, 15:40:32
    Author     : Ortegam
--%>

<%@page import="com.venvidrio.sgt.loader.TSgtActualizarLoader"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>HelpDesk | Crear Requerimiento</title>
        <link href="sgt_imagenes/icono2.ico" type="image/x-icon" rel="shortcut icon" />
        <link rel="stylesheet" type="text/css" href="sgt_estilos/estilohome.css" />
        <link rel="stylesheet" type="text/css" href="sgt_estilos/menu.css" />
        <script src="sgt_script/ajax.js" type="text/javascript" ></script>
        
        
        <link rel="stylesheet" href="sgt_estilos/jquery-ui.css">
        <link rel="stylesheet" href="sgt_estilos/jquery-ui.theme.css">
        <script src="sgt_script/jquery-1.11.1.js"></script>
        <script src="sgt_script/jquery-ui.js"></script>
        
        <%-- SCRIPT --%>
        <script> 
            
            function agregaFila( id ) { 
                var tabla = document.getElementById( id ); 
                var tbody = document.getElementById( tabla.id ).tBodies[0]; 
                var row = tbody.rows[0] .cloneNode( true ); 
                var id = 1; 
                while( document.getElementById( tabla.id+'_fila_'+id ) ) { 
                    id++; 
                } 
                if (id<=4){ 
                row.id = tabla.id+'_fila_'+id; 
                row.style.display = ''; 
                tbody.appendChild( row ); 
                } 
            } 

            function borraFila( fila ) { 
            var id = fila.id; 
            if( fila.parentNode.rows.length <= 1 ) return; 
            document.getElementById( id ).parentNode.removeChild( document.getElementById(id) ); 
            }
            
            function cargaAreaXML(cod_tipo){
                
                if(window.XMLHttpRequest){
                    xmlhttparea = new XMLHttpRequest();
                    xmlhttparea.onreadystatechange = processReqChangeArea;
                    xmlhttparea.open("GET","sgt_AjaxListarArea.jsp?COD_REQUERIMIENTO="+cod_tipo,true);
                    xmlhttparea.send(null);
                }
                
                    else if (window.ActiveXObject){// code for IE6, IE5
//	
                    xmlhttparea = new ActiveXObject("Microsoft.XMLHTTP");
                    xmlhttparea.onreadystatechange = processReqChangeArea;
                    xmlhttparea.open("GET","sgt_AjaxListarArea.jsp?COD_REQUERIMIENTO="+cod_tipo,true);
                    xmlhttparea.send();

                  }
                  
                  }

            function processReqChangeArea(){
                  var area = document.getElementById("area");

                  if(xmlhttparea.readyState === 4){
                          area.innerHTML = xmlhttparea.responseText;
                          
                  }
                  
                  }
            
            function cargaClasificacionXML(cod){
                
                if(window.XMLHttpRequest){
                    xmlhttpclasificacion = new XMLHttpRequest();
                    xmlhttpclasificacion.onreadystatechange = processReqChangeClasificacion;
                    xmlhttpclasificacion.open("GET","sgt_AjaxListarClasificacion.jsp?COD_AREA="+cod,true);
                    xmlhttpclasificacion.send(null);
                }
                
                    else if (window.ActiveXObject){// code for IE6, IE5
//	
                    xmlhttpclasificacion = new ActiveXObject("Microsoft.XMLHTTP");
                    xmlhttpclasificacion.onreadystatechange = processReqChangeClasificacion;
                    xmlhttpclasificacion.open("GET","sgt_AjaxListarClasificacion.jsp?COD_AREA="+cod,true);
                    xmlhttpclasificacion.send();

                  }
                  }

            function processReqChangeClasificacion(){
                  var clasificacion = document.getElementById("clasificacion");

                  if(xmlhttpclasificacion.readyState === 4){
                          clasificacion.innerHTML = xmlhttpclasificacion.responseText;
                  }
                  
                  }
            
            function fjs_guardarDatos(){                        
               //if(!fsj_validaSelect("lm_planta","una planta")){return false;}
               //if(!fsj_validaSelect("lm_dpto","un departamento")){return false;}
               //if(!fsj_validaSelect("lm_ficha","una ficha")){return false;}
                
                if(!fsj_validaSelect("lm_urgencia","una prioridad")){return false;}
                if(!fsj_validaSelect("lm_tipo_requerimiento","un requerimiento")){return false;}
                //if(!fsj_validaSelect("lm_area_requerimiento","un area")){return false;}
                //if(!fsj_validaSelect("lm_clasificacion","una clasificación")){return false;}
                if(!fsj_validaSelect("lm_descripcion","una descripcion")){return false;}
                var cod = "<%= request.getParameter("cod") %>";
                
                document.f_forma.cod.value= cod;
                if(window.confirm("¿Está seguro que desea Guardar la información?")){                        
			document.f_forma.submit();
                }                
            }            
            $(function() {
                
                $( "#dialogo" ).dialog({
                     draggable:false,
                     modal: true,
                     autoOpen: false,
                     width: 450,
                     height:400,
                     maxWidth: 450,
                     minWidth:450,                     
                     maxHeight:400,
                     minHeight:400,
                     buttons: {
                        "Cerrar": function () {
                        $(this).dialog("close");
                        }
                    },
                     show: {
                     effect: "drop",
                     duration: 500
                     
                    },
                     hide: {
                    effect: "drop",
                    duration: 500
                    }
                }); 
                $( "#pregunta" ).click(function() {
                $( "#dialogo" ).dialog( "open" );
                });
                $( "#help" ).click(function() {
                $( "#dialogo" ).dialog( "open" );
                });
            });$(function() {
                $( document ).tooltip({
                  position: {
                    my: "center bottom-20",
                    at: "center top",
                    using: function( position, feedback ) {
                      $( this ).css( position );
                      $( "<div>" )
                        .addClass( "arrow" )
                        .addClass( feedback.vertical )
                        .addClass( feedback.horizontal )
                        .appendTo( this );
                    }
                  }
                });
              });
        </script>
        <style>
            @font-face {
                font-family: "Open Sans Light";
                font-style: normal; 
                font-weight: normal;
                src: url("OpenSans-Light-webfont.woff")
            }
          .ui-tooltip, .arrow:after {
            background: black;
            border: 1px solid black;
          }
          .ui-tooltip {
            padding: 5px 5px;
            color: white;
            border-radius: 0px;
            font: 12px "Open Sans Light";            
            box-shadow: 0 0 7px black;
          }
          .arrow {
            width: 70px;
            height: 16px;
            overflow: hidden;
            position: absolute;
            left: 50%;
            margin-left: -35px;
            bottom: -16px;
          }
          .arrow.top {
            top: -16px;
            bottom: auto;
          }
          .arrow.left {
            left: 20%;
          }
          .arrow:after {
            content: "";
            position: absolute;
            left: 20px;
            top: -20px;
            width: 25px;
            height: 25px;
            box-shadow: 6px 5px 9px -9px black;
            -webkit-transform: rotate(45deg);
            -ms-transform: rotate(45deg);
            transform: rotate(45deg);
          }
          .arrow.top:after {
            bottom: -20px;
            top: auto;
          }
          </style>
    </head>
    <body>
        <%@ page language="java" isErrorPage="false" isThreadSafe="true" autoFlush="true" buffer="8kb"   %>
        <%@ page import= "java.lang.*, com.venvidrio.sgt.utility.sgtUtility, com.venvidrio.sgt.loader.TSgtVerTipoRequerimientoLoader"%>
         <%@ page import= "java.lang.*, com.venvidrio.sgt.utility.sgtUtility, com.venvidrio.sgt.loader.TSgtAprobarLoader"%>
        <%
        
            //TSgtVerTipoRequerimientoLoader requerimiento=new TSgtVerTipoRequerimientoLoader();
            
            //String tipo= requerimiento.verTiporequerimiento();
            //String prioridad = requerimiento.verUrgencia();
            
            //String tlm_planta = (String) session.getAttribute("planta");
        sgtUtility util = new sgtUtility(); 
	String barra="";
	String rol=(String) session.getAttribute("rol");
        if(rol==null || rol.equals(null) || rol.equals("") || rol.equals("null")){
        response.sendRedirect(util.getPaginaSessionCaducada());
	}else{
	barra = util.mostrarBarra(rol);
	}
            TSgtActualizarLoader ver = new TSgtActualizarLoader();
            String cod = (String) request.getParameter("cod");
            
            System.out.println("CODIGO REQ PARA EDITAR: "+cod);
            String editar = ver.verDetallesRequerimientoEditar(cod);
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
        <section id="centro">
            <h2 id="tituloEditarReque">Editar Requerimiento <strong>N°<span style="color: red;"><%= cod %></span></strong> </h2>
        </section>

        <section id="formularioEditar">
            <form name="f_forma" action="Controller?event=GUARDAR_EDICION_REQUERIMIENTO" method="post" accept-charset="ISO-8859-1">
                <input type="hidden" name="lm_cod" id="cod">
                <fieldset id="datosUsuario">
                    <legend id="tituloU">&nbsp; Datos de Usuario</legend>
                    <table>
                       
                        
                        
                       <%= editar %>                   
                        
                        
                        <tr hidden="true">
                            <td><span id="adjuntar">&nbsp;&nbsp;Adjuntar Archivo&nbsp;&nbsp;</span></td>
                            <td>
                                <table id="tabla_1">
                                    <tbody>
                                        <tr>                            
                                            <td><input type="file" name="pix1" id="Subir">&nbsp;&nbsp;<img src="sgt_imagenes/delete.png" alt="" id="delete" onClick="borraFila(this.parentNode.parentNode);"></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </td>                        
                        </tr>                       
                        <tr hidden="true">
                            <td></td>
                            <td><a  href="javascript:agregaFila( 'tabla_1' );" id="Agregar"><img src="sgt_imagenes/add.png" alt="" id="add">&nbsp;&nbsp;Agregar Mas...</a></td>
                        </tr>
                        <tr>
                            <td><br><input type="button" name="enviar" id="Enviar" value="Actualizar" onclick="fjs_guardarDatos();"></td>
                            <td><br><input type="reset" onclick="history.back();" name="cancelar" id="Cancelar" value="Atras"></td>                            
                        </tr>
                    </table>                               
                </fieldset>
            </form>
        </section>
    </body>
</html>
