package Pipes;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class Main {
    public static void main(String[] args) {
        try {
            PipedWriter emisor = new PipedWriter();
            PipedReader receptor = new PipedReader(emisor);

            Productor pHilo = new Productor(emisor);
            Consumidor cHilo = new Consumidor(receptor);

            pHilo.start();
            cHilo.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
