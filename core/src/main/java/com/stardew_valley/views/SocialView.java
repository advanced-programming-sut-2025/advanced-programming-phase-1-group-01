package com.stardew_valley.views;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.stardew_valley.models.AssetManager;

public class SocialView extends GameWindow {

    public SocialView(Stage stage) {
        super("Social", AssetManager.getAssetManager().getSkin(), "Letter", stage);
    }

    @Override
    public void update() {

    }
}
