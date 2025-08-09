package com.stardew_valley.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.stardew_valley.Main;
import com.stardew_valley.controllers.CraftingController;
import com.stardew_valley.controllers.GameController;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.cooking.CookingRecipes;
import com.stardew_valley.models.crafting.CraftingRecipe;
import com.stardew_valley.models.crafting.enums.CraftingRecipes;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;

public class CraftingView extends View {
    private Stage stage;
    private Skin skin;
    private final Texture workShop;
    private final Image workShopImage;
    private Texture background;
    private final Image backgroundImage;

    private Texture recipes;
    private final Image recipesImage;
    private List<Image> recipeItemImages;
    private SelectBox<String> craftSelection;
    private TextButton craftButton;

    private Label messageLabel;

    private boolean isPressedRecipes = false;
    private CraftingController controller;

    public CraftingView(CraftingController controller) {
        this.controller = controller;
        skin = AssetManager.getAssetManager().getSkin();
        workShop = new Texture("crafting/workshop.png");
        workShopImage = new Image(workShop);
        background = new Texture("crafting/background.png");
        backgroundImage = new Image(background);
        recipes = new Texture("crafting/recipe.png");
        recipesImage = new Image(recipes);
        recipeItemImages = new ArrayList<>();
        Array<String> recipeNames = new Array<>();
        for (CraftingRecipes r : CraftingRecipes.values()) {
            recipeNames.add(r.name().replace("_", " "));
        }
        craftSelection = new SelectBox<>(skin);
        craftSelection.setItems(recipeNames);
        craftButton = new TextButton("Craft", skin);
        messageLabel = new Label("", skin);
        messageLabel.setFontScale(0.7f);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        workShopImage.setSize(Gdx.graphics.getWidth() * 0.8f, Gdx.graphics.getHeight() * 0.8f);
        workShopImage.setPosition(200,100);

        stage.addActor(workShopImage);

        craftButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.craft(messageLabel,craftSelection.getSelected());
            }
        });
    }

    @Override
    public void handleInput() {

        if (Gdx.input.isKeyJustPressed(Input.Keys.X)) {
            Main.getMain().setScreen(new GameView(new GameController(controller.getRepo())));
            return;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.B)) {
            toggleRecipes();
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
        handleInput();
    }

    @Override
    public Stage getStage() {
        return stage;
    }

    private void toggleRecipes() {
        if (!isPressedRecipes) {
            showRecipesUI();
            showRecipes();
        }
        else {
            backgroundImage.remove();
            recipesImage.remove();
            craftSelection.remove();
            craftButton.remove();
            messageLabel.remove();
            removeRecipes();
        }
        isPressedRecipes = !isPressedRecipes;
    }

    private void showRecipesUI() {
        backgroundImage.setScale(6f,5f);
        backgroundImage.setPosition(600, 250);
        stage.addActor(backgroundImage);

        craftSelection.setPosition(870, 460);
        stage.addActor(craftSelection);

        craftButton.setPosition(870,330);
        stage.addActor(craftButton);

        recipesImage.setPosition(650, 580);
        stage.addActor(recipesImage);

        messageLabel.setAlignment(Align.center);

        messageLabel.setPosition(
            (Gdx.graphics.getWidth() - messageLabel.getWidth()) / 2f,
            290);
        stage.addActor(messageLabel);
    }

    public void showRecipes() {
        float cellWidth = 81f;
        float cellHeight = 82f;
        float startX = 670;
        float startY = 600;

        int columns = 8;

        Set<CraftingRecipe> recipes = controller.getRepo().getCurrentGame().getCurrentPlayer().getCraftingRecipes();
        int index = 0;
        for (CraftingRecipe recipe : recipes) {
            int col = index % columns;
            int row = index / columns;

            float x = startX + col * cellWidth;
            float y = startY + (2 - row) * cellHeight;

            Image cellImage = recipe.getImage();
            cellImage.setPosition(x, y);

            CraftingRecipes matched = null;
            for (CraftingRecipes r : CraftingRecipes.values()) {
                if (r.getName().equalsIgnoreCase(recipe.getName())) {
                    matched = r;
                }
            }

            if (isSmall(matched)) {
                cellImage.setScale(1.2f, 1.2f);
            }

            else {
                cellImage.setScale(0.7f, 0.7f);
            }
            stage.addActor(cellImage);
            recipeItemImages.add(cellImage);

            index++;
        }
    }

    private void removeRecipes() {
        for (Image cellImage : recipeItemImages) {
            cellImage.remove();
        }
    }

    private boolean isSmall(CraftingRecipes recipe) {
        ArrayList<CraftingRecipes> recipes = new ArrayList<>();
        recipes.add(CraftingRecipes.BOMB);
        recipes.add(CraftingRecipes.CHERRY_BOMB);
        recipes.add(CraftingRecipes.MEGA_BOMB);
        recipes.add(CraftingRecipes.SPRINKLER);
        recipes.add(CraftingRecipes.QUALITY_SPRINKLER);
        recipes.add(CraftingRecipes.IRIDIUM_SPRINKLER);
        recipes.add(CraftingRecipes.MYSTIC_TREE_SEED);
        if (recipes.contains(recipe)) {
            return true;
        }
        return false;
    }
}
