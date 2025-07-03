package com.stardew_valley.models.shop.enums;

import com.stardew_valley.models.shop.BlackSmithItem;

public enum BlacksmithProducts {
    COPPER_ORE("copper ore", 75, -1),
    IRON_ORE("iron ore", 150, -1),
    COAL("coal", 150, -1),
    GOLD_ORE("gold ore", 400, -1);

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
