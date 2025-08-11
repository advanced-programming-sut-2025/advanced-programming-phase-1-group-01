package com.stardew_valley.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.stardew_valley.Main;
import com.stardew_valley.controllers.CookingController;
import com.stardew_valley.controllers.GameController;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.Item;
import com.stardew_valley.models.character.player.Ability;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.character.player.Refrigerator;
import com.stardew_valley.models.character.player.Slot;
import com.stardew_valley.models.cooking.CookingRecipe;
import com.stardew_valley.models.cooking.CookingRecipes;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;



public class CookingView extends View {

    private Stage stage;
    private Skin skin;
    private final Texture kitchen;
    private Texture background;
    private final Image backgroundImage;

    private Texture refrigerator;
    private final Image refrigeratorImage;
    private final List<Label> refrigeratorItemCount;
    private final Label refrigeratorLabel;
    private final List<Image> refrigeratorItemImages;

    private Texture inventory;
    private final Image inventoryImage;
    private final List<Image> inventoryItemImages;
    private final Label inventoryLabel;
    private final List<Label> inventoryItemCount;

    private Texture recipes;
    private final Image recipesImage;
    private final List<Image> recipeItemImages;

    private final TextButton cookButton;
    private final SelectBox<String> foodSelection;
    private final TextButton putButton;
    private final TextButton pickButton;
    private final TextField itemTextField;
    private final TextField itemCountTextField;
    private final Label messageLabel;
    private final Label hoverLabel;

    private boolean isPressedRecipes = false;
    private boolean isPressedRefrigerator = false;
    private CookingController controller;

    public CookingView(CookingController controller) {
        this.controller = controller;
        skin = AssetManager.getAssetManager().getSkin();
        kitchen = new Texture("cooking/kitchen.png");
        background = new Texture("cooking/back.png");
        backgroundImage = new Image(background);
        refrigerator = new Texture("cooking/refrigerator.png");
        refrigeratorImage = new Image(refrigerator);
        refrigeratorItemImages = new ArrayList<>();
        refrigeratorItemCount = new ArrayList<>();
        refrigeratorLabel = new Label("Refrigerator", skin);
        inventory = new Texture("cooking/refrigerator.png");
        inventoryImage = new Image(refrigerator);
        inventoryItemImages = new ArrayList<>();
        inventoryItemCount = new ArrayList<>();
        inventoryLabel = new Label("Inventory", skin);
        recipes = new Texture("cooking/recipe.png");
        recipesImage = new Image(recipes);
        recipeItemImages = new ArrayList<>();
        cookButton = new TextButton("Cook", skin);
        foodSelection = new SelectBox<>(skin);
        Array<String> recipeNames = new Array<>();
        for (CookingRecipes r : CookingRecipes.values()) {
            recipeNames.add(r.name().replace("_", " "));
        }
        foodSelection.setItems(recipeNames);
        putButton = new TextButton("Put", skin);
        pickButton = new TextButton("Pick", skin);
        itemTextField = new TextField("item?", skin);
        itemCountTextField = new TextField("how?", skin);
        messageLabel = new Label("", skin);
        messageLabel.setFontScale(0.7f);

        hoverLabel = new Label("", skin);
        hoverLabel.setVisible(false);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Image kitchenImage = new Image(kitchen);
        kitchenImage.setSize(Gdx.graphics.getWidth() * 0.8f, Gdx.graphics.getHeight() * 0.8f);
        kitchenImage.setPosition(200,100);
        stage.addActor(kitchenImage);

        putButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.put(messageLabel,itemTextField.getText(),itemCountTextField.getText());

                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        messageLabel.setText("");
                    }
                },  //1
                    0.01f
                );
            }
        });

        pickButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.pick(messageLabel,itemTextField.getText(),itemCountTextField.getText());

                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        messageLabel.setText("");
                    }
                },  //1
                    0.01f
                );
            }
        });

        cookButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.cook(messageLabel, foodSelection.getSelected());

                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        messageLabel.setText("");
                    }
                },  //1
                    0.01f
                );
            }
        });

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
    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            toggleRecipes();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            toggleFridgeAndInventory();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.Z)) {
            Main.getMain().setScreen(new GameView(new GameController(controller.getRepo())));
        }
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
            foodSelection.remove();
            cookButton.remove();
            messageLabel.remove();
            removeRecipes();
        }
        isPressedRecipes = !isPressedRecipes;
    }

    private void toggleFridgeAndInventory() {
        if (!isPressedRefrigerator) {
            showFridgeInventoryUI();
            showRefrigeratorItems();
            showInventoryItems();
        }
        else {
            hideFridgeInventoryUI();
            clearRefrigeratorItems();
            clearInventoryItems();
        }
        isPressedRefrigerator = !isPressedRefrigerator;
    }

    private void showRecipesUI() {
        backgroundImage.setScale(6f,5f);
        backgroundImage.setPosition(600, 250);
        stage.addActor(backgroundImage);

        foodSelection.setPosition(870, 460);
        stage.addActor(foodSelection);

        cookButton.setPosition(870,330);
        stage.addActor(cookButton);

        recipesImage.setPosition(650, 580);
        stage.addActor(recipesImage);

        messageLabel.setAlignment(Align.center);
        messageLabel.setPosition(
            (Gdx.graphics.getWidth() - messageLabel.getWidth()) / 2f,
            270);
        stage.addActor(messageLabel);

        stage.addActor(hoverLabel);
    }

    private void showFridgeInventoryUI() {
        backgroundImage.setScale(6f,7.5f);
        backgroundImage.setPosition(600, 100);
        stage.addActor(backgroundImage);

        refrigeratorImage.setScale(0.5f, 0.5f);
        refrigeratorImage.setPosition(650, 700);
        refrigeratorLabel.setText("Refrigerator");
        refrigeratorLabel.setPosition(850, 950);
        stage.addActor(refrigeratorLabel);
        stage.addActor(refrigeratorImage);

        inventoryImage.setScale(0.5f, 0.5f);
        inventoryImage.setPosition(650, 350);
        inventoryLabel.setText("Inventory");
        inventoryLabel.setPosition(870, 600);
        pickButton.setPosition(750, 250);
        putButton.setPosition(950, 250);
        itemTextField.setPosition(970, 170);
        itemCountTextField.setPosition(770, 170);
        stage.addActor(inventoryLabel);
        stage.addActor(inventoryImage);
        stage.addActor(pickButton);
        stage.addActor(putButton);
        stage.addActor(itemTextField);
        stage.addActor(itemCountTextField);

        messageLabel.setAlignment(Align.center);
        messageLabel.setPosition(
            (Gdx.graphics.getWidth() - messageLabel.getWidth()) / 2f,
            145);
        stage.addActor(messageLabel);
    }

    private void hideFridgeInventoryUI() {
        backgroundImage.remove();
        refrigeratorImage.remove();
        refrigeratorLabel.remove();
        inventoryImage.remove();
        inventoryLabel.remove();
        pickButton.remove();
        putButton.remove();
        itemTextField.remove();
        itemCountTextField.remove();
        messageLabel.remove();
    }

    public void showRecipes() {
        float cellWidth = 81f;
        float cellHeight = 82f;
        float startX = 670;
        float startY = 600;

        int columns = 8;
        Set<CookingRecipe> recipes = controller.getRepo().getCurrentGame().getCurrentPlayer().getCookingRecipes();
        int index = 0;
        for (CookingRecipe recipe : recipes) {
            int col = index % columns;
            int row = index / columns;

            float x = startX + col * cellWidth;
            float y = startY + (2 - row) * cellHeight;

            Image cellImage = recipe.getImage();
            cellImage.setPosition(x, y);
            stage.addActor(cellImage);
            recipeItemImages.add(cellImage);

            CookingRecipes recipeName = null;
            for (CookingRecipes r: CookingRecipes.values()) {
                if (r.getName().equals(recipe.getName())) {
                    recipeName = r;
                }
            }

            setupTooltip(cellImage,recipeName,hoverLabel);

            index++;
        }
    }

    public void removeRecipes() {
        for (Image cellImage : recipeItemImages) {
            cellImage.remove();
        }
    }

    public void showRefrigeratorItems() {
        clearRefrigeratorItems();

        Refrigerator refrigerator = controller.getRepo().getCurrentGame().getCurrentPlayer().getRefrigerator();
        Map<String,Integer> items = refrigerator.getItems();
        float startX = 680f;
        float startY = 933f;
        float cellWidth = 40f;
        float cellHeight = 42f;

        int cols = 14;
        int i = 0;
        for (Map.Entry<String,Integer> entry : items.entrySet()) {
            int col = i % cols;
            int row = i / cols;

            float x = startX + col * cellWidth;
            float y = startY - (row + 1) * cellHeight;

            Player player = controller.getRepo().getCurrentGame().getCurrentPlayer();
            Item item = player.getInventory().getNewItem(entry.getKey());
            Image image = new Image(item.getTexture());
            image.setBounds(x, y, 30f, 30f);

            refrigeratorItemImages.add(image);
            stage.addActor(image);
            i++;

            Label.LabelStyle labelStyle = new Label.LabelStyle();
            labelStyle.font = new BitmapFont();
            Label quantityLabel = new Label(String.valueOf(entry.getValue()), labelStyle);

            quantityLabel.setFontScale(1.2f);
            quantityLabel.setPosition(x + 10, y);
            refrigeratorItemCount.add(quantityLabel);
            stage.addActor(quantityLabel);
        }
    }

    public void clearRefrigeratorItems() {
        for (Image img : refrigeratorItemImages) {
            img.remove();
        }
        refrigeratorItemImages.clear();
        for (Label label : refrigeratorItemCount) {
            label.remove();
        }
        refrigeratorItemCount.clear();
    }

    public void showInventoryItems() {
        clearInventoryItems();
        List<Slot> slots = controller.getRepo().getCurrentGame().getCurrentPlayer().getInventory().getSlots();

        float startX = 680f;
        float startY = 583f;
        float cellWidth = 40f;
        float cellHeight = 42f;

        int cols = 14;
        for (int i = 0; i < slots.size(); i++) {
            Slot slot = slots.get(i);
            int col = i % cols;
            int row = i / cols;

            float x = startX + col * cellWidth;
            float y = startY - (row + 1) * cellHeight;

            Image image = new Image(slot.getItem().getTexture());
            image.setBounds(x, y, 30f, 30f);

            refrigeratorItemImages.add(image);
            stage.addActor(image);

            Label.LabelStyle labelStyle = new Label.LabelStyle();
            labelStyle.font = new BitmapFont();
            Label quantityLabel = new Label(String.valueOf(slot.getQuantity()), labelStyle);

            quantityLabel.setFontScale(1.2f);
            quantityLabel.setPosition(x + 10, y);
            refrigeratorItemCount.add(quantityLabel);
            stage.addActor(quantityLabel);
        }
    }

    public void clearInventoryItems() {
        for (Image img : inventoryItemImages) {
            img.remove();
        }
        inventoryItemImages.clear();
        for (Label label : inventoryItemCount) {
            label.remove();
        }
        inventoryItemCount.clear();
    }

    private void setupTooltip(final Image recipeImage, final CookingRecipes recipe, final Label hoverLabel) {
        recipeImage.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                StringBuilder stringBuilder = new StringBuilder();
                for (Map.Entry<String, Integer> ingredient : recipe.getIngredients().entrySet()) {
                    stringBuilder.append(ingredient.getKey());
                    stringBuilder.append(": ");
                    stringBuilder.append(ingredient.getValue());
                    stringBuilder.append("\n");
                }
                hoverLabel.setText(stringBuilder.toString());
                hoverLabel.pack();
                hoverLabel.setPosition(650,400);
                hoverLabel.setVisible(true);
            }


            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                hoverLabel.setVisible(false);
            }
        });
    }
}
