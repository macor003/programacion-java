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
        <title>Mare Mare | Bikinis</title>
        <link href="mare_estilos/estilos.css" rel="stylesheet" type="text/css"/>
        <link href="mare_estilos/bootstrap.css" rel="stylesheet" type="text/css"/>
        <script src="mare_script/jquery.fancybox-1.3.4.js" type="text/javascript"></script>
        <link href="mare_script/jquery.fancybox-1.3.4.css" rel="stylesheet" type="text/css" media="screen"/>
        <script src="mare_script/jquery.mousewheel-3.0.4.pack.js" type="text/javascript"></script>
        <script src="mare_script/jquery.fancybox-1.3.4.pack.js" type="text/javascript"></script>
        <script>
                    
            
        </script>
        <script>
            
   
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
        <br>
        <div class="jumbotron" style="background-color: #8d46b0; color: white;">
            <div class="container">
              <h1>BIKINIS</h1>  
            </div>
            
        </div>
        <div class="container">            
            <div class="row">
                <div class="col-lg-4">
                    <a class="thumbnail fancybox" rel="ligthbox" href="mare_imagenes/woman/small/foto_bikini1.jpg">
                        <img src="mare_imagenes/woman/small/foto_b-1.jpg" alt="" style="width: 200px; height: 266px;"/>
                        <div class="caption">
                            <h3 class="text-center ">Modelo B-1</h3>
                        </div>
                    </a>                
                </div>
                <div class="col-lg-4">
                    <div class="thumbnail">
                        <img src="mare_imagenes/woman/small/foto_b-2.jpg" alt="" style="width: 200px; height: 266px;"/>
                        <div class="caption">
                            <h3 class="text-center">Modelo B-2</h3>
                        </div>
                    </div>                
                </div>
                <div class="col-lg-4">
                    <div class="thumbnail">
                        <img src="mare_imagenes/woman/small/foto_b-3.jpg" alt="" style="width: 200px; height: 266px;"/>
                        <div class="caption">
                            <h3 class="text-center">Modelo B-3</h3>
                        </div>
                    </div>                
                </div>
            </div>
            <div class="row">
                <div class="col-lg-4">
                    <div class="thumbnail">
                        <img src="mare_imagenes/woman/small/foto_b-4.jpg" alt="" style="width: 200px; height: 266px;"/>
                        <div class="caption">
                            <h3 class="text-center ">Modelo B-4</h3>
                        </div>
                    </div>                
                </div>
                <div class="col-lg-4">
                    <div class="thumbnail">
                        <img src="mare_imagenes/woman/small/foto_b-5.jpg" alt="" style="width: 200px; height: 266px;"/>
                        <div class="caption">
                            <h3 class="text-center">Modelo B-5</h3>
                        </div>
                    </div>                
                </div>
                <div class="col-lg-4">
                    <div class="thumbnail">
                        <img src="mare_imagenes/woman/small/foto_b-6.jpg" alt="" style="width: 200px; height: 266px;"/>
                        <div class="caption">
                            <h3 class="text-center">Modelo B-6</h3>
                        </div>
                    </div>                
                </div>
                <div class="col-lg-4">
                    <div class="thumbnail">
                        <img src="mare_imagenes/woman/small/foto_b-7.jpg" alt="" style="width: 200px; height: 266px;"/>
                        <div class="caption">
                            <h3 class="text-center ">Modelo B-7</h3>
                        </div>
                    </div>                
                </div>
                <div class="col-lg-4">
                    <div class="thumbnail">
                        <img src="mare_imagenes/woman/small/foto_b-8.jpg" alt="" style="width: 200px; height: 266px;"/>
                        <div class="caption">
                            <h3 class="text-center">Modelo B-8</h3>
                        </div>
                    </div>                
                </div>
                <div class="col-lg-4">
                    <div class="thumbnail">
                        <img src="mare_imagenes/woman/small/foto_b-9.jpg" alt="" style="width: 200px; height: 266px;"/>
                        <div class="caption">
                            <h3 class="text-center">Modelo B-9</h3>
                        </div>
                    </div>                
                </div>
            </div>
        </div>
        

        <footer class="navbar navbar-default navbar-btn" role="navigation">
            <div class="container">
                <div class="navbar-text text-center">
                    <p class="text-center">&COPY;Todos los derechos reservados por Mare Mare</p>
                </div>
            </div>            
        </footer>
        <script src="mare_script/jquery-1.11.1.js" type="text/javascript"></script>
        <script src="mare_script/bootstrap.js" type="text/javascript"></script>
        
    </body>
</html>
