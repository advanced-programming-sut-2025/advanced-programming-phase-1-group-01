package com.stardew_valley.models.farming;

import com.badlogic.gdx.graphics.Texture;
import com.stardew_valley.models.Item;
import com.stardew_valley.models.building.TileObject;

public class Seed implements Item, TileObject {
    private final SeedInfo info;

    public Seed(SeedInfo info) {
        this.info = info;
    }

    public SeedInfo getInfo() {
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

    @Override
    public String getSymbol() {
        return info.getSymbol();
    }
}
