package PruebasHilos;

public class LanzadorReloj {

    public static void main(String[] args) {
        HiloReloj hTac = new HiloReloj("Tic");
        hTac.start();

        HiloReloj hTic = new HiloReloj("Tac");
        hTic.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        hTac.terminar();
        hTic.terminar();
    }
}
