package com.stardew_valley.models;

import com.badlogic.gdx.graphics.Texture;
import com.stardew_valley.models.building.TileObject;

public class CageFence implements TileObject {
    @Override
    public Texture getTexture() {
        return AssetManager.getAssetManager().getCageFence();
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public int getPrice() {
        return 0;
    }
}
