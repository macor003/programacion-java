<%-- 
    Document   : sgtIncorrect
    Created on : 23-may-2014, 11:00:54
    Author     : Ortegam
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HelpDesk | Panel de Control</title>
        <link href="sgt_estilos/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="sgt_imagenes/icono2.ico" type="image/x-icon" rel="shortcut icon" />
        <link href="sgt_estilos/estilosBootstrap.css" rel="stylesheet" type="text/css"/>
        <script src="sgt_script/jquery-1.11.1.js" type="text/javascript"></script>
        <script>
        
        </script>
    </head>
    
    <body style="">
        <div class="container" style=" height: 100%; box-shadow: 0px 0px 5px 2px rgba(0, 0, 0, 0.75);  background-image: url('sgt_imagenes/background.png'); background-repeat: no-repeat; background-size: cover; ">
            <div class="row">
                <img src="sgt_imagenes/cintillo.png" style="width: 100%;" alt=""/>

                <nav class="navbar navbar-default" role="navigation">
                    
                </nav><br>
                <h1 class="text-center">Panel de Control</h1><br>
                <div class="col-sm-9 col-md-10 col-lg-offset-1 ">
                    <div class="col-md-4">
                        <div class="panel panel-info">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="glyphicon glyphicon-warning-sign" style="font-size: 70px"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <h1> <strong>150</strong></h1>
                                        <div>Incidentes</div>
                                    </div>
                                </div>
                            </div>
                            <a href="#">
                                <div class="panel-footer">
                                    <span class="pull-left">Ver Detalles</span>
                                    <span class="pull-right"><i class="glyphicon glyphicon-arrow-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="panel panel-success">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="glyphicon glyphicon-wrench" style="font-size:  70px;"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <h1><strong>110</strong></h1>
                                        <div>Petición de Servicios</div>
                                    </div>
                                </div>
                            </div>
                            <a href="#">
                                <div class="panel-footer text-success">
                                    <span class="pull-left">Ver detalles</span>
                                    <span class="pull-right"><i class="glyphicon glyphicon-arrow-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="panel panel-danger">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="glyphicon glyphicon-refresh" style="font-size: 70px;"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <h1><strong>40</strong></h1>
                                        <div>Solicitud de Cambio</div>
                                    </div>
                                </div>
                            </div>
                            <a href="#">
                                <div class="panel-footer text-danger">
                                    <span class="pull-left">Ver detalles</span>
                                    <span class="pull-right"><i class="glyphicon glyphicon-arrow-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>                
            </div>
            <script src="sgt_script/highcharts.js" type="text/javascript"></script>
            <script src="sgt_script/modules/exporting.js" type="text/javascript"></script>

            <%--div id="grafico" style="min-width: 310px; height: 400px; margin: 0 auto"></div--%>
            
            <div class="row navbar navbar-default navbar-btn" style="margin-bottom: 0px;">
                    <h5 class="text-center" style="color: white;">&copy; Gerencia Corporativa de Tecnología</h5>
            </div>
            
        </div>
        
        <script src="sgt_script/bootstrap.js" type="text/javascript"></script>        
    </body>
</html>
