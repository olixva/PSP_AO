package main;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BankServer {
    public static void main(String[] args) {
        try {
            ServerSocket miServer = new ServerSocket(8888);
            Banco miBanco = new Banco(100);
            System.out.println("Server Running ---> Esperando conexiones...");

            int id = 0;
            while (true) {
                //Aceptamos la conexion
                Socket miSocket = miServer.accept();
                //Iniciamos el servicio que gestiona la conexion
                id++;
                BankService servicio = new BankService(miSocket, id);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
