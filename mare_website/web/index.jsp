<%-- 
    Document   : index
    Created on : 27/10/2014, 02:41:37 PM
    Author     : Mario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="mare_imagenes/logo.ico" type="image/x-icon" rel="shortcut icon" />
        <title>Mare Mare | Home</title>
        <link href="mare_estilos/estilos.css" rel="stylesheet" type="text/css"/>
        <link href="mare_estilos/bootstrap.css" rel="stylesheet" type="text/css"/>
        
        <script>
            $(function () {
              $('#redSocial').tooltip();
            });
        </script>
    </head>
    
    <body>
        <%@ page import= "java.lang.*, utility.mareUtility"%>
        <%
        mareUtility util = new mareUtility(); 
	String barra="";
        String nombre = (String) session.getAttribute("nombre");
        String apellido =(String) session.getAttribute("apellido");
	String rol=(String) session.getAttribute("rol");
        if(rol==null || rol.equals(null) || rol.equals("") || rol.equals("null")){
        barra = util.mostrarBarraBasica();
	}else{
            barra = util.mostrarBarra(rol, nombre.toUpperCase(), apellido.toUpperCase());
        }
	
	
            
        %>
        <%--Barra de navegacion--%>   
        <%= barra%>
              
        <div class="jumbotron boxbienvenida">
            <div class="container">                
                <h1 style="color:white;">Bienvenidos!</h1>
                <p style="color: white;">Te damos la bienvenida a nuestra nueva pagina, cambiamos para que tengas una mejor experiencia.<br>
                    Registrate y conoce nuestras novedades, para que puedas enterarte de todo lo que Mare Mare te puede ofrecer.</p> 
                <a href="Controller?event=REGISTRAR_USUARIO_CLIENTE"><button class="btn btn-info btn-lg" >Regístrate</button></a>&nbsp;&nbsp;
                <a href="Controller?event=INICIO_SESION"><button type="submit" class="btn btn-info btn-lg">Inicia sesión</button></a>
            </div>
        </div>

        <div class="container">            
            <div class="row">
                <div class="list-group">
                    <div class="col-lg-4 text-center">
                        <div class="thumbnail">
                            <img src="mare_imagenes/woman/small/foto_t-1.jpg" alt="" style="width: 200px; height: 266px;"/>
                            <div class="caption">
                                <h3>Tankinis</h3> 
                                <a href="Controller?event=TANKINIS" class="btn btn-warning">Ver más »</a>
                            </div>
                        </div>                
                    </div>
                    <div class="col-lg-4 text-center">
                        <div class="thumbnail">
                            <img src="mare_imagenes/woman/small/foto_b-1.jpg" alt="" style="width: 200px; height: 266px;"/>
                            <div class="caption">
                                <h3>Bikinis</h3>
                                <a href="Controller?event=BIKINIS" class="btn btn-warning">Ver más »</a>
                            </div>
                        </div>                
                    </div>
                    <div class="col-lg-4 text-center">
                        <div class="thumbnail">
                            <img src="mare_imagenes/woman/small/foto_e-1.jpg" alt="" style="width: 200px; height: 266px;"/>
                            <div class="caption">
                                <h3>Enteros</h3>
                                <a href="Controller?event=ENTEROS" class="btn btn-warning">Ver más »</a>
                            </div>
                        </div>                
                    </div>
                    <div class="col-lg-4 text-center">
                        <h2>Nosotros</h2>
                        <p>Es una empresa Venezolana, dedicada al Diseño, confección y comercialización de la Marca registrada Mare Mare Trajes de Baño , que nace legalmente un 1ero de Octubre de 1.996 en la ciudad de Valencia- Venezuela.</p>
                        <a href="nosotros.jsp" class="btn btn-primary">Conoce más »</a>
                    </div>
                    <div class="col-lg-4 text-center">
                        <h2>Redes Sociales</h2>
                        <div class="col-xs-3">
                            <a><img class="img-responsive" style="width: 55px; height: 55px;" src="mare_imagenes/facebook.png"></a>
                        </div>
                        <div class="col-xs-3">
                            <a id="redSocial" data-toggle="tooltip" data-placement="bottom" title="Tooltip on bottom"><img class="img-responsive" style="width: 55px; height: 55px;" src="mare_imagenes/twitter.png"></a>
                        </div>
                    </div>
                    <div class="col-lg-4 text-center">
                        <h2>Contactanos</h2>
                        <div class="col-xs-2">
                            <a><img class="img-responsive" style="width: 35px; height: 40px;" src="mare_imagenes/location-pointer.png"></a>
                        </div>
                        <div class="col-xs-9 text-justify">
                            <p>Av. Bolívar Norte, Edif. Hotel Le Paris, Local 6, PB.
                                Valencia, Venezuela.<br>
                                Telf.: (+58)0241-824.6012</p>
                            <p>C.C. Via Veneto, Nivel Florencia, Local 7-F.
                                Valencia, Venezuela.<br>
                                Telf.: (+58)0241-843.3059</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <footer class="navbar navbar-default navbar-btn">
            <div class="container text-center">
                <div class="navbar-text text-center">
                    <p class="text-center">&COPY;Todos los derechos reservados por Mare Mare</p>
                </div>
            </div>            
        </footer>
        <script src="mare_script/jquery-1.11.1.js" type="text/javascript"></script>
        <script src="mare_script/bootstrap.js" type="text/javascript"></script>
    </body>
</html>
