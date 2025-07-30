package com.stardew_valley.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.stardew_valley.Main;
import com.stardew_valley.controllers.CookingController;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.cooking.CookingRecipe;
import com.stardew_valley.models.cooking.CookingRecipes;

import java.util.List;
import java.util.ArrayList;

public class CookingView extends View {

    private Stage stage;
    private Skin skin;
    private Texture kitchen;
    private Texture refrigerator;
    private Texture recipes;
    private Image kitchenImage;
    private Image refrigeratorImage;
    private Image recipesImage;
    private List<Image> recipeImages;
    private Label messageLabel;

    private boolean isPressed = false;
    private CookingController controller;

    public CookingView(CookingController controller) {
        this.controller = controller;
        skin = AssetManager.getAssetManager().getSkin();
        kitchen = new Texture("cooking/kitchen.png");
        kitchenImage = new Image(kitchen);
        refrigerator = new Texture("cooking/refrigerator.png");
        refrigeratorImage = new Image(refrigerator);
        recipes = new Texture("cooking/recipe.png");
        recipesImage = new Image(recipes);
        recipeImages = new ArrayList<>();
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Main.getBatch().begin();
        float bgWidth = kitchen.getWidth() * 2.2f;
        float bgHeight = kitchen.getHeight() * 2.2f;
        Main.getBatch().draw(kitchen, 100, 150, bgWidth, bgHeight);
        Main.getBatch().end();

        stage.act(delta);
        stage.draw();
        handleInput();
    }

    @Override
    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            if (!isPressed) {
                recipesImage.setPosition(350, 700);
                stage.addActor(recipesImage);
                isPressed = true;
                showRecipes();
            }

            else if (isPressed) {
                recipesImage.remove();
                isPressed = false;
                removeRecipes();
            }
        }
    }

    @Override
    public Stage getStage() {
        return stage;
    }


    public void showRecipes() {
        float cellWidth = 81f;
        float cellHeight = 82f;
        float startX = 370;
        float startY = 720;

        int columns = 8;
        //Set<CookingRecipe> recipes = controller.getRepo().getCurrentGame().getCurrentPlayer().getCookingRecipes();
        int index = 0;
        for (CookingRecipes recipe : CookingRecipes.values()) {
            int col = index % columns;
            int row = index / columns;

            float x = startX + col * cellWidth;
            float y = startY + (2 - row) * cellHeight;

            Image cellImage = recipe.toRecipe().getImage();
            cellImage.setPosition(x, y);
            stage.addActor(cellImage);
            recipeImages.add(cellImage);

            index++;
        }
    }

    public void removeRecipes() {
        for (Image cellImage : recipeImages) {
            cellImage.remove();
        }
    }
}
