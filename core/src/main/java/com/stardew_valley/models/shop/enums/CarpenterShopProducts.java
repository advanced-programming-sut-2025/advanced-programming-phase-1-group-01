package com.stardew_valley.models.shop.enums;

import com.stardew_valley.models.shop.CarpenterShopProductsItem;

public enum CarpenterShopProducts {
    WOOD("wood", 10, -1),
    STONE("stone", 20, -1);

    private final String name;
    private final int price;
    private final int dailyLimit;

    CarpenterShopProducts(String name, int price, int dailyLimit) {
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

    public CarpenterShopProductsItem toItem() {
        return new CarpenterShopProductsItem(name,price,dailyLimit);
    }
}
