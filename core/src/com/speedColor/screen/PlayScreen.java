package com.speedColor.screen;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Timer;
import com.speedColor.game.*;

import java.sql.Time;
import java.util.LinkedList;

public class PlayScreen implements Screen {

    public Stage stage;

    private SpriteBatch batch;
    private Case caseColor[];
    public static LinkedList<Cube> liste;
    public Timer t;
    public Texture top;

    public static int vie;
    public static int caseDetruits;
    public static boolean lose;
    public static int serie;
    public static int seriemax;


    public static int margeH =200;
    public static int margeV =Gdx.graphics.getHeight()/5;
    public Game g;

    public static float vitesse;
    float speedTime = 6f;
    float speedUp = 0.7f;

    public Bombe bombe;
    public Freeze freeze;
    public Vie vieAct;

    public PlayScreen(Game container) {

        bombe = new Bombe();
        freeze = new Freeze();
        vieAct = new Vie();



        //Stage
        stage = new Stage();
        bombe.setTouchable(Touchable.enabled);
        stage.addActor(bombe);

        freeze.setTouchable(Touchable.enabled);
        stage.addActor(freeze);
        stage.addActor(vieAct);
        liste = new LinkedList<Cube>();


        //batch
        batch = new SpriteBatch();


        lose = false;
        this.g = container;


        t = new Timer();


        t.scheduleTask(new com.badlogic.gdx.utils.Timer.Task() {
            @Override
            public void run() {

                vitesse -= speedUp;
                if(vitesse < 1.5f){
                    vitesse = 1.5f;
                }

                updateSpeed(vitesse);
            }
        }, 0, speedTime);


        int w = Gdx.app.getGraphics().getWidth() - margeH;
        int h = Gdx.app.getGraphics().getHeight() - margeV;

        //Texture Haut
        Pixmap pix = new Pixmap(Gdx.app.getGraphics().getWidth(), margeV, Pixmap.Format.RGB888);
        pix.setColor(Color.LIGHT_GRAY);
        pix.fillRectangle(0, 0, pix.getWidth(), pix.getHeight());
        this.top = new Texture(pix);






        caseColor = new Case[4];
        caseColor[0] = new Case(Config.listeColor[0], w/2, h/2, margeH, h/2);
        caseColor[1] = new Case(Config.listeColor[1], w/2, h/2,margeH+w/2,h/2);
        caseColor[2] = new Case(Config.listeColor[2], w/2, h/2,margeH,0);
        caseColor[3] = new Case(Config.listeColor[3], w/2, h/2,margeH+w/2,0);

        for(Case c : caseColor){
            stage.addActor(c);
            c.setTouchable(Touchable.enabled);
        }



        // INPUT

        Gdx.input.setInputProcessor(stage);
    }



    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(top, 0,Gdx.app.getGraphics().getHeight()-(top.getHeight()), top.getWidth(),top.getHeight());
        batch.end();



        stage.act(Gdx.graphics.getDeltaTime());

        stage.draw();
        update();

    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        this.vie = 50;
        this.caseDetruits = 0;
        this.lose = false;
        this.serie = 0;
        this.seriemax = 0;
        this.vitesse = 2f;
        bombe.nombre=1;
        freeze.nombre=1;
    }

    @Override
    public void hide() {
        for (Cube c : liste){
            c.removeCube();
        }
        liste = null;
        caseColor = null;
        batch.dispose();
        stage.dispose();
        t.clear();
        t = null;
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        for (Cube c : liste){
            c.removeCube();
        }
        liste = null;
        caseColor = null;
        batch.dispose();
        stage.dispose();
        t.clear();
        t = null;


    }
    public static void updateSpeed(float vitesse){

        for (Cube c : liste){
            c.updateCube(vitesse);
        }

    }

    public void addCube(){
        liste.addLast(new Cube(vitesse));
        stage.addActor(liste.getLast());
    }


    public void update(){



        if (vie>100) vie = 100;

        if (vie<=0){
            //lose = true;
        }
        else if(liste.size()>0){
            if(liste.peekFirst().getX()>=Gdx.graphics.getWidth()){
                Cube b = liste.peekFirst();
                liste.removeFirst();
                b.remove();
                fail();
            }
        }

        // SI la pile est vide ou si j'ai de la place
        if (liste.size()==0 || liste.peekLast().getX()> -75 + margeV*2/3 + 20){
            addCube();
        }

        // FIN JEU
        if (lose){
            LoseScreen ls = new LoseScreen(this.g);
            ls.detruite = this.caseDetruits;
            ls.serie = this.seriemax;

            ((Game)Gdx.app.getApplicationListener()).setScreen(ls);
        }
    }


    public static void fail(){
        vie -= 25;
        if(serie>seriemax){
            seriemax = serie;
        }

        serie = 0;
    }




}
