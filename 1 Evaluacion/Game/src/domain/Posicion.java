package domain;

public class Posicion {

    private int x;
    private int y;

    public void cambiarPosicion(DireccionEnum direccion) {
        switch (direccion) {
            case ARRIBA:
                this.y++;
                break;

            case ABAJO:
                this.y--;
                break;

            case DERECHA:
                this.x++;
                break;

            case IZQUIERDA:
                this.x--;
                break;
        }
    }

    public Posicion(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
