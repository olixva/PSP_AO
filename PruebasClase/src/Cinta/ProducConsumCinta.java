package Cinta;

public class ProducConsumCinta {
    public static void main(String[] args) {
        Cinta cinta = new Cinta(10);
        Productor productor = new Productor(cinta);
        Consumidor consumidor = new Consumidor(cinta);
        Consumidor consumidor2 = new Consumidor(cinta);

        productor.start();
        consumidor.start();
        consumidor2.start();
    }
}
