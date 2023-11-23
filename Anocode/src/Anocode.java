import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Anocode {

    private static int MAXIMO_ESTACIONES = 10;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Sincro sincro = new Sincro(MAXIMO_ESTACIONES);
        Estacion[] escucha = new Estacion[MAXIMO_ESTACIONES];
        
        try {
            //Comunicacion de la estacion al Anocode por radio (un solo pipe)
            PipedWriter pipeSalida = new PipedWriter();
            PipedReader pipeEntrada = new PipedReader(pipeSalida);
            BufferedReader radio = new BufferedReader(pipeEntrada);

            //Comunicacion del Anocode a cada uno de las estaciones (muchos pipes)
            PipedWriter[] pipesEmisores = new PipedWriter[MAXIMO_ESTACIONES];
            PrintWriter[] emisores = new PrintWriter[MAXIMO_ESTACIONES];
            

            for (int i = 0; i < escucha.length; i++) {
                pipesEmisores[i] = new PipedWriter();
                emisores[i] = new PrintWriter(pipesEmisores[i]);

                escucha[i] = new Estacion(sincro, i, pipeSalida, new PipedReader(pipesEmisores[i]));
            }

            sincro.esperarEstacionesCreadas();
            System.out.println("Todas las estaciones operativas");

            boolean terminado = false;
            while (!terminado) {
                if (radio.ready()) {
                    int estacion = radio.read();
                    System.out.println(estacion + " ha detectado movimiento.\nIntroduce la accion a realizar: ");

                    String mensaje = sc.nextLine();
                    emisores[estacion].println(mensaje);
                }
            }
        
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
}
