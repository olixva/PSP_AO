package main;
import java.awt.Color;

import topos.vista1.*;

public class TestScreen {

    static final int ANCHO = 11;
    static final int ALTO = 11;
    static final int LADO = 50;
    static final int REDIBUJAR = 100;

    static int objX = 5;
    static int objY = 5;

    public static void mg(String[] args) {

        final String rutaImagenFondo = "imagenes/panel-basico.gif";
        final String rutaObjetivo = "imagenes/objetivo.png";

        Pantalla pantalla = new Pantalla(ANCHO, ALTO, LADO, Color.BLUE);

        int objX = 5;
        int objY = 5;

        boolean fin = false;
        
        String tecla;
        do {
            pantalla.resetear();
            cargarFondo(pantalla, rutaImagenFondo);
            
            
            if (pantalla.hayTecla()) {
                tecla = pantalla.leerTecla();

                if (tecla.contains("w")) {
                    objY++;
                }

                if (tecla.contains("s")) {
                    objY--;
                }

                if (tecla.contains("a")) {
                    objX--;
                }

                if (tecla.contains("d")) {
                    objX++;
                }
            }
            pantalla.addImagen(objX, objY, rutaObjetivo);

            pantalla.dibujar();
            Alarma.dormir(REDIBUJAR);
        } while (!fin);
    }

    private static void cargarFondo(Pantalla pantalla, String rutaFondo) {

        for (int i = 0; i < ANCHO; i++) {
            for (int j = 0; j < ALTO; j++) {
                pantalla.addImagen(i, j, rutaFondo);
            }
        }
    }
}
