package PruebasProcesos;

public class Sumador {
    // suma n1 y n2
    public static void main(String[] args) {
        int n1 = Integer.parseInt(args[0]);
        int n2 = Integer.parseInt(args[1]);

        System.out.println("El numero n1 es: " + n1);
        System.out.println("El numero n2 es: " + n2);
        System.out.println("La suma es: " + sumar(n1, n2));
    }

    private static long sumar(int n1, int n2) {
        long suma = 0;

        if (n1 > n2) {
            int aux = n1;
            n1 = n2;
            n2 = aux;
        }

        for (int i = n1; i <= n2; i++) {
            suma = suma + i;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return suma;
    }
}
