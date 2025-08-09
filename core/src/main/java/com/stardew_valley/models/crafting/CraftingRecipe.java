package com.stardew_valley.models.crafting;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.stardew_valley.models.Item;

import java.util.Map;

public record CraftingRecipe(String name, Map<String, Integer> ingredients, String ability, int level, int sellPrice, String path) implements Item {
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return -1;
    }

    @Override
    public Texture getTexture() {
        return new Texture(path);
    }

    public Image getImage() {
        Texture texture = new Texture(path);
        return new Image(texture);
    }
}
