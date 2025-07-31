package com.stardew_valley.models.crafting;

import com.badlogic.gdx.graphics.Texture;
import com.stardew_valley.models.Item;

import java.util.Map;

public record CraftingRecipe(String name, Map<String, Integer> ingredients, String ability, int level, int sellPrice) implements Item {
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return -1;
    }
}
