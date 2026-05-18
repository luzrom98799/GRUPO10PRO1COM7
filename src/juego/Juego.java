package juego;


import java.awt.Color;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Personaje p;
	private Obstaculo o;
	// Variables y métodos propios de cada grupo
	// ...
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
		// Inicializar lo que haga falta para el juego
		// ...
		p = new Personaje(400,300,20,50);
		o = new Obstaculo(200,300,50,50);

		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{
		// Procesamiento de un instante de tiempo
		// ...
		
		//dibujado
		p.dibujar(entorno);
		o.dibujar(entorno);
		if(p.getDisparo()!=null) {
			p.getDisparo().dibujar(entorno);			
		}
		
		
		
		//capturar presion de teclas
		if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA) && p.getX()-p.getAncho()/2>0) {
			if(p.colisionaPorIzquierda(o)==false) {
				p.moverIzquierda();							
			}
		}
		if(entorno.estaPresionada(entorno.TECLA_DERECHA) && p.getX()+p.getAncho()/2<entorno.ancho()) {
			if(p.colisionaPorDerecha(o)==false) {
				p.moverDerecha();							
			}
		}
		
		if(entorno.estaPresionada(entorno.TECLA_ARRIBA) && p.getY()-p.getAlto()/2>=0) {
			if(p.colisionaPorArriba(o)==false) {
				p.moverArriba();				
			}
		}
		
		if(entorno.estaPresionada(entorno.TECLA_ABAJO) && p.getY()+p.getAlto()/2<=entorno.alto()) {
			if(p.colisionaPorAbajo(o)==false) {
				p.moverAbajo();				
			}
		}
		
		if(entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO) && p.getDisparo()==null) {
			p.disparar(entorno.mouseX(),entorno.mouseY());
		}
		
		
		
		//movimiento del Proyectil
		if(p.getDisparo()!=null) {
			p.getDisparo().mover();
		}
		
		
		//colisiones del proyectil
		if(p.getDisparo()!=null && p.getDisparo().getX()<0) {
			p.setDisparo(null);
		}
		
		
	}
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
