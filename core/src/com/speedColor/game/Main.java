package com.speedColor.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.speedColor.screen.*;

public class Main extends Game {

    private MenuScreen menu;
    private PlayScreen jouer;
    private PauseScreen pause;
    private OptionScreen option;

    @Override
    public void create() {
        setMenu(new MenuScreen(this));
        setJouer(new PlayScreen(this));
        setPause(new PauseScreen(this));
        setOption(new OptionScreen(this));

        this.menu = new MenuScreen(this);

        Gdx.input.setCatchBackKey(true);
        setScreen(this.jouer);
    }


    public MenuScreen getMenu() {
        return menu;
    }

    public void setMenu(MenuScreen menu) {
        this.menu = menu;
    }

    public PlayScreen getJouer() {
        return jouer;
    }

    public void setJouer(PlayScreen jouer) {
        this.jouer = jouer;
    }

    public PauseScreen getPause() {
        return pause;
    }

    public void setPause(PauseScreen pause) {
        this.pause = pause;
    }

    public OptionScreen getOption() {
        return option;
    }

    public void setOption(OptionScreen option) {
        this.option = option;
    }


}
