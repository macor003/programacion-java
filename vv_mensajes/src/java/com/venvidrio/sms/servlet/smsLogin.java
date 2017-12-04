/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venvidrio.sms.servlet;

import com.venvidrio.sms.loader.TSmsLogonLoader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ortegam
 */
@WebServlet(name = "smsLogin", urlPatterns = {"/smsLogin"})
public class smsLogin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            getAcceso(request, response);
        } catch (Exception ex) {
            Logger.getLogger(smsLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getAcceso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {

        HttpSession session = request.getSession(true);
        
        try {
         String ls_usuario = request.getParameter("tf_usuario").toUpperCase();
         String ls_contrasena = request.getParameter("tf_contrasena").toUpperCase();
            System.out.println("Usuario: "+ls_usuario);
            System.out.println("Contraseña: "+ls_contrasena);

             if ((ls_usuario != null) && (ls_contrasena != null)) {
             //select BD2.ficha,BD2.nombre where (BD1.FICHA = BD2.FICHA)
             //debe redireccionar a la pagina de inicio
                TSmsLogonLoader usuario = new TSmsLogonLoader();
                
                usuario.setsmsUsuario(ls_usuario);
                usuario.usuarioAplicacion(ls_usuario);
                //valida si el usuario tiene acceso a la aplicaci?n
                    if(usuario.getsmsUsuario()==null){
                        System.out.println("Usuario NULL==="+usuario.getsmsUsuario());
                        String error = "No tiene Acceso a la Aplicación";
                        request.setAttribute("error",error);
                        RequestDispatcher view = request.getRequestDispatcher("smsNoAcceso.jsp");
                        view.forward(request, response);                        
                        return;
                        //System.out.println(usuario.getsigUsuario());
                    }else{
                        //valida si esta activo el trabajador
                        boolean e = true;
                        if (usuario.getsmsStatus().equals("0")){
                            /*RequestDispatcher view = request.getRequestDispatcher("sgvError.jsp");
                            view.forward(request, response);    */
                            String cedula = usuario.getsmsCedula();
                            String planta = usuario.getsmsCodPlanta();
                            System.out.println("Cedula: "+cedula);
                            System.out.println("Planta: "+planta);
                            usuario.verOutsourcing(cedula, planta);
                            if(usuario.getsmsStatusOutsourcing() != null ){
                                e=true;
                                System.out.println("boolena: "+e);
                                if(ls_contrasena.equals(usuario.getsmsContrasena())){
                                session.setAttribute("planta", usuario.getsmsCodPlanta());
                                session.setAttribute("dpto", usuario.getsmsCodDepart());
                                session.setAttribute("ficha", usuario.getsmsFichaEmp());
                                session.setAttribute("nombre_usuario", usuario.getsmsNombre());
                                session.setAttribute("usuario", ls_usuario);
                                session.setAttribute("rol",usuario.getsmsRol());
                                session.setAttribute("desc_dpto", usuario.getsmsDescDpto());
                                session.setAttribute("nom_planta", usuario.getsmsNomPlanta());
                                RequestDispatcher view = request.getRequestDispatcher("smsPrincipal.jsp");
                                view.forward(request, response);
                                return;
                                //System.out.println(usuario.getsigContrasena());
                                }
                                else{
                                    String error = "Contraseña Incorrecta";
                                    request.setAttribute("error",error);
                                    System.out.println("Usuario NULL===");
                                    RequestDispatcher view = request.getRequestDispatcher("smsError.jsp");
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
                                System.out.println("Status: "+usuario.getsmsStatus()+" El trabajador esta retirado");                                
                            }      

                      }else{
                            //valida si la contrase?a es correcta
                            
                            if(ls_contrasena.equals(usuario.getsmsContrasena())){
                            session.setAttribute("planta", usuario.getsmsCodPlanta());
                            session.setAttribute("dpto", usuario.getsmsCodDepart());
                            session.setAttribute("ficha", usuario.getsmsFichaEmp());
                            session.setAttribute("nombre_usuario", usuario.getsmsNombre());
                            session.setAttribute("usuario", ls_usuario);
                            session.setAttribute("rol",usuario.getsmsRol());
                            session.setAttribute("desc_dpto", usuario.getsmsDescDpto());
                            session.setAttribute("nom_planta", usuario.getsmsNomPlanta());
                            RequestDispatcher view = request.getRequestDispatcher("smsPrincipal.jsp");
                            view.forward(request, response);
                            return;
                            //System.out.println(usuario.getsigContrasena());
                            }
                            else{
                                String error = "Contraseña Incorrecta";
                                request.setAttribute("error",error);
                                System.out.println("Usuario NULL===");
                                RequestDispatcher view = request.getRequestDispatcher("smsError.jsp");
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
                                RequestDispatcher view = request.getRequestDispatcher("smsError.jsp");
                                view.forward(request, response);
                                return;
            }
        }   catch (Exception e) {
            System.out.println("Error.... "+e.toString());
        }


         RequestDispatcher view = request.getRequestDispatcher("smsError.jsp");
         view.forward(request, response);

       
    }
}
