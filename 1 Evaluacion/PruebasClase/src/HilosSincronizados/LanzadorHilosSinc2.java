package HilosSincronizados;

public class LanzadorHilosSinc2 {
    public static void main(String[] args) {
        Object candado = new Object();
        int maxHilos = 100;
        HiloSincro2[] hSinc = new HiloSincro2[maxHilos];
        
        for (int i = 0; i < maxHilos; i++) {
            hSinc[i] = new HiloSincro2(i, candado);
        }
    }
}
