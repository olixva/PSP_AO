package HilosSincronizados;

public class HiloSincro2 extends Thread {
    public static int contador = 0;

    private int idHilo;
    private Object candado;
    private boolean fin = false;

    public HiloSincro2(int i, Object candado) {
        this.idHilo = i;
        this.candado = candado;
        this.start();
    }

    @Override
    public void run() {

        while (!fin) {
            cambiarContador();
        }
    }

    private void cambiarContador() {
        synchronized (this.candado) {

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
}
