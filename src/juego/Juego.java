package juego;

import java.awt.Color;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
    // El objeto Entorno que controla el tiempo y otros

    private Entorno entorno;

    // Variables y métodos propios de cada grupo
    // ...

    private Personaje personaje;
    private Isla[] pisos;
    private Enemigo[] enemigos;
    private Proyectil disparo;
    private Castillo castillo;
    private Item item;

    private boolean gano = false;
    private boolean perdio = false;

    Juego()
    {
        // Inicializa el objeto entorno

        this.entorno = new Entorno(this,"Proyecto para TP",800,600);

        // Inicializar lo que haga falta para el juego
        // ...

        // PERSONAJE

        personaje = new Personaje(400,100,30,50);

        // CREAR ISLAS

        pisos = new Isla[12];

        // piso inferior izquierdo
        pisos[0] = new Isla(150,550,250,30);

        // piso inferior derecho
        pisos[1] = new Isla(500,550,250,30);

        // isla media izquierda
        pisos[2] = new Isla(250,430,220,30);

        // isla media derecha
        pisos[3] = new Isla(600,430,220,30);

        // isla superior
        pisos[4] = new Isla(430,310,220,30);

        // NUEVAS ISLAS

        // camino hacia el castillo

        pisos[5] = new Isla(900,520,220,30);

        pisos[6] = new Isla(1200,430,220,30);

        pisos[7] = new Isla(1450,340,220,30);

        pisos[8] = new Isla(1700,500,220,30);

        pisos[9] = new Isla(2000,420,220,30);

        pisos[10] = new Isla(2300,320,220,30);

        pisos[11] = new Isla(2600,520,300,30);

        // ENEMIGOS

        enemigos = new Enemigo[10];

        // CASTILLO

        castillo = new Castillo(2900,470,120,180);

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

        // GANO EL JUEGO

        if (gano) {

            entorno.cambiarFont("Arial",40,Color.GREEN);

            entorno.escribirTexto("AHH GANASTE",300,300);

            return;
        }

        // PERDIO EL JUEGO

        if (perdio) {

            entorno.cambiarFont("Arial",40,Color.BLUE);

            entorno.escribirTexto("UH PERDISTE",300,300);

            return;
        }

        // DIBUJAR FONDO NEGRO

        entorno.dibujarRectangulo(
                entorno.ancho() / 2,
                entorno.alto() / 2,
                entorno.ancho(),
                entorno.alto(),
                0,
                Color.BLACK);

        // MOVIMIENTO DERECHA

        if (entorno.estaPresionada('d') ||
            entorno.estaPresionada(entorno.TECLA_DERECHA)) {

            // personaje libre al inicio

            if (personaje.getX() < 400) {

                personaje.moverDerecha();

            } else {

                // mover pisos

                for (int i = 0; i < pisos.length; i++) {

                    pisos[i].moverIzquierda(5);
                }

                // mover castillo

                castillo.moverIzquierda(5);

                // mover enemigos

                for (int i = 0; i < enemigos.length; i++) {

                    if (enemigos[i] != null) {

                        enemigos[i].moverIzquierda(5);
                    }
                }

                // mover disparo

                if (disparo != null) {

                    disparo.moverIzquierda(5);
                }

                // mover item

                if (item != null) {

                    item.moverIzquierda(5);
                }
            }
        }

        // MOVIMIENTO DE PERSONAJE HACIA LA IZQUIERDA

        if (entorno.estaPresionada('a') ||
            entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {

            personaje.moverIzquierda();
        }

        // SALTO DE PERSONAJE

        if (entorno.sePresiono('w') ||
            entorno.sePresiono(entorno.TECLA_ARRIBA)) {

            personaje.saltar();
        }

        // DISPARO DE PROYECTIL

        if (entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO) &&
            disparo == null) {

            disparo = new Proyectil(
                    personaje.getX(),
                    personaje.getY(),
                    entorno.mouseX(),
                    entorno.mouseY());
        }

        // GRAVEDAD DE PERSONAJE

        personaje.aplicarGravedad();

        // COLISIONES

        for (int i = 0; i < pisos.length; i++) {

            // colision por arriba

            personaje.tocarPiso(pisos[i]);

            // colision costados

            personaje.tocarCostado(pisos[i]);

            // colision techo

            personaje.tocarTecho(pisos[i]);
        }

        // LIMITES PANTALLA

        personaje.limitarPantalla(entorno);

        // DIBUJAR PISOS

        for (int i = 0; i < pisos.length; i++) {

            pisos[i].dibujar(entorno);
        }

                // choque disparo

                if (enemigos[i] != null &&
                    disparo != null &&
                    disparo.colisiona(enemigos[i])) {

                    enemigos[i] = null;

                    disparo = null;

                    // crear item aleatorio

                    if (Math.random() < 0.3) {

                        item = new Item(
                                personaje.getX(),
                                personaje.getY());
                    }
                }

                // fuera pantalla
                disparo = null;
    }




            }
        }

        // PROYECTIL
if (disparo != null) {

    disparo.mover();

    disparo.dibujar(entorno);
    
}



        // CASTILLO



        // VIDAS
for (int i = 0; i < personaje.getVidas(); i++) {

    entorno.dibujarCirculo(
            30 + (i * 40),
            30,
            15,
            Color.RED);
}

if (personaje.getVidas() <= 0) {

    perdio = true;
}

// ITEM

if (item != null) {

    item.dibujar(entorno);

    if (item.colisiona(personaje)) {

        personaje.ganarVida();

        item = null;
    }
}




        // DIBUJAR PERSONAJE

        personaje.dibujar(entorno);
        
    

    // GENERAR ENEMIGOS
    private void generarEnemigos()
    {
        int vivos = 0;

        // contar enemigos vivos

        for (int i = 0; i < enemigos.length; i++) {

            if (enemigos[i] != null) {

                vivos++;
            }
        }

        // mantener enemigos vivos

        if (vivos < 5) {

            for (int i = 0; i < enemigos.length; i++) {

                if (enemigos[i] == null) {

                    // elegir isla aleatoria

                    int pisoRandom =
                            (int)(Math.random() * pisos.length);

                    Piso piso = pisos[pisoRandom];

                    // posición aleatoria sobre isla

                    int xEnemigo =
                            piso.getX()
                            - piso.getAncho() / 2
                            + (int)(Math.random() * piso.getAncho());

                    // arriba del piso

                    int yEnemigo =
                            piso.getY()
                            - piso.getAlto() / 2
                            - 20;

                    // dirección aleatoria

                    int velocidad;

                    if (Math.random() < 0.5) {

                        velocidad = 2;

                    } else {

                        velocidad = -2;
                    }

                    // crear enemigo

                    enemigos[i] =
                            new Enemigo(
                                    xEnemigo,
                                    yEnemigo,
                                    velocidad);

                    break;
                }
            }
        }
    }
    


    @SuppressWarnings("unused")

    public static void main(String[] args)
    {
        Juego juego = new Juego();
    }
    
