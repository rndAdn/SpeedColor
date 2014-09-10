package com.speedColor.screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
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

    private static int vie = 50;
    private static int caseDetruits = 0;
    private static boolean lose = false;

    private int margeH =200;
    private int margeV =200;
    public Game g;

    private int vitesse = 20;
    private float popSec = 0;
    private float oldPopSec = 0;
    float curtime = 0;




    public PlayScreen(Game container){


        this.g = container;
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.setScale(3,3);
        batch = new SpriteBatch();
        liste = new LinkedList<Cube>();
        t = new Timer();
        popSec = Moteur.popTime(150,vitesse);
        t.scheduleTask(new com.badlogic.gdx.utils.Timer.Task() {
            @Override
            public void run() {
                oldPopSec = popSec;
                popSec = Moteur.popTime(150,vitesse);
                System.out.println(popSec);
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
                            //System.out.println("Y :"+liste.peekFirst().color);
                            liste.removeFirst();
                            caseDetruits++;
                            vie++;
                       }
                       else{
                            //System.out.println("N :"+ liste.peekFirst().color);
                            vie-=25;
                        }

                    }
                    else {
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

    public static void touch(Color c){
        System.out.println("rgt,uyiku,nbfvsd");
        System.out.println(liste.size());
        if(liste.getFirst().color.equals(c)){
            caseDetruits++;
            vie++;
            liste.removeFirst();
        }
        else{
            if(vie < 25){
                vie = 0;
                lose = true;
            }
            else
                vie -= 25;
        }
    }

    @Override
    public void render(float delta) {


        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0.128f, 0.128f, 0.255f, 1);
        batch.begin();

        update(delta);
        batch.draw(top, margeH,Gdx.app.getGraphics().getHeight()-(top.getHeight()), top.getWidth(),top.getHeight());



        for (Case c : caseColor){
            batch.draw(c.texture, c.position.x, c.position.y, c.position.width, c.position.height);
        }
        for (Cube c : liste){
            batch.draw(c.texture, c.position.x, c.position.y, c.position.width, c.position.height);
        }
        curtime+= delta;
        if(curtime>=oldPopSec){
            liste.addLast(new Cube());
            oldPopSec = popSec;
            curtime=0;
        }


        batch.draw(left, 0,0, left.getWidth(),left.getHeight());
        font.draw(batch, ""+vie, 20, 200);
        font.draw(batch, ""+caseDetruits, 20, 400);

        font.draw(batch, "FPS : " + Gdx.graphics.getFramesPerSecond(), 20, 50);

        batch.end();





    }

    public void update(float delta){
        //System.out.println(liste.size());
        for (Cube c : liste){
            c.update(vitesse, delta);
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
