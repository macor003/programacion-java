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
                             
             if(bootbox.confirm("¿Enviar pedido?", function(resultado){
                 if(resultado === false){
                     bootbox.alert("¡Atención! no se envio el pedido");
                 }else{
                    document.f_forma.submit();
                 }
                 
             })){                        
			
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
        String correo =(String) session.getAttribute("correo");
	String rol=(String) session.getAttribute("rol");
        if(rol==null || rol.equals(null) || rol.equals("") || rol.equals("null")){
        barra = util.mostrarBarraBasica();
	}else{
            barra = util.mostrarBarra(rol, nombre.toUpperCase(), apellido.toUpperCase());
        }
	
	     
       
        ConsultarRegistrosLoader consulta = new ConsultarRegistrosLoader();
        
        String tabla = consulta.verCarrito(correo);
        %>
        <%--Barra de navegacion--%>   
        <%= barra%><br><br><br><br><br><br><br><br><br><br>  
         <div class="container-fluid">
            <div class="row">
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main" style="margin-top: -12%;">
                <h1 class="page-header" style="color: mediumvioletred;">Carrito de pedidos</h1>
                <h2 class="sub-header"></h2>
                <a class="btn btn-info" href="bikinis_pedir.jsp">Agregar mas Bikinis</a>
                <a class="btn btn-warning" href="tankinis_pedir.jsp">Agregar mas Tankinis</a>
                <a class="btn btn-primary" href="enteros_pedir.jsp">Agregar mas Enteros</a>
                <form name="f_forma" method="post" action="">
                    <a class="btn btn-success" onclick="">Completar pedido</a>
                </form>
                <div  class="table-responsive">
                  <table id="pedidos" class="table table-striped table-bordered" cellspacing="0" width="100%">
                    <thead>
                        <tr>
                            <th>Modelo</th>
                            <th>Tallas</th>
                            <th>Cantidad</th>
                            <th>Precio</th>
                            <th>sub-total</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th></th>
                            <th></th>
                            <th></th>
                            <th style="text-align: right;">Total:</th>
                            <th></th>
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