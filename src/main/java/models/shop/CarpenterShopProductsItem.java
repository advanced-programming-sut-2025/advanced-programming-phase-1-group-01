package models.shop;

import models.Item;

public class CarpenterShopProductsItem implements Item {

    private final String name;
    private final int price;
    private final int dailyLimit;

    public CarpenterShopProductsItem(String name, int price, int dailyLimit) {
        this.name = name;
        this.price = price;
        this.dailyLimit = dailyLimit;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return price;
    }
}
