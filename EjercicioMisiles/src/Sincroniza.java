import java.util.concurrent.CountDownLatch;

public class Sincroniza {

    private int maximo_misiles;
    private CountDownLatch misilesListosCD;
    private CountDownLatch lanzarMisilesCD; 

    public Sincroniza(int maximoMisiles) {
        this.maximo_misiles = maximoMisiles;
        this.misilesListosCD = new CountDownLatch(maximoMisiles);
        this.lanzarMisilesCD = new CountDownLatch(1);
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

}
