package com.venvidrio.cdv.servlet;

/**
 * Created by IntelliJ IDEA.
 * User: CarmonaHJD
 * Date: 14-mar-2012
 * Time: 12:12:21
 * To change this template use File | Settings | File Templates.
 */

import com.venvidrio.cdv.loader.LoginLoader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.http.HttpSession;


/**g
 * Created by IntelliJ IDEA.
 * User: BolivarLM
 * Date: 02-dic-2011
 * Time: 11:03:47
 * To change this template use File | Settings | File Templates.
 * @author Luz M. Bol?var
 */


public class cdvLogin extends javax.servlet.http.HttpServlet{
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getAcceso(request, response);
    }



    public void getAcceso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
         String ls_usuario = request.getParameter("lm_rif").toUpperCase().trim();
         String ls_contrasena = request.getParameter("lm_passwd").trim();

            System.out.println(ls_usuario);
            System.out.println(ls_contrasena);
            if ((ls_usuario != null) && (ls_contrasena != null)) {
             //select BD2.ficha,BD2.nombre where (BD1.FICHA = BD2.FICHA)
             //debe redireccionar a la pagina de inicio
                LoginLoader usuario = new LoginLoader();
                
                int cod_valida=usuario.ValidaDatosUsuario(ls_usuario, ls_contrasena);
                
                if(cod_valida==0){//No Existe el usuario
                    String error = "ERROR. NO EXISTE EL USUARIO";
                    request.setAttribute("error",error);
                    RequestDispatcher view = request.getRequestDispatcher("cdv_exito.jsp");
                    view.forward(request, response);
                    return;
                    
                }else if(cod_valida==2){//Passwd Incorrecto
                    String error = "ERROR. CONTRASEÑA INCORRECTA";
                    request.setAttribute("error",error);
                    RequestDispatcher view = request.getRequestDispatcher("cdv_exito.jsp");
                    view.forward(request, response);
                    return;
                    
                }else if(cod_valida==3){//Usuario Inactivo
                    String error = "ERROR. USUARIO INACTIVO";
                    request.setAttribute("error",error);
                    RequestDispatcher view = request.getRequestDispatcher("cdv_exito.jsp");
                    view.forward(request, response);
                    return;
                    
                }else if(cod_valida==1){//CORRECTO
                    HttpSession session = request.getSession(true);
                    session.setAttribute("rif", usuario.getId_usuario());
                    session.setAttribute("correo", usuario.getCorreo());
                    session.setAttribute("nombre_usuario", usuario.getNombre_usuario());
                    session.setAttribute("direccion", usuario.getDireccion());
                    session.setAttribute("nivel", usuario.getNivel());
                    RequestDispatcher view = request.getRequestDispatcher("cdv_productos.jsp");
                    view.forward(request, response);
                    return;
                    
                }
                
            }else {
                String error = "ERROR. NO EXISTE EL USUARIO";
                request.setAttribute("error",error);
                RequestDispatcher view = request.getRequestDispatcher("cdv_exito.jsp");
                view.forward(request, response);
                return;
            }
        }   catch (Exception e) {
            e.printStackTrace();
        }


         RequestDispatcher view = request.getRequestDispatcher("cdv_exito.jsp");
         view.forward(request, response);

       
    }

}
