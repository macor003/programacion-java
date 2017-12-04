/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venvidrio.sgt.event;

import com.venvidrio.sgt.utility.EventHandlerBase;

import java.util.ResourceBundle;


/**
 *
 * @author Ortegam
 */
public class ReporteAnalistasEventHandler extends EventHandlerBase {

    private ResourceBundle bundle = ResourceBundle.getBundle("sgtURL");

    @Override
    protected String getURL() {

        return bundle.getString("REPORTE_ANALISTAS");

    }
}