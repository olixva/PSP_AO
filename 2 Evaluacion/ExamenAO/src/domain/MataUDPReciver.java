package domain;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;

public class MataUDPReciver extends Thread {
    private Socket s; //Socket del cliente receptor TCP
    private PrintWriter flujoSalida; //Flujo de salida TCP
    private boolean fin;

    public MataUDPReciver(Socket s) {
        this.s = s;
        fin = false;
    }
    public void run() {
        String serverIP = "224.0.20.0";
        int serverPort = 8020;

        try {
            InetAddress multicastAddr = InetAddress.getByName(serverIP);
            InetSocketAddress group = new InetSocketAddress(multicastAddr, serverPort);

            MulticastSocket sUDPMulti = new MulticastSocket(serverPort);
            NetworkInterface netIf = NetworkInterface.getByName("WiFi");

            sUDPMulti.joinGroup(group, netIf);

            byte[] buffer = new byte[1024];
            while (!fin) {
                //Construimos el DatagramPacket para recibir peticiones
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);

                //Leemos una petición del DatagramSocket
                sUDPMulti.receive(peticion);
                System.out.println("Petición recibida: ");
                System.out.println(new String(peticion.getData(), 0, peticion.getLength()));

                //La enviamos al cliente TCP
                System.out.println("Enviando al cliente TCP...");
                flujoSalida = new PrintWriter(s.getOutputStream(), true);
                flujoSalida.println(new String(peticion.getData(), 0, peticion.getLength()));

            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fin() {
        fin = true;
    }
}
