package com.speedColor.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Cube {

    public static int speed;
    public Rectangle position;
    public Texture texture;

    public Cube (int r, int g, int b){
        position = new Rectangle();
        position.x = 20;
        position.y = 200;
        position.width = 64;
        position.height = 64;

        Pixmap pix = new Pixmap(64, 64, Pixmap.Format.RGB888);
        pix.setColor(r / 255, g / 255, b / 255, 1);
        pix.fillRectangle(0, 0, pix.getWidth(), pix.getHeight());
        pix.setColor(Color.BLACK);
        pix.drawRectangle(0, 0, pix.getWidth(), pix.getHeight());
        this.texture = new Texture(pix);
    }


}