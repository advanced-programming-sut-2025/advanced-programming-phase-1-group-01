package com.stardew_valley.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.stardew_valley.models.AssetManager;

public class GiftView extends GameWindow {

    public GiftView() {
        super("Gift", AssetManager.getAssetManager().getSkin(), "Letter");
    }

    @Override
    public void update() {

    }
}
