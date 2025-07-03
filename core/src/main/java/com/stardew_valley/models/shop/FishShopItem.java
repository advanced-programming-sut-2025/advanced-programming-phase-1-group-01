package com.stardew_valley.models.shop;

import com.stardew_valley.models.Item;

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
