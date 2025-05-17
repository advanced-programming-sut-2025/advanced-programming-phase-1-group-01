package models.shop.enums;

import models.shop.CarpenterShopProductsItem;

public enum CarpenterShopProducts {
    WOOD("Wood", 10, -1),
    STONE("Stone", 20, -1);

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
