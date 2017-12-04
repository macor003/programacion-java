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
            
             function GuardarProducto(){
                 
            var tienda = document.getElementById("lm_tienda").value;
            var nombre = document.getElementById("lm_nombre").value;
            var tipo = document.getElementById("lm_tipo_producto").value;
            var inventario = document.getElementById("lm_inventario").value;
            var modelo = document.getElementById("lm_modelo").value;
            var costo = document.getElementById("lm_costo").value;
            
             if(tienda === "su"){
                 bootbox.alert("Debe indicar la tienda");
                 return false;
             }else if( nombre===""){
                 bootbox.alert("Debe ingresar un nombre");
                 return false;
             }else if( tipo==="su"){
                 bootbox.alert("Debe seleccionar el tipo de producto");
                 return false;
             }else if( inventario===""){
                 bootbox.alert("Debe ingresar el inventario");
                 return false;
             }else if( modelo===""){
                 bootbox.alert("Debe ingresar modelo");
                 return false;
             }else if( costo===""){
                 bootbox.alert("Debe ingresar costo");
                 return false;
             }
             if(bootbox.confirm("¿Está seguro(a) que desea Guardar la información?", function(resultado){
                 if(resultado === false){
                     bootbox.alert("¡Atención! No se guardo el producto.");
                 }else{
                    document.f_forma.submit();
                 }
                 
             })){                        
			
		}
            }
            
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
        
        String tabla = consulta.verProductos();
        String tiendas = consulta.verTiendas();
        String tipo_p = consulta.verTipoProducto();
        %>
        <%--Barra de navegacion--%>   
        <%= barra%><br><br><br><br>     
         <div class="container-fluid">
            <div class="row">
              <div class="col-sm-3 col-md-2 sidebar">
                <ul class="nav nav-pills nav-stacked">
                  <li><a href="Controller?event=PANEL_DE_CONTROL">Usuarios</a></li>
                  <li><a href="reporte.jsp">Reportes</a></li>
                  <li class="active"><a href="producto.jsp">Productos</a></li>
                </ul>          
              </div>
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main" style="margin-top: -12%;">
                <h1 class="page-header" style="color: mediumvioletred;">Productos</h1>
                <h2 class="sub-header"></h2>
                <div  class="table-responsive">
                  <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
                    <thead>
                        <tr>
                            <th>Codigo</th>
                            <th>Tienda</th>
                            <th>Nombre</th>
                            <th>Tipo</th>
                            <th>Talla</th>
                            <th>Inventario busto</th>
                            <th>Inventario cadera</th>
                        </tr>
                    </thead>

                    <tfoot>
                        <tr>
                            <th>Codigo</th>
                            <th>Tienda</th>
                            <th>Nombre</th>
                            <th>Tipo</th>
                            <th>Talla</th>
                            <th>Inventario busto</th>
                            <th>Inventario cadera</th>
                        </tr>
                    </tfoot>
                    <tbody>
                        <%= tabla %>
                    </tbody>
                    
                </table>
                </div>
                    <hr>
                    <h1 class="page-header" style="color: mediumvioletred;">Ingresar producto</h1>
                    <div class="col-md-9">
                        <form name="f_forma" action="Controller?event=GUARDAR_PRODUCTO" method="post" class="form-horizontal">
                            <fieldset> 
                                <legend>Datos del producto</legend>
                                <div class="form-group">
                                  <label for="inputTienda" class="col-lg-2 control-label">Tienda:</label>
                                  <div class="col-lg-10">
                                      <%= tiendas%>
                                  </div>
                                </div>
                                <div class="form-group">
                                  <label for="inputNombre" class="col-lg-2 control-label">Nombre:</label>
                                  <div class="col-lg-10">
                                    <input type="text" class="form-control" name="lm_nombre" id="lm_nombre" placeholder="Nombre">
                                  </div>
                                </div>
                                <div class="form-group">
                                  <label for="inputTienda" class="col-lg-2 control-label">Tipo producto:</label>
                                  <div class="col-lg-10">
                                      <%= tipo_p%>
                                  </div>
                                </div>
                                <div class="form-group">
                                  <label for="inputInventario" class="col-lg-2 control-label">Inventario:</label>
                                  <div class="col-lg-10">
                                      <input type="text" class="form-control" name="lm_inventario" id="lm_inventario" placeholder="Inventario">
                                  </div>
                                </div>
                                <div class="form-group">
                                  <label for="inputInventario" class="col-lg-2 control-label">Modelo:</label>
                                  <div class="col-lg-10">
                                      <input type="text" class="form-control" name="lm_modelo" id="lm_modelo" placeholder="Modelo">
                                  </div>
                                </div>
                                <div class="form-group">
                                  <label for="inputInventario" class="col-lg-2 control-label">Costo:</label>
                                  <div class="col-lg-10">
                                      <input type="text" class="form-control" name="lm_costo" id="lm_costo" placeholder="Costo">
                                  </div>
                                </div>
                                </fieldset>
                                <div class="form-group">
                                    <div class="col-lg-10 col-lg-offset-2">
                                      <button class="btn btn-default">Cancelar</button>
                                      <button onclick="GuardarProducto();"  type="button" class="btn btn-primary">Guardar</button>
                                    </div>
                                </div>
                        </form>
                    </div>
              </div>
            </div>
          </div>
         
         <%--button id="btnReporte" onclick="abrirReporteXML();">Mostrar reporte</button--%>
         <footer class="navbar navbar-default navbar-btn" role="navigation">
            <div class="container">
                <div class="navbar-text text-center">
                    <p class="text-center">&COPY;Todos los derechos reservados por Mare Mare</p>
                </div>
            </div>            
        </footer>
         
              
        <script src="mare_script/bootstrap.js" type="text/javascript"></script>
                
        <script src="mare_script/bootbox.js" type="text/javascript"></script>
    </body>
</html>