package main;

import domain.MesaService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class CitasServer {
    public static void main(String[] args) {
        try {
            List<Socket> clientesRemotos = new LinkedList<>();
            ServerSocket miServer = new ServerSocket(8888);
            System.out.println("Servicio de Atencion al Alumno Numerico (SATAN) en el puerto...  " + miServer.getLocalPort());

            int id = 1;
            while (true) {

                //Aceptamos la conexion
                Socket miSocket = miServer.accept();

                //Agregar el socket a la lista de clientes
                clientesRemotos.add(miSocket);
                System.out.println("Funcionario " + id + " conectado desde " + miSocket.getInetAddress().getHostAddress());

                //Iniciamos el servicio que gestiona la conexion
                MesaService mesaService = new MesaService(id, miSocket);
                id++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
