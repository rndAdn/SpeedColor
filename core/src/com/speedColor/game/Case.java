package com.speedColor.game;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.speedColor.screen.PlayScreen;

public class Case extends Actor{

    public Color color;
    public Texture texture;

    public Case(Color color, int w, int h, int x, int y){



        this.color = color;
        Pixmap pix = new Pixmap(w, h, Pixmap.Format.RGB888);
        pix.setColor(new Color(0,0,0,1));
        pix.fillRectangle(0, 0, pix.getWidth(), pix.getHeight());
        pix.setColor(color);
        pix.fillRectangle(1, 1, pix.getWidth()-2, pix.getHeight()-2);
        this.texture = new Texture(pix);
        setX(x);
        setY(y);
        setBounds(x,y,texture.getWidth(), texture.getHeight());

        addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(PlayScreen.liste.peekFirst().color.equals(((Case)event.getTarget()).color)){
                    Cube b = PlayScreen.liste.peekFirst();
                    PlayScreen.liste.removeFirst();
                    b.remove();
                    PlayScreen.caseDetruits++;
                    PlayScreen.vie++;
                    PlayScreen.serie++;
                    if (PlayScreen.serie%50 == 0){
                        Bombe.nombre++;
                    }
                    if (PlayScreen.serie%100 == 0){
                        Freeze.nombre++;
                    }
                }
                else{
                    PlayScreen.fail();
                }
                return true;
            }
        });
    }
    @Override
    public void draw(Batch batch, float alpha){

        batch.draw(texture, getX(), getY(),getWidth(), getHeight());

    }

    @Override
    public void act(float delta){
        super.act(delta);
    }


}
