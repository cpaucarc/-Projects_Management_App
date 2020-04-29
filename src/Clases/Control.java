package Clases;

import com.database.Controller;

public class Control {
    
    static String usuario = "root";
    static String psw = "1234";
    static String db = "proyectos";
    static String host = "localhost";
    
    
    static Controller control = new Controller(usuario, psw, db, host);  
 
    public static Controller getController(){
        return control;
    }
    
    
    
}
