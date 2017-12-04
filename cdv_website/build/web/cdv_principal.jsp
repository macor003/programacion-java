<%-- 
    Document   : cdv_registro
    Created on : 28/04/2015, 03:29:27 PM
    Author     : Ortegam
--%>

<%@page import="com.venvidrio.cdv.utility.cdvUtility"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Inicio</title>
        <link href="cdv_imagenes/ico.ico" type="image/x-icon" rel="shortcut icon" />
        <link href="cdv_estilos/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="cdv_estilos/material-fullpalette.css" rel="stylesheet" type="text/css"/>
        <link href="cdv_estilos/roboto.css" rel="stylesheet" type="text/css"/>
        <link href="cdv_estilos/ripples.css" rel="stylesheet" type="text/css"/>
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
                    <h1 class="text-danger">Bienvenidos</h1>
                </center>
            </div><br><br>
            
            <div class="col-lg-6">
                <img src="cdv_imagenes/casita_del_vidrio.png" alt="" class="center-block" style="width: 65%;"/>
            </div>
            <div class="col-lg-6">
                <h1 class="text-danger text-center">La Casita del Vidrio</h1>
                <h3 class="text-justify">
                    Este programa de la bandera de la gestión social de la
                    empresa estatal Venezolana del Vidrio C.A., ofrece la
                    venta al detal de envases de vidrio (desde una hasta cien
                    cajas mensuales), con sus respectivas tapas, a precio
                    justos y sin intermediarios, a los pequeños y medianos
                    productores, artesanos, cooperativas y público en general.
                    La Casita del vidrio permite acercar el vidrio al pueblo
                    venezolano, porque es una alternativa sostenible de
                    envasado, compuesto de ingredientes naturales, sinónimo de
                    calidad pureza y salud.
                </h3>
            </div>
            <br>
            
            <center>
                <h2 class="text-danger">"EL VIDRIO ES VENEZOLANO Y AHORA <br> ESTÁ MAS CERCA DE SU PUEBLO"</h2>
            </center>
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
