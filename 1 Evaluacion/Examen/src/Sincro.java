import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Sincro {

    private CyclicBarrier inicioCamiones;
    private CountDownLatch finCamiones;
    private CountDownLatch finCentral;
    private Semaphore semaforo;

    public Sincro(int maxCamiones) {
        this.inicioCamiones = new CyclicBarrier(maxCamiones);
        this.finCamiones = new CountDownLatch(1);
        this.finCentral = new CountDownLatch(1);
        this.semaforo = new Semaphore(1);
    }

    public void camionListo() {
        try {
            inicioCamiones.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void esperarFinCamiones() {
        try {
            finCamiones.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void esperarFinCentral() {
        try {
            finCentral.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void terminarCamiones() {
        finCamiones.countDown();
    }

    public void terminarCentral() {
        finCentral.countDown();
    }

    public void bloquearComunicaciones() {
        try {
            semaforo.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void liberarComunicaciones() {
        semaforo.release();
    }
}
