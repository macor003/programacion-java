<%-- 
    Document   : smsExito
    Created on : 30/07/2015, 02:37:58 PM
    Author     : ortegam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="vv_imagenes/icono.ico" type="image/x-icon" rel="shortcut icon" />
        <title>SMS Venvidrio</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="vv_estilos/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="vv_estilos/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        
        <script src="vv_scripts/jquery-1.11.1.js" type="text/javascript"></script>
        
        <script>
            
        </script>
    </head>
    <body>
        <%
            String nombre=(String) session.getAttribute("nombre_usuario");
        %>
        <nav class="navbar navbar-inverse" style="border-radius: 0px;">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                  <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                  </button>
                  <a class="navbar-brand" href="#"></a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                  <ul class="nav navbar-nav">
                      <li class="active"><a href="Controller?event=ENVIAR_MENSAJE"><span class="glyphicon glyphicon-send"></span> Enviar SMS<span class="sr-only">(current)</span></a></li>
                      <!--li><a href="#"><span class="glyphicon glyphicon-list-alt"></span> Lista de SMS Enviados</a></li-->
                  </ul>
                  <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                      <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-user"></span> <%= nombre%><span class="caret"></span></a>
                      <ul class="dropdown-menu">
                          <li><a href="smsCerrarSesion.jsp"><span class="glyphicon glyphicon-off"></span> Cerrar sesión</a></li>
                      </ul>
                    </li>
                  </ul>
                </div><!-- /.navbar-collapse -->
              </div><!-- /.container-fluid -->
            
        </nav>
        <img src="vv_imagenes/venvidrio.png" alt="" class="center-block img-responsive" style="width: 280px; margin-top: 50px;"/>
        <div class="container">
            <h1 class="text-center"><span class="glyphicon glyphicon-phone" style="font-size: 60px;"></span></h1>
            <h1 class="text-center">Mensaje enviado exitosamente <span class="glyphicon glyphicon-ok"></span></h1>
        </div>
        
        <footer class="navbar navbar-default navbar-fixed-bottom" style="border-radius: 0px;">
            <div class="container">               
                <div class="col-lg-12">
                    <p class="text-center">Dirección General de Tecnología &COPY; 2015 <br>
                        Venezolana del Vidrio C.A. (VENVIDRIO)<br>
                        G-20009820-1</p>
                </div>
                    
            </div>            
        </footer>
        
        <script src="vv_scripts/bootstrap.js" type="text/javascript"></script>
    </body>
</html>
