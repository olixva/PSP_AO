package Cinta;

import java.util.ArrayDeque;
import java.util.Queue;

public class Cinta {

    private Queue<Character> cinta = new ArrayDeque<>();
    private int tamano;

    public Cinta(int tamano) {
        this.tamano = tamano;
    }

    public synchronized char recoger() {
        while (cinta.size() <= 9) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        notifyAll();
        return cinta.poll();
    }

    public synchronized void poner(char c) {
        while ((cinta.size() > 9) && (cinta.size() <= tamano)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        notifyAll();
        cinta.add(c);
    }

    public synchronized int getNumElementos() {
        return cinta.size();
    }
}

