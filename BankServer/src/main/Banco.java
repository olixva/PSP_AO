package main;

public class Banco {

    private Cuenta[] cuentas;

    public Banco(int numeroCuentas) {
        
        cuentas = new Cuenta[numeroCuentas];
        for (int i = 0; i < numeroCuentas; i++) {
            cuentas[i] = new Cuenta(0);
        }
        
    }

}
