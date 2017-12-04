<%-- 
    Document   : cdv_panel_control
    Created on : 06/05/2015, 06:19:06 PM
    Author     : ortegam
--%>

<%@page import="com.venvidrio.cdv.loader.CarritoLoader"%>
<%@page import="com.venvidrio.cdv.utility.cdvUtility"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Carrito</title>
        <link href="cdv_imagenes/ico.ico" type="image/x-icon" rel="shortcut icon" />
        <link href="cdv_estilos/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="cdv_estilos/material-fullpalette.css" rel="stylesheet" type="text/css"/>
        <link href="cdv_estilos/roboto.css" rel="stylesheet" type="text/css"/>
        <link href="cdv_estilos/ripples.css" rel="stylesheet" type="text/css"/>
        
        <script src="js/jquery-1.11.2.js" type="text/javascript"></script>
        <script src="js/jquery.dataTables.js" type="text/javascript"></script>
        <script src="js/dataTables.bootstrap.js" type="text/javascript"></script>
        <link href="cdv_estilos/dataTables.bootstrap.css" rel="stylesheet" type="text/css"/>
        
        <script>
            $(document).ready(function() {
                $('#DataTable').dataTable( {
                } );
            } );
            function GuardarDespacho(codigo){
                alert("Codigo: "+codigo);
                document.getElementById("lm_codigo_pedido").value = codigo;
                document.f_forma.action="Controller?event=ACTUALIZAR_DESPACHO";
                document.f_forma.submit();
            }
        </script>
    </head>
    <body>
        <% 
            cdvUtility util = new cdvUtility();
            CarritoLoader carrito = new CarritoLoader();
            String[] detalles = carrito.getPedidosClientes();
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
                    <h1 class="text-danger"><i class="glyphicon glyphicon-file"></i> Pedidos Clientes</h1>
                </center>
            </div>            
             
            <!--------------------------AQUI VA EL MENU DEL ADMINISTRADOR---------------------------------------->
            <div class="col-lg-3">           
                <div class="panel panel-default" style="background-color: white; box-shadow: 0 8px 17px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);">
                    <div class="panel-heading">Menú Administrador</div>
                    <div class="panel-body">
                        <ul class="nav nav-pills nav-stacked">
                            <li class="active"><a href="#">Pedidos</a></li>
                            <!--li><a href="#">Productos</a></li-->
                        </ul>
                    </div>
                    <div class="clearfix visible-lg"></div>
                </div>
            </div>
            
            
            <!--------------------------AQUI VA EL DETALLE DE LA PAGINA DEL ADMINISTRADOR---------------------------------------->
            <div class="col-lg-9">
                <div class="jumbotron">
                    <table id="DataTable" class="table table-striped table-hover" >
                        <thead>
                            <th>Código</th>
                            <th>A Nombre de</th>
                            <th>Fecha</th>
                            <th>Estatus</th>
                            <th></th>
                        </thead>
                        <tbody>
                            <%= detalles[0] %>
                        </tbody>
                        
                    </table>
                </div>
            </div>
                
       </div>           
            <!---------------ESTA ES LA VENTANA EMERGENTE PARA EDITAR LA CANTIDAD  ---------------------->
            <form name="f_forma" id="f_forma" method="post">
                <input type='hidden' value='' id='lm_codigo_pedido' name='lm_codigo_pedido'>
                <%= detalles[1] %>
            </form>
            
        
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        
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
        <script>
            $(document).ready(function(){
                $.material.init();
            });
        </script>
    </body>
</html>
