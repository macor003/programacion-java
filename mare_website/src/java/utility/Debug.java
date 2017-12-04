/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;
import java.io.FileWriter;
import java.io.PrintWriter;
/**
 * Created by IntelliJ IDEA.
 * User: CarmonaHJD
 * Date: 21-mar-2012
 * Time: 10:13:55
 * To change this template use File | Settings | File Templates.
 */
public class Debug {

    static private PrintWriter _debugFile;

  public static void init() {
    try {
      _debugFile = new PrintWriter (
                                    new FileWriter ("debug.log"), true);
    } catch (Exception e) {
      System.out.println("error opening debug.log");
    }
  }

  public static void log (Object src, String method, String msg) {
    _debugFile.println(src + ":" + method + ":" + msg);
    System.out.println(src + ":" + method + ":" + msg);
  }
}
