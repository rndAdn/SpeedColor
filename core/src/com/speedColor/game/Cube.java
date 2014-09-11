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
    public Texture texture;
    public Color color;
    public MoveToAction action = new MoveToAction();
    static Random rand = new Random(System.currentTimeMillis());

    float vitesse;


    public Cube(float vitesse){

        this.vitesse = vitesse;

        int col = rand.nextInt(4);


        color = Config.listeColor[col];
        Pixmap pix = new Pixmap(100, 100, Pixmap.Format.RGB888);
        pix.setColor(color);
        pix.fillRectangle(0, 0, pix.getWidth(), pix.getHeight());
        this.texture = new Texture(pix);

        setX(75);
        setY(Gdx.graphics.getHeight()-texture.getHeight());
        setBounds(getX(),getY(), texture.getWidth(), texture.getHeight());



        // Action
        action.setPosition(Gdx.graphics.getWidth()-this.getWidth(), Gdx.graphics.getHeight()-texture.getHeight());
        action.setDuration(vitesse);

        this.addAction(action);
    }


    @Override
    public void draw(Batch batch, float alpha){
        //batch.draw(texture,this.getX(),this.getY());
        batch.draw(texture, getX(), getY(),getWidth(), getHeight());
    }

    @Override
    public void act(float delta){
        super.act(delta);
    }

    public void updateCube(float vitesse){
        this.vitesse = vitesse;
        float time = (this.action.getTime() * vitesse) / this.action.getDuration();
        System.out.println(time);
        setX(75);
        action.setDuration(vitesse);
        action.restart();
        action.setTime(time);
    }
}
