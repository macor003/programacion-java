<%-- 
    Document   : cdv_config_exito
    Created on : 20/07/2015, 09:08:44 AM
    Author     : Ortegam
--%>

<%@page import="com.venvidrio.cdv.loader.CarritoLoader"%>
<%@page import="com.venvidrio.cdv.loader.UsuariosLoader"%>
<%@page import="com.venvidrio.cdv.utility.cdvUtility"%>
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
        Object error=request.getAttribute("error");
    
    %>
    
    <script>
        
        function IniciarSesion(){
            location.href="cdv_carrito.jsp";
        }
        
    </script>
    
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
            <div class="jumbotron col-lg-10 col-lg-offset-1">
                <center>
                    
                    <%
                        if(error==null){
                    %>
                    
                    <h2 class="text-center text-danger"><%= mensaje1 %> <span class="glyphicon glyphicon-ok"></span></h2><br>
                    <button onclick="IniciarSesion()" class="btn btn-primary btn-lg">Regresar al Carrito</button>
                    
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
