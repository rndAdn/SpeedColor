package com.speedColor.game;


import java.util.LinkedList;

/**
 * Created by renaud on 10/09/14.
 */
public class Moteur {



    public static void deplaceCube(Cube c, int vitesse, float delta){

        float x= c.position.x;
        float y= c.position.y;
        c.position.setPosition(x + (vitesse * delta), y);

    }

    public static boolean popTime(LinkedList<Cube> l){

        return l.getLast().position.x > 50 + l.getLast().position.getWidth()+10;
    }
}
