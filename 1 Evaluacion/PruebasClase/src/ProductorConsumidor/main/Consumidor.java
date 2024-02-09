package ProductorConsumidor.main;

import ProductorConsumidor.domain.Buffer;

public class Consumidor extends Thread {
    private Buffer buffer;
    int id = 1;

    public Consumidor(Buffer miBuffer) {
        this.buffer = miBuffer;
        id++;
    }

    public void run() {
        char valor;
        for (int i = 0; i < 10; i++) {
            valor = buffer.recoger();
            System.out.println(i + " Consumidor "+ id + ": " + valor);
            try {
                sleep(100);
            } catch (InterruptedException e) {
            }
        }
    }
}