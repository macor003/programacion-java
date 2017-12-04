<%-- 
    Document   : inicio
    Created on : 31/08/2015, 03:08:05 PM
    Author     : Ortegam
--%>

<%@page import="com.venvidrio.intranet.utility.intranetUtility"%>
<%@page import="com.venvidrio.intranet.loader.NoticiaLoader"%>
<%@page import="com.venvidrio.intranet.loader.PersonalLoader"%>
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
        
        <!--CSS para el temporizador-->
        <link rel="stylesheet" href="css/flipclock.css" />
        <script>
            function enviar_noticia(cod){
                
                document.f_forma.action="Controller?event=DETALLE_NOTICIA";
                document.f_forma.codigo.value=cod;
                //alert("Codigo: "+document.getElementById("codigo").value);
                document.f_forma.submit();
            }
        </script>
    </head>
    <style>
        .thumbnail{
            overflow:hidden;
            cursor: pointer;
        }
        .thumbnail .img_noticia{
            -webkit-transition:all .9s ease; /* Safari y Chrome */
            -moz-transition:all .9s ease; /* Firefox */
            -o-transition:all .9s ease; /* IE 9 */
            -ms-transition:all .9s ease; /* Opera */
            width:100%;
            z-index: 1;
        }
        .thumbnail:hover .img_noticia{
            -webkit-transform:scale(1.25);
            -moz-transform:scale(1.25);
            -ms-transform:scale(1.25);
            -o-transform:scale(1.25);
            transform:scale(1.25);
        }
        .thumbnail .caption .col-lg-12{
            -webkit-transition:all .9s ease; /* Safari y Chrome */
            -moz-transition:all .9s ease; /* Firefox */
            -o-transition:all .9s ease; /* IE 9 */
            -ms-transition:all .9s ease; /* Opera */
        }
        .thumbnail .caption .col-lg-12{
            display: block; 
            background-color: white;
            color: black;
            padding: 9px;
        }
        .thumbnail:hover .caption .col-lg-12{
            background-color: #5F0000;
            color: white;
                
        }
        .thumbnail .caption .col-lg-12 a{
            color: black;
        }
        .thumbnail:hover .caption .col-lg-12 a{
            -webkit-transition:all .9s ease; /* Safari y Chrome */
            -moz-transition:all .9s ease; /* Firefox */
            -o-transition:all .9s ease; /* IE 9 */
            -ms-transition:all .9s ease; /* Opera */
            color: white;
                
        }
    </style>
    <body style=" background-image: url('vv_imagenes/Background.png');">
        <% 
            PersonalLoader perso = new PersonalLoader();
            NoticiaLoader news = new NoticiaLoader();
            String cumple = perso.verPersonalCumple();
            String años = perso.verPersonalServicio();
            intranetUtility utility = new intranetUtility();
            String barraNav = utility.mostrarBarra();
            String noticia = news.getNoticia();
            
            
        %>
        <div class="container">            
            <div class="row">
                <img src="vv_imagenes/cintillo.png" alt="" class="img-responsive" style="width: 100%;"/>
                <!--Barra de navegación-->    
                <%= barraNav %>
                <!-- Carrusel -->
                <!-- Wrapper for slides -->
                <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                    <!-- Indicators -->
                    <ol class="carousel-indicators">
                      <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                      <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                      <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                    </ol>

                    <!-- Wrapper for slides -->
                    <div class=" carousel-inner" role="listbox">
                      <div class="item active">
                          <img src="vv_imagenes/vv1.jpg" alt="" class="img-responsive" style="width: 100%; height: 50%;"/>
                        <div class="carousel-caption">
                        </div>
                      </div>
                      <div class="item">
                          <img src="vv_imagenes/vv2.jpg" alt="" class="img-responsive" style="width: 100%; height: 50%;"/>
                        <div class="carousel-caption">
                        </div>
                      </div>
                        <div class="item">
                          <img src="vv_imagenes/vv3.jpg" alt="" class="img-responsive" style="width: 100%; height: 50%;"/>
                        <div class="carousel-caption">
                        </div>
                      </div>
                    </div>

                    <!-- Controls -->
                    <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                      <span class="sr-only">Previous</span>                      
                    </a>
                    <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                      <span class="sr-only">Next</span>
                    </a>
                  </div><br>
            <!-- Fin Carrusel -->
        
                <div class="row">        
                    <div class="col-lg-3 col-md-4 col-xs-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="text-center"><span class="fa fa-link"></span>  Accesos Directos</h4>
                            </div>
                            <div class="panel-body">                                                          
                                <!--p><span class="fa fa-newspaper-o"></span><a class="btn btn-link" target="_blank" data-toggle="tooltip" href="http://oidvhome/OIDVHome/Noticias/notic_noticias.jsp"> Noticias</a></p-->
                                <p><span class="fa fa-book"></span><a class="btn btn-link" target="_blank" data-toggle="tooltip"  href="http://oidvhome/oidv_guia_telefonica/index_2011.jsp"> Directorio de Venvidrio</a></p>
                                <p><span class="fa fa-inbox"></span><a class="btn btn-link" target="_blank" data-toggle="tooltip" href="http://mail.venvidrio.com.ve/zimbra/" > Correo</a></p>
                                <p><span class="fa fa-laptop"></span><a class="btn btn-link" target="_blank" data-toggle="tooltip" data-placement="right" title="CRM" href="http://10.183.9.150:8085/wijsp/"> CRM</a></p>
                                <p><span class="fa fa-laptop"></span><a class="btn btn-link" target="_blank" data-toggle="tooltip" data-placement="right" title="Sistema de Gestión de Tecnología" href="http://10.183.9.20:8080/sgt_website/index.jsp"> SGT</a></p>
                                <p><span class="fa fa-laptop"></span><a class="btn btn-link" target="_blank" data-toggle="tooltip" data-placement="right" title="Sistema de Acciones Correctivas y Preventivas" href="http://10.183.9.12/sacp_website/calidad_sacp.jsp" > SACP</a></p>
                                <p><span class="fa fa-laptop"></span><a class="btn btn-link" target="_blank" data-toggle="tooltip" data-placement="right" title="Sistema de Indicadores de Gestión" href="http://10.183.9.20:8080/sig_website/"> SIGES</a></p>
                                <p><span class="fa fa-laptop"></span><a class="btn btn-link" target="_blank" data-toggle="tooltip" data-placement="right" title="Sistema Integrado de Personal" href="http://10.183.9.12/sip_website/index.jsp"> SIP</a></p>
                                <p><span class="fa fa-laptop"></span><a class="btn btn-link" target="_blank"  href="http://10.183.9.20:8080/SPPDisenoWeb/index.jsp"> SPP Decorado</a></p>
                                <p><span class="fa fa-line-chart"></span><a class="btn btn-link" target="_blank"  href="http://10.183.9.20:8080/sig_website/sigReportesIndicadorPublico.jsp"> Resultados de Indicadores</a></p>
                                <!--p><span class="fa fa-bar-chart"></span><a class="btn btn-link" target="_blank"  href="http://oidvhome/OIDVHome/Construccion.htm"> Reporte de Causas no <br>Conformidad</a></p-->
                                <p><span class="fa fa-database"></span><a class="btn btn-link" target="_blank"  href="http://10.183.9.12/bic_website/index.jsp"> BIC by Production Line</a></p>
                                <p><span class="fa fa-database"></span><a class="btn btn-link" target="_blank" href="http://10.183.9.12/wfc_website/index.jsp"> Waterfall Chart by Plant</a></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-8 col-xs-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="text-center"><span class="fa fa-birthday-cake"></span> CUMPLEAÑEROS</h4>
                            </div>
                            <div class="panel-body">
                                <%= cumple %>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="text-center"><span class="fa fa-clock-o"></span> AÑOS DE SERVICIO</h4>
                            </div>
                            <div class="panel-body">
                                <%= años %>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-8 col-xs-12">
                        <div class="thumbnail">
                            <a href="Controller?event=PUNTO_Y_CIRCULO"><img src="vv_imagenes/Logo_Puntoycirculo.jpg" alt="" style="width: 180px;"/></a>
                        </div>
                        <div class="thumbnail">
                            <a href="vv_pdf/Preguntas_9001-2008.pdf" target="_blank"><img src="vv_imagenes/calidad.png" alt="" style="width: 280px;"/></a>
                        </div>
                    </div> 

                </div>
            </div>
        </div>                            
        <div class="container" style="background-color: #5F0000;  padding: 1px;  border-radius: 10px 10px 0px 0px;"> 
            <h1 class="text-center" style="color: white;">Noticias</h1>
        </div><br>                                            
        <div class="container">                    
            <div class="row">
                <form method="post" name="f_forma"> 
                    <input type="hidden" id="codigo" name="codigo" value="">
                    <%= noticia %>   
                </form>                                     
            </div>
                
            
            <div class="modal fade Logo_venvidrio" role="dialog" aria-labelledby="myLargeModalLabel">
                <div class="modal-dialog" >
                  <div class="modal-content">
                    <div class="modal-header">
                      <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                      <h4 class="modal-title text-center" id="gridSystemModalLabel">Logo Venvidrio</h4>
                    </div>
                    <div class="modal-body">
                      <div class="container-fluid">
                          <img src="vv_imagenes/logo_venvidrio.png" alt="" class="center-block" style="width: 400px;"/>
                      </div>
                    </div>
                    <div class="modal-footer">
                        <a href="vv_imagenes/logo_venvidrio.png" download="Logo_Venvidrio" class="btn btn-primary"><span class="fa fa-download"></span> Descargar</a>
                    </div>
                  </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->
            <div class="modal fade politicas" role="dialog" aria-labelledby="myLargeModalLabel">
                <div class="modal-dialog" >
                  <div class="modal-content">
                    <div class="modal-header">
                      <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                      <h4 class="modal-title text-center" id="gridSystemModalLabel">Política Integral</h4>
                    </div>
                    <div class="modal-body">
                      <div class="container-fluid">
                          <img src="vv_imagenes/Politica_Integral.jpg" alt="" class="center-block" style="width: 400px;"/>
                      </div>
                    </div>
                    <div class="modal-footer">
                        <a href="vv_imagenes/Politica_Integral.jpg" download="Politica_integral" class="btn btn-primary"><span class="fa fa-download"></span> Descargar</a>
                    </div>
                  </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
                </div><!-- /.modal -->
                <div class="modal fade objetivos" role="dialog" aria-labelledby="myLargeModalLabel">
                <div class="modal-dialog" >
                  <div class="modal-content">
                    <div class="modal-header">
                      <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                      <h4 class="modal-title text-center" id="gridSystemModalLabel">Objetivos</h4>
                    </div>
                    <div class="modal-body">
                      <div class="container-fluid">
                          <img src="vv_imagenes/Objetivos.png" alt="" class="center-block" style="width: 400px;"/>
                      </div>
                    </div>
                    <div class="modal-footer">
                        <a href="vv_imagenes/Objetivos.png" download="objetivos" class="btn btn-primary"><span class="fa fa-download"></span> Descargar</a>
                    </div>
                  </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
                </div><!-- /.modal -->
            </div>
            <!--Modal Al inciar Document-->
            <!--div class="modal fade Principal" role="dialog" aria-labelledby="myLargeModalLabel">
                <div class="modal-dialog modal-lg" >
                  <div class="modal-content">
                    <div class="modal-header">
                      <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                      <h4 class="modal-title text-center" id="gridSystemModalLabel" style="color:white;">.</h4>
                    </div>
                    <div class="modal-body">
                      <div class="container-fluid">
                          <img src="vv_imagenes/vota.jpg" alt="" class="center-block" style="width: 800px;"/>
                          <div id="mi-reloj" style="margin-left:90px; margin-top: 50px;"></div>
                      </div>
                    </div>
                    <div class="modal-footer">
                        
                    </div>
                  </div><!-- /.modal-content -->
                <!--/div><!-- /.modal-dialog -->
            <!--/div--><!-- /.modal -->
            
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
    
   <!--SCRIPTS para el temporizador--> 
    <script src="js/flipclock/libs/prefixfree.min.js"></script>
    <script src="js/flipclock/flipclock.min.js"></script>
    <script src="js/mi-script.js"></script>
    </body>
</html>
