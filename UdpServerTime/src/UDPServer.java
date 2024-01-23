import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer {
    public static void main(String[] args) {
        final int port = 7777;

        boolean fin = false;
        try (DatagramSocket sUDP = new DatagramSocket(port)) { //Para conectarse (ncat -u 127.0.0.1 7777)
            System.out.println("Servidor UDP iniciado, esperando peticiones...");

            while (!fin){
                //Construimos el DatagramPacket para recibir peticiones
                byte[] buffer = new byte[1024];
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);

                //Leemos una petición del DatagramSocket
                sUDP.receive(peticion);
                System.out.println("Petición recibida desde: " + peticion.getAddress() + ":" + peticion.getPort());

                //Enviamos un echo como respuesta
                DatagramPacket respuesta = new DatagramPacket(peticion.getData(), peticion.getLength(), peticion.getAddress(), peticion.getPort());
                sUDP.send(respuesta);

            }

        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
