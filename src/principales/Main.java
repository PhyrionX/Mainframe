package principales;

import java.io.*;

public class Main {
    static Process process;
    private static InputStream in;
    private static PrintWriter out;

    public static void main(String[] arg) {
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

    public static void conectar() throws IOException, InterruptedException {

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
        escribir("string(2)");
        leerPantalla();
        escribir("enter()");
        escribir("string(1)");
        leerPantalla();
        escribir("enter()");
        leerPantalla();


    }
    public static void escribir(String texto) {
        out.println(texto);
        out.flush();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {

        }
    }
    public static void leerPantalla() {
        try {
            out.println("ascii()");
            out.flush();

            while (in.available() == 0) {
                Thread.sleep(100);
            }
            String pantalla = "";
            while (in.available() > 0) {

                pantalla +=  (char) in.read();

            }
            System.out.print(pantalla.replace("data:", ""));
        } catch (IOException ex) {
        } catch (InterruptedException ex) {
        }
    }
}
