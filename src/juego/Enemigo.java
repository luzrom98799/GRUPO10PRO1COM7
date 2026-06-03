package juego;

import java.awt.Color;
import entorno.Entorno;

public class Enemigo {
    private int x;
    private int y;
    private int ancho;
    private int alto;
    private int velocidad;

    // Constructor el ancho y alto rtc q pasamos en Juego.java
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

    // MOVER: Se desplaza según la velocidad asignada (positiva o negativa)
    public void mover() {
        this.x = this.x + this.velocidad;
    }

    public void moverIzquierda(int valor) {
        this.x = this.x - valor;
    }
    
    
   
    // MÉTODOS DE BORDES: para colisión
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
        // mover al enemigo si es necesario desde el juego
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
