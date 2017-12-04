
package com.venvidrio.sgt.event;

import com.venvidrio.sgt.utility.EventHandlerBase;



import java.util.ResourceBundle;


public class PrincipalEventHandler extends EventHandlerBase {

    private ResourceBundle bundle = ResourceBundle.getBundle("sgtURL");

    protected String getURL() {

        return bundle.getString("PAGINA_PRINCIPAL");

    }
     

}
