package main;


import services.ClienteMultiService;
import services.MultiCastEmisor;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Semaphore;

public class MugeServer {
    private static boolean salir;
    public static void main(String[] args) {
        try {
            ServerSocket miServer = new ServerSocket(9920);
            System.out.println("Servicio de claves MUGE en el puerto...  " + miServer.getLocalPort());

            //Iniciamos los pipes de comunicacion entre ClienteService y MultiCastEmisor
            PipedWriter tuberiaEscritura = new PipedWriter();
            PipedReader tuberiaLectura = new PipedReader(tuberiaEscritura);
            Semaphore escrituraPipe = new Semaphore(1);

            // Iniciamos el emisor multicast
            MultiCastEmisor emisor = new MultiCastEmisor(tuberiaLectura);

            int id = 1;
            salir = false;
            while (!salir) {
                //Aceptamos la conexion
                Socket miSocket = miServer.accept();

                //Iniciamos el servicio que gestiona la conexion
                ClienteMultiService clienteService = new ClienteMultiService(id, miSocket, tuberiaEscritura, escrituraPipe);
                id++;
            }

            //Cerramos los flujos
            tuberiaLectura.close();
            tuberiaEscritura.close();
            miServer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void detenerServidor() {
        salir = true;
    }
}
