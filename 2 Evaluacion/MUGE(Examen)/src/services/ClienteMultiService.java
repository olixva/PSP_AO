package services;

import data.FirmaDigital;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ClienteMultiService extends Thread {

    private int id;
    private Socket s;
    private ServerSocket server;
    private PrintWriter flujoSalida;
    private Scanner flujoEntrada;
    private FirmaDigital firmaDigital;

    public ClienteMultiService(int id, Socket s, ServerSocket server) {
        this.id = id;
        this.s = s;
        this.server = server;
        start();
    }

    public void run() {

        System.out.println("Cliente " + id + " se ha conectado en el puerto " + s.getPort());
        try {
            // Enlazamos los flujos
            flujoSalida = new PrintWriter(s.getOutputStream(), true);
            flujoEntrada = new Scanner(s.getInputStream());
            flujoSalida.println("Bienvenido a MUGE");
            flujoSalida.flush();

            boolean salir = false;
            while (!salir) {
                // Leemos el comando
                String comando = flujoEntrada.nextLine();

                // Procesamos el comando
                if (comando.contains("quit")) {
                    System.out.println("El cliente " + id + " se ha desconectado");
                    salir = true;
                } else if (comando.contains("halt")) {
                    System.out.println("El servidor se ha detenido");
                    salir = true;
                    server.close();
                } else {
                    procesaComando(comando);
                }
            }
            // Cerramos la conexion
            flujoEntrada.close();
            flujoSalida.close();
            s.close();

        } catch (IOException | NoSuchElementException e) {
            System.out.println("El cliente ha cerrado la conexion con el socket " + id);
        }
    }

    private void procesaComando(String comando) {
        if (comando.contains("genera")) {
            firmaDigital = new FirmaDigital();
            flujoSalida.println("Claves generadas");
            flujoSalida.flush();
        } else if (comando.contains("publica")) {
            if (firmaDigital != null) {
            } else {
                flujoSalida.println("No se han generado las claves. Ejecute el comando genera");
            }
        } else if (comando.contains("privada")) {
            if (firmaDigital != null) {
                flujoSalida.println("Tu clave privada es: " + firmaDigital.getClavePrivada());
            } else {
                flujoSalida.println("No se han generado las claves. Ejecute el comando genera");
            }
        } else {
            flujoSalida.println("Comando no reconocido");
        }
    }
}




