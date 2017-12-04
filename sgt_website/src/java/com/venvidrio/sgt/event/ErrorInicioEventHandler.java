
package com.venvidrio.sgt.event;

import java.util.ResourceBundle;

public class ErrorInicioEventHandler {
    
    private ResourceBundle bundle = ResourceBundle.getBundle("sgtURL");

    protected String getURL() {

        return bundle.getString("ERROR_AL_INICIAR");

    }
    
}
