import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Central extends Thread {

    private int maxCamiones;
    private Sincro sincro;
    private PrintWriter[] flujoEscritura;
    private BufferedReader flujoLectura;

    private int[] km;

    private Random random;

    public Central(int maxCamiones, Sincro sincro, PrintWriter[] flujoEscritura, BufferedReader flujoLectura) {
        this.maxCamiones = maxCamiones;
        this.sincro = sincro;
        this.km = new int[maxCamiones];
        this.flujoEscritura = flujoEscritura;
        this.flujoLectura = flujoLectura;

        this.random = new Random();

        this.start();
    }

    @Override
    public void run() {

        //Asignamos los viajes a los camiones
        for (int i = 0; i < maxCamiones; i++) {
            int numeroCamion = random.nextInt(maxCamiones);
            int numeroKm = random.nextInt(500, 999);

            flujoEscritura[numeroCamion].println(numeroKm);

            try {
                flujoLectura.readLine();
                km[numeroCamion] += numeroKm;
                System.out.println("El camion " + numeroCamion + " ha recorrido " + numeroKm + " km\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //Informamos a Tranca de que han terminado los camiones para que imprima la facturacion
        sincro.terminarCamiones();

        //Esperamos a que Tranca termine de imprimir la facturacion
        sincro.esperarFinCentral();

        //Informamos a los camiones de que terminen
        this.terminarCamiones();
    }

    public int getKm(int i) {
        return km[i];
    }

    private void terminarCamiones() {
        System.out.println("\nEnviamos orden de terminar a todos los camiones");

        for (int i = 0; i < maxCamiones; i++) {
            flujoEscritura[i].println("fin");
            flujoEscritura[i].flush();
        }
    }
}
