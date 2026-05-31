package juego;

import java.awt.Color;
import entorno.Entorno;

public class Proyectil {
    private double x;
    private double y;
    private int diametro;
    private double deltaX;
    private double deltaY;
    private double velocidad;

    // Constructor que coincide exactamente con el "new Proyectil" de Personaje
    public Proyectil(int x, int y, int diametro, double deltaX, double deltaY) {
        this.x = x;
        this.y = y;
        this.diametro = diametro;
        
        // Calculamos la dirección del disparo hacia donde se hizo clic
        double distancia = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        this.velocidad = 8; // Velocidad de la bala
        
        if (distancia != 0) {
            this.deltaX = (deltaX / distancia) * velocidad;
            this.deltaY = (deltaY / distancia) * velocidad;
        } else {
            this.deltaX = velocidad; // Por defecto dispara a la derecha si da 0
            this.deltaY = 0;
        }
    }

    // Método para mover el proyectil 
    public void mover() {
        this.x += this.deltaX;
        this.y += this.deltaY;
    }

    // Método para dibujar la bala
    public void dibujar(Entorno e) {
        e.dibujarCirculo((int)this.x, (int)this.y, this.diametro, Color.YELLOW);
    }

    // Método de colisión con enemigos 
    public boolean colisionaConObstaculo(Enemigo enemigo) {
        if (enemigo == null) {
            return false;
        }
        // Caja de colisión simple basada en distancias entre centros
        return Math.abs(this.x - enemigo.getX()) < (this.diametro / 2 + enemigo.getAncho() / 2) &&
               Math.abs(this.y - enemigo.getY()) < (this.diametro / 2 + enemigo.getAlto() / 2);
    }

    // Getters y Setters necesarios para Juego.java
    public int getX() {
        return (int) this.x;
    }

    public int getY() {
        return (int) this.y;
    }
}
