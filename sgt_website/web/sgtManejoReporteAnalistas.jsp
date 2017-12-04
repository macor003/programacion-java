<%-- 
    Document   : sgtReporteAnalistas
    Created on : 22/04/2015, 10:02:06 AM
    Author     : Ortegam
--%>
<%@page import="net.sf.jasperreports.engine.JasperRunManager"%>

<%@page import="net.sf.jasperreports.engine.JasperReport"%>
<%@page import="net.sf.jasperreports.engine.JasperPrint"%>
<%@page import="net.sf.jasperreports.engine.JasperExportManager"%>
<%@page import="net.sf.jasperreports.engine.JasperFillManager"%>
<%@page import="net.sf.jasperreports.engine.JRExporterParameter"%>
<%@page import="net.sf.jasperreports.engine.JasperCompileManager"%>

<%@page import="java.io.File"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.lang.*,java.util.*,com.venvidrio.sgt.utility.sgtUtility" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Reporte Analista</title>
    </head>


<%
    try{
        Connection con = new sgtUtility().Conexion_Sorg();
        System.out.println("Conexion: "+ con);
        //int anno=Integer.parseInt(new sigUtility().AñoActual());
        System.out.println("1");
        File reportFile = new File(application.getRealPath("sgt_reportes/Reporte1.jasper"));

        //File reportFile = new File(application.getRealPath("sig_reportes/Reporte Bueno 1.jasper"));
        String ficha="5163";
        Map parameters = new HashMap();
        parameters.put("ficha", ficha);
        System.out.println("ReportFile: "+ reportFile.getPath());
        System.out.println("Parameters: "+ parameters);
        
        System.out.println("2");
        //parameters.put("SUBREPORT_DIR", "C:/apache/jakarta-tomcat-3.3/webapps/sigWebsite/sig_reportes/");
        byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath (),parameters,con);
	    response.setContentType("application/pdf");
	    response.setContentLength(bytes.length);
	    ServletOutputStream ouputStream = response.getOutputStream();
	    ouputStream.write(bytes, 0, bytes.length);
	    ouputStream.flush();
	    ouputStream.close();        
		
		
	System.out.println("4");
	con.close();

    }catch (Exception e){
        System.out.println("Error cargando reporte: "+ e);
        e.printStackTrace();
    }
%>

</html>