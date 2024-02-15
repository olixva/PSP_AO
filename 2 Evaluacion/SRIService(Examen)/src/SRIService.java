import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SRIService extends Thread {

    private int id;
    private Socket s;
    private PrintWriter flujoSalida;
    private Scanner flujoEntrada;
    private SRIServiceUDP srIServiceUDP;

    public SRIService(Socket s, int id) {
        this.id = id;
        this.s = s;
        this.start();
    }

    public void run() {

        System.out.println(s.getInetAddress().getHostAddress() + " se ha conectado al socket " + id);
        try {
            // Enlazamos los flujos
            flujoSalida = new PrintWriter(s.getOutputStream(), true);
            flujoEntrada = new Scanner(s.getInputStream());
            flujoSalida.println("Bienvenido");

            boolean salir = false;
            while (!salir) {
                // Leemos el comando
                String comando = flujoEntrada.nextLine();

                if (comando.contains("quit")) {
                    salir = true;
                } else {
                    // Procesamos el comando
                    procesaComando(comando);
                }
            }
            // Cerramos la conexion
            flujoEntrada.close();
            flujoSalida.close();
            s.close();

        } catch (IOException | NoSuchElementException e) {
            System.out.println("El cliente ha cerrado la conexion con el socket " + id);
        }
    }

    private void procesaComando(String comando) {
        if (comando.contains("CLIENTE")){
            flujoSalida.println("CLIENTE: " + id);
        } else if (comando.contains("MENSA")){
            srIServiceUDP = new SRIServiceUDP(comando);
            srIServiceUDP.start();
        } else {
            flujoSalida.println("Comando no reconocido");
        }
    }
}
