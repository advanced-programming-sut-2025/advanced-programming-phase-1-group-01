package com.stardew_valley.views;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.stardew_valley.models.AssetManager;

public class MiniMapView extends GameWindow {

    public MiniMapView(Stage stage) {
        super("Map", AssetManager.getAssetManager().getSkin(), "Letter", stage);
    }

    @Override
    public void update() {

    }
}
