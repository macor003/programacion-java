<%-- 
    Document   : index
    Created on : 27/04/2015, 09:21:51 AM
    Author     : ortegam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bienvenidos</title>
        <link href="cdv_imagenes/ico.ico" type="image/x-icon" rel="shortcut icon" />
        <link href="cdv_estilos/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="cdv_estilos/material-fullpalette.css" rel="stylesheet" type="text/css"/>
        <link href="cdv_estilos/roboto.css" rel="stylesheet" type="text/css"/>
        <link href="cdv_estilos/ripples.css" rel="stylesheet" type="text/css"/>
        <script>
            function ValidaFormulario(){
                document.f_forma.submit();
            }
            
            function Volver(){
                location.href="Controller?event=PRINCIPAL";
            }
            
            $(document).ready(function(){
               $('#miModal').modal('show'); 
            });
        </script>
    </head>
    <body>
        <div class="container">
            <img src="cdv_imagenes/cintillo.png" class="img-responsive" style="width: 100%;" alt=""/>
        </div>        
        <div class="navbar navbar-default"></div>
        
        
        <div class="container">
            <div class="jumbotron col-lg-8 col-lg-offset-2">
                <form name="f_forma" class="form-horizontal" action="cdvLogin" method="post">
                    <fieldset>
                        <legend><h2 class="text-danger text-center">Bienvenidos a la Casita del Vidrio</h2></legend>
                        <img src="cdv_imagenes/casita_del_vidrio.png" style="width: 210px; height: 190px;" class="center-block" alt=""/>
                        <div class="icon-preview"><i class="glyphicon glyphicon-user text-center col-lg-12" style="font-size: 60px;"></i> </div><br><br>
                        <h4 class="text-center text-success">Iniciar Sesión</h4>
                        <div class="form-group">
                            <label for="inputRif" class="col-lg-4 control-label">Rif</label>
                            <div class="col-lg-5">
                                <input name="lm_rif" type="rif" class="form-control" id="inputRif" placeholder="Rif">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputPassword" class="col-lg-4 control-label">Contraseña</label>
                            <div class="col-lg-5">
                                <input name="lm_passwd" type="password" class="form-control" id="inputPassword" placeholder="Contraseña">                                
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <div class="col-lg-10 col-lg-offset-5">
                                <a href="#" onclick="ValidaFormulario()"><button type="button" class="btn btn-success">Entrar</button></a>
                                <button onclick="Volver()" type="button" class="btn btn-danger" data-toggle="modal" data-target="#miModal">Cancelar</button>
                            </div>
                        </div><br>
                        <div class="form-group">
                            <p class="text-center" style="font-size: 14px;">¿No tienes usuario? <a href="cdv_registro.jsp"> Regístrate ahora</a></p>                            
                        </div>
                    </fieldset>
                </form>
            </div>            
        </div>
        
        <!--Aqui coloco las ventanas modales para cada imagen --->
        <div class="modal fade" id="miModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 class="modal-title">Día de la tierra</h4>
                    </div>
                    <div class="modal-body">
                        <img src="cdv_imagenes/Tierra.png" class="img-responsive" alt=""/>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                        <!--button type="button" class="btn btn-primary">Save changes</button-->
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
        <script src="js/jquery-1.11.2.js" type="text/javascript"></script>
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
