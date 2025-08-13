package com.stardew_valley.models.shop;

import com.badlogic.gdx.graphics.Texture;
import com.stardew_valley.models.Item;

public class TheStardropSaloonItem implements Item {
    String name;
    int price;
    Texture texture;

    public TheStardropSaloonItem(String name, int price, Texture texture) {
        this.name = name;
        this.price = price;
        this.texture = texture;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public Texture getTexture() {
        return texture;
    }
}
