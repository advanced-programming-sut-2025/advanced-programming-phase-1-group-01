package com.stardew_valley.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetManager {
    private final static Skin skin = new Skin(Gdx.files.internal("craftacular-skin.json"));

    public static Skin getSkin() {
        return skin;
    }
}
