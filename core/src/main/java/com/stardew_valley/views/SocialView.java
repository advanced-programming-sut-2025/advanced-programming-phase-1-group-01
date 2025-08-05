package com.stardew_valley.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.stardew_valley.models.AssetManager;

public class SocialView extends Window {

    public SocialView() {
        super("Social", AssetManager.getAssetManager().getSkin(), "Letter");

        Skin skin = AssetManager.getAssetManager().getSkin();

        // DO NOT MODIFY
        this.setSize(900, 600);
        this.setPosition(Gdx.graphics.getWidth() / 2f - 400, Gdx.graphics.getHeight() / 2f - 300);
        this.setMovable(true);
        this.setVisible(false);
        // DO NOT MODIFY
    }
}
