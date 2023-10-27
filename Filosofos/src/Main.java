import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) throws Exception {
        int nFilosofos = 5;

        CyclicBarrier inicio = new CyclicBarrier(nFilosofos); // Para iniciarlos todos a la vez

        Filosofo[] filosofos = new Filosofo[nFilosofos];
        Semaphore[] palilloEspera = new Semaphore[nFilosofos];

        int[][] palillos = { { 0, 4 }, { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 } };

        // Inicializamos los filosofos
        int id = 0;
        for (Filosofo filosofo : filosofos) {
            filosofo = new Filosofo(id, inicio, palilloEspera, palillos);
            id++;
        }

        // Inicializamos los semaforos asociados a los palillos
        for (int i = 0; i < palilloEspera.length; i++) {
            palilloEspera[i] = new Semaphore(1);
        }
    }
}
