package com.stardew_valley.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.stardew_valley.models.AssetManager;

public class SocialView extends GameWindow {

    public SocialView() {
        super("Social", AssetManager.getAssetManager().getSkin(), "Letter");
    }

    @Override
    public void update() {

    }
}
