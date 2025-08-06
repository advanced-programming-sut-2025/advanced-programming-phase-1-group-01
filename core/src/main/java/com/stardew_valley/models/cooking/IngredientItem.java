package com.stardew_valley.models.cooking;

import com.badlogic.gdx.graphics.Texture;
import com.stardew_valley.models.Item;

public class IngredientItem implements Item {
    private final IngredientItems info;

    public IngredientItem(IngredientItems info) {
        this.info = info;
    }

    @Override
    public String getName() {
        return info.getName();
    }

    @Override
    public int getPrice() {
        return info.getPrice();
    }

    @Override
    public Texture getTexture() {
        return info.getTexture();
    }
}
