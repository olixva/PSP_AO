package domain;

import java.awt.Color;
import topos.vista1.*;

public class Game {

    private Pantalla pantalla;
    private Mira myMira;

    private int ancho = 11;
    private int alto = 11;
    private int lado = 50;
    private int redibujar = 100;

    private boolean finJuego = false;

    public Game(int ancho, int alto, int lado, int redibujar) {
        this.ancho = ancho;
        this.alto = alto;
        this.lado = lado;
        this.redibujar = redibujar;

        finJuego = false;
    }

    public void start() {
        
        final String rutaImagenFondo = "imagenes/panel-basico.gif";
        final String rutaImagenObjetivo = "imagenes/objetivo.png";

        Posicion posicionObjetivo = new Posicion(5, 5);

        pantalla = new Pantalla(ancho, alto, lado, Color.DARK_GRAY);
        myMira = new Mira(posicionObjetivo, rutaImagenObjetivo, pantalla);

        do {
            pantalla.resetear();
            // Recorrer todos los objetos de pantalla y agregar su imagen
            cargarFondo(rutaImagenFondo);
            pantalla.addImagen(myMira.getActual().getX(), myMira.getActual().getY(), rutaImagenObjetivo);
            pantalla.dibujar();
            Alarma.dormir(redibujar);
        } while (!finJuego);
    }

    private void cargarFondo(String rutaFondo) {

        for (int i = 0; i < this.ancho; i++) {
            for (int j = 0; j < this.alto; j++) {
                pantalla.addImagen(i, j, rutaFondo);
            }
        }
    }
}
