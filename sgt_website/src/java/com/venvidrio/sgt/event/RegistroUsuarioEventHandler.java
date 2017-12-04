
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

public class RegistroUsuarioEventHandler extends EventHandlerBase {

    private ResourceBundle bundle = ResourceBundle.getBundle("sgtURL");

    @Override
    protected String getURL() {

        return bundle.getString("REGISTRAR_USUARIO");

    }
     

}
