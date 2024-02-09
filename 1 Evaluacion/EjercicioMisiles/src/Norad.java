import java.io.PipedWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Norad {

    private static final int MAXIMO_MISILES = 100;

    public static void main(String[] args) {

        Sincroniza sincro = new Sincroniza(MAXIMO_MISILES); // Objeto para la Sincronizacion
        Misil[] misiles = new Misil[MAXIMO_MISILES]; // Array de misiles

        PipedWriter[] emisor = new PipedWriter[MAXIMO_MISILES]; // Array de emisores (Pipe)
        PrintWriter[] flujoSalida = new PrintWriter[MAXIMO_MISILES]; // Array de flujo de salida de texto
        for (int i = 0; i < misiles.length; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            emisor[i] = new PipedWriter();
            flujoSalida[i] = new PrintWriter(emisor[i]);

            misiles[i] = new Misil(i, sincro, emisor[i]);
        }

        sincro.esperarMisilesListos(); // Esperamos a que todos los misiles esten armados
        System.out.println("Todos los misiles operativos");

        Scanner sc = new Scanner(System.in);
        boolean fin = false;

        while (!fin) {
            System.out.print("Orden> ");
            String comando = sc.next();
            if (comando.contains("atacar")) {
                sincro.resetearContadores();
                sincro.realizarActivacion(); // Realizamos primera verificacion para lanzar
                dobleVerificacion(flujoSalida, comando);

                fin = true;
            } else {
                fin = true;
                sincro.realizarActivacion();
                dobleVerificacion(flujoSalida, comando);
            }

            sincro.esperarMisilesAcabados();

            System.out.println("Total aciertos: " + sincro.getAciertos() + " Total fallos: " + sincro.getFallos());
            System.out.println("Total misiles lanzados: " + MAXIMO_MISILES);
            System.out.println("Porcentaje de exito: " + sincro.getAciertos()*100/MAXIMO_MISILES + "%");
        }
    }

    private static void dobleVerificacion(PrintWriter[] flujoSalida, String mensaje) {
        for (PrintWriter flujo : flujoSalida) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flujo.println();
        }
    }
}
