<%-- 
    Document   : dataTable
    Created on : 14-ene-2015, 14:15:48
    Author     : Ortegam
--%>

<%@page import="com.venvidrio.sgt.loader.TSgtPendientesLoader"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="sgt_script/jquery.js"></script>
        <script src="sgt_script/jquery.dataTables.js"></script>
       <link rel="stylesheet" type="text/css" href="sgt_estilos/jquery.dataTables.css" />
       <link rel="stylesheet" type="text/css" href="sgt_estilos/estilohome.css" />
       <link rel="stylesheet" type="text/css" href="sgt_estilos/menu.css" />
        <script src="sgt_script/ajax.js" type="text/javascript" ></script>
        <script>
        $(document).ready(function() {
            $('#DataTable').dataTable( {
            } );
        } );
    </script>
    </head>
       
    <body>
        <% 
            TSgtPendientesLoader pendiente = new TSgtPendientesLoader();
            String tabla = pendiente.verDataTablePendientes();  %>
            <div style="width: 970px; font: 12px Open Sans Light;">
                <table id="DataTable" class="display" cellspacing="0" width="100%" style=" margin-top: 10px;">
                <thead style="color: #A60A0A; top: 10px;">
                    <tr>
                        <th>N°</th>
                        <th>Nombre</th>
                        <th>Fecha</th>
                        <th>Tipo Requerimiento</th>
                        <th></th>
                        <th>Clasificación</th>                        
                        <th>+</th>
                    </tr>
                </thead>
                <%= tabla %>
                
            </table>
            </div>
    </body>
</html>
