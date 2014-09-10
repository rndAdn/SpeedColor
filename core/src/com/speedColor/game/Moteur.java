package com.speedColor.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.Vector;

/**
 * Created by renaud on 10/09/14.
 */
public class Moteur {



    public static void deplaceCube(Cube c, int vitesse, float delta){

        float x= c.position.x;
        float y= c.position.y;

        /*System.out.println("delta :"+delta);
        System.out.println("x :"+x+" y: "+y);*/
        c.position.setPosition(x + (vitesse * delta), y);
        //System.out.println("Nx :"+(x + (vitesse * delta))+" Ny: "+y);

    }

    public static float popTime(int size, int vitesse){

        return (size+10)/ (float)vitesse;
    }
}
