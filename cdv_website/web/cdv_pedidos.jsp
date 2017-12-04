<%-- 
    Document   : cdv_pedidos
    Created on : 06/05/2015, 05:19:59 PM
    Author     : ortegam
--%>

<%@page import="com.venvidrio.cdv.utility.cdvUtility"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Carrito</title>
        <link href="cdv_imagenes/ico.ico" type="image/x-icon" rel="shortcut icon" />
        <link href="cdv_estilos/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="cdv_estilos/material-fullpalette.css" rel="stylesheet" type="text/css"/>
        <link href="cdv_estilos/roboto.css" rel="stylesheet" type="text/css"/>
        <link href="cdv_estilos/ripples.css" rel="stylesheet" type="text/css"/>
        
        <script src="js/jquery-1.11.2.js" type="text/javascript"></script>
        <script src="js/jquery.dataTables.js" type="text/javascript"></script>
        <script src="js/dataTables.bootstrap.js" type="text/javascript"></script>
        <link href="cdv_estilos/dataTables.bootstrap.css" rel="stylesheet" type="text/css"/>
        
        <script>
            $(document).ready(function() {
                $('#DataTable').dataTable( {
                } );
            } );
        </script>
    </head>
    <body>
        <% 
            cdvUtility util = new cdvUtility();
            String nom_usuario="";
            String rif="";
            if(session.getAttribute("nombre_usuario")!=null){
                nom_usuario=session.getAttribute("nombre_usuario").toString();
                rif=session.getAttribute("rif").toString();
            }else{
                nom_usuario="";
            }
            
            String barra = util.Barra(rif, nom_usuario);
        %>
        <div class="container">
            <img src="cdv_imagenes/cintillo.png" class="img-responsive" style="width: 100%;" alt=""/>
        </div>        
        <%= barra%>
        
        
        <div class="container"> 
            <div class="jumbotron">
                <center>
                    <h1 class="text-danger"><i class="glyphicon glyphicon-file"></i> Lista de Pedidos</h1>
                </center>
            </div>            
             
            <!--------------------------AQUI VA EL DETALLE DEL PEDIDO---------------------------------------->
            <div class="jumbotron">
                <table id="DataTable"  class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>Código</th>
                            <th>Estatus</th>
                            <th>Fecha</th>
                            <th>Cant. Pendiente</th>
                            <th>Cant. Despachada</th>
                            <th>Total</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td class="text-danger">265</td>
                            <td>Proceso de cotización</td>
                            <td>2015-05-06</td>
                            <td>2</td>
                            <td>8</td>
                            <td>15000,00</td>
                            <td>
                                <a class="btn btn-sm btn-primary" style="margin-top: -4px;" data-toggle="modal" data-target="#Editar"><i class="glyphicon glyphicon-pencil"></i> Registrar Pago</a>                               
                            </td>
                        </tr>
                        <tr>
                            <td class="text-danger">290</td>
                            <td>Cotizacion</td>
                            <td>2015-05-06</td>
                            <td>2</td>
                            <td>8</td>
                            <td>15000,00</td>
                            <td>
                                <a class="btn btn-sm btn-primary" style="margin-top: -4px;" data-toggle="modal" data-target="#Editar"><i class="glyphicon glyphicon-pencil"></i> Registrar Pago</a>                               
                            </td>
                        </tr>
                        <tr>
                            <td class="text-danger">265</td>
                            <td>P</td>
                            <td>2015-05-06</td>
                            <td>2</td>
                            <td>8</td>
                            <td>15000,00</td>
                            <td>
                                <a class="btn btn-sm btn-primary" style="margin-top: -4px;" data-toggle="modal" data-target="#Editar"><i class="glyphicon glyphicon-pencil"></i> Registrar Pago</a>                               
                            </td>
                        </tr>
                        <tr>
                            <td class="text-danger">265</td>
                            <td>P</td>
                            <td>2015-05-06</td>
                            <td>2</td>
                            <td>8</td>
                            <td>15000,00</td>
                            <td>
                                <a class="btn btn-sm btn-primary" style="margin-top: -4px;" data-toggle="modal" data-target="#Editar"><i class="glyphicon glyphicon-pencil"></i> Registrar Pago</a>                               
                            </td>
                        </tr>
                        <tr>
                            <td class="text-danger">265</td>
                            <td>P</td>
                            <td>2015-05-06</td>
                            <td>2</td>
                            <td>8</td>
                            <td>15000,00</td>
                            <td>
                                <a class="btn btn-sm btn-primary" style="margin-top: -4px;" data-toggle="modal" data-target="#Editar"><i class="glyphicon glyphicon-pencil"></i> Registrar Pago</a>                               
                            </td>
                        </tr>
                    </tbody>
                </table>             
            </div>
            
            <!---------------ESTA ES LA VENTANA EMERGENTE PARA EDITAR LA CANTIDAD  ---------------------->
            <div class="modal fade" id="Editar" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            <h2 class="modal-title text-danger text-center">Datos del Pago</h2>
                        </div>
                        <div class="modal-body">
                            <div class="col-lg-10 col-lg-offset-1">
                            
                                <table class="table">
                                    <tbody>
                                        <tr>
                                            <td><strong>Fecha:</strong></td>
                                            <td><input type="date" class="form-control"></td>
                                        </tr>
                                        <tr>
                                            <td><strong>Monto:</strong></td>
                                            <td><input type="text" class="form-control"></td>
                                        </tr>
                                        <tr>
                                            <td><strong>Numero de Referencia:</strong></td>
                                            <td><input type="text" class="form-control"></td>
                                        </tr>
                                        <tr>
                                            <td><button class="btn btn-primary btn-block"><span class="glyphicon glyphicon-ok"></span> Guardar</button></td>
                                            <td><button class="btn btn-danger btn-block" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancelar</button></td>
                                        </tr>
                                    </tbody>
                                </table>
                            
                            
                            </div>
                        </div>
                        <div class="modal-footer">
                            
                        </div>
                    </div>
                </div>
            </div>  
        </div>
        
        <footer class="navbar navbar-default navbar-btn" role="navigation"><br>
            <div class="container">
                <div class="col-lg-4">
                    <p class="text-center"><strong>Casita del Vidrio (Caracas)</strong><br>
                        (0212)300-2504 / 2577 / 2557<br>                        
                        castiadelvidrio.ccs@venvidrio.com.ve
                    </p>
                </div>
                <div class="col-lg-4">                    
                    <p class="text-center"><strong>Casita del Vidrio (Valera)</strong><br>
                        (0271)400-2841<br>
                        castiadelvidrio.vlr@venvidrio.com.ve
                    </p>
                </div>
                <div class="col-lg-4">                    
                    <p class="text-center"><strong>Casita del Vidrio (Valencia)</strong><br>
                        (0241)613-2398 / 1913<br>
                        castiadelvidrio.lgv@venvidrio.com.ve
                    </p>
                </div>
                <div class="col-lg-12">
                    <p class="text-center">Todos los derechos reservados &COPY; 2015 <br>
                        Venezolana del Vidrio C.A. (VENVIDRIO)<br>
                        G-20009820-1</p>
                </div>
                    
            </div>            
        </footer>
        
        <!--Aqui coloco los archivos .js necesarios para que los estilos funcionen bien --->
        
        <script src="js/bootstrap.js" type="text/javascript"></script>
        <script src="js/material.min.js" type="text/javascript"></script>
        <script src="js/ripples.min.js" type="text/javascript"></script>
        <script>
            $(document).ready(function(){
                $.material.init();
            });
        </script>
    </body>
</html>
