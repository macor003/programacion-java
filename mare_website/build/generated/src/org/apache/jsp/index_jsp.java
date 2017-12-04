package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.lang.*;
import utility.mareUtility;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"es\">\n");
      out.write("    <head>\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link href=\"mare_imagenes/logo.ico\" type=\"image/x-icon\" rel=\"shortcut icon\" />\n");
      out.write("        <title>Mare Mare | Home</title>\n");
      out.write("        <link href=\"mare_estilos/estilos.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <link href=\"mare_estilos/bootstrap.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        \n");
      out.write("        <script>\n");
      out.write("            $(function () {\n");
      out.write("              $('#redSocial').tooltip();\n");
      out.write("            });\n");
      out.write("        </script>\n");
      out.write("    </head>\n");
      out.write("    \n");
      out.write("    <body>\n");
      out.write("        \n");
      out.write("        ");

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
	
	
            
        
      out.write("\n");
      out.write("        ");
      out.write("   \n");
      out.write("        ");
      out.print( barra);
      out.write("\n");
      out.write("              \n");
      out.write("        <div class=\"jumbotron boxbienvenida\">\n");
      out.write("            <div class=\"container\">                \n");
      out.write("                <h1 style=\"color:white;\">Bienvenidos!</h1>\n");
      out.write("                <p style=\"color: white;\">Te damos la bienvenida a nuestra nueva pagina, cambiamos para que tengas una mejor experiencia.<br>\n");
      out.write("                    Registrate y conoce nuestras novedades, para que puedas enterarte de todo lo que Mare Mare te puede ofrecer.</p> \n");
      out.write("                <a href=\"Controller?event=REGISTRAR_USUARIO_CLIENTE\"><button class=\"btn btn-info btn-lg\" >Regístrate</button></a>&nbsp;&nbsp;\n");
      out.write("                <a href=\"Controller?event=INICIO_SESION\"><button type=\"submit\" class=\"btn btn-info btn-lg\">Inicia sesión</button></a>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <div class=\"container\">            \n");
      out.write("            <div class=\"row\">\n");
      out.write("                <div class=\"list-group\">\n");
      out.write("                    <div class=\"col-lg-4 text-center\">\n");
      out.write("                        <div class=\"thumbnail\">\n");
      out.write("                            <img src=\"mare_imagenes/woman/small/foto_t-1.jpg\" alt=\"\" style=\"width: 200px; height: 266px;\"/>\n");
      out.write("                            <div class=\"caption\">\n");
      out.write("                                <h3>Tankinis</h3> \n");
      out.write("                                <a href=\"Controller?event=TANKINIS\" class=\"btn btn-warning\">Ver más »</a>\n");
      out.write("                            </div>\n");
      out.write("                        </div>                \n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"col-lg-4 text-center\">\n");
      out.write("                        <div class=\"thumbnail\">\n");
      out.write("                            <img src=\"mare_imagenes/woman/small/foto_b-1.jpg\" alt=\"\" style=\"width: 200px; height: 266px;\"/>\n");
      out.write("                            <div class=\"caption\">\n");
      out.write("                                <h3>Bikinis</h3>\n");
      out.write("                                <a href=\"Controller?event=BIKINIS\" class=\"btn btn-warning\">Ver más »</a>\n");
      out.write("                            </div>\n");
      out.write("                        </div>                \n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"col-lg-4 text-center\">\n");
      out.write("                        <div class=\"thumbnail\">\n");
      out.write("                            <img src=\"mare_imagenes/woman/small/foto_e-1.jpg\" alt=\"\" style=\"width: 200px; height: 266px;\"/>\n");
      out.write("                            <div class=\"caption\">\n");
      out.write("                                <h3>Enteros</h3>\n");
      out.write("                                <a href=\"Controller?event=ENTEROS\" class=\"btn btn-warning\">Ver más »</a>\n");
      out.write("                            </div>\n");
      out.write("                        </div>                \n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"col-lg-4 text-center\">\n");
      out.write("                        <h2>Nosotros</h2>\n");
      out.write("                        <p>Es una empresa Venezolana, dedicada al Diseño, confección y comercialización de la Marca registrada Mare Mare Trajes de Baño , que nace legalmente un 1ero de Octubre de 1.996 en la ciudad de Valencia- Venezuela.</p>\n");
      out.write("                        <a href=\"nosotros.jsp\" class=\"btn btn-primary\">Conoce más »</a>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"col-lg-4 text-center\">\n");
      out.write("                        <h2>Redes Sociales</h2>\n");
      out.write("                        <div class=\"col-xs-3\">\n");
      out.write("                            <a><img class=\"img-responsive\" style=\"width: 55px; height: 55px;\" src=\"mare_imagenes/facebook.png\"></a>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"col-xs-3\">\n");
      out.write("                            <a id=\"redSocial\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Tooltip on bottom\"><img class=\"img-responsive\" style=\"width: 55px; height: 55px;\" src=\"mare_imagenes/twitter.png\"></a>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"col-lg-4 text-center\">\n");
      out.write("                        <h2>Contactanos</h2>\n");
      out.write("                        <div class=\"col-xs-2\">\n");
      out.write("                            <a><img class=\"img-responsive\" style=\"width: 35px; height: 40px;\" src=\"mare_imagenes/location-pointer.png\"></a>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"col-xs-9 text-justify\">\n");
      out.write("                            <p>Av. Bolívar Norte, Edif. Hotel Le Paris, Local 6, PB.\n");
      out.write("                                Valencia, Venezuela.<br>\n");
      out.write("                                Telf.: (+58)0241-824.6012</p>\n");
      out.write("                            <p>C.C. Via Veneto, Nivel Florencia, Local 7-F.\n");
      out.write("                                Valencia, Venezuela.<br>\n");
      out.write("                                Telf.: (+58)0241-843.3059</p>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <footer class=\"navbar navbar-default navbar-btn\">\n");
      out.write("            <div class=\"container text-center\">\n");
      out.write("                <div class=\"navbar-text text-center\">\n");
      out.write("                    <p class=\"text-center\">&COPY;Todos los derechos reservados por Mare Mare</p>\n");
      out.write("                </div>\n");
      out.write("            </div>            \n");
      out.write("        </footer>\n");
      out.write("        <script src=\"mare_script/jquery-1.11.1.js\" type=\"text/javascript\"></script>\n");
      out.write("        <script src=\"mare_script/bootstrap.js\" type=\"text/javascript\"></script>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
