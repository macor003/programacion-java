<%-- 
    Document   : index
    Created on : 05-may-2014, 15:40:32
    Author     : Ortegam
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
            
            function cargaDepartamentoXML(cod_planta){
                if(window.XMLHttpRequest){
                    xmlhttpdepartamento = new XMLHttpRequest();
                    xmlhttpdepartamento.onreadystatechange = processReqChangeDepartamento;
                    xmlhttpdepartamento.open("GET","sgt_AjaxListarDepartamento.jsp?COD_PLANTA="+cod_planta,true);
                    xmlhttpdepartamento.send(null);
                }
                
                    else if (window.ActiveXObject){// code for IE6, IE5
//	
                    xmlhttpdepartamento = new ActiveXObject("Microsoft.XMLHTTP");
                    xmlhttpdepartamento.onreadystatechange = processReqChangeDepartamento;
                    xmlhttpdepartamento.open("GET","sgt_AjaxListarDepartamento.jsp?COD_PLANTA="+cod_planta,true);
                    xmlhttpdepartamento.send();

                  }
            }            
            function processReqChangeDepartamento(){
                 
                  var departamento = document.getElementById("departamento");

                  if(xmlhttpdepartamento.readyState === 4){
                          departamento.innerHTML = xmlhttpdepartamento.responseText;
                          
                    }                 
            }
            
            function cargaPersonalXML(cod_dpto){
                
                if(window.XMLHttpRequest){
                    xmlhttppersonal = new XMLHttpRequest();
                    xmlhttppersonal.onreadystatechange = processReqChangePersonal;
                    xmlhttppersonal.open("GET","sgt_AjaxListarPersonal.jsp?COD_DEPARTAMENTO="+cod_dpto,true);
                    xmlhttppersonal.send(null);
                }
                
                    else if (window.ActiveXObject){// code for IE6, IE5
//	
                    xmlhttppersonal = new ActiveXObject("Microsoft.XMLHTTP");
                    xmlhttppersonal.onreadystatechange = processReqChangePersonal;
                    xmlhttppersonal.open("GET","sgt_AjaxListarPersonal.jsp?COD_DEPARTAMENTO="+cod_dpto,true);
                    xmlhttppersonal.send();

                  }
            }
            
            function processReqChangePersonal(){
                 
                  var personal = document.getElementById("personal");

                  if(xmlhttppersonal.readyState === 4){
                          personal.innerHTML = xmlhttppersonal.responseText;
                          
                    }
                  
            }
            
            function mostrarFicha( ficha ){
                document.getElementById("ficha").innerHTML= document.getElementById("selectC").value;
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
            
            function cargaAnalistaXML(cod_planta){
                if(window.XMLHttpRequest){
                    xmlhttpdepartamento = new XMLHttpRequest();
                    xmlhttpdepartamento.onreadystatechange = processReqChangeAnalista;
                    xmlhttpdepartamento.open("GET","sgt_AjaxListarPersonalAsignar.jsp?COD_PLANTA="+cod_planta,true);
                    xmlhttpdepartamento.send(null);
                }
                
                    else if (window.ActiveXObject){// code for IE6, IE5
//	
                    xmlhttpdepartamento = new ActiveXObject("Microsoft.XMLHTTP");
                    xmlhttpdepartamento.onreadystatechange = processReqChangeAnalista;
                    xmlhttpdepartamento.open("GET","sgt_AjaxListarPersonalAsignar.jsp?COD_PLANTA="+cod_planta,true);
                    xmlhttpdepartamento.send();

                  }
            }            
            function processReqChangeAnalista(){
                 
                  var analista = document.getElementById("analista");

                  if(xmlhttpdepartamento.readyState === 4){
                          analista.innerHTML = xmlhttpdepartamento.responseText;
                          
                    }                 
            }
            
            function fjs_guardarDatos(){
                   //alert ("lm_dpto = " + document.f_forma.lm_dpto.value);
                   //alert ("lm_ficha = " + document.f_forma.lm_ficha.value);
                    //alert ("lm_tipo_requerimiento = " + document.f_forma.lm_tipo_requerimiento.value);
                    //alert ("lm_area_requerimiento = " + document.f_forma.lm_area_requerimiento.value);
                    //alert ("lm_clasifi_requerimiento = " + document.f_forma.lm_clasificacion.value);
                    //alert ("lm_desc_requerimiento = " + document.f_forma.lm_descripcion.value);
                        
                if(!fsj_validaSelect("lm_planta","una Planta")){return false;}
                if(!fsj_validaSelect("lm_dpto","un Departamento")){return false;}
                if(!fsj_validaSelect("lm_ficha","un Usuario")){return false;}
                
                if(!fsj_validaSelect("lm_urgencia","una Urgencia")){return false;}
                if(!fsj_validaSelect("lm_tipo_requerimiento","un Tipo Requerimiento")){return false;}
                //if(!fsj_validaSelect("lm_area_requerimiento","un Area")){return false;}
                //if(!fsj_validaSelect("lm_clasificacion","la Clasificación del Requerimiento")){return false;}
                if(!fsj_validaSelect("lm_descripcion","la Descripcion del Requerimiento")){return false;}
                
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
                $( "#help" ).click(function() {
                $( "#dialogo" ).dialog( "open" );
                });
            });
            $(function() {
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
        <%
        
            TSgtVerTipoRequerimientoLoader requerimiento=new TSgtVerTipoRequerimientoLoader();
            String tipo= requerimiento.verTiporequerimiento();
            String planta = requerimiento.verPlanta();
            String planta_analista = requerimiento.verPlantaAnalista();
            String prioridad = requerimiento.verUrgencia();
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
        <img src="sgt_imagenes/help_desk_logo.png" alt="" class="logo">
        <section id="centro">
            <h1 style=" text-align: center">Crear <br>Requerimiento</h1>
        </section>

        <section id="formulario">
            <form name="f_forma" action="Controller?event=GUARDAR_REQUERIMIENTO_OTRO_USUARIO" method="post" accept-charset="ISO-8859-1">
                <fieldset id="datosUsuario">
                    <legend id="tituloU">&nbsp; Datos de Usuario</legend>
                    <table>
                        <tr>
                            <td>
                                <label>Planta:</label>
                            </td>
                            <td>
                                <div name="lm_planta">
                                    <%= planta %>
                                </div>
                            </td>
                        </tr>
                        <tr>                   
                            <td>
                                <label for="nombre">Nombre:</label>
                            </td>
                            <td>
                                <div id="personal" name="lm_nombre">
                                    <select id="selectC" >
                                        <option value="su">Seleccione Uno(a)</option>
                                    </select>
                                </div>                           
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label >Departamento:</label>
                            </td>
                            <td>
                                <div id="departamento" name="lm_dpto">
                                    <select id="selectT">
                                        <option value="su"> Seleccione Uno(a)</option>
                                    </select>
                                </div>
                            </td>                            
                        </tr>
                        <tr>
                            <td><label for="ficha">N° de Ficha:</label></td>
                            <td>
                                <div name="lm_ficha" >
                                    <select>
                                        <option id="ficha" selected>Ficha</option>
                                    </select>
                                </div>
                            </td>
                        </tr>
                    </table>
                </fieldset>
                <fieldset id="datosRequerimiento">
                    <legend id="tituloR">&nbsp; Datos del Requerimiento</legend>
                    <table>
                        <tr>
                            <td>Urgencia: </td>
                            <td>
                                <div name="lm_urgencia">
                                <%=prioridad %>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label id="txtTipo">Tipo de Requerimiento:</label>
                            </td>
                            <td>
                                <div name="lm_tipo_requerimiennto">
                                    <%= tipo %> &nbsp;
                                    <img src="sgt_imagenes/help.png" id="help" onmouseover="alerta();" title="¿Cuales son los tipos de requerimientos?">
                                </div>
                                <div id="dialogo" title="Tipos de Requerimientos">
                                    <p>
                                        <strong >¿Que es un Incidente?</strong><br>
                                        <label style="font-size: 12px" >Cualquier evento que no forma parte del desarrollo habitual del servicio y que causa, o puede causar una interrupción del mismo o una reducción de la calidad de dicho servicio.</label><br><br>
                                        <strong>¿Que es una Peticion de Servicio?</strong><br>
                                        <strong>¿Que es una Solicitud de Cambio?</strong>
                                    </p>
                                </div>
                            </td>
                        </tr>
                        <% if (rol.equals("01")){%>
                        <tr>
                            <td>
                                <label>Area:</label>
                            </td>
                            <td>
                                <div id="area" name="lm_area_requerimiento">
                                    <select id="selectA" disabled="true" >
                                        <option value="su1">Seleccione Uno(a)</option>
                                    </select>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td><label id="txtClasi">Clasificación:</label></td>
                            <td>
                                <div id="clasificacion" name="lm_clasificacion">
                                    <select id="selectC"  disabled="true">
                                        <option value="su">Seleccione Uno(a)</option>
                                    </select>
                                </div>
                            </td>
                        </tr>
                        <%}%>
                        <tr id="descrip">
                            <td>
                                <label id="txtDesc">Descripción:</label>                                
                            </td>
                            <td>
                                <div >
                                    <textarea rows="5" cols="45" placeholder="Ingrese la descripción del requerimiento" name="lm_descripcion"></textarea>
                                </div>
                            </td>
                        </tr>
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
                    </table>                               
                </fieldset>
                <fieldset id="datosUsuario">
                    <legend id="tituloU">&nbsp; Asignar Analista</legend>
                    <table>
                        <tr>
                            <td>
                                <label>Planta Analista:</label>
                            </td>
                            <td>
                                <div name="lm_planta_analista">
                                    <%= planta_analista %>
                                </div>
                            </td>
                        </tr>
                        <tr>                   
                            <td>
                                <label for="nombre">Nombre Analista:</label>
                            </td>
                            <td>
                                <div id="analista" name="lm_analista_asignado">
                                    <select id="selectC" name="lm_ficha_analista" >
                                        <option value="su">Seleccione Uno(a)</option>
                                    </select>
                                </div>                           
                            </td>
                        </tr>                        
                        <tr>
                            <td><br><br><input type="button" name="enviar" id="Enviar" value="Enviar" onclick="fjs_guardarDatos();"></td>
                            <td><br><br><input type="reset" name="cancelar" id="Cancelar" value="Borrar"></td>
                        </tr>
                    </table>
                </fieldset>
            </form>
        </section>
    </body>
</html>