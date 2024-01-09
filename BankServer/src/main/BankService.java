package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class BankService extends Thread {

    private int id;
    private Socket s;
    private PrintWriter flujoSalida;
    private Scanner flujoEntrada;

    public BankService(Socket s, int id) {
        this.id = id;
        this.s = s;
        this.start();
    }

    public void run() {

         System.out.println(s.getInetAddress().getHostAddress() + " se ha conectado al socket " + id);
        try {
            // Enlazamos los flujos
            flujoSalida = new PrintWriter(s.getOutputStream(), true);
            flujoEntrada = new Scanner(s.getInputStream());
            flujoSalida.println("Bienvenido a la banca online");

            boolean salir = false;
            while (!salir) {
                //Leemos el comando
                String comando = flujoEntrada.nextLine();
                flujoSalida.println("\nComando recibido: " + comando);

                //Si el comando es "close" cerramos la conexion
                if (comando.contains("quit")) {
                    salir = true;
                }

            }
            //Cerramos la conexion
            flujoEntrada.close();
            flujoSalida.close();
            s.close();

        } catch (IOException | NoSuchElementException e ) {
            System.out.println("El cliente ha cerrado la conexion con el socket " + id);
        }
    }
}
