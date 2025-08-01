package com.stardew_valley.models;

import com.badlogic.gdx.graphics.Texture;

public interface Drawable {
    default Texture getTexture() {
        return AssetManager.getAssetManager().getTempTex();
    }
}
