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
        <title>Mare Mare | Contactanos</title>        
        <link href="mare_estilos/estilos.css" rel="stylesheet" type="text/css"/>
        <link href="mare_estilos/bootstrap.css" rel="stylesheet" type="text/css"/>
        
        <script>            
            function GuardarMensaje(){ 

            var nombre = document.getElementById("lm_nombre").value;
            var apellido = document.getElementById("lm_apellido").value;
            var correo = document.getElementById("lm_correo").value;
            var mensaje = document.getElementById("lm_mensaje").value;
            var asunto = document.getElementById("lm_asunto").value;
            
             if(nombre === "" ){
                 bootbox.alert("Debe ingresar su Nombre");
                 return false;
             }else if( apellido ===""){
                 bootbox.alert("Debe ingresar su Apellido");
                 return false;
             }
             else if( correo ===""){
                 bootbox.alert("Debe ingresar su Correo Electronico");
                 return false;
             }else if( mensaje ===""){
                 bootbox.alert("Debe ingresar un Mensaje");
                 return false;
             }else if( asunto ==="su"){
                 bootbox.alert("Debe seleccionar un Asunto");
                 return false;
             }
             if(bootbox.confirm("¿Está seguro(a) que desea Guardar la información?", function(){
                 if(resultado === false){
                     bootbox.alert("¡Atención! Sus datos no seran guardados.");
                 }else{
                    document.f_forma.submit();
                 }
             })){                        
			
		}
        }
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
        
        <div class="container"><br>
            <div class="bb_alert alert alert-info" style="display: none;">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
  <strong>¡Cuidado!</strong> Es muy importante que leas este mensaje de alerta.
            </div>
            <div class="col-md-6">
                <img src="mare_imagenes/mare_mare.png" alt="" id="logoRegistro"/>
                <h3 style=" text-align: justify;">La comunicación con nuestros clientes contribuye a mejorar cada día nuestros productos y servicios para así cumplir nuestra principal meta: <strong>Tu satisfacción.</strong></h3>
            </div>
            <div class="col-md-6">
            <div class="jumbotron boxregistro">
                <form method="post" name="f_forma">                    
                    <h2 class="text-center" style="color: deeppink; margin-top: -20px; margin-bottom: 20px;">Contactanos</h2>
                    <input type="text" autocomplete="off" name="lm_nombre" id="lm_nombre" class="form-control nombre_usu" placeholder="Nombre">
                    <input type="text" autocomplete="off" name="lm_apellido" id="lm_apellido" class="form-control apellido_usu" placeholder="Apellido">
                    <input type="email" autocomplete="off" name="lm_correo" id="lm_correo" class="form-control correo_usu_new" placeholder="Correo Electrónico">
                    <select class="form-control" id="lm_asunto" style="margin-bottom: 5px;">
                        <option value="su">Asunto</option>
                        <option value="1">Agradecimiento</option>
                        <option value="2">Atención al cliente</option>
                        <option value="3">Información</option>
                        <option value="4">Informacion para envio de curriculum</option>              
                        <option value="5">Sugerencias</option>
                    </select>
                    <textarea class="form-control" autocomplete="off" id="lm_mensaje" placeholder="Escriba su mensaje aqui" style="margin-bottom: 5px;" rows="7"></textarea>
                    <input type="reset" value="Cancelar" class="btn btn-primary">                   
                    <input type="button" onclick="GuardarMensaje();" value="Enviar" class="btn btn-success" id="GuardarRegisto">
                </form>                
            </div>
            </div>
        </div>
       <footer class="navbar navbar-default navbar-fixed-bottom" role="navigation">
            <div class="container">
                <div class="navbar-text text-center">
                    <p class="text-center">&COPY;Todos los derechos reservados por Mare Mare</p>
                </div>
            </div>            
        </footer>
        <script src="mare_script/jquery-1.11.1.js" type="text/javascript"></script>
        <script src="mare_script/bootstrap.js" type="text/javascript"></script>
        <script src="mare_script/bootbox.js" type="text/javascript"></script>
    </body>
</html>