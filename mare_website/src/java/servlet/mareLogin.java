package servlet;

import loader.mareLogon;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ortegam
 */
public class mareLogin  extends HttpServlet{
     
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getAcceso(request, response);
    }

    public void getAcceso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        
        try {
         String ls_usuario = request.getParameter("tf_usuario").toUpperCase();
         String ls_contrasena = request.getParameter("tf_contrasena").toUpperCase();

             if ((ls_usuario != null) && (ls_contrasena != null)) {
             //select BD2.ficha,BD2.nombre where (BD1.FICHA = BD2.FICHA)
             //debe redireccionar a la pagina de inicio
                mareLogon usuario = new mareLogon();
                
                usuario.setUsuario(ls_usuario);
                usuario.usuarioAplicacion(ls_usuario);
                //valida si el usuario tiene acceso a la aplicaci?n
                    if(usuario.getUsuario()==null){
                        System.out.println("Usuario NULL==="+usuario.getUsuario());
                        String error = "Usuario no existe!";
                        request.setAttribute("error",error);
                        RequestDispatcher view = request.getRequestDispatcher("noExiste.jsp");
                        view.forward(request, response);                        
                        return;
                        //System.out.println(usuario.getsigUsuario());
                    }else{
                        //valida si esta activo el trabajador
                        
                        if (usuario.getRol().equals("0")){
                            /*RequestDispatcher view = request.getRequestDispatcher("sgvError.jsp");
                            view.forward(request, response);    */
                           String error = "Trabajador Inactivo";
                            request.setAttribute("error",error);
                            System.out.println(usuario.getRol());

                      }else{
                            //valida si la contrase?a es correcta
                            if(ls_contrasena.equals(usuario.getContrasena())){
                                session.setAttribute("apellido", usuario.getApellido());
                                session.setAttribute("nombre", usuario.getNombre());
                                session.setAttribute("correo", ls_usuario);
                                session.setAttribute("rol",usuario.getRol());
                                RequestDispatcher view = request.getRequestDispatcher("marePrincipal.jsp");
                                view.forward(request, response);
                                return;
                                //System.out.println(usuario.getsigContrasena());
                            }
                            else{
                                String error = "Contraseña Incorrecta";
                                request.setAttribute("error",error);
                                System.out.println("Usuario NULL===");
                                RequestDispatcher view = request.getRequestDispatcher("mareError.jsp");
                                view.forward(request, response);
                                
                                PrintWriter out = response.getWriter();
                                try {
                                    /* TODO output your page here. You may use following sample code. */
                                    out.println("<script>");
                                    out.println("alert('Contraseña Incorrecta')");
                                    out.println("</script>");
                                } finally {            
                                    out.close();
                                }
                                return;
                            }

                        }
                    }

            }
            else {
                 //redireciona a pagina de error
                System.out.println("Punto 5");
                String error = "Usuario No Existe";
                                request.setAttribute("error",error);
                                RequestDispatcher view = request.getRequestDispatcher("sgtError.jsp");
                                view.forward(request, response);
                                return;
            }
        }   catch (Exception e) {
            System.out.println("Error.... "+e.toString());
        }


         RequestDispatcher view = request.getRequestDispatcher("sgtError.jsp");
         view.forward(request, response);

       
    }
}
