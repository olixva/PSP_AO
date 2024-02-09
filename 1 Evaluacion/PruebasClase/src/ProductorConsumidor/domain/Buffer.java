package ProductorConsumidor.domain;

public class Buffer {
    private char contenido;
    private boolean disponible;

    public Buffer() {
        this.contenido = ' ';
        this.disponible = false;
    }

    public synchronized char recoger() {
        while (!disponible) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        disponible = false;
        notifyAll();
        return contenido;
    }

    public synchronized void poner(char c) {
        while (disponible) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        contenido = c;
        notifyAll();
        disponible = true;
    }
}
