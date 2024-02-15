import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerUDPMulti extends Thread{
    final String inetAddress = "224.0.0.1";
    final int port = 7777;
    private String mensaje;

    public ServerUDPMulti(String mensaje) {
        this.mensaje = mensaje;
    }

    public void run() {
        try {
            InetAddress serverIP = InetAddress.getByName(inetAddress);

            DatagramSocket sUDP = new DatagramSocket();

            //Construimos el mensaje


            //Construimos el DatagramPacket para enviar el mensaje al servidor
            DatagramPacket peticion = new DatagramPacket(mensaje.getBytes(), mensaje.length(), serverIP, port);
            System.out.println("Enviando: " + mensaje);
            sUDP.send(peticion);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
