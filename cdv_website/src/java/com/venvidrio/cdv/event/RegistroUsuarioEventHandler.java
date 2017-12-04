package com.venvidrio.cdv.event;

import com.venvidrio.cdv.loader.UsuariosLoader;
import com.venvidrio.cdv.utility.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by IntelliJ IDEA.
 * User: CarmonaHJD
 * Date: 28-ago-2012
 * Time: 16:02:01
 * To change this template use File | Settings | File Templates.
 */
public class RegistroUsuarioEventHandler extends EventHandlerBase  {

    private ResourceBundle bundle = ResourceBundle.getBundle("cdvURL");

      protected String getURL() {

          return bundle.getString("REGISTRO_USUARIO");
      }
    public void process(ServletContext sc, HttpServletRequest request, HttpServletResponse response)
                 throws IOException, ServletException, NamingException {
        
        //HttpSession session = request.getSession(false);
        //String rols=(String)session.getAttribute("rol");
        
            UsuariosLoader usuario=new UsuariosLoader();
        
            String rif=(String) request.getParameter("lm_rif");
            //String password=(String) request.getParameter("lm_password");
            String password=usuario.GenerarPasswdAleatorio();

            String nombre=(String) request.getParameter("lm_nombre");
            String correo=(String) request.getParameter("lm_correo");
            String direccion=(String) request.getParameter("lm_direccion");
            
            usuario.setRif(rif);
            usuario.setPassword(password);
            usuario.setNombre(nombre);
            usuario.setCorreo(correo);
            usuario.setDireccion(direccion);
            usuario.setNivel(2);
            usuario.setEstatus("A");
            
            try {
                usuario.RegistrarUsuario();
                request.setAttribute("mensaje1", "Su usuario se ha creado exitosamente");
                request.setAttribute("mensaje2", "Por favor ingrese ahora en su correo para verificar los datos para el inicio de sesión");
                request.setAttribute("dir", "cdv_exito.jsp");
            } catch (SQLException ex) {
                ex.printStackTrace();
                request.setAttribute("dir", "sigError.jsp");
            }
        
    }
    
    @Override
    protected void _dispatch(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        //Debug.log(this, "_dispatch", "redirecting to " + getURL());
        RequestDispatcher rd  = request.getRequestDispatcher((String) request.getAttribute("dir"));
        if (rd == null) {
            // Debug.log (this, "_dispatch", "rd = null!");
        }
        rd.forward(request, response);
     }

}
