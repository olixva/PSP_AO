import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Anocode {

    private static int MAXIMO_ESTACIONES = 2;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Sincro sincro = new Sincro(MAXIMO_ESTACIONES);
        Estacion[] escucha = new Estacion[MAXIMO_ESTACIONES]; //Creamos un array para las estaciones

        try {
            // Comunicacion de la estacion al Anocode por radio (un solo pipe)
            PipedWriter pipeSalida = new PipedWriter(); //Lo tendran las estaciones para enviar mensajes
            PipedReader pipeEntrada = new PipedReader(pipeSalida);
            BufferedReader radio = new BufferedReader(pipeEntrada);//Lo tendra Anocode para recibir

            // Comunicacion del Anocode a cada uno de las estaciones (muchos pipes)
            PipedWriter[] pipesEmisores = new PipedWriter[MAXIMO_ESTACIONES];
            PrintWriter[] emisores = new PrintWriter[MAXIMO_ESTACIONES];//Lo tendra el anocode para enviar ordenes 

            for (int i = 0; i < escucha.length; i++) {
                pipesEmisores[i] = new PipedWriter(); //Rellenamos el array de PipesEmisores
                emisores[i] = new PrintWriter(pipesEmisores[i]); //Usando los PipesEmisores creamos flujos de salida

                //A la estacion le mandamos la "radio emisora" (pipeSalida) y el Flujo de entrada
                escucha[i] = new Estacion(sincro, i, pipeSalida, new PipedReader(pipesEmisores[i]));
            }

            //Esperamos a que todas esten creadas
            sincro.esperarEstacionesCreadas();
            System.out.println("Todas las estaciones operativas\n");

            //Esperamos a que detecten movimiento y devolvemos la orden por el pipe correspondiente a la estacion
            int i = MAXIMO_ESTACIONES;
            while (i > 0) {
                int estacion = radio.read();
                System.out.println(estacion + " ha detectado movimiento.\nIntroduce la accion a realizar: ");

                emisores[estacion].println(sc.nextLine());
                System.out.println("Enviando orden de ataque...");

                System.out.println("Anocode operativo");
                i--;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
