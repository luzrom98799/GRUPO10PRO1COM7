																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																		package juego;
import java.awt.Color;
import entorno.Entorno;

public class Enemigo {
	
	private int x;
	private int y;
	private int ancho;
	private int alto;
	private int velocidad;
	
	public Enemigo(int x, int y, int ancho, int alto, int velocidad) {
		
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.velocidad = velocidad;
		
	}
	
	public void dibujar(Entorno e) {
		
		e.dibujarRectangulo(x, y, 30, 30, 0, Color.orange);
	}
	
	public void mover( ) {
		
		this.x = this.x + velocidad;
		
	}
	
	public boolean fueraDePantallita() {
		
		return (x < -ancho || x > 800 + ancho);
		
	}
	
	public boolean colisionaConIsla(Isla isla, int offsetX) {
		if (bordeDerecho()>= isla.bordeIzquierdo(offsetX)&& bordeIzquierdo()<= isla.bordeDerecho(offsetX)&&
		    bordeInferior()>=isla.bordeSuperior()&&
		    bordeSuperior()<=isla.bordeInferior()) {
			
			return true;
		}
		
		return false;
	}
	
	public int bordeDerecho() {
		return this.x+this.ancho/2;
	}
	
	public int bordeIzquierdo() {
		return this.x-this.ancho/2;
	}
	
	public int bordeSuperior() {
		return this.y-this.alto/2;
	}
	
	public int bordeInferior() {
		return this.y+this.alto/2;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x= x;
	}
	
	public int setY() {
		return y;
	}
	
	public void setY(int y) {
		this.y= y;
	}
	
	public int getAncho() {
		return ancho;
	}
	
	public void setAncho(int ancho) {
		this.ancho= ancho;
	}
	
	public int getAlto() {
		return alto;
	}
	
	public void setAlto(int alto) {
		this.alto= alto;
	}

}
