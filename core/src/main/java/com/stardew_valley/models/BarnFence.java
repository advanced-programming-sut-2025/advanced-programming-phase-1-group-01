package com.stardew_valley.models;

import com.badlogic.gdx.graphics.Texture;
import com.stardew_valley.models.building.TileObject;

public class BarnFence implements TileObject {

    @Override
    public String getName() {
        return "";
    }

    @Override
    public int getPrice() {
        return 0;
    }

    @Override
    public Texture getTexture() {
        return AssetManager.getAssetManager().getBarnFence();
    }
}
