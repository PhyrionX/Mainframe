package principales;

import gui.MainFrameGUI;

import java.io.*;

public class Main {


    public static void main(String[] arg) {
        ConexionMainFrame conexion = new ConexionMainFrame();
        MainFrameGUI mainframe = new MainFrameGUI(conexion);
        //System.out.println(conexion.operaciones("add", "1222","tarea", "Descripcion1"));
        //System.out.println(conexion.operaciones("mostrar"));
        //System.out.println(conexion.operaciones("add", "1223","tarea2", "Descripcion2"));


        //System.out.println(conexion.operaciones("mostrar"));
       // System.out.println("OPppppppppppppppp");
        /*conexion.operaciones("add", "1222","tarea", "Descripcion1");
        conexion.operaciones("add", "1222","tarea2", "Descripcion2");
        conexion.operaciones("mostrar");*/
    }


}
