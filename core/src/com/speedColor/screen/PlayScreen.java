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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Timer;
import com.speedColor.game.Case;
import com.speedColor.game.Config;
import com.speedColor.game.Cube;

import java.sql.Time;
import java.util.LinkedList;

public class PlayScreen implements Screen {

    public Stage stage;

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

    private float vitesse = 12f;
    float speedTime = 6f;
    float speedUp = 0.7f;

    public PlayScreen(Game container) {







        //Stage
        stage = new Stage();
        liste = new LinkedList<Cube>();

        //font
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.setScale(3,3);

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
        Pixmap pix = new Pixmap(w, margeV, Pixmap.Format.RGB888);
        pix.setColor(Color.LIGHT_GRAY);
        pix.fillRectangle(0, 0, pix.getWidth(), pix.getHeight());
        this.top = new Texture(pix);


        //texture Gauche
        pix = new Pixmap(margeH, Gdx.graphics.getHeight(), Pixmap.Format.RGB888);
        pix.setColor(new Color(80/255f,174/255f,221/255f,1f));
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



        // INPUT
        Gdx.input.setInputProcessor(new InputAdapter() {
            public boolean touchDown (int x, int y, int pointer, int button) {
                y = Gdx.graphics.getHeight()-y;

                for(Case c : caseColor){
                    if(c.position.contains(x,y)){
                        if(liste.peekFirst().color.equals(c.color)){
                            Cube b = liste.peekFirst();
                            liste.removeFirst();
                            b.remove();
                            caseDetruits++;
                            vie++;

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

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        batch.begin();
        batch.draw(top, margeH,Gdx.app.getGraphics().getHeight()-(top.getHeight()), top.getWidth(),top.getHeight());
        batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        /*if (liste.size() >1)
            System.out.println("A :"+(liste.getFirst().getX()-liste.get(1).getX()-liste.get(1).getWidth()));
        */
        stage.draw();

        //if (liste.size() >1)
            //System.out.println("B :"+(liste.getFirst().getX()-liste.get(1).getX()-liste.get(1).getWidth()));
        update();
        batch.begin();






        for (Case c : caseColor){
            batch.draw(c.texture, c.position.x, c.position.y, c.position.width, c.position.height);
        }
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

    }
    public void updateSpeed(float vitesse){

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
            lose = true;
        }
        else if(liste.size()>0){
            if(liste.peekFirst().getX()+liste.peekFirst().getWidth()>=Gdx.graphics.getWidth()){

                lose= true;

                Cube b = liste.peekFirst();
                liste.removeFirst();
                b.remove();
                caseDetruits++;
                vie++;
            }
        }

        // SI la pile est vide ou si j'ai de la place
        if (liste.size()==0 || liste.peekLast().getX()-75 > liste.peekFirst().getWidth()+20){
            addCube();
        }

        // FIN JEU
        if (lose){
            LoseScreen ls = new LoseScreen(this.g);
            ls.detruite = this.caseDetruits;
            ((Game)Gdx.app.getApplicationListener()).setScreen(ls);
        }
    }





}
