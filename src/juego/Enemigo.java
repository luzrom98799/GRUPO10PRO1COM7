package juego;

import java.awt.Color;
import entorno.Entorno;

public class Enemigo {
    private int x;
    private int y;
    private int ancho;
    private int alto;
    private int velocidad;

    // CONSTRUCTOR: El molde para fabricar cada bicho
    public Enemigo(int x, int y, int ancho, int alto, int velocidad) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.velocidad = velocidad;
    }

    // DIBUJAR: Muestra al enemigo en la pantalla
    public void dibujar(Entorno e) {
        // Podés cambiar el color si preferís que sean distintos al personaje
        e.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.BLUE);
    }

    // MOVER: Modifica la X según su velocidad (si es positiva va a la derecha, si es negativa a la izquierda)
    public void mover() {
        this.x = this.x + this.velocidad;
    }

    // MÉTODOS DE BORDES: Claves para que funcionen las colisiones en actualizarEnemigos()
    public int bordeDerecho() {
        return this.x + this.ancho / 2;
    }

    public int bordeIzquierdo() {
        return this.x - this.ancho / 2;
    }


    public int bordeInferior() {
        return this.y + this.alto / 2;

    
    
   
    // MÉTODOS DE BORDES: Iguales a los de Personaje.java para resolver los errores de colisión
    public int bordeDerecho() { 
        return this.x + this.ancho / 2; 

    }

    public int bordeSuperior() {
        return this.y - this.alto / 2;
    }

    // GETTERS Y SETTERS: Los puentes obligatorios para comunicarse con la clase Juego
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

    public int getAlto() {
        return this.alto;
    }

    public int getVelocidad() {
        return this.velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

	
}

