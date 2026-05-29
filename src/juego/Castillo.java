package juego;
import java.awt.Color;
import entorno.Entorno;

public class Castillo {
<<<<<<< Updated upstream
    private int x;
    private int y;
    private int ancho;
    private int alto;

    public Castillo(int x, int y, int ancho, int alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
    }

    public void dibujar(Entorno e) {
        e.dibujarRectangulo(x, y, ancho, alto, 0, Color.BLUE);
    }

    public int bordeDerecho() { 
    	return this.x + this.ancho / 2; }
    public int bordeIzquierdo() {
    	return this.x - this.ancho / 2; }
    public int bordeInferior() { 
    	return this.y + this.alto / 2; }
    public int bordeSuperior() { 
    	return this.y - this.alto / 2; }

    // GETTER Y SETTER PARA EL SCROLL
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() { 
    	return y; }
    public int getAncho() { 
    	return ancho; }
    public int getAlto() { 
    	return alto; }
}
=======
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
>>>>>>> Stashed changes
