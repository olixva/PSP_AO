package HilosSincronizados;

public class HiloSincro extends Thread {
    public static int contador = 0;

    private int idHilo;
    private boolean fin = false;

    public HiloSincro(int i) {
        this.idHilo = i;
        this.start();
    }

    @Override
    public void run() {

        while (!fin) {
            cambiarContador();
        }
    }

    private synchronized void cambiarContador() {
        if (this.idHilo == contador) {
            System.out.println("Hola soy " + idHilo);
            contador++;

        } else if (contador == 100) {
            contador = 0;
        }
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
