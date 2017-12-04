package com.venvidrio.intranet.utility;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public abstract class EventHandlerBase{

    protected abstract String getURL();

    public void process(ServletContext sc, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, NamingException {

        //Debug.log (this, "process", "Using default process");
    }

    public void forward(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        //Debug.log (this, "forward", "Using default forward");
        _dispatch(request, response);
    }

    protected void _dispatch(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        //Debug.log(this, "_dispatch", "redirecting to " + getURL());

        //RequestDispatcher rd  = getServletContext().getRequestDispatcher(getURL());
        RequestDispatcher rd  = request.getRequestDispatcher(getURL());
        if (rd == null) {
            // Debug.log (this, "_dispatch", "rd = null!");
        }
        rd.forward(request, response);
    }
}

