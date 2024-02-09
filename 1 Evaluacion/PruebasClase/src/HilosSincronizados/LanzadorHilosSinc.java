package HilosSincronizados;

public class LanzadorHilosSinc {
    public static void main(String[] args) {
        
        int maxHilos = 100;
        HiloSincro[] hSinc = new HiloSincro[maxHilos];
        
        for (int i = 0; i < maxHilos; i++) {
            hSinc[i] = new HiloSincro(i);
        }
    }
}
