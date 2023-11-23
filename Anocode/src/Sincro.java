import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Sincro {
    
    private CountDownLatch estacionesCreadas;
    private Semaphore radio;

    public Sincro(int maximo_estaciones) {
        this.estacionesCreadas = new CountDownLatch(maximo_estaciones);
        this.radio = new Semaphore(1);
    }

    public void notificarCreada() {
        estacionesCreadas.countDown();
    }

    public void esperarEstacionesCreadas() {
        try {
            estacionesCreadas.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void usarRadio() {
        try {
            radio.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void soltarRadio() {
        radio.release();
    }
}
