package main;

import java.io.IOException;
import java.net.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MataTimeServer {
    public static void main(String[] args) {
        final String inetAddress = "224.0.20.0";
        final int port = 8020;
        final int delay = 2000;
        int nMensaje = 0;

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
                mensaje = "Mensaje " + nMensaje + ": Clock> " + hh + ":" + mm + ":" + ss;

                //Construimos el DatagramPacket para enviar el mensaje al servidor
                DatagramPacket peticion = new DatagramPacket(mensaje.getBytes(), mensaje.length(), serverIP, port);
                System.out.println("Enviando: " + mensaje);
                sUDP.send(peticion);

                //Esperamos un tiempo
                Thread.sleep(delay);

                // Incrementamos el número de mensaje
                nMensaje++;
                // Si el número de mensaje es 100, lo reiniciamos
                if (nMensaje == 100) {
                    nMensaje = 0;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}