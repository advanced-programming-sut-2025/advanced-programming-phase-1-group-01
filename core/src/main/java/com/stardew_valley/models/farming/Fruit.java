package com.stardew_valley.models.farming;

import com.badlogic.gdx.graphics.Texture;
import com.stardew_valley.models.Drawable;
import com.stardew_valley.models.Item;

public class Fruit implements Item, Drawable {
    private final FruitInfo info;

    public Fruit(FruitInfo info) {
        this.info = info;
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
