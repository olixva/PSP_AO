import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;

public class Metre extends Thread {

    PipedReader pipeEntrada;
    BufferedReader flujoE;

    boolean fin = false;
    
    public Metre(PipedReader pipeEntrada) {
        this.pipeEntrada = pipeEntrada;
        flujoE = new BufferedReader(pipeEntrada);

        this.start();
    }

    @Override
    public void run() {

        while (!fin) {
            try {
                String mensaje = flujoE.readLine();
                System.out.println("Recibido: " + mensaje);
            } catch (IOException e) {
            }
        }

    }
    
    public void finalizar() {
        fin = true;
    }
    
}
