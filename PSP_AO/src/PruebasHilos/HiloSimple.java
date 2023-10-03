package PruebasHilos;

public class HiloSimple implements Runnable {

    private boolean on = true;

    @Override
    public void run() {
        while (on) {
            System.out.println("Hola soy un hilo simple");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        on = false;
    }

}
