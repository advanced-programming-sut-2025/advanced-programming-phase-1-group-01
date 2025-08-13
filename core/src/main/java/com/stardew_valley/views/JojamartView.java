package com.stardew_valley.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.shaders.DefaultShader;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.stardew_valley.Main;
import com.stardew_valley.controllers.GameController;
import com.stardew_valley.controllers.ShopControllers.JojaMartController;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.dateTime.Season;
import com.stardew_valley.models.shop.JojaMart;
import com.stardew_valley.models.shop.enums.JojaMartProducts;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class JojamartView extends View {
    private Stage stage;
    private Skin skin;
    private final Image jojamartBackground;
    private final Image background;
    private final SelectBox<String> allProducts;
    private final SelectBox<String> availableProducts;
    private final Label allProductsLabel;
    private final Label availableProductsLabel;
    private final Label jojamartProductsLabel;
    private final Label itemLabel;
    private final TextField itemName;
    private final TextButton buyButton;
    private final Label messageLabel;
    private boolean isShowing = false;

    private JojaMartController controller;

    public JojamartView(JojaMartController controller) {
        skin = AssetManager.getAssetManager().getSkin();
        jojamartBackground = new Image(AssetManager.getAssetManager().getFishShopIn());
        Texture texture = new Texture("shopping/back.png");
        background = new Image(texture);
        allProducts = new SelectBox<>(skin);
        availableProducts = new SelectBox<>(skin);
        itemLabel = new Label("item:", skin);
        itemName = new TextField("", skin);
        buyButton = new TextButton("Buy", skin);
        messageLabel = new Label("", skin);
        allProductsLabel = new Label("All Products", skin);
        availableProductsLabel = new Label("Available Products", skin);
        availableProductsLabel.pack();
        jojamartProductsLabel = new Label("Jojamart Store", skin);
        jojamartProductsLabel.pack();
        this.controller = controller;
    }


    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        jojamartBackground.setSize(Gdx.graphics.getWidth() * 0.65f, Gdx.graphics.getHeight() * 0.75f);
        jojamartBackground.setPosition(350,180);
        stage.addActor(jojamartBackground);

        buyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                try {
                    Integer.parseInt(itemName.getText());
                    controller.buy(availableProducts.getSelected(), Integer.parseInt(itemName.getText()), messageLabel);
                } catch (NumberFormatException e) {
                    messageLabel.setText("Invalid item name");
                }
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
            updateSelectBox();
        }

        else {
            background.remove();
            jojamartProductsLabel.remove();
            allProducts.remove();
            allProductsLabel.remove();
            availableProducts.remove();
            availableProductsLabel.remove();
            itemLabel.remove();
            itemName.remove();
            buyButton.remove();
        }
        isShowing = !isShowing;
    }

    private void showShopUI() {
        background.setScale(6f,5f);
        background.setPosition(600, 250);
        stage.addActor(background);

        jojamartProductsLabel.setAlignment(Align.center);
        jojamartProductsLabel.setFontScale(1.5f);
        jojamartProductsLabel.setPosition(820, 800);
        stage.addActor(jojamartProductsLabel);

        allProductsLabel.setPosition(670,700);
        stage.addActor(allProductsLabel);

        allProducts.setPosition(670, 620);
        stage.addActor(allProducts);

        availableProductsLabel.setPosition(950,700);
        stage.addActor(availableProductsLabel);

        availableProducts.setPosition(1020,620);
        stage.addActor(availableProducts);

        itemLabel.setPosition(900, 530);
        stage.addActor(itemLabel);

        itemName.setPosition(870, 450);
        stage.addActor(itemName);

        buyButton.setPosition(850,330);
        stage.addActor(buyButton);

        messageLabel.setAlignment(Align.center);
        messageLabel.setPosition(
            (Gdx.graphics.getWidth() - messageLabel.getWidth()) / 2f,
            270);
        stage.addActor(messageLabel);
    }

    private void updateSelectBox() {
        Map<JojaMartProducts,Integer> all = Repository.getRepo().getCurrentGame().getJojaMart().getAllProducts();
        Array<String> allProductsItem = new Array<>();
        Array<String> availableProductsItem = new Array<>();
        for (Map.Entry<JojaMartProducts, Integer> p : all.entrySet()) {
            if (p.getValue() == -1) {
                allProductsItem.add(p.getKey().getName() + " unlimited");
            }
            else {
                allProductsItem.add(p.getKey().getName() + " " + p.getValue() + "x");
            }
            if (p.getValue() == 0) continue;
            if (p.getKey().getSeason() != Season.SPECIAL) {
                if (p.getKey().getSeason() != Repository.getRepo().getCurrentGame().getTimeManager().getNow().getSeason()) {
                    continue;
                }
            }

            if (p.getValue() == -1) {
                availableProductsItem.add(p.getKey().getName());
            }
            else {
                availableProductsItem.add(p.getKey().getName());
            }
        }
        allProducts.setItems(allProductsItem);
        availableProducts.setItems(availableProductsItem);
    }
}
