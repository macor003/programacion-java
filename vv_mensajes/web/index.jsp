<%-- 
    Document   : index
    Created on : 28/07/2015, 03:31:55 PM
    Author     : ortegam
--%>

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
            function Limpiar(){
                document.getElementById("f_forma").reset();
            }
            function fjs_aceptar() {
                var dml = document.f_forma;

                // Se asignan los valores de la forma "f_forma" a variables locales
                var ls_usuario    = dml.tf_usuario.value;
                var ls_contrasena = dml.tf_contrasena.value;

                // Si el usuario no introdujo un nombre,se emite un mensaje de error y se retorna falso


                dml.submit();
              }
            function InicioSesionXML(){
                //alert("INGRESASTE ESTO: "+correo);
                var usuario = document.getElementById("ls_usuario").value();
                var contraseña = document.getElementById("ls_contraseña").value();
                alert("Usuario: "+usuario+" Contraseña: "+ contraseña);
                if(window.XMLHttpRequest){
                    xmlhttpiniciosesion = new XMLHttpRequest();
                    xmlhttpiniciosesion.onreadystatechange = processReqChangeNotificacion;
                    xmlhttpiniciosesion.open("GET","AjaxValidarInicioSesion.jsp?USUARIO="+usuario+"&PASS"+contraseña,true);
                    xmlhttpiniciosesion.send(null);
                }
                
                else if (window.ActiveXObject){// code for IE6, IE5
//	
                    xmlhttpiniciosesion = new ActiveXObject("Microsoft.XMLHTTP");
                    xmlhttpiniciosesion.onreadystatechange = processReqChangeNotificacion;
                    xmlhttpiniciosesion.open("GET","AjaxValidarInicioSesion.jsp?USUARIO="+usuario+"&PASS"+contraseña,true);
                    xmlhttpiniciosesion.send();

                }
                  
            }

            function processReqChangeNotificacion(){
                var notificacion = document.getElementById("notificacion");

                if(xmlhttpiniciosesion.readyState === 4){
                    notificacion.innerHTML = xmlhttpiniciosesion.responseText;
                          
                }
                  
            }
        </script>
    </head>
    <body>
        <nav class="navbar navbar-inverse" style="border-radius: 0px;">
            
        </nav>
        <img src="vv_imagenes/venvidrio.png" alt="" class="center-block img-responsive" style="width: 280px; margin-top: 50px;"/>
        <div class="container">
            <h1 class="text-center"><span class="glyphicon glyphicon-phone" style="font-size: 60px;"></span></h1>
            <h1 class="text-center">SMS</h1><br>
            <div class="col-lg-3 col-lg-offset-4 col-md-5 col-md-offset-3 col-xs-7 col-xs-offset-2" style="left: 4%;">
                <form role="form" name="f_forma" id="f_forma" onSubmit="return fjs_aceptar();" action="smsLogin" method="post">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user fa-fw"></i></span>
                        <input class="form-control" type="text" id="ls_usuario" name="tf_usuario" placeholder="Usuario">
                    </div>
                    <div class="input-group" style="margin-top: 5px;">
                        <span class="input-group-addon"><i class="fa fa-lock fa-fw"></i></span>
                        <input class="form-control" type="password" id="ls_contraseña" name="tf_contrasena" placeholder="Contraseña">
                    </div>
                    <div class="form-group" style="margin-top: 5px;">
                        <button type="submit" class="btn btn-success btn-block"  onclick="fjs_aceptar();" href="sms_Principal.jsp">Ingresar</button>
                        <button class="btn btn-danger btn-block" type="button" onclick="Limpiar();">Cancelar</button>
                    </div>                        
                </form>                
            </div>
        </div><br><br><br><br>
        
        <footer class="navbar navbar-default navbar-fixed-btn" style="border-radius: 0px;">
            <div class="container">               
                <div class="col-lg-12">
                    <p class="text-center">Dirección General de Tecnología &COPY; 2015 <br>
                        Venezolana del Vidrio C.A. (VENVIDRIO)<br>
                        G-20009820-1</p>
                </div>
                    
            </div>            
        </footer>
        
        <script src="vv_scripts/bootstrap.js" type="text/javascript"></script>
    </body>
</html>
