package com.stardew_valley.models.farming;

import com.badlogic.gdx.graphics.Texture;
import com.stardew_valley.models.Item;
import com.stardew_valley.models.building.TileObject;

public class Sapling implements TreeSource, TileObject, Item {
    private final SaplingInfo info;

    public Sapling(SaplingInfo info) {
        this.info = info;
    }

    @Override
    public SaplingInfo getInfo() {
        return info;
    }

    @Override
    public String getName() {
        return info.getName();
    }

    @Override
    public int getPrice() {
        return 0;
    }

    @Override
    public Texture getTexture() {
        return info.getTexture();
    }
}
