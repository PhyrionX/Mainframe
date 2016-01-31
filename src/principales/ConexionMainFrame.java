package principales;

import modelos.Tarea;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class ConexionMainFrame extends Observable{
    static Process process;
    private static InputStream in;
    private static PrintWriter out;
    private boolean estadoTratarPantalla = false;
    private List<Tarea> listaTareas;

    public ConexionMainFrame() {
        try {
            try {
                process = Runtime.getRuntime().exec("C:\\Program Files (x86)\\wc3270\\ws3270.exe");
                System.out.println("Dentro");
            } catch (IOException e) {
                e.printStackTrace();
            }
            in = process.getInputStream();

            out = new PrintWriter(new OutputStreamWriter(process.getOutputStream()));

            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

            Thread.sleep(500);

            conectar();
        } catch (InterruptedException ex) {
            System.out.println("La conexión ha sido interrumpida");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void conectar() throws IOException, InterruptedException {

        escribir("connect 155.210.152.51:101");
        leerPantalla();
        escribir("enter()");
        leerPantalla();
        escribir("string(grupo_03)");
        escribir("Tab()");
        escribir("string(secreto6)");
        leerPantalla();
        escribir("enter()");
        leerPantalla();
        escribir("enter()");
        leerPantalla();
        escribir("string(tareas.c)");
        leerPantalla();
        escribir("enter()");
        Thread.sleep(2000);
        leerPantalla();
        estadoTratarPantalla = true;
       /* escribir("string(2)");
        leerPantalla();
        escribir("enter()");
        escribir("string(1)");
        leerPantalla();
        escribir("enter()");
        leerPantalla();*/


    }
    public void escribir(String texto) {
        out.flush();
        //System.out.println(texto);
        out.println(texto);
        out.flush();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {

        }
    }

    public List<Tarea> operaciones(String op, String... datos) {
        List<Tarea> listaTareas = null;

        switch (op) {

            case "add":
                //System.out.println(datos[0] + " -- " + datos[1] + " -- " + datos[2]);
                escribir("string(1)");
                leerPantalla();
                escribir("enter()");
                leerPantalla();
                escribir("string(2)");
                leerPantalla();
                escribir("enter()");
                leerPantalla();
                escribir("string(" + datos[0]+ ")");
                leerPantalla();
                escribir("enter()");
                leerPantalla();
                escribir("string(" + datos[1] + ")");
                leerPantalla();
                escribir("enter()");
                leerPantalla();
                escribir("string(" + datos[2] + ")");
                leerPantalla();
                escribir("enter()");
                leerPantalla();
                escribir("string(3)");
                leerPantalla();
                escribir("enter()");
                leerPantalla();
                Tarea tarea = new Tarea(datos[0], datos[1], datos[2]);
                setChanged();
                notifyObservers(tarea);

                break;
            case "mostrar":
                leerPantalla();
                escribir("string(2)");
                leerPantalla();
                escribir("enter()");
                leerPantalla();
                escribir("string(2)");
                leerPantalla();
                escribir("enter()");
                leerPantalla();
                escribir("string(3)");
                listaTareas = getArrayList(leerPantalla());
                leerPantalla();
                escribir("enter()");
               leerPantalla();
                break;
        }
        return listaTareas;
    }

    private List<Tarea> getArrayList(String pantalla) {
        listaTareas = new ArrayList<>();
        String[] lineas = pantalla.split("\n");
        int lastLine = lineas.length - 1;
        boolean continuar = true;
        for (int i = lastLine; (i >= 0) && continuar; i--) {
            // Recorremos desde la ultima linea con datos de tareas
            if (lineas[i].trim().equals("TASK")) {
                String linea = lineas[i].trim().replace("TASK ", "");
                linea = linea.substring(linea.indexOf(":"));
                linea = linea.replace(": SPECIFIC", "");
                String[] datos  = linea.split(" ");
                Tarea tarea = new Tarea(datos[0], datos[1], datos[3]);
                listaTareas.add(tarea);
            }
            // nos aseguramos que recorra solo una lista de tareas ya qye en la pantalla
            // puede haber mas de una lista de tareas solo queremos la ultima
            if (lineas[i].trim().equals("TASK 0")) {
                continuar = false;
            }
        }
        return listaTareas;
    }

    public String leerPantalla() {
        String pantalla = "";
        try {
            out.println("ascii()");
            out.flush();

            while (in.available() == 0) {
                Thread.sleep(100);
            }

            while (in.available() > 0) {

                pantalla +=  (char) in.read();

            }

        } catch (IOException ex) {
            System.out.println("Exception");
        } catch (InterruptedException ex) {
            System.out.println("Exception");
        }
        if (estadoTratarPantalla) {
            tratarPantalla(pantalla.replace("data:", ""));
        }
        return pantalla;
    }

    private void tratarPantalla(String pantalla) {
        String[] lineas = pantalla.split("\n");
        int i = 0;
        for (String linea : lineas) {
            if (linea.trim().startsWith("TASK")) {
                System.out.println(linea);
            }
            if (linea.contains("------T")) {
                //System.out.println(linea.contains("------T") + "  linea: " + i + " -> " + lineas[i - 1]);
                if(!lineas[i - 1].trim().equals("")){
                    //System.out.println(lineas[i - 1]);
                    int n = i - 1;
                    while (lineas[n].trim().startsWith("TASK")) {
                        System.out.println("Opppp " + lineas[n]);
                        n--;
                    }
                    escribir("enter()");
                    leerPantalla();
                }


            }
            i++;
        }
    }
}
