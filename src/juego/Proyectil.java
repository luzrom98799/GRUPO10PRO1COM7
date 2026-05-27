package juego;

import java.awt.Color;

import entorno.Entorno;

public class Proyectil {
	private double x;
	private double y;
	private double radio;
	private double dx;
	private double dy;
	
	
	public Proyectil(double x, double y, double radio, double deltaX, double deltaY) {
		this.x = x;
		this.y = y;
		this.radio = radio;
		this.dx=deltaX;
		this.dy=deltaY;
		double distancia= Math.sqrt(dx*dx + dy*dy);
		this.dx=(this.dx/distancia)*6;
		this.dy=(this.dy/distancia)*6;
	}
	
	public void dibujar(Entorno e) {
		e.dibujarCirculo(x, y, radio*3, Color.BLUE);
	}
	
	public void mover() {
		this.x=this.x+dx;
		this.y=this.y+dy;
	}
	
	
	
	public boolean colisionaConObstaculo(Enemigo enemigo) {
	    if (enemigo == null) {
	        return false;
	    }

	    double izq = enemigo.getX() - enemigo.getAncho()/2;
	    double der = enemigo.getX() + enemigo.getAncho()/2;
	    double arriba = enemigo.getY() - enemigo.getAlto()/2;
	    double abajo = enemigo.getY() + enemigo.getAlto()/2;

	    double puntoX = Math.max(izq, Math.min(x, der));
	    double puntoY = Math.max(arriba, Math.min(y, abajo));

	    double dx = x -puntoX;
	    double dy = y - puntoY;

	    return (dx * dx + dy * dy) <= (radio * radio);
	}


	public double getX() {
		return x;
	}


	public void setX(double x) {
		this.x = x;
	}


	public double getY() {
		return y;
	}


	public void setY(double y) {
		this.y = y;
	}


	public double getRadio() {
		return radio;
	}


	public void setRadio(double radio) {
		this.radio = radio;
	}

	
	
	
	
}
