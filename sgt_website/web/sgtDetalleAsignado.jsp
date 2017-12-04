<%-- 
    Document   : sgtDetalleAprobarRequerimiento
    Created on : 20-ago-2014, 13:59:12
    Author     : Ortegam
--%>

<%@page import="com.venvidrio.sgt.loader.TSgtVerTipoRequerimientoLoader"%>
<%@page import="com.venvidrio.sgt.utility.sgtUtility"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@page import="com.venvidrio.sgt.loader.TSgtPendientesLoader"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1" />
        <title>HelpDesk | Detalles</title>
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
                $('#tablaHistorial').dataTable( {
                } );
            } );
           function Asignar(){
              var cod = "<%= request.getParameter("codigo")%>";
               //alert("Codigo del Requerimiento: "+ cod);
               
               document.f_forma.cod.value=cod;
               if(!fsj_validaSelect("lm_planta","una planta")){return false;}
               if(!fsj_validaSelect("lm_ficha_analista","un analista")){return false;}
               
               document.f_forma.action="Controller?event=GUARDAR_RE_ASIGNACION";         
               if(window.confirm("¿Está seguro(a) que desea asignar el requerimiento?")){                        
			document.f_forma.submit();
                }else{
                    location.reload(true);
                }
               
            }
            function Procesar(){
                var cod = "<%= request.getParameter("codigo") %>";
                
                document.f_forma.cod.value= cod;
                document.f_forma.action="Controller?event=PROCESAR_REQUERIMIENTO";         
               if(window.confirm("¿Quiere procesar este requerimiento?")){                        
			document.f_forma.submit();
                }else{
                    location.reload(true);
                }
            }
            
            function EditarRequerimiento(){
                var cod = "<%= request.getParameter("codigo") %>";
                
                document.f_forma.cod.value= cod;
                
                document.f_forma.action="Controller?event=EDITAR_REQUERIMIENTO";                       
			document.f_forma.submit();
                
            }
            function Comentar(){
                var cod = "<%= request.getParameter("codigo") %>";
                
                document.f_forma.cod.value= cod;
                if(!fsj_validaInput2("textSolucion","un comentario")){return false;}
                document.f_forma.action="Controller?event=GUARDAR_COMENTARIO";         
               if(window.confirm("¿Guardar comentario?")){                        
			document.f_forma.submit();
                }else{
                    location.reload(true);
                }
            }
            
            function Finalizar(){
                var cod = "<%= request.getParameter("codigo") %>";
                
                document.f_forma.cod.value= cod;                
                if(!fsj_validaInput2("textSolucion","la solución")){return false;}
                document.f_forma.action="Controller?event=GUARDAR_FINALIZAR";         
               if(window.confirm("¿Está seguro(a) que desea finalizar el requerimiento?")){                        
			document.f_forma.submit();
                }else{
                    location.reload(true);
                }
            }
            
            function Cancelar(){
                var cod = "<%= request.getParameter("codigo") %>";
                
                document.f_forma.cod.value= cod;
                
                document.f_forma.action="Controller?event=GUARDAR_CANCELAR";         
               if(window.confirm("¿Está seguro(a) que desea cancelar el requerimiento?")){                        
			document.f_forma.submit();
                }else{
                    location.reload(true);
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
                 
                  var analista = document.getElementById("analista");

                  if(xmlhttpdepartamento.readyState === 4){
                          analista.innerHTML = xmlhttpdepartamento.responseText;
                          
                    }                 
            }
            
            function atras(){
                document.f_forma.action="Controller?event=ASIGNADOS";
            }
            function fjs_guardarUrgenciaEditada(){                
                var cod_requerimiento = "<%= request.getParameter("codigo")%>";
                document.f_forma.cod.value = cod_requerimiento;
                //alert("codigo requerimiento: "+cod_requerimiento);
                if(!fsj_validaSelect2("SelecUrgencia","la urgencia para cambiarla.")){return false;}
                var cod_urge = document.getElementById("SelecUrgencia").value;
                document.f_forma.cod_urge.value = cod_urge;
                alert("codigo urgencia: "+cod_urge);
                
                document.f_forma.action="Controller?event=GUARDAR_EDITAR_URGENCIA";
                if(window.confirm("¿Está seguro(a) que desea cambiar la urgencia?")){                        
			document.f_forma.submit();
		}
                
            } 
            
        </script>
    </head>
    <body>
        <%@ page import= "java.lang.*, com.venvidrio.sgt.utility.sgtUtility, com.venvidrio.sgt.loader.TSgtAprobarLoader"%>
        <%
        TSgtVerTipoRequerimientoLoader requerimiento=new TSgtVerTipoRequerimientoLoader();
        String planta = requerimiento.verPlanta();
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
        
        TSgtPendientesLoader pendiente = new TSgtPendientesLoader();
        String tabla =""; 
        String historial = pendiente.verHistorialRequerimiento(var);
        if(var != "NULL"){            
            pendiente.verDetallesRequerimientoAsignado(var);
            System.out.println("Codigo del Requerimieno JSP Detalle: "+ var);
            tabla = pendiente.verDetallesRequerimientoAsignado(var); 
        }else{
            String var2 = (String) request.getSession().getAttribute("cod_reque");
            pendiente.verDetallesRequerimientoPendientes(var2);
            System.out.println("Codigo del Requerimieno JSP Detalle: "+ var2);
            tabla = pendiente.verDetallesRequerimientoPendientes(var2);
        } 
        
        //String TablaDetalle = detalle.verDetallesRequerimientoPorAprobar(ficha, var);
                   
        %>
        <img src="sgt_imagenes/background.png"  alt="" class="fondo">
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
            <h2 style="margin-left: 100px;">Detalles del Requerimiento</h2>
            <form name="f_forma" method="post">
                <input type="hidden" id="cod" name="cod">
                <input type="hidden" id="cod_urge" name="cod_urge">               
                <button type="button" onclick="Procesar();" id="btnProcesar" style="position: absolute; margin-left: 540px; margin-top: -45px;">Procesar</button>
                <%= tabla%>                
            </form>           
        </section>           
        <section>
            <div style=" width: 970px; margin-left: 225px; margin-top: -300px;">
                <table id="tablaHistorial" class="display" cellspacing="0" width="100%" style=" border: 1px solid #A60A0A; margin-top: 10px;">
                    <caption>Historial del requerimiento</caption>
                    <thead style="  font: 13px Open Sans Light; font-weight: 300;color: white; background-color: #A60A0A; top: 10px;">                                       
                        <tr>
                            <th style="width: 100px;">Fecha</th>
                            <th style="width: 100px;">Actividad</th>
                            <th style="width: 250px;">Responsable</th>
                            <th>Descripción</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%= historial %>
                    </tbody>
                </table>
            </div>
        </section>
        
    </body>
</html>
