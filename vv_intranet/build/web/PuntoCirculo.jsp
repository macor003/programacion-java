<%-- 
    Document   : PuntoCirculo
    Created on : 04/09/2015, 10:40:13 AM
    Author     : Ortegam
--%>

<%@page import="com.venvidrio.intranet.utility.intranetUtility"%>
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
            intranetUtility utility = new intranetUtility();
            String barraNav = utility.mostrarBarra();
        
        %>
        <div class="container">
            <div class="row">
                <img src="vv_imagenes/cintillo.png" alt="" class="img-responsive" style="width: 100%;"/>
                <!--Barra de navegación-->    
                <%= barraNav %>
            </div>
        </div>
        <div class="container" style="background-color: white; box-shadow: 0 8px 17px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);">
            <div class="col-lg-7">
                <h1 class="text-center"><u>Punto y Círculo</u></h1>
               <p class="text-justify">
                   &nbsp;&nbsp;&nbsp;&nbsp;El Punto y Círculo es un modelo de desarrollo socio productivo, que implica el uso de espacios
                   de producción que se articulan con un núcleo principal(que es productivo), consolidando así los esfuerzos del Gobierno Nacional con relación
                   a laparticipación popular y el empoderamiento por parte de las comunidades y de los trabajadores organizados.<br>

                   &nbsp;&nbsp;&nbsp;&nbsp;El núcleo productivo, en este caso Venvidrio, debe vincularse con las comunidades vecinas a sus áreas operacionales,
                   esto es posible a través del impulso de la participación de actores sociales como los consejos comunales.<br>

                   &nbsp;&nbsp;&nbsp;&nbsp;El trabajador que se comienza a desarrollar en conjunto empresa y Poder Popular, se ve enriquecido con el intercambio de conocimiento,
                   que es posible gracias a la práctica de actividades como la Esculea en la Fabrica, alli trabajadores, trabajadoras y miembros de las comunidades reciben conocimientos
                   que les permite desarrollar proyectos a favor de sus comunidades, este intercambio de saberes permite generar, rescatar y conservar conocimientos y valores que permitirán 
                   al pueblo venezolano avanzar en la contrucción del Socialismo del Siglo XXI.<br>

                   &nbsp;&nbsp;&nbsp;&nbsp;La fábrica además de ser un ente productor de un bien o servicio pasa a ser un sitio donde se buscan soluciones a los problemas locales,
                   un espacio para el conocimiento, un lugar para el desarrollo humano, es decir, pasa a ser un espacio que promueve el desarrollo sostenible local.<br>

                   &nbsp;&nbsp;&nbsp;&nbsp;El Punto y Círculo incluye también una linea de acción donde las empresas grandes y robustas deben apalancar a las empresas comunitarias de manera
                   que se promueva el desarrollo integral del espacio productivo, esto se puede lograr a tráves de ayuda financiera, orientación legal, soporte tecnológico, asesoría en la elaboración de proyectos y capacitación, entre otros. 
               </p>
            </div><br><br><br>
            <div class="col-lg-5">
                <img src="vv_imagenes/Logo_Puntoycirculo.jpg" alt="" class=" center-block img-circle img-responsive" style="width: 350px;"/>                    
            </div>  

            <div class="col-lg-12">
                <h3><span class="fa fa-angle-right"></span> Visión</h3>
                <p class="text-justify">
                    &nbsp;&nbsp;Ser una de las vías socio productivas que permita a Venezuela alcanzar la libertad y soberanía,
                    a través de un modelo de desarrollo social y humano que garantice la suprema felicidad social del pueblo.
                </p>
                <h3><span class="fa fa-angle-right"></span> Misión</h3>
                <p class="text-justify">
                    &nbsp;&nbsp;Promover, articular, desarrollar estrategias y mecanismos de acción en conjunto con la comunida
                    para dar impulso a las contrucción del nuevo modelo productivo socialista.
                </p>
                <h3><span class="fa fa-angle-right"></span> Objetivos</h3>
                <p class="text-justify">
                    &nbsp;&nbsp;&nbsp;&nbsp;<span class="fa fa-check"></span> Cambiar el modelo productivo, implementando nuevas relaciones de producción.<br>
                    &nbsp;&nbsp;&nbsp;&nbsp;<span class="fa fa-check"></span> Incorporar como elemento vital el desarrollo humano de la empresa, para alcanzar la mayor suma de felicidad posible de sus trabajadores y trabajadoras.<br>
                    &nbsp;&nbsp;&nbsp;&nbsp;<span class="fa fa-check"></span> Implementar una estrecha vinculación armónica con la comunidad y todo el entorno, para dar impulso al desarrollo económico mediante la conformación de empresas socio-productivas.
                </p>
            <br><br>
            </div>
        </div>
        <br><br> 
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
        <script>
            $(document).ready(function(){
                $('[data-toggle="tooltip"]').tooltip();   
            });
        </script>
    
    <script src="vv_scripts/bootstrap.js" type="text/javascript"></script>
    </body>
</html>
