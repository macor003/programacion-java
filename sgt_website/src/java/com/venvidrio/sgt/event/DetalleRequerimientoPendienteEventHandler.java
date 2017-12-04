/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venvidrio.sgt.event;


import com.venvidrio.sgt.utility.EventHandlerBase;

import java.util.ResourceBundle;

/**
 *
 * @author Ortegam
 */
public class DetalleRequerimientoPendienteEventHandler extends EventHandlerBase {
     private ResourceBundle bundle = ResourceBundle.getBundle("sgtURL");

    protected String getURL() {

        return bundle.getString("DETALLE_REQUERIMIENTO_PENDIENTE");

    }
}