package models.shop;

import models.Item;

public class MarnieRanchItem implements Item {
    String name;
    int price;

    public MarnieRanchItem(String name, int price) {
        this.name = name;
        this.price = price;
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
