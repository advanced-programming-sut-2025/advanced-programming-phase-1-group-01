package com.stardew_valley;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.stardew_valley.controllers.LoginMenuController;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.data.User;
import com.stardew_valley.models.enums.Gender;
import com.stardew_valley.models.enums.SecurityQuestion;
import com.stardew_valley.views.AppView;
import com.stardew_valley.views.LoginMenuView;
import com.badlogic.gdx.utils.ScreenUtils;
import com.stardew_valley.controllers.GameController;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.views.GameView;
import com.stardew_valley.views.TempLogin;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private static Main main;
    private Repository repo;
    private static SpriteBatch batch;

    public static Main getMain() {
        return main;
    }

    public static SpriteBatch getBatch() {
        return batch;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        repo = new Repository();
        main = this;
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
