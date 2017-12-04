/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venvidrio.sgt.event;

import com.venvidrio.sgt.utility.EventHandlerBase;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ortegam
 */
public class PendientesCreadosEventHandler extends EventHandlerBase{

   private ResourceBundle bundle = ResourceBundle.getBundle("sgtURL");

   @Override
    protected String getURL() {

        return bundle.getString("CREADOS");


    }
}

