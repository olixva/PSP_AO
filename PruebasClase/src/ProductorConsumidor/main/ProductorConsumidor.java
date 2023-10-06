package ProductorConsumidor.main;

import ProductorConsumidor.domain.Buffer;

public class ProductorConsumidor {
    public static void main(String[] args) {
        Buffer miBuffer = new Buffer();
        Productor productor = new Productor(miBuffer);
        Productor2 productor2 = new Productor2(miBuffer);
        Consumidor consumidor = new Consumidor(miBuffer);
        Consumidor consumidor2 = new Consumidor(miBuffer);

        System.out.println("Iniciamos Hilos");
        productor.start();
        productor2.start();
        consumidor.start();
        consumidor2.start();
        try {
            productor.join();
            consumidor.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
