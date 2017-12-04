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
        <title>Mare Mare | Tankinis</title>
        <link href="mare_estilos/estilos.css" rel="stylesheet" type="text/css"/>
        <link href="mare_estilos/bootstrap.css" rel="stylesheet" type="text/css"/>
        
        <script>
            function agregarCarrito(modelo){
                document.getElementById("modelo").value = modelo;
                var talla_b = document.getElementById("talla_b-"+modelo).value;
                var talla_c = document.getElementById("talla_c-"+modelo).value;
                var cant = document.getElementById("cantidad-"+modelo).value;
                var model = document.getElementById("modelo").value;
                alert("Modelo: "+model+" cantidad: "+cant+", Talla busto: "+talla_b+", Talla cadera: "+talla_c);
                if(bootbox.confirm("¿Está seguro(a) que desea Guardar la información?", function(resultado){
                 if(resultado === false){
                     bootbox.alert("¡Atención! No se guardo el producto.");
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
        <%= barra%>  
        <br>
        <div class="jumbotron" style="background-color: deeppink; color: white;">
            <div class="container">
              <h1>TANKINIS</h1>  
            </div>
            
        </div>
        <div class="container">   
            <form name="f_forma" method="post" action="Controller?event=AGREGAR_AL_CARRITO">
            <input type="hidden" name="modelo" id="modelo">
            <div class="row">
                <div class="col-lg-4">
                    <div class="thumbnail">
                        <img src="mare_imagenes/woman/small/foto_t-1.jpg" alt="" style="width: 200px; height: 266px;"/>
                        <div class="caption">
                            <h3 class="text-center ">Modelo T-1</h3>
                            Cantidad:<input name="cantidad-t-1" id="cantidad-t-1" type="number" class="form-control" min="0" max="15" step="1" value="1"><br>
                            Talla busto:<select class="form-control" id="talla_b-t-1" name="talla_b-t-1">
                                <option>Talla</option>
                                <option value="S">S</option>
                                <option value="M">M</option>
                                <option value="L">L</option>
                                <option value="XL">XL</option>
                            </select><br>
                            Talla cadera:<select class="form-control" id="talla_c-t-1" name="talla_c-t-1">
                                <option>Talla</option>
                                <option value="S">S</option>
                                <option value="M">M</option>
                                <option value="L">L</option>
                                <option value="XL">XL</option>
                            </select><br>
                            <input type="button" onclick="agregarCarrito(this.id);" id="t-1" class="btn btn-primary" value="Agregar a pedido">                        
                        </div>
                    </div>                
                </div>
                <div class="col-lg-4">
                    <div class="thumbnail">
                        <img src="mare_imagenes/woman/small/foto_t-2.jpg" alt="" style="width: 200px; height: 266px;"/>
                        <div class="caption">
                            <h3 class="text-center ">Modelo T-1</h3>
                            Cantidad:<input name="cantidad-t-2" id="cantidad-t-2" type="number" class="form-control" min="0" max="15" step="1" value="1"><br>
                            Talla busto:<select class="form-control" id="talla_b-t-2" name="talla_b-t-2">
                                <option>Talla</option>
                                <option value="S">S</option>
                                <option value="M">M</option>
                                <option value="L">L</option>
                                <option value="XL">XL</option>
                            </select><br>
                            Talla cadera:<select class="form-control" id="talla_c-t-2" name="talla_c-t-2">
                                <option>Talla</option>
                                <option value="S">S</option>
                                <option value="M">M</option>
                                <option value="L">L</option>
                                <option value="XL">XL</option>
                            </select><br>
                            <input type="button" onclick="agregarCarrito(this.id);" id="t-2" class="btn btn-primary" value="Agregar a pedido">                        
                        </div>
                    </div>                
                </div>
                <div class="col-lg-4">
                    <div class="thumbnail">
                        <img src="mare_imagenes/woman/small/foto_t-3.jpg" alt="" style="width: 200px; height: 266px;"/>
                        <div class="caption">
                            <h3 class="text-center ">Modelo T-3</h3>
                            Cantidad:<input name="cantidad-t-3" id="cantidad-t-3" type="number" class="form-control" min="0" max="15" step="1" value="1"><br>
                            Talla busto:<select class="form-control" id="talla_b-t-3" name="talla_b-t-3">
                                <option>Talla</option>
                                <option value="S">S</option>
                                <option value="M">M</option>
                                <option value="L">L</option>
                                <option value="XL">XL</option>
                            </select><br>
                            Talla cadera:<select class="form-control" id="talla_c-t-3" name="talla_c-t-3">
                                <option>Talla</option>
                                <option value="S">S</option>
                                <option value="M">M</option>
                                <option value="L">L</option>
                                <option value="XL">XL</option>
                            </select><br>
                            <input type="button" onclick="agregarCarrito(this.id);" id="t-3" class="btn btn-primary" value="Agregar a pedido">                        
                        </div>
                    </div>                
                </div>
            </div>
            <div class="row">
                <div class="col-lg-4">
                    <div class="thumbnail">
                        <img src="mare_imagenes/woman/small/foto_t-4.jpg" alt="" style="width: 200px; height: 266px;"/>
                        <div class="caption">
                            <h3 class="text-center ">Modelo T-4</h3>
                            Cantidad:<input name="cantidad-t-4" id="cantidad-t-1" type="number" class="form-control" min="0" max="15" step="1" value="1"><br>
                            Talla busto:<select class="form-control" id="talla_b-t-4" name="talla_b-t-4">
                                <option>Talla</option>
                                <option value="S">S</option>
                                <option value="M">M</option>
                                <option value="L">L</option>
                                <option value="XL">XL</option>
                            </select><br>
                            Talla cadera:<select class="form-control" id="talla_c-t-4" name="talla_c-t-4">
                                <option>Talla</option>
                                <option value="S">S</option>
                                <option value="M">M</option>
                                <option value="L">L</option>
                                <option value="XL">XL</option>
                            </select><br>
                            <input type="button" onclick="agregarCarrito(this.id);" id="t-4" class="btn btn-primary" value="Agregar a pedido">                        
                        </div>
                    </div>                
                </div>
            </div>
            </form>
        </div>
        

        <footer class="navbar navbar-default navbar-btn" role="navigation">
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
