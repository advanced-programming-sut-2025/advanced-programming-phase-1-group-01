package com.stardew_valley.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.stardew_valley.models.data.Repository;

public abstract class GameWindow extends Window {
    protected Repository repo = Repository.getRepo();
    protected Stage stage;

    public GameWindow(String title, Skin skin, String styleName, Stage stage) {
        super(title, skin, styleName);
        this.stage = stage;

        setSize(900, 600);
        setPosition(Gdx.graphics.getWidth() / 2f - 400, Gdx.graphics.getHeight() / 2f - 300);
        setMovable(true);
        setResizable(false);
        setVisible(false);

        stage.addActor(this);
    }

    public abstract void update();
}
