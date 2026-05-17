package juego;

import entorno.Entorno;
import java.awt.Color;

public class Personaje {
	private int x;
	private int y;
	private int ancho;
	private int alto;
	
	public Personaje(int x, int y, int ancho, int alto) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
	}
	public void dibujar(Entorno e) {
		e.dibujarRectangulo(x ,y,ancho, alto, 0, Color.red);
	}
	public void moverIzquierda() {
		this.x = this.x -5;
	}
	public void moverDerecha() {
		this.x = this.x +5;
	}
	public void moverArriba() {
		this.y = this.y -5;
	}
	public void moverAbajo() {
		this.y = this.y +5;
	}
	public boolean colisionaPorDerecha(Obstaculo o) {
	    if(bordeDerecho() >= o.bordeIzquierdo() &&
	       bordeDerecho() <= o.bordeDerecho()) {

	        if(bordeInferior() >= o.bordeSuperior() &&
	           bordeSuperior() <= o.bordeInferior()) {

	            return true;
	        }
	    }

	    return false;
	}
	public boolean colisionaPorIzquierda(Obstaculo o) {
		if(bordeIzquierdo()<= o.bordeDerecho() && bordeIzquierdo()>= o.bordeIzquierdo() ) {
			if( bordeInferior()>= o.bordeSuperior() && bordeSuperior() <= o.bordeInferior() ) {
				return true;
			}
		}
		else {
			return false;
		}
		return false;
	}
	public int bordeDerecho() {
		return this.x + this.ancho/2;
	}
	public int bordeIzquierdo() {
		return this.x - this.ancho/2;
	}
	public int bordeInferior() {
		return this.y + this.alto/2;
	}
	public int bordeSuperior() {
		return this.y - this.alto/2;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x= x;
		
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
		

}
