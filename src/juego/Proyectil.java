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
		this.dx=this.dx/distancia;
		this.dy=this.dy/distancia;
	}
	
	public void dibujar(Entorno e) {
		e.dibujarCirculo(x, y, radio*2, Color.BLUE);
	}
	
	public void mover() {
		this.x=this.x+dx;
		this.y=this.y+dy;
	}
	
	
	public boolean colisionaConObstaculo(Isla o) {
		
		return true;
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
