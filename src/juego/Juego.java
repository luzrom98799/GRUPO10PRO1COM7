package juego;

import java.awt.Color;
import java.util.Random;
import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
    private Entorno entorno;
    private Personaje personaje;
    private Isla[] islas;
    private Enemigo[] enemigos;
    private Proyectil disparo;
    private Castillo castillo;
    private Item item;
    private boolean gano = false;
    private boolean perdio = false;

    Juego() {
        this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
        personaje = new Personaje(400, 100, 30, 50);
        
        // Inicializa el mapa de 3 niveles
        rellenarIslas();
        
        // Lista para controlar exactamente 4 enemigos 
        enemigos = new Enemigo[4];
        
        castillo = new Castillo(5300, 470, 120, 180); 
        this.entorno.iniciar();
    }

    public void tick() {
        if (gano) {
            entorno.cambiarFont("Arial", 40, Color.GREEN);
            entorno.escribirTexto("GANASTE", 300, 300);
            return;
        }

        if (perdio) {
            entorno.cambiarFont("Arial", 40, Color.RED);
            entorno.escribirTexto("PERDISTE", 300, 300);
            return;
        }

        entorno.dibujarRectangulo(entorno.ancho() / 2, entorno.alto() / 2, entorno.ancho(), entorno.alto(), 0, Color.BLACK);

        // MOVIMIENTO DE LA PRINCESA Y DE ESCENARIO
        if (entorno.estaPresionada('d') || entorno.estaPresionada(entorno.TECLA_DERECHA)) {
            if (personaje.getX() < 400) {
                personaje.moverDerecha();
            } else {
                //hacia la izquierda
                for (int i = 0; i < islas.length; i++) {
                    if (islas[i] != null) {
                        islas[i].moverIzquierda(5);
                    }
                }
                castillo.moverIzquierda(5);
                
                // ENEMIGOS

                if (disparo != null) { disparo.moverIzquierda(5); }
                if (item != null) { item.moverIzquierda(5); }
            }
        }

        if (entorno.estaPresionada('a') || entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
            personaje.moverIzquierda();
        }

        if (entorno.sePresiono('w') || entorno.sePresiono(entorno.TECLA_ARRIBA)) {
            personaje.saltar();
        }

        if (entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO) && disparo == null) {
            disparo = new Proyectil(personaje.getX(), personaje.getY(), entorno.mouseX(), entorno.mouseY());
        }

        personaje.aplicarGravedad();

        // COLISIONES PERSONAJE-ISLAS
        for (int i = 0; i < islas.length; i++) {
            if (islas[i] != null) {
                personaje.tocarPiso(islas[i]);
                personaje.tocarCostado(islas[i]);
                personaje.tocarTecho(islas[i]);
            }
        }

        personaje.limitarPantalla(entorno);

        // DIBUJAR ISLAS
        for (int i = 0; i < islas.length; i++) {
            if (islas[i] != null) {
                islas[i].dibujar(entorno);
            }
        }

        castillo.dibujar(entorno);
        if (castillo.colisiona(personaje)) {
            gano = true;
        }


        generarEnemigos();

        for (int i = 0; i < enemigos.length; i++) {
            if (enemigos[i] != null) {
                // Se mueven a su ritmo constante (2 o -2) sin importar lo que haga el fondo
                enemigos[i].mover(); 
                enemigos[i].dibujar(entorno);

                // Control de bordes estricto de pantalla (Bordes -40 y 840)
                if (enemigos[i].getX() < -40 || enemigos[i].getX() > 840) {
                    enemigos[i] = null; // Se vacía el casillero y el generador crea otro al instante
                }

                // Choque con el personaje
                if (enemigos[i] != null && enemigos[i].colisiona(personaje)) {
                    personaje.perderVida();
                    enemigos[i] = null;
                }

                // Choque con el disparo
                if (enemigos[i] != null && disparo != null && disparo.colisiona(enemigos[i])) {
                    enemigos[i] = null;
                    disparo = null;
                    if (Math.random() < 0.3) {
                        item = new Item(personaje.getX(), personaje.getY());
                    }
                }
            }
        }

        // PROYECTIL, VIDAS Y ÍTEMS
        if (disparo != null) {
            disparo.mover();
            disparo.dibujar(entorno);
            if (disparo.fueraPantalla()) {
                disparo = null;
            }
        }

        for (int i = 0; i < personaje.getVidas(); i++) {
            entorno.dibujarCirculo(30 + (i * 40), 30, 15, Color.RED);
        }
        if (personaje.getVidas() <= 0) {
            perdio = true;
        }

        if (item != null) {
            item.dibujar(entorno);
            if (item.colisiona(personaje)) {
                personaje.ganarVida();
                item = null;
            }
        }

        personaje.dibujar(entorno);
    }

    // RELLENAR ISLAS (3 Niveles de igual longitud)
    private void rellenarIslas() {
        this.islas = new Isla[60]; 
        Random r = new Random();
        int xInicial = 50;
        int anchoIsla = 140;
        int separacionColumnas = 180;
        for (int i = 0; i < 30; i++) {
            int xBase = xInicial + (i * separacionColumnas);
            this.islas[i] = new Isla(xBase, 550, anchoIsla, 15); // Nivel 1: Suelo estático
            
            int xFlotante = xBase + r.nextInt(40);
            int yFlotante;
            if (i % 2 == 0) {
                yFlotante = 380; // Nivel 2
            } else {
                yFlotante = 210; // Nivel 3
            }
            this.islas[30 + i] = new Isla(xFlotante, yFlotante, 100, 15);
        }
    }

    //GENERAR ENEMIGOS VOLADORES fija de 4)
    private void generarEnemigos() {
        int cantidadMaxima = 4; 
        int vivos = 0;
        for (int i = 0; i < enemigos.length; i++) {
            if (enemigos[i] != null) vivos++;
        }
        if (vivos < cantidadMaxima) {
            for (int i = 0; i < enemigos.length; i++) {
                if (enemigos[i] == null) {
                    int yEnemigo;
                    // Pasillos libres donde físicamente no existen islas fijas
                    if (Math.random() < 0.6) {
                        yEnemigo = 60 + (int)(Math.random() * 140); // Pasillo alto (60 a 200)
                    } else {
                        yEnemigo = 450 + (int)(Math.random() * 30); // Pasillo bajo (450 a 480)
                    }
                    int xEnemigo;
                    int velocidad;
                    if (Math.random() < 0.5) {
                        xEnemigo = -30;
                        velocidad = 2;
                    } else {
                        xEnemigo = 830;
                        velocidad = -2;
                    }
                    enemigos[i] = new Enemigo(xEnemigo, yEnemigo, velocidad);
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Juego juego = new Juego();
    }
}




