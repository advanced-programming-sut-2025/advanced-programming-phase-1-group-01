package com.stardew_valley.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.stardew_valley.controllers.GameMenuController;
import com.stardew_valley.models.AssetManager;

public class GameMenuView extends View {
    private Stage stage;
    private Skin skin;
    private Table table;

    private Label titleLabel;
    private TextButton backButton;

    private final GameMenuController controller;

    public GameMenuView(GameMenuController controller) {
        this.controller = controller;
        this.skin = AssetManager.getAssetManager().getSkin();

        titleLabel = new Label("Fuck", skin);
        backButton = new TextButton("Back", skin);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table = new Table(skin);
        table.setFillParent(true);
        table.center();
        table.add(titleLabel).pad(10).row();
        table.add(backButton).pad(10).row();

        stage.addActor(table);

        handleInput();
    }

    @Override
    public void handleInput() {
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.back(titleLabel);
            }
        });
    }

    @Override
    public Stage getStage() {
        return stage;
    }
}
