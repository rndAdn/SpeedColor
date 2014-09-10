package com.speedColor.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

import java.util.Random;

public class Cube extends Actor {
    Texture texture;
    public Color color;
    public MoveToAction action = new MoveToAction();
    static Random rand = new Random(System.currentTimeMillis());


    public Cube(float vitesse){

        int col = rand.nextInt(4);


        color = Config.listeColor[col];
        Pixmap pix = new Pixmap(100, 100, Pixmap.Format.RGB888);
        pix.setColor(color);
        pix.fillRectangle(0, 0, pix.getWidth(), pix.getHeight());
        this.texture = new Texture(pix);
        setBounds(0, Gdx.graphics.getHeight()-texture.getHeight(), texture.getWidth(), texture.getHeight());
        setX(75);


        // Action
        action.setPosition(Gdx.graphics.getWidth()-this.getWidth(), Gdx.graphics.getHeight()-texture.getHeight());
        action.setDuration(vitesse);
        this.addAction(action);
    }


    @Override
    public void draw(Batch batch, float alpha){
        batch.draw(texture,this.getX(),this.getY());
    }

    @Override
    public void act(float delta){
        super.act(delta);
    }
}
