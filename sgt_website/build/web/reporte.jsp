<%-- 
    Document   : reporte
    Created on : 14-ene-2015, 10:52:26
    Author     : Ortegam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HelpDesk | Reporte</title>
        <link href="sgt_imagenes/icono2.ico" type="image/x-icon" rel="shortcut icon" />
        <link rel="stylesheet" type="text/css" href="sgt_estilos/estilohome.css" />
        <link rel="stylesheet" type="text/css" href="sgt_estilos/menu.css" />
        <script src="sgt_script/jquery-1.11.1.js"></script>
        
        <script>
            
            $(function () {
                $('#grafico').highcharts({
                    chart: {
                        type: 'column',
                        marginTop: '120'
                        
                    },
                    credits:{
                        text: 'GERENCIA CORPORATIVA DE TECNOLOGÍA'
                    },
                    exporting:{
                        filename: 'Reporte Aplicaciones'
                    },
                    title: {
                        text: 'Requerimientos para aplicaciones',
                        style:{
                        "fontSize": "28px;"}
                    },
                    subtitle: {
                        text: 'Sistema de Gestión de Tecnología',
                        style:{
                        "fontSize": "18px;"}
                    },
                    xAxis: {
                        categories: [
                            'Ene',
                            'Feb',
                            'Mar',
                            'Abr',
                            'May',
                            'Jun',
                            'Jul',
                            'Ago',
                            'Sep',
                            'Oct',
                            'Nov',
                            'Dic'
                        ]
                    },
                    yAxis: {
                        min: 0,
                        title: {
                            text: 'Cantidad tickets'
                        }
                    },
                    tooltip: {
                        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                            '<td style="padding:0"><b>{point.y:.1f} Tickets</b></td></tr>',
                        footerFormat: '</table>',
                        shared: false,
                        useHTML: true
                    },
                    plotOptions: {
                        column: {
                            pointPadding: 0.1,
                            borderWidth: 0
                        }
                    },              
                    series: [{
                        name: 'SPP',
                        data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 116.4, 194.1, 95.6, 54.4]

                    }, {
                        name: 'JD Edwards',
                        data: [29.9, 21.5, 36.4, 59.2, 44.0, 76.0, 65.6, 48.5, 56.4, 94.1, 55.6, 34.4]

                    }, {
                        name: 'SACP',
                        data: [29, 71.5, 100.4, 109.2, 184.0, 146.0, 115.6, 98.5, 76.4, 74.1, 65.6, 54.4]

                    }]
                });
            });            
              
            
            
        </script>
        
    </head>
    <body>
        
        <%@ page import= "java.lang.*, com.venvidrio.sgt.utility.sgtUtility, com.venvidrio.sgt.loader.TSgtAprobarLoader"%>
        <%
        sgtUtility util = new sgtUtility(); 
	String barra="";
	String rol=(String) session.getAttribute("rol");
        if(rol==null || rol.equals(null) || rol.equals("") || rol.equals("null")){
        response.sendRedirect(util.getPaginaSessionCaducada());
	}else{
	barra = util.mostrarBarra(rol);
	}
            
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
        
        <%--img src="sgt_imagenes/venvidrio.png" alt="" id="logo"--%>
              
        <script src="sgt_script/highcharts.js"></script>
        <script src="sgt_script/modules/exporting.js"></script>
        <div id="grafico" style=" width: 970px;min-width: 310px; height: 450px; margin: 0 auto; margin-top: -970px;"></div>
        
    </body>
</html>
