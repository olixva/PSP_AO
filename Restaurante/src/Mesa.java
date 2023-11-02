import java.io.PipedWriter;
import java.io.PrintWriter;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Mesa extends Thread {

    CyclicBarrier inicio;
    Sincro sincro;
    Semaphore bloqueoPantalla;

    int id;
    Semaphore[] cocineros;

    PipedWriter pipeSalida;
    PrintWriter flujoS;

    public Mesa(CyclicBarrier inicio, int id, Semaphore[] cocineros, PipedWriter pipeSalida, Sincro sincro) {
        this.inicio = inicio;
        this.id = id;
        this.cocineros = cocineros;
        this.pipeSalida = pipeSalida;
        this.sincro = sincro;

        this.bloqueoPantalla = new Semaphore(1);
        flujoS = new PrintWriter(pipeSalida);

        this.start();
    }

    @Override
    public void run() {

        try {
            inicio.await();

            Random numeroAleatorio = new Random();
            boolean terminada = false;
            while (!terminada) {

                for (int i = 0; i < cocineros.length && !terminada; i++) {

                    if (cocineros[i].tryAcquire()) {
                        Thread.sleep(numeroAleatorio.nextInt(1500) + 500);
                        System.out.println("Mesa " + id + " ha terminado.");
                        sincro.notificarFinMesa();

                        terminada = true;
                        cocineros[i].release();
                    }
                }
                
                if (!terminada) {
                    Thread.sleep(numeroAleatorio.nextInt(1000));
                    esperarCocinero();
                }

            }

        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

    }

    public void esperarCocinero() {
        try {
            bloqueoPantalla.acquire();
            System.out.println("Mesa " + id + " esperando cocinero");
            flujoS.println("Mesa " + id + " esperando cocinero");
            bloqueoPantalla.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
