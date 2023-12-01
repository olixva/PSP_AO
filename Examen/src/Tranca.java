import java.io.BufferedReader;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.PrintWriter;

public class Tranca {

    private static final int MAX_CAMIONES = 10;
    private static final float PRECIO_KM = 0.2f;

    public static void main(String[] args) throws Exception {

        // Guardamos tiempo de inicio
        long tiempoInicio = System.currentTimeMillis();

        // Creamos los pipes y sus respectivos flujos para la comunicacion entre central
        // y camiones
        PipedWriter[] tuberiasEscritura = new PipedWriter[MAX_CAMIONES];
        PipedReader[] tuberiasLectura = new PipedReader[MAX_CAMIONES];

        PrintWriter[] flujoEscritura = new PrintWriter[MAX_CAMIONES];
        BufferedReader[] flujoLectura = new BufferedReader[MAX_CAMIONES];

        // Creamos los pipes y flujos para la comunicacion compartida entre camiones y
        // central
        PipedWriter tuberiaEscrituraCentral = new PipedWriter();
        PipedReader tuberiaLecturaCentral = new PipedReader(tuberiaEscrituraCentral);

        PrintWriter flujoEscrituraCompartido = new PrintWriter(tuberiaEscrituraCentral);
        BufferedReader flujoLecturaCompartido = new BufferedReader(tuberiaLecturaCentral);

        // Creamos las variables y las inicializamos
        Sincro sincro = new Sincro(MAX_CAMIONES);
        Camion[] camiones = new Camion[MAX_CAMIONES];
        Central central = new Central(MAX_CAMIONES, sincro, flujoEscritura, flujoLecturaCompartido);

        // For que rellenará los arrays de tuberias y flujos
        for (int i = 0; i < MAX_CAMIONES; i++) {
            tuberiasEscritura[i] = new PipedWriter();
            tuberiasLectura[i] = new PipedReader(tuberiasEscritura[i]);
            flujoEscritura[i] = new PrintWriter(tuberiasEscritura[i]);
            flujoLectura[i] = new BufferedReader(tuberiasLectura[i]);
        }

        // Bucle que crea los camiones y los inicia
        for (int i = 0; i < MAX_CAMIONES; i++) {
            camiones[i] = new Camion(i, sincro, flujoLectura[i], flujoEscrituraCompartido);
        }

        System.out.println("Antonio Oliva, Iniciamos la simulación...\n");

        // Esperamos a que los camiones terminen sus viajes
        sincro.esperarFinCamiones();

        // Imprimimos datos de facturacion
        imprimirFacturacion(central);

        // Informamos a la clase central de que termine
        sincro.terminarCentral();

        // Imprimimos tiempo de la simulacion
        imprimirTiempoSimulacion(tiempoInicio);
    }

    private static void imprimirTiempoSimulacion(long tiempoInicio) {
        Long tiempoSimulacion = System.currentTimeMillis() - tiempoInicio;
        System.out.println("\nAntonio Oliva, Tiempo de la simulación: " + tiempoSimulacion + " milisegundos");
    }

    private static void imprimirFacturacion(Central central) {
        System.out.println("Antonio Oliva, Facturación:");

        for (int i = 0; i < MAX_CAMIONES; i++) {
            System.out.println("Camión " + i + " factura :" + central.getKm(i) + "km " + "Importe > "
                    + central.getKm(i) * PRECIO_KM + "$");
        }
    }
}
