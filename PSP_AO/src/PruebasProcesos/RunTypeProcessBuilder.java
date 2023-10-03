package PruebasProcesos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RunTypeProcessBuilder {

    public static void main(String[] args) throws IOException, InterruptedException {
        String[] comando = { "cmd", "/c", "type", "hola.txt" };
        ProcessBuilder pB = new ProcessBuilder(comando);

        Process p = pB.start();

        InputStreamReader iReader = new InputStreamReader(p.getInputStream());
        BufferedReader buffer = new BufferedReader(iReader);

        String linea;
        while ((linea = buffer.readLine()) != null) {
            System.out.println(linea);
        }
        buffer.close();
        
        int estado = p.waitFor();

        System.out.println("Terminado " + estado);
    }

}
