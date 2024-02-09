package Cinta;

public class Consumidor extends Thread {
    static int nConsumidores = 0;

    private int id;
    private Cinta cinta;

    public Consumidor(Cinta miCinta) {
        this.cinta = miCinta;
        this.id = ++nConsumidores;
    }

    public void run() {
        char valor;
        for (int i = 1; i < 10; i++) {
            valor = cinta.recoger();
            System.out.println(
                    i + " Consumidor " + this.id + ": " + valor + ". Elementos en a cinta: " + cinta.getNumElementos());
            try {
                sleep(100);
            } catch (InterruptedException e) {
            }
        }
    }
}