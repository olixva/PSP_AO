import java.io.PipedWriter;
import java.io.PrintWriter;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Mesa extends Thread {

    CyclicBarrier inicio;
    Sincro sincro;
    Lock bloqueoPantalla;

    int id;
    Semaphore cocineros;

    PipedWriter pipeSalida;
    PrintWriter flujoS;

    public Mesa(CyclicBarrier inicio, int id, Semaphore cocineros, PipedWriter pipeSalida, Sincro sincro, Lock bloqueoPantalla) {
        this.inicio = inicio;
        this.id = id;
        this.cocineros = cocineros;
        this.pipeSalida = pipeSalida;
        this.sincro = sincro;

        this.bloqueoPantalla = bloqueoPantalla;
        flujoS = new PrintWriter(pipeSalida);

        this.start();
    }

    @Override
    public void run() {

        try {
            inicio.await(); // Espera a que todas esten creadas

            Random numeroAleatorio = new Random();
            boolean comandaTerminada = false;
            while (!comandaTerminada) {

                // Si consigue cocinero comen, terminan y notifican que han acabado.
                if (cocineros.tryAcquire()) {

                    bloqueoPantalla.lock();
                    System.out.println("Mesa " + id + " esta comiendo.");
                    bloqueoPantalla.unlock();

                    Thread.sleep(numeroAleatorio.nextInt(15000) + 5000);

                    bloqueoPantalla.lock();
                    System.out.println("Mesa " + id + " ha terminado.");
                    bloqueoPantalla.unlock();

                    sincro.notificarFinMesa();

                    comandaTerminada = true;
                    cocineros.release(); // Liberamos al cocinero.
                } else { // Si no cosigue cocinero espera y avisa al Metre
                    Thread.sleep(numeroAleatorio.nextInt(10000));
                    esperarCocinero();
                }
            }

        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public void esperarCocinero() {
        
        bloqueoPantalla.lock();

        System.out.println("Mesa " + id + " esperando cocinero");
        flujoS.println("Mesa " + id + " esperando cocinero");
        flujoS.flush();
        
        bloqueoPantalla.unlock();
    }

}
