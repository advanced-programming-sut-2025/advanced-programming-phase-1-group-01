package com.stardew_valley.models.cooking;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.stardew_valley.models.Item;

import java.util.Map;

public record CookingRecipe(String name, Map<String, Integer> ingredients, String buff, int energy, int sellPrice,
                            String source, String path) implements Item {

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
