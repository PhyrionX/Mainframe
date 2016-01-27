package principales;

import gui.MainFrameGUI;

import java.io.*;

public class Main {


    public static void main(String[] arg) {
        ConexionMainFrame conexion = new ConexionMainFrame();
        MainFrameGUI mainframe = new MainFrameGUI(conexion);
        /*System.out.println(conexion.operaciones("add", "1222", "aaa"));
        System.out.println(conexion.operaciones("mostrar"));
        System.out.println("OPppppppppppppppp");
        System.out.println(conexion.operaciones("mostrar"));*/
    }


}
