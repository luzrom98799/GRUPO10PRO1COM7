package juego;
import java.util.Random;
import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Personaje p;
	private Isla [] islas;
	private Enemigo [] enemigos;
	private int offsetX = 0;
	private Proyectil pr;

	
	// Variables y métodos propios de cada grupo
	// ...
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
		// Inicializar para el juego
		// ...
		p = new Personaje(400,300,20,50);
	    
	    islas = new Isla[50]; 
	    
	    // RANDOM
	    Random r = new Random();
	    

	    for (int i =0; i< islas.length; i++) {
	    	if(i<4) {
	    	int x = 50 + (i*180);
	    	islas[i]= new Isla(x, 550,140,15);
	    	} else {
	    		int columna = i - 4;
	    		int x = 130 + (columna * 180) + r.nextInt(60);
	    		int y = 170 + r.nextInt(250);
	    		islas[i] = new Isla (x, y, 100, 15);
	    	}
	    }
	    
	    enemigos= new Enemigo[65];
	    for(int i=0; i<enemigos.length; i++) {
	    	int y= 50 + r.nextInt(500);
	    	int velocidad= 2+r.nextInt(2);
	    	
	    	if(i%2==0) {
	    		int x = -50-r.nextInt(2000);
	    		enemigos[i]=new Enemigo(x, y, 30, 30, velocidad);
	    	}else {
	    		int x= 850 +r.nextInt(2000);
	    		enemigos[i]= new Enemigo(x,y,30,30,-velocidad);
	    		
	    	}
	    		
	    }
	   
			
		// Inicia
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
		if(p.getDisparo()!=null) {
			p.getDisparo().dibujar(entorno);			
		}
		
		
		
		//capturar presion de teclas
		if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA) && p!=null && p.getX()-p.getAncho()/2>0) {
			if(p.colisionaPorIzquierda(islas, offsetX)==false) {
				p.moverIzquierda();							
			}
		}
		if(entorno.estaPresionada(entorno.TECLA_DERECHA) && p!=null && p.getX()+ p.getAncho()/2< entorno.ancho() ) {
			if(p.colisionaPorDerecha(islas, offsetX)==false) {
				int ultimaIsla = islas[islas.length-1].getX();
				
		        if (p.getX() < 400) {
		            p.moverDerecha();
		        } else {
		            if (ultimaIsla - offsetX > 750) {
		                offsetX += 5;
		            } else if (p.getX() + p.getAncho()/2 < entorno.ancho()) {
		                p.moverDerecha();
		            }
		        }
		    }
		}
		
		if(entorno.sePresiono(entorno.TECLA_ARRIBA) && p.getY()-p.getAlto()/2>=0) {
			if(p.colisionaPorArriba(islas, offsetX)==false) {
				p.moverArriba();
				p.setY(p.getY() -40 );
			}
		}
		
		if(entorno.estaPresionada(entorno.TECLA_ABAJO) && p.getY()+p.getAlto()/2<=entorno.alto()) {
			if(p.colisionaPorAbajo(islas, offsetX)==false) {
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
		if(p.getDisparo()!=null && p.getDisparo().getX()<0 || p.getDisparo()!=null &&  p.getDisparo().getY()<0 ||
			p.getDisparo()!=null && p.getDisparo().getX()>entorno.ancho() || p.getDisparo()!=null &&  p.getDisparo().getY()>entorno.alto()	)
		
		
			{
			p.setDisparo(null);
			
			
				
				
			}
		if ( p != null && pr!=null && p.getAncho() == pr.getX() ||  p!=null && pr!=null && p.getAlto()==pr.getY()  ) {
			p = null;
			pr=null;
		}
		
        // Dibujado recorriendo el arreglo islas
        for (int i = 0; i < islas.length; i++) {
            if (islas[i] != null) {
                islas[i].dibujar(entorno, offsetX);
            }
        }
        
        
        //enemigo
        for(int i=0; i<enemigos.length; i++) {
        	if(enemigos[i] != null) {
        		enemigos[i].mover();
        		enemigos[i].dibujar(entorno);
        	
        	}
        }
      //gravedad del personaje
		
        if (p != null) {
            if (!p.colisionaPorAbajo(islas, offsetX)) {
                p.setY(p.getY() + 2);   // 
            }
        }
      	
     
      		
	}

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
    public Isla[] getIslas() { return islas; }
    public void setIslas(Isla[] islas) { this.islas = islas; }

}
