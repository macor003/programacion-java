<%-- 
    Document   : nosotros
    Created on : 04/12/2014, 12:58:18 AM
    Author     : Mario
--%>

<%@page import="utility.mareUtility"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="mare_imagenes/logo.ico" type="image/x-icon" rel="shortcut icon" />
        <title>Mare Mare | Nosotros</title>
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
        <%= barra%><br><br>
        <div class="container">             
            <div class="row col-sm-6">
                <img src="mare_imagenes/mare.png" style="width: 350px; height: 130px; margin-left: 70px;" alt=""/>                
                <p style="font-size: 17px; text-align: justify; width: 500px;">Es una empresa Venezolana, dedicada al Diseño, confección y comercialización de la Marca registrada Mare Mare Trajes de Baño , que nace legalmente un 1ero de Octubre de 1.996 en la ciudad de Valencia- Venezuela.<br>
                    Y decimos legalmente, porque desde mucho antes ya estaba en los sueños y metas de sus propietarias, Betzaid Mendez y Ma. Angelica Antibero, cuando aun eran estudiantes de la carrera de Diseño de Modas y sentían la pasión de llevar a estas prendas de vestir el calor y color del Caribe y así ofrecerlo transformado para el resto del Mundo.<br>
                    En poco tiempo la marca Mare Mare Trajes de Baño se convirtió en sinónimo de calidad, creatividad y comodidad, ofreciendo un producto respaldado no solo en su corte, que busca la perfección de la silueta, sino además en la altísima calidad y exclusividad de sus telas e insumos.<br>
                    Todos los años ofrecemos una nueva colección, que busca interpretar las necesidades y tendencias del momento. Para ello te presenta su catalogo que sirve no solo de vitrina visual sino de herramienta si quieres pertenecer a nuestra lista de Distribuidores exclusivos. Ahora con nuestra pagina Web te invitamos a conocer nuestra línea de productos y nuestros puntos de ventas y así comprobaras la sensación de usar Mare Mare Trajes de Baño.</p>
            </div><br>
            <div class="col-sm-6">
                <img src="mare_imagenes/woman/small/foto_b-4.jpg" style="width: 400px; height: 550px;" class="img-responsive" alt=""/>
            </div>
            
        </div>
        <hr>
        <div class="jumbotron" style="background-color: #54FFF2; color: black;">
            <div class="container">
                <h1>Visión</h1>
                <p style="text-align: justify;">Ser reconocida mundialmente como la mejor empresa dedicada al diseño y confección de Trajes de baño, así como la comercialización de los mejores productos relacionados con el ramo, orientados al logro de altos niveles de calidad y competitividad.</p>
            </div>
        </div>
        <div class="jumbotron" style="background-color: #ED6EFF; color: black;">
            <div class="container">
                <h1>Misión</h1>
                <p style="text-align: justify;"><strong></strong>Somos una empresa dedicada a la confección de trajes de baño y ropa playera, que satisface las necesidades de nuestros clientes mediante el logro de la calidad y competitividad, que garantizan su crecimiento sostenido con una responsabilidad social y ética para con los propietarios, recurso humano, clientes, proveedores y entorno.</p>
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
