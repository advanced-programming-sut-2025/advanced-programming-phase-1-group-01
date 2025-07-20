package com.stardew_valley.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetManager {
    private final static Skin skin = new Skin(Gdx.files.internal("skin1/flat-earth-ui.json"));
    //private final static Skin skin = new Skin(Gdx.files.internal("skin2/craftacular-ui.json"));

    public static Skin getSkin() {
        return skin;
    }
}
