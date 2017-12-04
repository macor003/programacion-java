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
public class NosotrosEventHandler extends EventHandlerBase {

   private ResourceBundle bundle = ResourceBundle.getBundle("sgtURL");

   @Override
    protected String getURL() {
        return bundle.getString("NOSOTROS");
    }  
}