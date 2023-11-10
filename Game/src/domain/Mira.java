package domain;

import topos.vista1.Pantalla;

public class Mira extends Thread {

    Posicion actual;
    private String rutaImagenObjetivo;

    private Pantalla pantalla;

    private boolean isEnJuego;

    public Mira(Posicion posicion, String rutaImagenObjetivo, Pantalla pantalla) {
        this.actual = posicion;
        this.rutaImagenObjetivo = rutaImagenObjetivo;
        this.isEnJuego = true;
        this.pantalla = pantalla;
        this.start();
    }

    @Override
    public void run() {
        String tecla;

        while (isEnJuego) {

            if (pantalla.hayTecla()) {
                tecla = pantalla.leerTecla();

                if (tecla.contains("w")) {
                    actual.cambiarPosicion(DireccionEnum.ARRIBA);
                }

                if (tecla.contains("s")) {
                    actual.cambiarPosicion(DireccionEnum.ABAJO);
                }

                if (tecla.contains("a")) {
                    actual.cambiarPosicion(DireccionEnum.IZQUIERDA);
                }

                if (tecla.contains("d")) {
                    actual.cambiarPosicion(DireccionEnum.DERECHA);
                }
            }
        }
    }

    public Posicion getActual() {
        return actual;
    }

    public String getRutaImagenObjetivo() {
        return rutaImagenObjetivo;
    }

    public boolean isEnJuego() {
        return isEnJuego;
    }

}
