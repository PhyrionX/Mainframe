package principales;

import java.io.*;

/**
 * Created by Ruben on 27/01/2016.
 */
public class ConexionMainFrame {
    static Process process;
    private static InputStream in;
    private static PrintWriter out;

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
       /* escribir("string(2)");
        leerPantalla();
        escribir("enter()");
        escribir("string(1)");
        leerPantalla();
        escribir("enter()");
        leerPantalla();*/


    }
    public void escribir(String texto) {
        out.println(texto);
        out.flush();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {

        }
    }

    public String operaciones(String op, String... datos) {
        String pantalla = "";

        switch (op) {
            case "add":
                escribir("string(1)");
                leerPantalla();
                escribir("enter()");
                leerPantalla();
                escribir("string(1)");
                leerPantalla();
                escribir("enter()");
                leerPantalla();
                escribir("string(" + datos[0] + ")");
                leerPantalla();
                escribir("enter()");
                leerPantalla();
                escribir("string(" + datos[1] + ")");
                leerPantalla();
                escribir("enter()");
                leerPantalla();
                escribir("string(3)");
                leerPantalla();
                escribir("enter()");

                //TODO parte de mostrar tareas
                /*leerPantalla();
                escribir("string(2)");
                leerPantalla();
                escribir("enter()");
                leerPantalla();
                escribir("string(1)");
                leerPantalla();
                escribir("enter()");
                leerPantalla();
                escribir("string(1)");
                leerPantalla();
                escribir("enter()");
                leerPantalla();
                escribir("string(1)");
                leerPantalla();
                escribir("enter()");*/
                pantalla = leerPantalla();
                break;
            case "mostrar":
                leerPantalla();
                escribir("string(2)");
                leerPantalla();
                escribir("enter()");
                leerPantalla();
                escribir("string(1)");
                leerPantalla();
                escribir("enter()");
                leerPantalla();
                escribir("string(1)");
                leerPantalla();
                escribir("enter()");
                pantalla = leerPantalla();
                escribir("string(3)");
                leerPantalla();
                escribir("enter()");
                leerPantalla();
                break;
        }
        return pantalla;
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
        } catch (InterruptedException ex) {
        }
        return pantalla.replace("data:", "");
    }
}
