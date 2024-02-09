package PruebasHilos;

public class LanzadorHilo2 {

    public static void main(String[] args) {
        
        HiloSimple2 hs = new HiloSimple2();
        hs.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        hs.terminar();
    }
}
