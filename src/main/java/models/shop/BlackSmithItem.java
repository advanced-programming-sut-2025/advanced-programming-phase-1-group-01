package models.shop;

import models.Item;

public class BlackSmithItem implements Item {
    private final String itemName;
    private final int price;

    public BlackSmithItem(String itemName, int price) {
        this.itemName = itemName;
        this.price = price;
    }

    @Override
    public String getName() {
        return itemName;
    }

    @Override
    public int getPrice() {
        return price;
    }
}
