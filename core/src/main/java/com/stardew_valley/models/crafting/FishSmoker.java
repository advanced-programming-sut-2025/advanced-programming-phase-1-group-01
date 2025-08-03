package com.stardew_valley.models.crafting;

import com.stardew_valley.models.Item;

import java.util.List;

public class FishSmoker extends CraftingDevice {

    @Override
    public String getName() {
        return "Fish Smoker";
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

}
