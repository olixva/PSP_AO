package PruebasHilos;

public class HiloSimple2 extends Thread {
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

    public void terminar() {
        on = false;
    }
}
