/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venvidrio.sms.event;

import com.venvidrio.sms.utility.EventHandlerBase;
import java.util.ResourceBundle;

/**
 *
 * @author ortegam
 */
public class PrincipalEventHandler extends EventHandlerBase {

    private ResourceBundle bundle = ResourceBundle.getBundle("smsURL");

    @Override
    protected String getURL() {

        return bundle.getString("ENVIAR_MENSAJE");

    }
}
