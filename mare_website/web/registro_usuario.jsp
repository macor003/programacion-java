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
        <title>Mare Mare | Registro</title>        
        <link href="mare_estilos/estilos.css" rel="stylesheet" type="text/css"/>
        <link href="mare_estilos/bootstrap.css" rel="stylesheet" type="text/css"/>
        
        <script>            
            function GuardarRegistro(){
            var nombre = document.getElementById("lm_nombre").value;
            var apellido = document.getElementById("lm_apellido").value;
            var correo = document.getElementById("lm_correo").value;
            var contraseña = document.getElementById("lm_contrasena").value;
            var sexo = document.getElementById("lm_sexo").value;
            
             if(nombre === ""){
                 bootbox.alert("Debe indicar su Nombre");
                 return false;
             }else if( apellido===""){
                 bootbox.alert("Debe indicar su Apellido");
                 return false;
             }else if( correo===""){
                 bootbox.alert("Debe indicar su Correo");
                 return false;
             }else if( contraseña===""){
                 bootbox.alert("Debe ingresar una Contraseña");
                 return false;
             }else if( sexo==="su"){
                 bootbox.alert("Debe indicar su Sexo");
                 return false;
             }
             if(bootbox.confirm("¿Está seguro(a) que desea Guardar la información?", function(resultado){
                 if(resultado === false){
                     bootbox.alert("¡Atención! Sus datos no seran guardados.");
                 }else{
                    document.f_forma.submit();
                 }
                 
             })){                        
			
		}
            }
            function VerificarXML(correo){
                //alert("INGRESASTE ESTO: "+correo);
                if(window.XMLHttpRequest){
                    xmlhttparea = new XMLHttpRequest();
                    xmlhttparea.onreadystatechange = processReqChangeArea;
                    xmlhttparea.open("GET","AjaxValidarCorreoRegistro.jsp?CORREO="+correo,true);
                    xmlhttparea.send(null);
                }
                
                else if (window.ActiveXObject){// code for IE6, IE5
//	
                    xmlhttparea = new ActiveXObject("Microsoft.XMLHTTP");
                    xmlhttparea.onreadystatechange = processReqChangeArea;
                    xmlhttparea.open("GET","AjaxValidarCorreoRegistro.jsp?CORREO="+correo,true);
                    xmlhttparea.send();

                }
                  
            }

            function processReqChangeArea(){
                var area = document.getElementById("existe");

                if(xmlhttparea.readyState === 4){
                    area.innerHTML = xmlhttparea.responseText;
                          
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
                <h3 style=" text-align: justify;">Registrate para que disfrutes de las novedades que te ofrece Mare Mare Trajes de Baño. Si ya tienes una cuenta puedes <a href="inicio_sesion.jsp">Iniciar sesión</a></h3>
            </div>
            <div class="col-md-6">
            <div class="jumbotron boxregistro">
                <form method="post" action="Controller?event=GUARDAR_REGISTRO_USUARIO" name="f_forma">                    
                    <h2 class="text-center" style="color: deeppink; margin-top: -20px; margin-bottom: 20px;">Regístrate</h2>
                    <input type="text" autocomplete="off" name="lm_nombre" id="lm_nombre" class="form-control nombre_usu" placeholder="Nombre">
                    <input type="text" autocomplete="off" name="lm_apellido" id="lm_apellido" class="form-control apellido_usu" placeholder="Apellido">
                    <input type="email" autocomplete="off"  name="lm_correo" id="lm_correo" class="form-control correo_usu_new" placeholder="Correo Electrónico" onkeyup="">
                    <input type="password" name="lm_contrasena" id="lm_contrasena" class="form-control pass_usu_new" placeholder="Contraseña">
                    <select name="lm_sexo" id="lm_sexo" class="form-control">
                        <option value="su">Selecciona sexo</option>
                        <option value="01">Masculino</option>
                        <option value="02">Femenimo</option>
                    </select>
                    <label>Fecha de nacimiento:</label><input type="date" placeholder="Fecha nacimiento" name="lm_fecha_nac" id="lm_fecha_nac" class="form-control"><br>
                    <input type="button" onclick="GuardarRegistro();" value="Registrarte" class="btn btn-success btn-block" id="GuardarRegisto">
                </form>
                <hr>                
                <a class="btn btn-link btn-block" style="margin-top: -10px;" href="inicio_sesion.jsp">¿Ya tienes una cuenta?</a>
                <div id="existe" >
                    
                </div>
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