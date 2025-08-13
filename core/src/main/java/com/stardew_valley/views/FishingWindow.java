package com.stardew_valley.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.stardew_valley.Main;
import com.stardew_valley.controllers.GameController;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.Item;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.fish.FishInfo;

public class FishingWindow extends Window {

    private final Stage stage;
    private final Image bgImage;
    private final Image fish;
    private final Image bigFish;
    private final Image crown;
    private final Image rod;
    private final Image catchBar;
    private final Image fishingBar;

    private float time = 0f;
    private float fishY;
    private float fishSpeed = 80f;
    private float catchBarY;
    private float noiseAmplitude = 5f;
    private float noiseSpeed = 40f;
    private float catchBarSpeed = 300f;

    private final ProgressBar levelBar;
    private float progress = 0f;
    private Label statusLabel;
    private final FishInfo fishInfo;
    private final Label isPerfectLabel;
    private TextButton backButton;
    private boolean finished;

    private FishSelectWindow fishSelectWindow;

    public FishingWindow(Stage stage, FishInfo fishInfo) {
        super("Fishing Mini-Game", AssetManager.getAssetManager().getSkin());
        this.stage = stage;

        bgImage = new Image(new Texture("fishing/background.png"));
        bgImage.setSize(950, 750);
        bgImage.setPosition(25, 0);

        fishingBar = new Image(new Texture("fishing/bar.png"));
        fishingBar.setSize(50, 600);
        fishingBar.setPosition(850, 100);

        this.fishInfo = fishInfo;
        fish = new Image(fishInfo.getTexture());
        fish.setSize(32, 32);

        bigFish = new Image(fishInfo.getTexture());
        bigFish.setSize(80, 80);
        bigFish.setPosition(400, 300);
        bigFish.setVisible(false);

        crown = new Image(AssetManager.getAssetManager().getCrown());
        crown.setSize(60, 60);
        crown.setPosition(430, 370);
        crown.setVisible(false);

        rod = new Image(AssetManager.getAssetManager().getFiberglassRod());
        rod.setSize(80, 80);
        rod.setPosition(300, 400);

        catchBar = new Image(new Texture("fishing/catch_bar.png"));
        catchBar.setSize(22, 90);

        levelBar = new ProgressBar(0, 1, 0.01f, false, getSkin());
        levelBar.setValue(progress);
        levelBar.setSize(500,30);
        levelBar.setPosition(300,170);

        Label.LabelStyle style = new Label.LabelStyle();
        style.font = new BitmapFont();
        style.fontColor = Color.ORANGE;

        statusLabel = new Label("fishing status: ", style);
        statusLabel.setPosition(100,180);
        statusLabel.setFontScale(2f);

        isPerfectLabel = new Label("Perfect", style);
        isPerfectLabel.setPosition(400,700);
        isPerfectLabel.setFontScale(3f);

        backButton = new TextButton("Back",getSkin());
        backButton.setPosition(400,50);

        float diff = fishDifficulty(fishInfo);
        fishSpeed = fishSpeed * diff * 0.8f;
        noiseAmplitude = noiseAmplitude * diff;
        noiseSpeed = noiseSpeed * diff;

        finished = false;

        addActor(bgImage);
        addActor(fishingBar);
        addActor(catchBar);
        addActor(fish);
        addActor(bigFish);
        addActor(crown);
        addActor(rod);
        addActor(levelBar);
        addActor(statusLabel);
        addActor(isPerfectLabel);
        addActor(backButton);

        fishY = 110;
        catchBarY = 110;

        stage.addActor(this);

        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                fishSelectWindow = new FishSelectWindow(stage);
                fishSelectWindow.setPosition(500, 160);
                fishSelectWindow.setSize(1000, 800);
                stage.addActor(fishSelectWindow);
            }
        });
    }

    float delay = 0f;
    @Override
    public void act(float delta) {

        if (finished) {
            return;
        }

        super.act(delta);

        delay += delta;

        if (delay < 2f) {
            return;
        }

        float minY = 120;
        float maxY = 650;

        fishY += fishSpeed * delta;

        if (fishY > maxY) {
            fishY = maxY;
            fishSpeed *= -1;
        } else if (fishY < minY) {
            fishY = minY;
            fishSpeed *= -1;
        }

        float noise = noiseAmplitude * MathUtils.sin(time * noiseSpeed);
        fish.setPosition(860, fishY + noise);

        time += delta;

        if (Gdx.input.isKeyPressed(Input.Keys.F)) {
            catchBarY += catchBarSpeed * delta;
        } else {
            catchBarY -= catchBarSpeed * delta;
        }
        catchBarY = MathUtils.clamp(catchBarY, 120, 590);
        catchBar.setPosition(865, catchBarY);

        boolean catching =
            fishY + fish.getHeight() > catchBarY &&
                fishY < catchBarY + catchBar.getHeight();

        if (catching) {
            progress += delta * 0.1f;
        } else {
            isPerfectLabel.setText("Not Perfect");
            progress -= delta * 0.6f;
        }

        progress = MathUtils.clamp(progress, 0,1);
        levelBar.setValue(progress);
        if (progress >= 1f) {
            Repository.getRepo().getCurrentUser().getPlayer().getInventory().addItem(fishInfo.getName(),1);
            if (isPerfectLabel.getText().toString().equals("Perfect")) {
                Repository.getRepo().getCurrentUser().getPlayer().getInventory().addItem(fishInfo.getName(),1);
            }
            bigFish.setVisible(true);
            if (fishInfo.isLegendary()) {
                crown.setVisible(true);
            }
            finished = true;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.U)) {
             fishSelectWindow = new FishSelectWindow(stage);
            fishSelectWindow.setPosition(500, 160);
            fishSelectWindow.setSize(1000, 800);
            stage.addActor(fishSelectWindow);
        }
    }

    private float fishDifficulty(FishInfo fishInfo) {
        return MathUtils.clamp(fishInfo.getBasePrice() / 500f, 0.5f, 4f);
    }
}
