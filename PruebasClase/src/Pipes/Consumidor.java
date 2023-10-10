package Pipes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;

public class Consumidor extends Thread {

    private BufferedReader flujoE;
    private boolean fin = false;

    public Consumidor(PipedReader receptor) {
        flujoE = new BufferedReader(receptor);
    }

    @Override
    public void run() {
        while (!fin) {
            String mensaje;
            try {
                mensaje = flujoE.readLine();
                System.out.println("Recibido " + mensaje);

            } catch (IOException e) {
                fin = true;
            }
        }
        System.out.println("No hay nada mas que leer");
    }

}
