package ProductorConsumidor.main;

import ProductorConsumidor.domain.Buffer;

public class Consumidor2 extends Thread {
    private Buffer buffer;

    public Consumidor2(Buffer miBuffer) {
        this.buffer = miBuffer;
    }

    public void run() {
        char valor;
        for (int i = 0; i < 20; i++) {
            valor = buffer.recoger();
            System.out.println(i + " Consumidor 2: " + valor);
            try {
                sleep(100);
            } catch (InterruptedException e) {
            }
        }
    }
}