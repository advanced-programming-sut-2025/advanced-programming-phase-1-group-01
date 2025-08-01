package com.stardew_valley.models;

import com.badlogic.gdx.graphics.Texture;

public interface Item {
    String getName();
    int getPrice();
    default Texture getTextureImage() {
        return AssetManager.getAssetManager().getTempTex();
    }
}
