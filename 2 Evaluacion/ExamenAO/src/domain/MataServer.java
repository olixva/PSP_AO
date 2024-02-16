package domain;

import domain.MataService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MataServer {
    private final int MAX_CLIENTS;
    private static int clientesConectados;

    public MataServer(int maxClients) {
        this.MAX_CLIENTS = maxClients;
        this.clientesConectados = 0;
    }

    public void iniciar() {
        try {
            ServerSocket miServer = new ServerSocket(8020);
            System.out.println("Server Running at " + miServer.getLocalPort() + " ---> Esperando conexiones...");

            boolean fin = false;
            while (!fin) {

                //Aceptamos la conexion y iniciamos el servicio que gestiona la conexion
                Socket miSocket = miServer.accept();
                if (clientesConectados < MAX_CLIENTS){
                    MataService servicio = new MataService(clientesConectados, miSocket);
                    clientesConectados++;
                } else {
                    System.out.println("Maximo de clientes alcanzado");
                    miSocket.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void DesconectarCliente(int id) {
        System.out.println("Cliente " + id + " desconectado");
        clientesConectados--;
    }
}
