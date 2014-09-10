package com.speedColor.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Cube {

    public static int speed;
    public Rectangle position;
    public Texture texture;
    public Color color;
    static Random rand = new Random(System.currentTimeMillis());

    public Cube (){ // random color
        position = new Rectangle();
        position.x = 50;
        position.y = Gdx.app.getGraphics().getHeight()-175;
        position.width = 150;
        position.height = 150;

        int col = rand.nextInt(4);


        color = Config.listeColor[col];
        Pixmap pix = new Pixmap(64, 64, Pixmap.Format.RGB888);
        pix.setColor(color);
        pix.fillRectangle(0, 0, pix.getWidth(), pix.getHeight());
        this.texture = new Texture(pix);
    }

    public void update(int vitesse, float delta){

        Moteur.deplaceCube(this,vitesse, delta);
    }

}