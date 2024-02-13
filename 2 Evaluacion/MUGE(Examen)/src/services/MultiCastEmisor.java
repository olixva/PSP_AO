package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;
import java.net.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.Semaphore;

public class MultiCastEmisor extends Thread {

    private BufferedReader tuberiaLectura;

    public MultiCastEmisor(PipedReader tuberiaEscritura) {
        this.tuberiaLectura = new BufferedReader(tuberiaEscritura);
        start();
    }

    @Override
    public void run() {
        final String inetAddress = "224.0.0.1";
        final int port = 7777;

        int hh, mm, ss;

        try {
            InetAddress serverIP = InetAddress.getByName(inetAddress);

            DatagramSocket sUDP = new DatagramSocket();
            String clave;
            String mensaje;

            boolean fin = false;
            while (!fin) {
                //Construimos el mensaje
                clave = tuberiaLectura.readLine();
                Calendar miCalendario = new GregorianCalendar();
                hh = miCalendario.get(Calendar.HOUR_OF_DAY);
                mm = miCalendario.get(Calendar.MINUTE);
                ss = miCalendario.get(Calendar.SECOND);
                mensaje = "Clave publica: " + clave + " Clock> " + hh + ":" + mm + ":" + ss;

                //Construimos el DatagramPacket para enviar el mensaje al servidor
                DatagramPacket peticion = new DatagramPacket(mensaje.getBytes(), mensaje.length(), serverIP, port);
                System.out.println("Enviando: " + mensaje);
                sUDP.send(peticion);

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
