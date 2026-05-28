package juego;

import java.awt.Color;

import entorno.Entorno;

public class Castillo {

    private int x;
    private int y;

    private int ancho;
    private int alto;

    public Castillo(int x, int y, int ancho, int alto) {

        this.x = x;
        this.y = y;

        this.ancho = ancho;
        this.alto = alto;
    }

    public void dibujar(Entorno e) {

        e.dibujarRectangulo(x, y, ancho, alto, 0, Color.GRAY);
    }


}