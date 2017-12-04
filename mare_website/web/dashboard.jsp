<%-- 
    Document   : index
    Created on : 27/10/2014, 02:41:37 PM
    Author     : Mario
--%>

<%@page import="loader.ConsultarRegistrosLoader"%>
<%@page import="utility.mareUtility"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="mare_imagenes/logo.ico" type="image/x-icon" rel="shortcut icon" />
        <title>Mare Mare</title>
        <script src="mare_script/jquery-1.11.1.js" type="text/javascript"></script>
        <script src="mare_script/jquery.dataTables.js" type="text/javascript"></script>
        <script src="mare_script/dataTables.bootstrap.js" type="text/javascript"></script>
        <link href="mare_estilos/dataTables.bootstrap.css" rel="stylesheet" type="text/css"/>
                
        
        <link href="mare_estilos/estilos.css" rel="stylesheet" type="text/css"/>
        <link href="mare_estilos/bootstrap.css" rel="stylesheet" type="text/css"/>
        <script>
            $(document).ready(function(){
                $('#example').DataTable();
            });
            function abrirReporteXML(){
                
                if(window.XMLHttpRequest){
                    xmlhttparea = new XMLHttpRequest();
                    xmlhttparea.onreadystatechange = processReqChangeReporte;
                    xmlhttparea.open("GET","AjaxAbrirReporte.jsp");
                    xmlhttparea.send(null);
                }
                
                else if (window.ActiveXObject){// code for IE6, IE5
//	
                    xmlhttparea = new ActiveXObject("Microsoft.XMLHTTP");
                    xmlhttparea.onreadystatechange = processReqChangeReporte;
                    xmlhttparea.open("GET","AjaxAbrirReporte.jsp");
                    xmlhttparea.send();

                }
                  
            }

            function processReqChangeReporte(){
                var reporte = document.getElementById("reporte");

                if(xmlhttparea.readyState === 4){
                          reporte.innerHTML = xmlhttparea.responseText;
                          
                }
                  
            }
            
            
        </script>
    </head>
    <body>
        <%@ page import= "java.lang.*, utility.mareUtility"%>
       <%
        mareUtility util = new mareUtility(); 
	String barra="";
        String nombre = (String) session.getAttribute("nombre");
        String apellido =(String) session.getAttribute("apellido");
	String rol=(String) session.getAttribute("rol");
        if(rol==null || rol.equals(null) || rol.equals("") || rol.equals("null")){
        barra = util.mostrarBarraBasica();
	}else{
            barra = util.mostrarBarra(rol, nombre.toUpperCase(), apellido.toUpperCase());
        }
	
	
            
       
        ConsultarRegistrosLoader consulta = new ConsultarRegistrosLoader();
        
        String tabla = consulta.verDatosUsuarios();
        %>
        <%--Barra de navegacion--%>   
        <%= barra%><br><br><br>
         <div class="container-fluid">
            <div class="row">
              <div class="col-sm-3 col-md-2 sidebar">
                <ul class="nav nav-pills nav-stacked">
                  <li class="active"><a href="Controller?event=PANEL_DE_CONTROL">Usuarios</a></li>
                  <li><a href="reporte.jsp">Reportes</a></li>
                  <li><a href="producto.jsp">Productos</a></li><br><br> 
                  <a href="Controller?event=CORREO" class=" form-control btn btn-danger">Correo cumpleaños</a><br><br>
                  <a href="Controller?event=CORREO_PROMOCION" class=" form-control btn btn-warning">Enviar promoción</a>
                </ul>          
              </div>
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main" style="margin-top:-12%;">
                
                <h1 class="page-header">Panel de Control</h1>
                <h2 class="sub-header">Usuario Registrados</h2>
                <div  class="table-responsive">
                  <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>Correo</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>Correo</th>
                        </tr>
                    </tfoot>
                    <tbody>
                        <%= tabla %>
                    </tbody>
                    
                </table>
                </div>
              </div>
            </div>
          </div>
         
         
         <footer class="navbar navbar-default navbar-btn" role="navigation">
            <div class="container">
                <div class="navbar-text text-center">
                    <p class="text-center">&COPY;Todos los derechos reservados por Mare Mare</p>
                </div>
            </div>            
        </footer>
        
        
       
        
        <script src="mare_script/bootstrap.js" type="text/javascript"></script>
    </body>
</html>