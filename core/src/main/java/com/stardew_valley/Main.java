package com.stardew_valley;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.stardew_valley.controllers.*;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.data.User;
import com.stardew_valley.models.enums.Gender;
import com.stardew_valley.models.enums.SecurityQuestion;
import com.stardew_valley.views.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.stardew_valley.models.data.Repository;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private static Main main;
    private static SpriteBatch batch;
    private static Repository repo;

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
        main = this;

        //setScreen(new SignUpMenuView(new SignUpMenuController(new Repository())));
        //setScreen(new GameMenuView(new GameMenuController(repo)));
        //setScreen(new TempLogin(new Repository()));
        setScreen(new CookingView(new CookingController(repo)));
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
