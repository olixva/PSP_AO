import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.PrintWriter;
import java.util.Random;

public class Estacion extends Thread {

    private int numeroEstacion;
    private Sincro sincro;
    private PrintWriter radio;
    private BufferedReader entradaOrdenes;

    public Estacion(Sincro sincro, int numeroEstacion, PipedWriter radio, PipedReader entradaAtaca) {
        this.sincro = sincro;
        this.numeroEstacion = numeroEstacion;

        this.radio = new PrintWriter(radio);
        this.entradaOrdenes = new BufferedReader(entradaAtaca);

        this.start();
    }

    @Override
    public void run() {
        sincro.notificarCreada();

        Random ramdom = new Random();
        boolean funcionando = true;

        while (funcionando) {

            if ((ramdom.nextInt(9) + 1) >= 8) {

                funcionando = false;

                sincro.usarRadio();
                radio.write(numeroEstacion);
                sincro.soltarRadio();
            } else {

                try {
                    Thread.sleep(ramdom.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        while (true) {
            try {

                if (entradaOrdenes.readLine().equalsIgnoreCase("Atacar")) {
                    System.out.println("Estacion numero " + numeroEstacion + " atacando.");
                } else {
                    System.out.println("La estacion " + numeroEstacion + " no ha atacado.");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
