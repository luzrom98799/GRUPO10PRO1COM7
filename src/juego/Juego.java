package juego;
import java.awt.Color;
import java.util.Random;
import entorno.Entorno;
import entorno.InterfaceJuego;

<<<<<<< Updated upstream
public class Juego extends InterfaceJuego {
    private Entorno entorno;
    private Personaje p;
    private Isla[] islas;
    private Enemigo[] enemigos;
    private int salto = 0;
    private Castillo castillo;
    private boolean gano;
    private int cantidadVidas;
    private boolean perdio;

    // 1. CONSTRUCTOR JUEGO
    Juego() {
        this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
        gano = false;
        perdio = false;
        cantidadVidas = 5;
        
        // LLAMADA A TUS MÉTODOS DE CREACIÓN
        crearPersonaje();
        crearIslas();
        crearEnemigos();
        
        castillo = new Castillo(3005, 300, 120, 200);
        this.entorno.iniciar();
=======
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
	private Vida[]vidas;
	private int cantidadVidas;
	private boolean perdio;
	

	
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
	    
	    cantidadVidas=8;
	    vidas= new Vida[cantidadVidas];
	    for (int i =0; i<vidas.length; i++) {
	    	vidas[i]= new Vida(30+(i*25),30,15);
	    }
	    
	    perdio= false;
	    	
	    
	   
			
		// Inicia
		this.entorno.iniciar(); 
>>>>>>> Stashed changes
    }
	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{
		if(perdio) {
			entorno.escribirTexto("perdiste el juego wey", 350,300);
			return;
		}
		
		if(gano) {
			entorno.escribirTexto("ganastee!!", 350, 300);
			return;
		}
		// Procesamiento de un instante de tiempo
		// ...
		
		//dibujado
		p.dibujar(entorno);
		for (int i=0;i< cantidadVidas; i++) {
			if (vidas[i]!=null) {
				vidas[i].dibujar(entorno);
			}
		}
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

<<<<<<< Updated upstream
    // 2. MÉTODO TICK PRINCIPAL
    public void tick() {
        if (perdio) {
            entorno.escribirTexto("perdiste", 350, 300);
            return;
        }
        if (gano) {
            entorno.escribirTexto("GANASTE", 350, 300);
            return;
        }

        // VIDAS
        entorno.escribirTexto("Vidas: " + cantidadVidas, 20, 30);

        // Dibujado base
        if (p != null) p.dibujar(entorno);
        if (p != null && p.getDisparo() != null) p.getDisparo().dibujar(entorno);
        
        actualizarIslas();
        actualizarEnemigos();

        // CONTROL DE MOVIMIENTO
        if (p != null) {
            // Movimiento Izquierda
            if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA) && p.getX() - p.getAncho() / 2 > 0) {
                if (!p.colisionaPorIzquierda(islas)) {
                    p.moverIzquierda();
                }
            }

            // Movimiento Derecha
            if (entorno.estaPresionada(entorno.TECLA_DERECHA) && p.getX() + p.getAncho() / 2 < entorno.ancho()) {
                if (!p.colisionaPorDerecha(islas)) {
                    if (p.getX() < 400 || esFinDelMapa()) {
                        p.moverDerecha();
                    } else {
                        moverMundo(5);
                    }
                }
            }

            // Salto
            if (entorno.estaPresionada(entorno.TECLA_ARRIBA) && p.colisionaPorAbajo(islas) && salto == 0) {
                salto = 30;
            }

            // Sistema de disparo
            if (entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO) && p.getDisparo() == null) {
                p.disparar(entorno.mouseX(), entorno.mouseY());
            }

            if (p.getDisparo() != null) {
                p.getDisparo().mover();
                if (p.getDisparo().getX() < 0 || p.getDisparo().getY() < 0 || p.getDisparo().getX() > entorno.ancho() || p.getDisparo().getY() > entorno.alto()) {
                    p.setDisparo(null);
                }
            }

            // Gravedad y saltos
            if (!p.colisionaPorAbajo(islas)) {
                p.setY(p.getY() + 2);
            }

            if (salto > 0) {
                if (!p.colisionaPorArriba(islas)) {
                    p.setY(p.getY() - 6);
                    salto--;
                } else {
                    salto = 0;
                }
            }

            // Caída al vacío
            if (p.getY() > entorno.alto()) {
                descontarVida();
            }

            // Colisión con la victoria
            if (castillo != null && p.bordeDerecho() >= castillo.bordeIzquierdo() && p.bordeIzquierdo() <= castillo.bordeDerecho() && p.bordeInferior() >= castillo.bordeSuperior() && p.bordeSuperior() <= castillo.bordeInferior()) {
                gano = true;
            }
        }

        // fin de juego
        if (cantidadVidas <= 0) {
            perdio = true;
        }
    }


    // MÉTODO: CREAR PERSONAJE
    private void crearPersonaje() {
        this.p = new Personaje(400, 300, 20, 50);
    }

    // MÉTODO: CREAR ISLAS (Usa el Random de forma local adentro del método)
    private void crearIslas() {
        this.islas = new Isla[50];
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
    }

    // MÉTODO: CREAR ENEMIGOS 
    private void crearEnemigos() {
        this.enemigos = new Enemigo[120];
        Random r = new Random();
        for (int i = 0; i < enemigos.length; i++) {
            int y = 50 + r.nextInt(300);
            int velocidad = 2 + r.nextInt(2);
            if (i % 2 == 0) {
                int x = -50 - (i * 350);
                enemigos[i] = new Enemigo(x, y, 30, 30, velocidad);
            } else {
                int x = 850 + (i * 350);
                enemigos[i] = new Enemigo(x, y, 30, 30, -velocidad);
            }
        }
    }

    // MÉTODO: MOVER MUNDO
    private void moverMundo(int velocidad) {
        for (int i = 0; i < islas.length; i++) {
            if (islas[i] != null) {
                islas[i].setX(islas[i].getX() - velocidad);
            }
        }
        if (castillo != null) {
            castillo.setX(castillo.getX() - velocidad);
        }
    }

    private boolean esFinDelMapa() {
        int xDeLaIslaMasLejana = 0;
        for (int i = 0; i < islas.length; i++) {
            if (islas[i] != null && islas[i].getX() > xDeLaIslaMasLejana) {
                xDeLaIslaMasLejana = islas[i].getX();
            }
        }
        return xDeLaIslaMasLejana <= 730;
    }

    private void actualizarIslas() {
        for (int i = 0; i < islas.length; i++) {
            if (islas[i] != null) {
                islas[i].dibujar(entorno);
            }
        }
        if (castillo != null) {
            castillo.dibujar(entorno);
        }
    }

    // ENEMIGOS intento de hacer INDEPENDIENTES
    private void actualizarEnemigos() {
        for (int i = 0; i < enemigos.length; i++) {
            if (enemigos[i] == null) continue;

            enemigos[i].mover();
            enemigos[i].dibujar(entorno);

            if (enemigos[i].getVelocidad() < 0 && enemigos[i].getX() < -50) {
                enemigos[i].setX(850);
            } else if (enemigos[i].getVelocidad() > 0 && enemigos[i].getX() > 850) {
                enemigos[i].setX(-50);
            }

            if (p != null && p.getDisparo() != null && p.getDisparo().colisionaConObstaculo(enemigos[i])) {
                enemigos[i] = null;
                p.setDisparo(null);
                continue;
            }

            if (p != null && p.bordeDerecho() >= enemigos[i].bordeIzquierdo() && p.bordeIzquierdo() <= enemigos[i].bordeDerecho() && p.bordeInferior() >= enemigos[i].bordeSuperior() && p.bordeSuperior() <= enemigos[i].bordeInferior()) {
                enemigos[i] = null;
                descontarVida();
            }
        }
    }

    private void descontarVida() {
        cantidadVidas--;
        if (p != null) p.recibirDaño(1);
        if (cantidadVidas > 0) {
            crearPersonaje();
        } else {
            p = null;
            perdio = true;
        }
    }

    private void rellenarIslas() {
        this.islas = new Isla[88];
        Random r = new Random();
        for (int i = 0; i < islas.length; i++) {
            int columna = i / 3;
            int fila = i % 3;
            int x = 100 + (columna * 180);
            if (fila == 0) {
                islas[i] = new Isla(x, 650, 140, 200);
            } else if (fila == 1) {
                int y = 400;
                islas[i] = new Isla(x + r.nextInt(200), y, 100, 15);
            } else {
                int y = 250;
                islas[i] = new Isla(x + r.nextInt(400), y, 90, 15);
=======
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
					cantidadVidas--;
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
>>>>>>> Stashed changes
            }
        }
      //una vez que el personaje cae vuelve al centro de la pantalla	
     if (p!=null && p.getY()>entorno.alto()) {
    	 cantidadVidas--;
    	 p=null;
    	 p= new Personaje(400,300,20,50);
    	 	 
    	 
     }
     if (cantidadVidas<=0) {
    	 perdio=true;
     }
     if (gano) {
    	 entorno.escribirTexto("ganaste el juego", 350, 300);
    	 
     }
     if (perdio) {
    	 entorno.escribirTexto("perdiste el juego", 350, 300);
     }
      		
	}

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
    public Isla[] getIslas() { return islas; }
    public void setIslas(Isla[] islas) { this.islas = islas; }

<<<<<<< Updated upstream
    public static void main(String[] args) {
        @SuppressWarnings("unused")
        Juego juego = new Juego();
    }

    public Isla[] getIslas() { return islas; }
    public void setIslas(Isla[] islas) { this.islas = islas; }
=======
>>>>>>> Stashed changes
}
