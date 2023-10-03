package PruebasProcesos;

public class Lanzador {
    public static void main(String[] args) {
        LanzadorFicheros lF = new LanzadorFicheros();
        lF.lanzarSumador(1, 10, "r1.txt");
        lF.lanzarSumador(11, 20, "r2.txt");
    }
}
