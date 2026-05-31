package juego;

import java.awt.Color;
import entorno.Entorno;

public class Enemigo {
    private int x;
    private int y;
    private int ancho;
    private int alto;
    private int velocidad;

    // Constructor corregido para aceptar el ancho y alto que le pasás en Juego.java
    public Enemigo(int x, int y, int ancho, int alto, int velocidad) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.velocidad = velocidad;
    }

    // DIBUJAR: Centrado usando sus coordenadas
    public void dibujar(Entorno e) {
        // Usamos dibujarCirculo manteniendo el diámetro basado en su ancho/alto
        e.dibujarCirculo(this.x, this.y, this.ancho, Color.RED);
    }
 // ADENTRO DE TU CLASE ENEMIGO
    public boolean colisionaConIsla(Isla[] listaIslas) {
        for (int i = 0; i < listaIslas.length; i++) {
            if (listaIslas[i] != null) {
                
                // Calculamos los bordes del enemigo usando sus métodos
                int miIzquierda = this.x - (this.getAncho() / 2);
                int miDerecha   = this.x + (this.getAncho() / 2);
                int miTecho     = this.y - (this.getAlto() / 2);
                int miPiso      = this.y + (this.getAlto() / 2);

                // Calculamos los bordes de la isla que estamos revisando
                int islaIzquierda = listaIslas[i].getX() - (listaIslas[i].getAncho() / 2);
                int islaDerecha   = listaIslas[i].getX() + (listaIslas[i].getAncho() / 2);
                int islaTecho     = listaIslas[i].getY() - (listaIslas[i].getAlto() / 2);
                int islaPiso      = listaIslas[i].getY() + (listaIslas[i].getAlto() / 2);

                // Caja de colisión tradicional (AABB)
                if (miDerecha >= islaIzquierda && 
                    miIzquierda <= islaDerecha && 
                    miPiso >= islaTecho && 
                    miTecho <= islaPiso) {
                    return true; // Hubo choque real
                }
            }
        }
        return false; // No tocó ninguna isla
    }


    // MOVER: Se desplaza según la velocidad asignada (positiva o negativa)
    public void mover() {
        this.x = this.x + this.velocidad;
    }

    public void moverIzquierda(int valor) {
        this.x = this.x - valor;
    }

    // FUERA PANTALLA: Valida si el enemigo se pasó de los límites laterales
    public boolean fueraPantalla() {
        return this.x < -100 || this.x > 900;
    }

    // MÉTODOS DE BORDES: Iguales a los de Personaje.java para resolver los errores de colisión
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
        return x;
    }

    public void setX(int x) {
        // Permite mover al enemigo si es necesario desde el juego
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

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }
}
