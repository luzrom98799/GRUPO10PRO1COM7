package juego;

import java.awt.Color;

import entorno.Entorno;

public class Enemigo {


    private int x;
    private int y;

    private int velocidad;

    public Enemigo(int x, int y, int velocidad) {

        this.x = x;
        this.y = y;

        this.velocidad = velocidad;
    }

    
    // MOVER

    public void mover() {

        x += velocidad;
    }

}