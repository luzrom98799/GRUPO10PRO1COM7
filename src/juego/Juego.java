package juego;

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
        
        
        
    }

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
        
        for (int i = 0; i < enemigos.length; i++) {

            if (enemigos[i] == null) {
                enemigos[i] = generarEnemigo();
            }
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

    // MÉTODO: CREAR ENEMIGOS (Usa el Random de forma local adentro del método)
    private void crearEnemigos() {
        this.enemigos = new Enemigo[4];

        for (int i = 0; i < enemigos.length; i++) {
            enemigos[i] = generarEnemigo();
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
        
        for (int i = 0; i < enemigos.length; i++) {
            if (enemigos[i] != null) {
                enemigos[i].setX(enemigos[i].getX() - velocidad);
            }
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

    // MÉTODO: MANEJO DE ENEMIGOS debe ser independiente
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
    
    private Enemigo generarEnemigo() {

        Random r = new Random();

        int y = 50 + r.nextInt(300);
        int velocidad = 2 + r.nextInt(2);

        if (r.nextBoolean()) {

            int x = -50;

            return new Enemigo(x, y, 30, 30, velocidad);

        } else {

            int x = 850;

            return new Enemigo(x, y, 30, 30, -velocidad);
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
    
    @SuppressWarnings("unused")
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
            }
        }
    }

    public static void main(String[] args) {
        @SuppressWarnings("unused")
        Juego juego = new Juego();
    }

//    public Isla[] getIslas() { return islas; }
//    public void setIslas(Isla[] islas) { this.islas = islas; }
}

