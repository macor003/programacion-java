<%-- 
    Document   : index
    Created on : 05-may-2014, 15:40:32
    Author     : Ortegam
--%>

<%@page import="com.venvidrio.sgt.loader.TSgtVerTipoRequerimientoLoader"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HelpDesk | Verificar</title>
        <link href="sgt_imagenes/icono2.ico" type="image/x-icon" rel="shortcut icon" />
        <link rel="stylesheet" type="text/css" href="sgt_estilos/estilos2.css" />
        <script src="sgt_script/ajax.js" type="text/javascript" ></script>       
        
    </head>
    <body style="margin: 0 auto;">
        
        <% 
            TSgtVerTipoRequerimientoLoader ver = new TSgtVerTipoRequerimientoLoader();
            String planta = ver.verPlanta();
        %>
        <%@ page language="java" isErrorPage="false" isThreadSafe="true" autoFlush="true" buffer="8kb"   %>
        <%@ page import="java.lang.*,java.util.*"  %>
        <%@ page import="com.venvidrio.sgt.utility.sgtUtility" %>
        <SCRIPT language="JavaScript">
            function cargaVerificacionXML(){
                
                if(!fsj_validaSelect("lm_planta","una planta")){return false;}
                if(!fsj_validaInput2("lm_ficha","una ficha")){return false;}
                
                var cod = document.getElementById("planta").value;
                var ficha = document.getElementById("ficha").value;
                //alert("Este es el codigo de la planta: "+cod+" y esta la ficha: " +ficha);
                if(window.XMLHttpRequest){
                    xmlhttpverificar = new XMLHttpRequest();
                    xmlhttpverificar.onreadystatechange = processReqChangeVerificar;
                    xmlhttpverificar.open("GET","sgt_AjaxVerificarUsuario.jsp?COD_PLANTA="+cod+"&FICHA="+ficha,true);
                    xmlhttpverificar.send(null);
                }                
                    else if (window.ActiveXObject){// code for IE6, IE5	
                    xmlhttpverificar = new ActiveXObject("Microsoft.XMLHTTP");
                    xmlhttpverificar.onreadystatechange = processReqChangeVerificar;
                    xmlhttpverificar.open("GET","sgt_AjaxVerificarUsuario.jsp?COD_PLANTA="+cod+"&FICHA"+ficha,true);
                    xmlhttpverificar.send();

                  
                  }

            function processReqChangeVerificar(){
                  var verificado = document.getElementById("verificado");

                  if(xmlhttpverificar.readyState === 4){
                          verificado.innerHTML = xmlhttpverificar.responseText;
                  }
                  
                  }
            }
            function enviarDatos(){
                document.f_forma.action ="Controller?event=REGISTRAR_USUARIO";
                document.f_forma.submit();                                
            }
            function EnviarCorreo(){
                var usuario = document.getElementById("lm_usuario").value;
                var correo = document.getElementById("lm_correo").value;
                //alert("Se enviaran los datos de "+usuario+" a este correo " + correo);
                
                if(window.confirm("Se enviará información confidencial. ¿Está seguro que este es tu correo? "+correo)){  
                    document.f_forma.action ="Controller?event=CORREO_VERIFICACION";
                    document.f_forma.submit();
                } 
            }
            function EnviarCorreoActualizar(){
                var usuario = document.getElementById("lm_usuario").value;
                var correo = document.getElementById("lm_correo").value;
                //alert("Se enviaran los datos de "+usuario+" a este correo " + correo);
                
                if(window.confirm("Se enviará información confidencial. ¿Está seguro que este es tu correo? "+correo)){  
                    document.f_forma.action ="Controller?event=ACTUALIZAR_USUARIO";
                    document.f_forma.submit();
                } 
            }
            function EnviarCorreoCau(){
                var usuario = document.getElementById("lm_usuario").value;
                var correo = document.getElementById("lm_correo").value;
                //alert("Se enviaran los datos de "+usuario+" a este correo " + correo);
                
                if(window.confirm("Llama al 2073 para solicitar el usuario")){  
                    document.f_forma.action ="Controller?event=CORREO_VERIFICACION_CAU";
                    document.f_forma.submit();
                } 
            }
            function EnviarCorreoCauActualizar(){
                var usuario = document.getElementById("lm_usuario").value;
                var correo = document.getElementById("lm_correo").value;
                //alert("Se enviaran los datos de "+usuario+" a este correo " + correo);
                
                if(window.confirm("Llama al 2073 para solicitar el usuario ")){  
                    document.f_forma.action ="Controller?event=ACTUALIZAR_USUARIO_CAU";
                    document.f_forma.submit();
                } 
            }
            function IniciarSesion(){
                document.f_forma.action ="Controller?event=INICIO_SESION";
                document.f_forma.submit();
            }
        </SCRIPT> 
        <img src="sgt_imagenes/background.png" alt="" class="fondo">
        <img src="sgt_imagenes/cintillo.png" alt="" class="cintillo">
        <div class="BarraVino"></div>
        
        <h1 id="titulo" style="top: 17%;">Sistema de Gestión de Tecnología</h1>
        <h3 style="margin-left: 5%; top: 25%;">Verificar usuario</h3>
        <%--img src="sgt_imagenes/venvidrio.png" id="logo" alt=""--%>
        
        
        <section id="centro" style="top: 35%;">
            <form name="f_forma" method="post">
                <table>
                    <tr >
                        <td style="padding: 1px;"><label>Seleciona tu planta:</label></td>
                        <td>
                            <select style="width: 154px; height: 30px;" id="planta" name="lm_planta"> 
                                <option value="su">Seleccione</option>
                                <option value="02">Los Guayos</option>
                                <option value="05">Valera</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><label>Indica tu ficha:</label></td>
                        <td><input type="text" name="lm_ficha" placeholder="Ficha" id="ficha" style="width: 140px; height: 25px; padding-left:10px;"></td>
                    </tr>
                </table>
                <div id="verificado">
                    
                </div>
                <aside style="margin-top: -50px;">
                    <input style="margin-left: -80px;" type="button" name="acceder" id="acceder" value="Verificar" onclick="cargaVerificacionXML();">
                    <input style="margin-right: -150px;" type="reset" value="Borrar" onclick="javascript:location.reload();" id="acceder">
                </aside><br>
                <input style="margin-left: 15px;" type="button" id="acceder" value="Iniciar sesión" onclick="IniciarSesion();">
            </form>            
        </section>        
        
    </body>
</html>
