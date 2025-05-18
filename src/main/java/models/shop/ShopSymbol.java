package models.shop;

import models.building.TileObject;

public enum ShopSymbol implements TileObject {
    BLACKSMITH("blacksmith", "⚒️"),
    JOJA_MART("jojomart", "🏪"),
    PIERRE_GENERAL_STORE("pierre", "🏬"),
    CARPENTER_SHOP("carpenter", "🏠"),
    FISH_SHOP("fishshop", "🐟"),
    MARNIE_RANCH("marnieranch", "🐄"),
    STARDROP_SALOON("saloon", "🍻");

    private final String name;
    private final String symbol;

    ShopSymbol(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return -1;
    }
}
