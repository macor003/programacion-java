<%-- 
    Document   : index
    Created on : 11/09/2015, 02:23:54 PM
    Author     : Ortegam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Venvidrio C.A.</title>
        <link href="cnt_estilos/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="cnt_imagenes/icono.ico" type="image/x-icon" rel="shortcut icon" />
        <link href="cnt_estilos/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <script src="cnt_scripts/jquery-1.11.1.js" type="text/javascript"></script>
        <script src="cnt_scripts/Dropzonejs.js" type="text/javascript"></script>
    </head>
    <body style=" background-image: url('cnt_imagenes/Background.png');">
        
        <div class="container">
            <img src="cnt_imagenes/cintillo.png" alt="" class="img-responsive" style="width: 100%;"/>
            
            <nav class="nav navbar navbar-inverse" style="border-radius: 0px;" >
                                
            </nav>
            
            
            
            <div class="col-lg-8 col-lg-offset-2" style="border-radius: 0px; background-color: white; box-shadow: 0 8px 17px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);">
                <h2 class="text-center">Cargar Noticia</h2>
                <form role="form" id="f_forma" action="Controller?event=CARGAR_NOTICIA" name="f_forma" enctype="multiform/form-data" method="post">
                    <div class="form-group">
                        <label for="sel1">Título:</label>
                        <input type="text" class="form-control" name="lm_titulo" placeholder="Título">
                    </div>
                    <div class="form-group">
                        <label for="sel1">Fecha:</label>
                        <div class='input-group date' id='date'>
                            <input type='date' class="form-control" name="lm_fecha" />
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sel1">Autor</label>
                        <input type="text" class="form-control" name="lm_autor" placeholder="Autor">
                    </div>
                    <div class="form-group">
                        <label for="sel1">Imagen:</label>
                        <input type="file" class="form-control" name="lm_imagen">
                    </div>
                    <div class="form-group" style="margin-top: 5px;">
                        <label for="mensaje">Descripción:</label>
                        <textarea class="form-control" id="lm_mensaje" name="lm_mensaje" rows="5" cols="20" style="resize: none;"></textarea>                        
                    </div>
                    <div class="form-group" style="margin-top: 5px;">
                        <button class="btn btn-success " type="button" onclick="Enviar();"><span class="glyphicon glyphicon-send"></span> Enviar</button>
                        <button class="btn btn-danger " type="button" onclick="limpiar();"><span class="glyphicon glyphicon-trash"></span> Limpiar</button>
                    </div>                        
                </form>                
            </div>
            <br><br><br>
            
         </div>   
            <footer class="navbar navbar-default navbar-fixed-bottom" style="border-radius: 0px;">                                
                <div class="col-lg-12 col-md-12 col-xs-12">                        
                    <p class="text-center">Dirección General de Tecnología © 2015 <br>
                        Venezolana del Vidrio C.A. (VENVIDRIO)<br>
                        G-20009820-1</p>
                </div>           
            </footer>
        
        
        
        <script>
            $(function () {
                $('#date').datetimepicker();
            });
            $(document).ready(function(){
                $('[data-toggle="tooltip"]').tooltip();   
            });
        </script>
    
    <script src="cnt_scripts/bootstrap.js" type="text/javascript"></script>
    </body>
</html>
