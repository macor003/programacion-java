/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venvidrio.sgt.event;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.venvidrio.sgt.utility.EventHandlerBase;

import java.util.ResourceBundle;

/**
 *
 * @author Ortegam
 */
public class ActualizarAreaEventHandler  extends EventHandlerBase {

     private ResourceBundle bundle = ResourceBundle.getBundle("sgtURL");

     @Override
    protected String getURL() {

        return bundle.getString("ACTUALIZAR_AREA");

    }  

}