package gui;

import principales.ConexionMainFrame;

import javax.swing.*;

/**
 * Created by Ruben on 27/01/2016.
 */
public class MainFrameGUI extends JFrame{
    private ConexionMainFrame conexion;
    public MainFrameGUI(ConexionMainFrame conexion) {
        this.conexion = conexion;

        this.setBounds(10,10, 500, 300);
        this.setVisible(true);
    }
}
