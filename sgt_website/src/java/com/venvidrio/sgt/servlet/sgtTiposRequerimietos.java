/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venvidrio.sgt.servlet;

import com.venvidrio.sgt.loader.TSgtVerTipoRequerimientoLoader;

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
public class sgtTiposRequerimietos extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getTipoReque(request, response);
    }
    
    protected void getTipoReque(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
       HttpSession session = request.getSession(true);
       TSgtVerTipoRequerimientoLoader tipo = new TSgtVerTipoRequerimientoLoader();
       
       try{
           session.setAttribute("tipoReque", tipo.getsgtTipoRequerimieto());
       }catch(Exception e){
           System.out.println("Error.... "+e.toString());
       }
       
    }
}