/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

public class Constants {
    
    public static final String oidvPackage = "OIDV";
    public static final String oidvComponent = "n_oidv_verifica_usuario";

    public static final String sipUser = "jagadmin";
    public static final String sipPassword = "";

    public static final String JAGUSER = "jagadmin";
    public static final String JAGPASSWORD = "";

    public static final String ls_server = "10.159.9.67";
    public static final String ls_port = "9000";
    public static final String ls_protocol = "iiop";

    // "HTML variable" strings
    public static final String EVENT            = "event";
    public static final String USERNAME         = "USERNAME";
    public static final String PASSWORD         = "PASSWORD";

    // System event names (note, all other event names are
    // defined in the Event.properties file)
    public static final String LOGIN_FORM     = "LOGIN_FORM";
    public static final String LOGIN_FAILED   = "LOGIN_FAILED";
    public static final String USAGE          = "USAGE";
    public static final String LOGOUT         = "LOGOUT";
    public static final String ERROR_EVENT    = "ERROR_EVENT";
    public static final String UNKNOWN_EVENT  = "UNKNOWN_EVENT";

    // System event handlers (note, all other event handlers are
    // defined in the Event.properties file)
    private static final String EVENT_PACKAGE = "com.wrox.projsp.event.";
    public static final String LOGIN_FORM_HANDLER =
        EVENT_PACKAGE + "LoginEventHandler";
    public static final String LOGIN_FAILED_HANDLER =
        EVENT_PACKAGE + "LoginFailedEventHandler";
    public static final String USAGE_HANDLER =
        EVENT_PACKAGE + "UsageEventHandler";
    public static final String LOGOUT_HANDLER =
        EVENT_PACKAGE + "LogoutEventHandler";
    public static final String UNKNOWN_EVENT_HANDLER =
        EVENT_PACKAGE + "UnknownEventHandler";


}
