package com.speedColor.game;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
public class Case{

    public Color color;
    public Texture texture;
    public Rectangle position;
    public boolean istouch = false;

    public Case(Color color, int w, int h){
        position = new Rectangle();
        position.width = w;
        position.height = h;



        this.color = color;
        Pixmap pix = new Pixmap(300, 200, Pixmap.Format.RGB888);
        pix.setColor(color);
        pix.fillRectangle(0, 0, pix.getWidth(), pix.getHeight());
        this.texture = new Texture(pix);
    }


}
