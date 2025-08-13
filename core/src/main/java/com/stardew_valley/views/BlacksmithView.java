package com.stardew_valley.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.stardew_valley.Main;
import com.stardew_valley.controllers.GameController;
import com.stardew_valley.controllers.ShopControllers.BlackSmithController;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.data.Repository;


public class BlacksmithView extends View {
    private Stage stage;
    private Skin skin;
    private final Label blacksmithLabel;
    private final Image blacksmithBackground;
    private final Image background;
    private final Label toolLabel;
    private final SelectBox<String> toolSelectBox;
    private final Label qualityLabel;
    private final SelectBox<String> qualitySelectBox;
    private final Label priceLabel;
    private final TextButton selectButton;
    private final Label messageLabel;
    private boolean isShowing = false;

    private final BlackSmithController controller;

    public BlacksmithView(BlackSmithController controller) {
        skin = AssetManager.getAssetManager().getSkin();
        blacksmithLabel = new Label("Blacksmith", skin);
        blacksmithBackground = new Image(AssetManager.getAssetManager().getBlackSmithIn());
        Texture texture = new Texture("shopping/back.png");
        background = new Image(texture);
        toolLabel = new Label("Tool:", skin);
        toolSelectBox = new SelectBox<>(skin);
        toolSelectBox.setItems("Axe", "Pickaxe", "Hoe", "WateringCan", "TrashCan", "Backpack");
        qualityLabel = new Label("Quality:", skin);
        qualitySelectBox = new SelectBox<>(skin);
        priceLabel = new Label("Price: ?", skin);
        selectButton = new TextButton("Select", skin);
        messageLabel = new Label("", skin);
        this.controller = controller;
    }


    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        blacksmithBackground.setSize(Gdx.graphics.getWidth() * 0.65f, Gdx.graphics.getHeight() * 0.75f);
        blacksmithBackground.setPosition(350,180);
        stage.addActor(blacksmithBackground);

        selectButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.upgrade(toolSelectBox.getSelected(),messageLabel);
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        messageLabel.setText("");
                    }
                }, 2);
            }
        });
    }

    @Override
    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            toggleView();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.Z)) {
            Main.getMain().setScreen(new GameView(new GameController(Repository.getRepo())));
        }

        updateSelectBox();
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

    private void toggleView() {
        if (!isShowing) {
            showShopUI();
        }

        else {
            blacksmithLabel.remove();
            background.remove();
            toolLabel.remove();
            toolSelectBox.remove();
            qualityLabel.remove();
            qualitySelectBox.remove();
            priceLabel.remove();
            selectButton.remove();
            messageLabel.remove();
        }
        isShowing = !isShowing;
    }

    private void showShopUI() {
        background.setScale(6f,5f);
        background.setPosition(600, 250);
        stage.addActor(background);

        blacksmithLabel.setAlignment(Align.center);
        blacksmithLabel.setFontScale(1.5f);
        blacksmithLabel.setPosition(870, 800);
        stage.addActor(blacksmithLabel);

        toolLabel.setPosition(670,650);
        stage.addActor(toolLabel);

        toolSelectBox.setPosition(670, 570);
        stage.addActor(toolSelectBox);

        qualityLabel.setPosition(950,650);
        stage.addActor(qualityLabel);

        qualitySelectBox.setPosition(1020,570);
        stage.addActor(qualitySelectBox);

        priceLabel.setPosition(840,450);
        stage.addActor(priceLabel);

        selectButton.setPosition(850,330);
        stage.addActor(selectButton);

        messageLabel.setAlignment(Align.center);
        messageLabel.setPosition(
            (Gdx.graphics.getWidth() - messageLabel.getWidth()) / 2f,
            270);
        stage.addActor(messageLabel);
    }

    private void updateSelectBox() {
        switch (toolSelectBox.getSelected()) {
            case "Axe", "WateringCan", "Hoe", "TrashCan", "Pickaxe":
                qualitySelectBox.setItems("Copper", "Iron", "Gold", "Iridium");
                break;
            case "Backpack":
                qualitySelectBox.setItems("Big", "Deluxe");
        }

        switch (qualitySelectBox.getSelected()) {
            case "Copper", "Big":
                priceLabel.setText("price: 2000");
                break;
            case "Iron":
                priceLabel.setText("price: 5000");
                break;
            case "Gold", "Deluxe":
                priceLabel.setText("price: 10000");
                break;
            case "Iridium":
                priceLabel.setText("price: 25000");
                break;
        }
    }
}
