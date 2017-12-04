<%-- 
    Document   : cdv_carrito
    Created on : 05/05/2015, 02:31:51 PM
    Author     : Ortegam
--%>


<%@page import="com.venvidrio.cdv.utility.cdvUtility, com.venvidrio.cdv.loader.CarritoLoader"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Carrito</title>
        <script src="js/jquery-1.11.2.js" type="text/javascript"></script>
        <link href="cdv_imagenes/ico.ico" type="image/x-icon" rel="shortcut icon" />
        <link href="cdv_estilos/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="cdv_estilos/material-fullpalette.css" rel="stylesheet" type="text/css"/>
        <link href="cdv_estilos/roboto.css" rel="stylesheet" type="text/css"/>
        <link href="cdv_estilos/ripples.css" rel="stylesheet" type="text/css"/>
        <link href="cdv_estilos/snackbar.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <script>
            var options ={
                content: "Producto eliminado del carrito de compras",
                timeout: 5000
                
            };
            
            function Quitar(cod, pedido){
                var articulo = cod.substring(5);
                document.getElementById("lm_cod_pedido").value = pedido;
                document.getElementById("lm_cod_producto").value = articulo;
                document.f_forma.action="Controller?event=ELIMINAR_PRODUCTO";
                document.f_forma.submit;
            }
            
            function Quitar2(cod, pedido){                
                
                
                var codigo = document.getElementById(cod);
                var mover =  $('#'+cod);
                //localStorage.removeItem()
                //localStorage.removeItem(cod);
                            
                
                mover.addClass('removed-item')
                        .one('webkitAnimationEnd oanimationend msAnimationEnd animationend', function(e) {
                            mover.remove();
                            mover.removeClass('removed-item');
                        });
                        
                $.snackbar(options);
                setTimoout(function(){
                    $('.snackbar-content').fadeIn(2000);
                });
                
                document.getElementById("lm_cod_pedido").value = pedido;
                document.getElementById("lm_cod_producto").value = cod;
                //$("#snackbarid").snackbar("show");
            }
        </script>
        <style>
            .removed-item{
                -webkit-animation: removed-item-animation .8s cubic-bezier(.65,-0.02,.72,.29);
                animation: removed-item-animation .8s cubic-bezier(.65,-0.02,.72,.29);
            }
            
            @-webkit-keyframes removed-item-animation{
                0% {
                     opacity: 1;
                     transform: translateX(0);
                 }

                 30% {
                     opacity: 1;
                     transform: translateX(50px);
                 }

                 80% {
                     opacity: 0.3;
                     transform: translateX(-900px);
                 }

                 100% {
                     opacity: 0;
                     transform: translateX(-900px);
                 }
             }
            
        </style>
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
            
            CarritoLoader carrito = new CarritoLoader();
            
            String html_carrito=carrito.getProductosCarrito(rif);
            
            
        %>
        <div class="container">
            <img src="cdv_imagenes/cintillo.png" class="img-responsive" style="width: 100%;" alt=""/>
        </div>
        <%= barra%>
        
        
        <div class="container"> 
            <div class="jumbotron">
                <center>
                    <h1 class="text-danger"><i class="glyphicon glyphicon-shopping-cart"></i> Carrito de Compras</h1>
                </center>
                <span id="snackbarid" data-toggle=snackbar data-content="Producto eliminado del carrito de compras | Deshacer"></span>
            </div>
            <form name="f_forma" method="post">
                <input type="hidden" name="lm_cod_pedido" id="lm_cod_pedido" value="">
                <input type="hidden" name="lm_cod_producto" id="lm_cod_producto" value="">
                <%= html_carrito %> 
            </form>
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
        
        <script src="js/bootstrap.js" type="text/javascript"></script>
        <script src="js/material.min.js" type="text/javascript"></script>
        <script src="js/ripples.min.js" type="text/javascript"></script>
        <script src="js/snackbar.min.js" type="text/javascript"></script>
        <script>
            $(document).ready(function(){
                $.material.init();
            });
        </script>
    </body>
</html>
