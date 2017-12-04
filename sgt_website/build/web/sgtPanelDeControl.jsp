<%-- 
    Document   : sgtPanelDeControl
    Created on : 23/03/2015, 11:04:14 AM
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
    </head>
    <body style="">
        <div class="container" style="box-shadow: 0px 0px 5px 2px rgba(0, 0, 0, 0.75);">
            <div class="row">
                <img src="sgt_imagenes/cintillo.png" style="width: 100%;" alt=""/>

                <nav class="navbar navbar-default" role="navigation">
                    <!-- El logotipo y el icono que despliega el menú se agrupan
                         para mostrarlos mejor en los dispositivos móviles -->
                    <div class="navbar-header">
                      <button type="button" class="navbar-toggle" data-toggle="collapse"
                              data-target=".navbar-ex1-collapse">
                        <span class="sr-only">Desplegar navegación</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                      </button>
                        <a class="navbar-brand" href="#"><img src="sgt_imagenes/help_desk_logo.png" style="width: 50px; margin-top: -16px;" alt=""/></a>


                    </div >

                    <!-- Agrupar los enlaces de navegación, los formularios y cualquier
                         otro elemento que se pueda ocultar al minimizar la barra -->
                    <div class="collapse navbar-collapse navbar-ex1-collapse">
                      <ul class="nav navbar-nav">
                        <li class="active"><a href="#"><span class="glyphicon glyphicon-home"></span> Inicio</a></li>
                        <li class="dropdown">
                          <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                           <span class="glyphicon glyphicon-file"></span> Crear <b class="caret"></b>
                          </a>
                          <ul class="dropdown-menu">
                            <li><a href="#"><span class="glyphicon glyphicon-plus"></span> Nuevo requerimiento</a></li>
                            <li><a href="#"><span class="glyphicon glyphicon-plus"></span> Requerimiento para otro usuario</a></li>
                          </ul>
                        </li>
                        <li class="dropdown">
                          <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                           <span class="glyphicon glyphicon-tags"></span>  Requerimientos <b class="caret"></b>
                          </a>
                          <ul class="dropdown-menu">
                            <li><a href="#"><span class="glyphicon glyphicon-check"></span> Asignados</a></li>
                            <li><a href="#"><span class="glyphicon glyphicon-file"></span> Creados</a></li>
                            <li><a href="#"><span class="glyphicon glyphicon-list-alt"></span> Historial</a></li>
                            <li><a href="#"><span class="glyphicon glyphicon-inbox"></span> Pendientes por asignar</a></li>
                            <li><a href="#"><span class="glyphicon glyphicon-exclamation-sign"></span> Pendientes por aprobar</a></li>
                          </ul>
                        </li>
                      </ul>
                      <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                          <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                           <span class="glyphicon glyphicon-user"></span> ORTEGA RODRIGUEZ MARIO CESAR <b class="caret"></b>
                          </a>
                          <ul class="dropdown-menu">
                            <li><a href="#"><span class="glyphicon glyphicon-cog"></span> Configurar</a></li>
                            <li class="divider"></li>
                            <li><a href="#"><span class="glyphicon glyphicon-off"></span> Cerrar sesión</a></li>
                          </ul>
                        </li>
                      </ul>
                    </div>
                  </nav>
                
                <div class="jumbotron" style=" margin-top: -19px; background-image: url('sgt_imagenes/background.png'); background-repeat: no-repeat; background-size: 100%; ">
                     
                    <img src="sgt_imagenes/venvidrio.png" class="img-responsive " id="logo" alt="" >   
                    <h1 class="text-center" style="font-size: 35px; margin-top: -500px;">Sistema de Gestión de Tecnología</h1>          

                    <section class="jumbotron center-block" style=" position:  relative;width: 390px; height: 500px; background-color: transparent;">
                        <img id="perfil" class="center-block" src="sgt_imagenes/perfil2.png" alt="">
                        <h4 class="text-center">Ingrese su Usuario y Contraseña</h4>
                        <form name="f_forma" role="form" onSubmit="return fjs_aceptar();" action="sgtLogin" method="post">
                            <input type="text" class="form-control" name="tf_usuario" placeholder="Usuario" id="usuario" ><br>
                            <input type="password" class="form-control" name="tf_contrasena" placeholder="Contraseña" id="contraseña"><br>
                            <input type="submit" class="btn btn-default btn-block" name="acceder" value="Acceder" onclick="fjs_aceptar();">
                        </form>
                    </section>
                </div>
                <div class="navbar navbar-default navbar-btn">
                    <h5 class="text-center" style="color: white;">&copy; Gerencia Corporativa de Tecnología</h5>
                </div>
            </div>
        </div>
        <script src="sgt_script/jquery-1.11.1.js" type="text/javascript"></script>
        <script src="sgt_script/bootstrap.js" type="text/javascript"></script>        
    </body>
</html>
