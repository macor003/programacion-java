/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venvidrio.intranet.utility;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Ortegam
 */
public class intranetUtility {
    
    private Connection con = null;
    private String x;
     
    
    
    public void setX(String x) {
       this.x=x;
   }
    
    public Connection Conexion_IT () {
        

        /*Variable para almacenar la URL de conexión a nuestra Base de Datos, si esta estuviera en otra máquina, necesitariamos estar registrados en ella y contar con su IP*/
               //("Estoy en Conexion_SORG");
        String url = "jdbc:postgresql://10.183.9.190:5432/IT_DB";
  
        try {
       //Acceso al Driver
           //("Voy a accesar al Driver");
        Class.forName("org.postgresql.Driver");

       //La conexión con los parámetros necesarios
           //("la conexion sera "+url+",postgres,"+"123");

        con = DriverManager.getConnection( url,"postgres","123");//en donde tengo vv_sig va el due;o de la bd_

            //("EXITO");
        } catch (Exception e) {
           ////(e);
           //("El error=="+e);
        }

        return con;

}
    
    public String mostrarBarra(){
        
        String htmlcode="";
        
            htmlcode="<nav class=\"nav navbar navbar-inverse\" style=\"border-radius: 0px;\" >\n" +
"                    <div class=\"container\">\n" +
"                      <!-- Brand and toggle get grouped for better mobile display -->\n" +
"                      <div class=\"navbar-header\">\n" +
"                        <button type=\"button\" style=\"color:white;\" class=\"navbar-toggle collapsed\" data-toggle=\"collapse\" data-target=\"#bs-example-navbar-collapse-1\" aria-expanded=\"false\">\n" +
"                            MENÚ &nbsp; <span class=\"fa fa-navicon\"></span>\n" +
"                        </button>                      \n" +
"                          <a class=\" \" onclick=\"\"></a> \n" +
"                      </div>\n" +
"\n" +
"                      <!-- Collect the nav links, forms, and other content for toggling -->\n" +
"                      <div class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\">\n" +
"                        <ul class=\"nav navbar-nav\">\n" +
"\n" +
"                        </ul>\n" +
"                        <ul class=\"nav navbar-nav\">\n" +
"                            <li><a href='index.jsp'>Inicio <span class=\"sr-only\">(current)</span></a></li>\n" +
"                            <li class=\"dropdown\">\n" +
"                                <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\">Corporación <span class=\"caret\"></span></a>\n" +
"                                <ul class=\"dropdown-menu\">\n" +
"                                    <li><a href=\"#\" data-toggle=\"modal\" data-target=\".Logo_venvidrio\"><span class=\"fa fa-picture-o\" ></span> Logo Venvidrio</a></li>\n" +
"                                    <li><a href=\"#\" data-toggle=\"modal\" data-target=\".politicas\"><span class=\"fa fa-laptop\"></span> Política Integral</a></li>                          \n" +
"                                    <li><a href=\"#\" data-toggle=\"modal\" data-target=\".objetivos\"><span class=\"fa fa-laptop\"></span> Objetivos</a></li>\n" +
"                                </ul>\n" +
"                            </li>\n" +
"                            <li class=\"dropdown\">\n" +
"                                <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\">Aplicaciones <span class=\"caret\"></span></a>\n" +
"                                <ul class=\"dropdown-menu\">\n" +
"                                    <li><a target=\"_blank\" data-toggle=\"tooltip\"  href=\"http://oidvhome/oidv_guia_telefonica/index_2011.jsp\"><span class=\"fa fa-book\"></span> Directorio de Venvidrio</a></li>\n" +
"                                    <li><a target=\"_blank\" data-toggle=\"tooltip\" href=\"http://mail.venvidrio.com.ve/zimbra/\" ><span class=\"fa fa-inbox\"></span> Correo</a></li>\n" +
"                                    <li><a target=\"_blank\" data-toggle=\"tooltip\" data-placement=\"right\" title=\"CRM\" href=\"http://10.183.9.150:8085/wijsp/\"><span class=\"fa fa-laptop\"></span> CRM</a></li>\n" +
"                                    <li><a target=\"_blank\" data-toggle=\"tooltip\" data-placement=\"right\" title=\"Sistema de Gestión de Tecnología\" href=\"http://10.183.9.20:8080/sgt_website/index.jsp\"><span class=\"fa fa-laptop\"></span> SGT</a></li>\n" +
"                                    <li><a target=\"_blank\" data-toggle=\"tooltip\" data-placement=\"right\" title=\"Sistema de Acciones Correctivas y Preventivas\" href=\"http://10.183.9.12/sacp_website/calidad_sacp.jsp\" ><span class=\"fa fa-laptop\"></span> SACP</a></li>\n" +
"                                    <li><a target=\"_blank\" data-toggle=\"tooltip\" data-placement=\"right\" title=\"Sistema de Indicadores de Gestión\" href=\"http://10.183.9.20:8080/sig_website/\"><span class=\"fa fa-laptop\"></span> SIGES</a></li>\n" +
"                                    <li><a target=\"_blank\" data-toggle=\"tooltip\" data-placement=\"right\" title=\"Sistema Integrado de Personal\" href=\"http://10.183.9.12/sip_website/index.jsp\"><span class=\"fa fa-laptop\"></span> SIP</a></li>\n" +
"                                    <li><a target=\"_blank\"  href=\"http://10.183.9.20:8080/SPPDisenoWeb/index.jsp\"><span class=\"fa fa-laptop\"></span> SPP Decorado</a></li>\n" +
"                                    <li><a target=\"_blank\"  href=\"http://10.183.9.20:8080/sig_website/sigReportesIndicadorPublico.jsp\"><span class=\"fa fa-line-chart\"></span> Resultados de Indicadores</a></li>\n" +
"                                    <!--p><span class=\"fa fa-bar-chart\"></span><a class=\"btn btn-link\" target=\"_blank\"  href=\"http://oidvhome/OIDVHome/Construccion.htm\"> Reporte de Causas no <br>Conformidad</a></p-->\n" +
"                                    <li><a target=\"_blank\"  href=\"http://10.183.9.12/bic_website/index.jsp\"><span class=\"fa fa-database\"></span> BIC by Production Line</a></li>\n" +
"                                    <li><a target=\"_blank\" href=\"http://10.183.9.12/wfc_website/index.jsp\"><span class=\"fa fa-database\"></span> Waterfall Chart by Plant</a></li>\n" +
"                                </ul>\n" +
"                            </li>\n" +
"                        </ul>\n" +
"                      </div><!-- /.navbar-collapse -->\n" +
"                    </div><!-- /.container-fluid -->\n" +
"                </nav> ";
        return htmlcode;

    }
    
}
