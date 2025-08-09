package com.stardew_valley.models.crafting;

import com.badlogic.gdx.graphics.Texture;
import com.stardew_valley.models.Item;
import com.stardew_valley.models.crafting.enums.CraftingRecipes;

import java.util.List;

public class CheesePress extends CraftingDevice {

    @Override
    public String getName() {
        return "CheesePress";
    }

    @Override
    public boolean canProcess(Item item) {
        return false;
    }

    @Override
    public void insertItem(List<Item> items) {

    }

    @Override
    public Item collectProduct() {
        return null;
    }

    @Override
    public int getRequiredTurns() {
        return 0;
    }

    @Override
    public Texture getTexture() {
        return CraftingRecipes.CHEESE_PRESS.toRecipe().getTexture();
    }

}
