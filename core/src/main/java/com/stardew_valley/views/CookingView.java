package com.stardew_valley.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.stardew_valley.Main;
import com.stardew_valley.controllers.CookingController;
import com.stardew_valley.models.AssetManager;

public class CookingView extends View {

    private Stage stage;
    private Skin skin;
    private Texture kitchen;
    private Texture refrigerator;
    private Texture recipes;

    private Label messageLabel;

    private CookingController controller;

    public CookingView(CookingController controller) {
        this.controller = controller;
        skin = AssetManager.getAssetManager().getSkin();
        kitchen = new Texture("cooking/kitchen.png");
        refrigerator = new Texture("cooking/refrigerator.png");
        recipes = new Texture("cooking/recipe.png");
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Main.getBatch().begin();
        float bgWidth = kitchen.getWidth() * 2f;
        float bgHeight = kitchen.getHeight() * 2f;
        Main.getBatch().draw(kitchen, 200, 150, bgWidth, bgHeight);
        Main.getBatch().end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void handleInput() {

    }

    @Override
    public Stage getStage() {
        return stage;
    }
}
