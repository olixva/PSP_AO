import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SRIServer {

    public static void main(String[] args) {
        try {
            ServerSocket miServer = new ServerSocket(8899);
            System.out.println("Server Running at " + miServer.getLocalPort() + " ---> Esperando conexiones...");

            int id = 0;
            boolean fin = false;
            while (!fin) {
                //Aceptamos la conexion
                Socket miSocket = miServer.accept();
                //Iniciamos el servicio que gestiona la conexion
                id++;
                SRIService servicio = new SRIService(miSocket, id);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
