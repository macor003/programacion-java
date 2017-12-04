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
        <title>Mare Mare</title>        
               
        <link href="mare_estilos/estilos.css" rel="stylesheet" type="text/css"/>
        <link href="mare_estilos/bootstrap.css" rel="stylesheet" type="text/css"/>
        <script src="mare_script/jquery-1.11.1.js" type="text/javascript"></script>
        <script>
            
            $(function () {
    $("#container").highcharts({
        chart: {
            type: 'column'
        },
        
        credits:{
            text: 'MARE MARE'
        },
        title: {
            text: 'Registro de Actividad en la pagina'
        },
        subtitle: {
            text: 'www.mare-mare.com.ve'
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
                text: 'Cantidad'
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.1f} personas</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
            name: 'Usuarios que visitaron la pagina',
            data: [89, 81, 106, 129, 144, 176, 135, 148, 216, 194, 95, 94]

        }, {
            name: 'Usuario que iniciaron sesion',
            data: [83, 78, 98, 93, 106, 84, 105, 104, 91, 83, 86, 92]

        }]
    });
});
    $(function () {
    $('#container2').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: 1,//null,
            plotShadow: false
        },        
        credits:{
            text: 'MARE MARE'
        },
        title: {
            text: 'Usuarios Registrados por genero, 2014'
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'Registrados',
            data: [
                ['Mujeres',   450],
                ['Hombres',       180]
            ]
        }]
    });
});
$(function () {
    $('#pedidos').highcharts({
        title: {
            text: 'Pedidos de trajes de baños',
            x: -20 //center
        },
        credits:{
            text: 'MARE MARE'
        },
        subtitle: {
            text: 'Pedidos por meses',
            x: -20
        },
        xAxis: {
            categories: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun',
                'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic']
        },
        yAxis: {
            title: {
                text: 'Cantidad Pedidos'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: '°C'
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: [{
            name: 'Pedidos',
            data: [9, 15, 12, 14, 8, 10, 12, 10, 9, 5, 15, 20]
        }]
    });
});
            
        </script>
    </head>
    <body>
        <script src="mare_script/highcharts.js" type="text/javascript"></script>
        <script src="mare_script/modules/exporting.js" type="text/javascript"></script> 
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
        <%= barra%><br><br><br><br>     
         <div class="container-fluid">
            <div class="row">
              <div class="col-sm-3 col-md-2 sidebar">
                <ul class="nav nav-pills nav-stacked">
                  <li><a href="Controller?event=PANEL_DE_CONTROL">Usuarios</a></li>
                  <li class="active"><a href="reporte.jsp">Reportes</a></li>
                  <li><a href="producto.jsp">Productos</a></li>
                  
                </ul>          
              </div>
                
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2" style="margin-top: -12%;">
                <h1 class="page-header">Reportes</h1>
                <div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div><br><br>
                <div id="container2" style="min-width: 310px; height: 400px; margin: 0 auto"></div><br><br>
                <div id="pedidos" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
              </div>
            </div>
          </div>
         
         
         
         
         <footer class="navbar navbar-default navbar-btn" style="margin-bottom: 0px;" role="navigation">
            <div class="container">
                <div class="navbar-text text-center">
                    <p class="text-center">&COPY;Todos los derechos reservados por Mare Mare</p>
                </div>
            </div>            
        </footer>
        
        
       
        <%--script src="mare_script/jquery-1.11.1.js" type="text/javascript"></script--%>
        <script src="mare_script/bootstrap.js" type="text/javascript"></script>
    </body>
</html>