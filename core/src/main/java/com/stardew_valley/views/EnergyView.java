package com.stardew_valley.views;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.stardew_valley.controllers.EnergyController;
import com.stardew_valley.models.AssetManager;

public class EnergyView extends View {
    private Stage stage;
    private Skin skin;

    private EnergyController controller;

    public EnergyView(EnergyController controller) {
        this.controller = controller;
        skin = AssetManager.getAssetManager().getSkin();
    }

    @Override
    public void handleInput() {

    }

    @Override
    public Stage getStage() {
        return stage;
    }

    @Override
    public void show() {

    }
}
