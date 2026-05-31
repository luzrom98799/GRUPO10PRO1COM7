package juego;

import java.awt.Color;
import entorno.Entorno;

public class Castillo {
    private int x;
    private int y;
    private int ancho;
    private int alto;

    // Constructor que coincide con el 'new Castillo(3005, 300, 120, 200)' de Juego.java
    public Castillo(int x, int y, int ancho, int alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
    }

    // DIBUJAR: Muestra el rectángulo del castillo en el Entorno
    public void dibujar(Entorno e) {
        e.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.BLUE);
    }

    // MOVER IZQUIERDA: Método invocado por moverMundo para mov de mundo
    public void moverIzquierda(int valor) {
        this.x = this.x - valor;
    }

    // MÉTODOS DE BORDES: Necesarios para las colisiones de victoria en Juego.java
    public int bordeDerecho() { 
        return this.x + this.ancho / 2; 
    }

    public int bordeIzquierdo() { 
        return this.x - this.ancho / 2; 
    }

    public int bordeInferior() { 
        return this.y + this.alto / 2; 
    }

    public int bordeSuperior() { 
        return this.y - this.alto / 2; 
    }

    // GETTERS Y SETTERS
    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getAncho() {
        return this.ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return this.alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }
}
