package com.stardew_valley;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.stardew_valley.controllers.GameController;
import com.stardew_valley.controllers.GameMenuController;
import com.stardew_valley.controllers.LoginMenuController;
import com.stardew_valley.controllers.SignUpMenuController;
import com.stardew_valley.models.building.Farm;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.data.User;
import com.stardew_valley.models.enums.Gender;
import com.stardew_valley.models.enums.SecurityQuestion;
import com.stardew_valley.models.initializer.FarmInitializer;
import com.stardew_valley.models.initializer.VillageInitializer;
import com.stardew_valley.views.GameMenuView;
import com.stardew_valley.views.GameView;
import com.stardew_valley.views.LoginMenuView;
import com.stardew_valley.views.SignUpMenuView;

import java.util.ArrayList;
import java.util.List;

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
        main = this;
        batch = new SpriteBatch();
        repo = new Repository();
        setScreen(new SignUpMenuView(new SignUpMenuController(repo)));
        //setScreen(new GameMenuView(new GameMenuController(repo)));
        //setScreen(new LoginMenuView(new LoginMenuController(repo)));
        //setScreen(new GameView(new GameController(repo)));
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
