<%-- 
    Document   : cdv_productos
    Created on : 30/04/2015, 09:39:51 AM
    Author     : Ortegam
--%>

<%@page import="com.venvidrio.cdv.utility.cdvUtility, com.venvidrio.cdv.loader.ProductosLoader"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Productos</title>
        
        <link href="cdv_imagenes/ico.ico" type="image/x-icon" rel="shortcut icon" />
        <link href="cdv_estilos/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="cdv_estilos/material-fullpalette.css" rel="stylesheet" type="text/css"/>
        <link href="cdv_estilos/roboto.css" rel="stylesheet" type="text/css"/>
        <link href="cdv_estilos/ripples.css" rel="stylesheet" type="text/css"/>
        <script src="js/bootbox.min.js" type="text/javascript"></script>
        <script src="js/ajax.js" type="text/javascript"></script>
        <script src="js/prefixfree.min.js" type="text/javascript"></script>
        <link href="cdv_estilos/jquery-ui.min.css" rel="stylesheet" type="text/css"/>
        <script>
            function AgregarCarrito(nombre){
                var nom_usuario=document.getElementById("lm_nom_usuario").value;
                if(nom_usuario === ''){
                    location.href="index.jsp";
                }else{
                    var valor=document.getElementsByName(nombre)[0].value;
                    var id_producto=document.getElementsByName(nombre)[0].name.substring(14);
                    AgregarProductoXML(id_producto, valor); 
                    
                }
            }
        </script>
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
            
            ProductosLoader productos=new ProductosLoader();
            String html_productos=productos.getProductosHTML();
            
        %>
        
        <div class="container">
            <img src="cdv_imagenes/cintillo.png" class="img-responsive" style="width: 100%;" alt=""/>
        </div>
        <%= barra %>
        
        <%= html_productos %>
       
        
        
        <input type="hidden" name="lm_nom_usuario" id="lm_nom_usuario" value="<%= nom_usuario %>" />
        <footer class="navbar navbar-default navbar-btn" role="navigation">
            <br>
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
        <script src="js/jquery-ui.min.js" type="text/javascript"></script>
        <script>
            $('.agregar').on('click', function () {
                var cart = $('.carrito');
                var imgtodrag = $(this).parent('.item').find("img").eq(0);
                if (imgtodrag) {
                    var imgclone = imgtodrag.clone()
                        .offset({
                        top: imgtodrag.offset().top,
                        left: imgtodrag.offset().left
                    })
                        .css({
                        'opacity': '0.5',
                            'position': 'absolute',
                            'height': '150px',
                            'width': '150px',
                            'z-index': '100'
                    })
                        .appendTo($('body'))
                        .animate({
                        'top': cart.offset().top + 10,
                            'left': cart.offset().left + 10,
                            'width': 75,
                            'height': 75
                    }, 1000, 'easeInOutExpo');
                    
                    
                    imgclone.animate({
                        'width': 0,
                            'height': 0
                    }, function () {
                        $(this).detach();
                    });
                }
            });           
            
            
        </script>
        <script>
            $(document).ready(function(){
                $.material.init();
            });
        </script>
    </body>
</html>

