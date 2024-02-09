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
    private Banco miBanco;
    private int cuenta;

    public BankService(Socket s, int id, Banco miBanco) {
        this.id = id;
        this.s = s;
        this.miBanco = miBanco;
        this.cuenta = -1;
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
                // Limpiamos el buffer
                flujoEntrada.nextLine();
                // Leemos el comando
                String comando = flujoEntrada.next();

                if (comando.contains("quit")) {
                    salir = true;
                } else if (comando.contains("cuenta")) {
                    cuenta = flujoEntrada.nextInt();
                    String salida = (cuenta >= 0) ? "Cuenta " + cuenta + " seleccionada" : "Cuenta no valida";
                    flujoSalida.println(salida);
                } else {
                    // Procesamos el comando
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
        int importe = 0;
        
        if (cuenta == -1) {
            flujoSalida.println("No hay ninguna cuenta seleccionada");
            return;
        }

        switch (comando) {

            case "ingreso":
                importe = flujoEntrada.nextInt();
                miBanco.setIngreso(cuenta, importe);
                flujoSalida.println("Se han ingresado " + importe + " euros en la cuenta " + cuenta);
                break;

            case "saldo":
                flujoSalida.println("La cuenta " + cuenta + " tiene un saldo de " + miBanco.getSaldo(cuenta));
                break;

            case "reintegro":
                importe = flujoEntrada.nextInt();
                int cantidad = miBanco.getReintegro(cuenta, importe);
                if (cantidad == -1) {
                    flujoSalida.println("No hay suficiente saldo, saldo actual " + miBanco.getSaldo(cuenta) + " euros");
                } else {
                    flujoSalida.println("Se han retirado " + cantidad + " euros de la cuenta " + cuenta);
                    flujoSalida.println("El saldo actual es de " + miBanco.getSaldo(cuenta));
                }
                break;

            default:
                flujoSalida.println("Comando no reconocido");
                break;
        }
    }
}
