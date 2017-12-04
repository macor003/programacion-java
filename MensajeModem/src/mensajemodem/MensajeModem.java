/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensajemodem;

import java.util.Enumeration;
import javax.comm.CommPortIdentifier;

/**
 *
 * @author ortegam
 */
public class MensajeModem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new MensajeModem().searchForPorts();
    }
    

    public void searchForPorts() {
        
        System.out.println("Puertos Disponibles:");
        Enumeration ports = CommPortIdentifier.getPortIdentifiers();
        try{
            while (ports.hasMoreElements()){
                CommPortIdentifier curPort = (CommPortIdentifier)ports.nextElement();

                if (curPort.getPortType() == CommPortIdentifier.PORT_SERIAL){
                    System.out.println(curPort.getName());
                    //portMap.put(curPort.getName(), curPort);
                }                
            }
            System.out.println("----------------------");
            
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
}
