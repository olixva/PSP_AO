package domain;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MataService extends Thread {

    private int id;
    private Socket s;
    private PrintWriter flujoSalida;
    private Scanner flujoEntrada;
    private MataUDPReciver udpReciver;

    public MataService(int id, Socket s) {
        this.id = id;
        this.s = s;
        this.start();
    }

    public void run() {

        System.out.println("Cliente " + id + " conectado");
        try {
            // Enlazamos los flujos
            flujoSalida = new PrintWriter(s.getOutputStream(), true);
            flujoEntrada = new Scanner(s.getInputStream());
            flujoSalida.println("Bienvenido introduzca comando (START, STOP, QUIT)");

            boolean salir = false;
            while (!salir) {
                // Leemos el comando
                String comando = flujoEntrada.nextLine();

                if (comando.toLowerCase().contains("quit")) {
                    salir = true;
                    if (udpReciver != null) {
                        udpReciver.fin();
                    }
                    MataServer.DesconectarCliente(id);
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

        if (comando.toLowerCase().contains("start") && udpReciver == null) {
            udpReciver = new MataUDPReciver(s);
            udpReciver.start();
            flujoSalida.println("Servicio UDP iniciado");
        } else if (comando.toLowerCase().contains("start") && udpReciver != null) {
            flujoSalida.println("Servicio UDP ya iniciado");
        } else if (comando.toLowerCase().contains("stop")) {
            if (udpReciver != null) {
                udpReciver.fin();
                flujoSalida.println("Servicio UDP detenido");
            } else {
                flujoSalida.println("No hay servicio UDP iniciado");
            }
        } else {
            flujoSalida.println("Comando no reconocido");
        }

    }
}
