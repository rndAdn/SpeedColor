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

public class Bombe extends Actor{

    public Texture texture;
    public static int nombre;


    BitmapFont font;






    public Bombe(){

        font = new BitmapFont();


        this.texture = new Texture(Gdx.files.internal("data/Bomb.png"));

        setX(10);
        setY(10);
        setBounds(getX(),getY(), 75, 75);
        addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.print("qsdfg");
                if(((Bombe)event.getTarget()).nombre>0){
                    ((Bombe)event.getTarget()).blowUp();
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

    public void blowUp(){
        for (Cube c : PlayScreen.liste){
            PlayScreen.caseDetruits++;
            PlayScreen.vie++;
            c.removeCube();
        }
        PlayScreen.liste = new LinkedList<Cube>();
    }

}
