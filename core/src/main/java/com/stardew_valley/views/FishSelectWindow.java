package com.stardew_valley.views;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.stardew_valley.Main;
import com.stardew_valley.controllers.GameController;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.fish.FishInfo;

import com.badlogic.gdx.scenes.scene2d.ui.*;

public class FishSelectWindow extends Window {

    private Stage stage;
    private Skin skin;
    private final SelectBox<FishInfo> selectBox;
    private final TextButton startButton;
    private final TextButton backButton;
    private FishingWindow fishingWindow;

    public FishSelectWindow(Stage stage) {
        super("Fish Select", AssetManager.getAssetManager().getSkin());

        this.stage = stage;
        this.skin = AssetManager.getAssetManager().getSkin();
        Table fishTable = new Table();
        fishTable.top();
        fishTable.defaults().expandX().fillX();

        for (FishInfo fish : FishInfo.values()) {
            fishTable.add(new Image(fish.getTexture())).size(48, 48).padRight(10);
            fishTable.add(new Label(fish.getName(), skin)).padRight(20).left();
            fishTable.add(new Label(fish.getBasePrice() + "g", skin)).left().row();
        }

        ScrollPane scrollPane = new ScrollPane(fishTable, skin);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(true, false);

        add(scrollPane).width(600).height(400).row();

        selectBox = new SelectBox<>(skin);
        selectBox.setItems(FishInfo.values());

        startButton = new TextButton("Start", skin);
        backButton = new TextButton("Back", skin);

        add(selectBox).width(200).pad(10);
        row();
        add(startButton).width(150).pad(10);
        row();
        add(backButton).width(150).pad(10);

        pack();

        startButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                    fishingWindow = new FishingWindow(stage, selectBox.getSelected());
                    fishingWindow.setPosition(500, 160);
                    fishingWindow.setSize(1000, 800);
                    stage.addActor(fishingWindow);
            }
        });
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Main.getMain().setScreen(new GameView(new GameController(Repository.getRepo())));
            }
        });
    }

    public SelectBox<FishInfo> getSelectBox() {
        return selectBox;
    }

    public TextButton getStartButton() {
        return startButton;
    }
}
