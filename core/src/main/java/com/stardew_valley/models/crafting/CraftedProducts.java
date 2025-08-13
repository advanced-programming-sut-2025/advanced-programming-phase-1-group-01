package com.stardew_valley.models.crafting;

import com.badlogic.gdx.graphics.Texture;
import com.stardew_valley.models.Item;
import com.stardew_valley.models.crafting.enums.AllCraftedProductsType;

public class CraftedProducts implements Item {
    private final AllCraftedProductsType type;

    public CraftedProducts(AllCraftedProductsType type) {
        this.type = type;
    }

    @Override
    public String getName() {
        return type.toString().toLowerCase().replaceAll("_", " ");
    }

    @Override
    public int getPrice() {
        return type.getPrice();
    }

    @Override
    public Texture getTexture() {
        return type.getTexture();
    }
}
