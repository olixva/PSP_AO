import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Sincroniza {

    private int maximo_misiles;
    private CountDownLatch misilesListosCD;
    private CountDownLatch lanzarMisilesCD;
    private CountDownLatch misilesAcaban;

    private Semaphore lock = new Semaphore(1);
    private int aciertos;
    private int fallos;

    public Sincroniza(int maximoMisiles) {
        this.maximo_misiles = maximoMisiles;
        this.misilesListosCD = new CountDownLatch(maximoMisiles);
        this.lanzarMisilesCD = new CountDownLatch(1);
        this.misilesAcaban = new CountDownLatch(maximoMisiles);
    }

    public void esperarMisilesListos() {
        try {
            misilesListosCD.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void notificarMisileListo() {
        misilesListosCD.countDown();
    }

    public void esperarActivacion() {
        try {
            lanzarMisilesCD.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void realizarActivacion() {
        lanzarMisilesCD.countDown();
    }

    public synchronized void notificarAcierto() {
        try {
            lock.acquire();
            this.aciertos++;
            lock.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void notificarFallo() {
        try {
            lock.acquire();
            this.fallos++;
            lock.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resetearContadores() {
        try {
            lock.acquire();
            this.aciertos = 0;
            this.fallos = 0;
            lock.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getAciertos() {
        int aciertos = 0;
        try {
            lock.acquire();
            aciertos = this.aciertos;
            lock.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return aciertos;
    }

    public int getFallos() {
        int fallos = 0;
        try {
            lock.acquire();
            fallos = this.fallos;
            lock.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return fallos;
    }

    public void notificarMisilFinalizado() {
        misilesAcaban.countDown();
    }

    public void esperarMisilesAcabados() {
        try {
            misilesAcaban.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
