import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.time.LocalDate;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Restaurante {

    static final int MAX_MESAS = 20;
    static final int MAX_COCINEROS = 8;

    public static void main(String[] args) {

        System.out.println("Iniciando servicio restaurante...");
        System.out.println("Antonio Oliva Carceles " + LocalDate.now() + "\n-----------------------------------");

        long miliInicio = System.currentTimeMillis(); // Obtenemos los ms de inicio
        try {
            Sincro sincro = new Sincro(MAX_MESAS);

            PipedWriter pipeSalida = new PipedWriter();
            PipedReader pipeEntrada = new PipedReader(pipeSalida);

            // Inicializamos el metre de la sala
            Metre metre = new Metre(pipeEntrada);

            // Inicialiazamos los cocineros
            Semaphore cocineros = new Semaphore(MAX_COCINEROS);

            //Creamos el candado para imprimir por pantalla
            Lock lock = new ReentrantLock();
            
            // Iniciclizamos concurrentemente las mesas
            CyclicBarrier inicioMesas = new CyclicBarrier(MAX_MESAS);
            for (int i = 0; i < MAX_MESAS; i++) {
                new Mesa(inicioMesas, i, cocineros, pipeSalida, sincro, lock);
            }

            // Esperamos a que terminen todas las mesas
            sincro.esperarFinMesa();
            metre.finalizar(); // Finalizamos la instancia Metre

            // Mostramos estadisticas
            long tiempoSimulacion = System.currentTimeMillis() - miliInicio;
            System.out.println("---------------------------- \nSimulacion terminada");
            System.out.println("La simulacion ha durado " + tiempoSimulacion + " ms.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
