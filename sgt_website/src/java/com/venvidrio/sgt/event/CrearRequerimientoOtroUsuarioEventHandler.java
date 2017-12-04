
package com.venvidrio.sgt.event;

import com.venvidrio.sgt.utility.EventHandlerBase;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.naming.NamingException;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.io.IOException;

public class CrearRequerimientoOtroUsuarioEventHandler extends EventHandlerBase {

    private ResourceBundle bundle = ResourceBundle.getBundle("sgtURL");

    protected String getURL() {

        return bundle.getString("CREAR_REQUERIMIENTO_OTRO_USUARIO");


    }
}