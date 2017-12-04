<%-- 
    Document   : cdv_configurar
    Created on : 11/06/2015, 01:47:22 PM
    Author     : Ortegam
--%>

<%@page import="com.venvidrio.cdv.loader.CarritoLoader"%>
<%@page import="com.venvidrio.cdv.utility.cdvUtility"%>
<%@page import="com.venvidrio.cdv.loader.UsuariosLoader"%>
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
        <link href="cdv_estilos/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <script src="js/jquery-1.11.2.js" type="text/javascript"></script>
        
        <script src="js/jquery.numeric.js" type="text/javascript"></script>
        
        <script>
            $(document).ready(function(){
                $('#tlf').numeric();
                $('#tlf2').numeric();
            });
            $('#boton_enviar').attr('disabled', true);
            $('#f_forma').bind('change keyup', function() {
                if($(this).validate().checkForm()) {
                    $('#boton_enviar').attr('disabled', false);
                } else {
                    $('#boton_enviar').attr('disabled', true);
                } });
            
            
            function GuardarDatos(){               
                document.f_forma.action="Controller?event=ACTUALIZAR_CUENTA";
                document.f_forma.submit();                
            }
        </script>
    </head>
    <body>
        <% 
            cdvUtility util = new cdvUtility();
            UsuariosLoader usuario = new UsuariosLoader();
            CarritoLoader carrito = new CarritoLoader();
            String[] detalles = carrito.getPedidosClientes();
            String nom_usuario="";
            String rif="";
            if(session.getAttribute("nombre_usuario")!=null){
                nom_usuario=session.getAttribute("nombre_usuario").toString();
                rif=session.getAttribute("rif").toString();
            }else{
                nom_usuario="";
            }
            
            String barra = util.Barra(rif, nom_usuario);
            String configurar = usuario.ConfigurarUsuario(rif);
        %>
        <div class="container">
            <img src="cdv_imagenes/cintillo.png" class="img-responsive" style="width: 100%;" alt=""/>
        </div>        
        <%= barra%>
        
        
        <div class="container"> 
            <div class="jumbotron">
                <center>
                    <h1 class="text-danger"><i class="glyphicon glyphicon-cog"></i> Configuración</h1>
                </center>
            </div>            
             
            <!--------------------------AQUI VA EL MENU DEL ADMINISTRADOR---------------------------------------->
            <div class="col-lg-3">           
                <div class="panel panel-primary" style="background-color: white; box-shadow: 0 8px 17px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);">
                    <div class="panel-heading"><h3><%= nom_usuario %></h3></div>
                    <div class="panel-body">
                        <ul class="nav nav-pills nav-stacked">
                            <li class="active"><a href="#"><i class="fa fa-user"></i> Cuenta</a></li>
                            <li><a href="cdv_contraseña.jsp"><i class="fa fa-lock"></i> Contraseña</a></li>                            
                        </ul>
                    </div>
                    <div class="clearfix visible-lg"></div>
                </div>
            </div>
            
            
            <!--------------------------AQUI VA EL DETALLE DE LA PAGINA DEL ADMINISTRADOR---------------------------------------->
            <div class="col-lg-9">
                <div class="panel panel-default" style="background-color: white; box-shadow: 0 8px 17px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);">
                    <div class="panel-heading">
                        <h3>    Cuenta</h3>
                        <h4 class="text-muted">Cambia tus configuraciones básicas</h4>
                    </div>
                    <div class="panel-body">
                        <form class="form-horizontal" name="f_forma" id="f_forma" method="post">
                            <%= configurar %>
                        </form>
                    </div>
                    <div class="clearfix visible-lg"></div>
                </div>
            </div>
                
       </div>           
            
        <div class="modal fade in" id="Confirmar">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h3 class="modal-title text-danger text-center"><span class="fa fa-warning"></span> ¡Atención!</h3>
                    </div>
                    <div class="modal-body"><br>    
                        <p>¿Desea Guardar la Información?</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" onclick="GuardarDatos();">Si</button>
                        <button type="button" class="btn btn-danger" data-dismiss="modal">No</button>                        
                    </div>
                </div>
            </div>
        </div>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        
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
        
        <script src="js/bootbox.min.js" type="text/javascript"></script>
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
