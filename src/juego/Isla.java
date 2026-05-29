package juego;
import java.awt.Color;
import entorno.Entorno;
public class Isla {
	private int x;
	private int y;
	private int ancho;
	private int alto;
	
	public Isla(int x, int y, int ancho, int alto) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
	}
	
	public void dibujar(Entorno e) {
		e.dibujarRectangulo(x, y, ancho, alto, 0, Color.gray);
	}
	public void dibujar(Entorno e, int offsetX) {
		e.dibujarRectangulo(x-offsetX, y, ancho, alto, 0, Color.gray);
	}
	
	
	
	public int bordeDerecho() {
		return this.x+this.ancho/2;
	}
	public int bordeIzquierdo() {
		return this.x-this.ancho/2;
	}
	public int bordeInferior() {
		return this.y+this.alto/2;
	}
	public int bordeSuperior() {
		return this.y-this.alto/2;
	}
	
	

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
	
	
	
	

}