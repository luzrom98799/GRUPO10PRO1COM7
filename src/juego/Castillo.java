package juego;
import java.awt.Color;
import entorno.Entorno;

public class Castillo {
	private int x;
	private int y;
	private int ancho;
	private int alto;
	
	public Castillo(int x, int y, int ancho, int alto) {
		this.x= x;
		this.y= y;
		this.ancho= ancho;
		this.alto= alto;
	}
	
	public void dibujar(Entorno e, int offsetX) {
		e.dibujarRectangulo(x- offsetX, y,ancho, alto,0, Color.pink);
	}
	
	public int bordeDerecho(int offsetX) {
		return (x+ancho/2)- offsetX;
	}
	
	public int bordeIzquierdo(int offsetX) {
		return (x-ancho/2)- offsetX;
	}
	
	public int bordeSuperior() {
		return y - alto/2;
	}
	
	public int bordeInferior() {
		return y+ alto/2;
	}

}
