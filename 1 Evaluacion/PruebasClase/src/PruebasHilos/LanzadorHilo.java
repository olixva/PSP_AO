package PruebasHilos;

public class LanzadorHilo {
    public static void main(String[] args) {
        HiloSimple hs = new HiloSimple();
        Thread ts = new Thread(hs);
        ts.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        hs.stop();

    }
}
