package PruebasHilos;

public class HiloReloj extends Thread{
    private boolean on = true;
    private String mensaje;

    public HiloReloj(String mensaje) {
        this.mensaje = mensaje; 
    }

    @Override
    public void run() {
        while (on) {

            System.out.println(this.mensaje);
            try {
                Thread.sleep((int)(Math.random()*1000+1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void terminar() {
        on = false;
    }
}
