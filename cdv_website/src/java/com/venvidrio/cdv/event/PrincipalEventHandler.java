package com.venvidrio.cdv.event;

import com.venvidrio.cdv.utility.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.naming.NamingException;
import java.util.ResourceBundle;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: CarmonaHJD
 * Date: 28-ago-2012
 * Time: 16:02:01
 * To change this template use File | Settings | File Templates.
 */
public class PrincipalEventHandler extends EventHandlerBase  {

    private ResourceBundle bundle = ResourceBundle.getBundle("cdvURL");

      protected String getURL() {

          return bundle.getString("PRINCIPAL");
      }
    public void process(ServletContext sc, HttpServletRequest request, HttpServletResponse response)
                 throws IOException, ServletException, NamingException {
        
    }

}
