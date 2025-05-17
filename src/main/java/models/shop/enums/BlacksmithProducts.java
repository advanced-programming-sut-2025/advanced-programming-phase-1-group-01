package models.shop.enums;

import models.shop.BlackSmithItem;

public enum BlacksmithProducts {
    COPPER_ORE("Copper Ore", 75, -1),
    IRON_ORE("Iron Ore", 150, -1),
    COAL("Coal", 150, -1),
    GOLD_ORE("Gold Ore", 400, -1);

    private final String name;
    private final int price;
    private final int dailyLimit;

    BlacksmithProducts(String name, int price, int dailyLimit) {
        this.name = name;
        this.price = price;
        this.dailyLimit = dailyLimit;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getDailyLimit() {
        return dailyLimit;
    }

    public BlackSmithItem toItem() {
        return new BlackSmithItem(name,price);
    }
}
