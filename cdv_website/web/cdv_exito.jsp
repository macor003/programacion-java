<%-- 
    Document   : cdv_exito
    Created on : 11/05/2015, 09:40:41 AM
    Author     : Ortegam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mensaje</title>
        <link href="cdv_imagenes/ico.ico" type="image/x-icon" rel="shortcut icon" />
        <link href="cdv_estilos/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="cdv_estilos/material-fullpalette.css" rel="stylesheet" type="text/css"/>
        <link href="cdv_estilos/roboto.css" rel="stylesheet" type="text/css"/>
        <link href="cdv_estilos/ripples.css" rel="stylesheet" type="text/css"/>
        
    </head>
    
    <%
        String mensaje1=""+request.getAttribute("mensaje1");
        String mensaje2=""+request.getAttribute("mensaje2");
        Object error=request.getAttribute("error");
    
    %>
    
    <script>
        
        function IniciarSesion(){
            location.href="index.jsp";
        }
        
    </script>
    
    <body>
        <div class="container">
            <img src="cdv_imagenes/cintillo.png" class="img-responsive" style="width: 100%;" alt=""/>
        </div>        
        <div class="navbar navbar-default"></div>
        
        
        <div class="container">                        
            <div class="jumbotron col-lg-10 col-lg-offset-1">
                <center>
                    
                    <%
                        if(error==null){
                    %>
                    
                    <h2 class="text-center text-danger"><%= mensaje1 %> <span class="glyphicon glyphicon-ok"></span></h2><br>
                    <p class="text-muted"> <%= mensaje2 %></p>
                    <button onclick="IniciarSesion()" class="btn btn-primary btn-lg">Iniciar Sesión</button>
                    
                    <%
                        }else{
                    %>
                    
                    <h2 class="text-center text-danger"><%= error %> <span class="glyphicon glyphicon-remove"></span></h2><br>
                    <button onclick="IniciarSesion()" class="btn btn-primary btn-lg">Volver</button>
                    
                    <%
                        }
                    %>
                    
                </center>
            </div>            
        </div>
       
        
        
        <footer class="navbar navbar-default navbar-fixed-bottom" role="navigation"><br>
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
