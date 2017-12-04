package com.venvidrio.sgt.servlet;

import com.venvidrio.sgt.loader.TSgtLogonLoader;

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
public class sgtLogin  extends HttpServlet{
     
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
                TSgtLogonLoader usuario = new TSgtLogonLoader();
                
                usuario.setsgtUsuario(ls_usuario);
                usuario.usuarioAplicacion(ls_usuario);
                //valida si el usuario tiene acceso a la aplicaci?n
                    if(usuario.getsgtUsuario()==null){
                        System.out.println("Usuario NULL==="+usuario.getsgtUsuario());
                        String error = "No tiene Acceso a la Aplicación";
                        request.setAttribute("error",error);
                        RequestDispatcher view = request.getRequestDispatcher("sgtNoAcceso.jsp");
                        view.forward(request, response);                        
                        return;
                        //System.out.println(usuario.getsigUsuario());
                    }else{
                        //valida si esta activo el trabajador
                        boolean e = true;
                        if (usuario.getsgtStatus().equals("0")){
                            /*RequestDispatcher view = request.getRequestDispatcher("sgvError.jsp");
                            view.forward(request, response);    */
                            String cedula = usuario.getsgtCedula();
                            String planta = usuario.getsgtCodPlanta();
                            System.out.println("Cedula: "+cedula);
                            System.out.println("Planta: "+planta);
                            usuario.verOutsourcing(cedula, planta);
                            if(usuario.getsgtStatusOutsourcing() != null ){
                                e=true;
                                System.out.println("boolena: "+e);
                                if(ls_contrasena.equals(usuario.getsgtContrasena())){
                                session.setAttribute("planta", usuario.getsgtCodPlanta());
                                session.setAttribute("dpto", usuario.getsgtCodDepart());
                                session.setAttribute("ficha", usuario.getsgtFichaEmp());
                                session.setAttribute("nombre_usuario", usuario.getsgtNombre());
                                session.setAttribute("usuario", ls_usuario);
                                session.setAttribute("rol",usuario.getsgtRol());
                                session.setAttribute("desc_dpto", usuario.getsgtDescDpto());
                                session.setAttribute("nom_planta", usuario.getsgNomPlanta());
                                RequestDispatcher view = request.getRequestDispatcher("sgtPrincipal.jsp");
                                view.forward(request, response);
                                return;
                                //System.out.println(usuario.getsigContrasena());
                                }
                                else{
                                    String error = "Contraseña Incorrecta";
                                    request.setAttribute("error",error);
                                    System.out.println("Usuario NULL===");
                                    RequestDispatcher view = request.getRequestDispatcher("sgtError.jsp");
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
                            }else{
                                e=false;
                                System.out.println("boolena: "+e);
                                String error = "Trabajador Inactivo";
                                request.setAttribute("error",error);
                                System.out.println("Status: "+usuario.getsgtStatus()+" El trabajador esta retirado");                                
                            }      

                      }else{
                            //valida si la contrase?a es correcta
                            
                            if(ls_contrasena.equals(usuario.getsgtContrasena())){
                            session.setAttribute("planta", usuario.getsgtCodPlanta());
                            session.setAttribute("dpto", usuario.getsgtCodDepart());
                            session.setAttribute("ficha", usuario.getsgtFichaEmp());
                            session.setAttribute("nombre_usuario", usuario.getsgtNombre());
                            session.setAttribute("usuario", ls_usuario);
                            session.setAttribute("rol",usuario.getsgtRol());
                            session.setAttribute("desc_dpto", usuario.getsgtDescDpto());
                            session.setAttribute("nom_planta", usuario.getsgNomPlanta());
                            RequestDispatcher view = request.getRequestDispatcher("sgtPrincipal.jsp");
                            view.forward(request, response);
                            return;
                            //System.out.println(usuario.getsigContrasena());
                            }
                            else{
                                String error = "Contraseña Incorrecta";
                                request.setAttribute("error",error);
                                System.out.println("Usuario NULL===");
                                RequestDispatcher view = request.getRequestDispatcher("sgtError.jsp");
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
