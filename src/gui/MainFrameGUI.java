package gui;

import modelos.Tarea;
import principales.ConexionMainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MainFrameGUI extends JFrame implements Observer{
    private JButton bSalir = new JButton("Salir");
    private JButton bNuevaTarea = new JButton("Nueva tarea");

    private JTextArea txLista = new JTextArea();

    private JTextField tfTipoTarea = new JTextField("Especifica");
    private JTextField tfFecha = new JTextField("1205");
    private JTextField tfDescripcion = new JTextField("Es una descripcion de prueba");

    private JLabel lbTipo = new JLabel("Tipo tarea:");
    private JLabel lbFecha = new JLabel("Fecha:");
    private JLabel lbDes = new JLabel("Descripción:");

    private ConexionMainFrame conexion;

    String col[] = {"Tipo", "Fecha", "Descripción"};

    DefaultTableModel tableModel = new DefaultTableModel(col, 0);
    List<Tarea> arrayTareas;
    /**
     * Iniciamos la interfaz gráfica
     *
     * @param conexion
     */
    public MainFrameGUI(ConexionMainFrame conexion) {
        this.conexion = conexion;
        this.setLayout(null);
        iniciaTabla();
        // Create a table with 10 rows and 5 columns
        JTable tablaTareas = new JTable(tableModel);
        // Make the table vertically scrollable


        JScrollPane panelTareas = new JScrollPane(tablaTareas);
        // inicializando componentes de la gui

        this.getContentPane().add(lbTipo);
        lbTipo.setBounds(50, 20, 145, 30);

        this.getContentPane().add(lbFecha);
        lbFecha.setBounds(200, 20, 145, 30);

        this.getContentPane().add(lbDes);
        lbDes.setBounds(340, 20, 145, 30);

        this.getContentPane().add(tfTipoTarea);
        tfTipoTarea.setBounds(50, 60, 145, 30);

        this.getContentPane().add(tfFecha);
        tfFecha.setBounds(200, 60, 140, 30);

        this.getContentPane().add(tfDescripcion);
        tfDescripcion.setBounds(345, 60, 140, 30);

        this.getContentPane().add(panelTareas);
        panelTareas.setBounds(50, 110, 450, 325);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(bNuevaTarea);
        bNuevaTarea.setBounds(600,60, 150,30);
        bNuevaTarea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Se envía a introducir la tarea
                String tipo = tfTipoTarea.getText().replace(" ", "");
                String fecha = tfFecha.getText().replace(" ", "");
                String descripcion = tfDescripcion.getText().replace(" ", "");
                conexion.operaciones("add", fecha, tipo, descripcion);
                //System.out.println(conexion.operaciones("mostrar", null));
            }
        });

        bSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        this.getContentPane().add(bSalir);
        bSalir.setBounds(600,100, 150,30);




        this.setBounds(10,10, 800, 500);
        this.setVisible(true);
    }

    /**
     * Iniciamos la tabla con los datos de tareas recuperados
     */
    private void iniciaTabla() {
        arrayTareas = conexion.operaciones("mostrar");

        if (arrayTareas != null) {
            for (Tarea tarea : arrayTareas) {
                String row[] = {tarea.getTipo(), tarea.getFecha(), tarea.getDescripcion()};
                tableModel.addRow(row);
            }
        } else {
            arrayTareas = new ArrayList<>();
        }
    }

    /**
     * Actualizamos la tabla de tareas cuando se introduce una nueva tarea correctamente
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        Tarea tarea = (Tarea) arg;
        arrayTareas.add(tarea);
        String row[] = {tarea.getTipo(), tarea.getFecha(), tarea.getDescripcion()};
        tableModel.addRow(row);
    }
}
