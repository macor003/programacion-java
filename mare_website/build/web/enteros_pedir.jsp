<%-- 
    Document   : index
    Created on : 27/10/2014, 02:41:37 PM
    Author     : Mario
--%>

<%@page import="utility.mareUtility"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="mare_imagenes/logo.ico" type="image/x-icon" rel="shortcut icon" />
        <title>Mare Mare | Enteros</title>
        <link href="mare_estilos/estilos.css" rel="stylesheet" type="text/css"/>
        <link href="mare_estilos/bootstrap.css" rel="stylesheet" type="text/css"/>
        
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
        <div class="jumbotron" style="background-color: tomato; color: white;">
            <div class="container">
              <h1>ENTEROS</h1>  
            </div>            
        </div>
        <div class="container"> 
            
            <div class="row">
                <div class="col-lg-4">
                    <div class="thumbnail">
                        <img src="mare_imagenes/woman/small/foto_e-1.jpg" alt="" style="width: 200px; height: 266px;"/>
                        <div class="caption">
                            <h3 class="text-center ">Modelo E-1</h3>
                            <h3 class="label label-danger center-block">No Disponible</h3>
                        </div>
                    </div>                
                </div>
                <div class="col-lg-4">
                    <div class="thumbnail">
                        <img src="mare_imagenes/woman/small/foto_e-2.jpg" alt="" style="width: 200px; height: 266px;"/>
                        <div class="caption">
                            <h3 class="text-center">Modelo E-2</h3>
                            <h3 class="label label-danger center-block">No Disponible</h3>
                        </div>
                    </div>                
                </div>
                <div class="col-lg-4">
                    <div class="thumbnail">
                        <img src="mare_imagenes/woman/small/foto_e-3.jpg" alt="" style="width: 200px; height: 266px;"/>
                        <div class="caption">
                            <h3 class="text-center">Modelo E-3</h3>                            
                            <h3 class="label label-danger center-block">No Disponible</h3>
                        </div>
                    </div>                
                </div>
            </div>
            <div class="row">
                <div class="col-lg-4">
                    <div class="thumbnail">
                        <img src="mare_imagenes/woman/small/foto_e-4.jpg" alt="" style="width: 200px; height: 266px;"/>
                        <div class="caption">
                            <h3 class="text-center ">Modelo E-4</h3>
                            <h3 class="label label-danger center-block">No Disponible</h3>
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
