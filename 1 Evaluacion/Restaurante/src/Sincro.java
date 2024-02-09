import java.util.concurrent.CountDownLatch;

public class Sincro {

    CountDownLatch finMesas;
    
    public Sincro(int nMesas) {
        this.finMesas = new CountDownLatch(nMesas);
    }

    public void notificarFinMesa() {
        finMesas.countDown();
    }

    public void esperarFinMesa() {
        
        try {
            finMesas.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    
}
