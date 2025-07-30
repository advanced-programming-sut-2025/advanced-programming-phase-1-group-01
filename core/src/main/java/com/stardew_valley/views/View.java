package com.stardew_valley.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.stardew_valley.Main;

public abstract class View implements Screen {
    protected AppView appView;

    public abstract void handleInput();

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        getStage().act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        getStage().draw();
        Main.getBatch().end();
    }

    public abstract Stage getStage();

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
