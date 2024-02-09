package main;

public class Banco {

    private Cuenta[] cuentas;

    public Banco(int numeroCuentas) {

        cuentas = new Cuenta[numeroCuentas];
        for (int i = 0; i < numeroCuentas; i++) {
            cuentas[i] = new Cuenta(0);
        }

    }
    
    public void setIngreso(int cuentaId, int importe) {
        cuentas[cuentaId].setIngreso(importe);

    }

    public int getSaldo(int cuentaId) {
        return cuentas[cuentaId].getSaldo();
    }

    public int getReintegro(int cuentaID, int importe) {
        return cuentas[cuentaID].getReintegro(importe);
    }

}
