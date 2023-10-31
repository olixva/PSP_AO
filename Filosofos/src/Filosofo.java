import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Filosofo extends Thread {
    private static final int MAX_COMIDAS = 3;
    int comidas = 0;

    private int id;
    CyclicBarrier inicio;

    Semaphore[] palilloEspera; // Semeforos asociados a los palillos
    int palilloIzq;
    int palilloDer;

    public Filosofo(int id, CyclicBarrier inicio, Semaphore[] palilloEspera, int[][] palillos) {
        this.id = id;
        this.inicio = inicio;
        this.palilloEspera = palilloEspera;

        palilloDer = palillos[id][0]; // Ponemos los palillos a los que puede acceder
        palilloIzq = palillos[id][1];

        this.start();
    }

    @Override
    public void run() {

        try {
            inicio.await(); // Esperamos a que todos esten creados
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

        int comidas = 0;
        while (comidas < MAX_COMIDAS) {
            pensar();
            comer();
            comidas++;
        }
        System.out.println("Filosofo " + id + " ha terminado");
    }

    private void comer() {
        // Intenta coger palillo izquierdo
        if ((palilloEspera[palilloIzq].tryAcquire())) {
            // Si lo coje intenta coger el palillo derecho
            if ((palilloEspera[palilloDer].tryAcquire())) {
                // Si lo coje come
                System.out.println("Filosofo " + id + " comiendo como cabron...");
                esperar();
                // Y suelta los dos
                palilloEspera[palilloIzq].release();
                palilloEspera[palilloDer].release();
            } else {
                //Si coje el izquierdo pero no el derecho, suelta el izquierdo
                palilloEspera[palilloIzq].release();
            }
        }
    }

    private void esperar() {
        Random aleatorio = new Random();
        int espera = aleatorio.nextInt(1000) + 500;

        try {
            Thread.sleep(espera); // Espera tiempo aleatorio
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void pensar() {
        Random aleatorio = new Random();
        int espera = aleatorio.nextInt(3000) + 1000;

        try {
            System.out.println("Filosofo " + id + " pensando...");// Piensa un tiempo aleatorio
            Thread.sleep(espera);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
