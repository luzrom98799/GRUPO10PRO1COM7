package juego;
import java.awt.Color;
import entorno.Entorno;
public class Vida {
	
	private int x;
	private int y;
	private int tamaño;

	public Vida(int x, int y, int tamaño) {
		this.x = x;
		this.y = y;
		this.tamaño = tamaño;
	}

	public void dibujar(Entorno e) {
		
		e.dibujarTriangulo(x, y, tamaño,tamaño ,0, Color.yellow);
	}

}
