package com.stardew_valley;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.stardew_valley.controllers.LoginMenuController;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.views.LoginMenuView;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private static Main main;
    private Repository repo;
    private static SpriteBatch batch;

    public static Main getMain() {
        if (main == null) {
            main = new Main();
        }
        return main;
    }

    public static SpriteBatch getBatch() {
        return batch;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        repo = new Repository();
        setScreen(new LoginMenuView(new LoginMenuController(repo)));

    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
