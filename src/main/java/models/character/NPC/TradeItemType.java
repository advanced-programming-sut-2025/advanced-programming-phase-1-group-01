package models.character.NPC;

import models.Item;

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
}
