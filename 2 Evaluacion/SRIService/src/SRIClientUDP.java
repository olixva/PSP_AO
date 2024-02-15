import java.io.IOException;
import java.net.*;

public class SRIClientUDP {
    public static void main(String[] args) {
        String serverIP = "224.0.0.1";
        int serverPort = 7777;

        try {
            InetAddress multicastAddr = InetAddress.getByName(serverIP);
            InetSocketAddress group = new InetSocketAddress(multicastAddr, serverPort);

            MulticastSocket sUDPMulti = new MulticastSocket(serverPort);
            NetworkInterface netIf = NetworkInterface.getByName("WiFi");

            sUDPMulti.joinGroup(group, netIf);

            boolean fin = false;
            byte[] buffer = new byte[1024];
            while (!fin) {
                //Construimos el DatagramPacket para recibir peticiones
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);

                //Leemos una petición del DatagramSocket
                sUDPMulti.receive(peticion);
                System.out.println("Petición recibida desde: " + peticion.getAddress() + ":" + peticion.getPort());
                System.out.println(new String(peticion.getData(), 0, peticion.getLength()));

            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
