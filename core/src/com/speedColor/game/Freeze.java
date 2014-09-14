package com.speedColor.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.speedColor.screen.PlayScreen;

import java.util.LinkedList;

public class Freeze extends Actor{

    public Texture texture;
    public static int nombre;


    BitmapFont font;

    public Freeze(){

        font = new BitmapFont();


        this.texture = new Texture(Gdx.files.internal("data/Ice.png"));

        setX(10);
        setY(100);
        setBounds(getX(),getY(), 75, 75);
        addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(((Freeze)event.getTarget()).nombre>0){
                    ((Freeze)event.getTarget()).slowDown();
                    nombre--;
                }
                return true;
            }
        });

    }


    @Override
    public void draw(Batch batch, float alpha){
        batch.draw(texture, getX(), getY(),75, 75);
        font.setColor(new Color(1, 1, 1, 1));
        font.setScale(2,2);
        font.draw(batch, "" + nombre, getX()+65, getY()+20);
    }

    @Override
    public void act(float delta){
        super.act(delta);
    }

    public void slowDown(){

        float vit  = PlayScreen.vitesse + 7f;
        PlayScreen.vitesse = vit;
        PlayScreen.updateSpeed(vit);
    }

}
