package domain;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MesaService extends Thread {

    private int id;
    private Socket s;
    private int mesaId;
    private PrintWriter flujoSalida;
    private Scanner flujoEntrada;

    public MesaService(int id, Socket miSocket) {
        this.id = id;
        this.s = miSocket;
        this.mesaId = 0;
        this.start();
    }

    public void run() {

        System.out.println("Funcionario " + id + " se ha conectado al socket " + id);
        try {
            // Enlazamos los flujos
            flujoSalida = new PrintWriter(s.getOutputStream(), true);
            flujoEntrada = new Scanner(s.getInputStream());
            flujoSalida.println("Bienvenido a SATAN");

            boolean salir = false;
            while (!salir) {
                // Limpiamos el buffer
                flujoEntrada.nextLine();
                // Leemos el comando
                String comando = flujoEntrada.next();

                if (comando.contains("mesa")) {

                    mesaId = flujoEntrada.nextInt(); // Para seleccionar la mesa
                    if (mesaId > 0) {
                        flujoSalida.println("Mesa " + mesaId + " seleccionada");
                    } else {
                        flujoSalida.println("Error al seleccionar la mesa");
                    }

                } else if (mesaId > 0) { // Solo procesamos comandos si hay una mesa seleccionada
                    // Procesamos el comando
                    procesaComando(comando);
                } else {
                    flujoSalida.println("No hay ninguna mesa seleccionada");
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

    }
}



