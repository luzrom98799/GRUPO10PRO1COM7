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
	private int salto = 0;
	private Castillo castillo;
	private boolean gano;
	

	
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
	    

	    for (int i = 0; i < islas.length; i++) {
	        int columna = i / 3;   
	        int fila = i % 3;      

	        int x = 100 + (columna * 180);

	        if (fila == 0) {   
	            islas[i] = new Isla(x, 500, 140, 200);

	        } else if (fila == 1) {   
	            int y = 300 + r.nextInt(80);  
	            islas[i] = new Isla(x + r.nextInt(50), y, 100, 15);

	        } else {   
	            int y = 160 + r.nextInt(80);
	            islas[i] = new Isla(x + r.nextInt(50), y, 100, 15);
	        }
	    }
	
	    
	    
	    enemigos= new Enemigo[120];
	    for(int i=0; i<enemigos.length; i++) {
	    	int y= 50 + r.nextInt(300);
	    	int velocidad= 2+r.nextInt(2);
	    	
	    	if(i%2==0) {
	    		int x = -50-(i*350);
	    		enemigos[i]=new Enemigo(x, y, 30, 30, velocidad);
	    	}else {
	    		int x= 850 +(i*350);
	    		enemigos[i]= new Enemigo(x,y,30,30,-velocidad);
	    		
	    	}
	    		
	    }
	    
	    castillo= new Castillo(3005,300,120,200);
	    gano=false;
	    
	    
	   
			
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
		
		if (entorno.estaPresionada(entorno.TECLA_ARRIBA) && p.colisionaPorAbajo(islas, offsetX) && salto == 0) {

			    salto = 30;
			}
		

		
		if(entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO) && p.getDisparo()==null) {
			p.disparar(entorno.mouseX(),entorno.mouseY());
		}
		
		
		
		//movimiento del Proyectil
		if(p.getDisparo()!=null) {
			p.getDisparo().mover();
		}
		
		
		//el proyectil se vuelve null si sale del entorno	
		if(p.getDisparo()!=null && p.getDisparo().getX()<0 || p.getDisparo()!=null &&  p.getDisparo().getY()<0 ||
			p.getDisparo()!=null && p.getDisparo().getX()>entorno.ancho() || p.getDisparo()!=null &&  p.getDisparo().getY()>entorno.alto()	)
		
		
			{
			p.setDisparo(null);
			}
	
		
		//colision entre el proyectil y los enemigos
		for (int i = 0; i < enemigos.length; i++) {
			   if (enemigos[i] != null && p.getDisparo() != null) {
			       if (p.getDisparo().colisionaConObstaculo(enemigos[i])) {
			           enemigos[i] = null; 
			           p.setDisparo(null);
			             
			        }
			    }
			}
		//colision entre personaje y enemigo
		for (int i = 0; i < enemigos.length; i++) {
			if (enemigos[i] != null && p!=null ) {
				if( p.bordeDerecho() >= enemigos[i].bordeIzquierdo()  &&  p.bordeIzquierdo()<=enemigos[i].bordeDerecho() 
						&&  p.bordeInferior()>=enemigos[i].bordeSuperior() && p.bordeSuperior()<=enemigos[i].bordeInferior()) {
					 
					enemigos[i] = null;
					p.recibirDaño(1);
					p=null;
					p= new Personaje(400,300,20,50);
					
				
					 
					
				}
			}
			
		}
		
		if (p!= null) {
			if (p.bordeDerecho()>= castillo.bordeIzquierdo(offsetX) && p.bordeIzquierdo()<= castillo.bordeDerecho(offsetX)
					&& p.bordeInferior()>=castillo.bordeSuperior()&& p.bordeSuperior()<= castillo.bordeInferior()) {
				gano=true;
			}
		}
		
		
		
		
        // Dibujado recorriendo el arreglo islas
        for (int i = 0; i < islas.length; i++) {
            if (islas[i] != null) {
                islas[i].dibujar(entorno, offsetX);
            }
        }
        
        castillo.dibujar(entorno, offsetX);
        
        
        //enemigo
        for(int i=0; i<enemigos.length; i++) {
        	if(enemigos[i] != null  ) {
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
        // el personaje sube gradualmente
        if (salto > 0) {
            if (!p.colisionaPorArriba(islas, offsetX)) {
                p.setY(p.getY() - 6);
                salto--;
            } else {
                salto = 0;
            }
        }
      //una vez que el personaje cae vuelve al centro de la pantalla	
     if (p!=null && p.getY()>entorno.alto()) {
    	 p=null;
    	 p= new Personaje(400,300,20,50);
    	 	 
    	 
     }
     
     if (gano) {
    	 entorno.escribirTexto("ganaste el juego", 350, 300);
    	 
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
