package juego;

import java.awt.Color;
import entorno.Entorno;

public class Isla {
    private int x;
    private int y;
    private int ancho;
    private int alto;

    // Constructor estándar compatible con tu Juego.java
    public Isla(int x, int y, int ancho, int alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
    }

    // DIBUJAR: Renderiza el piso en la pantalla
    public void dibujar(Entorno e) {
        e.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.GREEN);
    }

    // MOVER IZQUIERDA: Requerido para el scroll de la cámara cuando avanzas a la derecha
    public void moverIzquierda(int valor) {
        this.x = this.x - valor;
    }

    // MÉTODOS DE BORDES: Necesarios para las colisiones del personaje
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

