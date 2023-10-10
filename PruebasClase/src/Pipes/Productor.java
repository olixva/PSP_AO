package Pipes;

import java.io.PipedWriter;
import java.io.PrintWriter;

public class Productor extends Thread {

    private PrintWriter flujoS;

    public Productor(PipedWriter emisor) {
        flujoS = new PrintWriter(emisor);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            String mensaje = "Hola " + i;
            flujoS.println(mensaje);
        }
    }

    
}
