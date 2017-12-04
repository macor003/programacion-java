<%-- 
    Document   : DetallesNoticia
    Created on : 02/09/2015, 03:05:14 PM
    Author     : Ortegam
--%>
<%@page import="com.venvidrio.intranet.utility.intranetUtility"%>
<%@page import="com.venvidrio.intranet.loader.NoticiaLoader"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Venvidrio C.A.</title>
        <link href="vv_estilos/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="vv_imagenes/icono.ico" type="image/x-icon" rel="shortcut icon" />
        <link href="vv_estilos/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <script src="vv_scripts/jquery-1.11.1.js" type="text/javascript"></script>
    </head>
    <body style=" background-image: url('vv_imagenes/Background.png');">
        <%
            String var = request.getParameter("codigo");
            NoticiaLoader noticia = new NoticiaLoader();
            String detalle = noticia.getDetalleNoticia(var);
            intranetUtility utility = new intranetUtility();
            String barraNav = utility.mostrarBarra();
        %>
        <div class="container">
            <img src="vv_imagenes/cintillo.png" alt="" class="img-responsive" style="width: 100%;"/>
            <!--Barra de navegación-->    
                <%= barraNav %>
            
            <div class="col-lg-8 col-lg-offset-2" style="border-radius: 0px; background-color: white; box-shadow: 0 8px 17px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);">
                <%= detalle%>
            </div><br><br><br>
            
            
            <footer class="navbar navbar-default navbar-fixed-btn" style="border-radius: 0px;">
                <div class="col-lg-2 col-md-2 col-xs-6">
                    <a href="http://www.movilnet.com.ve/" target="_blank" data-toggle="tooltip" data-placement="top" title="Movilnet"><img src="vv_imagenes/movilnet.png" alt="" class="img-responsive center-block" style="width: 150px; padding: 25px; "/></a>
                </div>
                <div class="col-lg-2 col-md-2 col-xs-6">
                    <a href="http://www.saime.gob.ve/" target="_blank" data-toggle="tooltip" data-placement="top" title="Servicio Administrativo de Identificación, Migración y Extranjería"><img src="vv_imagenes/saime.png" alt="" class=" img-responsive center-block" style="width: 70px; padding: 10px; "/></a>
                </div>
                <div class="col-lg-2 col-md-2 col-xs-6">
                    <a href="http://www.bt.gob.ve/" target="_blank" data-toggle="tooltip" data-placement="top" title="Banco del Tesoro"><img src="vv_imagenes/banco_del_tesoro.png" alt="" class=" img-responsive center-block" style="width: 100px; padding: 5px; "/></a>
                </div>
                <div class="col-lg-2 col-md-2 col-xs-6">
                    <a href="http://www.mppi.gob.ve/" target="_blank" data-toggle="tooltip" data-placement="top" title="MPP para Industrias"><img src="vv_imagenes/GBV_Negro.png" alt="" class=" img-responsive center-block" style="width: 100px; padding: 5px; "/></a>
                </div>
                <div class="col-lg-2 col-md-2 col-xs-6">
                    <a href="https://www.provincial.com/" target="_blank" data-toggle="tooltip" data-placement="top" title="BBVA Provincial"><img src="vv_imagenes/banco_provincial.png" alt="" class=" img-responsive center-block" style="width: 150px; padding: 15px; "/></a>
                </div>
                <div class="col-lg-2 col-md-2 col-xs-6">
                    <a href="http://www.intt.gob.ve/" target="_blank" data-toggle="tooltip" data-placement="top" title="Instituto Nacional de Transporte Terreste"><img src="vv_imagenes/intt.png" alt="" class=" img-responsive center-block" style="width: 70px; padding: 10px; "/></a>
                </div>                 
                <div class="col-lg-12 col-md-12 col-xs-12">                        
                    <p class="text-center">Dirección General de Tecnología © 2015 <br>
                        Venezolana del Vidrio C.A. (VENVIDRIO)<br>
                        G-20009820-1</p>
                </div>           
            </footer>
        </div>
        
        
        <script>
            $(document).ready(function(){
                $('[data-toggle="tooltip"]').tooltip();   
            });
        </script>
    
    <script src="vv_scripts/bootstrap.js" type="text/javascript"></script>
    </body>
</html>
