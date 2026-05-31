package juego;
import java.util.random.*;
import java.awt.Color;
import java.util.Random;
import entorno.Entorno;
import entorno.InterfaceJuego;

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

    // CONSTRUCTOR JUEGO
    Juego() {
        this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
        gano = false;
        perdio = false;
        cantidadVidas = 5;
        enemigos= new Enemigo[30];
        this.islas = new Isla[30]; 
        crearIslas(); 
        // LLAMADA metodos DE CREACIÓN
        crearPersonaje();
        castillo = new Castillo(0, 400, 120, 200);
        this.entorno.iniciar();
    }

    //  TICK PRINCIPAL
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
        dibujarIslas();
        actualizarIslas();
        actualizarEnemigos();
        reaperecerEnemigos();
        
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
                    if (p.getX() < 400  ){
                        p.moverDerecha();
                     
                    } else if(esFinDelMapa()){
                    	p.moverDerecha();  
             
                    }
                    else {
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








    // MÉTODO: este metodo actualiza enemigos es decir los dibuja y vuelve null(los mata x.x) ENEMIGOS debe ser independiente y no moverse con el mundo :C
    private void actualizarEnemigos() {
        for (int i = 0; i < enemigos.length; i++) {
        	if( enemigos[i]!=null) {
            enemigos[i].mover();
            enemigos[i].dibujar(entorno);

            if (enemigos[i].getVelocidad() > 0 && enemigos[i].getX() > 830) {
                enemigos[i] = null;
                continue;
            } else if (enemigos[i].getVelocidad() < 0 && enemigos[i].getX() < -30) {
                enemigos[i]=null;
                continue;
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
    }
    //metodo reapaerecer enemis :( , este metodo hace hace aparecer enemigos en todo momento :) :(
    public void reaperecerEnemigos() { 
        int cantVivos = 0; 
        for(int i = 0; i < enemigos.length; i++) { 
            if(enemigos[i] != null) { 
                cantVivos++; 
            } 
        } 
        
        if(cantVivos < 5) { 
            Random r = new Random(); 
            for(int i = 0; i < enemigos.length; i++) { 
                if(enemigos[i] == null && cantVivos < 5) { 
                    
                    int velocidad = 3; 
                    int aleatorio = r.nextInt(2); //izquierda/derecha
                    
                    if(aleatorio == 0) { 
                        enemigos[i] = new Enemigo(-10, 30, 30, 30, velocidad); // Viene por izquierda
                    } else { 
                        enemigos[i] = new Enemigo(820, 50, 30, 30, -velocidad); // Viene por derecha
                    } 
                    
                    cantVivos++;  
                } 
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

    private void moverMundo(int velocidad) {
        for (int i = 0; i < islas.length; i++) {
            if (islas[i] != null) {
                islas[i].setX(islas[i].getX() - velocidad);
            }
        }
    }
    
    private boolean esFinDelMapa() {
        Isla islaMasLejana = null;
        int xMaximo = -1;
        for (int i = 0; i < islas.length; i++) {
            if (islas[i] != null && islas[i].getX() > xMaximo) {
                xMaximo = islas[i].getX();
                islaMasLejana = islas[i];
            }
        }
        if (islaMasLejana == null) return true;
        
        return islaMasLejana.getX() <= 720; // Frena el mundo cuando la última isla está a la vista
    }

        private void dibujarIslas() {
            for (int i = 0; i < islas.length; i++) {
                if (islas[i] != null) {
                    islas[i].dibujar(entorno);
                }
            }
        }  
        private void actualizarIslas() { 
            // 1. Dibujamos todas las islas del mapa como siempre
            for (int i = 0; i < islas.length; i++) { 
                if (islas[i] != null) { 
                    islas[i].dibujar(entorno); 
                } 
            } 
            
            // la isla que tenga la coordenada X más grande
            Isla islaMasLejana = null;
            int xMaximo = -1; // en -1 para que cualquiera le gane

            for (int i = 0; i < islas.length; i++) {
                // Si n está vacío y su X es más grande que el máximo que encontramos antes
                if (islas[i] != null && islas[i].getX() > xMaximo) {
                    xMaximo = islas[i].getX();    // Guarda la X más grande
                    islaMasLejana = islas[i];     // Guardo esa isla como la última
                }
            }

            // Si encontramos la isla, dibujamos castillo encima de ella
            if (castillo != null && islaMasLejana != null) {
                castillo.setX(islaMasLejana.getX()); 
                castillo.setY(islaMasLejana.getY() - (castillo.getAlto() / 2) - (islaMasLejana.getAlto() / 2)); 
                castillo.dibujar(entorno); 
            } 
        }

        private void crearIslas() {
            this.islas = new Isla[30]; // Creamos el espacio para las 30 islas
            Random r = new Random();
            
            for (int i = 0; i < islas.length; i++) {
                
                //  dado del 0 al 2
                int dado = r.nextInt(3);
                
                if (dado == 0) {

                    this.islas[i] = new Isla(xFijoSuelo, 550, 100, 10);
                    
                } else if (dado == 1) {

                    int xRandomMedio = (i * 120) + r.nextInt(50);
                    this.islas[i] = new Isla(xRandomMedio, 360, 60, 10);
                    
                } else {
                    int xRandomArriba = (i * 120) + r.nextInt(50);
                    this.islas[i] = new Isla(xRandomArriba, 200, 160, 10);
                }
            }
        }




        


    public static void main(String[] args) {
        @SuppressWarnings("unused")
        Juego juego = new Juego();
    }

    public Isla[] getIslas() { return islas; }
    public void setIslas(Isla[] islas) { this.islas = islas; }
}
