import java.io.IOException;
import java.net.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TimeServer {
    public static void main(String[] args) {
        final String inetAddress = "224.0.0.1";
        final int port = 7777;
        final int delay = 1000;

        int hh, mm, ss;


        try {
            InetAddress serverIP = InetAddress.getByName(inetAddress);

            DatagramSocket sUDP = new DatagramSocket();
            String mensaje;

            boolean fin = false;
            while (!fin) {
                //Construimos el mensaje
                Calendar miCalendario = new GregorianCalendar();
                hh = miCalendario.get(Calendar.HOUR_OF_DAY);
                mm = miCalendario.get(Calendar.MINUTE);
                ss = miCalendario.get(Calendar.SECOND);
                mensaje = "Clock> " + hh + ":" + mm + ":" + ss;

                //Construimos el DatagramPacket para enviar el mensaje al servidor
                DatagramPacket peticion = new DatagramPacket(mensaje.getBytes(), mensaje.length(), serverIP, port);
                System.out.println("Enviando: " + mensaje);
                sUDP.send(peticion);

                //Esperamos un tiempo
                Thread.sleep(delay);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}