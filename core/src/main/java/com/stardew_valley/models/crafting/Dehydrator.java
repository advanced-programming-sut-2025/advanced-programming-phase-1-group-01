package com.stardew_valley.models.crafting;

import com.badlogic.gdx.graphics.Texture;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.Item;
import com.stardew_valley.models.enums.Emoji;

import java.util.List;

public class Dehydrator extends CraftingDevice {

    @Override
    public String getName() {
        return "Dehydrator";
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
    public String getSymbol() {
        return Emoji.DEHYDRATOR.getSymbol();
    }

    @Override
    public Texture getTexture() {
        return AssetManager.getAssetManager().defaultTexture();
    }
}
