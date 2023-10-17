import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class Misil extends Thread {
    private int id;

    private Sincroniza sincro;
    private PipedReader receptor ;
    private BufferedReader flujoEntrada;

    public Misil(int id, Sincroniza sincro, PipedWriter emisor) {
        try {
            this.id = id;

            this.receptor = new PipedReader(emisor);
            this.flujoEntrada = new BufferedReader(receptor);
            this.sincro = sincro;
            this.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public void run() {
        //Informamos de que esta listo
        System.out.println("Misil " + id + " armado.");
        sincro.notificarMisileListo();
        //Esperamos activacion del NORAD
        sincro.esperarActivacion();
        System.out.println("Misil " + id + " esperando doble verificacion.");
        
        try {
            String comando = flujoEntrada.readLine();
            if (comando.contains("atacar")) {
                System.out.println("Misil " + id + " lanzado.");
            } else {
                System.out.println("Misil " + id + " abortado.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
