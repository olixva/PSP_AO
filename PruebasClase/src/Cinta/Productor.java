package Cinta;

public class Productor extends Thread {
    private Cinta cinta;
    private final String letras = "abcdefghijklmnopqrstuvxyz";
    

    public Productor(Cinta miCinta) {
        this.cinta = miCinta;
    }

    public void run() {
        for (int i = 1; i < 28; i++) {
            char c = letras.charAt((int) (Math.random() * letras.length()));
            cinta.poner(c);
            System.out.println(i + " Productor 1: " + c + ". Elementos en a cinta: " + cinta.getNumElementos());
            try {
                sleep(400);
            } catch (InterruptedException e) {
            }
        }
    }
}
