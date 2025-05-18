package models.shop;

import models.Item;

public class FishShopItem implements Item {
    String name;
    int price;
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return price;
    }
}
