package ProductorConsumidor.main;

import ProductorConsumidor.domain.Buffer;

public class Productor2 extends Thread {
    private Buffer buffer;
    private final String letras = "ABDEFGHIJKLNOUPRSTUXWYZ";

    public Productor2(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            char c = letras.charAt((int) (Math.random() * letras.length()));
            buffer.poner(c);
            System.out.println(i + " Productor  2: " + c);
            try {
                sleep(400);
            } catch (InterruptedException e) {
            }
        }
    }
}
