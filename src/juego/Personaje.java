package juego;

import java.awt.Color;
import entorno.Entorno;

public class Personaje {
    private int x;
    private int y;
    private int ancho;
    private int alto;
    private Proyectil disparo;

    public Personaje(int x, int y, int ancho, int alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.disparo = null;
    }

    public void dibujar(Entorno e) {
        e.dibujarRectangulo(x, y, ancho, alto, 0, Color.RED);
      
    }

    public void moverIzquierda() {
        this.x = this.x - 5;
    }

    public void moverDerecha() {
        this.x = this.x + 5;
    }

    public void moverArriba() {
        this.y = this.y - 5;
    }

    public void moverAbajo() {
        this.y = this.y + 5;
    }

    public void disparar(int mouseX, int mouseY) {
        double deltaX = mouseX - this.x;
        double deltaY = mouseY - this.y;
        this.disparo = new Proyectil(this.x, this.y, 20, deltaX, deltaY);
    }

    public boolean colisionaPorIzquierda(Isla[] islas) {
        for (int i = 0; i < islas.length; i++) {
            Isla isla = islas[i];
            if (isla != null) {
                if (bordeIzquierdo() <= isla.bordeDerecho() && bordeIzquierdo() >= isla.bordeDerecho()-5) {
                    if (bordeInferior() > isla.bordeSuperior() && bordeSuperior() < isla.bordeInferior()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean colisionaPorDerecha(Isla[] islas) {
        for (int i = 0; i < islas.length; i++) {
            Isla isla = islas[i];
            if (isla != null) {
                if (bordeDerecho() >= isla.bordeIzquierdo() && bordeDerecho() < isla.bordeIzquierdo() + 5) {
                    if (bordeInferior() >= isla.bordeSuperior() && bordeSuperior() < isla.bordeInferior()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean colisionaPorArriba(Isla[] islas) {
        for (int i = 0; i < islas.length; i++) {
            Isla isla = islas[i];
            if (isla != null) {
                if (bordeSuperior() <= isla.bordeInferior() && bordeSuperior() > isla.bordeInferior() - 5) {
                    if (bordeDerecho() > isla.bordeIzquierdo() && bordeIzquierdo() < isla.bordeDerecho()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean colisionaPorAbajo(Isla[] islas) {
        for (int i = 0; i < islas.length; i++) {
            Isla isla = islas[i];
            if (isla != null) {
                if (bordeInferior() >= isla.bordeSuperior() && bordeInferior() < isla.bordeSuperior() + 5) {
                    if (bordeDerecho() >= isla.bordeIzquierdo() && bordeIzquierdo() < isla.bordeDerecho()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

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

   

    // GETTERS Y SETTER
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

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public Proyectil getDisparo() {
        return disparo;
    }

    public void setDisparo(Proyectil disparo) {
        this.disparo = disparo;
    }
}

