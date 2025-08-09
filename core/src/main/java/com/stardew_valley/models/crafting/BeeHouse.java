package com.stardew_valley.models.crafting;

import com.badlogic.gdx.graphics.Texture;
import com.stardew_valley.models.Item;
import com.stardew_valley.models.crafting.enums.CraftingRecipes;
import org.w3c.dom.Text;

import java.util.List;

public class BeeHouse extends CraftingDevice {

    @Override
    public String getName() {
        return "BeeHouse";
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
        return CraftingRecipes.BEE_HOUSE.toRecipe().getTexture();
    }

}

