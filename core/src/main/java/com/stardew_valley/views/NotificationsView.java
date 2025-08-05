package com.stardew_valley.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.stardew_valley.models.AssetManager;

public class NotificationsView extends GameWindow {

    public NotificationsView() {
        super("Notifications", AssetManager.getAssetManager().getSkin(), "Letter");
    }


    @Override
    public void update() {

    }
}
