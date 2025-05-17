package models.shop;

import models.Item;

public class TheStardropSaloonItem implements Item {
    String name;
    int price;

    public TheStardropSaloonItem(String name, int price) {
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
