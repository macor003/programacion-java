<%-- 
    Document   : cdv_registro
    Created on : 28/04/2015, 03:29:27 PM
    Author     : Ortegam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Registro de Usuario</title>
        <link href="cdv_imagenes/ico.ico" type="image/x-icon" rel="shortcut icon" />
        <link href="cdv_estilos/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="cdv_estilos/material-fullpalette.css" rel="stylesheet" type="text/css"/>
        <link href="cdv_estilos/roboto.css" rel="stylesheet" type="text/css"/>
        <link href="cdv_estilos/ripples.css" rel="stylesheet" type="text/css"/>
    </head>
    
    <script>


    /*bootbox.dialog({
      message: "I am a custom dialog",
      title: "Custom title",
      buttons: {
        main: {
          label: "Click ME!",
          className: "btn-primary",
          callback: function() {
            Example.show("Primary button");
          }
        }
      }
    });*/
    
    function ValidarCampos(){
        location.href="cdv_principal.jsp";
    }
    
    function ValidarCampos(){
        document.f_forma.submit();
    }


</script>
    
    <body>
        <div class="container">
            <img src="cdv_imagenes/cintillo.png" class="img-responsive" style="width: 100%;" alt=""/>
        </div>        
        <div class="navbar navbar-default"></div><br><br>
        
        
        <div class="container">           
            <div class="col-lg-6">
                <h1 class="text-danger text-center">Bienvenido al registro de usuario de la casita del vidrio.</h1>
                <img src="cdv_imagenes/venvidrio.png" style="width: 210px; height: 150px;" class="center-block" alt=""/><br>
                <p class="text-justify" style="font-size: 20px;">Si eres emprendedor y requieres <span class="text-danger">envases de vidrio</span> para tus productos artesanales, te ofrecemos: Envases de alta calidad, en la cantidad que requieras, y al precio justo que tú necesitas. Registrate para adquirir tus envases y si ya posees usuario puedes <a href="index.jsp">Iniciar Sesión</a></p>
            </div>
            <div class="jumbotron col-lg-6">
                <form class="form-horizontal" action="Controller?event=REGISTRO_USUARIO" name="f_forma" method="post">
                    <fieldset>
                        <legend><h2 class="text- text-center">Formulario de registro</h2></legend>
                        
                        <div class="icon-preview"><i class="glyphicon glyphicon-user text-center col-lg-12" style="font-size: 60px;"></i> </div><br><br>
                        <h4 class="text-center text-success">Regístrate</h4>
                        <div class="form-group">
                            <label for="inputRif" class="col-lg-4 control-label">Rif: </label>
                            <div class="col-lg-7">
                                <input type="text" class="form-control" id="inputRif" placeholder="Rif" name="lm_rif">
                                <span class="help-block text-danger">* Campo obligatorio</span>
                            </div>
                        </div>
                        <!--<div class="form-group">
                            <label for="inputPassword" class="col-lg-4 control-label">Contraseña</label>
                            <div class="col-lg-7">
                                <input type="password" class="form-control" id="inputPassword" placeholder="Contraseña" name="lm_password">
                                <span class="help-block text-danger">* Campo obligatorio</span>
                            </div>
                        </div>-->
                        <div class="form-group">
                            <label for="inputContraseña" class="col-lg-4 control-label">Nombre Empresa/Persona: </label>
                            <div class="col-lg-7">
                                <input type="text" class="form-control" id="inputEmail" placeholder="Nombre" name="lm_nombre">
                                <span class="help-block text-danger">* Campo obligatorio</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputContraseña" class="col-lg-4 control-label">Correo: </label>
                            <div class="col-lg-7">
                                <input type="text" class="form-control" id="inputEmail" placeholder="Correo" name="lm_correo">
                                <span class="help-block text-danger">* Campo obligatorio</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputEmail" class="col-lg-4 control-label">Dirreción</label>
                            <div class="col-lg-7">
                                <textarea class="form-control" rows="3" id="textArea" placeholder="Dirección fiscal" name="lm_direccion"></textarea>
                                <span class="help-block text-danger">* Campo obligatorio</span>
                            </div>
                        </div>
                        
                       <!-- <div class="form-group">
                            <label for="inputEmail" class="col-lg-4 control-label">Option:</label>
                            <div class="col-lg-7">
                                <div class="radio radio-primary">
                                    <label>
                                        <input type="radio" name="optionsRadios" id="optionsRadios1" value="V" checked="">
                                        V
                                    </label>
                                </div>
                                <div class="radio radio-primary">
                                    <label>
                                        <input type="radio" name="optionsRadios" id="optionsRadios1" value="J" >
                                        J
                                    </label>
                                </div>
                                <div class="radio radio-primary">
                                    <label>
                                        <input type="radio" name="optionsRadios" id="optionsRadios1" value="G" >
                                        G
                                    </label>
                                </div>
                                <input type="text" value="" class="form-control" placeholder="RIF" min="10" max="10">
                                <span class="help-block text-danger">* Campo obligatorio</span>
                            </div>
                        </div>   -->
                        
                        <div class="form-group">
                            <div class="col-lg-10 col-lg-offset-5">
                                <button type="button" class="btn btn-success" onclick="ValidarCampos();">Enviar</button>
                                <button type="button" class="btn btn-danger" onclick="Volver();">Cancelar</button>
                            </div>
                        </div>
                    </fieldset>
                </form>
            </div>
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
        <script src="js/jquery-1.11.2.js" type="text/javascript"></script>
        <script src="js/bootstrap.js" type="text/javascript"></script>
        <script src="js/material.min.js" type="text/javascript"></script>
        <script src="js/ripples.min.js" type="text/javascript"></script>
        <script src="js/bootbox.min.js" type="text/javascript"></script>
        <script>
            $(document).ready(function(){
                $.material.init();
            });
        </script>
    </body>
</html>
