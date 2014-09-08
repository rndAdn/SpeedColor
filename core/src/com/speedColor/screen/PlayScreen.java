package com.speedColor.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class PlayScreen implements Screen {

    private BitmapFont font;
    private SpriteBatch batch;

    public PlayScreen(Game container){
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.scale((0.5f));
        batch = new SpriteBatch();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0.128f, 0.128f, 0.255f, 1);
        batch.begin();
        font.draw(batch, "FPS : " + Gdx.graphics.getFramesPerSecond(), 20, 20);
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
        batch.dispose();
        font.dispose();
    }

}
