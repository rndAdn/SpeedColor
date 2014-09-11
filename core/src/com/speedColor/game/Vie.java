package com.speedColor.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.speedColor.screen.PlayScreen;

public class Vie extends Actor{

    public Texture textureG;
    public Texture textureR;
    public Texture textureO;


    public Vie(){
        this.textureG = new Texture(Gdx.files.internal("data/Gheart.png"));
        this.textureR = new Texture(Gdx.files.internal("data/Rheart.png"));
        this.textureO = new Texture(Gdx.files.internal("data/Oheart.png"));

        setX(10);
        setY(200);
        setBounds(getX(),getY(), 75, 75);


    }


    @Override
    public void draw(Batch batch, float alpha){

        if (PlayScreen.vie>50)
            batch.draw(textureG, getX(), getY(),75, 75);
        else if (PlayScreen.vie>25)
            batch.draw(textureO, getX(), getY(),75, 75);
        else
            batch.draw(textureR, getX(), getY(),75, 75);
    }

    @Override
    public void act(float delta){
        super.act(delta);
    }


}
