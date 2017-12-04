/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import java.util.ResourceBundle;
import utility.EventHandlerBase;

/**
 *
 * @author Mario
 */
public class PanelControlEventHandler extends EventHandlerBase {

   private final ResourceBundle bundle = ResourceBundle.getBundle("sgtURL");

  
   @Override
    protected String getURL() {
        return bundle.getString("PANEL_DE_CONTROL");
    }  
}