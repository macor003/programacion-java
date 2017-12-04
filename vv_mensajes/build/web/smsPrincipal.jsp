<%-- 
    Document   : sms_Principal
    Created on : 28/07/2015, 03:30:16 PM
    Author     : ortegam
--%>

<%@page import="com.venvidrio.sms.loader.TSmsMensajesLoader"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="vv_imagenes/icono.ico" type="image/x-icon" rel="shortcut icon" />
        <title>SMS Venvidrio</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="vv_estilos/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="vv_estilos/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <script src="vv_scripts/jquery-1.11.1.js" type="text/javascript"></script>
        <script>
            function limpiar(){
                document.getElementById("f_forma").reset();
                $('#Contador').html("140");
                $('#Contador').css({color:"Black"});
            }
            var limite = 140;

            $(document).ready(function()
            {

                // cada vez que se deja de presionar una tecla
                $("#lm_mensaje").keyup(function(e)
                {

                    // obtenemos el texto que está escrito en el textarea
                    var box = $(this).val();

                    // obtenemos cuántos caracteres quedan
                    var resta = limite - box.length;

                    if(box.length >= "130"){
                        $('#Contador').css({color:"RED"});
                    }else{
                        $('#Contador').css({color:"Black"});
                    }
                    // si aún no se llegó al límite
                    if(box.length <= limite){
                        // modificamos el texto que muestra la cantidad de caracteres que restan
                        $('#Contador').html(resta);            
                    }
                    else // si se llegó al límite no permitimos ingresar más caracteres
                    {
                        // evitamos que ingrese más caracteres
                        e.preventDefault();
                    }               
                });
            });
            
            function Enviar(){
                var directorio = document.getElementById("lm_directorio").value;
                var mensaje = document.getElementById("lm_mensaje").value;
                
                if(directorio==="su"){
                    bootbox.alert("Debe seleccionar un directorio")
                    return false;
                }else if(mensaje===""){
                    bootbox.alert("Debe ingresar el mensaje")
                    return false;
                }else if(bootbox.confirm("¿Está seguro(a) que desea enviar el SMS?", function(resultado){
                 if(resultado === false){
                     bootbox.alert("¡Atención! mensaje no enviado.");
                 }else{                    
                    document.f_forma.action="Controller?event=GUARDAR_MENSAJE";
                    document.f_forma.submit();
                 }
                 
             })){                        
			
		}
            }
            
        </script>
    </head>
    <body>
        <%
            String nombre=(String) session.getAttribute("nombre_usuario");
            TSmsMensajesLoader sms = new TSmsMensajesLoader();
            
            String dir = sms.getDirectorio();
        %>
        <nav class="navbar navbar-inverse navbar-fixed-top" style="border-radius: 0px;">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                  <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                  </button>
                  <a class="navbar-brand" href="#"></a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                  <ul class="nav navbar-nav">
                      <li class="active"><a href="Controller?event=ENVIAR_MENSAJE"><span class="glyphicon glyphicon-send"></span> Enviar SMS<span class="sr-only">(current)</span></a></li>
                      <!--li><a href="#"><span class="glyphicon glyphicon-list-alt"></span> Lista de SMS Enviados</a></li-->
                  </ul>
                  <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                      <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-user"></span> <%= nombre%><span class="caret"></span></a>
                      <ul class="dropdown-menu">
                          <li><a href="smsCerrarSesion.jsp"><span class="glyphicon glyphicon-off"></span> Cerrar sesión</a></li>
                      </ul>
                    </li>
                  </ul>
                </div><!-- /.navbar-collapse -->
              </div><!-- /.container-fluid -->
            
        </nav><br><br><br><br>
        <img src="vv_imagenes/venvidrio.png" alt="" class="center-block img-responsive" style="width: 200px;"/>
        <div class="container">
            <h1 class="text-center"><span class="glyphicon glyphicon-comment" style="font-size: 60px;"></span></h1>
            <h1 class="text-center">Redactar SMS</h1><br>
            <div class="col-lg-3 col-lg-offset-4 col-md-5 col-md-offset-3 col-xs-7 col-xs-offset-2" style="left: 4%;">
                <form role="form" id="f_forma" name="f_forma" method="post">
                    <div class="form-group">
                        <label for="sel1">Directorio:</label>
                        <%= dir %>
                    </div>
                    <div class="form-group" style="margin-top: 5px;">
                        <label for="mensaje">Mensaje:</label>
                        <textarea class="form-control" id="lm_mensaje" name="lm_mensaje" rows="5" cols="20" style="resize: none;" maxlength="140"></textarea>
                        <label id="Contador">140</label><label>/140</label>
                    </div>
                    <div class="form-group" style="margin-top: 5px;">
                        <button class="btn btn-success btn-block" type="button" onclick="Enviar();"><span class="glyphicon glyphicon-send"></span> Enviar</button>
                        <button class="btn btn-danger btn-block" type="button" onclick="limpiar();"><span class="glyphicon glyphicon-trash"></span> Limpiar</button>
                    </div>                        
                </form>                
            </div>
        </div><br><br><br><br><br>
        
        <footer class="navbar navbar-default navbar-btn" style="border-radius: 0px;">
            <div class="container">               
                <div class="col-lg-12">
                    <p class="text-center">Gerencia de Tecnología &COPY; 2015 <br>
                        Venezolana del Vidrio C.A. (VENVIDRIO)<br>
                        G-20009820-1</p>
                </div>
                    
            </div>            
        </footer>
        
        
        <script src="vv_scripts/bootstrap.js" type="text/javascript"></script>
        <script src="vv_scripts/bootbox.min.js" type="text/javascript"></script>
    </body>
</html>
