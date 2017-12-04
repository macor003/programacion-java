<%-- 
    Document   : index
    Created on : 27/10/2014, 02:41:37 PM
    Author     : Mario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="mare_imagenes/logo.ico" type="image/x-icon" rel="shortcut icon" />
        <title>Mare Mare | Bikinis</title>
        <link href="mare_estilos/estilos.css" rel="stylesheet" type="text/css"/>
        <link href="mare_estilos/bootstrap.css" rel="stylesheet" type="text/css"/>
        <script>
            function agregarCarrito(modelo){
                document.getElementById("modelo").value = modelo;
                var talla_b = document.getElementById("talla_b-"+modelo).value;
                var talla_c = document.getElementById("talla_c-"+modelo).value;
                var cant = document.getElementById("cantidad-"+modelo).value;
                var model = document.getElementById("modelo").value;
                //alert("Modelo: "+model+" cantidad: "+cant+", Talla busto: "+talla_b+", Talla cadera: "+talla_c);
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
        <div class="jumbotron" style="background-color: #8d46b0; color: white;">
            <div class="container">
              <h1>BIKINIS</h1>  
            </div>
            
        </div>
        <div class="container"> 
            <form name="f_forma" method="post" action="Controller?event=AGREGAR_AL_CARRITO">
            <input type="hidden" name="modelo" id="modelo">
            <div class="row">
                <div class="col-lg-4">
                    <div class="thumbnail">
                        <img src="mare_imagenes/woman/small/foto_b-1.jpg" alt="" style="width: 200px; height: 266px;"/>
                        <div class="caption">
                            <h3 class="text-center ">Modelo B-1</h3>
                            Cantidad:<input name="cantidad-b-1" id="cantidad-b-1" type="number" class="form-control" min="0" max="15" step="1" value="1"><br>
                            Talla busto:<select class="form-control" id="talla_b-b-1" name="talla_b-b-1">
                                <option>Talla</option>
                                <option value="S">S</option>
                                <option value="M">M</option>
                                <option value="L">L</option>
                                <option value="XL">XL</option>
                            </select><br>
                            Talla cadera:<select class="form-control" id="talla_c-b-1" name="talla_c-b-1">
                                <option>Talla</option>
                                <option value="S">S</option>
                                <option value="M">M</option>
                                <option value="L">L</option>
                                <option value="XL">XL</option>
                            </select><br>
                            <input type="button" onclick="agregarCarrito(this.id);" id="b-1" class="btn btn-primary" value="Agregar a pedido">                        
                        </div>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="thumbnail">
                        <img src="mare_imagenes/woman/small/foto_b-2.jpg" alt="" style="width: 200px; height: 266px;"/>
                        <div class="caption">
                            <h3 class="text-center">Modelo B-2</h3> 
                            Cantidad:<input name="cantidad-b-2" id="cantidad-b-2" type="number" class="form-control" name="points" min="0" max="15" step="1" value="1"><br>
                            Talla busto:<select class="form-control" id="talla_b-b-2" name="talla_b-b-2">
                                <option>Talla</option>
                                <option value="S">S</option>
                                <option value="M">M</option>
                                <option value="L">L</option>
                                <option value="XL">XL</option>
                            </select><br>
                            Talla cadera:<select class="form-control" id="talla_c-b-2" name="talla_c-b-2" >
                                <option>Talla</option>
                                <option value="S">S</option>
                                <option value="M">M</option>
                                <option value="L">L</option>
                                <option value="XL">XL</option>
                            </select><br>
                            <input type="button"onclick="agregarCarrito(this.id);" id="b-2" class="btn btn-primary" value="Agregar a pedido"> 
                        </div>
                    </div>                
                </div>
                <div class="col-lg-4">
                    <div class="thumbnail">
                        <img src="mare_imagenes/woman/small/foto_b-3.jpg" alt="" style="width: 200px; height: 266px;"/>
                        <div class="caption">
                            <h3 class="text-center">Modelo B-3</h3>
                            Cantidad:<input name="cantidad-b-3" id="cantidad-b-3" type="number" class="form-control" name="points" min="0" max="15" step="1" value="1"><br>
                            Talla busto:<select class="form-control" id="talla_b-b-3" name="talla_b-b-3">
                                <option>Talla</option>
                                <option value="S">S</option>
                                <option value="M">M</option>
                                <option value="L">L</option>
                                <option value="XL">XL</option>
                            </select><br>
                            Talla cadera:<select class="form-control" id="talla_c-b-3" name="talla_c-b-3">
                                <option>Talla</option>
                                <option value="S">S</option>
                                <option value="M">M</option>
                                <option value="L">L</option>
                                <option value="XL">XL</option>
                            </select><br>
                            <input type="button" onclick="agregarCarrito(this.id);" id="b-3" class="btn btn-primary" value="Agregar a pedido"> 
                        </div>
                    </div>                
                </div>
            </div>
            <div class="row">
                <div class="col-lg-4">
                    <div class="thumbnail">
                        <img src="mare_imagenes/woman/small/foto_b-4.jpg" alt="" style="width: 200px; height: 266px;"/>
                        <div class="caption">
                            <h3 class="text-center ">Modelo B-4</h3>
                            Cantidad:<input name="cantidad-b-4" id="cantidad-b-4" type="number" class="form-control" name="points" min="0" max="15" step="1" value="1"><br>
                            Talla busto:<select class="form-control" id="talla_b-b-4" name="talla_b-b-4">
                                <option>Talla</option>
                                <option value="S">S</option>
                                <option value="M">M</option>
                                <option value="L">L</option>
                                <option value="XL">XL</option>
                            </select><br>
                            Talla cadera:<select class="form-control" id="talla_c-b-4" name="talla_c-b-4">
                                <option>Talla</option>
                                <option value="S">S</option>
                                <option value="M">M</option>
                                <option value="L">L</option>
                                <option value="XL">XL</option>
                            </select><br>
                            <input type="button" onclick="agregarCarrito(this.id);" id="b-4" class="btn btn-primary" value="Agregar a pedido"> 
                        </div>
                    </div>                
                </div>
                <div class="col-lg-4">
                    <div class="thumbnail">
                        <img src="mare_imagenes/woman/small/foto_b-5.jpg" alt="" style="width: 200px; height: 266px;"/>
                        <div class="caption">
                            <h3 class="text-center">Modelo B-5</h3>
                            Cantidad:<input name="cantidad-b-5" id="cantidad-b-6" type="number" class="form-control" name="points" min="0" max="15" step="1" value="1"><br>
                            Talla busto:<select class="form-control" id="talla_b-b-5" name="talla_b-b-5">
                                <option>Talla</option>
                                <option value="S">S</option>
                                <option value="M">M</option>
                                <option value="L">L</option>
                                <option value="XL">XL</option>
                            </select><br>
                            Talla cadera:<select class="form-control" id="talla_c-b-5" name="talla_c-b-5">
                                <option>Talla</option>
                                <option value="S">S</option>
                                <option value="M">M</option>
                                <option value="L">L</option>
                                <option value="XL">XL</option>
                            </select><br>
                            <input type="button" onclick="agregarCarrito(this.id);" id="b-5" class="btn btn-primary" value="Agregar a pedido"> 
                        </div>
                    </div>                
                </div>
                <div class="col-lg-4">
                    <div class="thumbnail">
                        <img src="mare_imagenes/woman/small/foto_b-6.jpg" alt="" style="width: 200px; height: 266px;"/>
                        <div class="caption">
                            <h3 class="text-center">Modelo B-6</h3>
                            Cantidad:<input name="cantidad-b-6" id="cantidad-b-6" type="number" class="form-control" name="points" min="0" max="15" step="1" value="1"><br>
                            Talla busto:<select class="form-control" id="talla_b-b-6" name="talla_b-b-6">
                                <option>Talla</option>
                                <option value="S">S</option>
                                <option value="M">M</option>
                                <option value="L">L</option>
                                <option value="XL">XL</option>
                            </select><br>
                            Talla cadera:<select class="form-control" id="talla_c-b-6" name="talla_c-b-6">
                                <option>Talla</option>
                                <option value="S">S</option>
                                <option value="M">M</option>
                                <option value="L">L</option>
                                <option value="XL">XL</option>
                            </select><br>
                            <input type="button" onclick="agregarCarrito(this.id);" id="b-6" class="btn btn-primary" value="Agregar a pedido"> 
                        </div>
                    </div>                
                </div>
                <div class="col-lg-4">
                    <div class="thumbnail">
                        <img src="mare_imagenes/woman/small/foto_b-7.jpg" alt="" style="width: 200px; height: 266px;"/>
                        <div class="caption">
                            <h3 class="text-center ">Modelo B-7</h3>
                            Cantidad:<input name="cantidad-b-7" id="cantidad-b-7" type="number" class="form-control" name="points" min="0" max="15" step="1" value="1"><br>
                            Talla busto:<select class="form-control" id="talla_b-b-7" name="talla_b-b-7">
                                <option>Talla</option>
                                <option value="S">S</option>
                                <option value="M">M</option>
                                <option value="L">L</option>
                                <option value="XL">XL</option>
                            </select><br>
                            Talla cadera:<select class="form-control" id="talla_c-b-7" name="talla_c-b-7">
                                <option>Talla</option>
                                <option value="S">S</option>
                                <option value="M">M</option>
                                <option value="L">L</option>
                                <option value="XL">XL</option>
                            </select><br>
                            <input type="button" onclick="agregarCarrito(this.id);" id="b-7" class="btn btn-primary" value="Agregar a pedido"> 
                        </div>
                    </div>                
                </div>
                <div class="col-lg-4">
                    <div class="thumbnail">
                        <img src="mare_imagenes/woman/small/foto_b-8.jpg" alt="" style="width: 200px; height: 266px;"/>
                        <div class="caption">
                            <h3 class="text-center">Modelo B-8</h3>
                            Cantidad:<input  name="cantidad-b-8" id="cantidad-b-8" type="number" class="form-control" name="points" min="0" max="15" step="1" value="1"><br>
                            Talla busto:<select class="form-control" id="talla_b-b-8" name="talla_b-b-8">
                                <option>Talla</option>
                                <option value="S">S</option>
                                <option value="M">M</option>
                                <option value="L">L</option>
                                <option value="XL">XL</option>
                            </select><br>
                            Talla cadera:<select class="form-control" id="talla_c-b-8" name="talla_c-b-8">
                                <option>Talla</option>
                                <option value="S">S</option>
                                <option value="M">M</option>
                                <option value="L">L</option>
                                <option value="XL">XL</option>
                            </select><br>
                            <input type="button" onclick="agregarCarrito(this.id);" id="b-8" class="btn btn-primary" value="Agregar a pedido">  
                        </div>
                    </div>                
                </div>
                <div class="col-lg-4">
                    <div class="thumbnail">
                        <img src="mare_imagenes/woman/small/foto_b-9.jpg" alt="" style="width: 200px; height: 266px;"/>
                        <div class="caption">
                            <h3 class="text-center">Modelo B-9</h3>
                            Cantidad:<input  name="cantidad-b-9" id="cantidad-b-9" type="number" class="form-control" name="points" min="0" max="15" step="1" value="1"><br>
                            Talla busto:<select class="form-control" id="talla_b-b-9" name="talla_b-b-9">
                                <option>Talla</option>
                                <option value="S">S</option>
                                <option value="M">M</option>
                                <option value="L">L</option>
                                <option value="XL">XL</option>
                            </select><br>
                            Talla cadera:<select class="form-control" id="talla_c-b-9" name="talla_c-b-9">
                                <option>Talla</option>
                                <option value="S">S</option>
                                <option value="M">M</option>
                                <option value="L">L</option>
                                <option value="XL">XL</option>
                            </select><br>
                            <input type="button" onclick="agregarCarrito(this.id);" id="b-9" class="btn btn-primary" value="Agregar a pedido"> 
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
