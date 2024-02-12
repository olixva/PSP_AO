package main;


import services.ClienteMultiService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MugeServer {
    public static void main(String[] args) {
        try {
            ServerSocket miServer = new ServerSocket(9920);
            System.out.println("Servicio de claves MUGE en el puerto...  " + miServer.getLocalPort());

            int id = 1;
            boolean salir = false;
            while (!salir) {

                //Aceptamos la conexion
                Socket miSocket = miServer.accept();

                //Iniciamos el servicio que gestiona la conexion
                ClienteMultiService clienteService = new ClienteMultiService(id, miSocket, miServer);
                id++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
