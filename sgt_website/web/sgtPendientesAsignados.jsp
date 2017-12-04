<%-- 
    Document   : sgtPendientes
    Created on : 12-sep-2014, 9:29:23
    Author     : Ortegam
--%>

<%@page import="com.venvidrio.sgt.loader.TSgtPendientesLoader"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
     <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HelpDesk | Pendientes</title>
        <link href="sgt_imagenes/icono2.ico" type="image/x-icon" rel="shortcut icon" />
        <link rel="stylesheet" type="text/css" href="sgt_estilos/estilohome.css" />
        <link rel="stylesheet" type="text/css" href="sgt_estilos/menu.css" />
        <script src="sgt_script/ajax.js" type="text/javascript" ></script>
        <script src="sgt_script/jquery.js"></script>
        <script src="sgt_script/jquery.dataTables.js"></script>
        <link rel="stylesheet" type="text/css" href="sgt_estilos/jquery.dataTables.css" />
        <script src="sgt_script/jquery-ui.js"></script>
        <script>
            $(document).ready(function() {
            $('#DataTable').dataTable( {
            } );
        } );
              
           function ejecutaCodigo(nombre){
               document.f_forma.action = "Controller?event=DETALLE_ASIGNADO";
               var id = nombre.substring(3);
               //alert("Codigo del Requerimiento: " + id); 
               document.f_forma.codigo.value = id;               
            }
           
              
        function fjs_Buscar(){
                if(!fsj_validaSelect("selectTipoBusqueda"," el tipo de busqueda")){return false;}
                if(!fsj_validaInput2("txtBuscar"," lo que desea buscar")){return false;}
                document.f_forma.action="Controller?event=BUSCAR"; 
                document.f_forma.submit();                 
        }
        
        </script>
        
    </head>
    <body>
       <%@ page import= "java.lang.*, com.venvidrio.sgt.utility.sgtUtility, com.venvidrio.sgt.loader.TSgtAprobarLoader"%>
        <%
        sgtUtility util = new sgtUtility(); 
	String barra="";
        String ficha = (String) session.getAttribute("ficha");
        String planta = (String) session.getAttribute("planta");
	String rol=(String) session.getAttribute("rol");
        if(rol==null || rol.equals(null) || rol.equals("") || rol.equals("null")){
        response.sendRedirect(util.getPaginaSessionCaducada());
	}else{
	barra = util.mostrarBarra(rol);
	}
            TSgtPendientesLoader pendiente = new TSgtPendientesLoader();
            String tabla = pendiente.verRequerimientosAsignados(ficha, planta);
        
        %>
        <img src="sgt_imagenes/background.png" alt="" class="fondo">
        <header>
            <img src="sgt_imagenes/cintillo.png" alt="" class="cintillo">
            <%= barra %>
        </header>
        
        <div id="UsuarioSesion">
            <img src="sgt_imagenes/user.png" alt="">
            
            <a id="NombreUsuario"><%if(session.getAttribute("nombre_usuario")==null){%>			
			<%}else{ %>
            <%= session.getAttribute("nombre_usuario") %>
			  <%} %></a>                       
            <a href="sgtCerrarSesion.jsp" id="out"> | &nbsp;<span>Salir</span></a>             
        </div>            
            
            <img src="sgt_imagenes/venvidrio.png" alt="" id="logo">
            <label id="tituloPendiente" style="margin-left: 2%;">Requerimientos Asignados</label>
            <form name="f_forma" method="post">
                
                <section id="leyenda">
                    <img src="sgt_imagenes/inbox.png" style="width:20px; height: 20px;">&nbsp;<span>Pendiente</span>&nbsp;&nbsp;&nbsp;
                    <img src="sgt_imagenes/asignado.png" style="width:20px; height: 20px;">&nbsp;<span>Asignado</span>&nbsp;&nbsp;&nbsp;
                    <img src="sgt_imagenes/cancel2.png" style="width:20px; height: 20px;">&nbsp;<span>Cancelado</span>&nbsp;&nbsp;&nbsp;
                    <img src="sgt_imagenes/finish.png" style="width:20px; height: 20px;">&nbsp;<span>Finalizado</span>&nbsp;&nbsp;&nbsp;
                    <img src="sgt_imagenes/check.png" style="width:20px; height: 20px;">&nbsp;<span>Aprobado</span>&nbsp;&nbsp;&nbsp;
                    <img src="sgt_imagenes/cancel.png" style="width:20px; height: 20px;">&nbsp;<span>Rechazado</span>&nbsp;&nbsp;&nbsp;
                    <img src="sgt_imagenes/clock2.png" style="width:20px; height: 20px;">&nbsp;<span>En espera</span>&nbsp;&nbsp;&nbsp;                
                    <img src="sgt_imagenes/process.png" style="width:20px; height: 20px;">&nbsp;<span>Procesando</span>                
                </section>

                <input type="hidden" id="codigo" name="codigo">
                <input type="hidden" id="next" name="next">
                <div style="width: 970px; margin-left: 225px; margin-top: -950px; font: 12px Open Sans Light;">
                <table id="DataTable" class="display" cellspacing="0" width="100%" style=" border: 1px solid #A60A0A; margin-top: 10px;">
                <thead style="  font: 13px Open Sans Light; font-weight: 300;color: white; background-color: #A60A0A; top: 10px;">
                    <tr>
                        <th>N°</th>
                        <th>Nombre</th>
                        <th>Fecha</th>                        
                        <th>Tipo Requerimiento</th>
                        <th></th>
                        <th>Status</th>                        
                        <th>+</th>
                    </tr>
                </thead>
                <%= tabla %>
                </table>
                </div>
            </form>       
    </body>
</html>
