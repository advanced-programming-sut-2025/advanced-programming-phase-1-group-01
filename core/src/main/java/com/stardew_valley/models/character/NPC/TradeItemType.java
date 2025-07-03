package com.stardew_valley.models.character.NPC;

import com.stardew_valley.models.Item;

public enum TradeItemType implements Item {
    WOOL,
    PUMPKIN_PIE,
    PIZZA,
    STONE,
    IRON_ORE,
    COFFEE,
    PICKLE,
    WINE,
    SALAD,
    GRAPE,
    SPAGHETTI,
    WOOD,
    IRON_BAR;

    @Override
    public String getName() {
        return "CookingSystem";
    }

    @Override
    public int getPrice() {
        return 0;
    }
}
