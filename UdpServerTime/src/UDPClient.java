import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPClient {
    public static void main(String[] args) {
        final int port = 7777;
        String serverIP = "127.0.0.1";
        String datos;
        byte[] buffer;
        BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));

        try (DatagramSocket sUDP = new DatagramSocket()) {
            InetAddress host = InetAddress.getByName(serverIP);
            System.out.println("Cliente UDP funcionando...");

            boolean fin = false;
            while (!fin) {
                //Leemos la entrada del usuario
                datos = consola.readLine();
                buffer = datos.getBytes();

                //Construimos el DatagramPacket para enviar el mensaje al servidor
                DatagramPacket mensaje = new DatagramPacket(buffer, buffer.length, host, port);
                sUDP.send(mensaje);

            }

        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
