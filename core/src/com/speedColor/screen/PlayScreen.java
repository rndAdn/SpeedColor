package com.speedColor.screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.speedColor.game.Case;
import com.speedColor.game.Config;
import com.speedColor.game.Cube;
import com.speedColor.game.Moteur;
import java.util.LinkedList;


public class PlayScreen implements Screen {

    private BitmapFont font;
    private SpriteBatch batch;
    private Case caseColor[];
    private static LinkedList<Cube> liste;
    public Timer t;
    public Texture top;
    public Texture left;

    private int vie = 50;
    private int caseDetruits = 0;
    private boolean lose = false;

    private int margeH =200;
    private int margeV =200;
    public Game g;

    private int vitesse = 20;
    private boolean popSec = false;
    private float oldPopSec = 0;
    float curtime = 0;




    public PlayScreen(Game container){

        lose = false;
        this.g = container;
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.setScale(3,3);
        batch = new SpriteBatch();
        liste = new LinkedList<Cube>();
        liste.addFirst(new Cube());
        t = new Timer();

        t.scheduleTask(new com.badlogic.gdx.utils.Timer.Task() {
            @Override
            public void run() {
                vitesse += 10;
            }
        }, 0, 5);


        int w = Gdx.app.getGraphics().getWidth() - margeH;
        int h = Gdx.app.getGraphics().getHeight() - margeV;
        Pixmap pix = new Pixmap(w, margeV, Pixmap.Format.RGB888);
        pix.setColor(Color.LIGHT_GRAY);
        pix.fillRectangle(0, 0, pix.getWidth(), pix.getHeight());
        this.top = new Texture(pix);

        pix = new Pixmap(margeH, Gdx.graphics.getHeight(), Pixmap.Format.RGB888);
        pix.setColor(Color.NAVY);
        pix.fillRectangle(0, 0, pix.getWidth(), pix.getHeight());
        this.left = new Texture(pix);



        caseColor = new Case[4];
        //case 1
        caseColor[0] = new Case(Config.listeColor[0], w/2, h/2);
        caseColor[0].position.x = margeH;
        caseColor[0].position.y = caseColor[0].position.getHeight();

        //case 2
        caseColor[1] = new Case(Config.listeColor[1], w/2, h/2);
        caseColor[1].position.x = margeH+caseColor[0].position.getWidth();
        caseColor[1].position.y = caseColor[0].position.getHeight();

        //case 3
        caseColor[2] = new Case(Config.listeColor[2], w/2, h/2);
        caseColor[2].position.x = margeH;
        caseColor[2].position.y = 0;

        //case 4
        caseColor[3] = new Case(Config.listeColor[3], w/2, h/2);
        caseColor[3].position.x = margeH+caseColor[0].position.getWidth();
        caseColor[3].position.y = 0;




        Gdx.input.setInputProcessor(new InputAdapter() {
            public boolean touchDown (int x, int y, int pointer, int button) {
                y = Gdx.graphics.getHeight()-y;

                for(Case c : caseColor){
                   if(c.position.contains(x,y)){
                       if(liste.size()==0){
                           vie-=25;
                           return true;
                       }
                       if(liste.peekFirst().color.equals(c.color)){
                            liste.removeFirst();
                            caseDetruits++;
                            vie++;
                           if (vie>100) vie = 100;
                       }
                       else{
                            vie-=25;
                        }

                    }
                }

                return true; // return true to indicate the event was handled
            }

            public boolean touchUp (int x, int y, int pointer, int button) {
                // your touch up code here
                return true; // return true to indicate the event was handled
            }
        });





    }

    @Override
    public void render(float delta) {

        if (lose){
            LoseScreen ls = new LoseScreen(this.g);
            ls.detruite = this.caseDetruits;
            ((Game)Gdx.app.getApplicationListener()).setScreen(ls);
        }
        if (liste.size()==0) liste.addLast(new Cube());
        else if(popSec){
            liste.addLast(new Cube());
            popSec = false;
            curtime=0;
        }
        update(delta);
        popSec = Moteur.popTime(liste);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0.128f, 0.128f, 0.255f, 1);
        batch.begin();


        batch.draw(top, margeH,Gdx.app.getGraphics().getHeight()-(top.getHeight()), top.getWidth(),top.getHeight());



        for (Case c : caseColor){
            batch.draw(c.texture, c.position.x, c.position.y, c.position.width, c.position.height);
        }
        for (Cube c : liste){
            batch.draw(c.texture, c.position.x, c.position.y, c.position.width, c.position.height);
        }
        curtime+= delta;



        batch.draw(left, 0,0, left.getWidth(),left.getHeight());
        if(vie>50){
            font.setColor(Color.GREEN);
        }
        else if(vie>25){
            font.setColor(Color.ORANGE);
        }
        else{
            font.setColor(Color.RED);
        }

        font.draw(batch, ""+vie, 20, 200);

        font.setColor(Color.BLACK);
        font.draw(batch, ""+caseDetruits, 20, 400);

        font.draw(batch, "FPS : " + Gdx.graphics.getFramesPerSecond(), 20, 50);

        batch.end();





    }

    public void update(float delta){
        if (vie<=0) lose = true;
        for (Cube c : liste){
            c.update(vitesse, delta);
        }
        if(liste.size()>0){
            if(liste.peekFirst().position.x+liste.peekFirst().position.getWidth()>Gdx.graphics.getWidth()) lose = true;
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {


    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        liste=null;
    }

}
