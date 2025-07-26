package com.stardew_valley.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.Game;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.data.User;
import com.stardew_valley.models.enums.Gender;

import java.util.ArrayList;
import java.util.List;

public class TempLogin extends View {
    private Stage stage;

    private TextButton tempLoginButton;
    private Table table;

    private Repository repo;

    public TempLogin(Repository repo) {
        this.repo = repo;

        Skin skin = AssetManager.getAssetManager().getSkin();

        tempLoginButton = new TextButton("Temp Login", skin);
        table = new Table(skin);
    }

    @Override
    public void handleInput() {
//        empty for phase 2
    }

    @Override
    public Stage getStage() {
        return stage;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();

        table.add(tempLoginButton);

        tempLoginButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                makeNewGame();
            }
        });

        stage.addActor(table);
    }

    public void makeNewGame() {
//        User user = new User("KiaValliant", "Amirkiaghm1385", "Amirkia", "akghasemim@gmail.com", Gender.MALE);
//        Game game = new Game(List.of(user.getPlayer()));
//        repo.setCurrentGame(game);
    }
}
