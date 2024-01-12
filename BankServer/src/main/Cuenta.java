package main;

public class Cuenta {

    private int saldo;

    public Cuenta(int saldo) {
        this.saldo = saldo;
    }

    public void setIngreso(int importe) {
        saldo += importe;
    }

    public int getSaldo() {
        return saldo;
    }

    public int getReintegro(int importe) {
        
        if (importe > saldo) { //Si no hay suficiente saldo
            return -1;
        } else { //Si hay suficiente saldo
            saldo -= importe;
            return importe;
        }
    }
}
