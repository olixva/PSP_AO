import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Camion extends Thread {

    private int id;
    private Sincro sincro;
    private BufferedReader flujoLectura;
    private PrintWriter flujoEscritura;

    public Camion(int id, Sincro sincro, BufferedReader flujoLectura, PrintWriter flujoEscritura) {
        this.id = id;
        this.sincro = sincro;
        this.flujoLectura = flujoLectura;
        this.flujoEscritura = flujoEscritura;

        this.start();
    }

    @Override
    public void run() {
        // Inicio concurrente de los camiones
        sincro.camionListo();

        // Bucle que recibe los viajes y los realiza mientras no reciba la orden de fin
        boolean fin = false;
        while (!fin) {

            try {
                // Leemos el mensaje del pipe punto a punto
                String linea = flujoLectura.readLine();

                if (linea.contains("fin")) {
                    fin = true;
                    System.out.println("El camion " + id + " ha recibido la orden de terminar");

                } else {
                    int km = Integer.parseInt(linea);
                    System.out.println("El camion " + id + " ha recibido un viaje de " + km + " km");
                    Thread.sleep(km);
                    
                    // Enviamos la confirmacion de que hemos terminado el viaje
                    sincro.bloquearComunicaciones();
                    flujoEscritura.println("fin");
                    flujoEscritura.flush();
                    sincro.liberarComunicaciones();
                }

            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
