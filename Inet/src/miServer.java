import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class miServer {
    public static void main(String[] args) {
        try {
            ServerSocket miServer = new ServerSocket(8888);
            System.out.println("Server Running ---> Esperando conexiones...");

            while (true) {
                Socket miSocket = miServer.accept();
                System.out.println(miSocket.getInetAddress().getHostAddress() + " se ha conectado");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
