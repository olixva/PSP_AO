import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Random;

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
                
                Random aleatorio = new Random();
                Thread.sleep(aleatorio.nextInt(0, 500));
                if (aleatorio.nextInt(2) > 0) {
                    System.out.println("Misil " + id + " acierta.");
                    sincro.notificarAcierto();
                } else {
                    System.out.println("Misil " + id + " fallaa.");
                    sincro.notificarFallo();
                }
            } else {
                System.out.println("Misil " + id + " abortado.");
            }

            sincro.notificarMisilFinalizado();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
