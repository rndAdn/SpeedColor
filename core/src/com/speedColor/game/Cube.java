package com.speedColor.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.speedColor.screen.PlayScreen;

import java.util.Random;

public class Cube extends Actor {
    public Texture texture;
    public Color color;
    public MoveToAction action = new MoveToAction();
    static Random rand = new Random(System.currentTimeMillis());
    public static int X = -75;
    public static int Y = Gdx.graphics.getHeight()-((PlayScreen.margeV*2/3)+(PlayScreen.margeV*1/6));

    float vitesse;


    public Cube(float vitesse){

        this.vitesse = vitesse;

        int col = rand.nextInt(4);


        color = Config.listeColor[col];
        Pixmap pix = new Pixmap(PlayScreen.margeV*2/3, PlayScreen.margeV*2/3, Pixmap.Format.RGB888);
        pix.setColor(new Color(0,0,0,1));
        pix.fillRectangle(0, 0, pix.getWidth(), pix.getHeight());
        pix.setColor(color);
        pix.fillRectangle(1, 1, pix.getWidth()-2, pix.getHeight()-2);

        this.texture = new Texture(pix);
        pix.dispose();
        pix=null;
        setX(X);
        setY(Y);
        setBounds(getX(),getY(), texture.getWidth(), texture.getHeight());



        // Action
        action.setPosition(Gdx.graphics.getWidth(), Y);
        action.setDuration(vitesse);
        //action.setInterpolation(Interpolation.fade);

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
        setX(X);
        action.setDuration(vitesse);
        action.restart();
        action.setTime(time);
    }

    public void removeCube(){

        this.texture.dispose();
        this.clear();
        this.remove();
        color=null;
    }

}
